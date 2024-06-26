<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function selectTag(selfObj,url) {
            var selLi = $(selfObj).parent();
            selLi.siblings("li").removeClass("selectTag");
            selLi.addClass("selectTag");
            $("div.spreadOne").hide();
            $("#"+url).show();
        }
        function auditOpt(statusId){
            jboxConfirm("确认审核"+(statusId=='Passed'?'通过':'不通过')+"？", function(){
                var url = "<s:url value='/xjgl/tutor/auditOpt?role=${param.role}'/>&statusId="+statusId;
                jboxPost(url, $("#paramForm").serialize(), function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    }
                }, null, true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="background-color:white;height:20px;width:100%;"></div>
        <ul id="tags">
            <li class="selectTag">
                <a onclick="selectTag(this,'introduction')" href="javascript:void(0)">个人信息</a>
            </li>
            <li>
                <a onclick="selectTag(this,'recruit')" href="javascript:void(0)">招生信息</a>
            </li>
        </ul>
        <div id="tagContent" style="margin-top:3px;"></div>
        <div class="spreadOne" id="introduction">
            <table class="basic" style="width:100%;margin-top:6px;">
                <tr>
                    <td rowspan="7" style="min-width:150px;width:1%;padding:10px;"><img width="150px" height="200px" src="${sysCfgMap['upload_base_url']}${tutor.headUrl}"/></td>
                    <th style="min-width:100px;width:1%;">导师姓名：</th>
                    <td style="min-width:150px;width:1%;">${tutor.doctorName}&#12288;${tutor.spellName}</td>
                    <th style="min-width:100px;width:1%;">性别：</th>
                    <td>${tutor.sexName}</td>
                </tr>
                <tr>
                    <th style="min-width:100px;width:1%;">身份证号：</th>
                    <td>${tutor.idNo}</td>
                    <th style="min-width:100px;width:1%;">出生年月：</th>
                    <td>${tutor.birthDay}</td>
                </tr>
                <tr>
                    <th>联系方式：</th>
                    <td colspan="3">${tutor.mobilePhone}&#12288;${tutor.workPhone}&#12288;${tutor.emailNo}</td>
                </tr>
                <tr>
                    <th>技术职称：</th>
                    <td>${tutor.technicalTitleName}</td>
                    <th>工作单位：</th>
                    <td>${tutor.workUnit}</td>
                </tr>
                <tr>
                    <th>申报学科：</th>
                    <td>${tutor.oneLevelSubjectName}</td>
                    <th>二级学科：</th>
                    <td>${tutor.twoLevelSubjectName}</td>
                </tr>
                <tr>
                    <th>学术任职：</th>
                    <td><div style="padding:8px 0px;line-height:20px;">${tutor.academicActivities}</div></td>
                    <th>培训结业证<br/>书编号：</th>
                    <td><div style="padding:8px 0px;line-height:20px;">${tutor.certificateNo}<c:if test="${!empty tutor.certificateUrl}">&#12288;<a style="cursor:pointer;color:blue;" href="${sysCfgMap['upload_base_url']}${tutor.certificateUrl}" target="_blank">查看附件</a></c:if></div></td>
                </tr>
                <tr>
                    <th>研究方向：</th>
                    <td colspan="3"><div style="padding:8px 0px;line-height:20px;">${tutor.researchDirection}</div></td>
                </tr>
                <tr>
                    <th colspan="5" style="text-align:left;padding-left:10px;">学术专著：</th>
                </tr>
                <tr>
                    <td colspan="5"><div style="padding:8px 0px;line-height:20px;">${tutor.academicMonographs}</div></td>
                </tr>
                <tr>
                    <th colspan="5" style="text-align:left;padding-left:10px;">个人获奖情况：</th>
                </tr>
                <tr>
                    <td colspan="5"><div style="padding:8px 0px;line-height:20px;">${tutor.awardSituation}</div></td>
                </tr>
                <tr>
                    <th colspan="5" style="text-align:left;padding-left:10px;">从事的主要研究方向及其特点和意义、开展新医疗、新技术等情况、国内所处的学术地位：</th>
                </tr>
                <tr>
                    <td colspan="5"><div style="padding:8px 0px;line-height:20px;">${tutor.researchDescribe}</div></td>
                </tr>
            </table>
        </div>
        <div class="spreadOne" hidden="hidden" id="recruit">
            <table class="basic" style="width:100%; margin-top:6px;">
                <tr>
                    <th style="min-width:180px;width:1%;">导师类型：</th>
                    <td style="width:99%;">${tutor.doctorTypeName}</td>
                </tr>
                <tr>
                    <th>分委员会：</th>
                    <td>${tutor.fwhOrgName}</td>
                </tr>
                <tr>
                    <th>培养单位：</th>
                    <td>${tutor.pydwOrgName}</td>
                </tr>
                <tr>
                    <th>招生专业：</th>
                    <td>${tutor.zsCategoryName}>${tutor.zsSubjectName}>${tutor.recruitSpeName}</td>
                </tr>
                <tr>
                    <th>在读研究生人数：</th>
                    <td>硕士（${tutor.zdyjsMasterNum}）博士（${tutor.zdyjsDoctorNum}）在职博士（${tutor.inserviceDoctorNum}）</td>
                </tr>
                <tr>
                    <th>申招研究生名额：</th>
                    <td>硕士（${tutor.sbyjsMasterNum}）博士（${tutor.sbyjsDoctorNum}）在职博士（${tutor.declareDoctorNum}）</td>
                </tr>
                <tr>
                    <th>研究生论文盲审通过率：</th>
                    <td>硕士（${tutor.paperMasterRate}%）博士（${tutor.paperDoctorRate}%）</td>
                </tr>
                <tr>
                    <th>培养研究生论文获奖情况：</th>
                    <td><div style="padding:8px 0px;line-height:20px;">${tutor.pyyjsAwardDescribe}</div></td>
                </tr>
                <tr>
                    <th>培养研究生其他获奖情况：</th>
                    <td><div style="padding:8px 0px;line-height:20px;">${tutor.pyyjsOtherDescribe}</div></td>
                </tr>
            </table>
        </div>
        <div style="border:1px solid #bbbbbb;padding:0px 10px;line-height:35px;margin-top:10px;">
            <div style="font-weight:bold;">论文统计</div>
            <c:forEach items="${paperList}" var="pap" varStatus="i">
                <div style="word-break:break-all;line-height:25px;<c:if test="${!i.last}">border-bottom:1px solid #bbbbbb;</c:if>">论文标题：${pap.paperTitle}&#12288;&#12288;发表时间：${pap.publishTime}<br/>
                    期刊名称：${pap.periodicalName}&#12288;&#12288;影响因子：${pap.influenceFactor}&#12288;&#12288;JCR小类分区：${pap.jcrPartitionName}
                    <div style="text-align:center;padding-bottom:8px;">
                        <c:if test="${not empty pap.periodicalPicUrl}">
                            <img width="100px" height="150px" onclick="show(this.src)" src="${sysCfgMap['upload_base_url']}${pap.periodicalPicUrl}" style="margin-right:5px;cursor:pointer;"/>
                        </c:if>
                        <c:if test="${not empty pap.paperPicUrl}">
                            <img width="100px" height="150px" onclick="show(this.src)" src="${sysCfgMap['upload_base_url']}${pap.paperPicUrl}" style="cursor:pointer;"/>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div style="border:1px solid #bbbbbb;padding:0px 10px;line-height:35px;margin-top:10px;">
            <h2>课题统计统计</h2>
            <c:forEach items="${topicList}" var="top" varStatus="i">
                <div style="word-break:break-all;line-height:25px;<c:if test="${!i.last}">border-bottom:1px solid #bbbbbb;</c:if>">课题名称：${top.topicTitle}&#12288;&#12288;课题级别：${top.topicLevelName}<br/>
                    立项单位：${top.projectUnit}&#12288;&#12288;课题经费：${top.topicMoney}万元&#12288;&#12288;结题时间：${top.knotTime}
                    <div style="text-align:center;padding-bottom:8px;">
                        <c:if test="${not empty top.contractPicUrl}">
                            <img width="100px" height="150px" onclick="show(this.src)" src="${sysCfgMap['upload_base_url']}${top.contractPicUrl}" style="margin-right:5px;cursor:pointer;"/>
                        </c:if>
                        <c:if test="${not empty top.memberPicUrl}">
                            <img width="100px" height="150px" onclick="show(this.src)" src="${sysCfgMap['upload_base_url']}${top.memberPicUrl}" style="margin-right:5px;cursor:pointer;"/>
                        </c:if>
                        <c:if test="${not empty top.fundsPicUrl}">
                            <img width="100px" height="150px" onclick="show(this.src)" src="${sysCfgMap['upload_base_url']}${top.fundsPicUrl}" style="margin-right:5px;cursor:pointer;"/>
                        </c:if>
                        <c:if test="${not empty top.schedulePicUrl}">
                            <img width="100px" height="150px" onclick="show(this.src)" src="${sysCfgMap['upload_base_url']}${top.schedulePicUrl}" style="margin-right:5px;cursor:pointer;"/>
                        </c:if>
                        <c:if test="${not empty top.sealPicUrl}">
                            <img width="100px" height="150px" onclick="show(this.src)" src="${sysCfgMap['upload_base_url']}${top.sealPicUrl}" style="cursor:pointer;"/>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
        <form id="paramForm">
            <input type="hidden" name="doctorFlow" value="${tutor.doctorFlow}">
            <input type="hidden" name="lastTypeId" value="${tutor.doctorTypeId}">
            <input type="hidden" name="firstPassFlag" value="${tutor.firstPassFlag}">
            <c:set var="date" value="${pdfn:getCurrDate()}"/>
            <c:if test="${param.role eq 'pydw' || param.role eq 'fwh' || param.role eq 'xwk'}">
                <fieldset style="margin:5px 0px;padding:5px 10px 0px 10px;">
                    <legend style="color:#2F2F2F">培养单位审核</legend>
                    <c:set var="pydwFlag" value="${tutor.pydwAuditStatusId eq 'Passing' && tutor.fwhAuditStatusId ne 'Passed' && tutor.fwhAuditStatusId ne 'UnPassed'}"/>
                    <c:set var="pydwReFlag" value="${tutor.fwhAuditStatusId ne 'Passed' && tutor.fwhAuditStatusId ne 'UnPassed'}"/>
                    <div style="text-align:left;padding:10px;">
                        <c:if test="${(pydwFlag && param.viewFlag ne 'view') || (pydwReFlag && param.viewFlag eq 'reAudit')}">
                            <input type="button" class="search" value="通&#12288;过" onclick="auditOpt('Passed');"/>
                            <input type="button" class="search" value="不通过" onclick="auditOpt('UnPassed');"/>
                            <p style="line-height:35px;">审核意见：</p>
                            <textarea name="pydwAuditAdvice" style="width:100%;height:80px;" onchange="if(value.length>200) value=value.substr(0,200)">${tutor.pydwAuditAdvice}</textarea>
                            <p style="line-height:35px;">审核时间：${empty tutor.pydwAuditTime?date:tutor.pydwAuditTime}</p>
                        </c:if>
                        <c:if test="${(!pydwFlag || param.viewFlag eq 'view') && !(pydwReFlag && param.viewFlag eq 'reAudit')}">
                            <p style="line-height:25px;">审核结果：${tutor.pydwAuditStatusName}</p>
                            <p style="line-height:25px;">审核意见：</p>
                            <textarea name="pydwAuditAdvice" style="width:100%;height:80px;" disabled="disabled">${tutor.pydwAuditAdvice}</textarea>
                            <p style="line-height:25px;">审核时间：${tutor.pydwAuditTime}</p>
                        </c:if>
                    </div>
                </fieldset>
            </c:if>
            <c:if test="${param.role eq 'fwh' || param.role eq 'xwk'}">
                <fieldset style="margin:5px 0px;padding:5px 10px 0px 10px;">
                    <legend style="color:#2F2F2F">分委会审核</legend>
                    <c:set var="fwhFlag" value="${tutor.fwhAuditStatusId eq 'Passing' && tutor.xwkAuditStatusId ne 'Passed' && tutor.xwkAuditStatusId ne 'UnPassed'}"/>
                    <c:set var="fwhReFlag" value="${tutor.xwkAuditStatusId ne 'Passed' && tutor.xwkAuditStatusId ne 'UnPassed'}"/>
                    <div style="text-align:left;padding:10px;">
                        <c:if test="${(fwhFlag && param.viewFlag ne 'view') || (fwhReFlag && param.viewFlag eq 'reAudit')}">
                            <input type="button" class="search" value="通&#12288;过" onclick="auditOpt('Passed');"/>
                            <input type="button" class="search" value="不通过" onclick="auditOpt('UnPassed');"/>
                            <p style="line-height:35px;">审核意见：</p>
                            <textarea name="fwhAuditAdvice" style="width:100%;height:80px;" onchange="if(value.length>200) value=value.substr(0,200)">${tutor.fwhAuditAdvice}</textarea>
                            <p style="line-height:35px;">审核时间：${empty tutor.fwhAuditTime?date:tutor.fwhAuditTime}</p>
                        </c:if>
                        <c:if test="${(!fwhFlag || param.viewFlag eq 'view') && !(fwhReFlag && param.viewFlag eq 'reAudit')}">
                            <p style="line-height:25px;">审核结果：${tutor.fwhAuditStatusName}</p>
                            <p style="line-height:25px;">审核意见：</p>
                            <textarea name="fwhAuditAdvice" style="width:100%;height:80px;" disabled="disabled">${tutor.fwhAuditAdvice}</textarea>
                            <p style="line-height:25px;">审核时间：${tutor.fwhAuditTime}</p>
                        </c:if>
                    </div>
                </fieldset>
            </c:if>
            <c:if test="${param.role eq 'xwk'}">
                <fieldset style="margin:5px 0px;padding:5px 10px 0px 10px;">
                    <legend style="color:#2F2F2F">学位科审核</legend>
                    <div style="text-align:left;padding:10px;">
                        <c:if test="${(tutor.xwkAuditStatusId eq 'Passing' && param.viewFlag ne 'view') || param.viewFlag eq 'reAudit'}">
                            <input type="button" class="search" value="通&#12288;过" onclick="auditOpt('Passed');"/>
                            <input type="button" class="search" value="不通过" onclick="auditOpt('UnPassed');"/>
                            <p style="line-height:35px;">审核意见：</p>
                            <textarea name="xwkAuditAdvice" style="width:100%;height:80px;" onchange="if(value.length>200) value=value.substr(0,200)">${tutor.xwkAuditAdvice}</textarea>
                            <p style="line-height:35px;">审核时间：${empty tutor.xwkAuditTime?date:tutor.xwkAuditTime}</p>
                        </c:if>
                        <c:if test="${(tutor.xwkAuditStatusId ne 'Passing' || param.viewFlag eq 'view') && param.viewFlag ne 'reAudit'}">
                            <p style="line-height:25px;">审核结果：${tutor.xwkAuditStatusName}</p>
                            <p style="line-height:25px;">审核意见：</p>
                            <textarea name="xwkAuditAdvice" style="width:100%;height:80px;" disabled="disabled">${tutor.xwkAuditAdvice}</textarea>
                            <p style="line-height:25px;">审核时间：${tutor.xwkAuditTime}</p>
                        </c:if>
                    </div>
                </fieldset>
            </c:if>
        </form>
        <div style="text-align: center;margin-top:20px;">
            <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
        </div>
    </div>
</div>
<div id="bigPic" style="display:none;position:absolute;top:15%;left:30%;z-index:2;background:white;"></div>
<div id="over" style="display:none;width:100%;height:100%;opacity:0.8;filter:alpha(opacity=80);position:absolute;top:0;left:0;z-index:1;background:silver;"></div>
<a id="bigA" target="_blank"><span id="bigSpan"></span></a>
</body>
</html>
<script type="text/javascript">
    var login = document.getElementById('bigPic');
    var over = document.getElementById('over');
    function show(url)
    {
//        $("#bigPic").html("<img width='300px' height='400px' onclick='hide();' src='"+ url + "'/>");
//        login.style.display = "block";
//        over.style.display = "block";
        $("#bigA").attr("href",url);
        $("#bigSpan").click();
    }
    function hide()
    {
        login.style.display = "none";
        over.style.display = "none";
    }
</script>