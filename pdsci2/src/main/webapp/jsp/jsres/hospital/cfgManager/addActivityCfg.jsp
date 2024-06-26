<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function returnUrl(){
            var id="${file.itemId}";
            id = id.replace(".", "-");
            id = id.replace(".", "-");
            var delId="delFile_${file.recordFlow}";
            var html ="<span class='titleName' title='${file.fileName}' id='"+delId+"'><a style=\"color: #459ae9;\"  href='javascript:void(0);' onclick=\"downloadFile('${file.recordFlow}');\">${pdfn:cutString(file.fileName,6,true,3) }</a><button name='removeFileBtn' title='移除' style='background: white'  onclick=\"delFile('${file.recordFlow}','"+delId+"')\"><font color='red' size='3px;'>×</font></button></span><br>";
            window.parent.$("#"+"divFile_"+id)[0].innerHTML+=(html);
            window.parent.jboxTip("操作成功！");
            doClose();
        }
        $(document).ready(function(){
            var auditRole = "${auditRole}".split(",");
            var inputList = $("#auditRole").find('input');
            for(var i=0;i<inputList.length;i++){
                for (var j=0;j<auditRole.length;j++) {
                    if(auditRole[j]==$(inputList[i]).val()){
                       $(inputList[i]).attr("checked",true);
                    }
                }
            }
            var submitList = $("#submitRole").find('input');
            for(var n=0;n<submitList.length;n++){
                if($(submitList[n]).val()=="${submitRole}"){
                    $(submitList[n]).attr("checked",true);
                }
            }
            change('');
            if ("${GlobalConstant.FLAG_Y}"=="${result}") {
                returnUrl();
            }
        });
        function save() {
            var recordUUID = UUID();
            var url = "<s:url value='/jsres/cfgManager/addActivityCfg'/>?recordFlow="+recordUUID;
            var data = $('#addActivityCfg').serialize();
            var subRole = document.getElementById("subRoleName").value;
            var auditRole = document.getElementById("auditRoleName").value;
            if(subRole==""){
                jboxTip("活动发起人角色不能为空！");
                return;
            }
            if(auditRole==""){
                jboxTip("活动审批人角色不能为空！");
                return;
            }
            jboxPost(url, data, function(resp) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == resp) {
                    setTimeout(function(){
                        jboxClose();
                    }, 1000);
                }
            }, null, true);
            window.parent.jboxLoad("div_table",'<s:url value='/jsres/cfgManager/edit'/>?tagId=activityCfg',true);
        }
        function UUID(){
            var result = '';
            var hexDigits = "0123456789abcdef";
            for (var i = 0; i < 33; i++) {
                result += hexDigits[Math.floor(Math.random() * 0x10)];
            }
            return result;
        }
        function change(role){
            var subNames = document.getElementsByName("roleFlow");
            for(var i=0;i<subNames.length;i++){
                var subName=subNames[i];
                if(subName.checked){
                    document.getElementById("subRoleName").value = document.getElementById(subName.value).innerHTML;
                }
            }
            var auditNames = document.getElementsByName("auditRoleFlow");
            var auditValue =[];
              for(var j=0;j<auditNames.length;j++){
                  var auditName=auditNames[j];
                  if(auditName.checked){
                      auditValue.push(document.getElementById(auditName.value).innerHTML)
                  }
              }
            document.getElementById("auditRoleName").value = auditValue;
        }

        function update(recordFlow) {
            var url = "<s:url value='/jsres/cfgManager/updateActivity'/>?recordFlow="+recordFlow;
            var data = $('#addActivityCfg').serialize();
            var auditRole = document.getElementById("auditRoleName").value;
            if(auditRole==""){
                jboxTip("活动审批人角色不能为空！");
                return;
            }
            jboxPost(url, data, function(resp) {
                if ('${GlobalConstant.OPRE_SUCCESSED}' == resp) {
                    setTimeout(function(){
                        jboxClose();
                    }, 1000);
                }
            }, null, true);
            window.parent.jboxLoad("div_table",'<s:url value='/jsres/cfgManager/edit'/>?tagId=activityCfg',true);
        }
    </script>
</head>
<body>
<div class="infoAudit">
    <form id="addActivityCfg" method="post" style="position:relative;" action="<s:url value='/jsres/evaluation/addActivityCfg'/>?itemId="+${itemId} enctype="multipart/form-data">
        <input type="hidden" name="itemId" value="${param.itemId}"/>
        <input type="hidden" name="itemName" value="${param.itemName}"/>
        <div id="uploadFileDiv" style="display: ${empty result?'':'none'}">
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
                <tr>
                    <th>发起人：</th>
                    <td id="submitRole">
                        <c:forEach items="${sysRoleList}" var="sysRole">
                            &#12288;<input value="${sysRole.cfgValue}" onchange="change('${sysRole.cfgValue}')" name="roleFlow" type="radio"/><label id="${sysRole.cfgValue}">${sysRole.cfgDesc}</label>&#12288;
                        </c:forEach>
                        <input hidden id="subRoleName" name="subRoleName" >
                    </td>
                </tr>
                <tr>
                    <th>审批人：</th>
                    <td id="auditRole">
                       <c:forEach items="${sysRoleList}" var="sysRole">
                           &#12288;<input value="${sysRole.cfgValue}" name="auditRoleFlow" onchange="change('${sysRole.cfgValue}')" type="checkbox"/><label id="${sysRole.cfgValue}">${sysRole.cfgDesc}</label>&#12288;
                       </c:forEach>
                       <input hidden id="auditRoleName" name="auditRoleName">
                    </td>
                </tr>
            </table>
            <div class="button">
                <c:if test="${empty recordFlow}">
                 <input type="button" class="btn_green" onclick="save();" value="新&#12288;增"/>
                </c:if>
                <c:if test="${!empty recordFlow}">
                    <input type="button" class="btn_green" onclick="update('${recordFlow}');" value="修&#12288;改"/>
                </c:if>
                <input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>