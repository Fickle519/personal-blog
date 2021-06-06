package com.exh.myblog.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data@NoArgsConstructor@AllArgsConstructor
public class SimpleBlogListVO implements Serializable {

    private Long blogId;

    private String blogTitle;
}
