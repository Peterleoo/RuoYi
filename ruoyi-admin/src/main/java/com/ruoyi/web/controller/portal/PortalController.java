package com.ruoyi.web.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 门户网站控制器
 */
@Controller
@RequestMapping("/portal")
public class PortalController {

    /**
     * 首页
     */
    @GetMapping({"", "index"})
    public String index() {
        return "portal/index";
    }

    /**
     * 杂志天地
     */
    @GetMapping("magazine")
    public String magazine() {
        return "portal/magazine";
    }

    /**
     * 大学联盟
     */
    @GetMapping("university")
    public String university() {
        return "portal/university";
    }

    /**
     * 会员中心
     */
    @GetMapping("member")
    public String member() {
        return "portal/member";
    }

    /**
     * 银龄动态
     */
    @GetMapping("news")
    public String news() {
        return "portal/news";
    }

    /**
     * 老干部大学协会
     */
    @GetMapping("association")
    public String association() {
        return "portal/association";
    }

    /**
     * 联系我们
     */
    @GetMapping("contact")
    public String contact() {
        return "portal/contact";
    }

    /**
     * 登录页面
     */
    @GetMapping("login")
    public String login() {
        return "portal/login";
    }

    /**
     * 下载中心
     */
    @GetMapping("download")
    public String download() {
        return "portal/download";
    }

    /**
     * 在线课程
     */
    @GetMapping("course")
    public String course() {
        return "portal/course";
    }

    /**
     * 活动报名
     */
    @GetMapping("activity")
    public String activity() {
        return "portal/activity";
    }

    /**
     * 隐私政策
     */
    @GetMapping("privacy")
    public String privacy() {
        return "portal/privacy";
    }

    /**
     * 使用协议
     */
    @GetMapping("terms")
    public String terms() {
        return "portal/terms";
    }
}
