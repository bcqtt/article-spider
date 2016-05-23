/**
 * author:laizhiwen
 */
$(document).ready(function(){
	$('#reviews-table').bootstrapTable({
		url: 'reviews/reviewsList',
	    columns: [{
	    	checkbox: 'true'
	    },{
	    	field: 'id',
	    	title: "id",
	    	visible: false
	    },{
	        field: 'review',
	        title: '评论'
	    }, {
	        field: 'reviewer',
	        title: '评论人'
	    }, {
	        field: 'date',
	        title: '日期',
	        width: 100,
	        formatter: function(value, row, index){
	        	var date = value.substring(0,10);
	        	return date;
	        }
	    }, {
	        field: 'starts',
	        title: '得分'
	    }, {
	        field: 'helpfulCount',
	        title: '有用次数',
	        visible: false
	    }],
	    pagination: true,
	    //pageNumber: 1,
	    //pageSize: 3,
	    //showPaginationSwitch:true,
		pageList: [10, 20, 50, 100]
		
	});
	
	$("#searchReviewBtn").on("click",function(){
		var searchText = $("#searchText").val();
		var parameter = {
			url: "reviews/reviewsList?searchText=" + searchText,
		};
		$('#reviews-table').bootstrapTable('refresh', parameter);
	});
	
	/*
	$('#datetimepicker').datetimepicker({
		format: 'yyyy-mm-dd',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
	$('#datetimepicker').datetimepicker('setStartDate', '2012-01-01');
	$("#searchText").popover({
		delay: { show: 3000, hide: 200 }
	});
	*/
});