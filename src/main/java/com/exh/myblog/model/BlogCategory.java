package com.exh.myblog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data@NoArgsConstructor@AllArgsConstructor
public class BlogCategory {
    private Integer categoryId;

    private String categoryName;

    private String categoryIcon;

    private Integer categoryRank;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
