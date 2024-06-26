package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ZseyDevices;
import com.pinde.sci.model.mo.ZseyDevicesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZseyDevicesMapper {
    int countByExample(ZseyDevicesExample example);

    int deleteByExample(ZseyDevicesExample example);

    int deleteByPrimaryKey(String deviceFlow);

    int insert(ZseyDevices record);

    int insertSelective(ZseyDevices record);

    List<ZseyDevices> selectByExample(ZseyDevicesExample example);

    ZseyDevices selectByPrimaryKey(String deviceFlow);

    int updateByExampleSelective(@Param("record") ZseyDevices record, @Param("example") ZseyDevicesExample example);

    int updateByExample(@Param("record") ZseyDevices record, @Param("example") ZseyDevicesExample example);

    int updateByPrimaryKeySelective(ZseyDevices record);

    int updateByPrimaryKey(ZseyDevices record);
}