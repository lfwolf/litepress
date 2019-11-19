package cn.stylefeng.guns.modular.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.roses.core.base.controller.BaseController;

@Controller
@RequestMapping("/gen")
public class GenController extends BaseController {

    private String PREFIX = "/modular/gen/";
    
    @Value("${spring.datasource.db-name:guns}")
    private String dbName;
    
    @RequestMapping("")
    public String index() {
        return PREFIX + "gen.html";
    }
    
    @RequestMapping("/tableFields")
    public Object tableFields(@RequestParam("tableName") String tableName,Model model) {

    	model.addAttribute("tableName", tableName);
    	return PREFIX + "table_fields.html"; 
    }
    
    
    @RequestMapping("/tableList")
    @ResponseBody
	public Object tableList() {
        String sql = "select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = '" + dbName + "'";
        return WrapResult(SqlRunner.db().selectList(sql));
	}
    

    @RequestMapping("/getTableFields")
    @ResponseBody
    public Object getTableFields(@RequestParam("tableName") String tableName) {
    	
    	String sql = "select COLUMN_NAME as columnName,COLUMN_COMMENT as columnComment from information_schema.`COLUMNS` where table_name = '" + tableName + "'";
    	return WrapResult(SqlRunner.db().selectList(sql));
    }
    
    private LayuiPageInfo WrapResult(List data){
        LayuiPageInfo pageInfo = new LayuiPageInfo();
        pageInfo.setData(data);
    	return pageInfo;
    }

}
