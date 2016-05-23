<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("ul li a").on("click", function(){
		var nav = $(this).text();
		//alert(nav);
		$.initContent($(this).attr("target")+"?nav="+nav,nav);
	});  
});
</script>
<!-- start: Main Menu -->
<div id="sidebar-left" class="span2">
	<div class="nav-collapse sidebar-nav">
		<ul class="nav nav-tabs nav-stacked main-menu">
			<li><a href="index.jsp"><i class="icon-bar-chart"></i><span class="hidden-tablet"> 主页</span></a></li>	
			<li><a target="article/articleList"><i class="icon-envelope"></i><span class="hidden-tablet"> 文章列表</span></a></li>
			<li><a target="reviews/reviewsView"><i class="icon-tasks"></i><span class="hidden-tablet"> 评论信息列表</span></a></li>
			<li><a target="crawler/crawlerView"><i class="icon-tasks"></i><span class="hidden-tablet"> 爬虫配置</span></a></li>
			<!-- 
			<li><a href="ui/bootstrap/ui.html"><i class="icon-eye-open"></i><span class="hidden-tablet"> UI Features</span></a></li>
			<li><a href="ui/bootstrap/widgets.html"><i class="icon-dashboard"></i><span class="hidden-tablet"> Widgets</span></a></li>
			<li>
				<a class="dropmenu" href="#"><i class="icon-folder-close-alt"></i><span class="hidden-tablet"> Dropdown</span><span class="label label-important"> 3 </span></a>
				<ul>
					<li><a class="submenu" href="ui/bootstrap/submenu.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> Sub Menu 1</span></a></li>
					<li><a class="submenu" href="ui/bootstrap/submenu2.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> Sub Menu 2</span></a></li>
					<li><a class="submenu" href="ui/bootstrap/submenu3.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> Sub Menu 3</span></a></li>
				</ul>	
			</li>
			<li><a href="ui/bootstrap/form.html"><i class="icon-edit"></i><span class="hidden-tablet"> Forms</span></a></li>
			<li><a href="ui/bootstrap/chart.html"><i class="icon-list-alt"></i><span class="hidden-tablet"> Charts</span></a></li>
			<li><a href="ui/bootstrap/typography.html"><i class="icon-font"></i><span class="hidden-tablet"> Typography</span></a></li>
			<li><a href="ui/bootstrap/gallery.html"><i class="icon-picture"></i><span class="hidden-tablet"> Gallery</span></a></li>
			<li><a href="ui/bootstrap/table.html"><i class="icon-align-justify"></i><span class="hidden-tablet"> Tables</span></a></li>
			<li><a href="ui/bootstrap/calendar.html"><i class="icon-calendar"></i><span class="hidden-tablet"> Calendar</span></a></li>
			<li><a href="ui/bootstrap/file-manager.html"><i class="icon-folder-open"></i><span class="hidden-tablet"> File Manager</span></a></li>
			<li><a href="ui/bootstrap/icon.html"><i class="icon-star"></i><span class="hidden-tablet"> Icons</span></a></li>
			<li><a href="ui/bootstrap/login.html"><i class="icon-lock"></i><span class="hidden-tablet"> Login Page</span></a></li>
			 -->
		</ul>
	</div>
</div>
<!-- end: Main Menu -->