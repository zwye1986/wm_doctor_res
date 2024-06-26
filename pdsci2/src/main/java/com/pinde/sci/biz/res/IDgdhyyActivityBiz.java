package com.pinde.sci.biz.res;




import com.pinde.sci.model.mo.DgdhyyActivity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016-11-02.
 */
public interface IDgdhyyActivityBiz {
    //编辑活动
    int editDgdhyyActivity(DgdhyyActivity dgdhyyActivity);
    //查询活动
    List<DgdhyyActivity> searchDgdhyyActivity(DgdhyyActivity dgdhyyActivity);
    //活动导入
    int importDgdhyyActivity(MultipartFile file);
}
