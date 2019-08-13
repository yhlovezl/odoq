package com.xiaoniucr.reflect;

import com.xiaoniucr.entity.Cat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * java反射动态赋值
 */
public class ReflectInvoke {


    /**
     * 批量set属性值
     * @param obj
     * @param params
     * @return
     */
    public static Object invoke(Object obj,Map params){

        Class clazz = obj.getClass();
        //获取对象所有字段
        Field[] fields = clazz.getDeclaredFields();
        if (fields!=null && fields.length>0){
            for(Field field : fields){
                //字段名
                String fieldName = field.getName();
                //获取字段类型（int？string？）
                Class type = getType(field.getType().toString());
                try {
                    Method method = clazz.getMethod("set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1),type);
                    method.setAccessible(true);
                    method.invoke(obj,params.get(fieldName));
                    method.setAccessible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    /**
     * 获取字段类型
     * @param type
     * @return
     */
    public static Class getType(String type){

        if(type.endsWith("String")){
            return String.class;
        }else if(type.endsWith("int")){
            return int.class;
        }else if(type.endsWith("Integer")){
            return Integer.class;
        }
        return null;
    }




    public static void main(String[] args) {

        Map<String,Object> map = new HashMap<>(10);
        map.put("name","tom");
        map.put("height",20);
        map.put("weight",20);
        Cat car = new Cat();
        invoke(car,map);
        System.out.println(car);

    }
}
