package com.technology.service;

import com.technology.common.mongo.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/4/11 14:05
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
@Slf4j
@Service
public class MongoDbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存对象
     * @param book
     * @return
     */
    public String saveObj(Book book) {
        log.info("----------------mongodb save start");
        book.setCreateTime(new Date());
        mongoTemplate.save(book);
        return "添加成功";
    }

    public List<Book> findAll() {
        log.info("----------------mongodb find all");
        List<Book> list = mongoTemplate.findAll(Book.class);
        return list;
    }


    public Book getBookById(String id) {
        log.info("----------------mongodb get by id");
        Book book = mongoTemplate.findById(id, Book.class);
        Query query = new Query(Criteria.where("_id").is(id));
        book = mongoTemplate.findOne(query, Book.class);
        return book;
    }

    public Book getBookByName(String name) {
        log.info("----------------mongodb get by name");
        Query query = new Query(Criteria.where("name").is(name));
        Book book = mongoTemplate.findOne(query, Book.class);
        return book;
    }


    public String updateBook(Book book) {
        log.info("----------------mongodb update start");
        Query query = new Query(Criteria.where("_id").is(book.getId()));
        Update update = new Update().set("publish", book.getPublish())
                .set("name", book.getName())
                .set("info", book.getInfo());
        mongoTemplate.updateFirst(query, update, Book.class);

        return "success";
    }

    public String deleteBook(Book book) {
        log.info("----------------mongodb delete start");
        mongoTemplate.remove(book);
        return "success";
    }

    public String deleteBookById(String id) {
        log.info("----------------mongodb delete by id");
        Book book = getBookById(id);
        deleteBook(book);
        return "success";
    }
}
