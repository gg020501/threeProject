package com.fh.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMongoDb {

    public static void main(String[] args) {


        TestMongoDb.order();



    }


    public static String insertMany(){
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // 连接到 数据库
        MongoDatabase test = mongoClient.getDatabase("test");
        System.out.println("Connect to database successfully");
        MongoCollection<Document> user = test.getCollection("user");
        List<Document> documentlists = new ArrayList<>();

        //删除
        //user.drop();

        Document document = new Document();
        document.append("name","张三");
        document.append("age",2);
        document.append("weight",120);
        document.append("sex",1);
        documentlists.add(document);

        Document document1 = new Document();
        Map list = new HashMap();
        Map list2 = new HashMap();
        document1.append("name","李四");
        document1.append("weight",200);
        document1.append("age",22);
        document1.append("sex",-1);
        list.put("name","game");
        list.put("code",1);
        list2.put("name","study");
        list2.put("code",3);
        document1.append("hobby","["+list+","+list2+"]");
        documentlists.add(document1);

        Document document2 = new Document();
        document2.append("name","王五");
        document2.append("age",2);
        document2.append("weight",80);
        document2.append("sex",1);
        documentlists.add(document2);

        Document document3 = new Document();
        document3.append("name","麻子");
        document3.append("age",92);
        document3.append("weight",50);
        document3.append("sex",0);
        document3.append("score","China");
        documentlists.add(document3);

        Document document4 = new Document();
        document4.append("name","六六");
        document4.append("age",200);
        document4.append("weight",130);
        document4.append("sex",2);
        document4.append("score",100);
        documentlists.add(document4);

        user.insertMany(documentlists);

        return "新增成功";
    }

    //找到成年人
    public static void selectage(){

        //临时条件对象
        BasicDBObject cond = null;
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // 连接到 数据库
        MongoDatabase test = mongoClient.getDatabase("test");
        MongoCollection<Document> user = test.getCollection("user");
        BasicDBObject query = new BasicDBObject();
        // 查询j不等于3,k大于10的结果集
        query.put("age", new BasicDBObject("$gte", 18));
        FindIterable<Document> findIterable = test.getCollection("user").find(query);
        MongoCursor<Document> iterator = findIterable.iterator();

        while (iterator.hasNext()){
            System.out.println(":"+iterator.next());
        }

    }
    //找到有兴趣爱好的人
    public static void ifhobby(){

        //临时条件对象
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // 连接到 数据库
        MongoDatabase test = mongoClient.getDatabase("test");
        BasicDBObject query = new BasicDBObject();
        // 查询j不等于3,k大于10的结果集
        query.put("hobby", new BasicDBObject("$ne",null));
        FindIterable<Document> findIterable = test.getCollection("user").find(query);
        MongoCursor<Document> iterator = findIterable.iterator();

        while (iterator.hasNext()){
            System.out.println(":"+iterator.next());
        }

    }

    //找到分数为100  或者 体重大于120的人
    public static void ifOr(){

        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // 连接到 数据库
        MongoDatabase test = mongoClient.getDatabase("test");
        //设置条件
        Bson or = Filters.or(new BasicDBObject("weight", new BasicDBObject("$gte", 120)), new BasicDBObject("score", 100));
        FindIterable<Document> findIterable = test.getCollection("user").find(or);
        MongoCursor<Document> iterator = findIterable.iterator();
        while (iterator.hasNext()){
            System.out.println(":"+iterator.next().toJson());
        }
    }

    //查询 China
    public static void FilterfindTest(){

        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // 连接到 数据库
        MongoDatabase test = mongoClient.getDatabase("test");
        //指定查询过滤器
        Bson filter = Filters.eq("score", "China");
        //指定查询过滤器查询
        FindIterable findIterable = test.getCollection("user").find(filter);
        MongoCursor cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    //安照体重排序从高到底   取前两条数据   20
    public static void order(){
        BasicDBObject dbObject = new BasicDBObject();
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // 连接到 数据库
        MongoDatabase test = mongoClient.getDatabase("test");
        //指定查询过滤器查询
        dbObject.put("weight",-1);
        FindIterable findIterable = test.getCollection("user").find().sort(dbObject).limit(2);
        MongoCursor cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }



}