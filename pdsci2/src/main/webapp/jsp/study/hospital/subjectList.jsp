<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
<script type="text/javascript">
    $(document).ready(function () {
        $('.appoint,.differ').bind('click',function(){
            var subjectFlow = $(this).attr("subjectFlow");
            var url = "<s:url value='/study/hospital/detailInfoManage'/>?subjectFlow="+subjectFlow;
            jboxPostLoad("initCont",url,null,true);
        });
        $('.appoint').bind('hover',function(){
            $(this).attr("title","请点击进入");
        });
    });
    function search() {
        jboxStartLoading();
        $("#searchForm").submit();
    }
    function detail(subjectFlow,isEdit) {
        if(isEdit=="Y"){
            if(!subjectFlow)
                jboxOpen("<s:url value='/study/hospital/subjectDetail'/>" , '新增课程信息', 700, 450);
            else
                jboxOpen("<s:url value='/study/hospital/subjectDetail'/>?subjectFlow="+subjectFlow , '编辑课程信息', 700, 450);
        }else{
            jboxOpen("<s:url value='/study/hospital/showSubject'/>?subjectFlow="+subjectFlow , '查看课程信息', 700, 450);
        }
    }
    function del(subjectFlow) {
        jboxConfirm("确认删除？删除后无法恢复！", function () {
            jboxPost("<s:url value='/study/hospital/delSubject'/>?subjectFlow=" + subjectFlow, null, function (resp) {
                jboxEndLoading();
                if (resp == '${GlobalConstant.DELETE_SUCCESSED}') {
                    jboxTip("删除成功！");
                    toPage(1);
                }
            }, null, false);
        });
    }
    function release(subjectFlow) {
        jboxConfirm("确认发布？发布后无法编辑！", function () {
            jboxPost("<s:url value='/study/hospital/release'/>?subjectFlow=" + subjectFlow, null, function (resp) {
                jboxEndLoading();
                if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                    jboxTip("发布成功！");
                    toPage(1);
                }
            }, null, false);
        });
    }
    function toPage(page) {
        if(page){
            $("#currentPage").val(page);
        }
        search();
    }
    function exportExl(){
        var url = "<s:url value='/study/hospital/exportSubjects'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"),url);
    }
</script>
</head>
<body id="initCont">
<div class="mainright">
    <div class="content">
            <form id="searchForm" action="<s:url value="/study/hospital/subjectManage"/>" method="post">
                <div class="queryDiv">
                        <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                        <div class="inputDiv" style="min-width:400px;">
                            <label class="qlable">课程名称：</label>
                            <input type="text"style="min-width:300px;" class="qtext" name="subjectName" value="${param.subjectName}">
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">课程年份：</label>
                            <input type="text" class="qtext" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" name="subjectYear" value="${param.subjectYear}"/>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">课程类型：</label>
                            <select name="subjectType" class="xlselect" >
                                <option value="">全部</option>
                                <c:forEach items="${dictTypeEnumCourseTypeList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${dict.dictId eq param.subjectType}">selected</c:if>> ${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    <div class="lastDiv"  >
                        <input type="button" class="searchInput" value="查&#12288;询" onclick="toPage(1)"/>
                    </div>
                </div>
                <div class="funcDiv"  >
                    <input type="button" class="searchInput" value="新&#12288;增" onclick="detail('','Y')"/>&#12288;
                    <input type="button" class="searchInput" value="导&#12288;出" onclick="exportExl()">
                </div>
            </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th width="20px">序号</th>
                <th width="50px">课程年份</th>
                <th width="100px">课程名称</th>
                <th width="70px">开始时间</th>
                <th width="70px">结束时间</th>
                <th width="50px">预约容量</th>
                <th width="100px">课程类型</th>
                <th width="60px">操作</th>
            </tr>
            <c:forEach items="${list}" var="subject" varStatus="s">
                <tr>
                    <td class="${subject.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${subject.subjectFlow}">${(currentPage-1)*pageSize+ s.count}</td>
                    <td class="${subject.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${subject.subjectFlow}">${subject.subjectYear}</td>
                    <td class="${subject.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${subject.subjectFlow}">${subject.subjectName}</td>
                    <td class="${subject.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${subject.subjectFlow}">${subject.subjectStartTime}</td>
                    <td class="${subject.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${subject.subjectFlow}">${subject.subjectEndTime}</td>
                    <td class="${subject.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${subject.subjectFlow}">${subject.reservationsNum}</td>
                    <td class="${subject.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${subject.subjectFlow}">
                        <c:forEach items="${dictTypeEnumCourseTypeList}" var="dict">
                            <c:if test="${dict.dictId eq subject.subjectType}">${dict.dictName}</c:if>
                        </c:forEach>
                    </td>
                    <c:if test="${subject.postStatus ne 'Y'}" >
                        <td>
                            <a onclick="release('${subject.subjectFlow}','Y')"  style="cursor: pointer">[发布]</a>
                            <a onclick="detail('${subject.subjectFlow}','Y')" style="cursor: pointer">[编辑]</a>
                            <a onclick="del('${subject.subjectFlow}')" style="cursor: pointer">[删除]</a>
                        </td>
                    </c:if>
                    <c:if test="${subject.postStatus eq 'Y'}">
                        <td>
                            [已发布]
                            <a onclick="detail('${subject.subjectFlow}','N')" style="cursor: pointer">[查看]</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty list}">
                <tr>
                    <td colspan="8">无记录</td>
                </tr>
            </c:if>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
        <pd:pagination toPage="toPage"/>
    </div>
</div>

</body>
</html>


