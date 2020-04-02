package com.technology.pojo;

import com.technology.util.excel.annotation.ExcelField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @ExcelField(title = "名称")
    @Column(name = "name")
    private String name;

    /*@ExcelField(title="年龄",value="test.age", width = 4)
    private Test test;*/

    /*@ExcelField(title = "注册时间", pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;*/

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
}
