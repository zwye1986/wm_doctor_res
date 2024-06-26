
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect.js'/>"></script>
<script type="text/javascript">
	var datas =[];//所有的角色
	<c:if test="${!empty applicationScope.sysCfgMap['res_doctor_role_flow']}">
	var arry = {"id":"${applicationScope.sysCfgMap['res_doctor_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_doctor_role_flow']]?sysRoleMap[sysCfgMap['res_doctor_role_flow']].roleName:'培训学员'}"};
	datas.push(arry);
	</c:if>
	<c:if test="${!empty applicationScope.sysCfgMap['res_teacher_role_flow']}">
	var arry = {"id":"${applicationScope.sysCfgMap['res_teacher_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_teacher_role_flow']]?sysRoleMap[sysCfgMap['res_teacher_role_flow']].roleName:'带教老师'}"};
	datas.push(arry);
	</c:if>
	<c:if test="${!empty applicationScope.sysCfgMap['res_secretary_role_flow']}">
	var arry = {"id":"${applicationScope.sysCfgMap['res_secretary_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_secretary_role_flow']]?sysRoleMap[sysCfgMap['res_secretary_role_flow']].roleName:'科秘'}"};
	datas.push(arry);
	</c:if>
	<c:if test="${!empty applicationScope.sysCfgMap['res_head_role_flow']}">
	var arry = {"id":"${applicationScope.sysCfgMap['res_head_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_head_role_flow']]?sysRoleMap[sysCfgMap['res_head_role_flow']].roleName:'科主任'}"};
	datas.push(arry);
	</c:if>
	<c:if test="${!empty applicationScope.sysCfgMap['res_manager_role_flow']}">
	var arry = {"id":"${applicationScope.sysCfgMap['res_manager_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_manager_role_flow']]?sysRoleMap[sysCfgMap['res_manager_role_flow']].roleName:'基地主任'}"};
	datas.push(arry);
	</c:if>
	<c:if test="${!empty applicationScope.sysCfgMap['res_tutor_role_flow']}">
	var arry = {"id":"${applicationScope.sysCfgMap['res_tutor_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_tutor_role_flow']]?sysRoleMap[sysCfgMap['res_tutor_role_flow']].roleName:'导师'}"};
	datas.push(arry);
	</c:if>
	<c:if test="${!empty applicationScope.sysCfgMap['res_responsible_teacher_role_flow']}">
	var arry = {"id":"${applicationScope.sysCfgMap['res_responsible_teacher_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_responsible_teacher_role_flow']]?sysRoleMap[sysCfgMap['res_responsible_teacher_role_flow']].roleName:'责任导师'}"};
	datas.push(arry);
	</c:if>
	<c:if test="${!empty applicationScope.sysCfgMap['res_disciple_role_flow']}">
	var arry = {"id":"${applicationScope.sysCfgMap['res_disciple_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_disciple_role_flow']]?sysRoleMap[sysCfgMap['res_disciple_role_flow']].roleName:'师承老师'}"};
	datas.push(arry);
	</c:if>
	<c:if test="${!empty applicationScope.sysCfgMap['res_professionalBase_admin_role_flow']}">
	var arry = {"id":"${applicationScope.sysCfgMap['res_professionalBase_admin_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_professionalBase_admin_role_flow']]?sysRoleMap[sysCfgMap['res_professionalBase_admin_role_flow']].roleName:'专业基地管理员'}"};
	datas.push(arry);
	</c:if>
	<c:if test="${!empty applicationScope.sysCfgMap['res_admin_role_flow'] and roleFlag eq 'global'}">
	var arry = {"id":"${applicationScope.sysCfgMap['res_admin_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_admin_role_flow']]?sysRoleMap[sysCfgMap['res_admin_role_flow']].roleName:'医院管理员'}"};
	datas.push(arry);
	</c:if>
	$(function(){
		$.itemSelect("infoRoleFlows",datas,null,null,null);
	});
	$(document).ready(function(){
		var ue = UE.getEditor('ueditor', {
		    autoHeight: false,
		    imagePath: '${sysCfgMap['upload_base_url']}/',
		    imageManagerPath: '${sysCfgMap['upload_base_url']}/',
		    filePath: '${sysCfgMap['upload_base_url']}/',
		    videoPath: '${sysCfgMap['upload_base_url']}/',
		    wordImagePath: '${sysCfgMap['upload_base_url']}/',
		    snapscreenPath: '${sysCfgMap['upload_base_url']}/',
		    catcherPath: '${sysCfgMap['upload_base_url']}/',
		    scrawlPath: '${sysCfgMap['upload_base_url']}/',
		    elementPathEnabled : false,
		    autoFloatEnabled:false,
			initialFrameWidth:null,
			toolbars:[
		                ['|', 'undo', 'redo', '|','lineheight',
		                    'bold', 'italic', 'underline', 'fontborder', 'fontfamily', 'fontsize', '|',
		                    'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
		                    'link', 'insertimage', 'attachment' ,  'preview', 'wordimage']
		            ]
		});//实例化编辑器
		init();
	});
	
	function init(){
		$(".operDiv").on("mouseenter mouseleave",function(){
			$(this).find("span").toggle();
		});
	}
	
	function saveNotice(){
		if($("#title").val().length==0){
			jboxTip("公告标题不能为空!");
			return;
		}
		if($("#title").val().length>50){
			jboxTip("公告标题不能大于50个字符或汉字!");
			return;
		}
		if($("#noticeType").val().length==0){
			jboxTip("通知类型不能为空!");
			return;
		}
		var title = $.trim($("#title").val());
		var infoFlow = $("#infoFlow").val();
		var content = UE.getEditor('ueditor').getContent();
		var columnId = $("#columnId").val();
		var columnName = '系统公告';
		var infoRoleNames = $("#infoRoleFlows").val();
		var infoRoleFlows="";
		$("[name='itemId']:checked").each(function(i){
			if(i!=0)
				infoRoleFlows+=","+$(this).val();
			else
				infoRoleFlows=$(this).val();
		});
		var v=$("#noticeType").val();
		if(v=="SpecifiedNotice"&&infoRoleFlows=="")
		{
			jboxTip("请选择通知对象!");
			return;
		}
		if($("#noticeType option:selected").attr('class') == 'LM'){
            columnId = v;
            columnName = $("#noticeType option:selected").text();
		}
		var sessionNumber="";
		if(infoRoleFlows.length>0 && infoRoleFlows.indexOf("${applicationScope.sysCfgMap['res_doctor_role_flow'] }")>-1){
			sessionNumber=$("#sessionNumber").val();
		}
		var requestData ={
				"infoFlow":infoFlow,
				"infoTitle":title,
				"infoContent":content,
				"columnId":columnId,
				"columnName":columnName,
				"infoRoleNames":infoRoleNames,
				"infoRoleFlows":infoRoleFlows,
				"sessionNumber":sessionNumber
		};
		jboxStartLoading();
		var url = "<s:url value='/res/platform/saveNotice/${roleFlag}'/>";
		jboxPost(url,requestData,function(resp){
			if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
				jboxEndLoading();
				goNoticeByPage();
			}
		},null,true);
	}
	function toPage(page) {
		if(!page){
			$("#currentPage").val(page);			
		}
		location.href="<s:url value='/res/platform/notice/${roleFlag}'/>?currentPage="+page;
	} 
	function goNoticeByPage(){
		toPage("${param.currentPage}");
	}
	function edit(infoFlow){
		var url = "<s:url value='/res/platform/findNoticeByFlow'/>?infoFlow="+infoFlow;
		jboxGet(url , null , function(resp){
			var info=resp.info;
			var infoRoles=resp.infoRoles;
			var v="SystemNotice";
			var sessionNumber="";
			if(infoRoles&&infoRoles.length>0)
			{
				var datas2 =[];//已关联的角色
				for(var i=0;i<infoRoles.length;i++)
				{
					var arry={};
					arry.id=infoRoles[i].infoRole;
					arry.text="";
					datas2.push(arry);
					$("[name='itemId'][value='"+infoRoles[i].infoRole+"']").attr("checked","checked");
					if('${applicationScope.sysCfgMap['res_doctor_role_flow']}'==infoRoles[i].infoRole){
						sessionNumber=infoRoles[i].sessionNumber
					}
				}
				var result="";
				$("#infoRoleFlows-reqHome").find(".itemCheckbox:checked+font").each(function(){
					var currName = $(this).text();
					if(!result){
						result+=currName;
					}else{
						result+=(","+currName);
					}
				});
				$("#infoRoleFlows").val(result);
				v="SpecifiedNotice";
			}
			showPeople(v);
			if(info){
				if($("#editForm").is(":hidden")){
					editFormHidden();
				}
				$("#infoFlow").val(info.infoFlow);
				$("#title").val(info.infoTitle);
				$("#sessionNumber").val(sessionNumber);
				$("[value='"+v+"']").attr("selected","selected");
                var columnId = info.columnId;
                $(".LM[value='"+columnId+"']").attr("selected","selected");
				UE.getEditor('ueditor').setContent(info.infoContent);
				$("#bodyDiv").animate({scrollTop:"0px"},500);
			}
		} , null , false);
	}
	function delNotice(infoFlow){
		jboxConfirm("确认删除？" , function(){
			var url = "<s:url value='/res/platform/delNotice'/>?infoFlow="+infoFlow;
			jboxGet(url , null , function(resp){
				goNoticeByPage();
			} , null , true);
		});
	}
	function editFormHidden(){
		$("#editForm,#operImg img").toggle();
	}
	function editFormHidden2(){
		$("#editForm2,#operImg2 img").toggle();
	}
	function showPeople(v){
		if(v=="SpecifiedNotice")
		{
			$("#people").show();
		}else{
			$("#people").hide();
			$("[name='itemId']").removeAttr("checked");
		}
	}
</script>

</head>
<body>
<div id="bodyDiv" style="height: 100%;overflow: auto;">
	<div style="margin-bottom: 20px;">

	<c:set var="isFree" value="${pdfn:isFreeGlobal()}"></c:set>
	<c:if test="${!isFree}">
		<div style="margin-top: 5px;padding-left: 15px;padding-right: 15px;">
			<table width="100%" class="basic">
				<tr>
					<th style="text-align: left;padding-left:10px;">
						新增通知
						<span id="operImg" style="float: right;cursor: pointer;" onclick="editFormHidden();">
							<img title="收缩" src="<s:url value='/css/skin/up.png'/>"/>
							<img title="展开" src="<s:url value='/css/skin/down.png'/>" style="display: none;"/>
						</span>
					</th>
				</tr>
			</table>
			<form id="editForm" method="post">
				<input type="hidden" id="infoFlow" name="infoFlow" value=""/>
				<input type="hidden" id="orgFlow" name="orgFlow"/>
				<input type="hidden" id="orgName" name="orgName"/>
				<input type="hidden" id="columnId" name="columnId" value="LM05"/>
				<div style="height: auto;margin-top: 5px;">
					<table style="width: 100%;">
						<tr>
							<td width="10%" style="text-align: right;"><span style="color: red;">*</span>标题：</td>
							<td width="65%"><input id="title" name="title" class="" style="height: 22px;width:98%;"/></td>
							<td width="7%" style="text-align: right;"><span style="color: red;">*</span>通知类型：</td>
							<td width="13%">
								<select id="noticeType" name="noticeType" style="height: 22px;width: 98%;" class="" onchange="showPeople(this.value)">
									<option></option>
										<option value="SystemNotice">系统通知</option>
										<option value="SpecifiedNotice">特定通知</option>
										<c:if test="${sysCfgMap['zy_hospital_zcfg'] eq 'Y'}">
											<option value="LM06" class="LM">政策法规</option>
											<option value="LM07" class="LM">专题报道</option>
											<option value="LM08" class="LM">学术专区</option>
											<option value="LM09" class="LM">经验分享</option>
										</c:if>
								</select>
							</td>
							<td width="5%"><input type="button" id="saveBtn" value="发&#12288;布" onclick="saveNotice();" class="search"/></td>
						</tr>
						<tr id="people" style="display: none">
							<td width="10%" style="text-align: right;"><span style="color: red;">*</span>通知对象：</td>
							<td width="65%">
								<div style="min-width:98%;width:98%;border:1px;">
									<input id="infoRoleFlows" name="infoRoleFlows" class="input" placeholder="" style="width: 100%;" type="text" readonly="readonly" />
								</div>
							</td>
							<td width="7%" style="text-align: right;">学员年级：</td>
							<td width="13%">
								<input type="text" class="ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" style="height: 22px;width:93%;" id="sessionNumber"/>
							</td>
						</tr>
					</table>
				</div>
				<script id="ueditor" name="content" type="text/plain" style="height:250px;"></script>
			</form>
		</div>
	</c:if>
		<div id="noticeList" class="index_form" style="margin-top:10px;padding-left: 15px;padding-right: 15px; ">
			<table width="100%" class="basic">
				<tr>
					<th style="padding-left: 10px;text-align: left;">教学通知
					<span id="operImg2" style="float: right;cursor: pointer;" onclick="editFormHidden2();">
							<img title="收缩" src="<s:url value='/css/skin/up.png'/>"/>
							<img title="展开" src="<s:url value='/css/skin/down.png'/>" style="display: none;"/>
						</span>
					</th>
				</tr>
				<tbody id="editForm2">
				<c:forEach items="${infos}" var="msg">
		            <tr  class="operDiv">
		            	<td style="padding-right: 10px;">
		            		<div style="float: left;">
		            			<a href="<s:url value='/res/platform/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
		            			<c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.infoTime))<=7}">
									<img src="<s:url value='/css/skin/new.png'/>"/>
							     </c:if>
		            		</div>
		            		<div style="float: right;">
		            			<span>${pdfn:transDate(msg.infoTime)}</span>
		            			<span style="display:none;">
								<c:if test="${!isFree}">
									<a href="javascript:edit('${msg.infoFlow}');" style="color: gray;">编辑</a> |
									<a href="<s:url value='/res/platform/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank" style="color: gray;">查看</a> |
									<a href="javascript:delNotice('${msg.infoFlow}');" style="color: gray;">删除</a>
								</c:if>
								<c:if test="${isFree}">
									<a href="<s:url value='/res/platform/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank" style="color: gray;">查看</a>
								</c:if>
								</span>
		            		</div>
		                </td>
		             </tr>
		         </c:forEach>
		         <c:if test="${empty infos}">
				     <tr>
				     	<td style="text-align: center;"><strong>无记录!</strong></td>
					 </tr>
				 </c:if>
				</tbody>
			</table>
		     <div class="page" style="padding-right: 40px;">
		         <input id="currentPage" type="hidden" name="currentPage" value=""/>
		         <c:set var="pageView" value="${pdfn:getPageView(infos)}" scope="request"></c:set>
			  	 <pd:pagination toPage="toPage"/>	 
		     </div>
		</div>
	</div>
</div>
</body>
</html>
