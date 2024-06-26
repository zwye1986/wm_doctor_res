<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/jquery.jqprint-0.3.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
            text-align: center;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
            var value = sessionStorage.getItem("tabFlag");
            if (value != null) {
                selectTag($("#"+value+"Label"),value);
            }else{
                selectTag($("#budgetLabel"),"budget");
                sessionStorage.setItem("tabFlag", "budget");
            }
            countTotal();
        });
        function selectTag(selfObj,url) {
            var selLi = $(selfObj).parent();
            selLi.siblings("li").removeClass("selectTag");
            selLi.addClass("selectTag");
            $("div.spreadOne").hide();
            $("#"+url).show();
            sessionStorage.setItem("tabFlag", url);
        }
        function saveCost(){
            var value = sessionStorage.getItem("tabFlag");
            var form = $("#"+value+"Form");
            if(!form.validationEngine("validate")){
                return;
            }
            if(getByteLen($("textarea[name='feeDesc']").val())>500){
                jboxTip("费用说明不能超过250字！");
                return;
            }
            jboxPost("<s:url value='/gyxjgl/abroadApply/saveCost'/>", form.serialize(), function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    location.reload();
                }
            });
        }
        function countTotal(){
            var count = 0;
            $("input[name*='Subtotal']").each(function(){
                var score = $(this).val();
                if($.trim(score)!="" && !isNaN(score)){
                    count+=parseFloat(score);
                }
            });
            $("#totalLabel").html(count.toFixed(2));
            $("#totalLabel1").html(count.toFixed(2));
        }
        //获取字符串长度（汉字算两个字符，字母数字算一个）
        function getByteLen(val) {
            var len = 0;
            if(val!=null&&val!=undefined&&val!='undefined'){
                for (var i = 0; i < val.length; i++) {
                    var a = val.charAt(i);
                    if (a.match(/[^\x00-\xff]/ig) != null) {
                        len += 2;
                    }
                    else {
                        len += 1;
                    }
                }
            }
            return len;
        }
        function printOpt(){
            var value = sessionStorage.getItem("tabFlag");
            jboxTip("正在准备打印…");
            $("#printDivIframe"+value).removeAttr("hidden");
            setTimeout(function(){
                $("#printDivIframe"+value).jqprint({
                    debug: false,
                    importCSS: true,
                    printContainer: true,
                    operaSupport: false
                });
                $("#printDivIframe"+value).attr("hidden","hidden");
                jboxEndLoading();
                return false;
            },2000);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="background-color:white;height:20px;width:100%;"></div>
        <div>
            <div style="margin-bottom: 8px;width:100%;text-align: left;">
                <c:if test="${roleFlag eq 'student'||roleFlag eq 'tutor'}">
                    <input type="button" class="search" onclick="saveCost();" value="保&#12288;存"/>
                </c:if>
                <input type="button" class="search" onclick="printOpt();" value="打&#12288;印"/>
            </div>
            <ul id="tags">
                <li>
                    <a onclick="selectTag(this,'explain')" href="javascript:void(0)" id="explainLabel">费用说明登记</a>
                </li>
                <li>
                    <a onclick="selectTag(this,'budget')" href="javascript:void(0)" id="budgetLabel">出国费用预算登记</a>
                </li>
            </ul>
            <div id="tagContent" style="margin-top:3px;"></div>
            <div class="spreadOne" hidden="hidden" id="budget">
                <div class="spreadOneTwo">
                    <form id="budgetForm">
                        <input type="hidden" name="recordFlow" value="${abroadApply.recordFlow}">
                        <input type="hidden" name="tabFlag" value="budget">
                        <div style="text-align: center;width: 100%;">
                            <font style="font-weight:bold;font-size: 16px;">出国费用预算登记</font>
                        </div>
                        <table class="gridtable" style="width:100%; margin-top:3px; margin-bottom:8px;">
                            <tr>
                                <th style="width: 20%;">开支项目名称</th>
                                <th style="width: 30%;">开支标准</th>
                                <th style="width: 15%;">天数</th>
                                <th style="width: 15%;">人数</th>
                                <th style="width: 20%;">小计（折合人民币）</th>
                            </tr>
                            <tr>
                                <td>
                                    国际旅费
                                    <input type="hidden" name="gjlfName" value="国际旅费"/>
                                </td>
                                <td>
                                    <input type="text" class="validate[required]" name="gjlfStandard" value="${abroadApply.gjlfStandard}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'} >
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="gjlfDay" value="${abroadApply.gjlfDay}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="gjlfPeople" value="${abroadApply.gjlfPeople}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[number]]" name="gjlfSubtotal" value="${abroadApply.gjlfSubtotal}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'} onchange="countTotal();">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    国外城市间交通费
                                    <input type="hidden" name="gwcsjtfName" value="国外城市间交通费"/>
                                </td>
                                <td>
                                    <input type="text" class="validate[required]" name="gwcsjtfStandard" value="${abroadApply.gwcsjtfStandard}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="gwcsjtfDay" value="${abroadApply.gwcsjtfDay}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="gwcsjtfPeople" value="${abroadApply.gwcsjtfPeople}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[number]]" name="gwcsjtfSubtotal" value="${abroadApply.gwcsjtfSubtotal}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'} onchange="countTotal();">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    住宿费
                                    <input type="hidden" name="zsfName" value="住宿费"/>
                                </td>
                                <td>
                                    <input type="text" class="validate[required]" name="zsfStandard" value="${abroadApply.zsfStandard}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="zsfDay" value="${abroadApply.zsfDay}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="zsfPeople" value="${abroadApply.zsfPeople}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[number]]" name="zsfSubtotal" value="${abroadApply.zsfSubtotal}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'} onchange="countTotal();">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    伙食费
                                    <input type="hidden" name="hsfName" value="伙食费"/>
                                </td>
                                <td>
                                    <input type="text" class="validate[required]" name="hsfStandard" value="${abroadApply.hsfStandard}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="hsfDay" value="${abroadApply.hsfDay}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="hsfPeople" value="${abroadApply.hsfPeople}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[number]]" name="hsfSubtotal" value="${abroadApply.hsfSubtotal}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'} onchange="countTotal();">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    公杂费
                                    <input type="hidden" name="gzfName" value="公杂费"/>
                                </td>
                                <td>
                                    <input type="text" class="validate[required]" name="gzfStandard" value="${abroadApply.gzfStandard}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="gzfDay" value="${abroadApply.gzfDay}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="gzfPeople" value="${abroadApply.gzfPeople}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[number]]" name="gzfSubtotal" value="${abroadApply.gzfSubtotal}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'} onchange="countTotal();">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    其他费用（注明名称）
                                    <input type="hidden" name="otherName" value="其他费用（注明名称）"/>
                                </td>
                                <td>
                                    <input type="text" class="validate[required]" name="otherStandard" value="${abroadApply.otherStandard}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="otherDay" value="${abroadApply.otherDay}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[integer]]" name="otherPeople" value="${abroadApply.otherPeople}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'}>
                                </td>
                                <td>
                                    <input type="text" class="validate[required,custom[number]]" name="otherSubtotal" value="${abroadApply.otherSubtotal}" style="width:85%;" ${roleFlag eq 'student'?'':'readonly'} onchange="countTotal();">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    合&emsp;&emsp;计
                                </td>
                                <td>
                                    <label id="totalLabel"></label>
                                </td>
                            </tr>
                        </table>
                        <font style="font-weight:bold;">财务审核意见栏：</font>
                        <div style="width: 100%;text-align: right;"><br/><br/>
                            财务部门（公章）&emsp;<br/>
                            年&emsp;&emsp;月&emsp;&emsp;日&emsp;
                        </div>
                    </form>
                </div>
            </div>
            <div class="spreadOne" hidden="hidden" id="explain">
                <div class="spreadOneThree">
                    <form id="explainForm">
                        <input type="hidden" name="recordFlow" value="${abroadApply.recordFlow}">
                        <input type="hidden" name="tabFlag" value="explain">
                        <table class="gridtable" style="width:100%; margin-top:3px; margin-bottom:30px;">
                            <tr>
                                <td>
                                    <font style="font-weight:bold;font-size: 16px;">费用说明</font><br/>
                                    <textarea style="width: 98%;height: 150px;" name="feeDesc" placeholder="示例：张三，学号11111，基础医学院2017级博士生，去往美国威尔康奈尔医学院学习访问一年，共计花费人民币10万元。其中，广东省省级科技计划项目支出5万元，国家科技支撑计划与国家自然科学基金支出5万元。具体预算详情见预算明细表。">${abroadApply.feeDesc}</textarea>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div hidden="hidden" id="printDivIframebudget" name="printDivIframebudget">
    <div>
        <div style="text-align: center;width: 100%;">
            <font style="font-weight:bold;font-size: 22px;margin-top:10px;">出国费用预算登记</font>
        </div>
        <table class="xllist" style="width:100%; margin-top:10px; margin-bottom:8px;">
            <tr>
                <th style="width: 20%;">开支项目名称</th>
                <th style="width: 30%;">开支标准</th>
                <th style="width: 15%;">天数</th>
                <th style="width: 15%;">人数</th>
                <th style="width: 20%;">小计（折合人民币）</th>
            </tr>
            <tr>
                <td>国际旅费</td>
                <td>${abroadApply.gjlfStandard}</td>
                <td>${abroadApply.gjlfDay}</td>
                <td>${abroadApply.gjlfPeople}</td>
                <td>${abroadApply.gjlfSubtotal}</td>
            </tr>
            <tr>
                <td>国外城市间交通费</td>
                <td>${abroadApply.gwcsjtfStandard}</td>
                <td>${abroadApply.gwcsjtfDay}</td>
                <td>${abroadApply.gwcsjtfPeople}</td>
                <td>${abroadApply.gwcsjtfSubtotal}</td>
            </tr>
            <tr>
                <td>住宿费</td>
                <td>${abroadApply.zsfStandard}</td>
                <td>${abroadApply.zsfDay}</td>
                <td>${abroadApply.zsfPeople}</td>
                <td>${abroadApply.zsfSubtotal}</td>
            </tr>
            <tr>
                <td>伙食费</td>
                <td>${abroadApply.hsfStandard}</td>
                <td>${abroadApply.hsfDay}</td>
                <td>${abroadApply.hsfPeople}</td>
                <td>${abroadApply.hsfSubtotal}</td>
            </tr>
            <tr>
                <td>公杂费</td>
                <td>${abroadApply.gzfStandard}</td>
                <td>${abroadApply.gzfDay}</td>
                <td>${abroadApply.gzfPeople}</td>
                <td>${abroadApply.gzfSubtotal}</td>
            </tr>
            <tr>
                <td>其他费用（注明名称）</td>
                <td>${abroadApply.otherStandard}</td>
                <td>${abroadApply.otherDay}</td>
                <td>${abroadApply.otherPeople}</td>
                <td>${abroadApply.otherSubtotal}</td>
            </tr>
            <tr>
                <td colspan="4">
                    合&emsp;&emsp;计
                </td>
                <td>
                    <label id="totalLabel1"></label>
                </td>
            </tr>
        </table>
        <font style="font-weight:bold;">财务审核意见栏：</font>
        <div style="width: 100%;text-align: right;"><br/><br/>
            财务部门（公章）&emsp;<br/>
            年&emsp;&emsp;月&emsp;&emsp;日&emsp;
        </div>
    </div>
</div>
<div hidden="hidden" id="printDivIframeexplain" name="printDivIframeexplain">
    <div>
        <div style="text-align: center;width: 100%;margin-top:30px;">
            <font style="font-weight:bold;font-size: 22px;margin-top:30px;margin-bottom:30px;">费用说明</font><br/><br/>
            <textarea style="width: 98%;height: 300px;">${abroadApply.feeDesc}</textarea>
        </div>
    </div>
</div>
</body>
</html>