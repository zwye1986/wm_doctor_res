<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
    </jsp:include>
    <style type="text/css">
        input[type='text']{width:100px;border-width:0px;}
        input[type='text']{border-bottom:1px solid #e3e3e3;}
    </style>
    <script type="text/javascript" src="<s:url value='/js/jquery.jqprint-0.3.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        function search(){
            $("#searchForm").submit();
        }
        function save(){
            if (!$("#saveForm").validationEngine("validate")) {
                return;
            }
            jboxPost("<s:url value='/gyxjgl/user/saveEmployTutor'/>", $("#saveForm").serialize(), function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    location.reload();
                    jboxClose();
                }
            });
        }

        function auditOpt(statusId){
            jboxConfirm("确认审核"+(statusId=='Passed'?'通过':'不通过')+"？", function(){
                var url = "<s:url value='/gyxjgl/user/employAuditOpt?statusId='/>"+statusId;
                jboxPost(url, $("#saveForm").serialize(), function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    }
                }, null, true);
            });
        }

        $(function(){

        });

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <%--<form id="searchForm" action="<s:url value="/gyxjgl/user/saveEmployTutor"/>" method="post">--%>
            <div style="margin:20px 0px 10px 40px;">
                <c:if test="${roleFlag eq 'doctor' && empty resume.tutorAuditId}">
                    <input type="button" class="search" onclick="save();" value="保&#12288;存" />
                </c:if>
            </div>
        <%--</form>--%>
        <form id="saveForm">
            <input type="hidden" name="roleFlag" value="${roleFlag}">
            <input type="hidden" name="userFlow" value="${param.userFlow}">
            <input type="hidden" name="recordFlow" value="${resume.recordFlow}">
            <div style="text-align:center;font-size:30px;font-weight:500;">
                同等学力人员聘请硕士导师申请表
            </div>
            <div style="width: 100%;">
                <table class="basic" style="width:100%;">
                    <tr>
                        <th style="min-width:80px;" rowspan="3">同等学力人员简况</th>
                        <th style="min-width:80px;">学号</th>
                        <td style="min-width:115px;"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="studentId" value="${empty resume.studentId?eduUser.sid:resume.studentId}"/></td>
                        <th style="min-width:80px;">姓名</th>
                        <td style="min-width:115px;"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="userName" value="${empty resume.userName?user.userName:resume.userName}"/></td>
                        <th style="min-width:80px;">性别</th>
                        <td style="min-width:115px;"><input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="sexId" value="Man" ${resume.sexId eq 'Man'?'checked':''}>男&#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="sexId" value="Woman" ${resume.sexId eq 'Woman'?'checked':''}>女</td>
                        <th style="min-width:80px;">身份证号</th>
                        <td style="min-width:115px;"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}"  name="idNumber" value="${resume.idNumber}"/></td>
                    </tr>
                    <tr>
                        <th style="min-width:80px;">申请硕士学位专业</th>
                        <td style="min-width:115px;" colspan="2"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="speName" value="${resume.speName}"/></td>
                        <th style="min-width:80px;">工作单位及</br>联系电话</th>
                        <td style="min-width:115px;" colspan="4"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="workUnit" value="${resume.workUnit}" style="width: 280px;"/></td>
                    </tr>
                    <tr>
                        <th style="min-width:80px;">在本学科专业的主要成绩<br/>（科研、发表论文等）</th>
                        <td style="min-width:115px;" colspan="7">
                            <textarea class="${roleFlag eq 'global'?'':'validate[required]'}" name="paper" style="width: 95%;height: 80px;margin-top: 3px;" ${roleFlag eq 'doctor'?'':'readonly'}>${resume.paper}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th style="min-width:80px;" rowspan="3">拟聘导师简况</th>
                        <th style="min-width:80px;">姓名</th>
                        <td style="min-width:115px;">
                            <input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="tutorName" value="${empty resume.tutorName?eduUser.firstTeacher:resume.tutorName}"/>
                            <input type="hidden" name="tutorFlow" value="${empty resume.tutorFlow?eduUser.firstTeacherFlow:resume.tutorFlow}">
                        </td>
                        <th style="min-width:80px;">性别</th>
                        <td style="min-width:115px;"><input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="tutorSexId" value="Man" ${resume.tutorSexId eq 'Man'?'checked':''}>男&#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="tutorSexId" value="Woman" ${resume.tutorSexId eq 'Woman'?'checked':''}>女</td>
                        <th style="min-width:80px;">年龄</th>
                        <td style="min-width:115px;"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="tutorAge" value="${resume.tutorAge}"/></td>
                        <th style="min-width:80px;">职称/职务</th>
                        <td style="min-width:115px;"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="tutorTitle" value="${resume.tutorTitle}"/></td>
                    </tr>
                    <tr>
                        <th style="min-width:80px;">工作单位及</br>联系电话</th>
                        <td style="min-width:115px;" colspan="7"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="tutorWorkUnit" value="${resume.tutorWorkUnit}" style="width: 280px;"/></td>
                    </tr>
                    <tr>
                        <th style="min-width:80px;">主要研究方向</th>
                        <td style="min-width:115px;" colspan="7">
                            <textarea class="${roleFlag eq 'global'?'':'validate[required]'}" name="tutorDirection" style="width: 95%;height: 80px;margin-top: 3px;" ${roleFlag eq 'doctor'?'':'readonly'}>${resume.tutorDirection}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="9" style="text-align: left;">拟聘导师意见</th>
                    </tr>
                    <tr>
                        <td colspan="9">
                            <div style="text-align:left;padding:10px;">
                                <c:if test="${roleFlag eq 'tutor' && param.viewFlag ne 'view' && (empty resume.tutorAuditId)}">
                                    <p style="line-height:35px;">审核意见：</p>
                                    <textarea name="tutorOpinion" style="width:95%;height:80px;" onchange="if(value.length>250) value=value.substr(0,250)">${resume.tutorOpinion}</textarea>
                                    <input type="button" class="search" value="通&#12288;过" onclick="auditOpt('Passed');"/>
                                    <input type="button" class="search" value="不通过" onclick="auditOpt('UnPassed');"/>
                                </c:if>
                                <c:if test="${!(roleFlag eq 'tutor' && param.viewFlag ne 'view' && (empty resume.tutorAuditId))}">
                                    ${resume.tutorOpinion}<br/><br/>
                                    <br/><span style="padding-left: 550px;">签&emsp;&emsp;章&emsp;&emsp;&emsp;&emsp;</span>
                                    <br/><span style="padding-left: 550px;">年&emsp;&emsp;月&emsp;&emsp;日&emsp;</span>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="9" style="text-align: left;">导师所在单位院、系、所意见</th>
                    </tr>
                    <tr>
                        <td colspan="9">
                            <div style="text-align:left;padding:10px;">
                                <c:if test="${roleFlag eq 'pydw' && param.viewFlag ne 'view' && (empty resume.orgAuditId)}">
                                    <p style="line-height:35px;">审核意见：</p>
                                    <textarea name="orgOpinion" style="width:95%;height:80px;" onchange="if(value.length>250) value=value.substr(0,250)">${resume.orgOpinion}</textarea>
                                    <input type="button" class="search" value="通&#12288;过" onclick="auditOpt('Passed');"/>
                                    <input type="button" class="search" value="不通过" onclick="auditOpt('UnPassed');"/>
                                </c:if>
                                <c:if test="${!(roleFlag eq 'pydw' && param.viewFlag ne 'view' && (empty resume.orgAuditId))}">
                                    ${resume.orgOpinion}<br/><br/>
                                    <br/><span style="padding-left: 550px;">签&emsp;&emsp;章&emsp;&emsp;&emsp;&emsp;</span>
                                    <br/><span style="padding-left: 550px;">年&emsp;&emsp;月&emsp;&emsp;日&emsp;</span>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="9" style="text-align: left;">研究生院审批意见</th>
                    </tr>
                    <tr>
                        <td colspan="9">
                            <div style="text-align:left;padding:10px;">
                                <c:if test="${roleFlag eq 'xx' && param.viewFlag ne 'view' && (empty resume.schoolAuditId)}">
                                    <p style="line-height:35px;">审核意见：</p>
                                    <textarea name="schoolOpinion" style="width:95%;height:80px;" onchange="if(value.length>250) value=value.substr(0,250)">${resume.schoolOpinion}</textarea>
                                    <input type="button" class="search" value="通&#12288;过" onclick="auditOpt('Passed');"/>
                                    <input type="button" class="search" value="不通过" onclick="auditOpt('UnPassed');"/>
                                </c:if>
                                <c:if test="${!(roleFlag eq 'xx' && param.viewFlag ne 'view' && (empty resume.schoolAuditId))}">
                                    ${resume.schoolOpinion}<br/><br/>
                                    <br/><span style="padding-left: 550px;">签&emsp;&emsp;章&emsp;&emsp;&emsp;&emsp;</span>
                                    <br/><span style="padding-left: 550px;">年&emsp;&emsp;月&emsp;&emsp;日&emsp;</span>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
        <div style="text-align: center;margin-top:20px;">

        </div>
    </div>
</div>
</body>
</html>