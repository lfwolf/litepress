package cn.stylefeng.guns.modular.cms.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.cms.entity.CmsBanner;
import cn.stylefeng.guns.modular.cms.service.ICmsBannerService;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 横幅管理控制器
 *
 * @author fengshuonan
 * @Date 2019-11-04 20:09:13
 */
@Controller
@RequestMapping("/CmsBanner")
public class CmsBannerController extends BaseController {

    private String PREFIX = "/modular/cms/CmsBanner/";

    @Autowired
    private ICmsBannerService CmsBannerService;

    /**
     * 跳转到横幅管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "CmsBanner.html";
    }

    /**
     * 跳转到添加横幅管理
     */
    @RequestMapping("/CmsBanner_add")
    public String CmsBannerAdd() {
        return PREFIX + "CmsBanner_add.html";
    }

    /**
     * 跳转到修改横幅管理
     */
    @RequestMapping("/CmsBanner_update/{id}")
    public String CmsBannerUpdate(@PathVariable Long id, Model model) {
        CmsBanner CmsBanner = CmsBannerService.getById(id);
        //model.addAllAttributes(BeanUtil.beanToMap(CmsBanner));
        model.addAttribute("item", CmsBanner);
        return PREFIX + "CmsBanner_edit.html";
    }

    /**
     * 获取横幅管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	Page page = LayuiPageFactory.defaultPage();
        return LayuiPageFactory.createPageInfo(CmsBannerService.pageMaps(page));
    }

    /**
     * 新增横幅管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CmsBanner CmsBanner) {
        CmsBannerService.save(CmsBanner);
        return SUCCESS_TIP;
    }

    /**
     * 删除横幅管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
        CmsBannerService.removeById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改横幅管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CmsBanner CmsBanner) {
        CmsBannerService.updateById(CmsBanner);
        return SUCCESS_TIP;
    }

    /**
     * 横幅管理详情
     */
    @RequestMapping(value = "/detail/{CmsBannerId}")
    @ResponseBody
    public Object detail(@PathVariable("CmsBannerId") Integer CmsBannerId) {
        return CmsBannerService.getById(CmsBannerId);
    }
}
