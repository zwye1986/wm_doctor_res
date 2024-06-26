<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function saveDateForm(){
            var $form =  $("#dateForm");
            if($form.validationEngine("validate")){
                var url = "<s:url value='/res/doctorCfg/saveDate'/>";
                jboxPost(url,$form.serialize(),function(resp){
                    if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                        var $document = $(window.parent.frames["mainIframe"].document);
                        window.parent.frames['mainIframe'].window.location.reload();
                        jboxClose();
                    }
                },null,true);
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <form id="dateForm">
        <div class="content">
            <div class="title1 clearfix">
                <table class="xllist" >
                    <tr>
                        <th style="text-align: left;" ></th>
                        <th style="text-align: left;" >&#12288;开始时间-结束时间 <font color="red">*</font></th>
                    </tr>

                    <tr>
                        <td style="text-align: left;">&#12288;权限日期</td>
                        <td style="text-align: left;">&#12288;
                            <input type="text" class="validate[required] " id="powerStartTime" name="powerStartTime"  value="${powerCfg.powerStartTime }" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 80px;"/>&#12288;-&#12288;
                            <input type="text" class="validate[required] " id="powerEndTime" name="powerEndTime" value="${powerCfg.powerEndTime }" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 80px;"/>
                        </td>
                    </tr>
                </table>

                <div>
                    <p style="text-align: center;">
                        <input type="hidden" name="cfgCode" value="${powerCfg.cfgCode }"/>
                        <input type="button" class="search" value="保&#12288;存" onclick="saveDateForm();"></p>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>