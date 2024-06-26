package com.pinde.sci.biz.srm;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
public interface IProjAddBiz {

    public int addProjForm(List<MultipartFile> fileList,String projFlow, String recTypeId);
}
