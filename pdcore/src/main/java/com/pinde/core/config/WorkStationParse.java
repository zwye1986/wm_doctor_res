package com.pinde.core.config;


import com.pinde.core.exception.GeneralException;
import com.pinde.core.util.XmlParse;
import org.dom4j.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkStationParse extends XmlParse{

	public WorkStationParse(File xml) throws GeneralException {
		super(xml);
	}
	
	public List<WorkStation> parse() throws GeneralException, IOException {
		
		List<WorkStation> workStationList = new ArrayList<WorkStation>();
		List<Node> workStationNodeList  = getNodes("/workStations/workStation");
		for(Node workStationNode : workStationNodeList){
			String workStationId = workStationNode.selectSingleNode("./@id").getText();
			String workStationName = workStationNode.selectSingleNode("./@name").getText();
			
			WorkStation workStation = new WorkStation();
			workStation.setWorkStationId(workStationId);
			workStation.setWorkStationName(workStationName);
			
			List<Node> moduleNodeList = workStationNode.selectNodes("./module");
			for(Node moduleNode : moduleNodeList){
				String moduleId = moduleNode.selectSingleNode("./@id").getText();
				String moduleName = moduleNode.selectSingleNode("./@name").getText();
				Node remarkNode = moduleNode.selectSingleNode("./@remark");
				Node moduleVersionNode = moduleNode.selectSingleNode("./@version");
				Node moduleViewNode = moduleNode.selectSingleNode("./@view");
				Node moduleHiddenLeftNode = moduleNode.selectSingleNode("./@hiddenLeft");
				
				Module module =  new Module();
				module.setModuleId(workStationId+"-"+moduleId);
				module.setModuleName(moduleName);
				if(remarkNode!=null){
					module.setRemark(remarkNode.getText());
				}
				if(moduleVersionNode!=null){
					module.setVersion(moduleVersionNode.getText());
				}
				if(moduleViewNode!=null){
					module.setView(moduleViewNode.getText());
				}
				if(moduleHiddenLeftNode!=null){
					module.setHiddenLeft(moduleHiddenLeftNode.getText());
				}
				
				List<Node> menuSetNodeList = moduleNode.selectNodes("./menuset");				
				for(Node menuSetNode : menuSetNodeList){
					String menuSetId = menuSetNode.selectSingleNode("./@id").getText();
					String menuSetName = menuSetNode.selectSingleNode("./@name").getText();
					Node menuSetVersionNode = menuSetNode.selectSingleNode("./@version");
					MenuSet menuSet = new MenuSet();
					menuSet.setSetId(module.getModuleId()+"-"+menuSetId);
					menuSet.setSetName(menuSetName);
					
					if(menuSetVersionNode!=null){
						menuSet.setVersion(menuSetVersionNode.getText());
					}
					
					List<Node> menuNodeList = menuSetNode.selectNodes("./menu");				
					for(Node menuNode : menuNodeList){						
						String menuId = menuNode.selectSingleNode("./@id").getText();
						String menuName = menuNode.selectSingleNode("./@name").getText();
						String menuUrl = menuNode.selectSingleNode("./@url").getText();
						String openType = null == menuNode.selectSingleNode("./@openType") ? "" : menuNode.selectSingleNode("./@openType").getText();
						Node versionNode = menuNode.selectSingleNode("./@version");
						Menu menu = new Menu();
						menu.setMenuId(module.getModuleId()+"-"+menuSetId+"-"+menuId);
						menu.setMenuName(menuName);
						menu.setMenuUrl(menuUrl);
						menu.setOpenType(openType);
						if(versionNode!=null){
							menu.setVersion(versionNode.getText());
						}

						List<Node> actionNodeList = menuNode.selectNodes("./action");	
							for(Node actionNode : actionNodeList){		
								Action action = new Action();
								String actionId = actionNode.selectSingleNode("./@id").getText();
								String actionName = actionNode.selectSingleNode("./@name").getText();
								action.setActionId("action-"+menu.getMenuId()+"-"+actionId);
								action.setActionName(actionName);
								menu.addAction(action);
							}
						menuSet.addMenu(menu);						
					}
					module.addMenuSet(menuSet);
				}
				workStation.addModule(module);
			}
			workStationList.add(workStation);
		}
		return workStationList;
	}

}
