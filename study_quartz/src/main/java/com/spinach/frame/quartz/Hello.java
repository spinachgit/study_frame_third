package com.spinach.frame.quartz;

import java.util.Date;

import org.quartz.Job;
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
public class Hello  {

	public void test() {
		System.out.print("hello--->test: print!");
	}
	
}