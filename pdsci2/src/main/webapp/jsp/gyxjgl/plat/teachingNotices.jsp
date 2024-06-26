
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
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
		if($("#infoTypeId").val().length==0){
			jboxTip("通知类型不能为空!");
			return;
		}
		jboxStartLoading();
		var title = $.trim($("#title").val());
		var infoFlow = $("#infoFlow").val();
		var content = UE.getEditor('ueditor').getContent();
		var infoTargetFlow = $("#infoTargetFlow option:selected").val();
		var infoTypeId = $("#infoTypeId option:selected").val();
		var infoTypeName = $("#infoTypeId option:selected").text();
		//去除富文本编辑器自带<p></p>符号
		content = content.substring(3,content.length-4);
		var requestData ={
				"infoFlow":infoFlow,
				"infoTitle":title,
				"infoContent":content,
				"infoTypeId":infoTypeId,
				"infoTypeName":infoTypeName,
				"infoTargetFlow":infoTargetFlow
		};
		var url = "<s:url value='/gyxjgl/notice/saveNotice/${roleFlag}'/>";
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
		location.href="<s:url value='/gyxjgl/notice/getList/${roleFlag}'/>?infoTypeId=${param.infoTypeId}&currentPage="+page;
	} 
	function goNoticeByPage(){
		toPage("${param.currentPage}");
	}
	function edit(infoFlow){
		var url = "<s:url value='/gyxjgl/notice/findNoticeByFlow'/>?infoFlow="+infoFlow;
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
			var url = "<s:url value='/gyxjgl/notice/delNotice'/>?infoFlow="+infoFlow;
			jboxGet(url , null , function(resp){
				window.location.reload();
			} , null , true);
		});
	}
	function editFormHidden(){
		$("#editForm,#operImg img").toggle();
	}
	function searchInfo(infoTypeId,currentPage){
		var url = "<s:url value='/gyxjgl/notice/getListByType'/>?infoTypeId="+infoTypeId+"&currentPage="+currentPage;
		jboxLoad("noticeList", url, true);
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
				<input type="hidden" id="infoFlow" name="infoFlow" value=""/>
				<input type="hidden" id="orgFlow" name="orgFlow"/>
				<input type="hidden" id="orgName" name="orgName"/>
				<div style="height: 33px;margin-top: 5px;">
					<table style="width: 100%;">
						<tr>
							<td width="5%" style="text-align:right;"><span style="color: red;">*</span>标题：</td>
							<td width="50%"><input id="title" name="title" style="height: 22px;width:98%;color:red;"/></td>
							<td width="8%" style="text-align:right;"><span style="color: red;">*</span>通知类型：</td>
							<td width="12%">
								<select id="infoTypeId" name="infoTypeId" style="height: 26px;width: 94%;" class="">
									<option value="">全部</option>
									<option value="Science">通知</option>
									<option value="File">文件</option>
									<option value="Download">资料下载</option>
									<option value="Share">信息分享</option>
								</select>
							</td>
							<td width="8%" style="text-align:right;"><span style="color: red;">*</span>通知对象：</td>
							<td width="12%">
								<select id="infoTargetFlow" name="infoTargetFlow" style="height: 26px;width: 94%;" class="">
									<option value="">全部</option>
									<c:forEach items="${roles}" var="role">
										<option value="${role.roleFlow}">${role.roleName}</option>
									</c:forEach>
								</select>
							</td>
							</td>
							<td width="5%"><input type="button" id="saveBtn" value="发&#12288;布" onclick="saveNotice();" class="search"/></td>
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
					<th style="padding-left: 10px;text-align: left;">
						<c:choose>
							<c:when test="${param.infoTypeId eq 'Science'}">通知</c:when>
							<c:when test="${param.infoTypeId eq 'File'}">文件</c:when>
							<c:when test="${param.infoTypeId eq 'Download'}">资料下载</c:when>
							<c:when test="${param.infoTypeId eq 'Share'}">信息分享</c:when>
							<c:otherwise>
								<a style="color:blue;cursor:pointer;" onclick="searchInfo('',1)">全部</a>
								<a style="color:grey;cursor:pointer;" onclick="searchInfo('Science',1)">通知</a>
								<a style="color:grey;cursor:pointer;" onclick="searchInfo('File',1)">文件</a>
								<a style="color:grey;cursor:pointer;" onclick="searchInfo('Download',1)">资料下载</a>
								<a style="color:grey;cursor:pointer;" onclick="searchInfo('Share',1)">信息分享</a>
							</c:otherwise>
						</c:choose>
					</th>
				</tr>
				<c:forEach items="${infos}" var="msg">
		            <tr class="operDiv">
		            	<td style="padding-right: 10px;">
		            		<div style="float: left;">
		            			<a href="<s:url value='/gyxjgl/notice/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank"><font style="color:${roleFlag eq 'student'?'red':''};">${msg.infoTitle}</font></a>
		            			<c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.createTime))<=7}">
									<img src="<s:url value='/css/skin/new.png'/>"/>
							    </c:if>
		            		</div>
		            		<div style="float: right;">
		            			<span>${pdfn:transDate(msg.createTime)}</span>
		            			<span style="display:none;">
		            				<a href="javascript:edit('${msg.infoFlow}');" style="color: gray;">编辑</a> |
									<a href="<s:url value='/gyxjgl/notice/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank" style="color: gray;">查看</a> |
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
