package sz.fzf.litepress.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jodd.json.JsonSerializer;
import sz.fzf.litepress.api.service.IWordpressService;

/*
 * 获取wordpress的数据
 */
@RestController
@RequestMapping("api/wx/wp")
public class WordpressController extends BaseController {
	
	@Autowired
	IWordpressService service;
	
	
    @RequestMapping("/categories.json")
    @ResponseBody
    public Object listCategories() {
    	return  this.retSuccess(service.ListCategories());
    }
    
    @RequestMapping("/posts.json")
    @ResponseBody
    public Object listPosts(@RequestParam("cat") String catIds) {
    	return  this.retSuccess(service.ListPosts(catIds));
    }
    
    @RequestMapping("/post.json")
    @ResponseBody
    public Object getPost(@RequestParam("id") int id) {
    	return  this.retSuccess(service.GetPostById(id));
    }
}
