package com.spinach.frame.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>
 *  job的 执行类
 * </p>
 * @author wanghuihui
 * @date 2017-8-10下午11:54:36
 * Interface chInterface
 */
public class HelloJobAdaptor implements Job {
	private Object target ; 
	private String methodName ; 

	public HelloJobAdaptor(Object target,String method) {
		this.target = target;
		this.methodName = method;
	}


	public HelloJobAdaptor() {}


	public void execute(JobExecutionContext context) throws JobExecutionException {
		Object obj = context.getMergedJobDataMap().get("targetObjectId");
		/*Class<? extends Object> clazz = target.getClass();
		System.out.println(clazz);
		try {
			Method method = clazz.getDeclaredMethod(methodName);
			Object message = method.invoke(clazz);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}*/
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap dataMap = jobDetail.getJobDataMap();
		System.out.printf("HelloJob executed at %s%n", new Date());
	}
	
	//select * from Qrtz_job_details
}