<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     
    <title>下载Excel</title>
		<script src="js/jquery-3.4.1.js"></script>
		<script src="js/jquery-3.4.1.min.js"></script>
  </head>
  
  <body>
    礼品：<button id="giftdownload">导出</button>
    订单：<button id="orderdownload">导出</button>
    <script type="text/javascript">
    $(function() {
    $("#giftdownload").click(function(){					
		location.href="Gift/excelgift";
		});
	$("#orderdownload").click(function(){					
		location.href="Gift/excelorder";
		});
	});
    </script>
  </body>
</html>
