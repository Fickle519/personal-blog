package com.exh.myblog.mapper;

import com.exh.myblog.model.BlogTag;
import com.exh.myblog.model.BlogTagCount;
import com.exh.myblog.util.PageQueryUtil;

import java.util.List;

public interface TagMapper {

    int deleteByPrimaryKey(Integer tagId);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    BlogTag selectByPrimaryKey(Integer tagId);

    BlogTag selectByTagName(String tagName);

    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);

    List<BlogTag> findTagList(PageQueryUtil pageUtil);

    List<BlogTagCount> getTagCount();

    int getTotalTags(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);

    int batchInsertBlogTag(List<BlogTag> tagList);
}
