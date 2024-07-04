<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "inputs": [
    		<jsp:include page="/jsp/res/hbres/teacher/inputAfter.jsp"></jsp:include>
    ],
    "isExam":     ${pdfn:toJsonString(f)},
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
            "value": "${formDataMap['attendance']}"
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
            "inputId": "jxcb",
            "value": <c:if test="${empty formDataMap['jxcb']or (formDataMap['jxcb'] eq '0')}">
						"${dataMap['teachingRounds']}"
					</c:if>
					<c:if test="${not empty formDataMap['jxcb']  and !(formDataMap['jxcb'] eq '0')}">
						"${formDataMap['jxcb']}"
					</c:if>
        },
         {
            "inputId": "nwzbltl",
            "value":  <c:if test="${empty formDataMap['nwzbltl']or (formDataMap['nwzbltl'] eq '0')}">
						"${dataMap['difficult']}"
						</c:if>
						<c:if test="${not empty formDataMap['nwzbltl']  and !(formDataMap['nwzbltl'] eq '0')}">
							"${formDataMap['nwzbltl']}"
						</c:if>
        },
         {
            "inputId": "xsjz",
            "value":<c:if test="${empty formDataMap['xsjz']or (formDataMap['xsjz'] eq '0')}">
						"${dataMap['lecture']}"
					</c:if>
					<c:if test="${not empty formDataMap['xsjz']  and !(formDataMap['xsjz'] eq '0')}">
						"${formDataMap['xsjz']}"
					</c:if>
        },
         {
            "inputId": "swbltl",
            "value": <c:if test="${empty formDataMap['swbltl']or (formDataMap['swbltl'] eq '0')}">
						"${dataMap['death']}"
					</c:if>
					<c:if test="${not empty formDataMap['swbltl']  and !(formDataMap['swbltl'] eq '0')}">
						"${formDataMap['swbltl']}"
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
            "inputId": "teacherName",
            "value":"${formDataMap['teacherName']}"
        },
         {
            "inputId": "teacherDate",
            "value": "${formDataMap['teacherDate']}"
        }
    ]
    </c:if>
}
