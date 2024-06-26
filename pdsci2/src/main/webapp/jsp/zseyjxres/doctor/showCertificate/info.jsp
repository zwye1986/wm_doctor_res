<jsp:include page="/jsp/zseyjxres/htmlhead-gzzyjxres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script src="<s:url value='/js/jsres/canvasPutImg.js'/>"></script>
<script type="text/javascript">
    var map = {
        //进修结业证书样式
        "furtherEducation": {
            <%--<c:set var="thisTime" value="${sysCfgMap['res_certificateDate']}"></c:set>--%>
            <%--<c:if test="${empty thisTime }">--%>
            <%--<c:set var="thisTime" value="${date}"></c:set>--%>
            <%--</c:if>--%>
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/zseyjxres/daochu/jinxiujieyezhengshu.png'/>",
                    args: [0, 0, 1140, 754, 0, 0, 1140, 754]
                },
                {
                    //人物照片
                    url: <c:if test="${empty  sysUser.userHeadImg }">
                            "<s:url value='/css/skin/up-pic.jpg'/>",
                    </c:if>
                    <c:if test="${not empty sysUser.userHeadImg }">
                    "${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
                    </c:if>
                    args: [163, 267, 106, 150]
                }
            ],
            texts: [
                {
                    //姓名
                    text: '${sysUser.userName}',
                    x: 335,
                    y: 290,
                    style: '18px Arial',
                    rowSize: 5
                },
                {
                    //性别
                    text:'${sysUser.sexName}',
                    style: '18px Arial'
                },
                {
                    //出生年份
                    text:'${fn:substring(sysUser.userBirthday,0 , 4)}',
                    style: '18px Arial'
                },
                {
                    //出生月份
                    text:'${fn:substring(sysUser.userBirthday,5 , 7)}',
                    style: '18px Arial'
                },
                {
                    //出生日期
                    text:'${fn:substring(sysUser.userBirthday,8 , 10)}',
                    style: '18px Arial'
                },{
                    //身份证号
                    text:'${sysUser.idNo}',
                    style: '18px Arial'
                },
                {
                    //开始进修年份
                    text: '${fn:substring(regDate,0,4)}',
                    x: 510,
                    y: 290,
                    style: '18px Arial'
                },
                {
                    //开始进修月份
                    text: '${fn:substring(regDate,5,7)}',
                    x: 620,
                    y: 290,
                    style: '18px Arial'
                },
                {
                    //开始进修日期
                    text: '${fn:substring(regDate,8,10)}',
                    style: '18px Arial'
                },
                {
                    //结束进修年份
                    text: '${fn:substring(finishDate,0,4)}',
                    x: 740,
                    y: 290,
                    style: '18px Arial'
                },
                {
                    //结束进修月份
                    text: '${fn:substring(finishDate,5,7)}',
                    x: 850,
                    y: 290,
                    style: '18px Arial'
                },
                {
                    //结束进修日期
                    text: '${fn:substring(finishDate,8,10)}',
                    style: '18px Arial'
                },
                {
                    //基地名称
                    text: '${orgName}',
                    x: 660,
                    y: 580,
                    style: '25px Arial',
                    rowSize: 13,
                    weight:900
                },
                {
                    //培训专业
                    text: '${speName}',
                    x: 375,
                    y: 350,
                    style: '18px Arial',
                    witdh: 212
                },
                {
                    text: '${fn:substring(thisTime,0,4)}',
                    x: 665,
                    y: 625,
                    style: '25px Arial'
                },
                {
                    text: '${fn:substring(thisTime,4,6)}',
                    x: 775,
                    y: 625,
                    style: '25px Arial'
                },
                {
                    text: '${fn:substring(thisTime,6,8)}',
                    x: 865,
                    y: 625,
                    style: '25px Arial'
                }
            ],
            canattr: {
                id: 'downId',
                width: 1140,
                height: 750
            }
        },
    };
    $(document).ready(function () {
        var options = map['${completeNo}'];
        console.log(options);
        if (options != undefined) {
            $('#testDiv').drawCertificate(options);
            console.log(11);
        }

    });
    function down(name) {
        name = name + ".jpg";
        $('#testDiv canvas').downCanvasImg(name);
    }
</script>
<div id="all" style="width: auto;" align="center" onResize="Resize()" onLoad="Resize()">
    <%--进修结业证书--%>
    <%--<c:if test="${completeNo eq 'furtherEducation'}">--%>
        <div id="testDiv" style="width: 1200px;height: 816px;border: 0px solid;" onResize="Resize()" onLoad="Resize()">
            <div align="right" style="">
                <%--<c:if test="${flag ne 'view'}">--%>
                    <input type="button" id="submitBtn" class="btn_blue"
                           onclick="down('${sysUser.userName}');" value="打印"/>&nbsp;
                <%--</c:if>--%>
            </div>
        </div>
    <%--</c:if>--%>
</div>

