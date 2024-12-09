<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#startDate').datepicker();
            $('#endDate').datepicker();
            <c:forEach items="${resDocTypeEnumList}" var="type">
            <c:forEach items="${datas}" var="data">
            if("${data}"=="${type.id}"){
                $("#"+"${data}").attr("checked","checked");
            }
            </c:forEach>
            <c:if test="${empty datas}">
            $("#"+"${type.id}").attr("checked","checked");
            </c:if>
            </c:forEach>
        });
        function checkAll(obj){
            var f=false;
            if($(obj).attr("checked")=="checked")
            {
                f=true;
            }
            $(".docType").each(function(){
                if(f)
                {
                    $(this).attr("checked","checked");
                }else {
                    $(this).removeAttr("checked");
                }

            });
        }
        function changeAllBox(){
            if($(".docType:checked").length==$(".docType").length)
            {
                $("#all").attr("checked","checked");
            }else{
                $("#all").removeAttr("checked");
            }
        }
        function checkBDDate(dt) {
            var dates = $(':text', $(dt).closest("label"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                dt.value = "";
            }
        }
        var currPage;
        function toPage(page) {
            $("#currentPage").val(page);
            currPage =  $("#currentPage").val();
            var form = $("#searchForm").serialize();
            stuEvaluation(form);
        }
        function showEvalList(trId, obj) {
            if ($("#" + trId).is(":hidden")) {
                $("#" + trId).show();
                $(obj).html("[收缩]");
            } else {
                $("#" + trId).hide();
                $(obj).html("[展开]");
            }
        }
        function editEvalForm(processFlow, timeArea) {
            var url = "<s:url value='/jsres/teacher/editEvalForm'/>?processFlow=" + processFlow + "&timeArea=" + timeArea+ "&page=" + currPage;
            jboxOpen(url, "考评", 900, 550, true);
        }
        function showEvalDetail(recordFlow) {
            var url = "<s:url value='/jsres/teacher/showEvalDetail'/>?recordFlow=" + recordFlow;
            jboxOpen(url, "考评", 900, 550, true);
        }
    </script>
</head>

<body>
<div class="main_hd">
    <h2 class="underline">学员考评</h2>
</div>
<div class="main_bd" id="div_table_0" style="width:1000px;">
    <div style="margin: 10px 40px;line-height: 40px;">
        <form id="searchForm">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            姓&#12288;&#12288;名：<input type="text" name="userName" class="input" style="width: 100px;" value="${param.userName}">&#12288;
            专业：<select name="trainingSpeId" class="select" style="width: 100px;">
            <option></option>
            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
            </c:forEach>
        </select>&#12288;
            轮转时间：
            <label>
                <input type="text" id="startDate" name="schStartDate" value="${param.schStartDate}"
                       onchange="checkBDDate(this)" class="input datepicker" readonly="readonly" style="width:100px"/>
                ~
                <input type="text" id="endDate" name="schEndDate" value="${param.schEndDate}"
                       onchange="checkBDDate(this)" class="input datepicker" readonly="readonly" style="width:100px"/>
            </label>
            &#12288;<label><input type="checkbox" name="evalFlag" ${param.evalFlag eq 'on'?'checked':''}/>待考评</label>
            <br/>
            人员类型：
            <c:forEach items="${resDocTypeEnumList}" var="type">
                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
            </c:forEach>
            &#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
        </form>
    </div>
    <div style="overflow-x:auto;max-width:95%;overflow-y:auto;min-height:500px; margin-left: 20px;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="width: 100px;">姓名</th>
                <th style="width: 100px;">人员类型</th>
                <th style="width: 100px;">专业</th>
                <th style="width: 100px;">轮转科室</th>
                <th style="width: 170px;">轮转时间</th>
                <th style="width: 120px;">操作</th>
            </tr>
            <c:forEach items="${resDoctorSchProcess}" var="process">
                <c:set var="evalList" value="${evalMap[process.processFlow]}"/>
                <tr>
                    <td>${process.userName}</td>
                    <td>${process.doctorTypeName}</td>
                    <td>${process.trainingSpeName}</td>
                    <td>${process.schDeptName}</td>
                    <td>
                        <c:if test="${!empty process.schStartDate || !empty process.schEndDate}">
                            ${process.schStartDate}~${process.schEndDate}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${fn:length(pdfn:splitDateArea(process.schStartDate,process.schEndDate)) gt 1}">
                            <a onclick="showEvalList('${process.processFlow}',this)">[展开]</a>
                        </c:if>
                        <c:if test="${fn:length(pdfn:splitDateArea(process.schStartDate,process.schEndDate)) eq 1}">
                            <c:if test="${process.evalNum gt 0}">
                                <a onclick="showEvalDetail('${evalList[0].recordFlow}')">${evalList[0].isForm eq 'Y'?evalList[0].evalScore:'查看'}</a>
                            </c:if>
                            <c:if test="${process.evalNum le 0}">
                                <a onclick="editEvalForm('${process.processFlow}','${process.schStartDate}~${process.schEndDate}')">[考评]</a>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
                <tr id="${process.processFlow}" style="display: none;">
                    <td colspan="5" style="padding:0px;">
                        <table style="width:100%">
                            <tr>
                                <th>名称</th>
                                <th>时间</th>
                                <th>状态</th>
                                <th>成绩</th>
                            </tr>
                            <c:forEach items="${pdfn:splitDateArea(process.schStartDate,process.schEndDate)}"
                                       var="timeArea" varStatus="i">
                                <c:forEach items="${evalList}" var="eval">
                                    <c:if test="${fn:contains(timeArea,eval.startTime) && fn:contains(timeArea,eval.endTime)}">
                                        <tr>
                                            <td>月度考评表</td>
                                            <td>${timeArea}</td>
                                            <td>已考评</td>
                                            <td>
                                                <c:if test="${eval.isForm eq 'Y'}">
                                                    <a onclick="showEvalDetail('${eval.recordFlow}')">${eval.evalScore}</a>
                                                </c:if>
                                                <c:if test="${eval.isForm ne 'Y'}">
                                                    <a onclick="showEvalDetail('${eval.recordFlow}')">查看</a>
                                                </c:if>
                                            </td>
                                        </tr>
                                        <c:set var="exitId" value="${process.processFlow}_${i.index}"/>
                                    </c:if>
                                </c:forEach>
                                <c:set var="variable" value="${process.processFlow}_${i.index}"/>
                                <c:if test="${exitId ne variable}">
                                    <tr>
                                        <td>月度考评表</td>
                                        <td>${timeArea}</td>
                                        <td>待考评</td>
                                        <td><a onclick="editEvalForm('${process.processFlow}','${timeArea}')">[考评]</a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty resDoctorSchProcess}">
                <tr>
                    <td colspan="99">无记录</td>
                </tr>
            </c:if>
        </table>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(resDoctorSchProcess)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
 