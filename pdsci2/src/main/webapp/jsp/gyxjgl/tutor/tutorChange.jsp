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
            var url = "<s:url value='/gyxjgl/tutor/detailInfo?role=${param.role}&tutorFlow='/>"+tutorFlow+"&viewFlag="+viewFlag;
            jboxOpen(url, viewFlag=='view'?'查看':'审核',800,600);
        }
        $(function(){
            $("#orgName").likeSearchInit({});
        });

        function editInfo(tutorFlow,changeFlag){
            var url = "<s:url value='/gyxjgl/tutor/changeTutor?tutorFlow='/>"+tutorFlow+"&role=xwk"+"&changeFlag="+changeFlag;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,changeFlag=='view'?'查看':'编辑',860,600);
        }
        function blockInfo(doctorFlow,flag){
            var msg=flag=='Y'?"停用":"启用";
            jboxConfirm("确认"+msg+"此条信息？", function(){
                var url = "<s:url value='/gyxjgl/tutor/blockInfo?doctorFlow='/>"+doctorFlow+"&flag="+flag;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function exportTutors(){
            var url = "<s:url value='/gyxjgl/tutor/exportFromChange'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }

        //复选框事件
        //全选、取消全选、反选的事件
        function selectAll() {
            if($("#checkAll").is(':checked')){
                $(".check").prop("checked",true);
            }else{
                $(".check").prop("checked",false);
            }
        }
        //子复选框的事件
        function setSelectAll(obj){
            if(!$(obj).is(':checked')){
                $("#checkAll").prop("checked",false);
            }else{
                var checkAllLen = $("input[type='checkbox'][class='check']").length;
                var checkLen = $("input[type='checkbox'][class='check']:checked").length;
                if(checkAllLen == checkLen){
                    $("#checkAll").prop("checked",true);
                }
            }
        }
        function batchBlock(flag){
            var msg=flag=='Y'?"停用":"启用";
            if($(".check:checked").size()==0){
                jboxTip("至少勾选一条导师记录！");
                return;
            }
            var recordLst = [];
            $(".check:checked").each(function(){
                recordLst.push(this.value);
            })
            jboxConfirm("确认批量"+msg+"当前勾选的导师？", function(){
                var url = "<s:url value='/gyxjgl/tutor/blockInfoBatchOpt?recordLst='/>"+recordLst+"&opeFlag="+flag;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        location.reload();
                    }
                });
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gyxjgl/tutor/tutorChange?role=${param.role}"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="jsondata" type="hidden" name="jsondata" value=""/>
                <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
                <span style=""></span>导&ensp;&#8197;师&ensp;姓&ensp;&#8197;名：
                <input type="text" style="width:173px" name="doctorName" value="${param.doctorName}">
                <span style="padding-left:10px;"></span>导&ensp;&#8197;师&ensp;类&ensp;&#8197;型：
                <select class="validate[required] select" name="doctorTypeId" style="width:177px">
                    <option value="">请选择</option>
                    <option value="xsxbd" <c:if test="${param.doctorTypeId eq 'xsxbd'}">selected</c:if>>学术型博导</option>
                    <option value="xsxsd" <c:if test="${param.doctorTypeId eq 'xsxsd'}">selected</c:if>>学术型硕导</option>
                    <option value="zyxbd" <c:if test="${param.doctorTypeId eq 'zyxbd'}">selected</c:if>>专业型博导</option>
                    <option value="zyxsd" <c:if test="${param.doctorTypeId eq 'zyxsd'}">selected</c:if>>专业型硕导</option>
                </select>
                <span style="padding-left:10px;"></span>导&ensp;&#8197;师&ensp;状&ensp;&#8197;态：
                <select class="validate[required] select" name="blockFlag" style="width:177px">
                    <option value="">请选择</option>
                    <option value="N" <c:if test="${param.blockFlag eq 'N'}">selected</c:if>>启用</option>
                    <option value="Y" <c:if test="${param.blockFlag eq 'Y'}">selected</c:if>>停用</option>
                </select>
                <br/>
                <span style=""></span>新&ensp;&#8197;增&ensp;时&ensp;&#8197;间：
                <span>
                    <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime" value="${param.createTime}" onchange="checkTime(this)"/>
                    -- <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime2" value="${param.createTime2}" onchange="checkTime(this)"/>
                </span>
                <span style="padding-left:10px;"></span>申&ensp;&#8197;请&ensp;时&ensp;&#8197;间：
                <span>
                    <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="applyTime" value="${param.applyTime}" onchange="checkTime(this)"/>
                    -- <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="applyTime2" value="${param.applyTime2}" onchange="checkTime(this)"/>
                </span>
                <span style="padding-left:10px;"></span>认&ensp;&#8197;定&ensp;时&ensp;&#8197;间：
                <span>
                    <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="xwkAuditTime" value="${param.xwkAuditTime}" onchange="checkTime(this)"/>
                    -- <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="xwkAuditTime2" value="${param.xwkAuditTime2}" onchange="checkTime(this)"/>
                </span>
                <br/>
                <span style=""></span>退&ensp;&#8197;休&ensp;时&ensp;&#8197;间：
                <span>
                    <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="retireTime" value="${param.retireTime}" onchange="checkTime(this)"/>
                    -- <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="retireTime2" value="${param.retireTime2}" onchange="checkTime(this)"/>
                </span>
                <c:if test="${param.role eq 'xwk'}">
                    <span style="padding-left:10px;"></span>取&ensp;&#8197;消&ensp;时&ensp;&#8197;间：
                    <span>
                        <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="blockTime" value="${param.blockTime}" onchange="checkTime(this)"/>
                        -- <input type="text" style="width:75px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="blockTime2" value="${param.blockTime2}" onchange="checkTime(this)"/>
                    </span>
                    <span style="padding-left:10px;"></span>审&ensp;&#8197;核&ensp;状&ensp;&#8197;态：
                    <select name="xwkAuditStatusId" style="width:177px" class="select">
                        <option value="">请选择</option>
                        <option value="Passing" <c:if test="${param.xwkAuditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                        <option value="Passed" <c:if test="${param.xwkAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                        <option value="UnPassed" <c:if test="${param.xwkAuditStatusId eq 'UnPassed'}">selected</c:if>>审核不通过</option>
                    </select>
                    <br/>
                    <span style=""></span>分&ensp;&ensp;&ensp;委&ensp;&ensp;&ensp;会：
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
                    </select>
                    <span style="padding-left:10px;"></span>培&ensp;&#8197;养&ensp;单&ensp;&#8197;位：
                    <input id="orgName" type="text" style="width:173px" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" flow="${param.pydwOrgFlow}"/>&#12288;
                    <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:665px;">
                        <div id="boxHome" style="max-height:250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width:180px;border-top: none;position: relative;display: none;">
                            <c:forEach items="${orgList}" var="org">
                                <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                            </c:forEach>
                        </div>
                    </div><br/>
                    <input type="hidden" id="orgFlow" name="pydwOrgFlow" value="${param.pydwOrgFlow}"/>
                    <span style=""></span>培养单位审核：
                    <select name="pydwAuditStatusId" style="width:177px" class="select">
                        <option value="">请选择</option>
                        <option value="Passing" <c:if test="${param.pydwAuditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                        <option value="Passed" <c:if test="${param.pydwAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                        <option value="UnPassed" <c:if test="${param.pydwAuditStatusId eq 'UnPassed'}">selected</c:if>>审核不通过</option>
                    </select>
                </c:if>
                <span style="padding-left:10px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <input type="button" class="search" value="导&#12288;出" onclick="exportTutors()"/>
                <input type="button" class="search" value="批量停用" onclick="batchBlock('Y')"/>
                <input type="button" class="search" value="批量启用" onclick="batchBlock('N')"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <td style="min-width: 45px;"><input type="checkbox" name="checkAll" class="checkAll" value="Y" id="checkAll" onclick="selectAll()"/>全选</td>
                <th>导师姓名</th>
                <th>导师类型</th>
                <th width="120">培养单位</th>
                <th width="180">分委员会</th>
                <th>新增时间</th>
                <th>申请时间</th>
                <th>认定时间</th>
                <th>退休时间</th>
                <th>取消时间</th>
                <th>培养单位审核</th>
                <th>分委会审核</th>
                <th>研究生院审核</th>
                <th style="min-width:100px;">操作</th>
            </tr>
            <c:forEach items="${tutorList}" var="tutor">
                <tr>
                    <td><input type="checkbox" name="checkOne" class="check" value="${tutor.doctorFlow}" onclick="setSelectAll(this)" /></td>
                    <td>${tutor.doctorName}</td>
                    <td>${tutor.doctorTypeName}</td>
                    <td>${tutor.pydwOrgName}</td>
                    <td>${tutor.fwhOrgName}</td>
                    <td>${pdfn:transDate(tutor.createTime)}</td>
                    <td>${tutor.applyTime}</td>
                    <td>${tutor.xwkAuditTime}</td>
                    <td>${tutor.retireTime}</td>
                    <td>${tutor.blockTime}</td>
                    <td>${empty tutor.pydwAuditStatusName?'--':tutor.pydwAuditStatusName}</td>
                    <td>${empty tutor.fwhAuditStatusName?'--':tutor.fwhAuditStatusName}</td>
                    <td>${empty tutor.xwkAuditStatusName?'--':tutor.xwkAuditStatusName}</td>
                    <td>
                        <c:if test="${param.role eq 'xwk'}">
                            <c:if test="${tutor.applyFlag eq 'Y'}">
                                <a onclick="editInfo('${tutor.doctorFlow}','change');" style="cursor:pointer;color:blue;">编辑</a>
                                <a onclick="auditInfo('${tutor.doctorFlow}','view');" style="cursor:pointer;color:blue;">查看</a>
                            </c:if>
                            <c:if test="${tutor.applyFlag ne 'Y'}">待导师申请<span style="padding-left:3px;"></span></c:if>
                            <c:choose>
                                <c:when test="${tutor.blockFlag eq 'Y'}">
                                    <a onclick="blockInfo('${tutor.doctorFlow}','N');" style="cursor:pointer;color:blue;">启用</a>
                                </c:when>
                                <c:otherwise>
                                    <a onclick="blockInfo('${tutor.doctorFlow}','Y');" style="cursor:pointer;color:blue;">停用</a>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
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