package cn.stylefeng.guns.modular.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.stylefeng.guns.modular.cms.service.ICmsBannerService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;

@RestController
@RequestMapping("/cms/api")
public class CmsApiContoller extends BaseController {
	
	@Autowired
	ICmsBannerService bannerServide;
	
	
    @RequestMapping("/banner.json")
    @ResponseBody
    public ResponseData banner() {
    	Object data = bannerServide.list();
        return SUCCESS_TIP.success(data);
    }

}
