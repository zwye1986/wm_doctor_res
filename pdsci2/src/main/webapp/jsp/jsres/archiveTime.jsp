
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
<script type="text/javascript">
    function saveArchive(){
        var archiveTime = $("#archiveTime").val();
        var url = "<s:url value="/jsres/archive/archiveInfo"/>?archiveTime="+archiveTime;
        jboxStartLoading();
        jboxPost(url,null,function(resp){
            if(resp == "${GlobalConstant.OPRE_SUCCESSED}"){
                jboxClose();
            }else{
               jboxEndLoading();
            }
        },null,true);

    }
</script>
<style type="text/css">
    .btn_green{background-color: #44b549;color: #fff;border:none;}
    .btn_green:hover{background-color: #2f9833;}
    .btn_green{display: inline-block;overflow: visible;padding: 0 20px;height: 30px;line-height: 28px;vertical-align: middle;text-align: center;text-decoration: none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;font-size: 14px;cursor: pointer;font-family: "Microsoft YaHei";}
</style>
<div>
    <form>
    <table style="width: 100%;margin-top:30px;text-align: center">
        <tr>
           <td style="width: 30%">存档时间</td>
           <td style="width: 40%">
               <input class="xltext" type="text" value="${pdfn:getCurrDateTime('yyyy-MM-dd HH:mm:ss')}" id="archiveTime" name="archiveTime"
                      readonly="readonly" style="width: 98%" />
           </td>
           <td style="width: 30%"><input class="btn_green" type="button" value="保&#12288;存" onclick="saveArchive();"/></td>
        </tr>
    </table>
    </form>
</div>