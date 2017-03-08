package io.renren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author david
 *
 */
@Controller
public class SysPageController {
	
	/**
	 * /src/main/webapp/WEB-INF/page/sys路径下请求
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping("sys/{url}.html")
	public String sysPage(@PathVariable("url") String url){
		return "sys/" + url + ".html";
	}
	
	/**
	 * /src/main/webapp/WEB-INF/page/echarts路径下请求
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping("echarts/{url}.html")
	public String echartsPage(@PathVariable("url") String url){
		return "echarts/" + url + ".html";
	}

}
