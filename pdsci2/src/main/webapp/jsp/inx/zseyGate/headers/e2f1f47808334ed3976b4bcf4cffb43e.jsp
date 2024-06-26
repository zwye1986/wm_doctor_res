<script>

    function loadPic(type){
        jboxLoad("centerbox","<s:url value='/jsp/inx/zseyGate/static/imgList.jsp'/>?type="+type,false);
    }
	function goStaticHtml(type){
		jboxLoad("centerbox","<s:url value='/jsp/inx/zseyGate/static/'/>"+type+".jsp",false);
	}
</script>
		<div>
			<nav>
				<ul>
					<li>
						<a>通知栏</a>
					</li>
					<li>
						<a>招录</a>
						<ul>
							<li><a href="javascript:void(0);" onclick="goStaticHtml('recruitInfo');">招生简章</a></li>
							<li><a href="javascript:void(0);" onclick="goStaticHtml('singupMain');">报名登记</a></li>
							<li><a href="javascript:void(0);" onclick="goStaticHtml('audit');"style="z-index:999;position:relative">资料审核</a></li>
							<li><a href="javascript:void(0);" onclick="goStaticHtml('admissions');"style="z-index:999;position:relative">报名查询</a></li>
						</ul>
					</li>
					<li>
						<a>学员信息</a>
						<ul>
							<li><a>基本信息</a></li>
							<li><a>奖惩情况</a></li>
						</ul>
					</li>
					<li><a>学员管理</a>
						<ul>
							<li><a>请假审批</a></li>
							<li><a>学员事件</a></li>
						</ul>
					</li>
					<li><a>教学管理</a>
						<ul>
							<li><a>医德医风</a></li>
							<li><a>管理制度</a></li>
							<li><a>进修须知</a></li>
						</ul>
					</li>
					<li><a>教学计划</a>
						<ul>
							<li><a>专科培训计划（一年）</a></li>
							<li><a>专科培训计划（半年）</a></li>
							<li><a>专科培训计划（三个月）</a></li>
						</ul>
					</li>
					<li><a>技能培训</a>
						<ul>
							<li><a>技能中心培训课程</a></li>
							<li><a>技能中心预约</a></li>
							<li><a>技能中心考核</a></li>
						</ul>
					</li>
					<li><a>教学评价鉴定</a>
						<ul>
							<li><a>专科对学员评价</a></li>
							<li><a>学员对教学评价</a></li>
							<li><a>学员鉴定</a></li>
						</ul>
					</li>
					<li><a>教学资源</a>
						<ul>
							<li><a>教学视频</a></li>
							<li><a>教学课件</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>