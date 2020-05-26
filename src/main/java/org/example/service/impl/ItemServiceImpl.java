package org.example.service.impl;

import org.example.entity.Item;
import org.example.service.IItemService;
import org.example.web.dto.ItemListDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ItemListDTO> list(Integer pageNum, Integer pageSize, String keyword, String sortDirection, String sortColumn) {
        Query query = new Query();
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        query.with(pageable);

        if (!StringUtils.isEmpty(keyword)) {
            Pattern pattern = Pattern.compile("^.*" + keyword + ".*$", Pattern.CASE_INSENSITIVE);
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("name").regex(pattern),
                    Criteria.where("description").regex(pattern));
            query.addCriteria(criteria);
        }

        if (!StringUtils.isEmpty(sortColumn)) {
            query.with(Sort.by("ASC".equals(sortDirection) ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn));
        }

        // 指定字段
        query.fields().exclude("detail");

        List<Item> itemList = mongoTemplate.find(query, Item.class);
        List<ItemListDTO> result = new ArrayList<>();
        if (itemList != null && !itemList.isEmpty()) {
            itemList.forEach(item -> {
                ItemListDTO i = new ItemListDTO();
                BeanUtils.copyProperties(item, i);
                result.add(i);
            });
        }

        return result;
    }
}
