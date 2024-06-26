<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
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
                return;
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
                var url = "<s:url value='/jsres/lecture/saveLecture'/>";
                var data = $('#addForm').serialize();
                top.jboxPost(url,data,function(resp){
                    if(resp=='1'){
                        top.jboxTip("操作成功");
                        top.lectures(null);
                        top.jboxClose();
                    }else{
                        top.jboxTip("操作失败");
                    }
                },null,false);
            });
        }
        $(document).ready(function () {
            $("#code").empty();
            var url = "${signUrl}";
            $("#qrcode").qrcode({
                render: "canvas",
                width: 300,
                height:300,
                correctLevel:0,//纠错等级
                text: url
            });
        });

    </script>
</head>
<body>
<div class="mainright">
    <div class="basic">
        <form id="addForm">
            <input type="hidden" name="lectureFlow" value="${lectureFlow}">
            <input type="hidden" name="lectureCodeUrl" value="${signUrl}">
            <input type="hidden" name="lectureOutUrl" value="${signOutUrl}">
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="text-align: right;"><font color="red">*</font>讲座标题：</th>
                    <td colspan="3">
                        <input class="validate[required] input" name="lectureContent" type="text" value="${lectureInfo.lectureContent}" style="width:588px"></input>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red">*</font>授课老师：</th>
                    <td>
                        <input class="validate[required] input" name="lectureTeacherName" type="text"  value="${lectureInfo.lectureTeacherName}">
                    </td>
                    <th style="text-align: right;"><font color="red">*</font>讲座类型：</th>
                    <td style="padding-left: 10px">
                        <select name="lectureTypeId" class="validate[required] select" style=" width: 160px">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumLectureTypeList}" var="dict">
                            <option value="${dict.dictId}"<c:if test="${dict.dictId eq lectureInfo.lectureTypeId}">selected</c:if>  >${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red">*</font>日期：</th>
                    <td>
                        <input class="validate[required] input" name="lectureTrainDate" id="lectureTrainDate" type="text" readonly="readonly" value="${lectureInfo.lectureTrainDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </td>
                    <th style="text-align: right;"><font color="red">*</font>时间段：</th>
                    <td>
                        <input class="validate[required] input" name="lectureStartTime" id="lectureStartTime" type="text" readonly="readonly" value="${lectureInfo.lectureStartTime}"  onclick="WdatePicker({dateFmt:'HH:mm'})">~
                        <input class="validate[required] input" name="lectureEndTime" id="lectureEndTime" type="text" readonly="readonly" value="${lectureInfo.lectureEndTime}"  onclick="WdatePicker({dateFmt:'HH:mm'})">
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red">*</font>地点：</th>
                    <td style="width: 340px">
                       <input value="${lectureInfo.lectureTrainPlace}" class="validate[required] input" name="lectureTrainPlace" style="width: 90%"/>
                    </td>
                    <th style="text-align: right;">备注：</th>
                    <td>
                        <input value="${lectureInfo.lectureDesc}" name="lectureDesc" class="input" style="width: 90%"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red">*</font>评价周期：</th>
                    <td>
                        讲座结束后
                        <input class="validate[required,custom[integer]] input" type="text" name="lectureEvaPeriod" style="width: 65px" value="${lectureInfo.lectureEvaPeriod}">
                        <select class="validate[required] select" name="lectureUnitId">
                            <option value="">请选择</option>
                            <c:forEach items="${schUnitEnumList}" var="unit">
                                <c:if test="${unit.id ne schUnitEnumYear.id }">
                                    <option value="${unit.id}"<c:if test="${lectureInfo.lectureUnitId eq unit.id}">selected</c:if>>${unit.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        内可以进行评价
                    </td>
                    <th style="text-align: right;">报名人数限制：</th>
                    <td>
                        <input class="validate[custom[integer]] input" name="limitNum" id="limitNum" type="text" value="${lectureInfo.limitNum}"><font style="color: grey;">不设置表示不限制参加人数</font>
                    </td>
                </tr>
                <tr  >
                    <th style="text-align: right;"><span style="color: red;">*</span>通知对象：</td>
                    <td>
                        <div style="min-width:98%;width:98%;border:1px;">
                            <input id="infoRoleFlows" name="infoRoleFlows" class="input" placeholder="" style="width: 100%;" type="text" readonly="readonly" />
                        </div>
                    </td>
                    <th style="text-align: right;">是否进行随机签到：</th>
                    <td>
                        <input type="radio" name="randomSignIn"  value="${GlobalConstant.FLAG_Y}" id="isRandom_Y"
                               <c:if test="${lectureInfo.randomSignIn eq 'Y'}">checked</c:if>><label for="isRandom_Y">是&#12288;</label>
                        <input type="radio" name="randomSignIn"  value="${GlobalConstant.FLAG_N}" id="isRandom_N"
                               <c:if test="${lectureInfo.randomSignIn ne 'Y'}">checked</c:if>><label for="isRandom_N">否&#12288;</label>
                    </td>
                </tr>
            </table>
        </form>
        <p style="text-align: center;">
            <input type="button" onclick="save();" class="btn_green" value="保&#12288;存"/>&#12288;
            <input type="button" onclick="top.jboxClose();" class="btn_green" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
