<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/detail.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">
    function selectAll() {
        if($("#checkAll").is(':checked')){
            $(".check").prop("checked",true);
        }else{
            $(".check").prop("checked",false);
        }
    }

    function createCertificate(recruitFlow,tabTag) {
        if(!recruitFlow) {
            jboxTip("请选择需要生成证书的人员信息！");
            return false;
        }
        jboxConfirm("确认生成证书？" , function(){
            jboxPost("<s:url value='/jsres/certificateManage/createCertificate'/>?recruitFlow="+recruitFlow+"&tabTag="+tabTag, null,
                function(resp){
                    jboxTip(resp);
                    if(resp=="生成成功"){
                        toPage(1);
                    }else if(resp=="无法生成"){
                        jboxTip("基地未设置院长签名，无法生成！");
                        toPage(1);
					}
                },null,false);
        });
    }
</script>

	<div class="main_bd clearfix">
		<table class="grid" style="width: 100%;" id="dataTable">
			<thead>
            <tr>
				<th><input type="checkbox" id="checkAll" onchange="selectAll()"/>全选</th>
				<th style="min-width: 85px; max-width: 85px; " >操作</th>
				<th style="min-width: 60px; max-width: 60px; " >姓名</th>
				<th style="min-width: 150px; max-width: 150px; " >证件号码</th>
				<th style="min-width: 60px; max-width: 60px; " >年级</th>
				<th style="min-width: 60px; max-width: 60px; " >地市</th>
				<th style="min-width: 60px; max-width: 60px; " >培训类别</th>
				<th style="min-width: 150px; max-width: 150px; " >培训基地</th>
                <th style="min-width: 60px; max-width: 60px;" >培训专业</th>
            </tr>
			</thead>
			<tbody>
			<c:if test="${not empty doctorList}">
				 <c:forEach items="${doctorList}" var="doctor">
					 <tr class="fixTrTd">
						 <td>
							 <input recruitFlow="${doctor.recruitFlow}" type="checkbox" class="check"/>
						 </td>
						 <td>
							<a class="btn" onclick="createCertificate('${doctor.recruitFlow}','${param.tabTag}');" >生成证书</a>
						 </td>
						 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden" title="${doctor.sysUser.userName}">${doctor.sysUser.userName}</td>
						 <td style="min-width: 150px; max-width: 150px;" class="by text_hidden">${doctor.sysUser.idNo}</td>
						 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden">${doctor.sessionNumber}</td>
						 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden">${doctor.orgCityName}</td>
						 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden">${doctor.catSpeName}</td>
						 <td style="min-width: 150px; max-width: 150px;" class="by text_hidden" title="${doctor.orgName}">${doctor.orgName}</td>
						 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden" title="${doctor.speName}">${doctor.speName}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty doctorList}">
				<tr>
					<td colspan="16" >无记录！</td>
				</tr>
			</c:if>
			</tbody>
        </table>
    </div>
    <div class="page">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
