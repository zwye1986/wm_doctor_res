<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
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
<script type="text/javascript" src="<s:url value='/js/jsres/jquery-1.11.3.min.js'/>"></script>
<script src="<s:url value='/js/jsres/screenshot/html2canvas.min.js'/>"></script>
<script src="<s:url value='/js/jsres/screenshot/canvas2image.js'/>"></script>
<script src="<s:url value='/js/jsres/jszyCanvasPutImg.js'/>"></script>
<script type="text/javascript">
    var time ="";
    <c:if test="${not empty doctor.sendCertificateTime}">
    time = '${doctor.sendCertificateTime}';
    </c:if>
    <c:if test="${ empty doctor.sendCertificateTime}">
    time = '${nowDate}';
    </c:if>
    var map={
        "phyAss":{
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/jsres/phyAss/certificateNew.jpg'/>",
                    args: [0, 0, 1754, 1240, 0, 0, 1754, 1240]
                },
                {
                    //人物照片
                    url: <c:if test="${empty userFilePath}">
                        "<s:url value='/css/skin/up-pic.jpg'/>",
                    </c:if>
                    <c:if test="${not empty userFilePath}">
                    "${userFilePath}",
                    </c:if>
                    args: [370, 472, 209, 314]
                }
                ,
                {
                    //红章
                    url: "${filePath}/",
                    args: [1240, 900, 314, 209]
                }
            ],
            texts2:{
                startX: 950,//每一行文字开始的X
                endX: 1200,//每一行文字结束的X
                x: 950,
                y: 450,
                width: 650,
                height: 55,
                hj: 10,
                items:[
                    {
                        //姓名
                        text: '    ${doctor.doctorName}',
                        style: '36px 微软雅黑',
                        width:36
                    },
                    {
                        text: ' 同志于 ',
                        style: '36px 宋体',
                        width:36
                    },
                    {
                        //开始培训时间
                        text: '${plan.planStartTime}',
                        style: '36px 微软雅黑',
                        width:36
                    },
                    {
                        text: ' 参加江苏省医师协会毕业后医学教育部组织的',
                        style: '36px 宋体',
                        width:36
                    },
                    {
                        //培训专业
                        text: ' ${pdfn:sub(plan.planContent,0,7)}',
                        style: '36px 微软雅黑',
                        width:36
                    },
                    {
                        //培训专业
                        text: '${pdfn:sub(plan.planContent,7,14)}',
                        style: '36px 微软雅黑',
                        width:36
                    },
                    {
                        //培训专业
                        text: '${pdfn:sub(plan.planContent,14,conLen)}',
                        style: '36px 微软雅黑',
                        width:36
                    },
                    {
                        text: ' 专业师资培训班，修完培训规定的',
                        style: '36px 宋体',
                        width:36
                    },
                    {
                        text: '全部课程，经考核合格，特发此证。',
                        style: '36px 宋体',
                        width:36
                    }
                ]
            },
            texts: [
                {
                    //证书编号
                    text: '${pdfn:completeNoSplit(doctor.certificateNo)}',
                    x: 420,
                    y: 1010,
                    style: '36px 微软雅黑',
                    height: 30,
                    rowSize: 20
                },
                {
                    //日期
                    text: time,
                    x: 1250,
                    y: 1010,
                    height: 28,
                    style: '36px 微软雅黑',
                    rowSize: 13
                }
            ],
            canattr: {
                id: 'downId',
                width: 1754,
                height: 1240
            }
        }
    };
    $(document).ready(function(){
        var options=map['phyAss'];
        if(options!=undefined){
            $('#testDiv').drawCertificate(options);
        }

    });
    function  down(name)
    {
        name = name + ".png";
        var url="<s:url value='/jsres/phyAss/downCertificate' />?recordFlow=${doctor.recordFlow}";
        $('#testDiv canvas').downCanvasImg(name,url);
    }
</script>
<div id="all" style="width: auto;text-align: center" onResize="Resize()" onLoad="Resize()">
    <div id="testDiv" style="height: 1300px;border: 0px solid;padding-top: 30px;" onResize="Resize()" onLoad="Resize()">
        <div align="right">
            <input type="button" id="submitBtn" class="btn_green" onclick="down('${doctor.doctorName}${doctor.certificateNo}');" value="下&#12288;载"/>&nbsp;
        </div>
    </div>
</div>

