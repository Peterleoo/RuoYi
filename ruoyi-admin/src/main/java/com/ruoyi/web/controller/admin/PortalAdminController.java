package com.ruoyi.web.controller.admin;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.PortalMagazine;
import com.ruoyi.domain.PortalUniversity;
import com.ruoyi.domain.PortalMember;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台管理门户控制器
 */
@Controller
@RequestMapping("/admin/portal")
public class PortalAdminController extends BaseController {

    /**
     * 杂志管理页面
     */
    @GetMapping("/magazine")
    public String magazine() {
        return "admin/portal/magazine";
    }

    /**
     * 大学管理页面
     */
    @GetMapping("/university")
    public String university() {
        return "admin/portal/university";
    }

    /**
     * 会员管理页面
     */
    @GetMapping("/member")
    public String member() {
        return "admin/portal/member";
    }

    /**
     * 获取杂志列表
     */
    @PostMapping("/magazine/list")
    @ResponseBody
    public TableDataInfo magazineList(PortalMagazine magazine) {
        startPage();
        List<PortalMagazine> list = new ArrayList<>();
        // 模拟数据
        PortalMagazine mag1 = new PortalMagazine();
        mag1.setMagazineId(1L);
        mag1.setMagazineName("湖南老年人杂志");
        mag1.setIssueNumber("2024年第1期");
        mag1.setPublishDate("2024-01-01");
        mag1.setStatus("0");
        list.add(mag1);
        return getDataTable(list);
    }

    /**
     * 获取大学列表
     */
    @PostMapping("/university/list")
    @ResponseBody
    public TableDataInfo universityList(PortalUniversity university) {
        startPage();
        List<PortalUniversity> list = new ArrayList<>();
        // 模拟数据
        PortalUniversity uni1 = new PortalUniversity();
        uni1.setUniversityId(1L);
        uni1.setUniversityName("长沙市老干部大学");
        uni1.setCity("长沙市");
        uni1.setStatus("0");
        list.add(uni1);
        return getDataTable(list);
    }

    /**
     * 获取会员列表
     */
    @PostMapping("/member/list")
    @ResponseBody
    public TableDataInfo memberList(PortalMember member) {
        startPage();
        List<PortalMember> list = new ArrayList<>();
        // 模拟数据
        PortalMember mem1 = new PortalMember();
        mem1.setMemberId(1L);
        mem1.setMemberName("张三");
        mem1.setMemberAccount("zhangsan");
        mem1.setMemberLevel("0");
        mem1.setStatus("0");
        list.add(mem1);
        return getDataTable(list);
    }

    /**
     * 新增杂志
     */
    @PostMapping("/magazine/add")
    @ResponseBody
    public AjaxResult addMagazine(@RequestBody PortalMagazine magazine) {
        // 实际开发中这里应该调用Service层保存数据
        return AjaxResult.success("新增杂志成功");
    }

    /**
     * 修改杂志
     */
    @PostMapping("/magazine/edit")
    @ResponseBody
    public AjaxResult editMagazine(@RequestBody PortalMagazine magazine) {
        // 实际开发中这里应该调用Service层更新数据
        return AjaxResult.success("修改杂志成功");
    }

    /**
     * 删除杂志
     */
    @PostMapping("/magazine/remove")
    @ResponseBody
    public AjaxResult removeMagazine(Long[] magazineIds) {
        // 实际开发中这里应该调用Service层删除数据
        return AjaxResult.success("删除杂志成功");
    }

    /**
     * 新增大学
     */
    @PostMapping("/university/add")
    @ResponseBody
    public AjaxResult addUniversity(@RequestBody PortalUniversity university) {
        // 实际开发中这里应该调用Service层保存数据
        return AjaxResult.success("新增大学成功");
    }

    /**
     * 修改大学
     */
    @PostMapping("/university/edit")
    @ResponseBody
    public AjaxResult editUniversity(@RequestBody PortalUniversity university) {
        // 实际开发中这里应该调用Service层更新数据
        return AjaxResult.success("修改大学成功");
    }

    /**
     * 删除大学
     */
    @PostMapping("/university/remove")
    @ResponseBody
    public AjaxResult removeUniversity(Long[] universityIds) {
        // 实际开发中这里应该调用Service层删除数据
        return AjaxResult.success("删除大学成功");
    }

    /**
     * 新增会员
     */
    @PostMapping("/member/add")
    @ResponseBody
    public AjaxResult addMember(@RequestBody PortalMember member) {
        // 实际开发中这里应该调用Service层保存数据
        return AjaxResult.success("新增会员成功");
    }

    /**
     * 修改会员
     */
    @PostMapping("/member/edit")
    @ResponseBody
    public AjaxResult editMember(@RequestBody PortalMember member) {
        // 实际开发中这里应该调用Service层更新数据
        return AjaxResult.success("修改会员成功");
    }

    /**
     * 删除会员
     */
    @PostMapping("/member/remove")
    @ResponseBody
    public AjaxResult removeMember(Long[] memberIds) {
        // 实际开发中这里应该调用Service层删除数据
        return AjaxResult.success("删除会员成功");
    }
}