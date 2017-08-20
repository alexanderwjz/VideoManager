package com.alexandewjz.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Cat cat=new Cat();
		Thread t=new Thread(cat);
		t.start();*/
		String filename=" ";
		Pattern p=Pattern.compile(".*\\.mp4");
		Matcher m=p.matcher(filename);
		if(m.matches()){
			System.out.println("匹配成功");
		}else{
			System.out.println("匹配成失败");
		}
	}
}
class Cat implements Runnable{
	public void run(){
		int i=0;
		while(i<10){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("123");
		i++;
		}
	}
}