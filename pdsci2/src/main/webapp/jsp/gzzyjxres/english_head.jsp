<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="<s:url value='/gzzyjxres/doctor/index_en'/>">${sysCfgMap['jx_top_name_en']}</a>
     </h1>
     <c:if test="${not param.notShowAccount}">
     <div class="account">
       <h2 style="text-align: right;">Hello，${!empty sessionScope.currUser.userName?sessionScope.currUser.userName:sessionScope.currUser.userCode }</h2>
       <div class="head_right">
        <a href="<s:url value='/gzzyjxres/doctor/index_en'/>"> Homepage</a>&#12288;
        <c:if test='${param.notice}'>
           <a onclick="msgToPage();">Notice</a>&#12288;
        </c:if>

         <a href="<s:url value='/inx/gzzyjxrecruit/logout?flag='/>${sysCfgMap['jx_templete_name']}">Logout</a>
       </div>
     </div>
     </c:if>
   </div>
 </div>