package org.justice;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;

public class Main {
    public static void main(String[] args) {
        Example example = new Example();
        example.setAge(66);
        ExampleParam.AddParam addParam = BeanUtil.copyProperties(example,ExampleParam.AddParam.class);
        System.out.println(JSONUtil.toJsonStr(addParam));
    }
}
