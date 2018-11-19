package com.technology.controller;

import com.technology.mapper.BrandMapper;
import com.technology.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: huangzhb
 * @Date: 2018年11月19日 13:37:39
 * @Description:
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    //@Autowired
    //private BrandMapper brandMapper;

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        return "home";
    }

    @RequestMapping("/queryBrandList")
    @ResponseBody
    public Map<String, Object> queryBrandList(HttpServletRequest request) {

        Map<String, Object> result = new HashMap<String, Object>();

        List<Brand> brands = new ArrayList<Brand>();

        // 获取当前页
        String current = request.getParameter("pageNumber");
        String text = request.getParameter("selectPage_text");
        if (StringUtils.isBlank(current)) {
            current = "1";
        }

        result.put("totalRow", 11);
        result.put("pageNumber", current);
        result.put("list", brands);
        result.put("totalPage", 2);

        if (StringUtils.isBlank(current)) {
            return result;
        }

        int currentIndex = Integer.valueOf(current);
        for (int i = (currentIndex - 1) * 10;  i < (currentIndex - 1) * 10 + 10 && i < list.size(); i++) {
            brands.add(list.get(i));
        }

        return result;
    }

    private static List<Brand> list = new ArrayList<Brand>();

    static {
        String[] brands = {
                "奥迪",
                "奔驰",
                "宝马",
                "QQ",
                "法拉利",
                "保时捷",
                "现代",
                "福特",
                "本田",
                "东风",
                "红旗"
        };

        String[] descs = {
                "奥迪",
                "奔驰",
                "宝马",
                "QQ",
                "法拉利",
                "保时捷",
                "现代",
                "福特",
                "本田",
                "东风",
                "红旗"
        };
        Brand brand = null;
        for (int i = 0; i < 11; i++) {
            brand = new Brand();
            brand.setId(i + 1);
            brand.setName(brands[i]);
            brand.setDesc(descs[i]);

            list.add(brand);
        }
    }
}
