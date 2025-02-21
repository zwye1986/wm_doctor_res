<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){
        <%--<c:if test="${not empty list}">--%>
            <%--var style={"margin":"5px auto","width":"auto"};--%>
            <%--var options ={--%>
                <%--"colums":5//根据固定列的数量--%>
            <%--};--%>
            <%--$("#dataTable").Scorll(options,style,false,null);--%>
        <%--</c:if>--%>
	});
	//培训详情
	function showPeiDetail(recruitFlow,applyYear)
	{
		var url = "<s:url value='/jsres/asse/recruitDetail?recruitFlow='/>"+recruitFlow+"&applyYear="+applyYear;
		jboxOpen(url, "科室轮转详情",1330,500);
	}
	//成绩详情
	function showScore(scoreFlow,recruitFlow,applyYear)
	{
		var url = "<s:url value='/jsres/asse/showScore?recruitFlow='/>"+recruitFlow+"&scoreFlow="+scoreFlow+"&applyYear="+applyYear;
		jboxOpen(url, "公共课考核",800,500);
	}
	//所有详情
	function showAllInfo(recruitFlow,applyYear,applyFlow,flag,roleFlag) {
		var url = "<s:url value='/jsres/asse/showAllInfoMain?recruitFlow='/>"+recruitFlow+"&applyYear="+applyYear+"&applyFlow="+applyFlow+"&flag="+flag+"&roleFlag="+roleFlag;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='1330' height='650' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,'资格审核',1330,650);
	}
//毕业证书图片
function showCerificateImg(imgUrl)
{
	$("#imgUrl").removeAttr("src");
	if(imgUrl=="")
	{
		jboxTip("学员暂未上传毕业证书！");
		return false;
	}else{
		imgUrl="${sysCfgMap['upload_base_url']}/"+imgUrl;
		$("#imgUrl").attr("src",imgUrl);
		$("#imgUrlA").attr("href",imgUrl);
		jboxOpenContent($("#imgDiv").html(),"毕业证书",800,500);
	}
}
//审核
function chargeAuditApply(applyFlow)
{
	var url = "<s:url value='/jsres/asseGlobal/chargeAuditApply?applyFlow='/>"+applyFlow;
	jboxOpen(url, "审核学员填报信息",800,500);
}
//报考材料
function showApplyImg(imgUrl1,imgUrl2)
{
	$("#imgUrl1").removeAttr("src");
	$("#imgUrl2").removeAttr("src");
	if(imgUrl1==""&&imgUrl2==""||imgUrl1=="暂无"&&imgUrl2=="")
	{
		jboxTip("学员暂未上传报考资格材料！");
		return false;
	}else{
		if(imgUrl1==""||imgUrl1=="暂无")
			$("#div1").hide();
		if(imgUrl2=="")
			$("#div2").hide();
		imgUrl1="${sysCfgMap['upload_base_url']}/"+imgUrl1;
		imgUrl2="${sysCfgMap['upload_base_url']}/"+imgUrl2;
		$("#imgUrl1").attr("src",imgUrl1);
		$("#imgUrl2").attr("src",imgUrl2);
		$("#imgUrlA1").attr("href",imgUrl1);
		$("#imgUrlA2").attr("href",imgUrl2);
		jboxOpenContent($("#imgDiv2").html(),"报考资格材料",800,500);
	}
}
	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
		$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
	}
	function heightFiexed(){
		//修正高度
		var toFixedTd = $(".toFiexdDept");
		$(".fixedBy").each(function(i){
			$(toFixedTd[i]).height($(this).height());
		});
		var fixTrTd = $(".fixTrTd");
		var fixTrTh = $(".fixTrTh");
		var fixTd = $(".fix");
		$(fixTrTd).each(function(i)
		{
			var maxheight=-1;
			$(fixTrTd[i]).find(".by").each(function(){
				if($(this).height()>maxheight) maxheight=$(this).height();
			});
			if(maxheight!=-1) {
				$(fixTrTh[i]).find(".fix").each(function(){
					$(this).height(maxheight);
				});
			}
		});
	}

	onresize = function(){
		heightFiexed();
	};
	function selectAll(){
		$("td input[type='checkbox'][name='applyFlow']").attr("checked","checked");
	}
	function Contrary(exp){
		var selects = $("td input[type='checkbox'][name='applyFlow']");
		for(var i=0;i<selects.length;i++){
			var exp =$(selects[i]).attr("checked");
			if(exp=="checked"){
				$(selects[i]).attr("checked",false);
			}else {
				$(selects[i]).attr("checked","checked");
			}
		}
	}

	function checkboxOnclick(checkbox,auditStatusId) {

		if (checkbox.checked == true) {
			if(auditStatusId != 'WaitGlobalPass'){
				checkbox.checked = false;
				jboxTip("只能批量审核待审核的资格审查");
			}

		}
	}

</script>
<div id="imgDiv" style="display: none;text-align: center;">
	<a id="imgUrlA" href="" target="_blank">
		<img id="imgUrl" height="380" width="780">
	</a>
</div>
<div id="imgDiv2" style="display: none;text-align: center;">
	<div style="overflow: auto;max-height: 430px;">
		<div id="div1" class="div_table" style="width: 90%;">
			<h4>资格证书</h4>
			<a id="imgUrlA1" href="" target="_blank">
				<img id="imgUrl1" height="300" width="700">
			</a>
		</div>
		<div id="div2" class="div_table" style="width: 90%;">
			<h4>特殊岗位证明材料</h4>
			<a id="imgUrlA2" href="" target="_blank">
				<img id="imgUrl2" height="300" width="700">
			</a>
		</div>
	</div>
</div>

<div class="main_bd clearfix" style="width: auto;height: auto;margin-top: 5px;margin-bottom: 10px;overflow: auto;margin-left: 0px;">
    <c:if test="${not empty list}">
        <div class="search_table" style="padding:0">
        <table class="grid" style="width: 100%;" id="dataTable">
            <thead>
            <tr>
                <c:if test="${f eq 'Y' and (param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2')}">
                    <th style="min-width: 80px; max-width: 80px; ">
                        <c:if test="${param.auditStatusId eq 'WaitGlobalPass'}">
                            <a href="javascript:void(0);"
                               onclick="selectAll();">全选</a>/<a href="javascript:void(0);" onclick="Contrary();">反选</a>
                        </c:if>
                    </th>
                </c:if>
                <th style="min-width: 60px; max-width: 100px; " >姓名</th>
                <th style="min-width: 60px; max-width: 100px; " >地市</th>
                <th style="min-width: 150px; max-width: 150px; ">培训基地</th>
                <th style="min-width: 60px; max-width: 60px; " >报考专业</th>
                <th style="min-width: 170px; max-width: 170px; " >培训起止时间</th>
                <th style="min-width: 100px; max-width: 100px; " >学历</th>
                <th style="min-width: 150px; max-width: 150px; " >毕业证书编号</th>
                <th style="min-width: 150px; max-width: 150px; " >报考资格材料</th>
                <th style="min-width: 200px; max-width: 200px; " >报考资格材料编码</th>
                <th style="min-width: 60px; max-width: 60px; " >培训专业</th>
                <th style="min-width: 60px; max-width: 60px; " >执业范围</th>
                <th style="min-width: 60px; max-width: 60px; ">异常报考</th>
                <c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2'}">
                    <th style="min-width: 60px; max-width: 60px; ">审核</th>
                </c:if>
                <c:if test="${param.tabTag ne 'FristWait' and param.tabTag ne 'FristWait2'}">
                    <th style="min-width: 60px; max-width: 60px; " >操作</th>
                </c:if>
                <c:if test="${param.tabTag ne 'FristWait' and param.tabTag ne 'FristWait2'}">
                    <th style="min-width: 60px; max-width: 60px; " >审核状态</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="doctor">
                <tr class="fixTrTd">
                    <c:if test="${f eq 'Y' and (param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2')}">
                        <td style="min-width: 80px; max-width: 80px; " class="by">
                            <input value="${doctor.applyFlow}" name="applyFlow" type="checkbox" onclick="checkboxOnclick(this,'${doctor.auditStatusId}')"/>
                        </td>
                    </c:if>
                    <td style="min-width: 60px; max-width: 100px; " >${doctor.userName}
                    </td>
                    <td style="min-width: 60px; max-width: 100px; " >${doctor.orgCityName}
                    </td>
                    <td style="min-width: 150px; max-width: 150px; " >${doctor.orgName}</td>
                    <td style="min-width: 60px; max-width: 60px; " >${doctor.changeSpeName}</td>
                    <td style="min-width: 170px; max-width: 170px; " ><a href="javascript:void(0);"
                                                                                   onclick="showPeiDetail('${doctor.recruitFlow}','${doctor.applyYear}')">${doctor.startDate}~${doctor.endDate}</a>
                    </td>

                    <td style="min-width: 100px; max-width: 100px; " >${doctor.educationName}</td>
                    <td style="min-width: 150px; max-width: 150px; " >
                        <c:if test="${not empty doctor.certificateNo}">
                            <a href="javascript:void(0);"
                               onclick="showCerificateImg('${doctor.certificateUrl}')">${doctor.certificateNo}</a>
                        </c:if>
                    </td>
                        <%-- 毕业证书编号 --%>

                    <td style="min-width: 150px; max-width: 150px;" ><%-- 报考资格材料 --%>
                        <%--<c:if test="${not empty doctor.qualificationMaterialName}">--%>
                        <c:if test="${not empty doctor.materialName}">
                            <%--<a href="javascript:void(0);"--%>
                               <%--onclick="showApplyImg('${doctor.qualificationMaterialUri}','${doctor.specialCertificationUri}')">--%>
                                    <%--${doctor.qualificationMaterialName}--%>
                            <%--</a>--%>
                            <a href="javascript:void(0);"
                               onclick="showApplyImg('${doctor.qualificationMaterialUrl}','${doctor.specialCertificationUrl}')">
                                    ${doctor.materialName}
                            </a>
                        </c:if>
                    </td>
                    <td style="min-width: 100px; max-width: 100px; " >${doctor.qualificationMaterialCode}</td>
                        <%--报考资格材料编码--%>
                    <td style="min-width: 60px; max-width: 60px; " >${doctor.speName}</td>
                        <%-- 培训专业 --%>
                    <td style="min-width: 60px; max-width: 60px; " >${doctor.practicingScopeName}</td>
                        <%-- 执业范围 --%>
                    <td style="min-width: 60px; max-width: 60px; " >${doctor.tempDoctorFlag}</td>
                    <c:if test="${ f eq 'Y'}">
                        <td style="min-width: 60px; max-width: 60px; " >
                            <c:choose>
                                <%-- 待省厅审核 --%>
                                <c:when test="${doctor.auditStatusId eq 'WaitGlobalPass' and (param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2')}">
                                    <a class="btn"
                                       onclick="showAllInfo('${doctor.recruitFlow}','${doctor.applyYear}','${doctor.applyFlow}','Y','${roleFlag}')"
                                       style="margin-top: 5px;margin-bottom: 5px;padding: 0 2px">审核</a>
                                </c:when>
                                <%--省厅审核通过/不通过 --%>
                                <c:when test="${doctor.auditStatusId eq 'GlobalPassed' or doctor.auditStatusId eq 'GlobalNotPassed'}">
                                    <a class="btn"
                                       onclick="showAllInfo('${doctor.recruitFlow}','${doctor.applyYear}','${doctor.applyFlow}','Y','${roleFlag}')"
                                       style="margin-top: 5px;margin-bottom: 5px;padding: 0 2px">更改</a>
                                </c:when>
                                <c:otherwise> <%-- test="${doctor.auditStatusId ne 'WaitGlobalPass'  and doctor.auditStatusId ne 'GlobalPassed'}"--%>
                                    <a class="btn"
                                       onclick="showAllInfo('${doctor.recruitFlow}','${doctor.applyYear}','${doctor.applyFlow}','N','${roleFlag}')"
                                       style="margin-top: 5px;margin-bottom: 5px;padding: 0 2px">查看</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </c:if>
                    <c:if test="${ f eq 'N'}">
                        <td style="min-width: 60px; max-width: 60px; " class="by">
                            <a class="btn"
                               onclick="showAllInfo('${doctor.recruitFlow}','${doctor.applyYear}','${doctor.applyFlow}','N','${roleFlag}')"
                               style="margin-top: 5px;margin-bottom: 5px;padding: 0 2px">查看</a>
                        </td>
                    </c:if>
                    <c:if test="${param.tabTag ne 'FristWait' and param.tabTag ne 'FristWait2'}">
                        <td style="min-width: 100px; max-width: 100px; " >
                                ${doctor.auditStatusName}
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </c:if>
    <c:if test="${empty list}">
        <table class="grid" style="width: 100%;" >
            <thead>
            <tr>
                <th>姓名</th>
                <th>地市</th>
                <th>培训基地</th>
                <th>报考专业</th>
                <th>培训起止时间</th>
                <th>学历</th>
                <th>毕业证书编号</th>
                <th>报考资格材料</th>
                <th>报考资格材料编码</th>
                <th>培训专业</th>
                <th>执业范围</th>
                <th>异常报考</th>
                <th>审核</th>
                <c:if test="${param.tabTag ne 'FristWait' or param.tabTag ne 'FristWait2'}">
                    <th>审核状态</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="99">无记录！</td>
            </tr>
            </tbody>
        </table>
    </c:if>

	</div>

    <div class="page" 	>
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
      
