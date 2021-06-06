package com.exh.myblog.service;

import com.exh.myblog.model.BlogLink;
import com.exh.myblog.util.PageQueryUtil;
import com.exh.myblog.util.PageResult;

import java.util.List;
import java.util.Map;

public interface LinkService {


    PageResult getBlogLinkPage(PageQueryUtil pageUtil);

    int getTotalLinks();

    Boolean saveLink(BlogLink link);

    BlogLink selectById(Integer id);

    Boolean updateLink(BlogLink tempLink);

    Boolean deleteBatch(Integer[] ids);

    /**
     * 返回友链页面所需的所有数据
     *
     * @return
     */
    Map<Byte, List<BlogLink>> getLinksForLinkPage();
}
