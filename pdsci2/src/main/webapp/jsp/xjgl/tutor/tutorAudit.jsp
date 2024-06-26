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
        function auditInfo(tutorFlow,viewFlag){
            var url = "<s:url value='/xjgl/tutor/detailInfo?role=${param.role}&tutorFlow='/>"+tutorFlow+"&viewFlag="+viewFlag;
            jboxOpen(url, viewFlag=='view'?'查看':'审核',800,600);
        }
        function backInfo(tutorFlow){
            jboxConfirm("确认退回重审？", function(){
                var url = "<s:url value='/xjgl/tutor/backAuditOpt?tutorFlow='/>"+tutorFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function batchOpt(){
            jboxConfirm("资格失效的导师需重新申请，是否确认？", function(){
                var url = "<s:url value='/xjgl/tutor/backAuditBatchOpt'/>";
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        $(function(){
            $("#orgName").likeSearchInit({});
        });
        function batchAudit(){
            var doctorFlows=[];
            $('[id=batchInput]').each(function () {
                var flag=$(this).attr("flag");
                var doctorFlow=$(this).attr("value");
                if(flag=='true'){
                    doctorFlows.push(doctorFlow);
                }
            });
            if(doctorFlows.length==0){
                jboxTip("此页无待审核信息！");
                return;
            }
            jboxConfirm("确认审核通过此页待审核信息吗？", function(){
                var t = {'doctorFlows':doctorFlows};
                $("#jsondata").val(JSON.stringify(t));
                var url="<s:url value='/xjgl/tutor/batchAudit'/>?role=${param.role}";
                jboxPost(url,$("#searchForm").serialize(),function(resp){
                    if("${GlobalConstant.OPERATE_SUCCESSED}" == resp){
                        location.reload();
                    }
                }
                ,null,true);
            });
        }

        function sysCfgUpdate(startCode,endCode){
            if ($("#start").val() == '') {
                jboxTip("开始时间不能为空！");
                return;
            }if ($("#end").val() == '') {
                jboxTip("结束时间不能为空！");
                return;
            }
            jboxConfirm('确认保存吗?',function(){
                var startValue=$("#start").val();
                var endValue=$("#end").val();
                var url="<s:url value='/xjgl/course/manage/sysCfgUpdate'/>?startCode="+startCode+"&startValue="+startValue+"&endCode="+endCode+"&endValue="+endValue;
                jboxPost(url,null,function(){
                    window.parent.frames['mainIframe'].window.toPage();
                },null,true);
            });
        }
        function checkYear(obj) {
            var dates = $(':text', $(obj).closest("span"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/xjgl/tutor/tutorAudit?role=${param.role}"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="jsondata" type="hidden" name="jsondata" value=""/>
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>姓&#12288;&#12288;&#12288;&#12288;名：
                <input type="text" style="width:173px" name="doctorName" value="${param.doctorName}">
                <span style="padding-left:10px;"></span>导&ensp;&#8197;师&ensp;类&ensp;&#8197;型：
                <select class="validate[required] select" name="doctorTypeId" style="width:177px">
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
                <c:if test="${param.role eq 'pydw'}">
                    <span style=""></span>审&ensp;&#8197;核&ensp;状&ensp;&#8197;态：
                    <select name="pydwAuditStatusId" style="width:177px" class="select">
                        <option value="">请选择</option>
                        <option value="Passing" <c:if test="${param.pydwAuditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                        <option value="Passed" <c:if test="${param.pydwAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                        <option value="UnPassed" <c:if test="${param.pydwAuditStatusId eq 'UnPassed'}">selected</c:if>>审核不通过</option>
                    </select>
                </c:if>
                <c:if test="${param.role eq 'fwh'}">
                    <span style=""></span>审&ensp;&#8197;核&ensp;状&ensp;&#8197;态：
                    <select name="fwhAuditStatusId" style="width:177px" class="select">
                        <option value="">请选择</option>
                        <option value="Passing" <c:if test="${param.fwhAuditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                        <option value="Passed" <c:if test="${param.fwhAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                        <option value="UnPassed" <c:if test="${param.fwhAuditStatusId eq 'UnPassed'}">selected</c:if>>审核不通过</option>
                    </select>
                    <span style="padding-left:10px;"></span>培&ensp;&#8197;养&ensp;单&ensp;&#8197;位：
                    <input id="orgName" type="text" style="width:173px;" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" flow="${param.pydwOrgFlow}"/>&#12288;
                    <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:350px;">
                        <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 180px;border-top: none;position: relative;display: none;">
                            <c:forEach items="${orgList}" var="org">
                                <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                            </c:forEach>
                        </div>
                    </div>
                    <input type="hidden" id="orgFlow" name="pydwOrgFlow" value="${param.pydwOrgFlow}"/>
                    <span style="margin-left:-3px;"></span>培养单位审核：
                    <select name="pydwAuditStatusId" style="width:177px" class="select">
                        <option value="">请选择</option>
                        <option value="Passing" <c:if test="${param.pydwAuditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                        <option value="Passed" <c:if test="${param.pydwAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                        <option value="UnPassed" <c:if test="${param.pydwAuditStatusId eq 'UnPassed'}">selected</c:if>>审核不通过</option>
                    </select>
                </c:if>
                <c:if test="${param.role eq 'xwk'}">
                    <span style=""></span>审&ensp;&#8197;核&ensp;状&ensp;&#8197;态：
                    <select name="xwkAuditStatusId" style="width:177px" class="select">
                        <option value="">请选择</option>
                        <option value="Passing" <c:if test="${param.xwkAuditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                        <option value="Passed" <c:if test="${param.xwkAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                        <option value="UnPassed" <c:if test="${param.xwkAuditStatusId eq 'UnPassed'}">selected</c:if>>审核不通过</option>
                    </select>
                    <span style="padding-left:10px;"></span>分&ensp;&ensp;&ensp;委&ensp;&ensp;&ensp;会：
                    <select name="fwhOrgFlow" style="width:177px" class="select">
                        <option value="">请选择</option>
                        <c:forEach items="${deptList}" var="dept">
                            <option value="${dept.deptFlow}" <c:if test="${param.fwhOrgFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                        </c:forEach>
                    </select>
                    <span style="padding-left:10px;"></span>分&#8197;委&#8197;会&#8197;审&#8197;核：
                    <select name="fwhAuditStatusId" style="width:177px" class="select">
                        <option value="">请选择</option>
                        <option value="Passing" <c:if test="${param.fwhAuditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                        <option value="Passed" <c:if test="${param.fwhAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                        <option value="UnPassed" <c:if test="${param.fwhAuditStatusId eq 'UnPassed'}">selected</c:if>>审核不通过</option>
                    </select><br/>
                    <span style=""></span>培&ensp;&#8197;养&ensp;单&ensp;&#8197;位：
                    <input id="orgName" type="text" style="width:173px" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" flow="${param.pydwOrgFlow}"/>&#12288;
                    <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:94px;">
                        <div id="boxHome" style="max-height:250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width:180px;border-top: none;position: relative;display: none;">
                            <c:forEach items="${orgList}" var="org">
                                <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                            </c:forEach>
                        </div>
                    </div>
                    <input type="hidden" id="orgFlow" name="pydwOrgFlow" value="${param.pydwOrgFlow}"/>
                    <span style="margin-left:-3px;"></span>培养单位审核：
                    <select name="pydwAuditStatusId" style="width:177px" class="select">
                        <option value="">请选择</option>
                        <option value="Passing" <c:if test="${param.pydwAuditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                        <option value="Passed" <c:if test="${param.pydwAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                        <option value="UnPassed" <c:if test="${param.pydwAuditStatusId eq 'UnPassed'}">selected</c:if>>审核不通过</option>
                    </select>
                </c:if>
                <span style="padding-left:10px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <c:if test="${param.role eq 'xwk'}"><br/>
                    <input type="button" class="search" value="批量审核" onclick="batchAudit()"/>
                    <input type="button" class="search" value="失效批处理" onclick="batchOpt()"/><span style="color:red;">说明：导师资格已到失效时间需重新申请</span>
                    <span style="float: right;">
                           导师注册时间：
                        <input type="text" style="width:120px;margin:0px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" id="start" value="${start.cfgValue}" onchange="checkYear(this)"/>
                        &nbsp;-&nbsp;
                        <input type="text" style="width:120px;margin:0px;" id="end" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${end.cfgValue}" onchange="checkYear(this)"/>
                        <input type="button" class="search"  onclick="sysCfgUpdate('tutor_register_start_time','tutor_register_end_time');" value="确&#12288;认" />
                    </span>
                </c:if>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>导师姓名</th>
                <th>导师类型</th>
                <th>培养单位</th>
                <th>分委员会</th>
                <th>新增时间</th>
                <th>申请时间</th>
                <th>认定时间</th>
                <th>失效时间</th>
                <th>培养单位审核</th>
                <th>分委会审核</th>
                <th>研究生院审核</th>
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
                    <%--培养单位重审标识--%>
                    <c:set var="pydwReAuditFlag" value="${tutor.pydwAuditStatusId eq 'Passed' || tutor.pydwAuditStatusId eq 'UnPassed'}"/>
                    <%--分委会重审标识--%>
                    <c:set var="fwhReAuditFlag" value="${tutor.fwhAuditStatusId eq 'Passed' || tutor.fwhAuditStatusId eq 'UnPassed'}"/>
                    <%--学位科重审标识--%>
                    <c:set var="xwkReAuditFlag" value="${tutor.xwkAuditStatusId eq 'Passed' || tutor.xwkAuditStatusId eq 'UnPassed'}"/>
                    <c:if test="${param.role eq 'pydw'}">
                        <c:if test="${tutor.pydwAuditStatusId eq 'Passing'}">
                            <a onclick="auditInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">审核</a>
                        </c:if>
                        <c:if test="${pydwReAuditFlag && !fwhReAuditFlag}">
                            <a onclick="auditInfo('${tutor.doctorFlow}','reAudit');" style="cursor:pointer;color:blue;">重审</a>
                        </c:if>
                        <c:if test="${tutor.pydwAuditStatusId ne 'Passing'}">
                            <a onclick="auditInfo('${tutor.doctorFlow}','view');" style="cursor:pointer;color:blue;">查看</a>
                        </c:if>
                    </c:if>
                    <c:if test="${param.role eq 'fwh'}">
                        <%--分委会审核标识--%>
                        <c:set var="fwhFlag" value="${tutor.pydwAuditStatusId eq 'Passed' && tutor.fwhAuditStatusId eq 'Passing'}"/>
                        <c:if test="${fwhFlag}">
                            <a onclick="auditInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">审核</a>
                        </c:if>
                        <c:if test="${fwhReAuditFlag && !xwkReAuditFlag}">
                            <a onclick="auditInfo('${tutor.doctorFlow}','reAudit');" style="cursor:pointer;color:blue;">重审</a>
                        </c:if>
                        <c:if test="${!fwhFlag}">
                            <a onclick="auditInfo('${tutor.doctorFlow}','view');" style="cursor:pointer;color:blue;">查看</a>
                        </c:if>
                    </c:if>
                    <c:if test="${param.role eq 'xwk'}">
                        <%--学位科审核标识--%>
                        <c:set var="xwkFlag" value="${tutor.fwhAuditStatusId eq 'Passed' && tutor.xwkAuditStatusId eq 'Passing'}"/>
                        <c:if test="${xwkFlag}">
                            <a onclick="auditInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">审核</a>
                        </c:if>
                        <c:if test="${xwkReAuditFlag}">
                            <a onclick="auditInfo('${tutor.doctorFlow}','reAudit');" style="cursor:pointer;color:blue;">重审</a>
                            <%--退回，导师可重新完善提交资料并审核--%>
                            <a onclick="backInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">退回</a>
                        </c:if>
                        <c:if test="${!xwkFlag}">
                            <a onclick="auditInfo('${tutor.doctorFlow}','view');" style="cursor:pointer;color:blue;">查看</a>
                        </c:if>
                    </c:if>
                </td>
                <input type="hidden" id="batchInput" value="${tutor.doctorFlow}" flag="${xwkFlag}"/>
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