package com.spinach.frame.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * <p>
 * https://www.quartz-scheduler.org/documentation/quartz-2.1.x/quick-start.html
 * </p>
 * @author wanghuihui
 * @date 2017-8-17下午2:45:39
 */
public class QuartzTest {

	public static void main(String[] args) {

		try {
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			// define the job and tie it to our HelloJob class
			  JobDetail job = newJob(HelloJob.class)
		      .withIdentity("job1", "group1")
		      .build();

		  // Trigger the job to run now, and then repeat every 40 seconds
		  Trigger trigger = newTrigger()
		      .withIdentity("trigger1", "group1")
		      .startNow()
		            .withSchedule(simpleSchedule()
		              .withIntervalInSeconds(40)
		              .repeatForever())            
		      .build();

		  // Tell quartz to schedule the job using our trigger
		  scheduler.scheduleJob(job, trigger);
			// and start it off
			scheduler.start();

			scheduler.shutdown();

		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}
}