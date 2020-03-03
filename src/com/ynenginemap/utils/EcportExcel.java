package com.ynenginemap.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HCY
 * @version 创建时间：2018年9月13日 上午9:31:59
 * 类说明
 */
public class EcportExcel {

	/**
	 * 首页查询列表导出
	 * @param list
	 * @param userId
	 * @param xzdm
	 * @param tjlx
	 * @param request
	 * @param response
	 * @return
	 */
	public Map<String, Object> ecportExcel(List<Map<String, Object>> list,String userId,String xzdm,String tjlx,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> map0 = new HashMap<String, Object>();
			//表头
			String wzlx = "";
			String cjr = "";
			String cjsj = "";
			String wzmc = "";
			String clzt = "";
			if(!"合法建筑".equals(tjlx)){
				String[] top = {"序号","违章类型","户主","采集人","采集时间","位置","处理状态"};
				List<Object[]> list0 = new ArrayList<Object[]>();
				for(Map<String, Object> m : list){
					if(m.get("wzlx") != null){
						wzlx = (String) m.get("wzlx");
					}
					if(m.get("cjr") != null){
						cjr = (String) m.get("cjr");
					}
					if(m.get("cjsj") != null){
						cjsj = (String) m.get("cjsj");
					}
					if(m.get("wzmc") != null){
						wzmc = (String) m.get("wzmc");
					}
					if(m.get("clzt") != null){
						clzt = (String) m.get("clzt");
					}
					Object[] objs={m.get("xh"),wzlx,m.get("hzmc"),cjr,cjsj,wzmc,clzt};
					list0.add(objs);
				}
				map0.put("top", top);
				map0.put("data", list0);
			}else{
				String[] top = {"序号","户主","采集人","采集时间","位置"};
				List<Object[]> list0 = new ArrayList<Object[]>();
				for(Map<String, Object> m : list){
					if(m.get("cjr") != null){
						cjr = (String) m.get("cjr");
					}
					if(m.get("cjsj") != null){
						cjsj = (String) m.get("cjsj");
					}
					if(m.get("wzmc") != null){
						wzmc = (String) m.get("wzmc");
					}
					Object[] objs={m.get("xh"),m.get("hzmc"),cjr,cjsj,wzmc};
					list0.add(objs);
				}
				map0.put("top", top);
				map0.put("data", list0);
			}
			String path = ExcelUtil.creataExcel(map0, userId, "导出用户信息列表");
			System.out.println("导出用户信息列表 path = "+path);
			FileDownload filess = new FileDownload();
			filess.download(path, request,response);
			map.put("path", path);
			map.put("success", true);
			map.put("message", "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "导出失败");
		}

		return map;
	}
	

	public Map<String, Object> ecportExcelHj(List<Map<String, Object>> list,String userId,String xzdm,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> map0 = new HashMap<String, Object>();
			//表头
			String hzmc = "";
			String sex = "";
			String idcard = "";
			String csrq = "";
			String lxdh = "";
			String jtzz = "";
			String[] top = {"户主姓名","性别","身份证号","出生年月","联系电话","家庭住址"};
			List<Object[]> list0 = new ArrayList<Object[]>();
			for(Map<String, Object> m : list){
				if(m.get("xm") != null){
					hzmc = (String) m.get("xm");
				}
				if(m.get("sex") != null){
					sex = (String) m.get("sex");
				}
				if(m.get("idcard") != null){
					idcard = (String) m.get("idcard");
				}
				if(m.get("csrq") != null){
					csrq = (String) m.get("csrq");
				}
				if(m.get("lxdh") != null){
					lxdh = (String) m.get("lxdh");
				}
				if(m.get("jtzz") != null){
					jtzz = (String) m.get("jtzz");
				}
				Object[] objs={hzmc,sex,idcard,csrq,lxdh,jtzz};
				list0.add(objs);
			}
			map0.put("top", top);
			map0.put("data", list0);
			String path = ExcelUtil.creataExcel(map0, userId, "导出户主信息列表");
			System.out.println("导出用户信息列表 path = "+path);
			FileDownload filess = new FileDownload();
			filess.download(path, request,response);
			map.put("path", path);
			map.put("success", true);
			map.put("message", "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "导出失败");
		}

		return map;
	}
	

	public Map<String, Object> ecportExcelWzjz(List<Map<String, Object>> list,String userId,String xzdm,String type,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> map0 = new HashMap<String, Object>();
			//表头
			String xh = "";
			String wzlx = "";
			String hzmc = "";
			String cjr = "";
			String cjsj = "";
			String wzmc = "";
			String clzt = "";
			String[] top = {"序号","类型","户主","采集人","采集时间","位置","状态"};
			List<Object[]> list0 = new ArrayList<Object[]>();
			int aa = 1;
			for(Map<String, Object> m : list){
				xh = aa+"";
				if(m.get("wzlx") != null){
					wzlx = (String) m.get("wzlx");
				}
				if(m.get("hzmc") != null){
					hzmc = (String) m.get("hzmc");
				}
				if(m.get("cjr") != null){
					cjr = (String) m.get("cjr");
				}
				if(m.get("cjsj") != null){
					cjsj = (String) m.get("cjsj");
				}
				if(m.get("wzmc") != null){
					wzmc = (String) m.get("wzmc");
				}
				if(m.get("clzt") != null){
					clzt = (String) m.get("clzt");
				}
				Object[] objs={xh,wzlx,hzmc,cjr,cjsj,wzmc,clzt};
				list0.add(objs);
				aa++;
			}
			map0.put("top", top);
			map0.put("data", list0);
			String name = "";
			if("违章建筑".equals(type)){
				name = "导出违章建筑信息列表";
			}else if("快速查询".equals(type)){
				name = "导出快速查询建筑信息列表";
			}else if("何范围查询".equals(type)){
				name = "导出几何范围查询建筑信息列表";
			}
			String path = ExcelUtil.creataExcel(map0, userId, name);
			System.out.println(name+" path = "+path);
//			FileDownload filess = new FileDownload();
//			filess.download(path, request,response);
			map.put("path", path);
			map.put("success", true);
			map.put("message", "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "导出失败");
		}

		return map;
	}
	
}
