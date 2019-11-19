package cn.stylefeng.guns.modular.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.stylefeng.guns.modular.cms.task.CmsTasks;



@Configuration
public class CmsConfig {
	
    @Bean
    public CmsTasks scheduledTasks() {
        return new CmsTasks();
    }

}
