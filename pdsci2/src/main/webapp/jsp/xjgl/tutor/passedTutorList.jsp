<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function toPage(page){
            if($("#orgName").val()!=""){
                $("#orgFlow").val($("#orgName").attr("flow"));
            }else{
                $("#orgFlow").val("");
            }
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function editInfo(tutorFlow){
            var url = "<s:url value='/xjgl/tutor/editTutor?tutorFlow='/>"+tutorFlow+"&role=xwk";
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"编辑",860,600);
        }
        function delInfo(tutorFlow){
            jboxConfirm("删除后账号无法恢复，是否确认？", function(){
                var url = "<s:url value='/xjgl/tutor/delTutor?tutorFlow='/>"+tutorFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function impExcel(){
            jboxOpen("<s:url value='/jsp/xjgl/tutor/impTutorTemp.jsp'/>", "导入",600,200);
        }
        function exportTutors(){
            var url = "<s:url value='/xjgl/tutor/exportTutors'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
        $(function(){
            $("#orgName").likeSearchInit({});
        });
        function resetPasswd(userFlow){
            jboxConfirm("确认将该导师的密码重置为:${pdfn:getInitPass()} 吗？",function () {
                var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>"+userFlow;
                jboxGet(url,null,function(){
                });
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/xjgl/tutor/passedTutorList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>姓&#12288;&#12288;&#12288;&#12288;名：
                <input type="text" style="width:173px" name="doctorName" value="${param.doctorName}">
                <span style="padding-left:10px;"></span>导&ensp;&#8197;师&ensp;类&ensp;&#8197;型：
                <select class="validate[required] select" name="doctorTypeId" style="width:177px;">
                    <option value="">请选择</option>
                    <option value="xsxbd" <c:if test="${param.doctorTypeId eq 'xsxbd'}">selected</c:if>>学术型博导</option>
                    <option value="xsxsd" <c:if test="${param.doctorTypeId eq 'xsxsd'}">selected</c:if>>学术型硕导</option>
                    <option value="zyxbd" <c:if test="${param.doctorTypeId eq 'zyxbd'}">selected</c:if>>专业型博导</option>
                    <option value="zyxsd" <c:if test="${param.doctorTypeId eq 'zyxsd'}">selected</c:if>>专业型硕导</option>
                </select>
                <span style="padding-left:10px;"></span>新&ensp;&#8197;增&ensp;时&ensp;&#8197;间：
                <span>
                    <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime" value="${param.createTime}" onchange="checkTime(this)"/>
                    -- <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime2" value="${param.createTime2}" onchange="checkTime(this)"/>
                </span>
                <br/>
                <span style="padding-left:0px;"></span>申&ensp;&#8197;请&ensp;时&ensp;&#8197;间：
                <span>
                    <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="applyTime" value="${param.applyTime}" onchange="checkTime(this)"/>
                    -- <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="applyTime2" value="${param.applyTime2}" onchange="checkTime(this)"/>
                </span>
                <span style="padding-left:10px;"></span>认&ensp;&#8197;定&ensp;时&ensp;&#8197;间：
                <span>
                    <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="xwkAuditTime" value="${param.xwkAuditTime}" onchange="checkTime(this)"/>
                    -- <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="xwkAuditTime2" value="${param.xwkAuditTime2}" onchange="checkTime(this)"/>
                </span>
                <span style="padding-left:10px;"></span>失&ensp;&#8197;效&ensp;时&ensp;&#8197;间：
                <span>
                    <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="invalidTime" value="${param.invalidTime}" onchange="checkTime(this)"/>
                    -- <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="invalidTime2" value="${param.invalidTime2}" onchange="checkTime(this)"/>
                </span>
                <br/>
                <span style=""></span>分&ensp;&ensp;&ensp;委&ensp;&ensp;&ensp;会：
                <select name="fwhOrgFlow" style="width:177px;" class="select">
                    <option value="">请选择</option>
                    <c:forEach items="${deptList}" var="dept">
                        <option value="${dept.deptFlow}" <c:if test="${param.fwhOrgFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:10px;"></span>培&ensp;&#8197;养&ensp;单&ensp;&#8197;位：
                <input id="orgName" type="text" style="width:173px;" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" flow="${param.pydwOrgFlow}"/>&#12288;
                <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:380px;">
                    <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 180px;border-top: none;position: relative;display: none;">
                        <c:forEach items="${orgList}" var="org">
                            <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                        </c:forEach>
                    </div>
                </div>
                <input type="hidden" id="orgFlow" name="pydwOrgFlow" value="${param.pydwOrgFlow}"/>
                <span style="margin-left:-3px;">失&ensp;&#8197;效&ensp;年&ensp;&#8197;份：</span>
                <input type="text" style="width:173px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" name="invalidYear" value="${param.invalidYear}"/>
                <br/>
                <span style=""></span>是否暂停招生：
                <select name="stopRecruit" style="width:177px;" class="select">
                    <option value="">请选择</option>
                    <option value="Y" <c:if test="${param.stopRecruit eq 'Y'}">selected</c:if>>是</option>
                    <option value="N" <c:if test="${param.stopRecruit eq 'N'}">selected</c:if>>否</option>
                </select>
                &ensp;<input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <input type="button" class="search" value="导&#12288;入" onclick="impExcel()"/>
                <input type="button" class="search" value="导&#12288;出" onclick="exportTutors()"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th style="min-width:70px;">导师姓名</th>
                <th style="min-width:70px;">导师类型</th>
                <th style="min-width:130px;">培养单位</th>
                <th style="min-width:130px;">分委员会</th>
                <th style="min-width:80px;">新增时间</th>
                <th style="min-width:80px;">申请时间</th>
                <th style="min-width:80px;">认定时间</th>
                <th style="min-width:80px;">失效时间</th>
                <th style="min-width:75px;">培养单位审核</th>
                <th style="min-width:75px;">分委会审核</th>
                <th style="min-width:75px;">研究生院审核</th>
                <th style="min-width:120px;">操作</th>
            </tr>
            <c:forEach items="${tutorList}" var="tutor">
                <tr>
                <td>${tutor.doctorName}</td>
                <td>${tutor.doctorTypeName}</td>
                <td>${tutor.pydwOrgName}</td>
                <td>${tutor.fwhOrgName}</td>
                <td>${pdfn:transDate(tutor.createTime)}</td>
                <td>${tutor.applyTime}</td>
                <td>${tutor.xwkAuditTime}</td>
                <td>${tutor.invalidTime}</td>
                <td>${empty tutor.pydwAuditStatusName?'--':tutor.pydwAuditStatusName}</td>
                <td>${empty tutor.fwhAuditStatusName?'--':tutor.fwhAuditStatusName}</td>
                <td>${empty tutor.xwkAuditStatusName?'--':tutor.xwkAuditStatusName}</td>
                <td>
                    <c:if test="${tutor.applyFlag eq 'Y'}">
                        <a onclick="editInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                        <a onclick="delInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">删除</a>
                    </c:if>
                    <c:if test="${tutor.applyFlag ne 'Y'}">待导师申请</c:if>
                    <a onclick="resetPasswd('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">重置密码</a>
                </td>
            </tr>
            </c:forEach>
        </table>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(tutorList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>