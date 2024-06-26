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
	<h1 style="text-align: center;font-size: 25px;">2017年住院医师规范化培训评估指标——全科专业（基层）基地</h1>
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
          <th rowspan="8">1.基本条件（20分）</th>
          <td rowspan="6">1.1专业基地所在社区卫生服务机构条件</td>
          <td class="left">1.1.1机构资质</td>
          <td class="left">辖区卫生行政部门设置的、在当地具有示范作用的社区卫生服务中心或乡镇卫生院</td>
          <td class="left">检查相关文件复印件，需加盖医院公章 ，实地考查</td>
          <td class="left">符合标准，得1分<br/>不达标准，不得分</td>
          <td>1</td>
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
          <td class="left">1.1.2辖区人口数</td>
          <td class="left">≥5万人</td>
          <td class="left">检查相关统计报表复印件，需加盖医院公章</td>
          <td class="left">符合标准，得1分<br/>不达标准，不得分</td>
          <td>1</td>
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
          <td class="left">1.1.3科室设置</td>
          <td class="left">必备科室:全科、预防保健科（含精神病防治）、中医科、康复科</td>
          <td class="left">查看相关文件，实地考查</td>
          <td class="left">缺1个科室，扣0.5分。 </td>
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
          <td class="left">1.1.4轮转科室</td>
          <td class="left">全科和预防保健科</td>
          <td class="left">查看培训对象轮转计划和登记手册，访谈培训对象</td>
          <td class="left">均有，得2分<br/>缺1个科室，不得分</td>
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
          <td class="left">1.1.5慢性病种类及数量★</td>
          <td class="left" rowspan="2">符合《住院医师规范化培训基地认定标准（试行）》及«住院医师规范化培训内容与标准(试行)》全科专业相关要求</td>
          <td class="left">核对上1年度常见慢性病种类和数量统计报表（按每名全科带教师资管理慢性病患者≥200人计算总数）</td>
          <td class="left">符合要求，得4分；<br/>
            ≥规定数的90%，得2分；<br/>
            ≥规定数的85%，＜90%，得1分；<br/>
            ＜规定数的85%，不得分。 </td>
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
          <td class="left">1.1.6临床技能操作种类及数量★</td>
          <td class="left">核对上1年度技能操作的种类和数量统计报表（按技能操作要求×培训学员数量计算） </td>
          <td class="left">符合要求，得4分；<br/>
            ≥规定数的90%，得2分；<br/>
            ≥规定数的85%，＜90%，得1分；<br/>
            ＜规定数85%，不得分。</td>
          <td>4</td>
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
          <td  rowspan="2">1.2组织管理</td>
          <td class="left">1.2.1有联合管理办法或规定★</td>
          <td class="left">基层实践基地有与临床基地的联合培养协议，并切实落实</td>
          <td class="left">查看联合培养协议等原始资料，核实相关信息</td>
          <td class="left">有管理办法，且落实，得2分；<br/>
            无或未落实，不得分。</td>
          <td>2</td>
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
          <td class="left">1.2.2基层实践基地与专业基地有紧密教学联系★</td>
          <td class="left">1.基层实践基地与临床基地有联合教学会议；<br/>
            2.送出带教医师参加临床基地或外出的师资培训； <br/>
            3.每年至少4次接受临床基地教学指导工作。</td>
          <td class="left">查看教学会议、师资培训、教学指导等原始材料，核实相关信息</td>
          <td class="left">
            第1、2项，每缺1项扣1分；  <br/>
            第3项，每少1次扣0.5分；   <br/>
            均无，不得分。</td>
          <td>4</td>
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
          <th rowspan="8">2.师资条件（15分）</th>
          <td rowspan="5">2.1师资情况</td>
          <td class="left">2.1.1带教医师与培训对象比例★</td>
          <td class="left">每名带教医师同时带教培训对象不超过2名</td>
          <td class="left">查看原始资料，访谈培训对象</td>
          <td class="left">不达标准，不得分</td>
          <td>2</td>
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
          <td class="left">1.医学专科及以上学历<br/>
            2.主治医师及以上专业职称<br/>
            3.有３年及以上社区工作经历<br/>
            4.全科师资的执业范围注册应为全科</td>
          <td class="left" rowspan="3">查看人事部门提供的师资状况统计表，包括姓名、毕业时间、毕业学校、学历学位、专业技术职务、专业技术职务任职时间、工作时间，需加盖人事部门公章</td>
          <td class="left">均符合，得2分；<br/> 每1人不符合，扣0.5分，扣完为止</td>
          <td>2</td>
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
          <td class="left">1.有５名以上带教医师组成；<br/> 2.均参加过省级及以上师资培训,并获得师资培训证书</td>
          <td class="left">每项1分</td>
          <td>2</td>
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
          <td class="left">医学专科及以上学历、主治医师及以上专业技术职务,并有5年及以上社区工作经历</td>
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
          <td class="left">2.1.5设立全科教学组</td>
          <td class="left">有全科培训小组，有条件的成立全科教研室</td>
          <td class="left">有人员组成名单、人员职责及在相关教学活动中出现的记录</td>
          <td class="left">有人员名单及在相关教学活动中出现的记录，得1分；<br/>    其他，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.1.5"/>
          <td><input type="text" name="score" value="${scoreMap["2.1.5"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["2.1.5"]}</textarea></td>
          <td>
            <span id="2.1.5Span" style="display:${empty fileMap["2.1.5"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.5"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.5Up" href="javascript:uploadFile('2.1.5');" style="color:blue">${empty fileMap["2.1.5"]?'':'重新'}上传</a>
            <a id="2.1.5Del" href="javascript:delFile('2.1.5');"  style="color:blue;${empty fileMap["2.1.5"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.5Value" name="filePath" value="${fileMap["2.1.5"]}">
          </td>
        </tr>
		
        <tr class="td_group">
          <td rowspan="3">2.2师资建设</td>
          <td class="left">2.2.1师资培训★</td>
          <td class="left">1.所有师资均参加过上级培训基地的师资培训<br/>
          <td class="left">查看培训资料、名单和培训证书</td>
          <td class="left">每项1分</td>
          <td>2</td>
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
          <td class="left">有评价方案，且记录详实，得2分<br/>
            仅有评价记录或评价方案，得1分<br/>
            均无，不得分</td>
          <td>2</td>
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
          <td class="left">有激励机制，并与职称、奖金、评优挂钩，得3分<br/>
            有激励机制，未与职称、奖金、评优挂钩，得1分<br/>
            无，不得分</td>
          <td>3</td>
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
          <th rowspan="10">3.过程管理（30分）</th>
          <td rowspan="6">3.1培训制度与落实</td>
          <td class="left">3.1.1主任职责</td>
          <td class="left">实行专业基地负责人负责制，并切实落实</td>
          <td class="left" rowspan="3">查看相关文件，访谈各类人员</td>
          <td class="left">职责明确，履职认真，得1分；<br/>
            职责不清或履职不认真，不得分。</td>
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
          <td class="left">3.1.2教学主任</td>
          <td class="left">设置兼职（或专职）教学主任岗位，负责本基地教学工作的组织实施</td>
          <td class="left">职责明确，履职认真，得1分；<br/>
            职责不清或履职不认真，不得分。</td>
          <td>1</td>
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
          <td class="left">设置兼职（或专职）教学秘书岗位，落实本基地教学工作</td>
          <td class="left">有教学秘书，履职认真，得1分；<br/>
            无，或履职不认真，不得分。</td>
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
          <td class="left">轮转科室（全科和预防保健科）成立教学小组，明确小组职责，定期组织研究教学工作</td>
          <td class="left">查看教学小组名单、职责和研究教学工作记录</td>
          <td class="left">有教学小组，履职认真，得1分；<br/>
            无，或履职不认真，不得分。</td>
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
          <td class="left">按规定落实轮转计划和要求（如按本省卫计委培训计划实施的，需注明并附该文件；若无具体要求，全科临床实践轮转时间应至少为3个月或以上）</td>
          <td class="left">查看2～3培训对象轮转手册等原始资料，访谈培训对象</td>
          <td class="left">轮转计划符合要求，且严格落实，得4分；<br/>
            轮转计划符合要求，但未严格落实，得1分；              <br/>                       其他，不得分。</td>
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
          <td class="left">有考勤规章制度，有专人负责，并严格执行</td>
          <td class="left">查看考勤规章制度，并抽查2～3名培训对象考勤记录原始资料</td>
          <td class="left">有，且严格落实，得2分；<br/>
            有，但未严格落实，得0.5分；           <br/>                          其他，不得分。</td>
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
          <td rowspan="2">3.2培训活动</td>
          <td class="left">3.2.1入科教育</td>
          <td class="left">规范实施，包括科室情况、科室纪律、培养计划与要求、医德医风、医患沟通等入科教育，并有专人组织实施</td>
          <td class="left">提供本年度入科教育原始资料</td>
          <td class="left">有，且严格落实，得2分；<br/>
            有，但未严格落实，得1分；                    <br/>                 其他，不得分。</td>
          <td>3</td>
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
          <td class="left">3.2.2小讲课、病例讨论等教学活动</td>
          <td class="left">开展规范的小讲课、病例讨论等教学活动，至少2周1次</td>
          <td class="left">查看原始资料，访谈培训对象</td>
          <td class="left">次数达标且内容有针对性，得4分；<br/>
            次数达标但内容针对性不强，得2分；              <br/>                   次数不达标或无针对性，得1分；      <br/>            无小讲课，不得分。</td>
          <td>4</td>
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
          <td>3.3过程考核</td>
          <td class="left">3.3.1出科考核★</td>
          <td class="left">理论考核试题、技能操作(大纲要求技能操作、临床病例分析等)考核评分标准、培训对象测评结果、考勤记录等原始资料齐全，真实规范</td>
          <td class="left">随机抽查访谈本院、委培、社会招收培训对象各1～2名，检查近1年原始资料</td>
          <td class="left">考核项目全面、认真规范，得7分；<br/>
            仅有技能操作考核评分标准及实操结果，得4分；<br/>
            仅有理论考试，得2分；<br/>
            仅有测评结果和考勤记录，得1分；<br/>                         无学员，不得分。</td>
          <td>7</td>
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
          <td>3.4培训强度</td>
          <td class="left">3.4.1独立接诊★</td>
          <td class="left">培训对象独立接诊，平均每日≥5人次</td>
          <td class="left">随机抽查1～2名培训对象</td>
          <td class="left">达到要求，得6分；<br/>
            ≥规定数的80%，得4分；<br/>
            ≥规定数的60%，＜80%，得2分； <br/>                ＜60%或无学员，不得分。</td>
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
          <th rowspan="5">4.质量控制（35分）</th>
          <td rowspan="2">4.1带教医师教学质量</td>
          <td class="left">4.1.1接诊指导质量★</td>
          <td class="left">接诊带教严格规范，悉心指导培训对象</td>
          <td class="left">随机抽查1～2名带教医师带教质量</td>
          <td class="left">评分表见附表1:<br/>
            ≥90分，得9分，<br/>                      ≥80分，＜90分，得6分，      <br/>           ≥70分，＜80分，得3分，   <br/>              ≥60分，＜70分，得1分，       <br/>          ＜60分不得分。</td>
          <td>9</td>
          <input type="hidden" name="orderNumber" value="4.1.1"/>
          <td><input type="text" name="score" value="${scoreMap["4.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["4.1.1"]}</textarea></td>
          <td>
            <span id="4.1.1Span" style="display:${empty fileMap["4.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.1.1Up" href="javascript:uploadFile('4.1.1');" style="color:blue">${empty fileMap["4.1.1"]?'':'重新'}上传</a>
            <a id="4.1.1Del" href="javascript:delFile('4.1.1');"  style="color:blue;${empty fileMap["4.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.1.1Value" name="filePath" value="${fileMap["4.1.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">4.1.2技能操作带教情况★</td>
          <td class="left">带教医师协助并指导培训对象完成技能操作，带教严格规范</td>
          <td class="left">随机抽查1～2名二年级以上培训对象进行技能操作，查看带教医师带教指导情况</td>
          <td class="left">评分表见附表2与附表3，“指导教师得分”部分的平均分<br/>
            ≥90分，得6分，   <br/>                 ≥80分，＜90分，得4分，    <br/>             ≥70分，＜80分，得2分，   <br/>              ≥60分，＜70分，得1分，   <br/>              ＜60分不得分。</td>
          <td>6</td>
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
          <td rowspan="3">4.2培训对象学习效果</td>
          <td class="left">4.2.1SOAP病历书写★</td>
          <td class="left">培训对象书写规范SOAP病历</td>
          <td class="left">随机抽查1～2名培训对象SOAP病历，结合病历提问题</td>
          <td class="left">SOAP病历书写评分表见附表2: <br/>                                                                                                           ≥90分，得7分，     <br/>                ≥80分，＜90分，得5分，  <br/>               ≥70分，＜80分，得3分，     <br/>            ≥60分，＜70分，得1分，          <br/>       ＜60分或无培训对象不得分。 </td>
          <td>7</td>
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
          <td class="left">4.2.2技能操作★</td>
          <td class="left">培训对象技能操作情况</td>
          <td class="left">随机抽查1～2名二年级以上培训对象进行技能操作，查看其掌握情况</td>
          <td class="left">技能操作评分表见附表3:<br/>
            ≥90分，得7分，   <br/>                ≥80分，＜90分，得5分，  <br/>               ≥70分，＜80分，得3分，    <br/>             ≥60分，＜70分，得1分，   <br/>              ＜60分或无学员不得分。</td>
          <td>7</td>
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
        <tr class="td_group">
          <td class="left">4.2.3完成培训内容与要求★</td>
          <td class="left">按照本专业《住院医师规范化培训内容与标准（试行）》细则，核实培训内容完成情况。</td>
          <td class="left">随机抽查访谈2～3名培训对象，查看轮转登记手册、出科考核等原始资料</td>
          <td class="left">完成率≥90%，得6分；<br/>
            85%≤完成率＜90%，得4分；<br/>
            80%≤完成率＜85%，得2分；<br/>
            完成率＜80%，或无学员不得分。</td>
          <td>6</td>
          <input type="hidden" name="orderNumber" value="4.2.3"/>
          <td><input type="text" name="score" value="${scoreMap["4.2.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["4.2.3"]}</textarea></td>
          <td>
            <span id="4.2.3Span" style="display:${empty fileMap["4.2.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.2.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.2.3Up" href="javascript:uploadFile('4.2.3');" style="color:blue">${empty fileMap["4.2.3"]?'':'重新'}上传</a>
            <a id="4.2.3Del" href="javascript:delFile('4.2.3');"  style="color:blue;${empty fileMap["4.2.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.2.3Value" name="filePath" value="${fileMap["4.2.3"]}">
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
          1.一级指标4项，二级指标10项，三级指标31项。三级指标中，核心指标16项、计75分，一般指标15项、计25分，共100分。<br/>
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