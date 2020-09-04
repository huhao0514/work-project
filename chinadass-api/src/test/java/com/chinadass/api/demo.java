package com.chinadass.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by: huhao
 * Date: 2020/8/7
 */
public class demo {
    public static void main(String[] args) {
        String base="data:image/png;base64,"+"GnZPG+7/gm/tKTpUvCqeWDeSp";
        base=base.replaceAll("data:image/png;base64,","");
        System.out.println(base);
        List<String> list = Arrays.asList("aa","ddd");

        list = list.stream().filter(item->item.length() > 2).collect(Collectors.toList());

        System.out.println(list);
    }
}
