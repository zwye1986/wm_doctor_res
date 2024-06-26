<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style type="text/css">
        .content div{margin-top:5px;}
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="text-align:center;font-size:16px;font-weight:bold;">学术活动报销单（报销联）</div>
        <div>
            <span style="padding-left:39px;"></span>申请人：<input type="text" style="width:120px;" value="${acActivity.applyUserName}" readonly="readonly">
            <span style="padding-left:6px;"></span>申请时间：<input type="text" style="width:120px;" value="${fn:substring(acActivity.applyTime, 0, 10)}" readonly="readonly">
            <span style="padding-left:6px;"></span>申请人部门：<input type="text" style="width:120px;" value="${acActivity.applyDeptName}" readonly="readonly">
        </div>
        <div>
            <span style="color: red;padding-left:26px;"></span>学术名称：<input type="text" style="width:120px;" value="${acActivity.academicName}" readonly="readonly">
            <span style="color: red;padding-left:6px;"></span>学术类型：<input type="text" style="width:120px;" value="${acActivity.academicTypeName}" readonly="readonly">
        </div>
        <div>
            <span style="color: red;padding-left:22px;"></span>学术地点：
            <input type="text" style="width:120px;" value="${acActivity.placeProvince}" readonly="readonly">
            <input type="text" style="width:120px;" value="${acActivity.placeCity}" readonly="readonly">
            <input type="text" style="width:120px;" value="${acActivity.placeArea}" readonly="readonly">
        </div>
        <div>
            <span style="color:red;"></span>学术开始时间：<input type="text" style="width:120px;" readonly="readonly" value="${fn:substring(acActivity.beginTime, 0, 10)}">
            <span style="color:red;padding-left:0px;"></span>学术结束时间：<input type="text" style="width:120px;" readonly="readonly" value="${fn:substring(acActivity.endTime, 0, 10)}">
            <span style="color:red;padding-left:0px;"></span>学术天数：<input type="text" style="width:120px;" value="${acActivity.takeDay}" readonly="readonly">
        </div>
        <div>
            <span style="padding-left:26px;"></span>主办单位：<input type="text" maxlength="50" value="${acActivity.holdUnit}" readonly="readonly">
        </div>
        <div><span style="padding-left:26px;"></span>继教部审核意见：<br/><span style="padding-left:80px;"></span><input type="text" name="expenseAdminAdvice" value="${acActivity.expenseAdminAdvice}" size="85" readonly="readonly"></div>
        <div style="border-bottom:1px dashed black;height:15px;"></div>
        <div style="text-align:center;font-size:16px;font-weight:bold;">学术活动报销单（存根联）</div>
        <div>
            <span style="padding-left:39px;"></span>申请人：<input type="text" style="width:120px;" value="${acActivity.applyUserName}" readonly="readonly">
            <span style="padding-left:6px;"></span>申请时间：<input type="text" style="width:120px;" value="${fn:substring(acActivity.applyTime, 0, 10)}" readonly="readonly">
            <span style="padding-left:6px;"></span>申请人部门：<input type="text" style="width:120px;" value="${acActivity.applyDeptName}" readonly="readonly">
        </div>
        <div>
            <span style="color: red;padding-left:26px;"></span>学术名称：<input type="text" style="width:120px;" value="${acActivity.academicName}" readonly="readonly">
            <span style="color: red;padding-left:6px;"></span>学术类型：<input type="text" style="width:120px;" value="${acActivity.academicTypeName}" readonly="readonly">
        </div>
        <div>
            <span style="color: red;padding-left:22px;"></span>学术地点：
            <input type="text" style="width:120px;" value="${acActivity.placeProvince}" readonly="readonly">
            <input type="text" style="width:120px;" value="${acActivity.placeCity}" readonly="readonly">
            <input type="text" style="width:120px;" value="${acActivity.placeArea}" readonly="readonly">
        </div>
        <div>
            <span style="color:red;"></span>学术开始时间：<input type="text" style="width:120px;" readonly="readonly" value="${fn:substring(acActivity.beginTime, 0, 10)}">
            <span style="color:red;padding-left:0px;"></span>学术结束时间：<input type="text" style="width:120px;" readonly="readonly" value="${fn:substring(acActivity.endTime, 0, 10)}">
            <span style="color:red;padding-left:0px;"></span>学术天数：<input type="text" style="width:120px;" value="${acActivity.takeDay}" readonly="readonly">
        </div>
        <div>
            <span style="padding-left:26px;"></span>主办单位：<input type="text" maxlength="50" value="${acActivity.holdUnit}" readonly="readonly">
        </div>
        <div style="margin-top:15px;"><span style="padding-left:56px;"></span>实际费用：</div>
        <div><span style="padding-left:116px;"></span>会议费：<input type="text" style="width:120px;" name="meetingFee" value="${acActivity.meetingFee}" readonly="readonly"><span style="padding-left:35px;"></span>资料费：<input type="text" style="width:120px;" name="materialFee" value="${acActivity.materialFee}" readonly="readonly"></div>
        <div><span style="padding-left:116px;"></span>交通费：<input type="text" style="width:120px;" name="trafficFee" value="${acActivity.trafficFee}" readonly="readonly"><span style="padding-left:35px;"></span>服装费：<input type="text" style="width:120px;" name="costumeFee" value="${acActivity.costumeFee}" readonly="readonly"></div>
        <div><span style="padding-left:116px;"></span>是否含食宿：
            <input type="radio" name="foodFeeWhether" value="Y" <c:if test="${acActivity.foodFeeWhether eq 'Y'}">checked="checked"</c:if> disabled="disabled">含&#12288;
            <input type="radio" name="foodFeeWhether" value="N" <c:if test="${acActivity.foodFeeWhether eq 'N'}">checked="checked"</c:if> disabled="disabled">不含
            <span style="padding-left:42px;"></span>食宿费：<input type="text" style="width:120px;" name="foodFee" value="${acActivity.foodFee}" readonly="readonly"></div>
        <div><span style="padding-left:116px;"></span>补贴费：<input type="text" style="width:120px;" name="subsidyFee" value="${acActivity.subsidyFee}" readonly="readonly"><span style="padding-left:35px;"></span>其他费：<input type="text" style="width:120px;" name="otherFee" value="${acActivity.otherFee}" readonly="readonly"></div>
        <div><span style="padding-left:78px;"></span>实际费用合计：<input type="text" style="width:120px;" name="sumFee" value="${acActivity.sumFee}" readonly="readonly"></div>
    </div>
</div>
</body>
</html>