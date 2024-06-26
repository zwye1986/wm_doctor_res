
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
<script type="text/javascript">

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
		    toolbars:[
		                ['|', 'undo', 'redo', '|','lineheight',
		                    'bold', 'italic', 'underline', 'fontborder', 'fontfamily', 'fontsize', '|',
		                    'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
		                    'link', 'insertimage', 'attachment' ,  'preview', 'wordimage']
		            ]
		});//实例化编辑器
		<c:if test="${roleFlag eq 'admin'}">
		init();
		</c:if>
	});

	function init(){
		$(".operDiv").on("mouseenter mouseleave",function(){
			$(this).find("span").toggle();
		});
	}

	function saveNotice(){
		if($("#title").val().length==0){
			jboxTip("通知标题不能为空!");
			return;
		}
		if($("#title").val().length>50){
			jboxTip("通知标题不能大于50个字符或汉字!");
			return;
		}
		jboxStartLoading();
		var title = $.trim($("#title").val());
		var infoFlow = $("#infoFlow").val();
		var content = UE.getEditor('ueditor').getContent();
		var infoTargetFlow = $("#infoTargetFlow").val();
		var infoTargetName = $("#infoTargetFlow option:selected").text();
		var infoTypeId = $("#infoTypeId").val();
		var infoTypeName = $("#infoTypeId option:selected").text();
		var requestData ={
				"infoFlow":infoFlow,
				"infoTitle":title,
				"infoContent":content,
				"infoTargetFlow":infoTargetFlow,
				"infoTargetName":infoTargetName,
				"infoTypeId":infoTypeId,
				"infoTypeName":infoTypeName

		};
		var url = "<s:url value='/xjgl/notice/saveNotice/${roleFlag}'/>";
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
		location.href="<s:url value='/xjgl/notice/getList/${roleFlag}'/>?currentPage="+page;
	} 
	function goNoticeByPage(){
		toPage("${param.currentPage}");
	}
	function edit(infoFlow){
		var url = "<s:url value='/xjgl/notice/findNoticeByFlow'/>?infoFlow="+infoFlow;
		jboxGet(url , null , function(resp){
			if(resp){
				if($("#editForm").is(":hidden")){
					editFormHidden();
				}
				$("#infoFlow").val(resp.infoFlow);
				$("#title").val(resp.infoTitle);
				UE.getEditor('ueditor').setContent(resp.infoContent);
				$("[value='"+resp.infoTargetFlow+"']").attr("selected","selected");
				$("[value='"+resp.infoTypeId+"']").attr("selected","selected");
				$("#bodyDiv").animate({scrollTop:"0px"},500);
			}
		} , null , false);
	}
	function delNotice(infoFlow){
		jboxConfirm("确认删除？" , function(){
			var url = "<s:url value='/xjgl/notice/delNotice'/>?infoFlow="+infoFlow;
			jboxGet(url , null , function(resp){
				window.location.reload();
			} , null , true);
		});
	}
	function editFormHidden(){
		$("#editForm,#operImg img").toggle();
	}
</script>

</head>
<body>
<div id="bodyDiv" style="height: 100%;overflow: auto;">
	<div style="margin-bottom: 20px;">
		<c:if test="${roleFlag eq 'admin'}">
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
				<input type="hidden" id="infoFlow" name="infoFlow"/>
				<input type="hidden" id="orgFlow" name="orgFlow"/>
				<input type="hidden" id="orgName" name="orgName"/>
				<div style="height: 33px;margin-top: 5px;">
					<table style="width: 100%;">
						<tr>
							<td style="min-width: 400px;"><span style="color: red;">*</span>标题：
								<input id="title" name="title" style="height: 22px;width:400px;<c:if test="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}">color:red;</c:if>"/>
							</td>
							<td style="min-width: 200px;"><span style="color: red;">*</span>通知对象：
								<select id="infoTargetFlow" name="infoTargetFlow" style="height:26px;width:156px;">
									<option value="">全部</option>
									<c:forEach items="${roles}" var="role">
										<option value="${role.roleFlow}">${role.roleName}</option>
									</c:forEach>
								</select>
							</td>
							<td style="min-width: 200px;"><span style="color: red;">*</span>通知类型：
								<select id="infoTypeId" name="infoTypeId" style="height:26px;width:156px;">
									<option value="Science">教学通知</option>
									<option value="Study">学习通知</option>
								</select>
							</td>
							<td><input type="button" id="saveBtn" value="发&#12288;布" onclick="saveNotice();" class="search"/></td>
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
					<th style="padding-left: 10px;text-align: left;">通知</th>
				</tr>
				<c:forEach items="${infos}" var="msg">
		            <tr class="operDiv">
		            	<td style="padding-right: 10px;">
		            		<div style="float: left;">
		            			<a href="<s:url value='/xjgl/notice/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank"><font style="color:${roleFlag eq 'student'?'red':''};">${msg.infoTitle}（${msg.infoTypeName}）</font></a>
		            			<c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.createTime))<=7}">
									<img src="<s:url value='/css/skin/new.png'/>"/>
							    </c:if>
		            		</div>
		            		<div style="float: right;">
		            			<span>${pdfn:transDate(msg.createTime)}</span>
		            			<span style="display:none;">
		            				<a href="javascript:edit('${msg.infoFlow}');" style="color: gray;">编辑</a> |
									<a href="<s:url value='/xjgl/notice/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank" style="color: gray;">查看</a> |
									<a href="javascript:delNotice('${msg.infoFlow}');" style="color: gray;">删除</a>
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
