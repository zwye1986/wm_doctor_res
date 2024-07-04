package com.pinde.res.biz.mbgl.impl;


import com.alibaba.fastjson.JSON;
import com.pinde.res.biz.mbgl.IMbglBiz;
import com.pinde.res.dao.mbgl.ext.MbglTyhExtMapper;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

@Service
@Transactional(rollbackFor=Exception.class)
public class MbglBizImpl implements IMbglBiz {

	@Resource
	private MbglTyhExtMapper mbglTyhExtMapper;
	@Override
	public Map<String, Object> findByUserCode(String userCode) {
		return mbglTyhExtMapper.selectByCode(userCode);
	}

	@Override
	public List<Map<String, Object>> readOtherUsers(String userCode) {
		return mbglTyhExtMapper.readOtherUsers(userCode);
	}
}
