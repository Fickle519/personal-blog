package com.exh.myblog.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data@NoArgsConstructor@AllArgsConstructor
public class BlogDetailVO {
    private Long blogId;

    private String blogTitle;

    private Integer blogCategoryId;

    private Integer commentCount;

    private String blogCategoryIcon;

    private String blogCategoryName;

    private String blogCoverImage;

    private Long blogViews;

    private List<String> blogTags;

    private String blogContent;

    private Byte enableComment;

    private Date createTime;

}
