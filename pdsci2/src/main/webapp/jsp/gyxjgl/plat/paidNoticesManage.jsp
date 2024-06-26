
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
		var recordFlow = $("input[name='recordFlow']").val();
		var receiverFlag = $("select[name='receiverFlag'] option:selected").val();
		var publisherName = $("input[name='publisherName']").val();
		var publishTime = $("input[name='publishTime']").val();
		var content = UE.getEditor('ueditor').getContent();
		if('${config.fontRequired}' == 'Y' && (publisherName==''||publishTime==''||content=='')){
			jboxTip("发布人、发布时间及发布内容不能为空！");
			return;
		}
		jboxStartLoading();
		var requestData ={
				"recordFlow":recordFlow,
				"receiverFlag":receiverFlag,
				"publisherName":publisherName,
				"publishTime":publishTime,
				"content":content,
				"publishSwitch":'${config.publishSwitch}',
				"titlePosition":'${config.titlePosition}',
				"titleName":'${config.titleName}',
				"skinColor":'${config.skinColor}',
				"fontColor":'${config.fontColor}'
		};
		var url = "<s:url value='/gyxjgl/notice/saveFeeNotice'/>";
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
		location.href="<s:url value='/gyxjgl/notice/paidNotice/${roleFlag}'/>?currentPage="+page;
	} 
	function goNoticeByPage(){
		toPage("${param.currentPage}");
	}
	function edit(recordFlow){
		var url = "<s:url value='/gyxjgl/notice/findFeeNoticeByFlow'/>?recordFlow="+recordFlow;
		jboxGet(url , null , function(resp){
			if(resp){
				if($("#editForm").is(":hidden")){
					editFormHidden();
				}
				$("input[name='recordFlow']").val(resp.recordFlow);
				$("[value='"+resp.receiverFlag+"']").attr("selected","selected");
				$("input[name='publisherName']").val(resp.publisherName);
				$("input[name='publishTime']").val(resp.publishTime);
				UE.getEditor('ueditor').setContent(resp.content);
				$("#bodyDiv").animate({scrollTop:"0px"},500);
			}
		} , null , false);
	}
	function delNotice(recordFlow){
		jboxConfirm("确认删除？" , function(){
			var url = "<s:url value='/gyxjgl/notice/delFeeNotice'/>?recordFlow="+recordFlow;
			jboxGet(url , null , function(resp){
				window.location.reload();
			} , null , true);
		});
	}
	function editFormHidden(){
		$("#editForm,#operImg img").toggle();
	}
	function feeConfig(){
		var url = "<s:url value='/gyxjgl/notice/feeConfig'/>";
		jboxOpen(url ,"样式流程配置", 600, 300,true);
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
				<input type="hidden" name="recordFlow" value="${temp.recordFlow}"/>
				<div style="height: 33px;margin-top: 5px;">
					<table style="width: 100%;">
						<tr>
							<td>&#12288;通知对象：
								<select name="receiverFlag" style="height: 26px;width:156px;">
									<option value="N" ${temp.reciverFlag eq 'N'?'selected':''}>未缴清学费</option>
									<option value="Y" ${temp.reciverFlag eq 'Y'?'selected':''}>已缴清学费</option>
								</select>
							</td>
							<td>发布人：
								<input name="publisherName" style="height: 22px;" value="${empty temp.recordFlow?sessionScope.currUser.userName:temp.publisherName}"/>
							</td>
							<td>发布时间：<c:set var="curDate" value="${pdfn:getCurrDate()}"/>
								<input name="publishTime" style="height: 22px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${empty temp.recordFlow?curDate:temp.publishTime}"/>
							</td>
							<td>
								<input type="button" value="样式流程配置" onclick="feeConfig();" class="search"/>
								<input type="button" value="${config.publishSwitch eq 'Y'?'发&#12288;布':'保&#12288;存'}" onclick="saveNotice();" class="search"/>
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
					<th style="padding-left: 10px;text-align: left;">缴费通知</th>
				</tr>
				<c:forEach items="${infos}" var="msg">
		            <tr class="operDiv">
		            	<td style="padding-right: 10px;">
		            		<div style="float: left;">
		            			<a href="<s:url value='/gyxjgl/notice/feeNoticeView'/>?recordFlow=${msg.recordFlow}" target="_blank"><font style="color:${roleFlag eq 'student'?'red':''};">${msg.titleName}<c:if test="${roleFlag ne 'student'}">${msg.receiverFlag eq 'Y'?'（已缴清）':'（未缴清）'}</c:if></font></a>
		            			<c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.publishTime))<=7}">
									<img src="<s:url value='/css/skin/new.png'/>"/>
							    </c:if>
		            		</div>
		            		<div style="float: right;">
		            			<span>${pdfn:transDate(msg.publishTime)}</span>
		            			<span style="display:none;">
		            				<a href="javascript:edit('${msg.recordFlow}');" style="color: gray;">编辑</a> |
									<a href="<s:url value='/gyxjgl/notice/feeNoticeView'/>?recordFlow=${msg.recordFlow}" target="_blank" style="color: gray;">查看</a> |
									<a href="javascript:delNotice('${msg.recordFlow}');" style="color: gray;">删除</a>
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
