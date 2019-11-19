package sz.fzf.litepress.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import sz.fzf.litepress.api.entity.CatalogEntity;
import sz.fzf.litepress.api.entity.PostEntity;
import sz.fzf.litepress.api.model.LizhiAudioListResult;
import sz.fzf.litepress.api.model.NavModel;
import sz.fzf.litepress.api.model.ResponseResult;
import sz.fzf.litepress.api.repo.CatalogRepo;
import sz.fzf.litepress.api.repo.PostRepository;
import sz.fzf.litepress.api.service.LizhiService;

@RestController
@RequestMapping("api/front/")
public class IndexController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
	
	private static String BannerUrl = "https://api.qian.fm/api/indexv2/lunbo.ashx?fr=wxapp";
	private static String NavUrl = "https://api.qian.fm/api/indexv2/nav.ashx?fr=wxapp";
	private static String RecommendUrl = "https://api.qian.fm/api/indexv2/recommend.ashx?p=1&fr=wxapp";
	private static String AlbumUrl = "https://api.qian.fm/api/media/albumv2.ashx?page=1&phone=&sessionkey=&fr=wxapp&id=";
	private static String ArticleUrl = "https://api.qian.fm/api/media/audio.ashx?phone=&sessionkey=&fr=wxapp&id=";
	private static String CategoryUrl = "https://api.qian.fm/api/indexv2/category.ashx?fr=wxapp";
	private static String ArticleListUrl = "https://api.qian.fm/api/media/article.ashx?q=&p=1&fr=wxapp";
	private static String ArticleContentUrl = "https://api.qian.fm/api/media/news.ashx?fr=wxapp&id=";
	
	private static String AudioListUrl = "https://www.lizhi.fm/api/user/audios/";
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CatalogRepo catalogRepo;
	
    @RequestMapping("/index/banner.json")
    @ResponseBody
    public ResponseResult listBanners() {
    	return this.requestResponse(BannerUrl);
    }
    
    @RequestMapping("/index/qianerba/catalog.json")
    @ResponseBody
    public ResponseResult listNav() {
    	String[] covers = new String[] {
    			"https://cdn.qian.fm/upload/img/20180815/174515981.png",
    			"https://cdn.qian.fm/upload/img/20180815/174515981.png",
    			"https://cdn.qian.fm/upload/img/20180815/174515981.png",
    			"https://cdn.qian.fm/upload/img/20180815/174515981.png"
    	};
    	List<NavModel> list = new ArrayList<NavModel>();
    	NavModel model = new NavModel();
    	model.setCover("https://cdn.qian.fm/upload/img/20180815/174515981.png");
    	model.setTitle("原创文章");
    			
    	return this.requestResponse(NavUrl);
    }
    
    @RequestMapping("/index/recommends.json")
    @ResponseBody
    public ResponseResult listRecommends(@RequestParam("type") int type) {
    	String url = type == 1 ? RecommendUrl : null;
    	return this.requestResponse(url);
    }
    
    @RequestMapping("/album.json")
    @ResponseBody
    public Object getAlbum(@RequestParam("id") int id) {
    	
    	
    	String url = AlbumUrl +id;
    	HttpResponse response = HttpRequest.get(url).send();
    	String retdata = response.bodyText();
    	ResponseResult result = JsonParser.create().parse(retdata,ResponseResult.class);
    	if( result.getCode() == 1) {
    		 //Map<String,Object> map = new HashMap<String,Object>();
    		 //map.put("info", result.getInfo());
    		 //map.put("list", result.getList());
    		return result;
    	}
        return this.retFail();
    }
    
    @RequestMapping("/article.json")
    @ResponseBody
    public ResponseResult getArticle(@RequestParam("id") int id) {
    	
    	return this.requestResponse(ArticleUrl+id);
    }
    
    @RequestMapping("/catelog.json")
    @ResponseBody
    public ResponseResult listCatelogs() {
    	
    	String url = CategoryUrl;
    	HttpResponse response = HttpRequest.get(url).send();
    	String retdata = response.bodyText();
    	ResponseResult result = JsonParser.create().parse(retdata,ResponseResult.class);
    	if( result.getCode() == 1) {
    		return this.retSuccess(result.getData());
    	}
        return this.retFail();
    }
    
    @RequestMapping("/articlelist.json")
    @ResponseBody
    public ResponseResult listArticles() {
    	
    	return this.requestResponse(ArticleListUrl);
    }
    
    @RequestMapping("/articlecontent.json")
    @ResponseBody
    public ResponseResult getArticleContent(@RequestParam("id") int id) {
    	
    	return this.requestResponse(ArticleContentUrl+id);
    }
    
    @RequestMapping("/audio/list.json")
    @ResponseBody
    public ResponseResult getAudioList(@RequestParam("page") int page,@RequestParam("uid") String uid) {
    	
       	String url = AudioListUrl + uid + "/" + page;
       	LOG.info(url);
       	HttpRequest request = HttpRequest.get(url);
    	HttpResponse response = request.send();
    	String ret = response.bodyText();
    	int retCode = response.statusCode();
    	if( retCode == 302) {
    		String location = response.header("Location");
    		LOG.info(location);
    	} else if( retCode == 200 ) {
    		LOG.info("code:"+retCode);
    		String baseImgUrl = "http://cdnimg103.lizhi.fm/audio_cover/";
    		LizhiAudioListResult audioList = JsonParser.create().parse(ret,LizhiAudioListResult.class);
    		audioList.getAudios().forEach(a->{
    			a.setCover(baseImgUrl+a.getCover() );
    		});
    		return this.retSuccess(audioList); //JsonSerializer.create().deep(true).serialize(audioList);
    	}
    	 return this.retFail();
    }
    
    @RequestMapping("/voice/list.json")
    @ResponseBody
    public ResponseResult getVoiceList(@RequestParam("page") int page,@RequestParam("sid") String sid) {
    	int size = 10;
    	int kind = sid.equals("ieng-1") ? 1: ( sid.equals("ieng-2") ? 2 : 0 );
    	if (kind > 0 ) {
        	PageRequest pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
    		// This returns a JSON or XML with the users
    		Page<PostEntity> pageResult =  postRepository.findByKindAndNameIsNotNull(kind,pageable);
    		Map<String,Object> ret = new HashMap<String,Object>();
    		ret.put("total", pageResult.getTotalPages());
    		ret.put("p",pageResult.getNumber());
    		ret.put("size", pageResult.getSize());
    		ret.put("audios", pageResult.getContent());

    		
  
    		
    		Map<String,Object> bandInfo = new HashMap<String,Object>();
    		bandInfo.put("id", kind);
    		bandInfo.put("name", "英语伴读");
    		bandInfo.put("intro", "iEnglish 是一套类母语英语学习训练系统，利用人工智能AI技术、语音训练引擎、自适应互动教学技术"  
    				);
    		bandInfo.put("cover", "http://android-artworks.25pp.com/fs08/2016/06/08/1/1_830337973c81a050402810bbabfdef31_con.png");
    		bandInfo.put("voiceNum", pageResult.getTotalPages());
    		
    		ret.put("bandinfo",bandInfo);
    		//return JsonSerializer.create().deep(true).serialize(ret);
    		return this.retSuccess(ret);
    	}

    	
		LizhiService service = new LizhiService(); 
		//return JsonSerializer.create().deep(true).serialize( service.GetPlaySheetVoices(sid,page));
		return this.retSuccess(service.GetPlaySheetVoices(sid,page));
    }
    
    @RequestMapping("/voice/info.json")
    @ResponseBody
    public ResponseResult getVoiceInfo(@RequestParam("id") String id) {
    	
		LizhiService service = new LizhiService(); 
		ResponseResult result = service.GetVideoInfo(id);
		if( result == null ) {
			return this.retFail();
		}
		//return JsonSerializer.create().deep(true).serialize(result);
		return this.retSuccess(result);
    }
    
    /*
     * 读取ienglish里的陪读文章
     */
    @RequestMapping(path="/ieng/all")
	public @ResponseBody Iterable<PostEntity> getAllPosts(@RequestParam("kind") int kind,
			@RequestParam("page") int page,@RequestParam("size") int size) {
    	
    	PageRequest pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
		// This returns a JSON or XML with the users
		return postRepository.findByKindAndNameIsNotNull(kind,pageable);
    	
	}
    
    @RequestMapping(path="/index/catalog.json")
	public @ResponseBody ResponseResult getCatalogs(){
    	List<CatalogEntity> list = catalogRepo.findAll();
    	if (list.size() < 1) {
    		//初始化
    		list = this.initCatalog();
    	}
    	return this.retSuccess(list);
    }
    private List<CatalogEntity> initCatalog() {
    	List<CatalogEntity> list = new ArrayList<CatalogEntity>();
    	CatalogEntity entity = new CatalogEntity();
    	entity.setCover("https://cdn.qian.fm/upload/img/20180815/174543481.png");
    	entity.setUrl("/iframe?src=http://www.neumedias.com/store/index.php/online/series_new?id=279&org_id=35&user_id=");
    	entity.setName("十万个为什么");
    	list.add(entity);
    	entity = new CatalogEntity();
    	entity.setCover("https://cdn.qian.fm/upload/img/20180815/174543481.png");
    	entity.setUrl("/voice/list/ieng-1");
    	entity.setName("英语配音");
    	list.add(entity);
    	catalogRepo.saveAll(list);
    	return list;
    }
	


}
