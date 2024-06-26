<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script>

	$(function () {
		$("#treeTable").treetable({expandable: true,indenterTemplate: "<span class='indenter'></span>"});
		$("#tbody tr").on("click",function(){
			$(this).siblings().removeClass("selected");
			$(this).addClass("selected");
		});
		<c:if test="${not empty param.selectedId}">
			var f=false;
			$("#tbody tr").each(function(){
				if($(this).attr("data-tt-id")=="${param.selectedId}"){
					f=true;
					$(this).siblings().removeClass("selected");
					$(this).addClass("selected");
					return false;
				};
			});
			if(f){
				showTr("${ param.selectedId}");
			}
		</c:if>
	});

	function showTr(pId) {
		if (pId)
		{
			var id="";
			id=$("tr[data-tt-id='"+pId+"']").attr("data-tt-parent-id");
			showTr(id);
			$('#treeTable').treetable('collapseOrexpand',pId);
		}
	}
	function publish(cfgFlow){
		if($("td[needSpe]").length!=$("td[hasSpe]").length)
		{
			jboxTip("还有专业基地未关联相应专业！");
			return false;
		}
		jboxConfirm("确认发布?发布后将无法修改!",function(){
			jboxPost("<s:url value='/eval/evalCfg/publishCfg'/>",{cfgFlow:cfgFlow,isPublish:"${GlobalConstant.FLAG_Y}"},function(resp){
				if('${GlobalConstant.SAVE_SUCCESSED}'==resp){
					search();
					jboxTip("发布成功！");
				}else{
					jboxTip("发布失败！");
				}
			},null,false);
		});
	}
	function del(cfgFlow){
		jboxConfirm("确认删除？将同时删除子配置信息",function(){
			jboxPost("<s:url value='/eval/evalCfg/delCfg'/>",{cfgFlow:cfgFlow,recordStatus:"${GlobalConstant.FLAG_N}"},function(resp){
				if('${GlobalConstant.SAVE_SUCCESSED}'==resp){
					search();
					jboxTip("删除成功！");
				}else{
					jboxTip("删除失败！");
				}
			},null,false);
		});
	}
</script>

<table class="xllist"  id="treeTable"  style="width:100%;margin-top: 10px;text-align: center">
	<colgroup>
		<col width="30%"/>
		<col width="10%"/>
		<col width="20%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="20%"/>
	</colgroup>
	<thead>
	<tr>
		<th>评估指标</th>
		<th>评估年份</th>
		<th>评估名称</th>
		<th>文件类型</th>
		<th>发布状态</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody id="tbody">
	<c:forEach items="${allList}" var="cfg">
		<tr data-tt-id="${cfg.cfgFlow}"
		<c:if test="${not empty cfg.parentCfgFlow}">
			data-tt-parent-id="${cfg.parentCfgFlow}"
		</c:if>
		>
			<td style="text-align: left;">${cfg.cfgName}
			<c:if test="${not empty cfg.speName }">
				【${cfg.speName }】
			</c:if>
			</td>
			<td>${cfg.evalYear}</td>
			<td>${cfg.cfgName}</td>
			<td>
				<c:if test="${cfg.isFile eq 'Y'}">文件</c:if>
				<c:if test="${cfg.isFile eq 'N'}">文件夹</c:if>
				<c:if test="${cfg.isFile eq 'U'}">请求</c:if>
			</td>
			<td>
				<c:if test="${empty cfg.parentCfgFlow}">
					<c:if test="${cfg.isPublish eq 'Y'}">已发布</c:if>
					<c:if test="${cfg.isPublish eq 'N'}">未发布</c:if>
				</c:if>
			</td>
			<td style="text-align: left;"
					<c:if test="${cfg.isFile eq 'N' and cfg.levelId eq 2 and cfg.typeId eq 'CLINICAL'}">
						needSpe
					</c:if>
					<c:if test="${not empty cfg.speName }">
						hasSpe
					</c:if>
			>
				<c:if test="${cfg.isPublish eq 'N'}">
					[<span style="padding:0;"  href="javascript:void(0);" onclick="javascript:add('${cfg.cfgFlow}','${cfg.parentCfgFlow}');" >编辑</span>] |
					<c:if test="${empty cfg.parentCfgFlow}">
						[<span style="padding:0;"  href="javascript:void(0);" onclick="javascript:publish('${cfg.cfgFlow}');" >发布</span>] |
					</c:if>
					[<span style="padding:0;"  href="javascript:void(0);" onclick="javascript:del('${cfg.cfgFlow}');" >删除</span>] |
					<c:if test="${empty cfg.parentCfgFlow and fn:length(cfgMap[cfg.cfgFlow])<2}">
						[<span style="padding:0;"  href="javascript:void(0);" onclick="javascript:add('','${cfg.cfgFlow}');" >添加子配置</span>] |
					</c:if>
					<c:if test="${not empty cfg.parentCfgFlow and cfg.isFile eq 'N' and cfg.levelId<3}">
						[<span style="padding:0;"  href="javascript:void(0);" onclick="javascript:add('','${cfg.cfgFlow}');" >添加子配置</span>] |
					</c:if>
					<c:if test="${cfg.isFile eq 'N' and cfg.levelId eq 2 and cfg.typeId eq 'CLINICAL'}">
						[<span style="padding:0;"  href="javascript:void(0);" onclick="javascript:addSpe('${cfg.cfgFlow}');" >专业基地关联</span>] |
					</c:if>
				</c:if>
				<c:if test="${cfg.isPublish eq 'Y'}">
					[<span style="padding:0;"  href="javascript:void(0);" onclick="javascript:showCfg('${cfg.cfgFlow}','${cfg.parentCfgFlow}');" >查看</span>]
				</c:if>
			</td>
		</tr>
	</c:forEach>
		<c:if test="${empty allList }">
			<tr>
				<td colspan="14">无记录</td>
			</tr>
		</c:if>
	</tbody>
</table>
<div>
	<c:set var="pageView" value="${pdfn:getPageView(cfgList)}" scope="request"/>
	<pd:pagination toPage="toPage"/>
</div>