package com.ynenginemap.utils;

import java.util.HashMap;
import java.util.Map;
/*
 * 缓存accessToken 的Map  ,map中包含 一个accessToken 和 缓存的时间戳
 */
public class Singleton {
	  //缓存accessToken 的Map  ,map中包含 一个accessToken 和 缓存的时间戳
    private Map<String, Object> m_ssgx = new HashMap<String, Object>();
    private Map<Object, Object> m_wzgx = new HashMap<Object, Object>();
    private Map<Object, Object> m_tjgx = new HashMap<Object, Object>();
    private Map<Object, Object> m_WebSocket_LocationSharing = new HashMap<Object, Object>();//位置实时共享WebSocket缓存
    private Map<String, Object> m_ApplicationContext = new HashMap<String, Object>();//Web容器
    private Map<String, Object> m_RealTimeSharing = new HashMap<String, Object>();//实时位置共享信息
    private Map<Object, Object> m_WebSocket_LocationSharing_User = new HashMap<Object, Object>();//位置实时共享用户WebSocket缓存
    
    public Map<Object, Object> getM_WebSocket_LocationSharing() {
		return m_WebSocket_LocationSharing;
	}
	public void setM_WebSocket_LocationSharing(
			Map<Object, Object> m_WebSocket_LocationSharing) {
		this.m_WebSocket_LocationSharing = m_WebSocket_LocationSharing;
	}
	public Map<Object, Object> getM_WebSocket_LocationSharing_User() {
		return m_WebSocket_LocationSharing_User;
	}
	public void setM_WebSocket_LocationSharing_User(
			Map<Object, Object> m_WebSocket_LocationSharing_User) {
		this.m_WebSocket_LocationSharing_User = m_WebSocket_LocationSharing_User;
	}
	public Map<Object, Object> getM_tjgx() {
		return m_tjgx;
	}
	public void setM_tjgx(Map<Object, Object> m_tjgx) {
		this.m_tjgx = m_tjgx;
	}
	private Singleton() {
    }
 
    private static Singleton single = null;
 
    // 静态工厂方法
    public static Singleton getInstance() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }
	public Map<String, Object> getM_ssgx() {
		return m_ssgx;
	}
	public void setM_ssgx(Map<String, Object> m_ssgx) {
		this.m_ssgx = m_ssgx;
	}
	public Map<Object, Object> getM_wzgx() {
		return m_wzgx;
	}
	public void setM_wzgx(Map<Object, Object> m_wzgx) {
		this.m_wzgx = m_wzgx;
	}
	public static Singleton getSingle() {
        return single;
    }
 
    public static void setSingle(Singleton single) {
        Singleton.single = single;
    }
	public Map<String, Object> getM_ApplicationContext() {
		return m_ApplicationContext;
	}
	public void setM_ApplicationContext(Map<String, Object> m_ApplicationContext) {
		this.m_ApplicationContext = m_ApplicationContext;
	}
	public Map<String, Object> getM_RealTimeSharing() {
		return m_RealTimeSharing;
	}
	public void setM_RealTimeSharing(Map<String, Object> m_RealTimeSharing) {
		this.m_RealTimeSharing = m_RealTimeSharing;
	}
	
}
