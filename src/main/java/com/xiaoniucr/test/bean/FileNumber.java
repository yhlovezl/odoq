package com.xiaoniucr.test.bean;

import lombok.Data;

import java.util.List;

/**
 * @author yanghl
 * @date 2019/12/16 11:20
 */
@Data
public class FileNumber {

    private Long type;

    private String value;

    private List<OptionEntity> optionList;


}
