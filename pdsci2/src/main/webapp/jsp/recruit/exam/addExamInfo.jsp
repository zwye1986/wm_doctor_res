<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
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



        function setExamNum(roomName,examNum) {

        }

        function save(){
            var form = $("#addExamInfoForm");
            if(false==form.validationEngine("validate")){
                return ;
            }
            var examStartTime=$("#examStartTime").val();
            var examEndTime=$("#examEndTime").val();
            var interviewStartTime=$("#interviewStartTime").val();
            var interviewEndTime=$("#interviewEndTime").val();
            var interviewAddress=$("#interviewAddress").val();
            var interviewDemo=$("#interviewDemo").val();
            if(examEndTime<=examStartTime)
            {
                jboxTip("笔试结束时间必须大于笔试开始时间！");
                return false;
            }
            if(interviewStartTime<=examEndTime)
            {
                jboxTip("面试开始时间必须大于笔试结束时间！");
                return false;
            }
            if(interviewEndTime<=interviewStartTime)
            {
                jboxTip("面试结束时间必须大于面试开始时间！");
                return false;
            }
            var bean={};
            bean.examStartTime=examStartTime;
            bean.examEndTime=examEndTime;
            bean.interviewStartTime=interviewStartTime;
            bean.interviewEndTime=interviewEndTime;
            bean.interviewAddress=interviewAddress;
            bean.interviewDemo=interviewDemo;
            bean.mainFlow="${main.mainFlow}";
            bean.orgFlow="${main.orgFlow}";
            bean.examFlow="${examInfo.examFlow}";
            bean.examYear="${main.recruitYear}";
            var roomInfos=[];
            $("[name='examRoom']:checked").each(function () {
                var roomInfo={};
                roomInfo.roomFlow=$(this).val();
                roomInfo.examFlow="${examInfo.examFlow}";
                roomInfo.mainFlow="${main.mainFlow}";
                roomInfo.orgFlow="${main.orgFlow}";
                roomInfo.examNum=$(this).parent().parent().find("[name='examRoomDiv']").find("[name='examNum']").val();
                roomInfo.examAddress=$(this).parent().find("[name='roomAddress']").val();

                roomInfos.push(roomInfo);
            });
            bean.roomInfos=roomInfos;
            console.log(bean);
            var url = "<s:url value='/recruit/examInfo/saveExamInfo'/>";
            jboxPostJson(url,JSON.stringify(bean),function(resp){
                if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
                    window.parent.frames['mainIframe'].window.location.reload(true);
                    jboxClose();
                }
            },null,true);
        }
        function examRoom2(obj) {
            if($(obj).attr("checked")=="checked")
            {
                $(obj).parent().parent().find("[name='examRoomDiv']").show();
            }else{
                $(obj).parent().parent().find("[name='examRoomDiv']").hide();
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix" style="overflow: auto;min-height:300px">
            <form id="addExamInfoForm"name="addExamInfoForm" action="<s:url value='/recruit/examInfo/addExamInfo'/>" method="post" style="position: relative;">
                <input type="hidden" id="mainFlow" name="mainFlow" value="${main.mainFlow}"/>
                <input type="hidden" id="examFlow" name="examFlow" value="${examInfo.examFlow}"/>
                <table width="100%" class="basic" >
                    <tr>
                        <th style="width: 160px"> 考试年份：</th>
                        <td  colspan="3">
                            <input type="text" class="validate[required] xltext" id="examYear" name="examYear"
                                   value="${main.recruitYear}" style="margin-right: 0px"   readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>笔试时间：</th>
                        <td>
                            <input type="text"  class="validate[required] xltext" id="examStartTime" name="examStartTime" value="${examInfo.examStartTime}"
                                    style="margin-right: 0px"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'${main.startTime} 00:00',maxDate: '${main.endTime} 23:59'})" readonly="readonly"/>
                            ~
                            <input type="text"class="validate[required] xltext" id="examEndTime" name="examEndTime" value="${examInfo.examEndTime}"
                                    style="margin-right: 0px"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '#F{$dp.$D(\'examStartTime\')}',maxDate:'${main.endTime} 23:59'})" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>设置考场：</th>
                        <td  colspan="3">
                            <div style="max-height: 200px;overflow: auto;width:100%;padding-top:5px;">
                                <table class="basic" width="100%">
                                    <c:forEach items="${recruitExamRooms}" var="recruitExamRoom"  >
                                        <c:if test="${recruitExamRoom.recordStatus eq'Y'}">
                                            <c:set var="roomInfo" value="${roomInfoMap[recruitExamRoom.roomFlow]}"></c:set>
                                            <tr>
                                                <td width="60%">
                                                    <input name="examRoom" class="validate[required]" type="checkbox" ${not empty roomInfo ?'checked=\"checked\"':''} id="${recruitExamRoom.roomFlow}" value="${recruitExamRoom.roomFlow}" onchange="examRoom2(this)">
                                                    <input name="roomAddress"  type="hidden" id="${recruitExamRoom.roomAddress}" value="${recruitExamRoom.roomAddress}"  >
                                                    <label for="${recruitExamRoom.roomFlow}" style="cursor:pointer;">${recruitExamRoom.roomName}</label>
                                                </td>
                                                <td>
                                                    <div name="examRoomDiv" style="display: ${empty roomInfo ? 'none':''};">
                                                        <label >考场人数：</label>
                                                        <input  class="xltext validate[required,custom[number,min[0],max[${recruitExamRoom.examNum}]]]" name="examNum" value="${roomInfo.examNum}">
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>面试时间：</th>
                        <td>
                            <input type="text"class="validate[required] xltext" id="interviewStartTime" name="interviewStartTime" value="${examInfo.interviewStartTime}"
                                    style="margin-right: 0px"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '#F{$dp.$D(\'examEndTime\')}',maxDate: '#F{$dp.$D(\'interviewEndTime\')}'})" readonly="readonly"/>
                       ~
                            <input type="text"class="validate[required] xltext" id="interviewEndTime" name="interviewEndTime" value="${examInfo.interviewEndTime}"
                                    style="margin-right: 0px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '#F{$dp.$D(\'interviewStartTime\')}'})" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>

                        <th><font color="red" >*</font>面试地点：</th>
                        <td>
                            <input type="text" class="validate[required] xltext" id="interviewAddress" name="interviewAddress" value="${examInfo.interviewAddress}" style="width: 90%;"/>
                        </td>
                    </tr>
                    <tr>
                        <th>面试备注：</th>
                        <td  colspan="3">
                            <input type="text" class="xltext" id="interviewDemo" name="interviewDemo" value="${examInfo.interviewDemo}" style="width: 90%;"/>
                        </td>
                    </tr>
                </table>
                <div style="text-align: center;margin-top: 15px;">
                    <input type="button" onclick="save()" class="search" value="保&#12288;存"/>
                    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>