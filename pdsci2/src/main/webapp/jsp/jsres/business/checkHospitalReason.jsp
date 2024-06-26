<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>

<script type="text/javascript">
    function jboxClose(){
        _dialogClose('jbox-iframe');
    }
    function _dialogClose(dialogId){
        var myDialog = top.dialog.get(dialogId);
        if(myDialog!=null&&myDialog.open){
            top.$("[name='jbox-iframe']").attr("name","");
            myDialog.close().remove();
        }
    }
    function save(orgFlows,flag){
        if(flag == 'N'){
            if(!$("#checkReason").val()){
                jboxTip("请填写不通过原因！");
                return;
            }
        }
        var reason = $("#checkReason").val();
        var url = "<s:url value='/jsres/business/updateHospitalCheck'/>?orgFlows="+orgFlows+"&flag="+flag+"&checkReason="+reason;
        jboxPost(url, $("#cfgForm").serialize(), function(resp){
            if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                jboxTip("操作成功！");
                window.parent.searchHospital();
                setTimeout(function(){
                    jboxClose();
                },2000);
            }else{
                jboxTip("操作失败！");
            }
        }, null, false);
    }
</script>
</head>
<body>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="cfgForm" >
            <table style="width: 100%;" cellpadding="0" cellspacing="0" class="basic">
                <tr>
                    <td>备注：</td>
                </tr>
                <tr>
                    <td>
                        <input type="text" id="checkReason" class="input"  name="checkReason" style="width: 260px;height: 80px;margin-top: 5px;" />
                    </td>
                </tr>
            </table>
        </form>
        <div style="text-align: center">
            <input class="btn_green" type="button" value="通&#12288;过"  onclick="save('${orgFlows}','Y');" />
            <input class="btn_red" type="button" value="不通过"  onclick="save('${orgFlows}','N');" />
            <input class="btn_green" type="button" value="取&#12288;消"  onclick="jboxClose();" />
        </div>
    </div>
</div>
</body>
</html>
