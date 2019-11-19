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
import cn.stylefeng.guns.modular.cms.entity.CmsAlbum;
import cn.stylefeng.guns.modular.cms.service.ICmsAlbumService;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 专辑管理控制器
 *
 * @author fengshuonan
 * @Date 2019-11-04 20:28:52
 */
@Controller
@RequestMapping("/CmsAlbum")
public class CmsAlbumController extends BaseController {

    private String PREFIX = "/modular/cms/CmsAlbum/";

    @Autowired
    private ICmsAlbumService CmsAlbumService;

    /**
     * 跳转到专辑管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "CmsAlbum.html";
    }

    /**
     * 跳转到添加专辑管理
     */
    @RequestMapping("/CmsAlbum_add")
    public String CmsAlbumAdd() {
        return PREFIX + "CmsAlbum_add.html";
    }

    /**
     * 跳转到修改专辑管理
     */
    @RequestMapping("/CmsAlbum_update/{id}")
    public String CmsAlbumUpdate(@PathVariable Long id, Model model) {
        CmsAlbum CmsAlbum = CmsAlbumService.getById(id);
        //model.addAllAttributes(BeanUtil.beanToMap(CmsAlbum));
        model.addAttribute("item", CmsAlbum);
        return PREFIX + "CmsAlbum_edit.html";
    }

    /**
     * 获取专辑管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	Page page = LayuiPageFactory.defaultPage();
        return LayuiPageFactory.createPageInfo(CmsAlbumService.pageMaps(page));
    }

    /**
     * 新增专辑管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CmsAlbum CmsAlbum) {
        CmsAlbumService.save(CmsAlbum);
        return SUCCESS_TIP;
    }

    /**
     * 删除专辑管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
        CmsAlbumService.removeById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改专辑管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CmsAlbum CmsAlbum) {
        CmsAlbumService.updateById(CmsAlbum);
        return SUCCESS_TIP;
    }

    /**
     * 专辑管理详情
     */
    @RequestMapping(value = "/detail/{CmsAlbumId}")
    @ResponseBody
    public Object detail(@PathVariable("CmsAlbumId") Integer CmsAlbumId) {
        return CmsAlbumService.getById(CmsAlbumId);
    }
}
