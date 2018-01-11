package com.spinach.frame.quartz;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * <p>
 *  <dependency>
		    <groupId>org.quartz-scheduler</groupId>
		    <artifactId>quartz</artifactId>
		    <version>2.3.0</version>
		</dependency>
 * </p>
 * 2.3.0和1.5.2的区别:主要是:
 *  1.5.2：CronTrigger &　SimpleTrigger 为类，继承extends Trigger
 *  2.3.0:ScheduleBuilder<SimpleTrigger>,<CronTrigger><DailyTimeIntervalTrigger> <DailyTimeIntervalTrigger>  
 *  
 * @author wanghuihui
 * @date 2017-8-11上午12:28:23
 */
public class QuartzAllJobDemo {
	
	public void SimpleScheduleTest() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		//HelloJobAdaptor myJob = new HelloJobAdaptor(Hello.class, "test");
		
		//JobDetail job = newJob(myJob).withIdentity("job1", "group1").build();
		
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("methodName", "test");
		dataMap.put("clazzName", "Hello");
		JobDetail job = newJob(HelloJobAdaptor.class).withIdentity("job1", "group1").setJobData(dataMap).build();
		
		// 在指定时间，按照指定周期和次数重复激活
		SimpleScheduleBuilder scheduleBuilder = simpleSchedule().repeatSecondlyForTotalCount(5, 2);
		//如果startAt的時間大于now,則立即執行。
		Trigger trigger = TriggerBuilder.newTrigger().startAt(DateBuilder.dateOf(1, 13, 0, 11, 8, 2017)).withSchedule(scheduleBuilder).build();
		//Trigger trigger = TriggerBuilder.newTrigger().startNow().withSchedule(scheduleBuilder).build();
		Date startDate = scheduler.scheduleJob(job, trigger);
		scheduler.start();
	}
	

/*	public void SimpleScheduleTest() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();
		// 在指定时间，按照指定周期和次数重复激活
		SimpleScheduleBuilder scheduleBuilder = simpleSchedule().repeatSecondlyForTotalCount(5, 2);
		//如果startAt的時間大于now,則立即執行。
		Trigger trigger = TriggerBuilder.newTrigger().startAt(DateBuilder.dateOf(1, 13, 0, 11, 8, 2017)).withSchedule(scheduleBuilder).build();
		//Trigger trigger = TriggerBuilder.newTrigger().startNow().withSchedule(scheduleBuilder).build();
		Date startDate = scheduler.scheduleJob(job, trigger);
		scheduler.start();
	}
*/
	public void CalendarIntervalScheduleTest() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		JobDetail job = newJob(HelloJob.class).withIdentity("job", "group1").build();
		// 每隔5个月，在指定日期和时间激活任务
		CalendarIntervalScheduleBuilder calenderSchedule = calendarIntervalSchedule().withInterval(1, IntervalUnit.SECOND);
		Trigger trigger = newTrigger().startAt(DateBuilder.dateOf(0, 53, 30, 11, 8, 2017)).endAt(DateBuilder.dateOf(1, 17, 0, 11, 8, 2017)).withSchedule(calenderSchedule).build();
		//scheduler.addJob(job, true);
		scheduler.scheduleJob(job,trigger);
		scheduler.start();
	}

	public void CronScheduleTest() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("methodName", "test");
		dataMap.put("clazzName", "Hello");
		
		//JobDetail job = newJob(HelloJobAdaptor.class).withIdentity("job2", "group1").build();
		JobDetail job = newJob(HelloJobAdaptor.class).withIdentity("job2", "group1").setJobData(dataMap).build();
		// 每个周一到周五，早上8点-11点的整点激活任务，从明天早上9点开始
		CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
		//Trigger trigger = newTrigger().startAt(DateBuilder.tomorrowAt(9, 0, 0)).withIdentity("trigger2", "group1").withSchedule(cronSchedule("* 0 8-11 ? * MON-FRI")).build();
		Trigger trigger = newTrigger().startNow().withIdentity("trigger2", "group1").withSchedule(cronSchedule).build();
		Date startDate = sched.scheduleJob(job, trigger);
		sched.start();
	}

	public void DailyTimeIntervalScheduleTest() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		JobDetail job = newJob(HelloJob.class).withIdentity("job2", "group1").build();
		
		Set<Integer> daysOfWeek = new HashSet<Integer>();
		daysOfWeek.add(Calendar.SATURDAY);
		daysOfWeek.add(Calendar.SUNDAY);
		daysOfWeek.add(Calendar.FRIDAY);
		// 每个周末，00:10点-4點半分，每隔1分钟激活1次
		DailyTimeIntervalScheduleBuilder dailySchedule = dailyTimeIntervalSchedule().onDaysOfTheWeek(daysOfWeek)
						.withInterval(1, IntervalUnit.MINUTE)
						.withRepeatCount(5)
						.startingDailyAt(new TimeOfDay(00, 10))
						.endingDailyAt(new TimeOfDay(04, 30));
		Trigger trigger = newTrigger().startNow().withSchedule(dailySchedule).build();
		Date startDate = sched.scheduleJob(job, trigger);
		sched.start();
	}

	

	public static void main(String[] args) throws Exception {
		QuartzAllJobDemo example = new QuartzAllJobDemo();
		//example.SimpleScheduleTest();
		//example.CalendarIntervalScheduleTest();
		//example.DailyTimeIntervalScheduleTest();
		example.CronScheduleTest();
	}
}

