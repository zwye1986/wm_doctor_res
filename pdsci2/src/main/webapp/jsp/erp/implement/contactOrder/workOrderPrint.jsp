
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

</head>
<body>
<div style="width: 100%">
    <div>
        <p style="text-align:center"><span style=";font-family:黑体;font-weight:bold;font-size:19px">客户服务派工单</span></p>
        <p><span style=";font-family:黑体;font-weight:bold;font-size:14px">派工单号：${workNo}</span></p>
        <p><span style=";font-family:黑体;font-weight:bold;font-size:14px">联系单号：${contactNo}</span></p>
        <p style="text-indent:27px;text-align:right">
            <span  style=";font-family:黑体;font-weight:bold;font-size:13px">日期：${assignYear}年${assignMonth}月${assignDay}日</span>
        </p>
    </div>
    <table style="width: 100%">
        <tbody>
        <tr>
            <td valign="center"   style="border: 1px solid black;" rowspan="5">
                <p style="text-align:center">
                    <span style="font-family:宋体;font-weight:bold;font-size:14px">客户资料</span>
                </p>
            </td>
            <td valign="center" style="border: 1px solid black;" colspan="3">
                <span  style="font-family:宋体;font-size:14px">客户单位名称：${customerName}</span>
            </td>
            <td valign="center" style="border: 1px solid black;" colspan="2">
                <span   style="font-family:宋体;font-size:14px">要求完成时间：${requireCompleteDate}</span>
            </td>
        </tr>
        <tr>
            <td width="321" valign="center" style="border: 1px solid black;" colspan="3">
                <span  style="font-family:宋体;font-size:14px">单位地址：${customerAddress}</span>
            </td>
            <td width="274" valign="center" style="border: 1px solid black;" colspan="2">
                <span  style="font-family:宋体;font-size:14px">联系单生成时间：${contactGenerateDate}</span>
            </td>
        </tr>
        <tr>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">联系人</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">部门</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">职务</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">固话</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">手机</span>
            </td>
        </tr>
        <tr>
            <td valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">&#12288;${userName}</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">&#12288;${deptName}</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">&#12288;${postName}</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">&#12288;${userTelphone}</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">&#12288;${userCelphone}</span>
            </td>
        </tr>
        <tr>
            <td valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">&#12288;${userName2}</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">&#12288;${deptName2}</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">&#12288;${postName2}</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">&#12288;${userTelphone2}</span>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <span style="font-family:宋体;font-size:14px">&#12288;${userCelphone2}</span>
            </td>
        </tr>
        <tr>
            <td valign="center"  style="border: 1px solid black;"  rowspan="11">
                <p style="text-align:center">
                    <span style="font-family:宋体;font-weight:bold;font-size:14px">工作任务</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <p style="margin-left:0;text-align:center">
                    <span style="font-family:宋体;font-size:14px">产品名称</span>
                </p>
            </td>
            <td valign="center" style="border: 1px solid black;" colspan="4">
                <span  style="font-family:宋体;font-size:14px">${productTypeName}</span>
            </td>
        </tr>
        <tr >
            <td  valign="center" style="border: 1px solid black;">
                <p style="margin-left:0;text-align:center">
                    <span style="font-family:宋体;font-size:14px">备注</span>
                </p>
            </td>
            <td valign="center" style="border: 1px solid black;" colspan="4">
                <span style="font-family:宋体;font-size:14px">${remark}</span>
            </td>
        </tr>
        <tr >
            <td valign="center" style="border: 1px solid black;">
                <p style="margin-left:0;text-align:center">
                    <span style="font-family:宋体;font-size:14px">任务说明</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;" colspan="4">
                <span  style="font-family:宋体;font-size:14px">${taskState}</span>
            </td>
        </tr>
        <tr>
            <td  valign="center" style="border: 1px solid black;">
                <p style="margin-left:0;text-align:center">
                    <span style="font-family:宋体;font-size:14px">服务类型</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;" colspan="4">
                <span   style="font-family:宋体;font-size:14px">${serviceTypeName}</span>
            </td>
        </tr>
        <tr >
            <td  valign="center" style="border: 1px solid black;">
                <p style="margin-left:0;text-align:center">
                    <span style="font-family:宋体;font-size:14px">处理意见</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;" colspan="4">
                <span style="font-family:宋体;font-size:14px">${salesAdvice}</span>
            </td>
        </tr>
        <tr>
            <td  valign="center" style="border: 1px solid black;">
                <p style="margin-left:0;text-align:center">
                    <span style="font-family:宋体;font-size:14px">处理方式</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;" colspan="4">
                <span style="font-family:宋体;font-size:14px">${dealTypeName}</span>
            </td>
        </tr>
        <tr>
            <td  valign="center" style="border: 1px solid black;">
                <p style="margin-left:0;text-align:center">
                    <span style="font-family:宋体;font-size:14px">是否本部门处理</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;" colspan="4">
                <span style="font-family:宋体;font-size:14px">${thisDept}</span>
            </td>
        </tr>
        <tr>
            <td  valign="center" style="border: 1px solid black;">
                <p style="margin-left:0;text-align:center">
                    <span style="font-family:宋体;font-size:14px">分配工程师</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;" colspan="4">
                <span style="font-family:宋体;font-size:14px">${assignDeptName}${assignUserName}</span>
            </td>
        </tr>
        <tr>
            <td  valign="center" style="border: 1px solid black;">
                <p  style="margin-left:0;text-align:center">
                     <span style="font-family:宋体;font-size:14px">是否完成</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;" colspan="4">
                
            </td>
        </tr>
        <tr>
            <td  valign="center" style="border: 1px solid black;">
                <p style="text-align:center">
                    <span style="font-family:宋体;font-size:14px">具体内容</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;" colspan="4">
                
            </td>
        </tr>
        <tr>
            <td  valign="center" style="border: 1px solid black;">
                <p style="text-align:center">
                    <span style="font-family:宋体;font-size:14px">到达时间</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;" colspan="2">
                
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <p style="text-align:center">
                    <span style="font-family:宋体;font-size:14px">完成时间</span>
                </p>
            </td>
            <td  valign="top" style="border: 1px solid black;" >

            </td>
        </tr>
        <tr >
            <td valign="center"  style="border: 1px solid black;">
                <p><span style="font-family:宋体;font-weight:bold;font-size:14px">客户意见</span></p>
            </td>
            <td  valign="center" style="border: 1px solid black;" colspan="5">
                <p><span style="font-family:宋体;font-weight:bold;font-size:14px">您是否满意本次服务，欢迎提出宝贵意见和建议：</span></p>
                <p><span style="font-family:'Times New Roman';font-weight:bold;font-size:14px">&nbsp;</span></p>
                <p style="text-indent:7px"><span style="font-family:宋体;color:#000000;font-size:14px">
                    □很满意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□满意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□一般&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□不满意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□很不满意</span>
                </p>
                <br>
                <p><span style="font-family:宋体;color:#000000;font-weight:bold;font-size:14px">意见或建议内容：</span></p>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                </p>
                <p style="text-indent:279px;text-align:center">
                    <span  style="font-family:宋体;font-weight:bold;font-size:14px">客户签字：</span>
                </p>
                <p style="text-indent:279px;text-align:center"><span   style="font-family:宋体;font-weight:bold;font-size:14px">日&nbsp;&nbsp;&nbsp;&nbsp;期：</span></p>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <p style="text-indent:279px;text-align:center">
        <span style="font-family:宋体;font-weight:bold;font-size:14px">工程师签字：</span></p>
    <p style="text-indent:279px;text-align:center">
        <span style="font-family:宋体;font-weight:bold;font-size:14px">日&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期：</span></p>
    <p><span style=";font-family:'Times New Roman';font-size:14px">&nbsp;</span></p>
    <p><span style=";font-family:'Times New Roman';font-size:14px">&nbsp;</span></p>
    <table width="100%">
        <tbody>
        <tr>
            <td  valign="center"  style="border: 1px solid black;" rowspan="2">
                <p style="text-align:center"><span style="font-family:宋体;font-weight:bold;font-size:14px">公司审核</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <p style="text-align:center"><span style="font-family:宋体;font-size:14px">客户反馈信息记录人签字</span></p>
            </td>
            <td  valign="center" style="border: 1px solid black;" colspan="3"><br></td>
        </tr>
        <tr>
            <td valign="center" style="border: 1px solid black;">
                <p style="text-align:center">
                    <span style="font-family:宋体;font-size:14px">部门经理签字</span>
                </p>
            </td>
            <td valign="center" style="border: 1px solid black;">
                <p style="text-align:center"><span style="font-family:宋体;font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </p>
            </td>
            <td  valign="center" style="border: 1px solid black;">
                <p style="text-align:center"><span style="font-family:宋体;font-size:14px">日&nbsp;&nbsp;&nbsp;期</span>
                </p>
            </td>
            <td  valign="center" style="border: 1px solid black;">  <p style="text-align:center"><span style="font-family:宋体;font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </p>
            </td>
        </tr>
        <tr>
            <td valign="top" style="border: 1px solid black;background-color: grey;"  colspan="5">  <p style="text-align:center"><span style="font-family:宋体;font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </p>
            </td>
        </tr>
        <tr>
            <td  valign="center" style="border: 1px solid black;" rowspan="3">
                <p style="text-align:center"><span style="font-family:宋体;font-weight:bold;font-size:14px">客户回访</span>
            </p>
            </td>
            <td  valign="center" style="border: 1px solid black;"><p
                    style="text-align:center"><span style="font-family:宋体;font-size:14px">客户联系人</span></p>
            </td>
            <td  valign="center" style="border: 1px solid black;">  <p style="text-align:center"><span style="font-family:宋体;font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </p>
            </td>
            <td valign="center" style="border: 1px solid black;"><p
                    style="text-align:center"><span style="font-family:宋体;font-size:14px">回访日期</span></p>
            </td>
            <td  valign="center" style="border: 1px solid black;">  <p style="text-align:center"><span style="font-family:宋体;font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </p>
            </td>
        </tr>
        <tr>
            <td valign="top" style="border: 1px solid black;" colspan="4"><p>
                <span style="font-family:宋体;font-weight:bold;font-size:14px">回访结果：</span></p>
                <br>
                <br>
                <br>
                <br>
            </td>
        </tr>
        <tr>
            <td valign="center" style="border: 1px solid black;"><p
                    style="text-align:center"><span style="font-family:宋体;font-size:14px">回访人</span></p></td>
            <td valign="center" style="border: 1px solid black;">  <p style="text-align:center"><span style="font-family:宋体;font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </p>
            </td>
            <td valign="center" style="border: 1px solid black;"><p
                    style="text-align:center"><span style="font-family:宋体;font-size:14px">商务签字</span></p></td>
            <td valign="top" style="border: 1px solid black;">  <p style="text-align:center"><span style="font-family:宋体;font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </p>
            </td>
        </tr>
        </tbody>
    </table>
    <p style="text-indent:0"><span style="font-family:宋体;font-size:14px">注意事项：</span></p>
    <p style="margin-left:28px">
        <span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">1.&nbsp;考试系统是下单后3个工作日完成，住院医师是下单后5个工作日完成。（&nbsp;&nbsp;）</span>
    </p>
    <p style="margin-left:28px">
        <span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">2.&nbsp;提前24小时电话预约客户上门安装/更新维护时间。(&nbsp;&nbsp;)</span>
    </p>
    <p style="margin-left:28px"><span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">3.&nbsp;准备安装/更新产品所需的软件工具和硬件设备。(&nbsp;&nbsp;)</span>
    </p>
    <p style="margin-left:28px"><span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">4.&nbsp;安装/更新后，测试系统登陆页面有无报错，是否可以进入，有无异常。(&nbsp;&nbsp;)</span>
    </p>
    <p style="margin-left:28px"><span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">5.&nbsp;检查各角色登陆页面及内页LOGO是否正确。(&nbsp;&nbsp;)</span>
    </p>
    <p style="margin-left:28px"><span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">6.&nbsp;测试各角色使用的功能模块是否正常运行。(&nbsp;&nbsp;)</span>
    </p>
    <p style="margin-left:28px"><span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">7.&nbsp;用局域网电脑进行远程访问，测试系统的功能、页面是否能正常使用。(&nbsp;&nbsp;)</span>
    </p>
    <p style="margin-left:28px"><span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">8.&nbsp;再次确认安装/更新的产品是否符合派工单上的要求。(&nbsp;&nbsp;)</span>
    </p>
    <p style="margin-left:28px"><span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">9.&nbsp;拷贝客户数据库、程序、个性化页面。(&nbsp;&nbsp;)</span></p>
    <p style="margin-left:28px"><span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">10.&nbsp;更新注册文件(&nbsp;&nbsp;)</span></p>
    <p style="margin-left:28px"><span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">11.&nbsp;检查所带软件工具和硬件设备是否遗落客户处，然后礼貌离开。(&nbsp;&nbsp;)</span></p>
    <p style="text-indent:0"><span style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">其它：</span>
        <span
            style="font-family:宋体;color:#FF0000;font-weight:bold;font-size:15px">记录本次需要办理的其它事情，如带发票等。</span><span
            style="font-family:宋体;color:#000000;font-weight:bold;font-size:15px">(&nbsp;&nbsp;)</span></p>
    <p><br></p>
</div>
</body>
</html>
