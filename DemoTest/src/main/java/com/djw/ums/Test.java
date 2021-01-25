package com.djw.ums;

import com.djw.ums.common.Base64;
import com.djw.ums.common.MD5;

import java.io.UnsupportedEncodingException;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] b = {
				(byte)0x6E,
				(byte)0x29,
				(byte)0x99,
				(byte)0xA8,
				(byte)0x63,
				(byte)0xD0,
				(byte)0x79,
				(byte)0x3A,
				(byte)0xFF,
				(byte)0x0C,
				(byte)0x62,
				(byte)0x2A,
				(byte)0x6B,
				(byte)0x62,
				(byte)0x00,
				(byte)0x35,
				(byte)0x67,
				(byte)0x08,
				(byte)0x00,
				(byte)0x35,
				(byte)0x65,
				(byte)0xE5,
				(byte)0x00,
				(byte)0x32,
				(byte)0x00,
				(byte)0x34,
				(byte)0x65,
				(byte)0xF6,
				(byte)0xFF,
				(byte)0x0C,
				(byte)0x60,
				(byte)0xA8,
				(byte)0x5F,
				(byte)0x53,
				(byte)0x67,
				(byte)0x08,
				(byte)0x59,
				(byte)0x57,
				(byte)0x99,
				(byte)0x10,
				(byte)0x51,
				(byte)0x85,
				(byte)0x6D,
				(byte)0x41,
				(byte)0x91,
				(byte)0xCF,
				(byte)0x5D,
				(byte)0xF2,
				(byte)0x4F,
				(byte)0x7F,
				(byte)0x75,
				(byte)0x28,
				(byte)0x00,
				(byte)0x34,
				(byte)0x00,
				(byte)0x38,
				(byte)0x00,
				(byte)0x2E,
				(byte)0x00,
				(byte)0x36,
				(byte)0x00,
				(byte)0x35,
				(byte)0x00,
				(byte)0x4D,
				(byte)0x00,
				(byte)0x42,
				(byte)0xFF,
				(byte)0x0C,
				(byte)0x52,
				(byte)0x69,
				(byte)0x4F,
				(byte)0x59,
				(byte)0x6D,
				(byte)0x41,
				(byte)0x91,
				(byte)0xCF
		};
		try {
			System.out.println(new String( b,"UTF-16BE"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String getAuthen(String timestamp, String transactionid,
			String streamingno, String chargepartytype, String bizid,
			String custid, String userid, String productid, String capacityid,
			String sid) {
		MD5 md5 = new MD5();
		String datestr = "";
		try {
			String s = timestamp + transactionid + streamingno
					+ chargepartytype + bizid + custid + userid + productid
					+ capacityid + sid;
			System.out.println(s);

			datestr = Base64.encode(md5.md5(s));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return datestr;
	}
}
