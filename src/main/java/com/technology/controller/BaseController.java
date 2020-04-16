package com.technology.controller;

import com.technology.common.mongo.Book;
import com.technology.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/4/11 14:21
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
@RestController
public class BaseController {

    @Autowired
    private MongoDbService mongoDbService;

    @PostMapping("/mongo/save")
    public String saveObj(@RequestBody Book book) {return mongoDbService.saveObj(book);}

    @GetMapping("/mongo/findAll")
    public List<Book> findAll() {return mongoDbService.findAll();}

    @GetMapping("/mongo/findOne")
    public Book findOne(@RequestParam String id) {return mongoDbService.getBookById(id);}

    @GetMapping("/mongo/findOneByName")
    public Book findOneByName(@RequestParam String name) {return mongoDbService.getBookByName(name);}

    @PostMapping("/mongo/update")
    public String update(@RequestBody Book book) {return mongoDbService.updateBook(book);}

    @PostMapping("/mongo/delOne")
    public String delOne(@RequestBody Book book) {return mongoDbService.deleteBook(book);}

    @GetMapping("/mongo/delById")
    public String delById(@RequestParam String id) {return mongoDbService.deleteBookById(id);}
}
