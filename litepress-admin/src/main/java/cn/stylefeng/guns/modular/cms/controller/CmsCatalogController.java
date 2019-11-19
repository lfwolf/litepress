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
import cn.stylefeng.guns.modular.cms.entity.CmsCatalog;
import cn.stylefeng.guns.modular.cms.service.ICmsCatalogService;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 分类管理控制器
 *
 * @author fengshuonan
 * @Date 2019-11-04 11:09:55
 */
@Controller
@RequestMapping("/CmsCatalog")
public class CmsCatalogController extends BaseController {

    private String PREFIX = "/modular/cms/CmsCatalog/";

    @Autowired
    private ICmsCatalogService CmsCatalogService;

    /**
     * 跳转到分类管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "CmsCatalog.html";
    }

    /**
     * 跳转到添加分类管理
     */
    @RequestMapping("/CmsCatalog_add")
    public String CmsCatalogAdd() {
        return PREFIX + "CmsCatalog_add.html";
    }

    /**
     * 跳转到修改分类管理
     */
    @RequestMapping("/CmsCatalog_update/{id}")
    public String CmsCatalogUpdate(@PathVariable Long id, Model model) {
        CmsCatalog CmsCatalog = CmsCatalogService.getById(id);
        //model.addAllAttributes(BeanUtil.beanToMap(CmsCatalog));
        model.addAttribute("item", CmsCatalog);
        return PREFIX + "CmsCatalog_edit.html";
    }

    /**
     * 获取分类管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	Page page = LayuiPageFactory.defaultPage();
        return LayuiPageFactory.createPageInfo(CmsCatalogService.pageMaps(page));
    }

    /**
     * 新增分类管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CmsCatalog CmsCatalog) {
        CmsCatalogService.save(CmsCatalog);
        return SUCCESS_TIP;
    }

    /**
     * 删除分类管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
        CmsCatalogService.removeById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改分类管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CmsCatalog CmsCatalog) {
        CmsCatalogService.updateById(CmsCatalog);
        return SUCCESS_TIP;
    }

    /**
     * 分类管理详情
     */
    @RequestMapping(value = "/detail/{CmsCatalogId}")
    @ResponseBody
    public Object detail(@PathVariable("CmsCatalogId") Integer CmsCatalogId) {
        return CmsCatalogService.getById(CmsCatalogId);
    }
}
