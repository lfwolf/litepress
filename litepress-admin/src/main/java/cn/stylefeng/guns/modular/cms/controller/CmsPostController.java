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
import cn.stylefeng.guns.modular.cms.entity.CmsPost;
import cn.stylefeng.guns.modular.cms.service.ICmsPostService;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 文章管理控制器
 *
 * @author fengshuonan
 * @Date 2019-11-04 16:05:29
 */
@Controller
@RequestMapping("/CmsPost")
public class CmsPostController extends BaseController {

    private String PREFIX = "/modular/cms/CmsPost/";

    @Autowired
    private ICmsPostService CmsPostService;

    /**
     * 跳转到文章管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "CmsPost.html";
    }

    /**
     * 跳转到添加文章管理
     */
    @RequestMapping("/CmsPost_add")
    public String CmsPostAdd() {
        return PREFIX + "CmsPost_add.html";
    }

    /**
     * 跳转到修改文章管理
     */
    @RequestMapping("/CmsPost_update/{id}")
    public String CmsPostUpdate(@PathVariable Long id, Model model) {
        CmsPost CmsPost = CmsPostService.getById(id);
        //model.addAllAttributes(BeanUtil.beanToMap(CmsPost));
        model.addAttribute("item", CmsPost);
        return PREFIX + "CmsPost_edit.html";
    }

    /**
     * 获取文章管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	QueryWrapper<CmsPost> ew=new QueryWrapper<CmsPost>();
    	if ( condition != null ) {
    		ew.like("name", condition);
    	}
    	Page page = LayuiPageFactory.defaultPage();
        return LayuiPageFactory.createPageInfo(CmsPostService.pageMaps(page,ew));
    }

    /**
     * 新增文章管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CmsPost CmsPost) {
        CmsPostService.save(CmsPost);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
        CmsPostService.removeById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CmsPost CmsPost) {
        CmsPostService.updateById(CmsPost);
        return SUCCESS_TIP;
    }

    /**
     * 文章管理详情
     */
    @RequestMapping(value = "/detail/{CmsPostId}")
    @ResponseBody
    public Object detail(@PathVariable("CmsPostId") Integer CmsPostId) {
        return CmsPostService.getById(CmsPostId);
    }
}
