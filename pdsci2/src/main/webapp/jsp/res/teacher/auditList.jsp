<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="false"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="false"/>
		<jsp:param name="jquery_placeholder" value="false"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	
	<style type="text/css">
		table.basic th,table.basic td{text-align: center;padding: 0px;}
		table.basic a{color: blue;cursor: pointer;}
		.finish{color:green;/*font-weight: bold;*/}
		.toAudit:HOVER{background-color: #eee;}
		.toAudit{cursor: pointer;}
		.toInfo:HOVER{color: orange;}
		.doctorTypeDiv {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeLabel {
			border: 0px;
			float: left;
			width: 96px;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeContent {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
	</style>

	<script type="text/javascript">
		$(function(){
			$("#yearAuditHome").slideInit({
				width:900,
				speed:500,
				outClose:true,
				haveZZ:true
			});
		});
	
		var countData = {};
		function recAuditList(doctorFlow,schDeptFlow,recTypeId,processFlow,rotationFlow,resultFlow){
			var height=(window.screen.height)*0.7;
			var width=(window.screen.width)*0.7;
			var url="<s:url value='/res/teacher/recAuditList' />?recTypeId="+recTypeId+"&roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow+"&schDeptFlow="+schDeptFlow+"&processFlow="+processFlow+"&rotationFlow="+rotationFlow+"&resultFlow="+resultFlow;
			jboxOpen(url,"审核列表", width,height,true);
// 			var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
// 			jboxMessager(iframe, "审核列表", 900,550,null,false);
		}
		
		function afterAudit(recTypeId,rotationFlow,schDeptFlow,userFlow,processFlow){
			var url="<s:url value='/res/rec/showRegistryForm'/>?currentPage=${param.currentPage}&roleFlag=${param.roleFlag}&recTypeId="+recTypeId+"&rotationFlow="+rotationFlow+"&schDeptFlow="+schDeptFlow+"&operUserFlow="+userFlow+"&processFlow="+processFlow+"&isView=false";
			jboxOpen(url,"出科小结",800,500);
		}
		
		function search(){
			$("#searchForm").submit();
		}
		
		function toPage(page) {
			$("#currentPage").val(page);			
			search();
		}
		function defaultImg(img){
			img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
		}
// 		function preTrainForm(resultFlow){
// 			var h = $('.mainright').height();
// 			jboxOpen("<s:url value='/res/doc/preTrainForm'/>?roleFlag=${GlobalConstant.RES_ROLE_SCOPE_TEACHER}&resultFlow="+resultFlow, "科室岗前培训表",700,h);
// 		}
		
		function loadYearForm(doctorFlow){
			jboxGet("<s:url value='/res/rec/speRegistry/${param.roleFlag}'/>?noHead=true&doctorFlow="+doctorFlow,null,function(resp){
				$("#yearAuditHome").html(resp).rightSlideOpen();
			},null,false);
		}
		
		function preTrainForm(recFlow,schDeptFlow, rotationFlow, userFlow, schResultFlow){
			var url = "<s:url value='/res/rec/showRegistryForm'/>"+
					"?schDeptFlow="+schDeptFlow+
					"&rotationFlow="+rotationFlow+
					"&recTypeId=${resRecTypeEnumPreTrainForm.id}&userFlow="+userFlow+
					"&roleFlag=${param.roleFlag}&openType=open"+
					"&resultFlow="+schResultFlow+
					"&recFlow="+recFlow
			jboxOpen(url, "科室岗前培训表", 700, 500);
		}
		
		function evaluation(recFlow,schDeptFlow, rotationFlow, userFlow, schResultFlow){
			var url = "<s:url value='/res/rec/showRegistryForm'/>"+
					"?schDeptFlow="+schDeptFlow+
					"&rotationFlow="+rotationFlow+
					"&recTypeId=${resRecTypeEnumAfterEvaluation.id}&operUserFlow="+userFlow+
					"&roleFlag=${param.roleFlag}&openType=open"+
					"&resultFlow="+schResultFlow+
					"&recFlow="+recFlow
			jboxOpen(url, "出科考核表", 1000, 500);
		}
		
// 		function working(recFlow,schDeptFlow, rotationFlow, userFlow, schResultFlow){
// 			var url = "<s:url value='/res/rec/showRegistryForm'/>"+
// 					"?schDeptFlow="+schDeptFlow+
// 					"&rotationFlow="+rotationFlow+
// 					"&recTypeId=${afterRecTypeEnumWorkingAttitude.id}&userFlow="+userFlow+
// 					"&roleFlag=${param.roleFlag}&openType=open"+
// 					"&resultFlow="+schResultFlow+
// 					"&recFlow="+recFlow
// 			jboxOpen(url, "思想政治和工作态度", 1000, 500);
// 		}
	function edit(flow){
		var url="<s:url value='/res/teacher/showDocAndUser'/>?flow="+flow;
		jboxOpen(url, "信息", 1000, 500);
	}


        function oneKeyAudit(){
            var title = "确认一键审核通过";
            if($("#appealDiv").length){
                title+="(包括申述)";
            }
            title+="？";

            var url = "<s:url value='/res/teacher/oneKeyAuditAll'/>"
            jboxConfirm(title,function(){
                jboxPost(url,{
                    auditType:"resRec"
                },function(resp){
                    if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
                        top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
                        location.reload(true);
                    }
                },null,false);
            },null);
        }
	</script>
</head>
<body>
<div class="mainright">
  <div class="content">
	  <form id="searchForm" action="<s:url value='/res/teacher/auditListContent'/>" method="post">
		  <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
		  <input type="hidden" name="roleFlag" value="${param.roleFlag}">

		  <div class="queryDiv">
			  <div class="inputDiv">
				  <label class="qlable">轮转时间：</label>
				  <input type="text" class="qtime" name="startDate" value="${param.startDate}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
				  ~<input type="text" class="qtime" name="endDate" value="${param.endDate}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			  </div>
			  <div class="inputDiv">
				  <label class="qlable">姓&#12288;&#12288;名：</label>
				  <input type="text"  class="qtext" name="doctorName" value="${param.doctorName}">
			  </div>
			  <div class="doctorTypeDiv">
				  <div class="doctorTypeLabel">学员类型：</div>
				  <div class="doctorTypeContent">
					  <c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
						  <c:set var="docType" value="${type.dictId},"/>
						  <label><input type="checkbox" name="datas" value="${type.dictId}" ${empty dataStr or fn:contains(dataStr, docType)?"checked":""}>${type.dictName}</label>
					  </c:forEach>
				  </div>
			  </div>
			  <div class="qcheckboxDiv" style="min-width: 300px;max-width: none;">
				  <label class="qlable">
					  <input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""} />
					  轮转中学员
				  </label>
				  <label class="qlable">
					  <input type="checkbox" name="dshStatus" value="${GlobalConstant.FLAG_Y}" ${param.dshStatus eq GlobalConstant.FLAG_Y?"checked":""} />
					  只看待审核
				  </label>
				  <input type="button" value="查&#12288;询" class="searchInput" onclick="search();" <%--style="margin: 0px 8px 0px"--%>/>
				  <input type="button" value="返&#12288;回" class="searchInput" onclick="window.history.back(-1);" <%--style="margin: 0px 8px 0px"--%>/>
                  <input type="button" class="searchInput" value="一键审核" onclick="oneKeyAudit();" />
			  </div>
		  </div>


		  <c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag or GlobalConstant.RES_ROLE_SCOPE_SECRETARY eq param.roleFlag}">
			  <font style="float: right;margin-right: 10px;"><img src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>" style="margin-top:-5px;"/>待审核</font>
			  <font style="float: right;margin-right: 10px;color:green;/*font-weight: bold;*/">已完成</font>
		  </c:if>
	  </form>

	  <c:set var="preKey" value="res_${preRecTypeEnumPreTrainForm.id}_form_flag"/>
	<div class="resultDiv">
  	<table class="xllist" width="100%">
  		<tr>
  			<th style="min-width: 100px;">姓名</th>
  			<th style="min-width: 50px;">性别</th>
  			<th style="min-width: 100px;">电话</th>
<!--   			<th style="min-width: 80px;">入院时间</th> -->
  			<th style="min-width: 150px;">轮转科室</th>
<%--   			<c:if test="${empty sysCfgMap['res_process_flag'] || sysCfgMap['res_process_flag']==GlobalConstant.FLAG_Y }"> --%>
  			<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag or GlobalConstant.RES_ROLE_SCOPE_SECRETARY eq param.roleFlag}">
  				<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[preKey]}">
		  			<th style="min-width: 80px;">岗前培训</th>
  				</c:if>
	  			<c:forEach items="${registryTypeEnumList}" var="registryType">
					<c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
					<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
			  			<th style="min-width: 80px;max-width: 80px;">${registryType.name}</th>
					</c:if>
				</c:forEach>
  			</c:if>
  			<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER ne param.roleFlag and GlobalConstant.RES_ROLE_SCOPE_SECRETARY ne param.roleFlag}">
	  			<th style="min-width: 150px;">轮转时间</th>
	<!--   			<th>登记进度</th> -->
	  			<th style="min-width: 80px;">带教老师</th>
  			</c:if>
<%--   			</c:if> --%>
<%-- 			<c:set var="workKey" value="res_${afterRecTypeEnumWorkingAttitude.id}_form_flag"/> --%>
<%-- 			<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[workKey]}"> --%>
<%-- 				<th style="min-width: 100px;">${afterRecTypeEnumWorkingAttitude.name}</th> --%>
<%-- 			</c:if> --%>
<!--   			<th style="min-width: 150px;">出科审核</th> -->
<!--   			<th style="min-width: 80px;">年度审核</th> -->
  		</tr>
  		<c:forEach items="${processList}" var="process">
  			<c:set value="${process.userFlow}${process.processFlow}" var="key"/>
  			<tr>
  				<td onclick="edit('${process.userFlow}');" title="<img src='${sysCfgMap['upload_base_url']}/${userMap[process.userFlow].userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>">
  					<a class="toInfo" style="color: #2f8cef;cursor: pointer">${doctorMap[process.userFlow].doctorName}</a>
  				</td>
  				<td>${userMap[process.userFlow].sexName}</td>
  				<td>${userMap[process.userFlow].userPhone}</td>
<%--   				<td>${doctorMap[process.userFlow].inHosDate}</td> --%>
  				<td>${process.schDeptName}</td>
<%--   				<c:if test="${empty sysCfgMap['res_process_flag'] || sysCfgMap['res_process_flag']==GlobalConstant.FLAG_Y }"> --%>
  				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag or GlobalConstant.RES_ROLE_SCOPE_SECRETARY eq param.roleFlag}">
  				<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[preKey]}">
	  				<td>
	  					<c:set value="${process.userFlow}${process.processFlow}" var="preTrainMapKey"/>
	  					<c:if test="${!empty preTrainMap[preTrainMapKey]}">
	  						<c:set var="color" value="blue"/>
	  						
	  						<c:if test="${(GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag or GlobalConstant.RES_ROLE_SCOPE_SECRETARY eq param.roleFlag)&& not empty preTrainMap[preTrainMapKey].auditStatusId}">
	  							<c:set var="color" value="black"/>
	  						</c:if>
	  						
	  						<c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq param.roleFlag and not empty preTrainMap[preTrainMapKey].headAuditStatusId}">
	  							<c:set var="color" value="black"/>
	  						</c:if>
							<a style="color: ${color};cursor: pointer;" onclick="preTrainForm('${preTrainMap[preTrainMapKey].recFlow}','${process.schDeptFlow}','${doctorMap[process.userFlow].rotationFlow}','${process.userFlow}','${process.schResultFlow}');">
								岗前培训表
							</a>
						</c:if>
	  				</td>
  				</c:if>
  				<c:set value="${resultMap[process.schResultFlow].rotationFlow}${resultMap[process.schResultFlow].standardGroupFlow}${resultMap[process.schResultFlow].standardDeptId}" var="reqKeyHead"/>
  				<c:set value="${finishMap[process.schResultFlow]}" var="finishPre"></c:set>
  				<c:forEach items="${registryTypeEnumList}" var="registryType">
					<c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
					<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
						<c:if test="${pdfn:findChineseOrWestern(userMap[process.userFlow].medicineTypeId,registryType.id)}">
							<c:set value="${key}${registryType.id}" var="countKey"/>
						<c:set value="${reqKeyHead}${registryType.id}" var="reqKey"/>
						<c:set value="${process.schResultFlow}${registryType.id}req" var="reqKey2"/>
						<td
						title="<c:if test="${GlobalConstant.FLAG_Y eq registryType.haveAppeal}">申述数：${appealMap[countKey]+0}<br/></c:if><c:if test="${GlobalConstant.FLAG_Y eq registryType.haveReq}">要求数：${finishPre[reqKey2]+0}</c:if>"
						class="toAudit" <c:if test="${!(registryType.id eq 'CampaignNoItemRegistry' && sysCfgMap['res_form_category'] eq 'shrjyy')}">
						onclick="recAuditList('${process.userFlow}','${process.schDeptFlow}','${registryType.id}','${process.processFlow}','${doctorMap[process.userFlow].rotationFlow}','${process.schResultFlow}');"</c:if>
						>
							<c:if test="${!(registryType.id eq 'CampaignNoItemRegistry' && sysCfgMap['res_form_category'] eq 'shrjyy')}"><img src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>" style="visibility: ${(waitAuditCountMap[countKey]+waitAuditAppealCountMap[countKey])>0?'visible':'hidden'};margin-left:-10px;margin-top:-5px;"/></c:if>
		  					<font <c:if test="${finishCountMap[countKey]+appealMap[countKey]>=finishPre[reqKey2]}">class="finish"</c:if>>${finishCountMap[countKey]+0}</font>
		  				</td>
						</c:if>
						<c:if test="${!(pdfn:findChineseOrWestern(userMap[process.userFlow].medicineTypeId,registryType.id))}">
							<td >--</td>
						</c:if>
					</c:if>
				</c:forEach>
  				</c:if>
  				
  				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER ne param.roleFlag and GlobalConstant.RES_ROLE_SCOPE_SECRETARY ne param.roleFlag}">
  				<td><c:out value="${process.startDate}" default="${process.schStartDate}"/>~<c:out value="${process.endDate}" default="${process.schEndDate}"/></td>
<%--   				<td>${finishPreMap[process.userFlow]+0}%</td> --%>
  				<td>${process.teacherUserName}</td>
  				</c:if>
<%--   				</c:if> --%>
<%-- 				<c:set var="workKey" value="res_${afterRecTypeEnumWorkingAttitude.id}_form_flag"/> --%>
<%-- 				<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[workKey]}"> --%>
<!-- 					<td> -->
<%-- 						<c:if test="${!empty workingMap[key].recFlow}"> --%>
<%-- 							[<a href="javascript:void(0);" onclick="working('${workingMap[key].recFlow}','${process.schDeptFlow}','${doctorMap[process.userFlow].rotationFlow}','${process.userFlow}','${process.schResultFlow}');">${afterRecTypeEnumWorkingAttitude.name}</a>] --%>
<%-- 						</c:if> --%>
<!-- 					</td> -->
<%-- 				</c:if> --%>
<!--   				<td> -->
<%--   					<c:set var="afterKey" value="res_${afterRecTypeEnumAfterEvaluation.id}_form_flag"/> --%>
<%--   					<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[afterKey]}"> --%>
<%--   						<c:set var="color" value="blue"/> --%>
  						
<%--   						<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag && !empty evaluationMap[key].auditStatusId}"> --%>
<%--   							<c:set var="color" value="black"/> --%>
<%--   						</c:if> --%>
  						
<%--   						<c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq param.roleFlag && !empty evaluationMap[key].headAuditStatusId}"> --%>
<%--   							<c:set var="color" value="black"/> --%>
<%--   						</c:if> --%>
<%--   						[<a style="color: ${color};" href="javascript:void(0);" onclick="evaluation('${evaluationMap[key].recFlow}','${process.schDeptFlow}','${doctorMap[process.userFlow].rotationFlow}','${process.userFlow}','${process.schResultFlow}');">出科考核表</a>] --%>
<%--   					</c:if> --%>
  					
<%--   					<c:set var="summaryKey" value="res_${afterRecTypeEnumAfterSummary.id}_form_flag"/> --%>
<%--   					<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[summaryKey]}"> --%>
<%--   						<c:set var="color" value="blue"/> --%>
  						
<%--   						<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag && !empty summaryMap[key].auditStatusId}"> --%>
<%--   							<c:set var="color" value="black"/> --%>
<%--   						</c:if> --%>
  						
<%--   						<c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq param.roleFlag && !empty summaryMap[key].headAuditStatusId}"> --%>
<%--   							<c:set var="color" value="black"/> --%>
<%--   						</c:if> --%>
<%-- 	  					&#12288;[<a style="color: ${color};" onclick="afterAudit('${resRecTypeEnumAfterSummary.id}','${doctorMap[process.userFlow].rotationFlow}','${process.schDeptFlow}','${process.userFlow}','${process.processFlow}');"> --%>
<!-- 		  					出科小结 -->
<!-- 	  					</a>] -->
<%--   					</c:if> --%>
<!--   				</td> -->
  					<script>
  					<c:forEach items="${registryTypeEnumList}" var="registryType">
						<c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
						<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
							<c:set value="${key}${registryType.id}" var="countKey"/>
							<c:set value="${reqKeyHead}${registryType.id}" var="reqKey"/>
							<c:set value="${process.schResultFlow}${registryType.id}req" var="reqKey2"/>
	  						countData["${countKey}finish"] = "${finishCountMap[countKey]+0}";
	  						<c:if test="${GlobalConstant.FLAG_Y eq registryType.haveReq}">
		  						countData["${countKey}req"] = "${finishPre[reqKey2]+0}";
	  						</c:if>
							<c:if test="${GlobalConstant.FLAG_Y eq registryType.haveAppeal}">
		  						countData["${countKey}appeal"] = "${appealMap[countKey]+0}";
							</c:if>
						</c:if>
					</c:forEach>
  					</script>
<!--   				<td> -->
<%--   					[<a onclick="loadYearForm('${process.userFlow}');">审核</a>] --%>
<!--   				</td> -->
  			</tr>
  		</c:forEach>
  		<c:if test="${empty processList}">
  			<tr><td colspan="20">无记录</td></tr>
  		</c:if>
  	</table>
	</div>
  	<div class="resultDiv">
		<c:set var="pageView" value="${pdfn:getPageView(processList)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>	 
	</div>
  </div>
</div>
<div id="yearAuditHome">
	
</div>
</body>
</html>