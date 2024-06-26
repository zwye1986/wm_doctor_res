<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript"
        src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    table.grid th, table.grid td {
        padding: 0;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $("#currentPage").val("${param.currentPage}");
        <c:if test="${not empty list}">
        var style={"margin-top":"5px","width":"940px"};
        var options ={
            "colums":3//根据固定列的数量
        };
        $("#dataTable").Scorll(options,style,false,null);
        </c:if>

    });

    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/subjectList'/>?roleFlag=${roleFlag}", $("#searchForm").serialize(), false);
    }


    function selectUser() {
        var url = "<s:url value='/jsres/supervisio/selectUser'/>";
        jboxOpen(url, null, 800, 600);
    }

    function exportUser(subjectName,manageUserFlow,expertUserFlow) {
        var url = "<s:url value='/jsres/supervisio/exportSupervisioUserInfo'/>?userFlow="+manageUserFlow+','+expertUserFlow+"&subjectName="+subjectName;
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
    function revokeScore(subjectActivitiFlows,subjectFlow) {
        var url = "<s:url value='/jsres/supervisio/revokeScoreMain'/>?subjectActivitiFlows="+subjectActivitiFlows+"&subjectFlow="+subjectFlow;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe,"撤销评分", 690, 400, false);
    }
    /*function setFeedback(subjectActivitiFlows,type) {
        var title = "上传督导反馈";
        var url = "<s:url value='/jsres/supervisio/addFeedback'/>?subjectActivitiFlows="+subjectActivitiFlows+"&type="+type;
        if(type == 'spe'){
            title = "上传专业反馈";
            url = "<s:url value='/jsres/supervisio/addFeedback'/>?subjectFlow="+subjectActivitiFlows+"&type="+type;
        }
        jboxOpen(url,title, 600, 280);
    }*/
    function setFeedback(subjectActivitiFlows,type,isRead,speName) {
        var url ="";
        var  title = "报告";
        if(type == 'spe'){
            //按照项目
            url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectFlow="+subjectActivitiFlows+"&type="+type+"&isRead="+isRead+"&speName="+speName;
            jboxOpen(url,title, 1000, 666);
        }
        if(type == 'major'|| type=='management'){
            //按照批次
            url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectActivitiFlows="+subjectActivitiFlows+"&type="+type+"&isRead="+isRead+"&speName="+speName;
            jboxOpen(url,title, 1000, 800);
        }

    }
    function downFeedback(fileUrl,type) {
        jboxTip("下载中…………");
        var url = "<s:url value='/jsres/supervisio/downFeedbackFile'/>?fileUrl=" + fileUrl + "&type=" + type;
        window.location.href = url;
    }
    function exportReport(subjectActivitiFlows,type) {
        var url ="";
        if(type == 'spe'){
            url ="<s:url value='/jsres/supervisio/exportReport'/>?subjectActivitiFlows="+subjectActivitiFlows+"&type="+type;
        }
        if(type == 'major'){
            url ="<s:url value='/jsres/supervisio/exportReport'/>?&subjectFlow="+subjectActivitiFlows+"&type="+type;
        }
        jboxTip("导出中…………");
        top.jboxExp(null,url);
        jboxEndLoading();
    }

    function showRecords(subjectFlow,roleFlag,subjectActivitiFlows) {
        var url ="<s:url value='/jsres/supervisio/showRecords'/>?subjectFlow="+subjectFlow+"&roleFlag="+roleFlag+"&subjectActivitiFlows="+subjectActivitiFlows;
        jboxOpen(url,"提交记录", 1022, 666);
    }
</script>
<c:if test="${empty list}">
    <div class="search_table" style="width: 100%;padding: 0 20px">
        <table class="grid" >
            <tr>
                <th style="width: 130px;min-width: 130px;max-width: 110px;" class="toFiexd">项目名称</th>
                <th style="width: 80px;min-width:80px;max-width:80px;" class="fixed">基地代码</th>
                <th style="width: 130px;min-width:130px;max-width:130px;" class="fixed">检查基地</th>
                <th style="width: 110px;min-width:110px;max-width:110px;" class="fixed">检查专业</th>
                <th style="width: 70px;min-width:70px;max-width:70px;" class="fixed">检查年份</th>
                <th  style="width: 95px;min-width:  95px;max-width:  95px;" class="toFiexd">管理专家</th>
                <th  style="width: 95px;min-width: 95px;max-width: 95px;" class="toFiexd">专业专家</th>
                <th style="width: 80px;min-width:80px;max-width:80px;" class="fixed">督导结果</th>
                <th style="width: 100px;min-width:100px;max-width:100px;" class="fixed">操作</th>
            </tr>
            <tr>
                <td colspan="8">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="main_bd clearfix">
        <table class="grid" style="width: auto;" id="dataTable">
            <thead>
            <th style="width: 130px;min-width: 130px;max-width: 110px;" class="toFiexd">项目名称</th>
            <th style="width: 80px;min-width:80px;max-width:80px;" class="fixed">基地代码</th>
            <th style="width: 130px;min-width:130px;max-width:130px;" class="fixed">检查基地</th>
            <th style="width: 110px;min-width:110px;max-width:110px;" class="fixed">检查专业</th>
            <th style="width: 70px;min-width:70px;max-width:70px;" class="fixed">检查年份</th>
            <th  style="width: 95px;min-width:  95px;max-width:  95px;" class="toFiexd">管理专家</th>
            <th  style="width: 95px;min-width: 95px;max-width: 95px;" class="toFiexd">专业专家</th>
            <th style="width: 80px;min-width:80px;max-width:80px;" class="fixed">督导结果</th>
            <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL ||  roleFlag eq 'management' }">
                <th style="min-width: 150px; max-width: 150px; "  class="fixedBy">督导反馈</th>
            </c:if>
            <th style="min-width: 150px; max-width: 150px; "  class="fixedBy">专业基地反馈</th>
            <c:if test="${roleFlag ne  'expertLeader' }">
                <th style="min-width: 150px; max-width: 150px; "  class="fixedBy">基地自评反馈</th>
            </c:if>
            <th style="width: 80px;min-width:80px;max-width:80px;" class="fixed">提交记录</th>
            <th style="width: 100px;min-width:182px;max-width:100px;" class="fixed">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="s">
                <tr class="fixTrTd">
                    <td class="fixedBy" style="height: 40px">${s.subjectName}</td>
                    <td class="tdClass" >${s.baseCode==null?"暂无":s.baseCode}</td>
                    <td class="tdClass">${s.orgName}</td>
                    <td class="tdClass">${s.speName}</td>
                    <td class="tdClass">${s.subjectYear}</td>
                    <td><label >${s.manageUserName==null?"暂无":s.manageUserName}</label>&#12288;</td>
                    <td><label >${s.expertUserName==null?"暂无":s.expertUserName}</label>&#12288;</td>
                    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL ||  roleFlag eq 'management' }">
                        <td>
                            <c:choose>
                                <c:when test="${s.supervisioResults eq 'qualified'}">合格</c:when>
                                <c:when test="${s.supervisioResults eq 'basically'}">基本合格</c:when>
                                <c:when test="${s.supervisioResults eq 'yellowCard'}">限时整改</c:when>
                                <c:when test="${s.supervisioResults eq 'redCard'}">撤销资格</c:when>
                                <c:otherwise>暂无结果</c:otherwise>
                            </c:choose>
                        </td>
                    </c:if>
                    <c:if test="${roleFlag ne GlobalConstant.USER_LIST_GLOBAL && roleFlag ne 'management' }">
                        <td>暂无结果</td>
                    </c:if>
                    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL ||  roleFlag eq 'management' }">
                        <td style="min-width: 150px; max-width: 150px; " class="by">
                            <a class="btn_blue" style="width: 56px;color: white" href="javascript:void(0);"  onclick="setFeedback('${s.subjectActivitiFlows}','management','Y');">查看</a>
                        </td>
                    </c:if>
                    <td style="min-width: 150px; max-width: 150px; " class="by">
                        <c:if test="${roleFlag eq 'expertLeader' or roleFlag eq 'management'  or roleFlag eq GlobalConstant.USER_LIST_GLOBAL }">
                            <a class="btn_blue" style="width: 56px;color: white" href="javascript:void(0);"  onclick="setFeedback('${s.subjectFlow}','spe','Y','${s.speName}');">查看</a>
                        </c:if>
                    </td>
                    <c:if test="${roleFlag ne  'expertLeader' }">
                        <td style="min-width: 150px; max-width: 150px; " class="by">
                            <c:if test="${roleFlag eq 'expertLeader' or roleFlag eq 'management'  or roleFlag eq GlobalConstant.USER_LIST_GLOBAL }">
                                <a class="btn_blue" style="width: 56px;color: white" href="javascript:void(0);"  onclick="setFeedback('${s.subjectActivitiFlows}','major');">查看</a>
                            </c:if>
                        </td>
                    </c:if>

                    <td>
                        <c:if test="${roleFlag eq 'management'  or roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
                            <a class="btn_blue" style="color: white" href="javascript:void(0);" onclick="showRecords('${s.subjectFlow}','${roleFlag}','${s.subjectActivitiFlows}');">查看</a>
                        </c:if>
                        <c:if test="${roleFlag ne 'management'  and roleFlag ne GlobalConstant.USER_LIST_GLOBAL}">
                            <a class="btn_blue" style="color: white" href="javascript:void(0);" onclick="showRecords('${s.subjectFlow}','${roleFlag}','');">查看</a>
                        </c:if>

                    </td>

                    <td>
                        <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
                            <a class="btn_blue" style="width: 30px;margin-top: 3px;margin-right: 109px;color: white"
                               href="javascript:void(0);"
                               onclick="editSupervisioSubject('edit','${s.subjectFlow}');">修改</a>
                            <a class="btn_blue" style="width: 56px;margin-top: -32px;margin-left: 69px;color: white"
                               href="javascript:void(0);"
                               onclick="showPlanInfo('${s.speId}','${s.subjectFlow}','${roleFlag}','${s.subjectActivitiFlows}','${s.manageUserFlow}');">查看评分</a>
                            <br>
                            <a class="btn_blue" style="width: 30px;margin-top: 3px;margin-left: -41px;color: white"
                               href="javascript:void(0);"
                               onclick="delSupervisioSubject('${s.subjectFlow}');">删除</a>
                            <a class="btn_blue" style="width: 56px;margin-top: 3px;margin-right: -31px;color: white"
                               href="javascript:void(0);"
                               onclick="revokeScore('${s.subjectActivitiFlows}','${s.subjectFlow}');">撤销评分</a>
                            <a class="btn_blue" style="width: 132px;margin-top: 2px;margin-right: 7px;color: white"
                               href="javascript:void(0);"
                               onclick="exportUser('${s.subjectName}','${s.manageUserFlow}','${s.expertUserFlow}');">导出专家信息</a>
                        </c:if>
                        <c:if test="${roleFlag eq 'expertLeader' ||  roleFlag eq 'management'}">
                            <a class="btn_blue" style="width: 56px;color: white" href="javascript:void(0);"
                               onclick="showPlanInfo('${s.speId}','${s.subjectFlow}','${roleFlag}','${s.subjectActivitiFlows}','${s.manageUserFlow}');">查看评分</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
</div>
<div class="page" style="text-align: center">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
</div>
