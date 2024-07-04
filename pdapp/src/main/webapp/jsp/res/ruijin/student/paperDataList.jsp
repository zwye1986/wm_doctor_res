<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
	    <c:if test="${param.type eq 'Paper'}">
	    "head":[
	    	{
				"headId": "recordFlow",
				"label": "数据流水号"
			},
	    	{
				"headId": "paperDate",
				"label": "发表日期"
			},
			{
				"headId": "paperTitle",
				"label": "论文题目"
			},
			{
				"headId": "paperTypeId",
				"label": "论文类别"
			},
			{
				"headId": "paperTypeName",
				"label": "论文类别名称"
			},
			{
				"headId": "publishedJournals",
				"label": "发表刊物"
			},
			{
				"headId": "author",
				"label": "第几作者"
			}
	    ],
	    "datas": [
	        <c:forEach items="${list}" var="bean" varStatus="status">
		        {
		            "recordFlow": ${pdfn:toJsonString(bean.recordFlow)},
		            "paperDate": ${pdfn:toJsonString(bean.paperDate)},
		            "paperTitle": ${pdfn:toJsonString(bean.paperTitle)},
		            "paperTypeId": ${pdfn:toJsonString(bean.paperTypeId)},
		            "paperTypeName": ${pdfn:toJsonString(bean.paperTypeName)},
		            "publishedJournals": ${pdfn:toJsonString(bean.publishedJournals)},
		            "author": ${pdfn:toJsonString(bean.author)}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
	    <c:if test="${param.type eq 'Part'}">
	    "head":[
	    	{
				"headId": "recordFlow",
				"label": "数据流水号"
			},
	    	{
				"headId": "participationDate",
				"label": "时间"
			},
			{
				"headId": "participationRoom",
				"label": "所在实验室"
			},
			{
				"headId": "participationAuthor",
				"label": "课题负责人"
			},
			{
				"headId": "participationTitle",
				"label": "研究题目"
			},
			{
				"headId": "participationRole",
				"label": "参与角色"
			},
			{
				"headId": "participationComplete",
				"label": "完成情况"
			}
	    ],
	    "datas": [
	       <c:forEach items="${list}" var="bean" varStatus="status">
		        {
		            "recordFlow": ${pdfn:toJsonString(bean.recordFlow)},
		            "participationDate": ${pdfn:toJsonString(bean.participationDate)},
		            "participationRoom": ${pdfn:toJsonString(bean.participationRoom)},
		            "participationAuthor": ${pdfn:toJsonString(bean.participationAuthor)},
		            "participationTitle": ${pdfn:toJsonString(bean.participationTitle)},
		            "participationRole": ${pdfn:toJsonString(bean.participationRole)},
		            "participationComplete": ${pdfn:toJsonString(bean.participationComplete)}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
    </c:if>
}
