package com.exh.myblog.service;

import com.exh.myblog.model.BlogTagCount;
import com.exh.myblog.util.PageQueryUtil;
import com.exh.myblog.util.PageResult;

import java.util.List;

public interface TagService {


    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    boolean saveTag(String tagName);

    boolean deleteBatch(Integer[] ids);
    int getTotalTags();
    List<BlogTagCount> getBlogTagCountForIndex();
}
