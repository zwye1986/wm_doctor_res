<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        //身份证号合法性验证
        //支持15位和18位身份证号
        //支持地址编码、出生日期、校验位验证
        function identityCodeValid(code) {
            var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
            var tip = "身份证号码输入错误";
            var pass= true;
            if(!code || !/^(([1][1-5])|([2][1-3])|([3][1-7])|([4][1-6])|([5][0-4])|([6][1-5])|([7][1])|([8][1-2]))\d{4}(([1][9]\d{2})|([2]\d{3}))(([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|([3][0-1]))\d{3}[0-9xX]$/.test(code)){
//                tip = "身份证号格式错误";
                pass = false;
            }else if(!city[code.substr(0,2)]){
//                tip = "地址编码错误";
                pass = false;
            }else{
                //18位身份证需要验证最后一位校验位
                if(code.length == 18){
                    code = code.split('');
                    //加权因子
                    var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                    //校验位
                    var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                    var sum = 0;
                    var ai = 0;
                    var wi = 0;
                    for (var i = 0; i < 17; i++) {
                        ai = code[i];
                        wi = factor[i];
                        sum += ai * wi;
                    }
                    var last = parity[sum % 11];
                    if(parity[sum % 11] != code[17]){
//                        tip = "校验位错误";
                        pass =false;
                    }
                }
            }
            if(!pass) jboxTip(tip);
            return pass;
        }
        function isHKTWCard(card) {
            // 港澳居民来往内地通行证
            // 规则： H/M + 10位或6位数字
            // 样本： H1234567890
            var reg = /^([A-Z]\d{6,10}(\(\w{1}\))?)$/;
            // 台湾居民来往大陆通行证
            // 规则： 新版8位或18位数字， 旧版10位数字 + 英文字母
            // 样本： 12345678 或 1234567890B
            var reg2 = /^\d{8}|^[a-zA-Z0-9]{10}|^\d{18}$/;
            if(!reg.test(card) && !reg2.test(card)) jboxTip("港澳台居民居住证编码不合规");
            return reg.test(card) || reg2.test(card)?true:false;
        }
        function bindOpt(obj){
            if($(obj).val()==""){
                $(obj).next().val("");
            }else{
                $(obj).next().val($(obj).find("option:selected").text());
            }
            var value = $(obj).parents("tr").next().find("input").val().trim();
            if($(obj).val()=="1" && value != ""){
                //二代身份证编码
                if(!identityCodeValid(value)){
                    $(obj).parents("tr").next().find("input").val("");
                    return;
                }
            }else if($(obj).val()=="C" && value != ""){
                //港澳台身份证编码
                if(!isHKTWCard(value)){
                    $(obj).parents("tr").next().find("input").val("");
                    return;
                }
            }
        }
        function bindVal(obj){
            var zjlx = $(obj).parents("tr").prev().find("select option:selected").val();
            if($(obj).val().trim()!=""){
                if(zjlx == "1"){
                    if(!identityCodeValid($(obj).val().trim())){
                        $(obj).val("");
                        return;
                    }
                }else if(zjlx == "C"){
                    if(!isHKTWCard($(obj).val().trim())){
                        $(obj).val("");
                        return;
                    }
                }
            }
        }
        <c:if test="${param.role eq 'xs'&& (empty supple || supple.submitFlag ne 'Y')}">
            $(function(){
                var html = "<div>各位研究生在核准和补录信息前，请详细阅读以下事项：</div>" +
                        "<div>&#12288;&#12288;1.研究生相关数据信息核准和补录是个税改革“子女教育专项附加扣除”和“继续教育专项附加扣除”精准实施的重要前提。</div>" +
                        "<div>&#12288;&#12288;2.需要核准和补录信息内容：</div>" +
                        "<div>&#12288;&#12288;（1）核准信息内容：学信网学籍学历信息管理平台上的数据信息和研究生信息核准补录系统的学籍信息，包括学号、姓名、身份证号、入学日期等，确保数据信息对应一致；</div>" +
                        "<div>&#12288;&#12288;（2）补录信息内容：按照学生和家长<font color='red'>自愿原则</font>补录学生父母或监护人信息。对父母或监护人有个人所得税中子女教育专项附加扣除需求的研究生，需按要求在研究生教育管理系统中补录“父母或监护人姓名”、“父母或监护人身份证件类别”、“父母或监护人身份证件号码”三项信息，研究生可以填报父母双方信息，也可只填报一方信息。</div>" +
                        "<div>&#12288;&#12288;3.请各位研究生要充分认识到信息核准和补录工作的目的与重要性，研究生可提前与父母或监护人沟通，确认是否有个人所得税子女教育专项附加扣除需求，明确是否需要进行相关信息采集。因此，<font color='red'>在填报前征得父母或监护人的同意，并对核准和补录的信息负责，填报错误者，后果自负</font>。</div>" +
                        "<div>&#12288;&#12288;4.核准和补录完毕后，请再次认真核对信息，确认无误填写后再提交。</div>";
                jboxOpenContent(html,"温馨提示","460","500",true);
            });
        </c:if>
        function subOpt(flag){
            jboxConfirm("确认补录信息无误后"+(flag=='save'?'保存':'提交')+"？",function(){
                if(flag=='save' && '${param.role}'=='xs'){
                    $("input[name='submitFlag']").val("N");
                }
                jboxPost("<s:url value="/gyxjgl/user/saveSuppleInfo"/>",$("#saveForm").serialize(),function(){
                    if("${param.role}"=="xs"){
                        window.location.reload();
                    }else{
                        window.parent.frames['mainIframe'].window.search();
                        jboxClose();
                    }
                },null,true);
            });
        }
        function printInfo(){
            jboxTip("打印中，请稍等...");
            setTimeout(function(){
                var headstr = "<html><head><title></title></head><body>";
                var footstr = "</body>";
                var newstr = $("#printDiv").html();
                var oldstr = document.body.innerHTML;
                document.body.innerHTML = headstr+newstr+footstr;
                window.print();
                document.body.innerHTML = oldstr;
                return false;
            },3000);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content" style="padding:10px;">
        <form id="saveForm" method="post">
            <input type="hidden" name="userFlow" value="${empty supple?user.userFlow:supple.userFlow}">
            <c:if test="${param.role eq 'xs'}"><input type="hidden" name="submitFlag" value="Y"></c:if>
            <div style="margin:10px 0px 30px 150px;font-size:16px;font-weight:bold">南方医科大学研究生数据信息核准与补录表</div>
            <table class="basic" style="min-width:600px;max-width:600px;">
                <tr>
                    <td style="width:40%">学号</td>
                    <td style="width: 60%"><input type="text" name="xh" value="${empty supple?eduUser.sid:supple.xh}"></td>
                </tr>
                <tr>
                    <td>学生姓名</td>
                    <td><input type="text" name="xm" value="${empty supple?user.userName:supple.xm}"></td>
                </tr>
                <tr>
                    <td>学生身份证件类型</td>
                    <td>
                        <select style="width: 157px;" name="zjlxId" class="inputText" onchange="bindOpt(this)">
                            <option/>
                            <option value="1" ${supple.zjlxId eq '1'?'selected':''}>居民身份证</option>
                            <option value="6" ${supple.zjlxId eq '6'?'selected':''}>香港特区护照/身份证明</option>
                            <option value="7" ${supple.zjlxId eq '7'?'selected':''}>澳门特区护照/身份证明</option>
                            <option value="8" ${supple.zjlxId eq '8'?'selected':''}>台湾居民来往大陆通行证</option>
                            <option value="9" ${supple.zjlxId eq '9'?'selected':''}>境外永久居住证</option>
                            <option value="A" ${supple.zjlxId eq 'A'?'selected':''}>护照</option>
                            <option value="C" ${supple.zjlxId eq 'C'?'selected':''}>港澳台居民居住证</option>
                        </select>
                        <input type="hidden" name="zjlx" value="${supple.zjlx}">
                    </td>
                </tr>
                <tr>
                    <td>学生身份证件号码</td>
                    <td><input type="text" name="zjhm" value="${empty supple?user.idNo:supple.zjhm}" onchange="bindVal(this)"></td>
                </tr>
                <c:if test="${empty param.role}">
                    <tr>
                        <td>学生是否在职</td>
                        <td>
                            <select style="width: 157px;" name="sfzz" class="inputText">
                                <option/>
                                <option value="是" ${supple.sfzz eq '是'?'selected':''}>是</option>
                                <option value="否" ${supple.sfzz eq '否'?'selected':''}>否</option>
                            </select>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>入学日期</td>
                    <td><input type="text" style="width:150px;text-align:left;" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="inputText ctime" name="rxrq" value="${supple.rxrq}"/></td>
                </tr>
                <c:if test="${empty param.role}">
                    <tr>
                        <td>学籍状态</td>
                        <td>
                            <select style="width: 157px;" name="xjztId" class="inputText" onchange="bindOpt(this)">
                                <option/>
                                <option value="zcxj" ${supple.xjztId eq 'zcxj'?'selected':''}>注册学籍</option>
                                <option value="zhzc" ${supple.xjztId eq 'zhzc'?'selected':''}>暂缓注册</option>
                                <option value="xx" ${supple.xjztId eq 'xx'?'selected':''}>休学</option>
                                <option value="blxj" ${supple.xjztId eq 'blxj'?'selected':''}>保留学籍</option>
                            </select>
                            <input type="hidden" name="xjzt" value="${supple.xjzt}">
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>父母或监护人1姓名</td>
                    <td><input type="text" name="fmxm1" value="${supple.fmxm1}"></td>
                </tr>
                <tr>
                    <td>父母或监护人1身份证件类型</td>
                    <td>
                        <select style="width: 157px;" name="fmzjlx1Id" class="inputText" onchange="bindOpt(this)">
                            <option/>
                            <option value="1" ${supple.fmzjlx1Id eq '1'?'selected':''}>居民身份证</option>
                            <option value="6" ${supple.fmzjlx1Id eq '6'?'selected':''}>香港特区护照/身份证明</option>
                            <option value="7" ${supple.fmzjlx1Id eq '7'?'selected':''}>澳门特区护照/身份证明</option>
                            <option value="8" ${supple.fmzjlx1Id eq '8'?'selected':''}>台湾居民来往大陆通行证</option>
                            <option value="9" ${supple.fmzjlx1Id eq '9'?'selected':''}>境外永久居住证</option>
                            <option value="A" ${supple.fmzjlx1Id eq 'A'?'selected':''}>护照</option>
                            <option value="C" ${supple.fmzjlx1Id eq 'C'?'selected':''}>港澳台居民居住证</option>
                        </select>
                        <input type="hidden" name="fmzjlx1" value="${supple.fmzjlx1}"/>
                    </td>
                </tr>
                <tr>
                    <td>父母或监护人1身份证件号码</td>
                    <td><input type="text" name="fmzjhm1" value="${supple.fmzjhm1}" onchange="bindVal(this)"></td>
                </tr>
                <tr>
                    <td>父母或监护人2姓名</td>
                    <td><input type="text" name="fmxm2" value="${supple.fmxm2}"></td>
                </tr>
                <tr>
                    <td>父母或监护人2身份证件类型</td>
                    <td>
                        <select style="width: 157px;" name="fmzjlx2Id" class="inputText" onchange="bindOpt(this)">
                            <option/>
                            <option value="1" ${supple.fmzjlx2Id eq '1'?'selected':''}>居民身份证</option>
                            <option value="6" ${supple.fmzjlx2Id eq '6'?'selected':''}>香港特区护照/身份证明</option>
                            <option value="7" ${supple.fmzjlx2Id eq '7'?'selected':''}>澳门特区护照/身份证明</option>
                            <option value="8" ${supple.fmzjlx2Id eq '8'?'selected':''}>台湾居民来往大陆通行证</option>
                            <option value="9" ${supple.fmzjlx2Id eq '9'?'selected':''}>境外永久居住证</option>
                            <option value="A" ${supple.fmzjlx2Id eq 'A'?'selected':''}>护照</option>
                            <option value="C" ${supple.fmzjlx2Id eq 'C'?'selected':''}>港澳台居民居住证</option>
                        </select>
                        <input type="hidden" name="fmzjlx2" value="${supple.fmzjlx2}"/>
                    </td>
                </tr>
                <tr>
                    <td>父母或监护人2身份证件号码</td>
                    <td><input type="text" name="fmzjhm2" value="${supple.fmzjhm2}" onchange="bindVal(this)"></td>
                </tr>
            </table>
        </form>
        <div style="width:600px;line-height:25px;">
            <div style="font-weight: bold">备注：</div>
            <div>&#12288;&#12288;1.填写并核准—已有的学籍信息（学信网）是否与实际内容一致，主要包括：学号、姓名、身份证号、入学日期；</div>
            <div>&#12288;&#12288;2.填写补录信息—按照学生和家长自愿原则及时填报父母和监护人信息，主要包括：父母或监护人姓名、父母或监护人身份证号码。</div>
            <div>&#12288;&#12288;3.学生可以填报父母双方信息，也可以只填报一方信息，在职的学生不用填报父母信息；</div>
            <div>&#12288;&#12288;4.学生和父母的身份证件类型可选：1-居民身份证、6-香港特区护照/身份证明、7-澳门特区护照/身份证明、8-台湾居民来往大陆通行证、9-境外永久居住证、护照、C-港澳台居民居住证；</div>
            <div>&#12288;&#12288;5.学生和父母的姓名均以有效身份证件为准，分隔符用“·”，生僻字用大写汉语拼音代替（不含音调）；</div>
            <div>&#12288;&#12288;6.若证件类型是“1-居民身份证”，则证件号码需符合二代居民身份证编码规则；若证件类型是“C-港澳台居民居住证”，则证件号码需符合港澳台居民居住证编码规则。 </div>
        </div>
        <div style="float:left;margin:10px 0px 0px 260px;">
            <c:if test="${param.role eq 'xs' && (empty supple || supple.submitFlag ne 'Y')}">
                <input type="button" class="search" onclick="subOpt('save');" value="保&#12288;存">
                <input type="button" class="search" onclick="subOpt();" value="提&#12288;交">
            </c:if>
                <%--管理员角色--%>
            <c:if test="${empty param.role}">
                <input type="button" class="search" onclick="subOpt('save');" value="保&#12288;存">
            </c:if>
            <c:if test="${param.role eq 'xs' && supple.submitFlag eq 'Y'}">
                <input type="button" class="search" onclick="printInfo();" value="打&#12288;印">
            </c:if>
        </div>
    </div>
</div>
<div id="printDiv" style="display:none;">
    <div style="margin:10px 0px 30px 150px;font-size:16px;font-weight:bold">南方医科大学研究生数据信息核准与补录表</div>
    <table class="basic" style="min-width:600px;max-width:600px;">
        <tr>
            <td style="width:40%">学号</td>
            <td style="width: 60%">&ensp;${supple.xh}</td>
        </tr>
        <tr>
            <td>学生姓名</td>
            <td>&ensp;${supple.xm}</td>
        </tr>
        <tr>
            <td>学生身份证件类型</td>
            <td>&ensp;${supple.zjlx}</td>
        </tr>
        <tr>
            <td>学生身份证件号码</td>
            <td>&ensp;${supple.zjhm}</td>
        </tr>
        <tr>
            <td>入学日期</td>
            <td>&ensp;${supple.rxrq}</td>
        </tr>
        <tr>
            <td>父母或监护人1姓名</td>
            <td>&ensp;${supple.fmxm1}</td>
        </tr>
        <tr>
            <td>父母或监护人1身份证件类型</td>
            <td>&ensp;${supple.fmzjlx1}</td>
        </tr>
        <tr>
            <td>父母或监护人1身份证件号码</td>
            <td>&ensp;${supple.fmzjhm1}</td>
        </tr>
        <tr>
            <td>父母或监护人2姓名</td>
            <td>&ensp;${supple.fmxm2}</td>
        </tr>
        <tr>
            <td>父母或监护人2身份证件类型</td>
            <td>&ensp;${supple.fmzjlx2}</td>
        </tr>
        <tr>
            <td>父母或监护人2身份证件号码</td>
            <td>&ensp;${supple.fmzjhm2}</td>
        </tr>
        <tr>
            <td colspan="2">
                <div>本人承诺：</div>
                <div>&#12288;&#12288;以上所填写信息全部真实，签字确认后不再更改，由此造成的一切后果由本人负责。</div>
                <div style="padding:30px 0px 0px 260px;">学生签名：</div>
                <div><span style="padding-left:260px;"></span>年&#12288;&#12288;月&#12288;&#12288;日</div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>