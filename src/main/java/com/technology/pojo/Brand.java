package com.technology.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: huangzhb
 * @Date: 2018年11月19日 14:42:17
 * @Description:
 */
@Data
@Table(name = "brand")
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    //@Column(name = "`desc`")
    //private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }*/
}
