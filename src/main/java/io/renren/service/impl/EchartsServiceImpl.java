package io.renren.service.impl;

import org.springframework.stereotype.Service;

import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.code.BrushType;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.series.Force;
import com.github.abel533.echarts.series.force.Category;
import com.github.abel533.echarts.series.force.Link;
import com.github.abel533.echarts.series.force.Node;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.LinkStyle;
import com.github.abel533.echarts.style.NodeStyle;
import com.github.abel533.echarts.style.TextStyle;
import com.github.abel533.echarts.style.itemstyle.Normal;

import io.renren.service.EchartsService;

@Service("echartsService")
public class EchartsServiceImpl implements EchartsService {

	@Override
	public Option getOption() {
		
        Option option = new Option();
        option.title().text("股权关系：首钢集团").subtext("").x(X.left).y(Y.bottom);
        option.tooltip().trigger(Trigger.item).formatter("{a} : {b}");
        // 工具栏
        option.toolbox().show(true);
        option.legend("股东", "控股").legend().x(X.left);

        //数据
        Force force = new Force("股权关系");
        
        Category gudong = new Category();
        gudong.setName("股东");
        gudong.setSymbol("diamond");
        force.categories("股权", gudong, "控股");
        
        force.itemStyle().normal()
                .label(new Label().show(true).textStyle(new TextStyle().color("#333")))
                .nodeStyle().brushType(BrushType.both).color();

        force.itemStyle().emphasis()
                .linkStyle(new LinkStyle())
                .nodeStyle(new NodeStyle())
                .label().show(false);
        
        
		force.useWorker(false).minRadius(15).maxRadius(25).gravity(1.1).scaling(1.2).linkSymbol(Symbol.arrow).steps(10)
				.coolDown(0.9);
		Node mainNode = new Node(0, "首钢集团", 50);
		mainNode.setSymbol("image://http://59.110.63.49/sgtz/statics/images/SGJT.jpg");
		mainNode.setSymbolSize(40);
		mainNode.setDraggable(true);
		mainNode.setItemStyle(new ItemStyle()
				.normal(new Normal().label(new Label().position("right").textStyle(new TextStyle().color("black")))));
		
//		mainNode.initial("[500,500]");
//		mainNode.fixX(true);
//		mainNode.fixY(true);

		force.nodes(mainNode,
                new Node(1, "首钢股东1", 1),
                new Node(1, "首钢股东2", 1),
                new Node(2, "首钢股份", 1),
                new Node(2, "京唐公司", 1),
                new Node(2, "迁钢公司", 1),
                new Node(2, "顺义公司", 1));
		Link link1 = new Link("首钢集团", "首钢股份", 1);
		link1.setItemStyle(new ItemStyle().normal(new Normal().borderWidth(1).color("red")));
		
        force.links(link1,
                new Link("首钢股份", "京唐公司", 1),
                new Link("首钢股份", "迁钢公司", 1),
                new Link("首钢股份", "顺义公司", 1),
                new Link("首钢股东1", "首钢集团", 1),
                new Link("首钢股东2", "首钢集团", 1)
        );

        option.series(force);
        return option;
	}

}
