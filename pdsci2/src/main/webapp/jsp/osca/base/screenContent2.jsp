
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <style type="text/css">
        .xllistNew th{font-size:40px;line-height:60px;border: 0px;background: none;color: white;
            font-weight: normal;border: white 1px solid;padding-left: 5px}
        .xllistNew td{font-size:30px;line-height:48px;border: 0px;background: none;color: white;
            border: white 1px solid;padding-left: 5px}
    </style>
    <script type="text/javascript">
        $(function(){
            for(var i=0;i<$(".names").length;i++){
                var content = $($(".names")[i]).text();
                var reg = /,/g;
                var newContent=content.replace(reg,"，");
                $($(".names")[i]).text(newContent);
            }
            setInterval(carousel,8000);
        });
        function carousel(){
            var page = $("#currentPage").val();
            if(page != "" && !isNaN(page) && Math.ceil(${total/10}) > page){
                page = parseInt(page) + 1;
            }else{
                page = 1;
            }
            $("#currentPage").val(page);
            $("#screenForm").submit();
        }
    </script>
</head>
<body id="initCont" background="<s:url value='/jsp/inx/osce/images/${applicationScope.sysCfgMap["osce_screenBg"]}.png'/>">
<div class="mainright">
    <div class="content" style="background: none;">
        <div style="text-align: center;margin-top: 30px;margin-bottom: 20px;">
            <label style="font-size: 44px;color: white;">${orgName}临床技能考核房间动态</label>
        </div>
        <div style="text-align: center;width:100%;height:80%;">
            <form id="screenForm" method="get">
                <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
                <input type="hidden" name="clinicalFlow" value="${clinicalFlow}">
            </form>
            <table class="xllistNew" style="width:100%;height:90%;border: white 3px solid;">
                <tr>
                    <th style="width:15%;">考站名称</th>
                    <th style="width:15%;">房间号</th>
                    <th style="width:35%;">考官</th>
                    <th style="width:35%;">当前考核学员</th>
                </tr>
                <c:forEach items="${resultMapList}" var="item">
                    <tr>
                        <td>${item["STATION_NAME"]}</td>
                        <td>${item["ROOM_NAME"]}</td>
                        <td class="names">${item["PARTNER_NAME"]}</td>
                        <td class="names">${item["DOCTOR_NAME"]}</td>
                    </tr>
                </c:forEach>
            </table>
        <div style="text-align:center;">
            <label style="font-size: 28px;color: white;line-height: 100px;">技术支持：南京品德网络信息技术有限公司</label>
        </div>
        </div>
    </div>
</div>
</body>
</html>