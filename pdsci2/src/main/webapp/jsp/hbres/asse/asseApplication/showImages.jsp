
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
	* {
		padding: 0px;
		margin: 0px;
	}
	a {
		text-decoration: none;
	}
	ul {
		list-style: outside none none;
	}
	.slider, .slider-panel img, .slider-extra {
		width: 650px;
		height: 390px;
	}
	.slider {
		text-align: center;
		margin:  auto;
		position: relative;
	}
	.slider-panel, .slider-nav, .slider-pre, .slider-next {
		position: absolute;
		z-index: 8;
	}
	.slider-panel {
		position: absolute;
	}
	.slider-panel img {
		border: none;
	}
	.slider-extra {
		position: relative;
	}
	.slider-nav {
		margin-left: -51px;
		position: absolute;
		left: 50%;
		bottom: 4px;
	}
	.slider-nav li {
		background: #3e3e3e;
		border-radius: 50%;
		color: #fff;
		cursor: pointer;
		margin: 0 2px;
		overflow: hidden;
		text-align: center;
		display: inline-block;
		height: 18px;
		line-height: 18px;
		width: 18px;
	}
	.slider-nav .slider-item-selected {
		background: blue;
	}
	.slider-page a{
		background: rgba(0, 0, 0, 0.2);
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#33000000,endColorstr=#33000000);
		color: #fff;
		text-align: center;
		display: block;
		font-family: "simsun";
		font-size: 22px;
		width: 28px;
		height: 62px;
		line-height: 62px;
		margin-top: -31px;
		position: absolute;
		top: 50%;
	}
	.slider-page a:HOVER {
		background: rgba(0, 0, 0, 0.4);
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#66000000,endColorstr=#66000000);
	}
	.slider-next {
		left: 100%;
		margin-left: -28px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var length,
				currentIndex = 0,
				interval,
				hasStarted = false, //是否已经开始轮播
				t = 3000; //轮播时间间隔
		length = $('.slider-panel').length;

		//将除了第一张图片隐藏
		$('.slider-panel:not(:first)').hide();
		//将第一个slider-item设为激活状态
		$('.slider-item:first').addClass('slider-item-selected');
		//隐藏向前、向后翻按钮
		$('.slider-page').hide();

		//鼠标上悬时显示向前、向后翻按钮,停止滑动，鼠标离开时隐藏向前、向后翻按钮，开始滑动
		$('.slider-panel, .slider-pre, .slider-next').hover(function() {
			stop();
			$('.slider-page').show();
		}, function() {
			$('.slider-page').hide();
			start();
		});

		$('.slider-item').hover(function(e) {
			stop();
			var preIndex = $(".slider-item").filter(".slider-item-selected").index();
			currentIndex = $(this).index();
			play(preIndex, currentIndex);
		}, function() {
			start();
		});

		$('.slider-pre').unbind('click');
		$('.slider-pre').bind('click', function() {
			pre();
		});
		$('.slider-next').unbind('click');
		$('.slider-next').bind('click', function() {
			next();
		});

		/**
		 * 向前翻页
		 */
		function pre() {
			var preIndex = currentIndex;
			currentIndex = (--currentIndex + length) % length;
			play(preIndex, currentIndex);
		}
		/**
		 * 向后翻页
		 */
		function next() {
			var preIndex = currentIndex;
			currentIndex = ++currentIndex % length;
			play(preIndex, currentIndex);
		}
		/**
		 * 从preIndex页翻到currentIndex页
		 * preIndex 整数，翻页的起始页
		 * currentIndex 整数，翻到的那页
		 */
		function play(preIndex, currentIndex) {
			$('.slider-panel').eq(preIndex).fadeOut(500)
					.parent().children().eq(currentIndex).fadeIn(1000);
			$('.slider-item').removeClass('slider-item-selected');
			$('.slider-item').eq(currentIndex).addClass('slider-item-selected');
		}

		/**
		 * 开始轮播
		 */
		function start() {
			if(!hasStarted) {
				hasStarted = true;
				interval = setInterval(next, t);
			}
		}
		/**
		 * 停止轮播
		 */
		function stop() {
			clearInterval(interval);
			hasStarted = false;
		}

		//开始轮播
		<c:if test="${fn:length(imagelist)>1}">
			start();
		</c:if>
	});
</script>

<body>
<div class="slider">
	<ul class="slider-main">
		<c:forEach var="image" items="${imagelist}" varStatus="status">
			<li class="slider-panel">
				<img  src="${image.imageUrl}">
			</li>
		</c:forEach>
	</ul>
	<div class="slider-extra">
		<ul class="slider-nav">
			<c:forEach var="image" items="${imagelist}" varStatus="status">
				<li class="slider-item">${status.index+1}</li>
			</c:forEach>
		</ul>
		<div class="slider-page">
			<a class="slider-pre" href="javascript:;;">&lt;</a>
			<a class="slider-next" href="javascript:;;">&gt;</a>
		</div>
	</div>
</div>
</body>
    
