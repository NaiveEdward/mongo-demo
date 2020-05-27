package org.example.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.example.common.exception.CommonServiceException;
import org.example.entity.Contact;
import org.example.entity.User;
import org.example.service.IUserService;
import org.example.web.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User register(UserRegisterVO userRegisterVO) throws CommonServiceException {
        Query query = new Query()
                .addCriteria(Criteria.where("account").is(userRegisterVO.getAccount()));
        // 指定字段查询
        query.fields().include("account");

        List<User> userList = mongoTemplate.find(query, User.class);
        if (!userList.isEmpty()) {
            throw new CommonServiceException(2001, "该账号已存在");
        }

        User user = new User();
        BeanUtils.copyProperties(userRegisterVO, user);
        user = mongoTemplate.insert(user);

        user.setPassword(null);

        return user;
    }

    @Override
    public User login(UserLoginVO userLoginVO) throws CommonServiceException {
        Query query = new Query()
                .addCriteria(Criteria.where("account").is(userLoginVO.getAccount()));
        query.fields().exclude("tags");
        query.fields().exclude("contacts");

        User user = mongoTemplate.findOne(query, User.class);

        if (user == null) {
            throw new CommonServiceException(2002, "该账号不存在");
        }

        if (!userLoginVO.getPassword().equals(user.getPassword())) {
            throw new CommonServiceException(2003, "密码错误");
        }

        return user;
    }

    @Override
    public boolean saveInfo(UserInfoVO userInfoVO) {
        Query query = new Query()
                .addCriteria(Criteria.where("_id").is(userInfoVO.getId()));

        Update update = new Update();
        update.set("name", userInfoVO.getName());
        update.set("age", userInfoVO.getAge());

        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);

        return result.getModifiedCount() > 0;
    }

    @Override
    public boolean remove(String id) {
        Query query = new Query()
                .addCriteria(Criteria.where("_id").is(id));

        DeleteResult result = mongoTemplate.remove(query, User.class);

        return result.getDeletedCount() > 0;
    }

    @Override
    public long saveTag(UserTagVO userTagVO) {
        Query query = new Query()
                .addCriteria(Criteria.where("_id").is(userTagVO.getId()));

        String[] tags = userTagVO.getTag().split(",");

        Update update = new Update();
        update.set("tags", tags);

        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);

        return result.getModifiedCount();
    }

    @Override
    public long saveContact(UserContactVO contactVO) {
        Query query = Query.query(Criteria.where("_id").is(contactVO.getUserId()));

        Update update = new Update();
        if (StringUtils.isEmpty(contactVO.getId())) {
            Contact contact = new Contact();
            BeanUtils.copyProperties(contactVO, contact);
            contact.setId(new ObjectId().toString());
            update.push("contacts", contact);
        } else {
            query.addCriteria(Criteria.where("contacts.id").is(contactVO.getId()));
            update.set("contacts.$.type", contactVO.getType());
            update.set("contacts.$.contact", contactVO.getAccount());
        }

        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        return result.getModifiedCount();
    }

    @Override
    public boolean removeContact(String userId, String id) {
        Query query = new Query()
                .addCriteria(Criteria.where("_id").is(userId));

        BasicDBObject contact = new BasicDBObject();
        contact.put("id", id);

        Update update = new Update();
        update.pull("contacts", contact);

        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);

        return result.getModifiedCount() > 0;
    }
}
