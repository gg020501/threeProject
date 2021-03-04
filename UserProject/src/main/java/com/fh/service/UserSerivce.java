package com.fh.service;

import com.fh.model.User;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.List;

public interface UserSerivce {
    User selectNameOne(String name);

    void insertUser(User user);
}
