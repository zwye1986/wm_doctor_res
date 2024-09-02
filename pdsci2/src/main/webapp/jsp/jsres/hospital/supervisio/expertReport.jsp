<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <style type="text/css">
        .div_table table {
            border-collapse: collapse;
            border: 1px solid #D3D3D3;
        }

        .div_table table td {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
            padding-left: 4px;
            padding-right: 2px;
        }

        .div_table table th {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
        }

        .div_table table tr.lastrow td {
            border-bottom: 0;
        }

        .div_table table tr td.lastCol {
            border-right: 0;
        }
    </style>
    <script type="text/javascript">
        function saveScore(obj) {
            var itemId = $(obj).attr('id');
            var contentMas=obj.value;
            var reg =  /^(\d+|\d+\.\d{1,2})$/;
            if (reg.test(contentMas)) {
                var url = "<s:url value='/jsres/supervisio/saveReportContentMas'/>";
                var data = {
                    "itemId": itemId,
                    "subjectFlow": '${subjectFlow}',
                    "subjectActivitiFlows":'${subjectActivitiFlows}',
                    "contentMas":contentMas,
                    "type":"spe"
                };
                top.jboxPost(url, data, function (resp) {
                    top.jboxTip(resp);
                }, null, false);
            }else {
                top.jboxTip("只能输入数字且带有两位小数!");
            }
        }
        $(function () {
            var itemIdList = $("input");
            if (${"baseExpert" ne roleFlag  or isRead eq 'Y'}){
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("name") == "score") {
                        itemIdList[i].readOnly = "true";
                    }
                }
            }

            <c:forEach items="${subjectList}" var="item" varStatus="status" >
            //获得值
            for (var i = 0; i < itemIdList.length; i++) {
                if ($(itemIdList[i]).attr('id') == "${item.contentTitle}") {
                    itemIdList[i].value = "${item.contentMas}";
                }
            }
            </c:forEach>
        })
        function exportReport(subjectActivitiFlows,type) {
            var url ="";
            if(type == 'spe'){
                url ="<s:url value='/jsres/supervisio/exportReport'/>?subjectFlow="+subjectActivitiFlows+"&type="+type+"&speName=${speName}";
            }
            if(type == 'major'){
                url ="<s:url value='/jsres/supervisio/exportReport'/>?&subjectActivitiFlows="+subjectActivitiFlows+"&type="+type;
            }
            top.jboxTip("导出中…………");
            top.jboxExp(null,url);
            top.jboxEndLoading();
        }
        function subInfo() {
            top.jboxTip("保存成功!");
            top.jboxClose();
        }
        function subReport() {
            var url = "<s:url value='/jsres/supervisio/subReport'/>";
            var data = {
                "subjectFlow": '${subjectFlow}',
                "sub":'Y',
            };
            top.jboxPost(url, data, function (resp) {
                top.jboxTip(resp);
                top.jboxClose();
            }, null, false);
        }


    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <div>
        <div style="margin-left: 60px">
            <span style="font-weight: bold;line-height: 20px;font-size: 18px">
                 专业基地相关重要数据填报 <br>
                ${speName}
            </span>
        </div>
        <div style="margin-top: -36px;text-align: right;margin-bottom: 15px;margin-right: 4px">
            <input class="btn_green" type="button" value="导出Word文件"
                   style="background-color: #54B2E5;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                   onclick="exportReport('${subjectActivitiFlows}','spe');" />
        </div>
    </div>
    <table cellpadding="4" style="width: 920px">
        <tbody>
        <tr style="height:35px;"  >
            <th style="width: 350px;height: 35px; background: #f4f5f9;border-bottom: 1px solid #D3D3D3;text-align: left" ><font style="font-size: 16px;font-weight: bold;margin-left: 5px">指标</font></th>
            <th style="width: 110px;height: 35px; background: #f4f5f9;border-bottom: 1px solid #D3D3D3" >2019年</th>
            <th style="width: 110px;height: 35px; background: #f4f5f9;border-bottom: 1px solid #D3D3D3" >2020年</th>
            <th style="width: 110px;height: 35px; background: #f4f5f9;border-bottom: 1px solid #D3D3D3" >2021年</th>
        </tr>
        <tr style="height:35px;">
            <td colspan="4"><font style="font-size: 16px;font-weight: bold">基本情况:</font></td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >总床位数</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="zcws2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zcws2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zcws2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >年收治病人数</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="nszbrs2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="nszbrs2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center"  class="input"id="nszbrs2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >年门诊量</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="nmzl2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="nmzl2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="nmzl2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >年急诊量</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="njzl2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="njzl2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center"  class="input"id="njzl2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td colspan="4"><font style="font-size: 16px;font-weight: bold">师资情况:</font></td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >主任医师</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="zrys2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zrys2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zrys2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >副主任医师</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center"class="input" id="fzrys2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="fzrys2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="fzrys2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >主治医师</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="zzys2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zzys2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zzys2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td colspan="4"><font style="font-size: 16px;font-weight: bold">招生情况:</font></td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >招生人数</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="zsrs2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zsrs2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zsrs2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >招生计划完成率</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="zsjswcl2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zsjswcl2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zsjswcl2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >招收外单位委派的培训对象和面向社会招收的培训对象占比</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="zs2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zs2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center"  class="input" id="zs2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td colspan="4"><font style="font-size: 16px;font-weight: bold">结业考试情况:</font></td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >住培结业考试首考人数</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="zp2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zp2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zp2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >住培结业考试首考通过人数</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="zj2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zj2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zj2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >住培结业考试首考通过率</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="zjl2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zjl2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="zjl2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td colspan="4"><font style="font-size: 16px;font-weight: bold">执医考试情况:</font></td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >住院医师首次参加执医考试人数</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="sckrs2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="sckrs2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="sckrs2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >住院医师首次参加执医考试通过人数</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="scktrsl2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="scktrs2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="scktrs2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >住院医师首次参加执医考试通过率</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="scktrsll2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="scktrsll2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="scktrsll2021" onchange="saveScore(this);">
            </td>
        </tr>
        <tr style="height:35px;">
            <td colspan="4"></td>
        </tr>
        <tr style="height:35px;">
            <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3" >住院医师参加年度业务水平测试全国排名</td>
            <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" >
                <input type="text" name="score" style="width: 100px;text-align: center" class="input" id="pm2019" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="pm2020" onchange="saveScore(this);">
            </td>
            <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" >
                <input type="text"  name="score" style="width: 100px;text-align: center" class="input" id="pm2021" onchange="saveScore(this);">
            </td>
        </tr>
        </tbody>

    </table>
    <div>
        <div>
            <span style="font-weight: bold;line-height: 20px">
                注：<br>
                1、请注意提供数据真实、准确，评估组将与省毕教办数据对比。 <br>
                2、基本情况部分数据需各专业结合评估指标具体项目进行上报。
            </span>
        </div>
    </div>
</div>
<div class="button" style="margin-top: 25px">
    <c:if test="${roleFlag eq 'baseExpert' && isRead ne 'Y'}">
        <input class="btn_green" type="button" value="保&#12288;存" onclick="subInfo();"/>&#12288;
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subReport();"/>&#12288;
    </c:if>
    <input class="btn_green" type="button" value="关&#12288;闭" onclick="top.jboxClose();"/>
</div>

</body>
</html>