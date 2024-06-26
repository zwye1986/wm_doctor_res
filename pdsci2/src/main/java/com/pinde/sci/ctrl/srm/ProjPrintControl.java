package com.pinde.sci.ctrl.srm;

import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.jspform.PageGroup;
import com.pinde.core.pdf.DocumentVo;
import com.pinde.core.pdf.PdfDocumentGenerator;
import com.pinde.core.pdf.utils.ResourceLoader;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.srm.IProjPageBiz;
import com.pinde.sci.biz.srm.IProjProcessBiz;
import com.pinde.sci.biz.srm.IProjRecBiz;
import com.pinde.sci.biz.srm.IPubProjBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.JspFormUtil;
import com.pinde.sci.enums.pub.ProjRewardLevelEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping("/srm/proj/print")
public class ProjPrintControl extends GeneralController {

	@Autowired
	private IPubProjBiz projBiz;
	@Autowired
	private IProjRecBiz projRecBiz;
	@Autowired
	private IProjPageBiz projPageBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IProjProcessBiz projProcessBiz;
	@Autowired
	private IFileBiz pubFileBiz;

	/**
	 * 打印校验 是否存在打印模板
	 *
	 * @return
	 */
	@RequestMapping("/existsPrint")
	@ResponseBody
	public String existsPrint(String projFlow, String recFlow, String watermarkFlag, HttpServletRequest request) {
		String msg = GlobalConstant.FLAG_N;//默认为否
		boolean isPrint = false;
		PubProj proj = this.projBiz.readProject(projFlow);
		if (StringUtil.isNotBlank(recFlow)) {
			PubProjRec rec = this.projRecBiz.readProjRec(recFlow);
			if (GlobalConstant.FLAG_Y.equals(watermarkFlag)) {//如果是正式稿
				if (ProjStageEnum.Apply.getId().equals(rec.getProjStageId()) && ProjApplyStatusEnum.ThirdAudit.getId().equals(rec.getProjStatusId())) {
					//可以打印正式稿
					isPrint = true;
				} else if (ProjStageEnum.Contract.getId().equals(rec.getProjStageId()) && ProjContractStatusEnum.ThirdAudit.getId().equals(rec.getProjStatusId())) {
					//可以打印正式稿
					isPrint = true;
				} else if (ProjStageEnum.Schedule.getId().equals(rec.getProjStageId()) && ProjScheduleStatusEnum.ThirdAudit.getId().equals(rec.getProjStatusId())) {
					//可以打印正式稿
					isPrint = true;
				} else if (ProjStageEnum.Complete.getId().equals(rec.getProjStageId()) && ProjCompleteStatusEnum.ThirdAudit.getId().equals(rec.getProjStatusId())) {
					//可以打印正式稿
					isPrint = true;
				} else {
					msg = GlobalConstant.FLAG_F;
				}
			} else {
				//如果是草稿
				isPrint = true;
			}
			if (isPrint) {
				String typeId = proj.getProjTypeId();
				//判断是否存在模板
				if(StringUtil.isNotBlank(rec.getPageGroupName())){
					typeId =rec.getPageGroupName();
				}
				PageGroup pageGroup = this.projPageBiz.getPageGroup(rec.getRecTypeId(), typeId);
				if (pageGroup != null && StringUtil.isNotBlank(pageGroup.getPrintTemeplete())) {
					/*if(SpringUtil.getResource(pageGroup.getPrintTemeplete()).exists()){
						msg = GlobalConstant.FLAG_Y;
					}*/
					String path = pageGroup.getPrintTemeplete();
					File file = new File(request.getServletContext().getRealPath(path));
					if (file.isFile()) {
						msg = GlobalConstant.FLAG_Y;
					}
				}
			}

		}
		return msg;
	}

	private void printLczcbzzljs(Map<String, Object> resultMap) {
		List<ItemGroupData> projMainPeopList = (List<ItemGroupData>) resultMap.get("projMainPeop");
		if (projMainPeopList != null && !projMainPeopList.isEmpty()) {
			for (ItemGroupData igd : projMainPeopList) {
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("projMainPeop_sex") != null && StringUtil.isNotBlank((String) objMap.get("projMainPeop_sex"))) {
					String sexName = UserSexEnum.getNameById((String) objMap.get("projMainPeop_sex"));
					objMap.put("projMainPeop_sex", sexName);
				}

			}
		}


		List<ItemGroupData> projPeopList = (List<ItemGroupData>) resultMap.get("projPeop");
		if (projPeopList != null && !projPeopList.isEmpty()) {
			for (ItemGroupData igd : projPeopList) {
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("projPeop_sex") != null && StringUtil.isNotBlank((String) objMap.get("projPeop_sex"))) {
					String sexName = UserSexEnum.getNameById((String) objMap.get("projPeop_sex"));
					objMap.put("projPeop_sex", sexName);
				}

			}
		}


	}

	private void printYxxjs(Map<String, Object> resultMap) {
		//组织奖项数据
		String rewardLevel = (String) resultMap.get("rewardLevel");
		resultMap.put(ProjRewardLevelEnum.SpecialReward.getId(), "");
		resultMap.put(ProjRewardLevelEnum.FirstReward.getId(), "");
		resultMap.put(ProjRewardLevelEnum.SecondReward.getId(), "");
		resultMap.put(rewardLevel, "√");
		//组织完成人情况      
		List<ItemGroupData> applyUserList = (List<ItemGroupData>) resultMap.get("applyUser");
		if (applyUserList != null && !applyUserList.isEmpty()) {
			for (ItemGroupData igd : applyUserList) {
				Map<String, Object> objMap = igd.getObjMap();
				//语言熟练程度
				String[] str = {"jt", "sl", "lh", "yb"};
				//第一完成人
				if ("1".equals(objMap.get("applyUser_type"))) {
					for (Entry<String, Object> en : objMap.entrySet()) {
						resultMap.put(en.getKey() + "1", en.getValue());
					}

					for (int i = 0; i < str.length; i++) {
						resultMap.put("applyUser_skillful1" + str[i], "");
					}
					String skillId = (String) resultMap.get("applyUser_skillful1");
					resultMap.put("applyUser_skillful1" + skillId, "√");
				}
				//第二完成人
				if ("2".equals(objMap.get("applyUser_type"))) {
					for (Entry<String, Object> en : objMap.entrySet()) {
						resultMap.put(en.getKey() + "2", en.getValue());
					}
					for (int i = 0; i < str.length; i++) {
						resultMap.put("applyUser_skillful2" + str[i], "");
					}
					String skillId = (String) resultMap.get("applyUser_skillful2");
					resultMap.put("applyUser_skillful2" + skillId, "√");
				}
				//第三完成人
				if ("3".equals(objMap.get("applyUser_type"))) {
					for (Entry<String, Object> en : objMap.entrySet()) {
						resultMap.put(en.getKey() + "3", en.getValue());
					}
					for (int i = 0; i < str.length; i++) {
						resultMap.put("applyUser_skillful3" + str[i], "");
					}
					String skillId = (String) resultMap.get("applyUser_skillful3");
					resultMap.put("applyUser_skillful3" + skillId, "√");
				}
			}

		}
	}

	private void printQnkj(Map<String, Object> resultMap) {
		List<ItemGroupData> projMainPeopList = (List<ItemGroupData>) resultMap.get("projMainPeop");
		if (projMainPeopList != null && !projMainPeopList.isEmpty()) {
			for (ItemGroupData igd : projMainPeopList) {
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("projMainPeop_sex") != null && StringUtil.isNotBlank((String) objMap.get("projMainPeop_sex"))) {
					String sexName = UserSexEnum.getNameById((String) objMap.get("projMainPeop_sex"));
					objMap.put("projMainPeop_sex", sexName);
				}

			}
		}
		List<ItemGroupData> projPeopList = (List<ItemGroupData>) resultMap.get("projPeop");
		if (projPeopList != null && !projPeopList.isEmpty()) {
			for (ItemGroupData igd : projPeopList) {
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("projPeop_sex") != null && StringUtil.isNotBlank((String) objMap.get("projPeop_sex"))) {
					String sexName = UserSexEnum.getNameById((String) objMap.get("projPeop_sex"));
					objMap.put("projPeop_sex", sexName);
				}

			}
		}

	}

	private void printZdxk(Map<String, Object> resultMap) {
		List<ItemGroupData> personList = (List<ItemGroupData>) resultMap.get("person");
		if (null != personList && !personList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : personList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("person_education") != null && StringUtil.isNotBlank((String) objMap.get("person_education"))) {
					String education = DictTypeEnum.UserEducation.getDictNameById((String) objMap.get("person_education"));
					objMap.put("person_education", education);
				}
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> fruitList = (List<ItemGroupData>) resultMap.get("fruit");
		if (null != fruitList && !fruitList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : fruitList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> topicList = (List<ItemGroupData>) resultMap.get("topic");
		if (null != topicList && !topicList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : topicList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> thesisList = (List<ItemGroupData>) resultMap.get("thesis");
		if (null != thesisList && !thesisList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : thesisList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> magazineList = (List<ItemGroupData>) resultMap.get("magazine");
		if (null != magazineList && !magazineList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : magazineList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> groupList = (List<ItemGroupData>) resultMap.get("group");
		if (null != groupList && !groupList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : groupList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> personInfoList = (List<ItemGroupData>) resultMap.get("person");
		if (null != personInfoList && !personInfoList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : personInfoList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> completeList = (List<ItemGroupData>) resultMap.get("complete");
		if (null != completeList && !completeList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : completeList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> researchList = (List<ItemGroupData>) resultMap.get("research");
		if (null != researchList && !researchList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : researchList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> praiseList = (List<ItemGroupData>) resultMap.get("praise");
		if (null != praiseList && !praiseList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : praiseList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> sciThesisList = (List<ItemGroupData>) resultMap.get("sciThesis");
		if (null != sciThesisList && !sciThesisList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : sciThesisList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> certificateList = (List<ItemGroupData>) resultMap.get("certificate");
		if (null != certificateList && !certificateList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : certificateList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> monographList = (List<ItemGroupData>) resultMap.get("monograph");
		if (null != monographList && !monographList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : monographList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> officeList = (List<ItemGroupData>) resultMap.get("office");
		if (null != officeList && !officeList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : officeList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> equipmentList = (List<ItemGroupData>) resultMap.get("equipment");
		if (null != equipmentList && !equipmentList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : equipmentList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				objMap.put("sta_count", String.valueOf(i));
			}
		}
		List<ItemGroupData> directorList = (List<ItemGroupData>) resultMap.get("director");
		if (null != directorList && !directorList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : directorList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("director_sex") != null && StringUtil.isNotBlank((String) objMap.get("director_sex"))) {
					String sexName = UserSexEnum.getNameById((String) objMap.get("director_sex"));
					objMap.put("director_sex", sexName);
				}
				if (objMap.get("director_title") != null && StringUtil.isNotBlank((String) objMap.get("director_title"))) {
					String titleName = DictTypeEnum.UserTitle.getDictNameById((String) objMap.get("director_title"));
					objMap.put("director_title", titleName);
				}
				objMap.put("director_autograph", "");
			}
		}
		List<ItemGroupData> deputyList = (List<ItemGroupData>) resultMap.get("deputy");
		if (null != deputyList && !deputyList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : deputyList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("deputy_sex") != null && StringUtil.isNotBlank((String) objMap.get("deputy_sex"))) {
					String sexName = UserSexEnum.getNameById((String) objMap.get("deputy_sex"));
					objMap.put("deputy_sex", sexName);
				}
				if (objMap.get("deputy_title") != null && StringUtil.isNotBlank((String) objMap.get("deputy_title"))) {
					String titleName = DictTypeEnum.UserTitle.getDictNameById((String) objMap.get("deputy_title"));
					objMap.put("deputy_title", titleName);
				}
				objMap.put("deputy_autograph", "");
			}
		}
		List<ItemGroupData> otherList = (List<ItemGroupData>) resultMap.get("other");
		if (null != otherList && !otherList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : otherList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("other_sex") != null && StringUtil.isNotBlank((String) objMap.get("other_sex"))) {
					String sexName = UserSexEnum.getNameById((String) objMap.get("other_sex"));
					objMap.put("other_sex", sexName);
				}
				if (objMap.get("other_title") != null && StringUtil.isNotBlank((String) objMap.get("other_title"))) {
					String titleName = DictTypeEnum.UserTitle.getDictNameById((String) objMap.get("other_title"));
					objMap.put("other_title", titleName);
				}
				objMap.put("other_autograph", "");
			}
		}
	}

	private void printZdjbcrbyfkz(Map<String, Object> resultMap) {
		List<ItemGroupData> directorList = (List<ItemGroupData>) resultMap.get("director");
		if (null != directorList && !directorList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : directorList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("director_sex") != null && StringUtil.isNotBlank((String) objMap.get("director_sex"))) {
					String sexName = UserSexEnum.getNameById((String) objMap.get("director_sex"));
					objMap.put("director_sex", sexName);
				}
				if (objMap.get("director_title") != null && StringUtil.isNotBlank((String) objMap.get("director_title"))) {
					String titleName = DictTypeEnum.UserTitle.getDictNameById((String) objMap.get("director_title"));
					objMap.put("director_title", titleName);
				}
			}
		}
		List<ItemGroupData> otherList = (List<ItemGroupData>) resultMap.get("other");
		if (null != otherList && !otherList.isEmpty()) {
			int i = 0;
			for (ItemGroupData igd : otherList) {
				i++;
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("other_sex") != null && StringUtil.isNotBlank((String) objMap.get("other_sex"))) {
					String sexName = UserSexEnum.getNameById((String) objMap.get("other_sex"));
					objMap.put("other_sex", sexName);
				}
				if (objMap.get("other_title") != null && StringUtil.isNotBlank((String) objMap.get("other_title"))) {
					String titleName = DictTypeEnum.UserTitle.getDictNameById((String) objMap.get("other_title"));
					objMap.put("other_title", titleName);
				}
			}
		}
	}

	@RequestMapping("/info")
	public void printInfo(String projFlow, String recFlow, String watermarkFlag, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PubProj proj = this.projBiz.readProject(projFlow);
		WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
		if (StringUtil.isNotBlank(recFlow)) {
			PubProjRec rec = this.projRecBiz.readProjRec(recFlow);
			String typeId = proj.getProjTypeId();
			//判断是否存在模板
			if(StringUtil.isNotBlank(rec.getPageGroupName())){
				typeId =rec.getPageGroupName();
			}
			PageGroup pageGroup = this.projPageBiz.getPageGroup(rec.getRecTypeId(), typeId);
//			PageGroup pageGroup = this.projPageBiz.getPageGroup(rec.getRecTypeId(), proj.getProjTypeId());
			if (rec != null) {
				String content = rec.getRecContent();
				if (StringUtil.isNotBlank(content)) {
					Map<String, Object> resultMap = JspFormUtil.parseXmlStr(content);
					resultMap.put("projNo", proj.getProjNo());
					if(StringUtil.isNotBlank(proj.getAcceptNumber())){
						resultMap.put("acceptNum", proj.getAcceptNumber());//受理编号
					}
					if ("szwsj.lczcbzzljs".equals(proj.getProjTypeId())) {
						printLczcbzzljs(resultMap);
					} else if ("szwsj.yxxjs".equals(proj.getProjTypeId())) {
						printYxxjs(resultMap);
					} else if ("szwsj.qnkj".equals(proj.getProjTypeId())) {
						printQnkj(resultMap);
						//如果是卫生局版本的话
						String srmForUse = InitConfig.getSysCfg("srm_for_use");
						if (GlobalConstant.USER_LIST_GLOBAL.equals(srmForUse)) {
							if (ProjRecTypeEnum.Contract.getId().equals(rec.getRecTypeId())) {
								String globalOrgFlow = InitConfig.getSysCfg("global_org_flow");
								if (StringUtil.isNotBlank(globalOrgFlow)) {
									if (globalOrgFlow.equals(proj.getChargeOrgFlow())) {
										resultMap.put("caiZhengBoKuan", "市财政拨款");
										resultMap.put("jiaAndBing", "甲方");
									} else {
										resultMap.put("bingfang", "丙方——" + proj.getChargeOrgName() + "，");
										resultMap.put("caiZhengBoKuan", "县、区财政拨款");
										resultMap.put("jiaAndBing", "丙方");
									}

								}
							}

						}
					} else if ("szwsj.zdxk".equals(proj.getProjTypeId()) || "szwsj.lcyxzx".equals(proj.getProjTypeId())) {
						printZdxk(resultMap);
					} else if ("szwsj.zdjbcrbyfkz".equals(proj.getProjTypeId())) {
						printZdjbcrbyfkz(resultMap);
					} //else if ("wxwsj".equals(proj.getProjTypeId().substring(0, 5))) {
						else if (("wxwsj.qnxm".equals(proj.getProjTypeId()) || "wxwsj.zdxm".equals(proj.getProjTypeId())||"wxwsj.kpxm".equals(proj.getProjTypeId())||
							"wxwsj.msxm".equals(proj.getProjTypeId()))&&(!"contract".equals(pageGroup.getPrintTemeplete().substring(19, 27)))) {
						/*String path = pageGroup.getPrintTemeplete();
						String pathStageId = path.substring(19, 27);
						if ("contract".equals(pathStageId)) {
								pageGroup.setPrintTemeplete("/jsp/srm/proj/form/contract/wxwsj.zdxm/printTemeplete.docx");
						}*/
						String projYear = proj.getProjYear();
						if (StringUtil.isNotBlank(projYear)) {
							Integer year = Integer.parseInt(projYear);
							Integer year2 = Integer.valueOf(year + 1);
							Integer year3 = Integer.valueOf(year + 2);
							Integer year4 = Integer.valueOf(year + 3);
							resultMap.put("budgetYear1", projYear);
							resultMap.put("budgetYear2", String.valueOf(year2));
							resultMap.put("budgetYear3", String.valueOf(year3));
							resultMap.put("budgetYear4", String.valueOf(year4));
						}
						printWxwsjZdxm(resultMap);
						resultMap.put("projTypeName", proj.getProjTypeName());
						resultMap.put("projName", proj.getProjName());
						resultMap.put("projNo", proj.getProjNo());
						resultMap.put("applyOrgName", proj.getApplyOrgName());
						resultMap.put("projStartTime", proj.getProjStartTime());
						resultMap.put("projEndTime", proj.getProjEndTime());
						if (StringUtil.isNotBlank(proj.getApplyOrgFlow())) {
							SysOrg sysOrg = orgBiz.readSysOrg(proj.getApplyOrgFlow());
							if (sysOrg != null) {
								resultMap.put("orgAddress", sysOrg.getOrgAddress());
							}
						}
						resultMap.put("chargeOrgName", proj.getChargeOrgName());
						resultMap.put("applyUserName", proj.getApplyUserName());
						if (StringUtil.isNotBlank(proj.getApplyUserFlow())) {
							SysUser sysUser = userBiz.readSysUser(proj.getApplyUserFlow());
							if (sysUser != null) {
								resultMap.put("userPhone", sysUser.getUserPhone());
							}
						}
						//审核意见
						PubProjProcess projProcess = new PubProjProcess();
						projProcess.setProjFlow(projFlow);
						String orderByClause = "CREATE_TIME DESC";
						List<PubProjProcess> projProcessList = projProcessBiz.searchAuditProjProcess(projProcess, orderByClause);
						if (projProcessList != null && !projProcessList.isEmpty()) {
							for (int i = 0; i < projProcessList.size(); i++) {
								PubProjProcess process = projProcessList.get(i);
								if ((proj.getChargeOrgFlow()).equals(process.getOperOrgFlow())) {
									resultMap.put("chargeOrgAuditContent", process.getAuditContent());
									continue;
									/*if(ProjApplyStatusEnum.ThirdAudit.getId().equals(process.getProjStatusId())){
                                        resultMap.put("thirdAuditContent", process.getAuditContent());
										continue;
									}*/
								}
								if (ProjApplyStatusEnum.FirstAudit.getId().equals(process.getProjStatusId())) {
									resultMap.put("firstAuditContent", process.getAuditContent());
									break;
								}
							}
						}
					} else if ("yhwsj".equals(proj.getProjTypeId().substring(0, 5))) {
						printFileList(resultMap);
						resultMap.put("projName", proj.getProjName());
						resultMap.put("applyOrgName", proj.getApplyOrgName());
						resultMap.put("projStartTime", proj.getProjStartTime());
						resultMap.put("projEndTime", proj.getProjEndTime());
						resultMap.put("applyDate", DateUtil.transDate(proj.getCreateTime()));

						if ("yhwsj.zdxk".equals(proj.getProjTypeId())) {
							temeplete = printYhReserveLeader(pageGroup, proj, watermarkFlag, request, resultMap);
						}
					}else if (("jszyyj.zdxm".equals(proj.getProjTypeId()))||("jszyyj.ybxm".equals(proj.getProjTypeId()))||("jsszyy.kyxm".equals(proj.getProjTypeId()))){
                        if(StringUtil.isNotBlank((String) resultMap.get("agreeOpinion1"))){
                            resultMap.put("agreeOpinion1", "☑");
                        }else{
                            resultMap.put("agreeOpinion1", "☐");
                        }
                        if(StringUtil.isNotBlank((String) resultMap.get("agreeOpinion2"))){
                            resultMap.put("agreeOpinion2", "☑");
                        }else{
                            resultMap.put("agreeOpinion2", "☐");
                        }
                        if(StringUtil.isNotBlank((String) resultMap.get("agreeOpinion3"))){
                            resultMap.put("agreeOpinion3", "☑");
                        }else{
                            resultMap.put("agreeOpinion3", "☐");
                        }
                        if(StringUtil.isNotBlank((String) resultMap.get("agreeOpinion4"))){
                            resultMap.put("agreeOpinion4", "☑");
                        }else{
                            resultMap.put("agreeOpinion4", "☐");
                        }
						/*预期研究结果*/
						if(StringUtil.isNotBlank((String) resultMap.get("projFruit1"))){
							resultMap.put("projFruit1", "☑");
						}else{
							resultMap.put("projFruit1", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("projFruit2"))){
							resultMap.put("projFruit2", "☑");
						}else{
							resultMap.put("projFruit2", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("projFruit3"))){
							resultMap.put("projFruit3", "☑");
						}else{
							resultMap.put("projFruit3", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("projFruit4"))){
							resultMap.put("projFruit4", "☑");
						}else{
							resultMap.put("projFruit4", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("projFruit5"))){
							resultMap.put("projFruit5", "☑");
						}else{
							resultMap.put("projFruit5", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("projFruit6"))){
							resultMap.put("projFruit6", "☑");
						}else{
							resultMap.put("projFruit6", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("projFruit7"))){
							resultMap.put("projFruit7", "☑");
						}else{
							resultMap.put("projFruit7", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("projFruit8"))){
							resultMap.put("projFruit8", "☑");
						}else{
							resultMap.put("projFruit8", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("projFruit9"))){
							resultMap.put("projFruit9", "☑");
						}else{
							resultMap.put("projFruit9", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("projFruit10"))){
							resultMap.put("projFruit10", "☑");
						}else{
							resultMap.put("projFruit10", "☐");
						}
						//变更内容
						if(StringUtil.isNotBlank((String) resultMap.get("change_conclusionTime"))){
							resultMap.put("change_conclusionTime", "☑");
						}else{
							resultMap.put("change_conclusionTime", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("change_research"))){
							resultMap.put("change_research", "☑");
						}else{
							resultMap.put("change_research", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("change_applyUserName"))){
							resultMap.put("change_applyUserName", "☑");
						}else{
							resultMap.put("change_applyUserName", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("change_member"))){
							resultMap.put("change_member", "☑");
						}else{
							resultMap.put("change_member", "☐");
						}
						if(StringUtil.isNotBlank((String) resultMap.get("change_other"))){
							resultMap.put("change_other", "☑");
						}else{
							resultMap.put("change_other", "☐");
						}
                    }
					String[] projTypeArray = {"jsswst.mskt","jsswst.qnkt","jsswst.zdxkt","jsswst.xxhkt","jsswst.hlxkt","jsswst.jcws","jsswst.wsbzyj","jsswst.rsrcgl","jsswst.wszyjsjy","jsswst.dxwtkt"};
					List<String> projTypeList =new ArrayList<>(Arrays.asList(projTypeArray));
					if(projTypeList.contains(proj.getProjTypeId())){
						printFileList(resultMap);
					}
                    if ("wxwsj.qnxm".equals(proj.getProjTypeId()) || "wxwsj.zdxm".equals(proj.getProjTypeId())||"wxwsj.msxm".equals(proj.getProjTypeId())){
                        resultMap.put("acceptNum", proj.getProjTypeName());
                    }
					//获取附件列表
					List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
					Map<String, PubFile> pageFileMap = this.projBiz.getFile(resultMap);
					List<ItemGroupData> fileList = new ArrayList<ItemGroupData>();
					ItemGroupData itemGroupData = null;
					if (fileFlows != null && !fileFlows.isEmpty()) {
						for (String fileFlow : fileFlows) {
							PubFile file = pageFileMap.get(fileFlow);
							Map<String, Object> objMap = new HashMap<String, Object>();
							objMap.put("file_name", file == null ? "" : file.getFileName());
							itemGroupData = new ItemGroupData();
							itemGroupData.setFlow(fileFlow);
							itemGroupData.setObjMap(objMap);
							fileList.add(itemGroupData);
						}

					}
					resultMap.put("file", fileList);
					String watermark = GeneralMethod.getWatermark(watermarkFlag);
					ServletOutputStream out = null;
					try {
						//DocxUtil.convert(SpringUtil.getResource("classpath:srm/product/print/lczdzzjs-sbs.xml").getFile(), "D:\\"+DateUtil.getCurrDateTime()+".docx", resultMap);
						if (!"yhwsj.zdxk".equals(proj.getProjTypeId())) {
							ServletContext context = request.getServletContext();
							String path = pageGroup.getPrintTemeplete();
							//无锡卫生局若干项目需要根据合同名称切换打印模板
							if("wxwsj.lcyxzx".equals(proj.getProjTypeId())&&"Contract".equals(rec.getRecTypeId())){
								path = "/jsp/srm/proj/form/contract/wxwsj.lcyxzx/"+resultMap.get("contractName")+".docx";
							}
							if("wxwsj.yxcxpt".equals(proj.getProjTypeId())&&"Contract".equals(rec.getRecTypeId())){
								path = "/jsp/srm/proj/form/contract/wxwsj.yxcxpt/"+resultMap.get("contractName")+".docx";
							}
							if("wxwsj.yxjcrc".equals(proj.getProjTypeId())&&"Contract".equals(rec.getRecTypeId())){
								path = "/jsp/srm/proj/form/contract/wxwsj.yxjcrc/"+resultMap.get("contractName")+".docx";
							}
							if("wxwsj.yxljrc".equals(proj.getProjTypeId())&&"Contract".equals(rec.getRecTypeId())){
								path = "/jsp/srm/proj/form/contract/wxwsj.yxljrc/"+resultMap.get("contractName")+".docx";
							}
							if("wxwsj.yxzdrc".equals(proj.getProjTypeId())&&"Contract".equals(rec.getRecTypeId())){
								path = "/jsp/srm/proj/form/contract/wxwsj.yxzdrc/"+resultMap.get("contractName")+".docx";
							}
							if("wxwsj.yxzdxk".equals(proj.getProjTypeId())&&"Contract".equals(rec.getRecTypeId())){
								path = "/jsp/srm/proj/form/contract/wxwsj.yxzdxk/"+resultMap.get("contractName")+".docx";
							}//无锡模板替换完毕

							temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), resultMap, watermark, true);
						}
						if (temeplete != null) {
							response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
							String name = proj.getProjTypeName() + rec.getRecTypeName() + ".docx";
							response.setHeader("Content-disposition", "attachment; filename="
									+ new String(name.getBytes("gbk"),
									"ISO8859-1") + "");

							out = response.getOutputStream();
							(new SaveToZipFile(temeplete)).save(out);

							out.flush();

						}

					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					} finally {
						try {
							if (out != null) {
								out.close();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					//获取附件列表
					/*Map<String , PubFile> pageFileMap = this.projBiz.getFile(resultMap);
					if(pageFileMap!=null){
						model.addAttribute("pageFileMap" , pageFileMap);
					}*/
				}

			}

		}
	}

	private void printWxwsjZdxm(Map<String, Object> resultMap) {
		//年度计划及年度目标
		List<ItemGroupData> yearPlanTargetList = (List<ItemGroupData>) resultMap.get("yearPlanTarget");
		if (null != yearPlanTargetList && !yearPlanTargetList.isEmpty()) {
			for (ItemGroupData igd : yearPlanTargetList) {
				Map<String, Object> objMap = igd.getObjMap();
				String yearPlanTarget_startYear = new String();
				String yearPlanTarget_endYear = new String();
				if (objMap.get("yearPlanTarget_startYear") != null && StringUtil.isNotBlank((String) objMap.get("yearPlanTarget_startYear"))) {
					yearPlanTarget_startYear = (String) objMap.get("yearPlanTarget_startYear");
				}
				if (objMap.get("yearPlanTarget_endYear") != null && StringUtil.isNotBlank((String) objMap.get("yearPlanTarget_endYear"))) {
					yearPlanTarget_endYear = (String) objMap.get("yearPlanTarget_endYear");
				}
				objMap.put("yearPlanTarget_startEndYear", yearPlanTarget_startYear + "~" + yearPlanTarget_endYear);
			}
		}
		//主要研究人员
		List<ItemGroupData> mainResearcherList = (List<ItemGroupData>) resultMap.get("mainResearcher");
		if (null != mainResearcherList && !mainResearcherList.isEmpty()) {
			for (ItemGroupData igd : mainResearcherList) {
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("mainResearcher_gender") != null && StringUtil.isNotBlank((String) objMap.get("mainResearcher_gender"))) {
					String sexName = UserSexEnum.getNameById((String) objMap.get("mainResearcher_gender"));
					objMap.put("mainResearcher_gender", sexName);
				}
				if (objMap.get("mainResearcher_title") != null && StringUtil.isNotBlank((String) objMap.get("mainResearcher_title"))) {
					String titleName = DictTypeEnum.UserTitle.getDictNameById((String) objMap.get("mainResearcher_title"));
					objMap.put("mainResearcher_title", titleName);
				}
				if (objMap.get("mainResearcher_duty") != null && StringUtil.isNotBlank((String) objMap.get("mainResearcher_duty"))) {
					String dutyName = DictTypeEnum.UserPost.getDictNameById((String) objMap.get("mainResearcher_duty"));
					objMap.put("mainResearcher_duty", dutyName);
				}
				objMap.put("mainResearcher_autograph", "");
			}
		}
		//参加人员
		List<ItemGroupData> joinUserList = (List<ItemGroupData>) resultMap.get("joinUser");
		if (null != joinUserList && !joinUserList.isEmpty()) {
			for (ItemGroupData igd : joinUserList) {
				Map<String, Object> objMap = igd.getObjMap();
				if (objMap.get("joinUser_gender") != null && StringUtil.isNotBlank((String) objMap.get("joinUser_gender"))) {
					String sexName = UserSexEnum.getNameById((String) objMap.get("joinUser_gender"));
					objMap.put("joinUser_gender", sexName);
				}
				if (objMap.get("joinUser_title") != null && StringUtil.isNotBlank((String) objMap.get("joinUser_title"))) {
					String titleName = DictTypeEnum.UserTitle.getDictNameById((String) objMap.get("joinUser_title"));
					objMap.put("joinUser_title", titleName);
				}
				if (objMap.get("joinUser_duty") != null && StringUtil.isNotBlank((String) objMap.get("joinUser_duty"))) {
					String dutyName = DictTypeEnum.UserPost.getDictNameById((String) objMap.get("joinUser_duty"));
					objMap.put("joinUser_duty", dutyName);
				}
				objMap.put("joinUser_autograph", "");
			}
		}

		printFileList(resultMap);
	}

	/**
	 * 附件清单(指定文件)
	 *
	 * @param resultMap
	 */
	private void printFileList(Map<String, Object> resultMap) {
		List<ItemGroupData> fileEditList = (List<ItemGroupData>) resultMap.get("fileEdit");
		List<ItemGroupData> existFileEditList = new ArrayList<ItemGroupData>();
		if (null != fileEditList && !fileEditList.isEmpty()) {
			for (ItemGroupData igd : fileEditList) {
				Map<String, Object> objMap = igd.getObjMap();
				String fileEdit_file = (String) objMap.get("fileEdit_file");
				String fileEdit_fileName = (String) objMap.get("fileEdit_fileName");
				objMap.put("fileNameList", fileEdit_fileName);
				if (StringUtil.isNotBlank(fileEdit_file)) {
					objMap.put("file_isHave", "√");
					existFileEditList.add(igd);
				}
			}
		}
		resultMap.put("fileEdit", existFileEditList);
	}


	/**
	 * 打印余杭重点学科
	 *
	 * @param watermarkFlag
	 * @param request
	 * @param resultMap
	 * @return
	 * @throws Exception
	 * @throws Docx4JException
	 * @throws IOException
	 */
	private WordprocessingMLPackage printYhReserveLeader(PageGroup pageGroup, PubProj proj, String watermarkFlag, HttpServletRequest request, Map<String, Object> resultMap)
			throws Exception {
		WordprocessingMLPackage temeplete;
		List<WordprocessingMLPackage> templates = new ArrayList<WordprocessingMLPackage>();
		String path = pageGroup.getPrintTemeplete();
		String parentPath = path.substring(0, path.lastIndexOf("/"));
		ServletContext context = request.getServletContext();
		String watermark = GeneralMethod.getWatermark(watermarkFlag);
		WordprocessingMLPackage printTemeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path)), resultMap, watermark, true);
		List<WordprocessingMLPackage> reserveLeaderTemplates = new ArrayList<WordprocessingMLPackage>();
		List<ItemGroupData> reserveLeaderList = (List<ItemGroupData>) resultMap.get("reserveLeader");
		if (null != reserveLeaderList && !reserveLeaderList.isEmpty()) {
			for (ItemGroupData igd : reserveLeaderList) {
				Map<String, Object> objMap = igd.getObjMap();
				resultMap.put("reserveLeader_name", StringUtil.defaultString((String) objMap.get("reserveLeader_name")));
				resultMap.put("reserveLeader_gender", StringUtil.defaultString((String) objMap.get("reserveLeader_gender")));
				resultMap.put("reserveLeader_birthday", StringUtil.defaultString((String) objMap.get("reserveLeader_birthday")));
				resultMap.put("reserveLeader_idNo", StringUtil.defaultString((String) objMap.get("reserveLeader_idNo")));
				resultMap.put("reserveLeader_title", StringUtil.defaultString((String) objMap.get("reserveLeader_title")));
				resultMap.put("reserveLeader_post", StringUtil.defaultString((String) objMap.get("reserveLeader_post")));
				resultMap.put("reserveLeader_academicPost", StringUtil.defaultString((String) objMap.get("reserveLeader_academicPost")));
				resultMap.put("reserveLeader_major", StringUtil.defaultString((String) objMap.get("reserveLeader_major")));
				resultMap.put("reserveLeader_education", StringUtil.defaultString((String) objMap.get("reserveLeader_education")));
				resultMap.put("reserveLeader_mobilePhone", StringUtil.defaultString((String) objMap.get("reserveLeader_mobilePhone")));
				resultMap.put("reserveLeader_introduce", StringUtil.defaultString((String) objMap.get("reserveLeader_introduce")));
				//后备学科带头人模板
				path = parentPath + "/printTemeplete2.docx";
				WordprocessingMLPackage template2 = Docx4jUtil.convert(new File(context.getRealPath(path)), resultMap, watermark, true);
				reserveLeaderTemplates.add(template2);
			}
		}

		path = parentPath + "/printTemeplete3.docx";
		WordprocessingMLPackage printTemeplete3 = Docx4jUtil.convert(new File(context.getRealPath(path)), resultMap, watermark, true);
		//合并模板
		templates.add(printTemeplete1);
		if (reserveLeaderTemplates.size() > 0) {
			templates.addAll(reserveLeaderTemplates);
		}
		templates.add(printTemeplete3);
		temeplete = Docx4jUtil.mergeDocx(templates);
		return temeplete;
	}

	@RequestMapping(value = "/printPDFRec")
	public void printPDFRec(String projFlow, String recFlow, HttpServletResponse response) throws Exception{
		PubProj proj = this.projBiz.readProject(projFlow);
		if("jsswst.jcrc".equals(proj.getProjTypeId())){
			String formName = "江苏省医学杰出人才申报书";
			String temeDir = "srm/jsswst.jcrc.html";
			printPDFRec(projFlow,recFlow,response,formName,temeDir);
		}
		if("jsswst.yxzdrc".equals(proj.getProjTypeId())){
			String formName = "江苏省医学重点人才申报书";
			String temeDir = "srm/jsswst.yxzdrc.html";
			printPDFRec(projFlow,recFlow,response,formName,temeDir);
		}
		if("jsswst.yxcx".equals(proj.getProjTypeId())){
			String formName = "江苏省医学领军人才与创新团队申报书";
			String temeDir = "srm/jsswst.yxcx.html";
			printPDFRec(projFlow,recFlow,response,formName,temeDir);
		}
		if("jsswst.yxzdxk".equals(proj.getProjTypeId())){
			String formName = "江苏省医学重点学科(实验室)申报书";
			String temeDir = "srm/jsswst.yxzdxk.html";
			printPDFRec(projFlow,recFlow,response,formName,temeDir);
		}
		if("jsswst.qnyxrc".equals(proj.getProjTypeId())){
			String formName = "江苏省青年医学人才申报书";
			String temeDir = "srm/jsswst.qnyxrc.html";
			printPDFRec(projFlow,recFlow,response,formName,temeDir);
		}
		if("jsswst.lcyx".equals(proj.getProjTypeId())){
			String formName = "江苏省临床医学中心(创新平台)申报书";
			String temeDir = "srm/jsswst.lcyx.html";
			printPDFRec(projFlow,recFlow,response,formName,temeDir);
		}
        if("jszyyj.zdxm".equals(proj.getProjTypeId())){
            String formName = "江苏省中医药科技项目申报书";
            String temeDir = "srm/jszyyj.zyykjxm.html";
            printPDFRec(projFlow,recFlow,response,formName,temeDir);
        }
	}

	public void printPDFRec(String projFlow, String recFlow, HttpServletResponse response,String formName,String temeDir) throws Exception{
		final String fileName = formName;
		String outputFileClass = ResourceLoader.getPath("/");
		String outputFile = new File(outputFileClass) + "/load/" + fileName + ".pdf";
		File file = new File(outputFile);
		PubProj proj = this.projBiz.readProject(projFlow);
		if (StringUtil.isNotBlank(recFlow)) {
			PubProjRec rec = this.projRecBiz.readProjRec(recFlow);
			PageGroup pageGroup = this.projPageBiz.getPageGroup(rec.getRecTypeId(), proj.getProjTypeId());
			if (rec != null) {
				String content = rec.getRecContent();
				if (StringUtil.isNotBlank(content)) {
                    //String content = contentTemp.replaceAll("(\r\n|\r|\n|\n\r)", "&lt;br/&gt;");
                    final Map<String, Object> root = JspFormUtil.parseXmlStr(content);
					root.put("projNo", proj.getProjNo());
					//获取附件列表
					List<String> fileFlows = this.projBiz.getFileFlows(root);
					Map<String, PubFile> pageFileMap = this.projBiz.getFile(root);
					List<ItemGroupData> fileList = new ArrayList<ItemGroupData>();
					ItemGroupData itemGroupData;
					if (fileFlows != null && !fileFlows.isEmpty()) {
						for (String fileFlow : fileFlows) {
							PubFile pubFile = pageFileMap.get(fileFlow);
							Map<String, Object> objMap = new HashMap<String, Object>();
							objMap.put("file_name", pubFile == null ? "" : pubFile.getFileName());
							itemGroupData = new ItemGroupData();
							itemGroupData.setFlow(fileFlow);
							itemGroupData.setObjMap(objMap);
							fileList.add(itemGroupData);
						}

					}
					root.put("file", fileList);
					// 模板数据
					DocumentVo vo = new DocumentVo() {
						@Override
						public String findPrimaryKey() {
							return fileName;
						}

						@Override
						public Map<String, Object> fillDataMap() {
							return root;
						}
					};
					String template = temeDir;
					PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
					// 生成pdf
					pdfGenerator.generate(template, vo, outputFile);

					pubFileBiz.downFile(file, response);
				}
			}
		}


	}

}