<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        .table {
            border: 1px solid #e3e3e3;
        }

        .table tr:nth-child(2n) {
            background-color: #fcfcfc;
            transition: all 0.125s ease-in-out 0s;
        }

        .table tr:hover {
            background: #fbf8e9 none repeat scroll 0 0;
        }

        .table th, .table td {
            border-bottom: 1px solid #e3e3e3;
            border-right: 1px solid #e3e3e3;
            text-align: center;
        }

        .table th {
            background: rgba(0, 0, 0, 0) url("<s:url value='/jsp/res/disciple/images/table.png'/>") repeat-x scroll 0 0;
            color: #585858;
            height: 30px;
        }

        .table td {
            height: 30px;
            line-height: 25px;
            text-align: center;
            word-break: break-all;
        }
        .doctorTypeDiv {
            border: 0px;
            float: left;
            width: auto;
            line-height: 35px;
            height: 35px;
            text-align: right;
        }
        .doctorTypeLabel {
            border: 0px;
            float: left;
            width: 96px;
            line-height: 35px;
            height: 35px;
            text-align: right;
        }
        .doctorTypeContent {
            border: 0px;
            float: left;
            width: auto;
            line-height: 35px;
            height: 35px;
            text-align: right;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#doctorCategory").change(function(){
                $("#spe").empty();
                $("#spe").append($("."+this.value).clone());
                if(this.value=='${recDocCategoryEnumIntern.id}'){
                    $("#work").show();
                }else{
                    $("#work").hide();
                    $("#workOrgId").val("");
                }
            }).change();
        });

        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function toPage(page) {
            if (page) {
                $("#currentPage").val(page);
            }
            search();
        }
        function showInfo(infoFlow) {
            var width = (window.screen.width) * 0.7;
            var height = (window.screen.height) * 0.6;
            var url = "<s:url value='/res/disciple/discipleTeacherIndex?role=admin'/>&infoFlow=" + infoFlow;
            jboxOpen(url, "师承指导老师简况表", width, height);
        }
        function showBook(docFlow) {
            var width = (window.screen.width) * 0.7;
            var height = (window.screen.height) * 0.6;
            var url = "<s:url value='/res/bookStudyRecord/list?role=admin'/>&doctorFlow=" + docFlow;
            jboxOpen(url, "中医经典书籍学习记录", width, height);
        }
        function showGradutaion(gradutationFlow) {
            var width = (window.screen.width) * 0.7;
            var height = (window.screen.height) * 0.6;
            var url = "<s:url value='/res/graduation/showInfo?role=teacher'/>&recordFlow=" + gradutationFlow;
            jboxOpen(url, "跟师学习结业考核情况记录表", width, height);
        }
        function showRecord(doctorFlow, teacherFlow) {
            var currentPage = $("#currentPage").val();
            var width = (window.screen.width) * 0.7;
            var height = (window.screen.height) * 0.6;
            var roleId = "adminSee";
            var url = "<s:url value='/res/folowTeacherRecord/showFollowTeacherRecord'/>?teacherCurrentPage=" + currentPage + "&doctorFlow=" + doctorFlow + "&teacherFlow=" + teacherFlow + "&roleId=" + roleId;
            jboxOpen(url, "学员跟师记录查询", width, height);
        }
        function showCaseInfo(doctorFlow, teacherFlow) {
            var currentPage = $("#currentPage").val();
            var roleId = "adminSee";
            var width = (window.screen.width) * 0.7;
            var height = (window.screen.height) * 0.6;
            var url = "<s:url value='/res/typicalCases/showTypicalCases'/>?doctorFlow=" + doctorFlow + "&teacherFlow=" + teacherFlow + "&roleId=" + roleId + "&currentPage=" + currentPage;
            jboxOpen(url, "跟师医案", width, height);
        }
        function showNoteInfo(doctorFlow, roleScope, scope) {
            var width = (window.screen.width) * 0.8;
            var height = (window.screen.height) * 0.6;
            var title;
            if (scope == '${noteTypeEnumNote.id}') {
                title = "学习笔记";
            }
            if (scope == '${noteTypeEnumExperience.id}') {
                title = "跟师心得";
            }
            if (scope == '${noteTypeEnumBookExperience.id}') {
                title = "医籍学习体会";
            }
            url = "<s:url value='/res/discipleNote/showDiscipleNoteInfo/'/>" + roleScope + "/" + scope + "?doctorFlow=" + doctorFlow;
            jboxOpen(url, title, width, height);
            //window.location.href= url;
        }

        function showYearAss(doctorFlow, roleScope) {
            url = "<s:url value='/res/disciple/initAnnualAssessment/'/>" + roleScope + "?doctorFlow=" + doctorFlow;
            var width = (window.screen.width) * 0.8;
            var height = (window.screen.height) * 0.6;
            var title = "年度考核情况记录表";
            jboxOpen(url, title, width, height);
        }


    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/res/discipleAdminAudit/studentList'/>" method="post">
                <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
                <div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">培训基地：</label>
                        <select name="orgFlow"  id="orgFlow" class="qselect" >
                            <c:forEach items="${orgList}" var="org">
                                <option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">年&#12288;&#12288;级：</label>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                               readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">培训专业：</label>
                        <select id="doctorCategory" name="doctorCategoryId" class="qselect">
                            <option />
                            <c:forEach items="${recDocCategoryEnumList}" var="category">
                                <c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
                                <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
                                    <option value="${category.id}" ${param.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">对应专业：</label>
                        <select id="spe" name="speId" class="qselect">
                            <option></option>
                        </select>
                        <div style="display: none;">
                            <option
                                    class="
								${recDocCategoryEnumDoctor.id} |
								${recDocCategoryEnumGraduate.id} |
								${recDocCategoryEnumIntern.id} |
								${recDocCategoryEnumScholar.id} |
								${recDocCategoryEnumSpecialist.id} |
								${recDocCategoryEnumGeneralDoctor.id} |
								${recDocCategoryEnumWMFirst.id} |
								${recDocCategoryEnumWMSecond.id} |
								${recDocCategoryEnumAssiGeneral.id} |
								${recDocCategoryEnumChineseMedicine.id} |
								${recDocCategoryEnumTCMGeneral.id} |
								${recDocCategoryEnumTCMAssiGeneral.id} |
								" value="">请选择</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <option
                                        class="
								${recDocCategoryEnumDoctor.id} |
								${recDocCategoryEnumGraduate.id} |
								${recDocCategoryEnumIntern.id} |
								${recDocCategoryEnumScholar.id} |
								${recDocCategoryEnumSpecialist.id} |
								${recDocCategoryEnumGeneralDoctor.id} |
								" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
                                <option class="${recDocCategoryEnumWMFirst.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
                                <option class="${recDocCategoryEnumWMSecond.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
                                <option class="${recDocCategoryEnumAssiGeneral.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
                                <option class="${recDocCategoryEnumChineseMedicine.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
                                <option class="${recDocCategoryEnumTCMGeneral.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
                                <option class="${recDocCategoryEnumTCMAssiGeneral.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">师承老师：</label>
                        <input type="text" name="teacherName" value="${param.teacherName}" class="qtext"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">姓&#12288;&#12288;名：</label>
                        <input type="text" name="doctorName" value="${param.doctorName}" class="qtext"/>
                    </div>
                    <div class="doctorTypeDiv">
                        <div class="doctorTypeLabel">人员类型：</div>
                        <div class="doctorTypeContent">
                            <c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
                                <label><input type="checkbox" name="doctorTypeIdList" <c:if test="${empty param.doctorTypeIdList}" >checked</c:if> value="${type.dictId}"
                                    ${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
                    </div>
                </div>
            </form>
            <br>
            <table class="table" width="100%">
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>培训专业</th>
                    <th>对应专业</th>
                    <th>年级</th>
                    <th>师承指导<br/>老师简况表</th>
                    <th>跟师记录</th>
                    <th>跟师学习<br/>笔记</th>
                    <th>跟师心得</th>
                    <th>中医经典<br/>书籍学习记录</th>
                    <th>经典医籍<br/>学习体会</th>
                    <th>跟师医案</th>
                    <th>跟师学习<br/>年度考核情况记录表</th>
                    <th>跟师学习<br/>结业考核情况记录表</th>
                </tr>
                <c:forEach items="${list}" var="bean" varStatus="status">
                    <tr>
                        <td style="text-align: center;padding: 0px;">${status.index+1}</td>
                        <td style="text-align: center;padding: 0px;">${bean.userName}</td>
                        <td style="text-align: center;padding: 0px;">
                            <c:if test="${jszyFlag ne 'Y'}">${bean.doctorCategoryName}</c:if>
                            <c:if test="${jszyFlag eq 'Y'}">${bean.trainingTypeName}</c:if>
                        </td>
                        <td style="text-align: center;padding: 0px;">${bean.speName}</td>
                        <td style="text-align: center;padding: 0px;">${bean.sessionNumber}</td>
                        <td style="text-align: center;padding: 0px;">
                            <c:if test="${empty bean.infoFlow}">
                                无
                            </c:if>
                            <c:if test="${not empty bean.infoFlow}">
                                <a style="color: blue" href="javascript:void(0)"
                                   onclick="showInfo('${bean.infoFlow}');">查看</a>
                            </c:if>
                        </td>
                        <td style="text-align: center;padding: 0px;">
                            <a style="color: blue" href="javascript:void(0)"
                               onclick="showRecord('${bean.doctorFlow}','admin');">${bean.recordCount}</a>
                        </td>
                        <td style="text-align: center;padding: 0px;">
                            <a style="color: blue" href="javascript:void(0)"
                               onclick="showNoteInfo('${bean.doctorFlow}','admin','${noteTypeEnumNote.id}');">${bean.noteCount}</a>
                        </td>
                        <td style="text-align: center;padding: 0px;">
                            <a style="color: blue" href="javascript:void(0)"
                               onclick="showNoteInfo('${bean.doctorFlow}','admin','${noteTypeEnumExperience.id}');">${bean.expCount}</a>
                        </td>
                        <td style="text-align: center;padding: 0px;">
                            <a style="color: blue" href="javascript:void(0)"
                               onclick="showBook('${bean.doctorFlow}','admin');">${bean.bookCount}</a>
                        </td>
                        <td style="text-align: center;padding: 0px;">
                            <a style="color: blue" href="javascript:void(0)"
                               onclick="showNoteInfo('${bean.doctorFlow}','admin','${noteTypeEnumBookExperience.id}');">${bean.bookExpCount}</a>
                        </td>
                        <td style="text-align: center;padding: 0px;">
                            <a style="color: blue" href="javascript:void(0)"
                               onclick="showCaseInfo('${bean.doctorFlow}','admin');">${bean.casesCount}</a>
                        </td>
                        <td style="text-align: center;padding: 0px;">
                            <a style="color: blue" href="javascript:void(0)"
                               onclick="showYearAss('${bean.doctorFlow}','admin');">${bean.yearCount}</a>
                        </td>
                        <td style="text-align: center;padding: 0px;">
                            <c:if test="${empty bean.gradutationFlow}">
                                学员未提交结业考核表
                            </c:if>
                            <c:if test="${not empty bean.gradutationFlow}">
                                <a style="color: blue" href="javascript:void(0)"
                                   onclick="showGradutaion('${bean.gradutationFlow}');">查看</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty list}">
                    <tr>
                        <td style="text-align: center;" colspan="99">暂无信息！</td>
                    </tr>
                </c:if>
            </table>
            <div>
                <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
                <pd:pagination toPage="toPage"/>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>