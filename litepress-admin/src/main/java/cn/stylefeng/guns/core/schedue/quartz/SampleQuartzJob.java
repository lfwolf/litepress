package cn.stylefeng.guns.core.schedue.quartz;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * quartz的执行示例
 *
 * @author fengshuonan
 * @Date 2019/2/24 16:55
 */
public class SampleQuartzJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        System.err.println("<<<<<"+new Date()+"定时任务！>>>>> quartz task执行 >>>>> quartz执行了！");
    }

}