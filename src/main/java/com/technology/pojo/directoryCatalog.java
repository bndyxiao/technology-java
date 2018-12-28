package com.technology.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "directory_catalog")
public class directoryCatalog {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 数据字典分类唯一标识
     */
    @Column(name = "catalog_code")
    private String catalogCode;

    /**
     * 数据字典分类名称
     */
    @Column(name = "catalog_name")
    private String catalogName;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 是否删除 0未删除 1删除
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 是否可用 1可用 0不可用
     */
    private Boolean status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建用户ID
     */
    @Column(name = "create_user_id")
    private Integer createUserId;

    /**
     * 修改用户ID
     */
    @Column(name = "update_user_id")
    private Integer updateUserId;

    /**
     * 排序号
     */
    @Column(name = "catalog_order")
    private Integer catalogOrder;
}