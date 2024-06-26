<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="<s:url value='/js/layui/css/layui.css'/>">
  <script src="<s:url value='/js/layui/layui.all.js'/>"></script>
  <script type="text/javascript" charset="utf-8"
          src="<s:url value='/js/bootstrap-datepicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
  <link rel="stylesheet" type="text/css"
        href="<s:url value='/css/datepicker.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
  <style>
    .tbCenter tbody{
      text-align: center;
    }
    .tbTitle{
      text-align: center;
      padding-top: 10px;
    }
    .tbTitleTxt{
      font-weight: bold;
      font-size: 22px;
        display: inline-block;
        margin-bottom: 20px;
    }
    .tbButtonBox{
      text-align: right;
    }
    /* 添加样式 */
    .clearfix:after{
      content:"";
      height:0;
      line-height:0;
      display:block;
      clear:both;
      visibility:hidden;
    }
    .clearfix{
      zoom:1;
    }
    .setLbtnBg{
      background: #44b549;
      border-radius: 3px;
    }
    .tbLeft{
      float: left;
    }
    .tbRight{
      float: right;
    }
    .tbCenter th{
      text-align: center;
    }
    .search-item{
      float: left;
      margin-right: 10px;
      margin-bottom: 10px;
    }
    .layui-form-radio{
      margin: 0;
    }
  </style>
  <title>Document</title>
</head>

<body>
<div class="layui-container">
  <div class="layui-row">
    <div class="layui-col-xs12">
      <div class="tbTitle clearfix">
          <div class="tbTitleTxt">
              在培学员未使用APP人数统计表
          </div>
        <div class="tbLeft layui-form">
          <form id="searchForm">
          <input name="monthDate" value="${param.monthDate}" type="hidden">
          <div class="search-item">
            <span>培训基地：</span>
            <div class="layui-input-inline">
              <select name="orgFlow" id="orgFlow" lay-filter="orgFlow" lay-verify="">
                <option value="">全部</option>
                <c:forEach items="${orgs}" var="org">
                  <option value="${org.orgFlow}" ${orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="search-item">
            <span>年级：</span>
            <div class="layui-input-inline">
              <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="layui-input"
                     readonly="readonly"/>
            </div>
          </div>
          <div class="search-item">
            <span>培训类别：</span>
            <div class="layui-input-inline">
              <select name="trainingTypeId" id="trainingTypeId" lay-filter="trainingTypeId" lay-verify="">
                <option value="">全部</option>
                <option value="WMFirst" ${param.trainingTypeId eq 'WMFirst'?'selected':''}>一阶段</option>
                <option value="DoctorTrainingSpe" ${param.trainingTypeId eq 'DoctorTrainingSpe'?'selected':''}>住院医师</option>
              </select>
            </div>
          </div>
          <div class="search-item">
            <span>培训专业：</span>
            <div class="layui-input-inline">
              <select name="trainingSpeId" id="trainingSpeId" lay-verify="">
                <option value="">全部</option>
              </select>
            </div>
          </div>
          <div class="search-item">
            <span style="display: inline-block;margin-top: 6px;">快速查询：</span>
            <input type="radio" name="monthLengh" lay-filter="monthLengh" value="1" ${monthLengh eq '1'?'checked':''}
                   title="本月" onclick="search()">
            <input type="radio" name="monthLengh" lay-filter="monthLengh" value="3" ${monthLengh eq '3'?'checked':''}
                   title="近三个月" onclick="search()">
          </div>
          </form>
        </div>
        <div class="tbRight">
          <button class="layui-btn layui-btn-normal setLbtnBg" onclick="search()">查询</button>
          <button class="layui-btn layui-btn-normal setLbtnBg" onclick="exportChart3()">导出</button>
          <button class="layui-btn layui-btn-normal setLbtnBg" onclick="exportChart3Detail()">导出详情</button>
        </div>
      </div>
      <table class="layui-table tbCenter">
        <thead>
        <tr>
          <th>培训基地</th>
          <th>住院医师</th>
          <th>在校专硕</th>
          <th>总人数</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${resultMapList}" var="result">
          <tr>
            <td>${result.ORG_NAME}</td>
            <td>${result.A}</td>
            <td>${result.B}</td>
            <td>${result.C}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
<script>
    ;!function(){
        var layer = layui.layer
            ,form = layui.form;
        form.on('select(trainingTypeId)', function(data){
            changeTrainSpes();
        });
        form.on('select(orgFlow)', function(data){
            changeTrainSpes();
        });
        form.on('radio(monthLengh)', function(data){
            search();
        });
    }();
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        }).on('change', function(e) {
            changeTrainSpes();
        });
        changeTrainSpes();
    });
    function changeTrainSpes(t){
        var layer = layui.layer
            ,form = layui.form;
        //清空原来专业！！！
        var sessionNumber=$("#sessionNumber").val();
        var trainCategoryid=$("#trainingTypeId").val();
        var orgFlow=$("#orgFlow").val();
        if(trainCategoryid==""){
            $("select[name=trainingSpeId] option[value != '']").remove();
            return false;
        }
        if(orgFlow == "all" || sessionNumber==""){
            $("select[name=trainingSpeId] option[value != '']").remove();
            $("#"+trainCategoryid+"_select").find("option").each(function(){
                $(this).clone().appendTo($("#trainingSpeId"));
            });
            if('${param.trainingSpeId}'){
                $('#trainingSpeId option[value="${param.trainingSpeId}"]').attr('selected','selected');
            }
            form.render('select');
            return false;
        }
        var url = "<s:url value='/jsres/doctor/searchResOrgSpeList'/>?sessionNumber="+sessionNumber+"&orgFlow="+orgFlow+"&speTypeId="+trainCategoryid;
        jboxGet(url, null, function(resp){
            $("select[name=trainingSpeId] option[value != '']").remove();
            if(resp!=""){
                var dataObj = resp;
                for(var i = 0; i<dataObj.length;i++){
                    var speId = dataObj[i].speId;
                    var speName = dataObj[i].speName;
                    $option =$("<option></option>");
                    $option.attr("value",speId);
                    $option.text(speName);
                    if('${param.trainingSpeId}'==speId){
                        $option.attr('selected','selected');
                    }
                    $("select[name=trainingSpeId]").append($option);
                    form.render('select');
                }
            }
        }, null , false);
    }
    function search() {
        var url = "<s:url value='/jsres/monthlyReportGlobalNew/chart3'/>";
        jboxPost(url,$("#searchForm").serialize(),function (resp) {
            $("#content1").html(resp);
        },null,false)
    }
    function exportChart3(){
        var url="<s:url value='/jsres/monthlyReportGlobalNew/exportChart3'/>?"+$("#searchForm").serialize();
        window.open(url);
    }
    function exportChart3Detail(){
        var url="<s:url value='/jsres/monthlyReportGlobalNew/exportChart3Detail'/>?"+$("#searchForm").serialize();
        window.open(url);
    }
</script>
<div style="display: none;">
  <select id="WMFirst_select">
    <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
      <option  value="${dict.dictId}">${dict.dictName}</option>
    </c:forEach>
  </select>
  <select id="WMSecond_select">
    <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
      <option  value="${dict.dictId}">${dict.dictName}</option>
    </c:forEach>
  </select>
  <select id="AssiGeneral_select">
    <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
      <option  value="${dict.dictId}">${dict.dictName}</option>
    </c:forEach>
  </select>
  <select id="DoctorTrainingSpe_select">
    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
      <option  value="${dict.dictId}">${dict.dictName}</option>
    </c:forEach>
  </select>
</div>
</body>
</html>