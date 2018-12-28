package com.technology.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "directory_item")
public class directoryItem {
    @Id
    private Integer id;

    /**
     * 字典项名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 字典项值
     */
    @Column(name = "item_value")
    private String itemValue;

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
     * 更新时间
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
     * 字典分类ID
     */
    @Column(name = "catalog_id")
    private Integer catalogId;

    /**
     * 排序号
     */
    @Column(name = "item_order")
    private Integer itemOrder;
}