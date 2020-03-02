package com.ynenginemap.utils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class Mapping {
	/**将t 的值 赋值给 s ,返回   s ,id 为忽略的跳过的值  */
	public static  <T> T get(T t,T s,String id){//t为前台传来之值
		if(t == null || s == null){
			System.out.println("赋值出错：t 和 s 不能为空值");
			return null;
		}
		Method[] methods = t.getClass().getMethods();
        for(Method method : methods ){
            String methodM = method.getName();
            if(!methodM.startsWith("get")) continue;
            if(methodM.contains(id)) continue;
            if(methodM.contains("Class")) continue;
            String methodSuff = methodM.replace("get", "");
            if(methodSuff == null || methodSuff.length()==0) continue;
            Method getM =null;
            try {
                getM = t.getClass().getMethod("get"+methodSuff);
            } catch (Exception e) {
                System.out.println(methodSuff+"==========methodSuff=========");
            }
            if(getM == null) continue;
            Object val = null;
            try {
                val = getM.invoke(t);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("=============val===========");
            }
            if(val == null) continue;
            Method setM = null;
            try {
                setM = s.getClass().getMethod("set"+methodSuff, val.getClass());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("===========setM============");
            }
            if(setM == null) continue;
            try {
                setM.invoke(s, val);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("============ setM.invoke()========");
            }
        }
		return s;
	}
	
	public static  <T> T get(T t,T s,List<String> l){//t为前台传来之值
		Method[] methods = t.getClass().getMethods();
        for(Method method : methods ){
        	boolean a = false;
            String methodM = method.getName();
            if(!methodM.startsWith("get")) continue;
            if(methodM.contains("Class")) continue;
            
            for(String id : l){
            	if(methodM.contains(id)){
            		a = true;
            	}            	
            }
            
            if(a) continue;
            String methodSuff = methodM.replace("get", "");
            if(methodSuff == null || methodSuff.length()==0) continue;
            Method getM =null;
            try {
            	getM = t.getClass().getMethod("get"+methodSuff);
            } catch (Exception e) {
            	System.out.println(methodSuff+"==========methodSuff=========");
            }
            if(getM == null) continue;
            Object val = null;
            try {
            	val = getM.invoke(t);
            } catch (Exception e) {
            	e.printStackTrace();
            	System.out.println("=============val===========");
            }
            if(val == null) continue;
            Method setM = null;
            try {
            	setM = s.getClass().getMethod("set"+methodSuff, val.getClass());
            } catch (Exception e) {
            	e.printStackTrace();
            	System.out.println("===========setM============");
            }
            if(setM == null) continue;
            try {
            	setM.invoke(s, val);
            } catch (Exception e) {
            	e.printStackTrace();
            	System.out.println("============ setM.invoke()========");
            }
        }
		return s;
	}
	/**将 t中的数据 赋值到 s中，跳过 里面的数值  */
	public static  <T> T get1(T t,T s,List<String> l){//t为前台传来之值
		Method[] methods = t.getClass().getMethods();
        for(Method method : methods ){
        	boolean a = false;
            String methodM = method.getName();
            if(!methodM.startsWith("get")) continue;
            if(methodM.contains("Class")) continue;
            if(methodM.contains("Uuid")) continue;
            if(l.contains(methodM)) continue;
            String methodSuff = methodM.replace("get", "");
            if(methodSuff == null || methodSuff.length()==0) continue;
            Method getM =null;
            try {
            	getM = t.getClass().getMethod("get"+methodSuff);
            } catch (Exception e) {
            	System.out.println(methodSuff+"==========methodSuff=========");
            }
            if(getM == null) continue;
            Object val = null;
            try {
            	val = getM.invoke(t);
            } catch (Exception e) {
            	e.printStackTrace();
            	System.out.println("=============val===========");
            }
            if(val == null) continue;
            Method setM = null;
            try {
            	setM = s.getClass().getMethod("set"+methodSuff, val.getClass());
            } catch (Exception e) {
            	e.printStackTrace();
            	System.out.println("===========setM============");
            }
            if(setM == null) continue;
            try {
            	setM.invoke(s, val);
            } catch (Exception e) {
            	e.printStackTrace();
            	System.out.println("============ setM.invoke()========");
            }
        }
		return s;
	}
	
	
	public static  <T> T getEntity(T s,Map<Object,Object> m){//t为前台传来之值
		Method[] methods = s.getClass().getMethods();
        for(Method method : methods ){
        	boolean a = false;
            String methodM = method.getName();
            if(!methodM.startsWith("get")) continue;
            if(methodM.contains("Class")) continue;
            if(methodM.contains("Uuid")) continue;
            String methodSuff = methodM.replace("get", "");
            if(methodSuff == null || methodSuff.length()==0) continue;
            
            Object val = m.get(methodSuff);
            if(val == null){
            	continue;
            }
            
            Method setM = null;
            try {
            	setM = s.getClass().getMethod("set"+methodSuff, val.getClass());
            } catch (Exception e) {
            	e.printStackTrace();
            	System.out.println("===========setM============");
            }
            if(setM == null) continue;
            try {
            	setM.invoke(s, val);
            } catch (Exception e) {
            	e.printStackTrace();
            	System.out.println("============ setM.invoke()========");
            }
        }
		return s;
	}
	
	
}
