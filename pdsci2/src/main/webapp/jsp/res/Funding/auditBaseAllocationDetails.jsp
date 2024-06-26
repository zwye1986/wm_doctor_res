<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <script type="text/javascript">

        function reChoose(obj){
            $(obj).hide();
            $("#isRe").val("Y");
            $("#down").hide();
            $("#file").show();
        }

        function auditBase(recordFlow,state)
        {
            var msg="";
            if(state=="1")
            {
                msg="确定通过该条记录？";
            }else{
                msg="确定不通过该条记录？";
            }
            jboxConfirm(msg,function(){
                var url = "<s:url value='/res/resFunds/auditBaseAllocationDetails'/>?recordFlow=${resBaseFunds.recordFlow}&state="+state;
                jboxStartLoading();
                jboxPost(url,null,function(resp){
                    jboxEndLoading();
                    if(resp=='1') {
                        window.parent.search();
                        setTimeout(function(){
                            jboxClose();
                        },100);
                    }
                },null,false);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="basic" style="max-height: 392px;">
        <form id="addForm" style="position: relative;">
            <input name="resBaseinputs" id="resBaseinputs" type="hidden" />
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 30px;">

                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>培训基地：</th>
                    <td >
                        <input name="orgName" id="orgName"  class="input validate[required,maxSize[200]]" value="${resBaseFunds.orgName}"  readonly="true" />
                    </td>
                    <th style="text-align: right;"><font color="red" >*</font>金额：</th>
                    <td >
                        <input name="amountOfExpenses" id="amountOfExpenses" class="input validate[required,maxSize[200]]" value="${resBaseFunds.amountOfExpenses}"  readonly="true" /><font >（万元）</font>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>资金下拨日期：</th>
                    <td>
                        <input type="text" id="inPlaceDate" name="inPlaceDate" value="${resBaseFunds.inPlaceDate}"    readonly="true" >
                    </td>
                    <th style="text-align: right;"><font color="red" >*</font>上报时间：</th>
                    <td>
                        <input type="text" id="reportDate" name="reportDate" value="${resBaseFunds.reportDate}"     readonly="true" >
                    </td>
                </tr>
                <tr>
                    <th  style="text-align: left;" colspan="4">基地拨付具体用途 :</th>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center" > 统计项目 </td>
                    <td colspan="2" style="text-align: left"> 金额（万元）</td>
                </tr>
                <c:forEach items="${dictTypeEnumBaseFundingUseList}" var="dict" >
                    <tr>
                        <td colspan="2" style="text-align: center"> ${dict.dictName} </td>

                        <td colspan="2" style="text-align: left">
                            <c:set  var="key" value="${dict.dictId}${teacher.recordFlow}"/>
                            <input name="hospitalTypeId" class="input resBaseinputValue" value="${detailMap[key]}" id="${dict.dictId}"  readonly="true">
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2" style="text-align: center" > 合计 </td>
                    <td colspan="2" style="text-align: left"> <input name="amountOfIncome" class="input" id="amountOfIncome" value="${resBaseFunds.amountOfIncome}" readonly="true"> </td>
                </tr>

            </table>
        </form>
        <p style="text-align: center;">
            <c:if test="${role eq 'global' and '3' eq resBaseFunds.auditStatusId}">
                <input type="button" onclick="auditBase('${resBaseFunds.recordFlow}','1');" class="btn_green" value="审核通过"/>&#12288;
                <input type="button" onclick="auditBase('${resBaseFunds.recordFlow}','2');" class="btn_green" value="审核不通过"/>&#12288;
            </c:if>
            <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
