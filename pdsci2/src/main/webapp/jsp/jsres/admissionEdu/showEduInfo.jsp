<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style>
        .processInfo{
            width: 95%;
            margin-top: 10px;
            margin-left: 2.5%;
            min-height: 440px;
            max-height: 440px;
            border: 1px solid #e7e7eb;
            overflow: auto
        }
    </style>
    <script type="text/javascript">

        $(document).ready(function(){
            if ("${param.tagId}" != "") {
                $("#${param.tagId}").click();
            } else {
                $("li a:first").click();
            }
        });

        function selectTag(selfObj,index) {
            var selLi = $(selfObj);
            $(".tab_select").removeClass("tab_select");
            selLi.removeClass("tab");
            selLi.addClass("tab_select");
            $(".processInfo").hide();
            $("#"+index).show();
        }
    </script>
</head>

<div class="main_hd">
    <div class="title_tab" style="margin-top: 0;">
        <ul id="tags">
            <li class="tab" onclick="selectTag(this,'index1');">
                <a id="">科室简介</a>
            </li>
            <li class="tab" onclick="selectTag(this,'index2');">
               <a id="">科室成员简介</a>
            </li>
            <li class="tab" onclick="selectTag(this,'index3');">
                <a id="">工作环境介绍</a>
            </li>
            <li class="tab" onclick="selectTag(this,'index4');">
                <a id="">诊治范围</a>
            </li>
            <li class="tab" onclick="selectTag(this,'index5');">
                <a id="">科室学术及活动安排</a>
            </li>
            <li class="tab" onclick="selectTag(this,'index6');">
                <a id="">科室考勤制度</a>
            </li>
            <li class="tab" onclick="selectTag(this,'index7');">
                <a id="">入科教育文档</a>
            </li>
        </ul>
    </div>
</div>
<div class="main_bd">
    <div class="processInfo" id="index1" style="padding-top: 10px;" >
        <c:if test="${empty info.deptBriefing}">
            暂无科室简介
        </c:if>
        ${info.deptBriefing}
    </div>
    <div class="processInfo" id="index2" >
        <div>
            <table class="grid" style="font-size: 13px;width: 100%;">
                <thead>
                <tr>
                    <th>职务</th>
                    <th>姓名</th>
                    <th>联系方式</th>
                    <th>职称</th>
                </tr>
                </thead>
                <tbody id="memberList">
                <c:if test="${not empty members}">
                    <c:forEach items="${members}" var="member">
                        <tr>
                            <td>
                                <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                    <c:if test="${member.memberPostId eq post.dictId }">${post.dictName}</c:if>
                                </c:forEach>
                            </td>
                            <td>${member.memberName}</td>
                            <td>${member.memberPhone}</td>
                            <td>
                                <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                    <c:if test="${member.memberTitleId eq title.dictId }">${title.dictName}</c:if>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty members}">
                    <tr>
                        <td colspan="4">
                            暂未添加人员信息
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <div style="margin-top: 10px;">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th style="text-align: left;" >科室架构信息
                    </th>
                </tr>
                <tr>
                    <td style="text-align: left;" >
                        <c:if test="${empty info.deptFramework}">
                            暂无科室架构信息
                        </c:if>
                        ${info.deptFramework}
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="processInfo" id="index3"  style="padding-top: 10px;">
        <c:if test="${empty info.workEnvironment}">
            暂无工作环境介绍
        </c:if>
        ${info.workEnvironment}
    </div>
    <div class="processInfo" id="index4"  style="padding-top: 10px;">

        <c:if test="${empty info.workScope}">
            暂无诊治范围
        </c:if>
        ${info.workScope}
    </div>
    <div class="processInfo" id="index5" >
        <table class="grid" style="font-size: 13px;width: 100%;">
            <thead>
            <tr>
                <th align="center">时间</th>
                <th align="center">地点</th>
                <th align="center">内容</th>
            </tr>
            </thead>
            <tbody id="teaching">
            <tr>
                <td>周一</td>
                <td>${arrangeMap['address1']}</td>
                <td>${arrangeMap['content1']}</td>
            </tr>
            <tr>
                <td>周二</td>
                <td>${arrangeMap['address2']}</td>
                <td>${arrangeMap['content2']}</td>
            </tr>
            <tr>
                <td>周三</td>
                <td>${arrangeMap['address3']}</td>
                <td>${arrangeMap['content3']}</td>
            </tr>
            <tr>
                <td>周四</td>
                <td>${arrangeMap['address4']}</td>
                <td>${arrangeMap['content4']}</td>
            </tr>
            <tr>
                <td>周五</td>
                <td>${arrangeMap['address5']}</td>
                <td>${arrangeMap['content5']}</td>
            </tr>
            <tr>
                <td>周六</td>
                <td>${arrangeMap['address6']}</td>
                <td>${arrangeMap['content6']}</td>
            </tr>
            <tr>
                <td>周日</td>
                <td>${arrangeMap['address7']}</td>
                <td>${arrangeMap['content7']}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="processInfo" id="index6"  style="padding-top: 10px;">
        <c:if test="${empty info.attendanceInfo}">
            暂无科室考勤制度
        </c:if>
        ${info.attendanceInfo}
    </div>
    <div class="processInfo" id="index7" >

        <table id="files" class="grid" style="font-size: 13px;width: 100%;">
            <tr>
                <th style="width: 90%;">附件名称</th>
                <th>操作</th>
            </tr>
            <c:if test="${not empty files}">
                <c:forEach items="${files}" var="file">
                    <tr>
                        <td style="text-align: left;">
                            <a href="<s:url value='/jsres/kzr/fileDown'/>?fileFlow=${file.fileFlow}">${file.fileName}</a>
                        </td>
                        <td>
                            <%--<a href="<s:url value='/jsres/kzr/fileDown'/>?fileFlow=${file.fileFlow}" target="_blank">查看</a>--%>
                            <a href="<s:url value='/jsres/kzr/fileDown'/>?fileFlow=${file.fileFlow}">下载</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty files}">
                <tr>
                    <td style="text-align: center;">
                        暂无附件
                    </td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
</div>