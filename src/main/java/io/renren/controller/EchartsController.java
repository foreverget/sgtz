package io.renren.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.json.GsonUtil;

import io.renren.service.EchartsService;
import io.renren.utils.R;


@RestController
@RequestMapping("/echarts")
public class EchartsController extends AbstractController {
	
	@Autowired
	private EchartsService echartsService;
	
	/**
	 * 获取关系图表
	 */
	@RequestMapping("/getRelationChart")
	public R getRelationChart(){
		Option option = echartsService.getOption();
		return R.ok().put("option", GsonUtil.format(option));
	}
	
	
}
