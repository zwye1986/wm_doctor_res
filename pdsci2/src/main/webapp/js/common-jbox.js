//////////////////// jbox相关  ////////////////////////
function jboxStartLoading(){
	if(window.parent.frames['mainIframe']!=null){
		top.$.jBox.tip("正在加载中...", 'loading', {
			id : 'jboxMainIframeLoading'
		});	
		top.$("#jboxMainIframeLoading").css('z-index','30001');
		top.$("#jboxMainIframeLoading").css('position','fixed');
		top.$("#jboxMainIframeLoading").children("#jbox-fade").css("z-index","30001");
		top.$("#jboxMainIframeLoading").children("#jbox-fade").css('position','fixed');
		top.$("#jboxMainIframeLoading").children("#jbox-temp").css("z-index","30001");
		top.$("#jboxMainIframeLoading").children("#jbox-temp").css('position','fixed');
		top.$("#jboxMainIframeLoading").children("#jbox").css("z-index","30002");
		top.$("#jboxMainIframeLoading").children("#jbox").css('position','fixed');
		jboxCenter();	
	}else{
		$.jBox.tip("正在加载中...", 'loading', {
			id : 'jboxMainIframeLoading'
		});	
		$("#jboxMainIframeLoading").css('z-index','30001');
		$("#jboxMainIframeLoading").css('position','fixed');
		$("#jboxMainIframeLoading").children("#jbox-fade").css("z-index","30001");
		$("#jboxMainIframeLoading").children("#jbox-fade").css('position','fixed');
		$("#jboxMainIframeLoading").children("#jbox-temp").css("z-index","30001");
		$("#jboxMainIframeLoading").children("#jbox-temp").css('position','fixed');
		$("#jboxMainIframeLoading").children("#jbox").css("z-index","30002");
		$("#jboxMainIframeLoading").children("#jbox").css('position','fixed');
		jboxCenter();	
	}
}
function jboxEndLoading(){
	if(window.parent.frames['mainIframe']!=null){
		var jboxMainIframeLoading = $("#jboxMainIframeLoading",window.parent.document);
		if(jboxMainIframeLoading!=null){
			jboxMainIframeLoading.fadeOut(500,function(){ 
				$(jboxMainIframeLoading).remove(); 			
			});		
		}
	}else{	
		var jboxMainIframeLoading = $("#jboxMainIframeLoading");
		if(jboxMainIframeLoading!=null){
			jboxMainIframeLoading.fadeOut(500,function(){ 
				$(jboxMainIframeLoading).remove(); 			
			});
		}
	}
}
function jboxCenter(){
	if(window.parent.frames['mainIframe']!=null){
		var jboxs = $(window.parent.document).find(".jbox");
		$.each(jboxs , function(i,jbox){ 
			if($(jbox).parent().attr('class')!='jbox-messager'){
				$(jbox).position({
					   my: "center",
					   at: "center",
					   of: $("body",window.parent.document),
					   within: $("body",window.parent.document)
					});				
			}
		});
	}else{
		var jboxs = $(window.document).find(".jbox");
		$.each(jboxs , function(i,jbox){ 
			if($(jbox).parent().attr('class')!='jbox-messager'){
				$(jbox).position({
					   my: "center",
					   at: "center",
					   of: $("body",window.document),
					   within: $("body",window.document)
					});				
			}
		});
	}
}

function jboxContentClose(){
	jboxClose();
}

function jboxClose(){
	top.$.jBox.close('jbox-iframe');
}
function jboxTip(tip){
	if(window.parent.frames['mainIframe']!=null){
		top.$.jBox.tip(tip);
		jboxCenter();
	}else{
		$.jBox.tip(tip);
		jboxCenter();
	}
}

function jboxMessager(url,title,w,h,id,showClose2){
	if(id==null){
		id = "jbox-message-iframe";
	}
	if(showClose2!=false){
		showClose2 = true;
	}
	if(window.parent.frames['mainIframe']!=null){
		top.$.jBox.messager(url , title , 0 , {id:id,width:w , height:h,showClose : showClose2 });
	}else{
		$.jBox.messager(url , title , 0 , {id:'jbox-message-iframe',width:w , height:h ,showClose : showClose2});
	}
}
function jboxCloseMessager(){
	 top.$.jBox.closeMessager();
}
function jboxInfoBasic(info,width){
	if(window.parent.frames['mainIframe']!=null){
		top.$.jBox.info(info,'提示',{icon: 'info',width:width});
		jboxCenter();
	}else{
		$.jBox.info(info,'提示',{icon: 'info',width:width});
		jboxCenter();
	}
}
function jboxInfo(info){
	jboxInfoBasic(info);
}
function jboxError(info){
	if(window.parent.frames['mainIframe']!=null){
		top.$.jBox.error(info,'发生错误啦！！！');
		jboxCenter();
	}else{
		$.jBox.error(info,'发生错误啦！！！');
		jboxCenter();
	}
}
function jboxConfirm(msg,funcOk,funcCancel){
	if(window.parent.frames['mainIframe']!=null){
		top.$.jBox.confirm(msg, "提示", function(v, h, f) {
			if (v == 'ok'){
				funcOk();
			}
			else if (v == 'cancel'){
				if(funcCancel!=null){
					funcCancel();					
				}
				return true; //close
			}
		});
		jboxCenter();
	}else{
		$.jBox.confirm(msg, "提示", function(v, h, f) {
			if (v == 'ok'){
				if(funcOk!=null){
					funcOk();
				}
			}else if (v == 'cancel'){
				if(funcCancel!=null){
					funcCancel();					
				}
				return true; //close
			}
		});
		jboxCenter();
	}
}
function jboxOpen(url,title,width,height,showClose){
	if(url.indexOf('?')>-1){
		url = url+"&time="+new Date();
	}else{
		url = url+"?time="+new Date();
	}	
	if(title==null){
		title="";
	}
	if(window.parent.frames['mainIframe']!=null){
		if(showClose==false){
			top.$.jBox.open("iframe:"+url,
					title, width, height, {
						id:'jbox-iframe',buttons : {},showClose : false
					});
			
		}else{
			top.$.jBox.open("iframe:"+url,
					title, width, height, {
						id:'jbox-iframe',buttons : {}
					});
			
		}
		jboxCenter();
	}else{
		if(showClose==false){
			$.jBox.open("iframe:"+url,
					title, width, height, {
						id:'jbox-iframe',buttons : {},showClose : false
					});
			
		}else{
			$.jBox.open("iframe:"+url,
					title, width, height, {
						id:'jbox-iframe',buttons : {}
					});			
		}
		jboxCenter();
	}
}

function jboxOpenContent(content , title , width,height,showClose){
	if(title==null){
		title="";
	}
	if(window.parent.frames['mainIframe']!=null){
		if(showClose==false){
			top.$.jBox.open(content,
					title, width, height, {
						id:'jbox-iframe',buttons : {},showClose : false
					});
			
		}else{
			top.$.jBox.open(content,
					title, width, height, {
						id:'jbox-iframe',buttons : {}
					});
			
		}
		jboxCenter();
	}else{
		if(showClose==false){
			$.jBox.open(content,
					title, width, height, {
						id:'jbox-iframe',buttons : {},showClose : false
					});
			
		}else{
			$.jBox.open(content,
					title, width, height, {
						id:'jbox-iframe',buttons : {}
					});			
		}
		jboxCenter();
	}
}
//////////////////////////// ajax 相关 ///////////////////////////
//异步
function jboxGet(geturl,getdata,funcOk,funcErr,showResp){
	$.ajax({
		type : "get",
		url : geturl,
		data : getdata,
		cache : false,
		beforeSend : function(){
			jboxStartLoading();
		},
		success : function(resp) {
			jboxEndLoading();
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxEndLoading();
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
		},
		complete : function(){
			jboxEndLoading();
		}
	});
}
//同步
function jboxGetAsync(geturl,getdata,funcOk,funcErr,showResp){
	$.ajax({
		type : "get",
		url : geturl,
		data : getdata,
		cache : false,
		async : false,
		beforeSend : function(){
			jboxStartLoading();
		},
		success : function(resp) {
			jboxEndLoading();
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxEndLoading();
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
		},
		complete : function(){
			jboxEndLoading();
		}
	});
}
function jboxScript(geturl,funcOk){
	jboxStartLoading();
	$.getScript(geturl).done(function(script, textStatus) {
		funcOk();
	})
	.fail(function(jqxhr, settings, exception) {
		 console.log( exception );
	})
	.complete(function(){
		
	});
	jboxEndLoading();
}
function jboxLoad(id,geturl,showLoading){
	$.ajax({
		type : "get",
		url : geturl,
		cache : false,
		beforeSend : function(){
			if(showLoading){
				jboxStartLoading();
			}
		},
		success : function(resp) {
			$("#"+id).html(resp);		
		},
		error : function() {
		},
		complete : function(){
			if(showLoading){
				jboxEndLoading();				
			}
		}
	});
}

function jboxLoadPost(id,geturl,showLoading){
	$.ajax({
		type : "post",
		url : geturl,
		cache : false,
		beforeSend : function(){
			if(showLoading){
				jboxStartLoading();
			}
		},
		success : function(resp) {
			$("#"+id).html(resp);		
		},
		error : function() {
		},
		complete : function(){
			if(showLoading){
				jboxEndLoading();				
			}
		}
	});
}
//异步
function jboxPost(posturl,postdata,funcOk,funcErr,showResp){
	$.ajax({
		type : "post",
		url : posturl,
		data : postdata,
		cache : false,
		beforeSend : function(){
			jboxStartLoading();
		},
		success : function(resp) {
			jboxEndLoading();
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxEndLoading();
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
		},
		complete : function(){
			jboxEndLoading();
		}
	});
}
//同步
function jboxPostAsync(posturl,postdata,funcOk,funcErr,showResp){
	$.ajax({
		type : "post",
		url : posturl,
		data : postdata,
		cache : false,
		async : false,
		beforeSend : function(){
			jboxStartLoading();
		},
		success : function(resp) {
			jboxEndLoading();
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxEndLoading();
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
		},
		complete : function(){
			jboxEndLoading();
		}
	});
}

function jboxPostNoLoad(posturl,postdata,funcOk,funcErr,showResp){
	$.ajax({
		type : "post",
		url : posturl,
		data : postdata,
		cache : false,
		beforeSend : function(){
		},
		success : function(resp) {
			jboxEndLoading();
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxEndLoading();
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
		},
		complete : function(){
		}
	});
}
function jboxPostNoLoad2(posturl,postdata,funcOk,funcErr,showResp){
	if(showResp!=false){
		showResp = true;
	}
	$.ajax({
		type : "post",
		url : posturl,
		data : postdata,
		cache : false,
		beforeSend : function(){
		},
		success : function(resp) {
			if(showResp){
				jboxTip(resp);
			}
			if(funcOk!=null){
				if(showResp){
					setTimeout(function(){
						funcOk(resp);
					},1000);
				}else{
					funcOk(resp);
				}

			}
		},
		error : function() {
			jboxEndLoading();
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();
			}
		},
		complete : function(){
		}
	});
}
function jboxSubmit(jqForm,posturl,funcOk,funcErr,showResp){
	var options = {
		url : posturl,
		type : "post", 
		cache : false,
		beforeSend : function(){
			jboxStartLoading();
		},
		success : function(resp) {
			jboxEndLoading();
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxEndLoading();
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
		},
		complete : function(){
			jboxEndLoading();
		},
		iframe : true
	};
	jqForm.ajaxSubmit(options);
}

function jboxSubmitNoLoad(jqForm,posturl,funcOk,funcErr,showResp){
	var options = {
		url : posturl,
		type : "post", 
		cache : false,
		beforeSend : function(){
		},
		success : function(resp) {
			jboxEndLoading();
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxEndLoading();
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
		},
		complete : function(){
		},
		iframe : true
	};
	jqForm.ajaxSubmit(options);
}

function jboxPostJson(posturl,postdata,funcOk,funcErr,showResp){
	$.ajax({
		type : "post",
		url : posturl,
		//dataType:"json",      
        contentType:"application/json",
		data : postdata,
		cache : false,
		beforeSend : function(){
			jboxStartLoading();
		},
		success : function(resp) {
			jboxEndLoading();
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxEndLoading();
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
		},
		complete : function(){
			jboxEndLoading();
		}
	});
}

function jboxPostJsonWait(posturl,postdata,funcOk,funcErr,showResp){
	if(showResp!=false){
		showResp = true;
	}
	$.ajax({
		type : "post",
		url : posturl,
		//dataType:"json",
		contentType:"application/json",
		data : postdata,
		cache : false,
		timeout:0,
		beforeSend : function(){
			jboxStartLoading();
		},
		success : function(resp) {
			jboxEndLoading();
			if(showResp){
				jboxTip(resp);
			}
			if(funcOk!=null){
				if(showResp){
					setTimeout(function(){
						funcOk(resp);
					},1000);
				}else{
					funcOk(resp);
				}
			}
		},
		error : function() {
			jboxEndLoading();
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();
			}
		},
		complete : function(){
			jboxEndLoading();
		}
	});
}
function jboxPostJsonLoad(id,geturl,data,showLoading){
	$.ajax({
		type : "post",
		url : geturl,
		contentType:"application/json",
		data : data,
		cache : false,
		beforeSend : function(){
			if(showLoading){
				jboxStartLoading();
			}
		},
		success : function(resp) {
			$("#"+id).html(resp);
		},
		error : function() {
		},
		complete : function(){
			jboxEndLoading();
		}
	});
}
function jboxPostLoad(id,geturl,data,showLoading){
	$.ajax({
		type : "post",
		url : geturl,
		data:data,
		cache : false,
		beforeSend : function(){
			if(showLoading){
				jboxStartLoading();
			}
		},
		success : function(resp) {
			$("#"+id).html(resp);		
		},
		error : function() {
		},
		complete : function(){
			jboxEndLoading();
		}
	});
}

/*********************/

function jboxExp(jqForm,posturl){
	jboxTip("导出中…………");
	var url = posturl;
	$("#exportDataForm").remove();
	var expForm =document.createElement("form");
	var datas = decodeURIComponent($(jqForm).serialize(),true).split("&");
	for(var i=0;i<datas.length;i++)
	{
		var input=document.createElement("input");
		$(input).attr("type","text");
		var data=datas[i].split("=");
		$(input).attr("name",data[0]);
		var val=data[1]==undefined?"":data[1];
		$(input).val(val);
		$(input).appendTo(expForm);
	}
	$(expForm).attr("display","none");
	$(expForm).attr("action",url);
	$(expForm).attr("id","exportDataForm");
	$(expForm).attr("name","exportDataForm");
	$(expForm).attr("method","post");
	$(expForm).appendTo('body').submit().remove();
}

function jboxExpLoading(jqForm,posturl){
	var timer=null;
	var checkCookie=function()
	{
		console.log(getCookie("updateStatus"));
		if(getCookie("updateStatus")=="success"){
			clearInterval(timer);//每隔一秒的判断操作停止
			timer=null;
			delCookie("updateStatus");//删除cookie
			jboxEndLoading();
		}
	};
	//清除cookie
	var delCookie =function(name) {
		setCookie(name, "", -1);
	};
	//设置cookie
	var setCookie =function (cname, cvalue, exdays) {
		var d = new Date();
		d.setTime(d.getTime() + (exdays*60*60*1000));
		var expires = "expires="+d.toUTCString();
		document.cookie = cname + "=" + cvalue + "; " + expires+";path=/";
	};
	//获取cookie
	var getCookie=function (cname) {
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for(var i=0; i<ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1);
			if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
		}
		return "";
	};
	if(getCookie("updateStatus")=="loading"){
		jboxTip("导出中,请稍等")
		return ;
	}
	setCookie("updateStatus", "loading", 1);
	jboxStartLoading();
	top.jboxTip("导出中,请稍等...");
	var url = posturl;
	$("#exportDataForm").remove();
	var expForm =document.createElement("form");
	var datas = decodeURIComponent($(jqForm).serialize(),true).split("&");
	for(var i=0;i<datas.length;i++)
	{
		var input=document.createElement("input");
		$(input).attr("type","text");
		var data=datas[i].split("=");
		$(input).attr("name",data[0]);
		var val=data[1]==undefined?"":data[1];
		$(input).val(val);
		$(input).appendTo(expForm);
	}
	$(expForm).attr("display","none");
	$(expForm).attr("action",url);
	$(expForm).attr("id","exportDataForm");
	$(expForm).attr("name","exportDataForm");
	$(expForm).attr("method","post");
	$(expForm).appendTo('body').submit().remove();
	timer=setInterval(checkCookie,1000);
}
/*function jboxButtonConfirm(msg,button1,button2,funcOk,funcCancel,width){

	jBox.confirm(msg, "提示", function (v, h, f) {
		if (v == true)
			funcOk();
		else
			funcCancel();
		return true;
	}, { id: 'hahaha', showScrolling: false, buttons: { '是': true, '否': false } });

}*/
