<c:if test="${param.open ==GlobalConstant.FLAG_Y }">
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jbox" value="true"/>
	</jsp:include>
</c:if>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
require.config({
    paths: {
        echarts: "<s:url value='/js/echarts'/>"
    }
});

require(['echarts','echarts/chart/line'],function(ec){
	var myChart = ec.init(document.getElementById('hosChart')); 
   	var lineLabel = [];
 	var lineValue = [];
 	var value = [];
	<c:forEach items="${timeGapMon}" var="t">
		var t="${t}";
		lineLabel.push(t.substring(0,4)+"-"+t.substring(4,6));
		var a="${percentMap[t]}";
		lineValue.push(toPercent(a));
		value.push("${countMap[t]}");
//			lineValue.push("${countMap[t]}"+"("+"${percentMap[t]}"+")");
	</c:forEach>
	if (lineLabel.length==0) {
		lineLabel.push("");
	  	lineValue.push("");
	}
	var startDate=(1-4/lineLabel.length)*100;
	var title="";var sessionNumber=""; var type="";
	if("${param.catSpeId}"!=""){
		<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
			if("${param.catSpeId}" =="${trainCategory.id}"){
				type="${trainCategory.name}";
			}
		</c:forEach>
	}
	if("${ param.sessionNumber}"!=""){
		sessionNumber="${param.sessionNumber}";
	}else{
		sessionNumber="${sysCfgMap['jsres_doctorCount_sessionNumber']}";
	}
	title=sessionNumber+"届"+type+"APP的使用情况";
option = {
		 title : {
		        text: title,
		        x:'center'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    toolbox: {
	        show : true,
	        feature : {}
	    },
	    calculable : true,
	    dataZoom : {//下面拖动的代码块
	        show : true,
	        realtime : true,
	        start :startDate,
	        end : 100
	    },
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : true,
	            data : lineLabel
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            interval: 'auto',
	            axisLabel : {
	            		formatter: function(value)
	            		{
            				return parseInt(value)+"%";
	            		}
            	},
	        }
	    ],
	    series : [
	        {
	            name:'百分比',
	            type:'line',
	            data:lineValue,
	            itemStyle: {
                    normal: {
                        label : {
                            show: true, position: 'top',
                            formatter:function(a,b,c){
                            	return c+"%";
                            }
                        }
                    }
           		}
	        },
	        {
	            name:'使用人数',
	            type:'line',
	            data:value,
	            symbol:'none',//去掉点
	            itemStyle: {
                    normal: {
                       lineStyle:{
                    	   color:'transparent'//透明se
                       }
                    }
           		}
	        },
	    ]
	};

myChart.setOption(option);

var ecConfig = require('echarts/config');
var timeoutObj ;
myChart.on(ecConfig.EVENT.DATA_ZOOM,function(param){
	if(param){
		if(param.zoom){
			var labelLength = lineLabel.length;
			if(labelLength){
				var start = param.zoom.start;
				var end = param.zoom.end;
				var startDate = lineLabel[Math.floor(labelLength*(start/100))];
				var endDate = lineLabel[Math.ceil(labelLength*(end/100)-1)];
				clearTimeout(timeoutObj);
				timeoutObj = setTimeout(function(){
					caculate(startDate,endDate);
				},500);
			}
		}
	}
});
});

$(document).ready(function(){
	showNoAppUser("${param.orgFlow}","${param.catSpeId}","${param.currentPage}");
	top.jboxEndLoading();
});
function caculate(startDate,endDate){
	var url = "<s:url value='/jsres/institute/statisticsNoAppUser'/>?open=${param.open}&currentPage=${param.currentPage}&catSpeId=${param.catSpeId}&orgFlow=${param.orgFlow}&endDate="+endDate+"&startDate="+startDate;
	jboxPostLoad("othersDiv", url, function(resp){
		if(resp!=null){
			top.jboxEndLoading();
		}
	}, false); 
}
function toPercent(data){
    var strData = parseFloat(data)*10000;
    strData = Math.round(strData);
    strData/=100.00;
    var ret = strData.toString();
    return ret;
}
function changeTrainSpes(catSpeId){
	var orgFlow="${param.orgFlow}";
	var url="<s:url value='/jsres/institute/statisticsAppUser'/>?open=Y&orgFlow="+orgFlow+"&catSpeId="+catSpeId;
	top.jboxStartLoading();
	window.location.href=url;
}
function showNoAppUser(orgFlow,catSpeId,currentPage){
	if(currentPage == undefined || currentPage ==""){
		currentPage=1;
	}
	var url = "<s:url value='/jsres/institute/statisticsNoAppUser'/>?open=${param.open}&currentPage="+currentPage+"&catSpeId="+catSpeId+"&orgFlow="+orgFlow;
	jboxPostLoad("othersDiv", url, function(resp){
		if(resp!=null){
			top.jboxEndLoading();
		}
	}, false); 
}
</script>
</head>
<body>
<c:if test="${param.open ==GlobalConstant.FLAG_Y }">
	<div class="infoAudit">
</c:if>
	<div style="margin:90px 0 0 90px ">
	学员类型：
		<select name="catSpeId" id="catSpeId" class="select" onchange="changeTrainSpes(this.value)" style="width:107px;">
	        	<option value="">请选择</option>
				<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
					<option value="${trainCategory.id}" <c:if test="${param.catSpeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
				</c:forEach>
			</select>
	</div>
	<div id="hosChart" <c:if test="${param.openType eq GlobalConstant.FLAG_Y}">style="height: 250px;width:100%;margin: 50px 0 0 0;"</c:if>
	<c:if test="${param.openType != GlobalConstant.FLAG_Y}">style="height: 250px;width:100%;margin: 20px 0 0 0;"</c:if>
	>
	</div>
		<div style="margin-left: 90px;margin-top: 40px;" >
			<h4>未使用APP的学员信息</h4>
		</div>
<div>
	<div id="othersDiv" <c:if test="${param.open !=GlobalConstant.FLAG_Y }">style="width: 890px;margin-left: 50px;"</c:if>>
	
	</div>
</div>
</div>
</body>
</html>
