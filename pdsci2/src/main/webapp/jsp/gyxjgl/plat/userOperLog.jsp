<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function search(){
            $("#searchForm").submit();
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/gyxjgl/user/usersOperLog'/>" method="post">
            <input name="currentPage" id="currentPage" type="hidden" value="">
            <input name="aim" type="hidden" value="${aim}">
            <table  style="width:100%;min-width: 1080px; margin-bottom: 10px; margin-top: 10px;border: none;">
                <tr>
                    <td style="border: none;line-height: 260%;">
                        学号范围：<input type="text" name="startStudyId" value="${param.startStudyId}" style="width: 137px;">
                        至
                        <input type="text" name="endStudyId" value="${param.endStudyId}" style="width: 137px;">
                        &#12288;
                        年&#12288;&#12288;级：<input type="text" name="gradeNumber" value="${param.gradeNumber}" style="width: 137px;" onclick="WdatePicker({dateFmt:'yyyy'})">
                        &#12288;
                        修&nbsp;改&nbsp;项：<select name="changeItem" style="height: 21px;width: 141px;">
                        <c:if test="${aim eq 'xj'}">
                            <option value="">全部</option>
                            <option <c:if test="${param.changeItem eq '学号'}">selected</c:if>>学号</option>
                            <option <c:if test="${param.changeItem eq '姓名'}">selected</c:if>>姓名</option>
                            <option <c:if test="${param.changeItem eq '证件号码'}">selected</c:if>>证件号码</option>
                            <option <c:if test="${param.changeItem eq '性别'}">selected</c:if>>性别</option>
                            <option <c:if test="${param.changeItem eq '民族'}">selected</c:if>>民族</option>
                            <option <c:if test="${param.changeItem eq '出生日期'}">selected</c:if>>出生日期</option>
                            <option <c:if test="${param.changeItem eq '专业名称'}">selected</c:if>>专业名称</option>
                            <option <c:if test="${param.changeItem eq '学位分委员会'}">selected</c:if>>学位分委员会</option>
                            <option <c:if test="${param.changeItem eq '培养单位'}">selected</c:if>>培养单位</option>
                            <option <c:if test="${param.changeItem eq '培养类型'}">selected</c:if>>培养类型</option>
                            <option <c:if test="${param.changeItem eq '是否5+3培养模式'}">selected</c:if>>是否5+3培养模式</option>
                            <option <c:if test="${param.changeItem eq '导师一'}">selected</c:if>>导师一</option>
                            <option <c:if test="${param.changeItem eq '导师二'}">selected</c:if>>导师二</option>
                            <option <c:if test="${param.changeItem eq '考生编号'}">selected</c:if>>考生编号</option>
                            <option <c:if test="${param.changeItem eq '考生来源'}">selected</c:if>>考生来源</option>
                            <option <c:if test="${param.changeItem eq '政治面貌'}">selected</c:if>>政治面貌</option>
                            <option <c:if test="${param.changeItem eq '入党（团）时间'}">selected</c:if>>入党（团）时间</option>
                            <option <c:if test="${param.changeItem eq '退学时间'}">selected</c:if>>退学时间</option>
                        </c:if>
                        <c:if test="${aim eq 'cj'}">
                            <option value="成绩管理">成绩管理</option>
                        </c:if>
                        </select>
                        <br/>
                        修改日期：<input type="text" name="startChangeTime" value="${param.startChangeTime}" style="width: 137px;"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" >
                        至
                        <input type="text" name="endChangeTime" value="${param.endChangeTime}" style="width: 137px;"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" >
                        &#12288;
                        姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" style="width: 137px;">&#12288;
                        <input type="button" value="查&#12288;询" class="search" onclick="search();"/>
                    </td>
                </tr>
            </table>
        </form>
        <table class="basic" style="margin-top: 10px;width:100%;">
            <tr style="font-weight: bold;">
                <td style="width: 10%;text-align: center;padding-left: 0px;">年级</td>
                <td style="width: 10%;text-align: center;padding-left: 0px;">学号</td>
                <td style="width: 10%;text-align: center;padding-left: 0px;">姓名</td>
                <td style="width: 15%;text-align: center;padding-left: 0px;">修改项</td>
                <td style="width: 15%;text-align: center;padding-left: 0px;">原数据</td>
                <td style="width: 15%;text-align: center;padding-left: 0px;">新数据</td>
                <td style="width: 15%;text-align: center;padding-left: 0px;">修改时间</td>
                <td style="width: 10%;text-align: center;padding-left: 0px;">修改账户</td>
            </tr>
            <c:forEach items="${logs}" var="log" varStatus="s">
                <tr>
                    <td style="text-align: center;padding-left: 0px;">${log.gradeNumber}</td>
                    <td style="text-align: center;padding-left: 0px;">${log.studyId}</td>
                    <td style="text-align: center;padding-left: 0px;">${log.userName}</td>
                    <td style="line-height: 130%;">${log.changeItem} <c:if test="${log.changeItem eq '成绩管理'}">
                    [${log.logDesc}]
                    </c:if></td>
                    <td class="oldData">
                        <c:choose>
                            <c:when test="${log.oldData==GlobalConstant.FLAG_Y }">
                                通过
                            </c:when>
                            <c:when test="${log.oldData==GlobalConstant.FLAG_N}">
                                不通过
                            </c:when>
                            <c:when test="${log.oldData==GlobalConstant.FLAG_F}">
                                缺考
                            </c:when>
                            <c:otherwise>
                                ${log.oldData}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="newData" style="text-align: center;padding-left: 0px;">
                        <c:choose>
                            <c:when test="${log.newData==GlobalConstant.FLAG_Y }">
                                通过
                            </c:when>
                            <c:when test="${log.newData==GlobalConstant.FLAG_N}">
                                不通过
                            </c:when>
                            <c:when test="${log.newData==GlobalConstant.FLAG_F}">
                                缺考
                            </c:when>
                            <c:otherwise>
                                ${log.newData}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <script>
                        var oldData = $(".oldData").eq(${s.index}).text();
                        var newData = $(".newData").eq(${s.index}).text();
                        var oldDatas = oldData.split("");
                        var newDatas = newData.split("");
                        var result ="";
                        var result2 ="";
                        for(var i=0;i<oldDatas.length;i++){
                            if(oldDatas[i]==newDatas[i]){
                                result = result+oldDatas[i];
                            }else{
                                result = result +'<font color="red" size="3">'+oldDatas[i]+'</font>';
                            }
                            $(".oldData").eq(${s.index}).html(result);
                        }
                        for(var i=0;i<newDatas.length;i++){
                            if(oldDatas[i]==newDatas[i]){
                                result2 = result2+newDatas[i];
                            }else{
                                result2 = result2 +'<font color="red" size="3">'+newDatas[i]+'</font>';
                            }
                            $(".newData").eq(${s.index}).html(result2);
                        }
                    </script>
                    <td style="text-align: center;padding-left: 0px;">${pdfn:transDateTime(log.changeTime)}</td>
                    <td style="text-align: center;padding-left: 0px;">${log.changeUserCode}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty logs}">
                <tr>
                    <td colspan="8">无记录</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(logs)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
