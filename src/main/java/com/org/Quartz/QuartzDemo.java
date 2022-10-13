package com.org.Quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author
 * @date 20-2-5 下午3:11
 */
public class QuartzDemo implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Quartz定时器执行--------" + new Date());
    }


}
