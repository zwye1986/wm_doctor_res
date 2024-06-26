<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script type="text/javascript"
            src="<s:url value='/jsp/srm/proj/form/apply/jszyyj.zyykjxm/IDcardCheck.js'/>"></script>
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            if($('.memberList tr').length < 3){
                jboxTip("课题组主要成员至少三人");
                return false;
            }
            var form = $('#projForm');
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }
        function checkBDDate(dt) {
            var dates = $(':text', $(dt).closest("td"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                dt.value = "";
            }
        }

        function add(templateId) {
            if (templateId) {
                $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                reSeq(templateId);
                // projCount(templateId);

            }
        }

        function del(templateId) {
            if (templateId) {
                if (!$('.' + templateId + ' .toDel:checked').length) {
                    return jboxTip('请选择需要删除的项目！');
                }
                jboxConfirm('确认删除？', function () {
                    $('.' + templateId + ' .toDel:checked').closest('tr').remove();
                    reSeq(templateId);
                    IDcardInforStatistics();
                    //projCount(templateId);
                    //jboxClose();
                }, null);
            }
        }

        function reSeq(templateId) {
            $('.' + templateId + ' .seq').each(function (i, n) {
                $(n).text(i + 1);
            });
        }
        $(function () {
            $('input[name="projFruit10"]').change(function () {
                if ($("#projFruit10").attr("checked")) {
                    $("#otherProjFruit").removeAttr("readonly");
                } else {
                    $("#otherProjFruit").val("");
                    $("#otherProjFruit").attr("readonly", "readonly");
                }
            });
        });


        function IDcardInforStatistics() {
            var manNum = 0;//性别 男 人数
            var womanNum = 0;//性别 女 人数
            var time = new Date();
            var nowYear = time.getFullYear();
            var amountMember = 0;//总人数
            var amountAge = 0;//总年龄
            var avgAge;//平均年龄
            var title_senior = 0;//高级人数
            var title_middle = 0;//中级人数
            var title_junior = 0;//初级人数
            var title_other = 0; //其它人数

            var degree_academician = 0;//院士人数

            var degree_postdoctor = 0;//博士后人数
            var degree_doctor = 0;//博士人数
            var degree_bachelor = 0;//学士人数
            var degree_other = 0;//其它人数
            $("#memberStatistics input").each(function () {
                $(this).val("0");
            });
            $(".memberList tr").each(function (index, element) {
                var IDcard = $(element).find("input[name=memberList_card]").val();//获取身份证
                var education = $(element).find("select[name=memberList_education]").val();//获取学历
                var titleLevel = $(element).find(".memberTitleLevel").val();//获取职称级别
                var title = $(element).find(".memberTitleLevel option:selected").text();//获取职称
                var post = $(element).find("select[name=memberList_post]").val();//获取职务类别
                $(element).find("input[name=memberList_title]").val(title);
                if (titleLevel == '高级') {
                    title_senior++;
                } else if (titleLevel == '中级') {
                    title_middle++;
                } else if (titleLevel == '初级') {
                    title_junior++;
                } else {
                    title_other++;
                }
                //   alert(titleLevel+".............."+title);

                if (education == '博士后') {
                    degree_postdoctor++;
                } else if (education == '博士') {
                    degree_doctor++;
                } else if (education == '学士') {
                    degree_bachelor++;
                } else {
                    degree_other++;
                }
                if (post == '院士') {
                    degree_academician++;
                }

                if (IDcard && (IDcard.length == 15 || IDcard.length == 18)) {
                    //if (IdCardValidate(IDcard)) {//判断性别
                        var sexChecked = maleOrFemalByIdCard(IDcard);
                        if (sexChecked == 'male') {
                            manNum++;
                        } else if (sexChecked == 'female') {
                            womanNum++
                        }

                        var year;
                        if (IDcard.length == 15) {
                            if (isValidityBrithBy15IdCard(IDcard)) {//生日是否有效
                                year = parseInt("19" + "" + IDcard.substring(6, 8));
                            }
                        } else if (IDcard.length == 18) {
                            if (isValidityBrithBy18IdCard(IDcard)) {//生日是否有效
                                year = IDcard.substring(6, 10);
                            }
                        }
                        amountAge = amountAge + (parseInt(nowYear) - parseInt(year));
                  //  }
                }
                amountMember++;
            });

            avgAge = amountAge / amountMember;
            if(!avgAge){
                avgAge = 0;
            }
            $("input[name=total_number]").val(amountMember);
            $("input[name=average_age]").val(parseFloat(avgAge.toFixed(1)));
            $("input[name=member_man]").val(manNum);
            $("input[name=member_woman]").val(womanNum);
            $("input[name=degree_academician]").val(degree_academician);
            $("input[name=degree_postdoctor]").val(degree_postdoctor);
            $("input[name=degree_doctor]").val(degree_doctor);
            $("input[name=degree_bachelor]").val(degree_bachelor);
            $("input[name=degree_other]").val(degree_other);
            $("input[name=title_senior]").val(title_senior);
            $("input[name=title_middle]").val(title_middle);
            $("input[name=title_junior]").val(title_junior);
            $("input[name=title_other]").val(title_other);

        }


        var setting = {
            view: {
                dblClickExpand: false,
                showIcon: false,
                showTitle: false,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: onClick
            }
        };

        function beforeClick(treeId, treeNode) {
            var check = (treeNode.id != 0);
            if (!check) jboxTip('不能选择根节点');
            return check;
        }

        function onClick(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = zTree.getSelectedNodes(),
                    v = "";
            id = "";
            nodes.sort(function compare(a, b) {
                return a.id - b.id;
            });
            for (var i = 0, l = nodes.length; i < l; i++) {
                v += nodes[i].name + ",";
                id += nodes[i].id + ",";
            }
            if (v.length > 0) v = v.substring(0, v.length - 1);
            if (id.length > 0) id = id.substring(0, id.length - 1);
            var cityObj = $("#proSelect");
            $("#selectProjId").val(id);
            cityObj.attr("value", v);
        }
        function showMenu() {
            var cityObj = $("#proSelect");
            var cityOffset = $("#proSelect").offset();
            $("#menuContent").css({
                left: cityOffset.left + "px",
                top: cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");

            $("body").bind("mousedown", onBodyDown);
        }
        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
                hideMenu();
            }
        }
        $(document).ready(function () {
            var url = "<s:url value='/sys/subject/getAllDataJson'/>";
            jboxPostJson(url, null, function (data) {
                if (data) {
                    zNodes = $.parseJSON(data);
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                    $.fn.zTree.init($("#treeDemo2"), setting2, zNodes);//相关学科
                }
            }, null, false);
            $(".lable").click(function(){
                var radioId= $(this).attr("radioId");
                if($("#"+radioId).is(":checked")){
                    $("#"+radioId).removeAttr("checked");
                }else{
                    $("#"+radioId).attr("checked","checked");
                }
            });
            var projAnimal = $("input[name='projAnimal']");
            var removeChecked = false;
            $(projAnimal).mousedown(function(){
                if($(this).is(":checked")){
                    removeChecked = true;
                }else{
                    removeChecked = false;
                }
            });

            $(projAnimal).click(function(){
                if(removeChecked){
                    $(this).removeAttr("checked");
                }
            });
        });

        var setting2 = {
            view: {
                dblClickExpand: false,
                showIcon: false,
                showTitle: false,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick2,
                onClick: onClick2
            }
        };

        function beforeClick2(treeId, treeNode) {
            var check = (treeNode.id != 0);
            if (!check) jboxTip('不能选择根节点');
            return check;
        }

        function onClick2(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo2"),
                    nodes = zTree.getSelectedNodes(),
                    v = "";
            id = "";
            nodes.sort(function compare(a, b) {
                return a.id - b.id;
            });
            for (var i = 0, l = nodes.length; i < l; i++) {
                v += nodes[i].name + ",";
                id += nodes[i].id + ",";
            }
            if (v.length > 0) v = v.substring(0, v.length - 1);
            if (id.length > 0) id = id.substring(0, id.length - 1);
            var cityObj = $("#projTwoSubject");
            $("#selectProjId2").val(id);
            cityObj.attr("value", v);
        }
        function showMenu2() {
            var cityObj = $("#projTwoSubject");
            var cityOffset = $("#projTwoSubject").offset();
            $("#menuContent2").css({
                left: cityOffset.left + "px",
                top: cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");

            $("body").bind("mousedown", onBodyDown2);
        }
        function hideMenu2() {
            $("#menuContent2").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown2);
        }
        function onBodyDown2(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent2" || $(event.target).parents("#menuContent2").length > 0)) {
                hideMenu2();
            }
        }

    </script>
    <style type="text/css">
        .basic .inputText {
             text-align: left;
         }
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step2"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333;">一、基本情况</font>
                    <table class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th colspan="7" style="text-align: left;padding-left: 20px;">研究课题</th>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>名称：</th>
                            <td colspan="6">
                                <input type="text" class="validate[required] inputText" name="projName"
                                       value="<c:if test='${empty resultMap.projName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${!empty resultMap.projName}'>${resultMap.projName}</c:if>"
                                       style="width: 60%"/>
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>课题总经费：</th>
                            <td><input type="text" name="projAmountFund" value="${resultMap.projAmountFund}"
                                       class="inputText validate[required,custom[number]]" style="width: 75%"/>万元
                            </td>
                            <th><font color="red">*</font>申请经费：</th>
                            <td colspan="2">
                                <input type="text" name="projApplyFund" value="${resultMap.projApplyFund}"
                                       class="inputText validate[required,custom[number]]" style="width: 75%"/>万元
                            </td>
                            <th><font color="red">*</font>匹配经费：</th>
                            <td><input type="text" name="projMatchFund" value="${resultMap.projMatchFund}"
                                       class="inputText validate[required,custom[number]]" style="width: 75%"/>万元
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>主题词：</th>
                            <td colspan="2"><input type="text" name="projKeyWord" value="${resultMap.projKeyWord}"
                                                   class="inputText validate[required]" style="width: 90%"/></td>
                            <th rowspan="2">申报学科：</th>
                            <th>名称1：</th>
                            <td colspan="2"><input id="proSelect" name="projOneSubject" class="inputText"
                                                   value="${resultMap.projOneSubject}"
                                                   readonly="readonly" style="width: 180px;text-align: left"
                                                   onclick="showMenu(); return false;"/></td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>申报部门：</th>
                            <td colspan="2"><input type="text" name="projApplyDept" value="${resultMap.projApplyDept}"
                                                   class="inputText validate[required]" style="width: 90%"/></td>
                            <th>名称2：</th>
                            <td colspan="2"><input id="projTwoSubject" name="projTwoSubject" class="inputText"
                                                   value="${resultMap.projTwoSubject}"
                                                   readonly="readonly" style="width: 180px;text-align: left"
                                                   onclick="showMenu2(); return false;"/></td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>研究工作起止年月：</th>
                            <td colspan="6">
                                <input type="text" class="inputText ctime validate[required]" name="projStartDate"
                                       onchange="checkBDDate(this)" value="${resultMap.projStartDate}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                                       style="width: 110px;"/>
                                至
                                <input type="text" class="inputText ctime validate[required]" name="projEndDate"
                                       onchange="checkBDDate(this)" value="${resultMap.projEndDate}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                                       style="width: 110px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th>实验动物设施：</th>
                            <td colspan="2">
                                <input type="radio" value="普通级"
                                       <c:if test="${resultMap.projAnimal eq '普通级'}">checked="checked"</c:if>
                                       name="projAnimal" id="projAnimal1"/><label class="lable" radioId="projAnimal1">普通级</label>&#12288;
                                <input type="radio" value="清洁级"
                                       <c:if test="${resultMap.projAnimal eq '清洁级'}">checked="checked"</c:if>
                                       name="projAnimal" id="projAnimal2"/><label class="lable" radioId="projAnimal2">清洁级</label>&#12288;
                                <input type="radio" value="SPF级"
                                       <c:if test="${resultMap.projAnimal eq 'SPF级'}">checked="checked"</c:if>
                                       name="projAnimal" id="projAnimal3"/><label class="lable" radioId="projAnimal3">SPF级</label>
                            </td>
                            <th colspan="2">所用实验室：</th>
                            <td colspan="2">
                                <select name="projLab" class="inputText" style="width: 90%;">
                                    <option value="">请 选 择</option>
                                    <option value="一级"
                                            <c:if test="${resultMap.projLab eq '一级'}">selected="selected"</c:if>>
                                        &#12288;一级
                                    </option>
                                    <option value="二级"
                                            <c:if test="${resultMap.projLab eq '二级'}">selected="selected"</c:if>>
                                        &#12288;二级
                                    </option>
                                    <option value="三级"
                                            <c:if test="${resultMap.projLab eq '三级'}">selected="selected"</c:if>>
                                        &#12288;三级
                                    </option>
                                    <option value="省部重点"
                                            <c:if test="${resultMap.projLab eq '省部重点'}">selected="selected"</c:if>>省部重点
                                    </option>
                                    <option value="国家重点"
                                            <c:if test="${resultMap.projLab eq '国家重点'}">selected="selected"</c:if>>国家重点
                                    </option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>预期研究结果：</th>
                            <td colspan="6">
                                <input type="checkbox" value="论文"
                                       <c:if test="${resultMap.projFruit1 eq '论文'}">checked="checked"</c:if>
                                       name="projFruit1" id="projFruit1"/><label for="projFruit1">论文</label>&#12288;
                                <input type="checkbox" value="著作"
                                       <c:if test="${resultMap.projFruit2 eq '著作'}">checked="checked"</c:if>
                                       name="projFruit2" id="projFruit2"/><label for="projFruit2">著作</label>&#12288;
                                <input type="checkbox" value="新观点"
                                       <c:if test="${resultMap.projFruit3 eq '新观点'}">checked="checked"</c:if>
                                       name="projFruit3" id="projFruit3"/><label for="projFruit3">新观点</label>
                                <input type="checkbox" value="新学说"
                                       <c:if test="${resultMap.projFruit4 eq '新学说'}">checked="checked"</c:if>
                                       name="projFruit4" id="projFruit4"/><label for="projFruit4">新学说</label>&#12288;
                                <input type="checkbox" value="新理论"
                                       <c:if test="${resultMap.projFruit5 eq '新理论'}">checked="checked"</c:if>
                                       name="projFruit5" id="projFruit5"/><label for="projFruit5">新理论</label>&#12288;
                                <input type="checkbox" value="新方法"
                                       <c:if test="${resultMap.projFruit6 eq '新方法'}">checked="checked"</c:if>
                                       name="projFruit6" id="projFruit6"/><label for="projFruit6">新方法</label>
                                <input type="checkbox" value="新方案"
                                       <c:if test="${resultMap.projFruit7 eq '新方案'}">checked="checked"</c:if>
                                       name="projFruit7" id="projFruit7"/><label for="projFruit7">新方案</label>&#12288;
                                <input type="checkbox" value="新药前期研究"
                                       <c:if test="${resultMap.projFruit8 eq '新药前期研究'}">checked="checked"</c:if>
                                       name="projFruit8" id="projFruit8"/><label for="projFruit8">新药前期研究</label>&#12288;
                                <input type="checkbox" value="新诊疗设备"
                                       <c:if test="${resultMap.projFruit9 eq '新诊疗设备'}">checked="checked"</c:if>
                                       name="projFruit9" id="projFruit9"/><label for="projFruit9">新诊疗设备</label>
                                <input type="checkbox" value="其他"
                                       <c:if test="${resultMap.projFruit10 eq '其他'}">checked="checked"</c:if>
                                       name="projFruit10" id="projFruit10"/><label for="projFruit10">其他</label>
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                                    <input type="text" class="inputText" name="otherProjFruit" id="otherProjFruit"
                                           value="${resultMap.otherProjFruit}" readonly="readonly">
                                </c:if>
                                <c:if test="${param.view eq GlobalConstant.FLAG_Y }">
                                    <span class="inputText">${resultMap.otherProjFruit}</span>
                                </c:if>
                            </td>
                        </tr>
                    </table>

                    <table class="basic" style="width: 100%">
                        <tr>
                            <th style="text-align: left;padding-left: 15px;" colspan="10">
                                课题组主要成员
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('memberList');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('memberList');"/>
						</span>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                                <th style="text-align: left" width="4%">&nbsp;选择</th>
                            </c:if>
                            <th style="text-align: left"  width="4%">&nbsp;序号</th>
                            <th style="text-align: left" width="9%">&nbsp;<font color="red">*</font>姓名</th>
                            <th style="text-align: left" width="14%">&nbsp;<font color="red">*</font>身份证号码</th>
                            <th style="text-align: left" width="9%">&nbsp;<font color="red">*</font>学位</th>
                            <th style="text-align: left" width="9%">&nbsp;职称</th>
                            <th style="text-align: left" width="14%">&nbsp;<font color="red">*</font>所在单位</th>
                            <th style="text-align: left" width="9%">&nbsp;<font color="red">*</font>职务类别</th>
                            <th style="text-align: left" width="18%">&nbsp;<font color="red">*</font>课题中的分工</th>
                            <th style="text-align: left" width="10%">&nbsp;<font color="red">*</font>本项目投入时间</th>
                            <%--<td style="text-align: center;" width="7%">签名</td>--%>
                        </tr>
                        <tbody class="memberList">
                        <c:forEach var="memberList" items="${resultMap.memberList}" varStatus="memberListStatus">
                            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                                <tr>
                                    <td><input type="checkbox" class="toDel"></td>

                                    <td class="seq">${memberListStatus.count}</td>
                                    <td>
                                        <input type="text" class="inputText validate[required]" name="memberList_name"
                                               value="${memberList.objMap.memberList_name}" style="width: 90%"/>
                                    </td>
                                    <td>
                                        <input type="text" class="inputText validate[required,custom[chinaId]]"
                                               name="memberList_card"
                                               value="${memberList.objMap.memberList_card}" style="width: 90%"
                                               onchange="IDcardInforStatistics();"/>
                                    </td>
                                    <td>
                                        <select name="memberList_education" class="inputText validate[required]"
                                                style="width: 90%;" onchange="IDcardInforStatistics();">
                                            <option value="">请选择</option>
                                            <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                                <option value="${dict.dictName }"
                                                        <c:if test="${memberList.objMap.memberList_education eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                                            </c:forEach>
                                        </select>

                                    </td>
                                    <td>
                                        <select class="memberTitleLevel inputText" onchange="IDcardInforStatistics();"
                                                style="width: 90%;">
                                            <option value="">请选择</option>
                                            <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                                <option value="${dict.dictDesc }"
                                                        <c:if test="${memberList.objMap.memberList_title eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" name="memberList_title"
                                               value="${memberList.objMap.memberList_title}"/>
                                    </td>
                                    <span style="display: none"></span>
                                    <td>
                                        <input type="text" class="inputText validate[required]" name="memberList_dept"
                                               value="${memberList.objMap.memberList_dept}" style="width: 90%"/>
                                    </td>
                                    <td>
                                        <select name="memberList_post" class="inputText validate[required]"
                                                onchange="IDcardInforStatistics();"
                                                style="width: 90%;">
                                            <option value="">请选择</option>
                                            <c:forEach var="dict" items="${dictTypeEnumPostTypeList}">
                                                <option value="${dict.dictName }"
                                                        <c:if test="${memberList.objMap.memberList_post eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="text" class="inputText"
                                               name="memberList_bearResponsibility"
                                               value="${memberList.objMap.memberList_bearResponsibility}"
                                               style="width: 90%"/>
                                    </td>
                                    <td>
                                        <input type="text" class="inputText" name="memberList_time"
                                               value="${memberList.objMap.memberList_time}" style="width: 90%"/>
                                    </td>
                                        <%--<td>--%>
                                        <%--<input type="text" class="inputText" name="memberList_sign"--%>
                                        <%--value="${memberList.objMap.memberList_sign}" style="width: 90%"/>--%>
                                        <%--</td>--%>
                                </tr>
                            </c:if>
                            <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
                                <tr>
                                    <td class="seq">${memberListStatus.count}</td>
                                    <td>
                                            ${memberList.objMap.memberList_name}
                                    </td>
                                    <td>
                                            ${memberList.objMap.memberList_card}
                                    </td>
                                    <td>
                                            ${memberList.objMap.memberList_education}
                                    </td>
                                    <td>
                                            ${memberList.objMap.memberList_title}
                                    </td>
                                    <td>
                                            ${memberList.objMap.memberList_dept}
                                    </td>
                                    <td>
                                            ${memberList.objMap.memberList_post}
                                    </td>
                                    <td>
                                            ${memberList.objMap.memberList_bearResponsibility}
                                    </td>
                                    <td>
                                            ${memberList.objMap.memberList_time}
                                    </td>
                                        <%--<td>--%>
                                        <%--<input type="text" class="inputText" name="memberList_sign"--%>
                                        <%--value="${memberList.objMap.memberList_sign}" style="width: 90%"/>--%>
                                        <%--</td>--%>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                    <table class="basic" id="memberStatistics" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th style="text-align: left;border-right: double">&nbsp;总人数</th>
                            <th style="text-align: left">&nbsp;平均年龄</th>
                            <th style="text-align: left">&nbsp;男</th>
                            <th style="text-align: left;border-right: double">&nbsp;女</th>
                            <th style="text-align: left">&nbsp;高级</th>
                            <th style="text-align: left">&nbsp;中级</th>
                            <th style="text-align: left">&nbsp;初级</th>
                            <th style="text-align: left;border-right: double">&nbsp;其他</th>
                            <th style="text-align: left;border-right: double">&nbsp;院士</th>
                            <th style="text-align: left">&nbsp;博士后</th>
                            <th style="text-align: left">&nbsp;博士</th>
                            <th style="text-align: left">&nbsp;学士</th>
                            <th style="text-align: left">&nbsp;其他</th>
                        </tr>
                        <tr>
                            <td style="border-right: double">
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="total_number"
                                       value="${resultMap.total_number}">
                            </td>
                            <td>
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="average_age"
                                       value="${resultMap.average_age}">
                            </td>
                            <td>
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="member_man"
                                       value="${resultMap.member_man}">
                            </td>
                            <td style="border-right: double">
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="member_woman"
                                       value="${resultMap.member_woman}">
                            </td>
                            <td>
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="title_senior"
                                       value="${resultMap.title_senior}">
                            </td>
                            <td>
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="title_middle"
                                       value="${resultMap.title_middle}">
                            </td>
                            <td>
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="title_junior"
                                       value="${resultMap.title_junior}">
                            </td>
                            <td style="border-right: double">
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="title_other"
                                       value="${resultMap.title_other}">
                            </td>
                            <td style="border-right: double">
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="degree_academician"
                                       value="${resultMap.degree_academician}">
                            </td>
                            <td>
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="degree_postdoctor"
                                       value="${resultMap.degree_postdoctor}">
                            </td>
                            <td>
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="degree_doctor"
                                       value="${resultMap.degree_doctor}">
                            </td>
                            <td>
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="degree_bachelor"
                                       value="${resultMap.degree_bachelor}">
                            </td>
                            <td>
                                <input type="text" style="width: 90%" readonly="readonly" title="自动统计，不可修改"
                                       class="inputText validate[required,custom[number]]" name="degree_other"
                                       value="${resultMap.degree_other}">
                            </td>
                        </tr>
                    </table>
                    <table class="basic" style="width: 100%;margin-top: 10px;">
                        <tr>
                            <th style="text-align: left;padding-left: 15px;" colspan="7">承担单位
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                    <span style="float: right;padding-right: 10px">
                    <img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                         style="cursor: pointer;" onclick="add('employer')"/>&#12288;
                    <img title="删除" style="cursor: pointer;"
                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                         onclick="del('employer');"/></span>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <th style="text-align: left" width="50px"></th>
                            <th style="text-align: left" width="50px">&nbsp;序号</th>
                            <th style="text-align: left">&nbsp;<font color="red">*</font>单位名称</th>
                            <th style="text-align: left">&nbsp;<font color="red">*</font>通讯地址及邮政编码</th>
                            <th style="text-align: left">&nbsp;<font color="red">*</font>单位性质</th>
                        </tr>
                        <tbody class="employer">
                        <c:if test="${not empty resultMap.employer}">
                            <c:forEach var="employer" items="${resultMap.employer}" varStatus="status">
                                <tr>
                                    <td><input type="checkbox" class="toDel"></td>
                                    <td class="seq">${status.count}</td>
                                    <td><input type="text" name="employer_name" value="${employer.objMap.employer_name}"
                                               class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="employer_address"
                                               value="${employer.objMap.employer_address}"
                                               class="inputText invalidate[required]" style="width: 90%"/></td>
                                    <td><input type="text" name="employer_type" value="${employer.objMap.employer_type}"
                                               class="inputText invalidate[required]" style="width: 90%"/></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                    <table class="basic" style="width: 100%;margin-top: 10px;">
                        <tr>
                            <th style="text-align: left;padding-left: 15px;" >
                                <font color="red">*</font>研 究 课 题 摘 要
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <textarea class="validate[required,maxSize[300]]" name="proj_abstract"
                                          style="width:98%;height: 150px;"
                                          placeholder="研究内容、方法及意义，是否已获得其他科技项目资助(限300字)">${resultMap.proj_abstract}
                                </textarea>
                            </td>
                        </tr>
                    </table>
                </form>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                    <div align="center" style="margin-top: 10px">
                        <input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
                        <input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="下一步"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index:1000;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:210px;"></ul>
</div>
<div id="menuContent2" class="menuContent" style="display:none; position:absolute;z-index:1000;">
    <ul id="treeDemo2" class="ztree" style="margin-top:0; width:210px;"></ul>
</div>
</body>
</html>
<table id="template" style="display: none">
    <tr id="memberList">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq">${memberListStatus.count}</td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_name" style="width: 90%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required,custom[chinaId]]" onchange="IDcardInforStatistics();"
                   name="memberList_card" style="width: 90%"/>
        </td>
        <td>
            <select name="memberList_education" class="inputText validate[required]" onchange="IDcardInforStatistics();"
                    style="width: 90%;">
                <option value="">请选择</option>
                <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                    <option value="${dict.dictName }"
                            <c:if test="${memberList.objMap.memberList_education eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                </c:forEach>
            </select>

        </td>
        <td>
            <select class="memberTitleLevel inputText" onchange="IDcardInforStatistics();" style="width: 90%;">
                <option value="">请选择</option>
                <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                    <option value="${dict.dictDesc }"
                            <c:if test="${memberList.objMap.memberList_title eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                </c:forEach>
            </select>
            <input type="hidden" name="memberList_title" value=""/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_dept" style="width: 90%"/>
        </td>
        <td>
            <select name="memberList_post" class="inputText validate[required]" onchange="IDcardInforStatistics();"
                    style="width: 90%;">
                <option value="">请选择</option>
                <c:forEach var="dict" items="${dictTypeEnumPostTypeList}">
                    <option value="${dict.dictName }"
                            <c:if test="${memberList.objMap.memberList_post eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <input type="text" class="inputText validate[required]"
                   name="memberList_bearResponsibility"
                   style="width: 90%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_time" style="width: 90%"/>
        </td>
        <%--<td>--%>
        <%--<input type="text" class="inputText" name="memberList_sign" style="width: 90%"/>--%>
        <%--</td>--%>
    </tr>

    <tr id="employer">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td><input type="text" name="employer_name" class="inputText invalidate[required]" style="width: 90%"/></td>
        <td><input type="text" name="employer_address" class="inputText invalidate[required]" style="width: 90%"/></td>
        <td><input type="text" name="employer_type" class="inputText invalidate[required]" style="width: 90%"/></td>
    </tr>

</table>
