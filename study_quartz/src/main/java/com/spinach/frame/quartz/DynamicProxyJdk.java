package com.spinach.frame.quartz;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.quartz.Job;
import org.quartz.JobExecutionException;

public class DynamicProxyJdk implements InvocationHandler {
	private Object target;

	/**
	 * 生成代理对象，并和真实服务对象绑定.
	 * @param target 真实服务对线下
	 * @return 代理对象
	 */
	public Object bind(Object target) {
		this.target = target; // 生成代理对象,并绑定.
		Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
		// 指明代理类，this代表用当前类对象，那么就要求其实现InvocationHandler接口
		return proxy;
	}

	/**
	 * 当生成代理对象时，第三个指定使用HelloProxy进行代理时， 代理对象调用的方法就会进入这个方法。
	 * @param proxy ——代理对象
	 * @param method -- 被调用的方法
	 * @param args -- 方法参数
	 * @return 代理方法返回。
	 * @throws Throwable -- 异常处理
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.err.println("反射真实对象方法前");
		Object obj = method.invoke(target, args);// 相当于sayHello方法调用.
		System.err.println("反射真实对象方法后");
		return obj;
	}

	public static void main(String[] args) throws JobExecutionException {
		//因为使用了接口HelloService绑定了代理对象，所以可以用HelloService作为代理对象的声明. 
		DynamicProxyJdk proxy = new DynamicProxyJdk();
		Job mytask = (Job) proxy.bind(new HelloJob());
		//此时使用代理对象运行方法进入HelloProxy的invoke方法里 
		mytask.execute(null);
	}
}