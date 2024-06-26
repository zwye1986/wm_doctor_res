<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        var ZJLX = {"01":"居民身份证","03":"港澳台身份证","04":"华侨身份证件"};
        var HFM = {"1":"未婚","2":"已婚","3":"丧偶","4":"离婚","9":"其他"};
        var XYJRM = {"1":"军队在职干部","2":"军校应届本科毕业生","3":"国防生","0":"非军人"};
        var KSLYM = {"1":"科学研究人员","2":"高等教育教师","3":"中等教育教师","4":"其他在职人员","5":"普通全日制应届本科毕业生","6":"成人应届本科毕业生","7":"其他人员"};
        var XXXS = {"1":"普通全日制","2":"成人教育","3":"自学考试","4":"网络教育","5":"获境外学历或学位证书者","6":"其他"};
        var XLM = {"1":"研究生","2":"本科毕业","3":"本科结业","4":"高职高专"};
        var XWM = {"1":"博士学位","2":"硕士学位","3":"学士学位","4":"无"};
        var ZXJH = {"1":"强军计划","2":"援藏计划","4":"少数民族骨干计划","7":"退役大学生计划","0":"无专项计划"};
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table class="basic" style="width: 100%;margin: 10px 0px;">
            <tr>
                <th style="width: 24.5%">考生编号</th>
                <td style="width: 24.5%">${user.userCode}</td>
                <th style="width: 24.5%">考生姓名</th>
                <td style="width: 24.5%">${user.userName}</td>
            </tr>
            <tr>
                <th>证件类型</th>
                <td id="zjlx">
                   <script>
                       $(function(){
                           if(ZJLX['${extForm.zjlx}']){
                               $("#zjlx").text(ZJLX['${extForm.zjlx}'])
                           }else{
                               $("#zjlx").text('${extForm.zjlx}')
                           }
                       })
                   </script>
                </td>
                <th>证件号码</th>
                <td>${extForm.zjhm}</td>
            </tr>
            <tr>
                <th>出生日期</th>
                <td>${extForm.csny}</td>
                <th>名族码</th>
                <td>${extForm.mzm}</td>
            </tr>
            <tr>
                <th>性别</th>
                <td>${user.sexName}</td>
                <th>婚姻状况</th>
                <td id="hfm">
                    <script>
                    $(function () {
                        if (HFM['${extForm.hfm}']) {
                            $("#hfm").text(HFM['${extForm.hfm}'])
                        } else {
                            $("#hfm").text('${extForm.hfm}')
                        }
                    })
                    </script>
                </td>
            </tr>
            <tr>
                <th>现役军人</th>
                <td id="xyjrm">
                    <script>
                        $(function () {
                            if (XYJRM['${extForm.xyjrm}']) {
                                $("#xyjrm").text(XYJRM['${extForm.xyjrm}'])
                            } else {
                                $("#xyjrm").text('${extForm.xyjrm}')
                            }
                        })
                    </script>
                </td>
                <th>政治面貌码</th>
                <td>${extForm.zzmmm}</td>
            </tr>
            <tr>
                <th>户口所在地</th>
                <td>${extForm.hkszdm}</td>
                <th>户口所在地详细地址</th>
                <td>${extForm.hkszdxxdz}</td>
            </tr>
            <tr>
                <th>考生档案所在单位</th>
                <td>${extForm.daszdw}</td>
                <th>考生档案所在单位地址</th>
                <td>${extForm.daszdwdz}</td>
            </tr>
            <tr>
                <th>考生档案所在单位邮政编码</th>
                <td>${extForm.daszdwyzbm}</td>
                <th>考生来源码</th>
                <td id="kslym">
                    <script>
                        $(function () {
                            if (KSLYM['${extForm.kslym}']) {
                                $("#kslym").text(KSLYM['${extForm.kslym}'])
                            } else {
                                $("#kslym").text('${extForm.kslym}')
                            }
                        })
                    </script>
                </td>
            </tr>
            <tr>
                <th>毕业学校代码</th>
                <td>${extForm.bydwm}</td>
                <th>毕业学校名称</th>
                <td>${extForm.bydw}</td>
            </tr>
            <tr>
                <th>毕业专业代码</th>
                <td>${extForm.byzydm}</td>
                <th>毕业专业名称</th>
                <td>${extForm.byzymc}</td>
            </tr>
            <tr>
                <th>取得最后学历的学习形式</th>
                <td id="xxxs">
                        <script>
                            $(function(){
                                if(XXXS['${extForm.xxxs}']){
                                    $("#xxxs").text(XXXS['${extForm.xxxs}']);
                                }else{
                                    $("#xxxs").text('${extForm.xxxs}');
                                }
                            })
                        </script>
                    </td>
                <th>最后学历</th>
                <td id="xlm">
                    <script>
                        $(function () {
                            if (XLM['${extForm.xlm}']) {
                                $("#xlm").text(XLM['${extForm.xlm}'])
                            } else {
                                $("#xlm").text('${extForm.xlm}')
                            }
                        })
                    </script>
                </td>
            </tr>
            <tr>
                <th>毕业证书编号</th>
                <td>${extForm.xlzsbh}</td>
                <th>获得最后学历毕业年月</th>
                <td>${extForm.byny}</td>
            </tr>
            <tr>
                <th>注册学号</th>
                <td>${extForm.zcxh}</td>
                <th>最后学位</th>
                <td id="xwm">
                    <script>
                        $(function () {
                            if (XWM['${extForm.xwm}']) {
                                $("#xwm").text(XWM['${extForm.xwm}'])
                            } else {
                                $("#xwm").text('${extForm.xwm}')
                            }
                        })
                    </script>
                </td>
            </tr>
            <tr>
                <th>学位证书编号</th>
                <td>${extForm.xwzsbh}</td>
                <th>录取单位</th>
                <td>${extForm.lqdwdm}</td>
            </tr>
            <tr>
                <th>录取单位名称</th>
                <td>${extForm.lqdwmc}</td>
                <th>录取专业</th>
                <td>${extForm.lqzydm}</td>
            </tr>
            <tr>
                <th>录取专业名称</th>
                <td>${extForm.lqzymc}</td>
                <th>考试方式码</th>
                <td>${extForm.ksfsm}</td>
            </tr>
            <tr>
                <th>专项计划</th>
                <td>
                        ${extForm.zxjh}
                </td>
                <th>入伍批准书编号</th>
                <td>${extForm.rwpzsbh}</td>
            </tr>
            <tr>
                <th>退出现役证编号</th>
                <td>${extForm.tcxyzbh}</td>
                <th>录取类别码</th>
                <td>${extForm.lqlbm}</td>
            </tr>
            <th>定向就业单位所在地</th>
            <td>${extForm.dxwpdwszdm}</td>
            <th>定向就业单位</th>
            <td>${extForm.dxwpdw}</td>
            </tr>
            <tr>
                <th>录取院系所码</th>
                <td>${extForm.lqxysm}</td>
                <th>政治理论码</th>
                <td>${extForm.zzllm}</td>
            </tr>
            <tr>
                <th>政治理论名称</th>
                <td>${extForm.zzllmc}</td>
                <th>外国语码</th>
                <td>${extForm.wgym}</td>
            </tr>
            <tr>
                <th>外国语名称</th>
                <td>${extForm.wgymc}</td>
                <th>业务课一</th>
                <td>${extForm.ywk1mc}</td>
            </tr>
            <tr>
                <th>业务课一码名称</th>
                <td>${extForm.ywk1m}</td>
                <th>业务课二</th>
                <td>${extForm.ywk2mc}</td>
            </tr>
            <tr>
                <th>业务课二码名称</th>
                <td>${extForm.ywk2m}</td>
                <th>政治理论成绩</th>
                <td>${extForm.zzll}</td>
            </tr>
            <tr>
                <th>外国语成绩</th>
                <td>${extForm.wgy}</td>
                <th>业务课一成绩</th>
                <td>${extForm.ywk1}</td>
            </tr>
            <tr>
                <th>业务课二成绩</th>
                <td>${extForm.ywk2}</td>
                <th>初试成绩</th>
                <td>${extForm.cscj}</td>
            </tr>
            <tr>
                <th>复试成绩</th>
                <td>${extForm.fscj}</td>
                <th>加试科目1名称</th>
                <td>${extForm.js1mc}</td>
            </tr>
            <tr>
                <th>总成绩</th>
                <td>${extForm.zcj}</td>
                <th>加试科目2名称</th>
                <td>${extForm.js2mc}</td>
            </tr>
            <tr>
                <th>加试科目1成绩</th>
                <td>${extForm.js1cj}</td>
                <th>保留入学资格年限</th>
                <td>${extForm.blzgnx}</td>
            </tr>
            <tr>
                <th>加试科目2成绩</th>
                <td>${extForm.js2cj}</td>
                <th>享受照顾政策</th>
                <td>${extForm.xszgzc}</td>
            </tr>
            <tr>
                <th>是否破格</th>
                <td>${extForm.qg}</td>
                <th>学制</th>
                <td>${extForm.xz}</td>
            </tr>
            <tr>
                <th>招生单位所在省市码</th>
                <td>${extForm.szssm}</td>
                <th>联合培养单位名称</th>
                <td>${extForm.lhpydw}</td>
            </tr>
            <tr>
                <th>联合培养单位代码</th>
                <td>${extForm.lhpydwm}</td>
                <th>备注</th>
                <td>${extForm.bz}</td>
            </tr>
            <tr>
                <th>是否校外考生</th>
                <td>${user.isOwnerStu=="Y"?"否":(user.isOwnerStu=="N"?"是":"")}</td>
                <th></th>
                <td></td>
            </tr>
            <c:if test="${param.showReturn eq 'Y'}">
                <input type="button" value="返&#12288;回" class="search" onclick="javascript :history.go(-1);"
                        style="position:fixed;left:90%;top:95%">
            </c:if>
        </table>
    </div>
</div>
</body>
</html>