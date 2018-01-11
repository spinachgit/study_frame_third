package com.spinach.frame.quartz;

import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobExecutionException;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib动态代理 intercept(Object obj, Method method, Object[] args,MethodProxy
 * proxy) 是CGLib定义的Inerceptor接口的方法，一般使用 proxy进行调用父类方法，这样更快。
 * 引入:<dependency>
		    <groupId>cglib</groupId>
		    <artifactId>cglib</artifactId>
		    <version>3.2.5</version>
		</dependency>
 * @author wanghuihui
 */
public class DynamicProxyCGlib implements MethodInterceptor {

	private Enhancer enhancer = new Enhancer();

	public Object getProxy(Class c) {
		enhancer.setSuperclass(c); // ① 设置需要创建子类的类
		enhancer.setCallback(this);
		return enhancer.create(); // ②通过字节码技术动态创建子类实例
	}
	
	/**
	 *  方法调用
	 * @param obj  表示代理后的子类
	 * @param method 父类方法的反射对象
	 * @param args 方法的动态入参
	 * @param proxy proxy为代理类实例
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("--------在调用要代理类的方法前做的处理----------");
		proxy.invokeSuper(obj, args);
		System.out.println("--------在调用要代理类的方法后做的处理----------");
		return null;
	}
	
	public static void main(String[] args) throws JobExecutionException {
		//因为使用了接口HelloService绑定了代理对象，所以可以用HelloService作为代理对象的声明. 
		DynamicProxyCGlib proxy = new DynamicProxyCGlib();
		Job mytask = (Job) proxy.getProxy(HelloJob.class);
		//此时使用代理对象运行方法进入HelloProxy的invoke方法里 
		mytask.execute(null);
	}

}