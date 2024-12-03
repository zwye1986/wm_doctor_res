<!DOCTYPE html>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="bootstrapSelect" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
</jsp:include>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
        body {
            margin: 0;
            padding: 0;
        }


        /* tab */
        .tab-container {
            display: flex;
            align-items: center;
            gap: 100px;
            border-bottom: 2px #d0d0d0 solid;
            height: 35px;
        }

        .tab {
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            border-bottom: 1px #d0d0d0 solid;
        }
        .tab.active {
            border-bottom: 3px #54B2E5 solid;
        }

        /* select */
        .row {
            display: flex;
            margin-top: 10px;
            margin-left: 3px;
            align-items: center;
            height: 40px;
        }
        .row-item {
            display: flex;
            align-items: center;
            /*justify-content: space-between;*/
            width: 240px;
            margin-right: 15px;
        }
        .select-item {
            width: 150px;
            height: 30px;
            border-radius: 8px;
            padding: 0 10px;
        }

        .zl-btn {
            height: 30px;
            width: 60px;
            background-color: #54B2E5;
            border: none;
            border-radius: 8px;
            color: white;
            font-weight: 600;
            margin-right: 15px;
            cursor: pointer;
        }

        /* 统计 */
        .statistics-row {
            display: flex;
            align-items: center;
            margin-top: 20px;
            height: 60px;
            /*position: relative;*/
            /*mask-image: linear-gradient(*/
            /*        to right,*/
            /*        #000 60%,*/
            /*        rgba(0, 0, 0, 0.4) 100%*/
            /*);*/
            border-radius: 10px;
        }

        .row-doctor {
            background-color: #e5efff;
            border: 1px #d9e8ff solid;
        }

        .row-master {
            background-color: #e6fbf4;
            border: 1px #cffae4 solid;
        }
        .statistics-row-title {
            height: 40px;
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            font-size: 18px;
            font-weight: 600;
            border-right: 1px #d9d9d9 solid;
        }

        .statistics-row-item {
            height: 40px;
            flex: 1;
            padding: 0 25px;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            justify-content: center;
            border-right: 1px #d9d9d9 solid;
            overflow: visible;
        }
        .statistics-row-item > div:first-child {
            margin-bottom: 8px;
        }

        .statistics-row-item:last-child {
            flex: 1.5;
            border-right: none;
        }

        .statistics-row-num {
            font-weight: 700;
        }

        .table-container {
            width: calc(90vw - 208px - 57px);
            margin-top: 20px;
            overflow: auto;
        }

        /* 表格 */
        .table {
            width: 100%;
            table-layout: fixed;
            border: 1px solid black;
            /*border-collapse: collapse;*/
        }

        .table td {
            width: 150px;
            height: 22px;
            text-align: center;
            border: 1px solid black;
        }

        td.table-title-header {
            width: 150px;
            position: relative;
        }
        td.total-td {
            width: 80px;
            font-weight: 600;
        }

        .total-all-td {
            position: sticky;
            left: 0;
            background-color: #e7e6e6;
            z-index: 1;
            box-sizing: border-box;
            outline: 1px solid black;
        }

        .total-all-td-left {
            position: sticky;
            left: 150px;
            background-color: #e7e6e6;
            z-index: 1;
            box-sizing: border-box;
            outline: 1px solid black;
        }

        .total-td-text {
            font-weight: 600;
        }

        /* 斜边边长 */
        /* Math.sqrt(Math.pow(154, 2) + Math.pow(52, 2)) */
        /* 角度计算公式 */
        /*  Math.atan(height / width) * 180 / Math.PI  */
        /*  Math.atan(52 / 154) * 180 / Math.PI  */
        .slash {
            position: absolute;
            display: block;
            top: 0;
            left: 0;
            background-color: #000;
            width: 159px;
            height: 1px;
            transform: rotate(16.8deg);
            transform-origin: top left;
        }

        /* 左下角文字 */
        .table-title-left {
            position: absolute;
            /* 左下角 left:0; bottom: 0; */
            left: 0px;
            bottom: 0px;
            font-size: 13px;
            font-weight: normal;
        }

        /* 右上角文字 */
        .table-title-right {
            position: absolute;
            /* 右上角 right:0; top: 0; */
            right: 0px;
            top: 0px;
            font-size: 13px;
            font-weight: normal;
        }
        .table tbody td {
            text-align: right;
            border: 1px solid black;
        }
        .table tfoot td {
            background-color: #e7e6e6;
            font-weight: 600;
            text-align: right;
            border: 1px solid black;
        }

        .row-doctor-history {
            background-color: #e5efff;
            width: 30%;
            border: 1px #d9e8ff solid;
        }

        .red {
            color: red;
        }

        .zl-blue {
            color: #1da4fe;
        }

        .green {
            color: #54B2E5;
        }

        .isHidden {
            display: none;
        }

        .text{
            margin-left: 0;
            width: auto;
            height: auto;
            line-height: inherit;
            color: black;
        }
        .selected a{
            padding: 0;
            background: #69BCE8;
        }
        .btn{
            /*height: 28px !important;*/
            border: 1px solid #e7e7eb;
            padding: 0px 3px;
        }
        .body{
            width: 90%;
            margin-left: auto;
            margin-right: auto;
            padding: 0 0 88px;
        }
        .container{
            padding-left: 0;
            padding-right: 0;
        }
        .btn-default{
            background-color: #fff;
        }
        .form-control,.input{
            height: 28px;
            padding: 0;
        }
        .bootstrap-select{
            width: 127px !important;
        }
        .search-div{
            float: left;
            margin-bottom: 18px;
            margin-right: 8px;
        }
        .clearfix {
            clear: both;
            height: 0;
        }

    </style>
</head>
<body>
<div style="padding:20px;width:calc(90vw - 208px - 57px)">
    <div class="tab-container">
        <div class="tab active" data-name="statistics">结业学员统计</div>
    </div>

    <!-- 统计 -->
    <div class="main-container" data-index="statistics">
        <div>
            <div class="row">
                <div class="row-item">
                    <div>结业年份 :&nbsp</div>
                    <input type="text" id="graduationYear" name="graduationYear" value="${graduationYear}" class="toggleView input" readonly="readonly" style="width: 123px;margin-left: 0px;"/>
                </div>
                <div class="row-item">
                    <div>年&#12288;&#12288;级 :&nbsp</div>
                    <input type="text" id="sessionNumber" name="sessionNumber" value="${sessionNumber}" class="toggleView input" readonly="readonly" style="width: 123px;margin-left: 0px;"/>
                </div>
                <div class="row-item">
                    <div>城市划分 :&nbsp;</div>
                    <div id="native">
                        <select class="notBlank province inputText" data-value="320000" style="display: none;width: 128px;margin-left: 0px;" ></select>
                        <select id="cityId" name="cityId"
                                class="notBlank city select" style="width: 128px;margin-left: 0px;"
                                onchange="chanegOrgCity(this.value);"></select>
                    </div>
                </div>
                <div class="row-item">
                    <div>培训基地 :&nbsp;</div>
                    <select name="orgFlow" id="orgFlow" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                        <option value="">全部</option>
                        <c:forEach items="${orgList}" var="org">
                            <option value="${org.orgFlow}">${org.orgName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row-item">
                    <div>协同单位 :&nbsp;</div>
                    <select name="isJointOrgIn" id="isJointOrgIn" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                        <option value="Y" <c:if test="${isJointOrgIn eq 'Y'}">selected</c:if>>参与统计</option>
                        <option value="N" <c:if test="${isJointOrgIn eq 'N'}">selected</c:if>>不参与统计</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="row-item">
                    <div>专业基地 :&nbsp;</div>
                    <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList" />
                    <select name="speId" id="speId" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                        <option value="">全部</option>
                        <c:forEach items="${applicationScope[dictName] }" var="dict">
                            <option value="${dict.dictId}" <c:if test="${speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row-item">
                    <div>成绩分类 :&nbsp;</div>
                    <select name="scoreType" id="scoreType" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                        <option value="">全部</option>
                        <option value="Theory" <c:if test="${scoreType eq 'Theory'}">selected</c:if>>理论成绩</option>
                        <option value="Skill" <c:if test="${scoreType eq 'Skill'}">selected</c:if>>技能成绩</option>
                    </select>
                </div>
                <div class="row-item">
                    <div>排序方式 :&nbsp;</div>
                    <select name="sortBy" id="sortBy" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                        <option value="orgCode" <c:if test="${sortBy eq 'orgCode'}">selected</c:if>>基地编码</option>
                        <option value="firstExamPassPercent" <c:if test="${sortBy eq 'firstExamPassPercent'}">selected</c:if>>首考通过率</option>
                        <option value="secondExamPassPercent" <c:if test="${sortBy eq 'secondExamPassPercent'}">selected</c:if>>补考通过率</option>
                        <option value="examPassPercent" <c:if test="${sortBy eq 'examPassPercent'}">selected</c:if>>综合通过率</option>
                    </select>
                </div>
                <div class="row-item">
                    <div>人员类型 :&nbsp;</div>
                    <select name="doctorType" id="doctorType" multiple="multiple" data-actions-box="true">
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <button class="zl-btn" onclick="searchFinishStusyReport()">查询</button>
                <button class="zl-btn" onclick="clearSearchArgs('statistics')">重置</button>
                <button class="zl-btn" onclick="exportStatisticsData()">导出</button>
            </div>
        </div>

        <div id="statistics_load">
            <div class="statistics-row row-doctor">
                <div class="statistics-row-title">住院医师</div>

                <div class="statistics-row-item">
                    <div>应考人数</div>
                    <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['all'] ? 0 : doctorTrainingMap['all']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>实考人数</div>
                    <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['realExam'] ? 0 : doctorTrainingMap['realExam']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>缺考人数</div>
                    <div><span class="statistics-row-num zl-blue">${(empty doctorTrainingMap['all'] ? 0 : doctorTrainingMap['all']) - (empty doctorTrainingMap['realExam'] ? 0 : doctorTrainingMap['realExam'])}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>首考人数</div>
                    <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['firstExam'] ? 0 : doctorTrainingMap['firstExam']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>补考人数</div>
                    <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['secondExam'] ? 0 : doctorTrainingMap['secondExam']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>首考通过率</div>
                    <div><span class="statistics-row-num zl-blue">${(empty doctorTrainingMap['firstPassPercent'] ? 0 : doctorTrainingMap['firstPassPercent'])}</span>%</div>
                </div>
                <div class="statistics-row-item">
                    <div>补考通过率</div>
                    <div><span class="statistics-row-num zl-blue">${(empty doctorTrainingMap['secondPassPercent'] ? 0 : doctorTrainingMap['secondPassPercent'])}</span>%</div>
                </div>
                <div class="statistics-row-item">
                    <div>综合通过率</div>
                    <div><span class="statistics-row-num zl-blue">${(empty doctorTrainingMap['PassPercent'] ? 0 : doctorTrainingMap['PassPercent'])}</span>%</div>
                </div>
            </div>

            <div class="statistics-row row-master">
                <div class="statistics-row-title">在校专硕</div>

                <div class="statistics-row-item">
                    <div>应考人数</div>
                    <div><span class="statistics-row-num green">${empty graduateMap['all'] ? 0 : graduateMap['all']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>实考人数</div>
                    <div><span class="statistics-row-num green">${empty graduateMap['realExam'] ? 0 : graduateMap['realExam']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>缺考人数</div>
                    <div><span class="statistics-row-num green">${(empty graduateMap['all'] ? 0 : graduateMap['all']) - (empty graduateMap['realExam'] ? 0 : graduateMap['realExam'])}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>首考人数</div>
                    <div><span class="statistics-row-num green">${empty graduateMap['firstExam'] ? 0 : graduateMap['firstExam']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>补考人数</div>
                    <div><span class="statistics-row-num green">${empty graduateMap['secondExam'] ? 0 : graduateMap['secondExam']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>首考通过率</div>
                    <div><span class="statistics-row-num green">${(empty graduateMap['firstPassPercent'] ? 0 : graduateMap['firstPassPercent'])}</span>%</div>
                </div>
                <div class="statistics-row-item">
                    <div>补考通过率</div>
                    <div><span class="statistics-row-num green">${(empty graduateMap['secondPassPercent'] ? 0 : graduateMap['secondPassPercent'])}</span>%</div>
                </div>
                <div class="statistics-row-item">
                    <div>综合通过率</div>
                    <div><span class="statistics-row-num green">${(empty graduateMap['PassPercent'] ? 0 : graduateMap['PassPercent'])}</span>%</div>
                </div>
            </div>

            <div class="table-container">
                <table cellspacing="0" border="1" class="table">
                    <thead>
                    <tr>
                        <td rowspan="3" class="total-all-td">城市划分</td>
                        <td rowspan="3" class="total-all-td-left">培训基地</td>
                        <td colspan="2">应考人数</td>
                        <td rowspan="3" class="total-td">合计</td>
                        <td colspan="2">实考人数</td>
                        <td rowspan="3" class="total-td">合计</td>
                        <td colspan="2">首考人数</td>
                        <td rowspan="3" class="total-td">合计</td>
                        <td colspan="2">补考人数</td>
                        <td rowspan="3" class="total-td">合计</td>
                        <td colspan="2">缺考人数</td>
                        <td rowspan="3" class="total-td">合计</td>
                        <td colspan="4" style="width: 220px">首考通过率</td>
                        <td rowspan="3" class="total-td">平均通过率</td>
                        <td colspan="4" style="width: 220px">补考通过率</td>
                        <td rowspan="3" class="total-td">平均通过率</td>
                        <td colspan="4" style="width: 220px">综合通过率</td>
                        <td rowspan="3" class="total-td">平均通过率</td>
                    </tr>
                    <tr>
                        <td rowspan="2">理论考试</td>
                        <td rowspan="2">技能考试</td>
                        <td rowspan="2">理论考试</td>
                        <td rowspan="2">技能考试</td>
                        <td rowspan="2">理论考试</td>
                        <td rowspan="2">技能考试</td>
                        <td rowspan="2">理论考试</td>
                        <td rowspan="2">技能考试</td>
                        <td rowspan="2">理论考试</td>
                        <td rowspan="2">技能考试</td>
                        <td colspan="2">理论考试</td>
                        <td colspan="2">技能考试</td>
                        <td colspan="2">理论考试</td>
                        <td colspan="2">技能考试</td>
                        <td colspan="2">理论考试</td>
                        <td colspan="2">技能考试</td>
                    </tr>
                    <tr>
                        <td>住院医师</td>
                        <td>在校专硕</td>
                        <td>住院医师</td>
                        <td>在校专硕</td>
                        <td>住院医师</td>
                        <td>在校专硕</td>
                        <td>住院医师</td>
                        <td>在校专硕</td>
                        <td>住院医师</td>
                        <td>在校专硕</td>
                        <td>住院医师</td>
                        <td>在校专硕</td>
                    </tr>
                    </thead>
                    <!-- 主体 -->
                    <tbody>
                    <c:forEach items="${resultList}" var="result">
                        <tr>
                            <td class="total-all-td">${result['orgCityName']}</td>
                            <td class="total-all-td-left">${result['orgName']}</td>
                            <td>${empty result['Theory'] ? 0 : result['Theory']}</td>
                            <td>${empty result['Skill'] ? 0 : result['Skill']}</td>
                            <td class="total-td-text">${empty result['all'] ? 0 : result['all']}</td>
                            <td>${empty result['realExamTheory'] ? 0 : result['realExamTheory']}</td>
                            <td>${empty result['realExamSkill'] ? 0 : result['realExamSkill']}</td>
                            <td class="total-td-text">${empty result['realExamAll'] ? 0 : result['realExamAll']}</td>
                            <td>${(empty result['realExamGraduationTheoryFirst'] ? 0 : result['realExamGraduationTheoryFirst']) + (empty result['realExamDoctorTheoryFirst'] ? 0 : result['realExamDoctorTheoryFirst'])}</td>
                            <td>${(empty result['realExamGraduationSkillFirst'] ? 0 : result['realExamGraduationSkillFirst']) + (empty result['realExamDoctorSkillFirst'] ? 0 : result['realExamDoctorSkillFirst'])}</td>
                            <td class="total-td-text">${empty result['firstExam'] ? 0 : result['firstExam']}</td>
                            <td>${(empty result['realExamGraduationTheorySecond'] ? 0 : result['realExamGraduationTheorySecond']) + (empty result['realExamDoctorTheorySecond'] ? 0 : result['realExamDoctorTheorySecond'])}</td>
                            <td>${(empty result['realExamGraduationSkillSecond'] ? 0 : result['realExamGraduationSkillSecond']) + (empty result['realExamDoctorSkillSecond'] ? 0 : result['realExamDoctorSkillSecond'])}</td>
                            <td class="total-td-text">${empty result['secondExam'] ? 0 : result['secondExam']}</td>
                            <td>${empty result['missTheory'] ? 0 : result['missTheory']}</td>
                            <td>${empty result['missSkill'] ? 0 : result['missSkill']}</td>
                            <td class="total-td-text">${empty result['missAll'] ? 0 : result['missAll']}</td>
                            <td>${empty result['doctorTheoryFirstPassPercent'] ? 0 : result['doctorTheoryFirstPassPercent']}%</td>
                            <td>${empty result['graduationTheoryFirstPassPercent'] ? 0 : result['graduationTheoryFirstPassPercent']}%</td>
                            <td>${empty result['doctorSkillFirstPassPercent'] ? 0 : result['doctorSkillFirstPassPercent']}%</td>
                            <td>${empty result['graduationSkillFirstPassPercent'] ? 0 : result['graduationSkillFirstPassPercent']}%</td>
                            <td class="total-td-text">${empty result['firstPassPercent'] ? 0 : result['firstPassPercent']}%</td>
                            <td>${empty result['doctorTheorySecondPassPercent'] ? 0 : result['doctorTheorySecondPassPercent']}%</td>
                            <td>${empty result['graduationTheorySecondPassPercent'] ? 0 : result['graduationTheorySecondPassPercent']}%</td>
                            <td>${empty result['doctorSkillSecondPassPercent'] ? 0 : result['doctorSkillSecondPassPercent']}%</td>
                            <td>${empty result['graduationSkillSecondPassPercent'] ? 0 : result['graduationSkillSecondPassPercent']}%</td>
                            <td class="total-td-text">${empty result['secondPassPercent'] ? 0 : result['secondPassPercent']}%</td>
                            <td>${empty result['doctorTheoryPassPercent'] ? 0 : result['doctorTheoryPassPercent']}%</td>
                            <td>${empty result['graduationTheoryPassPercent'] ? 0 : result['graduationTheoryPassPercent']}%</td>
                            <td>${empty result['doctorSkillPassPercent'] ? 0 : result['doctorSkillPassPercent']}%</td>
                            <td>${empty result['graduationSkillPassPercent'] ? 0 : result['graduationSkillPassPercent']}%</td>
                            <td class="total-td-text">${empty result['PassPercent'] ? 0 : result['PassPercent']}%</td>
                        </tr>
                    </c:forEach>
                    </tbody>

                    <!-- footer -->
                    <tfoot>
                    <td colspan="2" class="total-all-td">总计</td>
                    <td>${empty footMap['Theory'] ? 0 : footMap['Theory']}</td>
                    <td>${empty footMap['Skill'] ? 0 : footMap['Skill']}</td>
                    <td class="total-td-text">${empty footMap['all'] ? 0 : footMap['all']}</td>
                    <td>${empty footMap['realExamTheory'] ? 0 : footMap['realExamTheory']}</td>
                    <td>${empty footMap['realExamSkill'] ? 0 : footMap['realExamSkill']}</td>
                    <td class="total-td-text">${empty footMap['realExamAll'] ? 0 : footMap['realExamAll']}</td>
                    <td>${(empty footMap['realExamGraduationTheoryFirst'] ? 0 : footMap['realExamGraduationTheoryFirst']) + (empty footMap['realExamDoctorTheoryFirst'] ? 0 : footMap['realExamDoctorTheoryFirst'])}</td>
                    <td>${(empty footMap['realExamGraduationSkillFirst'] ? 0 : footMap['realExamGraduationSkillFirst']) + (empty footMap['realExamDoctorSkillFirst'] ? 0 : footMap['realExamDoctorSkillFirst'])}</td>
                    <td class="total-td-text">${empty footMap['firstExam'] ? 0 : footMap['firstExam']}</td>
                    <td>${(empty footMap['realExamGraduationTheorySecond'] ? 0 : footMap['realExamGraduationTheorySecond']) + (empty footMap['realExamDoctorTheorySecond'] ? 0 : footMap['realExamDoctorTheorySecond'])}</td>
                    <td>${(empty footMap['realExamGraduationSkillSecond'] ? 0 : footMap['realExamGraduationSkillSecond']) + (empty footMap['realExamDoctorSkillSecond'] ? 0 : footMap['realExamDoctorSkillSecond'])}</td>
                    <td class="total-td-text">${empty footMap['secondExam'] ? 0 : footMap['secondExam']}</td>
                    <td>${empty footMap['missTheory'] ? 0 : footMap['missTheory']}</td>
                    <td>${empty footMap['missSkill'] ? 0 : footMap['missSkill']}</td>
                    <td class="total-td-text">${empty footMap['missAll'] ? 0 : footMap['missAll']}</td>
                    <td>${empty footMap['doctorTheoryFirstPassPercent'] ? 0 : footMap['doctorTheoryFirstPassPercent']}%</td>
                    <td>${empty footMap['graduationTheoryFirstPassPercent'] ? 0 : footMap['graduationTheoryFirstPassPercent']}%</td>
                    <td>${empty footMap['doctorSkillFirstPassPercent'] ? 0 : footMap['doctorSkillFirstPassPercent']}%</td>
                    <td>${empty footMap['graduationSkillFirstPassPercent'] ? 0 : footMap['graduationSkillFirstPassPercent']}%</td>
                    <td class="total-td-text">${empty footMap['firstPassPercent'] ? 0 : footMap['firstPassPercent']}%</td>
                    <td>${empty footMap['doctorTheorySecondPassPercent'] ? 0 : footMap['doctorTheorySecondPassPercent']}%</td>
                    <td>${empty footMap['graduationTheorySecondPassPercent'] ? 0 : footMap['graduationTheorySecondPassPercent']}%</td>
                    <td>${empty footMap['doctorSkillSecondPassPercent'] ? 0 : footMap['doctorSkillSecondPassPercent']}%</td>
                    <td>${empty footMap['graduationSkillSecondPassPercent'] ? 0 : footMap['graduationSkillSecondPassPercent']}%</td>
                    <td class="total-td-text">${empty footMap['secondPassPercent'] ? 0 : footMap['secondPassPercent']}%</td>
                    <td>${empty footMap['doctorTheoryPassPercent'] ? 0 : footMap['doctorTheoryPassPercent']}%</td>
                    <td>${empty footMap['graduationTheoryPassPercent'] ? 0 : footMap['graduationTheoryPassPercent']}%</td>
                    <td>${empty footMap['doctorSkillPassPercent'] ? 0 : footMap['doctorSkillPassPercent']}%</td>
                    <td>${empty footMap['graduationSkillPassPercent'] ? 0 : footMap['graduationSkillPassPercent']}%</td>
                    <td class="total-td-text">${empty footMap['PassPercent'] ? 0 : footMap['PassPercent']}%</td>
                    </tfoot>
                </table>
            </div>
        </div>

    </div>
</div>
</body>

<script>
    var selectAll = false;
    var deselectAll = false;

    $(document).ready(function() {
        $("#doctorType").selectpicker({
            deselectAllText: "全不选",
            selectAllText: "全选",
            noneResultsText: "没有匹配{0}的选项",
            noneSelectedText: "全部",
            selectAllCb: function () {
                selectAll = true;
                $('#doctorType').trigger('changed.bs.select');
            },
            deselectAllCb: function () {
                deselectAll = true;
                $('#doctorType').trigger('changed.bs.select');
            }
        });


        $.checkYear("sessionNumber","",null);
        $('#graduationYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        showProve();
    });

    function showProve() {
        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        jboxGet(url,null, function(json) {
            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
            var newJsonData=new Array();
            var j=0;
            for (var i = 0; i < json.length; i++) {
                var provData = json[i];
                if (provData.v == '320000') {
                    if ("${orgCityId}" != "") {
                        var s = provData.s;
                        var k = 0;
                        var newS = new Array();
                        for (var m = 0; m < s.length; m++) {
                            if (s[m].v == "${orgCityId}") {
                                newS[k++] = s[m];
                            }
                        }
                        provData.s = newS;
                    }
                    newJsonData[j++] = provData;
                }
            }
            $.cxSelect.defaults.url = newJsonData;
            $.cxSelect.defaults.nodata = "none";
            if("${sessionScope.userListScope==GlobalConstant.USER_LIST_CHARGE}"=="true"){
                $.cxSelect.defaults.required = true;
            }
            $("#native").cxSelect({
                selects : ["province", "city"],
                nodata : "none",
                firstValue : ""
            });
        },null,false);
    }

    function chanegOrgCity(cityId) {
        var url = "<s:url value='/jsres/graduation/getOrgListByCityId?cityId='/>" + cityId;
        $("#orgFlow").empty();
        var option="<option value=''>请选择</option>";
        $("#orgFlow").append(option);
        jboxPost(url, null, function(resp){
            for (var i = 0; i < resp.length; i++) {
                $("#orgFlow").append('<option value="'+resp[i].orgFlow+'">'+resp[i].orgName+'</option>');
            }
        }, null, false)
    }

    function searchFinishStusyReport() {
        var graduationYear = $("#graduationYear").val();
        var sessionNumber = $("#sessionNumber").val();
        var cityId = $("#cityId").val();
        var orgFlow = $("#orgFlow").val();
        var isJointOrgIn = $("#isJointOrgIn").val();
        var speId = $("#speId").val();
        var doctorType = $("#doctorType").val();

        var scoreType = $("#scoreType").val();
        var sortBy = $("#sortBy").val();
        var url = "<s:url value='/jsres/graduation/finishStudyReport'/>?catSpeId=DoctorTrainingSpe";
        var data = {
            "graduationYear": graduationYear,
            "sessionNumber": sessionNumber,
            "cityId": cityId,
            "orgFlow": orgFlow,
            "isJointOrgIn": isJointOrgIn,
            "speId": speId,
            "doctorType": doctorType == null ? [] : doctorType,
            "scoreType": scoreType,
            "sortBy": sortBy,
            "isLoad": 'Y'
        };
        jboxPostLoad("statistics_load",url,data,true);
    }

    function exportStatisticsData() {
        var graduationYear = $("#graduationYear").val();
        var sessionNumber = $("#sessionNumber").val();
        var cityId = $("#cityId").val();
        var orgFlow = $("#orgFlow").val();
        var isJointOrgIn = $("#isJointOrgIn").val();
        var speId = $("#speId").val();
        var doctorType = $("#doctorType").val();
        var scoreType = $("#scoreType").val();
        var sortBy = $("#sortBy").val();
        var url = "<s:url value='/jsres/graduation/exportFinishStudyReport?sessionNumber='/>" + sessionNumber + "&graduationYear=" + graduationYear
            + "&cityId" + cityId + "&orgFlow=" + orgFlow + "&isJointOrgIn=" + isJointOrgIn + "&speId=" + speId + "&doctorType=" + doctorType + "&scoreType=" + scoreType + "&sortBy=" + sortBy;
        jboxTip("导出中…………");
        jboxExp(null, url);
        jboxEndLoading();
    }

    function clearSearchArgs() {
        $("#graduationYear").val("");
        $("#sessionNumber").val("");
        $("#cityId").val("");
        $("#orgFlow").val("");
        $("#isJointOrgIn").val("");
        $("#speId").val("");
        $("#doctorType").val("");
        $("#scoreType").val("");
        $("#sortBy").val("");
        searchFinishStusyReport();
    }


</script>
</html>
