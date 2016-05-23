/**
 * author:laizhiwen
 */
$(document).ready(function(){
	var TABLE_DATA_URL="crawler/crawlerList";
	
	$('#crawler-table').bootstrapTable({
		url: TABLE_DATA_URL,
		height: '100%',
		//cardView: true,
	    columns: [{
	    	checkbox: 'true'
	    },{
	    	field: 'id',
	    	title: "id",
	    	visible: false
	    },{
	        field: 'name',
	        title: '名称'
	    },{
	        field: 'scope',
	        title: '范围'
	    }, {
	        field: 'startUrl',
	        title: '初始URL'
	    }, {
	        field: 'startUrlTemplate',
	        title: '开始URL模板'
	    }, {
	        field: 'targetUrlTemplate',
	        title: '目标URL提取表达式'
	    }, {
	        field: 'contentGrabExpression',
	        title: '目标内容提取表达式'
	    },{
	        field: 'authorGrabExpression',
	        title: '目标内容：作者表达式'
	    },{
	        field: 'dateGrabExpression',
	        title: '目标内容：日期提取表达式'
	    },{
	        field: 'startsGrabExpression',
	        title: '目标内容：星星提取表达式'
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
	
	//表单赋值
	function setFormdata(cra){
		$("#crawlerId").val(cra.id);
	    $("#crawlerName").val(cra.name);
	    $("#crawlerScope").val(cra.scope);
	    $("#startUrl").val(cra.startUrl);
	    $("#startUrlTemplate").val(cra.startUrlTemplate);
	    $("#targetUrlTemplate").val(cra.targetUrlTemplate);
	    $("#contentGrabExpression").val(cra.contentGrabExpression);
	    $("#authorGrabExpression").val(cra.authorGrabExpression);
	    $("#dateGrabExpression").val(cra.dateGrabExpression);
	    $("#startsGrabExpression").val(cra.startsGrabExpression);
	    $("#crawlerStatus").val(cra.status);
	}
	
	$("#addCrawlerBtn").on("click",function(){
		$("#crawlerForm").resetForm();
		$("#crawlerForm").attr("action","crawler/saveCrawler?opt=add");
	});
	$("#editCrawlerBtn").on("click",function(){
		$("#crawlerForm").attr("action","crawler/saveCrawler?opt=edit");
		var select_datas = $('#crawler-table').bootstrapTable('getSelections');
		if(select_datas.length>0){
			var cra = select_datas[0];
			setFormdata(cra);
		    $("#crawlerModal").modal('toggle');
		}else{
			noty({"text":"请选择一个修改项。","layout":"top","type":"information"});
		}
	});
	$("#startCrawlerBtn").on("click",function(){
		var select_datas = $('#crawler-table').bootstrapTable('getSelections');
		if(select_datas.length>0){
			var cra = select_datas[0];
			$("#crawlerForm").attr("action","crawler/startCrawler");
			setFormdata(cra);
			var options = {
					formid: "#crawlerForm",
					url: $("#crawlerForm").attr("action"),
				};
			$.formSubmit(options);
		}else{
			noty({"text":"请选择一个启动项。","layout":"top","type":"information"});
		}
	});
	$("#stopCrawlerBtn").on("click",function(){
		var select_datas = $('#crawler-table').bootstrapTable('getSelections');
		if(select_datas.length>0){
			$.ajax({
				url: 'crawler/stopCrawler',
				data:{id: select_datas[0].id},
				type: 'post',
				async: true,
				error : function() {
					noty(data);
				},
				success : function(data) {
					noty(data);
				}
			});
		}else{
			noty({"text":"请选择一个启动项。","layout":"top","type":"information"});
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