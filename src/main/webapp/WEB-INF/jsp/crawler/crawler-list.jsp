<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="ui/bootstrap/js/custom.js"></script>
<script src="ui/js/crawler.js"></script>

<div class="row-fluid">
	<div class="box black span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon white align-justify"></i><span class="break"></span>操作面板
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i class="halflings-icon white chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<!-- 内容 -->
			<a id="addCrawlerBtn" href="#crawlerModal" class="quick-button-small span1" data-toggle="modal"> <i class="icon-plus"></i>
				<p>新增</p>
			</a> 
			<a class="quick-button-small span1"> <i class="icon-pencil"></i>
				<p>编辑</p>
			</a> 
			<a class="quick-button-small span1"> <i class="icon-play"></i>
				<p>开启</p>
			</a> 
			<a class="quick-button-small span1"> <i class="icon-stop"></i>
				<p>关闭</p>
			</a> 
			<a class="quick-button-small span1"> <i class="icon-trash"></i>
				<p>删除</p>
			</a> 
			<div class="clearfix"></div>
		</div>
	</div>
</div>

<table id="crawler-table"></table>

<div id="crawlerModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="crawlerModalLabel">编辑信息</h3>
  </div>
	<div class="modal-body">
		<form id="crawlerForm" class="form-horizontal" method="post" action="#">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="focusedInput">名称</label>
					<div class="controls">
						<input class="input-xlarge focused" id="crawlerName" name="name" type="text" value="">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">初始URL</label>
					<div class="controls">
						<input class="input-xlarge focused" id="startUrl" name="startUrl" type="text" value="">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">开始URL模板</label>
					<div class="controls">
						<input class="input-xlarge focused" id="startUrlTemplate" name="startUrlTemplate" type="text" value="">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">目标URL模板</label>
					<div class="controls">
						<input class="input-xlarge focused" id="targetUrlTemplate" name="targetUrlTemplate" type="text" value="">
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<div class="modal-footer">
	<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	<button id="saveBtn" class="btn btn-primary">保存</button>
  </div>
</div>