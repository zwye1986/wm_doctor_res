<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<script>
        function changeWenda() {
            var url = "<s:url value='/jsres/consult/main'/>";
            window.open(url,"_blank");
        }
	</script>
	<style>
		@charset "utf-8";
		/* cus_ser */
		#cus_ser{z-index:9999;width:32px;height:auto;position:fixed;right:0px;color:#FFF;top:200px;overflow:hidden;}
		*html #cus_ser{position:absolute;top:expression(eval(document.documentElement.scrollTop));margin:200px 0 0 0;}
		.cus_ser_{width:165px;height:auto;background:#1595d4;border-bottom-left-radius:4px; border-top-left-radius:4px;overflow:hidden;}
		#cus_ser .title{width:32px;height:138px;float:left;background:url(<s:url value='/css/skin/${skinPath}/images/zxzx.jpg'/>) no-repeat 2px 15px;cursor:pointer;}
		#cus_ser ul{padding:10px 0px 0px 2px;width:130px;float:left;}
		#cus_ser ul li#zqq{height:30px;line-height:30px;display:block;font-size:14px;padding-left:24px;background:url(<s:url value='/css/skin/${skinPath}/images/zqq.png'/>) no-repeat 2px 7px;}
		#cus_ser ul li#zphone{height:30px;line-height:30px;display:block;font-size:14px;padding-left:24px;background:url(<s:url value='/css/skin/${skinPath}/images/zphone.png'/>) no-repeat 2px 8px;}
		#cus_ser ul li#wenda{height:30px;line-height:30px;display:block;font-size:14px;padding-left:24px;background:url(<s:url value='/css/skin/${skinPath}/images/wenda.png'/>) no-repeat 2px 8px;}
		#cus_ser ul li#wb{height:30px;line-height:30px;display:block;font-size:12px;padding-left: 12px;}
		#cus_ser ul li a{color:#FFF;}
		#cus_ser ul li a:hover{text-decoration:underline;}
		#cus_ser .close{border:0;margin:0;padding:0;display:inline-block;width:16px;height:16px;overflow:hidden;cursor:pointer;margin-right:4px;float:right;margin:0;padding:0;list-style-type:none;}
		#cus_ser ul, ol{list-style: none;margin:0 0 9px 0;}
		#cus_ser span img{border:0;margin:0;padding:0;background: #ffffff;}
	</style>
	
	<div id="cus_ser">
		<div class="cus_ser_">
			<div class="title"></div>
			<ul>
				<c:choose>

					<c:when test="${empty sessionScope.currUser}">
						<c:if test="${not empty applicationScope.sysCfgMap['online_service_qq3']}">
							<%-- <li id="zqq"><a href="#">${applicationScope.sysCfgMap['online_service_qq1'] }</a></li> --%>
							<c:forEach items="${pdfn:split(applicationScope.sysCfgMap['online_service_qq3'], ',')}"
									   var="qq3">
								<li id="zqq"><a href="#">${qq3}</a></li>
							</c:forEach>
						</c:if>

						<c:if test="${not empty applicationScope.sysCfgMap['online_service_phone3']}">
							<c:forEach items="${pdfn:split(applicationScope.sysCfgMap['online_service_phone3'], ',')}"
									   var="phone3">
								<li id="zphone"><a href="#">${phone3}</a></li>
							</c:forEach>
						</c:if>
					</c:when>

					<c:when test="${sessionScope.currRoleObj.roleFlow eq applicationScope.sysCfgMap['res_global_role_flow']  or sessionScope.currRoleObj.roleFlow eq applicationScope.sysCfgMap['res_admin_role_flow'] }">
						<c:if test="${not empty applicationScope.sysCfgMap['online_service_qq1']}">
							<%-- <li id="zqq"><a href="#">${applicationScope.sysCfgMap['online_service_qq1'] }</a></li> --%>
							<c:forEach items="${pdfn:split(applicationScope.sysCfgMap['online_service_qq1'], ',')}"
									   var="qq1">
								<li id="zqq"><a href="#">${qq1}</a></li>
							</c:forEach>
						</c:if>

						<c:if test="${not empty applicationScope.sysCfgMap['online_service_phone1']}">
							<c:forEach items="${pdfn:split(applicationScope.sysCfgMap['online_service_phone1'], ',')}"
									   var="phone1">
								<li id="zphone"><a href="#">${phone1}</a></li>
							</c:forEach>
						</c:if>
					</c:when>
					<c:otherwise>
						<c:if test="${not empty applicationScope.sysCfgMap['online_service_qq2']}">
							<c:forEach items="${pdfn:split(applicationScope.sysCfgMap['online_service_qq2'], ',')}"
									   var="qq2">
								<li id="zqq"><a href="#">${qq2}</a></li>
							</c:forEach>
						</c:if>
						<c:if test="${not empty applicationScope.sysCfgMap['online_service_phone2']}">
							<c:forEach items="${pdfn:split(applicationScope.sysCfgMap['online_service_phone2'], ',')}"
									   var="phone2">
								<li id="zphone"><a href="#">${phone2}</a></li>
							</c:forEach>
						</c:if>
					</c:otherwise>
				</c:choose>
				<li id="wenda"><a onclick="changeWenda()">问答专区</a></li>
			</ul>
		</div>
		<div class="close" style="width: 40px; height: 20px; color: rgb(0, 0, 0); float: right; margin-right: 0px; font-size: 12px; font-family: 微软雅黑; text-align: right; line-height: 20px; background-color: rgb(255, 255, 255);">关闭</div>
		<%-- <span class="close"><img src="<s:url value='/css/skin/${skinPath}/images/icon_close.png'/>"/></span> --%>
	</div>
	
	<script type="text/javascript">
	function openCusSer(){
		if($("#cus_ser").css("display")=="none"){
			$("#cus_ser").css({
		        display: ''
		    });
		    $("#cus_ser").stop();
			$("#cus_ser").animate({width: 165},400,'swing');
		}else{
		    $("#cus_ser").stop();
		    $("#cus_ser").animate({width:32},400, 'swing');
		    $("#cus_ser").css({
		        display: 'none'
		    });
		}
	}
	
	$("#cus_ser .close").click(function(){
	    $("#cus_ser").css({
	        display: 'none'
	    });
	});
	
	$("#cus_ser").mouseover(function(){
	    $("#cus_ser").stop();
	    $("#cus_ser").animate({width: 165},400,'swing');
	});
	
	$("#cus_ser").mouseout(function(){
	    $("#cus_ser").stop();
	    $("#cus_ser").animate({width:32},400, 'swing');
	});
	</script>
		