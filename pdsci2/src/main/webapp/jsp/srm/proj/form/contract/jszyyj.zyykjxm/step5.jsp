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
        function checkBDDate(dt) {
            var dates = $(':text', $(dt).closest("td"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                dt.value = "";
            }
        }

        function add(tb) {
            $("#" + tb + "Tb").append($("#" + tb + "Template tr:eq(0)").clone());
        }

        function delTr(tb) {
            //alert("input[name="+tb+"Ids]:checked");
            var checkboxs = $("input[name='" + tb + "Ids']:checked");
            if (checkboxs.length == 0) {
                jboxTip("请勾选要删除的！");
                return false;
            }
            jboxConfirm("确认删除?", function () {
                var trs = $('#' + tb + 'Tb').find(':checkbox:checked');
                $.each(trs, function (i, n) {
                    $(n).parent('td').parent("tr").remove();
                });
            });

        }

        $(document).ready(function () {
            if ($("#progressPlanTb tr").length <= 0) {
                add('progressPlan');
            }
        });
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step5"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">项目年度计划及考核指标</font>
    <table class="bs_tb" style="width: 100%;margin-top: 10px; border-bottom-style: none;">
        <tr>
            <th width="15%">年&#12288;&#12288;度</th>
            <th width="75%">项目年度计划及考核指标</th>
        </tr>
        <tbody id="progressPlanTb">
            <tr>
                <td style="text-align: center;">
                    <input class="inputText ctime" type="text" name="progressPlan_year1"
                           value="${resultMap.progressPlan_year1}"
                           onclick="WdatePicker({dateFmt:'yyyy年'})" readonly="readonly"
                           style="width: 100px; margin-right: 0px;text-align:left;"/>
                </td>
                <td style="text-align:left;">
                    <textarea placeholder="" class="validate[maxSize[1000]] xltxtarea"
                              name="progressPlan_content1">${resultMap.progressPlan_content1}</textarea>
                </td>
            </tr>
            <tr>
                <td style="text-align: center;">
                    <input class="inputText ctime" type="text" name="progressPlan_year2"
                           value="${resultMap.progressPlan_year2}"
                           onclick="WdatePicker({dateFmt:'yyyy年'})" readonly="readonly"
                           style="width: 100px; margin-right: 0px;text-align:left;"/>
                </td>
                <td style="text-align:left;">
                    <textarea placeholder="" class="validate[maxSize[1000]] xltxtarea"
                              name="progressPlan_content2">${resultMap.progressPlan_content2}</textarea>
                </td>
            </tr>
            <tr>
                <td style="text-align: center;">
                    <input class="inputText ctime" type="text" name="progressPlan_year3"
                           value="${resultMap.progressPlan_year3}"
                           onclick="WdatePicker({dateFmt:'yyyy年'})" readonly="readonly"
                           style="width: 100px; margin-right: 0px;text-align:left;"/>
                </td>
                <td style="text-align:left;">
                    <textarea placeholder="" class="validate[maxSize[1000]] xltxtarea"
                              name="progressPlan_content3">${resultMap.progressPlan_content3}</textarea>
                </td>
            </tr>
        </tbody>
    </table>

    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>

<div style="display: none">
    <!-- 模板 -->
    <table class="basic" id="progressPlanTemplate" style="width: 100%">
        <tr>
            <td width="4%" style="text-align: center;"><input name="progressPlanIds" type="checkbox"/></td>
            <td>
                <input class="inputText ctime" type="text" name="progressPlan_startTime"
                       onclick="WdatePicker({dateFmt:'yyyy年MM月'})" readonly="readonly"
                       style="width: 100px; margin-right: 0px;text-align:left;"/>
                ~<input class="inputText ctime" type="text" name="progressPlan_endTime"
                        onclick="WdatePicker({dateFmt:'yyyy年MM月'})" readonly="readonly"
                        style="width: 100px;text-align:left;"/>
            </td>
            <td style="text-align:left;">
                <textarea placeholder="" class="validate[maxSize[1000]] xltxtarea"
                          name="progressPlan_content"></textarea>
            </td>
        </tr>
    </table>
</div>