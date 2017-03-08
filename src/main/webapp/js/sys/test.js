var vm = new Vue({
	el:'#do',
	methods: {
		loadChar: function (event) {
			//图表  
			var myChart = echarts.init(document.getElementById('chart'));
			myChart.clear();
			var option;
			myChart.showLoading({text: '正在努力的读取数据中...'});  
			$.get('../echarts/getRelationChart', function(r) {
				if (r.code === 0) {
					option = eval("("+r.option+")"); 
					myChart.setOption(option);
					myChart.hideLoading();
					// 图表数据
					picInfo = myChart.getDataURL();
				} else {
					alert(r.msg);
					return;
				}
			});

			// 单击节点
			myChart.on('dblclick', function(param) {
				var data = param.data;
				var links = option.series[0].links;
				var nodes = option.series[0].nodes;
				// 点击的是边
				if (data.source != null && data.target != null) { 
				// 点击的是点
				} else { 
					alert('显示【'+data.name+'】具体信息,并提供钻取按钮')
				}
				
			});

			myChart.on('forceLayoutEnd', function() {
				console.log(myChart.chart.force.getPosition());
			});
			
		},
		save: function (event) {
			if(picInfo){
				alert("保存为图片");
			}else{
				alert("请先点击生成图表后保存");
			}
		},
		goBack: function (event) {
			alert('返回');
		}
	}
});