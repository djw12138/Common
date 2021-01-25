package com.djw.ums;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpTest {
	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).setConnectionRequestTimeout(15000).build();

	public static void main(String[] args) {
		String info = null;
		try{
			HttpPost post = new HttpPost("http://sms.api.ums86.com:8899/sms/Api/Send.do");
	        String params = "SpCode=1&LoginName=1&Password=1&MessageContent=你好&UserNumber=18068161321&SerialNumber=&ScheduleTime=&ExtendAccessNum=&f=1";
			StringEntity stringEntity = new StringEntity(params, "gbk");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			post.setEntity(stringEntity);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			post.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(post);
			String retMessage = EntityUtils.toString(response.getEntity(),"gbk");
			System.out.println(retMessage);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		String info = null;
//		try{
//			CloseableHttpClient httpClient = HttpClients.createDefault();
//			PostMethod post = new PostMethod("http://112.65.228.39:8897/sms/Api/Send.do");//
//			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
//			post.addParameter("SpCode", "1");
//			post.addParameter("LoginName", "1");
//			post.addParameter("Password", "1");
//			post.addParameter("MessageContent", "你好");
//			post.addParameter("UserNumber", "18616330318");
//			post.addParameter("SerialNumber", "");
//			post.addParameter("ScheduleTime", "");
//			post.addParameter("ExtendAccessNum", "");
//			post.addParameter("f", "1");
//			httpclient.executeMethod(post);
//			info = new String(post.getResponseBody(),"gbk");
//			System.out.println(info);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public static String getByteString( byte[] buff_out )
	{
		StringBuffer strBuf = new StringBuffer(buff_out.length * 3);
		strBuf.append("Length[");
		strBuf.append(buff_out.length);
		strBuf.append("];Content[");
		for ( int i = 0 ; i < buff_out.length ; ++i ) {
			int l = buff_out[i] & 0x0F;
			int h = (buff_out[i] & 0xF0) >> 4;

			char ll = (char) (l > 9 ? 'a' + l - 10 : '0' + l);
			char hh = (char) (h > 9 ? 'a' + h - 10 : '0' + h);

			strBuf.append(hh);
			strBuf.append(ll);
			strBuf.append(" ");
		}
		strBuf.append("]");
		return strBuf.toString().toUpperCase();
	}

}
