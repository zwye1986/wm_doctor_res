<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
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
<html>
<head>
    <style type="text/css">
        .boxHome .item:HOVER{background-color: #eee;}
        .cur{color:red;}
        .searchTable{
            width: auto;
        }
        .searchTable td{
            width: auto;
            height: auto;
            line-height: auto;
            text-align: left;
            max-width: 150px;;
        }
        .searchTable .td_left{
            word-wrap:break-word;
            width:5em;
            height: auto;
            line-height: auto;
            text-align: right;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#startDate').datepicker();
            ;
            $('#endDate').datepicker();

            $('#sessionNumber').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode: 2,
                format: 'yyyy'
            });
            $(".showInfo").on("mouseover mouseout",
                    function () {
                        $(".theInfo", this).toggle();
                    }
            );
            changeTrainSpes();
            <c:forEach items="${resDocTypeEnumList}" var="type">
                <c:forEach items="${datas}" var="data">
                if("${data}"=="${type.id}"){
                    $("#"+"${data}").attr("checked","checked");
                    $("#export"+"${data}").attr("checked","checked");
                }
                </c:forEach>
                <c:if test="${empty datas}">
                    $("#"+"${type.id}").attr("checked","checked");
                    $("#export"+"${type.id}").attr("checked","checked");
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
        function toPage(page) {
            console.log(page);
            page = page || "${param.currentPage}";
            console.log(page);
            $("#currentPage").val(page);
            searchAtendanceByitems()
        }
        function searchAtendanceByitems() {
            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();
            var currentDate = "${pdfn:getCurrDate()}";
            if (startDate > currentDate) {
                var startDate = $("#itemsDate").attr("schStartDate");
                var endDate = $("#itemsDate").attr("schEndDate");
                $("#startDate").val(startDate);
                $("#endDate").val(endDate);
                jboxTip("开始日期不能大于当前日期");
                return;
            }
            if (startDate > endDate) {
                var startDate = $("#itemsDate").attr("schStartDate");
                var endDate = $("#itemsDate").attr("schEndDate");
                $("#startDate").val(startDate);
                $("#endDate").val(endDate);
                jboxTip("开始日期不能大于结束日期");
                return;
            }
            var form = $("#searchForm").serialize();
            jboxPostLoad("mainDiv", "<s:url value='/jsres/teacher/attendanceSearchTabAcc/${roleId}'/>?baseFlag=${baseFlag}", $("#searchForm").serialize(), true);
        }
        function exportAttendance() {
            if (${empty jsResAttendanceExts}) {
                jboxTip("当前无记录!");
                return;
            }
            var schStartDate = $("#exportForm").find("input[name=schStartDate]").val();
            var schEndDate = $("#exportForm").find("input[name=schEndDate]").val();
            var iDays = getDays(schStartDate,schEndDate);
            if(iDays>30){
                jboxTip("导出时间段不能大于30天!");
                return;
            }
            var roleId = "${roleId}";
            var url = "<s:url value='/jsres/teacher/exportAttendanceTab/${roleId}'/>";
            console.log(url);
            jboxTip("导出中…………");
            jboxSubmit($("#exportForm"), url, null, null, false);
        }
        function searchDeptList(orgFlow){
            jboxPost("<s:url value='/jsres/teacher/searchDeptList'/>?orgFlow="+orgFlow, null, function (resp) {
                $("#deptFlow").empty();
                $("#deptFlow").append("<option value=''>请选择</option>");
                if (null != resp && resp.length > 0) {
                    for(var i= 0;i<resp.length;i++){
                        $("#deptFlow").append("<option value='"+resp[i].deptFlow+"'>"+resp[i].deptName+"</option>");
                    }
                }
            },null,false);
        }
        function changeTrainSpes(t){
            var trainCategoryid=$("#trainingTypeId").val();
            if(trainCategoryid =="${dictTypeEnumDoctorTrainingSpe.id}"){
                $("#derateFlagLabel").show();
            }else{
                $("#derateFlag").attr("checked",false);
                $("#derateFlagLabel").hide();
            }
            if(trainCategoryid==""){
                $("select[name=trainingSpeId] option[value != '']").remove();
                return false;
            }
            $("select[name=trainingSpeId] option[value != '']").remove();
            $("#"+trainCategoryid+"_select").find("option").each(function(){
                $(this).clone().appendTo($("#trainingSpeId"));
            });
            return false;
        }
        function setDateTime(vari,value){
            $("#exportForm").find("input[name="+vari+"]").val(value);
        }
        function getDays(strDateStart,strDateEnd) {
            var strSeparator = "-"; //日期分隔符
            var oDate1;
            var oDate2;
            var iDays;
            oDate1 = strDateStart.split(strSeparator);
            oDate2 = strDateEnd.split(strSeparator);
            var strDateS = new Date(oDate1[0], oDate1[1] - 1, oDate1[2]);
            var strDateE = new Date(oDate2[0], oDate2[1] - 1, oDate2[2]);
            iDays = parseInt(Math.abs(strDateS - strDateE) / 1000 / 60 / 60 / 24)//把相差的毫秒数转换为天数
            return iDays;
        }
    </script>
</head>

<body>
<div class="main_bd" id="div_table_0">
    <div style="display: none;">
        <select id="WMFirst_select">
            <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
                <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="WMSecond_select">
            <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
                <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="AssiGeneral_select">
            <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
                <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="DoctorTrainingSpe_select">
            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>

    </div>
    <div class="div_search">
        <form id="exportForm" action="" method="post">
            <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
            <input type="hidden" name="studentName" value="${param.studentName}"/>
            <input type="hidden" name="trainingTypeId" value="${param.trainingTypeId}"/>
            <input type="hidden" name="trainingSpeId" value="${param.trainingSpeId}"/>
            <input type="hidden" name="orgFlow" value="${param.orgFlow}"/>
            <input type="hidden" name="deptFlow" value="${param.deptFlow}"/>
            <input type="hidden" name="sessionNumber" value="${param.sessionNumber}"/>
            <input type="hidden" name="schStartDate" value="${schStartDate}"/>
            <input type="hidden" name="schEndDate" value="${schEndDate}"/>

            <c:forEach items="${resDocTypeEnumList}" var="type">
                <input type="checkbox" style="display: none;" id="export${type.id}" value="${type.id}"class="docType" name="datas" />
            </c:forEach>
        </form>
        <form id="searchForm" action="" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
            <input id="itemsDate" type="hidden" schStartDate="${schStartDate}" schEndDate="${schEndDate}"/>
            <input id="searchType" type="hidden" name="searchType" value=""/>
            <c:if test="${isQuality eq 'Y'}">
                <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
                    <tr>

                        <td class="td_left" style="width: 70px">学生姓名：</td>
                        <td>
                            <input type="text" name="studentName" class="input" value="${param.studentName}"  style="width: 100px;margin-left: 0px"/>
                        </td>
                        <td class="td_left"  style="width: 70px">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber"  value="${param.sessionNumber}" class="input" readonly="readonly"style="width: 100px;margin-left: 0px"/>
                        </td>
                        <c:if test="${JointOrgCount ne '0'}">
                            <td class="td_left"  style="width: 70px">培训基地：</td>
                            <td>
                                <select class="select" id="orgFlow" name="orgFlow" style="width: 106px;" onchange="searchDeptList(this.value)">
                                    <option value="" <c:if test="${empty param.orgFlow}">selected="selected"</c:if>>全部</option>
                                    <c:forEach items="${orgList}" var="org">
                                        <option value="${org.orgFlow}" <c:if test="${param.orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </c:if>
                        <td class="td_left" style="width: 70px">科&#12288;&#12288;室：</td>
                        <td>
                            <select class="select" id="deptFlow" name="deptFlow" style="width: 106px;">
                                <option value="">全部</option>

                                <c:forEach items="${deptList}" var="dept" varStatus="num">
                                    <option value="${dept.deptFlow}" name="${dept.deptFlow}"
                                            <c:if test="${param.deptFlow==dept.deptFlow}">selected="selected"</c:if>>${dept.deptName}</option>
                                </c:forEach>

                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left" style="width: 70px">考勤时间：</td>
                        <td colspan="3">
                            <input type="text" id="startDate" name="schStartDate" value="${schStartDate}"
                                   class="input datepicker" onchange="setDateTime('schStartDate',this.value);"
                                   readonly="readonly" style="width: 90px;"/>
                            ~
                            <input type="text" id="endDate" name="schEndDate" value="${schEndDate}"
                                   class="input datepicker" onchange="setDateTime('schEndDate',this.value);"
                                   readonly="readonly" style="width: 90px;"/>
                        </td>
                        <c:choose>
                            <c:when test="${sessionScope.userListScope == GlobalConstant.USER_LIST_BASE}">
                                <td class="td_left">人员类型：</td>
                                <td colspan="3">
                                    <c:forEach items="${resDocTypeEnumList}" var="type">
                                        <label><input type="checkbox" id="${type.id}" value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                                    </c:forEach>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td class="td_left">人员类型：</td>
                                <td colspan="3">
                                    <c:forEach items="${resDocTypeEnumList}" var="type">
                                        <label><input type="checkbox" id="${type.id}" value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                                    </c:forEach>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <c:choose>
                            <c:when test="${sessionScope.userListScope == GlobalConstant.USER_LIST_BASE}">
                                <td colspan="4">
                                    <input type="button" class="btn_green" onclick="toPage(1);" value="查&#12288;询"/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td colspan="4">
                                    <input type="button" class="btn_green" onclick="toPage(1);"
                                           value="查&#12288;询"/>
                                    &#12288;<input type="button" class="btn_green" onclick="exportAttendance();" value="导&#12288;出"/>
                                </td>
                            </c:otherwise>
                        </c:choose>
                            </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${isQuality ne 'Y'}">
            <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
                <tr>

                    <td class="td_left">学生姓名：</td>
                    <td>
                        <input type="text" name="studentName" class="input" value="${param.studentName}"  style="width: 100px;margin-left: 0px"/>
                    </td>
                    <td class="td_left">培训类别：</td>
                    <td>
                        <select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width: 106px;">
                            <option value="AssiGeneral">助理全科</option>
                            <%--<option value="">全部</option>
                            <c:forEach items="${trainCategoryEnumList}" var="trainCategory">
                                <option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                            </c:forEach>--%>
                        </select>
                    </td>
                    <td class="td_left">培训专业：</td>
                    <td>
                        <select name="trainingSpeId" id="trainingSpeId" class="select"  style="width: 106px;">
                            <option value="">全部</option>
                        </select>
                    </td>
                    <td class="td_left">年&#12288;&#12288;级：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber"  value="${param.sessionNumber}" class="input" readonly="readonly"style="width: 100px;margin-left: 0px"/>
                    </td>
                </tr>
                <tr>

                    <c:if test="${JointOrgCount ne '0'}">
                        <td class="td_left">培训基地：</td>
                        <td>
                            <select class="select" id="orgFlow" name="orgFlow" style="width: 106px;" onchange="searchDeptList(this.value)">
                                <option value="" <c:if test="${empty param.orgFlow}">selected="selected"</c:if>>全部</option>
                                <c:forEach items="${orgList}" var="org">
                                    <option value="${org.orgFlow}" <c:if test="${param.orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </c:if>
                    <td class="td_left">科&#12288;&#12288;室：</td>
                    <td>
                    <select class="select" id="deptFlow" name="deptFlow" style="width: 106px;">
                        <option value="">全部</option>

                            <c:forEach items="${deptList}" var="dept" varStatus="num">
                                <option value="${dept.deptFlow}" name="${dept.deptFlow}"
                                        <c:if test="${param.deptFlow==dept.deptFlow}">selected="selected"</c:if>>${dept.deptName}</option>
                            </c:forEach>

                    </select>
                    </td>
                    <td class="td_left">考勤时间：</td>
                    <td colspan="3">
                        <input type="text" id="startDate" name="schStartDate" value="${schStartDate}"
                               class="input datepicker" onchange="setDateTime('schStartDate',this.value);"
                               readonly="readonly" style="width: 90px;"/>
                        ~
                        <input type="text" id="endDate" name="schEndDate" value="${schEndDate}"
                               class="input datepicker" onchange="setDateTime('schEndDate',this.value);"
                               readonly="readonly" style="width: 90px;"/>
                    </td>
                </tr>
                <tr>
                <c:choose>
                    <c:when test="${sessionScope.userListScope == GlobalConstant.USER_LIST_BASE}">
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                        <td colspan="4">
                            <input type="button" class="btn_green" onclick="toPage(1);" value="查&#12288;询"/>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                        <td colspan="4">
                            <input type="button" class="btn_green" onclick="toPage(1);"
                                   value="查&#12288;询"/>
                            &#12288;<input type="button" class="btn_green" onclick="exportAttendance();" value="导&#12288;出"/>
                        </td>
                    </c:otherwise>
                 </c:choose>
                    <%--<td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}" value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td colspan="4">
                        <input type="button" class="btn_green" onclick="toPage(1);"
                               value="查&#12288;询"/>
                        &#12288;<input type="button" class="btn_green" onclick="exportAttendance();" value="导&#12288;出"/>
                       &lt;%&ndash; <c:when test="${sessionScope.userListScope == GlobalConstant.USER_LIST_BASE}">

                        </c:when>&ndash;%&gt;
                    </td>--%>
                </tr>
            </table>
            </c:if>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>姓名</th>
                <th>年级</th>
                <th>人员类型</th>
                <th>培训类别</th>
                <th>专业</th>
                <th>轮转科室</th>
                <th>出勤（天数）</th>
                <th>缺勤（天数）</th>
                <th>轮休（天数）</th>
            </tr>
            <c:forEach items="${jsResAttendanceExts}" var="jsResAttendanceExt">
                <tr>
                    <td>${jsResAttendanceExt.userName}</td>
                    <td>${jsResAttendanceExt.sessionNumber}</td>
                    <td>${jsResAttendanceExt.doctorTypeName}</td>
                    <td>${jsResAttendanceExt.trainingTypeName}</td>
                    <td>${jsResAttendanceExt.trainingSpeName}</td>
                    <td>${jsResAttendanceExt.deptName}</td>
                    <td>${jsResAttendanceExt.cqNum}</td>
                    <td>${jsResAttendanceExt.qqNum}</td>
                    <td>${jsResAttendanceExt.lxNum}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty jsResAttendanceExts}">
                <tr>
                    <td colspan="99">无记录</td>
                </tr>
            </c:if>
        </table>

    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(jsResAttendanceExts)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>

</body>
</html>
 