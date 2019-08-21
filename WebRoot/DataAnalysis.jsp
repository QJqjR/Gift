<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'DataAnalysis.jsp' starting page</title>

<meta charset="utf-8">
<link rel="icon"
	href="https://jscdn.com.cn/highcharts/images/favicon.ico">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
/* css 代码  */
</style>
<script src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
<script
	src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
<script
	src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
<script src="js/jquery-3.4.1.js"></script>
		<script src="js/jquery-3.4.1.min.js"></script>
</head>

<body>
	<div id="containerrefund" style="min-width:400px;height:400px"></div>
	<div id="containersexmoneyorder" style="min-width:400px;height:400px"></div>
	<div id="containeragemoneyorder" style="min-width:400px;height:400px"></div>
	<div id="containeragesexmoneyorder" style="min-width:400px;height:400px"></div>
	<script>
		$(document).ready(function() {
		    var cause=[],
		         num=[];
					$.ajax({
						type : "post",
						async : false,
						cache : false,
						url : "dataAnalysisrefund",
						dataType : "json",
						success : function(data) {
							//alert(typeof data);
							console.log(data);
							data = JSON.parse(data).results;
							for(var i = 0 ;i<data.length;i++){
								cause.push(data[i][0]);
								num.push(data[i][1]);
							}
							var chart = Highcharts.chart('containerrefund',
						{
							chart : {
								type : 'column'
							},
							title : {
								text : '每个原因退订量'
							},
							subtitle : {
								text : '数据来源: 礼记'
							},
							xAxis : {
								categories : cause,
								crosshair : true
							},
							yAxis : {
								min : 0,
								title : {
									text : '退订数'
								}
							},
							tooltip : {
								// head + 每个 point + footer 拼接成完整的 table
								headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
								pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
										+ '<td style="padding:0"><b>{point.y} 个</b></td></tr>',
								footerFormat : '</table>',
								shared : true,
								useHTML : true
							},
							plotOptions : {
								column : {
									borderWidth : 0
								}
							},
							series : [
									{
										name : '数量',
										data : num
									}]
						});
						}
					});
				});
	</script>
	<script>
		$(document).ready(function() {
				var ageregion=['年龄:0-18','年龄:18-30','年龄:30-45','年龄:45-65','年龄:65以上','总量'];
				    price = [],
				    age1 = [],
					age2 = [],
					age3 = [],
					age4 = [],
					age5 = [],
					total = [];
					$.ajax({
						type : "post",
						async : false,
						cache : false,
						url : "dataAnalysisagemoneyorder",
						dataType : "json",
						success : function(data) {
							//alert(typeof data);
							console.log(data);
							data = JSON.parse(data).results;
							for(var i = 0 ;i<data.length;i++){
								price.push(data[i][0]);
								age1.push(data[i][1]);
								age2.push(data[i][2]);
								age3.push(data[i][3]);
								age4.push(data[i][4]);
								age5.push(data[i][5]);
								total.push(data[i][6]);
							}
					var chart = Highcharts.chart('containeragemoneyorder',
						{
							chart : {
								type : 'column'
							},
							title : {
								text : '价格区间的订单量'
							},
							subtitle : {
								text : '数据来源: 礼记'
							},
							xAxis : {
								categories : price,
								crosshair : true
							},
							yAxis : {
								min : 0,
								title : {
									text : '订单数'
								}
							},
							tooltip : {
								// head + 每个 point + footer 拼接成完整的 table
								headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
								pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
										+ '<td style="padding:0"><b>{point.y} 个</b></td></tr>',
								footerFormat : '</table>',
								shared : true,
								useHTML : true
							},
							plotOptions : {
								column : {
									borderWidth : 0
								}
							},
							series : [
									{
										name : ageregion[0],
										data : age1
									},
									{
										name : ageregion[1],
										data : age2
									},
									{
										name : ageregion[2],
										data : age3
									},
									{
										name : ageregion[3],
										data : age4
									},
									{
										name : ageregion[4],
										data : age5
									},
									{
										name :  ageregion[5],
										data : total
									}]
						});
								
						}
					});
				});
	</script>
	<script>
		$(document).ready(function() {
				var m = [],
					f = [],
					price = [],
					total = [];
					$.ajax({
						type : "post",
						async : false,
						cache : false,
						url : "dataAnalysissexmoneyorder",
						dataType : "json",
						success : function(data) {
							//alert(typeof data);
							console.log(data);
							data = JSON.parse(data).results;
							for(var i = 0 ;i<data.length;i++){
								price.push(data[i][0]);
								m.push(data[i][1]);
								f.push(data[i][2]);
								total.push(data[i][3]);
							}
					var chart = Highcharts.chart('containersexmoneyorder',
						{
							chart : {
								type : 'column'
							},
							title : {
								text : '价格区间的订单量'
							},
							subtitle : {
								text : '数据来源: 礼记'
							},
							xAxis : {
								categories : price,
								crosshair : true
							},
							yAxis : {
								min : 0,
								title : {
									text : '订单数'
								}
							},
							tooltip : {
								// head + 每个 point + footer 拼接成完整的 table
								headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
								pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
										+ '<td style="padding:0"><b>{point.y} 个</b></td></tr>',
								footerFormat : '</table>',
								shared : true,
								useHTML : true
							},
							plotOptions : {
								column : {
									borderWidth : 0
								}
							},
							series : [
									{
										name : '男',
										data : m
									},
									{
										name : '女',
										data : f
									},
									{
										name : '总量',
										data : total
									}]
						});
								
						}
					});
				});
	</script>
	<script>
		$(document).ready(function() {
				var ageregion=['年龄:0-18(男)','年龄:0-18(女)','年龄:18-30(男)','年龄:18-30(女)','年龄:30-45(男)','年龄:30-45(女)','年龄:45-65(男)','年龄:45-65(女)','年龄:65以上(男)','年龄:65以上(女)','总量'];
				    price = [],
				    age1 = [],
					age2 = [],
					age3 = [],
					age4 = [],
					age5 = [],
					age6 = [],
					age7 = [],
					age8 = [],
					age9 = [],
					age10 = [],
					total = [];
					$.ajax({
						type : "post",
						async : false,
						cache : false,
						url : "dataAnalysisagesexmoneyorder",
						dataType : "json",
						success : function(data) {
							//alert(typeof data);
							console.log(data);
							data = JSON.parse(data).results;
							for(var i = 0 ;i<data.length;i++){
								price.push(data[i][0]);
								age1.push(data[i][1]);
								age2.push(data[i][2]);
								age3.push(data[i][3]);
								age4.push(data[i][4]);
								age5.push(data[i][5]);
								age6.push(data[i][1]);
								age7.push(data[i][2]);
								age8.push(data[i][3]);
								age9.push(data[i][4]);
								age10.push(data[i][5]);
								total.push(data[i][6]);
							}
					var chart = Highcharts.chart('containeragesexmoneyorder',
						{
							chart : {
								type : 'column'
							},
							title : {
								text : '价格区间的订单量'
							},
							subtitle : {
								text : '数据来源: 礼记'
							},
							xAxis : {
								categories : price,
								crosshair : true
							},
							yAxis : {
								min : 0,
								title : {
									text : '订单数'
								}
							},
							tooltip : {
								// head + 每个 point + footer 拼接成完整的 table
								headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
								pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
										+ '<td style="padding:0"><b>{point.y} 个</b></td></tr>',
								footerFormat : '</table>',
								shared : true,
								useHTML : true
							},
							plotOptions : {
								column : {
									borderWidth : 0
								}
							},
							series : [
									{
										name : ageregion[0],
										data : age1
									},
									{
										name : ageregion[1],
										data : age2
									},
									{
										name : ageregion[2],
										data : age3
									},
									{
										name : ageregion[3],
										data : age4
									},
									{
										name : ageregion[4],
										data : age5
									},{
										name : ageregion[5],
										data : age6
									},
									{
										name : ageregion[6],
										data : age7
									},
									{
										name : ageregion[7],
										data : age8
									},
									{
										name : ageregion[8],
										data : age9
									},
									{
										name : ageregion[9],
										data : age10
									},
									{
										name :  ageregion[10],
										data : total
									}]
						});
								
						}
					});
				});
	</script>
</body>
</html>
