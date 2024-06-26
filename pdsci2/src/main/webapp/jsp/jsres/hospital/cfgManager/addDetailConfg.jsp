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
        <%--$(document).ready(function(){--%>
            <%--var auditRole = "${auditRole}".split(",");--%>
            <%--var inputList = $("#auditRole").find('input');--%>
            <%--for(var i=0;i<inputList.length;i++){--%>
                <%--for (var j=0;j<auditRole.length;j++) {--%>
                    <%--if(auditRole[j]==$(inputList[i]).val()){--%>
                       <%--$(inputList[i]).attr("checked",true);--%>
                    <%--}--%>
                <%--}--%>
            <%--}--%>
            <%--var submitList = $("#submitRole").find('input');--%>
            <%--for(var n=0;n<submitList.length;n++){--%>
                <%--if($(submitList[n]).val()=="${submitRole}"){--%>
                    <%--$(submitList[n]).attr("checked",true);--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>
        function save() {
            var url = "<s:url value='/jsres/cfgManager/saveDeptConfig'/>";
            var data = $('#addDeptCfg').serialize();
            jboxPost(url, data, function(resp) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == resp) {
                    setTimeout(function(){
                        jboxClose();
                    }, 1000);
                }
            }, null, true);
            window.parent.jboxLoad("div_table",'<s:url value='/jsres/cfgManager/searchDeptConfig'/>?tagId=ckxzCfg',true);
        }
        
        function changeDept() {
            var deptName = $("#deptFlow").find("option:selected").text();
            $("#deptName").val(deptName);
        }

        function spanShow(flag) {
            if(flag == "Y"){
                $("#testOutTimeSpan").show();
            }else{
                $("#testOutTimeSpan").hide();
            }
        }
    </script>
</head>
<body>
<div class="infoAudit">
    <form id="addDeptCfg" method="post" style="position:relative;" >
        <input type="hidden" name="itemId" value="${param.itemId}"/>
        <input type="hidden" name="itemName" value="${param.itemName}"/>
        <input type="hidden" name="orgFlow" value="${orgFlow}"/>
        <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
            <tr>
                <th>轮转科室：</th>
                <td>
                    <select id="deptFlow" name="deptFlow" class="input" style="height: 30px;width: 159px" onchange="changeDept()">
                        <option value="" ></option>
                        <c:forEach var="dept" items="${deptList}">
                            <option value="${dept.deptFlow}" >${dept.deptName}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" id="deptName" name="deptName" value="">
                </td>
            </tr>
            <tr>
                <th>出科考核次数：</th>
                <td>
                    <input type="text" class="input validate[custom[number],min[0]]" id="testNum" name="testNum" />
                </td>
            </tr>
            <tr>
                <th>默认合格线：</th>
                <td>
                    <input type="text" class="input validate[custom[number],maxSize[100],min[0]]" id="scorePass" name="scorePass" />
                </td>
            </tr>
            <tr>
                <%--<th>出科考核时间：</th>--%>
                <%--<td>--%>
                    <%--超出轮转结束时间--%>
                    <%--<input type="text" style="width: 30px" class="input validate[custom[number],min[0]]" id="testTime" name="testTime" />--%>
                    <%--日内可进行出科考试--%>
                <%--</td>--%>
                <th>是否允许学员在轮转时间结束后考试：</th>
                <td>
                    <input type="radio" id="isTestOutY" name="isTestOut" value="Y" onclick="spanShow('Y')"/>
                        <label for="isTestOutY">是</label>&#12288;
                    <input type="radio" id="isTestOutN" name="isTestOut" value="N" onclick="spanShow('N')" checked="checked"/>
                        <label for="isTestOutN">否</label>
                    <span hidden="hidden" id="testOutTimeSpan">
                        <br/>
                        超出轮转结束时间
                        <input type="text" style="width: 30px" class="input validate[custom[number],min[0]]" id="testOutTime" name="testOutTime" />
                        日内可进行出科考试
                    </span>
                </td>
            </tr>
            <tr>
                <th>是否允许带教老师填写分数：</th>
                <td>
                    <input type="radio" id="teacherWriteY" name="teacherWrite" value="Y"/>
                        <label for="teacherWriteY">是</label>&#12288;
                    <input type="radio" id="teacherWriteN" name="teacherWrite" value="N" checked="checked"/>
                        <label for="teacherWriteN">否</label>
                </td>
            </tr>
        </table>
        <div style="text-align: center">
            <span style="color: red">注意：轮转科室选择默认时，所有未配置科室出科配置为默认配置</span>
        </div>
        <div class="button">
            <input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>
            <input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
        </div>
    </form>
</div>
</body>
</html>