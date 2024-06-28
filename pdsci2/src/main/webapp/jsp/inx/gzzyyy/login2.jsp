<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>中医住院医师规范化培训管理平台</title>
	<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
	</jsp:include>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/gzzyyy/css/login.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
	<style>
		#cnzz_stat_icon_1273267552 a {color: #fff;font-size: 11px;cursor: default;}
		.menuTable{
			width:105%;
			margin-top: 70px;
		}
		.menuTable img{
			width: 100%;
		}
	</style>
</head>
<script>

	$(function(){
		var pwd = $("#placepwd");
		var password = $("#userPasswd");
		if (navigator.appName.indexOf("Microsoft Internet Explorer")>-1) {//处理ie浏览器placeholder和password的不兼容问题
			password.hide();
			pwd.show();

			pwd.focus(function(){
				pwd.hide();
				password.show().focus();
			});

			password.focusout(function(){
				if(password.val().trim() === ""){
					password.hide();
					pwd.show();
				}
			});
		}
	});

	function register(){
		window.location.href="<s:url value='/jsp/inx/jszy/register.jsp'/>";
	}
	function reloadVerifyCode()
	{
		$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
	}
	function checkForm(){
		$(".log_tips").html("");
		if($("#userCode").val()==""){
			$(".log_tips").html("用户名不能为空!");
			return false;
		}
		if($("#userPasswd").val()==""){
			$(".log_tips").html("密码不能为空!");
			return false;
		}
		if($("#verifyCode").val()==""){
			$(".log_tips").html("验证码不能为空!");
			return false;
		}

		var f=checkUser();
		if(f){
			return true;
		}else {
			return false;
		}
//	return true;
	}

	function checkUser()
	{
		var userCode = $("#userCode").val();
		var url = "<s:url value='/inx/jszy/checkUserCodeInBlack'/>";
		var data = {userCode:userCode};
		jboxPost(url,data,
				function(resp){
					if(resp != "" && resp != null && typeof(resp) != 'undefined'){
						var height=(window.screen.height)*0.3;
						var width=(window.screen.width)*0.5;
						jboxOpenContent(resp,"提示信息",width,height,true);
						return false;
					}else {
						$("#loginForm").submit();
						return true;
					}
				}, null, false);
	}

	window.onresize = function(){
		setIndexHeight();
	};

	$(function(){
		setIndexHeight();
	});

	function setIndexHeight(){
		//$("#containerDiv").css("height",document.documentElement.clientHeight);
	}
	function imgclick(obj,index,sysId,typeId)
	{
		$(".downImg").removeClass("selected");
		$(obj).addClass("selected");

		$(".downImg").each(function(i){
			out(this,i);
		});
		over(obj,index);
		loadNotices(sysId,typeId);
	}

    function imgclick1(obj,index,sysId,typeId,flagId)
    {
        $(".topImg").removeClass("selected");

        $(obj).addClass("selected");

        $(".topImg").each(function(i){
            out1(this,i);
        });
        over1(obj,index);
       /* if(index==1){
            loadNotices1(sysId,typeId);
        }*/
        loadNotices1(sysId,typeId,flagId);
    }

	function loadNotices(sysId,typeId)
	{
		jboxLoad("noticeDiv","<s:url value='/inx/gzzyyy/queryNoticeByRoleFlowAndColumnId?sysId='/>"+sysId+"&typeId="+typeId);
	}
    function loadNotices1(sysId,typeId,flagId)
    {
        jboxLoad("systemNotice","<s:url value='/inx/jszy/queryNoticeByRoleFlowAndColumnId?sysId=${GlobalConstant.RES_NOTICE_SYS_ID}&typeId='/>"+typeId+'&flagId='+flagId);
    }

	function goMore(sysId,typeId,flagId)
	{
		var url="<s:url value='/inx/gzzyyy/noticelist'/>?currentPage=1&sysId="+sysId+"&typeId="+typeId+"&flagId="+flagId;
		window.open(url);
	}


	function over(obj,i){
			var src = $(obj).attr("src");
			if (i == 0) {
				src = "<s:url value='/jsp/inx/jszy/img/zcfg.png'/>";
			} else if (i == 1) {
				src = "<s:url value='/jsp/inx/jszy/img/ztbd.png'/>";
			} else if (i == 2) {
				src = "<s:url value='/jsp/inx/jszy/img/xszq.png'/>";
			} else if (i == 3) {
				src = "<s:url value='/jsp/inx/jszy/img/jyfx.png'/>";
			} else if (i == 4) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/1.jpg'/>";
            } else if (i == 5) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/2.jpg'/>";
            }else if (i == 6) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/3.jpg'/>";
            }else if (i == 7) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/4.jpg'/>";
            }else if (i == 8) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/5.jpg'/>";
            }else if (i == 9) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/6.jpg'/>";
            }
        $(obj).attr("src", src);
	}
    function over1(obj,i){
        var src = $(obj).attr("src");
        if (i == 0) {
            src = "<s:url value='/jsp/inx/jszy/img/xtgg.png'/>";
        } else if (i == 1) {
            src = "<s:url value='/jsp/inx/jszy/img/jdgg.png'/>";
        }
        $(obj).attr("src", src);
    }
	function out(obj,i){
		if ($(obj).hasClass('selected')) {

		}else{
			var src=$(obj).attr("src");
			if(i==0){
				src="<s:url value='/jsp/inx/jszy/img/zcfg1.png'/>";
			}else if(i==1){
				src="<s:url value='/jsp/inx/jszy/img/ztbd1.png'/>";
			}else if(i==2){
				src="<s:url value='/jsp/inx/jszy/img/xszq1.png'/>";
			}else if(i==3){
				src="<s:url value='/jsp/inx/jszy/img/jyfx1.png'/>";
			}else if (i == 4) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/11.jpg'/>";
            } else if (i == 5) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/22.jpg'/>";
            }else if (i == 6) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/33.jpg'/>";
            }else if (i == 7) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/44.jpg'/>";
            }else if (i == 8) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/55.jpg'/>";
            }else if (i == 9) {
                src = "<s:url value='/jsp/inx/gzzyyy/img/66.jpg'/>";
            }
			$(obj).attr("src",src);
		}
	}
    function out1(obj,i){
        if ($(obj).hasClass('selected')) {

        }else{
            var src=$(obj).attr("src");
            if(i==0){
                src="<s:url value='/jsp/inx/jszy/img/xtgg1.png'/>";
            }else if(i==1){
                src="<s:url value='/jsp/inx/jszy/img/jdgg1.png'/>";
            }
            $(obj).attr("src",src);
        }
    }
	$(document).ready(function(){
		$(".downImg:eq(0)").click();
        jboxLoad("systemNotice","<s:url value='/inx/gzzyyy/queryNoticeByRoleFlowAndColumnId?sysId=${GlobalConstant.RES_NOTICE_SYS_ID}&typeId=LM05&flagId=Y'/>");
	});

</script>
<body>
<div style="overflow:auto;"id="indexBody">
	<div class="bd_bg">
		<div class="bg">
			<div class="bodyDiv">
				<div id="containerDiv" class="containerDiv">
					<div class="head">
						<img class="banner_logo" src="<s:url value='/jsp/inx/gzzyyy/img/logo.png'/>"/>
					</div>
					<div class="mid">
						<div class="mid_left">
							<div class="login_form">
								<span class="login_title"><img src="<s:url value='/jsp/inx/jszy/img/yhdl.png'/>"/></span>
								<table class="menuTable">
									<tr>
										<td><a href="http://121.8.227.41:8080/pdsci/index.jsp" target="_blank"><img src="<s:url value='/jsp/inx/gzzyyy/img/11.jpg'/>" onMouseOver="over(this,4)" onMouseOut="out(this,4)" style="cursor: pointer"/></a></td>
										<td><a href="http://121.8.227.41:8089/" target="_blank"><img src="<s:url value='/jsp/inx/gzzyyy/img/22.jpg'/>" onMouseOver="over(this,5)" onMouseOut="out(this,5)" style="cursor: pointer"/></a></td>
									</tr>
									<tr>
										<td><a href="http://121.8.227.41:8088/login/loginzhongyi.aspx" target="_blank"><img src="<s:url value='/jsp/inx/gzzyyy/img/33.jpg'/>" onMouseOver="over(this,6)" onMouseOut="out(this,6)" style="cursor: pointer"/></a></td>
										<td><a href="http://121.8.227.41:6060/pdsci/index.jsp" target="_blank"><img src="<s:url value='/jsp/inx/gzzyyy/img/44.jpg'/>" onMouseOver="over(this,7)" onMouseOut="out(this,7)" style="cursor: pointer"/></a></td>
									</tr>
									<tr>
										<td><a href="http://121.8.227.41:8090/pdsci/" target="_blank"><img src="<s:url value='/jsp/inx/gzzyyy/img/55.jpg'/>" onMouseOver="over(this,8)" onMouseOut="out(this,8)" style="cursor: pointer"/></a></td>
										<td><a ><img src="<s:url value='/jsp/inx/gzzyyy/img/66.jpg'/>" onMouseOver="over(this,9)" onMouseOut="out(this,9)" style="cursor: pointer"/></a></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="mid_right">
                            <div class="systemNotice">
                                <div style="margin-left: 10px;">
                                    <img class="topImg" src="<s:url value='/jsp/inx/gzzyyy/img/jdgg.png'/>"/>
                                </div>
                            </div>
                            <div id="systemNotice">

						    </div>
						</div>
					</div>
					<div class="down">
						<div class="down_left">
							<div class="downImgDiv">
								<img class="downImg" style="cursor: pointer;" onclick="imgclick(this,'0','${GlobalConstant.RES_NOTICE_SYS_ID}','LM06');"onMouseOver="over(this,0)" onMouseOut="out(this,0)" src="<s:url value='/jsp/inx/jszy/img/zcfg1.png'/>"/>
							</div>
							<div class="downImgDiv">
								<img class="downImg" style="cursor: pointer;" onclick="imgclick(this,'1','${GlobalConstant.RES_NOTICE_SYS_ID}','LM07');"onMouseOver="over(this,1)" onMouseOut="out(this,1)" src="<s:url value='/jsp/inx/jszy/img/ztbd1.png'/>"/>
							</div>
							<div class="downImgDiv">
								<img class="downImg" style="cursor: pointer;" onclick="imgclick(this,'2','${GlobalConstant.RES_NOTICE_SYS_ID}','LM08');"onMouseOver="over(this,2)" onMouseOut="out(this,2)" src="<s:url value='/jsp/inx/jszy/img/xszq1.png'/>"/>
							</div>
							<div class="downImgDiv">
								<img class="downImg" style="cursor: pointer;" onclick="imgclick(this,'3','${GlobalConstant.RES_NOTICE_SYS_ID}','LM09');"onMouseOver="over(this,3)" onMouseOut="out(this,3)" src="<s:url value='/jsp/inx/jszy/img/jyfx1.png'/>"/>
							</div>
						</div>
						<div class="down_mid">
							<div id="noticeDiv">
							</div>
						</div>
						<div class="down_right">
							<div class="appCode">
								<img class="imgCode" src="<s:url value='/jsp/inx/gzzyyy/img/zpapp.png'/>"/>
							</div>
							<div class="weixin">
								<span class="codeSpan">关注微信订阅号</span>
								<img class="imgCode imgCode2" src="<s:url value='/jsp/inx/gzzyyy/img/dyh.png'/>"/>
							</div>
							<div class="weixin2">
								<span class="codeSpan">关注微信服务号</span>
								<img class="imgCode imgCode2" src="<s:url value='/jsp/inx/gzzyyy/img/fwh.png'/>"/>
							</div>
						</div>
					</div>
					<div class="login_footer_box" style="height:80px;color:#fff;">
						<%--主管单位：江苏省中医药局 | --%>
						技术支持：<a class="login_footer_a db" href="http://www.njpdxx.com/" target="_blank">南京品德网络信息技术有限公司</a><br />
						<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1273267552'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s19.cnzz.com/z_stat.php%3Fid%3D1273267552%26online%3D1%26show%3Dline' type='text/javascript'%3E%3C/script%3E"));</script>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
	<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
</c:if>
</body>
</html>
