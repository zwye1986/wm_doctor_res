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

    </style>
</head>
<body>
<div style="padding:20px;width:calc(90vw - 208px - 57px)">
    <div class="tab-container">
        <div class="tab active" data-name="statistics">招录统计分析</div>
    </div>

    <!-- 统计 -->
    <div class="main-container" data-index="statistics">
        <iframe src="https://restest.njpdxx.com:5650/app/dashboards?auth_provider_hint=anonymous1#/view/2bb4a74b-e2b8-48ed-9862-e0edc26f21cb?embed=true&_g=(filters:!(),refreshInterval%3A(pause%3A!t%2Cvalue%3A60000)%2Ctime%3A(from%3Anow-15m%2Cto%3Anow))"  height="2100" width="1460"></iframe>
<%--        <iframe src="https://restest.njpdxx.com:5650/app/dashboards?auth_provider_hint=anonymous1#/view/2bb4a74b-e2b8-48ed-9862-e0edc26f21cb?embed=true&_g=(filters:!(),refreshInterval%3A(pause%3A!t%2Cvalue%3A60000)%2Ctime%3A(from%3Anow-15m%2Cto%3Anow))&hide-filter-bar=true"  height="2100" width="1460"></iframe>--%>
    </div>
</div>
</body>
</html>
