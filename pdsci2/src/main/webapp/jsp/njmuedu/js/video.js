var	playserWaitTime;//计时器
var randomTime=-1;//随机验证秒数
var isConfirm = false;
var thePlayer;
var nowSecond;//当前播放秒数
var nowMinutes;//当前播放分钟数
var lastSecond;//前一秒播放秒数
var lastMinutes;//前一秒播放分钟数
var studyStatusId="";//前一秒播放分钟数
var allTimes=0;//总时长
var interval;
var isRun = false;
var sec;
var timer;
var isFinish="";
var chapterflow="";
var firstPlayFlag="Y";
var currentTimeInterval=""; 
var currTime="";
var isSave=false;
var playerVolumn=100;
function initPlayer(chapterFlow,control,validation,sysUrl,saveFlag,streamUrl,level,statusId){
	console.log("streamUrl:"+streamUrl);
	console.log("sysUrl:"+sysUrl);
	chapterflow=chapterFlow;
	var controls = false;
	if(control=="Y"){
		controls = true;
	}
	var isValidation = true;
	if(validation=="N"){
		isValidation = false;
	}
	if(saveFlag=='Y'){
		isSave=true;
	}
	studyStatusId=statusId;
	var url = rootPath()+"/njmuedu/course/chapterData?chapterFlow="+chapterFlow+"&level="+level;
	jboxPost(url,null,function(data){
		if(data){
			var videoFile=streamUrl+data["eduCourseChapter"].chapterFile;
			var imageFile;
			if(data["eduCourseChapter"].chapterImg){
				imageFile=sysUrl+data["eduCourseChapter"].chapterImg;
			}else{
				imageFile=rootPath()+"/js/jwplayer/player.jpg";
			}
			//console.log("videoFile:"+videoFile);
			//console.log("imageFile:"+imageFile);
			//console.log("rootPath():"+rootPath());
			currTime=data['CurrentTime'];
			var chapterTime=data["eduCourseChapter"].chapterTime;
			var chapterTimes=chapterTime.split(":");

			console.log("currTime ："+currTime);
			console.log("chapterTime ："+chapterTime);
			thePlayer =  jwplayer("myPlayer").setup({
				flashplayer: rootPath()+"/js/jwplayer/jwplayer.flash.swf", //调用播放器
				image: imageFile,		//视频预览图片
				controls: controls,
				showvolume:function(){
					return false;
				},
		        file:videoFile,	//调用视频文件
				controlbar: "bottom",  //控制条位置	//controlbar: "bottom",controlbar: "none"
				screencolor:"#fff",   //播放器颜色
				stretching: "fill",    //画面自适应
				smoothing : "false",
				repeat:"",       //always 重复播放
				width: "100%",	
				aspectratio: "16:9",
				events: {
				 onReady: function(event) {
					 $("#nowMinutes").html(data["CurrentTimeMinutes"]);
					 $("#nowSecond").html(data["CurrentTimeSecond"]);
					 firstPlayFlag="N";
					 this.play();
					 $("#video_length").html(data["eduCourseChapter"].chapterTime);//设置视频右下方总时长
					 },
				 onComplete : function() {
					$("#play_length").html($("#video_length").html());//解决输入验证码后播放完成时间比总时间晚一秒问题	
					$("#completeShowBox").show();
					stopClock();
					 if(studyStatusId!="Finish") {
						 updateChapterFinishFlag();
					 }
					 saveCourseHistory();//保存学习记录
					 isFinish='Y';
					},
				 onError: function (obj) {
					 //console.log("播放器出错!!!" + obj.message);
					  },
				 onTime:function(event) {
					    //设置当前播放时间
					    paseTime(event.position);
					 //console.log("isConfirm=="+isConfirm);
					 //console.log("isValidation=="+isValidation);
					 //console.log("randomTime=="+randomTime);
					 //console.log("event.position=="+event.position);
						if(randomTime==-1){
							randomVerify();
						}else {
							if(studyStatusId!="Finish") {
								//if (parseInt(event.position) == randomTime && !isConfirm && isValidation) {
								//	thePlayer.play(false);
								//	$("#randomConfirm").show();
								//	//设置定时器,验证码X秒后未输入，重新播放视频
								//	timer = setInterval("setSec()", 1000);
								//	stopClock();
								//	$("#playButton").unbind("click");
								//	$("#videoLevelSelect").attr("disabled", "disabled");
								//}
							}
						}
					},
				  onPlay:function() {
					  //console.log("onPlay");
					  if(data["eduCourseChapter"].chapterTime=="" || data["eduCourseChapter"].chapterTime==null){
						  zongTime();
					  }
					  if(firstPlayFlag=="N" && parseFloat(data['CurrentTime'])>0){
						  //seeeeeek();
						  setTimeout("seeeeeek()",500);
						  firstPlayFlag="Y";
					  }
					  startClock();
				  },
				  onPause:function(){
					  stopClock();
					
				  }
				},
				plugins: {
				    sharing: {link:true}
				}
		    });
			/*jwplayer().onReady(function() {
				if (window.location.hash) {
					var offset = window.location.hash.substr(3);
					thePlayer.seek(offset);
				}
			});*/
		//播放/暂停按钮控制
			bindPlayEvent();
		}
	},null,false);
	
}
function KeyOn_Space(ev){     //JW Player 空格控制播放暂停<body onKeyDown="KeyOn_Space(event);">
    ev = ev || window.event;    
    var code = (ev.keyCode || ev.which);   
    if(code == 32) {  
        jwplayer('myElement').pause();  
    }  
}
function seeeeeek(){
	thePlayer.seek(currTime);
}
function reloadVerifyCode(){
	$("#verifyImage").attr("src",rootPath()+"/captcha?random="+Math.random());
}
function doContinue(){
	var form=$("#randForm");
	var url = rootPath() + "/njmuedu/course/checkVerifyCode";
	if(form.validationEngine("validate")){
		jboxPost(url,form.serialize(),function(resp){
			if(resp=="Y"){
				thePlayer.play(true);
				$("#randomConfirm").hide();
				isConfirm = true;
				clearInterval(timer);
				bindPlayEvent();
				startClock();
				$("#videoLevelSelect").removeAttr("disabled");
			}else {
				jboxTip("验证码输入错误！");
			}
		},function(){
			reloadVerifyCode();
		},false);
	}
}
/*生成随机秒数*/
function randomVerify(){
	var min = 10;
	var max = parseInt(thePlayer.getDuration());
	randomTime = getRandomNum(min,max);
}

function paseTime(time){
	if(time) {
		time = parseInt(time);
		var minutes = Math.floor(time / 60);
		var second = Math.floor(time % 60);
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		if (second < 10) {
			second = "0" + second;
		}
		$("#nowMinutes").html(minutes);
		$("#nowSecond").html(second);

		var videol=$("#video_length").html();
		if(videol)
		{
			if(minutes>videol.split(":")[0]||minutes==videol.split(":")[0]&&second>videol.split(":")[1])
			{
				$("#play_length").html($("#video_length").html());//解决输入验证码后播放完成时间比总时间晚一秒问题
				$("#completeShowBox").show();
				stopClock();
				if(studyStatusId!="Finish") {
					updateChapterFinishFlag();
				}
				saveCourseHistory();//保存学习记录
				isFinish='Y';
			}
		}
	}
}

/*重新播放*/
function doRestart(){
	var url=rootPath()+"/njmuedu/course/updateChapterFinishFlag?chapterFlow="+chapterflow;
	jboxPost(url,null,function(){
		window.location.reload();
	},null,false);
	
}
function getRandomNum(Min,Max){ 
    var Range = Max - Min; 
    var Rand = Math.random(); 
    return(Min + Math.round(Rand * Range)); 
}
/*开始计时*/
function startClock(){
   if(!isRun){
		nowSecond =  $.trim($("#nowSecond").html());
		nowMinutes = $.trim($("#nowMinutes").html());
    currentTimeInterval=setInterval("saveCurrentTime()",3000);
    isRun = true; 
   	}
  }
/*暂停计时*/
function stopClock(){
  	if(isRun){
  		clearInterval(interval);
  		clearInterval(currentTimeInterval);
  		isRun = false; 
  		}
 }
/*计时器*/
function runclick(){
   nowSecond = parseInt(nowSecond) + 1;
   //小于十秒
   if(nowSecond < 10){
    $("#nowSecond").html('0' + nowSecond);
   }else{
    if(nowSecond == 60){
     nowSecond = '00';
	}
    $("#nowSecond").html(nowSecond);
	   }
	   //大于59秒
	   if(nowSecond == 0){
	    nowMinutes = parseInt(nowMinutes) + 1;
	    nowSecond = 0;
	    if(nowMinutes < 10){
	     $("#nowMinutes").html('0' + nowMinutes);
	}else{
	 $("#nowMinutes").html(nowMinutes);
    }
	    
   }
}
function setVolume(volume){
	jwplayer("myPlayer").setVolume(volume);
}
function setSec(){
	sec=parseInt($("#sec").text());
	
	if(sec<=1){
		doRestart();
	}
	if(sec>=1){
		sec=sec-1;	
	}
	
	$("#sec").text(sec);
}
/*设置静音*/
function setMute(obj){
	var volume =  jwplayer("myPlayer").getVolume();
	var $obj = $(obj);
	if(volume>0){
		jwplayer("myPlayer").setVolume(0);
		$obj.attr("class","video-m");
		$obj.attr("title","取消静音");
		$("#mySlider").slider("value",[0]);
		$("#mySlider").slider("disable");
	}else{
		jwplayer("myPlayer").setVolume(70);
		$obj.attr("class","video-j");
		$obj.attr("title","静音");
		$("#mySlider").slider("enable");
		$("#mySlider").slider("value",[70]);
	}
}
function bindPlayEvent(){
	$("#playButton").click(function() {
		if (thePlayer.getState() != 'PLAYING') {
			thePlayer.play(true);
			$(this).parent().attr("class","video-pause");
		} else {
			thePlayer.play(false);
			$(this).parent().attr("class","video-play");
		}
	});
}
function saveCourseHistory(){
	var url = rootPath() + "/njmuedu/course/saveCourseHistory?chapterFlow=" + $("#chapterFlow").val();
	jboxGet(url,null,null,null,false);
}
//修改时间
function saveCurrentTime(){
	if(isSave==true) {
		nowSecond = $.trim($("#nowSecond").html());
		nowMinutes = $.trim($("#nowMinutes").html());
		//jboxTip(thePlayer.getPosition());
		if(studyStatusId!="Finish") {
			if (lastMinutes != nowMinutes || lastSecond != nowSecond) {
				lastMinutes = nowMinutes;
				lastSecond = nowSecond;
				var url = rootPath() + "/njmuedu/course/updateTime?chapterFlow=" + chapterflow + "&nowSecond=" + nowSecond + "&nowMinutes=" + nowMinutes;
				jboxPost(url, null, function () {
					//alert(thePlayer.getPosition());
				}, null, false);
			}
		}
	}
}
//获取当前视频总时长
function zongTime(){
	var time=thePlayer.getDuration();
	var url=rootPath()+"/njmuedu/course/zongTime?chapterFlow="+chapterflow+"&time="+time;
	jboxPost(url,null,function(){
		window.location.reload();
	},null,false);
	
}
//视频播放完修改章节的状态及时间清空
function updateChapterFinishFlag(){
	var url=rootPath()+"/njmuedu/course/updateChapterFinishFlag?chapterFlow="+chapterflow+"&chapterFinishFlag=Y";
	jboxPost(url,null,function(){
					
	},null,false);
}