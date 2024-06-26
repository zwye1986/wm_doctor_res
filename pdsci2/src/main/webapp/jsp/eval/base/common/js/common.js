/**
 * 文本框根据输入内容自适应高度
 * @param                {HTMLElement}        输入框元素
 * @param                {Number}                设置光标与输入框保持的距离(默认0)
 * @param                {Number}                设置最大高度(可选)
 */
var autoTextarea = function (elem, extra, maxHeight) {
	extra = extra || 0;
	var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
		isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
		addEvent = function (type, callback) {
			elem.addEventListener ?
				elem.addEventListener(type, callback, false) :
				elem.attachEvent('on' + type, callback);
		},
		getStyle = elem.currentStyle ? function (name) {
			var val = elem.currentStyle[name];

			if (name === 'height' && val.search(/px/i) !== 1) {
				var rect = elem.getBoundingClientRect();
				return rect.bottom - rect.top -
					parseFloat(getStyle('paddingTop')) -
					parseFloat(getStyle('paddingBottom')) + 'px';
			};

			return val;
		} : function (name) {
			return getComputedStyle(elem, null)[name];
		},
		minHeight = parseFloat(getStyle('height')+30);


	elem.style.resize = 'none';

	var change = function () {
		var scrollTop, height,
			padding = 0,
			style = elem.style;

		if (elem._length === elem.value.length) return;
		elem._length = elem.value.length;

		if (!isFirefox && !isOpera) {
			padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
		};
		scrollTop = document.body.scrollTop || document.documentElement.scrollTop;

		elem.style.height = minHeight + 'px';
		if (elem.scrollHeight > minHeight) {
			if (maxHeight && elem.scrollHeight > maxHeight) {
				height = maxHeight - padding;
				style.overflowY = 'auto';
			} else {
				height = elem.scrollHeight - padding;
				style.overflowY = 'hidden';
			};
			style.height = height + extra + 'px';
			scrollTop += parseInt(style.height) - elem.currHeight;
			document.body.scrollTop = scrollTop;
			document.documentElement.scrollTop = scrollTop;
			elem.currHeight = parseInt(style.height+30);
		};
	};

	addEvent('propertychange', change);
	addEvent('input', change);
	addEvent('focus', change);
	addEvent('click', change);
	change();
};
//////////////////// jbox相关  ////////////////////////
function jboxStartLoading(){
	$.DialogByZ.Loading("/pdapp/jsp/res/eval/image/loading.png");
}
function jboxEndLoading(){
	$.DialogByZ.Close();
}
function closeDialog(){
	$.DialogByZ.Close();
}
function jboxInfo(msg)
{
	$.DialogByZ.Alert({Title: "", Content: msg});
}
function jboxPost(posturl,postdata,funcOk,funcErr,showResp){
	$.ajax({
		type : "post",
		dataType:'json',
		url : posturl,
		data : postdata,
		cache : false,
		beforeSend : function(){
			jboxStartLoading();
		},
		success : function(resp) {
			jboxEndLoading();
			if(showResp==true)
			{
				setTimeout(function(){
					jboxInfo(resp);
				},500);
			}
			if(funcOk!=null){
				funcOk(resp);				
			}				
		},
		error : function() {
			jboxEndLoading();
			jboxInfo("操作失败,请刷新页面后重试");
			if(funcErr!=null){
				funcErr();				
			}
		},
		complete : function(){
			jboxEndLoading();
		}
	});
}
