package com.pinde.sci.biz.osca.impl;


import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaFormCfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.OscaFromMapper;
import com.pinde.sci.dao.osca.OscaFromExtMapper;
import com.pinde.sci.model.mo.OscaFrom;
import com.pinde.sci.model.mo.OscaFromExample;
import com.pinde.sci.model.mo.OscaSubjectStationFrom;
import com.pinde.sci.model.osca.OscaFromCfgExt;
import com.pinde.sci.model.osca.OscaFromCfgItemExt;
import com.pinde.sci.model.osca.OscaFromCfgTitleExt;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class OscaFormCfgBizImpl implements IOscaFormCfgBiz{
    @Autowired
    OscaFromMapper fromMapper;
    @Autowired
    OscaFromExtMapper oscaFromExtMapper;

    @Override
    public List<OscaFrom> searchHospitalFrom(OscaFrom from) {
        List<String> orgFlows = new ArrayList<>();
        String currOrgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        orgFlows.add(currOrgFlow);
        orgFlows.add("jsst");
        OscaFromExample example = new OscaFromExample();
        OscaFromExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowIn(orgFlows)
                ;
        if(from!=null){
            if(StringUtil.isNotBlank(from.getFromTypeId())){
                criteria.andFromTypeIdEqualTo(from.getFromTypeId());
            }
            if(StringUtil.isNotBlank(from.getIsReleased())){
                criteria.andIsReleasedEqualTo(from.getIsReleased());
            }
            if(StringUtil.isNotBlank(from.getFromName())){
                criteria.andFromNameLike("%"+from.getFromName()+"%");
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return fromMapper.selectByExample(example);
    }

    @Override
    public List<OscaFrom> searchForm(OscaFrom from) {
        OscaFromExample example = new OscaFromExample();
        OscaFromExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(from!=null){
            if(StringUtil.isNotBlank(from.getFromTypeId())){
                criteria.andFromTypeIdEqualTo(from.getFromTypeId());
            }
            if(StringUtil.isNotBlank(from.getIsReleased())){
                criteria.andIsReleasedEqualTo(from.getIsReleased());
            }
            if(StringUtil.isNotBlank(from.getOrgFlow())){
                criteria.andOrgFlowEqualTo(from.getOrgFlow());
            }
            if(StringUtil.isNotBlank(from.getFromName())){
                criteria.andFromNameLike("%"+from.getFromName()+"%");
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return fromMapper.selectByExample(example);
    }

    @Override
    public int editForm(OscaFrom from) {
        if (from != null) {
            String fromFlow = from.getFromFlow();
            if(StringUtil.isNotBlank(fromFlow)){
                GeneralMethod.setRecordInfo(from,false);
                return fromMapper.updateByPrimaryKeySelective(from);
            }else{
                from.setFromFlow(PkUtil.getGUID());
                GeneralMethod.setRecordInfo(from,true);
                return fromMapper.insertSelective(from);
            }
        }
        return 0;
    }

    @Override
    public OscaFrom readFrom(String fromFlow) {
        if(StringUtil.isNotBlank(fromFlow)){
            return fromMapper.selectByPrimaryKey(fromFlow);
        }
        return null;
    }

    @Override
    public int deleteTitle(String fromFlow, String id) throws Exception {
        if(StringUtil.isNotBlank(fromFlow) && StringUtil.isNotBlank(id)){
            OscaFrom existFrom = readFrom(fromFlow);
            if(existFrom != null){
                Document dom = DocumentHelper.parseText(existFrom.getRromContent());
                String titleXpath = "//title[@id='"+id+"']";
                Element titleElement = (Element) dom.selectSingleNode(titleXpath);
                titleElement.getParent().remove(titleElement);
                setFromScore(existFrom,dom);
                existFrom.setRromContent(dom.asXML());
                return editForm(existFrom);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public int editFromCfgTitle(OscaFrom from, OscaFromCfgTitleExt fromCfgTitleExt) throws Exception {
            Document dom = null;
            Element root = null;
            String titleId = StringUtil.defaultIfEmpty(fromCfgTitleExt.getId(),"");
            String titleName = StringUtil.defaultIfEmpty(fromCfgTitleExt.getName(),"");
            String titleTypeName = StringUtil.defaultIfEmpty(fromCfgTitleExt.getTypeName(),"");
            String orderNum = StringUtil.defaultIfEmpty(fromCfgTitleExt.getOrderNum(),"");

        OscaFrom existFrom = readFrom(from.getFromFlow());
            if(StringUtil.isBlank(existFrom.getRromContent())){

                //第一次新增XML
                dom = DocumentHelper.createDocument();
                root = dom.addElement("fromCfg");
                Element titleElement = root.addElement("title").addAttribute("id", PkUtil.getUUID());
                titleElement.addAttribute("name",titleName);
                titleElement.addAttribute("typeName",titleTypeName);
                titleElement.addAttribute("orderNum",orderNum);

                setFromScore(existFrom,dom);
                existFrom.setRromContent(dom.asXML());

                return editForm(existFrom);
            }else{
                dom = DocumentHelper.parseText(existFrom.getRromContent());
                root = dom.getRootElement();

                if(StringUtil.isBlank(titleId)){//新增title节点
                    Element titleElement = root.addElement("title");
                    titleElement.addAttribute("id", PkUtil.getUUID());
                    titleElement.addAttribute("name", titleName);
                    titleElement.addAttribute("typeName",titleTypeName);
                    titleElement.addAttribute("orderNum",orderNum);
                }else{
                    String titleXpath = "//title[@id='"+titleId+"']";
                    Element titleElement = (Element) dom.selectSingleNode(titleXpath);
                    titleElement.addAttribute("typeName",titleTypeName);
                    titleElement.addAttribute("orderNum",orderNum);
                    String oldName = titleElement.attributeValue("name");
                    List<Element> nameElements = dom.selectNodes("//title[@name='"+oldName+"']");
                    if(nameElements!=null&&nameElements.size()>0){
                        for(Element nameElement:nameElements){
                            nameElement.attribute("name").setValue(titleName);
                        }
                    }
                }
                setFromScore(existFrom,dom);
                existFrom.setRromContent(dom.asXML());
                return editForm(existFrom);
            }
    }

    @Override
    public int saveFromItemList(OscaFromCfgExt form) throws Exception {
        String fromFlow = form.getFromFlow();
        List<OscaFromCfgItemExt> itemFormList = form.getItemFormList();
        if(itemFormList != null && !itemFormList.isEmpty()){
            OscaFrom existFrom = readFrom(fromFlow);
            if(existFrom != null){
                Document dom = DocumentHelper.parseText(existFrom.getRromContent());
                for(OscaFromCfgItemExt item : itemFormList){
                    String titleId = item.getTitleId();
                    String name = item.getName();
                    String score = item.getScore();
                    String order = item.getOrder();
                    String titleXpath = "//title[@id='"+titleId+"']";
                    Element titleElement = (Element) dom.selectSingleNode(titleXpath);
                    Element itemElement = titleElement.addElement("item");
                    itemElement.addAttribute("id", PkUtil.getUUID());
                    itemElement.addElement("name").setText(name);
                    itemElement.addElement("score").setText(score);
                    itemElement.addElement("order").setText(order);
                }
                setFromScore(existFrom,dom);
                existFrom.setRromContent(dom.asXML());
                return editForm(existFrom);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    private void setFromScore(OscaFrom existFrom, Document dom) {
        List<Element> elements  = dom.selectNodes("//score");
        double sum = 0;
        if(elements !=null&&elements .size()>0){
            for(Element element:elements ){
                String s = element.getText();
                double s2 = Double.parseDouble(s);
                sum+=s2;
            }
        }
        existFrom.setFromScore(String.valueOf(sum));
    }

    @Override
    public int modifyItem(String cfgFlow, OscaFromCfgItemExt itemForm) throws Exception {
        String id = itemForm.getId();
        if(StringUtil.isNotBlank(cfgFlow) && StringUtil.isNotBlank(id)){
            OscaFrom existFrom = readFrom(cfgFlow);
            if(existFrom != null){
                Document dom = DocumentHelper.parseText(existFrom.getRromContent());
                String name = itemForm.getName();
                name = StringUtil.defaultString(name);
                String score = itemForm.getScore();
                score = StringUtil.defaultString(score);
                String order = itemForm.getOrder();
                order = StringUtil.defaultString(order);
                String itemXpath = "//item[@id='"+id+"']";
                Node itemNode = dom.selectSingleNode(itemXpath);
                Node nameNode = itemNode.selectSingleNode("name");
                nameNode.setText(name);
                Node scoreNode = itemNode.selectSingleNode("score");
                scoreNode.setText(score);
                Node orderNode = itemNode.selectSingleNode("order");
                if(orderNode!=null){
                    orderNode.setText(order);
                }
                setFromScore(existFrom,dom);
                existFrom.setRromContent(dom.asXML());
                return editForm(existFrom);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public int deleteItem(String cfgFlow, String id) throws Exception {
        if(StringUtil.isNotBlank(cfgFlow) && StringUtil.isNotBlank(id)){
            OscaFrom existFrom = readFrom(cfgFlow);
            if(existFrom != null){
                Document dom = DocumentHelper.parseText(existFrom.getRromContent());
                String itemXpath = "//item[@id='"+id+"']";
                Element itemElement = (Element) dom.selectSingleNode(itemXpath);
                itemElement.getParent().remove(itemElement);
                setFromScore(existFrom,dom);
                existFrom.setRromContent(dom.asXML());
                return editForm(existFrom);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public String checkFromName(String fromName) {
        if (StringUtil.isNotBlank(fromName)){
            OscaFromExample example = new OscaFromExample();
            example.createCriteria().andFromNameEqualTo(fromName).andRecordStatusEqualTo("Y");
            List<OscaFrom> list = fromMapper.selectByExample(example);
            if (list==null || list.size()==0){
                return GlobalConstant.FLAG_Y;
            }
        }
        return GlobalConstant.FLAG_N;
    }

    @Override
    public List<OscaSubjectStationFrom> selectSubjectStationFromByFlow(String flow) {
        return oscaFromExtMapper.selectSubjectStationFromByFlow(flow);
    }
}
