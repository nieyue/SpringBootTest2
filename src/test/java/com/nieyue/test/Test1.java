package com.nieyue.test;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Test1 {
public static void main(String[] args) throws InterruptedException{
	long a=Calendar.getInstance().getTimeInMillis();
	System.out.println(a);
	Thread.sleep(2000,10000);
	long b=Calendar.getInstance().getTimeInMillis();
	System.out.println(b-a);
	
}
}
