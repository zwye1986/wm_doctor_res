<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            if('${flag}'=='Y'){
                $("input").attr("disabled","disabled");
                $("textarea").attr("disabled","disabled");
                $("select").attr("disabled","disabled");
                $("#applyBtn").hide();
            }
        });
        function saveApply(){
            if(!$("#searchForm").validationEngine("validate")){
                return;
            }
            var start = $("#leaveTimeStart").val();
            var end = $("#leaveTimeEnd").val();
            $("#leaveTimeInput").val(start+"/"+end);
            var url ="<s:url value='/xjgl/leave/saveApply'/>";
            jboxPost(url, $("#searchForm").serialize() , function(resp){
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}'){
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            } , null , true);
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
    </script>
</head>
<body>

<div class="mainright">
    <div class="content">
        <form id="searchForm" method="post" action="<s:url value='/xjgl/leave/leaveInfosByStu'/>">
            <input type="hidden" name="recordFlow" value="${leaveMain.recordFlow}"/>
            <table class="basic" style="width: 100%;margin: 5px 0px;border: none;">
                <tr >
                    <td style="border: none;" colspan="2">
                        请假类型：
                        <select style="width: 141px;" name="applyTypeId">
                            <c:forEach items="${xjNyqjApplyTypeEnumList}" var="type">
                                <option value="${type.id}" <c:if test="${leaveMain.applyTypeId eq type.id}">selected="selected"</c:if>>${type.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td style="border: none;text-align: right;" colspan="2">
                        <input type="button" class="search" onclick="saveApply();" value="提&#12288;交" id="applyBtn"/>
                    </td>
                </tr>
                <tr>
                    <td style="border: none;text-align: center;" colspan="4">
                        <span style="font-size: 16px;">南方医科大学研究生</span><br/>
                        <span style="font-size: 16px;">请假申请表</span>
                    </td>
                </tr>
                <tr>
                    <td style="border: none;text-align: left;padding: 0px;">姓名：${flag eq 'Y' ?leaveMain.userName:sysUser.userName}</td>
                    <td style="border: none;text-align: left;padding: 0px;">学院：
                        <c:if test="${flag ne 'Y'}">
                            <input name="collegeName" value="${leaveMain.collegeName}" style="width: 140px;"/>
                        </c:if>
                        <c:if test="${flag eq 'Y'}">${leaveMain.collegeName}</c:if>
                    </td>
                    <td style="border: none;text-align: left;padding: 0px;">专业：${flag eq 'Y' ?leaveMain.majorName:eduUser.majorName}</td>
                    <td style="border: none;text-align: left;padding: 0px;">学号：${flag eq 'Y' ?leaveMain.studentId:eduUser.sid}</td>
                    <input type="hidden" name="majorName" value="${eduUser.majorName}"/>
                    <input type="hidden" name="studentId" value="${eduUser.sid}"/>
                </tr>
            </table>
            <table class="basic" style="width: 100%;margin: 0px 0px 10px 0px;">
                <tr>
                    <td style="text-align: center;padding: 0px;">离校事由</td>
                    <td colspan="3">
                        <textarea name="leaveReason" style="width: 95%;height: 100px;margin: 2px 0px;" placeholder="（1.参加学术会议和外出交流学习，须附会议邀请函；2.病假须附诊断证明书；3.事假理由须详细。）" maxlength="250">${leaveMain.leaveReason}</textarea>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center;padding: 0px;">离校期限</td>
                    <td>
                        <span>
                        <input type="text" name="leaveTimeStart" id="leaveTimeStart" style="width: 100px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="validate[required]" value="${fn:split(leaveMain.leaveTime,"至")[0]}" onchange="checkTime(this)" />
                        至
                        <input type="text" name="leaveTimeEnd" id="leaveTimeEnd" style="width: 100px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="validate[required]" value="${fn:split(leaveMain.leaveTime,"至")[1]}" onchange="checkTime(this)" />
                        </span>
                        <input type="hidden" name="leaveTime" id="leaveTimeInput"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center;padding: 0px;">去往地点</td>
                    <td><input name="destination" type="text" style="width: 95%;" value="${leaveMain.destination}" maxlength="100"/></td>
                </tr>
                <tr>
                    <td style="text-align: center;padding: 0px;">联系方式</td>
                    <td><input name="linkPhone" type="text" style="width: 95%;" value="${leaveMain.linkPhone}" maxlength="25"/></td>
                </tr>
            </table>
        </form>
        备注：1.事假三天以内、病假七天以内交培养单位审批。<br/>&emsp;&emsp;&ensp;
        2.事假三天以上、病假七天以上必须通过研究生工作部审批。
    </div>
</div>
</body>
</html>
