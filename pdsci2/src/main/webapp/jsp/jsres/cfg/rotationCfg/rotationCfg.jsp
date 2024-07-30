<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<html>
<head>
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        .formItem{
            width: 233px;
        }

        .search_form > .flex {
            flex-wrap: wrap;
        }

        .searchCss {
            float: left;
            min-width: 220px;
        }

        .search_form > .flex .searchCss {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 14px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#sessionNumber').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode:2,
                format:'yyyy'
            });
        });

        function getRotationCfgList() {
            var sessionYear = $("#sessionNumber").val();
            if (sessionYear < "2024") {
                jboxTip("2024级以前的学员无需配置轮转方案，请重新选择年级进行配置！");
                return;
            }
            var url = "<s:url value='/jsres/message/rotationCfg'/>";
            jboxStartLoading();
            jboxPost(url, $("#searchForm").serialize(), function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        //方案详情
        function rotationDetail(rotationFlow,title){
            jboxOpen("<s:url value='/sch/template/ruleView'/>?rotationFlow="+rotationFlow,title,1200,700);
        }

        function selectRotation(speId, rotationFlow, sessionNumber) {
            jboxConfirm("确认选择此方案？",function(){
                jboxPost("<s:url value='/jsres/message/saveRotationCfg'/>?rotationFlow=" + rotationFlow + "&speId=" + speId + "&sessionNumber=" + sessionNumber, null, function(resp){
                    window.parent.getRotationCfgList();
                    jboxClose();
                    jboxTip(resp);
                },null,true);
            }, function () {
                window.parent.getRotationCfgList();
                jboxClose();
            });
        }
    </script>
</head>

<body>
    <div class="main_hd">
        <h2 class="underline">轮转方案配置</h2>
    </div>
    <div class="main_bd" id="div_table_0">
        <div class="div_search"  style="padding: 24px 40px 6px 16px">
            <form id="searchForm" class="search_form" action="" method="post">
                <div class="flex">
                    <div class="searchCss formItem">
                        <label>&#12288;年&#12288;&#12288;级：</label>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${sessionNumber}" class="input" readonly="readonly" style="width: 128px;margin: 0px 4px;"/>
                    </div>
                    <div class="searchCss formItem">
                        <label>&#12288;&#12288;培训专业：</label>
                        <select name="speId" id="speId" class="select" style="width: 128px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <option <c:if test="${speId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div style="margin: 4px 0px;">
                        &#12288;&#12288;<input class="btn_green"  type="button" value="查&#12288;询" onclick="getRotationCfgList();"/>&#12288;
                    </div>
                </div>
            </form>
        </div>
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th width="20%">年级</th>
                    <th width="30%">培训专业</th>
                    <th width="50%">方案选择</th>
                </tr>
                <c:forEach items="${resultList}" var="result">
                    <tr>
                        <td>${sessionNumber}</td>
                        <td>${result["speName"]}</td>
                        <td style="padding-left: 18%;padding-top:5px;padding-bottom:5px;text-align: left">
                            <c:forEach items="${result['rotationList']}" var="rotation">
                                <input type="radio" id="select" name="${result["speId"]}" value="${rotation.rotationFlow}" onclick="selectRotation('${result["speId"]}', '${rotation.rotationFlow}', '${sessionNumber}');" class="inputText" <c:if test="${rotation.recordStatus eq 'Y'}">checked="checked"</c:if>/>&#12288;
                                <a style="color: blue;cursor: pointer;" onclick="rotationDetail('${rotation.rotationFlow}','${rotation.rotationName}');">${rotation.rotationName}</a></br>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
 