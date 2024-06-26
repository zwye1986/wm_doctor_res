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
    <style type="text/css">
        .xllist th{
            text-align: right;
            height: 40px;
        }
        .xllist td{
            text-align: left;
            height: 40px;
        }
    </style>
    <script type="text/javascript">
        function saveDateForm(){
            var $form =  $("#dateForm");
            if($form.validationEngine("validate")){
                var oldDeptFLow=$("#oldSignDeptFlow").val();
                var deptFlow=$("#signDeptFlow").val();
                var oldUser1=$("#oldChargeUserFlow").val();
                var oldUser2=$("#oldChargeUser2Flow").val();
                var user1=$("#ChargeUserFlow").val();
                var user2=$("#ChargeUser2Flow").val();
                if(oldUser1==user1&&oldUser2==user2&&oldDeptFLow==deptFlow)
                {
                    jboxTip("原合同负责人与变更后合同负责一样，请重新选择！");
                    return false;
                }
                var url = "<s:url value='/erp/crm/chargeUserChange/save'/>";
                jboxPost(url,$form.serialize(),function(resp){
                    var code=resp.code;
                    if(code==1)
                    {
                        jboxTip("保存成功");
                        setTimeout(function(){
                            window.parent.frames['mainIframe'].window.location.reload();
                            jboxClose();
                        },2000);
                    }else{
                        var msg="保存失败！"+resp.msg;
                        jboxTip(msg);
                    }
                },null,false);
            }
        }

        function setUserName(id){
            $("#"+id+"Name").val($("#"+id+"Flow").find("option:selected").text());
        }
        function searchDeptUser(obj,type){
            var deptFlow=$(obj).val();
            var url="<s:url value='/erp/crm/searchDeptUserJsonNew'/>?deptFlow="+deptFlow;
            if(type=="")
            {
                url="<s:url value='/erp/crm/searchDeptUserJson'/>?deptFlow="+deptFlow;
            }
            $("#"+type+"ChargeUserFlow").empty();
            $("#"+type+"ChargeUser2Flow").empty();

            var option="<option value=''>请选择</option>";
            $("#"+type+"ChargeUserFlow").append(option);
            $("#"+type+"ChargeUser2Flow").append(option);
            if(deptFlow!=""){
                jboxPost(url,null,function(data){
                    if(data!=null){
                        for ( var i = 0; i < data.length; i++) {
                            $("#"+type+"ChargeUserFlow").append('<option value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
                            $("#"+type+"ChargeUser2Flow").append('<option value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
                        }
                    }
                },null,false);

                var signDeptName=$(obj).find("option:selected").text();
                if(type==""){
                    $("#signDeptName").val(signDeptName);
                }else {
                    $("#oldSignDeptName").val(signDeptName);
                }
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
                        <th style="width:24%;">原合同负责部门：</th>
                        <td colspan="3">
                            <select name="oldSignDeptFlow" id="oldSignDeptFlow" class="xlselect validate[required]" onchange="searchDeptUser(this,'old');" style="width:158px;margin-left: 10px;">
                                <option value="">请选择</option>
                                <option value="f48a5060931147daa467eadbb5885629">总经办</option>
                                <option value="a8a0aeef43c846c4a976589184b4ed8b">销售一部</option>
                                <option value="a02a8190fc58440f8635b7c5cfcd1949">销售二部</option>
                                <option value="d7f1afc58416446ba414d3c69bb204e2">销售三部</option>
                                <option value="5b42e66a13e742d3a796f17d8104e02d">销售四部</option>
                                <option value="1f56f216c3fd4dc4a1b23e7dda645c0b">销售五部</option>
                            </select>
                            <input type="hidden" name="oldSignDeptName" id="oldSignDeptName"/>
                            &#12288;&#12288;&#12288;&#12288;&#12288;变更为：<select name="signDeptFlow" id="signDeptFlow" class="xlselect validate[required]" onchange="searchDeptUser(this,'');" style="width:158px;margin-left: 10px;">
                                        <option value="">请选择</option>
                                        <option value="f48a5060931147daa467eadbb5885629">总经办</option>
                                        <option value="a8a0aeef43c846c4a976589184b4ed8b">销售一部</option>
                                        <option value="a02a8190fc58440f8635b7c5cfcd1949">销售二部</option>
                                        <option value="d7f1afc58416446ba414d3c69bb204e2">销售三部</option>
                                        <option value="5b42e66a13e742d3a796f17d8104e02d">销售四部</option>
                                        <option value="1f56f216c3fd4dc4a1b23e7dda645c0b">销售五部</option>
                                    </select>
                                    <input type="hidden" name="signDeptName" id="signDeptName"/>
                        </td>
                    </tr>
                    <tr>
                            <th>原合同负责人：</th>
                            <td colspan="3">
                                <select name="oldChargeUserFlow" id="oldChargeUserFlow" class="xlselect validate[required]" onchange="setUserName('oldChargeUser')" style="width:158px;margin-left: 10px;">
                                    <option value="">请选择</option>
                                </select>
                                <input type="hidden" name="oldChargeUserName" id="oldChargeUserName"/>
                                &#12288;&#12288;&#12288;&#12288;&#12288;变更为：<select name="chargeUserFlow" id="ChargeUserFlow" class="xlselect validate[required]" onchange="setUserName('ChargeUser')" style="width:158px;margin-left: 10px;">
                                            <option value="">请选择</option>
                                        </select>
                                        <input type="hidden" name="chargeUserName" id="ChargeUserName"/>
                            </td>
                    </tr>
                    <tr>
                            <th>原合同负责人2：</th>
                            <td colspan="3">
                                <select name="oldChargeUser2Flow" id="oldChargeUser2Flow" class="xlselect validate[required]" onchange="setUserName('oldChargeUser2')" style="width:158px;margin-left: 10px;">
                                    <option value="">请选择</option>
                                </select>
                                <input type="hidden" name="oldChargeUser2Name" id="oldChargeUser2Name"/>
                                &#12288;&#12288;&#12288;&#12288;&#12288;变更为：<select name="chargeUser2Flow" id="ChargeUser2Flow" class="xlselect validate[required]"onchange="setUserName('ChargeUser2')"  style="width:158px;margin-left: 10px;">
                                            <option value="">请选择</option>
                                        </select>
                                        <input type="hidden" name="chargeUser2Name" id="ChargeUser2Name"/>
                            </td>
                        </td>
                    </tr>
                </table>
                <div>
                    <p style="text-align: center;"><input type="button" class="search" value="提交申请" onclick="saveDateForm();"></p>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>