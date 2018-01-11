package com.spinach.frame.quartz;

import java.text.ParseException;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * <p>
 * <dependency> <groupId>quartz</groupId> <artifactId>quartz</artifactId>
 * <version>1.5.2</version> </dependency>
 * </p>
 * CronTrigger & SimpleTrigger 两种
 * 
 * @author wanghuihui
 * @date 2017-8-11上午12:28:41
 */
public class QuartzOldCronTriggerDemo {
	public static void main(String[] args) throws SchedulerException, ParseException, ClassNotFoundException {
		QuartzOldCronTriggerDemo demo = new QuartzOldCronTriggerDemo();
		//demo.cronTriggerTest();

	}

	/*@Deprecated
	void cronTriggerTest() throws SchedulerException, ParseException, ClassNotFoundException{
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		CronTrigger cronTrigger = new CronTrigger("jobName","jobGroup");
		CronTrigger cronTrigger2 =new CronTrigger("cron_" + 1, Scheduler.DEFAULT_GROUP, "jobName", Scheduler.DEFAULT_GROUP);
		cronTrigger.setCronExpression("0/1 * * * * ?");
		
		//必须设置不然会报空指针
		cronTrigger.setJobGroup("jobgroup"); //job.getJobGroup()
		cronTrigger.setJobName("jobname");  //job.getJobName()
		cronTrigger.setCronExpression(new CronExpression("0/1 * * * * ?")); //job.getJobRule()
		if(job.getStartTime() != null){
			//cronTrigger.setStartTime(job.getStartTime());
		}
		if(job.getEndTime() != null){
			//cronTrigger.setEndTime(job.getEndTime());
		}
		JobDetail jobDetail = new JobDetail("jobname", "jobgroup", Class.forName("com.spinach.frame.quartz.HelloJob"));
		scheduler.addJob(jobDetail, true);
		scheduler.scheduleJob(cronTrigger);
		scheduler.start();
	}*/
}
