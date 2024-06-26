<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true" />
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        table .ltd{text-align:right;font-weight:bold;color:#707070;}
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
            var value = sessionStorage.getItem("tabFlag");
            if (value != "null") {
                selectTag($("#"+value+"Label"),value);
            }
        });
        function selectTag(selfObj,url) {
            var selLi = $(selfObj).parent();
            selLi.siblings("li").removeClass("selectTag");
            selLi.addClass("selectTag");
            $("div.spreadOne").hide();
            $("#"+url).show();
            sessionStorage.setItem("tabFlag", url);
        }
        function uploadImage(name,flag) {
            if(name=="headUrl" && ${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}){
                jboxTip("审核流程中，无法变更导师信息");
                return;
            }
            if($("#"+name).val() == ""){
                jboxTip("请选择文件");
                return;
            }
            $.ajaxFileUpload({
                url: "<s:url value='/gyxjgl/tutor/imageUpload?tutorFlow=${tutor.doctorFlow}&identifyFlag='/>"+flag,
                secureuri: false,
                fileElementId: name,
                dataType: 'json',
                success: function (data) {
                    if (data.indexOf("success") == -1) {
                        jboxTip(data);
                    } else {
                        jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
                        var url = "${sysCfgMap['upload_base_url']}" + data.split(":")[1];
                        $("img[name='"+name+"']").attr("src", url);//展示图片路径
                        $("."+name).val(data.split(":")[1]);//路径保存参数
                        $(".search",$("."+name).closest("td")).val("重新上传");
                    }
                },
                error:function(){
                    jboxTip('${GlobalConstant.UPLOAD_FAIL}');
                }
            });
        }
        function saveTutor(obj){
            var form = $(obj).parents("form");
            if(!form.validationEngine("validate")){
                return;
            }
            var jcrText = $("select[name='jcrPartitionId'] option:selected").text();
            $("input[name='jcrPartitionName']").val(jcrText);
            var topText = $("select[name='topicLevelId'] option:selected").text();
            $("input[name='topicLevelName']").val(topText);
            var docText = $("select[name='doctorTypeId'] option:selected").text();
            $("input[name='doctorTypeName']").val(docText);
            var fwhText = $("select[name='fwhOrgFlow'] option:selected").text();
            $("input[name='fwhOrgName']").val(fwhText);
            var pydText = $("select[name='pydwOrgFlow'] option:selected").text();
            $("input[name='pydwOrgName']").val(pydText);
            jboxPost("<s:url value='/gyxjgl/tutor/saveTutor'/>", form.serialize(), function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    location.reload();
                }
            });
        }
        function delInfo(recordFlow,tabFlag){
            jboxConfirm("确认删除"+(tabFlag=="paper"?"论文":"课题")+"信息？", function(){
                var url = "<s:url value='/gyxjgl/tutor/delInfo?recordFlow='/>"+recordFlow+"&tabFlag="+tabFlag;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function editInfo(obj,tabFlag){
            var url="<s:url value='/gyxjgl/tutor/showEditTopicOrPaper'/>?flow="+obj+"&tabFlag="+tabFlag;
            jboxOpen(url, "编辑", 860, 600);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="background-color:white;height:20px;width:100%;"></div>
        <div>
            <ul id="tags">
                <li class="selectTag">
                    <a onclick="selectTag(this,'photo')" href="javascript:void(0)" id="photoLabel">导师照片</a>
                </li>
                <li>
                    <a onclick="selectTag(this,'introduction')" href="javascript:void(0)" id="introductionLabel">个人信息</a>
                </li>
                <li>
                    <a onclick="selectTag(this,'paper')" href="javascript:void(0)" id="paperLabel">论文统计</a>
                </li>
                <li>
                    <a onclick="selectTag(this,'topic')" href="javascript:void(0)" id="topicLabel">课题统计</a>
                </li>
                <li>
                    <a onclick="selectTag(this,'recruit')" href="javascript:void(0)" id="recruitLabel">招生信息</a>
                </li>
            </ul>
            <div id="tagContent" style="margin-top:3px;"></div>
            <c:set var="recruitFlag" value="${tutor.xwkAuditStatusId eq 'Passed'}" />
            <div class="spreadOne" id="photo">
                <div class="spreadOneOne">
                    <div style="line-height:20px;font-size:16px;color:grey;padding:5px 0px 5px 0px;">&#12288;&#12288;请上传个人近期正面肖像照一张，该照片将在导师库中显示</div>
                    <table class="basic" style="width:100%;margin-top:3px;margin-bottom:30px;">
                        <tr><td rowspan="3" style="min-width:240px;width:1%;padding:10px;"><img name="headUrl" src="${sysCfgMap['upload_base_url']}${tutor.headUrl}" width="240px" height="320px"/></td><td style="font-weight:bold;color:#707070;width:99%;">请选择要上传的照片文件：</td></tr>
                        <tr><td style="height:60px;">
                            <input type="file" id="headUrl" name="image" style="width:176px;font-size:14px;color:#707070;" ${tutor.applyFlag eq 'Y' && param.role ne 'xwk'?'disabled':''}>
                            <input type="button" class="search" onclick="uploadImage('headUrl','headUrl');" value="${empty tutor.headUrl?'上传照片':'重新上传'}">
                            <input type="hidden" class="headUrl" value="${tutor.headUrl}">
                        </td></tr>
                        <tr><td style="color:grey;white-space:nowrap;">注意事项：<br/>1、上传的照片图像格式要求为JPG<br/>2、要求照片高度宽度比例为4:3 （建议图像大小为320*240像素）<br/>3、图像文件大小不能超过1M（1024K）</td></tr>
                    </table>
                </div>
            </div>
            <div class="spreadOne" hidden="hidden" id="introduction">
                <div class="spreadOneTwo">
                    <form>
                        <input type="hidden" name="doctorFlow" value="${tutor.doctorFlow}">
                        <input type="hidden" name="tabFlag" value="introduction">
                        <div style="line-height:20px;font-size:16px;color:grey;padding:5px 0px 5px 0px;">&#12288;&#12288;请认真填写以下个人信息，导师资格审核工作将以此信息为准</div>
                        <table class="basic" style="width:100%; margin-top:3px; margin-bottom:30px;">
                            <tr>
                                <td class="ltd" style="min-width:160px;width:1%;">导师类型：</td>
                                <td style="width:99%;">
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <select class="validate[required]" name="doctorTypeId" style="width:156px;">
                                            <option value="">请选择</option>
                                            <option value="xsxbd" <c:if test="${tutor.doctorTypeId eq 'xsxbd'}">selected</c:if>>学术型博导</option>
                                            <option value="xsxsd" <c:if test="${tutor.doctorTypeId eq 'xsxsd'}">selected</c:if>>学术型硕导</option>
                                            <option value="zyxbd" <c:if test="${tutor.doctorTypeId eq 'zyxbd'}">selected</c:if>>专业型博导</option>
                                            <option value="zyxsd" <c:if test="${tutor.doctorTypeId eq 'zyxsd'}">selected</c:if>>专业型硕导</option>
                                        </select><input type="hidden" name="doctorTypeName">
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.doctorTypeName}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">二级机构：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <select class="validate[required]" name="pydwOrgFlow" style="width:156px;">
                                        <option value="">请选择</option>
                                        <c:forEach items="${orgList}" var="org">
                                            <option value="${org.orgFlow}" <c:if test="${tutor.pydwOrgFlow eq org.orgFlow }">selected</c:if>>${org.orgName}</option>
                                        </c:forEach>
                                        </select><input type="hidden" name="pydwOrgName">
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.pydwOrgName}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">招生专业一：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <select class="validate[required]" name="recruitSpeId" style="width:156px;">
                                            <option value="">请选择</option>
                                            <c:forEach items="${dictTypeEnumGyMajorList}" var="major">
                                                <option value="${major.dictId}" ${tutor.recruitSpeId eq major.dictId?'selected':''}>
                                                    [${major.dictId}]${major.dictName}</option>
                                            </c:forEach>
                                        </select><input type="hidden" name="recruitSpeName"><span style="color:red;">&#12288;*&ensp;招生专业一必填</span>
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.recruitSpeName}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">招生专业二：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <select name="secondRecruitSpeId" style="width:156px;">
                                        <option value="">请选择</option>
                                            <c:forEach items="${dictTypeEnumGyMajorList}" var="major">
                                                <option value="${major.dictId}" ${tutor.secondRecruitSpeId eq major.dictId?'selected':''}>
                                                    [${major.dictId}]${major.dictName}</option>
                                            </c:forEach>
                                        </select><input type="hidden" name="secondRecruitSpeName">
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.secondRecruitSpeName}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd" style="min-width:160px;width:1%;">导师姓名：</td>
                                <td style="width:99%;">
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="doctorName" value="${tutor.doctorName}" style="width:152px;">
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.doctorName}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">姓名拼音：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="spellName" value="${tutor.spellName}" style="width:152px;">
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.spellName}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">身份证号：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="idNo" value="${tutor.idNo}" style="width:152px;">
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk' && !recruitFlag}">${tutor.idNo}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">性&#12288;&#12288;别：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || (tutor.applyFlag eq 'Y' and recruitFlag) || param.role eq 'xwk'}">
                                        <select class="validate[required]" style="width: 156px;" name="sexId">
                                            <option/>
                                            <option value="${userSexEnumMan.id}" ${tutor.sexId eq userSexEnumMan.id?'selected':''}>${userSexEnumMan.name}</option>
                                            <option value="${userSexEnumWoman.id}" ${tutor.sexId eq userSexEnumWoman.id?'selected':''}>${userSexEnumWoman.name}</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk' && !recruitFlag}">${tutor.sexName}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">出生年月：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="birthDay" value="${tutor.birthDay}" style="width:152px;"><span style="color:red;">&#12288;格式：1970-05</span>
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.birthDay}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">手&#12288;&#12288;机：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="mobilePhone" value="${tutor.mobilePhone}" style="width:152px;">
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.mobilePhone}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">办公电话：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="workPhone" value="${tutor.workPhone}" style="width:152px;"><span style="color:red;">&#12288;格式：020-61640114-001（区号-电话号码-分机号）</span>
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.workPhone}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">电子邮箱：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="emailNo" value="${tutor.emailNo}" style="width:152px;">
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.emailNo}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">工作单位：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="workUnit" value="${tutor.workUnit}" style="width:300px;">
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.workUnit}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">技术职称：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="technicalTitleName" value="${tutor.technicalTitleName}" style="width:300px;">
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.technicalTitleName}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">学术任职：</td>
                                <td style="overflow:hidden;white-space:nowrap;">
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="academicActivities" value="${tutor.academicActivities}" style="width:400px;"><span style="color:red;">&#12288;格式：多个学术任职请用、号分隔</span>
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.academicActivities}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">研究方向：</td>
                                <td>
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="researchDirection" value="${tutor.researchDirection}" style="width:400px;">
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.researchDirection}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">学术专著：</td>
                                <td style="padding:2px 0px 2px 10px;">
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <textarea class="validate[required]" name="academicMonographs" style="width:600px;height:100px;" onchange="if(value.length>1000) value=value.substr(0,1000)">${tutor.academicMonographs}</textarea>
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.academicMonographs}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">个人获奖情况：</td>
                                <td style="padding:2px 0px 2px 10px;">
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <textarea class="validate[required]" name="awardSituation" style="width:600px;height:100px;" onchange="if(value.length>1000) value=value.substr(0,1000)">${tutor.awardSituation}</textarea>
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}">${tutor.awardSituation}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="font-weight:bold;color:#707070;">&#12288;&#12288;&#12288;&#12288;&#12288;&nbsp;本人从事的主要研究方向及其特点和意义、开展新医疗、新技术等情况、国内所处的学术地位：</td>
                            </tr>
                            <tr>
                                <td colspan="2" style="padding:2px 0px 2px 105px;">
                                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                        <textarea class="validate[required]" name="researchDescribe" style="width:677px;height:100px;" onchange="if(value.length>1000) value=value.substr(0,1000)">${tutor.researchDescribe}</textarea>
                                    </c:if>
                                    <c:if test="${tutor.applyFlag eq 'Y' && param.role ne 'xwk'}"><div style="width:677px;">${tutor.researchDescribe}</div></c:if>
                                </td>
                            </tr>
                        </table>
                        <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                        <div style="text-align:center;"><input type="button" onclick="saveTutor(this)" class="search" value="提交个人信息"></div>
                        </c:if>
                    </form>
                </div>
            </div>
            <div class="spreadOne" hidden="hidden" id="paper">
                <div class="spreadOneThree">
                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                    <form>
                        <input type="hidden" name="doctorFlow" value="${tutor.doctorFlow}">
                        <input type="hidden" name="tabFlag" value="paper">
                        <div style="line-height:20px;font-size:16px;color:grey;padding:5px 0px 5px 0px;">&#12288;&#12288;请填写近三年发表的最具代表性科研论文相关信息，本人必须为第一作者或通讯作者，最多三篇，导师资格审核工作将以此信息为准。每篇论文需同时提供论文相关的各类资料证明照片，包括：【1、期刊首页或pubmed搜索结果截图；2、论文首页照片】</div>
                        <table class="basic" style="width:100%; margin-top:3px; margin-bottom:30px;">
                            <tr>
                                <td class="ltd" style="min-width:160px;width:1%;">论文标题：</td>
                                <td style="width:99%;"><input type="text" class="validate[required]" name="paperTitle" style="width:400px;"></td>
                            </tr>
                            <tr>
                                <td class="ltd">发表时间：</td>
                                <td><input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="validate[required]" name="publishTime"></td>
                            </tr>
                            <tr>
                                <td class="ltd">作者排名：</td>
                                <td><input type="text" class="validate[required]" name="authorRank"></td>
                            </tr>
                            <tr>
                                <td class="ltd">期刊名称：</td>
                                <td><input type="text" class="validate[required]" name="periodicalName"></td>
                            </tr>
                            <tr>
                                <td class="ltd">影响因子：</td>
                                <td><input type="text" class="validate[required]" name="influenceFactor"></td>
                            </tr>
                            <tr>
                                <td class="ltd">JCR分区：</td>
                                <td><select name="jcrPartitionId" class="validate[required]" style="width:156px;">
                                    <option value="">请选择</option>
                                    <option value="1">一区</option>
                                    <option value="2">二区</option>
                                    <option value="3">三区</option>
                                    <option value="4">四区</option>
                                </select><input type="hidden" name="jcrPartitionName"></td>
                            </tr>
                            <tr>
                                <td style="text-align:right;padding:3px 0px 3px 20px;"><span style="line-height:16px;font-weight:bold;color:#707070;">期刊首页照片或pubmed&nbsp;<br/>搜索结果截图：</span></td>
                                <td>
                                    <input type="file" id="periodicalPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                                    <input type="button" class="search" onclick="uploadImage('periodicalPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                                    <input type="hidden" class="periodicalPicUrl" name="periodicalPicUrl">
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">论文正文图片：</td>
                                <td>
                                    <input type="file" id="paperPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                                    <input type="button" class="search" onclick="uploadImage('paperPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                                    <input type="hidden" class="paperPicUrl" name="paperPicUrl">
                                </td>
                            </tr>
                        </table>
                        <div style="text-align:center;"><input type="button" onclick="saveTutor(this)" class="search" value="保存论文信息"></div>
                    </form>
                    </c:if>
                    <div style="font-weight:bold;color:#707070;line-height:35px;">已添加文章列表</div>
                    <table class="xllist">
                        <tr>
                            <th>论文标题</th>
                            <th>期刊名称</th>
                            <th>发表时间</th>
                            <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                            <th>操作</th>
                            </c:if>
                        </tr>
                        <c:forEach items="${paperList}" var="pap">
                            <tr>
                                <td>${pap.paperTitle}</td>
                                <td>${pap.periodicalName}</td>
                                <td>${pap.publishTime}</td>
                                <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                <td><a onclick="delInfo('${pap.recordFlow}','paper');" style="cursor:pointer;color:blue;">删除</a>
                                    <a onclick="editInfo('${pap.recordFlow}','paper');" style="cursor:pointer;color:blue;">编辑</a></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="spreadOne" hidden="hidden" id="topic">
                <div class="spreadOneFour">
                    <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                    <form>
                        <input type="hidden" name="doctorFlow" value="${tutor.doctorFlow}">
                        <input type="hidden" name="tabFlag" value="topic">
                        <div style="line-height:20px;font-size:16px;color:grey;padding:5px 0px 5px 0px;">&#12288;&#12288;请填写本人承担的科研课题相关信息（要求本人为课题负责人），导师资格审核工作将以此信息为准。每项课题需同时提供课题相关的各类资料证明照片，包括：【1、项目合同书首页；2、课题组成员签字页；3、含项目资助经费页面；4、含项目进度内容页面（项目起始时间）；5、项目主管部门盖章页】，每张图片文件大小不能超过2M（2048K）。本年度12月31日前结题的课题经费不纳入统计范围。《国家自然科学基金资助项目》只需上传资助计划书首页即可。</div>
                        <table class="basic" style="width:100%; margin-top:3px; margin-bottom:30px;">
                            <tr>
                                <td class="ltd" style="min-width:160px;width:1%;">课题名称：</td>
                                <td style="width:99%;"><input type="text" class="validate[required]" name="topicTitle" style="width:400px;"></td>
                            </tr>
                            <tr>
                                <td class="ltd">课题级别：</td>
                                <td><select name="topicLevelId" class="validate[required]" style="width:156px;">
                                    <option value="">请选择</option>
                                    <option value="country">国家级</option>
                                    <option value="province">省级</option>
                                    <option value="city">市级</option>
                                </select><input type="hidden" name="topicLevelName"></td>
                            </tr>
                            <tr>
                                <td class="ltd">立项单位：</td>
                                <td><input type="text" class="validate[required]" name="projectUnit"></td>
                            </tr>
                            <tr>
                                <td class="ltd">立项时间：</td>
                                <td><input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="validate[required]" name="setupTime"></td>
                            </tr>
                            <tr>
                                <td class="ltd">课题经费：</td>
                                <td><input type="text" class="validate[required,custom[number]]" name="topicMoney"> 万元</td>
                            </tr>
                            <tr>
                                <td class="ltd">结题时间：</td>
                                <td><input type="text" class="validate[required]" name="knotTime"></td>
                            </tr>
                            <tr>
                                <td class="ltd">项目合同书首页：</td>
                                <td>
                                    <input type="file" id="contractPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                                    <input type="button" class="search" onclick="uploadImage('contractPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                                    <input type="hidden" class="contractPicUrl" name="contractPicUrl">
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">课题成员签字页：</td>
                                <td>
                                    <input type="file" id="memberPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                                    <input type="button" class="search" onclick="uploadImage('memberPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                                    <input type="hidden" class="memberPicUrl" name="memberPicUrl">
                                </td>                            </tr>
                            <tr>
                                <td class="ltd">项目资助经费页：</td>
                                <td>
                                    <input type="file" id="fundsPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                                    <input type="button" class="search" onclick="uploadImage('fundsPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                                    <input type="hidden" class="fundsPicUrl" name="fundsPicUrl">
                                </td>                            </tr>
                            <tr>
                                <td class="ltd">项目进度内容页：</td>
                                <td>
                                    <input type="file" id="schedulePicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                                    <input type="button" class="search" onclick="uploadImage('schedulePicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                                    <input type="hidden" class="schedulePicUrl" name="schedulePicUrl">
                                </td>                            </tr>
                            <tr>
                                <td class="ltd">主管部门盖章页：</td>
                                <td>
                                    <input type="file" id="sealPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                                    <input type="button" class="search" onclick="uploadImage('sealPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                                    <input type="hidden" class="sealPicUrl" name="sealPicUrl">
                                </td>                            </tr>
                        </table>
                        <div style="text-align:center;"><input type="button" onclick="saveTutor(this)" class="search" value="保存课题信息"></div>
                    </form>
                    </c:if>
                    <div style="font-weight:bold;color:#707070;line-height:35px;">已添加课题列表</div>
                    <table class="xllist">
                        <tr>
                            <th>课题标题</th>
                            <th>课题级别</th>
                            <th>课题经费（万元）</th>
                            <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                            <th>操作</th>
                            </c:if>
                        </tr>
                        <c:forEach items="${topicList}" var="top">
                            <tr>
                                <td>${top.topicTitle}</td>
                                <td>${top.topicLevelName}</td>
                                <td>${top.topicMoney}</td>
                                <c:if test="${tutor.applyFlag ne 'Y' || param.role eq 'xwk'}">
                                <td><a onclick="delInfo('${top.recordFlow}','topic');" style="cursor:pointer;color:blue;">删除</a>
                                    <a onclick="editInfo('${top.recordFlow}','topic');" style="cursor:pointer;color:blue;">编辑</a></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="spreadOne" hidden="hidden" id="recruit">
                <div class="spreadOneFive">
                    <form>
                        <input type="hidden" name="doctorFlow" value="${tutor.doctorFlow}">
                        <input type="hidden" name="tabFlag" value="recruit">
                        <div style="line-height:20px;font-size:16px;color:grey;padding:5px 0px 5px 0px;">&#12288;&#12288;请如实填写以下招生信息，导师招生计划申核工作将以此信息为准</div>
                        <table class="basic" style="width:100%; margin-top:3px; margin-bottom:30px;">
                            <tr>
                                <td class="ltd" style="min-width:160px;width:1%;">导师类型：</td>
                                <td style="width:99%;">${tutor.doctorTypeName}</td>
                            </tr>
                            <tr>
                                <td class="ltd">二级机构：</td>
                                <td>${tutor.pydwOrgName}</td>
                            </tr>
                            <tr>
                                <td class="ltd">招生专业一：</td>
                                <td>${tutor.recruitSpeName}</td>
                            </tr>
                            <tr>
                                <td class="ltd">招生专业二：</td>
                                <td>${tutor.secondRecruitSpeName}</td>
                            </tr>
                            <%--<tr>--%>
                                <%--<td class="ltd">招生专业：</td>--%>
                                <%--<td>--%>
                                    <%--<c:if test="${recruitFlag || param.role eq 'xwk'}">--%>
                                        <%--<select class="validate[required]" name="recruitSpeId" style="width:156px;">--%>
                                        <%--<option value="">请选择</option>--%>
                                        <%--<c:forEach items="${dictTypeEnumGyMajorList}" var="spe">--%>
                                            <%--<option value="${spe.dictId}" ${tutor.recruitSpeId eq spe.dictId?'selected':''}>${spe.dictName}</option>--%>
                                        <%--</c:forEach>--%>
                                        <%--</select><input type="hidden" name="recruitSpeName">--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${!recruitFlag && param.role ne 'xwk'}">${tutor.recruitSpeName}</c:if>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <tr>
                                <td style="text-align:right;padding:3px 0px 3px 20px;"><span style="line-height:16px;font-weight:bold;color:#707070;">在读研究生人数：<br/>[ 硕士/博士/在职博士 ]&nbsp;</span></td>
                                <td>
                                    <c:if test="${recruitFlag || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="zdyjsMasterNum" value="${tutor.zdyjsMasterNum}" style="width:60px"> / <input type="text" class="validate[required]" name="zdyjsDoctorNum" value="${tutor.zdyjsMasterNum}" style="width:60px"> / <input type="text" class="validate[required]" name="inserviceDoctorNum" value="${tutor.inserviceDoctorNum}" style="width:60px">
                                    </c:if>
                                    <c:if test="${!recruitFlag && param.role ne 'xwk'}">${tutor.zdyjsMasterNum} / ${tutor.zdyjsMasterNum} / ${tutor.inserviceDoctorNum}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align:right;padding:3px 0px 3px 20px;"><span style="line-height:16px;font-weight:bold;color:#707070;">申招研究生名额：<br/>[ 硕士/博士/在职博士 ]&nbsp;</span></td>
                                <td>
                                    <c:if test="${recruitFlag || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="sbyjsMasterNum" value="${tutor.sbyjsMasterNum}" style="width:60px"> / <input type="text" class="validate[required]" name="sbyjsDoctorNum" value="${tutor.sbyjsDoctorNum}" style="width:60px"> / <input type="text" class="validate[required]" name="declareDoctorNum" value="${tutor.declareDoctorNum}" style="width:60px">
                                    </c:if>
                                    <c:if test="${!recruitFlag && param.role ne 'xwk'}">${tutor.sbyjsMasterNum} / ${tutor.sbyjsDoctorNum} / ${tutor.declareDoctorNum}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align:right;padding:3px 0px 3px 20px;"><span style="line-height:16px;font-weight:bold;color:#707070;">研究生论文盲审通过率：<br/>[ 硕士/博士 ]&nbsp;</span></td>
                                <td>
                                    <c:if test="${recruitFlag || param.role eq 'xwk'}">
                                        <input type="text" class="validate[required]" name="paperMasterRate" value="${tutor.paperMasterRate}" style="width:60px"> % / <input type="text" class="validate[required]" name="paperDoctorRate" value="${tutor.paperDoctorRate}" style="width:60px"> %<span style="color:red;">&#12288;说明：指上一年度所培养研究生毕业论文盲审首次送申通过率</span>
                                    </c:if>
                                    <c:if test="${!recruitFlag && param.role ne 'xwk'}">${tutor.paperMasterRate} % / ${tutor.paperDoctorRate} %</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">培养研究生论文获奖情况：</td>
                                <td style="padding:2px 0px 2px 10px;">
                                    <c:if test="${recruitFlag || param.role eq 'xwk'}">
                                        <textarea class="validate[required]" name="pyyjsAwardDescribe" style="width:600px;height:100px;" onchange="if(value.length>1000) value=value.substr(0,1000)">${tutor.pyyjsAwardDescribe}</textarea>
                                    </c:if>
                                    <c:if test="${!recruitFlag && param.role ne 'xwk'}">${tutor.pyyjsAwardDescribe}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">培养研究生其他获奖情况：</td>
                                <td style="padding:2px 0px 2px 10px;">
                                    <c:if test="${recruitFlag || param.role eq 'xwk'}">
                                        <textarea class="validate[required]" name="pyyjsOtherDescribe" style="width:600px;height:100px;" onchange="if(value.length>1000) value=value.substr(0,1000)">${tutor.pyyjsOtherDescribe}</textarea>
                                    </c:if>
                                    <c:if test="${!recruitFlag && param.role ne 'xwk'}">${tutor.pyyjsOtherDescribe}</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="ltd">是否愿意带同等学力学生：</td>
                                <td style="padding:2px 0px 2px 10px;">
                                    <c:if test="${recruitFlag || param.role eq 'xwk'}">
                                        <input type="radio" name="leadStuFlag" value="Y" ${tutor.leadStuFlag eq 'Y'?'checked':''} class="validate[required]">是
                                        <input type="radio" name="leadStuFlag" value="N" ${tutor.leadStuFlag eq 'N'?'checked':''} class="validate[required]">否
                                    </c:if>
                                    <c:if test="${!recruitFlag && param.role ne 'xwk'}">${tutor.leadStuFlag eq 'Y'?'是':''}${tutor.leadStuFlag eq 'N'?'否':''}</c:if>
                                </td>
                            </tr>
                        </table>
                        <c:if test="${recruitFlag}">
                            <div style="text-align:center;"><input type="button" onclick="saveTutor(this)" class="search" value="提交招生信息"></div>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>