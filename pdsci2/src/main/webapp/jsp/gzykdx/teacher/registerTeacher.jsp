<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
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
        function checkUser(obj,type){
            var userCode ="";
            var idNo ="";
            userCode= $("#userCode").val();
            idNo = $("#idNo").val();
            var url = "<s:url value='/gzykdx/teaAndDoc/checkTeacherRegist'/>?userCode="+userCode+"&idNo="+idNo;
            jboxPostMine(url,null,function(resp){
                        jboxEndLoading();
                        if(resp != "NULL"){
                            jboxTip(resp);
                            $(obj).val("");
                            return false;
                        }else {
                            if(type=='idNo'){
                                $("input[name='userCode']").val(idNo);
                                $("input[name='userBirthday']").val(idNo.substring(6,10)+"-"+idNo.substring(10,12));
                                parseInt(idNo.substring(16,17))%2==0?$("input[name='sexId']").eq(1).attr("checked","checked"):$("input[name='sexId']").eq(0).attr("checked","checked");
                                var birthDay = idNo.substring(6,10)+"-"+idNo.substring(10,12)+"-"+idNo.substring(12,14);
                                var r = birthDay.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
                                if(r==null) {return false;}
                                var d= new Date(r[1],r[3]-1,r[4]);
                                if(d.getFullYear()==r[1] && (d.getMonth()+1)==r[3] && d.getDate()==r[4]){
                                    var Y = new Date().getFullYear();
                                    $("input[name='age']").val(Y-r[1]);
                                }
                            }
                        }
                    }, null, false);
        }

        function saveTeacher(){
            if (false == $("#appointForm").validationEngine("validate")) {
                return false;
            }
            var userCode = $("input[name='userCode']").val();
            var userName = $("input[name='userName']").val();
            var userBirthday = $("input[name='userBirthday']").val();
            var age = $("input[name='age']").val();
            var idNo = $("input[name='idNo']").val();
            var userPhone = $("input[name='userPhone']").val();
            var workAreaYear = $("input[name='workAreaYear']").val();
            var workDate = $("input[name='workDate']").val();
            var orgFlow = $("select[name='orgFlow']").val();
            var orgName=$("select[name='orgFlow']").find("option:selected").attr("orgName");
            orgName=encodeURI(encodeURI(orgName));
//            var orgIdentifyNo=$("select[name='orgFlow']").find("option:selected").attr("identifyNo");
            var sexId = $("input[name='sexId']:checked").val();
            var educationId = $("select[name='educationId']").val();
            var degreeId = $("select[name='degreeId']").val();
            var titleName = $("input[name='titleName']").val();
            var identifyNo = $("input[name='identifyNo']").val();
            var educationSchool = $("input[name='educationSchool']").val();
            var educationDate = $("input[name='educationDate']").val();
            var degreeOrg = $("input[name='degreeOrg']").val();
            var degreeDate = $("input[name='degreeDate']").val();
            var applyInfo = {
                "userCode": userCode,
                "userName": userName,
                "userBirthday": userBirthday,
                "age": age,
                "idNo": idNo,
                "userPhone": userPhone,
                "workAreaYear": workAreaYear,
                "workDate": workDate,
                "orgFlow": orgFlow,
                "orgName": orgName,
                "sexId": sexId,
                "educationId": educationId,
                "educationSchool": educationSchool,
                "educationDate": educationDate,
                "degreeId": degreeId,
                "degreeOrg": degreeOrg,
                "degreeDate": degreeDate,
                "titleName": titleName,
                "identifyNo":identifyNo
//                "orgIdentifyNo":orgIdentifyNo
            };
            var t = {'applyInfo': applyInfo};
            $('#jsondata').val(JSON.stringify(t));
            var url = "<s:url value='/gzykdx/teaAndDoc/registTeacher'/>";
            jboxSubmitMine($('#appointForm'), url, function (resp) {
                        if(resp == "NULL"){
                            jboxTip("所填验证码与招生单位验证码不符！");
                            $("input[name='identifyNo']").val("");
                            setTimeout(function(){
                                jboxEndLoading();
                            },1000);
                        }else{
                            $("input").attr("disabled","disabled");
                            jboxTip(resp);
                            setTimeout(function(){
                                window.location.href="<s:url value='/inx/gzzl'/>";
                            },2000);
                        }
                    },
                    null, false);
        }

        function jboxPostMine(posturl,postdata,funcOk,funcErr,showResp){
            $.ajax({
                type : "post",
                url : posturl,
                data : postdata,
                cache : false,
                beforeSend : function(){
                },
                success : function(resp) {
                    if(showResp==false){

                    }else{
                        jboxTip(resp);
                    }
                    if(funcOk!=null){
                        funcOk(resp);
                    }
                },
                error : function() {
                    jboxTip("操作失败,请刷新页面后重试");
                    if(funcErr!=null){
                        funcErr();
                    }
                },
                complete : function(){
                }
            });
        }

        function jboxSubmitMine(jqForm,posturl,funcOk,funcErr,showResp){
            if(showResp!=false){
                showResp = true;
            }
            var options = {
                url : posturl,
                type : "post",
                cache : false,
                beforeSend : function(){
                },
                success : function(resp) {
                    if(showResp){
                        jboxTip(resp);
                    }
                    if(funcOk!=null){
                        if(showResp){
                            setTimeout(function(){
                                funcOk(resp);
                            },1000);
                        }else{
                            funcOk(resp);
                        }
                    }
                },
                error : function() {
                    jboxTip("操作失败,请刷新页面后重试");
                    if(funcErr!=null){
                        funcErr();
                    }
                },
                complete : function(){
                },
                iframe : true
            };
            jqForm.ajaxSubmit(options);
        }
    </script>
</head>
<body>

<div class="mainright">
    <div class="content">
        <form id="appointForm" method="post">
            <input id="jsondata" type="hidden" name="jsondata" value=""/>
            <table class="basic" style="width:100%;margin:10px 0px;border: 0;">
                <%--<tr>--%>
                    <%--<td colspan="2" style="text-align: center;"><h3>导师注册</h3></td>--%>
                <%--</tr>--%>
                    <br/><br/>
                <tr>
                    <td style="text-align:right;border: 0;">所在单位：</td>
                    <td style="border: 0;">
                        <select name="orgFlow" class="validate[required]" style="width:156px;">
                            <option></option>
                            <c:forEach items="${applicationScope.sysOrgList}" var="org">
                                <c:if test="${org.isSecondFlag eq 'Y'}">
                                <option value="${org.orgFlow}" orgName="${org.orgName}"
                                        <c:if test="${org.orgFlow eq param.orgFlow}">selected</c:if>
                                >${org.orgName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">身份证号码：</td>
                    <td style="border: 0;">
                        <input type="text" name="idNo" id="idNo" onchange="checkUser(this,'idNo')" class="validate[required,custom[chinaId]">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">用户名：</td>
                    <td style="border: 0;">
                        <input type="text" name="userCode" id="userCode" onchange="checkUser(this,'userCode')" class="validate[required]">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">姓名：</td>
                    <td style="border: 0;">
                        <input type="text" name="userName" class="validate[required]">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">性别：</td>
                    <td style="border: 0;">
                        <label><input type="radio" name="sexId" class="validate[required]" value="${userSexEnumMan.id}" />男</label>&#12288;
                        <label><input type="radio" name="sexId" class="validate[required]" value="${userSexEnumWoman.id}" />女</label>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">出生年月：</td>
                    <td style="border: 0;">
                        <input type="text" name="userBirthday" class="validate[required]" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">年龄：</td>
                    <td style="border: 0;">
                        <input type="text" name="age" class="validate[required,custom[positiveNum]" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">手机号码：</td>
                    <td style="border: 0;">
                        <input type="text" name="userPhone" class="validate[required,custom[mobile]">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">最高学历：</td>
                    <td style="border: 0;">
                        <select name="educationId" class="validate[required]" style="width:156px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumHighestEducationList }" var="dict">
                                <option
                                        <c:if test="${user.educationId eq dict.dictId }">selected="selected"</c:if>
                                        value="${dict.dictId }">${dict.dictName }</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">最高学历毕业学校：</td>
                    <td style="border: 0;">
                        <input type="text" name="educationSchool" class="validate[required]">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">最高学历毕业时间：</td>
                    <td style="border: 0;">
                        <input type="text" name="educationDate" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM'})">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">最高学位：</td>
                    <td style="border: 0;">
                        <select name="degreeId" class="validate[required]" style="width:156px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumHighestDegreeList }" var="dict">
                                <option
                                        <c:if test="${user.degreeId eq dict.dictId }">selected="selected"</c:if>
                                        value="${dict.dictId }">${dict.dictName }</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">最高学位获得单位：</td>
                    <td style="border: 0;">
                        <input type="text" name="degreeOrg" class="validate[required]">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">最高学位获得时间：</td>
                    <td style="border: 0;">
                        <input type="text" name="degreeDate" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM'})">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">职称：</td>
                    <td style="border: 0;">
                        <input type="text" name="titleName" class="validate[required]">
                        <%--<select name="titleId" class="validate[required]" style="width:156px;">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--<c:forEach items="${dictTypeEnumGzykdxUserTitleList }" var="dict">--%>
                                <%--<option--%>
                                        <%--<c:if test="${user.titleId eq dict.dictId }">selected="selected"</c:if>--%>
                                        <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">所在领域工作年限：</td>
                    <td style="border: 0;">
                        <input type="text" name="workAreaYear" class="validate[required,custom[positiveNum]">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">参加工作时间：</td>
                    <td style="border: 0;">
                        <input type="text" name="workDate" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM'})">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;border: 0;">注册验证码：</td>
                    <td style="border: 0;">
                        <input type="text" name="identifyNo" id="identifyNo" class="validate[required]">
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="saveTeacher();" value="注&#12288;册"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>