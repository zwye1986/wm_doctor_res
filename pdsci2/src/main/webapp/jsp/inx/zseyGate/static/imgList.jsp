<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>中山大学孙逸仙纪念医院住院医师规范化培训管理平台</title>
    <link rel="shortcut icon" href="<s:url value='/jsp/inx/zsey/images/favicon.ico'/>" />
    <link href="<s:url value='/jsp/inx/zseyGate/css/style.css'/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/json2${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value='/js/artDialog/css/ui-dialog.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
    <script type="text/javascript" src="<s:url value='/js/artDialog/dialog-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/artDialog/dialog-plus-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/common-edu-jbox.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/jsp\inx\zseyGate\funcMap.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <script type="text/javascript">
        //map里面放list
        var typeMap={};
        var imgList={};

        imgList[0]="<s:url value='/jsp/inx/zseyGate/images/static/regist.png'/>";
        imgList[1]="<s:url value='/jsp/inx/zseyGate/images/static/personal.png'/>";
        imgList[2]="<s:url value='/jsp/inx/zseyGate/images/static/submission.png'/>";
        typeMap['registration']=imgList;
        typeMap['registrationLength']=3;

        imgList={};
        imgList[0]="<s:url value='/jsp/inx/zseyGate/images/static/auditing.png'/>";
        imgList[1]="<s:url value='/jsp/inx/zseyGate/images/static/personalAudit.png'/>";
        typeMap['auditing']=imgList;
        typeMap['auditingLength']=2;

        imgList={};
        imgList[0]="<s:url value='/jsp/inx/zseyGate/images/static/admission.png'/>";
        typeMap['admission']=imgList;
        typeMap['admissionLength']=1;
        var index=0;
        function showImg()
        {
            var type="${param.type}";
            var typeLength="${param.type}Length";
            var imgList=typeMap[type];
            console.log(imgList);
            var src=imgList[index];
            $("#img").attr("src",src);
            index++;
            if(index==typeMap[typeLength])
            {
                index=0;
            }
        }
        $(document).ready(function(){
            showImg()
        });
    </script>
</head>
<body>
<div>
    <img id="img" width="1000" src="" onclick="showImg()"/>
</div>
</body>
</html>