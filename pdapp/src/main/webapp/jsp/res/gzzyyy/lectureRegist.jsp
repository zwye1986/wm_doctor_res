<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "msg":"报名成功！上课时间:${year}年${month}月${day}日${hour}时${min}分,请准时参加。"
    </c:if>
}
