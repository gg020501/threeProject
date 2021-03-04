package com.fh.service.Impl;

import com.fh.model.User;
import com.fh.service.UserSerivce;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserSerivce {

    @Resource
    private MongoTemplate m;

    @Override
    public User selectNameOne(String name) {
        User user = m.findOne(new Query(Criteria.where("name").is(name)),User.class);

        return user;
    }

    @Override
    public void insertUser(User user) {
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // 连接到 数据库
        MongoDatabase test = mongoClient.getDatabase("test");
        MongoCollection<Document> add = test.getCollection("user");
        Document document = new Document();
        document.append("name",user.getName());
        document.append("password",user.getPassword());
        document.append("data",user.getCreateDate());
        add.insertOne(document);
    }


}
