package sz.fzf.litepress.api.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonObject;
import jodd.json.JsonParser;
import sz.fzf.litepress.api.controller.IndexController;
import sz.fzf.litepress.api.model.LizhiAudioListResult;
import sz.fzf.litepress.api.model.LizhiAudioModel;
import sz.fzf.litepress.api.model.ResponseResult;
import sz.fzf.litepress.api.result.lizhi.LizhiPlaySheetResult;
import sz.fzf.litepress.api.result.lizhi.LizhiVoiceInfo;

public class LizhiService {
	private static final Logger LOG = LoggerFactory.getLogger(LizhiService.class);
	
	/*
	 * 
	 * https://m.lizhi.fm/vodapi/playsheet/listdata?userId=2540285708286522412
	 */
	public String GetPlaySheet(String uid) {
		String url = "https://m.lizhi.fm/vodapi/playsheet/listdata?userId="+ uid;
		HttpResponse response = HttpRequest.get(url).send();
		JsonObject jsonObj = JsonParser.create().parseAsJsonObject(response.bodyText());
		int retCode = jsonObj.getInteger("code");
		if (retCode == 0) {
			
		}
		return null;
	}
	/*
	 * https://m.lizhi.fm/vodapi/playsheet/data?id=2667635973481609793
	 * 
	 */
	public LizhiAudioListResult GetPlaySheetVoices(String sid,int page) {
		String url = "https://m.lizhi.fm/vodapi/playsheet/data?id="+sid+"&page="+page;
		HttpResponse response = HttpRequest.get(url).send();
		LOG.info("url: "+ url);
		LOG.info(response.bodyText());
		LizhiPlaySheetResult ret = JsonParser.create().parse(response.bodyText(), LizhiPlaySheetResult.class);
		
		LizhiAudioListResult result = new LizhiAudioListResult();
		if( page == 1) {
			result.setTotal(ret.getPlaySheetInfo().getVoiceNum());
			result.setSize(10);
			result.setP(page);
			result.setBandinfo(ret.getPlaySheetInfo());
		}
		List<LizhiAudioModel> audioList = new ArrayList<LizhiAudioModel>();
		ret.getVoices().forEach(v->{
			LizhiAudioModel model = new LizhiAudioModel();
			model.setCover(v.getCover());
			model.setId(v.getVoiceId());
			model.setName(v.getName());
			model.setUrl(v.getVoiceTrack());
			audioList.add(model);
			
		});
		result.setAudios(audioList);
		return result;
	}
	/*
	 * https://m.lizhi.fm/vodapi/voice/info/2689909258709092358
	 */
	public ResponseResult GetVideoInfo(String id) {
		String url = "https://m.lizhi.fm/vodapi/voice/info/"+id;
		HttpResponse response = HttpRequest.get(url).send();
		ResponseResult ret = JsonParser.create().parse(response.bodyText(),ResponseResult.class);
		if( ret.getCode() == 0 ) {
			
			return ret;
			
		}
		return null;
	}
	

	
	public static void main(String[] args) {
		String uid="2540285708286522412";
		String sid="2667635973481609793";
		LizhiService service = new LizhiService(); 
		//service.GetPlaySheetVoices(sid,1);
		service.GetVideoInfo("2689909258709092358");
	}

}
