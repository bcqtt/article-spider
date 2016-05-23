<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络爬虫 专业爬取热门文章</title>
	<meta name="description" content="Bootstrap Metro Dashboard">
	<meta name="author" content="赖志文">
	<meta name="keyword" content="Metro, Metro UI, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
	<!-- end: Meta -->
	
	<!-- start: Mobile Specific -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- end: Mobile Specific -->
	
	<!-- start: CSS -->
	<link id="bootstrap-style" href="ui/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link id="bootstrap-table-css" href="ui/bootstrap/table/bootstrap-table.css" rel="stylesheet">
	<link href="ui/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link id="base-style" href="ui/bootstrap/css/style.css" rel="stylesheet">
	<link id="base-style-responsive" href="ui/bootstrap/css/style-responsive.css" rel="stylesheet">

	<!-- end: CSS -->
	

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="ui/bootstrap/css/ie.css" rel="stylesheet">
	<![endif]-->
	
	<!--[if IE 9]>
		<link id="ie9style" href="ui/bootstrap/css/ie9.css" rel="stylesheet">
	<![endif]-->
		
	<!-- start: Favicon -->
	<link rel="shortcut icon" href="ui/bootstrap/img/favicon.ico">
	<!-- end: Favicon -->
	
	<!-- start: JavaScript-->
	<script src="ui/bootstrap/js/jquery-1.9.1.min.js"></script>
	<script src="ui/bootstrap/js/jquery.form.js"></script>
	<script src="ui/bootstrap/js/jquery-migrate-1.0.0.min.js"></script>
	<script src="ui/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
	<script src="ui/bootstrap/js/jquery.ui.touch-punch.js"></script>
	<script src="ui/bootstrap/js/modernizr.js"></script>
	<script src="ui/bootstrap/js/bootstrap.min.js"></script>
	<script src="ui/bootstrap/js/jquery.cookie.js"></script>
	<script src='ui/bootstrap/js/fullcalendar.min.js'></script>
	<script src='ui/bootstrap/js/jquery.dataTables.min.js'></script>
	<script src="ui/bootstrap/js/excanvas.js"></script>
	<script src="ui/bootstrap/js/jquery.flot.js"></script>
	<script src="ui/bootstrap/js/jquery.flot.pie.js"></script>
	<script src="ui/bootstrap/js/jquery.flot.stack.js"></script>
	<script src="ui/bootstrap/js/jquery.flot.resize.min.js"></script>
	<script src="ui/bootstrap/js/jquery.chosen.min.js"></script>
	<script src="ui/bootstrap/js/jquery.uniform.min.js"></script>
	<script src="ui/bootstrap/js/jquery.cleditor.min.js"></script>
	<script src="ui/bootstrap/js/jquery.noty.js"></script>
	<script src="ui/bootstrap/js/jquery.elfinder.min.js"></script>
	<script src="ui/bootstrap/js/jquery.raty.min.js"></script>
	<script src="ui/bootstrap/js/jquery.iphone.toggle.js"></script>
	<script src="ui/bootstrap/js/jquery.uploadify-3.1.min.js"></script>
	<script src="ui/bootstrap/js/jquery.gritter.min.js"></script>
	<script src="ui/bootstrap/js/jquery.imagesloaded.js"></script>
	<script src="ui/bootstrap/js/jquery.masonry.min.js"></script>
	<script src="ui/bootstrap/js/jquery.knob.modified.js"></script>
	<script src="ui/bootstrap/js/jquery.sparkline.min.js"></script>
	<script src="ui/bootstrap/js/counter.js"></script>
	<script src="ui/bootstrap/js/retina.js"></script>
	<script src="ui/bootstrap/table/bootstrap-table.js"></script>
	<script src="ui/bootstrap/table/bootstrap-table-locale-all.js"></script>
	
	<script src="ui/js/global.js"></script>
	
	<!-- end: JavaScript-->
	
	<script type="text/javascript">
		$(document).ready(function(){
			$.initContent("content/toToContent");
		});
	</script>

</head>
<body>
	<!-- start: Header -->
	<%@ include file="ui/admin/header.jsp" %>
	<!-- start: Header -->
	
		<div class="container-fluid-full">
		<div class="row-fluid">
				
			<!-- start: Main Menu -->
			<%@ include file="ui/admin/menu.jsp" %>
			<!-- end: Main Menu -->
			
			<!-- start: Content -->
			<div id="content" class="span10">
				<!-- 页面的主体内容 -->
			</div><!--/.fluid-container-->
			<!-- end: Content -->
		</div><!--/#content.span10-->
		</div><!--/fluid-row-->
		
	<div class="modal hide fade" id="myModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>设置窗口</h3>
		</div>
		<div class="modal-body">
			<p>这里可以配置内容......</p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">关闭</a>
			<a href="#" class="btn btn-primary">保存</a>
		</div>
	</div>
	
	<div class="clearfix"></div>
	
	<footer>

		<p>
			<span style="text-align:left;float:left">&copy; 2016 <a href="index.jsp" alt="网络爬虫练习">网络爬虫练习</a></span>
			
		</p>

	</footer>

	

</body>
</html>