/**
 * author:laizhiwen
 */
$(document).ready(function(){
	$('#reviews-table').bootstrapTable({
		url: 'reviews/reviewsList',
		height: 300,
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
	        title: '日期'
	    }, {
	        field: 'helpful_count',
	        title: '有用次数'
	    }],
	    pagination: true,
	    //pageNumber: 1,
	    //pageSize: 3,
	    //showPaginationSwitch:true,
		pageList: [10, 20, 50, 100]
		
	});
});