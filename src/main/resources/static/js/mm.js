
layui.define(['jquery','element','laytpl','carousel','laypage'],function(exports){
	var $ = layui.$,laytpl = layui.laytpl,element = layui.element,laypage = layui.laypage,carousel = layui.carousel;
	var _mm = {
		server_url:"http://localhost:8888",
		request : function(param){
			var _this = this;
			$.ajax({
				type   		: param.type || 'get',
				url    		: param.url    || '',
				dataType 	: param.dataType || 'json',
				data 		: param.data || '',
				success 	: function(res){
					 // 请求成功
	                if(0 === res.code){
	                    typeof param.success === 'function' && param.success(res, res.msg);
	                }
					// 请求数据错误
	                else if(-1 === res.code){
	                    typeof param.error === 'function' && param.error(res.msg);
	                }
				},
				error       : function(err){
					 typeof param.error === 'function' && param.error(err.statusText);	
				}
			});
		},
		renderHtml : function(htmlTemplate, data){
	        var template    = laytpl(htmlTemplate),
	            result      = template.render(data);
	        return result;
	    },
	   	/** 
		 * 时间戳转化为年 月 日 时 分 秒 
		 * number: 传入时间戳 
		 * format：返回格式，支持自定义，但参数必须与formateArr里保持一致 
		*/
	   	formatTime : function(number,format){
	   		var formateArr  = ['Y','M','D','h','m','s'];  
		  	var returnArr   = [];  
		  
		 	 //如果记得时间戳是毫秒级的就需要*1000 不然就错了记得转换成整型
//		  	var date = new Date(number * 1000);  
		  	var date = new Date(number); 
		  	returnArr.push(date.getFullYear());  
		  	returnArr.push(_mm.formatNumber(date.getMonth() + 1));  
		  	returnArr.push(_mm.formatNumber(date.getDate()));  
		  
		  	returnArr.push(_mm.formatNumber(date.getHours()));  
		  	returnArr.push(_mm.formatNumber(date.getMinutes()));  
		  	returnArr.push(_mm.formatNumber(date.getSeconds()));  
		  
		  	for (var i in returnArr)  
		  	{  
		    	format = format.replace(formateArr[i], returnArr[i]);  
		  	}  
		  	return format;  
	   	},
	   	//数据转化  
		formatNumber : function (n) {  
		  n = n.toString()  
		  return n[1] ? n : '0' + n  
		},
		/**
		 * 获取当前url中的某个参数
		 * @param {Object} name 参数名
		 */
		getUrlParam : function(name){
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		    var r = window.location.search.substr(1).match(reg);
		    if (r!=null) return unescape(r[2]); return null;
		}
	}
  exports('mm',_mm)
});