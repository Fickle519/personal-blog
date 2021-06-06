package com.exh.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data@NoArgsConstructor@AllArgsConstructor
public class BlogConfig {
    private String configName;

    private String configValue;

    private Date createTime;

    private Date updateTime;

}