<c:if test="${param.view != GlobalConstant.FLAG_Y}">

    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append("<input name='nextPageName' value='" + step + "' type='hidden'/>");
            $('#nxt').attr({"disabled": "disabled"});
            $('#prev').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }
        function checkBDDate(dt){
            var dates = $(':text',$(dt).closest("td"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                dt.value = "";
            }
        }
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step5"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">四、推广计划进度</font>
    <table width="100%" cellspacing="0" cellpadding="0" class="basic">
        <tr>
            <th style="text-align: center;" width="10%">阶段</th>
            <th style="text-align: left;">
                &#12288;&#12288;（以2年为周期，按半年度划分工作节点，明确各阶段工作内容、进度与目标）
            </th>
        </tr>
        <tr>
            <td rowspan="2" style="text-align: center;">一</td>
            <td><span>起始时间：</span><input type="text" class="inputText validate[required]" name="startTimeOne" value="${resultMap.startTimeOne}"
                       onclick="WdatePicker({dateFmt:'yyyy 年 MM 月'})" readonly="readonly"
                       style="width: 20%" onchange="checkBDDate(this)"/>&#12288;至&#12288;
                <input type="text" class="inputText validate[required]" name="endTimeOne" value="${resultMap.endTimeOne}"
                       onclick="WdatePicker({dateFmt:'yyyy 年 MM 月'})" readonly="readonly"
                       style="width: 20%" onchange="checkBDDate(this)"/></td>
        </tr>
        <tr>
            <td><textarea name="planOne" style="width:90%;height: 150px;"
                         class="validate[maxSize[1000]]">${resultMap.planOne}</textarea></td>
        </tr>
        <tr>
            <td rowspan="2" style="text-align: center;">二</td>
            <td><span>起始时间：</span><input type="text" class="inputText validate[required]" name="startTimeTwo" value="${resultMap.startTimeTwo}"
                       onclick="WdatePicker({dateFmt:'yyyy 年 MM 月'})" readonly="readonly"
                       style="width: 20%" onchange="checkBDDate(this)"/>&#12288;至&#12288;
                <input type="text" class="inputText validate[required]" name="endTimeTwo" value="${resultMap.endTimeTwo}"
                       onclick="WdatePicker({dateFmt:'yyyy 年 MM 月'})" readonly="readonly"
                       style="width: 20%" onchange="checkBDDate(this)"/></td>
        </tr>
        <tr>
            <td><textarea name="planTwo" style="width:90%;height: 150px;"
                          class="validate[maxSize[1000]]">${resultMap.planTwo}</textarea></td>
        </tr>
        <tr>
            <td rowspan="2" style="text-align: center;">三</td>
            <td><span>起始时间：</span><input type="text" class="inputText validate[required]" name="startTimeThree" value="${resultMap.startTimeThree}"
                       onclick="WdatePicker({dateFmt:'yyyy 年 MM 月'})" readonly="readonly"
                       style="width: 20%" onchange="checkBDDate(this)"/>&#12288;至&#12288;
                <input type="text" class="inputText validate[required]" name="endTimeThree" value="${resultMap.endTimeThree}"
                       onclick="WdatePicker({dateFmt:'yyyy 年 MM 月'})" readonly="readonly"
                       style="width: 20%" onchange="checkBDDate(this)"/></td>
        </tr>
        <tr>
            <td><textarea name="planThree" style="width:90%;height: 150px;"
                          class="validate[maxSize[1000]]">${resultMap.planThree}</textarea></td>
        </tr>
        <tr>
            <td rowspan="2" style="text-align: center;">四</td>
            <td><span>起始时间：</span><input type="text" class="inputText validate[required]" name="startTimeFour" value="${resultMap.startTimeFour}"
                       onclick="WdatePicker({dateFmt:'yyyy 年 MM 月'})" readonly="readonly"
                       style="width: 20%" onchange="checkBDDate(this)"/>&#12288;至&#12288;
                <input type="text" class="inputText validate[required]" name="endTimeFour" value="${resultMap.endTimeFour}"
                       onclick="WdatePicker({dateFmt:'yyyy 年 MM 月'})" readonly="readonly"
                       style="width: 20%" onchange="checkBDDate(this)"/></td>
        </tr>
        <tr>
            <td><textarea name="planFour" style="width:90%;height: 150px;"
                          class="validate[maxSize[1000]]">${resultMap.planFour}</textarea></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: left;">
                &#12288;&#12288;本项目应于 <input type="text" class="inputText validate[required]" name="reportTime" value="${resultMap.reportTime}"
                                             onclick="WdatePicker({dateFmt:'yyyy 年 MM 月'})" readonly="readonly"
                                             style="width: 120px;"/><font color="red">*</font>
                       前完成中期进展报告，于<input type="text" class="inputText validate[required]" name="acceptTime" value="${resultMap.acceptTime}"
                                         onclick="WdatePicker({dateFmt:'yyyy 年 MM 月'})" readonly="readonly"
                                         style="width: 120px;"/><font color="red">*</font>前进行结题验收
            </td>
        </tr>
    </table>

    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
