package _09_.payment.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;

public class createPayLink {
	public static AllInOne all;
	
	private static void initial(){
		all = new AllInOne("");
	}
	
	
	@SuppressWarnings("deprecation")
	public static String genAioCheckOutOneTime(String merchantTradeNo, String totalAmount, String itemName, String url) throws UnsupportedEncodingException{
		initial();
		
		Date dNow = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

		AioCheckOutOneTime obj = new AioCheckOutOneTime();
		obj.setMerchantTradeNo(merchantTradeNo);
		obj.setMerchantTradeDate(formatter.format(dNow));
		obj.setTotalAmount(totalAmount);
		obj.setTradeDesc(URLEncoder.encode("貓貓測試"));
		obj.setItemName(itemName);
		obj.setReturnURL("httP://" + url + "confirmPay");
		obj.setReturnURL("httP://" + url + "confirmPay");
		obj.setClientBackURL("httP://" + url + "Orders.jsp");
//		obj.setReturnURL("http://12cbd622.ngrok.io/Mid_Project/confirmPay");
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
		return form;
	} 
	
	
}
