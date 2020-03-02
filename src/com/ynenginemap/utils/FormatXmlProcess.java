package com.ynenginemap.utils;

import java.util.Date;

/**
 * 封装最终的xml格式结果
 * @author admin
 *
 */
public class FormatXmlProcess {
	 /** 
     * 封装文字类的返回消息 
     * @param to 
     * @param from 
     * @param content 
     * @return 
     */  
    public String formatXmlAnswer(String to, String from, String content,String MsgType) {  
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[");  
        sb.append(MsgType);
        sb.append("]]></MsgType><Content><![CDATA[");
        sb.append(content);  
        sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");  
        return sb.toString();  
    }  
}
