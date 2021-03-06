package com.exh.myblog.controller.admin;

import com.exh.myblog.model.BlogTag;
import com.exh.myblog.service.TagService;
import com.exh.myblog.util.PageQueryUtil;
import com.exh.myblog.util.Result;
import com.exh.myblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tagPage(HttpServletRequest request) {
        request.setAttribute("path", "tags");
        return "admin/tag";
    }

    @GetMapping("/tags/list")
    @ResponseBody
    public Result list(@RequestParam Map<String,Object> params){
        if(StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(tagService.getBlogTagPage(pageUtil));
    }

    @PostMapping("/tags/save")
    @ResponseBody
    public Result save(String tagName){
        if(StringUtils.isEmpty(tagName)){
            return ResultGenerator.genFailResult("参数异常");
        }
        if(tagService.saveTag(tagName)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("标签名重复");
        }
    }
    @PostMapping("/tags/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if(ids.length<1){
            return ResultGenerator.genFailResult("参数异常");
        }
        if(tagService.deleteBatch(ids)){
            return ResultGenerator.genSuccessResult();
        }else {
            return  ResultGenerator.genFailResult("有关联数据请勿删除!");
        }
    }



}
