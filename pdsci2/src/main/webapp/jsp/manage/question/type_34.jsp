<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="basic" style="width: 100%;height: 100%;">
    <thead>
        <tr style="background-color: #e3e3e3;">
            <th colspan="2" style="text-align: center;"> <h2>${pdfn:getQuestTypeName(param.questionTypeId)}</h2></th>
        </tr>
    </thead>
    <tbody id="">
	    <c:forEach items="${examQuestionList}" var="question" varStatus="status">
	        <tr style="background-color:#fffdf5;">
	            <td>${status.index+1}.<span>${fn:replace(question.questionContent , "manager/" , "/manager/")}</span>
					&nbsp;&nbsp;<span style="color:#666;">[最后修改时间：${question.modifyTime == null ? "" : pdfn:transDateTime(question.modifyTime)}]</span></td>
	        </tr>
			<tr style="cursor: pointer;" width="100%">
				<td>
				    
				    <textarea rows="5" cols="100" style="margin-top: 10px;"></textarea>
				    <br/>
				    <span style="color: green;">正确答案：${fn:replace(question.rightAnswer , "manager/" , "/manager/")}</span>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	<c:if test="${examQuestionList == null || examQuestionList.size()==0 }">
	    <tr>
	        <td align="center" style="text-align: center;" colspan="2">无记录</td>
	    </tr>
	</c:if>
</table>