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
	    }, {
	        field: 'status',
	        title: '状态',
	        visible: false
	    }],
	    pagination: true,
	    //pageNumber: 1,
	    //pageSize: 3,
	    //showPaginationSwitch:true,
		pageList: [10, 20, 50, 100],
		rowStyle: function(row,index){
			if(row.status==1){
				return {classes:'success'};
			}
			return {};
		}
	});
	
	$("#addCrawlerBtn").on("click",function(){
		$("#crawlerForm").attr("action","crawler/saveCrawler?opt=add");
	});
	$("#editCrawlerBtn").on("click",function(){
		$("#crawlerForm").attr("action","crawler/saveCrawler?opt=edit");
		var select_datas = $('#crawler-table').bootstrapTable('getSelections');
		if(select_datas.length>0){
			var cra = select_datas[0];
		    $("#crawlerId").val(cra.id);
		    $("#crawlerName").val(cra.name);
		    $("#startUrl").val(cra.startUrl);
		    $("#startUrlTemplate").val(cra.startUrlTemplate);
		    $("#targetUrlTemplate").val(cra.targetUrlTemplate);
		    $("#crawlerStatus").val(cra.status);
		}
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