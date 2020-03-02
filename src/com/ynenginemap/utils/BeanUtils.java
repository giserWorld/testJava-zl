package com.ynenginemap.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 检测实体对象所有属性是否为空 
 * 为空返回true
 * @author admin
 *
 */
public class BeanUtils {
	  public static boolean checkFieldValueNull(Object bean) {  //为空返回true
	        boolean result = true;
	        if (bean == null) {
	            return true;
	        }
	        Class<?> cls = bean.getClass();
	        Method[] methods = cls.getDeclaredMethods();
	        Field[] fields = cls.getDeclaredFields();
	        for (Field field : fields) {
	            try {
	                String fieldGetName = parGetName(field.getName());
	                if (!checkGetMet(methods, fieldGetName)) {
	                	fieldGetName = parIsName(field.getName());
	                	if(!checkGetMet(methods, fieldGetName)){
	                		 continue;               		
	                	}
	                   
	                }
	                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
	                Object fieldVal = fieldGetMet.invoke(bean, new Object[]{});
	                if (fieldVal != null && !"".equals(fieldVal) && !"null".equals(fieldVal)) {
	                       result = false;
	                }
	            } catch (Exception e) {
	            	System.out.println(e.getMessage());
	                continue;
	            }
	        }
	        return result;
	    }
	  
	  /**判断属性是否为空，是则返回true*/
	  public static boolean checkFieldValueNull(Object bean,String fieldName) {
		  boolean result = true;
	        if (bean == null) {
	            return true;
	        }
	        
	        Class<?> cls = bean.getClass();
	        Method[] methods = cls.getDeclaredMethods();
	        Field[] fields = cls.getDeclaredFields();
	        
	        try {
                String fieldGetName = parGetName(fieldName);
                if (!checkGetMet(methods, fieldGetName)) {
                	fieldGetName = parIsName(fieldName);
                	if(!checkGetMet(methods, fieldGetName)){
                		return true;                		
                	}
                }
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[]{});
                if (fieldVal != null) {
                    if ("".equals(fieldVal)) {
                        result = true;
                    } else {
                        result = false;
                    }
                }
            } catch (Exception e) {
            	System.out.println(e.getMessage());
            	return true;
            }
	        return result;
	  }


	    /**
	     * 拼接某属性的 get方法
	     *
	     * @param fieldName
	     * @return String
	     */
	    public static String parGetName(String fieldName) {
	        if (null == fieldName || "".equals(fieldName)) {
	            return null;
	        }
	        int startIndex = 0;
	        if (fieldName.charAt(0) == '_')
	            startIndex = 1;
	        return "get"
	                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
	                + fieldName.substring(startIndex + 1);
	    }
	    
	    /**
	     * 拼接某属性的 is方法
	     *
	     * @param fieldName
	     * @return String
	     */
	    public static String parIsName(String fieldName) {
	        if (null == fieldName || "".equals(fieldName)) {
	            return null;
	        }
	        int startIndex = 0;
	        if (fieldName.charAt(0) == '_')
	            startIndex = 1;
	        return "is"
	                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
	                + fieldName.substring(startIndex + 1);
	    }

	    /**
	     * 判断是否存在某属性的 get或is方法
	     *
	     * @param methods
	     * @param fieldGetMet
	     * @return boolean
	     */
	    public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
	        for (Method met : methods) {
	            if (fieldGetMet.equals(met.getName())) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    
	    
	    /**
	     * 用于做控制权限使用
	     * @param 
	     */
	    public static String Qxkz(String userid){
			return userid;
	    }
	    public static void main(String[] args) {
//	    	SearchScshtjBean bean = new SearchScshtjBean();
//	    	bean.setA21("true");
//	    	boolean a = checkFieldValueNull(bean);
//	    	System.out.println(a);
		}
}
