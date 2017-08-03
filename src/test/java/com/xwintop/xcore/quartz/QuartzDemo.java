package com.xwintop.xcore.quartz;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
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
	private SchedulerFactory sf = new StdSchedulerFactory();
	@Test
	public void run() throws SchedulerException {
		System.out.println("------- 初始化 ----------------------");
		// 通过SchedulerFactory工厂类获取Scheduler
//		SchedulerFactory sf = new StdSchedulerFactory();
		System.out.println("------- 初始化完成 -----------");
		// 任务执行时间
		// Date runTime = DateBuilder.evenMinuteDate(new Date());
		Date runTime = DateBuilder.evenSecondDateAfterNow();

		System.out.println("------- 将Job加入Scheduler中  ------");

		// 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
		JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("testJob_1", "group_1").build();

		//简单规则
//		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)// 时间间隔
//				.withRepeatCount(5);// 重复次数（将执行6次）
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
		
		// 描叙触发Job执行的时间触发规则,Trigger实例化一个触发器
		Trigger trigger = TriggerBuilder.newTrigger()// 创建一个新的TriggerBuilder来规范一个触发器
				.withIdentity("trigger_1", "group_1")// 给触发器一个名字和组名
				// .startNow()//立即执行
				.startAt(runTime)// 设置触发开始的时间
				.withSchedule(scheduleBuilder).build();// 产生触发器

		// 运行容器，使用SchedulerFactory创建Scheduler实例
		Scheduler sched = sf.getScheduler();

		// 向Scheduler添加一个job和trigger
		sched.scheduleJob(jobDetail, trigger);
		System.out.println(jobDetail.getKey() + " 运行在: " + runTime);
		
		sched.start();
		System.out.println("");
	}
	
	public void triggerJob() throws SchedulerException{
		Scheduler sched = sf.getScheduler();
//		手动触发作业 triggerJob
		sched.triggerJob(new JobKey("job8", "group8"));  
		sched.triggerJob(new JobKey("job8", "group8"));  
	}
	
	public void deleteJob() throws SchedulerException{
		Scheduler sched = sf.getScheduler();
		// 取消/删除作业
		// removes the given trigger
		sched.unscheduleJob(new TriggerKey("job10", "group10"));
		// removes all triggers to the given job
		sched.deleteJob(new JobKey("job10", "group10")); 
	}
	
//	列举所有作业 getJobKeys
	public void getJobKeys() throws SchedulerException{
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
	
//	传递参数 usingJobData 
	public void usingJobData() throws SchedulerException{
		SchedulerFactory sf = new StdSchedulerFactory();  
		Scheduler sched = sf.getScheduler();  
		Date runTime = DateBuilder.evenSecondDate(new Date());  
		JobDetail job = JobBuilder.newJob(TestJob.class).withIdentity("job9", "group9")  
		        .usingJobData("jobSays", "Hello Quartz!")  
		        .build();  
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger9","group9")  
		        .startAt(runTime)  
		        .build();
		sched.scheduleJob(job, trigger);  
		sched.start();  
	}

	/**
	 * 调度测试
	 * 
	 * @param args
	 * @throws SchedulerException
	 */
	public static void main(String[] args) throws SchedulerException {
		QuartzDemo demo = new QuartzDemo();
		demo.run();
		demo.getJobKeys();
	}
}
