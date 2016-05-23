<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="ui/bootstrap/js/custom.js"></script>
<script src="ui/js/review.js"></script>

<div class="row-fluid">
	<div class="box blue span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i><span class="break"></span>全文搜索面板
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<!-- 内容 -->	
			<input class="input-xlarge focused" id="searchText" type="text" value="" style="margin-bottom:0;" >
			<button class="btn btn-primary" id="searchReviewBtn"><i class="halflings-icon white search"></i>搜索</button>
		</div>
	</div>
</div>

<table id="reviews-table"></table>