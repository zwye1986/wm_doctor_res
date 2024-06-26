<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/css/font.css'/>"></link>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
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
    <style type="text/css">
        .xuanze {
            background-color: #D0E3F2;
        }
        .mouseover{
            background-color: #D0E3F2;
        }
        .noteItems {
            border-bottom: 1px solid #D0E3F2;
        }
        .noteItems p{
            white-space:nowrap;/* 强制在同一行内显示所有文本，直到文本结束或者遭遇 br 对象。不换行 */
            overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow:ellipsis;/* IE 专有属性，当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
        }
    </style>
    <script type="text/javascript">
        $(function () {
            //鼠标的移入移出
            $(".noteItems").mouseover(function () {
                $(this).addClass("mouseover");
            }).mouseout(function () {
                $(this).removeClass("mouseover");
            });
            $(".noteItems").live("click",function(){
                $("#assessmentList").children().removeClass("xuanze");
                $(this).addClass("xuanze");
            });
            $(".noteItems").eq(0).addClass("xuanze");
        });

        $(function(){
            if("" == "${assessmentList[0].doctorFlow}"){
                return;
            }
            $('#asswssmentDetail').load("<s:url value='/res/disciple/initAnnualAssessmentDetail/${roleScope}?doctorFlow=${assessmentList[0].doctorFlow}&recordFlow=${assessmentList[0].recordFlow}&operaFlag=${operaFlag}'/>");
        });

        function loadAsswssmentDetail(doctorFlow,recordFlow,recordYear,discipleStartDate,discipleEndDate){
            if(!discipleStartDate){
                discipleStartDate = "";
            }
            if(!discipleEndDate){
                discipleEndDate = "";
            }
            if(!recordYear){
                recordYear = "";
            }
            var url = "<s:url value='/res/disciple/initAnnualAssessmentDetail/${roleScope}?doctorFlow='/>"
                    +doctorFlow+"&recordFlow="+recordFlow+"&operaFlag=${operaFlag}"
                    +"&recordYear="+recordYear+"&discipleStartDate="+discipleStartDate+"&discipleEndDate="+discipleEndDate;
            jboxGet(url , null , function(data){
                $('#asswssmentDetail').html(data);
            }, null , false);
        }

        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function selectfollowTime(){
        	var html = $("#followTime").html();
        	jboxOpenContent(html,"请选择" , 400,270,true);
        }
        function saveTimeInfo(doctorFLow,obj)
        {
            var recordYear = $("#recordYearInputHid").val();
            var discipleStartDate = $("#discipleStartDateInputHid").val();
            var discipleEndDate = $("#discipleEndDateInputHid").val();
            if(!recordYear || !discipleStartDate || !discipleEndDate){
                jboxInfo("年度，跟师开始/结束时间不允许为空！");
                return false;
            }
            //检查时间是否有重叠
            var flag = isDateOverlapped(discipleStartDate,discipleEndDate);
            if(flag){
                jboxInfo("跟师时间与已有数据的跟师时间有重叠！");
                return false;
            }
            loadAsswssmentDetail('${resDoctor.doctorFlow}','',recordYear,discipleStartDate,discipleEndDate);

            var openDialog = top.dialog.get('openDialog');
            if(openDialog!=null&&openDialog.open){
                openDialog.close().remove();
            }
        }
        function initTime(inputHid,strV){
            $("#"+inputHid).val(strV);
        }

        function  isDateOverlapped(startDate,endDate){
            var flag = false;
            var url = "<s:url value='/res/disciple/isDateOverlapped?startDate='/>"
                    +startDate+"&endDate="+endDate;
            jboxGetAsync(url , null , function(data){
                if(data == "1"){
                    flag = true;
                }else{
                    flag=false;
                }
            }, null , false);
            return flag;
        }
    </script>
</head>
<body>
<div style="display: none">
    <form id="searchForm" action="<s:url value="<s:url value='/res/disciple/initAnnualAssessment/${roleScope}'"/>"
          method="post">
        <input type="hidden" name="userFlow" value="${resDoctor.doctorFlow}"/>
    </form>
</div>
<div class="mainright">
    <div class="content">
        <input type="hidden" id="recordYearInputHid">
        <input type="hidden" id="discipleStartDateInputHid">
        <input type="hidden" id="discipleEndDateInputHid">
        <div style="display: none;">
            <div id="followTime">
                <div style="height: 10px;"></div>
                <span style="margin-left:66px;" class="red">Tip：请选择跟师时间和考核年度！</span>
                <br/>
                <div style="height: 10px;"></div>
                <label style="cursor: pointer;margin-left: 66px;">年&#12288;&#12288;&#12288;&#12288;度：</label>
                <input type="text" id="recordYearInput" onclick="WdatePicker({dateFmt:'yyyy'});" onchange="window.document.mainIframe.initTime('recordYearInputHid',this.value);"/>
                <br/>
                <div style="height: 10px;"></div>
                <label style="cursor: pointer;margin-left:66px;">跟师开始时间：</label>
                <input type="text" id="discipleStartDateInput" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" onchange="window.document.mainIframe.initTime('discipleStartDateInputHid',this.value);"/>
                <br/>
                <div style="height: 10px;"></div>
                <label style="cursor: pointer;margin-left: 66px;">跟师结束时间：</label>
                <input type="text" id="discipleEndDateInput" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" onchange="window.document.mainIframe.initTime('discipleEndDateInputHid',this.value);"/>
                <br/>
                <input style="margin-top: 10px;margin-left: 166px;" type="button" class="search"
                       onclick="window.document.mainIframe.saveTimeInfo('${resDoctor.doctorFlow}',this);" value="保&#12288;存"/>
            </div>
        </div>
        <div style="margin-top: 10px;width: 100%;">
            <div style="width: 30%;margin-bottom: 40px; float: left">
                <div class="index_title" style="height: 50px;width: 100%;">
                    <p>
                        <span style="float: left;margin-top: 10px;">跟师学习年度考核情况记录表:</span>
                        <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
                        <input class="btn_blue" style="float: right" type="button" value="添加考核记录表"
                               onclick="selectfollowTime('${resDoctor.doctorFlow}',null)"/>
                        </c:if>
                    </p>
                </div>
                <div id="assessmentList" style="height: 800px;width: 100%;margin-bottom: 20px;border:1px solid #D0E3F2;overflow:auto;">
                    <c:if test="${empty assessmentList}">
                        <p id="" style="margin-top: 30px;color: #BABABA;font-size:18px;">
                            <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
                                &#12288;&#12288;还没有创建任何考核记录表哦
                            </c:if>
                            <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE}">
                                &#12288;&#12288;暂无待审核考核记录
                            </c:if>
                            <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_ADMIN}">
                                &#12288;&#12288;暂无待审核考核记录
                            </c:if>
                        </p>
                    </c:if>
                    <c:forEach items="${assessmentList}" var="assessment">
                        <c:set var="statusColor" value=""/>
                        <c:if test="${(assessment.auditStatusId eq 'Apply' or  'UnQualified' eq assessment.auditStatusId ) or (assessment.auditStatusId eq 'Submit') or (assessment.auditStatusId eq 'DiscipleAudit')}">
                            <c:set var="statusColor" value="#428BFF"/>
                        </c:if>
                        <c:if test="${assessment.auditStatusId eq 'AdminAudit'}">
                            <c:set var="statusColor" value="#00CC33"/>
                        </c:if>
                        <c:if test="${(assessment.auditStatusId eq 'DiscipleBack') or (assessment.auditStatusId eq 'AdminBack')}">
                            <c:set var="statusColor" value="#FF2F5D"/>
                        </c:if>
                        <div class="noteItems" style="width: 100%;padding-bottom: 10px;padding-top: 10px;<c:if
                                test="${param.recordFlow eq assessment.recordFlow}">background: #D0E3F2;</c:if>"
                             onclick="loadAsswssmentDetail('${assessment.doctorFlow}','${assessment.recordFlow}')">
                            <p>考核年度：<span
                                    name="studyStartDate">${assessment.recordYear}</span></p>
                            <p>创建时间：<span name="createTime">${pdfn:transDateTime(assessment.createTime)}</span>&#12288;&#12288;状态：
                                <span title="${assessment.auditStatusName}" style="color: ${statusColor}">${assessment.auditStatusName}</span></p>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div id="asswssmentDetail" style="width: 65%;margin-left: 20px;margin-bottom: 40px; float: left">

            </div>
        </div>
    </div>

</div>
</body>
</html>