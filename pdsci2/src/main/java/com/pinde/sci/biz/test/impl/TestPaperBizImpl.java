package com.pinde.sci.biz.test.impl;

import com.pinde.sci.biz.test.ITestPaperBiz;
import com.pinde.sci.dao.base.TestPaperMapper;
import com.pinde.sci.dao.test.TestPaperExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TestPaperBizImpl implements ITestPaperBiz{
    
	@Autowired
	private TestPaperMapper testPaperMapper;
	@Autowired
	private TestPaperExtMapper testPaperExtMapper;

//	@Override
//	public List<TestPaper> searchTestPaperList(TestPaper testPaper) {
//		TestPaperExample example=new TestPaperExample();
//		Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		if(testPaper!=null){
//			if(StringUtil.isNotBlank(testPaper.getPaperUserFlow())){
//				criteria.andPaperUserFlowEqualTo(testPaper.getPaperUserFlow());
//			}
//		}
//		return testPaperMapper.selectByExample(example);
//	}

//	@Override
//	public TestPaper readTestPaper(String paperFlow){
//		return this.testPaperMapper.selectByPrimaryKey(paperFlow);
//	}

//	@Override
//	public List<TestPaper> searchTestPaperList(String courseFlow,String chapterFlow,String userFlow){
//		Map<String, Object> paramMap=new HashMap<String, Object>();
//	    if(StringUtil.isNotBlank(courseFlow)){
//	    	paramMap.put("courseFlow", courseFlow);
//	    }
//	    if(StringUtil.isNotBlank(userFlow)){
//	    	paramMap.put("userFlow", userFlow);
//	    }
//	    if(StringUtil.isNotBlank(chapterFlow)){
//	    	paramMap.put("chapterFlow", chapterFlow);
//	    }
//	    return testPaperExtMapper.searchTestPapers(paramMap);
//	}


	
}
