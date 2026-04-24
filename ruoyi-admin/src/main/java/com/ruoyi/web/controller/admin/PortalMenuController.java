package com.ruoyi.web.controller.admin;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台管理菜单控制器
 */
@Controller
@RequestMapping("/admin/portal/menu")
public class PortalMenuController extends BaseController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 菜单管理页面
     */
    @GetMapping("/index")
    public String index() {
        return "admin/portal/menu";
    }

    /**
     * 获取菜单列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysMenu menu) {
        startPage();
        List<SysMenu> list = menuService.selectMenuList(menu, getUserId());
        return getDataTable(list);
    }
}