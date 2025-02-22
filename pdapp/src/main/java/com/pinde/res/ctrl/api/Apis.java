package com.pinde.res.ctrl.api;

import com.pinde.core.common.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class Apis {
    @Resource
    private Environment env;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = {"/wx/appid"},method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,String> getWxAppId(){
        Map<String,String> map = new HashMap<>();

        String wxAppid = stringRedisTemplate.opsForValue().get(GlobalConstant.WX_APPID);
        if(StringUtils.isEmpty(wxAppid)){
            wxAppid = env.getProperty("appId");
            if(StringUtils.isNotBlank(wxAppid)){
                stringRedisTemplate.opsForValue().set(GlobalConstant.WX_APPID,wxAppid);
                map.put("data",wxAppid);
                map.put("code", "200");
                map.put("msg","get appid successfully");
            }else{
                map.put("data",null);
                map.put("code", "500");
                map.put("msg","get appid is null");
            }
        } else{
            map.put("data",wxAppid);
            map.put("code", "200");
            map.put("msg","get appid successfully");
        }
        return map;
    }
}
