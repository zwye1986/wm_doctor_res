<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="<%--<s:url value='/zseyjxres/doctor/index'/>--%>">${sysCfgMap['jx_top_name']}</a>
     </h1>
     <c:if test="${not param.notShowAccount}">
     <div class="account">
       <h2 style="text-align: right;">您好，${not empty sessionScope.currUser.userName?sessionScope.currUser.userName:sessionScope.currUser.userCode }</h2>
       <div class="head_right">
        <a href="<s:url value='/zseyjxres/head/home'/>">首页</a>&#12288;
        <c:if test='${param.notice}'>
           <a onclick="msgToPage();">公告</a>&#12288;
        </c:if>

         <a href="<s:url value='/inx/zseyjxres/logout?flag='/>${sysCfgMap['jx_templete_name']}">退出</a>
       </div>
     </div>
     </c:if>
   </div>
 </div>