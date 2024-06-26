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
            border-bottom: 3px #44b549 solid;
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
            background-color: #44b549;
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
            color: #44b549;
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

        <div class="tab" data-name="history">历史招录数据</div>
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
                    <div>培训基地 :&nbsp;</div>
                    <select name="orgFlow" id="orgFlow" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                        <option value="">全部</option>
                        <c:forEach items="${orgList}" var="org">
                            <option value="${org.orgFlow}">${org.orgName}</option>
                        </c:forEach>
                    </select>
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
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <button class="zl-btn" onclick="searchRecruitInfo()">搜索</button>
                <button class="zl-btn" onclick="clearSearchArgs('statistics')">重置</button>
                <button class="zl-btn" onclick="exportStatisticsData()">导出</button>
                <c:if test="${jsres_photo_recruitData eq 'Y'}">
                    <button class="zl-btn" onclick="photoRecruitInfo()">固定数据</button>
                </c:if>
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
                        <td class="table-title-header" rowspan="2">
                            <span class="table-title-left">培训基地</span>
                            <span class="slash"></span>
                            <span class="table-title-right">专业基地</span>
                        </td>
                        <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList" />
                        <c:forEach items="${applicationScope[dictName] }" var="dict">
                            <td colspan="2">${dict.dictName}</td>
                            <td colspan="2" rowspan="2" class="total-td">合计</td>
                        </c:forEach>
                        <td colspan="2" rowspan="2" class="total-all-td">总计</td>
                    </tr>
                    <tr>
                        <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList" />
                        <c:forEach items="${applicationScope[dictName] }" var="dict">
                            <td>住院医师</td>
                            <td>在校专硕</td>
                        </c:forEach>
                    </tr>
                    </thead>
                    <!-- 主体 -->
                    <tbody>
                    <c:forEach items="${searchOrgList}" var="org">
                        <c:set var="orgFlow" value="${org.orgFlow}" />
                        <c:set var="orgSpe" value="${orgSpeList[orgFlow]}" />
                        <tr>
                            <td>${org.orgName}</td>
                            <c:set var="orgSpeInfo" value="${orgSpe[orgFlow]}" />
                            <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList" />
                            <c:forEach items="${applicationScope[dictName] }" var="dict">
                                <c:set var="doctorTraining" value="${dict.dictId}doctorTraining" />
                                <c:set var="graduate" value="${dict.dictId}graduate" />
                                <c:set var="all" value="${dict.dictId}all" />
                                <td>${empty orgSpeInfo[doctorTraining] ? 0 : orgSpeInfo[doctorTraining]}</td>
                                <td>${empty orgSpeInfo[graduate] ? 0 : orgSpeInfo[graduate]}</td>
                                <td colspan="2" class="total-td-text">${empty orgSpeInfo[all] ? 0 : orgSpeInfo[all]}</td>
                            </c:forEach>
                            <td class="total-all-td" colspan="2">${empty orgSpeInfo['all'] ? 0 : orgSpeInfo['all']}</td>
                        </tr>
                    </c:forEach>
                    </tbody>

                    <!-- footer -->
                    <tfoot>
                    <td>总计</td>
                    <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList" />
                    <c:forEach items="${applicationScope[dictName] }" var="dict">
                        <c:set var="doctorTraining" value="${dict.dictId}doctorTraining" />
                        <c:set var="graduate" value="${dict.dictId}graduate" />
                        <c:set var="all" value="${dict.dictId}all" />
                        <td>${empty speAll[doctorTraining] ? 0 : speAll[doctorTraining]}</td>
                        <td>${empty speAll[graduate] ? 0 : speAll[graduate]}</td>
                        <td colspan="2">${empty speAll[all] ? 0 : speAll[all]}</td>
                    </c:forEach>
                    <td colspan="2" class="total-all-td red">${doctorTrainingMap['all'] + graduateMap['all']}</td>
                    </tfoot>
                </table>
            </div>
        </div>

    </div>

    <!-- 历史 -->
    <div class="main-container isHidden" data-index="history">
        <div id="history_load">

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

    const tabContainer = document.querySelector(".tab-container");
    const tabEles = document.querySelectorAll(".tab");
    const mainContainers = document.querySelectorAll(".main-container");

    tabContainer.onclick = (evt) => {
        if (evt.target.classList.contains("tab")) {
            const name = evt.target.getAttribute("data-name");

            tabEles.forEach((ele) => {
                ele.classList.remove("active");
            });

            evt.target.classList.add("active");

            mainContainers.forEach((ele) => {
                if (ele.getAttribute("data-index") === name)
                    ele.classList.remove("isHidden");
                else ele.classList.add("isHidden");
            });
            if (name === "history") {
                var url = "<s:url value='/jsres/manage/recruitHistoryReport'/>";
                jboxPostLoad("history_load", url, null, true);
            }
        }
    };

    function searchRecruitInfo() {
        var sessionNumber = $("#sessionNumber").val();
        var orgFlow = $("#orgFlow").val();
        var speId = $("#speId").val();
        var docType = $("#docType").val();
        var statusId = $("#statusId").val();
        var url = "<s:url value='/jsres/manage/recruitStatisticsReport'/>";
        var data = {
            "sessionNumber": sessionNumber,
            "orgFlow": orgFlow,
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
        var url = "<s:url value='/jsres/manage/exportRecruitStatistics?sessionNumber='/>" + sessionNumber + "&orgFlow=" + orgFlow + "&speId=" + speId + "&docType=" + docType + "&statusId=" + statusId;
        jboxTip("导出中…………");
        jboxExp(null, url);
        jboxEndLoading();
    }

    function exportHistoryData() {
        var photoTime = $("#photoTime").val();
        var orgFlow = $("#orgFlow_history").val();
        var speId = $("#speId_history").val();
        var docType = $("#docType_history").val();
        var url = "<s:url value='/jsres/manage/exportRecruitHistory?photoTime='/>" + photoTime + "&orgFlow=" + orgFlow + "&speId=" + speId + "&docType=" + docType;
        jboxTip("导出中…………");
        jboxExp(null, url);
        jboxEndLoading();
    }

    function photoRecruitInfo() {
        jboxConfirm("是否固定当日招录数据?",  function(){
            jboxStartLoading();
            var url = "<s:url value='/jsres/manage/photoRecruitInfo'/>";
            jboxPost(url, null, function(resp){
                jboxTip(resp);
                setTimeout(function(){
                    jboxClose();
                    jboxEndLoading();
                }, 1000);
            }, null, false);
        });
    }

    function searchRecruitHistory() {
        var photoTime = $("#photoTime").val();
        var orgFlow = $("#orgFlow_history").val();
        var speId = $("#speId_history").val();
        var docType = $("#docType_history").val();
        var url = "<s:url value='/jsres/manage/recruitHistoryReport'/>";
        var data = {
            "photoTime": photoTime,
            "orgFlow": orgFlow,
            "speId": speId,
            "docType": docType,
            "isLoad": "Y"
        };
        jboxPostLoad("history_inner", url, data, true);
    }

    function modifyRecruitHistory() {
        var photoTime = $("#photoTime").val();
        var orgFlow = $("#orgFlow_history").val();
        var speId = $("#speId_history").val();
        var docType = $("#docType_history").val();
        var url = "<s:url value='/jsres/manage/recruitHistoryReport'/>";
        var data = {
            "photoTime": photoTime,
            "orgFlow": orgFlow,
            "speId": speId,
            "docType": docType,
            "isLoad": "Y",
            "isModify": "Y"
        };
        jboxPostLoad("history_inner", url, data, true);
    }

    function modifyHistoryData(docType, orgFlow, speId) {
        var photoTime = $("#photoTime").val();
        var url = "<s:url value='/jsres/manage/modifyHistoryData?photoTime='/>" + photoTime + "&orgFlow=" + orgFlow + "&speId=" + speId + "&docType=" + docType;
        var title = "修改历史数据";
        jboxOpen(url, title, 500, 400, true);

    }

    function removeRecruitHistory() {
        var photoTime = $("#photoTime").val();
        jboxConfirm("是否删除当前节点数据?",  function(){
            jboxStartLoading();
            var url = "<s:url value='/jsres/manage/removeHistoryReport'/>";
            var data = {
                "photoTime": photoTime
            };
            jboxPost(url, data, function(resp){
                jboxTip(resp);
                setTimeout(function(){
                    jboxClose();
                    var url = "<s:url value='/jsres/manage/recruitHistoryReport'/>";
                    jboxPostLoad("history_load", url, null, true);
                    jboxEndLoading();
                }, 1000);
            }, null, false);
        });
    }

    function clearSearchArgs(tab) {
        if (tab == "statistics") {
            $("#orgFlow").val("");
            $("#speId").val("");
            $("#docType").val("");
            $("#statusId").val("");
            searchRecruitInfo();
        } else {
            $("#orgFlow_history").val("");
            $("#speId_history").val("");
            $("#docType_history").val("");
            searchRecruitHistory();
        }
    }


</script>
</html>
