package sz.fzf.litepress.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sz.fzf.litepress.api.model.NavModel;
import sz.fzf.litepress.api.model.PostModel;
import sz.fzf.litepress.api.model.wordpress.WpPostArgumentModel;
import sz.fzf.litepress.api.model.wordpress.WpPostCategory;
import sz.fzf.litepress.api.model.wordpress.WpPostModel;

public interface IWordpressService {
	
	List<NavModel> ListCategories();
	
	List<PostModel> ListPosts(String catid);
	
	PostModel GetPostById(int id);

}
