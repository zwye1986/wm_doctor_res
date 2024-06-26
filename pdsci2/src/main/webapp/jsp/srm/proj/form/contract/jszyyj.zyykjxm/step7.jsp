<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            $('#nxt').attr({"disabled":"disabled"});
            $('#prev').attr({"disabled":"disabled"});
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }

        function calculation1(td,y){
            //横向计算
            var xSum = 0;
            for(var i=1;i<=3;i++){
                var sub = Number($(td).closest("tr").find("input").eq(i).val())
                if(sub){
                    xSum+=sub;
                }
            }
            $(td).closest("tr").find("input").eq(0).val(parseFloat(xSum.toFixed(4)));
            //纵向计算
            var ySum = 0;
            for(var i=1;i<=4;i++){
                var sub = Number($(td).closest("table").find("."+y).eq(i).val())
                if(sub){
                    ySum+=sub;
                }
            }
            $(td).closest("table").find("."+y).eq(0).val(parseFloat(ySum.toFixed(4)));
            //总和
            var sumAll = 0;
            for(var i=1;i<=3;i++){
                var sub = Number($(td).closest("table").find("tr").eq(1).find("input").eq(i).val())
                if(sub){
                    sumAll+=sub;
                }
            }
            $("input[name='amountMoneyAll']").val(parseFloat(sumAll.toFixed(4)));
        }

        function calculation2(td,y){
            //横向计算
            var xSum = 0;
            for(var i=1;i<=2;i++){
                var sub = Number($(td).closest("tr").find("input").eq(i).val())
                if(sub){
                    xSum+=sub;
                }
            }
            $(td).closest("tr").find("input").eq(0).val(parseFloat(xSum.toFixed(4)));
            //纵向计算
            var ySum = 0;
            for(var i=1;i<=9;i++){
                var sub = Number($(td).closest("table").find("."+y).eq(i).val())
                if(sub){
                    ySum+=sub;
                }
            }
            $(td).closest("table").find("."+y).eq(0).val(parseFloat(ySum.toFixed(4)));
            //总和
            var sumAll = 0;
            for(var i=1;i<=2;i++){
                var sub = Number($(td).closest("table").find("tr").eq(2).find("input").eq(i).val())
                if(sub){
                    sumAll+=sub;
                }
            }
            $("input[name='amountAll']").val(parseFloat(sumAll.toFixed(4)));
            //比重
            for(var i=1;i<=9;i++){
                var sumSub = Number($(td).closest("table").find("tr").eq(i+2).find("input").eq(0).val());
                var proportion = (sumSub/sumAll*100).toFixed(1);
                if(!isNaN(proportion)){
                    $(td).closest("table").find("tr").eq(i+2).find("input").eq(3).val(proportion);
                }else {
                    $(td).closest("table").find("tr").eq(i+2).find("input").eq(3).val("");
                }
            }
        }
    </script>
</c:if>
<style type="text/css">
    /*.basic tbody th {
        text-align: center;
    }*/
    .readonlycss {
        background-color: #EEEEEE;
    }
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
    <input type="hidden" id="pageName" name="pageName" value="step7"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">项目经费预算</font><br/><br/>
    （一）项目经费来源预算
    <span style="float: right;padding-right: 10px;">经费单位：万元</span>
    <table class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <th></th>
            <th style="text-align: center;padding-right: 0px;padding-left: 10px">合计</th>
            <th style="text-align: center;padding-right: 0px;padding-left: 10px"><input type="text" class="inputText" name="year1" value="${resultMap.year1}">年</th>
            <th style="text-align: center;padding-right: 0px;padding-left: 10px"><input type="text" class="inputText" name="year2" value="${resultMap.year2}">年</th>
            <th style="text-align: center;padding-right: 0px;padding-left: 10px"><input type="text" class="inputText" name="year3" value="${resultMap.year3}">年</th>
            <th style="text-align: center;padding-right: 0px;padding-left: 10px">备注</th>
        </tr>
        <tr>
            <td>合计</td>
            <td style="text-align: center"><input type="text" class="inputText" name="amountMoneyAll" value="${resultMap.amountMoneyAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="amountMoney1" value="${resultMap.amountMoney1}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="amountMoney2" value="${resultMap.amountMoney2}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 3" name="amountMoney3" value="${resultMap.amountMoney3}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks1" value="${resultMap.remarks1}"></td>
        </tr>
        <tr>
            <td>1、省拨款</td>
            <td style="text-align: center"><input type="text" class="inputText" name="provinceMoneyAll" value="${resultMap.provinceMoneyAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="provinceMoney1" value="${resultMap.provinceMoney1}" onchange="calculation1(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="provinceMoney2" value="${resultMap.provinceMoney2}" onchange="calculation1(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText 3" name="provinceMoney3" value="${resultMap.provinceMoney3}" onchange="calculation1(this,3)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks2" value="${resultMap.remarks2}"></td>
        </tr>
        <tr>
            <td>2、部门、地方配套</td>
            <td style="text-align: center"><input type="text" class="inputText" name="partMoneyAll" value="${resultMap.partMoneyAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="partMoney1" value="${resultMap.partMoney1}" onchange="calculation1(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="partMoney2" value="${resultMap.partMoney2}" onchange="calculation1(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText 3" name="partMoney3" value="${resultMap.partMoney3}" onchange="calculation1(this,3)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks3" value="${resultMap.remarks3}"></td>
        </tr>
        <tr>
            <td>3、承担单位自筹</td>
            <td style="text-align: center"><input type="text" class="inputText" name="selfMoneyAll" value="${resultMap.selfMoneyAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="selfMoney1" value="${resultMap.selfMoney1}" onchange="calculation1(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="selfMoney2" value="${resultMap.selfMoney2}" onchange="calculation1(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText 3" name="selfMoney3" value="${resultMap.selfMoney3}" onchange="calculation1(this,3)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks4" value="${resultMap.remarks4}"></td>
        </tr>
        <tr>
            <td>4、其他来源</td>
            <td style="text-align: center"><input type="text" class="inputText" name="otherMoneyAll" value="${resultMap.otherMoneyAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="otherMoney1" value="${resultMap.otherMoney1}" onchange="calculation1(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="otherMoney2" value="${resultMap.otherMoney2}" onchange="calculation1(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText 3" name="otherMoney3" value="${resultMap.otherMoney3}" onchange="calculation1(this,3)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks5" value="${resultMap.remarks5}"></td>
        </tr>
    </table><br/>
    （二）项目经费支出预算
    <span style="float: right;padding-right: 10px;">经费单位：万元</span>
    <table class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <th rowspan="2" style="text-align: center;padding-right: 0px;padding-left: 10px"></th>
            <th colspan="3" style="text-align: center;padding-right: 0px;padding-left: 10px">预算数</th>
            <th rowspan="2" style="text-align: center;padding-right: 0px;padding-left: 10px">占预算支出总额的比重（%）</th>
            <th rowspan="2" style="text-align: center;padding-right: 0px;padding-left: 10px">备注</th>
        </tr>
        <tr>
            <th style="text-align: center;padding-right: 0px;padding-left: 10px">合计</th>
            <th style="text-align: center;padding-right: 0px;padding-left: 10px">资助</th>
            <th style="text-align: center;padding-right: 0px;padding-left: 10px">自筹</th>
        </tr>
        <tr>
            <td>合计</td>
            <td style="text-align: center"><input type="text" class="inputText" name="amountAll" value="${resultMap.amountAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="amountOut" value="${resultMap.amountOut}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="amountIn" value="${resultMap.amountIn}" readonly="readonly"></td>
            <td style="text-align: center"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks6" value="${resultMap.remarks6}"></td>
        </tr>
        <tr>
            <td>1、人员费</td>
            <td style="text-align: center"><input type="text" class="inputText" name="peopleAll" value="${resultMap.peopleAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="peopleOut" value="${resultMap.peopleOut}" onchange="calculation2(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="peopleIn" value="${resultMap.peopleIn}" onchange="calculation2(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="peopleProportion" value="${resultMap.peopleProportion}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks7" value="${resultMap.remarks7}"></td>
        </tr>
        <tr>
            <td>2、设备费</td>
            <td style="text-align: center"><input type="text" class="inputText" name="equipmentAll" value="${resultMap.equipmentAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="equipmentOut" value="${resultMap.equipmentOut}" onchange="calculation2(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="equipmentIn" value="${resultMap.equipmentIn}" onchange="calculation2(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="equipmentProportion" value="${resultMap.equipmentProportion}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks8" value="${resultMap.remarks8}"></td>
        </tr>
        <tr>
            <td>3、能源材料费</td>
            <td style="text-align: center"><input type="text" class="inputText" name="energyMaterialsAll" value="${resultMap.energyMaterialsAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="energyMaterialsOut" value="${resultMap.energyMaterialsOut}" onchange="calculation2(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="energyMaterialsIn" value="${resultMap.energyMaterialsIn}" onchange="calculation2(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="energyMaterialsProportion" value="${resultMap.energyMaterialsProportion}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks9" value="${resultMap.remarks9}"></td>
        </tr>
        <tr>
            <td>4、试验外协费</td>
            <td style="text-align: center"><input type="text" class="inputText" name="experimentalOutsourcingAll" value="${resultMap.experimentalOutsourcingAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="experimentalOutsourcingOut" value="${resultMap.experimentalOutsourcingOut}" onchange="calculation2(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="experimentalOutsourcingIn" value="${resultMap.experimentalOutsourcingIn}" onchange="calculation2(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="experimentalOutsourcingProportion" value="${resultMap.experimentalOutsourcingProportion}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks10" value="${resultMap.remarks10}"></td>
        </tr>
        <tr>
            <td>5、差旅费</td>
            <td style="text-align: center"><input type="text" class="inputText" name="travelAll" value="${resultMap.travelAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="travelOut" value="${resultMap.travelOut}" onchange="calculation2(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="travelIn" value="${resultMap.travelIn}" onchange="calculation2(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="travelProportion" value="${resultMap.travelProportion}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks11" value="${resultMap.remarks11}"></td>
        </tr>
        <tr>
            <td>6、会议费</td>
            <td style="text-align: center"><input type="text" class="inputText" name="meetingsAll" value="${resultMap.meetingsAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="meetingsOut" value="${resultMap.meetingsOut}" onchange="calculation2(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="meetingsIn" value="${resultMap.meetingsIn}" onchange="calculation2(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="meetingsProportion" value="${resultMap.meetingsProportion}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks12" value="${resultMap.remarks12}"></td>
        </tr>
        <tr>
            <td>7、管理费</td>
            <td style="text-align: center"><input type="text" class="inputText" name="manageAll" value="${resultMap.manageAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="manageOut" value="${resultMap.manageOut}" onchange="calculation2(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="manageIn" value="${resultMap.manageIn}" onchange="calculation2(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="manageProportion" value="${resultMap.manageProportion}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks13" value="${resultMap.remarks13}"></td>
        </tr>
        <tr>
            <td>8、基建费</td>
            <td style="text-align: center"><input type="text" class="inputText" name="capitalConstructionAll" value="${resultMap.capitalConstructionAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="capitalConstructionOut" value="${resultMap.capitalConstructionOut}" onchange="calculation2(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="capitalConstructionIn" value="${resultMap.capitalConstructionIn}" onchange="calculation2(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="capitalConstructionProportion" value="${resultMap.capitalConstructionProportion}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks14" value="${resultMap.remarks14}"></td>
        </tr>
        <tr>
            <td>9、其他相关费用</td>
            <td style="text-align: center"><input type="text" class="inputText" name="otherAll" value="${resultMap.otherAll}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText 1" name="otherOut" value="${resultMap.otherOut}" onchange="calculation2(this,1)"></td>
            <td style="text-align: center"><input type="text" class="inputText 2" name="otherIn" value="${resultMap.otherIn}" onchange="calculation2(this,2)"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="otherProportion" value="${resultMap.otherProportion}" readonly="readonly"></td>
            <td style="text-align: center"><input type="text" class="inputText" name="remarks15" value="${resultMap.remarks15}"></td>
        </tr>
    </table>

</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('finish')" class="search" value="完&#12288;成"/>
    </div>
</c:if>	