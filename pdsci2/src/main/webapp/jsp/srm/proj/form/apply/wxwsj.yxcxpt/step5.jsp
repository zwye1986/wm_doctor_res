<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
            $('#nxt').attr({"disabled": "disabled"});
            $('#prev').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }

        function add(tb) {
            var length = $("#" + tb + "Tb").children().length;
            if (length > 18) {
                jboxTip("限填19项！");
                return false;
            }
            //计算行数
            $("#" + tb + "Num").val(length + 1);

            $("#" + tb + "Tb").append($("#" + tb + "Template tr:eq(0)").clone());
            var length = $("#" + tb + "Tb").children().length;
            //序号
            $("#" + tb + "Tb").children("tr").last().children("td").eq(1).children("input").val(length);
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
                //删除后序号
                var serial = 0;
                $("." + tb + "Serial").each(function () {
                    serial += 1;
                    $(this).children("input").val(serial);
                });
                //计算行数
                var length = $("#" + tb + "Tb").children().length;
                $("#" + tb + "Num").val(length);
                theLevelAgeTotal();
            });
        }

        function reSeq(templateId) {
            $('.' + templateId + ' .seq').each(function (i, n) {
                $(n).text(i + 1);
            });
        }

        $(function () {
            $('#userlistTemplate tr').each(function () {
                var id = this.id;
                if (id) {
                    if (!$('.' + id + ' .toDel').length) {
                        add(id);
                    }
                }
            });
        });

        function getIdByName(name) {
            /* 住院医师、主治医师、主任医师、副主任医师、主任医师技师、主管技师、副主任技师、主任技师药师、主管药师、副主任药师、
             主任药师、研究实习员、助理研究员、副研究员、研究员护师、主管护师、副主任护师、主任护师、助教、讲师、教授、副教授、其他*/
            if (name == "主任医师") {
                return "zy";
            } else if (name == "主治医师") {
                return "z";
            } else if (name == "副主任医师") {
                return "f";
            } else if (name == "博士") {
                return "doc";
            } else if (name == "硕士") {
                return "master";
            } else if (name == "本科") {
                return "bk";
            } else if (name == "大专及以下") {
                return "jun";
            }
        }
        function defaultIfEmpty(obj) {
            if (isNaN(obj)) {
                return 0;
            } else {
                return obj;
            }
        }

        function setTotal(op) {
            var amountFund = 0.0;
            var opone = parseFloat($("input[name='" + op + "one']").val());
            var optwo = parseFloat($("input[name='" + op + "two']").val());
            var opthree = parseFloat($("input[name='" + op + "three']").val());
            var opfour = parseFloat($("input[name='" + op + "four']").val());
            var opfive = parseFloat($("input[name='" + op + "five']").val());
            amountFund = defaultIfEmpty(opone) + defaultIfEmpty(optwo) + defaultIfEmpty(opthree) + defaultIfEmpty(opfour) + defaultIfEmpty(opfive);
            $("input[name='" + op + "total']").val(parseFloat(amountFund.toFixed(3)));
        }

        function theLevelAgeTotal() {
            $("#titAgeTotal input").each(function () {
                $(this).val("0");
            });
            $("#userlistTb select[name=userlist_level]").each(function (index, element) {
                var titStr = $(element).val();
                var ageStr = $(element).parent().prev().prev().children().val();
                var eduStr = $(element).parent().prev().children().val();
                if (isNaN(ageStr)) {
                    ageStr = 0;
                } else {
                    ageStr = ageStr * 1;
                }
                // alert(titStr+"="+ageStr);

                var tit = getIdByName(titStr);
                var edu = getIdByName(eduStr);
                var titAge = "";
                if (ageStr < 35) {
                    titAge = "one";
                } else if (ageStr >= 35 && ageStr <= 45) {
                    titAge = "two";
                } else if (ageStr >= 46 && ageStr <= 55) {
                    titAge = "three";
                } else if (ageStr >= 56 && ageStr <= 60) {
                    titAge = "four";
                } else if (ageStr >= 61) {
                    titAge = "five";
                }
                if (tit != "" && titAge != "") {
                    var num = parseFloat($("input[name=" + tit + titAge + "]").val());
                    if (isNaN(num)) {
                        num = 0;
                    }
                    $("input[name=" + tit + titAge + "]").val(num + 1);
                    setTotal(tit);
                }
                if (edu != "" && titAge != "") {
                    var num = parseFloat($("input[name=" + edu + titAge + "]").val());
                    if (isNaN(num)) {
                        num = 0;
                    }
                    $("input[name=" + edu + titAge + "]").val(num + 1);
                    setTotal(edu);
                }
            });
        }
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" name="projForm"
      style="position: relative;">
    <input type="hidden" name="pageName" value="step5"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

    <%--<font style="font-size: 14px; font-weight:bold;color: #333; "></font>--%>
    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="9" class="theader">五、医学创新平台（实验室）人员信息（限19人以内）
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="add('userlist')"/>&#12288;
									<img title="删除" style="cursor: pointer;"
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick="delTr('userlist');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <td width="50px"></td>
            <td width="50px">序号</td>
<c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
            <td>姓名</td>
    </c:if>
            <td>年龄</td>
            <td>学历</td>
            <td>技术职称</td>
<c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
            <td>所在单位</td>
    </c:if>
            <td>科室</td>
            <td>专业</td>
        </tr>
        <tbody id="userlistTb">
        <c:if test="${not empty resultMap.userlist}">
            <c:forEach var="userlist" items="${resultMap.userlist}" varStatus="status">
                <tr>
                    <td width="50px" style="text-align: center;"><input name="userlistIds" type="checkbox"/></td>
                    <td width="50px" style="text-align: center;" class="userlistSerial"><input name="userlistSerialNum"
                                                                                               type="text"
                                                                                               value="${userlist.objMap.userlistSerialNum}"
                                                                                               style="width:50px;border:0px; text-align: center;"
                                                                                               readonly="readonly"/>
                    </td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                    <td><input type="text" name="userlist_name" value="${userlist.objMap.userlist_name}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    </c:if>
                    <td><input type="text" name="userlist_age" value="${userlist.objMap.userlist_age}"
                               onchange="theLevelAgeTotal();"
                               class="inputText validate[required,custom[number],min[1],max[150]]" style="width: 90%"/>
                    </td>
                    <td>
                        <select name="userlist_edu" class="inputText validate[required]" style="width: 90%;"
                                onchange="theLevelAgeTotal();">
                            <option value="">请选择</option>
                            <option value="博士"
                                    <c:if test="${userlist.objMap.userlist_edu eq '博士'}">selected="selected"</c:if>>博士
                            </option>
                            <option value="硕士"
                                    <c:if test="${userlist.objMap.userlist_edu eq '硕士'}">selected="selected"</c:if>>硕士
                            </option>
                            <option value="本科"
                                    <c:if test="${userlist.objMap.userlist_edu eq '本科'}">selected="selected"</c:if>>本科
                            </option>
                            <option value="大专及以下"
                                    <c:if test="${userlist.objMap.userlist_edu eq '大专及以下'}">selected="selected"</c:if>>
                                大专及以下
                            </option>
                        </select>
                    </td>
                    <td>
                        <select name="userlist_level" class="inputText validate[required]" style="width: 90%;"
                                onchange="theLevelAgeTotal();">
                            <option value="">请选择</option>
                            <option value="住院医师"
                                    <c:if test="${userlist.objMap.userlist_level eq '住院医师'}">selected="selected"</c:if>>
                                住院医师
                            </option>
                            <option value="主治医师"
                                    <c:if test="${userlist.objMap.userlist_level eq '主治医师'}">selected="selected"</c:if>>
                                主治医师
                            </option>
                            <option value="副主任医师"
                                    <c:if test="${userlist.objMap.userlist_level eq '副主任医师'}">selected="selected"</c:if>>
                                副主任医师
                            </option>
                            <option value="主任医师"
                                    <c:if test="${userlist.objMap.userlist_level eq '主任医师'}">selected="selected"</c:if>>
                                主任医师
                            </option>
                            <option value="主管技师"
                                    <c:if test="${userlist.objMap.userlist_level eq '主管技师'}">selected="selected"</c:if>>
                                主管技师
                            </option>
                            <option value="副主任技师"
                                    <c:if test="${userlist.objMap.userlist_level eq '副主任技师'}">selected="selected"</c:if>>
                                副主任技师
                            </option>
                            <option value="主任技师"
                                    <c:if test="${userlist.objMap.userlist_level eq '主任技师'}">selected="selected"</c:if>>
                                主任技师
                            </option>
                            <option value="主管药师"
                                    <c:if test="${userlist.objMap.userlist_level eq '主管药师'}">selected="selected"</c:if>>
                                主管药师
                            </option>
                            <option value="主任药师"
                                    <c:if test="${userlist.objMap.userlist_level eq '主任药师'}">selected="selected"</c:if>>
                                主任药师
                            </option>
                            <option value="副主任药师"
                                    <c:if test="${userlist.objMap.userlist_level eq '副主任药师'}">selected="selected"</c:if>>
                                副主任药师
                            </option>
                            <option value="研究实习员"
                                    <c:if test="${userlist.objMap.userlist_level eq '研究实习员'}">selected="selected"</c:if>>
                                研究实习员
                            </option>
                            <option value="助理研究员"
                                    <c:if test="${userlist.objMap.userlist_level eq '助理研究员'}">selected="selected"</c:if>>
                                助理研究员
                            </option>
                            <option value="副研究员"
                                    <c:if test="${userlist.objMap.userlist_level eq '副研究员'}">selected="selected"</c:if>>
                                副研究员
                            </option>
                            <option value="研究员"
                                    <c:if test="${userlist.objMap.userlist_level eq '研究员'}">selected="selected"</c:if>>
                                研究员
                            </option>
                            <option value="主管护师"
                                    <c:if test="${userlist.objMap.userlist_level eq '主管护师'}">selected="selected"</c:if>>
                                主管护师
                            </option>
                            <option value="副主任护师"
                                    <c:if test="${userlist.objMap.userlist_level eq '副主任护师'}">selected="selected"</c:if>>
                                副主任护师
                            </option>
                            <option value="主任护师"
                                    <c:if test="${userlist.objMap.userlist_level eq '主任护师'}">selected="selected"</c:if>>
                                主任护师
                            </option>
                            <option value="助教"
                                    <c:if test="${userlist.objMap.userlist_level eq '助教'}">selected="selected"</c:if>>
                                助教
                            </option>
                            <option value="讲师"
                                    <c:if test="${userlist.objMap.userlist_level eq '讲师'}">selected="selected"</c:if>>
                                讲师
                            </option>
                            <option value="教授"
                                    <c:if test="${userlist.objMap.userlist_level eq '教授'}">selected="selected"</c:if>>
                                教授
                            </option>
                            <option value="副教授"
                                    <c:if test="${userlist.objMap.userlist_level eq '副教授'}">selected="selected"</c:if>>
                                副教授
                            </option>
                            <option value="技师"
                                    <c:if test="${userlist.objMap.userlist_level eq '技师'}">selected="selected"</c:if>>
                                技师
                            </option>
                            <option value="药师"
                                    <c:if test="${userlist.objMap.userlist_level eq '药师'}">selected="selected"</c:if>>
                                药师
                            </option>
                            <option value="护师"
                                    <c:if test="${userlist.objMap.userlist_level eq '护师'}">selected="selected"</c:if>>
                                护师
                            </option>
                            <option value="其他"
                                    <c:if test="${userlist.objMap.userlist_level eq '其他'}">selected="selected"</c:if>>
                                其他
                            </option>
                        </select>
                    </td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                    <td><input type="text" name="userlist_org" value="${userlist.objMap.userlist_org}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    </c:if>
                    <td><input type="text" name="userlist_room" value="${userlist.objMap.userlist_room}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    <td><input type="text" name="userlist_title" value="${userlist.objMap.userlist_title}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <table id="titAgeTotal" class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <th colspan="8" class="theader" style="text-align:left;padding-left:10px;">年龄结构</th>
        </tr>
        <tr>
            <th style="text-align: center;" rowspan="4">职称</th>
            <th style="text-align: center;padding-left: 20px;">等级</th>
            <th style="text-align: center;">合计</th>
            <th style="text-align: center;">35岁以下</th>
            <th style="text-align: center;">35-45岁</th>
            <th style="text-align: center;">46-55岁</th>
            <th style="text-align: center;">56-60岁</th>
            <th style="text-align: center;">61岁以上</th>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">主任医师</td>
            <td><input type="text" name="zytotal" value="${resultMap.zytotal}" readonly="readonly" class="inputText"
                       title="自动计算合计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="zyone" value="${resultMap.zyone}" onchange="setTotal('zy')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="zytwo" value="${resultMap.zytwo}" onchange="setTotal('zy')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="zythree" value="${resultMap.zythree}" onchange="setTotal('zy')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="zyfour" value="${resultMap.zyfour}" onchange="setTotal('zy')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="zyfive" value="${resultMap.zyfive}" onchange="setTotal('zy')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">副主任医师</td>
            <td><input type="text" name="ftotal" value="${resultMap.ftotal}" readonly="readonly" class="inputText"
                       title="自动计算合计，不可修改" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="fone" value="${resultMap.fone}" onchange="setTotal('f')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="ftwo" value="${resultMap.ftwo}" onchange="setTotal('f')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="fthree" value="${resultMap.fthree}" onchange="setTotal('f')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="ffour" value="${resultMap.ffour}" onchange="setTotal('f')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="ffive" value="${resultMap.ffive}" onchange="setTotal('f')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">主治医师</td>
            <td><input type="text" name="ztotal" value="${resultMap.ztotal}" readonly="readonly" class="inputText"
                       title="自动计算合计，不可修改" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="zone" value="${resultMap.zone}" onchange="setTotal('z')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="ztwo" value="${resultMap.ztwo}" onchange="setTotal('z')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="zthree" value="${resultMap.zthree}" onchange="setTotal('z')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="zfour" value="${resultMap.zfour}" onchange="setTotal('z')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="zfive" value="${resultMap.zfive}" onchange="setTotal('z')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
        </tr>
        <tr>
            <th style="text-align: center;" rowspan="4">学位</th>
            <td style="text-align: center;" width="120px;">博士</td>
            <td><input type="text" name="doctotal" value="${resultMap.doctotal}" readonly="readonly" class="inputText"
                       title="自动计算合计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="docone" value="${resultMap.docone}" onchange="setTotal('doc')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="doctwo" value="${resultMap.doctwo}" onchange="setTotal('doc')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="docthree" value="${resultMap.docthree}" onchange="setTotal('doc')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="docfour" value="${resultMap.docfour}" onchange="setTotal('doc')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="docfive" value="${resultMap.docfive}" onchange="setTotal('doc')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">硕士</td>
            <td><input type="text" name="mastertotal" value="${resultMap.mastertotal}" readonly="readonly"
                       class="inputText"
                       title="自动计算合计，不可修改" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="masterone" value="${resultMap.masterone}" onchange="setTotal('master')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="mastertwo" value="${resultMap.mastertwo}" onchange="setTotal('master')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="masterthree" value="${resultMap.masterthree}" onchange="setTotal('master')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="masterfour" value="${resultMap.masterfour}" onchange="setTotal('master')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="masterfive" value="${resultMap.masterfive}" onchange="setTotal('master')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">本科</td>
            <td><input type="text" name="bktotal" value="${resultMap.bktotal}" readonly="readonly" class="inputText"
                       title="自动计算合计，不可修改" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="bkone" value="${resultMap.bkone}" onchange="setTotal('bk')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="bktwo" value="${resultMap.bktwo}" onchange="setTotal('bk')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="bkthree" value="${resultMap.bkthree}" onchange="setTotal('bk')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="bkfour" value="${resultMap.bkfour}" onchange="setTotal('bk')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="bkfive" value="${resultMap.bkfive}" onchange="setTotal('bk')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">大专及以下</td>
            <td><input type="text" name="juntotal" value="${resultMap.juntotal}" readonly="readonly" class="inputText"
                       title="自动计算合计，不可修改" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="junone" value="${resultMap.junone}" onchange="setTotal('jun')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="juntwo" value="${resultMap.juntwo}" onchange="setTotal('jun')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="junthree" value="${resultMap.junthree}" onchange="setTotal('jun')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="junfour" value="${resultMap.junfour}" onchange="setTotal('jun')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="junfive" value="${resultMap.junfive}" onchange="setTotal('jun')"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
        </tr>

    </table>

</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="下一步"/>
    </div>
</c:if>

<div style="display: none;">
    <!-- 1.人员信息模板 -->
    <table class="basic" id="userlistTemplate" style="width: 100%">
        <tr>
            <td width="50px" style="text-align: center;"><input name="userlistIds" type="checkbox"/></td>
            <td width="50px" style="text-align: center;" class="userlistSerial"><input name="userlistSerialNum"
                                                                                       type="text"
                                                                                       value="${userlist.objMap.userlistSerialNum}"
                                                                                       style="width:50px;border:0px; text-align: center;"
                                                                                       readonly="readonly"/></td>
            <td><input type="text" name="userlist_name" class="inputText validate[required]" style="width: 90%"/></td>
            <td><input type="text" name="userlist_age"
                       class="inputText validate[required,custom[number],min[1],max[150]]"
                       onchange="theLevelAgeTotal();" style="width: 90%"/></td>
            <td>
                <select name="userlist_edu" class="inputText validate[required]" style="width: 90%;"
                        onchange="theLevelAgeTotal();">
                    <option value="">请选择</option>
                    <option value="博士">博士</option>
                    <option value="硕士">硕士</option>
                    <option value="本科">本科</option>
                    <option value="大专及以下">大专及以下</option>
                </select>
            </td>
            <td>
                <select name="userlist_level" class="inputText validate[required]" style="width: 90%;"
                        onchange="theLevelAgeTotal();">
                    <option value="">请选择</option>
                    <option value="住院医师">
                        住院医师
                    </option>
                    <option value="主治医师">
                        主治医师
                    </option>
                    <option value="副主任医师">
                        副主任医师
                    </option>
                    <option value="主任医师">
                        主任医师
                    </option>
                    <option value="主管技师">
                        主管技师
                    </option>
                    <option value="副主任技师">
                        副主任技师
                    </option>
                    <option value="主任技师">
                        主任技师
                    </option>
                    <option value="主管药师">
                        主管药师
                    </option>
                    <option value="主任药师">
                        主任药师
                    </option>
                    <option value="副主任药师">
                        副主任药师
                    </option>
                    <option value="研究实习员">
                        研究实习员
                    </option>
                    <option value="助理研究员">
                        助理研究员
                    </option>
                    <option value="副研究员">
                        副研究员
                    </option>
                    <option value="研究员">
                        研究员
                    </option>
                    <option value="主管护师">
                        主管护师
                    </option>
                    <option value="副主任护师">
                        副主任护师
                    </option>
                    <option value="主任护师">
                        主任护师
                    </option>
                    <option value="助教">
                        助教
                    </option>
                    <option value="讲师">
                        讲师
                    </option>
                    <option value="教授">
                        教授
                    </option>
                    <option value="副教授">
                        副教授
                    </option>
                    <option value="技师">
                        技师
                    </option>
                    <option value="药师">
                        药师
                    </option>
                    <option value="护师">
                        护师
                    </option>
                    <option value="其他">
                        其他
                    </option>
                </select>
            </td>
            <td><input type="text" name="userlist_org" class="inputText validate[required]" style="width: 90%"/></td>
            <td><input type="text" name="userlist_room" class="inputText validate[required]" style="width: 90%"/></td>
            <td><input type="text" name="userlist_title" class="inputText validate[required]" style="width: 90%"/></td>
        </tr>
    </table>
</div>
