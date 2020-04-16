package com.technology.common.mongo;

import org.springframework.stereotype.Repository;

/**
 * @author: huangyibo
 * @Date: 2019/1/31 0:12
 * @Description:
 */
@Repository
public class BookMongoDbDao extends MongoDbDao<Book> {
    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}