package com.pinde.core.jspform;


import com.pinde.core.exception.GeneralException;
import com.pinde.core.util.XmlParse;
import org.dom4j.Element;
import org.dom4j.Node;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PageGroupParse extends XmlParse{

	public PageGroupParse(File xml) throws GeneralException {
		super(xml);
	}
	
	public Map<String , PageGroup> parse() throws GeneralException, IOException {
		Map<String , PageGroup> map = new HashMap<String , PageGroup>();
		PageGroup projApplyPage = null;
		List<Node> pagesList = this.getNodes("/jspForm/pageGroup");
		Iterator<Node> pagesIterator = pagesList.iterator();
		while(pagesIterator.hasNext()){
			Node pagesNode = pagesIterator.next();
			String pageGroupName = ((Element)pagesNode).attributeValue("name");
			String firstPage = ((Element)pagesNode).attributeValue("firstPage");
			String view = ((Element)pagesNode).attributeValue("view");
			String printTemeplete = ((Element)pagesNode).attributeValue("printTemeplete");
			projApplyPage = new PageGroup();
			projApplyPage.setFirstPage(firstPage);
			projApplyPage.setName(pageGroupName);
			projApplyPage.setView(view);
			projApplyPage.setPrintTemeplete(printTemeplete);
			//获取当前pages下的所有page
			List<Node> pageNodeList = pagesNode.selectNodes("./page");
			Map<String, Page> pageMap = new HashMap<String, Page>();
			for(Node pageNode : pageNodeList){
				Node nameNode = pageNode.selectSingleNode("./@name");
				Node remarkNode = pageNode.selectSingleNode("./@remark");
				Node jspNode = pageNode.selectSingleNode("./@jsp");
				Page page = new Page();
				if(nameNode!=null){
					page.setName(nameNode.getText());
				}
				if(remarkNode!=null){
					page.setRemark(remarkNode.getText());
				}
				if(jspNode!=null){
					page.setJsp(jspNode.getText());
				}
				
				//获取page元素下的所有item
				List<Node> itemNodeList = pageNode.selectNodes("./item");
				List<Item> itemList = new ArrayList<Item>();
				for(Node itemNode : itemNodeList){
					Node itemNameNode = itemNode.selectSingleNode("./@name");
					Node itemRemarkNode = itemNode.selectSingleNode("./@remark");
					Node itemMultipleNode = itemNode.selectSingleNode("./@multiple");
					Node itemIsFileNode = itemNode.selectSingleNode("./@isFile");
					Item item = new Item();
					if(itemNameNode!=null){
						item.setName(itemNameNode.getText());
					}
					if(itemRemarkNode!=null){
						item.setRemark(itemRemarkNode.getText());
					}
					if(itemMultipleNode!=null){
						item.setMultiple(itemMultipleNode.getText());
					}
					if(itemIsFileNode!=null){
						item.setIsFile(itemIsFileNode.getText());
					}
					itemList.add(item);
				}
				//获取page元素下所有的itemGroup
				List<Node> itemGroupNodeList = pageNode.selectNodes("./itemGroup");
				List<ItemGroup> itemGroupList = new ArrayList<ItemGroup>();
				for(Node itemGroupNode : itemGroupNodeList){
					Node itemGroupNameNode = itemGroupNode.selectSingleNode("./@name");
					Node itemGroupRemarkNode = itemGroupNode.selectSingleNode("./@remark");
					Node itemGroupJspNode = itemGroupNode.selectSingleNode("./@jsp");
					Node itemGroupFlowNode = itemGroupNode.selectSingleNode("./@flow");
					Node itemGroupModelStyleNode = itemGroupNode.selectSingleNode("./@modelStyle");
					ItemGroup itemGroup = new ItemGroup();
					if(itemGroupNameNode!=null){
						itemGroup.setName(itemGroupNameNode.getText());
					}
					if(itemGroupRemarkNode!=null){
						itemGroup.setRemark(itemGroupRemarkNode.getText());
					}
					if(itemGroupJspNode!=null){
						itemGroup.setJsp(itemGroupJspNode.getText());
					}
					if(itemGroupFlowNode!=null){
						itemGroup.setFlow(itemGroupFlowNode.getText());
					}
					if(itemGroupModelStyleNode!=null){
						itemGroup.setModelStyle(itemGroupModelStyleNode.getText());
					}
					//获取当前itemGroup元素下的所有item元素
					List<Node> itemGroupChildrenItemNodeList = itemGroupNode.selectNodes("./item");
					List<Item> itemGroupChildrenItemList = new ArrayList<Item>();
					for(Node itemGroupChildrenItemNode:itemGroupChildrenItemNodeList){
						Node itemGroupChildrenNameNode = itemGroupChildrenItemNode.selectSingleNode("./@name");
						Node itemGroupChildrenRemarkNode = itemGroupChildrenItemNode.selectSingleNode("./@remark");
						Node itemGroupChildrenMutipleNode = itemGroupChildrenItemNode.selectSingleNode("./@multiple");
						Node itemGroupChildrenIsFileNode = itemGroupChildrenItemNode.selectSingleNode("./@isFile");
						Item itemGroupChildrenItem = new Item();
						if(itemGroupChildrenNameNode!=null){
							itemGroupChildrenItem.setName(itemGroupChildrenNameNode.getText());
						}
						if(itemGroupChildrenRemarkNode!=null){
							itemGroupChildrenItem.setRemark(itemGroupChildrenRemarkNode.getText());
						}
						if(itemGroupChildrenMutipleNode!=null){
							itemGroupChildrenItem.setMultiple(itemGroupChildrenMutipleNode.getText());
						}
						if(itemGroupChildrenIsFileNode!=null){
							itemGroupChildrenItem.setIsFile(itemGroupChildrenIsFileNode.getText());
						}
						itemGroupChildrenItemList.add(itemGroupChildrenItem);
					}
					itemGroup.setItemList(itemGroupChildrenItemList);
					itemGroupList.add(itemGroup);
					
				}
				page.setItemGroupList(itemGroupList);
				page.setItemList(itemList);
				pageMap.put(page.getName(), page);
			}
			projApplyPage.setPageMap(pageMap);
			
			map.put(pageGroupName, projApplyPage);
		}

		return map;
	}

}
