package com.exh.myblog.service.impl;

import com.exh.myblog.mapper.TagMapper;
import com.exh.myblog.mapper.TagRelationMapper;
import com.exh.myblog.model.BlogTag;
import com.exh.myblog.model.BlogTagCount;
import com.exh.myblog.service.TagService;
import com.exh.myblog.util.PageQueryUtil;
import com.exh.myblog.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagRelationMapper tagRelationMapper;

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
        List<BlogTag> tagList =  tagMapper.findTagList(pageUtil);
        int total = tagMapper.getTotalTags(pageUtil);
        PageResult pageResult = new PageResult(tagList,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public boolean saveTag(String tagName) {
        BlogTag blogTag = tagMapper.selectByTagName(tagName);
        if(blogTag==null){
            BlogTag tag = new BlogTag();
            tag.setTagName(tagName);
            return tagMapper.insertSelective(tag)>0;
        }
        return false;
    }

    @Override
    public boolean deleteBatch(Integer[] ids) {
        List<Long> tagIds = tagRelationMapper.selectDistinctTagIds(ids);
        if(!CollectionUtils.isEmpty(tagIds)){
            return false;
        }
        return tagMapper.deleteBatch(ids)>0;
    }


    public int getTotalTags(){
        return tagMapper.getTotalTags(null);
    }

    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        return tagMapper.getTagCount();
    }


}
