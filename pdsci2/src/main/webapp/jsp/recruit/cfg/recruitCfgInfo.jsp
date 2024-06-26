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

    <script type="text/javascript">
        function updateCfgInfo(){
            if(false==$("#searchForm").validationEngine("validate")){
                return false;
            }
            var startDate = $("#recruitStartDate").val();
            var endDate = $("#recruitEndDate").val();
            if(startDate > endDate){
                jboxTip("开放时间填写有误");
                return false;
            }
            var url = "<s:url value='/recruit/cfgInfo/updateCfgInfo'/>";
            var data = $('#searchForm').serialize();
            jboxPost(url, data, function(resp) {
                location.reload();
            }, null, true)
        }

        function addCfgForm(){
            jboxOpen("<s:url value='/recruit/cfgInfo/addCfgForm'/>", "新增招录信息", 600, 285 ,true);
        }
        
        function searchCfgInfo() {
            var url = "<s:url value='/recruit/cfgInfo/searchCfgInfo'/>";
            var data = $('#searchForm').serialize();
            jboxPost(url,data,function (resp) {
                var jsObject = JSON.parse(resp);
                $("#recruitStartDate").val(jsObject.recruitStartDate);
                $("#recruitEndDate").val(jsObject.recruitEndDate);
                $("#cfgFlow").val(jsObject.cfgFlow);
                $("#isRecruit").val(jsObject.isRecruit);
                changeButton();
            },null,false);
        }

        function setCurrYear() {

            jboxConfirm("确认设置为招录年份？设置之后无法修改招录时间" , function(){
                var url = "<s:url value='/recruit/cfgInfo/setCurrYear'/>";
                var data = $('#searchForm').serialize();
                jboxPost(url,data,function (resp) {
                        searchCfgInfo();
                },null,false);
            });
        }
        
        function changeButton() {
            // alert($("#isRecruit").val());
            if ($("#isRecruit").val() == 'N'){
                $("#notCurrRecruit").show();
                $("#saveBtn").show();
                $("#recruitStartDate").removeAttr("disabled");
                $("#recruitEndDate").removeAttr("disabled");
                $("#isCurrRecruit").hide();
            }else {
                $("#notCurrRecruit").hide();
                $("#saveBtn").hide();
                $("#recruitStartDate").attr("disabled","disabled");
                $("#recruitEndDate").attr("disabled","disabled");
                $("#isCurrRecruit").show();
            }
        }

        document.onreadystatechange = function() {
            if (document.readyState == "complete") {
                changeButton();
            }
        }

    </script>
</head>
<body>
<div class="content">
    <div class="title1 clearfix">
        <div id="tagContent">
            <fieldset>
                <legend>报名时间设置</legend>
                <div style="text-align: center">
                    <form id="searchForm" method="post" >
                        <div class="div_search" style="padding: 10px;">
                            <input id="cfgFlow" name="cfgFlow" value="${recruitCfgInfo.cfgFlow}" hidden/>
                            <input id="isRecruit" name="isRecruit" value="${recruitCfgInfo.isRecruit}" hidden/>
                            <p1 style="margin-right: auto">招录年份：</p1>
                            <select id="recruitYear" name="recruitYear" class="validate[required] xlselect" onchange="searchCfgInfo()">
                                <c:forEach items="${recruitYearList}" var="year">
                                    <option value="${year}" <c:if test="${year eq recruitCfgInfo.recruitYear}">selected="selected"</c:if>>${year}</option>
                                </c:forEach>
                            </select>&nbsp;年&nbsp;&nbsp;&nbsp;

                            <p1 style="margin-right: auto">开放时间：</p1>
                            <input type="text" class="validate[required] xltext" id="recruitStartDate" name="recruitStartDate" value="${recruitCfgInfo.recruitStartDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate: '#F{$dp.$D(\'recruitEndDate\')}'})" readonly="readonly">
                            ~&#12288;
                            <input type="text" class="validate[required] xltext" id="recruitEndDate"   name="recruitEndDate"   value="${recruitCfgInfo.recruitEndDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate: '#F{$dp.$D(\'recruitStartDate\')}'})" readonly="readonly">

                            <input name="notCurrRecruit" id="notCurrRecruit" style="margin-left: 30px" type="button" class="search" onclick="setCurrYear();" value="设置招录"/>
                            <input name="isCurrRecruit" id="isCurrRecruit" style="margin-left: 30px;border:none" type="text" class="text" value="当前为招录年份" readonly="readonly"/>
                        </div>
                    </form>
                </div>
                <div class="button">
                    <input type="button" class="search" onclick="addCfgForm();" value="新&#12288;增"/>
                    <input type="button" class="search" id="saveBtn" onclick="updateCfgInfo();" value="保&#12288;存"/>
                </div>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>