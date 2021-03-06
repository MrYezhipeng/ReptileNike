package com.nike.reptile.reptile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nike.reptile.constant.URLConstant;
import com.nike.reptile.entity.ShoeEntity;
import com.nike.reptile.util.HttpUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

/**
 * @program: reptile
 * @description: 爬虫工具类
 * @author: Geekye
 * @create: 2018-11-28 16:53
 **/
public class ReptileUtil {

//    public List<>

    public List<ShoeEntity> getNikeResource(String URL, Integer pageNum) {
        List<ShoeEntity> shoes = new ArrayList<>();
        if (pageNum <= 0) {
            pageNum = 1;
        }
        String result = HttpUtil.sendGet(MessageFormat.format(URL, pageNum));
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("sections");
        JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
        JSONArray jsonArray1 = jsonObject1.getJSONArray("items");
        ListIterator it = jsonArray1.listIterator();
        while (it.hasNext()) {
            JSONObject j = (JSONObject) it.next();
            ShoeEntity shoeEntity = JSONObject.parseObject(j.toJSONString(), ShoeEntity.class);
            shoes.add(shoeEntity);
        }
        return shoes;
    }

    public static void main(String[] args) {

        Set set = new HashSet();
        set.addAll(Arrays.asList(1,2));
        System.out.println(set.add(3));
//        List<ShoeEntity> shoes = new ReptileUtil().getNikeResource(URLConstant.NIKE_MAN_URL, 0);
//        int i = 0;
//        Set set = new HashSet();
//        for (ShoeEntity shoe : shoes
//                ) {
//            Boolean flag = set.add(shoe.hashCode());
//            System.out.println("flag：" + flag + ".hashcode:" + shoe.hashCode());
//            i++;
//        }
//
//        System.out.println("该页有多少鞋：" + i + "个！");


    }
}
