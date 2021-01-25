/*
package com.djw.ums;

import java.io.File;

import sun.misc.BASE64Encoder;

import cn.com.flaginfo.ems.send.EmsSend;


public class EmsSendTest {

	public static void main(String[] args) {

		EmsSend emsSend = new EmsSend("http://112.65.228.36:5555/sms/sendEms.do","1","2","3");
		try{
			File file0 = new File("D:\\12test\\1.txt");
			File file1 = new File("D:\\12test\\yy.jpg");
			File[] files = new File[2];
			files[0]=file0;
			files[1]=file1;
			boolean b = emsSend.send("E信接口测试6", "18616330318", null, null, files);
			if(b){
				System.out.println("发送成功");
			}else{
				System.out.println("发送失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}


	}
}
*/
