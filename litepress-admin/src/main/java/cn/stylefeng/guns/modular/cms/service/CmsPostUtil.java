package cn.stylefeng.guns.modular.cms.service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.cms.entity.CmsPost;
import cn.stylefeng.guns.modular.cms.mapper.CmsPostMapper;
import cn.stylefeng.guns.modular.cms.model.CmsPostDto;
import cn.stylefeng.guns.modular.cms.task.CmsTasks;
import cn.stylefeng.guns.modular.system.mapper.DeptMapper;
import cn.stylefeng.guns.modular.system.model.params.DictParam;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonArray;
import jodd.json.JsonObject;
import jodd.json.JsonParser;

@Service
public class CmsPostUtil extends ServiceImpl<CmsPostMapper, CmsPost> {
	
	private static Logger logger = LoggerFactory.getLogger(CmsPostUtil.class);
    @Resource
    private CmsPostMapper postMapper;
	
	   public void add(CmsPost param) {
		   
	        this.save(param);
		   
	   }
	    
	    public void delete(CmsPostDto post) {
	    	 this.removeById(post.getPostId());
	    }
	    
	    private static Pattern pattern = Pattern.compile("studentEbookID\\s+=\\s+(\\d+);");
	    
	    /*
	     * 解析source是ienglish的url，获取文章并设置文章的标题、封面图。
	     */
	    public void parseUrlToPost() {
	    	QueryWrapper<CmsPost> ew=new QueryWrapper<CmsPost>();
	    	ew.eq("source", "ienglish").isNull("name").isNotNull("url");
	    	this.postMapper.selectList(ew).forEach(p ->{
	    		
	    		String url = p.getUrl();
		    	HttpResponse response = HttpRequest.get(url).send();
	            Matcher m = pattern.matcher(response.bodyText()); // 获取 matcher 对象
	            if(m.find()) {
	            	String bookid = m.group(1);
	            	HttpResponse apiResponse = HttpRequest.get("https://readappc.tope365.com/engApi/front/student_ebook/view/"+bookid).send();
	            	String apiReturn = apiResponse.bodyText().replaceFirst("jsonpCallback\\(", "");
	            	apiReturn = apiReturn.substring(0,apiReturn.length()-1);;
	            	logger.info(apiReturn);
	            	JsonObject jsonObj = JsonParser.create().parseAsJsonObject(apiReturn);
	            	if( jsonObj.getInteger("code") == 200) {
	            		JsonObject jsonData = jsonObj.getJsonObject("data");	            		
	            		p.setName( jsonData.getString("ebookName"));
	            		p.setCover(jsonData.getString("coverUrl"));
	            		p.setUpdateUser(1L);
	            		this.updateById(p);
	            	}
	            }
	    	});
	    }
	    
	    public void getIEnglish() {
	    	if( this.list().size() > 0 ) return;
	    	//音频分享地址
	    	String shareurl = "https://dictjson.tope365.com/share/student_ebook_share/student_ebook_%d.html?studentID=327307";
	    	//音频列表api
	    	String apiUrl = "https://wxparent.tope365.com/family/student_ebook/list_data?pageSize=5000&ebookKind=1&pageNo=1";
	    	
	    	this.getIEnglish(apiUrl, shareurl,1);
	    	//视频列表api
	    	apiUrl = "https://wxparent.tope365.com/family/student_ebook/list_data?pageSize=500&ebookKind=2&pageNo=1";
	    	//视频分享地址
	    	shareurl= "https://videopy.ipeiyinla.com/share/student_ebook_share/student_ebook_%d.html?studentID=327307";
	    	this.getIEnglish(apiUrl, shareurl,2);
	    	
	    }
	    private void getIEnglish(String apiUrl , String shareUrl,int albumId) {

	    	HttpRequest request = HttpRequest.get(apiUrl);
	    	request.header("cookie","JSESSIONID_COOKIE=23b59d5e-4d88-4768-ab88-fe4985a1362d; www=666a8a1b9f4a761a720dc64c11090b90");
	    	HttpResponse apiResponse =  request.send();
	    	//logger.info(apiResponse.bodyText());
	    	JsonObject jsonObj = JsonParser.create().parseAsJsonObject(apiResponse.bodyText());
	    	if( jsonObj.getInteger("code") == 200) {
	    		JsonArray jsonRows = jsonObj.getJsonObject("data").getJsonArray("rows");
	    		for (int i = 0; i < jsonRows.size(); i++) {
	    			JsonObject jsonObject = jsonRows.getJsonObject(i);
	    			String url = String.format(shareUrl, jsonObject.getInteger("studentEbookID"));
	    			logger.info("url: {}", url);;
	    			CmsPost post =new CmsPost();
	    			post.setUrl(url);
	    			post.setSource("ienglish");
	    			//post.setAlbumId(albumId);
	    			post.setCreateUser(1L);
	    			this.save(post);
	    	
	    		}
	    	}
	    	
	    }

}
