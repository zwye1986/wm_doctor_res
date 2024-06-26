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
        <jsp:param name="jquery_cxselect" value="true"/>
    </jsp:include>
    <style type="text/css">
        input[type='text']{width:100px;border-width:0px;}
        input[type='text']{border-bottom:1px solid #e3e3e3;}
    </style>
    <script type="text/javascript" src="<s:url value='/js/jquery.jqprint-0.3.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        //获取字符串长度（汉字算两个字符，字母数字算一个）
        function getByteLen(val) {
            var len = 0;
            if(val!=null&&val!=undefined&&val!='undefined'){
                for (var i = 0; i < val.length; i++) {
                    var a = val.charAt(i);
                    if (a.match(/[^\x00-\xff]/ig) != null) {
                        len += 2;
                    }
                    else {
                        len += 1;
                    }
                }
            }
            return len;
        }
        function search(){
            $("#searchForm").submit();
        }
        function save(){
            if (!$("#saveForm").validationEngine("validate")) {
                return;
            }
//            if(getByteLen($("input[name='researchDirection']").val())>1000){
//                jboxTip("研究方向不能超过500字！");
//                return;
//            }
            if(getByteLen($("textarea[name='applyReason']").val())>500){
                jboxTip("申请原因不能超过250字！");
                return;
            }
            if(getByteLen($("textarea[name='promiseMemo']").val())>500){
                jboxTip("出国研究生承诺不能超过250字！");
                return;
            }
            if(getByteLen($("textarea[name='outTutorAdvice']").val())>200){
                jboxTip("导师意见不能超过100字！");
                return;
            }
            jboxPost("<s:url value='/xjgl/abroadApply/saveAbroadApply'/>", $("#saveForm").serialize(), function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames["mainIframe"].window.toPage(1);
                    jboxClose();
                }
            });
        }
        function uploadImage() {
            $.ajaxFileUpload({
                url: "<s:url value='/gyxjgl/user/uploadImage'/>",
                secureuri: false,
                fileElementId: 'imageFile',
                dataType: 'json',
                success: function (data, status) {
                    if (data.indexOf("success") == -1) {
                        jboxTip(data);
                    } else {
                        jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
                        var arr = [];
                        arr = data.split(":");
                        var url = "${sysCfgMap['upload_base_url']}/" + arr[1];
                        $("#showImg").attr("src", url);
                        $("#img").val(arr[1]);
                    }
                },
                error: function (data, status, e) {
                    jboxTip('${GlobalConstant.UPLOAD_FAIL}');
                },
                complete: function () {
                    $("#imageFile").val("");
                }
            });
        }
        function printOpt(){
            jboxTip("正在准备打印…");
            $("#printDivIframe").removeAttr("hidden");
            setTimeout(function(){
                $("#printDivIframe").jqprint({
                    debug: false,
                    importCSS: true,
                    printContainer: true,
                    operaSupport: false
                });
                $("#printDivIframe").attr("hidden","hidden");
                jboxEndLoading();
                return false;
            },2000);
        }

        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
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
        <form id="searchForm" action="<s:url value="/xjgl/abroadApply/saveAbroadApply"/>" method="post">
            <input type="hidden" name="roleFlag" value="${roleFlag}">
            <div style="margin:20px 0px 10px 10px;">
                <c:if test="${roleFlag eq 'student'||roleFlag eq 'tutor'}">
                    <input type="button" class="search" onclick="save();" value="保&#12288;存" />
                </c:if>
                <input type="button" class="search" onclick="printOpt();" value="打&#12288;印" />
                <span style="padding-left: 80px;color:red;font-weight:bold;">填写信息前请先下载研究生出国承诺书“<a href="<s:url value='/jsp/xjgl/temeplete/LetterOfCommitment.docx'/>" style="cursor:pointer;color:blue;">附件</a>”</span>
            </div>
        </form>
        <form id="saveForm">
            <input type="hidden" name="roleFlag" value="${roleFlag}">
            <c:if test="${roleFlag eq 'student'}">
                <input type="hidden" name="userFlow" value="${sysUser.userFlow}">
                <input type="hidden" name="fwhOrgFlow" value="${sysUser.deptFlow}">
                <input type="hidden" name="fwhOrgName" value="${sysUser.deptName}">
            </c:if>
            <div style="text-align:center;font-size:30px;font-weight:500;">
                南方医科大学研究生出国（境）申请表
            </div>
            <div style="min-width:800px;">
                <table class="basic" style="width:100%;">
                    <tr>
                        <input type="hidden" name="period" value="${empty abroadApply.period?eduUser.period:abroadApply.period}"/>
                        <input type="hidden" name="recordFlow" value="${abroadApply.recordFlow}"/>
                        <th style="min-width:60px;">姓名</th>
                        <td style="min-width:110px;"><input type="text" name="userName" value="${empty abroadApply.userName?sysUser.userName:abroadApply.userName}" readonly="readonly"/></td>
                        <th style="min-width:60px;">学号</th>
                        <td style="min-width:100px;"><input type="text" name="stuNo" value="${empty abroadApply.stuNo?eduUser.sid:abroadApply.stuNo}" readonly="readonly"/></td>
                        <th style="min-width:60px;">性别</th>
                        <td style="min-width:90px;">
                            <input type="text" name="sexName" value="${empty abroadApply.sexName?sysUser.sexName:abroadApply.sexName}" readonly="readonly"/>
                            <input type="hidden" name="sexId" value="${empty abroadApply.sexId?sysUser.sexId:abroadApply.sexId}"/>
                        </td>
                        <th style="min-width:60px;">婚否</th>
                        <td style="min-width:90px;"><input type="text" name="maritalName" value="${abroadApply.maritalName}" class="${roleFlag eq 'global'?'':'validate[required]'}" ${roleFlag eq 'student'?'':'readonly'}/></td>
                        <th style="min-width:60px;">出生年月</th>
                        <td style="min-width:100px;"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'} inputText ctime" readonly="readonly"  <c:if test="${roleFlag eq 'student'}">onClick="WdatePicker({dateFmt:'yyyy-MM'})"</c:if> style="width: 100px;text-align:left;" name="birthDate" value="${abroadApply.birthDate}"/></td>
                    </tr>
                    <tr>
                        <th>培养单位</th>
                        <td style="line-height: 130%;height: 25px;">
                            <input type="text" name="pydwOrgName" value="${empty abroadApply.pydwOrgName?resDoctor.orgName:abroadApply.pydwOrgName}" readonly="readonly"/>
                            <input type="hidden" name="pydwOrgFlow" value="${empty abroadApply.pydwOrgFlow?resDoctor.orgFlow:abroadApply.pydwOrgFlow}"/>
                        </td>
                        <th>专业</th>
                        <td style="line-height: 130%;height: 25px;">
                            <input type="text" name="majorName" value="${empty abroadApply.majorName?eduUser.majorName:abroadApply.majorName}" readonly="readonly"/>
                            <input type="hidden" name="majorId" value="${empty abroadApply.majorId?eduUser.majorId:abroadApply.majorId}"/>
                        </td>
                        <th>导师</th>
                        <td colspan="3">
                            <input type="text" name="tutorName" value="${empty abroadApply.tutorName?eduUser.firstTeacher:abroadApply.tutorName}" readonly="readonly"/>
                            <input type="hidden" name="tutorFlow" value="${empty abroadApply.tutorFlow?eduUser.firstTeacherFlow:abroadApply.tutorFlow}"/>
                        </td>
                        <th>担保人</th>
                        <td style="line-height: 130%;height: 25px;"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="securityName" value="${abroadApply.securityName}" placeholder="父母或配偶" ${roleFlag eq 'student'?'':'readonly'}/></td>
                    </tr>
                    <tr>
                        <th>出国类别</th>
                        <td colspan="9">
                            <c:forEach items="${abroadCategoryEnumList}" var="category">
                                <input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="goAbroadId" value="${category.id}" <c:if test="${abroadApply.goAbroadId eq category.id}"> checked="checked"</c:if> <c:if test="${roleFlag ne 'student'}">disabled="disabled"</c:if>>${category.name}&#12288;
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th>学生层次</th>
                        <td colspan="6">
                            <input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="stuLevelId" value="master" <c:if test="${abroadApply.stuLevelId eq 'master' || pdfn:contains(eduUser.trainTypeName,'硕士')}"> checked="checked"</c:if>  disabled="disabled">硕士研究生
                            &#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="stuLevelId" value="doctor" <c:if test="${abroadApply.stuLevelId eq 'doctor' || pdfn:contains(eduUser.trainTypeName,'博士')}"> checked="checked"</c:if> disabled="disabled">博士研究生
                        </td>
                        <th>入学时间</th>
                        <td colspan="2"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'} ctime" name="inSchoolDate" value="${abroadApply.inSchoolDate}" readonly="readonly"  <c:if test="${roleFlag eq 'student'}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if> style="width: 100px;text-align:left;"/></td>
                    </tr>
                    <tr>
                        <th>联系电话</th>
                        <td colspan="6"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="telphone" value="${abroadApply.telphone}" style="width:260px;" ${roleFlag eq 'student'?'':'readonly'}/></td>
                        <th>E-MAIL</th>
                        <td colspan="2"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="email" value="${abroadApply.email}" ${roleFlag eq 'student'?'':'readonly'}/></td>
                    </tr>
                    <tr>
                        <th colspan="2">起止时间</th>
                        <td colspan="8">
                            <span><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'} inputText ctime" readonly="readonly"   <c:if test="${roleFlag eq 'student'}">onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if> style="width: 100px;text-align:left;" name="beginDate" value="${abroadApply.beginDate}" onchange="checkTime(this)"/>至
                            <input type="text" class="${roleFlag eq 'global'?'':'validate[required]'} inputText ctime" readonly="readonly"   <c:if test="${roleFlag eq 'student'}">onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if> style="width: 100px;text-align:left;" name="endDate" value="${abroadApply.endDate}" onchange="checkTime(this)"/></span>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">拟去国家及城市名称</th>
                        <td colspan="4"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="destinationName" value="${abroadApply.destinationName}" style="width:300px;" ${roleFlag eq 'student'?'':'readonly'}/></td>
                        <th colspan="2">访学院校名称</th>
                        <td colspan="2"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="visitCollegeName" value="${abroadApply.visitCollegeName}" style="width:150px;" ${roleFlag eq 'student'?'':'readonly'}/></td>
                    </tr>
                    <tr>
                        <th colspan="2" rowspan="3">经费组成及出处&#12288;<br/>（项目名称及代码）</th>
                        <th colspan="2">学校项目经费</th>
                        <td colspan="2"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="schoolFee" value="${abroadApply.schoolFee}" ${roleFlag eq 'student'?'':'readonly'}/></td>
                        <th colspan="2">预算金额</th>
                        <td colspan="2"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="schoolBudgetFee" value="${abroadApply.schoolBudgetFee}" ${roleFlag eq 'student'?'':'readonly'}/></td>
                    </tr>
                    <tr>
                        <th colspan="2">导师科研经费</th>
                        <td colspan="2"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="tutorFee" value="${abroadApply.tutorFee}" ${roleFlag eq 'student'?'':'readonly'}/></td>
                        <th colspan="2">预算金额</th>
                        <td colspan="2"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="tutorBudgetFee" value="${abroadApply.tutorBudgetFee}" ${roleFlag eq 'student'?'':'readonly'}/></td>
                    </tr>
                    <tr>
                        <th colspan="2">其他来源经费</th>
                        <td colspan="2"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="otherFee" value="${abroadApply.otherFee}" ${roleFlag eq 'student'?'':'readonly'}/></td>
                        <th colspan="2">预算金额</th>
                        <td colspan="2"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="otherBudgetFee" value="${abroadApply.otherBudgetFee}" ${roleFlag eq 'student'?'':'readonly'}/></td>
                    </tr>
                    <tr>
                        <th>申请原因</th>
                        <td colspan="9">
                            <textarea class="${roleFlag eq 'global'?'':'validate[required]'}" name="applyReason" style="width: 800px;height: 80px;" ${roleFlag eq 'student'?'':'readonly'} placeholder="联合培养的需说明出国留学国家与大学名称、国外导师姓名及联系方式；出国短期学习的要说明出国学习任务及在外时间；出国开会的要说明会议所在国家及城市名称、会议名称、主办机构等。均需附上国外邀请函、或会议通知等。">${abroadApply.applyReason}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>出国研究<br/>生承诺</th>
                        <td colspan="9">
                            <textarea class="${roleFlag eq 'global'?'':'validate[required]'}" name="promiseMemo" style="width: 800px;height: 80px;margin-top: 3px;" ${roleFlag eq 'student'?'':'readonly'}>${abroadApply.promiseMemo}</textarea>
                            <br/><span style="padding-left: 550px;">出国人签名：&emsp;&emsp;&emsp;&emsp;日期：&emsp;&emsp;&emsp;&emsp;</span>
                            <br/><span style="padding-left: 550px;">担保人签名：&emsp;&emsp;&emsp;&emsp;日期：&emsp;&emsp;&emsp;&emsp;</span>
                        </td>
                    </tr>
                    <tr>
                        <th>导师意见</th>
                        <td colspan="9">
                            <textarea class="${roleFlag eq 'tutor'?'validate[required]':''}" name="outTutorAdvice" style="width: 800px;height: 80px;margin-top: 3px;" <c:if test="${roleFlag ne 'tutor'}">readonly="readonly"</c:if>>${abroadApply.outTutorAdvice}</textarea>
                            <br/><span style="padding-left: 550px;">签名：${eduUser.firstTeacher}&emsp;日期：${abroadApply.tutorAuditDate}</span>
                        </td>
                    </tr>
                    <tr>
                        <th>培养单位<br/>意见</th>
                        <td colspan="9">
                            <br/><br/>
                            <span style="padding-left: 550px;">签名（公章）：&emsp;&emsp;&emsp;&emsp;日期：&emsp;&emsp;&emsp;&emsp;</span>
                        </td>
                    </tr>
                    <tr>
                        <th>研究生院<br/>意见</th>
                        <td colspan="9">
                            <br/><br/>
                            <span style="padding-left: 550px;">签名（公章）：&emsp;&emsp;&emsp;&emsp;日期：&emsp;&emsp;&emsp;&emsp;</span>
                        </td>
                    </tr>
                </table>
                <font style="font-weight:bold;">注：此表一式四份，由研究生本人、导师、培养单位及研究生院各留存一份。</font>
            </div>
        </form>
    </div>
</div>
<div hidden="hidden" id="printDivIframe" name="printDivIframe">
    <div style="text-align:center;font-size:20px;font-weight:500;margin-bottom: 10px;">
        南方医科大学研究生出国（境）申请表
    </div>
    <div>
        <table class="basic" style="width:100%;font-size: 11px;">
            <tr>
                <th style="width:50px;">姓名</th>
                <td style="width:60px;">${abroadApply.userName}</td>
                <th style="width:50px;">学号</th>
                <td style="width:60px;">${abroadApply.stuNo}</td>
                <th style="width:50px;">性别</th>
                <td style="width:50px;">${abroadApply.sexName}</td>
                <th style="width:50px;">婚否</th>
                <td style="width:50px;">${abroadApply.maritalName}</td>
                <th style="width:60px;">出生年月</th>
                <td style="width:60px;">${abroadApply.birthDate}</td>
            </tr>
            <tr>
                <th>培养单位</th>
                <td style="line-height: 130%;height: 25px;">${abroadApply.pydwOrgName}</td>
                <th>专业</th>
                <td style="line-height: 130%;height: 25px;">${abroadApply.majorName}</td>
                <th>导师</th>
                <td colspan="3">${abroadApply.tutorName}</td>
                <th>担保人</th>
                <td style="line-height: 130%;height: 25px;">${abroadApply.securityName}</td>
            </tr>
            <tr>
                <th>出国类别</th>
                <td colspan="9">
                    <c:forEach items="${abroadCategoryEnumList}" var="category">
                        <input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="goAbroadId" value="${category.id}" <c:if test="${abroadApply.goAbroadId eq category.id}"> checked="checked"</c:if> <c:if test="${roleFlag ne 'student'}">disabled="disabled"</c:if>>${category.name}&#12288;
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th>学生层次</th>
                <td colspan="6">
                    <input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="stuLevelId" value="master" <c:if test="${abroadApply.stuLevelId eq 'master'}"> checked="checked"</c:if>  <c:if test="${roleFlag ne 'student'}">disabled="disabled"</c:if>>硕士研究生
                    &#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="stuLevelId" value="doctor" <c:if test="${abroadApply.stuLevelId eq 'doctor'}"> checked="checked"</c:if>  <c:if test="${roleFlag ne 'student'}">disabled="disabled"</c:if>>博士研究生
                </td>
                <th>入学时间</th>
                <td colspan="2">${abroadApply.inSchoolDate}</td>
            </tr>
            <tr>
                <th>联系电话</th>
                <td colspan="6">${abroadApply.telphone}</td>
                <th>E-MAIL</th>
                <td colspan="2">${abroadApply.email}</td>
            </tr>
            <tr>
                <th colspan="2">起止时间</th>
                <td colspan="8">&emsp;${abroadApply.beginDate}&emsp;至&emsp;${abroadApply.endDate}</td>
            </tr>
            <tr>
                <th colspan="2">拟去国家及城市名称</th>
                <td colspan="4" style="line-height: 130%;height: 25px;">${abroadApply.destinationName}</td>
                <th colspan="2">访学院校名称</th>
                <td colspan="2">${abroadApply.visitCollegeName}</td>
            </tr>
            <tr>
                <th colspan="2" rowspan="3">经费组成及出处&#12288;<br/>（项目名称及代码）</th>
                <th colspan="2">学校项目经费</th>
                <td colspan="2">${abroadApply.schoolFee}</td>
                <th colspan="2">预算金额</th>
                <td colspan="2">${abroadApply.schoolBudgetFee}</td>
            </tr>
            <tr>
                <th colspan="2">导师科研经费</th>
                <td colspan="2">${abroadApply.tutorFee}</td>
                <th colspan="2">预算金额</th>
                <td colspan="2">${abroadApply.tutorBudgetFee}</td>
            </tr>
            <tr>
                <th colspan="2">其他来源经费</th>
                <td colspan="2">${abroadApply.otherFee}</td>
                <th colspan="2">预算金额</th>
                <td colspan="2">${abroadApply.otherBudgetFee}</td>
            </tr>
            <tr>
                <th>申请原因</th>
                <td colspan="9" style="line-height: 130%;height: 25px;">
                    ${abroadApply.applyReason}<br/>
                </td>
            </tr>
            <tr>
                <th>出国研究<br/>生承诺</th>
                <td colspan="9" style="line-height: 130%;height: 30px;">
                    ${abroadApply.promiseMemo}<br/>
                    <br/><span style="padding-left: 60%;">出国人签名：&emsp;&emsp;&emsp;日期：&emsp;&emsp;&emsp;</span>
                    <br/><span style="padding-left: 60%;">担保人签名：&emsp;&emsp;&emsp;日期：&emsp;&emsp;&emsp;</span>
                </td>
            </tr>
            <tr>
                <th>导师意见</th>
                <td colspan="9" style="line-height: 130%;height: 30px;">
                    ${abroadApply.outTutorAdvice}<br/><br/>
                    <br/><span style="padding-left: 60%;">签名：${eduUser.firstTeacher}&emsp;日期：${abroadApply.tutorAuditDate}</span>
                </td>
            </tr>
            <tr>
                <th>培养单位<br/>意见</th>
                <td colspan="9" style="line-height: 130%;height: 30px;">
                    <br/><br/><br/>
                    <span style="padding-left: 60%;">签名（公章）：&emsp;&emsp;&emsp;日期：&emsp;&emsp;&emsp;</span>
                </td>
            </tr>
            <tr>
                <th>研究生院<br/>意见</th>
                <td colspan="9" style="line-height: 130%;height: 30px;">
                    <br/><br/><br/>
                    <span style="padding-left: 60%;">签名（公章）：&emsp;&emsp;&emsp;日期：&emsp;&emsp;&emsp;</span>
                </td>
            </tr>
        </table>
        <font style="font-weight:bold;">注：此表一式四份，由研究生本人、导师、培养单位及研究生院各留存一份。</font>
    </div>
</div>
</body>
</html>