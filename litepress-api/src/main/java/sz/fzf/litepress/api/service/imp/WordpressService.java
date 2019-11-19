package sz.fzf.litepress.api.service.imp;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonParser;
import sz.fzf.litepress.api.model.NavModel;
import sz.fzf.litepress.api.model.PostModel;
import sz.fzf.litepress.api.model.ResponseResult;
import sz.fzf.litepress.api.model.wordpress.WpPostArgumentModel;
import sz.fzf.litepress.api.model.wordpress.WpPostCategory;
import sz.fzf.litepress.api.model.wordpress.WpPostModel;
import sz.fzf.litepress.api.service.IWordpressService;

@Service
public class WordpressService implements IWordpressService {
	
	static String ListPostUrl = "http://www.akucun.xyz/wp-json/wp/v2/posts" ;
	static String ListCategoryUrl = "http://www.akucun.xyz/wp-json/wp/v2/categories";
	
	@Override
	public List<NavModel> ListCategories() {
		// TODO Auto-generated method stub
		HttpResponse response = HttpRequest.get(ListCategoryUrl).send();
		List<WpPostCategory> ret = JsonParser.create().parseAsList(response.bodyText(),WpPostCategory.class);
		
		List<NavModel> navList = new ArrayList<NavModel>();
		ret.forEach(w->{
			NavModel model = new NavModel();
			model.setId( w.getId());
			model.setTitle( w.getName());
			navList.add(model);
		});
		return navList;
	}

	@Override
	public List<PostModel> ListPosts(String catid) {
		// TODO Auto-generated method stub
		HttpResponse response = HttpRequest.get(ListPostUrl).query("categories", catid).send();
		List<WpPostModel> ret = JsonParser.create().parseAsList(response.bodyText(),WpPostModel.class);
		List<PostModel> models = new ArrayList<PostModel>();
		ret.forEach(w->{
			models.add(toPostModel(w));
		});
		return models;
	}
	
	@Override
	public PostModel GetPostById(int id) {
		// TODO Auto-generated method stub
		HttpResponse response = HttpRequest.get(ListPostUrl + "/" + id).send();
		WpPostModel ret = JsonParser.create().parse(response.bodyText(),WpPostModel.class);
		return toPostModel(ret);
	}
	
	private PostModel toPostModel(WpPostModel wpmodel) {
		PostModel model = new PostModel();
		model.setCategories(wpmodel.getCategories());
		model.setId(wpmodel.getId());
		model.setDate(wpmodel.getDate());
		model.setContent(Base64.getEncoder().encodeToString(wpmodel.getContentRendered().getBytes()));
		model.setMediaid(wpmodel.getFeatured_media());
		model.setTitle(wpmodel.getTitleRendered());
		return model;
	}

}
