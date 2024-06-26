<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/zseyGate/css/font2.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script>
    function go(){
        jboxOpen("<s:url value='/jsp/inx/zseyGate/static/gateLoginPage.jsp'/>","用户登录",500,400);
    }
</script>
<div class="sonbox">
    <div class="sonright fr">
        <div class="sontitle">
            <span class="fl sonnewtitle">最新考试安排</span>
            <span class="fr sonnewnap"><a>您现在的位置：教学网站>考试安排</a></span>
        </div>
    <div class="main_bd" id="div_table_0" >
        <div class="div_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>考试科目</th>
                <th>考试科室</th>
                <th>考试类型</th>
                <th>专业</th>
                <th>考试时间</th>
                <th>考试地点</th>
                <th>监考人</th>
                <th>操作</th>
            </tr>
            <tr>
                <td>肠胃外科理论考试</td>
                <td>外科</td>
                <td>出科考试</td>
                <td>外科</td>
                <td>2018-08-08</td>
                <td>大前门</td>
                <td>张学德</td>
                <td><a onclick="go();">参加考试</a></td>
            </tr>
            <tr>
                <td>外科理论考试</td>
                <td>内科</td>
                <td>年度考试</td>
                <td>外科</td>
                <td>2018-08-08</td>
                <td>大前门</td>
                <td>张学德</td>
                <td><a onclick="go();">参加考试</a></td>
            </tr>
        </table>
        </div>
    </div>
    </div>
</div>
