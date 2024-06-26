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
	var myChart = ec.init(document.getElementById('doctorNum'));
   	var lineLabel = []; //x轴文字
 	var lineValue = [];
 	var value = [];
	var specialty = '';
	<c:if test="${seeFlag eq 'Y'}">
		var sum=0;
		specialty="培训基地"
		<c:forEach items="${orgs}" var="org">
			lineLabel.push("${org.orgName}");
			sum+=parseFloat("0");
			<c:if test="${countMap[org.orgFlow] != null}">
				sum+=parseFloat("${countMap[org.orgFlow]}");
			</c:if>
			lineValue.push(sum);
			sum=0;
		</c:forEach>
	</c:if>
	<c:if test="${seeFlag eq 'N'}">
		<c:set var= "mk" value="dictTypeEnum${param.trainTypeId}List"/>
		var sum=0;

		<c:if test="${countByTrainTypeId eq 'Y'}">
			specialty="培训类别"
			<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">
				lineLabel.push("${trainCategory.name}");
				sum+=parseFloat("${totalCountMap[trainCategory.id]+0}");
				lineValue.push(sum);
				sum=0;
			</c:forEach>
		</c:if>
		<c:if test="${countByTrainTypeId ne 'Y'}">
			specialty="专业"
			<c:forEach items="${applicationScope[mk]}" var="dict">
				lineLabel.push("${dict.dictName}");
				<c:forEach items="${sysOrgList}" var="org">
					<c:set var="flow" value="${org.orgFlow}${param.trainTypeId}${dict.dictId}"/>
					<c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
					sum+=parseFloat("${totalCountMap[flow]+0}");
					</c:if>
				</c:forEach>
				lineValue.push(sum);
				sum=0;
			</c:forEach>
		</c:if>
	</c:if>
	//console.log(lineLabel);
	//console.log(lineValue);
	if (lineLabel.length==0) {
		lineLabel.push("");
	  	lineValue.push("");
	}
	var title="江苏省住院医师规范化培训人员统计";
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
					rotate:45,
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
	<div id="doctorNum" style="height: 550px;width:100%;">

	</div>
</div>
</body>
</html>
