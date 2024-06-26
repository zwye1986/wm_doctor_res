<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
    <jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jsres/jquery-1.11.3.min.js'/>"></script>
<script src="<s:url value='/js/jsres/screenshot/html2canvas.min.js'/>"></script>
<script src="<s:url value='/js/jsres/screenshot/canvas2image.js'/>"></script>
<script src="<s:url value='/js/jsres/jszyCanvasPutImg.js'/>"></script>
<script type="text/javascript">
    var map = {
        //结业证书样式
        "certificate": {
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/jszy/graduationManager/images/certificate3.jpg'/>",
                    args: [0, 0, 1240, 1754, 0, 0, 1240, 1754]
                },
                {
                    //人物照片
                    url: <c:if test="${empty  sysUser.userHeadImg }">
                            "<s:url value='/css/skin/up-pic.jpg'/>",
                    </c:if>
                    <c:if test="${not empty sysUser.userHeadImg }">
                    "${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
                    </c:if>
                    args: [522, 472, 209, 314]
                },
                {
                    //培训基地院长签字
                    url: "<s:url value='/jsp/jszy/graduationManager/images/'/>" + '${info.orgCode}' + ".png",
                    args: [280, 1400, 300, 180]
                }
                <%--,--%>
                <%--{--%>
                    <%--//中药局红章--%>
                    <%--url: "<s:url value='/jsp/jszy/graduationManager/images/seal.png'/>",--%>
                    <%--args: [760, 1295, 248, 248]--%>
                <%--}--%>
            ],
            texts2:{
                startX: 190,//每一行文字开始的X
                endX: 1060,//每一行文字结束的X
                x: 280,
                y: 940,
                width: 870,
                height: 55,
                hj: 10,
                items:[
                    {
                        //姓名
                        text: '${info.doctorName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '于',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //开始培训时间
                        text: '${info.trainingStartTime}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '至',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //结束培训时间
                        text: '${info.trainingEndTime}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '期间 ，在',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //基地名称
                        text: '${info.nationalBaseName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '培训基地参加 ',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //培训专业
                        text: '${info.trainingTypeName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: ' 专业住院医师规范化培训，经考核合格，特发此证。',
                        style: '41px 微软雅黑',
                        width:41
                    }
                ]
            },
            texts: [
                {
                    //证书编号
                    text: '${pdfn:completeNoSplit(info.certificateNumber)}',
                    x: 552,
                    y: 850,
                    style: '26px 微软雅黑',
                    height: 30,
                    rowSize: 20
                },
                {
                    //日期
                    <c:if test="${info.graduationYear eq '2017'}">
                    text: '${info.graduationYear}年 12月 31日',
                    </c:if>
                    <c:if test="${info.graduationYear eq '2018'}">
                    text: '${info.graduationYear}年 7 月 30日',
                    </c:if>
                    x: 760,
                    y: 1515,
                    height: 28,
                    style: '28px 楷体',
                    rowSize: 13
                },
                {
                    //流水号
                    text: '${info.certificateFlow}',
                    x: 290,
                    y: 1680,
                    style: '20px 华文中宋'
                }
            ],
            canattr: {
                id: 'downId',
                width: 1240,
                height: 1754
            }
        },
        "certificate2": {
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/jszy/graduationManager/images/certificate4.jpg'/>",
                    args: [0, 0, 2479, 3508, 0, 0, 2479, 3508]
                },
                {
                    //人物照片
                    url: <c:if test="${empty  sysUser.userHeadImg }">
                            "<s:url value='/css/skin/up-pic.jpg'/>",
                    </c:if>
                    <c:if test="${not empty sysUser.userHeadImg }">
                    "${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
                    </c:if>
                    args: [1027, 960, 420, 581]
                },
                {
                    //培训基地院长签字
                    url: "<s:url value='/jsp/jszy/graduationManager/images/'/>" + '${info.orgCode}' + ".png",
                    args: [688, 2800, 420, 300]
                },
                {
                    //中药局红章
                    url: "<s:url value='/jsp/jszy/graduationManager/images/seal.png'/>",
                    args: [1500, 2660, 548, 548]
                }
            ],
            texts2:{
                    startX: 380,//每一行文字开始的X
                    endX: 2120,//每一行文字结束的X
                    x: 560,
                    y: 1880,
                    width: 1740,
                    height: 110,
                    hj: 25,
                    items:[
                        {
                            //姓名
                            text: '${info.doctorName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '于',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //开始培训时间
                            text: '${info.trainingStartTime}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '至',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //结束培训时间
                            text: '${info.trainingEndTime}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '期间 ，在',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //基地名称
                            text: '${info.nationalBaseName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '培训基地参加 ',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //培训专业
                            text: '${info.trainingTypeName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: ' 专业住院医师规范化培训，经考核合格，特发此证。',
                            style: '82px 微软雅黑',
                            width:82
                        }
                    ]
                },
                texts: [
                    {
                        //证书编号
                        text: '${pdfn:completeNoSplit(info.certificateNumber)}',
                        x: 1100,
                        y: 1700,
                        style: '60px 微软雅黑',
                        height: 60,
                        rowSize: 20
                    },
                    {
                        //日期
                        <c:if test="${info.graduationYear eq '2017'}">
                        text: '${info.graduationYear}年 12月 31日',
                        </c:if>
                        <c:if test="${info.graduationYear eq '2018'}">
                        text: '${info.graduationYear}年 7 月 30日',
                        </c:if>
                        x: 1600,
                        y: 3100,
                        height: 50,
                        style: '58px 楷体',
                        rowSize: 13
                    },
                    {
                        //流水号
                        text: '${info.certificateFlow}',
                        x: 600,
                        y: 3362,
                        style: '44px 华文中宋'
                    }
                ],
            canattr: {
                id: 'downId2',
                width: 2479,
                height: 3508
            }
        },
        //结业证书样式
        "zlcertificate": {
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/jszy/graduationManager/images/zlcertificate3.png'/>",
                    args: [0, 0, 1240, 1754, 0, 0, 1240, 1754]
                },
                {
                    //人物照片
                    url: <c:if test="${empty  sysUser.userHeadImg }">
                            "<s:url value='/css/skin/up-pic.jpg'/>",
                    </c:if>
                    <c:if test="${not empty sysUser.userHeadImg }">
                    "${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
                    </c:if>
                    args: [522, 472, 209, 314]
                },
                {
                    //培训基地院长签字
                    url: "<s:url value='/jsp/jszy/graduationManager/images/'/>" + '${info.orgCode}' + ".png",
                    args: [280, 1400, 300, 180]
                }
                <%--,--%>
                <%--{--%>
                    <%--//中药局红章--%>
                    <%--url: "<s:url value='/jsp/jszy/graduationManager/images/seal.png'/>",--%>
                    <%--args: [760, 1295, 248, 248]--%>
                <%--}--%>
            ],
            texts2:{
                startX: 190,//每一行文字开始的X
                endX: 1060,//每一行文字结束的X
                x: 280,
                y: 940,
                width: 870,
                height: 55,
                hj: 10,
                items:[
                    {
                        //姓名
                        text: '${info.doctorName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '于',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //开始培训时间
                        text: '${info.trainingStartTime}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '至',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //结束培训时间
                        text: '${info.trainingEndTime}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '期间 ，在',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //基地名称
                        text: '${info.nationalBaseName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '培训基地参加 ',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //培训专业
                        text: '${info.trainingTypeName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: ' 专业住院医师规范化培训，经考核合格，特发此证。',
                        style: '41px 微软雅黑',
                        width:41
                    }
                ]
            },
            texts: [
                {
                    //证书编号
                    text: '${info.certificateNumber}',
                    x: 552,
                    y: 850,
                    style: '26px 微软雅黑',
                    height: 30,
                    rowSize: 20
                },
                {
                    //日期
                    <c:if test="${info.graduationYear eq '2017'}">
                    text: '${info.graduationYear}年 12月 31日',
                    </c:if>
                    <c:if test="${info.graduationYear eq '2018'}">
                    text: '${info.graduationYear}年 7 月 30日',
                    </c:if>
                    x: 760,
                    y: 1515,
                    height: 28,
                    style: '28px 楷体',
                    rowSize: 13
                },
                {
                    //流水号
                    text: '${info.certificateFlow}',
                    x: 290,
                    y: 1680,
                    style: '20px 华文中宋'
                }
            ],
            canattr: {
                id: 'downId',
                width: 1240,
                height: 1754
            }
        },
        "zlcertificate2": {
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/jszy/graduationManager/images/zlcertificate4.png'/>",
                    args: [0, 0, 2479, 3508, 0, 0, 2479, 3508]
                },
                {
                    //人物照片
                    url: <c:if test="${empty  sysUser.userHeadImg }">
                            "<s:url value='/css/skin/up-pic.jpg'/>",
                    </c:if>
                    <c:if test="${not empty sysUser.userHeadImg }">
                    "${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
                    </c:if>
                    args: [1027, 960, 420, 581]
                },
                {
                    //培训基地院长签字
                    url: "<s:url value='/jsp/jszy/graduationManager/images/'/>" + '${info.orgCode}' + ".png",
                    args: [688, 2800, 420, 300]
                },
                {
                    //中药局红章
                    url: "<s:url value='/jsp/jszy/graduationManager/images/seal.png'/>",
                    args: [1500, 2660, 548, 548]
                }
            ],
            texts2:{
                    startX: 380,//每一行文字开始的X
                    endX: 2120,//每一行文字结束的X
                    x: 560,
                    y: 1880,
                    width: 1740,
                    height: 110,
                    hj: 25,
                    items:[
                        {
                            //姓名
                            text: '${info.doctorName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '于',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //开始培训时间
                            text: '${info.trainingStartTime}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '至',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //结束培训时间
                            text: '${info.trainingEndTime}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '期间 ，在',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //基地名称
                            text: '${info.nationalBaseName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '培训基地参加 ',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //培训专业
                            text: '${info.trainingTypeName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: ' 专业住院医师规范化培训，经考核合格，特发此证。',
                            style: '82px 微软雅黑',
                            width:82
                        }
                    ]
                },
                texts: [
                    {
                        //证书编号
                        text: '${info.certificateNumber}',
                        x: 1100,
                        y: 1700,
                        style: '60px 微软雅黑',
                        height: 60,
                        rowSize: 20
                    },
                    {
                        //日期
                        <c:if test="${info.graduationYear eq '2017'}">
                        text: '${info.graduationYear}年 12月 31日',
                        </c:if>
                        <c:if test="${info.graduationYear eq '2018'}">
                        text: '${info.graduationYear}年 7 月 30日',
                        </c:if>
                        x: 1600,
                        y: 3100,
                        height: 50,
                        style: '58px 楷体',
                        rowSize: 13
                    },
                    {
                        //流水号
                        text: '${info.certificateFlow}',
                        x: 600,
                        y: 3362,
                        style: '44px 华文中宋'
                    }
                ],
            canattr: {
                id: 'downId2',
                width: 2479,
                height: 3508
            }
        },
    };
    var map2 = {
        //结业证书样式
        "certificate": {
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/jszy/graduationManager/images/certificate3.jpg'/>",
                    args: [0, 0, 1240, 1754, 0, 0, 1240, 1754]
                },
                {
                    //人物照片
                    url: <c:if test="${empty  sysUser.userHeadImg }">
                            "<s:url value='/css/skin/up-pic.jpg'/>",
                    </c:if>
                    <c:if test="${not empty sysUser.userHeadImg }">
                    "${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
                    </c:if>
                    args: [522, 472, 209, 314]
                },
                {
                    //培训基地院长签字
                    url: "<s:url value='/jsp/jszy/graduationManager/images/'/>" + '${info.orgCode}' + ".png",
                    args: [280, 1400, 300, 180]
                }
                <%--,--%>
                <%--{--%>
                    <%--//中药局红章--%>
                    <%--url: "<s:url value='/jsp/jszy/graduationManager/images/seal2.png'/>",--%>
                    <%--args: [760, 1295, 248, 248]--%>
                <%--}--%>
            ],
            texts2:{
                startX: 190,//每一行文字开始的X
                endX: 1060,//每一行文字结束的X
                x: 280,
                y: 940,
                width: 870,
                height: 55,
                hj: 10,
                items:[
                    {
                        //姓名
                        text: '${info.doctorName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '于',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //开始培训时间
                        text: '${info.trainingStartTime}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '至',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //结束培训时间
                        text: '${info.trainingEndTime}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '期间 ，在',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //基地名称
                        text: '${info.nationalBaseName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '培训基地参加 ',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //培训专业
                        text: '${info.trainingTypeName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: ' 专业住院医师规范化培训，经考核合格，特发此证。',
                        style: '41px 微软雅黑',
                        width:41
                    }
                ]
            },
            texts: [
                {
                    //证书编号
                    text: '${pdfn:completeNoSplit(info.certificateNumber)}',
                    x: 552,
                    y: 850,
                    style: '26px 微软雅黑',
                    height: 30,
                    rowSize: 20
                },
                {
                    //日期
                    <c:if test="${info.graduationYear eq '2017'}">
                    text: '${info.graduationYear}年 12月 31日',
                    </c:if>
                    <c:if test="${info.graduationYear eq '2018'}">
                    text: '${info.graduationYear}年 12月 29日',
                    </c:if>
                    x: 760,
                    y: 1515,
                    height: 28,
                    style: '28px 楷体',
                    rowSize: 13
                },
                {
                    //流水号
                    text: '${info.certificateFlow}',
                    x: 290,
                    y: 1680,
                    style: '20px 华文中宋'
                }
            ],
            canattr: {
                id: 'downId',
                width: 1240,
                height: 1754
            }
        },
        "certificate2": {
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/jszy/graduationManager/images/certificate4.jpg'/>",
                    args: [0, 0, 2479, 3508, 0, 0, 2479, 3508]
                },
                {
                    //人物照片
                    url: <c:if test="${empty  sysUser.userHeadImg }">
                            "<s:url value='/css/skin/up-pic.jpg'/>",
                    </c:if>
                    <c:if test="${not empty sysUser.userHeadImg }">
                    "${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
                    </c:if>
                    args: [1027, 960, 420, 581]
                },
                {
                    //培训基地院长签字
                    url: "<s:url value='/jsp/jszy/graduationManager/images/'/>" + '${info.orgCode}' + ".png",
                    args: [688, 2800, 420, 300]
                },
                {
                    //中药局红章
                    url: "<s:url value='/jsp/jszy/graduationManager/images/seal2.png'/>",
                    args: [1500, 2660, 548, 548]
                }
            ],
            texts2:{
                    startX: 380,//每一行文字开始的X
                    endX: 2120,//每一行文字结束的X
                    x: 560,
                    y: 1880,
                    width: 1740,
                    height: 110,
                    hj: 25,
                    items:[
                        {
                            //姓名
                            text: '${info.doctorName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '于',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //开始培训时间
                            text: '${info.trainingStartTime}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '至',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //结束培训时间
                            text: '${info.trainingEndTime}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '期间 ，在',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //基地名称
                            text: '${info.nationalBaseName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '培训基地参加 ',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //培训专业
                            text: '${info.trainingTypeName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: ' 专业住院医师规范化培训，经考核合格，特发此证。',
                            style: '82px 微软雅黑',
                            width:82
                        }
                    ]
                },
                texts: [
                    {
                        //证书编号
                        text: '${pdfn:completeNoSplit(info.certificateNumber)}',
                        x: 1100,
                        y: 1700,
                        style: '60px 微软雅黑',
                        height: 60,
                        rowSize: 20
                    },
                    {
                        //日期
                        <c:if test="${info.graduationYear eq '2017'}">
                        text: '${info.graduationYear}年 12月 31日',
                        </c:if>
                        <c:if test="${info.graduationYear eq '2018'}">
                        text: '${info.graduationYear}年 12月 29日',
                        </c:if>
                        x: 1600,
                        y: 3100,
                        height: 50,
                        style: '58px 楷体',
                        rowSize: 13
                    },
                    {
                        //流水号
                        text: '${info.certificateFlow}',
                        x: 600,
                        y: 3362,
                        style: '44px 华文中宋'
                    }
                ],
            canattr: {
                id: 'downId2',
                width: 2479,
                height: 3508
            }
        },
        //结业证书样式
        "zlcertificate": {
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/jszy/graduationManager/images/zlcertificate3.png'/>",
                    args: [0, 0, 1240, 1754, 0, 0, 1240, 1754]
                },
                {
                    //人物照片
                    url: <c:if test="${empty  sysUser.userHeadImg }">
                            "<s:url value='/css/skin/up-pic.jpg'/>",
                    </c:if>
                    <c:if test="${not empty sysUser.userHeadImg }">
                    "${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
                    </c:if>
                    args: [522, 472, 209, 314]
                },
                {
                    //培训基地院长签字
                    url: "<s:url value='/jsp/jszy/graduationManager/images/'/>" + '${info.orgCode}' + ".png",
                    args: [280, 1400, 300, 180]
                }
                <%--,--%>
                <%--{--%>
                    <%--//中药局红章--%>
                    <%--url: "<s:url value='/jsp/jszy/graduationManager/images/seal2.png'/>",--%>
                    <%--args: [760, 1295, 248, 248]--%>
                <%--}--%>
            ],
            texts2:{
                startX: 190,//每一行文字开始的X
                endX: 1060,//每一行文字结束的X
                x: 280,
                y: 940,
                width: 870,
                height: 55,
                hj: 10,
                items:[
                    {
                        //姓名
                        text: '${info.doctorName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '于',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //开始培训时间
                        text: '${info.trainingStartTime}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '至',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //结束培训时间
                        text: '${info.trainingEndTime}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '期间 ，在',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //基地名称
                        text: '${info.nationalBaseName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '培训基地参加 ',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //培训专业
                        text: '${info.trainingTypeName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: ' 专业住院医师规范化培训，经考核合格，特发此证。',
                        style: '41px 微软雅黑',
                        width:41
                    }
                ]
            },
            texts: [
                {
                    //证书编号
                    text: '${info.certificateNumber}',
                    x: 552,
                    y: 850,
                    style: '26px 微软雅黑',
                    height: 30,
                    rowSize: 20
                },
                {
                    //日期
                    <c:if test="${info.graduationYear eq '2017'}">
                    text: '${info.graduationYear}年 12月 31日',
                    </c:if>
                    <c:if test="${info.graduationYear eq '2018'}">
                    text: '${info.graduationYear}年 12月 29日',
                    </c:if>
                    x: 760,
                    y: 1515,
                    height: 28,
                    style: '28px 楷体',
                    rowSize: 13
                },
                {
                    //流水号
                    text: '${info.certificateFlow}',
                    x: 290,
                    y: 1680,
                    style: '20px 华文中宋'
                }
            ],
            canattr: {
                id: 'downId',
                width: 1240,
                height: 1754
            }
        },
        "zlcertificate2": {
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/jszy/graduationManager/images/zlcertificate4.png'/>",
                    args: [0, 0, 2479, 3508, 0, 0, 2479, 3508]
                },
                {
                    //人物照片
                    url: <c:if test="${empty  sysUser.userHeadImg }">
                            "<s:url value='/css/skin/up-pic.jpg'/>",
                    </c:if>
                    <c:if test="${not empty sysUser.userHeadImg }">
                    "${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
                    </c:if>
                    args: [1027, 960, 420, 581]
                },
                {
                    //培训基地院长签字
                    url: "<s:url value='/jsp/jszy/graduationManager/images/'/>" + '${info.orgCode}' + ".png",
                    args: [688, 2800, 420, 300]
                },
                {
                    //中药局红章
                    url: "<s:url value='/jsp/jszy/graduationManager/images/seal2.png'/>",
                    args: [1500, 2660, 548, 548]
                }
            ],
            texts2:{
                    startX: 380,//每一行文字开始的X
                    endX: 2120,//每一行文字结束的X
                    x: 560,
                    y: 1880,
                    width: 1740,
                    height: 110,
                    hj: 25,
                    items:[
                        {
                            //姓名
                            text: '${info.doctorName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '于',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //开始培训时间
                            text: '${info.trainingStartTime}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '至',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //结束培训时间
                            text: '${info.trainingEndTime}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '期间 ，在',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //基地名称
                            text: '${info.nationalBaseName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: '培训基地参加 ',
                            style: '82px 微软雅黑',
                            width:82
                        },
                        {
                            //培训专业
                            text: '${info.trainingTypeName}',
                            style: '80px 楷体',
                            width:80
                        },
                        {
                            text: ' 专业住院医师规范化培训，经考核合格，特发此证。',
                            style: '82px 微软雅黑',
                            width:82
                        }
                    ]
                },
                texts: [
                    {
                        //证书编号
                        text: '${info.certificateNumber}',
                        x: 1100,
                        y: 1700,
                        style: '60px 微软雅黑',
                        height: 60,
                        rowSize: 20
                    },
                    {
                        //日期
                        <c:if test="${info.graduationYear eq '2017'}">
                        text: '${info.graduationYear}年 12月 31日',
                        </c:if>
                        <c:if test="${info.graduationYear eq '2018'}">
                        text: '${info.graduationYear}年 12月 29日',
                        </c:if>
                        x: 1600,
                        y: 3100,
                        height: 50,
                        style: '58px 楷体',
                        rowSize: 13
                    },
                    {
                        //流水号
                        text: '${info.certificateFlow}',
                        x: 600,
                        y: 3362,
                        style: '44px 华文中宋'
                    }
                ],
            canattr: {
                id: 'downId2',
                width: 2479,
                height: 3508
            }
        },
    };
    $(document).ready(function () {
        var typeId="${info.trainingTypeId}";
        if("${info.createTime}">="20181225000000")
        {
            if("ChineseMedicine"==typeId||"TCMGeneral"==typeId){
                var options = map2['${certificate}'];
                if (options != undefined) {
                    $('#testDiv').drawCertificate(options);
                }
                var options2 = map2['${certificate}2'];
                if (options2 != undefined) {
                    $('#testDiv2').drawCertificate(options2);
                }
            }
            if("TCMAssiGeneral"==typeId){
                var options = map2['zl${certificate}'];
                if (options != undefined) {
                    $('#testDiv').drawCertificate(options);
                }
                var options2 = map2['zl${certificate}2'];
                if (options2 != undefined) {
                    $('#testDiv2').drawCertificate(options2);
                }
            }
        }else{
            if("ChineseMedicine"==typeId||"TCMGeneral"==typeId){
                var options = map['${certificate}'];
                if (options != undefined) {
                    $('#testDiv').drawCertificate(options);
                }
                var options2 = map['${certificate}2'];
                if (options2 != undefined) {
                    $('#testDiv2').drawCertificate(options2);
                }
            }
            if("TCMAssiGeneral"==typeId){
                var options = map['zl${certificate}'];
                if (options != undefined) {
                    $('#testDiv').drawCertificate(options);
                }
                var options2 = map['zl${certificate}2'];
                if (options2 != undefined) {
                    $('#testDiv2').drawCertificate(options2);
                }
            }
        }
    });
    function down(name) {
        name = name + ".png";
        var url="<s:url value='/jszy/graduationManager/showCertificate3' />?recordFlow=${info.recordFlow}&isGlobal=${param.isGlobal}";
        $('#testDiv2 canvas').downCanvasImg(name,url);
    }
</script>
<div id="all" style="width: auto;" align="center" onResize="Resize()" onLoad="Resize()">
    <%--结业证书--%>
    <%--<c:if test="${certificate eq 'certificate'}">--%>
        <div id="testDiv" style="width: 1240px;height: 1754px;border: 0px solid;" onResize="Resize()" onLoad="Resize()">
            <div align="right" style="">
                <c:if test="${param.isGlobal eq 'Y'}">
                    <input type="button" id="submitBtn" class="btn_blue"
                           onclick="down('${info.doctorName}');" value="下载"/>&nbsp;
                </c:if>
            </div>
        </div>
    <%--</c:if>--%>
    <%--<c:if test="${certificate eq 'certificate2'}">--%>
        <div id="testDiv2" style="width: 2479px;height: 3508px;border: 0px solid;display: none;" onResize="Resize()" onLoad="Resize()">
        </div>
    <%--</c:if>--%>
</div>
