package com.xwintop.xcore.quartz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.StatefulJob;

//@PersistJobDataAfterExecution
@DisallowConcurrentExecution
//public class TestJob implements StatefulJob {
public class TestJob implements Job {
	private String a;
	// 开发者实现该接口定义需要执行的任务。JobExecutionContext类提供调度上下文的各种信息
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 通过上下文获取
		JobKey jobKey = context.getJobDetail().getKey();
		
		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
		System.out.println(jobKey + "在" + df.format(new Date()) + "时，输出"+a);
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("传递过来的值"+context.getMergedJobDataMap().get("a"));
		// do more这里可以执行其他需要执行的任务
	}
	public void setA(String a) {
		this.a = a;
	}
}