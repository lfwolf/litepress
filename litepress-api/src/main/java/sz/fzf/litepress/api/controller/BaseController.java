package sz.fzf.litepress.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import sz.fzf.litepress.api.model.ResponseResult;

public class BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
	
	protected ResponseResult retFail( ){
		return this.retFail("fail");
	}
	protected ResponseResult retFail(String msg){
		ResponseResult rr = new ResponseResult();
		rr.setCode(0);
		rr.setMsg(msg);
		//return JsonSerializer.create().serialize(rr);
		return rr;
	}
	protected ResponseResult retSuccess(Object data){
		return this.retSuccess("success", data);
	}
	protected ResponseResult retSuccess(String msg,Object data){
		ResponseResult rr = new ResponseResult();
		rr.setCode(1);
		rr.setMsg(msg);
		rr.setData(data);
		//return JsonSerializer.create().deep(true).serialize(rr);
		return rr;
	}
	protected ResponseResult requestResponse(String url) {
    	HttpResponse response = HttpRequest.get(url).send();
    	ResponseResult result = JsonParser.create().parse(response.bodyText(),ResponseResult.class);
    	if( result.getCode() == 1) {
    		return this.retSuccess(result.getData());
    	}
        return this.retFail();
	}
}
