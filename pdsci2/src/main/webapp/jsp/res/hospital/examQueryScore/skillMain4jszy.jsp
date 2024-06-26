<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true" />
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
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
<script type="text/javascript">
    $(document).ready(function(){
        toPage(1);
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
        if(page==undefined||page=="undefined")
            page=1;
        $("#currentPage").val(page);
        var year=$("#assessmentYear").val();
        if(year=="")
        {
            jboxTip("请选择年度！");
            return false;
        }
        var typeNum=$("input[name='doctorTypeIdList']:checked").length;
        if(typeNum<1){
            jboxTip("请选择人员类型！");
            return;
        }
        jboxStartLoading();
        jboxPostLoad("doctorListZi","<s:url value='/res/examCfg/skillScorelist'/>",$("#searchForm").serialize(),false);
    }
    function exportInfo()
    {
        var year=$("#assessmentYear").val();
        if(year=="")
        {
            jboxTip("请选择年度！");
            return false;
        }
        var typeNum=$("input[name='doctorTypeIdList']:checked").length;
        if(typeNum<1){
            jboxTip("请选择人员类型！");
            return;
        }
        jboxExp($("#searchForm"),"<s:url value='/res/examCfg/exportSkillScoreInfo'/>");
    }
    //技能成绩详情
    function skillScoreDetail(doctorFlow, scoreFlow, scoreType, scoreYear) {
        var url = "<s:url value='/res/examCfg/scoreDetail?scoreFlow='/>" + scoreFlow + "&doctorFlow=" + doctorFlow + "&scoreType=" + scoreType + "&scoreYear=" + scoreYear;
        if (scoreType == "theoryScore") {
            jboxOpen(url, "理论成绩详情", 800, 400);
        } else {
            jboxOpen(url, "技能成绩详情", 1200, 400);
        }
    }

    function importExcel() {
        var url = "<s:url value='/res/examCfg/importSkillScore'/>";
        jboxOpen(url, "导入技能成绩", 800, 300);
    }
</script>

<form id="exportFrom">
    <input type="hidden" id="url" name="url"/>
</form>
<div class="mainright" style="min-height: 300px;">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm">
                <input type="hidden" id="currentPage" name="currentPage"/>
                <div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">姓&#12288;&#12288;名：</label>
                        <input type="text" name="userName" class="qtext"/>
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
                    <div class="inputDiv">
                        <label class="qlable">年&#12288;&#12288;级：</label>
                        <input type="text" id="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy'})" value="${param.sessionNumber}" name="sessionNumber" class="qtext" readonly="readonly"/>
                        <%--<select id="sessionNumber" name="sessionNumber" class="qselect">--%>
                            <%--<option></option>--%>
                            <%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">--%>
                                <%--<option value="${dict.dictId}" ${param.sessionNumber eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">年&#12288;&#12288;度：</label>
                        <input type="text" id="assessmentYear" onclick="WdatePicker({dateFmt:'yyyy'})" value="${pdfn:getCurrYear()}" name="assessmentYear" class="qtext" readonly="readonly"/>
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
                    <div class="lastDiv" style="min-width: 280px;max-width: 280px;">
                        <input class="searchInput" type="button" value="查&#12288;询" onclick="toPage();"/>
                        <input class="searchInput" type="button" value="导&#12288;入" onclick="importExcel();"/>
                        <input class="searchInput" type="button" value="导&#12288;出" onclick="exportInfo();"/>
                    </div>
                </div>
            </form>
        </div>
        <div id="doctorListZi">
        </div>
    </div>
</div>
</html>