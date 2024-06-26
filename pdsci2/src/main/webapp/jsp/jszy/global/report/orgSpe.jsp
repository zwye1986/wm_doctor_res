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
	var myChart = ec.init(document.getElementById('orgSpeFlag'));
   	var lineLabel = []; //x轴文字
 	var lineValue = [];
 	var value = [];
	<c:set var= "mk" value="dictTypeEnumDoctorTrainingSpeList"/>
	var sum=0;
	<c:forEach items="${applicationScope[mk]}" var="dict">
		lineLabel.push("${dict.dictName}");
		<c:forEach items="${sysOrgList}" var="org">
		<c:set var="flow" value="${org.orgFlow}${dict.dictId}"/>
			<c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
				sum+=1;
			</c:if>
		</c:forEach>
		lineValue.push(sum);
		sum=0;
	</c:forEach>
	//console.log(lineLabel);
	//console.log(lineValue);
	if (lineLabel.length==0) {
		lineLabel.push("");
	  	lineValue.push("");
	}
	var title="江苏省住院医师规范化培训专业基地统计";
option = {
		 title : {
		        text: title,
		        x:'center'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    calculable : true,
		grid:{
			zlevel:0,
			height:350
		},
	    xAxis : [
	        {
	            type : 'category',
				axisLabel:{
					show:true,
					interval:0,
					rotate:45,
					margin:10,
					textStyle:{
						align:'center'
					}
				},
				name:'专业',
	            boundaryGap : true,
	            data : lineLabel
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            interval: 'auto',
				name:'基地数量',
	            axisLabel : {
	            		formatter: function(value)
	            		{
            				return parseInt(value);
	            		}
            	},
	        }
	    ],
	    series : [
	        {
	            name:'专业基地数量',
	            type:'line',
	            data:lineValue,
	            itemStyle: {
                    normal: {
                        label : {
                            show: true, position: 'top',
                            formatter:function(a,b,c){
                            	return c;
                            }
                        }
                    }
           		}
	        }
	    ]
	};

myChart.setOption(option);
});

</script>
</head>
<body>
	<div id="orgSpeFlag" style="height: 550px;width:100%;">

	</div>
</div>
</body>
</html>
