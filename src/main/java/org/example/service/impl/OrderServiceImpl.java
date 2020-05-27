package org.example.service.impl;

import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.example.common.exception.CommonServiceException;
import org.example.entity.Item;
import org.example.entity.Order;
import org.example.entity.User;
import org.example.service.IOrderService;
import org.example.web.vo.OrderCreateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order create(OrderCreateVO orderCreateVO) throws CommonServiceException {
        // 判断用户
        Query query = new Query()
                .addCriteria(Criteria.where("_id").is(orderCreateVO.getUserId()));
        query.fields().include("_id").include("name");

        // 注意： Read preference in a transaction must be primary
        User user = mongoTemplate.findOne(query, User.class);
        if (user == null || StringUtils.isEmpty(user.getId())) {
            throw new CommonServiceException(3001, "用户不存在");
        }

        // TODO 需要上锁 -> 分布式锁实现
        // 判断库存
        query = new Query()
                .addCriteria(Criteria.where("_id").is(orderCreateVO.getItemId()));
        query.fields().include("_id").include("name").include("sku").include("price");
        Item item = mongoTemplate.findOne(query, Item.class);
        if (item == null || StringUtils.isEmpty(item.getId())) {
            throw new CommonServiceException(3002, "商品不存在");
        }

        if (item.getSku() < orderCreateVO.getCount()) {
            throw new CommonServiceException(3003, "库存不足");
        }

        // 扣库存
        Update update = new Update();
        update.set("sku", item.getSku() - orderCreateVO.getCount());
        UpdateResult result = mongoTemplate.updateFirst(query, update, Item.class);
        if (result.getModifiedCount() == 0) {
            throw new CommonServiceException(3004, "扣库存失败");
        }

        // 创建订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setUserName(user.getName());
        order.setItemId(item.getId());
        order.setItemName(item.getName());
        order.setCount(orderCreateVO.getCount());
        order.setUnitPrice(item.getPrice());
        order.setTotalPrice(item.getPrice().multiply(BigDecimal.valueOf(orderCreateVO.getCount())));
        order.setCreateTime(LocalDateTime.now());
        order = mongoTemplate.insert(order);

        // TODO 释放分布式锁
        return order;
    }
}
