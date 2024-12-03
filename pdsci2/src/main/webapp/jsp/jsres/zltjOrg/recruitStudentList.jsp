<!DOCTYPE html>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
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
            position: relative;
            mask-image: linear-gradient(
                    to right,
                    #000 60%,
                    rgba(0, 0, 0, 0.4) 100%
            );
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
            border-collapse: collapse;
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
            font-size: 15px;
            font-weight: bold;
        }

        td.total-td {
            width: 80px;
            font-weight: 600;
        }

        .total-all-td {
            width: 80px;
            position: sticky;
            right: 0;
            background-color: #e7e6e6;
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
            text-align: center;
            border: 1px solid black;
        }
        .table tfoot td {
            background-color: #e7e6e6;
            font-weight: 600;
            text-align: center;
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
    </style>
</head>
<body>
<div style="padding:20px;width:calc(90vw - 208px - 57px)">
    <div class="tab-container">
        <div class="tab active" data-name="statistics">招录统计报表</div>
    </div>

    <!-- 统计 -->
    <div class="main-container" data-index="statistics">
        <div>
            <div class="row">
                <div class="row-item">
                    <div>年&nbsp;级 :&nbsp</div>
                    <input type="text" id="sessionNumber" name="sessionNumber" value="${sessionNumber}" class="toggleView input" readonly="readonly" style="width: 128px;margin-left: 0px;"/>
                </div>
                <div class="row-item">
                    <div>专业基地 :&nbsp;</div>
                    <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList" />
                    <select name="speId" id="speId" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                        <option value="">全部</option>
                        <c:forEach items="${applicationScope[dictName] }" var="dict">
                            <option value="${dict.dictId}">${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row-item">
                    <div>学员状态 :&nbsp;</div>
                    <select name="statusId" id="statusId" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                        <option value="">全部</option>
                        <option value="20" ${param.statusId eq '20'?'selected':''}>在培</option>
                        <option value="22" ${param.statusId eq '22'?'selected':''}>已考核待结业</option>
                        <option value="21" ${param.statusId eq '21'?'selected':''}>结业</option>
                        <option value="23" ${param.statusId eq '23'?'selected':''}>退培</option>
                    </select>
                </div>
                <div class="row-item">
                    <div>人员类型 :&nbsp;</div>
                    <select name="docType" id="docType" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                        <option value="">全部</option>
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <button class="zl-btn" onclick="searchRecruitInfo()">搜索</button>
                <button class="zl-btn" onclick="clearSearchArgs('statistics')">重置</button>
                <button class="zl-btn" onclick="exportStatisticsData()">导出</button>
            </div>
        </div>

        <div id="statistics_load">
            <div class="statistics-row row-doctor">
                <div class="statistics-row-title">住院医师</div>

                <div class="statistics-row-item">
                    <div>招录</div>
                    <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['all'] ? 0 : doctorTrainingMap['all']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>在培</div>
                    <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['20'] ? 0 : doctorTrainingMap['20']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>结业</div>
                    <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['21'] ? 0 : doctorTrainingMap['21']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>退培</div>
                    <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['23'] ? 0 : doctorTrainingMap['23']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>已考核待结业</div>
                    <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['22'] ? 0 : doctorTrainingMap['22']}</span>人</div>
                </div>
            </div>

            <div class="statistics-row row-master">
                <div class="statistics-row-title">在校专硕</div>

                <div class="statistics-row-item">
                    <div>招录</div>
                    <div><span class="statistics-row-num green">${empty graduateMap['all'] ? 0 : graduateMap['all']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>在培</div>
                    <div><span class="statistics-row-num green">${empty graduateMap['20'] ? 0 : graduateMap['20']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>结业</div>
                    <div><span class="statistics-row-num green">${empty graduateMap['21'] ? 0 : graduateMap['21']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>退培</div>
                    <div><span class="statistics-row-num green">${empty graduateMap['23'] ? 0 : graduateMap['23']}</span>人</div>
                </div>
                <div class="statistics-row-item">
                    <div>已考核待结业</div>
                    <div><span class="statistics-row-num green">${empty graduateMap['22'] ? 0 : graduateMap['22']}</span>人</div>
                </div>
            </div>

            <div class="table-container">
                <table cellspacing="0" border="1" class="table">
                    <thead>
                        <tr>
                            <td class="table-title-header">
                                <span>专业基地</span>
                            </td>
                            <td class="table-title-header">
                                <span>本单位人</span>
                            </td>
                            <td class="table-title-header">
                                <span>委培单位人</span>
                            </td>
                            <td class="table-title-header">
                                <span>社会人</span>
                            </td>
                            <td class="table-title-header">
                                <span>在校专硕</span>
                            </td>
                            <td class="table-title-header">
                                <span>合计</span>
                            </td>
                        </tr>
                    </thead>
                    <!-- 主体 -->
                    <tbody>
                        <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList"/>
                        <c:forEach items="${applicationScope[dictName] }" var="dict">
                            <c:set var="Company" value="${dict.dictId}Company"/>
                            <c:set var="CompanyEntrust" value="${dict.dictId}CompanyEntrust"/>
                            <c:set var="Social" value="${dict.dictId}Social"/>
                            <c:set var="Graduate" value="${dict.dictId}Graduate"/>
                            <c:set var="all" value="${dict.dictId}all"/>
                            <tr>
                                <td>${dict.dictName}</td>
                                <td>${empty speInfos[Company] ? 0 : speInfos[Company]}</td>
                                <td>${empty speInfos[CompanyEntrust] ? 0 : speInfos[CompanyEntrust]}</td>
                                <td>${empty speInfos[Social] ? 0 : speInfos[Social]}</td>
                                <td>${empty speInfos[Graduate] ? 0 : speInfos[Graduate]}</td>
                                <td>${empty speInfos[all] ? 0 : speInfos[all]}</td>
                            </tr>
                        </c:forEach>
                    </tbody>

                    <!-- footer -->
                    <tfoot>
                        <td>合计</td>
                        <td>${empty speAll["Company"] ? 0 : speAll["Company"]}</td>
                        <td>${empty speAll["CompanyEntrust"] ? 0 : speAll["CompanyEntrust"]}</td>
                        <td>${empty speAll["Social"] ? 0 : speAll["Social"]}</td>
                        <td>${empty speAll["Graduate"] ? 0 : speAll["Graduate"]}</td>
                        <td>${empty speAll["all"] ? 0 : speAll["all"]}</td>
                    </tfoot>
                </table>
            </div>
        </div>

    </div>
</div>
</body>

<script>

    $('#sessionNumber').datepicker({
        startView: 2,
        maxViewMode: 2,
        minViewMode:2,
        format:'yyyy'
    });

    function searchRecruitInfo() {
        var sessionNumber = $("#sessionNumber").val();
        var speId = $("#speId").val();
        var docType = $("#docType").val();
        var statusId = $("#statusId").val();
        var url = "<s:url value='/jsres/recruitDoctorInfo/recruitStatisticsReport'/>";
        var data = {
            "sessionNumber": sessionNumber,
            "speId": speId,
            "docType": docType,
            "statusId": statusId,
            "isLoad": "Y"
        };
        jboxPostLoad("statistics_load",url,data,true);
    }

    function exportStatisticsData() {
        var sessionNumber = $("#sessionNumber").val();
        var orgFlow = $("#orgFlow").val();
        var speId = $("#speId").val();
        var docType = $("#docType").val();
        var statusId = $("#statusId").val();
        var url = "<s:url value='/jsres/recruitDoctorInfo/exportRecruitStatistics?sessionNumber='/>" + sessionNumber + "&orgFlow=" + orgFlow + "&speId=" + speId + "&docType=" + docType + "&statusId=" + statusId;
        jboxTip("导出中…………");
        jboxExp(null, url);
        jboxEndLoading();
    }

    function clearSearchArgs(tab) {
        if (tab == "statistics") {
            $("#speId").val("");
            $("#docType").val("");
            $("#statusId").val("");
            searchRecruitInfo();
        }
    }


</script>
</html>
