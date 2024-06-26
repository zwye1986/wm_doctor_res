<script type="text/javascript">

	function signUrl(activityFlow){
		jboxOpen("<s:url value='/jsres/activityQuery/signUrl'/>?activityFlow=" + activityFlow,'二维码',450,500);
	}
	function showInfo(activityFlow,roleFlag){
		jboxOpen("<s:url value='/jsres/activityQuery/activityDetail'/>?activityFlow=" + activityFlow+"&roleFlag="+roleFlag,'活动详情',800,500);
	}

	function upload(activityFlow,isUpload){
		var url = "<s:url value='/jszy/base/activityQuery/upload'/>?activityFlow="+activityFlow+"&isUpload="+isUpload;
		jboxOpen(url, "查看活动图片",700,550);
	}
	function showEval(activityFlow,roleFlag){
		jboxOpen("<s:url value='/jsres/speAdmin/showEvalType'/>?activityFlow=" + activityFlow,'活动评价详情',1000,600,false);
	}
	function showDocEval(resultFlow){
		jboxOpen("<s:url value='/jsres/activityQuery/showDocEval'/>?resultFlow=" + resultFlow,'评价详情',800,600);
	}
	function evalInfo(resultFlow){
		var url = "<s:url value='/jsres/activityQuery/checkEval'/>?resultFlow=" + resultFlow;
		jboxGet(url, null, function(resp){
			if(resp == ''){
				jboxOpen("<s:url value='/jsres/activityQuery/evalInfo'/>?resultFlow=" + resultFlow,'评价',800,600);
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
	.grid>tbody>tr td:last-child img{
		vertical-align: inherit;
	}
	.gray{
		color: #ccc;
	}
	.gray:hover{
		text-indent: initial;
	}
</style>
    <div class="div_table">
        <table border="0" cellpadding="0" cellspacing="0"  class="grid" style="word-break: break-all;">
            <tr>
                <th style="width:88px">活动名称</th>
                <th style="width:56px">活动形式</th>
                <th>活动地点</th>
                <th style="width:45px">主讲人</th>
                <th>所在科室</th>
                <th style="width: 120px;">活动时间</th>
				<th>评价</th>
                <th>签到人数</th>
				<th style="width:64px">活动状态</th>
                <th>附件</th>
                <th >操作</th>
				<th>是否认可</th>
            </tr>

             <c:forEach items="${list}" var="b" varStatus="s">
	             <tr>
	                <td title="${b.activityName}">${pdfn:cutString(b.activityName,12,true,3)}</td>
	                <td>${b.activityTypeName}</td>
	                <td title="${b.activityAddress}">${pdfn:cutString(b.activityAddress,6,true,3)}</td>
	                <td>${b.userName}</td>
	                <td>${b.deptName}</td>
	                <td>${b.startTime}<br/>${b.endTime}</td>

					<td onclick="showEval('${b.activityFlow}','${param.roleFlag}');" style="cursor: pointer;">
						<div class="bg">
							<div class="over" style="width:${12*b.evalScore}px"></div>
						</div>
					</td>
                    <%--签到人数--%>
                     <td>${b.scanNum}</td>
					<%--教学活动审核状态--%>
						 <c:if test="${b.activityStatus eq 'audit'}">
						     <c:if test="${b.audit eq 'Y'}">
							     <td>待审核</td>
							 </c:if>
							 <c:if test="${b.audit ne 'Y'}">
								 <td>待审核</td>
						     </c:if>
						 </c:if>
						 <c:if test="${b.activityStatus eq 'pass'}">
							 <td>已通过</td>
						 </c:if>
						 <c:if test="${b.activityStatus eq 'unpass'}">
							 <td>未通过</td>
						 </c:if>
						 <c:if test="${b.activityStatus eq 'over'}">
							 <td>已过期</td>
						 </c:if>

                     <%-- 附件 --%>
	                <td>
						<c:if test="${sessionScope.currUser.userFlow eq b.speakerFlow or param.roleFlag eq 'secretary'}">
							<c:if test="${not empty b.fileFlows}">
								<a  href="javascript:void(0);" onclick="searchFile('${b.activityFlow}','${param.roleFlag}');">查看</a>
							</c:if>

						</c:if>
						<c:if test="${!((sessionScope.currUser.userFlow eq b.speakerFlow) or param.roleFlag eq 'secretary')}">
							<c:if test="${empty b.fileFlows}">
								无
							</c:if>
							<c:if test="${not empty b.fileFlows}">
								<a  href="javascript:void(0);" onclick="searchFile('${b.activityFlow}','${param.roleFlag}');">查看</a>
							</c:if>
						</c:if>
					</td>

                     <%-- 操作 --%>
	          		<td  style="text-align: left; white-space: nowrap ;" >
						<img class="img" style="cursor:pointer" onclick="signUrl('${b.activityFlow}')" title="二维码" src="<s:url value='/jsp/jsres/activity/img/code.png'/>"></a>

						<c:if test="${ b.startTime <= nowDate}">
							<img class="img" style="cursor:pointer" onclick="showInfo('${b.activityFlow}','${param.roleFlag}')" title="详情" src="<s:url value='/jsp/jsres/activity/img/show.png'/>"></a>
						</c:if>

						<c:if test="${sessionScope.currUser.userFlow ne b.speakerFlow and param.roleFlag ne 'secretary'}">
							<img class="img" style="cursor:pointer" onclick="upload('${b.activityFlow}','N')" title="查看活动图片" src="<s:url value='/jsp/jsres/activity/img/showUpload.png'/>"></a>
						</c:if>
					</td>
					 <td>
					 <c:if test="${ b.IS_EFFECTIVE eq 'N'}">
						 <font>不认可</font>
					 </c:if>
					 <c:if test="${ b.IS_EFFECTIVE eq 'Y'}">
						 <font>认可</font>
					 </c:if>
					</td>


	            </tr>
            </c:forEach>
            <c:if test="${empty list}">
				<tr>
					<td colspan="99" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
