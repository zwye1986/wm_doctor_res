<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        .cur{ background-color: pink;}
        .basic td,tr{border: 0}
    </style>
    <script type="text/javascript">
        /**
         *模糊查询加载
         */
        (function($){
            $.fn.likeSearchInit1 = function(option){
                option.clickActive = option.clickActive || null;

                var searchInput = this;
                searchInput.on("keyup focus",function(){
                    $("#boxHome1").show();
                    if($.trim(this.value)){
                        $("#boxHome1 .item").hide();
                        var items = $("#boxHome1 .item[value*='"+this.value+"']").show();
                        if(!items){
                            $("#boxHome1").hide();
                        }
                    }else{
                        $("#boxHome1 .item").show();
                    }
                });
                searchInput.on("blur",function(){
                    if(!$("#boxHome1.on").length){
                        $("#boxHome1").hide();
                    }
                });
                $("#boxHome1").on("mouseenter mouseleave",function(){
                    $(this).toggleClass("on");
                });
                $("#boxHome1 .item").click(function(){
                    $("#boxHome1").hide();
                    var value = $(this).attr("value");
                    $("#itemName").val(value);
                    searchInput.val(value);
                    searchInput.attr("flow",$(this).attr("flow"));
                    if(option.clickActive){
                        option['clickActive']($(this).attr("flow"));
                    }
                });
            };
        })(jQuery);
        function toPage(page){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            $("#currentPage").val(page);
            search();
        }
        function search(){
            if($("#fwhName").val() != ""){
                $("#fwhFlow").val($("#fwhName").attr("flow"));
            }else{
                $("#fwhFlow").val("");
            }
            var fwhFlow=$("#fwhFlow").val();
            if($("#orgName").val() != ""){
                $("#orgFlow").val($("#orgName").attr("flow"));
            }else{
                $("#orgFlow").val("");
            }
            if('${adminFlag}'=="Y"){
                if(fwhFlow==""){
                    jboxTip("请选择分委会！");
                    return;
                }
            }
            var form = $("#recSearchForm");
            jboxStartLoading();
            form.submit();
        }
        function leadTo(){
            jboxOpen("<s:url value='/nyzl/admissionStudent/leadTo'/>?stuSignFlag=${stuSignFlag}","导入",500,150);
        }

        function exportAdmissionStudent(){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            var url = "<s:url value='/nyzl/admissionStudent/exportAdmissionStudent?stuSignFlag=${stuSignFlag}&adminFlag=${adminFlag}'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#recSearchForm"), url, null, null, false);
            jboxEndLoading();
        }

        $(function(){
            $("#fwhName").likeSearchInit({});
            $("#orgName").likeSearchInit1({});
        });
        function editRecord(recordFlow,educationType){
            var url = "<s:url value='/nyzl/admissionStudent/showAdmissionStudent'/>?recordFlow="+recordFlow+"&stuSignFlag=${stuSignFlag}&adminFlag=${adminFlag}"+"&educationType="+educationType;
            jboxOpen(url,'编辑',800,600);
        }
    </script>
    <style type="text/css">
        .table tr td, .table tr th{border-bottom: 0px; }
        .table1 td{border: none;}
        .table1{border: none;}
        .basicData{border:1px solid #bbbbbb;}
        .basicData th,.basicData td { border-right:1px solid #bbbbbb; border-bottom:1px solid #bbbbbb; height:35px;line-height:140%;}
        .basicData tbody th { background:#f9f9f9; color:#333; height:35px; text-align:center;}
        .basicData td { text-align:center; line-height:140%;}
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="recSearchForm" method="post" action="<s:url value='/nyzl/admissionStudent/admissionStudentList?stuSignFlag=${stuSignFlag}&adminFlag=${adminFlag}'/>">
            <table style="width: 100%;margin: 10px 0px 5px -10px;border: none;">
                <tr>
                    <td style="border: none;">
                        <table class="basic table1" style="width: 980px">
                            <input id="currentPage" type="hidden" name="currentPage"/>
                            <input type="hidden" name="from" value="${param.from }"/>
                            <input type="hidden" name="flag" value="${flag}"/>
                            <tr>
                                <td>考生编号：<input style="width:137px;" value="${param.stuNo}" name="stuNo" />
                                    &#12288;姓&#12288;&#12288;名：<input style="width:137px;" value="${param.stuName}" name="stuName" />
                                    &#12288;是否录取：<select style="width: 141px;" name="admissionFlag">
                                        <option value="">全部</option>
                                        <option value="Y" <c:if test="${'Y' eq param.admissionFlag}">selected="selected"</c:if>>是</option>
                                        <option value="N" <c:if test="${'N' eq param.admissionFlag}">selected="selected"</c:if>>否</option>
                                    </select>
                                    &#12288;是否调剂生：<select style="width: 141px;" name="swapFlag">
                                        <option value="">全部</option>
                                        <option value="Y" <c:if test="${'Y' eq param.swapFlag}">selected="selected"</c:if>>是</option>
                                        <option value="N" <c:if test="${'N' eq param.swapFlag}">selected="selected"</c:if>>否</option>
                                    </select>
                                    <br/>
                                    专业名称：<input type="text" name="speName" value="${param.speName}" style="width: 137px;"/>
                                    &#12288;方&#12288;&#12288;向：<input type="text" name="directionName" value="${param.directionName}" style="width: 137px;"/>
                                    &#12288;学位类型：<select style="width: 141px;" name="degreeTypeId">
                                        <option value="">全部</option>
                                        <c:forEach items="${dictTypeEnumNyzlDegreeTypeList}" var="degreeType">
                                            <option value="${degreeType.dictId}" <c:if test="${param.degreeTypeId==degreeType.dictId}">selected="selected"</c:if>>${degreeType.dictName}</option>
                                        </c:forEach>
                                    </select>
                                    &#12288;&#12288;年&#12288;&#12288;份：<input style="width:137px;" value="${(empty param.recruitYear)?thisYear:param.recruitYear}" name="recruitYear" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" /><br/>
                                    <c:if test="${adminFlag eq 'Y'}">
                                        分&ensp;委&ensp;会：<input id="fwhName" type="text" name="fwhName" value="${param.fwhName}" autocomplete="off" title="${param.fwhName}" onmouseover="this.title = this.value" flow="${param.fwhFlow}" style="width: 137px;" />
                                        <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:64px;">
                                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                                                <c:forEach items="${deptList}" var="dept">
                                                    <p class="item" flow="${dept.deptFlow}" value="${dept.deptName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${dept.deptName}</p>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <input type="hidden" id="fwhFlow" name="fwhFlow" value="${param.fwhFlow}"/>
                                        &#12288;培养单位：<input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" onmouseover="this.title = this.value" flow="${param.orgFlow}" style="width: 137px;" />
                                        <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:285px;">
                                            <div id="boxHome1" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                                                <c:forEach items="${orgs}" var="org">
                                                    <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <input type="hidden" id="orgFlow" name="orgFlow" value="${param.orgFlow}"/><span style="padding-left: 13px;"></span>
                                    </c:if>
                                    <c:if test="${stuSignFlag eq 'DoctoralStudent'}">
                                        博士类型：<select style="width: 141px;" name="educationTypeId">
                                        <option value="">全部</option>
                                        <option value="1" <c:if test="${'1' eq param.educationTypeId}">selected="selected"</c:if>>全日制</option>
                                        <option value="0" <c:if test="${'0' eq param.educationTypeId}">selected="selected"</c:if>>在职</option>
                                        </select>
                                    </c:if>
                                    <input type="button" name="" class="search" onclick="search();" value="查&#12288;询"/>
                                    <c:if test="${adminFlag ne 'Y'}">
                                        <input type="button" name="" class="search" onclick="leadTo();" value="导&#12288;入" />
                                    </c:if>
                                    <input type="button" name="" class="search" onclick="exportAdmissionStudent();" value="导&#12288;出" />
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
        <form>
            <table class="basicData" style="min-width:1500px;">
                <tr style="font-weight: bold;">
                    <td style="width: 60px;">考生编号</td>
                    <td style="width: 40px;">姓名</td>
                    <td style="width: 60px;">身份证号</td>
                    <td style="width: 100px;">分委会</td>
                    <td style="width: 100px;">培养单位</td>
                    <td style="width: 70px;">录取专业代码</td>
                    <td style="width: 100px;">录取专业名称</td>
                    <td style="width: 75px;">录取专业研<br/>究方向代码</td>
                    <td style="width: 100px;">录取专业<br/>研究方向</td>
                    <td style="width: 34px;">初试成绩</td>
                    <td style="width: 34px;">复试成绩</td>
                    <td style="width: 34px;">总成绩</td>
                    <td style="width: 100px;">导师所在单位</td>
                    <td style="width: 40px;">导师姓名</td>
                    <td style="width: 100px;">导师方向</td>
                    <td style="width: 100px;">报考方向</td>
                    <td style="width: 80px;">复试小组入学<br/>总成绩排名</td>
                    <td style="width: 35px;">是否<br/>录取</td>
                    <td style="width: 40px;">学位类型</td>
                    <td style="width: 40px;">是否<br/>调剂生</td>
                    <td style="width: 38px;">复试批次</td>
                    <td style="width: 40px;">操作</td>
                </tr>
                <c:forEach items="${recordList}" var="record">
                    <tr>
                        <td>${record.stuNo}</td>
                        <td>${record.stuName}</td>
                        <td>${record.cardNo}</td>
                        <td>${record.fwhName}</td>
                        <td>${record.orgName}</td>
                        <td>${record.speId}</td>
                        <td>${record.speName}</td>
                        <td>${record.stuSpeDirectionId}</td>
                        <td>${record.stuSpeDirectionName}</td>
                        <td>${record.testGrade}</td>
                        <td>${record.retestGrade}</td>
                        <td>${record.totalGrade}</td>
                        <td>${record.teaWorkUnit}</td>
                        <td>${record.teaName}</td>
                        <td>${record.teaDirectionName}</td>
                        <td>${record.stuDirectionName}</td>
                        <td>${record.groupRanking}</td>
                        <td>${empty record.admissionFlag?"":('Y' eq record.admissionFlag?"是":"否")}</td>
                        <td>${record.degreeTypeName}</td>
                        <td>${empty record.swapFlag?"":('Y' eq record.swapFlag?"是":"否")}</td>
                        <td>${record.swapBatchName}</td>
                        <td>
                            <a href="javascript:void(0);" onclick="editRecord('${record.recordFlow}','${record.educationTypeId}')" style="color: blue;">编辑</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty recordList}">
                    <tr>
                        <td colspan="99" >无记录！</td>
                    </tr>
                </c:if>
            </table>
        </form>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(recordList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
