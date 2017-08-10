package com.xwintop.xcore.quartz;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

/**
 * 任务调度
 * 
 * @author feizi
 * @time 2015-1-29下午4:47:57
 */
public class QuartzDemo {
	private static int num = 0;
	private SchedulerFactory sf = new StdSchedulerFactory();
	private String schedulerKeyName = "b1";
	private String schedulerKeyGroup = "a1";

	@Test
	public void run() throws SchedulerException {
		System.out.println("------- 初始化 ----------------------");
		// 通过SchedulerFactory工厂类获取Scheduler
		// SchedulerFactory sf = new StdSchedulerFactory();
		System.out.println("------- 初始化完成 -----------");
		// 任务执行时间
		// Date runTime = DateBuilder.evenMinuteDate(new Date());
		Date runTime = DateBuilder.evenSecondDateAfterNow();

		System.out.println("------- 将Job加入Scheduler中  ------");
		// 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
		JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity(schedulerKeyName, schedulerKeyGroup)
				.build();
		jobDetail.getJobDataMap().put("a", "a1");
		// jobDetail.isConcurrentExectionDisallowed();
		// 简单规则
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1)// 时间间隔
				.withRepeatCount(-1);// 重复次数（将执行6次）
		// 表达式调度构建器
		// CronScheduleBuilder scheduleBuilder =
		// CronScheduleBuilder.cronSchedule("0/1 * * * * ?");

		// 描叙触发Job执行的时间触发规则,Trigger实例化一个触发器
		Trigger trigger = TriggerBuilder.newTrigger()// 创建一个新的TriggerBuilder来规范一个触发器
				.withIdentity(schedulerKeyName, schedulerKeyGroup)// 给触发器一个名字和组名
				// .startNow()//立即执行
				.startAt(runTime)// 设置触发开始的时间
				.withSchedule(scheduleBuilder).build();// 产生触发器

		// 运行容器，使用SchedulerFactory创建Scheduler实例
		Scheduler scheduler = sf.getScheduler();

		// 向Scheduler添加一个job和trigger
		scheduler.scheduleJob(jobDetail, trigger);
		System.out.println(jobDetail.getKey() + " 运行在: " + runTime);

		scheduler.start();
	}

	public void rescheduleJob() throws SchedulerException {
		Scheduler scheduler = sf.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(schedulerKeyName, schedulerKeyGroup);
		Trigger trigger = scheduler.getTrigger(triggerKey);
		if (null != trigger) {
			// trigger已存在，则更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
			// // 按新的cronExpression表达式重新构建trigger
			trigger = ((TriggerBuilder<Trigger>) trigger.getTriggerBuilder()).withIdentity(triggerKey)
					.withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			System.out.println("更新任务:" + schedulerKeyGroup + ":" + schedulerKeyName);
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	public void triggerJob() throws SchedulerException {
		Scheduler sched = sf.getScheduler();
		// 手动触发作业 triggerJob
		sched.triggerJob(new JobKey("job8", "group8"));
		sched.triggerJob(new JobKey("job8", "group8"));
	}

	public void deleteJob() throws SchedulerException {
		Scheduler sched = sf.getScheduler();
		// 取消/删除作业
		// removes the given trigger
		sched.unscheduleJob(new TriggerKey("job10", "group10"));
		// removes all triggers to the given job
		sched.deleteJob(new JobKey("job10", "group10"));
	}

	// 列举所有作业 getJobKeys
	public void getJobKeys() throws SchedulerException {
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		for (String groupName : scheduler.getJobGroupNames()) {
			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
				String jobName = jobKey.getName();
				String jobGroup = jobKey.getGroup();
				List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
				Date nextFireTime = triggers.get(0).getNextFireTime();
				System.out.println("[jobName] : " + jobName + " [groupName] : " + jobGroup + " - " + nextFireTime);
			}
		}
	}

	// 传递参数 usingJobData
	public void usingJobData() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		Date runTime = DateBuilder.evenSecondDate(new Date());
		JobDetail job = JobBuilder.newJob(TestJob.class).withIdentity("job9", "group9")
				.usingJobData("jobSays", "Hello Quartz!").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger9", "group9").startAt(runTime).build();
		sched.scheduleJob(job, trigger);
		sched.start();
	}

	public void updateData() throws Exception {
		Scheduler scheduler = sf.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(schedulerKeyName, schedulerKeyGroup);
		Trigger trigger = scheduler.getTrigger(triggerKey);
		// JobKey jobKey = JobKey.jobKey(schedulerKeyName, schedulerKeyGroup);
		// JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		JobDetail jobDetail = scheduler.getJobDetail(trigger.getJobKey());
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		System.out.println("data:" + jobDataMap.get("a"));
		jobDataMap.put("a", "a3" + (++num));
		System.out.println("data:" + jobDataMap.get("a"));
		// CronScheduleBuilder scheduleBuilder =
		// CronScheduleBuilder.cronSchedule("0/4 * * * * ?");
		// // 按新的cronExpression表达式重新构建trigger
		// trigger = ((TriggerBuilder<Trigger>)
		// trigger.getTriggerBuilder()).withIdentity(triggerKey)
		// .withSchedule(scheduleBuilder).build();
		scheduler.deleteJob(trigger.getJobKey());
		scheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 * 调度测试
	 * 
	 * @param args
	 * @throws SchedulerException
	 */
	public static void main(String[] args) throws Exception {
		QuartzDemo demo = new QuartzDemo();
		demo.run();
		demo.getJobKeys();
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					demo.rescheduleJob();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 5000);
	}
}
