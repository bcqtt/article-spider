/**
 * author:laizhiwen
 */
(function($) {
	var version =  "1.0";
	var navigation =  "<ul id=\"home\" class=\"breadcrumb\">"
					+	"<li>"
					+		"<i class=\"icon-home\"></i>"
					+		"<a href=\"index.jsp\">主页</a>" 
					+		"<i class=\"icon-angle-right\"></i>"
					+	"</li>"
					+   "<li><a href=\"{href}\">{nav}</a></li>"
					+"</ul>";
	$.initContent = function(requestUrl,nav){
		jQuery.ajax({
			type:'GET',
			url:requestUrl,
			dataType:'html',
			timeout: 50000,
			cache: false,
			error: function(xhr,textStatus,errorThrown ){
				alert('加载页面失败: ' + requestUrl + "\nHttp 状态码: " + xhr.status + " " + xhr.statusText);
			}, 
			success: function( data, textStatus, jqXHR){
				if(nav!="主页"){
					var _navigation = navigation.replace("{nav}", nav);
				}
				$("#content").html(_navigation + data);
			}
		});
	}
	
	/**
	 * data:{formid:"#formid",url:"url",tableid:"#tableid",refhUrl:"refhUrl"}
	 * 注意：如果操作完之后需要刷新列表，则加入参数tableid&&refUrl(必须同时配置)
	 */
	$.formSubmit = function(options) {
		$(options.formid).ajaxSubmit({
			type: 'post',
			dataType: 'json',
			url: options.url,
			success: function(responseText) {
				if(options.tableid != undefined){
					$(options.tableid).bootstrapTable('refresh');
				}
				noty(responseText);
			}
		});
	}
})(jQuery);