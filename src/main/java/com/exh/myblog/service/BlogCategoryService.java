package com.exh.myblog.service;

import com.exh.myblog.model.BlogCategory;
import com.exh.myblog.util.PageQueryUtil;
import com.exh.myblog.util.PageResult;

import java.util.List;

public interface BlogCategoryService {
    /**
     * 查询分类的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    int getTotalCategories();

    /**
     * 添加分类数据
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean saveCategory(String categoryName,String categoryIcon);

    Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    Boolean deleteBatch(Integer[] ids);

    List<BlogCategory> getAllCategories();
}
