/**
 * author:laizhiwen
 */
$(document).ready(function(){
	var TABLE_DATA_URL="crawler/crawlerList";
	$('#crawler-table').bootstrapTable({
		url: TABLE_DATA_URL,
		height: '100%',
		cardView: true,
	    columns: [{
	    	checkbox: 'true'
	    },{
	    	field: 'id',
	    	title: "id",
	    	visible: false
	    },{
	        field: 'name',
	        title: '名称'
	    }, {
	        field: 'startUrl',
	        title: '初始URL'
	    }, {
	        field: 'startUrlTemplate',
	        title: '开始URL模板'
	    }, {
	        field: 'targetUrlTemplate',
	        title: '目标URL模板'
	    }],
	    pagination: true,
	    //pageNumber: 1,
	    //pageSize: 3,
	    //showPaginationSwitch:true,
		pageList: [10, 20, 50, 100]
	});
	
	$("#addCrawlerBtn").on("click",function(){
		$("#crawlerForm").attr("action","crawler/saveCrawler?opt=add");
	});
	$("#addCrawlerBtn").on("click",function(){
		$("#crawlerForm").attr("action","crawler/saveCrawler?opt=add");
	});
	
	$("#saveBtn").on("click",function(){
		var options = {
			formid: "#crawlerForm",
			url: $("#crawlerForm").attr("action"),
			tableid: "#crawler-table",
			refUrl: TABLE_DATA_URL
		};
		$.formSubmit(options);
	});
});