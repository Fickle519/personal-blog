package com.exh.myblog.controller.admin;

import com.exh.myblog.model.AdminUser;
import com.exh.myblog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogCategoryService categoryService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/login")
    public String loginPage(){
        return "admin/login";
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        request.setAttribute("categoryCount", categoryService.getTotalCategories());
        request.setAttribute("blogCount", blogService.getTotalBlogs());
        request.setAttribute("linkCount", linkService.getTotalLinks());
        request.setAttribute("tagCount", tagService.getTotalTags());
        request.setAttribute("commentCount", commentService.getTotalComments());
        return "admin/index";
    }
    @PostMapping("/login")
    public String login(String userName, String password, String verifyCode, HttpSession session){
        if(StringUtils.isEmpty(verifyCode)){
            session.setAttribute("errorMsg","empty verfiy code!");
            return "admin/login";
        }
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            session.setAttribute("errorMsg","empty userName or password!");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        AdminUser adminUser = adminUserService.login(userName,password);
        if(adminUser!=null){
            session.setAttribute("loginUser",adminUser.getNickName());
            session.setAttribute("loginUserId",adminUser.getAdminUserId());
//            session.setMaxInactiveInterval(60*60*2);
            return "redirect:/admin/index";
        }else {
            session.setAttribute("errorMsg", "login failed");
            return "admin/login";
        }
    }
    @GetMapping("/profile")
    public String profile(HttpServletRequest request){
        Integer loginUserId = (Integer)request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.getAdminUserDetailById(loginUserId);
        if(adminUser==null){
            return "admin/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        return "admin/profile";
    }
    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updatePassword(loginUserId, originalPassword, newPassword)) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return "success";
        } else {
            return "修改失败";
        }
    }

    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName) {
        if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
            return "success";
        } else {
            return "修改失败";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }
}
