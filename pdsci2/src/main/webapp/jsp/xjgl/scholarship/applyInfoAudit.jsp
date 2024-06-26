<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
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
        function auditOpt(recordFlow,auditFlag,doctorFlag){
            if(('${param.role}'=='fwh' || '${param.role}'=='szk') && doctorFlag=='Xyjxj'){
                var url = "<s:url value='/xjgl/scholarship/batchAudit?role=${param.role}&recordLst='/>"+recordFlow+"&auditFlag="+auditFlag+"&remarkFlag=Y";
                jboxOpen(url, '审核意见',400,260);
            }else{
                var url = "<s:url value='/xjgl/scholarship/auditOption?role=${param.role}&recordFlow='/>"+recordFlow+"&auditFlag="+auditFlag+"&doctorFlag="+doctorFlag+"&applyTypeId="+doctorFlag;
                jboxOpen(url, '审核意见',400,260);
            }
        }
        function detailInfo(recordFlow){
            var url = "<s:url value='/xjgl/scholarship/detailInfo?recordFlow='/>"+recordFlow;
            jboxOpen(url, "查看",800,600);
        }
        function expApplyInfo(){
            var url = "<s:url value='/xjgl/scholarship/expApplyInfo'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
        $(function(){
            $("#orgName").likeSearchInit({});
            $("input:checkbox[name='recordFlow']").change(function(){
                var isCheckAll=true;
                var checkedBox = $("input:checkbox[name='recordFlow']");
                $(checkedBox).each(function(index, element) {
                    if(!$(element).prop("checked")){
                        isCheckAll=false;
                    }
                });
                $("#checkall").prop("checked",isCheckAll);
            });
        });
        function checkAllChange(){
            var isChecked = $("#checkall").prop("checked");
            $("input:checkbox[name='recordFlow']").each(function(i,n){
                $(n).prop("checked",isChecked);
            });
        }
        function batchAuditOpt(){
            var recordLst = [];
            $(":checkbox[name='recordFlow']:checked").each(function () {
                recordLst.push(this.value);
            });
            if (recordLst.length > 0) {
                var flag = 'N';
                var a = $(":checkbox[name='recordFlow']:checked").not(".remark").length;
                var b = $(":checkbox[class='remark']:checked").length;
                if(recordLst.length != a && recordLst.length != b){
                    jboxTip("学业奖学金需独立批量审核！");
                    return;
                }else if(recordLst.length == b){
                    flag = 'Y';
                }
                var docFlagLst = [];
                $("input[name='doctorFlag']").each(function () {
                    docFlagLst.push(this.value);
                });
                var url = "<s:url value='/xjgl/scholarship/batchAudit?role=${param.role}&recordLst='/>"+recordLst+"&remarkFlag="+flag+"&docFlagLst="+docFlagLst;
                jboxOpen(url, '批量审核',400,260);
            } else {
                jboxTip("请勾选需要批量审核的数据！");
                return;
            }
        }
        function backOpt(recordFlow,applyTypeId){
            jboxConfirm("确认退回上一级重审？" , function(){
                $("#recordFlow").val(recordFlow);
                $("#applyTypeId").val(applyTypeId);
                jboxPost("<s:url value='/xjgl/scholarship/backOption'/>", $("#myForm").serialize(), function (resp) {
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    }
                },true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="recordFlow" id="recordFlow">
            <input type="hidden" name="role" value="${param.role}">
        </form>
        <form id="searchForm" action="<s:url value="/xjgl/scholarship/stuApplyAudit?role=${param.role}"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>奖助类型：
                <select class="select" name="applyTypeId" style="width:137px;">
                    <option value="">请选择</option>
                    <c:forEach items="${scholarshipTypeEnumList}" var="ship">
                        <option value="${ship.id}" ${param.applyTypeId eq ship.id?'selected':''}>${ship.name}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:20px;"></span>申请时间：
                <span>
                    <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime" value="${param.createTime}" onchange="checkTime(this)"/>
                    -- <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="modifyTime" value="${param.modifyTime}" onchange="checkTime(this)"/>
                </span>
                <span style="padding-left:20px;"></span>姓&#12288;&#12288;名：
                <input type="text" name="userName" value="${param.userName}">
                <br/>
                <span style="padding-left:0px;"></span>学&#12288;&#12288;号：
                <input type="text" name="studentId" value="${param.studentId}">
                <c:if test="${param.role eq 'szk'}">
                    <span style="padding-left:20px;"></span>分&ensp;委&ensp;会：
                    <select class="select" name="fwhOrgFlow" style="width:293px;;">
                        <option value="">请选择</option>
                        <c:forEach items="${deptList}" var="dept">
                            <option value="${dept.deptFlow}" ${param.fwhOrgFlow eq dept.deptFlow?'selected':''}>${dept.deptName}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${param.role eq 'fwh'|| param.role eq 'szk'}">
                    <span style="padding-left:20px;"></span>培养单位：
                    <input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" flow="${param.pydwOrgFlow}"/>&#12288;
                    <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:${param.role eq 'fwh'?'293':'680'}px;">
                        <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                            <c:forEach items="${orgList}" var="org">
                                <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                            </c:forEach>
                        </div>
                    </div>
                    <input type="hidden" id="orgFlow" name="pydwOrgFlow" value="${param.pydwOrgFlow}"/>
                </c:if>
                <c:if test="${param.role eq 'szk'}">
                    <br/>
                    <span style="padding-left:0px;"></span>班&#12288;&#12288;级：
                    <select class="select" name="classId" style="width:137px;;">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumXjClassList}" var="xjclass">
                            <option value="${xjclass.dictId}" ${param.classId eq xjclass.dictId?'selected':''}>${xjclass.dictName}</option>
                        </c:forEach>
                    </select>
                    <span style="padding-left:20px;"></span>年&#12288;&#12288;级：
                    <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" name="sessionNumber" value="${param.sessionNumber}"/>
                </c:if>
                <span style="padding-left:10px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <input type="button" class="search" value="批量审核" onclick="batchAuditOpt()"/>
                <c:if test="${param.role eq 'szk'}">
                    <input type="button" class="search" value="导&#12288;出" onclick="expApplyInfo()"/>
                </c:if>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>全选<input type="checkbox" id="checkall" onchange="checkAllChange()"/></th>
                <th>学号</th>
                <th>姓名</th>
                <th>申请时间</th>
                <th>申请奖助类型</th>
                <th>申请内容</th>
                <th>导师</th>
                <th>培养单位</th>
                <th>分委会</th>
                <th>学校</th>
            </tr>
            <c:forEach items="${mainList}" var="main">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${param.role eq 'ds'}">
                                <c:if test="${main.doctorAuditStatusId eq 'Passing' || main.secondAuditStatusId eq 'Passing'}">
                                    <input type="checkbox" name="recordFlow" value="${main.recordFlow}"/>
                                    <c:if test="${main.doctorAuditStatusId eq 'Passing'}"><input type="hidden" name="doctorFlag" value="first"/></c:if>
                                    <c:if test="${main.secondAuditStatusId eq 'Passing'}"><input type="hidden" name="doctorFlag" value="second"/></c:if>
                                </c:if>
                                <c:if test="${!(main.doctorAuditStatusId eq 'Passing' || main.secondAuditStatusId eq 'Passing')}">
                                    <input type="checkbox" disabled="disabled"/>
                                </c:if>
                            </c:when>
                            <c:when test="${param.role eq 'pydw'}">
                                <c:if test="${main.pydwAuditStatusId eq 'Passing'}">
                                    <input type="checkbox" name="recordFlow" value="${main.recordFlow}"/>
                                </c:if>
                                <c:if test="${main.pydwAuditStatusId ne 'Passing'}">
                                    <input type="checkbox" disabled="disabled"/>
                                </c:if>
                            </c:when>
                            <c:when test="${param.role eq 'fwh'}">
                                <c:if test="${main.fwhAuditStatusId eq 'Passing'}">
                                    <input type="checkbox" name="recordFlow" class="${main.applyTypeId eq 'Xyjxj'?'remark':''}" value="${main.recordFlow}"/>
                                </c:if>
                                <c:if test="${main.fwhAuditStatusId ne 'Passing'}">
                                    <input type="checkbox" disabled="disabled"/>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${main.szkAuditStatusId eq 'Passing'}">
                                    <input type="checkbox" name="recordFlow" class="${main.applyTypeId eq 'Xyjxj'?'remark':''}" value="${main.recordFlow}"/>
                                </c:if>
                                <c:if test="${main.szkAuditStatusId ne 'Passing'}">
                                    <input type="checkbox" disabled="disabled"/>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${main.studentId}</td>
                    <td>${main.userName}</td>
                    <td>${main.applyTime}</td>
                    <td>${main.applyTypeName}</td>
                    <td><a onclick="detailInfo('${main.recordFlow}')" style="cursor:pointer;color:blue;">查看</a></td>
                    <c:if test="${param.role eq 'ds' && main.doctorFlow eq doctorFlow}">
                        <td title="${main.doctorAuditAdvice}">
                            <c:if test="${main.doctorAuditStatusId eq 'Passing'}">
                                <a onclick="auditOpt('${main.recordFlow}','Passed','first');" style="cursor:pointer;color:blue;">同意</a>
                                <a onclick="auditOpt('${main.recordFlow}','UnPassed','first');" style="cursor:pointer;color:blue;">不同意</a>
                            </c:if>
                            <c:if test="${main.doctorAuditStatusId ne 'Passing'}">${main.doctorAuditStatusName}</c:if>
                        </td>
                    </c:if>
                    <c:if test="${param.role eq 'ds' && main.secondDoctorFlow eq doctorFlow}">
                        <td title="${main.secondAuditAdvice}">
                            <c:if test="${main.secondAuditStatusId eq 'Passing'}">
                                <a onclick="auditOpt('${main.recordFlow}','Passed','second');" style="cursor:pointer;color:blue;">同意</a>
                                <a onclick="auditOpt('${main.recordFlow}','UnPassed','second');" style="cursor:pointer;color:blue;">不同意</a>
                            </c:if>
                            <c:if test="${main.secondAuditStatusId ne 'Passing'}">${main.secondAuditStatusName}</c:if>
                        </td>
                    </c:if>
                    <c:if test="${param.role ne 'ds'}">
                        <td>
                            <c:choose>
                                <c:when test="${main.applyTypeId eq 'Zggw'}">--</c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${!empty main.doctorFlow && !empty main.secondDoctorFlow}">
                                            <span title="${main.doctorAuditAdvice}">导师一：${main.doctorAuditStatusName}</span>&ensp;<span title="${main.secondAuditAdvice}">导师二：${main.secondAuditStatusName}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${!empty main.doctorFlow}"><span title="${main.doctorAuditAdvice}">${main.doctorAuditStatusName}</span></c:if>
                                            <c:if test="${!empty main.secondDoctorFlow}"><span title="${main.secondAuditAdvice}">${main.secondAuditStatusName}</span></c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </c:if>
                    <td title="${main.pydwAuditAdvice}">
                        <c:if test="${param.role eq 'pydw' && main.pydwAuditStatusId eq 'Passing'}">
                            <a onclick="auditOpt('${main.recordFlow}','Passed','${main.applyTypeId}');" style="cursor:pointer;color:blue;">同意</a>
                            <a onclick="auditOpt('${main.recordFlow}','UnPassed','${main.applyTypeId}');" style="cursor:pointer;color:blue;">不同意</a>
                        </c:if>
                        <c:if test="${param.role ne 'pydw' || main.pydwAuditStatusId ne 'Passing'}">${empty main.pydwAuditStatusId?'--':main.pydwAuditStatusName}</c:if>
                        <c:if test="${param.role eq 'pydw' && main.pydwAuditStatusId ne 'Passing' && (empty main.szkAuditStatusId || main.szkAuditStatusId eq 'Passing') && (empty main.fwhAuditStatusId || main.fwhAuditStatusId eq 'Passing')}">
                            &nbsp;<a onclick="backOpt('${main.recordFlow}');" style="cursor:pointer;color:blue;">退回</a>
                        </c:if>
                    </td>
                    <td title="${main.fwhAuditAdvice}">
                        <c:if test="${param.role eq 'fwh' && main.fwhAuditStatusId eq 'Passing'}">
                            <a onclick="auditOpt('${main.recordFlow}','Passed','${main.applyTypeId}');" style="cursor:pointer;color:blue;">同意</a>
                            <a onclick="auditOpt('${main.recordFlow}','UnPassed','${main.applyTypeId}');" style="cursor:pointer;color:blue;">不同意</a>
                        </c:if>
                        <c:if test="${param.role ne 'fwh' || main.fwhAuditStatusId ne 'Passing'}">${empty main.fwhAuditStatusId?'--':main.fwhAuditStatusName}</c:if>
                        <c:if test="${param.role eq 'fwh' && main.fwhAuditStatusId ne 'Passing' && (empty main.szkAuditStatusId || main.szkAuditStatusId eq 'Passing')}">
                            &nbsp;<a onclick="backOpt('${main.recordFlow}');" style="cursor:pointer;color:blue;">退回</a>
                        </c:if>
                    </td>
                    <td title="${main.szkAuditAdvice}">
                        <c:if test="${param.role eq 'szk' && main.szkAuditStatusId eq 'Passing'}">
                            <a onclick="auditOpt('${main.recordFlow}','Passed','${main.applyTypeId}');" style="cursor:pointer;color:blue;">同意</a>
                            <a onclick="auditOpt('${main.recordFlow}','UnPassed','${main.applyTypeId}');" style="cursor:pointer;color:blue;">不同意</a>
                        </c:if>
                        <c:if test="${param.role ne 'szk' || main.szkAuditStatusId ne 'Passing'}">${empty main.szkAuditStatusId?'--':main.szkAuditStatusName}</c:if>
                        <c:if test="${param.role eq 'szk' && main.applyTypeId ne 'Zggw' && (main.szkAuditStatusId eq 'Passed' || main.szkAuditStatusId eq 'UnPassed')}">
                            &nbsp;<a onclick="backOpt('${main.recordFlow}');" style="cursor:pointer;color:blue;">退回</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(mainList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>