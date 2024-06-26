<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(function(){
        if('${operFlag}' == '${GlobalConstant.OPRE_SUCCESSED_FLAG}'){
            jboxClose();
        }
    });
 function saveInfo(){
     if(false==$("#saveForm").validationEngine("validate")){
         return;
     }
    $('#saveForm').submit();
 }
</script>

</head>

<body style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">
<div class="infoAudit" style="overflow-y: hidden;">
    <form id="saveForm" action="<s:url value='/hbres/singup/saveInfo'/>" method="post">
        <table border="0" class="base" width="945" cellspacing="0" cellpadding="0">
            <caption>补填信息</caption>
            <tr>
                <th width="18%"><span style="color: red;">*</span>民族：</th>
                <td>
                    <select name="nationId" style="width: 144px;height:22px;" class="validate[required] inputText">
                        <option/>
                        <c:forEach items="${userNationEnumList}" var="nation">
                            <option value="${nation.id}" ${sysUser.nationId eq nation.id?'selected':''}>${nation.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <th><span style="color: red;">*</span>是否应届：</th>
                <td>
                    <input type="radio" name="graduateFlag" class="validate[required]" value="N" ${extInfo.graduateFlag eq 'N'?'checked':''} />否
                    <input type="radio" name="graduateFlag" class="validate[required]" value="Y" ${extInfo.graduateFlag eq 'Y'?'checked':''} />是
                </td>
            </tr>
            <tr>
                <th><span style="color: red;">*</span>计算机能力：</th>
                <td colspan="3">
                    <input type="text" style="width:140px" name="computerSkills" value="${doctor.computerSkills}" class="validate[required]" />
                    <label>（一级、二级、三级、四级、其他）</label>
                </td>
            </tr>
            <tr>
                <th><span style="color: red;">*</span>外语能力：</th>
                <td colspan="3">
                    <input type="text" style="width:140px" name="foreignSkills" value="${doctor.foreignSkills}" class="validate[required]" />
                    <label>（未通过四级、四级、六级、八级、其他）</label>
                </td>
            </tr>
            <tr <c:if test="${doctor.doctorTypeId ne 'Company'}">style="display: none;"</c:if>>
                <th><span style="color: red;">*</span>单位级别：</th>
                <td>
                    <select name="unitLevel" style="width: 144px;height:22px;" class="validate[required] inputText">
                        <option/>
                        <c:forEach items="${dictTypeEnumOrgRankList}" var="dict">
                            <option value="${dict.dictId}" ${extInfo.unitLevel eq dict.dictId?'selected':''}>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
                <th><span style="color: red;">*</span>单位性质：</th>
                <td>
                    <select name="unitProperty" style="width: 144px;height:22px;" class="validate[required] inputText">
                        <option/>
                        <c:forEach items="${dictTypeEnumBaseAttributeList}" var="dict">
                            <option value="${dict.dictId}" ${extInfo.unitProperty eq dict.dictId?'selected':''}>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>医师资格级别：</th>
                <td>
                    <select name="docQuaLevel" style="width: 144px;height:22px;" class="inputText">
                        <option/>
                        <option value="1" ${extInfo.docQuaLevel eq '1'?'selected':''}>无</option>
                        <option value="2" ${extInfo.docQuaLevel eq '2'?'selected':''}>执业医师</option>
                        <option value="3" ${extInfo.docQuaLevel eq '3'?'selected':''}>助理执业医师</option>
                    </select>
                </td>
                <th>医师资格类别：</th>
                <td>
                    <select name="docQuaType" style="width: 144px;height:22px;" class="inputText">
                        <option/>
                        <option value="1" ${extInfo.docQuaLevel eq '1'?'selected':''}>临床</option>
                        <option value="2" ${extInfo.docQuaLevel eq '2'?'selected':''}>口腔</option>
                        <option value="3" ${extInfo.docQuaLevel eq '3'?'selected':''}>公共卫生</option>
                        <option value="4" ${extInfo.docQuaLevel eq '4'?'selected':''}>中医</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>取得执业医师资格证时间：</th>
                <td>
                    <input type="text" style="width:140px" name="qualifiedDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${doctor.qualifiedDate}"/>
                </td>
            </tr>
        </table>
    </form>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input type="button" style="width:100px;" class="btn_grey" onclick="javascript:saveInfo();" value="保&#12288;存"/>
    </div>
    </div>
</body>
</html>
