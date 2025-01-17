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
            width: 200px;
            position: relative;
        }
        td.total-td {
            width: 80px;
            font-weight: 600;
        }

        .total-all-td {
            position: sticky;
            right: 0;
            background-color: #e7e6e6;
            z-index: 1;
            box-sizing: border-box;
            outline: 1px solid black;
            font-weight: 600;
        }

        .total-all-td-a {
            position: sticky;
            right: 150px;
            background-color: #e7e6e6;
            z-index: 1;
            box-sizing: border-box;
            outline: 1px solid black;
            font-weight: 600;
        }

        .total-all-td-yb {
            position: sticky;
            right: 225px;
            background-color: #e7e6e6;
            z-index: 1;
            box-sizing: border-box;
            outline: 1px solid black;
            font-weight: 600;
        }

        .total-td-text {
            font-weight: 600;
            text-align: center;
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
            width: 204px;
            height: 1px;
            transform: rotate(12deg);
            transform-origin: top left;
        }

        /* 左下角文字 */
        .table-title-left {
            position: absolute;
            /* 左下角 left:0; bottom: 0; */
            left: 0px;
            bottom: 0px;
            font-size: 13px;
            font-weight: 600;
        }

        /* 右上角文字 */
        .table-title-right {
            position: absolute;
            /* 右上角 right:0; top: 0; */
            right: 0px;
            top: 0px;
            font-size: 13px;
            font-weight: 600;
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
    </style>
</head>
<body>
<div style="padding:20px;width:calc(90vw - 208px - 57px)">
    <!-- 统计 -->
    <div class="main-container" data-index="statistics">
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
                            <td style="cursor: pointer;color: blue;font-weight: 600" onclick="viewReport('spe', '${dict.dictId}')" colspan="2">${dict.dictName}</td>
                            <td colspan="2" rowspan="2" class="total-td">小计</td>
                        </c:forEach>
                        <td style="cursor: pointer;color: blue;font-weight: 600" onclick="viewReport('all', '')" colspan="2" class="total-all-td-a">总计</td>
                        <td colspan="2" rowspan="2" class="total-all-td">合计</td>
                    </tr>
                    <tr>
                        <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList" />
                        <c:forEach items="${applicationScope[dictName] }" var="dict">
                            <td style="font-weight: 600">一般师资</td>
                            <td style="font-weight: 600">骨干师资</td>
                        </c:forEach>
                        <td class="total-all-td-yb">一般师资</td>
                        <td class="total-all-td-a">骨干师资</td>
                    </tr>
                    </thead>
                    <!-- 主体 -->
                    <tbody>
                    <c:forEach items="${searchOrgList}" var="org">
                        <c:set var="orgFlow" value="${org.orgFlow}" />
                        <c:set var="orgSpe" value="${orgSpeList[orgFlow]}" />
                        <tr>
                            <td style="cursor: pointer;color: blue" onclick="viewReport('org', '${orgFlow}')">${org.orgName}</td>
                            <c:set var="orgSpeInfo" value="${orgSpe[orgFlow]}" />
                            <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList" />
                            <c:forEach items="${applicationScope[dictName] }" var="dict">
                                <c:set var="GeneralFaculty" value="${dict.dictId}GeneralFaculty" />
                                <c:set var="KeyFaculty" value="${dict.dictId}KeyFaculty" />
                                <c:set var="all" value="${dict.dictId}all" />
                                <td style="text-align: center">${empty orgSpeInfo[GeneralFaculty] ? 0 : orgSpeInfo[GeneralFaculty]}</td>
                                <td style="text-align: center">${empty orgSpeInfo[KeyFaculty] ? 0 : orgSpeInfo[KeyFaculty]}</td>
                                <td style="text-align: center" colspan="2" class="total-td-text">${empty orgSpeInfo[all] ? 0 : orgSpeInfo[all]}</td>
                            </c:forEach>
                            <td style="text-align: center" class="total-all-td-yb">${empty orgSpeInfo["GeneralFacultyall"] ? 0 : orgSpeInfo["GeneralFacultyall"]}</td>
                            <td style="text-align: center" class="total-all-td-a">${empty orgSpeInfo["KeyFacultyall"] ? 0 : orgSpeInfo["KeyFacultyall"]}</td>
                            <td style="text-align: center" colspan="2" class="total-all-td total-td-text">${empty orgSpeInfo["all"] ? 0 : orgSpeInfo["all"]}</td>
                        </tr>
                    </c:forEach>
                    </tbody>

                    <!-- footer -->
                    <tfoot>
                    <td>合计</td>
                    <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList" />
                    <c:forEach items="${applicationScope[dictName] }" var="dict">
                        <c:set var="GeneralFaculty" value="${dict.dictId}GeneralFaculty" />
                        <c:set var="KeyFaculty" value="${dict.dictId}KeyFaculty" />
                        <c:set var="all" value="${dict.dictId}all" />
                        <td style="text-align: center">${empty speAll[GeneralFaculty] ? 0 : speAll[GeneralFaculty]}</td>
                        <td style="text-align: center">${empty speAll[KeyFaculty] ? 0 : speAll[KeyFaculty]}</td>
                        <td style="text-align: center" colspan="2">${empty speAll[all] ? 0 : speAll[all]}</td>
                    </c:forEach>
                    <td style="text-align: center" class="total-all-td-yb">${empty speAll["GeneralFacultyall"] ? 0 : speAll["GeneralFacultyall"]}</td>
                    <td style="text-align: center" class="total-all-td-a">${empty speAll["KeyFacultyall"] ? 0 : speAll["KeyFacultyall"]}</td>
                    <td style="text-align: center" class="total-all-td" colspan="2">${empty speAll["all"] ? 0 : speAll["all"]}</td>
                    </tfoot>
                </table>
            </div>
        </div>

    </div>
</div>
</body>

<script>

    function viewReport(type, flow) {
        if (type == "org") {
            var url = "<s:url value='/jsres/statistic/globalTeacherReportByOrg'/>?orgFlow=" + flow;
            jboxLoad("div_table_0", url, true);
        }
        if (type == "spe") {
            var url = "<s:url value='/jsres/statistic/globalTeacherReportBySpe'/>?speId=" + flow;
            jboxLoad("div_table_0", url, true);
        }
        if (type == "all") {
            var url = "<s:url value='/jsres/statistic/globalTeacherReportAll'/>";
            jboxLoad("div_table_0", url, true);
        }
    }


</script>
</html>
