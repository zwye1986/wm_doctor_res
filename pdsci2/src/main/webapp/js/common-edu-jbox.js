function jboxTip(tip){
	var d = dialog({
		padding:20,
		content:tip
	});
	d.show();
	setTimeout(function(){
		d.close().remove();
	},2000);
}

/*edu原型需要  后期可删除*/
function jboxTipExt(tip){
	if(window.parent.frames['mainIframe']!=null){
		var d = top.dialog({
			id:"artTip",
			padding:5,
			content:tip,
		    backdropOpacity: 0.1
		});
		d.show();
		setTimeout(function(){
			d.close().remove();
		},2000);
	}else{
		var d = dialog({
			padding:5,
			content:tip
		});
		d.show();
		setTimeout(function(){
			d.close().remove();
		},2000);
	}
	jboxCenter();
}


function jboxConfirmBasic(msg,funcOk,funcCancel,width){
	  dialog({
		fixed: true,
		width:width,
	    title: '提示',
	    cancelValue:'关闭',
	    content: msg,
	    backdropOpacity:0.1,
	    button:[
	         {
	        	 value: '确定',
	             callback:funcOk,
	             autofocus: true
	         }, 
	         {
	        	 value: '取消',
	             callback:funcCancel
	         }
	    ]
	}).showModal();
}

function jboxConfirm(msg,funcOk,funcCancel){
	jboxConfirmBasic(msg,funcOk,funcCancel, 300);
}
function jboxInfo(info){
	var d = dialog({
		fixed: true,
		width:300,
	    title: '提示',
	    cancelValue:'关闭',
	    content: info,
	    button:[
	         {
	        	 value: '确定',
	             callback:function(){
	            	 d.close().remove();
	             },
	             autofocus: true
	         }
	    ]
	});
	d.show();
}

function jboxOpen(url,title,width,height,showClose){
	top.dialog({
		id:'openDialog',
		fixed: true,
		padding: 5,
		title: title,
		url: url,
		width:width,
		height:height,
		cancelDisplay: showClose,
	    cancelValue: '',
	    backdropOpacity:0.1
	}).showModal();
	if(showClose==false){
		$(".ui-dialog-close",window.top.window.document).remove();
	}
}

function jboxOpenContent(content , title , width,height,showClose){
	if(showClose!=false){
		showClose = true;
	}
	top.dialog({
		id:'openDialog',
		fixed: true,
		padding: 10,
		title: title,
		content: content,
		width:width,
		height:height,
		overflow:'auto',
		cancelDisplay: showClose,
	    cancelValue: '',
	    backdropOpacity:0.1
	}).showModal();
	$(".ui-dialog-content").css("overflow","auto");
}


function dialogClose(dialogId){
	var myDialog = dialog.get(dialogId);
	if(myDialog!=null&&myDialog.open){
		myDialog.close().remove();
	}
}
function jboxClose(){
	var openDialog = top.dialog.get('openDialog');
	if(openDialog!=null&&openDialog.open){
		openDialog.close().remove();
	}
}
function jboxDelayClose(){
	var openDialog = top.dialog.get('openDialog');
	if(openDialog!=null&&openDialog.open){
		setTimeout(function(){
			openDialog.close().remove();
		},2000);
	}
}
function jboxBubble(targetId,content,direction,quickClose){
	dialog({
		id:'bubbleDialog',
		padding:11,
	    content: content,
	    align: direction,//方向
	    quickClose: quickClose// 点击空白处快速关闭
	}).show(document.getElementById(targetId));
}

function jboxButtonConfirm(msg,button1,button2,funcOk,funcCancel,width){
    dialog({
        fixed: true,
        width:width,
        title: '提示',
        cancelValue:'关闭',
        content: msg,
        backdropOpacity:0.1,
        button:[
            {
                value: button1,
                callback:funcOk,
                autofocus: true
            },
            {
                value: button2,
                callback:funcCancel
            }
        ]
    }).showModal();
}

function jboxBubbleClose(){
	dialogClose('bubbleDialog');
}
function jboxStartLoading(){
	return dialog({
		id:'loadingDialog',
		width: 40,
		height: 40,
		backdropOpacity:0.1
	}).showModal(); 
}
function jboxEndLoading(){
	dialogClose('loadingDialog');
}
////////////////////////////ajax 相关 ///////////////////////////
function jboxGet(geturl,getdata,funcOk,funcErr,showResp){
	$.ajax({
		type : "get",
		url : geturl,
		data : getdata,
		cache : false,
		success : function(resp) {
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
		}
	});
}
function jboxLoad(id,geturl,showLoading){
	var modalDialog;
	$.ajax({
		type : "get",
		url : geturl,
		cache : false,
		beforeSend : function(){
			if(showLoading){
				modalDialog = jboxStartLoading();
			}
		},
		success : function(resp) {
			$("#"+id).html(resp);
			if(showLoading) {
				if (modalDialog && modalDialog.open) {
					modalDialog.close().remove();
				}
			}
		},
		error : function() {
			if(showLoading) {
				if (modalDialog && modalDialog.open) {
					modalDialog.close().remove();
				}
			}
		},
		complete : function(){
			if(showLoading){
				if(modalDialog && modalDialog.open) {
					modalDialog.close().remove();
				}
				// jboxEndLoading();
			}
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
function jboxPost(posturl,postdata,funcOk,funcErr,showResp){
	$.ajax({
		type : "post",
		url : posturl,
		data : postdata,
		cache : false,
		success : function(resp) {
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
		}
	});
}
function jboxPostAsync(posturl,postdata,funcOk,funcErr,showResp){
	$.ajax({
		type : "post",
		url : posturl,
		data : postdata,
		cache : false,
		async : false,
		success : function(resp) {
			if(showResp==false){

			}else{
				jboxTip(resp);
			}
			if(funcOk!=null){
				funcOk(resp);
			}
		},
		error : function() {
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();
			}
		}
	});
}
function jboxSubmit(jqForm,posturl,funcOk,funcErr,showResp){
	var options = {
		url : posturl,
		type : "post", 
		cache : false,
		success : function(resp) {
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
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
		success : function(resp) {
			if(showResp==false){
				
			}else{
				jboxTip(resp);					
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxTip("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
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
function jboxMessager(url,title,width,height,id,showClose){
	if(!id){
		id = "jbox-message-iframe";
	}
	if(showClose!=false){
		showClose = true;
	}
	if(window.parent.frames['mainIframe']!=null){
		top.dialog({
			id:id,
			align: 'bottom right',
			fixed: true,
			padding: 5,
			title: title,
			content: url,
			width:width,
			height:height,
			cancelDisplay: false,
		    cancelValue: '',
		    backdropOpacity:0.1,
		    cancel: function () {
		        return true;
		    }
		}).showModal();
		if(showClose==false){
			$(".ui-dialog-close",window.top.window.document).remove();
		}
	}else{
		dialog({
			id:id,
			align: 'bottom right',
			fixed: true,
			padding: 5,
			title: title,
			content: url,
			width:width,
			height:height,
			cancelDisplay: false,
		    cancelValue: '',
		    backdropOpacity:0.1,
		    cancel: function () {
		        return true;
		    }
		}).showModal();
		if(showClose==false){
			$(".ui-dialog-close",window.document).remove();
		}
	}
}
function jboxCloseMessager(){
	if(window.parent.frames['mainIframe']!=null){
		var openDialog = top.dialog.get('jbox-message-iframe');
		if(openDialog!=null&&openDialog.open){
			openDialog.close().remove();
		}
	}else{
		var openDialog = dialog.get('jbox-message-iframe');
		if(openDialog!=null&&openDialog.open){
			openDialog.close().remove();
		}
	}
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
