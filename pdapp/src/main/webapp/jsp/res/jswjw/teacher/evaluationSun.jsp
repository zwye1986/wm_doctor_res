<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
        "checkProcess":
        <c:if test="${empty checkProcess}">
            "0",
        </c:if>
        <c:if test="${not empty checkProcess}">
            ${pdfn:toJsonString(checkProcess)},
        </c:if>
        <c:set value="${(roleFlag=='teacher' && empty rec.auditStatusId or (roleFlag=='teacher' and (cksh ne 'Y' or empty rec.managerAuditUserFlow)))
		  || (roleFlag=='Head' or roleFlag == 'Seretary') and (cksh eq 'Y' or not empty rec.managerAuditUserFlow) }" var="showEdit"></c:set>
        <c:set value="false" var="showSave"></c:set>
        <c:if test="${empty rec}">
            <c:if test="${roleFlag eq 'teacher'}">
                <c:set value="true" var="showSave"></c:set>
            </c:if>
        </c:if>
        <c:if test="${not empty rec}">
            <c:if test="${roleFlag eq 'teacher' and empty rec.managerAuditUserFlow  }">
                <c:set value="true" var="showSave"></c:set>
            </c:if>
            <c:if test="${roleFlag eq 'Head' and not empty rec.managerAuditUserFlow }">
                <c:set value="true" var="showSave"></c:set>
            </c:if>
            <c:if test="${roleFlag eq 'Seretary' and not empty rec.managerAuditUserFlow }">
                <c:set value="true" var="showSave"></c:set>
            </c:if>
        </c:if>

        <c:set var="classVal" value="validate[required]"></c:set>
        <c:set var="readonly" value="false"></c:set>
        <c:if test="${empty rec}">
            <c:if test="${roleFlag eq 'teacher' and cksh eq 'Y'}">
                <c:set var="classVal" value=""></c:set>
                <c:set var="readonly" value="true"></c:set>
            </c:if>
        </c:if>
        <c:if test="${not empty rec}">
            <c:if test="${roleFlag eq 'teacher' and not empty rec.managerAuditUserFlow }">
                <c:set var="classVal" value=""></c:set>
                <c:set var="readonly" value="true"></c:set>
            </c:if>
        </c:if>
    <c:if test="${showSave}">
        "action":{
            "save":"保存"
        },
    </c:if>
    "inputs": [
    		<jsp:include page="/jsp/res/jswjw/teacher/inputAfter.jsp"></jsp:include>
    ],
    "subEvaluation":     ${pdfn:toJsonString(subEvaluation)},
    "isExam":     ${pdfn:toJsonString(f)},
    "showEdit":     ${showEdit},
    "readonly":     ${readonly},
    "values": [
        {
            "inputId": "formFileName",
            "value": ${pdfn:toJsonString(formFileName)}
        },
        {
            "inputId": "deptFlow",
            "value": "${param.deptFlow}"
        },
         {
            "inputId": "operUserFlow",
            "value": "${param.docFlow}"
        },
         {
            "inputId": "roleFlag",
            "value": "${roleFlag}"
        },
         {
            "inputId": "recFlow",
			"value": "${rec.recFlow}"
        },
         {
            "inputId": "processFlow",
            "value": "${processFlow}"
        },
         {
            "inputId": "recordFlow",
            "value": "${param.recordFlow}"
        },
         {
            "inputId": "cksh",
            "value": "${cksh}"
        },
         {
            "inputId": "auditStatusId",
            "value": "TeacherAuditY"
        },
         {
            "inputId": "headAuditStatusId",
            "value": ""
        },
         {
            "inputId": "name",
            "value": <c:if test="${empty formDataMap['name']}">
						"${doctor.doctorName}"
					</c:if>
					<c:if test="${not empty formDataMap['name']}">
						"${formDataMap['name']}"
					</c:if>
        },
         {
            "inputId": "sessional",
            "value": <c:if test="${empty formDataMap['sessional']}">
						"${doctor.sessionNumber}"
					</c:if>
					<c:if test="${not empty formDataMap['sessional']}">
						"${formDataMap['sessional']}"
					</c:if>
        },
         {
            "inputId": "trainMajor",
            "value": <c:if test="${empty formDataMap['trainMajor']}">
						"${doctor.trainingSpeName}"
					</c:if>
					<c:if test="${not empty formDataMap['trainMajor']}">
						"${formDataMap['trainMajor']}"
					</c:if>
        },
         {
            "inputId": "deptName",
            "value": <c:if test="${empty formDataMap['deptName']}">
						"${resDoctorSchProcess.schDeptName}"
					</c:if>
					<c:if test="${not empty formDataMap['deptName']}">
						"${formDataMap['deptName']}"
					</c:if>
        },
         {
            "inputId": "cycleTimeQ",
            "value": <c:if test="${empty formDataMap['cycleTimeQ']}">
						"${resDoctorSchProcess.schStartDate}"
					</c:if>
					<c:if test="${not empty formDataMap['cycleTimeQ']}">
						"${formDataMap['cycleTimeQ']}"
					</c:if>
        },
         {
            "inputId": "cycleTimeH",
            "value":<c:if test="${empty formDataMap['cycleTimeH']}">
						"${resDoctorSchProcess.schEndDate}"
					</c:if>
					<c:if test="${not empty formDataMap['cycleTimeH']}">
						"${formDataMap['cycleTimeH']}"
					</c:if>
        },
         {
            "inputId": "attendance",
            "value": "${empty formDataMap['attendance']?attendance:formDataMap['attendance']}"
        },
         {
            "inputId": "leave",
            "value": "${formDataMap['leave']}"
        },
         {
            "inputId": "sickLeave",
            "value": "${formDataMap['sickLeave']}"
        },
         {
            "inputId": "materLeave",
            "value": "${formDataMap['materLeave']}"
        },
         {
            "inputId": "absenteeism",
            "value": "${formDataMap['absenteeism']}"
        },
         {
            "inputId": "blsywc",
            "value": "${dataMap['caseRegistryReqNum']}"
        },
         {
            "inputId": "blsyjwc",
            "value": "${dataMap['caseRegistryFinished']}"
        },
         {
            "inputId": "blswcbl",
            "value": "${dataMap['caseRegistry']}"
        },
         {
            "inputId": "bzsywc",
            "value": "${dataMap['diseaseRegistryReqNum']}"
        },
         {
            "inputId": "bzsyjwc",
            "value": "${dataMap['diseaseRegistryFinished']}"
        },
         {
            "inputId": "bzswcbl",
            "value": "${dataMap['diseaseRegistry']}"
        },
         {
            "inputId": "czsywc",
            "value": "${dataMap['skillRegistryReqNum']}"
        },
         {
            "inputId": "czsyjwc",
            "value": "${dataMap['skillRegistryFinished']}"
        },
         {
            "inputId": "czswcbl",
            "value": "${dataMap['skillRegistry']}"
        },
         {
            "inputId": "sssywc",
            "value": "${dataMap['operationRegistryReqNum']}"
        },
         {
            "inputId": "sssyjwc",
            "value": "${dataMap['operationRegistryFinished']}"
        },
         {
            "inputId": "ssswcbl",
            "value": "${dataMap['operationRegistry']}"
        },
         {
            "inputId": "zsgjflfg_name",
            "value": "${formDataMap['zsgjflfg_name']}"
        },
         {
            "inputId": "zsgjflfg",
            "value": "${formDataMap['zsgjflfg_id']}"
        },
         {
            "inputId": "lxgwzz_name",
            "value": "${formDataMap['lxgwzz_name']}"
        },
        {
            "inputId": "lxgwzz",
            "value": "${formDataMap['lxgwzz_id']}"
        },
         {
            "inputId": "ybrwzx_name",
            "value": "${formDataMap['ybrwzx_name']}"
        },
         {
            "inputId": "ybrwzx",
            "value": "${formDataMap['ybrwzx_id']}"
        },
         {
            "inputId": "rjgtbdnl_name",
            "value": "${formDataMap['rjgtbdnl_name']}"
        },
         {
            "inputId": "rjgtbdnl",
            "value": "${formDataMap['rjgtbdnl_id']}"
        },
         {
            "inputId": "tjxzjsxm_name",
            "value": "${formDataMap['tjxzjsxm_name']}"
        },
         {
            "inputId": "tjxzjsxm",
            "value": "${formDataMap['tjxzjsxm_id']}"
        },
         {
            "inputId": "jbllzwcd_name",
            "value": "${formDataMap['jbllzwcd_name']}"
        },
         {
            "inputId": "jbllzwcd",
            "value": "${formDataMap['jbllzwcd_id']}"
        },
         {
            "inputId": "njbjnzwcd_name",
            "value": "${formDataMap['njbjnzwcd_name']}"
        },
         {
            "inputId": "njbjnzwcd",
            "value": "${formDataMap['njbjnzwcd_id']}"
        },
         {
            "inputId": "lcswnl_name",
            "value": "${formDataMap['lcswnl_name']}"
        },
         {
            "inputId": "lcswnl",
            "value": "${formDataMap['lcswnl_id']}"
        },
         {
            "inputId": "lczlnl_name",
            "value": "${formDataMap['lczlnl_name']}"
        },
         {
            "inputId": "lczlnl",
            "value": "${formDataMap['lczlnl_id']}"
        },
         {
            "inputId": "jjclnl_name",
            "value": "${formDataMap['jjclnl_name']}"
        },
         {
            "inputId": "jjclnl",
            "value": "${formDataMap['jjclnl_id']}"
        },
         {
            "inputId": "jxcf",
            "value": <c:if test="${empty formDataMap['jxcf']or (formDataMap['jxcf'] eq '0')}">
						"${dataMap['jxcf']}"
					</c:if>
					<c:if test="${not empty formDataMap['jxcf']  and !(formDataMap['jxcf'] eq '0')}">
						"${formDataMap['jxcf']}"
					</c:if>
        },
         {
            "inputId": "jxbltl",
            "value":  <c:if test="${empty formDataMap['jxbltl']or (formDataMap['jxbltl'] eq '0')}">
						"${dataMap['jxbltl']}"
						</c:if>
						<c:if test="${not empty formDataMap['jxbltl']  and !(formDataMap['jxbltl'] eq '0')}">
							"${formDataMap['jxbltl']}"
						</c:if>
        } ,
        {
            "inputId": "ynbltl",
            "value":  <c:if test="${empty formDataMap['ynbltl']or (formDataMap['ynbltl'] eq '0')}">
                        "${dataMap['ynbltl']}"
                        </c:if>
                        <c:if test="${not empty formDataMap['ynbltl']  and !(formDataMap['ynbltl'] eq '0')}">
                            "${formDataMap['ynbltl']}"
                        </c:if>
        } ,
        {
            "inputId": "wzbltl",
            "value":  <c:if test="${empty formDataMap['wzbltl']or (formDataMap['wzbltl'] eq '0')}">
                        "${dataMap['wzbltl']}"
                        </c:if>
                        <c:if test="${not empty formDataMap['wzbltl']  and !(formDataMap['wzbltl'] eq '0')}">
                            "${formDataMap['wzbltl']}"
                        </c:if>
        } ,
        {
            "inputId": "swbltl",
            "value":  <c:if test="${empty formDataMap['swbltl']or (formDataMap['swbltl'] eq '0')}">
                        "${dataMap['swbltl']}"
                        </c:if>
                <c:if test="${not empty formDataMap['swbltl']  and !(formDataMap['swbltl'] eq '0')}">
                    "${formDataMap['swbltl']}"
                </c:if>
        },
<%--         {--%>
<%--            "inputId": "wzbltl",--%>
<%--            "value":<c:if test="${empty formDataMap['wzbltl']or (formDataMap['wzbltl'] eq '0')}">--%>
<%--						"${dataMap['wzbltl']}"--%>
<%--					</c:if>--%>
<%--					<c:if test="${not empty formDataMap['wzbltl']  and !(formDataMap['wzbltl'] eq '0')}">--%>
<%--						"${formDataMap['wzbltl']}"--%>
<%--					</c:if>--%>
<%--        },--%>
         {
            "inputId": "xjk",
            "value": <c:if test="${empty formDataMap['xjk']or (formDataMap['xjk'] eq '0')}">
						"${dataMap['xjk']}"
					</c:if>
					<c:if test="${not empty formDataMap['xjk']  and !(formDataMap['xjk'] eq '0')}">
						"${formDataMap['xjk']}"
					</c:if>
        },
<%--        {--%>
<%--        "inputId": "swbltl",--%>
<%--        "value": <c:if test="${empty formDataMap['swbltl']or (formDataMap['swbltl'] eq '0')}">--%>
<%--        "${dataMap['swbltl']}"--%>
<%--        </c:if>--%>
<%--        <c:if test="${not empty formDataMap['swbltl']  and !(formDataMap['swbltl'] eq '0')}">--%>
<%--            "${formDataMap['swbltl']}"--%>
<%--        </c:if>--%>
<%--        },--%>
        {
        "inputId": "rkjy",
        "value": <c:if test="${empty formDataMap['rkjy']or (formDataMap['rkjy'] eq '0')}">
        "${dataMap['rkjy']}"
        </c:if>
        <c:if test="${not empty formDataMap['rkjy']  and !(formDataMap['rkjy'] eq '0')}">
            "${formDataMap['rkjy']}"
        </c:if>
        },
        {
        "inputId": "ckkh",
        "value": <c:if test="${empty formDataMap['ckkh']or (formDataMap['ckkh'] eq '0')}">
        "${dataMap['ckkh']}"
        </c:if>
        <c:if test="${not empty formDataMap['ckkh']  and !(formDataMap['ckkh'] eq '0')}">
            "${formDataMap['ckkh']}"
        </c:if>
        },
        {
        "inputId": "jnpx",
        "value": <c:if test="${empty formDataMap['jnpx']or (formDataMap['jnpx'] eq '0')}">
        "${dataMap['jnpx']}"
        </c:if>
        <c:if test="${not empty formDataMap['jnpx']  and !(formDataMap['jnpx'] eq '0')}">
            "${formDataMap['jnpx']}"
        </c:if>
        },
        {
        "inputId": "yph",
        "value": <c:if test="${empty formDataMap['yph']or (formDataMap['yph'] eq '0')}">
        "${dataMap['yph']}"
        </c:if>
        <c:if test="${not empty formDataMap['yph']  and !(formDataMap['yph'] eq '0')}">
            "${formDataMap['yph']}"
        </c:if>
        },
        {
        "inputId": "jxhz",
        "value": <c:if test="${empty formDataMap['jxhz']or (formDataMap['jxhz'] eq '0')}">
        "${dataMap['jxhz']}"
        </c:if>
        <c:if test="${not empty formDataMap['jxhz']  and !(formDataMap['jxhz'] eq '0')}">
            "${formDataMap['jxhz']}"
        </c:if>
        },
<%--        {--%>
<%--        "inputId": "jxbltl",--%>
<%--        "value": <c:if test="${empty formDataMap['jxbltl']or (formDataMap['jxbltl'] eq '0')}">--%>
<%--        "${dataMap['jxbltl']}"--%>
<%--        </c:if>--%>
<%--        <c:if test="${not empty formDataMap['jxbltl']  and !(formDataMap['jxbltl'] eq '0')}">--%>
<%--            "${formDataMap['jxbltl']}"--%>
<%--        </c:if>--%>
<%--        },--%>
        {
        "inputId": "lcczjnzd",
        "value": <c:if test="${empty formDataMap['lcczjnzd']or (formDataMap['lcczjnzd'] eq '0')}">
        "${dataMap['lcczjnzd']}"
        </c:if>
        <c:if test="${not empty formDataMap['lcczjnzd']  and !(formDataMap['lcczjnzd'] eq '0')}">
            "${formDataMap['lcczjnzd']}"
        </c:if>
        },
        {
        "inputId": "lcblsxzd",
        "value": <c:if test="${empty formDataMap['lcblsxzd']or (formDataMap['lcblsxzd'] eq '0')}">
        "${dataMap['lcblsxzd']}"
        </c:if>
        <c:if test="${not empty formDataMap['lcblsxzd']  and !(formDataMap['lcblsxzd'] eq '0')}">
            "${formDataMap['lcblsxzd']}"
        </c:if>
        },
        {
        "inputId": "ssczzd",
        "value": <c:if test="${empty formDataMap['ssczzd']or (formDataMap['ssczzd'] eq '0')}">
        "${dataMap['ssczzd']}"
        </c:if>
        <c:if test="${not empty formDataMap['ssczzd']  and !(formDataMap['ssczzd'] eq '0')}">
            "${formDataMap['ssczzd']}"
        </c:if>
        },
        {
        "inputId": "yxzdbgsxzd",
        "value": <c:if test="${empty formDataMap['yxzdbgsxzd']or (formDataMap['yxzdbgsxzd'] eq '0')}">
        "${dataMap['yxzdbgsxzd']}"
        </c:if>
        <c:if test="${not empty formDataMap['yxzdbgsxzd']  and !(formDataMap['yxzdbgsxzd'] eq '0')}">
            "${formDataMap['yxzdbgsxzd']}"
        </c:if>
        },
        {
        "inputId": "lcwxyd",
        "value": <c:if test="${empty formDataMap['lcwxyd']or (formDataMap['lcwxyd'] eq '0')}">
        "${dataMap['lcwxyd']}"
        </c:if>
        <c:if test="${not empty formDataMap['lcwxyd']  and !(formDataMap['lcwxyd'] eq '0')}">
            "${formDataMap['lcwxyd']}"
        </c:if>
        },
        {
        "inputId": "ryjy",
        "value": <c:if test="${empty formDataMap['ryjy']or (formDataMap['ryjy'] eq '0')}">
        "${dataMap['ryjy']}"
        </c:if>
        <c:if test="${not empty formDataMap['ryjy']  and !(formDataMap['ryjy'] eq '0')}">
            "${formDataMap['ryjy']}"
        </c:if>
        },
        {
        "inputId": "rzyjdjy",
        "value": <c:if test="${empty formDataMap['rzyjdjy']or (formDataMap['rzyjdjy'] eq '0')}">
        "${dataMap['rzyjdjy']}"
        </c:if>
        <c:if test="${not empty formDataMap['rzyjdjy']  and !(formDataMap['rzyjdjy'] eq '0')}">
            "${formDataMap['rzyjdjy']}"
        </c:if>
        },
        {
        "inputId": "cjbg",
        "value": <c:if test="${empty formDataMap['cjbg']or (formDataMap['cjbg'] eq '0')}">
        "${dataMap['cjbg']}"
        </c:if>
        <c:if test="${not empty formDataMap['cjbg']  and !(formDataMap['cjbg'] eq '0')}">
            "${formDataMap['cjbg']}"
        </c:if>
        },
        {
        "inputId": "bgdfx",
        "value": <c:if test="${empty formDataMap['bgdfx']or (formDataMap['bgdfx'] eq '0')}">
        "${dataMap['bgdfx']}"
        </c:if>
        <c:if test="${not empty formDataMap['bgdfx']  and !(formDataMap['bgdfx'] eq '0')}">
            "${formDataMap['bgdfx']}"
        </c:if>
        },
        {
        "inputId": "jxsj",
        "value": <c:if test="${empty formDataMap['jxsj']or (formDataMap['jxsj'] eq '0')}">
        "${dataMap['jxsj']}"
        </c:if>
        <c:if test="${not empty formDataMap['jxsj']  and !(formDataMap['jxsj'] eq '0')}">
            "${formDataMap['jxsj']}"
        </c:if>
        },
        {
        "inputId": "sjys",
        "value": <c:if test="${empty formDataMap['sjys']or (formDataMap['sjys'] eq '0')}">
        "${dataMap['sjys']}"
        </c:if>
        <c:if test="${not empty formDataMap['sjys']  and !(formDataMap['sjys'] eq '0')}">
            "${formDataMap['sjys']}"
        </c:if>
        },
         {
            "inputId": "theoreResult",
            <c:set var="score" value="${formDataMap['theoreResult']}"></c:set>
            <c:if test="${f and (empty formDataMap['theoreResult'])}">
                <c:set var="score" value="${outScore.theoryScore}"></c:set>
            </c:if>
            "value": "${score}"
        },
         {
            "inputId": "skillName",
            "value": "${formDataMap['skillName']}"
        },
         {
            "inputId": "score",
            "value": "${formDataMap['score']}"
        },
         {
            "inputId": "examiner1",
            "value": "${formDataMap['examiner1']}"
        },
         {
            "inputId": "examiner2",
            "value": "${formDataMap['examiner2']}"
        },
         {
            "inputId": "szkskhxzztpj_name",
            "value": "${formDataMap['szkskhxzztpj_name']}"
        },
         {
            "inputId": "szkskhxzztpj",
            "value": "${formDataMap['szkskhxzztpj_id']}"
        },
        {
            "inputId": "teacherComment",
            "value": "${rec.teacherComment}"
        },
         {
            "inputId": "teacherName",
            "value":<c:if test="${empty rec.auditStatusId}">
						"${currUser.userName}"
					</c:if>
					<c:if test="${not empty rec.auditStatusId}">
						"${formDataMap['teacherName']}"
					</c:if>
        },
         {
            "inputId": "teacherDate",
            "value": "${empty formDataMap['teacherDate'] ? pdfn:getCurrDate():formDataMap['teacherDate']}"
        },
         {
            "inputId": "directorName",
            "value":"${formDataMap['directorName']}"
        },
         {
            "inputId": "directorDate",
            "value": "${ formDataMap['directorDate']  }"
        },
        <%--{--%>
            <%--"inputId": "theoreticalCfg",--%>
            <%--"value": "${empty theoreticalCfg ? 'N' : theoreticalCfg}"--%>
        <%--},--%>
        {
            "inputId": "theoryScorePass",
            "value": "${empty theoryScorePass ? '' : theoryScorePass}"
        },
        {
            "inputId": "teacherWrite",
            "value": "${empty teacherWrite ? 'N' : teacherWrite}"
        },
        {
            "inputId": "passScore",
            "value": "${not empty testPaper && not empty testPaper.passScore  ? testPaper.passScore : ''}"
        }
    ]
    </c:if>
}
