
 <html>
 <head>

   <style type="text/css">
     .td_group .left{
       text-align: left;
     }
     .td_group .right{
       text-align: right;
     }
     .th_group th {
       background-color: rgba(0,0,0,0.07);
       border: 1px solid rgba(0,0,0,0.07);
       text-align: center;
       vertical-align:middle;
     }
     .td_group td {
       border: 1px solid rgba(0,0,0,0.07);
       text-align: center;
       vertical-align:middle;
     }
     .td_group th {
       border: 1px solid rgba(0,0,0,0.07);
       text-align: center;
       vertical-align:middle;
     }
     body {
       width:100%;
       overflow:auto;
     }
     .td_group textarea:not(.problems) {
       height: 25px;
       line-height: 25px;
       padding: 0 3px;
       border: 1px solid #bdbebe;
       background: white;
       font-family: "Microsoft Yahei", Helvetica, sans-serif;
       width: 142px;
     }
     .td_group input[name='score']:not(#signDate){
       height: 25px;
       line-height: 25px;
       padding: 0 3px;
       border: 1px solid #bdbebe;
       background: white;
       font-family: "Microsoft Yahei", Helvetica, sans-serif;
       width: 30px;
     }
     .table-inner td{
       text-align: left;
     }
   </style>
   <script>
     $(function(){
       $(".td_group textarea").css("height","25px")
       $(".problems").css("height","");
       $("input[name='score']:not(#signDate,#inputSumScore)").each(function(){
         $(this).attr("onblur","checkPoint(this)");
       });
       if('${param.viewFlag}'=='Y'){
         $(".td_group textarea").attr("disabled",true);
         $(".td_group input").attr("disabled",true);
         $(".td_group").each(function(){
           $(this).find("a").last().hide().prev().hide();
         })
       }
     });
     function sumScore()
     {
       var allScore=0;
       $("input[name='score']").each(function(){
         if($(this).attr("id")!='signDate' && $(this).attr("id")!='inputSumScore'){
           var score=$(this).val();
           if(!isNaN(score)&&score!="")
           {
             allScore+=parseFloat(score);
           }else{
             allScore+=0;
           }
         }
       });
       $("#inputSumScore").val(allScore);
     }
     function checkPoint(obj){
       var maxPonit = $(obj).parent().prev().prev().text();
       if(parseFloat(maxPonit)<parseFloat($(obj).val())){
         $(obj).val("");
         jboxTip("分数不能超过最大值");
         setTimeout(function(){
           $(obj).focus();
         },100);
       }else if(isNaN($(obj).val())){
         $(obj).val("");
         jboxTip("请输入数字");
         setTimeout(function(){
           $(obj).focus();
         },100);
       }else {
         sumScore();
       }
     }
   </script>
 </head>
 <body >
 <input type="hidden" id="formFlow" name="formFlow" value="${formFlow}">
 <input type="hidden" id="recordFlow" name="recordFlow" value="${baseeval.recordFlow}">
<div data-role="page" id="pageone">
  <div data-role="header">
	<h1 style="text-align: center;font-size: 25px;">2017年住院医师规范化培训评估指标——麻醉科专业基地</h1>
	
  </div>
  <div data-role="main" class="ui-content">
    <table data-role="table" data-mode=""  class="ui-responsive table-stroke">
       <colgroup>
         <col style="width:7%"/>
         <col style="width:7%"/>
         <col style="width:7%"/>
         <col style="text-align: left;width:17%"/>
         <col style="width:17%"/>
         <col style="width:20%"/>
         <col style="width:5%"/>
         <col style="width:5%"/>
         <col style="width:7%"/>
         <col style="width:10%;"/>
      </colgroup>
      <thead>
        <tr class="th_group">
          <th colspan="3">评估项目</th>
          <th rowspan="2">评估内容</th>
          <th rowspan="2">现场评估方式</th>
          <th rowspan="2">评分标准</th>
          <th rowspan="2">分值</th>
          <th rowspan="2">得分</th>
          <th rowspan="2">扣分原因</th>
          <th rowspan="2">附件材料</th>
        </tr>
        <tr class="th_group">
          <th>一级指标</th>
          <th>二级指标</th>
          <th>三级指标<br/>★为核心指标
		  </th>
        </tr>
      </thead>
      <tbody  id="dataTable">
        <tr class="td_group">
          <th rowspan="10">1.基本条件（18分）</th>
          <td rowspan="7">1.1专业基地所在医院条件</td>
          <td class="left">1.1.1年麻醉总量</td>
          <td class="left">麻醉总数量≥10000例<br/>
            麻醉恢复室≥2500例<br/>
            疼痛门诊≥1000例<br/>
            重症监护室≥200例</td>
          <td class="left">检查相关文件复印件，需加盖医院公章 ，实地考查</td>
          <td class="left">符合标准，得满分<br/>
            1项不达标，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="1.1.1"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["1.1.1"]}</textarea></td>
          <td>
            <span id="1.1.1Span" style="display:${empty fileMap["1.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.1Up" href="javascript:uploadFile('1.1.1');" style="color:blue">${empty fileMap["1.1.1"]?'':'重新'}上传</a>
            <a id="1.1.1Del" href="javascript:delFile('1.1.1');"  style="color:blue;${empty fileMap["1.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.1Value" name="filePath" value="${fileMap["1.1.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.2年亚专业麻醉例数</td>
          <td class="left" style="padding:0;">
            <table class="table-inner">
              <tr>
                <td>普通外科、泌尿外科和骨科麻醉≥5500例</td>
              </tr>
              <tr>
                <td>眼、耳鼻喉科麻醉≥700例</td>
              </tr>
              <tr>
                <td>神经外科麻醉≥600例</td>
              </tr>
              <tr>
                <td>胸心血管麻醉≥600例</td>
              </tr>
              <tr>
                <td>妇产科麻醉≥400例</td>
              </tr>
              <tr>
                <td>口腔外科麻醉≥500例</td>
              </tr>
              <tr>
                <td>小儿麻醉(只含小儿普外、小儿泌外和小儿骨科)≥600例</td>
              </tr>
              <tr>
                <td>门诊和(或)手术室外麻醉≥500例</td>
              </tr>
              <tr>
                <td>其他麻醉≥600例</td>
              </tr>
            </table>
          </td>
          <td class="left">检查相关统计报表复印件，需加盖医院公章</td>
          <td class="left">符合标准，得满分<br/>1项不达标，扣1分<br/>2项及以上不达标，不得分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="1.1.2"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["1.1.2"]}</textarea></td>
          <td>
            <span id="1.1.2Span" style="display:${empty fileMap["1.1.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.2Up" href="javascript:uploadFile('1.1.2');" style="color:blue">${empty fileMap["1.1.2"]?'':'重新'}上传</a>
            <a id="1.1.2Del" href="javascript:delFile('1.1.2');"  style="color:blue;${empty fileMap["1.1.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.2Value" name="filePath" value="${fileMap["1.1.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.3轮转科室</td>
          <td class="left">麻醉科室(必选)：普外科麻醉、骨科麻醉、泌尿外科麻醉、眼科和耳鼻咽喉科麻醉、口腔外科麻醉、神经外科麻醉、胸心血管外科麻醉、妇产科麻醉、小儿外科麻醉、门诊和手术室外麻醉、麻醉恢复室、疼痛科、重症监护<br/>
            非麻醉科室(任选2～3个)：普通外科、神经内科、神经外科、胸心外科、呼吸内科、心血管内科、内分泌科、小儿内科、急诊科、心电图室、影像科</td>
          <td class="left">1.查看各(亚)专业设置名称<br/>
            2.查看培训对象轮转计划和登记手册<br/>
            3.实地考查，访谈培训对象</td>
          <td class="left">轮转科室齐全，得满分<br/>
            必选科室缺1个科室，不得分<br/>
            任选科室缺2个科室，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="1.1.3"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["1.1.3"]}</textarea></td>
          <td>
            <span id="1.1.3Span" style="display:${empty fileMap["1.1.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.3Up" href="javascript:uploadFile('1.1.3');" style="color:blue">${empty fileMap["1.1.3"]?'':'重新'}上传</a>
            <a id="1.1.3Del" href="javascript:delFile('1.1.3');"  style="color:blue;${empty fileMap["1.1.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.3Value" name="filePath" value="${fileMap["1.1.3"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.4疾病种类及数量</td>
          <td class="left" rowspan="2">符合《住院医师规范化培训基地认定标准（试行）》和《住院医师规范化培训内容与标准（试行）》麻醉科专业细则要求，详见附件1 </td>
          <td class="left">核对上1年度各亚专业（专科）收治疾病种类和数量统计报表 </td>
          <td class="left">符合要求（含协同单位），得满分<br/>
            疾病种类及数量≥规定数的90%，得1分<br/>
            疾病种类及数量≥规定数的85%，得0.5分<br/>
            疾病种类及数量＜规定数的85%，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="1.1.4"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.4"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["1.1.4"]}</textarea></td>
          <td>
            <span id="1.1.4Span" style="display:${empty fileMap["1.1.4"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.4"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.4Up" href="javascript:uploadFile('1.1.4');" style="color:blue">${empty fileMap["1.1.4"]?'':'重新'}上传</a>
            <a id="1.1.4Del" href="javascript:delFile('1.1.4');"  style="color:blue;${empty fileMap["1.1.4"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.4Value" name="filePath" value="${fileMap["1.1.4"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.5临床技能操作种类及数量★</td>
          <td class="left">核对上1年度各亚专业（专科）临床技能操作种类和数量的统计报表 </td>
          <td class="left">符合要求（含协同单位），得满分<br/>
            技能操作种类及数量≥规定数的90%，得2分<br/>
            技能操作种类及数量≥规定数的85%，得1分<br/>
            技能操作种类及数量＜规定数85%，不得分 </td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="1.1.5"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.5"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["1.1.5"]}</textarea></td>
          <td>
            <span id="1.1.5Span" style="display:${empty fileMap["1.1.5"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.5"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.5Up" href="javascript:uploadFile('1.1.5');" style="color:blue">${empty fileMap["1.1.5"]?'':'重新'}上传</a>
            <a id="1.1.5Del" href="javascript:delFile('1.1.5');"  style="color:blue;${empty fileMap["1.1.5"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.5Value" name="filePath" value="${fileMap["1.1.5"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.6医院设备</td>
          <td class="left">有创血压监测设备、除颤器、 血气分析仪、快速输血系统、保温及降温设备、微量注射泵、血糖仪、肌松监测仪、神经刺激器、血液回收机、纤维支气管镜及应对呼吸困难的常用设备</td>
          <td class="left">检查设备清单复印件，需加盖医院公章，实地考查</td>
          <td class="left">缺1项，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.1.6"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.6"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["1.1.6"]}</textarea></td>
          <td>
            <span id="1.1.6Span" style="display:${empty fileMap["1.1.6"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.6"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.6Up" href="javascript:uploadFile('1.1.6');" style="color:blue">${empty fileMap["1.1.6"]?'':'重新'}上传</a>
            <a id="1.1.6Del" href="javascript:delFile('1.1.6');"  style="color:blue;${empty fileMap["1.1.6"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.6Value" name="filePath" value="${fileMap["1.1.6"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.7专业基地设备</td>
          <td class="left">
            <table class="table-inner">
              <tr>
                <td>最低配置:麻醉机，具有心电图、氧饱和度监测、无创血压监测等功能的监护仪，急救药物及基本麻醉与复苏用品</td>
              </tr>
              <tr>
                <td>重症监护室最低配置:呼吸机，具有心电图、氧饱和度、温度监测、无创及有创血压监测的监护仪，多通道输液泵/床<br/>
                  重症监护室公用设备:除颤器、血气分析仪、快速输血系统、保温及降温设备、血糖仪、纤维支气管镜</td>
              </tr>
              <tr>
                <td>疼痛门诊及病房最低配置:急救复苏设备、神经刺激器、激光理疗仪</td>
              </tr>
              <tr>
                <td>模拟教学设备：气管插管模型、桡动脉和中心静脉穿刺模型和心肺复苏模型</td>
              </tr>
            </table>
          </td>
          <td class="left">检查设备清单复印件，需加盖医院公章，实地考查</td>
          <td class="left">缺1项，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.1.7"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.7"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["1.1.7"]}</textarea></td>
          <td>
            <span id="1.1.7Span" style="display:${empty fileMap["1.1.7"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.7"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.7Up" href="javascript:uploadFile('1.1.7');" style="color:blue">${empty fileMap["1.1.7"]?'':'重新'}上传</a>
            <a id="1.1.7Del" href="javascript:delFile('1.1.7');"  style="color:blue;${empty fileMap["1.1.7"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.7Value" name="filePath" value="${fileMap["1.1.7"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td  rowspan="3">1.2协同单位</td>
          <td class="left">1.2.1协同数</td>
          <td class="left">协同数量不应超过3个</td>
          <td class="left" rowspan="3">查看原始资料，核实相关信息</td>
          <td class="left">满足要求，得1分（无协同单位的专业基地，此处不失分）</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.2.1"/>
          <td><input type="text" name="score" value="${scoreMap["1.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["1.2.1"]}</textarea></td>
          <td>
            <span id="1.2.1Span" style="display:${empty fileMap["1.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.2.1Up" href="javascript:uploadFile('1.2.1');" style="color:blue">${empty fileMap["1.2.1"]?'':'重新'}上传</a>
            <a id="1.2.1Del" href="javascript:delFile('1.2.1');"  style="color:blue;${empty fileMap["1.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.2.1Value" name="filePath" value="${fileMap["1.2.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.2.2协同床位数</td>
          <td class="left">各亚专业（专科）床位数（参照《住院医师规范化培训基地认定标准（试行）》本专业细则要求）</td>
          <td class="left">满足要求，得1分（无协同单位的专业基地，此处不失分）</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.2.2"/>
          <td><input type="text" name="score" value="${scoreMap["1.2.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["1.2.2"]}</textarea></td>
          <td>
            <span id="1.2.2Span" style="display:${empty fileMap["1.2.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.2.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.2.2Up" href="javascript:uploadFile('1.2.2');" style="color:blue">${empty fileMap["1.2.2"]?'':'重新'}上传</a>
            <a id="1.2.2Del" href="javascript:delFile('1.2.2');"  style="color:blue;${empty fileMap["1.2.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.2.2Value" name="filePath" value="${fileMap["1.2.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.2.3轮转时间</td>
          <td class="left">在协同亚专业（专科）轮转时间不超过3个月</td>
          <td class="left">满足要求，得1分（无协同单位的专业基地，此处不失分）</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.2.3"/>
          <td><input type="text" name="score" value="${scoreMap["1.2.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["1.2.3"]}</textarea></td>
          <td>
            <span id="1.2.3Span" style="display:${empty fileMap["1.2.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.2.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.2.3Up" href="javascript:uploadFile('1.2.3');" style="color:blue">${empty fileMap["1.2.3"]?'':'重新'}上传</a>
            <a id="1.2.3Del" href="javascript:delFile('1.2.3');"  style="color:blue;${empty fileMap["1.2.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.2.3Value" name="filePath" value="${fileMap["1.2.3"]}">
          </td>
        </tr>
		
        <tr class="td_group">
          <th rowspan="7">2.师资条件（17分）</th>
          <td rowspan="4">2.1师资情况</td>
          <td class="left">2.1.1带教医师与培训对象比例★</td>
          <td class="left">每2名带教医师同时带教本专业培训对象不超过1名</td>
          <td class="left">查看原始资料，访谈培训对象</td>
          <td class="left">不达标准，不得分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="2.1.1"/>
          <td><input type="text" name="score" value="${scoreMap["2.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["2.1.1"]}</textarea></td>
          <td>
            <span id="2.1.1Span" style="display:${empty fileMap["2.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.1Up" href="javascript:uploadFile('2.1.1');" style="color:blue">${empty fileMap["2.1.1"]?'':'重新'}上传</a>
            <a id="2.1.1Del" href="javascript:delFile('2.1.1');"  style="color:blue;${empty fileMap["2.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.1Value" name="filePath" value="${fileMap["2.1.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.2带教医师条件</td>
          <td class="left">医学本科及以上学历,主治医师专业技术职务3年以上</td>
          <td class="left" rowspan="3">查看人事部门提供的师资状况统计表，包括姓名、毕业时间、毕业学校、学历学位、专业技术职务、专业技术职务任职时间、工作时间，需加盖人事部门公章</td>
          <td class="left">其中1名带教医师不符合要求，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.1.2"/>
          <td><input type="text" name="score" value="${scoreMap["2.1.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["2.1.2"]}</textarea></td>
          <td>
            <span id="2.1.2Span" style="display:${empty fileMap["2.1.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.2Up" href="javascript:uploadFile('2.1.2');" style="color:blue">${empty fileMap["2.1.2"]?'':'重新'}上传</a>
            <a id="2.1.2Del" href="javascript:delFile('2.1.2');"  style="color:blue;${empty fileMap["2.1.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.2Value" name="filePath" value="${fileMap["2.1.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.3带教医师组成</td>
          <td class="left">各亚专业（专科）主任医师≥1人，副主任医师≥3人，主治医师≥6人</td>
          <td class="left">1个亚专业（专科）不达标，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.1.3"/>
          <td><input type="text" name="score" value="${scoreMap["2.1.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["2.1.3"]}</textarea></td>
          <td>
            <span id="2.1.3Span" style="display:${empty fileMap["2.1.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.3Up" href="javascript:uploadFile('2.1.3');" style="color:blue">${empty fileMap["2.1.3"]?'':'重新'}上传</a>
            <a id="2.1.3Del" href="javascript:delFile('2.1.3');"  style="color:blue;${empty fileMap["2.1.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.3Value" name="filePath" value="${fileMap["2.1.3"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.4专业基地负责人条件</td>
          <td class="left">医学本科及以上学历,主任医师专业技术职务,从事本专业的医疗、科研和教学工作超过15年</td>
          <td class="left">1项不符合条件，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.1.4"/>
          <td><input type="text" name="score" value="${scoreMap["2.1.4"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["2.1.4"]}</textarea></td>
          <td>
            <span id="2.1.4Span" style="display:${empty fileMap["2.1.4"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.4"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.4Up" href="javascript:uploadFile('2.1.4');" style="color:blue">${empty fileMap["2.1.4"]?'':'重新'}上传</a>
            <a id="2.1.4Del" href="javascript:delFile('2.1.4');"  style="color:blue;${empty fileMap["2.1.4"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.4Value" name="filePath" value="${fileMap["2.1.4"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td rowspan="3">2.2师资建设</td>
          <td class="left">2.2.1师资培训★</td>
          <td class="left">带教医师均参加过院级师资培训<br/>
            各亚专业（专科）至少1名带教医师参加过省级及以上师资培训</td>
          <td class="left">查看培训资料、名单和培训证书</td>
          <td class="left">2项培训均满足，得满分<br/>
            1项满足，得1分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="2.2.1"/>
          <td><input type="text" name="score" value="${scoreMap["2.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["2.2.1"]}</textarea></td>
          <td>
            <span id="2.2.1Span" style="display:${empty fileMap["2.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.1Up" href="javascript:uploadFile('2.2.1');" style="color:blue">${empty fileMap["2.2.1"]?'':'重新'}上传</a>
            <a id="2.2.1Del" href="javascript:delFile('2.2.1');"  style="color:blue;${empty fileMap["2.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.1Value" name="filePath" value="${fileMap["2.2.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.2师资评价★</td>
          <td class="left">每年至少组织1次对带教医师教学工作进行评价</td>
          <td class="left">查看原始资料，访谈带教医师和培训对象</td>
          <td class="left">有评价方案，原始记录详实，得满分<br/>
            有评价记录无方案，得2分<br/>
            有方案无记录，得1分</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="2.2.2"/>
          <td><input type="text" name="score" value="${scoreMap["2.2.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["2.2.2"]}</textarea></td>
          <td>
            <span id="2.2.2Span" style="display:${empty fileMap["2.2.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.2Up" href="javascript:uploadFile('2.2.2');" style="color:blue">${empty fileMap["2.2.2"]?'':'重新'}上传</a>
            <a id="2.2.2Del" href="javascript:delFile('2.2.2');"  style="color:blue;${empty fileMap["2.2.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.2Value" name="filePath" value="${fileMap["2.2.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.3激励制度★</td>
          <td class="left">建立带教医师激励机制，将教学工作与绩效考评、奖金、评优等挂钩</td>
          <td class="left">查看相关材料，访谈带教医师</td>
          <td class="left">有激励机制，并与奖金、评优挂钩，得满分<br/>有激励机制，未与奖金、评优挂钩，得2分<br/>无，不得分</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="2.2.3"/>
          <td><input type="text" name="score" value="${scoreMap["2.2.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["2.2.3"]}</textarea></td>
          <td>
            <span id="2.2.3Span" style="display:${empty fileMap["2.2.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.3Up" href="javascript:uploadFile('2.2.3');" style="color:blue">${empty fileMap["2.2.3"]?'':'重新'}上传</a>
            <a id="2.2.3Del" href="javascript:delFile('2.2.3');"  style="color:blue;${empty fileMap["2.2.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.3Value" name="filePath" value="${fileMap["2.2.3"]}">
          </td>
        </tr>
		
		
        <tr class="td_group">
          <th rowspan="12">3.过程管理（30分）</th>
          <td rowspan="6">3.1培训制度与落实</td>
          <td class="left">3.1.1主任职责</td>
          <td class="left">实行专业基地负责人负责制，并切实落实</td>
          <td class="left" rowspan="3">查看相关文件，访谈各类人员</td>
          <td class="left">职责明确，履职认真，得1分<br/>
            无岗位职责，或履职不认真，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="3.1.1"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.1.1"]}</textarea></td>
          <td>
            <span id="3.1.1Span" style="display:${empty fileMap["3.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.1Up" href="javascript:uploadFile('3.1.1');" style="color:blue">${empty fileMap["3.1.1"]?'':'重新'}上传</a>
            <a id="3.1.1Del" href="javascript:delFile('3.1.1');"  style="color:blue;${empty fileMap["3.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.1Value" name="filePath" value="${fileMap["3.1.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.2教学主任★</td>
          <td class="left">设置专职教学主任岗位，专门负责本专业基地教学工作的组织实施</td>
          <td class="left">职责明确，履职认真，得4分<br/>
            无岗位职责，或履职不认真，不得分</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="3.1.2"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.1.2"]}</textarea></td>
          <td>
            <span id="3.1.2Span" style="display:${empty fileMap["3.1.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.2Up" href="javascript:uploadFile('3.1.2');" style="color:blue">${empty fileMap["3.1.2"]?'':'重新'}上传</a>
            <a id="3.1.2Del" href="javascript:delFile('3.1.2');"  style="color:blue;${empty fileMap["3.1.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.2Value" name="filePath" value="${fileMap["3.1.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.3教学秘书</td>
          <td class="left">设置专职教学秘书岗位，落实本专业基地教学工作</td>
          <td class="left">有教学秘书，履职认真，得1分<br/>
            无，或履职不认真，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="3.1.3"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.1.3"]}</textarea></td>
          <td>
            <span id="3.1.3Span" style="display:${empty fileMap["3.1.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.3Up" href="javascript:uploadFile('3.1.3');" style="color:blue">${empty fileMap["3.1.3"]?'':'重新'}上传</a>
            <a id="3.1.3Del" href="javascript:delFile('3.1.3');"  style="color:blue;${empty fileMap["3.1.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.3Value" name="filePath" value="${fileMap["3.1.3"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.4教学小组</td>
          <td class="left">成立教学小组，明确小组职责，定期组织研究教学工作</td>
          <td class="left">查看教学小组名单、职责和研究教学工作记录</td>
          <td class="left">有教学小组，履职认真，得1分<br/>
            无，或履职不认真，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="3.1.4"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.4"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.1.4"]}</textarea></td>
          <td>
            <span id="3.1.4Span" style="display:${empty fileMap["3.1.4"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.4"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.4Up" href="javascript:uploadFile('3.1.4');" style="color:blue">${empty fileMap["3.1.4"]?'':'重新'}上传</a>
            <a id="3.1.4Del" href="javascript:delFile('3.1.4');"  style="color:blue;${empty fileMap["3.1.4"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.4Value" name="filePath" value="${fileMap["3.1.4"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.5轮转计划★</td>
          <td class="left">按规定落实轮转计划和要求</td>
          <td class="left">查看2～3培训对象轮转手册等原始资料，访谈培训对象</td>
          <td class="left">有，且严格落实，得满分<br/>
            未严格落实，不得分</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="3.1.5"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.5"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.1.5"]}</textarea></td>
          <td>
            <span id="3.1.5Span" style="display:${empty fileMap["3.1.5"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.5"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.5Up" href="javascript:uploadFile('3.1.5');" style="color:blue">${empty fileMap["3.1.5"]?'':'重新'}上传</a>
            <a id="3.1.5Del" href="javascript:delFile('3.1.5');"  style="color:blue;${empty fileMap["3.1.5"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.5Value" name="filePath" value="${fileMap["3.1.5"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.6考勤制度</td>
          <td class="left">有考勤规章制度规定，有专人负责，并严格执行</td>
          <td class="left">查看考勤规章制度，并抽查2～3名培训对象考勤记录原始资料</td>
          <td class="left">有，且严格落实，得满分<br/>
            未严格落实，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="3.1.6"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.6"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.1.6"]}</textarea></td>
          <td>
            <span id="3.1.6Span" style="display:${empty fileMap["3.1.6"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.6"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.6Up" href="javascript:uploadFile('3.1.6');" style="color:blue">${empty fileMap["3.1.6"]?'':'重新'}上传</a>
            <a id="3.1.6Del" href="javascript:delFile('3.1.6');"  style="color:blue;${empty fileMap["3.1.6"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.6Value" name="filePath" value="${fileMap["3.1.6"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td rowspan="4">3.2培训活动</td>
          <td class="left">3.2.1入科教育</td>
          <td class="left">规范实施包括科室情况、科室纪律、培养计划与要求、医德医风、医患沟通等入科教育，并有专人组织实施</td>
          <td class="left">提供本年度入科教育原始资料</td>
          <td class="left">有，且严格落实，得满分<br/>
            未严格落实，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="3.2.1"/>
          <td><input type="text" name="score" value="${scoreMap["3.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.2.1"]}</textarea></td>
          <td>
            <span id="3.2.1Span" style="display:${empty fileMap["3.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.2.1Up" href="javascript:uploadFile('3.2.1');" style="color:blue">${empty fileMap["3.2.1"]?'':'重新'}上传</a>
            <a id="3.2.1Del" href="javascript:delFile('3.2.1');"  style="color:blue;${empty fileMap["3.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.2.1Value" name="filePath" value="${fileMap["3.2.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.2教学查房</td>
          <td class="left">开展规范的教学查房，至少每2周1次</td>
          <td class="left" rowspan="3">提供本年度原始资料，访谈培训对象，核实落实情况</td>
          <td class="left">开展次数达标，且认真规范，得满分<br/>未达标或不规范，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="3.2.2"/>
          <td><input type="text" name="score" value="${scoreMap["3.2.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.2.2"]}</textarea></td>
          <td>
            <span id="3.2.2Span" style="display:${empty fileMap["3.2.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.2.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.2.2Up" href="javascript:uploadFile('3.2.2');" style="color:blue">${empty fileMap["3.2.2"]?'':'重新'}上传</a>
            <a id="3.2.2Del" href="javascript:delFile('3.2.2');"  style="color:blue;${empty fileMap["3.2.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.2.2Value" name="filePath" value="${fileMap["3.2.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.3小讲课</td>
          <td class="left">开展规范的小讲课活动，至少每周1次</td>
          <td class="left">开展次数达标，且认真规范，得满分<br/>未达标或不规范，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="3.2.3"/>
          <td><input type="text" name="score" value="${scoreMap["3.2.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.2.3"]}</textarea></td>
          <td>
            <span id="3.2.3Span" style="display:${empty fileMap["3.2.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.2.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.2.3Up" href="javascript:uploadFile('3.2.3');" style="color:blue">${empty fileMap["3.2.3"]?'':'重新'}上传</a>
            <a id="3.2.3Del" href="javascript:delFile('3.2.3');"  style="color:blue;${empty fileMap["3.2.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.2.3Value" name="filePath" value="${fileMap["3.2.3"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.4疑难病例讨论</td>
          <td class="left">开展规范的疑难病例讨论，至少2周1次</td>
          <td class="left">开展次数达标，且认真规范，得满分<br/>未达标或不规范，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="3.2.4"/>
          <td><input type="text" name="score" value="${scoreMap["3.2.4"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.2.4"]}</textarea></td>
          <td>
            <span id="3.2.4Span" style="display:${empty fileMap["3.2.4"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.2.4"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.2.4Up" href="javascript:uploadFile('3.2.4');" style="color:blue">${empty fileMap["3.2.4"]?'':'重新'}上传</a>
            <a id="3.2.4Del" href="javascript:delFile('3.2.4');"  style="color:blue;${empty fileMap["3.2.4"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.2.4Value" name="filePath" value="${fileMap["3.2.4"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td>3.3过程考核</td>
          <td class="left">3.3.1出科考核★</td>
          <td class="left">理论考核(如临床病例分析)试题、技能操作考核评分标准、培训对象测评结果、考勤记录等原始资料齐全，真实规范</td>
          <td class="left">随机抽查访谈本院、委培、社会招收培训对象各1～2名，检查近1年原始资料</td>
          <td class="left">考核项目全面，且认真规范，得满分<br/>
            仅有技能操作考核，得2分<br/>
            仅有理论考试，得1分<br/>
            仅有测评结果和考勤记录，得1分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="3.3.1"/>
          <td><input type="text" name="score" value="${scoreMap["3.3.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.3.1"]}</textarea></td>
          <td>
            <span id="3.3.1Span" style="display:${empty fileMap["3.3.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.3.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.3.1Up" href="javascript:uploadFile('3.3.1');" style="color:blue">${empty fileMap["3.3.1"]?'':'重新'}上传</a>
            <a id="3.3.1Del" href="javascript:delFile('3.3.1');"  style="color:blue;${empty fileMap["3.3.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.3.1Value" name="filePath" value="${fileMap["3.3.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td>3.4培训工作量</td>
          <td class="left">3.4.1培训强度★</td>
          <td class="left">按照专业基地培训对象临床技能操作能够达到《住院医师规范化培训内容与标准（试行）》麻醉科培训细则的要求</td>
          <td class="left">查看轮转手册等相关材料，随机抽查访谈本院、委培、社会招收培训对象各1～2名</td>
          <td class="left">独立技能操作种类及数量达要求，得满分<br/>
            独立技能操作≥规定数的80%，得3分<br/>
            独立技能操作≥规定数的60%，得1分<br/>
            ＜60%，或未安排独立操作，不得分</td>
          <td>6</td>
          <input type="hidden" name="orderNumber" value="3.4.1"/>
          <td><input type="text" name="score" value="${scoreMap["3.4.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["3.4.1"]}</textarea></td>
          <td>
            <span id="3.4.1Span" style="display:${empty fileMap["3.4.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.4.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.4.1Up" href="javascript:uploadFile('3.4.1');" style="color:blue">${empty fileMap["3.4.1"]?'':'重新'}上传</a>
            <a id="3.4.1Del" href="javascript:delFile('3.4.1');"  style="color:blue;${empty fileMap["3.4.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.4.1Value" name="filePath" value="${fileMap["3.4.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <th rowspan="4">4.质量控制（35分）</th>
          <td rowspan="2">4.1带教医师教学质量</td>
          <td class="left">4.1.1技能操作或手术带教情况★</td>
          <td class="left">带教医师协助并指导培训对象完成技能操作或手术，带教严格规范</td>
          <td class="left">随机抽查1～2名带教医师指导培训对象（二年级以上）进行技能操作或手术</td>
          <td class="left">1.培训对象操作前是否与患者交流、沟通情况1分<br/>
            2.培训对象操作中存在问题及时进行指导  1分<br/>
            3.培训对象操作结束后是否进行提问1分<br/>
            4.对培训对象的操作的总体评价（优、缺点点评)2分</td>
          <td>5</td>
          <input type="hidden" name="orderNumber" value="4.4.1"/>
          <td><input type="text" name="score" value="${scoreMap["4.4.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["4.4.1"]}</textarea></td>
          <td>
            <span id="4.4.1Span" style="display:${empty fileMap["4.4.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.4.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.4.1Up" href="javascript:uploadFile('4.4.1');" style="color:blue">${empty fileMap["4.4.1"]?'':'重新'}上传</a>
            <a id="4.4.1Del" href="javascript:delFile('4.4.1');"  style="color:blue;${empty fileMap["4.4.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.4.1Value" name="filePath" value="${fileMap["4.4.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">4.1.2技能操作或手术安排★</td>
          <td class="left">每个轮转科室均按照《住院医师规范化培训内容与标准（试行）》本专业培训细则要求执行，为每名培训对象安排并完成规定的技能操作和手术</td>
          <td class="left">随机抽查5～10名培训对象技能操作和手术记录，了解实际情况</td>
          <td class="left">完成率≥90%，得满分<br/>
            完成率≥80%，得5分<br/>
            完成率＜80%，不得分</td>
          <td>10</td>
          <input type="hidden" name="orderNumber" value="4.1.2"/>
          <td><input type="text" name="score" value="${scoreMap["4.1.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["4.1.2"]}</textarea></td>
          <td>
            <span id="4.1.2Span" style="display:${empty fileMap["4.1.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.1.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.1.2Up" href="javascript:uploadFile('4.1.2');" style="color:blue">${empty fileMap["4.1.2"]?'':'重新'}上传</a>
            <a id="4.1.2Del" href="javascript:delFile('4.1.2');"  style="color:blue;${empty fileMap["4.1.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.1.2Value" name="filePath" value="${fileMap["4.1.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td rowspan="2">4.2培训对象学习效果</td>
          <td class="left">4.2.1技能操作★</td>
          <td class="left">培训对象进行技能操作情况</td>
          <td class="left">随机抽查1～2名二年级以上培训对象进行技能操作，查看其掌握情况</td>
          <td class="left">技能操作评分表见附表2、3、4<br/>
            ≥90分得满分，≥80分得6分，≥70分得3分，≥60分得1分，＜60分不得分   </td>
          <td>10</td>
          <input type="hidden" name="orderNumber" value="4.2.1"/>
          <td><input type="text" name="score" value="${scoreMap["4.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["4.2.1"]}</textarea></td>
          <td>
            <span id="4.2.1Span" style="display:${empty fileMap["4.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.2.1Up" href="javascript:uploadFile('4.2.1');" style="color:blue">${empty fileMap["4.2.1"]?'':'重新'}上传</a>
            <a id="4.2.1Del" href="javascript:delFile('4.2.1');"  style="color:blue;${empty fileMap["4.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.2.1Value" name="filePath" value="${fileMap["4.2.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">4.2.2完成培训内容与要求★</td>
          <td class="left">按照本专业《住院医师规范化培训内容与标准（试行）》细则，核实培训内容的完成情况</td>
          <td class="left">随机抽查访谈本院、委培、社会招收培训对象各2～3名，查看轮转登记手册</td>
          <td class="left">完成率≥90%，得满分<br/>
            完成率≥85%，得6分<br/>
            完成率≥80%，得3分<br/>
            完成率＜80%，不得分</td>
          <td>10</td>
          <input type="hidden" name="orderNumber" value="4.2.2"/>
          <td><input type="text" name="score" value="${scoreMap["4.2.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["4.2.2"]}</textarea></td>
          <td>
            <span id="4.2.2Span" style="display:${empty fileMap["4.2.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.2.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.2.2Up" href="javascript:uploadFile('4.2.2');" style="color:blue">${empty fileMap["4.2.2"]?'':'重新'}上传</a>
            <a id="4.2.2Del" href="javascript:delFile('4.2.2');"  style="color:blue;${empty fileMap["4.2.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.2.2Value" name="filePath" value="${fileMap["4.2.2"]}">
          </td>
        </tr>
      </tbody>
      <tbody>
      <tr class="td_group">
        <td colspan="6" class="right">合计</td>
        <td>100</td>
        <input type="hidden" name="orderNumber" value="sumScore"/>
        <td><input id="inputSumScore" readonly name="score" value="${scoreMap["sumScore"]}"/></td>
        <td></td>
        <td></td>
      </tr>
      <tr class="td_group">
        <td class="right">存在问题</td>
        <td colspan="8" >
          <input type="hidden" name="orderNumber" value="problems"/>
          <textarea name="deductMarks" class="problems" rows="4" placeholder="" style="width:99%;resize:none;margin:3px; " >
            ${marksMap["problems"]}</textarea>
        <td><span id="problemsSpan" style="display:${empty fileMap["problems"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["problems"]}" target="_blank" style="color:blue">查看</a>
			</span>
          <a id="problemsUp" href="javascript:uploadFile('problems');" style="color:blue">${empty fileMap["problems"]?'':'重新'}上传</a>
          <a id="problemsDel" href="javascript:delFile('problems');"  style="color:blue;${empty fileMap["problems"]?'display:none':''}">删除</a>
          <input type="hidden" id="problemsValue" name="filePath" value="${fileMap["problems"]}"></td>
        </td>
      </tr>
      <tr class="td_group">
        <td colspan="10" style="text-align: left">
          备注：<br/>
          1.一级指标4项，二级指标10项，三级指标33项。三级指标中，核心指标13项、计70分，一般指标20项、计30分，共100分。<br/>
          2.指标中所有规章制度，专指住院医师规范化培训相关制度。<br/>
          3.随机抽查对象优先选择委托培训对象和面向社会招收的培训对象，如果没有，可考虑本基地培训对象。<br/>
          4.现场评估时详细填写存在的问题和扣分原因。
        </td>
      </tr>
      <tr class="td_group">
        <td colspan="10">
          专家姓名：
          <input type="hidden" name="orderNumber" value="sign"/>
          <input type="text" class="qtext" name="deductMarks" value="${marksMap["sign"]}"/>
          &#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
          评估日期：
          <input type="text" class="qtext" name="score" value="${scoreMap["sign"]}" id="signDate"
                 onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
        </td>
      </tr>
      </tbody>
    </table>

  </div>
    <div data-role="popup" id="myPopup" class="ui-content">
       <p id="errorMsg"></p>
    </div>
  
 <div data-role="footer">
 <h1></h1>
 </div>
</div> 
 </body>
 </html>