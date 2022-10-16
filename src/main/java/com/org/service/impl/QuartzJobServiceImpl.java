package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.mapper.QuartzJobMapper;
import com.org.pojo.QuartzJob;
import com.org.pool.CommonConstant;
import com.org.service.IQuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper, QuartzJob> implements IQuartzJobService {

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private Scheduler scheduler;


    /**
     * 保存&启动定时任务
     */
    @Override
    public boolean saveAndScheduleJob(QuartzJob quartzJob) {
        if (CommonConstant.STATUS_NORMAL.equals(quartzJob.getStatus())) {
            // 定时器添加
            this.schedulerAdd(quartzJob.getJobClassName().trim(), quartzJob.getCronExpression().trim(), quartzJob.getParameter());
        }
        // DB设置修改
        return this.save(quartzJob);
    }

    /**
     * 恢复定时任务
     */
    @Override
    public boolean resumeJob(QuartzJob quartzJob) {
        schedulerDelete(quartzJob.getJobClassName().trim());
        schedulerAdd(quartzJob.getJobClassName().trim(), quartzJob.getCronExpression().trim(), quartzJob.getParameter());
        quartzJob.setStatus(CommonConstant.STATUS_NORMAL);
        return this.updateById(quartzJob);
    }

    /**
     * 编辑&启停定时任务 @throws SchedulerException
     */
    @Override
    public boolean editAndScheduleJob(QuartzJob quartzJob) throws SchedulerException {
        if (CommonConstant.STATUS_NORMAL.equals(quartzJob.getStatus())) {
            schedulerDelete(quartzJob.getJobClassName().trim());
            schedulerAdd(quartzJob.getJobClassName().trim(), quartzJob.getCronExpression().trim(), quartzJob.getParameter());
        } else {
            scheduler.pauseJob(JobKey.jobKey(quartzJob.getJobClassName().trim()));
        }
        return this.updateById(quartzJob);
    }

    /**
     * 删除&停止删除定时任务
     */
    @Override
    public boolean deleteAndStopJob(QuartzJob job) {
        schedulerDelete(job.getJobClassName().trim());
        return this.removeById(job.getId());
    }

    /**
     * 添加定时任务
     */
    private void schedulerAdd(String jobClassName, String cronExpression, String parameter) {
        try {
            // 启动调度器
            scheduler.start();

            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName).usingJobData("parameter", parameter).build();

            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("创建定时任务失败", e);
            e.printStackTrace();
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (Exception e) {
            log.error("后台找不到该类名：" + jobClassName, e);
            e.printStackTrace();

        }
    }

    /**
     * 删除定时任务
     */
    private void schedulerDelete(String jobClassName) {
        try {
            /*使用给定的键暂停Trigger 。*/
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName));
            /*从调度程序中删除指示的Trigger */
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName));
            /*从 Scheduler 中删除已识别的Job - 以及任何关联的Trigger */
            scheduler.deleteJob(JobKey.jobKey(jobClassName));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    private static Job getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job) class1.newInstance();
    }
}
