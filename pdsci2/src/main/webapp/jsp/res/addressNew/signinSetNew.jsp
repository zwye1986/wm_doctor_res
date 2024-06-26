<html>
<head>
    <script type="text/javascript">
        $(function(){
            <c:if test="${empty thisAddress}">
            addMember('');
            </c:if>
            <c:if test="${empty outsideAddress}">
            addMember1('');
            </c:if>
            <c:if test="${not empty thisAddress}">
            <c:forEach var="address" items="${thisAddress}">
            addMember("${address.recordFlow}");
            </c:forEach>
            </c:if>
           <c:if test="${not empty outsideAddress}">
            <c:forEach var="address" items="${outsideAddress}">
            addMember1("${address.recordFlow}");
            </c:forEach>
            </c:if>

        })
        function save(){
            if($("#memberList").find("tr").length<=0)
            {
                jboxTip("至少设置一个签到地点");
                return false;
            }
            var bean={};
            bean.orgFlow="${sessionScope.currUser.orgFlow }";
            bean.addressType="this";
            var addresses=[];
            var i=0;
            var i2=0;
            var f=true;
            $("#memberList").find("tr").each(function(index){
                var iframe=$(this).find("iframe");
                var longitude=$(iframe).contents().find("input[name=longitude]").val();
                var latitude=$(iframe).contents().find("input[name=latitude]").val();
                var recordFlow=$(iframe).contents().find("input[name=recordFlow]").val();
                var orgAddress=$(iframe).contents().find("input[name=orgAddress]").val();
                var scopeLength=$(iframe).contents().find("input[name=scopeLength]").val();
                var  address={};
                address.recordFlow=recordFlow;
                address.orgAddress=orgAddress;
                address.orgFlow=bean.orgFlow;
                address.scopeLength=scopeLength;
                address.latitude=latitude;
                address.longitude=longitude;
                addresses.push(address);
                if(!orgAddress)
                {
                    jboxTip("请为签到点，设置签到地址！");
                    f=false ;
                    return false;
                }
                if(!scopeLength)
                {
                    jboxTip("请为签到点，设置签到半径！");
                    f=false ;
                    return false;
                }
                if(!isPositiveInteger(scopeLength)){//非数字
                    jboxTip("签到的签到半径只能是正整数！");
                    f=false ;
                    return false;
                }
            });
            bean.addresses=addresses;
            if(f)
            {
                console.log(JSON.stringify(bean));
                var url = "<s:url value='/jsres/attendanceNew/saveSigninSetList'/>";
                jboxPostJson(url,  JSON.stringify(bean), function(resp) {
                    if(resp !="保存失败！") {
                        jboxLoad("content","<s:url value='/jsres/attendanceNew/signinSet'/>",true);
                    }
                }, null, true);
            }
        }
        function saveOutside(){
            if($("#memberList1").find("tr").length<=0)
            {
                jboxTip("至少设置一个签到地点");
                return false;
            }
            var bean={};
            bean.orgFlow="${sessionScope.currUser.orgFlow }";
            bean.addressType="outside";
            var addresses=[];
            var i=0;
            var i2=0;
            var f=true;
            $("#memberList1").find("tr").each(function(index){
                var iframe=$(this).find("iframe");
                var longitude=$(iframe).contents().find("input[name=longitude]").val();
                var latitude=$(iframe).contents().find("input[name=latitude]").val();
                var recordFlow=$(iframe).contents().find("input[name=recordFlow]").val();
                var orgAddress=$(iframe).contents().find("input[name=orgAddress]").val();
                var scopeLength=$(iframe).contents().find("input[name=scopeLength]").val();
                var  address={};
                address.recordFlow=recordFlow;
                address.orgAddress=orgAddress;
                address.orgFlow=bean.orgFlow;
                address.scopeLength=scopeLength;
                address.latitude=latitude;
                address.longitude=longitude;
                addresses.push(address);
                if(!orgAddress)
                {
                    jboxTip("请为签到点，设置签到地址！");
                    f=false ;
                    return false;
                }
                if(!scopeLength)
                {
                    jboxTip("请为签到点，设置签到半径！");
                    f=false ;
                    return false;
                }
                if(!isPositiveInteger(scopeLength)){//非数字
                    jboxTip("签到的签到半径只能是正整数！");
                    f=false ;
                    return false;
                }
            });
            bean.addresses=addresses;
            if(f)
            {
                console.log(JSON.stringify(bean));
                var url = "<s:url value='/jsres/attendanceNew/saveSigninSetList'/>";
                jboxPostJson(url,  JSON.stringify(bean), function(resp) {
                    if(resp !="保存失败！") {
                        jboxLoad("content","<s:url value='/jsres/attendanceNew/signinSet'/>",true);
                    }
                }, null, true);
            }
        }
        function isPositiveInteger(s){//是否为正整数
            var re = /^[1-9][0-9]+$/ ;
            return re.test(s)
        }
        function addMember(recordFlow){
            var index=$("#memberList").find("tr").length+1;
            var url ='<s:url value="/jsres/attendanceNew/oneAddress?recordFlow="/>'+recordFlow;
            var iframe ="<iframe  width='100%' height='100%' " + " id='iframeId"+index+"'"+
                "marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            var tr =$("#moban tr:eq(0)").clone();
            $(tr).find("td[name='iframeTd']").html(iframe)
            $('#memberList').append($(tr));
            jboxStartLoading();
        }
        function addAddressMember1(recordFlow){
            var index=$("#memberList").find("tr").length+1;
            var url ='<s:url value="/jsres/attendanceNew/oneAddress?recordFlow="/>'+recordFlow;
            var iframe ="<iframe  width='100%' height='100%' " + " id='iframeId"+index+"'"+
                "marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            var tr =$("#moban tr:eq(0)").clone();
            $(tr).find("td[name='iframeTd']").html(iframe)
            $('#memberList').append($(tr));
            // jboxStartLoading();
        }
        function addAddressMember2(recordFlow){
            var index=$("#memberList1").find("tr").length+1;
            var url ='<s:url value="/jsres/attendanceNew/oneAddress?recordFlow="/>'+recordFlow;
            var iframe ="<iframe  width='100%' height='100%' " + " id='iframeId"+index+"'"+
                "marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            var tr =$("#moban tr:eq(0)").clone();
            $(tr).find("td[name='iframeTd']").html(iframe)
            $('#memberList1').append($(tr));
            // jboxStartLoading();
        }
        function addMember1(recordFlow){
            var index=$("#memberList1").find("tr").length+1;
            var url ='<s:url value="/jsres/attendanceNew/oneAddress?recordFlow="/>'+recordFlow;
            var iframe ="<iframe  width='100%' height='100%' " + " id='iframeId"+index+"'"+
                "marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            var tr =$("#moban tr:eq(0)").clone();
            $(tr).find("td[name='iframeTd']").html(iframe)
            $('#memberList1').append($(tr));
            jboxStartLoading();
        }
        function moveTr(obj){
            if($("#memberList").find("tr").length<=1 || $("#memberList1").find("tr").length<=1)
            {
                jboxTip("至少设置一个签到地点");
                return false;
            }
            jboxConfirm("确认删除？" , function(){
                var tr=obj.parentNode.parentNode;
                tr.remove();
            });
        }
    </script>
    <style type="text/css">
        .xllist th, .xllist td {
            border: 1px solid #bbbbbb;
        }
        .iframeTd {
            height: 500px;
        }
    </style>
</head>
<body>
<div class="main_hd">
    <h2 class="underline">签到地点设置</h2>
</div>
<div class="mainright">
    <fieldset style="margin-top: 20px;">
        <table class="xllist nofix" id="moban" style="display: none" style="width: 100%">
            <tr>
                <td name="iframeTd" class="iframeTd" style="height: 481px;">
                </td>
            </tr>
        </table>
        <div style="min-height: 486px;margin-top: 20px;">
            <table class="xllist nofix" style="font-size: 25px;width:100%;border:1px solid #bbbbbb;">
                <thead>
                <tr>
                    <th colspan="2">本部考勤定位地点设置
                        <span style="padding-right: 0px;">
						 <img class="opBtn" title="新增" src="<s:url value='/css/skin/LightBlue/images/add3.png'/>" style="cursor: pointer;" onclick="addAddressMember1('');"/>
						</span>
                    </th>
                </tr>
                </thead>
                <tbody id="memberList">
                </tbody>
            </table>
        </div>
        <div style="width: 100%">
            <div class="button">
                <input class="btn_green"  onclick="save()" type="button" value="本部签到地点保存"/>
            </div>
        </div>
    </fieldset>
</div>
<div class="mainright">
    <fieldset style="margin-top: 20px;">
        <table class="xllist nofix" id="moban" style="display: none" style="width: 100%">
            <tr>
                <td name="iframeTd" class="iframeTd" style="height: 481px;">
                </td>
            </tr>
        </table>
        <div style="min-height: 486px;margin-top: 20px;">
            <table class="xllist nofix" style="font-size: 25px;width:100%;border:1px solid #bbbbbb;">
                <thead>
                <tr>
                    <th colspan="2">外院考勤定位地点设置
                        <span style="padding-right: 0px;">
						 <img class="opBtn" title="新增" src="<s:url value='/css/skin/LightBlue/images/add3.png'/>" style="cursor: pointer;" onclick="addAddressMember2('');"/>
						</span>
                    </th>
                </tr>
                </thead>
                <tbody id="memberList1">
                </tbody>
            </table>
        </div>
        <div style="width: 100%">
            <div class="button">
                <input class="btn_green"  onclick="saveOutside()" type="button" value="外院签到地点保存"/>
            </div>
        </div>
    </fieldset>
</div>
</body>
</html>