
<script>
	function showNext(obj)
	{
		var nextTr=$(obj).parent("tr").next("tr");
		if($(nextTr).is(":hidden")){
			$(nextTr).show();
		}else{
			$(nextTr).hide();
		}
	}
</script>
<table width="100%">
	<tr>
		<th colspan="4" onclick="showNext(this);" style="font-color:blue;">外科临床技能相关评分标准</th>
	</tr>
	<tr hidden>
		<td colspan="4">
				<div id="msg"><p style="text-indent:21px;line-height:150%"><span
					style=";font-size:14px">1．检查住院病历(100&nbsp;分)</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(1)主诉：5&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①主要症状和发病时间有错误……2(“……”为应扣分数，下类同)</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②发病部位、主要症状、发病时间有遗漏……2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③没有突出重点……1</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(2)现病史：25&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①起病情况叙述不清……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②发病经过顺序不清、条理性差……1～4</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③主要症状不清……1～4</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">④伴随症状不清……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑤有关鉴别的症状或重要的阴性症状不清……1～4</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑥遗漏病史……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑦治疗经过叙述不全……1～2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑧一般症状未叙述或不全面……1～2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(3)其他病史：5&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①项目有遗漏每项……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②有关阴性病史未提及……l～2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(4)体检：10&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①一般项目有遗漏……1</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②重要阳性或阴性体征有遗漏……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③检查结果与实际不符……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">④描述不准确……1～2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑤顺序错误……1</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(5)外科情况：15&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①检查顺序错误……1</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②没有图示……1</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③重要阳性体征遗漏或有错误……1~4</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">④检查结果与实际不符……1~4</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑤阴性体征遗漏……1~3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑥描述不清楚或不准确……1～2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(6)辅助检查：5&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①与诊断或治疗有关的重要检查结果未列出……1～2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②不能熟练掌握简单必要的检查方法及其结果…1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(7)诊断：10&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①主要诊断错误或部位、程度有遗漏……1～5</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②次要诊断错误或有遗漏……1～3．．</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③诊断不够明确、较笼统或顺序错误……1～2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(8)病程记录：20&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①治疗经过有遗漏……2～6</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②病情变化有遗漏……2～6</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③上级医生意见没有及时记录……2～4</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">④没有交班注意的病情……2～4</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(10)规格文字：5&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①地址、年龄不详……1～2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②字迹潦草或错别字……1</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③规格不符、不符合医学术语……1～2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">2．手术操作(100&nbsp;分)</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(1)入室与洗手：25&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①入手术室前，口罩、帽、衬衣裤穿着不合要求……1～5</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②洗手前未剪指甲，未按要求擦手或要求不严格……1～6</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③擦手巾不正确……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">④泡手未看时间、或手臂未够泡手深度……1～5</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑤泡手时上臂碰桶边，泡手后未滴干浸泡液，双手姿势不正确……1～5</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑥有意浸泡过时……1</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(2)消毒铺巾：25&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①消毒有遗漏、范围不准确、次序错误……1～5</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②消毒碘酊未干即涂酒精或擦净碘酊……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③碘酊或酒精流向其他部位……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">④消毒时碰触污染部位……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑤铺巾顺序错误或违反无菌原则……1～5</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑥铺巾不准确……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑦操作不熟练……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(3)穿手术衣、戴手套：25&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①穿手术衣时手触及污染部位……1～4</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②穿手术衣时手触及无菌部位……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③送腰带方法不对或忘记送腰带……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">④手术衣或手触及其他物件……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑤取手套未拿反卷部位……1～2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑥戴手套时手触及手套外面……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑦动作不熟练……1～4</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑧滑石粉飞扬或未洗净手套外滑石粉……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">(4)手术基本操作：25&nbsp;分</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①持刀、剪、持钳、持镊方法错误……1～5</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②止血不准确或未压迫止血……1～3</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③缝合方法不准确、组织对合不好……1～4</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">④打结牵拉组织、过松或过紧、动作生硬……1～5</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑤打结拉线方向错误或打反结……1～6．</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑥头离术野过近或术中交换站立位置错误……1～2</span>
				</p>
				<p style="text-indent:21px;line-height:150%"><span
						style=";font-size:14px">3</span><span
						style=";font-size:14px">．病房工作及换药</span><span
						style=";font-size:14px">(100&nbsp;</span><span
						style=";font-size:14px">分</span><span
						style=";font-size:14px">)</span></p>
				<p style="text-indent:21px;line-height:150%"><span
						style=";font-size:14px">(1)</span><span
						style=";font-size:14px">病房工作：</span><span
						style=";font-size:14px">40&nbsp;</span><span
						style=";font-size:14px">分</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①工作安排统筹不好，工作秩序条理差……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②查房无主动报告病史或病情重点突出不够，不够准确，简练……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③对病人态度生硬……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">4</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">④对术前准备、术后并发症不熟练……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">6</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑤对病情不熟悉……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑥医嘱、处方、各种检查申请单书写错误……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑦验单粘贴不整齐或漏贴……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑧填写各种病案有错误或遗漏……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p style="text-indent:21px;line-height:150%"><span
						style=";font-size:14px">(2)</span><span
						style=";font-size:14px">换药：</span><span
						style=";font-size:14px">50&nbsp;</span><span
						style=";font-size:14px">分</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①换药次序未注意无菌原则……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">4</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">②换药前未戴口罩、帽子……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">③换药前未检查伤口……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">3</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">④敷药、用品准备不完全或浪费……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">3</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑤拿换药碗、镊不合无菌原则……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑥未洗去胶布痕迹、撕胶布动作粗暴……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">3</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑦内层敷料用手取去……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">4</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑧消毒不合无菌要求，未洗净伤口……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑨所用引流物不恰当或伤口处理不当……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑩贴胶布错误或包扎不当……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">4</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑾换药后敷料、用具的处理有错误……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">4</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⑿换药后不洗手……</span><span
						style=";font-size:14px">2</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">⒀操作次序有错误……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">3</span></p>
				<p style="text-indent:21px;line-height:150%"><span
						style=";font-size:14px">(3)</span><span
						style=";font-size:14px">拆线：</span><span
						style=";font-size:14px">10&nbsp;</span><span
						style=";font-size:14px">分</span></p>
				<p style="text-indent:21px;line-height:150%"><span style=";font-size:14px">①拆线前、后未消毒……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p><span style=";font-size:14px">②剪线位置或拉线方向错误……</span><span
						style=";font-size:14px">1</span><span
						style=";font-size:14px">～</span><span
						style=";font-size:14px">5</span></p>
				<p><br></p>
		</td>
	</tr>
</table>