<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
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
		var requestData ={
				"infoFlow":infoFlow,
				"infoTitle":title,
				"infoContent":content,
		};
		var url = "<s:url value='/zsey/base/saveNotice'/>";
		jboxPost(url,requestData,function(resp){
			if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
				jboxEndLoading();
				location.reload();
			}
		},null,true);
	}
	function toPage(page) {
		$("#currentPage").val(page);
		location.href="<s:url value='/zsey/base/announcementList/${param.roleFlag}'/>?currentPage="+page;
	}
	function edit(infoFlow){
		var url = "<s:url value='/zsey/base/findNoticeByFlow'/>?infoFlow="+infoFlow;
		jboxGet(url , null , function(resp){
			if(resp){
				if($("#editForm").is(":hidden")){
					editFormHidden();
				}
				$("#infoFlow").val(resp.infoFlow);
				$("#title").val(resp.infoTitle);
				UE.getEditor('ueditor').setContent(resp.infoContent);
				$("#bodyDiv").animate({scrollTop:"0px"},500);
			}
		} , null , false);
	}
	function delNotice(infoFlow){
		jboxConfirm("确认删除？" , function(){
			var url = "<s:url value='/zsey/base/delNotice'/>?infoFlow="+infoFlow;
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
						发布公告
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
							<td style="white-space:nowrap;width:5%;"><span style="color: red;">*</span>标题：</td>
							<td style="width:90%;"><input id="title" name="title" style="height: 22px;width:98%;"/></td>
							<td style="width:5%;"><input type="button" id="saveBtn" value="发&#12288;布" onclick="saveNotice();" class="search"/></td>
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
					<th style="padding-left: 10px;text-align: left;">公告信息</th>
				</tr>
				<c:forEach items="${notices}" var="msg">
		            <tr class="operDiv">
		            	<td style="padding-right: 10px;">
		            		<div style="float: left;">
		            			<a href="<s:url value='/zsey/base/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
		            			<c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.createTime))<=7}">
									<img src="<s:url value='/css/skin/new.png'/>"/>
							    </c:if>
		            		</div>
		            		<div style="float: right;">
		            			<span>${pdfn:transDate(msg.createTime)}</span>
		            			<span style="display:none;">
		            				<a href="javascript:edit('${msg.infoFlow}');" style="color: gray;">编辑</a> |
									<a href="<s:url value='/zsey/base/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank" style="color: gray;">查看</a> |
									<a href="javascript:delNotice('${msg.infoFlow}');" style="color: gray;">删除</a>
		            			</span>
		            		</div>
		                </td>
		             </tr>
		         </c:forEach>
		         <c:if test="${empty notices}">
				     <tr>
				     	<td style="text-align: center;"><strong>无记录!</strong></td>
					 </tr>
				 </c:if>
			</table>
		     <div class="page" style="padding-right: 40px;">
		         <input id="currentPage" type="hidden" name="currentPage" value=""/>
		         <c:set var="pageView" value="${pdfn:getPageView(notices)}" scope="request"></c:set>
			  	 <pd:pagination toPage="toPage"/>
		     </div>
		</div>
	</div>
</div>
</body>
</html>
