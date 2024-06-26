<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="true" />
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
    <script>
        $(document).ready(function(){
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
        });
        var yearJson = {"OneYear":"一年","TwoYear":"两年","ThreeYear":"三年"};
        function search(){
            $("#searchForm").submit();
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        function doctorScore(param1,param2){
            var url='<s:url value="/osca/provincial/doctorScore?clinicalFlow="/>'+param1+"&doctorFlow="+param2;
            jboxOpen(url,"详情",650,150);
        }
        function exportExcel(){
            jboxSubmit($("#searchForm"),'<s:url value="/osca/provincial/exportExcel"/>',null,null,false);
            jboxEndLoading();
        }
        function editGradeOpt(clinicalFlow,doctorFlow,subjectFlow){
            jboxOpen("<s:url value='/osca/base/editGradeOpt?doctorFlow='/>"+doctorFlow+"&clinicalFlow="
                    +clinicalFlow+"&subjectFlow="+subjectFlow+"&role=province", "成绩编辑",480,530);
        }
        function releaseScores(){
            var clinicalFlows = "";
            <c:forEach items="${clinicalFlows}" var="clinicalFlow">
                clinicalFlows += "${clinicalFlow},"
            </c:forEach>
            if(!clinicalFlows){
                jboxTip("请先查询结果");
                return;
            }else {
                clinicalFlows=clinicalFlows.substring(0,clinicalFlows.length-1);
            }
            var url = "<s:url value='/osca/provincial/releaseScores'/>?clinicalFlows="+clinicalFlows;
            jboxPost(url,null, function (resp) {
                if(resp=="1") jboxTip("发布成功");
            },null,false);
        }
        function linkageSubject(dictId){
            $('#trainingSpeId').val("");//清空上次展现数据
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="queryDiv">
        <form id="searchForm" action="<s:url value='/osca/provincial/doctorScoreList'/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <%--<table class="basic" style="width:100%; margin-bottom: 15px; margin-top: 10px;border: none">--%>
                <%--<tr>--%>
                    <%--<td style="border: none">--%>
            <div class="inputDiv">
                培训类型：
                <select name="trainingTypeId" class="qselect" onchange="linkageSubject(this.value)">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                        <option value="${dict.dictId}" ${param.trainingTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="inputDiv">
                培训专业：
                <c:set value="OscaTrainingType.${param.trainingTypeId}" var="trainingTypeClass"></c:set>
                <select id="trainingSpeId" name="speId" class="qselect" onchange="selectSpe1()">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                        <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
                        <c:forEach items="${applicationScope[dictKey]}" var="scope">
                            <option class="${scope.dictTypeId}" value="${scope.dictId}" ${(param.speId eq scope.dictId && trainingTypeClass eq scope.dictTypeId)?'selected':''}>${scope.dictName}</option>
                        </c:forEach>
                    </c:forEach>
                </select>
            </div>
                        <div class="inputDiv">
                            <label class="qlable">考核年份：</label>
                            <input type="text" class="qtext" value="${param.clinicalYear}" name="clinicalYear" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">考点名称：</label>
                            <select name="orgFlow" class="qselect">
                                <option value="">全部</option>
                                <c:forEach items="${examOrgList}" var="dict">
                                    <option value="${dict.orgFlow}"
                                            <c:if test="${dict.orgFlow eq param.orgFlow}">selected</c:if>>${dict.orgName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">培训基地：</label>
                            <select name="trainingOrgFlow" class="qselect">
                                <option value="">全部</option>
                                <c:forEach items="${orgList}" var="org">
                                <option value="${org.orgFlow}" ${org.orgFlow eq param.trainingOrgFlow?'selected':''}>${org.orgName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <div style="border: none">
                        <div class="inputDiv">
                            <label class="qlable">考试阶段：</label>
                            <select name="trainCategoryId" class="qselect">
                                <option value="">全部</option>
                                <c:if test="${not empty trainCategoryEnumList}">
                                    <c:forEach items="${trainCategoryEnumList}" var="cate">
                                        <option value="${cate.id}" ${param.trainCategoryId eq cate.id?'selected':''}>${cate.name}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">考核结果：</label>
                            <select name="resultId" class="qselect">
                                <option value="">全部</option>
                                <c:if test="${not empty doctorScoreEnumList}">
                                    <c:forEach items="${doctorScoreEnumList}" var="rlt">
                                        <option value="${rlt.id}" ${param.resultId eq rlt.id?'selected':''}>${rlt.name}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">身份证号：</label>
                            <input type="text" class="qtext" name="idNo" value="${param.idNo}">
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">姓&#12288;&#12288;名：</label>
                            <input type="text" class="qtext" name="gradeDoctorName" value="${param.gradeDoctorName}">
                        </div>
                    </div>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>--%>
                        <div class="inputDiv">
                            <label class="qlable">地&#12288;&#12288;区：</label>
                            <select name="orgCityId" class="qselect">
                            <option value="">全部</option>
                            <option value="320100" ${param.orgCityId eq 320100?"selected":""}>南京市</option>
                            <option value="320200" ${param.orgCityId eq 320200?"selected":""}>无锡市</option>
                            <option value="320300" ${param.orgCityId eq 320300?"selected":""}>徐州市</option>
                            <option value="320400" ${param.orgCityId eq 320400?"selected":""}>常州市</option>
                            <option value="320500" ${param.orgCityId eq 320500?"selected":""}>苏州市</option>
                            <option value="320600" ${param.orgCityId eq 320600?"selected":""}>南通市</option>
                            <option value="320700" ${param.orgCityId eq 320700?"selected":""}>连云港市</option>
                            <option value="320800" ${param.orgCityId eq 320800?"selected":""}>淮安市</option>
                            <option value="320900" ${param.orgCityId eq 320900?"selected":""}>盐城市</option>
                            <option value="321000" ${param.orgCityId eq 321000?"selected":""}>扬州市</option>
                            <option value="321100" ${param.orgCityId eq 321100?"selected":""}>镇江市</option>
                            <option value="321200" ${param.orgCityId eq 321200?"selected":""}>泰州市</option>
                            <option value="321300" ${param.orgCityId eq 321300?"selected":""}>宿迁市</option>
                        </select>
                        </div>

                        <div class="lastDiv">
                            <input type="button" value="查&#12288;询" onclick="search()" class="searchInput">
                        </div>
                        <div class="lastDiv">
                            <input type="button" value="导&#12288;出" onclick="exportExcel()" class="searchInput">
                        </div>
                        <div class="lastDiv">
                            <input type="button" value="成绩发布" onclick="releaseScores()" class="searchInput">
                        </div>

                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
        </form>
        </div>
        <table class="xllist" style="margin-top: 10px;">
            <tr>
                <th>序号</th>
                <th>姓名</th>
                <th>考核年份</th>
                <th>考核名称</th>
                <th>培训年限</th>
                <th>证件号码</th>
                <th>培训届别</th>
                <th>培训基地</th>
                <th>所在考点</th>
                <th>考核专业</th>
                <th>成绩</th>
                <th>是否通过</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${resultMapList}" var="item" varStatus="s">
                <tr>
                    <c:set var="key" value="${item['clinicalFlow']}${item['doctorFlow']}"></c:set>
                    <td>${s.index+1}</td>
                    <td>${item['doctorName']}</td>
                    <td>${item['clinicalYear']}</td>
                    <td>${item['clinicalName']}</td>
                    <td id="trainingYear${s.index+1}"></td>
                    <script>
                        $("#trainingYear${s.index+1}").text(yearJson["${item['trainingYears']}"]);
                    </script>
                    <td>${item['idNo']}</td>
                    <td>${item['sessionNumber']}</td>
                    <td>${item['rdorgName']}</td>
                    <td>${item['osorgName']}</td>
                    <td>${item['speName']}</td>
                    <td style="cursor: pointer" onclick="doctorScore( '${item['clinicalFlow']}','${item['doctorFlow']}')"><a style="color: #4195c5;">${scoreMap[key]}</a></td>
                    <td>${item['isPassName']} </td>
                    <td style="cursor: pointer" onclick="editGradeOpt('${item['clinicalFlow']}','${item['doctorFlow']}','${item['subjectFlow']}')"><a style="color: #4195c5;">编辑</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty resultMapList}">
                <tr>
                    <td colspan="13">无记录</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
