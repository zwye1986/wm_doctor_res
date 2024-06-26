<script>
$(function(){
	var editFlag = 'Y';
	var showPrint = 'N';
	<c:if test="${apply.doctorStatusId eq '1'}">
	editFlag = 'N';
	</c:if>
	<c:if test="${apply.orgStatusId eq '1'}">
	showPrint = 'Y';
	</c:if>
	$("#toptab li").click(function(){
		$(".tab_select").addClass("tab");
		$(".tab_select").removeClass("tab_select");
		$(this).removeClass("tab");
		$(this).addClass("tab_select");
		if($(this).attr('id')=="zgshqkb"){//资格审核情况表
			jboxStartLoading();
			jboxLoad("content2" , "<s:url value='/sczyres/doctor/zgshqkb'/>?editFlag="+editFlag+"&showPrint="+showPrint , true);
		}
		if($(this).attr("id")=="lzqkshb"){//轮转情况审核表
			jboxStartLoading();
			jboxLoad("content2" , "<s:url value='/sczyres/doctor/lzqkshb'/>?editFlag="+editFlag+"&showPrint="+showPrint , true);
		}
	});
	if('${param.firstFlag}'=='Y'){
		$("#zgshqkb").click();
	}
});
function submit(){
	jboxConfirm("提交后不能修改，请确认两张表格都填写完毕<br/><br/>重新填报注意先保存再提交",function(){
		jboxPost("<s:url value='/sczyres/doctor/submitApply'/>?recordFlow=${apply.recordFlow}",null,function(resp){
			if(resp=='1'){
				jboxTip("操作成功");
				graduation();
			}else{
				jboxTip(resp);
			}
		})
	})
}
	function printTicketDoc(recordFlow){
		window.open("<s:url value='/sczyres/manage/ticketDetail'/>?recordFlow="+recordFlow+"&role=doctor");
	}
</script>
<div class="main_hd">
     <h2>
     	结业申请&#12288;&#12288;
		 <c:if test="${apply.doctorStatusId ne '1' && allowFlag}">
		 	<input class="btn_blue" type="button" onclick="submit();" value="提&#12288;交"/>
		 </c:if>
		<c:if test="${!allowFlag}">
			当前结业申请${regCfgMsg.msg}&#12288;
		</c:if>
		<c:if test="${apply.doctorStatusId eq '1' && apply.chargeStatusId ne '1'}">
			<c:if test="${apply.orgStatusId eq '1'}">
				<input class="btn_blue" type="button" value="初审已通过" style="cursor: auto"/>
			</c:if>
			<c:if test="${apply.orgStatusId ne '1'}">
				<input class="btn_blue" type="button" value="待审核" style="cursor: auto"/>
			</c:if>
		</c:if>
		 <c:if test="${apply.chargeStatusId eq '1'}">
			 <input class="btn_green" type="button" value="审核通过" style="cursor: auto"/>
		 </c:if>
		 <c:if test="${apply.orgStatusId eq '0'}">
			 <font color="red" title="协同基地审核意见：${apply.orgRemark}">协同基地审核不通过</font>
		 </c:if>
		<c:if test="${apply.xtOrgStatusId eq '0'}">
			<font color="red" title="基地审核意见：${apply.xtOrgRemark}">基地审核不通过</font>
		</c:if>
		<c:if test="${apply.chargeStatusId eq '0'}">
			<font color="red" title="中管局审核意见：${apply.chargeRemark}">中管局审核不通过</font>
		</c:if>
		<c:if test="${apply.chargeStatusId eq '1' && cfg.cfgValue eq 'Y' && not empty ticket}">
			<input class="btn_blue" type="button" onclick="printTicketDoc('${ticket.recordFlow}');" value="打印准考证"/>
		</c:if>
     </h2>
    <div class="title_tab" id="toptab" style="height:44px;">
        <ul>
            <li class="tab_select" id='zgshqkb'><a>资格审核情况表</a></li>
        </ul>
		<ul>
			<li id='lzqkshb'><a>轮转情况审核表</a></li>
		</ul>
    </div>
</div>
<div id="content2">
</div>
