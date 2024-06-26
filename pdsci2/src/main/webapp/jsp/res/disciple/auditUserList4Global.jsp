<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <style type="text/css">
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

    <script type="text/javascript"
            src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

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
        function toPage(page) {
            var currentPage = "";
            if (!page || page != undefined) {
                currentPage = page;
            }
            if (page == undefined || page == "") {
                currentPage = 1;
            }
            $("#currentPage").val(currentPage);
            searchByName();
        }
        function checkRecord(doctorFlow, teacherFlow) {
            var url;
            if ("${scope}" == "ndkhsh") {//年度考核审核
                url = "<s:url value='/res/disciple/initAnnualAssessment/${roleScope}?doctorFlow='/>" + doctorFlow + "&teacherFlow=" + teacherFlow + "&operaFlag=${GlobalConstant.FLAG_Y}";
            } else {
                url = "<s:url value='/res/discipleNote/showDiscipleNoteInfo/${roleScope}/${scope}?doctorFlow='/>" + doctorFlow + "&teacherFlow=" + teacherFlow + "&operaFlag=${GlobalConstant.FLAG_Y}";
            }
            window.location.href = url;
        }
        function searchByName() {
            $("#searchForm").submit();
        }

        function agreeRecordBatch(exp){
            var url
            if ("${scope}" == "ndkhsh") {//年度考核审核
                url = "<s:url value='/res/disciple/annualAssessmentOneKeyAudit'/>" ;
            }else{
                url = "<s:url value='/res/discipleNote/agreeRecordBatch/see/${scope}?doctorFlow=${doctorFlow}'/>";
            }

            var auditName ="年度考核" ;
            <c:forEach items="${noteTypeEnumList}" var="noteType">
                <c:if  test="${noteType.id eq scope}">
            auditName ="${noteType.name}"
                </c:if>
            </c:forEach>
            jboxConfirm("确认审核通过所有学员所有待审核"+auditName+"？" , function() {
                jboxPost(url,null,function(resp){
                    jboxTip(resp);
                    setTimeout(function(){
                        location.reload();
                    },1000);
                }, null, false);
            });
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
            <form id="searchForm" action="<s:url value='/res/discipleNote/auditUserList/${roleScope}/${scope}'/>"
                  method="post">
                <input type="hidden" name="currentPage" id="currentPage"/>
                <div class="queryDiv">
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
                        <select id="spe" name="trainingSpeId" class="qselect">
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
								" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
                                <option class="${recDocCategoryEnumWMFirst.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
                                <option class="${recDocCategoryEnumWMSecond.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
                                <option class="${recDocCategoryEnumAssiGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
                                <option class="${recDocCategoryEnumChineseMedicine.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
                                <option class="${recDocCategoryEnumTCMGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
                                <option class="${recDocCategoryEnumTCMAssiGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </div>
                    </div>
                    <c:if test="${(scope eq 'ndkhsh')}">
                        <div class="inputDiv">
                            <label class="qlable">师承老师：</label>
                            <input class="qtext" id="discipleTeacherName" name="discipleTeacherName"
                                   value="${param.discipleTeacherName}" type="text">
                        </div>
                    </c:if>
                    <div class="inputDiv">
                        <label class="qlable">培训基地：</label>
                        <select name="orgFlow" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${orgs}" var="org">
                                <option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">姓&#12288;&#12288;名：</label>
                        <input class="qtext" id="doctorName" name="doctorName" value="${param.doctorName}" type="text"/>
                    </div>
                    <c:if test="${(scope eq 'ndkhsh') and (roleScope eq 'disciple')}">
                    <div>
                        <span style="float: right;"><input style="margin: 5px;" type="button" value="一键审核" class="search" onclick="agreeRecordBatch();"/></span>
                    </div>
                    </c:if>
                    <c:if test="${GlobalConstant.USER_LIST_UNIVERSITY ne roleScope}">
                    <div class="doctorTypeDiv">
                        <div class="doctorTypeLabel">人员类型：</div>
                        <div class="doctorTypeContent">
                            <c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
                                <label><input type="checkbox" name="doctorTypeIdList" <c:if test="${empty param.doctorTypeIdList}" >checked</c:if> value="${type.dictId}"
                                    ${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
                            </c:forEach>
                        </div>
                    </div>
                    </c:if>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="searchByName();"/>
                    </div>
                </div>
            </form>
            <div id="base">
                <table class="xllist">
                    <c:if test="${not scope eq 'ndkhsh'}">
                        <colgroup>
                            <col width="30%"/>
                            <col width="30%"/>
                            <col width="40%"/>
                        </colgroup>
                    </c:if>
                    <c:if test="${scope eq 'ndkhsh'}">
                        <colgroup>
                            <col width="8%"/>
                            <col width="10%"/>
                            <col width="10%"/>
                            <col width="8%"/>
                            <col width="10%"/>
                            <col width="8%"/>
                            <col width="10%"/>
                            <col width="20%"/>
                            <col width="10%"/>
                        </colgroup>
                    </c:if>
                    <tr>
                        <th style="text-align: center;">序号</th>
                        <th style="text-align: center;">姓名</th>
                        <th style="text-align: center;">培训基地</th>
                        <th style="text-align: center;">培训专业</th>
                        <th style="text-align: center;">对应专业</th>
                        <th style="text-align: center;">年级</th>
                        <th style="text-align: center;">师承老师</th>
                        <th style="text-align: center;">跟师时间</th>
                        <c:if test="${scope eq noteTypeEnumNote.id}">
                            <th>学习笔记</th>
                        </c:if>
                        <c:if test="${scope eq noteTypeEnumExperience.id}">
                            <th colspan="">跟师心得</th>
                        </c:if>
                        <c:if test="${scope eq noteTypeEnumBookExperience.id}">
                            <th colspan="99">学习体会</th>
                        </c:if>
                        <c:if test="${scope eq 'ndkhsh'}">
                            <th colspan="99">年度考核</th>
                        </c:if>
                    </tr>
                    <c:if test="${not empty doctorList}">
                        <c:forEach items="${doctorList}" var="doctor" varStatus="num">
                            <tr>
                                <td>${num.count }</td>
                                <td>${doctor.doctorName}</td>
                                <td>${doctor.orgName}</td>
                                <td>${doctor.doctorCategoryName}</td>
                                <td>${doctor.trainingSpeName}</td>
                                <td>${doctor.sessionNumber}</td>
                                <td>${doctor.discipleTeacherName}</td>
                                <td>${doctor.discipleStartDate} ~ ${doctor.discipleEndDate}</td>
                                <td>
                                    <a style="color: blue;" href="javascript:void(0)" onclick="showYearAss('${doctor.doctorFlow}','global');">查看</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <c:if test="${empty doctorList}">
                        <c:if test="${scope eq noteTypeEnumNote.id}">
                            <tr>
                                <td colspan="99">暂无待审核学习笔记</td>
                            </tr>
                        </c:if>
                        <c:if test="${scope eq noteTypeEnumExperience.id}">
                            <tr>
                                <td colspan="99">暂无待审核跟师心得</td>
                            </tr>
                        </c:if>
                        <c:if test="${scope eq noteTypeEnumBookExperience.id}">
                            <tr>
                                <td colspan="99">暂无待审核学习体会</td>
                            </tr>
                        </c:if>
                        <c:if test="${scope eq 'ndkhsh'}">
                            <tr>
                                <td colspan="99">暂无信息！</td>
                            </tr>
                        </c:if>
                    </c:if>
                </table>
                <div>
                    <c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
                    <pd:pagination toPage="toPage"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>