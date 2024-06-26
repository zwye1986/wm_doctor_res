<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select2.js'/>?time=2"></script>
    <style type="text/css">
        th{width: 10%}
        textarea{width: 98%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <script type="text/javascript">
        var members=[];
        <c:forEach items="${teachers}" var="s">
        var d = {};
        d.id = "${s.userFlow}";
        d.text = "${s.userName}";
        var attrs=[];
        var attr={};
        attr.name="userCode";
        attr.value="${s.userCode}";
        attrs.push(attr);
        d.attrs = attrs;
        members.push(d);
        </c:forEach>
        function initSelect(datas,nameId,id) {
            var itemSelectFuntion = function () {
                $("#"+id).val(this.id);
            };
            var attrFunction=null;
            $.selectSuggest(nameId, datas, itemSelectFuntion, id, true,attrFunction);
        }
        function save(){
            if(!$("#addForm").validationEngine("validate")){
                return ;
            }
            if($('#lectureStartTime').val()>$('#lectureEndTime').val()){
                jboxTip("开始时间大于结束时间了!")
                return;
            }
            jboxConfirm("确认保存？" , function(){
                var url = "<s:url value='/res/lecture4qingpu/manager/saveLecture'/>";
                var data = $('#addForm').serialize();
                jboxPost(url,data,function(resp){
                    if(resp=='1'){
                        top.jboxTip("操作成功");
                        top.parent.frames['mainIframe'].window.location.reload();
                        top.jboxClose();
                    }else if(resp=='-1'){
                        top.jboxTip("操作失败！当前讲座类型须配置评分表单");
                    }else{
                        top.jboxTip("操作失败");
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
<div class="mainright">
    <div class="content">
        <form id="addForm">
            <input type="hidden" name="lectureFlow" value="${lectureFlow}">
            <input type="hidden" name="lectureCodeUrl" value="${signUrl}">
            <table class="basic" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="padding-right: 0px;text-align: right;"><font color="red">*</font>&#12288;讲座标题：</th>
                    <td>
                        <input class="validate[required] xltext" name="lectureContent" type="text" value="${lectureInfo.lectureContent}"/>
                    </td>
                    <th style="padding-right: 0px;text-align: right;"><font color="red">*</font>&#12288;讲座级别：</th>
                    <td>
                        <select name="lectureLevelId" class="validate[required] xlselect" style="width: 166px">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumLectureLevelList}" var="dict">
                                <option value="${dict.dictId}"<c:if test="${dict.dictId eq lectureInfo.lectureLevelId}">selected</c:if>  >${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;授课老师：</th>
                    <td>

                        <input id="lectureTeacherName" class="validate[required] qtext" name="lectureTeacherName" type="text"style="width: 160px"
                               value="${lectureInfo.lectureTeacherName}" autocomplete="off"/>
                        <input id="lectureTeacherFlow" name="lectureTeacherFlow" class="input" value="${lectureInfo.lectureTeacherFlow}" type="text"
                               hidden style="margin-left: 0px;"/>
                        <script>
                            initSelect(members,"lectureTeacherName","lectureTeacherFlow");
                        </script>
                    </td>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;讲座类型：</th>
                    <td>
                        <select name="lectureTypeId" class="validate[required] xlselect" style="width: 160px">
                            <option value="">请选择</option>
                            <c:forEach items="${qingpuLectureTypeEnumList}" var="dict">
                                <option value="${dict.id}"<c:if test="${dict.id eq lectureInfo.lectureTypeId}">selected</c:if>  >${dict.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;日&#12288;&#12288;期：</th>
                    <td>
                        <input class="validate[required] xltext" name="lectureTrainDate" id="lectureTrainDate" type="text" readonly="readonly" value="${lectureInfo.lectureTrainDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </td>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;时&nbsp;间&nbsp;段&nbsp;：</th>
                    <td>
                        <input class="validate[required] xltext" name="lectureStartTime" id="lectureStartTime" type="text" readonly="readonly" value="${lectureInfo.lectureStartTime}"  onclick="WdatePicker({dateFmt:'HH:mm'})" onchange="checkTime(this)">~
                        <input class="validate[required] xltext" name="lectureEndTime" id="lectureEndTime" type="text" readonly="readonly" value="${lectureInfo.lectureEndTime}"  onclick="WdatePicker({dateFmt:'HH:mm'})" onchange="checkTime(this)">
                    </td>
                </tr>
                <tr>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;地&#12288;&#12288;点：</th>
                    <td style="width: 340px">
                        <input value="${lectureInfo.lectureTrainPlace}" class="validate[required] xltext" name="lectureTrainPlace" style="width: 90%"/>
                    </td>
                    <th style="padding-right: 0px;text-align: right"><font color="red">&nbsp;</font>&#12288;备&#12288;&#12288;注：</th>
                    <td>
                        <input value="${lectureInfo.lectureDesc}" name="lectureDesc" class="xltext" style="width: 90%"/>
                    </td>
                </tr>
                <tr>
                    <th style="padding-right: 0px;text-align: right"><font color="red">*</font>&#12288;评价周期：</th>
                    <td>
                        讲座结束后
                        <input class="validate[required,custom[integer]] xltext" type="text" name="lectureEvaPeriod" style="width: 65px" value="${empty lectureInfo.lectureEvaPeriod?'1':lectureInfo.lectureEvaPeriod}">
                        <select class="validate[required] xlselect" name="lectureUnitId" style="width: 80px">
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
                        <input value="${lectureInfo.limitNum}" name="limitNum" class="validate[custom[integer]] xltext"/><span style="color:red;">&ensp;允许最大报名数</span>
                    </td>
                </tr>
                <tr>
                    <th style="padding-right: 0px;text-align: right">&#12288;讲座内容：</th>
                    <td colspan="3">
                        <textarea name="lectureDescription" style="width:98%;height: 100px;" class="validate[maxSize[500]]">
                        ${lectureInfo.lectureDescription}</textarea>
                    </td>
                </tr>
            </table>
        </form>
        <p style="text-align: center;">
            <input type="button" onclick="save();" class="search" value="保&#12288;存"/>
            <input type="button" onclick="doClose();" class="search" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
