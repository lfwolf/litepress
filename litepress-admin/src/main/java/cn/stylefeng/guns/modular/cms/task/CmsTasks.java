package cn.stylefeng.guns.modular.cms.task;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.fastjson.JSONObject;



public class CmsTasks {
	
	private static Logger logger = LoggerFactory.getLogger(CmsTasks.class);
	
   //@Autowired
   //private CmsPostService cmsPostService;
   
	@Scheduled(cron = "0 0 */2 * * *")
    public void cronParseIEnglish() {
		System.err.println(new Date() + "<<<<<调试信息,注释掉SchedulingConfig类中的内容来关闭这个定时任务！>>>>> spring task执行 >>>>>每隔2小时执行一次！");
		
		//cmsPostService.getIEnglish();
	}
	
	@Scheduled(cron = "*/30 * * * * *")
    public void cronParsePost() {
		System.err.println(new Date() + "<<<<<调试信息,注释掉SchedulingConfig类中的内容来关闭这个定时任务！>>>>> spring task执行 >>>>>每隔30秒执行一次！");
		
		//cmsPostService.parseUrlToPost();
			/*
			if( a.getUrl() != null ) {
		    	String strtime = null;
		    	String bookid = null;
		    	String booktitle = null;
		    	String bookname = null;
		    	String coverurl  =null;
				String address = a.getUrl();
		    	int type = 0;// 从当前页面获取图片
		    	if( address.startsWith("https://dictjson.tope365.com/ipeiyin/oral")) {
		    		type = 1;
		    	}else if (  address.startsWith("https://videopy.ipeiyinla.com/share")) {
		    		//视频配音
		    		type = 2;
		    	}
				Document doc;
			    Document doc2;
				try {
					doc = Jsoup.connect(address).get();
		            Pattern p = Pattern.compile("studentEbookID\\s+=\\s+(\\d+);");
		            Matcher m = p.matcher(doc.toString()); // 获取 matcher 对象
		            if(m.find()) {
		            	 bookid = m.group(1);
		            }
					doc2 = Jsoup.connect("https://readappc.tope365.com/engApi/front/student_ebook/view/"+bookid).get();
		            String retData = doc2.getElementsByTag("body").text().replaceFirst("jsonpCallback\\(", "");
		            retData = retData.substring(0,retData.length()-1);
		            JSONObject json = JSONObject.parseObject(retData);
		            JSONObject jsonData = JSONObject.parseObject(json.getString("data"));
		            strtime = jsonData.getString("createTime").substring(0,10);
		            bookname = jsonData.getString("ebookName");
		            coverurl = jsonData.getString("coverUrl");
		            System.err.println("bookName:" + bookname);
		            System.err.println("createTime:" + strtime);
		            
		            if(type == 0) {
			            Element eleTitle = doc.getElementById("1");
			            booktitle = eleTitle.text();
		            }else  {
		            	booktitle = bookname;
		            }
		            System.err.println(booktitle);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		*/
		
    }
	

}
