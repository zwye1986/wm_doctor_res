<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker2" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        .grid td{
            text-align: left;
        }
        textarea{width: 98%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect.js'/>"></script>
    <script type="text/javascript">
        var datas =[];//所有的角色
        <c:if test="${!empty applicationScope.sysCfgMap['res_doctor_role_flow']}">
        var arry = {"id":"${applicationScope.sysCfgMap['res_doctor_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_doctor_role_flow']]?sysRoleMap[sysCfgMap['res_doctor_role_flow']].roleName:'培训学员'}"};
        datas.push(arry);
        </c:if>
        <c:if test="${!empty applicationScope.sysCfgMap['res_teacher_role_flow']}">
        var arry = {"id":"${applicationScope.sysCfgMap['res_teacher_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_teacher_role_flow']]?sysRoleMap[sysCfgMap['res_teacher_role_flow']].roleName:'带教老师'}"};
        datas.push(arry);
        </c:if>
        <c:if test="${!empty applicationScope.sysCfgMap['res_head_role_flow']}">
        var arry = {"id":"${applicationScope.sysCfgMap['res_head_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_head_role_flow']]?sysRoleMap[sysCfgMap['res_head_role_flow']].roleName:'科主任'}"};
        datas.push(arry);
        </c:if>
        <c:if test="${!empty applicationScope.sysCfgMap['res_secretary_role_flow']}">
        var arry = {"id":"${applicationScope.sysCfgMap['res_secretary_role_flow'] }","text":"${!empty sysRoleMap[sysCfgMap['res_secretary_role_flow']]?sysRoleMap[sysCfgMap['res_secretary_role_flow']].roleName:'教秘'}"};
        datas.push(arry);
        </c:if>
        $(function(){
            $.itemSelect("infoRoleFlows",datas,null,null,null);
            <c:forEach var="role" items="${roles}">
            $("[name='itemId'][value='${role.lectureRole}']").attr("checked","checked");
            </c:forEach>
            <c:if test="${!empty applicationScope.sysCfgMap['res_doctor_role_flow'] and isNew and empty roles}">
            $("[name='itemId'][value='${applicationScope.sysCfgMap['res_doctor_role_flow'] }']").attr("checked","checked");
            </c:if>
            var result="";
            $("#infoRoleFlows-reqHome").find(".itemCheckbox:checked+font").each(function(){
                var currName = $(this).text();
                if(!result){
                    result+=currName;
                }else{
                    result+=(","+currName);
                }
            });
            $("#infoRoleFlows").val(result);
        });
        function save(){
            if(!$("#addForm").validationEngine("validate")){
                return ;
            }
            if($('#lectureStartTime').val()>$('#lectureEndTime').val()){
                jboxTip("开始时间大于结束时间了!")
                return;
            }
            var infoRoleFlows="";
            $("[name='itemId']:checked").each(function(i){
                if(i!=0)
                    infoRoleFlows+=","+$(this).val();
                else
                    infoRoleFlows=$(this).val();
            });
            if(infoRoleFlows=="")
            {
                jboxTip("请选择发布对象!");
                return;
            }
            jboxConfirm("确认保存？" , function(){
                var url = "<s:url value='/res/manager/saveLecture'/>";
                var data = $('#addForm').serialize();
                jboxPost(url,data,function(resp){
                    if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
                        top.parent.search(1);
                        top.jboxClose();
                    }else{
                        top.jboxTip(resp);
                    }
                },null,false);
            });
        }
        function doClose(){
            jboxClose();
        }

        function checkTime(obj){
            var dates = $(':text',$(obj).closest("td"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
    </script>
</head>
<body>
<div class="div_table">
        <form id="addForm">
            <input type="hidden" name="lectureFlow" value="${lectureFlow}">
            <input type="hidden" name="lectureCodeUrl" value="${signUrl}">
            <input type="hidden" name="lectureOutUrl" value="${signOutUrl}">
            <table class="grid" style="margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="padding-right: 0px;text-align: right;"><font color="red">*</font>&#12288;讲座标题：</th>
                    <td>
                        <input class="validate[required] input" name="lectureContent" type="text" value="${lectureInfo.lectureContent}"/>
                    </td>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;授课角色：</th>
                    <td>
                        <select name="lectureSpeakerRoleId" class="validate[required] select" style="width: 166px;margin-left: 4px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumLectureSpeakerRoleList}" var="dict">
                                <option value="${dict.dictId}"<c:if test="${dict.dictId eq lectureInfo.lectureSpeakerRoleId}">selected</c:if>  >${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;授课老师：</th>
                    <td>
                        <input class="validate[required] input" name="lectureTeacherName" type="text"  value="${lectureInfo.lectureTeacherName}">
                    </td>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;讲座类型：</th>
                    <td>
                        <select name="lectureTypeId" class="validate[required] select" style="width: 166px;margin-left: 4px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumLectureTypeList}" var="dict">
                                <option value="${dict.dictId}"<c:if test="${dict.dictId eq lectureInfo.lectureTypeId}">selected</c:if>  >${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;日&#12288;&#12288;期：</th>
                    <td>
                        <input class="validate[required] input" name="lectureTrainDate" id="lectureTrainDate" type="text" readonly="readonly" value="${lectureInfo.lectureTrainDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </td>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;时&nbsp;间&nbsp;段&nbsp;：</th>
                    <td>
                        <input class="validate[required] input" style="width: 138px;" name="lectureStartTime" id="lectureStartTime" type="text" readonly="readonly" value="${lectureInfo.lectureStartTime}"  onclick="WdatePicker({dateFmt:'HH:mm'})" onchange="checkTime(this)">~
                        <input class="validate[required] input" style="width: 138px;" name="lectureEndTime" id="lectureEndTime" type="text" readonly="readonly" value="${lectureInfo.lectureEndTime}"  onclick="WdatePicker({dateFmt:'HH:mm'})" onchange="checkTime(this)">
                    </td>
                </tr>
                <tr>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;地&#12288;&#12288;点：</th>
                    <td style="width: 340px">
                        <input value="${lectureInfo.lectureTrainPlace}" class="validate[required] input" name="lectureTrainPlace" style="width: 90%"/>
                    </td>
                    <th style="padding-right: 0px;text-align: right"><font color="red">&nbsp;</font>&#12288;备&#12288;&#12288;注：</th>
                    <td>
                        <input value="${lectureInfo.lectureDesc}" name="lectureDesc" class="input" style="width: 90%"/>
                    </td>
                </tr>
                <tr>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;评价周期：</th>
                    <td>
                        讲座结束后
                        <input class="validate[required,custom[integer]] input" type="text" name="lectureEvaPeriod" style="width: 65px" value="${empty lectureInfo.lectureEvaPeriod?'1':lectureInfo.lectureEvaPeriod}">
                        <select class="validate[required] select" name="lectureUnitId" style="width: 80px">
                            <option value="">请选择</option>
                            <c:forEach items="${schUnitEnumList}" var="unit">
                                <c:if test="${unit.id ne 'Year'}">
                                    <option value="${unit.id}"<c:if test="${lectureInfo.lectureUnitId eq unit.id}">selected</c:if>>${unit.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>内可以进行评价
                    </td>
                    <th style="padding-right: 0px;text-align: right">&#12288;人数限制：</th>
                    <td>
                        <input value="${lectureInfo.limitNum}" name="limitNum" class="input validate[custom[integer]] xltext"/><span style="color:red;">&ensp;允许最大报名数</span>
                    </td>
                </tr>
                <tr>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;通知对象：</td>
                    <td <%--colspan="3"--%>>
                        <div style="min-width:98%;width:98%;border:1px;">
                            <input id="infoRoleFlows" name="infoRoleFlows" class="input" placeholder="" style="width: 93%;" type="text" readonly="readonly" />
                        </div>
                    </td>
                    <th style="text-align: right;padding-right: 0px;">是否进行随机签到：</th>
                    <td style="padding-left: 10px;">
                        <input type="radio" name="randomSignIn"  value="${GlobalConstant.FLAG_Y}" id="isRandom_Y"
                               <c:if test="${lectureInfo.randomSignIn eq 'Y'}">checked</c:if>><label for="isRandom_Y">是&#12288;</label>
                        <input type="radio" name="randomSignIn"  value="${GlobalConstant.FLAG_N}" id="isRandom_N"
                               <c:if test="${lectureInfo.randomSignIn ne 'Y'}">checked</c:if>><label for="isRandom_N">否&#12288;</label>
                    </td>
                </tr>
            </table>
        </form>
        <div class="button">
            <input type="button" onclick="save();" class="btn_green" value="保&#12288;存"/>&#12288;
            <input type="button" onclick="doClose();" class="btn_green" value="关&#12288;闭"/>
        </div>
    </div>
</body>
</html>
