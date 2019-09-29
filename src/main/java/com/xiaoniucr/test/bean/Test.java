package com.xiaoniucr.test.bean;

import com.xiaoniucr.entity.Cat;
import com.xiaoniucr.entity.Person;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class Test  {


    public static void main(String[] args) {


        Cat user = new Cat("张三",18,10);
        Person person = new Person();

        try {
            BeanUtils.copyProperties(person,user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(person.getName());

        //        fileDraft.setVersion(1L);
//        fileDraft.setEnterpiseProductId(file.getEnterpiseProductId());
//        fileDraft.setEnterpiseId(file.getEnterpiseId());
//        fileDraft.setEnterpiseName(file.getEnterpiseName());
//        fileDraft.setProcessId(file.getProcessId());
//        fileDraft.setProcessName(file.getProcessName());
//        fileDraft.setName(file.getName());
//        fileDraft.setFileFormat(file.getFileFormat());
//        fileDraft.setSize(file.getSize());
//        fileDraft.setEditNumber(file.getEditNumber());
//        fileDraft.setType(file.getType());

    }
}
