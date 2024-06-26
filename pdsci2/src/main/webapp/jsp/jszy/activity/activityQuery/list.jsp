<script type="text/javascript">
function regiestInfo(activityFlow)
{
	var url = '<s:url value="/jszy/base/activityQuery/joinActivity"/>?activityFlow='+activityFlow;
	jboxConfirm("确认报名参加此活动？" , function(){
		jboxPost(url , null , function(resp){
			toPage(1);
		} , null , true);
	});
}
function signUrl(activityFlow){
	jboxOpen("<s:url value='/jszy/base/activityQuery/signUrl'/>?activityFlow=" + activityFlow,'二维码',450,500);
}
function showInfo(activityFlow,roleFlag){
	jboxOpen("<s:url value='/jszy/base/activityQuery/activityDetail'/>?activityFlow=" + activityFlow+"&roleFlag="+roleFlag,'活动详情',600,400);
}
function delActivity(activityFlow){
	jboxConfirm("确认删除？", function(){
		var url = "<s:url value='/jszy/base/activityQuery/delActivity'/>?activityFlow=" + activityFlow;
		jboxGet(url, null, function(resp){
			if(resp == '${GlobalConstant.DELETE_SUCCESSED}'){
				toPage(1);
			}
		});
	});
}
function showEval(activityFlow,roleFlag){
	jboxOpen("<s:url value='/jszy/base/activityQuery/showEvalType'/>?activityFlow=" + activityFlow+"&roleFlag="+roleFlag,'活动评价详情',1000,600,false);
}
function showDocEval(resultFlow){
	jboxOpen("<s:url value='/jszy/base/activityQuery/showDocEval'/>?resultFlow=" + resultFlow,'评价详情',600,300);
}
function evalInfo(resultFlow){
	var url = "<s:url value='/jszy/base/activityQuery/checkEval'/>?resultFlow=" + resultFlow;
	jboxGet(url, null, function(resp){
		if(resp == ''){
			jboxOpen("<s:url value='/jszy/base/activityQuery/evalInfo'/>?resultFlow=" + resultFlow,'评价',600,300);
		}else{
			jboxTip(resp);
		}
	},null,false);
}
var indexActivityFlow="";
var indexFileFlow="";
var indexObj=null;
function uploadFile(activityFlow,fileFlow,obj)
{
	indexActivityFlow=activityFlow;
	indexFileFlow=fileFlow;
	indexObj=obj;

	$("#file").click();
}
/**
 * 上传头像
 */
function uploadImage(file){
	var fileName=$(file).val();
	if(fileName=="")
	{
		jboxTip("请选择文件！");
		return ;
	}
	if(indexActivityFlow=="")
	{
		jboxTip("请选择教学活动！");
		return ;
	}
	if(indexObj==null)
	{
		jboxTip("请选择教学活动！");
		return ;
	}
	$.ajaxFileUpload({
		url:"<s:url value='/jszy/base/activityQuery/saveActivityFile'/>?activityFlow="+indexActivityFlow+"&fileFlow="+indexFileFlow,
		secureuri:false,
		fileElementId:'file',
		dataType: 'json',
		success: function (resp, status){
			var json=eval("("+resp+")");
			if(json.code!="1"){
				jboxTip(json.msg);
			}else{
				var fileFlow=json.fileFlow;
				var fileName=json.fileName;
				console.log(fileName);
				var url="<s:url value='/jszy/base/activityQuery/downFile'/>?fileFlow="+fileFlow;
				var html='<a href="'+url+'"><img style="cursor:pointer" title="'+fileName+'" src="<s:url value='/jsp/jsres/images/manual.png'/>"></a>'
				+'<a href="javascript:void(0);" onclick="uploadFile(\''+indexActivityFlow+'\',\''+fileFlow+'\',this);">重传</a>';
				$(indexObj).parent().html(html);
				jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
				fileName="";
				indexObj=null;
			}
		},
		error: function (data, status, e){
			jboxTip('${GlobalConstant.UPLOAD_FAIL}');
		},
		complete:function(){
			$("#file").val("");
			indexActivityFlow="";
			indexFileFlow="";
		}
	});
}
</script>

<input type="file" id="file" name="file" onchange="uploadImage();" style="display: none"/>
<style type="text/css">
	.bg{
		width: 60px;
		height: 16px;
		background: url(<s:url value='/jsp/jsres/activity/img/star_gray.png'/>);
		margin-left: 15px;
	}
	.img{
		width: 20px;
		height: 20px;
		margin-left: 5px;
	}
	.over{
		height:16px;
		background:url(<s:url value='/jsp/jsres/activity/img/star_org.png'/>) no-repeat;
	}
</style>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="20%" />
				<c:if test="${param.roleFlag ne 'doctor'}">
					<col width="10%" />
				</c:if>
				<col width="5%" />
				<col width="15%" />
			</colgroup>
            <tr>
                <th>活动名称</th>
                <th>活动形式</th>
                <th>活动地点</th>
                <th>主讲人</th>
                <th>所在科室</th>
                <th>活动时间</th>
				<c:if test="${param.roleFlag ne 'doctor'}">
     	           <th>评价</th>
				</c:if>
                <th>附件</th>
                <th>操作</th>
            </tr>
             <c:forEach items="${list}" var="b" varStatus="s">
	             <tr>
	                <td title="${b.activityName}">${pdfn:cutString(b.activityName,12,true,3)}</td>
	                <td>${b.activityTypeName}</td>
	                <td title="${b.activityAddress}">${pdfn:cutString(b.activityAddress,6,true,3)}</td>
	                <td>${b.userName}</td>
	                <td>${b.deptName}</td>
	                <td>${b.startTime}<br/>${b.endTime}</td>
					<c:if test="${param.roleFlag ne 'doctor'}">
						<td <c:if test="${param.roleFlag ne 'doctor'}">onclick="showEval('${b.activityFlow}','${param.roleFlag}');" style="cursor: pointer;" </c:if>>
							<div class="bg">
								<div class="over" style="width:${12*b.evalScore}px"></div>
							</div>
						</td>
					</c:if>
	                <td>
						<c:if test="${(param.roleFlag eq 'teach'or param.roleFlag eq 'head') and sessionScope.currUser.userFlow eq b.speakerFlow}">

							<c:if test="${not empty b.fileFlow}">
								<a href="<s:url value='/jszy/base/activityQuery/downFile'/>?fileFlow=${b.fileFlow}"><img style="cursor:pointer" title="${b.fileName}" src="<s:url value='/jsp/jsres/images/manual.png'/>"></a>
								<a  href="javascript:void(0);" onclick="uploadFile('${b.activityFlow}','${b.fileFlow}',this);">重传</a>
							</c:if>
							<c:if test="${empty b.fileFlow}">
								<a  href="javascript:void(0);" onclick="uploadFile('${b.activityFlow}','${b.fileFlow}',this);">上传</a>
							</c:if>
						</c:if>
						<c:if test="${!((param.roleFlag eq 'teach'or param.roleFlag eq 'head') and sessionScope.currUser.userFlow eq b.speakerFlow)}">
							<c:if test="${empty b.fileFlow}">
								无
							</c:if>
							<c:if test="${not empty b.fileFlow}">
								<a href="<s:url value='/jszy/base/activityQuery/downFile'/>?fileFlow=${b.fileFlow}">
									<img style="cursor:pointer" title="${b.fileName}" src="<s:url value='/jsp/jsres/images/manual.png'/>"></a>
							</c:if>
						</c:if>
					</td>
	          		<td style="text-align: left;padding-left: 30px;">
						<c:if test="${param.roleFlag eq 'teach'or param.roleFlag eq 'head'}">
							<c:if test="${(fn:length(resultMap[b.activityFlow])+0)<=0}">
								<img class="img" style="cursor:pointer"  onclick="editActivity('${b.activityFlow}','${param.roleFlag}');" title="编辑" src="<s:url value='/jsp/jsres/activity/img/edit.png'/>"></a>
								<img class="img" style="cursor:pointer" onclick="delActivity('${b.activityFlow}')" title="删除" src="<s:url value='/jsp/jsres/activity/img/del.png'/>"></a>
							</c:if>
							<img class="img" style="cursor:pointer" onclick="signUrl('${b.activityFlow}')" title="二维码" src="<s:url value='/jsp/jsres/activity/img/code.png'/>"></a>
						</c:if>
						<c:if test="${param.roleFlag ne 'teach' and param.roleFlag ne 'head'}">
							<img class="img" style="cursor:pointer" onclick="showInfo('${b.activityFlow}','${param.roleFlag}')" title="详情" src="<s:url value='/jsp/jsres/activity/img/show.png'/>"></a>
						</c:if>
						<c:if test="${(param.roleFlag eq 'teach'or param.roleFlag eq 'head') and b.startTime <= nowDate}">
							<img class="img" style="cursor:pointer" onclick="showInfo('${b.activityFlow}','${param.roleFlag}')" title="详情" src="<s:url value='/jsp/jsres/activity/img/show.png'/>"></a>
						</c:if>
						<c:if test="${param.roleFlag eq 'doctor'}">
							<c:if test="${param.isNew eq 'Y'}">
								<c:if test="${(empty b.resultFlow||(not empty b.resultFlow and b.isRegiest ne 'Y')) and b.startTime >= nowDate}">
									<img class="img" style="cursor:pointer" onclick="regiestInfo('${b.activityFlow}')" title="报名" src="<s:url value='/jsp/jsres/activity/img/regiest.png'/>"></a>
								</c:if>
							</c:if>
							<c:if test="${not empty b.resultFlow and b.isRegiest eq 'Y' and b.isScan ne 'Y'}">
								<img class="img" style="cursor:pointer" onclick="regiestCancel('${b.activityFlow}')" title="取消报名" src="<s:url value='/jsp/jsres/activity/img/cannel.png'/>"></a>
							</c:if>
							<c:if test="${param.isEval eq 'Y' and b.isScan eq 'Y' and b.isScan2 eq 'Y'}">
								<c:if test="${empty b.evalScore2}">
									<img class="img" style="cursor:pointer" onclick="evalInfo('${b.resultFlow}')" title="评价" src="<s:url value='/jsp/jsres/activity/img/eval.png'/>"></a>
								</c:if>
								<c:if test="${not empty b.evalScore2}">
									<img class="img" style="cursor:pointer" onclick="showDocEval('${b.resultFlow}')" title="查看评价" src="<s:url value='/jsp/jsres/activity/img/showEval.png'/>"></a>
								</c:if>
							</c:if>
						</c:if>
						<c:if test="${param.roleFlag eq 'teach' or param.roleFlag eq 'head'}">
							<c:if test="${sessionScope.currUser.userFlow eq b.speakerFlow}">
								<img class="img" style="cursor:pointer" onclick="upload('${b.activityFlow}','Y')" title="上传活动图片" src="<s:url value='/jsp/jsres/activity/img/upload.png'/>"></a>
							</c:if>
						</c:if>
						<c:if test="${param.roleFlag eq 'local' and b.HaveImg eq 'Y'}">
							<img class="img" style="cursor:pointer" onclick="upload('${b.activityFlow}','N')" title="查看活动图片" src="<s:url value='/jsp/jsres/activity/img/showUpload.png'/>"></a>
						</c:if>
					</td>
	            </tr>
            </c:forEach>
            <c:if test="${empty list}">
				<tr>
					<c:if test="${param.roleFlag ne 'doctor'}">
						<td colspan="10" >无记录！</td>
					</c:if>
					<c:if test="${param.roleFlag eq 'doctor'}">
						<td colspan="9" >无记录！</td>
					</c:if>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
