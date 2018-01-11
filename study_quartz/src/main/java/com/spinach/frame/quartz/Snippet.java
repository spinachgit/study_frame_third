package com.spinach.frame.quartz;

public class Snippet {
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println(1);
						Thread.sleep(1000L); // 线程完成后延时10秒（做成参数）
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		thread.start();
	}
}
