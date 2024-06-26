<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        .edit3{text-align: center;border:none;}
        th{width: 20%}
        textarea{width: 98%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <script type="text/javascript">
        function save(){
            if(!$("#addForm").validationEngine("validate")){
                return ;
            }
            var trs = $(".itemTd .addTr");
            var checkedDepts = $('input:checkbox:checked');
            jboxConfirm("确认保存？" , function(){

                var depts =[];
                $.each(checkedDepts, function(i,n){
                    var deptFlow = $(n).val();
                    depts.push(deptFlow);
                });
                var cfgFlow = $("input[name='cfgFlow']").val();
                var requestData = { "cfgFlow":cfgFlow,"depts":depts};
                var url = "<s:url value='/res/evaluation/saveEvaluationDept'/>";
//                $("#saveButton").attr("disabled",true);

                jboxPostJson(
                        url,
                        JSON.stringify(requestData),
                        function(resp){
                            if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
                                window.parent.frames['mainIframe'].search();
                                jboxClose();
                            }
                        }, null, true);
            });
        }
        function doClose(){
            jboxClose();
        }
        function change(deptFlow,cfgFlow,obj){
            var isChecked="N";
            var checkedVal = $(obj).attr("checked");
            if ("checked" == checkedVal) {
                isChecked="Y";
            }
            var url = "<s:url value='/res/evaluation/checkHaveEval'/>?deptFlow="+deptFlow+"&cfgFlow="+cfgFlow;
            jboxPost(
                url,
                null,
                function(resp){
                   if(resp=="N")
                   {
                       jboxTip("此科室已有评分数据，无法取消关联！");
                       if(isChecked=="N")
                       {
                           $(obj).attr("checked","checked");
                       }else{
                           $(obj).removeAttr("checked");
                       }
                       return ;
                   }
               }, null, false);
        }
    </script>
</head>
<%--<body>
<div class="mainright">
    <div class="content">
        <form id="addForm">
            <input type="hidden" name="cfgFlow" value="${evaluation.cfgFlow}">
            <input type="hidden" name="id" id="titleId"/>
            <table class="basic" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th  style="padding-right: 0px;text-align: right;"><font color="red">*</font>&#12288;双向评价表单编号：</th>
                    <td  >
                        <input class="validate[required] xltext" readonly name="cfgCodeId" type="text" value="${evaluation.cfgCodeId}"/>
                    </td>
                    <th  style="padding-right: 0px;text-align: right;"><font color="red">*</font>&#12288;双向评价表单名称：</th>
                    <td  >
                        <input class="validate[required] xltext" readonly name="cfgCodeName" type="text" value="${evaluation.cfgCodeName}"/>
                    </td>
                </tr>
                  <tr>
                      <th style="padding-right: 0px;text-align: right;"><font color="red">*</font>&#12288;科室列表：</th>
                      <td colspan="3">
                          <div class="div_search" style="padding: 10px;margin: 10px;float:left;overflow:auto;height: auto;max-height: 400px;width: 95%;">
                              <c:forEach items="${depts}" var="dept">
                                  <div style="width: 24%;float: left;" deptName="${dept.deptName}">
                                      <table cellpadding="0" cellspacing="0" class="basic" style="width: 100%;border: 0px;">
                                          <tr>
                                              <td style="width: 100%;border: 0px;"  cellpadding="0" cellspacing="0">

                                                      <input type="checkbox" id="${dept.deptFlow}" class="validate[required]" name="dept"
                                                             <c:if test="${ dept.checked eq 'Y'}">checked</c:if>  value="${dept.deptFlow}"/>
                                                  <label for="${dept.deptFlow}">
                                                           ${dept.deptName}
                                                  </label>
                                              </td>
                                          </tr>
                                      </table>
                                  </div>
                              </c:forEach>
                          </div>
                      </td>
                  </tr>
            </table>
        </form>

        <div style="text-align: center;margin-top: 30px">
            <input type="button" onclick="save();"  id="saveButton" class="search" value="保&#12288;存"/>
            <input type="button" onclick="doClose();" class="search" value="关&#12288;闭"/>
        </div>
    </div>
</div>

</body>
</html>--%>
<body>
<div class="mainright">
    <div class="content">
        <form id="addForm">
            <input type="hidden" name="cfgFlow" value="${evaluation.cfgFlow}">
            <input type="hidden" name="id" id="titleId"/>
            <table class="basic" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th  style="padding-right: 0px;text-align: right;"><font color="red">*</font>&#12288;双向评价表单编号：</th>
                    <td  >
                        <input class="validate[required] xltext" readonly name="cfgCodeId" type="text" value="${evaluation.cfgCodeId}"/>
                    </td>
                    <th  style="padding-right: 0px;text-align: right;"><font color="red">*</font>&#12288;双向评价表单名称：</th>
                    <td  >
                        <input class="validate[required] xltext" readonly name="cfgCodeName" type="text" value="${evaluation.cfgCodeName}"/>
                    </td>
                </tr>
                <tr>
                    <th style="padding-right: 0px;text-align: right;"><font color="red">*</font>&#12288;科室列表：</th>
                    <td colspan="3">
                        <div class="div_search" style="padding: 10px;margin: 10px;float:left;overflow:auto;height: auto;max-height: 400px;width: 95%;">
                            <c:forEach items="${depts}" var="dept">
                                <div style="width: 24%;float: left;" deptName="${dept.deptName}">
                                    <table cellpadding="0" cellspacing="0" class="basic" style="width: 100%;border: 0px;">
                                        <tr>
                                            <td style="width: 100%;border: 0px;"  cellpadding="0" cellspacing="0">

                                                <input type="checkbox" id="${dept.deptFlow}" class="validate[required]" name="dept"
                                                       <c:if test="${ dept.checked eq 'Y'}">checked</c:if>  value="${dept.deptFlow}" onclick="change('${dept.deptFlow}','${evaluation.cfgFlow}',this)"/>
                                                <label for="${dept.deptFlow}">
                                                        ${dept.deptName}
                                                </label>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </c:forEach>
                        </div>
                    </td>
                </tr>
            </table>
           <%-- <div style="text-align:center;font-weight: bold;font-size: 16px;color: red">
                ${msg}
            </div>--%>
        </form>

        <div style="text-align: center;margin-top: 30px">
            <input type="button" onclick="save();"  id="saveButton" class="search" value="保&#12288;存"/>
            <input type="button" onclick="doClose();" class="search" value="关&#12288;闭"/>
        </div>
    </div>
</div>

</body>
</html>
