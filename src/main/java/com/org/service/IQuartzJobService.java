package com.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.pojo.QuartzJob;
import org.quartz.SchedulerException;

public interface IQuartzJobService extends IService<QuartzJob> {

    boolean saveAndScheduleJob(QuartzJob quartzJob);

    boolean editAndScheduleJob(QuartzJob quartzJob) throws SchedulerException;

    boolean deleteAndStopJob(QuartzJob quartzJob);

    boolean resumeJob(QuartzJob quartzJob);

}
