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
//	console.log(document.getElementById("schoolNum"));
	var myChart = ec.init(document.getElementById("schoolNum"));
   	var lineLabel = []; //x轴文字
 	var lineValue = [];
 	var value = [];
	var sum=0;
	var specialty = '';
	<c:if test="${seeFlag eq 'Y'}">
		specialty="医学院校"
		<c:forEach items="${schools}" var="school">
			lineLabel.push("${school}");
			sum+=parseFloat("0");
				<c:if test="${graduatesMap[school] != null}">
				sum+=parseFloat("${graduatesMap[school]}");
				</c:if>
			lineValue.push(sum);
			sum=0;
		</c:forEach>
	</c:if>
	<c:if test="${seeFlag eq 'N'}">
		specialty="专业"
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			lineLabel.push("${dict.dictName}");
			<c:if test="${not empty graduatesMap[dict.dictId] }">
			sum+=parseFloat("${graduatesMap[dict.dictId]+0}");
			</c:if>
			lineValue.push(sum);
			sum=0;
		</c:forEach>
		<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
			lineLabel.push("${dict.dictName}");
			<c:if test="${not empty graduatesMap[dict.dictId] }">
			sum+=parseFloat("${graduatesMap[dict.dictId]+0}");
			</c:if>
			lineValue.push(sum);
			sum=0;
		</c:forEach>
	</c:if>
//	console.log(lineLabel);
//	console.log(lineValue);
	if (lineLabel.length==0) {
		lineLabel.push("");
	  	lineValue.push("");
	}
	var title="高校医学院在校专硕人员统计";
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
					interval:0,
					rotate:-90,
					margin:10
				},
				name:specialty,
				boundaryGap : true,
	            data : lineLabel
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            interval: 'auto',
				name:'人数',
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
	            name:'人数',
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
	<div id="schoolNum" style="height: 550px;width:100%;">

	</div>
</div>
</body>
</html>
