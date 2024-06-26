<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
 <html>
 <head>
     <%@include file="/jsp/common/html.jsp" %>
 <title>专家反馈意见</title>
 <style type="text/css">
 .th_group .left{
  text-align: left;
}
.th_group .right{
  text-align: right;
}
.th_group th {
  background-color: rgba(0,0,0,0.07);
  border: 1px solid rgba(0,0,0,0.07);
  text-align: center;
  vertical-align:middle;
}
.td_group td {
  border: 1px solid rgba(0,0,0,0.07);
  vertical-align:middle;
}
.td_group th {
  text-align: center;
  vertical-align:middle;
}
.table-inner{
  width:100%;
  border-collapse: collapse;
  border: 1px solid rgba(0,0,0,0.07);
  padding:0;
  border-top: hidden;
  border-left: hidden;
  border-right: hidden;
  border-bottom: hidden;
}
.inputText{
  border:0; 
  border-bottom:1px solid #ccc;
  text-align: left;
}
body {
  width:100%;
  overflow:auto;
}
</style>
</head>
<body >
  <div data-role="page" id="pageone">
    <div data-role="header">
     <h1>江苏省住院医师规范化培训<br/>基地专项评估《专家反馈意见》</h1>
   </div>
   <div data-role="main" class="ui-content">
    <div style="margin-bottom: 20px;border:1px solid rgba(0,0,0,0.07);overflow:auto;">
      <form id="noteInfoForm">
        <table data-role="table" data-mode=""  class="ui-responsive table-stroke">
          <tr class="td_group">
            <td class="left"  colspan="2"style="vertical-align:middle;"><b>管理方面：</b></td>
          </tr>
          <tr class="td_group">
            <td class="left" colspan="2">
              <textarea style="height: 200px;resize: none;overflow:auto;" name="administration" >${formDataMap['administration']}</textarea>
            </td>
          </tr>
          <tr class="td_group">
            <td class="left" colspan="2" style="vertical-align:middle;"><b>亮点：</b></td>
          </tr>
          <tr class="td_group">
            <td class="left" colspan="2">
              <textarea style="height: 200px;resize: none;overflow:auto;" name="highlights">${formDataMap['highlights']}</textarea>
            </td>
          </tr>
          <tr class="td_group">
            <td class="left"  colspan="2" style="vertical-align:middle;"><b>不足：</b></td>
          </tr>
          <tr class="td_group">
              <td class="left" colspan="2">
              <textarea style="height: 200px;resize: none;overflow:auto;" name="insufficiency">${formDataMap['insufficiency']}</textarea>
            </td>
          </tr>
          <tr class="td_group">
            <td class="left"  colspan="2" style="vertical-align:middle;"><b>建议：</b></td>
          </tr>
          <tr class="td_group">
            <td class="left" colspan="2">
              <textarea style="height: 200px;resize: none;overflow:auto;" name="opinion">${formDataMap['opinion']}</textarea>
            </td>
          </tr>
          <tr class="td_group">
            <td class="left">
                专家姓名：<input class="inputText" type="text" name="siginName" value="${formDataMap['siginName']}"readonly  data-role="none"/>
            </td>
            <td class="right">
                评估日期：<input class="inputText" type="text" id="siginDate" name="siginDate" value="${formDataMap['siginDate']}" readonly data-role="none"/>
            </td>
          </tr>
        </table>
      </form>
    </div>

       <script>
           $(document).ready(function(){
               $(".left").css("text-align","left");
               $(".right").css("text-align","right");
               $("input").attr("readonly","readonly");
               $("textarea").attr("disabled","disabled");
               $("textarea").css("height","200px");
           });
       </script>
  </div>

 <div data-role="footer">
   <h1></h1>
 </div>
</div> 
</body>
</html>