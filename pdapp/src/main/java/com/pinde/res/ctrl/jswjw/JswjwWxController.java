package com.pinde.res.ctrl.jswjw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.pinde.app.common.GeneralController;
import com.pinde.app.common.InitConfig;
import com.pinde.core.common.form.UserResumeExtInfoForm;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.*;
import com.pinde.core.common.enums.osca.AuditStatusEnum;
import com.pinde.core.common.enums.osca.DoctorScoreEnum;
import com.pinde.core.common.enums.osca.ScanDocStatusEnum;
import com.pinde.core.common.enums.osca.SignStatusEnum;
import com.pinde.core.common.sci.dao.VerificationCodeRecordMapper;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.pdf.utils.ObjectUtils;
import com.pinde.core.util.*;
import com.pinde.res.biz.common.impl.DictBizImpl;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.hbres.IResInprocessInfoBiz;
import com.pinde.res.biz.jswjw.*;
import com.pinde.res.biz.osca.IOscaAppBiz;
import com.pinde.res.biz.stdp.*;
import com.pinde.res.dao.jswjw.ext.JsResUserBalckListExtMapper;
import com.pinde.res.dao.jswjw.ext.TempMapper;
import com.pinde.core.common.sci.dao.ResOrgCkxzMapper;
import com.pinde.core.common.sci.dao.SysCfgMapper;
import com.pinde.core.common.sci.dao.SysDictMapper;
import com.pinde.sci.util.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.bouncycastle.util.encoders.Hex;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/res/jswjw/wx")
public class JswjwWxController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(JswjwWxController.class);

    private static String regex = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_.!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\\W_.!@#$%^&*`~()-+=]+$)(?![0-9\\W_.!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\\W_.!@#$%^&*`~()-+=]{8,20}$";

//    private static String appId = "wx85a06561c53844ee";
//    private static String secret = "9b8decc6ab8cda83f56b915803e7ec9b";

    @Autowired
    private IResLiveTrainingBiz resLiveTrainingBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private IJswjwBiz jswjwBiz;
    @Autowired
    private IResOrgTimeBiz timeBiz;
    @Autowired
    private IResGradeBiz gradeBiz;
    @Autowired
    private IJswjwStudentBiz jswjwStudentBiz;
    @Autowired
    private IOscaAppBiz oscaAppBiz;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private IInxInfoBiz inxInfoBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ISchExamScoreQueryBiz scoreQueryBiz;
    @Autowired
    private ISchExamCfgBiz examCfgBiz;

    @Autowired
    private JsResUserBalckListExtMapper balcklistExtMapper;

    @Autowired
    private IJswjwTeacherBiz jswjwTeacherBiz;
    @Autowired
    private ISchAndStandardDeptCfgBiz deptCfgBiz;
    @Autowired
    private ResPaperBiz paperBiz;
    @Autowired
    private IResInprocessInfoBiz resInprocessInfoBiz;
    @Autowired
    private IFileBiz pubFileBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private IResActivityBiz activityBiz;
    @Autowired
    private IResActivityTargetBiz activityTargeBiz;
    @Autowired
    private IExamResultsBiz examResultsBiz;
    @Autowired
    private SysCfgMapper cfgMapper;
    @Autowired
    private SysDictMapper dictMapper;
    @Autowired
    private DictBizImpl dictBizImpl;
    @Autowired
    private TempMapper tempMapper;
    @Autowired
    private VerificationCodeRecordMapper verificationCodeRecordMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ResOrgCkxzMapper resOrgCkxzMapper;

    @Autowired
    IAccessTokenService accessTokenService;

    public boolean isChargeOrg(String userFlow) {
        if (StringUtil.isNotBlank(userFlow)) {
            return com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jswjwBiz.getJsResCfgAppMenu(userFlow));
        } else {
            return false;
        }
    }

    /**
     * @param session
     * @Department：研发部
     * @Description 进行网页授权，便于获取到用户的绑定的内容
     * @Date 2022/11/23
     */
    @RequestMapping("/getOpenId")
    @ResponseBody
    public Object getOpenId(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Origin", "*");
        /**
         * 进行获取openId，必须的一个参数，这个是当进行了授权页面的时候，再重定向了我们自己的一个页面的时候，
         * 会在request页面中，新增这个字段信息，要结合这个ProjectConst.Get_WEIXINPAGE_Code这个常量思考
         */
        String appid = PropertiesUtils.getValue("appId");
        resultMap.put("appid", appid);
        String secret = PropertiesUtils.getValue("appSecret");
        resultMap.put("secret", secret);
        String wxcode = request.getParameter("wxcode");
        logger.info("=========获取wxcode信息: {}", JSON.toJSONString(wxcode));
        if (StringUtil.isBlank(wxcode)) {
            logger.error("=========获取wxcode失败，失败信息: {}", JSON.toJSONString(wxcode));
            return ResultDataThrow("获取wxcode失败");
        }
        resultMap.put("wxcode", wxcode);
        Map<String, String> authInfo = WechatUtil.getOauthUser(appid, secret, wxcode);
        String openId = authInfo.get("openid");
        resultMap.put("openId", openId);
        logger.info("=========获取openId信息: {}", JSON.toJSONString(openId));
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String access_token = valueOps.get("access_token");
        if(StringUtil.isBlank(access_token)){// 获取基础刷新的接口访问凭证
            try {
                access_token = accessTokenService.getAccessToken(appid, secret);
            } catch (Exception e) {
                logger.error("获取access_token失败",e);
                return ResultDataThrow("获取access_token失败");
            }
        }
        resultMap.put("access_token", access_token);
        //判断是否绑定账号
        SysUser user = jswjwBiz.readSysUserByOpenId(openId);
        if (null == user) {
            resultMap.put("resultId", "1001");
            resultMap.put("resultType", "未绑定微信");
            resultMap.put("access_token", "");
            return resultMap;
        }
        if(user.getUserPhone()==null||"".equals(user.getUserPhone())){
            resultMap.put("resultId", "1005");
            resultMap.put("resultType", "未绑定手机号，用户名：" + user.getUserCode());
            resultMap.put("access_token", "");
            return resultMap;
        }

        return getUserInfo(user, resultMap, null, null, openId,request);
    }

    /**
     * 发送验证码
     */
    @RequestMapping(value = {"/sendPhoneCode"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object sendPhoneCode(String userPhone) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isBlank(userPhone)) {
            return ResultDataThrow("手机号为空");
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            return ResultDataThrow(phoneRegex(userPhone).get(1));
        }
//        List<SysUser> sysUsers = jswjwBiz.selectByUserPhoneAndIsVerify(userPhone);
//        if (sysUsers != null && sysUsers.size() > 0) {
        SMSUtil smsUtil = new SMSUtil(userPhone);
        int code = (int) ((Math.random() * 9 + 1) * 1000);
        SysSmsLog sSmsRecord = smsUtil.send("10001", "516946", "R101", code);
        if (!"100".equals(sSmsRecord.getStatusCode())) {
            return ResultDataThrow(sSmsRecord.getStatusName());
        }
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "发送成功");
        resultMap.put("phoneCode", code);
        return resultMap;
//        }
//        return ResultDataThrow("用户不存在");
    }

    /**
     * @Department：研发部
     * @Description 绑定账号
     * @Date 2022/11/23
     */
    @RequestMapping("/bindSysUser")
    @ResponseBody
    public Object bindSysUser(String openId, String userPhone,HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "绑定成功");
        //根据手机号查询账户
        SysUser oldUser = jswjwBiz.getUserByUserPhone(userPhone);
        if(null == oldUser){
            //根据openId查询用户
            SysUser user = jswjwBiz.readSysUserByOpenId(openId);


            if(user != null){

                //用来更新的用户
                SysUser updateUser = new SysUser();

                updateUser.setUserFlow(user.getUserFlow());
                //设置用户手机号
                updateUser.setUserPhone(userPhone);

                jswjwBiz.updateUser(updateUser);

                resultMap.put("openId", openId);
                resultMap.put("userFlow", user.getUserFlow());

                return getUserInfo(user, resultMap, null, null, openId, request);

            }else {

                resultMap.put("resultId", "1004");
                resultMap.put("resultType", "用户未绑定该公众号，请联系管理员");

                return resultMap;

            }

        }else {

            resultMap.put("resultId", "1003");
            resultMap.put("resultType", "该手机号已绑定用户"+oldUser.getUserCode());

            return resultMap;

        }

    }

    /**
     * @Department：研发部
     * @Description 绑定账号
     * @Date 2022/11/23
     */
    @RequestMapping("/changePhone")
    @ResponseBody
    public Object changePhone(String openId, String userPhone,HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "绑定成功");
        //根据手机号查询账户
        SysUser oldUser = jswjwBiz.getUserByUserPhone(userPhone);
        if(oldUser != null){
            oldUser.setUserPhone("");
            jswjwBiz.updateUser(oldUser);
        }
        //根据openId查询用户
        SysUser user = jswjwBiz.readSysUserByOpenId(openId);
        if(user == null){
            resultMap.put("resultId", "1004");
            resultMap.put("resultType", "用户未绑定该公众号，请联系管理员");
            return resultMap;
        }
        //设置用户手机号
        user.setUserPhone(userPhone);
        jswjwBiz.updateUser(user);

        resultMap.put("openId", openId);
        resultMap.put("userFlow", user.getUserFlow());
        return getUserInfo(user, resultMap, null, null, openId,request);
    }

//
//    /**
//     * @Department：研发部
//     * @Description 绑定账号成功回调登录
//     * @Date 2022/11/23
//     */
//    @RequestMapping("/loginByOpenId")
//    @ResponseBody
//    public Object loginByOpenId(String userFlow, String openId) {
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("resultId", "200");
//        resultMap.put("resultType", "交易成功");
//        if (StringUtil.isBlank(userFlow)) {
//            return ResultDataThrow("学员标识符为空");
//        }
//        if (StringUtil.isBlank(openId)) {
//            return ResultDataThrow("openId为空");
//        }
//        SysUser user = jswjwBiz.readSysUser(userFlow);
//        if (null == user) {
//            return ResultDataThrow("用户不存在");
//        }
//        return getUserInfo(user, resultMap, null, null, openId);
//    }

    /**
     * @Department：研发部
     * @Description 获取调用微信接口参数
     * @Date 2022/11/23
     */
    @RequestMapping(value = {"/wechatScan"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object wechatScan(String pagePath) {
        String appid = PropertiesUtils.getValue("appId");
        logger.debug("=========获取appId成功，appid信息: {}", JSON.toJSONString(appid));
        String secret = PropertiesUtils.getValue("appSecret");
        logger.debug("=========获取appSecret成功，appSecret信息: {}", JSON.toJSONString(secret));
        // 调用生成access_token的方法
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String access_token = valueOps.get("access_token");
        if(StringUtil.isBlank(access_token)){// 获取基础刷新的接口访问凭证
            try {
                access_token = accessTokenService.getAccessToken(appid, secret);
            } catch (Exception e) {
                logger.error("获取access_token失败",e);
                return ResultDataThrow("获取access_token失败");
            }
        }
        logger.info("=========获取access_token成功: {}", JSON.toJSONString(access_token));
//        redissonManager.setString(ACCESS_TOKEN, access_token, 7000, TimeUnit.SECONDS);
//        logger.info("=========access_token保存成功================");

        // 调用生成ticket的方法，获取ticket
        JSONObject ticketJsonObject = WechatUtil.getJsApiTicket(access_token);
        if (ObjectUtils.isEmpty(ticketJsonObject) || !ticketJsonObject.containsKey("ticket")) {
            logger.error("=========获取ticket失败，失败信息: {}", JSON.toJSONString(ticketJsonObject));
//            return R.error(ErrorCodeMsg.SYSTEM_ERROR.getMsg());
            return ResultDataThrow("获取ticket失败");
        }
        String ticket = ticketJsonObject.getString("ticket");
        logger.info("=========获取ticket成功: {}", ticket);
//        redissonManager.setString(TICKET, ticket, 7000, TimeUnit.SECONDS);
//        logger.info("=========ticket保存成功================");

        SortedMap<String, Object> map = new TreeMap<>();
        String noncestr = WechatSignUtils.createNonceStr();
        String timestamp = WechatSignUtils.createTimestamp();
        map.put("appId", appid);
        map.put("access_token", access_token);
        map.put("jsapi_ticket", ticket);
        map.put("noncestr", noncestr);
        map.put("timestamp", timestamp);
        map.put("url", pagePath);
        // 对参数进行加密操作
        String signature = WechatSignUtils.getSignature(ticket, noncestr, timestamp, pagePath);
        map.put("signature", signature);
        return map;
    }

    /**
     * @param lat 纬度
     * @param lng 经度
     * @Department：研发部
     * @Description 根据经纬度获取地址
     * @Date 2022/11/23
     */
    @RequestMapping(value = {"/getAddress"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object getAddress(String lat, String lng) throws IOException {
        logger.debug("纬度：", lat);
        logger.debug("经度：", lng);
        String address = QQMapUtil.getAddress(lat, lng);
        if (StringUtil.isBlank(address)) {
            return ResultDataThrow("获取地址失败");
        }
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        resultMap.put("address", address);
        return resultMap;
    }

    /**
     * @Department：研发部
     * @Description 公众号上传头像
     * @Date 2022/12/01
     */
    @RequestMapping(value = {"/addHeadImage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object addHeadImage(ImageFileForm form, String userFlow) {
//		System.err.println(form);
//		System.err.println(form.getUploadFileData());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(form.getUserFlow())) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser user = jswjwBiz.readSysUser(form.getUserFlow());
        if (null == user) {
            return ResultDataThrow("用户不存在");
        }
        if (StringUtil.isEmpty(form.getFileName())) {
            return ResultDataThrow("文件名为空");
        }
        if (StringUtil.isBlank(form.getImageContent())) {
            return ResultDataThrow("上传文件不能为空");
        }
        String path = jswjwBiz.addHeadImage(form);
        if (null == path) {
            return ResultDataThrow("头像上传失败");
        }
        user.setUserHeadImg(path);
        jswjwBiz.updateUser(user);
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        resultMap.put("userHeadImage", uploadBaseUrl + "/" + path);
        return resultMap;
    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object login(String userCode, String userPasswd, String uuid, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (StringUtil.isEmpty(userCode)) {
            return ResultDataThrow("用户名不能为空");
        }
        if (StringUtil.isEmpty(userPasswd)) {
            return ResultDataThrow("密码为空");
        }
        SysUser userinfo = jswjwBiz.findByUserCode(userCode);
        if (userinfo == null) {
            userinfo = jswjwBiz.findByUserPhone(userCode);
        }
        if (userinfo == null) {
            return ResultDataThrow("用户不存在");
        }

        try {
            userPasswd = DESUtil.decryptWx(userPasswd);
        }catch (Exception e){
            return ResultDataThrow("用户密码错误");
        }

//        ServletContext application = request.getServletContext();
//        if (application.getAttribute("onlineCountNum") == null) {
//            application.setAttribute("onlineCountNum", 1);
//        } else {
//            application.setAttribute("onlineCountNum", (Integer) application.getAttribute("onlineCountNum") + 1);
//        }

        return getUserInfo(userinfo, resultMap, userPasswd, uuid, null,request);
    }

    /**
     * 校验学员数据填写：限制登录
     *
     * @param doctor
     * @return
     */
    private boolean checkDoctorDataFilling(ResDoctor doctor) {
        String lockStatus = doctor.getLockStatus() == null ? "" : doctor.getLockStatus();
        if ("Lock".equals(lockStatus)) {
//            return ResultDataThrow("由于您长时间未填写培训数据，现无法继续使用APP，请联系管理员！");
            return true;
        }
        String loginLimit = jswjwBiz.getCfgCode("student_timeout_login_limit");
        if (StringUtil.isNotBlank(loginLimit) && !com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(loginLimit)) {
            String doctorFlow = doctor.getDoctorFlow();
            // 当前时间
            String currentTime = DateUtil.getCurrDate();
            int continuousMonth = 0;
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(loginLimit);
            if (isNum.matches()) {
                continuousMonth = Integer.parseInt(loginLimit);
            }
            if ("UnLock".equals(lockStatus)) {
                Boolean flag = false;
                if (continuousMonth > 0) {
                    Map<String, Object> queryMap = new HashMap<>();
                    queryMap.put("doctorFlow", doctorFlow);
                    List<DoctorUntiedRecording> RecordingList = jswjwBiz.queryDoctorUnLockDate(queryMap);
                    if (RecordingList != null && RecordingList.size() > 0) {
                        DoctorUntiedRecording doctorUntiedRecording = RecordingList.get(0);
                        String untiedDate = doctorUntiedRecording.getUntiedDate() == null ? "" : doctorUntiedRecording.getUntiedDate();
                        if (StringUtil.isNotBlank(untiedDate)) {
                            // 解锁时间推+后n月份时间
                            String dayTime = DateTimeUtil.getAfterDate(untiedDate, continuousMonth, "month");
                            try {
                                int num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), dayTime);
                                if (num == 1) {
                                    Map<String, Object> paramMap = new HashMap<>();
                                    paramMap.put("startTime", untiedDate.replace("-", "") + "000000");
                                    paramMap.put("endTime", dayTime.replace("-", "") + "235959");
                                    paramMap.put("doctorFlow", doctorFlow);
                                    // 查询学员填写数据记录
                                    int dateCount = jswjwBiz.queryStudentsFilldDta(paramMap);
                                    if (!(dateCount > 0)) {
                                        flag = true;
                                    }
                                }
                            } catch (ParseException e) {
                                logger.error("", e);
                            }
                        } else {
                            flag = true;
                        }

                    } else {
                        flag = true;
                    }
                }

                if (flag) {
                    // 锁定账号
                    doctor.setLockStatus("Lock");
                    int count = doctorBiz.editDoctor(doctor);
                    if (count > 0) {
                        // 锁定记录
                        DoctorUntiedRecording recording = new DoctorUntiedRecording();
                        recording.setLockStatus("Lock");
                        recording.setDoctorFlow(doctorFlow);
                        recording.setLockDate(currentTime);
                        jswjwBiz.updateRecording(recording);
                    }
//                    return ResultDataThrow("由于您长时间未填写培训数据，现无法继续使用APP，请联系管理员！");
                    return true;
                }
            } else {
                if (continuousMonth > 0) {
                    // 新用户 n个月内不校验
                    String createTime = doctor.getCreateTime() == null ? "" : doctor.getCreateTime();
                    if (StringUtil.isNotBlank(createTime) && createTime.length() >= 8) {
                        try {
                            String substring = createTime.substring(0, 4) + "-" + createTime.substring(4, 6) + "-" + createTime.substring(6, 8);
                            String month = DateTimeUtil.getAfterDate(substring, continuousMonth, "month");
                            int month2 = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), month);
                            if (month2 == -1) {
                                return false;
                            }
                        } catch (ParseException e) {
                            logger.error("", e);
                        }

                    }
                    // 获取时间范围
                    String startTime = DateTimeUtil.getAfterDate(currentTime, -continuousMonth, "month");
                    Map<String, Object> paramMap = new HashMap<>();
                    paramMap.put("startTime", startTime.replace("-", "") + "000000");
                    paramMap.put("endTime", currentTime.replace("-", "") + "235959");
                    paramMap.put("doctorFlow", doctorFlow);
                    // 查询学员填写数据记录
                    int dateCount = jswjwBiz.queryStudentsFilldDta(paramMap);

                    if (!(dateCount > 0)) {
                        // 锁定账号
                        doctor.setLockStatus("Lock");
                        int count = doctorBiz.editDoctor(doctor);
                        if (count > 0) {
                            // 锁定记录
                            DoctorUntiedRecording recording = new DoctorUntiedRecording();
                            recording.setLockStatus("Lock");
                            recording.setDoctorFlow(doctorFlow);
                            recording.setLockDate(currentTime);
                            jswjwBiz.updateRecording(recording);
                        }
//                        return ResultDataThrow("由于您长时间未填写培训数据，现无法继续使用APP，请联系管理员！");
                        return true;
                    }
                } else {
                    logger.error("已启用限制登录APP但配置月份信息有误，检查表【SYS_CFG】中 CFG_CODE = 'student_timeout_login_limit_value' 配置月份值是否大于0！");
                }
            }
        }
        return false;
    }

    @RequestMapping(value = {"/rotationList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object rotationList(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空");
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.getRecruitList(userFlow);
        if (null == recruitList || recruitList.size() == 0) {
            return ResultDataThrow("该学员暂未培训信息！");
        }
        resultMap.put("recruitList", recruitList);
        resultMap.put("dataCount", PageHelper.total);
        System.err.println(resultMap);
        return resultMap;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/deptList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object deptList(String userFlow, String rotationFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空！");
        }
        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空！");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空！");
        }

        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        if (doctor == null || StringUtil.isBlank(rotationFlow)) {
            return ResultDataThrow("该学员未设置轮转方案！");
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("doctorFlow", doctor.getDoctorFlow());
        paramMap.put("rotationFlow", rotationFlow);

        String trainingType = doctor.getTrainingTypeId();
        String sessionNumber = doctor.getSessionNumber();
        String trainingYears = doctor.getTrainingYears();
        boolean isReduction = com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType);
        isReduction = isReduction && "2015".compareTo(sessionNumber) <= 0;
        isReduction = isReduction && (TrainYearEnum.OneYear.getId().equals(trainingYears) || TrainYearEnum.TwoYear.getId().equals(trainingYears));
        paramMap.put("isReduction", isReduction ? com.pinde.core.common.GlobalConstant.FLAG_Y : com.pinde.core.common.GlobalConstant.FLAG_N);


        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> deptList = jswjwBiz.searchSchRotationDept(paramMap);

        resultMap.put("rotationDeptMap", deptList);

        //查看轮转计划排班设置是否开启
        JsresPowerCfg cfg = jswjwBiz.readPowerCfg("jsres_"+doctor.getOrgFlow()+"_org_process_scheduling_N");
        if (null != cfg && StringUtil.isNotBlank(cfg.getCfgValue()) && cfg.getCfgValue().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
            //查看轮转计划排班最短时间
            JsresPowerCfg cfgTime = jswjwBiz.readPowerCfg("jsres_"+doctor.getOrgFlow()+"_org_process_scheduling_time");
            if (null!=cfgTime && StringUtil.isNotBlank(cfgTime.getCfgValue())){
                Integer cfgValue = Integer.parseInt(cfgTime.getCfgValue());//轮转计划排班最短时间

                int years=3;
                //查看学员的减免年份
                if (null == trainingYears || StringUtil.isEmpty(trainingYears)){
                    years=3;
                }else if (trainingYears.equals("ThreeYear")){
                    years=3;
                } else if (trainingYears.equals("TwoYear")){
                    years=2;
                } else if (trainingYears.equals("OneYear")){
                    years=1;
                }
                //提醒所有学员（除2020级学员，2021级减免学员，2022级减免2年学员）需要在 2023 年7月1日前设置完最少一年的轮转记录,否则将无法填写轮转数据,添加轮转记录
                boolean flag=true;
                if (Integer.parseInt(doctor.getSessionNumber())<=2020){
                    flag=false;
                }else if (doctor.getSessionNumber().equals("2020")){
                    flag=false;
                }else if (doctor.getSessionNumber().equals("2021") && years >=1){
                    flag=false;
                }else if (doctor.getSessionNumber().equals("2022") && years==2){
                    flag=false;
                }else if (Integer.parseInt(doctor.getSessionNumber())<=2021 && doctor.getTrainingTypeId().equals("AssiGeneral")){
                    flag=false; //21年和21年之前的助理全科都不提示
                }
                //只要有待审核的，就提示，并且不给填写数据
                List<SchArrangeResult> results = jswjwBiz.searchSchArrangeResultPassing(doctor.getDoctorFlow(), rotationFlow);
                if (results!=null && results.size()>0){
                    resultMap.put("edit", com.pinde.core.common.GlobalConstant.FLAG_N);
                    resultMap.put("cfgValue","尚有需要审核的轮转科室数据");
                    flag=false;
                }
                if (flag){
                    //如果学员减免年份小于基地设置的时间，默认轮转最短时间为减免时间
                    if (cfgValue>=years){
                        cfgValue=years;
                    }
                    //需求：入科后，要求学员把至少n年(基地设置)的科室轮转情况填写完成，到时间后，继续要求学员填写下一阶段的轮转情况，包括轮转科室、轮转时间
                    String monthNum = jswjwBiz.searchByDoctorAndRotationFlow(userFlow, rotationFlow);
                    if (null == monthNum || StringUtil.isEmpty(monthNum)){ //没有填写，提示需要填写数据
                        resultMap.put("edit", com.pinde.core.common.GlobalConstant.FLAG_N);
                        resultMap.put("cfgValue","请至少填写未来"+cfgValue+"年的科室轮转情况");
                    }else {
                        double num = Double.parseDouble(monthNum);
                        //基地最短时间只能选择1年、2年、3年
                        //判断学员填写的总月份是否超过基地设置的时间
                        if (num<cfgValue*12){
                            resultMap.put("edit", com.pinde.core.common.GlobalConstant.FLAG_N);
                            resultMap.put("cfgValue","请至少填写未来"+cfgValue+"年的科室轮转情况");
                        }else {
                            //超过了基地设置的时间
                            com.pinde.core.model.ResDoctorRecruit recruit = jswjwBiz.getNewRecruit(userFlow);
                            //获取学员入培时间和现在时间相差的月份
                            long month = DateUtil.signMonthBetweenTwoMonth(recruit.getRecruitDate(), DateUtil.getCurrDate());
                            //如果相差一年，说明就是现在就是第二年（第二次），否则就是第三次
                            if (month>cfgValue*12){
                                if (month>12 && month <24){
                                    if (num<cfgValue*24){   //学员填写的轮转计划总月份小于 基地设置的轮转最短时间
                                        resultMap.put("edit", com.pinde.core.common.GlobalConstant.FLAG_N);
                                        cfgValue=years-1<cfgValue?cfgValue=years-1:cfgValue;
                                        resultMap.put("cfgValue", "请至少填写未来"+cfgValue+"年的科室轮转情况");
                                    }
                                }else {
                                    if (num<cfgValue*36){   //学员填写的轮转计划总月份小于 基地设置的轮转最短时间
                                        resultMap.put("edit", com.pinde.core.common.GlobalConstant.FLAG_N);
                                        cfgValue=years-2<cfgValue?cfgValue=years-2:cfgValue;
                                        resultMap.put("cfgValue", "请至少填写未来"+cfgValue+"年的科室轮转情况");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        String isChargeOrg = jswjwBiz.getJsResCfgAppMenu(doctor.getDoctorFlow());
        boolean f = false;
        if (StringUtil.isNotBlank(isChargeOrg) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isChargeOrg) && "Passed".equals(doctor.getCheckStatusId())) {
            f = true;
        }

        if (f) {
            Map<String, String> progressMap = jswjwBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow());
            resultMap.put("progressMap", progressMap);
        }

        resultMap.put("dataCount", PageHelper.total);
        return resultMap;
    }

    @RequestMapping(value = {"/afterDeptList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object afterDeptList(String userFlow, String deptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空！");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空！");
        }

        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            return ResultDataThrow("请联系管理员维护考试地址！");
        }
        List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, pageIndex, pageSize);
//        resultMap.put("resultList", resultList);
        resultMap.put("dataCount", PageHelper.total);

        Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
        Map<String, ResScore> scoreMap = new HashMap<String, ResScore>();
        Map<String, String> countMap = new HashMap<>();
        List<String> orgFlows = new ArrayList<>();
        for (SchArrangeResult result : resultList) {
            ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
            if (process != null) {
                processMap.put(result.getResultFlow(), process);
                ResScore score = jswjwTeacherBiz.readScoreByProcessFlow(process.getProcessFlow());
                scoreMap.put(result.getResultFlow(), score);
                List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
                if (examResultsList != null)
                    countMap.put(process.getProcessFlow(), examResultsList.size() + "");
            }
            if (!orgFlows.contains(result.getOrgFlow())) {
                String powerCfg = jswjwBiz.getJsResCfgCode("out_test_limit_" + result.getOrgFlow());
                if (powerCfg != null && StringUtil.isNotBlank(powerCfg)) {
                    countMap.put((String) result.getOrgFlow(), powerCfg);
                }
                orgFlows.add((String) result.getOrgFlow());
            }
        }
        String currDate = DateUtil.getCurrDate();
        resultMap.put("currDate", currDate);
//        resultMap.put("processMap", processMap);
//        resultMap.put("scoreMap", scoreMap);

        List<Map<String, Object>> resultMapList = new ArrayList<>();
        if (null != resultList && resultList.size() > 0) {
            for (SchArrangeResult result : resultList) {
                Map<String, Object> map = new HashMap<>();
                map.put("resultFlow", result.getResultFlow());
                map.put("schDeptFlow", result.getSchDeptFlow());
                map.put("schDeptName", result.getSchDeptName());
                map.put("schStartDate", result.getSchStartDate());
                map.put("schEndDate", result.getSchEndDate());
                ResDoctorSchProcess process = processMap.get(result.getResultFlow());
                if (null != process) {
                    map.put("processFlow", process.getProcessFlow());
                    String count = countMap.get(result.getOrgFlow());
                    String count1 = countMap.get(process.getProcessFlow());
                    if (StringUtil.isBlank(count) || (StringUtil.isNotBlank(count) && count1.compareTo(count) < 0)) {
                        map.put("canJoinExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                    } else {
                        map.put("canJoinExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                    }
                } else {
                    map.put("canJoinExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                }
                ResScore score = scoreMap.get(result.getResultFlow());
                if (null != score) {
                    BigDecimal theoryScore = score.getTheoryScore();
                    if (null == theoryScore) {
                        map.put("schScore", "--");
                    } else {
                        map.put("schScore", theoryScore);
                    }
                } else {
                    map.put("schScore", "--");
                }
                resultMapList.add(map);
            }
        }
        resultMap.put("resultMapList", resultMapList);
        return resultMap;
    }


    /**
     * 查看错题
     */
    @RequestMapping(value = {"/viewError"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object viewError(String processFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(processFlow)) {
            return ResultDataThrow("轮转科室标识符为空！");
        }
        List<ExamResults> results = examResultsBiz.getByProcessFlow(processFlow);

        for (ExamResults result : results) {

            if(!result.getExamTime().contains("-")){

                String time = result.getExamTime();

                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; i < time.toCharArray().length; i++) {

                    if(i==4||i==6){
                        stringBuilder.append("-");
                    }

                    if(i==8){
                        stringBuilder.append(" ");
                    }

                    if(i==10||i==12){
                        stringBuilder.append(":");
                    }

                    stringBuilder.append(time.charAt(i));

                }

                result.setExamTime(stringBuilder.toString());

            }

        }

        resultMap.put("results", results);
        return resultMap;
    }

    @RequestMapping(value = {"/viewErrorDetail"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object viewErrorDetail(String processFlow, String resultsId,HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(processFlow)) {
            return ResultDataThrow("轮转科室标识符为空！");
        }

        if (!StringUtil.isNotBlank(resultsId)) {
            return ResultDataThrow("当前考试试卷ID为空！");
        }

        //错题查看地址
        String testUrl = jswjwBiz.getCfgCode("res_after_wrong_exam_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            return ResultDataThrow("请联系管理员维护错题查看地址！");
        }
        ExamResults results = examResultsBiz.getByFlow(resultsId);

        if (results == null) {
            return ResultDataThrow("当前考试记录信息获取失败！");
        }

        testUrl = testUrl + "?paperFlow=" + results.getSoluId() + "&studentFlow=" +results.getUserId()+"&count="+ results.getTestCount()+"&token=" +request.getSession().getId();
        resultMap.put("testUrl", testUrl);
        return resultMap;
    }

    @RequestMapping(value = {"/allAfterDept"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object allAfterDept(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            return ResultDataThrow("请联系管理员维护考试地址");
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        if (null == doctor) {
            return ResultDataThrow("医师信息不能为空");
        }
        List<SchArrangeResult> resultList = jswjwBiz.getSchArrangeResult(userFlow, doctor.getOrgFlow(), pageIndex, pageSize, doctor.getRotationFlow());
//        resultMap.put("resultList", resultList);
        resultMap.put("dataCount", PageHelper.total);
        Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
        Map<String, ResScore> scoreMap = new HashMap<String, ResScore>();

        Map<String, String> countMap = new HashMap<>();
        List<String> orgFlows = new ArrayList<>();
        for (SchArrangeResult result : resultList) {
            ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
            if (process != null) {
                processMap.put(result.getResultFlow(), process);
                ResScore score = jswjwTeacherBiz.readScoreByProcessFlow(process.getProcessFlow());
                scoreMap.put(result.getResultFlow(), score);
                List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
                if (examResultsList != null)
                    countMap.put(process.getProcessFlow(), examResultsList.size() + "");
            }

            if (!orgFlows.contains(result.getOrgFlow())) {
                String powerCfg = jswjwBiz.getJsResCfgCode("out_test_limit_" + result.getOrgFlow());
                if (powerCfg != null && StringUtil.isNotBlank(powerCfg)) {
                    countMap.put((String) result.getOrgFlow(), powerCfg);
                }
                orgFlows.add((String) result.getOrgFlow());
            }
        }
//        resultMap.put("countMap", countMap);
        String currDate = DateUtil.getCurrDate();
        resultMap.put("currDate", currDate);
//        resultMap.put("processMap", processMap);
//        resultMap.put("scoreMap", scoreMap);

        List<Map<String, Object>> resultMapList = new ArrayList<>();
        if (null != resultList && resultList.size() > 0) {
            for (SchArrangeResult result : resultList) {
                Map<String, Object> map = new HashMap<>();
                map.put("resultFlow", result.getResultFlow());
                map.put("schDeptFlow", result.getSchDeptFlow());
                map.put("schDeptName", result.getSchDeptName());
                map.put("schStartDate", result.getSchStartDate());
                map.put("schEndDate", result.getSchEndDate());
                ResDoctorSchProcess process = processMap.get(result.getResultFlow());
                if (null != process) {
                    map.put("processFlow", process.getProcessFlow());
                    String count = countMap.get(result.getOrgFlow());
                    String count1 = countMap.get(process.getProcessFlow());
                    if (StringUtil.isBlank(count) || (StringUtil.isNotBlank(count) && count1.compareTo(count) < 0)) {
                        map.put("canJoinExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                    } else {
                        map.put("canJoinExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                    }
                } else {
                    map.put("canJoinExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                }
                ResScore score = scoreMap.get(result.getResultFlow());
                if (null != score) {
                    BigDecimal theoryScore = score.getTheoryScore();
                    if (null == theoryScore) {
                        map.put("schScore", "--");
                    } else {
                        map.put("schScore", theoryScore);
                    }
                } else {
                    map.put("schScore", "--");
                }
                resultMapList.add(map);
            }
        }
        resultMap.put("resultMapList", resultMapList);
        return resultMap;
    }

    @RequestMapping(value = {"/joinExam"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object joinExam(String userFlow, String schDeptFlow, String resultFlow, String processFlow, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(schDeptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }

        if (StringUtil.isEmpty(resultFlow)) {
            return ResultDataThrow("轮转计划标识符为空");
        }
        if (StringUtil.isEmpty(processFlow)) {
            return ResultDataThrow("请重新维护轮转信息");
        }
        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            return ResultDataThrow("请联系管理员维护考试地址");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);

        SchAndStandardDeptCfg cfg = deptCfgBiz.readBySchDeptFlow(schDeptFlow);
        if (cfg == null) {
            return ResultDataThrow("请基地管理员维护出科考核标准科室");
        }
        ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(resultFlow);
        if (process == null) {
            return ResultDataThrow("当前轮转信息获取失败");
        }
        String TestNum = "";

        //禅道201 修改
        JsresPowerCfg jsresPowerCfg = jswjwBiz.readPowerCfg("out_test_check_" + process.getOrgFlow());
        if (jsresPowerCfg != null && StringUtil.isNotBlank(jsresPowerCfg.getCfgValue()) &&
                com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(jsresPowerCfg.getCfgValue())) {
            //查询科室是否单独配置出科考核参数
            JsresDeptConfig deptConfig = jswjwBiz.searchDeptCfg(process.getOrgFlow(), process.getSchDeptFlow());
            if (null != deptConfig) {
                if (StringUtil.isNotBlank(deptConfig.getTestNum())) {
                    TestNum = deptConfig.getTestNum();
                    int c = 0;
                    List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
                    if (examResultsList != null)
                        c = examResultsList.size();
                    if (c >= Integer.valueOf(TestNum)) {
                        return ResultDataThrow("当前考试次数已经达到" + deptConfig.getTestNum() + ",无法再次考试!");
                    }
                }
                //是否允许学员轮转时间结束后考试
                if (StringUtil.isNotBlank(deptConfig.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(deptConfig.getIsTestOut())) {
                    //不允许  则判断时间是否在轮转时间内
                    String currDate = DateUtil.getCurrDate();
                    String endDate = process.getSchEndDate();
                    try {
                        int num = this.compareDate(endDate, currDate);
                        if (num > 0) {
                            return ResultDataThrow("轮转时间结束,无法参加考试!");
                        }
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                } else if (StringUtil.isNotBlank(deptConfig.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(deptConfig.getIsTestOut())) {
                    String currDate = DateUtil.getCurrDate();
                    String endDate = process.getSchEndDate();
                    if (StringUtil.isNotBlank(deptConfig.getTestOutTime())) {
                        String testOutDate = deptConfig.getTestOutTime();
                        String newDate = DateUtil.addDate(endDate, Integer.parseInt(testOutDate));
                        try {
                            int num = this.compareDate(newDate, currDate);
                            if (num > 0) {
                                return ResultDataThrow("超出轮转时间结束后" + testOutDate + "天,无法参加考试!");
                            }
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                    }
                }
            } else {
                //未单独配置  取全局配置
                JsresDeptConfig config = jswjwBiz.searchBaseDeptConfig(process.getOrgFlow());
                if (null != config) {
                    if (StringUtil.isNotBlank(config.getTestNum())) {
                        TestNum = config.getTestNum();
                        int c = 0;
                        List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
                        if (examResultsList != null)
                            c = examResultsList.size();
                        if (c >= Integer.valueOf(TestNum)) {
                            return ResultDataThrow("当前考试次数已经达到" + config.getTestNum() + ",无法再次考试!");
                        }
                    }
                    //是否允许学员轮转时间结束后考试
                    if (StringUtil.isNotBlank(config.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(config.getIsTestOut())) {
                        //不允许  则判断时间是否在轮转时间内
                        String currDate = DateUtil.getCurrDate();
                        String endDate = process.getSchEndDate();
                        try {
                            int num = this.compareDate(endDate, currDate);
                            if (num > 0) {
                                return ResultDataThrow("轮转时间结束,无法参加考试!");
                            }
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                    } else if (StringUtil.isNotBlank(config.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(config.getIsTestOut())) {
                        String currDate = DateUtil.getCurrDate();
                        String endDate = process.getSchEndDate();
                        if (StringUtil.isNotBlank(config.getTestOutTime())) {
                            String testOutDate = config.getTestOutTime();
                            String newDate = DateUtil.addDate(endDate, Integer.parseInt(testOutDate));
                            try {
                                int num = this.compareDate(newDate, currDate);
                                if (num > 0) {
                                    return ResultDataThrow("超出轮转时间结束后" + testOutDate + "天,无法参加考试!");
                                }
                            } catch (Exception e) {
                                logger.error("", e);
                            }
                        }
                    }
                }
            }
        }
        //试卷id
        String ExamSoluID = "0";
        //时间戳
        String Date = "0";
        //标准科室
        String standardDeptId = cfg.getStandardDeptId();
        ResPaper paper = paperBiz.getPaperByOrgStandardDeptId(process.getOrgName(), standardDeptId);
        if (paper == null) {
            paper = paperBiz.getPaperByStandardDeptId(standardDeptId);
        }
        if (paper != null) {
            ExamSoluID = paper.getPaperFlow();
        }
        if ("0".equals(ExamSoluID)) {
            return ResultDataThrow("该科室暂无试卷信息!");
        }
        SchArrangeResult result = jswjwTeacherBiz.readSchArrangeResult(resultFlow);
        //创建分数数据
        ResScore score = jswjwTeacherBiz.readScoreByProcessFlow(processFlow);
        if (score == null) {
            score = new ResScore();
            score.setDoctorFlow(userFlow);
            score.setScoreTypeId(com.pinde.core.common.enums.ResScoreTypeEnum.DeptScore.getId());
            score.setScoreTypeName(com.pinde.core.common.enums.ResScoreTypeEnum.DeptScore.getName());
            score.setResultFlow(resultFlow);
            score.setProcessFlow(processFlow);
            score.setSchDeptFlow(result.getSchDeptFlow());
            score.setSchDeptName(result.getSchDeptName());
        }

        score.setPaperFlow(ExamSoluID);

        int saveResult = jswjwBiz.saveScore(score, user);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE >= saveResult) {
            return ResultDataThrow("分数信息创建出错!");
        }
        testUrl = testUrl + "?Action=ChuKeMobileExam&paperFlow=" + ExamSoluID + "&CardID=" + URLEncoder.encode(user.getUserCode(), "utf-8") + "&ProcessFlow=" + processFlow + "&count=" + TestNum + "&Date=" + Date + "&userFlow=" + userFlow +"&isStartExam=Y&paperType=7"+ "&token=" +request.getSession().getId();
        resultMap.put("testUrl", testUrl);
        return resultMap;
    }

    private int compareDate(String Date, String sysDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = sdf.parse(Date);
        Date date2 = sdf.parse(sysDate);
        //获取毫秒
        long time = date1.getTime();
        long sysTime = date2.getTime();
        //小于系统时间
        if (time < sysTime) {
            return 1;
        } else {
            return -1;
        }
    }

    @RequestMapping(value = {"/lectureExam"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object lectureExam(String userFlow) throws UnsupportedEncodingException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            return ResultDataThrow("请联系管理员维护考试地址!");
        }
        //是否开启考试
        String lectureTest = jswjwBiz.getCfgCode("res_lecture_test");
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(lectureTest)) {
            return ResultDataThrow("暂未开启考试!");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        //是否开启考试
        String lectureTestNo = jswjwBiz.getCfgCode("res_lecture_test_no");
        if (StringUtil.isBlank(lectureTestNo)) {
            return ResultDataThrow("请联系管理员先配置考试试题!");
        }
        String lectureTestNum = jswjwBiz.getCfgCode("res_lecture_test_num");
        if (StringUtil.isBlank(lectureTestNum)) {
            return ResultDataThrow("请联系管理员先配置考试次数!");
        }
        //试卷id
        String ExamSoluID = lectureTestNo;
        //时间戳
        String Date = "0";
        //考试类型
        String UnifyExamTimeID = "3";
        //创建分数数据
        ResScore score = new ResScore();
        score = new ResScore();
        score.setDoctorFlow(userFlow);
        score.setScoreTypeName("天津讲座考试");
        score.setPaperFlow(ExamSoluID);
        int saveResult = jswjwBiz.saveScore(score, user);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE >= saveResult) {
            return ResultDataThrow("分数信息创建出错!");
        }
        testUrl = testUrl + "?Action=ChuKeMobileExam&ExamSoluID=" + ExamSoluID + "&CardID=" + URLEncoder.encode(user.getUserCode(), "utf-8") + "&ProcessFlow=1&TestNum=" + lectureTestNum + "&Date=" + Date;
        resultMap.put("testUrl", testUrl);
        return resultMap;
    }

    @RequestMapping(value = {"/toGraduationTest"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object toGraduationTest(String userFlow, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (user == null) {
            return ResultDataThrow("用户不存在!");
        }
        //权限
        String cfgv = jswjwBiz.getJsResCfgCodeNew("jsres_doctor_graduation_exam_" + userFlow);
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfgv)) {
            return ResultDataThrow("无权限参加考试!");
        }
        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            return ResultDataThrow("请联系管理员维护考试地址!");
        }
        String userCode = user.getUserCode();
        //用户的医师信息
        ResDoctor doctor = doctorBiz.readDoctor(userFlow);
        if (doctor == null) {
            return ResultDataThrow("无医师信息，无法参加考试!");
        }
        if (StringUtil.isBlank(doctor.getTrainingSpeId())) {
            return ResultDataThrow("医师无培训专业信息，无法参加考试!");
        }
        //获取考试参数
        //考试结果记录流水号
        String recordFlow = PkUtil.getUUID();

        //试卷id
        String ExamSoluID = "0";
        //试卷类型
        String CardType = "0";
        //时间戳
        String Date = "0";

        if (doctor != null) {
            //专业
            String speId = doctor.getTrainingSpeId();
            ResPaper paper = paperBiz.getPaperBySpeId(speId);

            if (paper != null) {
                ExamSoluID = paper.getPaperFlow();
            }
            if ("0".equals(ExamSoluID)) {
                return ResultDataThrow("该专业下暂无试卷信息!");
            }

            TestPaper standardPaper = paperBiz.readTestPaper(ExamSoluID);
            if (standardPaper != null) {
                CardType = standardPaper.getPaperTypeId();
            }
        }//创建考核结果
        ResDoctorGraduationExam result = new ResDoctorGraduationExam();
        result.setExamFlow(recordFlow);
        result.setDoctorFlow(userFlow);
        result.setExamYear(DateUtil.getYear());
        int saveResult = examCfgBiz.saveGraduationExam(result);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE >= saveResult) {
            return ResultDataThrow("考核结果信息创建出错!");
        }
        testUrl = testUrl + "?Action=ChuKeMobileExam&ExamSoluID=" + ExamSoluID + "&CardID=" + URLEncoder.encode(user.getUserCode(), "utf-8") + "&ProcessFlow=" + recordFlow + "&TestNum=" + "&Date=" + Date;
        resultMap.put("testUrl", testUrl);
        return resultMap;
    }


    @RequestMapping(value = {"/globalProgress"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object globalProgress(String userFlow, String deptFlow, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空!");
        }

        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        String isChargeOrg = jswjwBiz.getJsResCfgAppMenu(doctor.getDoctorFlow());
        String orgFlow = "";
        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            orgFlow = doctor.getSecondOrgFlow();
        } else {
            orgFlow = doctor.getOrgFlow();
        }
        String addActivity = jswjwBiz.getJsResCfgCode("jsres_" + orgFlow + "_org_stu_add_activity");
        boolean f = false;
        if (StringUtil.isNotBlank(isChargeOrg) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isChargeOrg)) {
            f = true;
        }

        List<Map<String, Object>> globalDataList = jswjwBiz.globalProgress(userFlow, deptFlow, f);
        resultMap.put("globalDataList", globalDataList);
        resultMap.put("addActivity", addActivity);
        String deptName = jswjwBiz.searchRotationDeptName(deptFlow);
        resultMap.put("deptName", deptName);
        return resultMap;
    }

    @RequestMapping(value = {"/inProcessInfoList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object inProcessInfoList(String userFlow, String deptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空!");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, pageIndex, pageSize);
        resultMap.put("resultList", resultList);
        resultMap.put("dataCount", PageHelper.total);
        Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
        Map<String, ResScore> scoreMap = new HashMap<String, ResScore>();
        Map<String, ResInprocessInfo> inprocessInfoMap = new HashMap<String, ResInprocessInfo>();
        for (SchArrangeResult result : resultList) {
            ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
            if (process != null) {
                processMap.put(result.getResultFlow(), process);
                ResScore score = jswjwTeacherBiz.readScoreByProcessFlow(process.getProcessFlow());
                scoreMap.put(result.getResultFlow(), score);
            }
            ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlow(result.getSchDeptFlow());
            inprocessInfoMap.put(result.getResultFlow(), info);
        }
        String currDate = DateUtil.getCurrDate();
        resultMap.put("currDate", currDate);
        resultMap.put("processMap", processMap);
        resultMap.put("inprocessInfoMap", inprocessInfoMap);
        resultMap.put("scoreMap", scoreMap);
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        resultMap.put("uploadBaseUrl", uploadBaseUrl);
        return resultMap;
    }

    @RequestMapping(value = {"/inProcessInfo"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object inProcessInfo(String userFlow, String schDeptFlow, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", ResultEnum.Success.getId());
        resultMap.put("resultType", ResultEnum.Success.getName());
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        if (StringUtil.isEmpty(schDeptFlow)) {
            return ResultDataThrow("科室标识符为空!");
        }
        SysDept dept = jswjwBiz.readSysDept(schDeptFlow);
        if (dept == null) {
            return ResultDataThrow("科室标识符为空!");
        }
        String orgFlow = dept.getOrgFlow();
        ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(schDeptFlow, orgFlow);
        resultMap.put("info", info);
        if (info != null) {
            List<ResInprocessInfoMember> members = resInprocessInfoBiz.readMembersByRecordFlow(info.getRecordFlow());
            resultMap.put("members", members);
            List<PubFile> files = pubFileBiz.searchByProductFlow(info.getRecordFlow());
            resultMap.put("files", files);
            String arrange = info.getTeachingArrangement();
            if (StringUtil.isNotBlank(arrange)) {
                Map<String, Object> arrangeMap = new HashMap<>();
                arrangeMap = paressXml(arrange);
                resultMap.put("arrangeMap", arrangeMap);

                Map<Integer, String> week = new HashMap<>();
                week.put(1, "周一");
                week.put(2, "周二");
                week.put(3, "周三");
                week.put(4, "周四");
                week.put(5, "周五");
                week.put(6, "周六");
                week.put(7, "周日");
                List<Map<String, String>> days = new ArrayList<>();
                for (int i = 1; i <= 7; i++) {
                    String addressV = (String) arrangeMap.get("address" + i);
                    String contentV = (String) arrangeMap.get("content" + i);
                    if (StringUtil.isNotBlank(addressV) || StringUtil.isNotBlank(contentV)) {
                        Map<String, String> m = new HashMap<>();
                        m.put("address", addressV);
                        m.put("content", contentV);
                        m.put("dayName", week.get(i));
                        days.add(m);
                    }
                }
                resultMap.put("days", days);
            }
        } else {
            return ResultDataThrow("科室暂未添加入科教育信息!");
        }
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        resultMap.put("uploadBaseUrl", uploadBaseUrl);

        return resultMap;
    }

    private Map<String, Object> paressXml(String content) {
        Map<String, Object> formDataMap = null;
        if (StringUtil.isNotBlank(content)) {
            formDataMap = new HashMap<String, Object>();
            try {
                Document document = DocumentHelper.parseText(content);
                Element rootElement = document.getRootElement();
                List<Element> elements = rootElement.elements();
                for (Element element : elements) {
                    formDataMap.put(element.getName(), element.getText());
                }
            } catch (DocumentException e) {
                logger.error("", e);
            }
        }
        return formDataMap;
    }

    @RequestMapping(value = {"/subDeptList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object subDeptList(String userFlow, String deptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空!");
        }

        boolean isChargeOrg = isChargeOrg(userFlow);
//		model.addAttribute("isChargeOrg", isChargeOrg);
        //学员app放开选择带教与科主任
        resultMap.put("isChargeOrg", true);
        List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, pageIndex, pageSize);
        resultMap.put("resultList", resultList);
        resultMap.put("dataCount", PageHelper.total);

        SchRotationDept rotationDept = jswjwBiz.readSchRotationDept(deptFlow);
        resultMap.put("schMaxMonth", StringUtil.isBlank(rotationDept.getSchMaxMonth()) ? "0" : rotationDept.getSchMaxMonth());

        if (true) {
//		if(isChargeOrg){
            List<ResDoctorSchProcess> processList = jswjwBiz.searchSchProcess(userFlow, deptFlow);
            Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
            for (ResDoctorSchProcess process : processList) {
                process.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
                //判断是否已经出科 借用recordStatus字段
                ResSchProcessExpress rec = expressBiz.getExpressByRecType(process.getProcessFlow(), "AfterEvaluation");
                if(null != rec){
                    if (StringUtil.isNotBlank(rec.getManagerAuditUserFlow()) && StringUtil.isNotBlank(rec.getHeadAuditStatusId())) {
                        process.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    }
                }
                processMap.put(process.getSchResultFlow(), process);
            }
            resultMap.put("processMap", processMap);

            List<DeptTeacherGradeInfo> gradeList = jswjwBiz.searchNewGrade(userFlow, deptFlow);
            Map<String, String> gradeMap = jswjwBiz.getNewGradeMap(gradeList);
            resultMap.put("gradeMap", gradeMap);

        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        String ckxz = jswjwBiz.getJsResCfgCode("jsres_" + doctor.getOrgFlow() + "_org_ckxz");
        if (null != ckxz && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ckxz)) {
            ResOrgCkxzExample example = new ResOrgCkxzExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                    .andOrgFlowEqualTo(doctor.getOrgFlow())
                    .andSessionGradeEqualTo(doctor.getSessionNumber());
            List<ResOrgCkxz> list = resOrgCkxzMapper.selectByExample(example);
            if (list.size()>0){
                ckxz = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }else{
                ckxz = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
        }
        resultMap.put("ckxz", ckxz);
        return resultMap;
    }

    @RequestMapping(value = {"/gradeDeptList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object gradeDeptList(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        if (null == doctor) {
            return ResultDataThrow("医师信息不能为空!");
        }
        List<SchArrangeResult> resultList = jswjwBiz.getSchArrangeResult(userFlow, doctor.getOrgFlow(), pageIndex, pageSize, doctor.getRotationFlow());
//        resultMap.put("resultList", resultList);
        resultMap.put("dataCount", PageHelper.total);

        List<DeptTeacherGradeInfo> gradeList = jswjwBiz.searchAllGrade(userFlow);
        Map<String, String> gradeMap = jswjwBiz.getNewGradeMap(gradeList);
//        resultMap.put("gradeMap", gradeMap);

        Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
        Map<String, SchRotationDept> deptMap = new HashMap<String, SchRotationDept>();
        for (SchArrangeResult result : resultList) {
            ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
            processMap.put(result.getResultFlow(), process);
            SchRotationDept dept = jswjwBiz.searchGroupFlowAndStandardDeptIdQuery(result.getStandardGroupFlow(), result.getStandardDeptId());
            deptMap.put(result.getResultFlow(), dept);
        }
//        resultMap.put("processMap", processMap);
//        resultMap.put("deptMap", deptMap);
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        if (null != resultList && resultList.size() > 0) {
            for (SchArrangeResult result : resultList) {
                Map<String, Object> map = new HashMap<>();
                map.put("subDeptFlow", result.getResultFlow());
                SchRotationDept rotationDept = deptMap.get(result.getResultFlow());
                map.put("deptFlow", rotationDept.getRecordFlow());
                map.put("subDeptName", result.getSchDeptName());
                map.put("startDate", result.getSchStartDate());
                map.put("endDate", result.getSchEndDate());
                map.put("sysDeptFlow", result.getDeptFlow());
                ResDoctorSchProcess process = processMap.get(result.getResultFlow());
                map.put("teacherFlow", process.getTeacherUserFlow());
                map.put("teacherName", process.getTeacherUserName());
                map.put("headFlow", process.getHeadUserFlow());
                map.put("headName", process.getHeadUserName());
                String teacherKey = process.getProcessFlow() + "_TeacherGrade";
                map.put("teacherScore", StringUtil.isBlank(gradeMap.get(teacherKey)) ? "未评价" : gradeMap.get(teacherKey));
                String headKey = process.getProcessFlow() + "_DeptGrade";
                map.put("deptScore", StringUtil.isBlank(gradeMap.get(headKey)) ? "未评价" : gradeMap.get(headKey));
                map.put("progress", 100);
                resultMapList.add(map);
            }
        }
        resultMap.put("resultMapList", resultMapList);
        return resultMap;
    }

    @RequestMapping(value = {"/subDeptSel"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object subDeptSel(String userFlow, String searchStr, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        String orgFlow = "";
        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            orgFlow = doctor.getSecondOrgFlow();
        } else {
            orgFlow = doctor.getOrgFlow();
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<SysDept> sysDeptList = jswjwBiz.searchSysDeptList(orgFlow, searchStr);

        resultMap.put("sysDeptList", sysDeptList);
        resultMap.put("dataCount", PageHelper.total);

        return resultMap;
    }

    @RequestMapping(value = {"/subDeptTeacherSel"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object subDeptTeacherSel(String userFlow, String searchStr, String sysDeptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        if (StringUtil.isEmpty(sysDeptFlow)) {
            return ResultDataThrow("轮转科室不能为空!");
        }
        //获取当前配置的老师角色
        String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
        PageHelper.startPage(pageIndex, pageSize);
        List<SysUser> teacherList = jswjwBiz.searchTeacherList(sysDeptFlow, teacherRole, searchStr);
        resultMap.put("teacherList", teacherList);
        resultMap.put("dataCount", PageHelper.total);

        return resultMap;
    }

    @RequestMapping(value = {"/subDeptHeadSel"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object subDeptHeadSel(String userFlow, String searchStr, String sysDeptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        //获取当前配置的科主任角色
        String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
        PageHelper.startPage(pageIndex, pageSize);
        List<SysUser> headList = jswjwBiz.searchTeacherList(sysDeptFlow, headRole, searchStr);

        resultMap.put("headList", headList);
        resultMap.put("dataCount", PageHelper.total);

        return resultMap;
    }

    @RequestMapping(value = {"/addSubDept"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object addSubDept(String userFlow, String deptFlow, String sysDeptFlow, String headFlow, String teacherFlow, String subDeptName, String startDate, String endDate, String schMonth) throws ParseException {
        System.out.println("轮转月份schMonth:" + schMonth);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空!");
        }
        if (StringUtil.isEmpty(startDate)) {
            return ResultDataThrow("开始日期为空!");
        }
        if (StringUtil.isEmpty(endDate)) {
            return ResultDataThrow("结束日期为空!");
        }

        if (startDate.compareTo(endDate) > 0) {
            return ResultDataThrow("轮转开始时间必须早于结束时间!");
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        //检测时间是否重叠
        List<SchArrangeResult> results = jswjwBiz.checkResultDate(userFlow, startDate, endDate, null, doctor.getRotationFlow());
        if (results != null && !results.isEmpty()) {
            return ResultDataThrow("轮转时间与其他科室重叠!");
        }

        // 获取当前入科时间填写系统限制 add shengl
        if (CheckRotationTime(userFlow, deptFlow, startDate, endDate, resultMap, "1", "", schMonth)) {
            return ResultDataThrow((String) resultMap.get("msg"));
        }

//        String orgFlow = "";
//
//        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
//            orgFlow = doctor.getSecondOrgFlow();
//        } else {
//            orgFlow = doctor.getOrgFlow();
//        }
//        String ckxz = jswjwBiz.getJsResCfgCode("jsres_" + orgFlow + "_org_ckxz");
//        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ckxz)) {
//            List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResults(userFlow, doctor.getRotationFlow(), startDate);
//            if (resultList != null) {
//                for (SchArrangeResult result : resultList) {
//                    ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
//
//                    ResSchProcessExpress rec = expressBiz.getExpressByRecType(process.getProcessFlow(), "AfterEvaluation");
//                    if (rec == null) {
//                        return ResultDataThrow(process.getSchDeptName() + "[" + process.getSchStartDate() + "~" + process.getSchEndDate() + "]带教还未审核出科考核表");
//                    }
//                    if (StringUtil.isNotBlank(rec.getManagerAuditUserFlow()) && StringUtil.isBlank(rec.getHeadAuditStatusId())) {
//                        return ResultDataThrow(process.getSchDeptName() + "[" + process.getSchStartDate() + "~" + process.getSchEndDate() + "]主任还未审核出科考核表");
//                    }
//                }
//            }
//        }


//		if(isChargeOrg(userFlow)){
        if (true) {
            if (StringUtil.isEmpty(sysDeptFlow)) {
                return ResultDataThrow("请选择轮转科室!");
            }
//            if (StringUtil.isEmpty(headFlow)) {
//                return ResultDataThrow("请选择科主任!");
//            }
//            if (StringUtil.isEmpty(teacherFlow)) {
//                return ResultDataThrow("请选择带教老师!");
//            }
            jswjwBiz.addSubDept(userFlow, deptFlow, sysDeptFlow, headFlow, teacherFlow, startDate, endDate);
        } else {
            if (StringUtil.isEmpty(subDeptName)) {
                return ResultDataThrow("科室名称为空!");
            }
            jswjwBiz.addSubDept(userFlow, deptFlow, subDeptName, startDate, endDate);
        }
        return resultMap;
    }

    /**
     * 填写轮转计划时校验时间是否超过轮转计划规定时间
     *
     * @param userFlow
     * @param deptFlow
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    private boolean CheckRotationTime(String userFlow, String deptFlow, String startDate, String endDate, Map<String, Object> map, String type, String subDeptFlow, String schMonth) throws ParseException {
        String isProcess = jswjwBiz.getCfgCode("jsres_is_process");
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(isProcess)) {
            return false;
        }
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            logger.info("系統設置：科室轮转时间不允許超过规定的月份,校验用户填写时间 Start");
            if (StringUtil.isNotBlank(schMonth)) {
                // 学员填写时间间隔自然月
                int month = DateTimeUtil.getMonth(startDate, endDate);
                // 填写时间间隔天数
                int distanceDay = DateTimeUtil.getDistanceDay(startDate, endDate);
                List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, null, null);
                // 第一次填写，月份按自然月计算
                if (resultList == null || resultList.size() == 0) {
                    // 用户填写时间超过规定范围
                    if ("0.5".equals(schMonth)) {
                        if (distanceDay > 15) {
                            map.put("msg", "当前填写时间超过规定的时间范围");
                            return true;
                        }
                    } else if (month > Double.parseDouble(schMonth)) {
                        map.put("msg", "当前填写时间超过规定的时间范围");
                        return true;
                    }
                }
                // 该轮转已有超过一条记录，月份限制按31天计算
                else {
                    int sumTime = 0;
                    for (SchArrangeResult schArrangeResult : resultList) {
                        String resultFlow = schArrangeResult.getResultFlow();
                        // 已有记录，修改当前唯一一条数据，月份按自然月计算
                        if (resultList.size() == 1 && subDeptFlow.equals(resultFlow)) {
                            if ("0.5".equals(schMonth)) {
                                if (distanceDay > 15) {
                                    map.put("msg", "当前填写时间超过规定的时间范围");
                                    return true;
                                }
                            } else if (month > Double.parseDouble(schMonth)) {
                                map.put("msg", "当前填写时间超过规定的时间范围");
                                return true;
                            } else if (month == Double.parseDouble(schMonth)) {
                                return false;
                            }
                        }
                        String schStartDate = schArrangeResult.getSchStartDate();
                        String schEndDate = schArrangeResult.getSchEndDate();
                        if (StringUtil.isNotBlank(schStartDate) && StringUtil.isNotBlank(schEndDate)) {
                            // 新增或者编辑（编辑排除当前操作记录的起始时间）
                            if (("1".equals(type)) || ("2".equals(type) && !subDeptFlow.equals(resultFlow))) {
                                sumTime += DateTimeUtil.getDistanceDay(schStartDate, schEndDate);
                            }
                        }
                    }
                    // 用户填写时间范围间隔天数 + 已有轮转记录时间总和超过规定的时间范围
                    if ("0.5".equals(schMonth)) {
                        if (distanceDay > 15) {
                            map.put("msg", "当前填写时间超过规定的时间范围");
                            return true;
                        }
                    } else if ((sumTime + distanceDay) > Double.parseDouble(schMonth) * 31) {
                        map.put("msg", "当前填写时间超过规定的时间范围");
                        return true;
                    }
                }

            } else {
                logger.info("CheckRotationTime 未查到轮转月份信息！");
            }

        }
        return false;
    }

    @RequestMapping(value = {"/modSubDept"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object modSubDept(String userFlow, String deptFlow, String subDeptFlow, String subDeptName, String sysDeptFlow, String headFlow, String teacherFlow, String startDate,
                             String endDate, String schMonth) throws ParseException {
        System.out.println("轮转月份schMonth:" + schMonth);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }
        if (StringUtil.isEmpty(startDate)) {
            return ResultDataThrow("开始日期为空");
        }
        if (StringUtil.isEmpty(endDate)) {
            return ResultDataThrow("结束日期为空");
        }

        if (startDate.compareTo(endDate) > 0) {
            return ResultDataThrow("轮转开始时间必须早于结束时间");
        }

        //检测时间是否重叠
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        System.err.println("subDeptFlow=" + subDeptFlow);
        List<SchArrangeResult> results = jswjwBiz.checkResultDate(userFlow, startDate, endDate, subDeptFlow, doctor.getRotationFlow());
        if (results != null && !results.isEmpty()) {
            return ResultDataThrow("轮转时间与其他科室重叠");
        }

        String cksh = jswjwBiz.getJsResCfgCode("jsres_" + doctor.getOrgFlow() + "_org_cksh");
        if (StringUtil.isNotBlank(subDeptFlow)) {
            SchArrangeResult schArrangeResult = jswjwBiz.readSchArrangeResult(subDeptFlow);
            ResDoctorSchProcess process = jswjwBiz.readSchProcessByResultFlow(schArrangeResult.getResultFlow());
            ResSchProcessExpress rec = null;
            if (process != null) {
                rec = expressBiz.getExpressByRecType(process.getProcessFlow(), "AfterEvaluation");
            }
            if (rec != null) {
                if (StringUtil.isNotBlank(cksh) && cksh.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                    if (StringUtil.isNotBlank(rec.getManagerAuditUserFlow()) && StringUtil.isNotBlank(rec.getHeadAuditStatusId()) && rec.getHeadAuditStatusId().equals(RecStatusEnum.HeadAuditY.getId())) {
                        if (!process.getTeacherUserFlow().equals(teacherFlow)) {
                            return ResultDataThrow("您已在该轮转科室完成出科，不可再修改您的带教老师！");
                        }
                    }
                } else {
                    if (StringUtil.isNotBlank(rec.getAuditStatusId()) && rec.getAuditStatusId().equals("TeacherAuditY")) {
                        if (!process.getTeacherUserFlow().equals(teacherFlow)) {
                            return ResultDataThrow("您已在该轮转科室完成出科，不可再修改您的带教老师！");
                        }
                    }
                }
            }
        }

        // 获取当前入科时间填写系统限制 add shengl
        if (CheckRotationTime(userFlow, deptFlow, startDate, endDate, resultMap, "2", subDeptFlow, schMonth)) {
            return ResultDataThrow((String) resultMap.get("msg"));
        }

//		if(isChargeOrg(userFlow)){
        if (true) {
            if (StringUtil.isEmpty(sysDeptFlow)) {
                return ResultDataThrow("请选择轮转科室");
            }
//            if (StringUtil.isEmpty(headFlow)) {
//                return ResultDataThrow("请选择科主任");
//            }
//            if (StringUtil.isEmpty(teacherFlow)) {
//                return ResultDataThrow("请选择带教老师");
//            }
            jswjwBiz.modSubDept(userFlow, deptFlow, subDeptFlow, sysDeptFlow, headFlow, teacherFlow, startDate, endDate);
        } else {
            if (StringUtil.isEmpty(subDeptName)) {
                return ResultDataThrow("科室名称为空");
            }
            jswjwBiz.modSubDept(userFlow, deptFlow, subDeptFlow, subDeptName, startDate, endDate);
        }
        return resultMap;
    }

    @RequestMapping(value = {"/deleteSubDept"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object deleteSubDept(String userFlow, String deptFlow, String subDeptFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }
        if (StringUtil.isEmpty(subDeptFlow)) {
            return ResultDataThrow("轮转科室流水号为空");
        }

        int count = jswjwBiz.searchRecCount(subDeptFlow);
        if (count > 0) {
            return ResultDataThrow("该轮转科室已有数据保存，无法删除!");
        }

        jswjwBiz.deleteSubDept(userFlow, deptFlow, subDeptFlow);
        return resultMap;
    }


    @RequestMapping(value = {"/categoryProgress"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object categoryProgress(String userFlow, String deptFlow, String dataType, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }
        if (StringUtil.isEmpty(dataType)) {
            return ResultDataThrow("dataType为空或不能识别");
        }
        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空");
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        List<Map<String, Object>> catagoryDataList = jswjwBiz.categoryProgress(dataType, userFlow, deptFlow, doctor.getRotationFlow(), pageIndex, pageSize);
        resultMap.put("catagoryDataList", catagoryDataList);
        resultMap.put("dataCount", PageHelper.total);
        return resultMap;
    }

    @RequestMapping(value = {"/dataList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object dataList(String userFlow, String deptFlow, String dataType, String cataFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }
        if (StringUtil.isEmpty(dataType)) {
            return ResultDataThrow("dataType为空或不能识别");
        }
        if (StringUtil.isEmpty(cataFlow)) {
            return ResultDataThrow("cataFlow为空或不能识别");
        }
        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空");
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> dataList = jswjwBiz.dataList(dataType, userFlow, deptFlow, cataFlow);
        resultMap.put("dataList", dataList);
        resultMap.put("dataCount", PageHelper.total);
        return resultMap;
    }

    @RequestMapping(value = {"/inputList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object inputList(String userFlow, String deptFlow, String dataType, String cataFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }
        if (StringUtil.isEmpty(dataType)) {
            return ResultDataThrow("dataType为空或不能识别");
        }
//		if(StringUtil.isEmpty(cataFlow)){
//			model.addAttribute("resultId", "31004");
//			model.addAttribute("resultType", "数据分类标识为空或不能识别");
//			return "res/jswjw/addData";
//		}
//
//		if(isChargeOrg(userFlow)){
        if (true) {
            List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, null, null);
            resultMap.put("resultList", resultList);
        }
        _inputList(userFlow, deptFlow, dataType, cataFlow, resultMap);
        resultMap.put("isChargeOrg", com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jswjwBiz.getJsResCfgAppMenu(userFlow)));
        resultMap.put("isOther", com.pinde.core.common.GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
        return resultMap;
    }

    private void _inputList(String userFlow, String deptFlow, String dataType, String cataFlow, Map<String, Object> map) {
        switch (dataType) {
            case "mr":
                break;
            case "disease":
                List<Map<String, Object>> diseaseDiagNameList = jswjwBiz.commReqOptionNameList(userFlow, deptFlow, dataType, cataFlow);
                map.put("diseaseDiagNameList", diseaseDiagNameList);
                break;
            case "skill":
                List<Map<String, Object>> skillOperNameList = jswjwBiz.commReqOptionNameList(userFlow, deptFlow, dataType, cataFlow);
                map.put("skillOperNameList", skillOperNameList);
                break;
            case "operation":
                List<Map<String, Object>> operationOperNameList = jswjwBiz.commReqOptionNameList(userFlow, deptFlow, dataType, cataFlow);
                map.put("operationOperNameList", operationOperNameList);
                break;
            case "activity":
                List<SysDict> activityList = new ArrayList<>();
                List<ActivityTypeEnum> list = Arrays.asList(ActivityTypeEnum.values());
                for (ActivityTypeEnum ae : list) {
                    SysDict dict = new SysDict();
                    dict.setDictId(ae.getId());
                    dict.setDictName(ae.getName());
                    activityList.add(dict);
                }
                map.put("activityTypeList", activityList);
                break;
            case "summary":
                break;
            default:
                break;
        }
    }

    @RequestMapping(value = {"/addCaseData"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object addCaseData(JsResDataExt dataExt) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (null == dataExt) {
            return ResultDataThrow("参数格式错误");
        }
        String userFlow = dataExt.getUserFlow();
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        String deptFlow = dataExt.getDeptFlow();
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }
        String dataType = dataExt.getDataType();
        if (StringUtil.isEmpty(dataType)) {
            return ResultDataThrow("dataType为空或不能识别");
        }
        String cataFlow = dataExt.getCataFlow();
        if (StringUtil.isEmpty(cataFlow)) {
            return ResultDataThrow("数据分类标识为空或不能识别");
        }

        boolean isChargeOrg = true;

        if (studentFillInDataCheck(resultMap, dataExt)) {
            return ResultDataThrow((String) resultMap.get("msg"));
        }
        String filearr = dataExt.getFilearr();
        if (StringUtil.isNotBlank(filearr)) {
            List<JsResDataFile> fileList = JSON.parseObject(filearr, new TypeReference<List<JsResDataFile>>() {
            });
            dataExt.setFiles(fileList);
        }

        if(null != dataExt.getResultFlow()){
            ResDoctorSchProcess process = jswjwBiz.readSchProcessByResultFlow(dataExt.getResultFlow());
            if(null != process){
                if(StringUtil.isEmpty(process.getTeacherUserFlow()) || StringUtil.isEmpty(process.getHeadUserFlow())){
                    resultMap.put("resultId", "31104");
                    resultMap.put("resultType", "请先维护当前科室的带教老师和科主任！");
                    return resultMap;
                }
            }
            ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
            List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, null, null);
            String limitMonth = "";
            //禅道3300当设置为是时,不论是否出科，学员都可以添加多个轮转科室,但是只有当前科室出科后,才可以添加下个轮转科室的轮转数据；
            String ckxz = jswjwBiz.getJsResCfgCode("jsres_" + doctor.getOrgFlow() + "_org_ckxz");
            if (null != ckxz && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ckxz)) {
                ResOrgCkxzExample example = new ResOrgCkxzExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                        .andOrgFlowEqualTo(doctor.getOrgFlow())
                        .andSessionGradeEqualTo(doctor.getSessionNumber());
                List<ResOrgCkxz> list = resOrgCkxzMapper.selectByExample(example);
                if (list.size()>0){
                    ckxz = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    limitMonth = list.get(0).getSessionYear();
                }else{
                    ckxz = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ckxz)) {
                List<SchArrangeResult> resultListNew = jswjwBiz.searchSchArrangeResults(userFlow, doctor.getRotationFlow(), process.getSchStartDate());
                if (resultListNew != null) {
                    for (SchArrangeResult result : resultListNew) {
                        ResDoctorSchProcess process1 = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
                        if(null != process1 && StringUtil.isNotBlank(limitMonth) && process1.getSchEndDate().substring(0, 7).compareTo(limitMonth) >= 0){
                            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(process1.getTemporaryOut()) || (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(process1.getTemporaryOut()) && !"Passed".equals(process1.getTemporaryAuditStatusId()))) {
                                ResSchProcessExpress rec = expressBiz.getExpressByRecType(process1.getProcessFlow(), "AfterEvaluation");
                                if (rec == null) {
                                    resultMap.put("resultId", "30506");
                                    resultMap.put("resultType", "当前有未出科科室,无法填写当前科室数据");
                                    return resultMap;
                                }else{
                                    if (StringUtil.isNotBlank(rec.getManagerAuditUserFlow()) && StringUtil.isBlank(rec.getHeadAuditStatusId())) {
                                        resultMap.put("resultId", "30506");
                                        resultMap.put("resultType", "当前有未出科科室,无法填写当前科室数据");
                                        return resultMap;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        jswjwBiz.addData2(dataType, userFlow, deptFlow, cataFlow, dataExt, isChargeOrg);
        return resultMap;
    }

    /**
     * 学员补填数据 增加超时校验 shengl
     *
     * @return
     */
    private boolean studentFillInDataCheck(Map<String, Object> resultMap, JsResDataExt dataExt) {
        String inDataType = jswjwBiz.getCfgCode("student_fill_in_data");
        String userFlow = dataExt.getUserFlow();
        SysUser user = jswjwBiz.readSysUser(userFlow);
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        String inDataValueNew = jswjwBiz.getCfgCode("student_fill_in_data_time_" + user.getOrgFlow());
        String doctorTypeValue = jswjwBiz.getCfgCode("student_doctorType_" + user.getOrgFlow());
        List<String> result = new ArrayList<>();
        if (StringUtil.isNotBlank(doctorTypeValue)) {
            result.addAll(Arrays.asList(doctorTypeValue.split(",")));
        }
        String resultFlow = dataExt.getResultFlow();
        if (StringUtil.isNotBlank(inDataType) && StringUtil.isNotBlank(resultFlow)) {
            SchArrangeResult schArrangeResult = jswjwTeacherBiz.readSchArrangeResult(resultFlow);
            if (schArrangeResult != null) {
                int num = 0;
                String schEndDate = schArrangeResult.getSchEndDate();
                if (StringUtil.isNotBlank(schEndDate)) {
                    if (StringUtil.isNotBlank(inDataValueNew)) {
                        if (StringUtil.isNotBlank(doctorTypeValue) && result.contains(doctor.getDoctorTypeId())) {
                            if (inDataValueNew.compareTo(schEndDate) > 0) {
                                resultMap.put("msg", "已超出补填时间，不允许补填数据，请与基地管理员联系！");
                                return true;
                            }
                        }
                    }
                    try {
                        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(inDataType)) {
                            num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), schEndDate);
                        } else {
                            String inDataValue = jswjwBiz.getCfgCode("student_fill_in_data_value");
                            if (StringUtil.isNotBlank(inDataValue)) {
                                Pattern pattern = Pattern.compile("[0-9]*");
                                Matcher isNum = pattern.matcher(inDataValue);
                                if (isNum.matches()) {
                                    int SettingTime = Integer.parseInt(inDataValue);
                                    if (SettingTime > 0) {
                                        String afterTime = "";
                                        String timeType = jswjwBiz.getCfgCode("student_fill_in_data_type");
                                        if (com.pinde.core.common.GlobalConstant.PARAM_FLAG_ONE.equals(timeType)) {
                                            afterTime = DateTimeUtil.getAfterDate(schEndDate, SettingTime, "day");
                                        } else {
                                            afterTime = DateTimeUtil.getAfterDate(schEndDate, SettingTime, "month");
                                        }
                                        if (StringUtil.isNotBlank(afterTime)) {
                                            num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), afterTime);
                                        }
                                    }
                                }
                            }
                        }
                        if (num == 1) {
                            resultMap.put("msg", "已超过出科时间限制,无法补填数据！");
                            return true;
                        }
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                }

            }
        }
        return false;
    }

    @RequestMapping(value = {"/viewData"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object viewData(String userFlow, String deptFlow, String dataType, String cataFlow, String dataFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }
        if (StringUtil.isEmpty(dataType)) {
            return ResultDataThrow("dataType为空或不能识别");
        }
        if (StringUtil.isEmpty(dataFlow)) {
            return ResultDataThrow("数据唯一标识为空");
        }

//		if(isChargeOrg(userFlow)){
        if (true) {
            List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, null, null);
            resultMap.put("resultList", resultList);
        }

        _inputList(userFlow, deptFlow, dataType, cataFlow, resultMap);
        Map<String, Object> resultData = jswjwBiz.viewData(dataType, userFlow, deptFlow, dataFlow);
        resultMap.put("resultData", resultData);

        resultMap.put("isChargeOrg", com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jswjwBiz.getJsResCfgAppMenu(userFlow)));
        resultMap.put("isOther", com.pinde.core.common.GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));

        return resultMap;
    }

    @RequestMapping(value = {"/modData"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object modData(JsResDataExt dataExt) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (null == dataExt) {
            return ResultDataThrow("参数格式错误");
        }
        String userFlow = dataExt.getUserFlow();
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        String deptFlow = dataExt.getDeptFlow();
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }
        String dataType = dataExt.getDataType();
        if (StringUtil.isEmpty(dataType)) {
            return ResultDataThrow("dataType为空或不能识别");
        }
        String dataFlow = dataExt.getDataFlow();
        if (StringUtil.isEmpty(dataFlow)) {
            return ResultDataThrow("数据唯一标识为空");
        }
        String cataFlow = dataExt.getCataFlow();
        if (StringUtil.isEmpty(cataFlow)) {
            return ResultDataThrow("数据分类标识为空或不能识别");
        }

//		boolean isChargeOrg = isChargeOrg(userFlow);
        boolean isChargeOrg = true;
        String filearr = dataExt.getFilearr();
        if (StringUtil.isNotBlank(filearr)) {
            List<JsResDataFile> fileList = JSON.parseObject(filearr, new TypeReference<List<JsResDataFile>>() {
            });
            dataExt.setFiles(fileList);
        }

        jswjwBiz.modData2(dataExt, isChargeOrg);
        return resultMap;
    }

    @RequestMapping(value = {"/delData"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object delData(String userFlow, String deptFlow, String dataType, String dataFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }
        if (StringUtil.isEmpty(dataType)) {
            return ResultDataThrow("dataType为空或不能识别");
        }
        if (StringUtil.isEmpty(dataFlow)) {
            return ResultDataThrow("数据唯一标识为空");
        }
//        ResRec rec = jswjwBiz.readResRecByDataType(dataFlow, dataType);
        ResRec rec = jswjwBiz.readResRec(dataFlow);
        if (StringUtil.isNotBlank(rec.getAuditStatusId())) {
            return ResultDataThrow("数据已审核,无法删除");
        }
        jswjwBiz.delData(dataType, userFlow, deptFlow, dataFlow);
        return resultMap;
    }

    @RequestMapping(value = {"/viewImage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object viewImage(String userFlow, String deptFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }
        List<Map<String, String>> dataList = jswjwBiz.viewImage(userFlow, deptFlow);
        resultMap.put("dataList", dataList);
        return resultMap;
    }

    @RequestMapping(value = {"/addImage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object addImage(ImageFileForm form, HttpServletRequest request, HttpServletResponse response) {
//		System.err.println(form);
//		System.err.println(form.getUploadFileData());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(form.getUserFlow())) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(form.getDeptFlow())) {
            return ResultDataThrow("科室标识符为空");
        }
        if (StringUtil.isEmpty(form.getFileName())) {
            return ResultDataThrow("文件名为空");
        }
        if (StringUtil.isBlank(form.getImageContent())) {
            return ResultDataThrow("上传文件不能为空");
        }
        jswjwBiz.addImage2(form);
        return resultMap;
    }

    @RequestMapping(value = {"/addCaseImage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object addCaseImage(ImageFileForm form, HttpServletRequest request, HttpServletResponse response) {
//		System.err.println(form);
//		System.err.println(form.getUploadFileData());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(form.getUserFlow())) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(form.getFileName())) {
            return ResultDataThrow("文件名为空");
        }
        if (StringUtil.isEmpty(form.getImageContent())) {
            return ResultDataThrow("文件内容不能为空");
        }
//        if (form.getUploadFile() == null && StringUtil.isBlank(form.getUploadFileData())) {
//            return ResultDataThrow("上传文件不能为空");
//        }
        Map<String, String> imgUrlMap = jswjwBiz.addCaseImage2(form);
        if (imgUrlMap == null) {
            return ResultDataThrow("上传文件失败");
        }
        resultMap.put("imgUrlMap", imgUrlMap);
        //定义上传路径
//        String baseDir = jswjwBiz.getCfgCode("upload_base_dir");
//        String dateString = DateUtil.getCurrDate2();
//        String newDir = baseDir + File.separator+"caseImage"+File.separator +dateString ;
//        String fileName = form.getFileName();
//        String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
//        String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
//        fileName=preffix+suffix;
//        File fileDir = new File(newDir);
//        if(!fileDir.exists()){
//            fileDir.mkdirs();
//        }
//        //重命名上传后的文件名
//        try {
//            generateImage(form.getImageContent(), fileDir + File.separator + fileName);
//        } catch (Exception e) {
//             logger.error("",e);
//            throw new RuntimeException("保存文件失败！");
//        }
//        String urlCfg = jswjwBiz.getCfgCode("upload_base_url");
//        resultMap.put("imageUrl",urlCfg+"/caseImage/"+dateString+"/"+fileName);
        return resultMap;
    }

    @RequestMapping(value = {"/deleteImage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object deleteImage(String userFlow, String deptFlow, String imageFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("科室标识符为空");
        }
        if (StringUtil.isEmpty(imageFlow)) {
            return ResultDataThrow("图片标识符为空");
        }
        jswjwBiz.deleteImage(userFlow, deptFlow, imageFlow);
        return resultMap;
    }

    @RequestMapping(value = {"/userCenter"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object userCenter(String userFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser userinfo = jswjwBiz.getUserBaseInfo(userFlow);
        resultMap.put("userinfo", userinfo);

        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        if (userinfo != null && StringUtil.isNotBlank(userinfo.getUserHeadImg())) {
            resultMap.put("userHeadImage", uploadBaseUrl + "/" + userinfo.getUserHeadImg());
        } else {
            resultMap.put("userHeadImage", "");
        }

        Map<String, Object> doctorInfoMap = jswjwBiz.getDoctorExtInfo(userFlow);
        if (doctorInfoMap != null) {
            resultMap.put("doctorInfoMap", doctorInfoMap);
        } else {
            return ResultDataThrow("登录失败,学员数据出错!");
        }

//        boolean monthFirst = jswjwBiz.saveLoginLog(userinfo);
        boolean monthFirst = false;
        resultMap.put("tipFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
        if (monthFirst) {
            resultMap.put("tipFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            String loginLimit = jswjwBiz.getCfgCode("student_timeout_login_limit");
            String msgMonth = "1";
            if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(loginLimit) && StringUtil.isNotBlank(loginLimit)) {
                msgMonth = loginLimit;
            }
            resultMap.put("tipContent", "学员需每" + msgMonth + "个月至少填写一次培训数据，否则将无法继续使用APP。");
        }
        ResDoctor doctor = (ResDoctor) doctorInfoMap.get("doctor");
//        //消息提醒
//        int noticesCount = jswjwStudentBiz.countResErrorNotices(userFlow, NoticeStatusEnum.NoRead.getId());
//        resultMap.put("noticeCount", String.valueOf(noticesCount));
//
//        String trainingYears = doctor.getTrainingYears();
//        //提醒所有学员（除2020级学员，2021级减免学员，2022级减免2年学员）需要在 2023 年7月1日前设置完最少一年的轮转记录,否则将无法填写轮转数据,添加轮转记录
//        boolean flag=true;
//        int years=3;
//        //查看学员的减免年份
//        if (null == trainingYears || StringUtil.isEmpty(trainingYears)){
//            years=3;
//        }else if (trainingYears.equals("ThreeYear")){
//            years=3;
//        } else if (trainingYears.equals("TwoYear")){
//            years=2;
//        } else if (trainingYears.equals("OneYear")){
//            years=1;
//        }
//        if (Integer.parseInt(doctor.getSessionNumber())<=2020){
//            flag=false;
//        }else if (doctor.getSessionNumber().equals("2020")){
//            flag=false;
//        }else if (doctor.getSessionNumber().equals("2021") && years >=1){
//            flag=false;
//        }else if (doctor.getSessionNumber().equals("2022") && years==2){
//            flag=false;
//        }else if (Integer.parseInt(doctor.getSessionNumber())<=2021 && doctor.getTrainingTypeId().equals("AssiGeneral")){
//            flag=false; //21年和21年之前的助理全科都不提示
//        }
//        String nowDate = DateUtil.getCurrDate();
//        if (nowDate.compareTo("2023-07-01")<0 && flag==true){
//            //根据基地参数配置（轮转计划排版设置）提示不同信息
//            //设置为不开启时，学员APP端不弹出任何提示
//            List<String> codes = new ArrayList<>();
//            String code1 = "jsres_"+doctor.getOrgFlow()+"_org_process_scheduling_N";
//            String code2 = "jsres_"+doctor.getOrgFlow()+"_org_process_scheduling_org";
//            String code3 = "jsres_"+doctor.getOrgFlow()+"_org_process_scheduling_user";
//            codes.add(code1);
//            codes.add(code2);
//            codes.add(code3);
//            List<JsresPowerCfg> cfgs = powerCfgExtMapper.searchPowerCfg(codes);
//            // 组装成map
//            Map<String, String> codeMap = cfgs.stream().collect(Collectors.toMap(JsresPowerCfg::getCfgCode, JsresPowerCfg::getCfgValue));
//            if (codeMap != null) {
//                boolean code2Flag = com.pinde.core.common.GlobalConstant.FLAG_Y.equals(codeMap.get(code2));
//                boolean code3Flag = com.pinde.core.common.GlobalConstant.FLAG_Y.equals(codeMap.get(code3));
//                //设置为基地配置时
//                if (code2Flag) {
//                    resultMap.put("tipFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
//                    resultMap.put("tipContent", "您好，请联系基地管理员提前导入最少一年的科室轮转计划，如未导入轮转记录，后续将无法填写轮转数据；");
//                }
//                //设置为学员自行配置时
//                if (code3Flag) {
//                    resultMap.put("tipFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
//                    resultMap.put("tipContent", "您好！请预先填写完未来最少一年的科室轮转记录，如未添加轮转记录，将无法填写轮转数据");
//                }
//                //设置为基地配置和学员自行配置都勾选时
//                if (code2Flag && code3Flag) {
//                    resultMap.put("tipFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
//                    resultMap.put("tipContent", "您好！请预先填写完未来最少一年的科室轮转记录，如未添加轮转记录，将无法填写轮转数据");
//                }
//            }
//        }
        String orgFlow = "";

        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            orgFlow = doctor.getSecondOrgFlow();
        } else {
            orgFlow = doctor.getOrgFlow();
        }
        String key = "jsres_hospital_ndkhsz_" + orgFlow;
        String isNdks = jswjwBiz.getJsResCfgCode(key);
        resultMap.put("isNdks", isNdks);

        resultMap = (Map<String, Object>) jswjwBiz.getDoctorAuthorityInfo2(resultMap, userFlow, orgFlow);
        if (doctor != null && com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(doctor.getTrainingTypeId())) {
            resultMap.put("isPxsc", true);
        }

        Map<String, Object> schMap = jswjwBiz.getDoctorSchInfo(userFlow);

        resultMap.put("schMap",schMap);

        // 查询医师权限
        return resultMap;
    }

    /**
     * 出科异常通知
     */
    @RequestMapping(value = {"/resErrorNotices"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object resErrorNotices(String userFlow, Integer pageIndex, Integer pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }

        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空!");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空!");
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (currUser == null) {
            return ResultDataThrow("用户不存在!");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("userFlow", userFlow);
        //消息提醒
        PageHelper.startPage(pageIndex, pageSize);
        List<ResErrorSchNotice> notices = jswjwStudentBiz.getResErrorNotices(param);
        resultMap.put("notices", notices);
        resultMap.put("dataCount", PageHelper.total);

        param.put("statusId", "NoRead");//查询未读消息
        List<ResErrorSchNotice> noReadNotices = jswjwStudentBiz.getResErrorNotices(param);
        resultMap.put("noReadNotices", null == noReadNotices ? "0" : String.valueOf(noReadNotices.size()));

        return resultMap;
    }

    @RequestMapping(value = {"/saveReadNotice"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveReadNotice(String userFlow, String recordFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        if (StringUtil.isEmpty(recordFlow)) {
            return ResultDataThrow("信息标识符为空!");
        }
        ResErrorSchNotice notice = jswjwStudentBiz.readErrorSchNotice(recordFlow);
        if (notice == null) {
            return ResultDataThrow("信息不存在!");
        }
        notice.setStatusId(NoticeStatusEnum.IsRead.getId());
        notice.setStatusName(NoticeStatusEnum.IsRead.getName());
        SysUser user = jswjwBiz.readSysUser(userFlow);
        jswjwStudentBiz.editErrorNotice(notice, user);
        return resultMap;
    }

    @RequestMapping(value = {"/viewGrade"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object viewGrade(String userFlow, String deptFlow, String subDeptFlow, String assessType, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("标准科室不能为空!");
        }
        if (StringUtil.isEmpty(subDeptFlow)) {
            return ResultDataThrow("轮转科室标识符为空!");
        }
        if (StringUtil.isEmpty(assessType)) {
            return ResultDataThrow("评价类型不能为空!TeacherAssess | DeptAssess!");
        }
        DeptTeacherGradeInfo rec = null;
        ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(subDeptFlow);
        if (process == null) {
            return ResultDataThrow("轮转科室未关联，请重新选择轮转科室!");
        }


        if ("TeacherAssess".equals(assessType)) {
            rec = gradeBiz.getGradeRec(userFlow, deptFlow, process.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
        } else if ("DeptAssess".equals(assessType)) {
            rec = gradeBiz.getGradeRec(userFlow, deptFlow, process.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
        }
        ResAssessCfg assessCfg;
        if (rec != null) {
            assessCfg = jswjwBiz.readCfgAssess(rec.getCfgFlow());
        } else {
            ResAssessCfg search = new ResAssessCfg();
            search.setCfgCodeId(assessType);
            search.setFormStatusId(com.pinde.core.common.GlobalConstant.FLAG_Y);
            List<ResAssessCfg> resAssessCfgList = jswjwBiz.selectResByExampleWithBLOBs(search);
            if (resAssessCfgList != null && resAssessCfgList.size() > 0) {
                assessCfg = resAssessCfgList.get(0);
            } else {
                return ResultDataThrow("未查到配置评分表,请联系管理员!");
            }
        }
        resultMap.put("assessCfg", assessCfg);
        resultMap.put("cfgFlow", assessCfg.getCfgFlow());
        if (assessCfg != null) {
            String content = assessCfg.getCfgBigValue();
            //解析评分xml
            List<Map<String, Object>> assessMaps = jswjwBiz.parseAssessCfg(content);

            Map<String, Object> recMap = new HashMap<>();
            if (rec != null) {
                String recContent = rec.getRecContent();
                //解析登记信息的xml
                Object formDataMap = jswjwBiz.parseDocGradeXml(recContent);
                List<Map<String, Object>> formDataList = (List<Map<String, Object>>) formDataMap;
//                resultMap.put("formDataMap", formDataMap);
                if (null != formDataList && formDataList.size() > 0) {
                    for (Map<String, Object> data : formDataList) {
                        recMap.put((String) data.get("inputId"), data.get("value"));
                    }
                }
            }

            if (null != assessMaps && assessMaps.size() > 0) {
                for (Map<String, Object> m : assessMaps) {
                    List<Map<String, Object>> itemList = (List<Map<String, Object>>) m.get("items");
                    if (null != itemList && itemList.size() > 0) {
                        for (Map<String, Object> item : itemList) {
                            String id = (String) item.get("id");
                            item.put("dataScore", recMap.get(id + "_score"));
                            item.put("lostReason", recMap.get(id + "_lostReason"));
                        }
                    }

                }
            }
            resultMap.put("assessMaps", assessMaps);
        }
        return resultMap;
    }

    @RequestMapping(value = {"/saveGrade"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveGrade(String cfgFlow, String userFlow, String deptFlow, String subDeptFlow, String assessType, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空!");
        }
        if (StringUtil.isEmpty(deptFlow)) {
            return ResultDataThrow("标准科室不能为空!");
        }
        if (StringUtil.isEmpty(subDeptFlow)) {
            return ResultDataThrow("轮转科室标识符为空!");
        }

        if (StringUtil.isEmpty(assessType)) {
            return ResultDataThrow("评价类型不能为空!TeacherAssess | DeptAssess!");
        }

        DeptTeacherGradeInfo rec = null;
        ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(subDeptFlow);
        if (process == null) {
            return ResultDataThrow("轮转科室未关联，请重新选择轮转科室!");
        }
        if ("TeacherAssess".equals(assessType)) {
            rec = gradeBiz.getGradeRec(userFlow, deptFlow, process.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
        } else if ("DeptAssess".equals(assessType)) {
            rec = gradeBiz.getGradeRec(userFlow, deptFlow, process.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
        }
        ResAssessCfg assessCfg;
        if (rec != null) {
            assessCfg = jswjwBiz.readCfgAssess(rec.getCfgFlow());
        } else {
            ResAssessCfg search = new ResAssessCfg();
            search.setCfgCodeId(assessType);
            search.setFormStatusId(com.pinde.core.common.GlobalConstant.FLAG_Y);
            List<ResAssessCfg> resAssessCfgList = jswjwBiz.selectResByExampleWithBLOBs(search);
            if (resAssessCfgList != null && resAssessCfgList.size() > 0) {
                assessCfg = resAssessCfgList.get(0);
            } else {
                return ResultDataThrow("未查到配置评分表,请联系管理员!");
            }
        }

        String result = gradeBiz.saveGrade1(assessCfg, cfgFlow, userFlow, deptFlow, subDeptFlow, assessType, request);
        if (result != null && result.startsWith("error:")) {
            return ResultDataThrow(result.split(":")[1]);
        }
        return resultMap;
    }

    /**
     * 最新讲座查询
     */
    @RequestMapping(value = {"/getNewLectures"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object getNewLectures(String userFlow, String roleId, Integer pageIndex, Integer pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(roleId)) {
            return ResultDataThrow("roleId标识符为空");
        }
        if (!"Student".equals(roleId) && !"Teacher".equals(roleId) && !"Head".equals(roleId) && !"Seretary".equals(roleId)) {
            return ResultDataThrow("roleId标识符不正确");
        }

        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空");
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (currUser == null) {
            return ResultDataThrow("用户不存在");
        }
        String orgFlow = currUser.getOrgFlow();
        //获取当前配置的医师角色
        String doctorRole = jswjwBiz.getCfgCode("res_doctor_role_flow");
        //获取当前配置的老师角色
        String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
        //获取当前配置的科主任角色
        String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
        //获取当前配置的科秘角色
        String secretaryRole = jswjwBiz.getCfgCode("res_secretary_role_flow");
        String roleFlow = "";
        if ("Student".equals(roleId)) {
            ResDoctor doctor = jswjwBiz.readResDoctor(currUser.getUserFlow());
            if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                orgFlow = doctor.getSecondOrgFlow();
            } else {
                orgFlow = doctor.getOrgFlow();
            }
            roleFlow = doctorRole;
        }


        if ("Teacher".equals(roleId)) {
            roleFlow = teacherRole;
        }
        if ("Head".equals(roleId)) {
            roleFlow = headRole;
        }
        if ("Seretary".equals(roleId)) {
            roleFlow = secretaryRole;
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<ResLectureInfo> lectureInfos = jswjwBiz.SearchNewLectures(orgFlow, roleId, roleFlow);
//        resultMap.put("lectureInfos", lectureInfos);
        resultMap.put("dataCount", PageHelper.total);
        List<Map<String, Object>> resultMapList = new ArrayList<>();

//        Map<String, ResLectureScanRegist> registMap = new HashMap<>();
        Map<String, Integer> registNumMap = new HashMap<>();
        if (lectureInfos != null && lectureInfos.size() > 0) {
            for (ResLectureInfo lectureInfo : lectureInfos) {
                Map<String, Object> map = new HashMap<>();
                map.put("lectureFlow", lectureInfo.getLectureFlow());
                map.put("lectureTrainDate", lectureInfo.getLectureTrainDate());
                map.put("lectureStartTime", lectureInfo.getLectureStartTime());
                map.put("lectureEndTime", lectureInfo.getLectureEndTime());
                map.put("lectureTypeName", lectureInfo.getLectureTypeName());
                map.put("lectureContent", lectureInfo.getLectureContent());
                map.put("lectureTrainPlace", lectureInfo.getLectureTrainPlace());
                map.put("lectureTeacherName", lectureInfo.getLectureTeacherName());
                map.put("lectureDesc", StringUtil.isNotBlank(lectureInfo.getLectureDesc()) ? lectureInfo.getLectureDesc() : "无");

                String lectureFlow = lectureInfo.getLectureFlow();
                ResLectureScanRegist lectureScanRegist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
//                registMap.put(lectureFlow, lectureScanRegist);

                List<ResLectureScanRegist> resLectureScanRegists = jswjwBiz.searchIsRegists(lectureFlow);
                if (lectureScanRegist != null) {
                    registNumMap.put(lectureFlow, resLectureScanRegists.size());
                } else {
                    registNumMap.put(lectureFlow, 0);
                }

                if ((null == lectureScanRegist || (null != lectureScanRegist && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(lectureScanRegist.getIsRegist()))) &&
                        (StringUtil.isBlank(lectureInfo.getLimitNum()) || (StringUtil.isNotBlank(lectureInfo.getLimitNum()) && lectureInfo.getLimitNum().compareTo(registNumMap.get(lectureInfo.getLectureFlow()).toString()) > 0))
                ) {
                    map.put("operId", "NoSign");
                    map.put("operName", "报名");
                }

                if (null != lectureScanRegist && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(lectureScanRegist.getIsRegist())) {
                    if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(lectureScanRegist.getIsScan())) {
                        map.put("operId", "NoScan");
                        map.put("operName", "取消报名");
                    } else {
                        map.put("operId", "IsSign");
                        map.put("operName", "已报名");
                    }
                }
                resultMapList.add(map);
            }
//            resultMap.put("registMap", registMap);
        }
        resultMap.put("resultMapList", resultMapList);
        return resultMap;
    }

    /**
     * 历史讲座查询
     */
    @RequestMapping(value = {"/getHistoryLectures"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object getHistoryLectures(String userFlow, Integer pageIndex, Integer pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }

        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空");
        }
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        PageHelper.startPage(pageIndex, pageSize);

        Map<String, ResLectureEvaDetail> evaDetailMap = new HashMap<>();
        Map<String, Integer> evaMap = new HashMap<>();
        Map<String, String> scanMap = new HashMap<>();
        Map<String, String> scan2Map = new HashMap<>();
        List<Map<String, String>> newList = jswjwBiz.getHistoryLecture(userFlow);
        if (newList != null && !newList.isEmpty()) {
            String currDateTime = DateUtil.getCurrDateTime();
            String currDate = currDateTime.substring(0, 4) + "-" + currDateTime.substring(4, 6) + "-" + currDateTime.substring(6, 8);
            String currTime = currDateTime.substring(8, 10) + ":" + currDateTime.substring(10, 12);
            for (Map<String, String> bean : newList) {
                String isScan = bean.get("isCan");
                String isScan2 = bean.get("isCan2");
                String lectureFlow = bean.get("lectureFlow");
                String lectureEndTime = bean.get("lectureEndTime");
                String lectureTrainDate = bean.get("lectureTrainDate");
                //判断是否到评价期限
                String date = bean.get("lectureTrainDate");
                String time = bean.get("lectureEndTime");
                String unitID = bean.get("lectureUnitId");
                String period = bean.get("lectureEvaPeriod");
                String startDate = date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10) + time.substring(0, 2) + time.substring(3, 5) + "00";
                int step = 0;
                if (com.pinde.core.common.enums.SchUnitEnum.Hour.getId().equals(unitID)) {
                    step = Integer.parseInt(period);
                }
                if (com.pinde.core.common.enums.SchUnitEnum.Day.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24;
                }
                if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24 * 7;
                }
                if (com.pinde.core.common.enums.SchUnitEnum.Month.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24 * 30;
                }
                if (com.pinde.core.common.enums.SchUnitEnum.Year.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24 * 365;
                }
                String endDate = DateUtil.addHour(startDate, step);
                String currentDate = DateUtil.getCurrDateTime();
                int dateFlag = endDate.compareTo(currentDate);
                //判断结束
                if ((lectureEndTime.compareTo(currTime) < 0 && lectureTrainDate.compareTo(currDate) == 0) || (lectureTrainDate.compareTo(currDate) < 0)) {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isScan)) {
                        scanMap.put(lectureFlow, com.pinde.core.common.GlobalConstant.FLAG_Y);
                    }
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isScan2)) {
                        scan2Map.put(lectureFlow, com.pinde.core.common.GlobalConstant.FLAG_Y);
                    }
                    evaMap.put(lectureFlow, dateFlag);
                }
                List<ResLectureEvaDetail> lectureEvaDetails = jswjwBiz.searchByUserFlowLectureFlow(userFlow, lectureFlow);
                if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
                    ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
                    evaDetailMap.put(lectureFlow, lectureEvaDetail);
                }

                Map<String, Object> map = new HashMap<>();
                map.put("lectureFlow", lectureFlow);
                map.put("lectureTrainDate", lectureTrainDate);
                map.put("lectureStartTime", bean.get("lectureStartTime"));
                map.put("lectureEndTime", bean.get("lectureEndTime"));
                map.put("lectureTypeName", bean.get("lectureTypeName"));
                map.put("lectureContent", bean.get("lectureContent"));
                map.put("lectureTrainPlace", bean.get("lectureTrainPlace"));
                map.put("lectureTeacherName", bean.get("lectureTeacherName"));
                map.put("isRegist", bean.get("isRegist"));
                map.put("isCan", bean.get("isCan"));
                map.put("evaEndTime", bean.get("evaEndTime"));
                map.put("startTime", bean.get("startTime"));

                map.put("endTime", bean.get("endTime"));
                map.put("nowTime", bean.get("nowTime"));
                map.put("isClosed", bean.get("isClosed"));
                map.put("evaFlow", bean.get("evaFlow"));
                map.put("lectureDesc", StringUtil.isBlank(bean.get("lectureDesc")) ? "无" : bean.get("lectureDesc"));
                ResLectureEvaDetail detail = evaDetailMap.get(lectureFlow);
                if (null == detail && evaMap.get(lectureFlow) >= 0 &&
                        StringUtil.isNotBlank(scanMap.get(lectureFlow)) && StringUtil.isNotBlank(scan2Map.get(lectureFlow))) {
                    map.put("operId", "Evaluate");
                    map.put("operName", "评价");
                } else if (null != detail && StringUtil.isNotBlank(scanMap.get(lectureFlow))) {
                    map.put("operId", "ShowLecture");
                    map.put("operName", "查看评价");
                } else if (evaMap.get(lectureFlow) < 0 && StringUtil.isNotBlank(scanMap.get(lectureFlow)) &&
                        StringUtil.isNotBlank(scan2Map.get(lectureFlow)) && null == detail
                ) {
                    map.put("operId", "IsClosed");
                    map.put("operName", "评价已关闭");
                } else if (StringUtil.isNotBlank(scanMap.get(lectureFlow)) && StringUtil.isBlank(scan2Map.get(lectureFlow))) {
                    map.put("operId", "IsScan");
                    map.put("operName", "已签到");
                } else if (StringUtil.isNotBlank(scanMap.get(lectureFlow)) && StringUtil.isNotBlank(scan2Map.get(lectureFlow))) {
                    map.put("operId", "IsOut");
                    map.put("operName", "已签退");
                }
                resultMapList.add(map);
            }
        }
//        resultMap.put("scanMap", scanMap);
//        resultMap.put("scan2Map", scan2Map);
//        resultMap.put("evaMap", evaMap);
        resultMap.put("dataCount", PageHelper.total);
//        resultMap.put("evaDetailMap", evaDetailMap);
//        resultMap.put("lectureInfos", newList);
        resultMap.put("resultMapList", resultMapList);
        return resultMap;
    }

    /**
     * 报名讲座
     */
    @RequestMapping(value = {"/lectureRegist"}, method = {RequestMethod.POST})
    @ResponseBody
    public synchronized Object lectureRegist(String lectureFlow, String roleId, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "报名成功！");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(lectureFlow)) {
            return ResultDataThrow("讲座流水号为空");
        }
        if (StringUtil.isEmpty(roleId)) {
            return ResultDataThrow("roleId标识符为空");
        }
        if (!"Student".equals(roleId) && !"Teacher".equals(roleId) && !"Head".equals(roleId) && !"Seretary".equals(roleId)) {
            return ResultDataThrow("roleId标识符不正确");
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (currUser == null) {
            return ResultDataThrow("用户不存在");
        }
        String orgFlow = currUser.getOrgFlow();
        //获取当前配置的医师角色
        String doctorRole = jswjwBiz.getCfgCode("res_doctor_role_flow");
        //获取当前配置的老师角色
        String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
        //获取当前配置的科主任角色
        String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
        String roleFlow = "";
        ResDoctor doctor = null;
        if ("Student".equals(roleId)) {
            doctor = jswjwBiz.readResDoctor(currUser.getUserFlow());
            orgFlow = doctor.getOrgFlow();
            roleFlow = doctorRole;
        }
        if ("Teacher".equals(roleId)) {
            roleFlow = teacherRole;
        }
        if ("Head".equals(roleId)) {
            roleFlow = headRole;
        }
        ResLectureScanRegist regist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
        if (regist != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsRegist())) {
            return ResultDataThrow("已经报过名了");
        }

        List<ResLectureScanRegist> resLectureScanRegists = jswjwBiz.searchIsRegists(lectureFlow);
        ResLectureInfo lectureInfo = jswjwBiz.read(lectureFlow);
        if (StringUtil.isBlank(lectureInfo.getLimitNum()) || resLectureScanRegists == null || resLectureScanRegists.size() < Integer.valueOf(lectureInfo.getLimitNum())) {

            List<ResLectureInfo> infos = jswjwBiz.checkJoinList(lectureFlow, userFlow);
            if (infos != null && infos.size() > 0) {
                ResLectureInfo resLectureInfo = infos.get(0);
                return ResultDataThrow("已报名同一时间【" + resLectureInfo.getLectureContent() + "】，不能报名！");
            }
            int count = jswjwBiz.editLectureScanRegist(lectureFlow, currUser, regist, doctor);
            if (count < 0) {
                return ResultDataThrow("该讲座报名人数已满，请刷新列表页面！");
            }
            if (count == 0) {
                return ResultDataThrow("报名失败，请刷新列表页面！");
            }
            if (count == 1) {
                String lectureTrainDate = lectureInfo.getLectureTrainDate();
                String lectureStartTime = lectureInfo.getLectureStartTime();
                String year = lectureTrainDate.substring(0, 4);
                resultMap.put("year", year);
                String month = lectureTrainDate.substring(5, 7);
                resultMap.put("month", month);
                String day = lectureTrainDate.substring(8, 10);
                resultMap.put("day", day);
                String hour = lectureStartTime.substring(0, 2);
                resultMap.put("hour", hour);
                String min = lectureStartTime.substring(3, 5);
                resultMap.put("min", min);
            } else {
                return ResultDataThrow("报名失败！");
            }
        } else {
            return ResultDataThrow("该讲座报名人数已满，请刷新列表页面！");
        }
        return resultMap;
    }

    /**
     * 取消报名讲座
     */
    @RequestMapping(value = {"/lectureCannelRegist"}, method = {RequestMethod.POST})
    @ResponseBody
    public synchronized Object lectureCannelRegist(String lectureFlow, String roleId, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "取消成功！");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(lectureFlow)) {
            return ResultDataThrow("讲座流水号为空");
        }
        if (StringUtil.isEmpty(roleId)) {
            return ResultDataThrow("roleId标识符为空");
        }
        if (!"Student".equals(roleId) && !"Teacher".equals(roleId) && !"Head".equals(roleId) && !"Seretary".equals(roleId)) {
            return ResultDataThrow("roleId标识符不正确");
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (currUser == null) {
            return ResultDataThrow("用户不存在");
        }
        String orgFlow = currUser.getOrgFlow();
        //获取当前配置的医师角色
        String doctorRole = jswjwBiz.getCfgCode("res_doctor_role_flow");
        //获取当前配置的老师角色
        String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
        //获取当前配置的科主任角色
        String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
        String roleFlow = "";
        ResDoctor doctor = null;
        if ("Student".equals(roleId)) {
            doctor = jswjwBiz.readResDoctor(currUser.getUserFlow());
            orgFlow = doctor.getOrgFlow();
            roleFlow = doctorRole;
        }
        if ("Teacher".equals(roleId)) {
            roleFlow = teacherRole;
        }
        if ("Head".equals(roleId)) {
            roleFlow = headRole;
        }
        ResLectureScanRegist regist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
        if (regist == null) {
            return ResultDataThrow("你未报名，无法取消报名信息！");
        }
        if (StringUtil.isBlank(regist.getIsRegist())) {
            return ResultDataThrow("你未报名，无法取消报名信息！");
        }
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsRegist())) {
            return ResultDataThrow("你已取消报名！");
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsScan())) {
            return ResultDataThrow("你已扫码签到，无法取消报名信息！");
        }
        regist.setIsRegist(com.pinde.core.common.GlobalConstant.FLAG_N);
        int c = jswjwBiz.saveRegist(regist);
        if (c == 0) {
            return ResultDataThrow("取消失败！");
        }
        return resultMap;
    }

    /**
     * 查看页面
     */
    @RequestMapping(value = {"/evaluate"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object evaluate(String lectureFlow, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(lectureFlow)) {
            return ResultDataThrow("讲座流水号为空");
        }
        resultMap.put("lectureFlow", lectureFlow);
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        List<ResLectureEvaDetail> lectureEvaDetails = jswjwBiz.searchByUserFlowLectureFlow(userFlow, lectureFlow);
        if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
            ResLectureEvaDetail resLectureEvaDetail = lectureEvaDetails.get(0);
            if (resLectureEvaDetail != null) {
                resultMap.put("resLectureEvaDetail", resLectureEvaDetail);
            }
        }
        return resultMap;
    }

    /**
     * 查看页面
     */
    @RequestMapping(value = {"/evaluateNew"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object evaluateNew(String lectureFlow, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(lectureFlow)) {
            return ResultDataThrow("讲座流水号为空");
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        // 查询评价人信息
        ResLectureScanRegist scanRegist = jswjwBiz.searchByUserFlowAndLectureFlow(currUser.getUserFlow(), lectureFlow);
        resultMap.put("scanRegist", scanRegist);
        // 查询评价指标信息
        List<LectureInfoTarget> lectureTargetList = jswjwBiz.searchLectureInfoTargetList(lectureFlow);
        resultMap.put("lectureTargetList", lectureTargetList);
        // 查询当前用户评价信息
        Map<String, String> param = new HashMap<String, String>();
        param.put("lectureFlow", lectureFlow);
        param.put("userFlow", currUser.getUserFlow());
        List<ResLectureEvaDetail> lectureEvaDetailList = jswjwBiz.searchUserEvalList(param);
        //评价人员评分详情
        Map<String, Object> evalDetailMap = new HashMap<>();
        if (null != lectureEvaDetailList && 0 < lectureEvaDetailList.size()) {
            for (ResLectureEvaDetail evaDetail : lectureEvaDetailList) {
                evalDetailMap.put(scanRegist.getRecordFlow() + evaDetail.getTargetFlow(), evaDetail.getEvaScore());
            }
        }
        resultMap.put("evalDetailMap", evalDetailMap);
        return resultMap;
    }

    /**
     * 保存评价
     */
    @RequestMapping(value = {"/saveEvaluate"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveEvaluate(ResLectureEvaDetail resLectureEvaDetail, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(resLectureEvaDetail.getLectureFlow())) {
            return ResultDataThrow("讲座流水号为空");
        }
        if (StringUtil.isEmpty(resLectureEvaDetail.getEvaContent())) {
            return ResultDataThrow("评价内容为空");
        }
        if (StringUtil.isEmpty(resLectureEvaDetail.getEvaScore())) {
            return ResultDataThrow("评价分数为空");
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        String userName = currUser.getUserName();
        if (StringUtil.isNotBlank(userFlow)) {
            resLectureEvaDetail.setOperUserFlow(userFlow);
        }
        if (StringUtil.isNotBlank(userName)) {
            resLectureEvaDetail.setOperUserName(userName);
        }
        ResLectureScanRegist lectureScanRegists = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, resLectureEvaDetail.getLectureFlow());
        if (lectureScanRegists == null) {
            return ResultDataThrow("报名信息与扫码信息为空");
        }
        if (StringUtil.isBlank(lectureScanRegists.getIsScan())) {
            return ResultDataThrow("扫码信息为空,不得评价");
        }
        List<ResLectureEvaDetail> lectureEvaDetails = jswjwBiz.searchByUserFlowLectureFlow(userFlow, resLectureEvaDetail.getLectureFlow());
        if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
            return ResultDataThrow("已经评价过讲座信息！请刷新页面后重试！");
        }
        int count = jswjwBiz.editResLectureEvaDetail(resLectureEvaDetail, userFlow);
        if (count == 0) {
            return ResultDataThrow("保存评价失败！");
        }
        return resultMap;
    }

    /**
     * 保存评价
     */
    @RequestMapping(value = {"/saveEvaluateNew"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveEvaluateNew(String evals, String recordFlow, BigDecimal avgScore, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(recordFlow)) {
            return ResultDataThrow("讲座流水号为空");
        }
        // 查询当前用户报名签到信息
        ResLectureScanRegist scanRegist = jswjwBiz.getLectureScanRegistInfoByFlow(recordFlow);
        if (scanRegist == null) {
            return ResultDataThrow("未参加该活动，无法评价");
        }
        if (scanRegist.getEvalScore() != null) {
            return ResultDataThrow("活动已评价");
        }
        // 讲座活动评分信息
        scanRegist.setEvalScore(avgScore);
        if (StringUtil.isBlank(evals)) {
            return ResultDataThrow("未选择评分");
        }
        List<ResLectureEvaDetail> lectureEvaDetailList = JSON.parseArray(evals, ResLectureEvaDetail.class);
        if (null == lectureEvaDetailList || 0 == lectureEvaDetailList.size()) {
            return ResultDataThrow("未选择评分");
        }
        // 保存评分用到的参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        paramMap.put("recordStatus", com.pinde.core.common.GlobalConstant.FLAG_Y);
        paramMap.put("operUserFlow", currUser.getUserFlow());
        paramMap.put("operUserName", currUser.getUserName());
        paramMap.put("createTime", DateUtil.getCurrDateTime());
        paramMap.put("lectureEvaDetailList", lectureEvaDetailList);
        String isSuccess = jswjwBiz.saveLectureEval(scanRegist, paramMap);
        if (com.pinde.core.common.GlobalConstant.SAVE_FAIL.equals(isSuccess)) {
            return ResultDataThrow("保存异常");
        }
        return resultMap;
    }

    @RequestMapping(value = {"/suggestions"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object suggestions(String userFlow, Integer pageIndex, Integer pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }

        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空");
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<ResTrainingOpinion> trainingOpinions = resLiveTrainingBiz.readByOpinionUserFlow(userFlow);
        resultMap.put("trainingOpinions", trainingOpinions);
        resultMap.put("dataCount", PageHelper.total);
        return resultMap;
    }

    @RequestMapping(value = {"/delOpinions"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object delOpinions(String trainingOpinionFlow, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(trainingOpinionFlow)) {
            return ResultDataThrow("意见反馈流水号为空");
        }
        ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
        if (trainingOpinion == null) {
            return ResultDataThrow("意见反馈不存在");
        }
        trainingOpinion.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        int count = resLiveTrainingBiz.edit(trainingOpinion, userFlow);
        if (count == 0) {
            return ResultDataThrow("删除失败");
        }
        return resultMap;
    }

    @RequestMapping(value = {"/opinionsDetail"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object opinionsDetail(String trainingOpinionFlow, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(trainingOpinionFlow)) {
            return ResultDataThrow("意见反馈流水号为空");
        }
        ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
        List<ResTrainingOpinion> trainingOpinions = new ArrayList<>();
        trainingOpinions.add(trainingOpinion);
        resultMap.put("trainingOpinions", trainingOpinions);
        return resultMap;
    }

    @RequestMapping(value = {"/saveOpinions"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveOpinions(ResTrainingOpinion trainingOpinion, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }

        if (StringUtil.isEmpty(trainingOpinion.getOpinionUserContent())) {
            return ResultDataThrow("意见反馈信息为空");
        } else {
            try {
                String content = URLDecoder.decode(trainingOpinion.getOpinionUserContent(), "UTF-8");
                trainingOpinion.setOpinionUserContent(content);
            } catch (UnsupportedEncodingException e) {
                logger.error("", e);
            }
        }
        SysUser currentUser = jswjwBiz.readSysUser(userFlow);
        String userName = currentUser.getUserName();
        if (StringUtil.isNotBlank(userName)) {
            trainingOpinion.setOpinionUserName(userName);
        }
        trainingOpinion.setOpinionUserFlow(userFlow);

        trainingOpinion.setOpinionUserFlow(userFlow);
        String orgFlow = "";
        String orgName = "";
        if (StringUtil.isBlank(orgFlow)) {
            ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
            if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                orgFlow = doctor.getSecondOrgFlow();
                orgName = doctor.getSecondOrgName();
            } else {
                orgFlow = doctor.getOrgFlow();
                orgName = doctor.getOrgName();
            }
        }
        if (StringUtil.isNotBlank(orgFlow)) {
            trainingOpinion.setOrgFlow(orgFlow);
        }
        if (StringUtil.isNotBlank(orgName)) {
            trainingOpinion.setOrgName(orgName);
        }
        String currTime = DateUtil.getCurrDateTime();
        trainingOpinion.setEvaTime(currTime);
        int count = resLiveTrainingBiz.edit(trainingOpinion, userFlow);
        if (count == 0) {
            return ResultDataThrow("保存失败");
        }
        return resultMap;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/getZhupeiNotices"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object getZhupeiNotices(String userFlow, String roleId, Integer pageIndex, Integer pageSize, String noticeTitle, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空");
        }
        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色为空");
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        String orgFlow = currUser.getOrgFlow();
        String doctorTypeId = "";
        ResDoctor doctor = null;
        if ("Student".equals(roleId)) {
            doctor = jswjwBiz.readResDoctor(userFlow);
            if (doctor == null) {
                return ResultDataThrow("医师信息不存在");
            }
            if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                orgFlow = doctor.getSecondOrgFlow();
            } else {
                orgFlow = doctor.getOrgFlow();
            }
            doctorTypeId = doctor.getDoctorTypeId();
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<ResTarinNotice> tarinNotices = resLiveTrainingBiz.searchByTitleOrgFlowAndWorkOrgId(noticeTitle, orgFlow, doctor);
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        resultMap.put("uploadBaseUrl", uploadBaseUrl);

        String httpurl = request.getRequestURL().toString();
        String servletPath = request.getServletPath();
        String hostUrl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/jswjw/showNotice/no-pic.png";
        resultMap.put("hostUrl", hostUrl);
        resultMap.put("tarinNotices", tarinNotices);
        resultMap.put("dataCount", PageHelper.total);
        return resultMap;
    }

    @RequestMapping(value = {"/showSysNotice"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String showSysNotice(String infoFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
        map.put("info", info);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = {"/zpNoticeDetail"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object zpNoticeDetail(String userFlow, String recordFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(recordFlow)) {
            return ResultDataThrow("住培指南标识符为空");
        }
        ResTarinNotice tarinNotices = resLiveTrainingBiz.readNotice(recordFlow);
        if (tarinNotices == null) {
            return ResultDataThrow("住培指南标不存在，请刷新列表页面");
        }
        resultMap.put("title", tarinNotices.getResNoticeTitle());
        resultMap.put("content", tarinNotices.getResNoticeContent());
        resultMap.put("createTime", DateUtil.transDate(tarinNotices.getCreateTime()));
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String httpurl = httpRequest.getRequestURL().toString();
//        if (!httpurl.contains("https")) {
//            httpurl = "https" + httpurl.substring(4);
//        }
//        String servletPath = httpRequest.getServletPath();
//        request.getServerName();
//        String hostUrl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/jswjw/showNotice/showNoticeDetail.jsp?recordFlow=" + recordFlow;
//        String androidimgurl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/jswjw/showNotice/showNoticeDetail2.jsp?recordFlow=" + recordFlow;
//        resultMap.put("hostUrl", hostUrl);
//        resultMap.put("androidDetailUrl", androidimgurl);
        return resultMap;
    }

    @RequestMapping(value = {"/showZPdetail"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object showZPdetail(String recordFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        ResTarinNotice tarinNotices = resLiveTrainingBiz.readNotice(recordFlow);
        if (tarinNotices != null) {
            resultMap.put("title", "住培指南详情——【" + tarinNotices.getResNoticeTitle() + "】");
            resultMap.put("content", tarinNotices.getResNoticeContent());
        } else {
            resultMap.put("title", "无详细信息");
            resultMap.put("content", "住培指南详情");
        }
        return resultMap;
    }

    @RequestMapping(value = {"/studentSignIn"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object studentSignIn(String userFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (user == null) {
            return ResultDataThrow("用户不存在");
        }
        ResDoctor resDoctor = jswjwBiz.readResDoctor(userFlow);
        if (resDoctor == null) {
            return ResultDataThrow("医师信息不存在");
        }
        String nowDay = DateUtil.getCurrDate();
        List<JsresAttendanceDetail> list = jswjwBiz.getAttendanceDetailList(nowDay, userFlow);
        int count = 0;
        String workingDaySign = com.pinde.core.common.GlobalConstant.FLAG_Y;
        if (list != null && list.size() > 0) {
            count = list.size();
            JsresAttendance attendance = jswjwBiz.getJsresAttendance(nowDay, userFlow);
            if ("-1".equals(attendance.getAttendStatus())) {
                workingDaySign = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
        }
        resultMap.put("count", count);
        resultMap.put("list", list);
        resultMap.put("nowDay", nowDay);
        //告诉app端上一次签到是否为工作日签到，saveSignIn须有app端传workingDaySign值
        resultMap.put("workingDaySign", workingDaySign);
        String timeN[] = nowDay.split("-");
        String timeInfoN = timeN[0] + "年" + timeN[1] + "月" + timeN[2] + "日";
        resultMap.put("chinessNowDay", timeInfoN);
        resultMap.put("resdoctor", resDoctor);
        List<ResOrgAddress> orgAddresses = timeBiz.readOrgAddress(resDoctor.getOrgFlow());
        resultMap.put("orgAddresses", orgAddresses);

        List<ResOrgTime> times = timeBiz.readOrgTime(resDoctor.getOrgFlow());
        resultMap.put("times", times);
        return resultMap;
    }

    @RequestMapping(value = {"/saveSignIn"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveSignIn(String userFlow, String local, String remark, String workingDaySign, String lat, String lng) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(local)) {
            return ResultDataThrow("签到地点为空");
        }
        if (StringUtil.isBlank(workingDaySign)) {
            return ResultDataThrow("未选择是否工作日签到");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (user == null) {
            return ResultDataThrow("用户不存在");
        }
        ResDoctor resDoctor = jswjwBiz.readResDoctor(userFlow);
        if (resDoctor == null) {
            return ResultDataThrow("医师信息不存在");
        }
        List<ResOrgAddress> orgAddresses = timeBiz.readOrgAddress(resDoctor.getOrgFlow());
        //判断是否在签到范围内
        boolean flag = false;
        if (null != orgAddresses && orgAddresses.size() > 0) {
            Double lng1 = Double.valueOf(lng);
            Double lat1 = Double.valueOf(lat);
            for (ResOrgAddress roa : orgAddresses) {
                Double centerLat = Double.valueOf(roa.getLatitude());
                Double centerLng = Double.valueOf(roa.getLongitude());
                Double length = Double.valueOf(roa.getScopeLength());
                boolean inScope = QQMapUtil.isInCircle(lng1, lat1, centerLng, centerLat, length);
                if (inScope) {
                    flag = true;
                    break;
                }
            }
        } else {
            return ResultDataThrow("对不起，你不在规定区域，请重新定位");
        }
        if (!flag) {
            return ResultDataThrow("对不起，你不在规定区域，请重新定位");
        }
        // 优化：江苏西医未使用打卡时间区间功能，判断暂时注释
        /*List<ResOrgTime> times = timeBiz.readOrgTime(resDoctor.getOrgFlow());
        if (times != null && times.size() > 0) {
            boolean f = false;
            for (ResOrgTime t : times) {
                if (t.getStartTime().compareTo(time) <= 0 && t.getEndTime().compareTo(time) >= 0) {
                    f = true;
                }
            }
            if (!f) {
                model.addAttribute("resultId", "3011102");
                model.addAttribute("resultType", "当前时间不在签到时间范围内，无法签到！");
                return "res/jswjw/success";
            }
        }*/
        String nowDay = DateUtil.getCurrDate();
        JsresAttendance attendance = jswjwBiz.getJsresAttendance(nowDay, userFlow);
        String attendanceFlow = PkUtil.getUUID();
        if (attendance != null)
            attendanceFlow = attendance.getAttendanceFlow();
        if (attendance == null) {
            attendance = new JsresAttendance();
            attendance.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            attendance.setAttendanceFlow(attendanceFlow);
            attendance.setDoctorFlow(userFlow);
            attendance.setDoctorName(user.getUserName());
            attendance.setAttendDate(nowDay);
            attendance.setCreateTime(DateUtil.getCurrDateTime());
            attendance.setCreateUserFlow(userFlow);
            attendance.setModifyTime(DateUtil.getCurrDateTime());
            attendance.setModifyUserFlow(userFlow);
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(workingDaySign)) {
                attendance.setAttendStatus("-1");
                attendance.setAttendStatusName("轮休");
            } else {
                attendance.setAttendStatus("1");
                attendance.setAttendStatusName("出勤");
            }
            jswjwBiz.addJsresAttendance(attendance);
        }
        JsresAttendanceDetail detail = new JsresAttendanceDetail();
        detail.setRecordFlow(PkUtil.getUUID());
        detail.setAttendanceFlow(attendanceFlow);
        detail.setAttendDate(nowDay);
        // 禅道bug 学员app考勤签到，修改手机时间后打卡时间应该取服务器时间
        detail.setAttendTime(DateFormatUtils.format(System.currentTimeMillis(), "HH:mm"));
        detail.setAttendLocal(local);
        detail.setSelfRemarks(remark);
        detail.setDoctorFlow(userFlow);
        detail.setDoctorName(user.getUserName());
        detail.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        detail.setCreateTime(DateUtil.getCurrDateTime());
        detail.setCreateUserFlow(userFlow);
        detail.setModifyTime(DateUtil.getCurrDateTime());
        detail.setModifyUserFlow(userFlow);
        int count = 0;
        count = jswjwBiz.addJsresAttendanceDetail(detail);
        if (count != 1) {
            return ResultDataThrow("签到失败");
        }
        return resultMap;
    }

    @RequestMapping(value = {"/qrCode"}, method = {RequestMethod.POST})
    @ResponseBody
    public synchronized Object qrCode(String userFlow, String roleId, String funcTypeId, String codeInfo, String scanTime) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }

        if (StringUtil.isEmpty(funcTypeId)) {
            return ResultDataThrow("功能类型为空");
        }

        if (StringUtil.isNotEquals("qrCode", funcTypeId)) {
            return ResultDataThrow("功能类型错误");
        }
        Map<String, String> paramMap = new HashMap<String, String>();
        transCodeInfo(paramMap, codeInfo);
        String funcFlow = paramMap.get("funcFlow");
        //讲座签到
        if (StringUtil.isEquals(funcFlow, "lectureSignin")) {
            String lectureFlow = paramMap.get("lectureFlow");
            if (StringUtil.isBlank(lectureFlow)) {
                return ResultDataThrow("讲座信息不存在");
            }
            if (StringUtil.isBlank(roleId)) {
                return ResultDataThrow("roleId为空");
            }
            if (!"Student".equals(roleId) && !"Teacher".equals(roleId) && !"Head".equals(roleId) && !"Seretary".equals(roleId)) {
                return ResultDataThrow("roleId标识符不正确");
            }

            SysUser currUser = jswjwBiz.readSysUser(userFlow);
            if (currUser == null) {
                return ResultDataThrow("用户不存在");
            }
            ResDoctor doctor = null;
            String orgFlow = currUser.getOrgFlow();

            if ("Student".equals(roleId)) {
                doctor = jswjwBiz.readResDoctor(currUser.getUserFlow());
                if (doctor == null) {
                    return ResultDataThrow("学员医师信息不存在");
                }
                if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                    orgFlow = doctor.getSecondOrgFlow();
                } else {
                    orgFlow = doctor.getOrgFlow();
                }
            }

            String currDate = DateUtil.getCurrDate();
            // 教学活动增加权限判断
            if ("Student".equals(roleId) && ("activitySigin".equals(funcFlow) || "activityOutSigin".equals(funcFlow))) {
                String isActivity = jswjwBiz.getJsResCfgCodeNew("jsres_doctor_activity_" + userFlow);
                //查询基地是否有时间配置
                SysOrg org = jswjwBiz.getOrg(orgFlow);
                JsresPowerCfg startCfg = jswjwBiz.readPowerCfg("jsres_payPower_startDate_" + orgFlow);
                JsresPowerCfg endCfg = jswjwBiz.readPowerCfg("jsres_payPower_endDate_" + orgFlow);
                if (null != org && !"Passed".equals(org.getCheckStatusId())) {
                    startCfg = null;
                    endCfg = null;
                }

                if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(isActivity)) {
                    return ResultDataThrow("无操作权限，请联系基地管理员！");
                } else {
                    if (null != startCfg && null != endCfg) {
                        if (currDate.compareTo(startCfg.getCfgValue()) < 0 || currDate.compareTo(endCfg.getCfgValue()) > 0) {
                            return ResultDataThrow("无操作权限，请联系基地管理员！");
                        }
                    }
                }
            }

            ResLectureScanRegist lectureScanRegist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
            if (lectureScanRegist == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(lectureScanRegist.getIsRegist())) {
                return ResultDataThrow("您还没有报名，请先报名！");
            }
            ResLectureInfo info = jswjwBiz.read(lectureFlow);
            if (info != null) {
                String signTime = paramMap.get("time");
                String key = "jsres_" + info.getOrgFlow() + "_org_jiangzuo_code_type";
                String cfgv = jswjwBiz.getJsResCfgCode(key);
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfgv)) {
                    if (StringUtil.isBlank(signTime)) {
                        return ResultDataThrow("无效二维码！");
                    }
                    //5秒钟有效
                    String currTime = DateUtil.getCurrDateTime();
                    if (StringUtil.isBlank(scanTime)) {
                        return ResultDataThrow("缺少参数！");
                    }
                    if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 15) {
                        return ResultDataThrow("二维码已失效，请重新扫码！");
                    }
                }
                String key1 = jswjwBiz.getJsResCfgCode("jsres_" + info.getOrgFlow() + "_org_jiangzuo_start_time");
                int startTime = 10;
                if (StringUtil.isNotBlank(key1)) {
                    startTime = Integer.valueOf(key1);
                }
                //扫码报名
                ResLectureScanRegist regist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
                if (regist != null) {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsScan())) {
                        return ResultDataThrow("已经扫过码了！");
                    }
                    String startDate = info.getLectureTrainDate() + " " + info.getLectureStartTime();
                    String endDate = info.getLectureTrainDate() + " " + info.getLectureEndTime();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date date;
                    try {
                        date = simpleDateFormat.parse(startDate);
                        Calendar calender = Calendar.getInstance();
                        calender.setTime(date);
                        calender.add(Calendar.MINUTE, -startTime);
                        startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

                        calender.add(Calendar.MINUTE, startTime * 2);
                        endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
                    } catch (ParseException e) {
                    }
                    String nowDate = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
                    if (nowDate.compareTo(startDate) < 0) {
                        return ResultDataThrow("不在扫码时间范围内，无法扫码！");
                    }
                    if (nowDate.compareTo(endDate) > 0) {
                        return ResultDataThrow("不在扫码时间范围内，无法扫码！");
                    }
                    if (nowDate.compareTo(endDate) > 0) {
                        return ResultDataThrow("讲座已经结束！");
                    }
                    regist.setIsScan(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    regist.setScanTime(DateUtil.getCurrDateTime());
                } else {
                    String startDate = info.getLectureTrainDate() + " " + info.getLectureStartTime();
                    String endDate = info.getLectureTrainDate() + " " + info.getLectureEndTime();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date date;
                    try {
                        date = simpleDateFormat.parse(startDate);
                        Calendar calender = Calendar.getInstance();
                        calender.setTime(date);
                        calender.add(Calendar.MINUTE, -startTime);
                        startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

                        calender.add(Calendar.MINUTE, startTime * 2);
                        endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
                    } catch (ParseException e) {
                    }
                    String nowDate = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
                    if (nowDate.compareTo(startDate) < 0) {
                        return ResultDataThrow("不在扫码时间范围内，无法扫码！");
                    }
                    if (nowDate.compareTo(endDate) > 0) {
                        return ResultDataThrow("不在扫码时间范围内，无法扫码！");
                    }
                    List<ResLectureInfo> infos = jswjwBiz.checkJoinList(lectureFlow, userFlow);
                    if (infos != null && infos.size() > 0) {
                        String msg = "";
                        for (ResLectureInfo resLectureInfo : infos) {
                            msg += "【" + resLectureInfo.getLectureContent() + "】,";
                        }
                        return ResultDataThrow("当前讲座与" + msg + "时间段重叠，请取消重叠时间段讲座！");
                    }
                    regist = new ResLectureScanRegist();
                    regist.setIsScan(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    regist.setScanTime(DateUtil.getCurrDateTime());
                    regist.setLectureFlow(lectureFlow);
                    regist.setOperUserFlow(userFlow);
                    regist.setOperUserName(currUser.getUserName());
                    if (doctor != null) {
                        regist.setTrainingTypeId(doctor.getTrainingTypeId());
                        regist.setTrainingTypeName(doctor.getTrainingTypeName());
                        regist.setTrainingSpeId(doctor.getTrainingSpeId());
                        regist.setTrainingSpeName(doctor.getTrainingSpeName());
                        regist.setSessionNumber(doctor.getSessionNumber());
                    }
                }
                int count = jswjwBiz.scanRegist(regist);
                if (count != 1) {
                    return ResultDataThrow("扫码失败，请稍后再试！");
                }
                resultMap.put("resultType", "签到成功");
                return resultMap;
            } else {
                return ResultDataThrow("二维码已失效！讲座信息不存在！");
            }

//            return resultMap;
        } else if (StringUtil.isEquals(funcFlow, "lectureOutSignin")) {
            String lectureFlow = paramMap.get("lectureFlow");
            if (StringUtil.isBlank(lectureFlow)) {
                return ResultDataThrow("讲座信息不存在！");
            }
            if (StringUtil.isBlank(roleId)) {
                return ResultDataThrow("roleId为空！");
            }
            if (!"Student".equals(roleId) && !"Teacher".equals(roleId) && !"Head".equals(roleId) && !"Seretary".equals(roleId)) {
                return ResultDataThrow("roleId标识符不正确！");
            }

            SysUser currUser = jswjwBiz.readSysUser(userFlow);
            if (currUser == null) {
                return ResultDataThrow("用户不存在！");
            }
            ResDoctor doctor = null;
            if ("Student".equals(roleId)) {
                doctor = jswjwBiz.readResDoctor(currUser.getUserFlow());
                if (doctor == null) {
                    return ResultDataThrow("学员医师信息不存在！");
                }
            }
            ResLectureInfo info = jswjwBiz.read(lectureFlow);
            if (info != null) {

                String signTime = paramMap.get("time");
                String key = "jsres_" + info.getOrgFlow() + "_org_jiangzuo_code_type";
                String cfgv = jswjwBiz.getJsResCfgCode(key);
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfgv)) {
                    if (StringUtil.isBlank(signTime)) {
                        return ResultDataThrow("无效二维码！");
                    }
                    //5秒钟有效
                    if (StringUtil.isBlank(scanTime)) {
                        return ResultDataThrow("缺少参数！");
                    }
                    if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 15) {
                        return ResultDataThrow("二维码已失效，请重新扫码！");
                    }
                }
                String key2 = jswjwBiz.getJsResCfgCode("jsres_" + info.getOrgFlow() + "_org_jiangzuo_end_time");
                //扫码报名
                ResLectureScanRegist regist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
                if (regist != null) {
                    if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsScan())) {
                        return ResultDataThrow("你未签到参加该讲座，无法进行签退！");
                    }
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsScan2())) {
                        return ResultDataThrow("已经扫过码了！");
                    }
                    regist.setIsScan2(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    regist.setScan2Time(DateUtil.getCurrDateTime());
                } else {
                    return ResultDataThrow("你未参加该讲座，无法进行签退！");
                }
                int count = jswjwBiz.scanRegist(regist);
                if (count != 1) {
                    return ResultDataThrow("扫码失败，请稍后再试！");
                }
                resultMap.put("resultType", "签退成功");
                return resultMap;
            } else {
                return ResultDataThrow("二维码已失效！讲座信息不存在！");
            }

        } else if (StringUtil.isEquals(funcFlow, "queryQrCode")) {//技能考核签到
            String recordFlow = paramMap.get("recordFlow");
            if (StringUtil.isNotBlank(recordFlow)) {
                return ResultDataThrow("无效二维码！");
            }
            String clinicalFlow = paramMap.get("clinicalFlow");
            if (StringUtil.isBlank(clinicalFlow)) {
                return ResultDataThrow("技能考核信息无效！");
            }
            OscaSkillsAssessment skillsAssessment = oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
            if (skillsAssessment == null) {
                return ResultDataThrow("技能考核信息不存在！");
            }
            //查询预约信息
            OscaDoctorAssessment oscaDoctorAssessment = jswjwStudentBiz.getOscaDoctorAssessment(userFlow, clinicalFlow);
            if (oscaDoctorAssessment == null) {
                return ResultDataThrow("你未预约该场考核！");
            }
            if (!AuditStatusEnum.Passed.getId().equals(oscaDoctorAssessment.getAuditStatusId())) {
                return ResultDataThrow("你的预约信息暂未审核通过！");
            }
            if (SignStatusEnum.SignIn.getId().equals(oscaDoctorAssessment.getSiginStatusId())) {
                return ResultDataThrow("你已签到成功！");
            }
            oscaDoctorAssessment.setSiginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            oscaDoctorAssessment.setSiginStatusId(SignStatusEnum.SignIn.getId());
            oscaDoctorAssessment.setSiginStatusName(SignStatusEnum.SignIn.getName());
            if (StringUtil.isNotBlank(oscaDoctorAssessment.getIsPass()) && DoctorScoreEnum.Miss.getId().equals(oscaDoctorAssessment.getIsPass())) {
                oscaDoctorAssessment.setIsPass(DoctorScoreEnum.PendingEnter.getId());
                oscaDoctorAssessment.setIsPassName(DoctorScoreEnum.PendingEnter.getName());
            }
            SysUser user = jswjwBiz.readSysUser(userFlow);

            List<OscaSubjectStation> stations = oscaAppBiz.getOscaSubjectStations(skillsAssessment.getSubjectFlow());
            List<OscaSkillDocStation> docStations = new ArrayList<>();
            for (OscaSubjectStation station : stations) {
                OscaSkillDocStation docStation = oscaAppBiz.getDocSkillStation(station.getStationFlow(), user.getUserFlow(), clinicalFlow);
                if (docStation == null)
                    docStation = new OscaSkillDocStation();
                if (!com.pinde.core.common.enums.ExamStatusEnum.AssessIng.getId().equals(docStation.getExamStatusId()) &&
                        !com.pinde.core.common.enums.ExamStatusEnum.Assessment.getId().equals(docStation.getExamStatusId())) {
                    docStation.setClinicalFlow(clinicalFlow);
                    docStation.setClinicalName(skillsAssessment.getClinicalName());
                    docStation.setStationFlow(station.getStationFlow());
                    docStation.setStationName(station.getStationName());
                    docStation.setDoctorFlow(userFlow);
                    docStation.setDoctorName(user.getUserName());
                    String date = DateUtil.getCurrDateTime2();
                    docStation.setHoukaoTime(date);
                    docStation.setWaitingTime(date);
                    docStation.setExamStatusId(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getId());
                    docStation.setExamStatusName(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getName());
                    docStation.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    docStations.add(docStation);
                }
            }
            oscaAppBiz.saveDocSkillStations(docStations, user);

            return resultMap;
        } else if (StringUtil.isEquals(funcFlow, "activitySigin")) {//教学活动签到

            String signTime = paramMap.get("time");
            String activityFlow = paramMap.get("activityFlow");

            if (StringUtil.isBlank(activityFlow)) {
                return ResultDataThrow("二维码错误！");
            }
            TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
            if (info == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus())) {
                return ResultDataThrow("活动信息不存在！");
            }
            //活动不是已通过、不能扫码yuh20200518
            if (!"pass".equals(info.getActivityStatus())) {
                return ResultDataThrow("此活动未被审核！");
            }
            String key = "jsres_" + info.getOrgFlow() + "_org_activity_code_type";
            String cfgv = jswjwBiz.getJsResCfgCode(key);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfgv)) {
                if (StringUtil.isBlank(signTime)) {
                    return ResultDataThrow("无效二维码！");
                }
                //5秒钟有效
                if (StringUtil.isBlank(scanTime)) {
                    return ResultDataThrow("缺少参数！");
                }
                if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 15) {
                    return ResultDataThrow("二维码已失效，请重新扫码！");
                }
            }
            String key1 = jswjwBiz.getJsResCfgCode("jsres_" + info.getOrgFlow() + "_org_activity_start_time");
            String end = jswjwBiz.getJsResCfgCode("jsres_" + info.getOrgFlow() + "_org_activity_end_time");
            int startTime = 10;
            int endTime = 10;
            if (StringUtil.isNotBlank(key1)) {
                startTime = Integer.parseInt(key1);
            }
            if (StringUtil.isNotBlank(end)) {
                endTime = Integer.parseInt(end);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date;
            String startDate = info.getStartTime();
            String startDateAfter = info.getStartTime();
            String endDate = info.getEndTime();
            try {
                date = simpleDateFormat.parse(info.getStartTime());
                Calendar calender = Calendar.getInstance();
                calender.setTime(date);
                calender.add(Calendar.MINUTE, -startTime);

                startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
                calender.add(Calendar.MINUTE, startTime * 2);
                startDateAfter = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

                date = simpleDateFormat.parse(info.getEndTime());
                calender.setTime(date);
                calender.add(Calendar.MINUTE, endTime);
                endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
            } catch (ParseException e) {
                startDate = info.getStartTime();
                endDate = info.getEndTime();
            }
            if (DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(startDate) < 0) {
                return ResultDataThrow("活动暂未开始，无法参加！");
            }
            if (DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(endDate) > 0) {
                return ResultDataThrow("活动已结束，无法参加！");
            }
            int count = activityBiz.checkJoin2(activityFlow, userFlow);
            if (count > 0) {
                return ResultDataThrow("该时间段，你已报名参加其他教学活动，无法参加！");
            }
            ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
            if (doctor == null) {
                return ResultDataThrow("医师信息不存在！");
            }
            String orgFlow = "";
            if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                orgFlow = doctor.getSecondOrgFlow();
            } else {
                orgFlow = doctor.getOrgFlow();
            }
            Map<String, String> param = new HashMap<>();
            param.put("roleFlag", "doctor");
            param.put("userFlow", userFlow);
            param.put("infoOrgFlow", info.getOrgFlow());
            param.put("orgFlow", orgFlow);
            param.put("deptFlow", info.getDeptFlow());
//            List<Map<String, Object>> list = activityBiz.findActivityList(param);
//            if (list == null || list.size() <= 0) {
//                return ResultDataThrow("该教学活动你无权限参加！");
//            }


            // 获取当前时间
            String currentDateTime = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");

           // 检查当前时间是否在 startDate 和 info.getStartTime() 之间
            boolean isAfterOrEqualStartDate = currentDateTime.compareTo(startDate) > 0;
            boolean isBeforeOrEqualStartTime = currentDateTime.compareTo(startDateAfter) < 0;

            if (!isAfterOrEqualStartDate || !isBeforeOrEqualStartTime) {
                return ResultDataThrow("未在时间范围内签到！");
            }

            TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
            if (result != null) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsScan())) {
                    return ResultDataThrow("你已扫码签到成功！");
                }
                result.setIsScan(com.pinde.core.common.GlobalConstant.FLAG_Y);
                result.setScanTime(DateUtil.getCurrDateTime());

                int c = activityBiz.saveResult(result, userFlow);
                if (c == 0) {
                    return ResultDataThrow("签到失败！");
                }
            } else {
                List<TeachingActivityInfo> infos = activityBiz.checkJoinList(activityFlow, userFlow);
                if (infos != null && infos.size() > 0) {
                    String msg = "";
                    for (TeachingActivityInfo activityInfo : infos) {
                        msg += "【" + activityInfo.getActivityName() + "】,";
                    }
                    return ResultDataThrow("当前活动与" + msg + "时间段重叠，请取消重叠时间段的活动！");
                }
                result = new TeachingActivityResult();
                result.setIsScan(com.pinde.core.common.GlobalConstant.FLAG_Y);
                result.setScanTime(DateUtil.getCurrDateTime());
                result.setActivityFlow(activityFlow);
                result.setUserFlow(userFlow);
                result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                int c = activityBiz.saveResult(result, userFlow);
                if (c == 0) {
                    return ResultDataThrow("签到失败！");
                }
            }
            resultMap.put("resultType", "签到成功");
            return resultMap;
        } else if (StringUtil.isEquals(funcFlow, "activityOutSigin")) {//教学活动签退

            String activityFlow = paramMap.get("activityFlow");
            if (StringUtil.isBlank(activityFlow)) {
                return ResultDataThrow("二维码错误！");
            }
            TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
            if (info == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus())) {
                return ResultDataThrow("活动信息不存在！");
            }
            String signTime = paramMap.get("time");
            String key = "jsres_" + info.getOrgFlow() + "_org_activity_code_type";
            String cfgv = jswjwBiz.getJsResCfgCode(key);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfgv)) {
                if (StringUtil.isBlank(signTime)) {
                    return ResultDataThrow("无效二维码！");
                }
                //5秒钟有效
                if (StringUtil.isBlank(scanTime)) {
                    return ResultDataThrow("缺少参数！");
                }
                if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 15) {
                    return ResultDataThrow("二维码已失效，请重新扫码！");
                }
            }
            String key2 = jswjwBiz.getJsResCfgCode("jsres_" + info.getOrgFlow() + "_org_activity_end_time");
            int endTime = 10;
            if (StringUtil.isNotBlank(key2)) {
                endTime = Integer.valueOf(key2);
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date;
            String startDate = info.getEndTime();
            String endDate = info.getEndTime();
            try {
                date = simpleDateFormat.parse(info.getEndTime());
                Calendar calender = Calendar.getInstance();
                calender.setTime(date);
                calender.add(Calendar.MINUTE, -endTime);
                startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
                // 教学活动配置的结束后的一段时间
                calender.add(Calendar.MINUTE, endTime * 2);
                endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
            } catch (ParseException e) {
                startDate = info.getEndTime();
                endDate = info.getEndTime();
            }

            TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
            if (result == null) {
                return ResultDataThrow("你未参加该活动，无法进行签退！");
            }
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsScan())) {
                return ResultDataThrow("你未签到参加该活动，无法进行签退！");
            }
            String nowDate = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
            if (nowDate.compareTo(startDate) < 0) {
                return ResultDataThrow("活动签退暂未开始，无法签退！");
            }
            if (nowDate.compareTo(endDate) > 0) {
                return ResultDataThrow("活动签退已结束，无法签退！");
            }

            String currentDateTime = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");

            boolean isAfterOrEqualEndDate = currentDateTime.compareTo(startDate) > 0;
            boolean isBeforeOrEqualEndTime = currentDateTime.compareTo(endDate) < 0;
            // 检查 scanTime 是否在 startDate 和 info.getEndTime() 之间
            if (!isAfterOrEqualEndDate || !isBeforeOrEqualEndTime) {
                return ResultDataThrow("未在时间范围内签退！");
            }
            
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsScan2())) {
                return ResultDataThrow("你已扫码签退成功！");
            }
            result.setIsEffective(com.pinde.core.common.GlobalConstant.FLAG_Y);
            result.setIsScan2(com.pinde.core.common.GlobalConstant.FLAG_Y);
            result.setScanTime2(DateUtil.getCurrDateTime());
            int c = activityBiz.saveResult(result, userFlow);
            if (c == 0) {
                return ResultDataThrow("签退失败！");
            }
            resultMap.put("resultType", "签退成功");
            return resultMap;
        } else if (StringUtil.isEquals(funcFlow, "osceDocJoinTea")) {//学员扫码考官

            String teaFlow = paramMap.get("teaFlow");
            if (StringUtil.isBlank(teaFlow)) {
                return ResultDataThrow("二维码错误！");
            }

            OscaDoctorAssessment doctorAssessment = jswjwStudentBiz.getAuditOscaDocInfo(userFlow);
            //如果有查询有几站并且查询有哪些房间及相应信息
            if (doctorAssessment == null) {
                return ResultDataThrow("你暂未预约考核信息，无法参加考核！");
            }

            String clinicalFlow = doctorAssessment.getClinicalFlow();
            String doctorFlow = userFlow;
            paramMap.put("doctorFlow", doctorFlow);
            paramMap.put("clinicalFlow", clinicalFlow);
            paramMap.put("userFlow", teaFlow);
            paramMap.put("roleId", roleId);
            if (StringUtil.isNotBlank(clinicalFlow) && StringUtil.isNotBlank(doctorFlow)) {
                //考核信息
                OscaSkillsAssessment skillsAssessment = oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
                if (skillsAssessment == null) {
                    return ResultDataThrow("考核信息不存在！");
                }
                String sigin = doctorAssessment.getSiginStatusId();
                if (StringUtil.isBlank(sigin) || SignStatusEnum.NoSignIn.getId().equals(sigin)) {
                    return ResultDataThrow("该考生尚未考核签到，无法进行考核，请学员联系管理员签到！");
                }
                //学员信息
                SysUser docUser = oscaAppBiz.readSysUser(doctorFlow);
                if (docUser == null) {
                    return ResultDataThrow("用户信息不存在！");
                }
                ResDoctor doctor = oscaAppBiz.readResDoctor(doctorFlow);
                if (doctor == null) {
                    return ResultDataThrow("医师信息不存在！");
                }
                if (StringUtil.isBlank(doctorAssessment.getExamStartTime()) || StringUtil.isBlank(doctorAssessment.getExamEndTime())) {
                    return ResultDataThrow("管理员未维护考核时间！");
                }
                //查询当前考官可以考核学员哪些站点
                List<OscaSubjectStation> stations = oscaAppBiz.getTeaDocStation(paramMap);
                if (stations == null || stations.size() <= 0) {
                    return ResultDataThrow("你不在该考官负责的考核范围内，无法参加考核哦，换一个考官扫一扫吧~");
                }
                //先获取考官是否已经扫过学员的二维码了
                OscaTeaScanDoc b = oscaAppBiz.getOscaTeaScanDoc(paramMap);
                if (b != null) {
                    return ResultDataThrow("你扫过码了，请等待考核！");
                }
                //记录在哪个考场扫码的
                String examRoomFlow = "";
                for (int i = 0; i < stations.size(); i++) {
                    OscaSubjectStation s = stations.get(i);
                    Map<String, Object> bean = new HashMap<>();
                    //查询当前站点考官可以考核学员的考场
                    Map<String, String> param = new HashMap<>();
                    param.put("userFlow", teaFlow);
                    param.put("doctorFlow", doctorFlow);
                    param.put("stationFlow", s.getStationFlow());
                    param.put("clinicalFlow", clinicalFlow);
                    //该站点考官可以考核的考场
                    List<OscaSkillRoomTea> roomTeas = oscaAppBiz.getTeaRooms(param);
                    OscaSkillRoomTea roomTea = null;
                    if (roomTeas != null && roomTeas.size() > 0) {
                        roomTea = roomTeas.get(0);
                    }
                    String roomRecordFlow = roomTea.getRoomRecordFlow();
                    bean.put("roomRecordFlow", roomRecordFlow);
                    bean.put("roomFlow", roomTea.getRoomFlow());
                    bean.put("roomName", roomTea.getRoomName());
                    bean.put("examStatusId", com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getId());
                    bean.put("examStatusName", com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getName());
                    if (i == 0) {
                        examRoomFlow = roomRecordFlow;
                    }
                    //当前站点信息学员排队的考场
                    OscaSkillRoomDoc docStation = oscaAppBiz.getOscaSkillRoomDocByDoc(param);
                    if (docStation != null) {
                        roomRecordFlow = docStation.getRoomRecordFlow();
                        if (com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId().equals(docStation.getExamStatusId())) {
                            examRoomFlow = roomRecordFlow;
                        }
                    }
                }
                //看看考场
                OscaSkillRoom room = oscaAppBiz.getOscaskillRoomByFlow(examRoomFlow);
                //添加考官扫码学员的信息
                SysUser user = oscaAppBiz.readSysUser(teaFlow);
                b = new OscaTeaScanDoc();
                b.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                b.setClinicalFlow(clinicalFlow);
                b.setClinicalName(skillsAssessment.getClinicalName());
                b.setDoctorFlow(doctorFlow);
                b.setDoctorName(docUser.getUserName());
                b.setExamTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                b.setRoomRecordFlow(examRoomFlow);
                b.setRoomFlow(room.getRoomFlow());
                b.setRoomName(room.getRoomName());
                if (StringUtil.isBlank(b.getStatusId())) {
                    b.setStatusId(ScanDocStatusEnum.StayAssessment.getId());
                    b.setStatusName(ScanDocStatusEnum.StayAssessment.getName());
                }
                b.setPartnerFlow(teaFlow);
                b.setPartnerName(user.getUserName());
                b.setCodeInfo(doctorAssessment.getCodeInfo());//保存学员二维码信息
                int num = oscaAppBiz.editTeaScanDoc(b, user);

                return resultMap;
            } else {
                return ResultDataThrow("无效二维码！");
            }
        } else if (StringUtil.isEquals(funcFlow, "randomSignIn")) {//讲座随机签到
            String randomFlow = paramMap.get("randomFlow");
            if (StringUtil.isBlank(randomFlow)) {
                return ResultDataThrow("无效二维码！");
            }
            ResLectureRandomSign sign = jswjwBiz.readLectureRandomSign(randomFlow);
            if (sign == null) {
                return ResultDataThrow("随机签到信息不存在！");
            }
            ResLectureInfo lectureInfo = jswjwBiz.read(sign.getLectureFlow());
            if (lectureInfo == null) {
                return ResultDataThrow("讲座信息不存在！");
            }
            String nowDate = DateUtil.getCurrDate();
            if (!nowDate.equals(lectureInfo.getLectureTrainDate())) {
                return ResultDataThrow("讲座日期不是当前日期！");
            }
            //学员信息
            SysUser docUser = oscaAppBiz.readSysUser(userFlow);
            if (docUser == null) {
                return ResultDataThrow("用户信息不存在！");
            }
            ResDoctor doctor = oscaAppBiz.readResDoctor(userFlow);
			/*if(doctor==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "医师信息不存在");
				return "res/jswjw/qrCode";
			}*/
            ResLectureScanRegist regist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, sign.getLectureFlow());
            if (regist == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsScan())) {
                return ResultDataThrow("讲座未签到无法进行随机签到！");
            }
            ResLectureRandomScan scan = jswjwBiz.readLectureRandomScan(userFlow, randomFlow);
            if (scan != null) {
                return ResultDataThrow("此次随机签到，你已完成！");
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(sign.getCodeStatusType())) {
                String signTime = paramMap.get("time");
                if (StringUtil.isBlank(signTime)) {
                    return ResultDataThrow("无效二维码！");
                }
                //5秒钟有效
                if (StringUtil.isBlank(scanTime)) {
                    return ResultDataThrow("缺少参数！");
                }
                if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 15) {
                    return ResultDataThrow("二维码已失效，请重新扫码！");
                }
            }
            String nowTime = DateUtil.transDateTime(scanTime, "yyyyMMddHHmmss", "HH:mm:ss");
            if (nowTime.compareTo(sign.getCodeStartTime()) < 0 || nowTime.compareTo(sign.getCodeEndTime()) > 0) {
                return ResultDataThrow("扫码时间不在签到时间范围内！");
            }
            scan = new ResLectureRandomScan();
            scan.setIsScan(com.pinde.core.common.GlobalConstant.FLAG_Y);
            scan.setScanTime(DateUtil.getCurrDateTime());
            scan.setRandomFlow(randomFlow);
            scan.setLectureFlow(lectureInfo.getLectureFlow());
            scan.setOperUserFlow(userFlow);
            scan.setCreateUserFlow(userFlow);
            scan.setCreateTime(DateUtil.getCurrDateTime());
            scan.setModifyUserFlow(userFlow);
            scan.setModifyTime(DateUtil.getCurrDateTime());
            scan.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            if (doctor != null) {
                String session = doctor.getSessionNumber();
                String trainingTypeId = doctor.getTrainingTypeId();
                String trainingTypeName = doctor.getTrainingTypeName();
                String trainingSpeId = doctor.getTrainingSpeId();
                String trainingSpeName = doctor.getTrainingSpeName();
                if (StringUtil.isNotBlank(session)) {
                    scan.setSessionNumber(session);
                }
                if (StringUtil.isNotBlank(trainingTypeId)) {
                    scan.setTrainingTypeId(trainingTypeId);
                }
                if (StringUtil.isNotBlank(trainingTypeName)) {
                    scan.setTrainingTypeName(trainingTypeName);
                }
                if (StringUtil.isNotBlank(trainingSpeId)) {
                    scan.setTrainingSpeId(trainingSpeId);
                }
                if (StringUtil.isNotBlank(trainingSpeName)) {
                    scan.setTrainingSpeName(trainingSpeName);
                }
            }
            int c = jswjwBiz.saveLectureRandomScan(scan);
            if (c == 0) {
                return ResultDataThrow("随机签到失败！");
            }
            return resultMap;
        } else {
            return ResultDataThrow("无效二维码！");
        }
    }

    //解析二维码字符串为map
    private void transCodeInfo(Map<String, String> paramMap, String codeInfo) {
        String[] params = StringUtil.split(codeInfo, "&");
        for (String paramStr : params) {
            if (paramStr.indexOf("=") > 0) {
                String key = paramStr.substring(0, paramStr.indexOf("="));
                String value = paramStr.substring(paramStr.indexOf("=") + 1, paramStr.length());
                paramMap.put(key, value);
            }
        }
    }

    /**
     * 获取指定HTML标签的指定属性的值
     *
     * @param source  要匹配的源文本
     * @param element 标签名称
     * @param attr    标签的属性名称
     * @return 属性值列表
     */
    public static List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?(\\s.*?)?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }

    /**
     * 获取指定的HTML标签及内容
     *
     * @param source
     * @param elemnt
     * @return
     */
    public static List<Map<String, String>> getAllElements(String source, String elemnt) {
        List<Map<String, String>> result = null;
        if (StringUtil.isNotBlank(source)) {
            result = new ArrayList<>();
            Pattern p = Pattern.compile("<" + elemnt + "[^>]*>([^<]*)</" + elemnt + ">");
            Matcher m = p.matcher(source);
            Map<String, String> map = new HashMap<>();
            while (m.find()) {
                map = new HashMap<>();
                map.put("source", m.group());
                map.put("innerHtml", m.group(1));
                result.add(map);
            }
        }
        return result;
    }


    @RequestMapping(value = {"/osca"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object osca(String userFlow, String roleId, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }

        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色为空");
        }
        if (!roleId.equals("Student")) {
            return ResultDataThrow("用户与角色不符");
        }
        //查询是否是有成功  预约考核信息
        OscaDoctorAssessment oscaDoctorAssessment = jswjwStudentBiz.getAuditOscaDocInfo(userFlow);
        resultMap.put("oscaDoctorAssessment", oscaDoctorAssessment);
        String canClick = com.pinde.core.common.GlobalConstant.FLAG_Y;
        //如果有查询有几站并且查询有哪些房间及相应信息
        if (oscaDoctorAssessment != null) {
            OscaSkillsAssessment oscaSkillsAssessment = jswjwStudentBiz.getOscaSkillsAssessmentByFlow(oscaDoctorAssessment.getClinicalFlow());
            resultMap.put("oscaSkillsAssessment", oscaSkillsAssessment);
            if (oscaSkillsAssessment == null) {
                return ResultDataThrow("考核信息不存在");
            }
            String sigin = oscaDoctorAssessment.getSiginStatusId();
            if (StringUtil.isBlank(sigin) || SignStatusEnum.NoSignIn.getId().equals(sigin)) {
                return ResultDataThrow("技能考核暂未签到，无法查看考核信息");
            }
            OscaSubjectMain main = jswjwStudentBiz.getOscaSubjectMain(oscaSkillsAssessment.getSubjectFlow());
            resultMap.put("main", main);
            List<Map<String, Object>> resultMapList = new ArrayList<>();
            List<OscaSubjectStation> stations = jswjwStudentBiz.getOscaSubjectStations(oscaSkillsAssessment.getSubjectFlow());
            int order = 1;
            if (stations != null && stations.size() > 0) {
//                resultMap.put("stations", stations);
//                Map<String, Object> stationRoomMap = new HashMap<>();
                for (OscaSubjectStation s : stations) {
                    String key = s.getStationFlow();

                    Map<String, Object> map = new HashMap<>();
                    map.put("stationFlow", key);
                    map.put("stationName", s.getStationName());
                    map.put("clinicalFlow", oscaSkillsAssessment.getClinicalFlow());
                    map.put("clinicalName", oscaSkillsAssessment.getClinicalName());
                    map.put("subjectFlow", s.getSubjectFlow());
                    map.put("subjectName", s.getSubjectName());
                    map.put("examinedContent", s.getExaminedContent());
                    map.put("stationScore", s.getStationScore());
                    map.put("order", order);
                    order++;

//                    Map<String, Object> roomMap = new HashMap<>();
                    //查看站点下是否有考场
                    List<OscaSkillRoom> rooms = jswjwStudentBiz.getRooms(s.getStationFlow(), oscaSkillsAssessment.getClinicalFlow());
//                    roomMap.put("rooms", rooms);
//                    roomMap.put("roomSize", rooms.size());
                    if (rooms != null && rooms.size() > 0) {
                        //获取学员排队或已经考核的考场相应信息
                        OscaSkillRoomExt roomExt = jswjwStudentBiz.getDocRoom(userFlow, s.getStationFlow(), oscaSkillsAssessment.getClinicalFlow());
                        if (roomExt == null) {
                            //站点考场排队人数最少的一个考场
                            roomExt = jswjwStudentBiz.getStationBestRoom(s.getStationFlow(), oscaSkillsAssessment.getClinicalFlow());
                        } else {
                            if (roomExt.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId()) || roomExt.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.AssessIng.getId())) {
                                canClick = com.pinde.core.common.GlobalConstant.FLAG_N;
                            }
                        }
//                        roomMap.put("roomExt", roomExt);
                        map.put("roomRecordFlow", roomExt.getRecordFlow());
                        map.put("roomFlow", roomExt.getRoomFlow());
                        map.put("roomName", roomExt.getRoomName());
                        map.put("peopleCount", roomExt.getPeopleCount());
                        map.put("waitingCount", roomExt.getWaitingCount());
                        map.put("examStatusId", roomExt.getExamStatusId());
                        map.put("examStatusName", roomExt.getExamStatusName());
                        map.put("examScore", roomExt.getExamScore());
                        map.put("showBtn", com.pinde.core.common.GlobalConstant.FLAG_N);
                        map.put("canClick", canClick);
                    }
//                    stationRoomMap.put(key, roomMap);
                    map.put("roomSize", rooms.size());
                    resultMapList.add(map);
                }
//                resultMap.put("stationRoomMap", stationRoomMap);
                resultMap.put("resultMapList", resultMapList);
            }
        } else {
            return ResultDataThrow("暂无考核信息");
        }
//        resultMap.put("canClick", canClick);
        return resultMap;
    }

    @RequestMapping(value = {"/lineUp"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object lineUp(String userFlow, String roleId, String roomRecordFlow,
                         String clinicalFlow, String stationFlow, String waitingFlag,
                         HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }

        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色为空");
        }
        if (StringUtil.isBlank(roomRecordFlow)) {
            return ResultDataThrow("考场流水号为空");
        }
        if (StringUtil.isBlank(clinicalFlow)) {
            return ResultDataThrow("考核信息流水号为空");
        }
        if (StringUtil.isBlank(stationFlow)) {
            return ResultDataThrow("站点流水号为空");
        }
        if (StringUtil.isBlank(waitingFlag)) {
            return ResultDataThrow("排队标识符为空");
        }
        if (!waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) && !waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
            return ResultDataThrow("排队状态只能是排队或取消排队");
        }
        if (!roleId.equals("Student")) {
            return ResultDataThrow("用户与角色不符");
        }
        //校验是否已经在其他站排队
        Map<String, Object> param = new HashMap<>();
        param.put("userFlow", userFlow);
        param.put("roomRecordFlow", roomRecordFlow);
        param.put("clinicalFlow", clinicalFlow);
        param.put("stationFlow", stationFlow);
        if (waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
            //校验是否已经在排队 或正在考核中
            int count = jswjwStudentBiz.checkIsWait(param);
            if (count > 0) {
                return ResultDataThrow("你正在其他站点或考场排队，请取消后再进行排队！");
            }
        } else {
            //校验当前站点是否正在考核或已考核
            int count = jswjwStudentBiz.checkIsAssess(param);
            if (count > 0) {
                return ResultDataThrow("当前站点正在考核或已考核，不得取消排队！");
            }
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        //考场信息
        OscaSkillRoom room = jswjwStudentBiz.getRoomByFlow(roomRecordFlow);
        //考核信息
        OscaSkillsAssessment skillsAssessment = jswjwStudentBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
        //站点信息
        OscaSubjectStation station = jswjwStudentBiz.getOscaSubjectStationsByFlow(stationFlow);
        //当前站点信息
        OscaSkillRoomDoc docStation = jswjwStudentBiz.getOscaSkillRoomDocByDoc(param);
        if (docStation == null) {
            docStation = new OscaSkillRoomDoc();
            docStation.setDoctorFlow(userFlow);
            docStation.setDoctorName(user.getUserName());
        }
        if (room != null) {
            docStation.setRoomRecordFlow(room.getRecordFlow());
            docStation.setRoomFlow(room.getRoomFlow());
            docStation.setRoomName(room.getRoomName());
        } else {
            return ResultDataThrow("读取考场信息失败！");
        }
        if (skillsAssessment != null) {
            docStation.setClinicalFlow(skillsAssessment.getClinicalFlow());
            docStation.setClinicalName(skillsAssessment.getClinicalName());
        } else {
            return ResultDataThrow("读取考核信息失败！");
        }
        if (station != null) {
            docStation.setStationFlow(station.getStationFlow());
            docStation.setStationName(station.getStationName());
        } else {
            return ResultDataThrow("读取站点信息失败！");
        }
        if (waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
            docStation.setExamStatusId(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId());
            docStation.setExamStatusName(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getName());
            docStation.setWaitingTime(DateUtil.getCurrentTime());
        } else {
            docStation.setExamStatusId(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getId());
            docStation.setExamStatusName(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getName());
            docStation.setWaitingTime("");
        }
        int count = jswjwStudentBiz.updateOscaSkillRoomDoc(docStation, user);
        if (count == 0) {
            return ResultDataThrow("操作失败！");
        }
        return resultMap;
    }

    @RequestMapping(value = {"/stationRooms"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object stationRooms(String userFlow, String roleId, String clinicalFlow, String stationFlow, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }

        if (StringUtil.isBlank(roleId)) {
            return ResultDataThrow("用户角色为空");
        }
        if (StringUtil.isBlank(clinicalFlow)) {
            return ResultDataThrow("考核信息流水号为空");
        }
        if (StringUtil.isBlank(stationFlow)) {
            return ResultDataThrow("站点流水号为空");
        }
        if (!roleId.equals("Student")) {
            return ResultDataThrow("用户与角色不符");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("userFlow", userFlow);
        param.put("clinicalFlow", clinicalFlow);
        param.put("stationFlow", stationFlow);
        //当前站点信息 是否有考核或排阶段或已考核的考场信息
        //获取学员排队或已经考核的考场相应信息
        OscaSkillRoomExt roomExt = jswjwStudentBiz.getDocRoom(userFlow, stationFlow, clinicalFlow);
        OscaSubjectStation station = jswjwStudentBiz.getOscaSubjectStationsByFlow(stationFlow);
//        resultMap.put("station", station);
        List<OscaSkillRoomDoc> skillRoomDocs = jswjwStudentBiz.getDocAllStation(param);
        String canClick = com.pinde.core.common.GlobalConstant.FLAG_Y;
        if (skillRoomDocs != null) {
            for (OscaSkillRoomDoc rd : skillRoomDocs) {
                if (rd.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId()) || rd.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.AssessIng.getId())) {
                    canClick = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
        }
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        Map<String, Object> map = map = new HashMap<>();
        String roomRecordFlow = "";
        if (roomExt != null) {
            roomRecordFlow = roomExt.getRecordFlow();
            resultMap.put("isSelect", com.pinde.core.common.GlobalConstant.FLAG_Y);

            map.put("stationFlow", roomExt.getStationFlow());
            map.put("stationName", roomExt.getStationName());
            map.put("subjectFlow", station.getSubjectFlow());
            map.put("subjectName", station.getSubjectName());
            map.put("examinedContent", station.getExaminedContent());
            map.put("stationScore", station.getStationScore());
            map.put("roomRecordFlow", roomExt.getRecordFlow());
            map.put("roomFlow", roomExt.getRoomFlow());
            map.put("roomName", roomExt.getRoomName());
            map.put("peopleCount", roomExt.getPeopleCount());
            map.put("waitingCount", roomExt.getWaitingCount());
            map.put("examStatusId", roomExt.getExamStatusId());
            map.put("examStatusName", roomExt.getExamStatusName());
            map.put("examScore", roomExt.getExamScore());
            map.put("showBtn", com.pinde.core.common.GlobalConstant.FLAG_N);
            map.put("canClick", canClick);
        }
//        resultMap.put("canClick", canClick);
        param.put("roomRecordFlow", roomRecordFlow);
        //考核信息
        OscaSkillsAssessment skillsAssessment = jswjwStudentBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
//        resultMap.put("skillsAssessment", skillsAssessment);
        map.put("clinicalFlow", skillsAssessment.getClinicalFlow());
        map.put("clinicalName", skillsAssessment.getClinicalName());
        resultMapList.add(map);

        List<OscaSkillRoomExt> list = jswjwStudentBiz.getStationAllRoom(param);
//        resultMap.put("list", list);
//        resultMap.put("roomExt", roomExt);

        if (null != list && list.size() > 0) {
            for (OscaSkillRoomExt ext : list) {
                map = new HashMap<>();
                map.put("clinicalFlow", skillsAssessment.getClinicalFlow());
                map.put("clinicalName", skillsAssessment.getClinicalName());
                map.put("stationFlow", ext.getStationFlow());
                map.put("stationName", ext.getStationName());
                map.put("subjectFlow", station.getSubjectFlow());
                map.put("subjectName", station.getSubjectName());
                map.put("examinedContent", station.getExaminedContent());
                map.put("stationScore", station.getStationScore());
                map.put("roomRecordFlow", ext.getRecordFlow());
                map.put("roomFlow", ext.getRoomFlow());
                map.put("roomName", ext.getRoomName());
                map.put("peopleCount", ext.getPeopleCount());
                map.put("waitingCount", ext.getWaitingCount());
                map.put("examStatusId", ext.getExamStatusId());
                map.put("examStatusName", ext.getExamStatusName());
                map.put("examScore", ext.getExamScore());
                map.put("showBtn", com.pinde.core.common.GlobalConstant.FLAG_N);
                map.put("canClick", canClick);
                resultMapList.add(map);
            }
        }
        resultMap.put("resultMapList", resultMapList);
        return resultMap;
    }

    @RequestMapping(value = {"/addPaperOrPart"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object addPaperOrPart(String userFlow, String type, String recordFlow, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }

        if (StringUtil.isBlank(type)) {
            return ResultDataThrow("添加数据类型为空");
        }
        if (!type.equals("Paper") && !type.equals("Part")) {
            return ResultDataThrow("数据类型选择错误");
        }
        if (type.equals("Paper") && StringUtil.isNotBlank(recordFlow)) {
            JsresDoctorPaper paper = jswjwStudentBiz.readJsresDoctorPaperByFlow(recordFlow);
            resultMap.put("paper", paper);
        }
        if (type.equals("Part") && StringUtil.isNotBlank(recordFlow)) {
            JsresDoctorParticipation paper = jswjwStudentBiz.readJsresDoctorJsresDoctorParticipationByFlow(recordFlow);
            resultMap.put("paper", paper);

        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        resultMap.put("doctor", doctor);
        return resultMap;
    }

    @RequestMapping(value = {"/savePaperOrPart"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object savePaperOrPart(String userFlow, String type, String recordFlow, JsresDoctorPaper paper, JsresDoctorParticipation part,
                                  HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }

        if (StringUtil.isBlank(type)) {
            return ResultDataThrow("添加数据类型为空");
        }
        if (!type.equals("Paper") && !type.equals("Part")) {
            return ResultDataThrow("数据类型选择错误");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (type.equals("Paper")) {
            if (paper == null) {
                return ResultDataThrow("论文信息为空");
            }
            if (StringUtil.isBlank(paper.getPaperDate())) {
                return ResultDataThrow("发表日期为空");
            }
            if (StringUtil.isBlank(paper.getPaperTitle())) {
                return ResultDataThrow("论文题目为空");
            }
            if (StringUtil.isBlank(paper.getPaperTypeId())) {
                return ResultDataThrow("论文类型为空");
            } else {
                String typeId = paper.getPaperTypeId();
                if ("1".equals(typeId)) {
                    paper.setPaperTypeName("专业期刊发表的文章");
                }
                if ("2".equals(typeId)) {
                    paper.setPaperTypeName("学术会议交流论文");
                }
                if ("3".equals(typeId)) {
                    paper.setPaperTypeName("院（所）级学时会议交流文章");
                }
                if ("4".equals(typeId)) {
                    paper.setPaperTypeName("提交文章");
                }
            }
            if (StringUtil.isBlank(paper.getPublishedJournals())) {
                return ResultDataThrow("论文信息为空");
            }
            if (StringUtil.isBlank(paper.getAuthor())) {
                return ResultDataThrow("论文信息为空");
            }
            paper.setDoctorFlow(userFlow);
            paper.setDoctorName(user.getUserName());
            int count = jswjwStudentBiz.editJsresDoctorPaper(paper, user);
            if (count == 0) {
                return ResultDataThrow("保存失败");
            }
            return resultMap;
        }
        if (type.equals("Part")) {

            if (part == null) {
                return ResultDataThrow("科研记录为空");
            }
            if (StringUtil.isBlank(part.getParticipationDate())) {
                return ResultDataThrow("日期为空");
            }
            if (StringUtil.isBlank(part.getParticipationRoom())) {
                return ResultDataThrow("所在实验室为空");
            }
            if (StringUtil.isBlank(part.getParticipationAuthor())) {
                return ResultDataThrow("负责人为空");
            }
            if (StringUtil.isBlank(part.getParticipationTitle())) {
                return ResultDataThrow("题目为空");
            }
            if (StringUtil.isBlank(part.getParticipationRole())) {
                return ResultDataThrow("参与角色为空");
            }
            if (StringUtil.isBlank(part.getParticipationComplete())) {
                return ResultDataThrow("完成情况为空");
            }
            part.setDoctorFlow(userFlow);
            part.setDoctorName(user.getUserName());
            int count = jswjwStudentBiz.editJsresDoctorPart(part, user);
            if (count == 0) {
                return ResultDataThrow("保存失败");
            }
            return resultMap;
        }
        return resultMap;
    }

    @RequestMapping(value = {"/deleteData"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object deleteData(String userFlow, String recordFlow, String type, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }

        if (StringUtil.isBlank(type)) {
            return ResultDataThrow("数据类型为空");
        }
        if (StringUtil.isBlank(recordFlow)) {
            return ResultDataThrow("数据流水号为空");
        }
        if (!type.equals("Paper") && !type.equals("Part")) {
            return ResultDataThrow("数据类型选择错误");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (type.equals("Paper") && StringUtil.isNotBlank(recordFlow)) {
            JsresDoctorPaper paper = jswjwStudentBiz.readJsresDoctorPaperByFlow(recordFlow);
            resultMap.put("paper", paper);
        }
        if (type.equals("Part") && StringUtil.isNotBlank(recordFlow)) {
            JsresDoctorParticipation paper = jswjwStudentBiz.readJsresDoctorJsresDoctorParticipationByFlow(recordFlow);
            resultMap.put("paper", paper);

        }
        if (type.equals("Paper")) {
            jswjwStudentBiz.deleteJsresDoctorPaperByFlow(recordFlow);
        }
        if (type.equals("Part")) {
            jswjwStudentBiz.deleteJsresDoctorParticipationByFlow(recordFlow);
        }
        return resultMap;
    }

    //数据列表
    @RequestMapping(value = {"/paperDataList"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object paperDataList(String userFlow, String type, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空");
        }
        if (StringUtil.isBlank(type)) {
            return ResultDataThrow("添加数据类型为空");
        }
        if (!type.equals("Paper") && !type.equals("Part")) {
            return ResultDataThrow("数据类型选择错误");
        }


        PageHelper.startPage(pageIndex, pageSize);
        if (type.equals("Paper")) {
            List<JsresDoctorPaper> list = jswjwStudentBiz.readJsresDoctorPaperByDoctorFlow(userFlow);
            resultMap.put("list", list);
        }
        if (type.equals("Part")) {
            List<JsresDoctorParticipation> list = jswjwStudentBiz.readJsresDoctorJsresDoctorParticipationByDoctorFlow(userFlow);
            resultMap.put("list", list);
        }
        resultMap.put("dataCount", PageHelper.total);

        return resultMap;
    }

    //数据列表
    @RequestMapping(value = {"/showCertificate"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object showCertificate(String userFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwStudentBiz.findCertificates(userFlow);
//        if (recruits != null && recruits.size() > 0) {
//            HttpServletRequest httpRequest = (HttpServletRequest) request;
//            String httpurl = httpRequest.getRequestURL().toString();
//            String servletPath = httpRequest.getServletPath();
//            String hostUrl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/jswjw/showCertificate/showCertificateList.jsp?userFlow=" + userFlow;
//            resultMap.put("imgUrl", hostUrl);
//        }
        resultMap.put("list", recruits);
        return resultMap;
    }

    //数据列表
    @RequestMapping(value = {"/showCertificateImage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object showCertificateImage(String recruitFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        com.pinde.core.model.ResDoctorRecruit recruit = jswjwStudentBiz.readRecruit(recruitFlow);
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        if (recruit != null && StringUtil.isNotBlank(recruit.getGraduationCertificateNo())) {
            if (!"已发放".equals(recruit.getCertificateIssuingStatus())) {
                return ResultDataThrow("暂无证书信息");
            }

            SysUser sysUser = jswjwBiz.readSysUser(recruit.getDoctorFlow());

            String completeNo = "";
            completeNo = getShowCountryOrProvince(recruit);
            resultMap.put("completeNo", completeNo);

            List<ResJointOrg> jointOrgs = jswjwBiz.searchResJointByJointOrgFlow(recruit.getOrgFlow());
            //是协同基地 显示国家基地
            if (!jointOrgs.isEmpty() && jointOrgs.size() > 0) {
                recruit.setOrgFlow(jointOrgs.get(0).getOrgFlow());
                recruit.setOrgName(jointOrgs.get(0).getOrgName());
            }
         /*   //查询基地院长前面图片
            List<JsresSign> signList = jswjwBiz.searchSignListByOrgFlow(recruit.getOrgFlow(),com.pinde.core.common.GlobalConstant.FLAG_Y);
            if(null != signList && signList.size() > 0){
                resultMap.put("signUrl",uploadBaseUrl+"/" + signList.get(0).getSignUrl());
            }*/

            resultMap.put("headImg", uploadBaseUrl + "/" + sysUser.getUserHeadImg());
            resultMap.put("idNo", sysUser.getIdNo());
            resultMap.put("userName", sysUser.getUserName());
            resultMap.put("graduationCertificateNo", recruit.getGraduationCertificateNo());
            resultMap.put("background", uploadBaseUrl + "/" + "zhengshuTemp/certificateNew.jpg");

            String res_certificateDate = jswjwBiz.getCfgCode("res_certificateDate");
            resultMap.put("res_certificateDate", StringUtil.isBlank(res_certificateDate) ? recruit.getCertificateDate() : res_certificateDate);
            resultMap.put("certificateFlow", recruit.getCertificateFlow());

            String endTime = "";
            String startTime = "";
            //开始时间
            String recruitDate = recruit.getRecruitDate();
            //培养年限
            String trianYear = recruit.getTrainYear();
            String graudationYear = recruit.getGraduationYear();
            if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(graudationYear)) {
                try {
                    int year = 0;
                    year = Integer.valueOf(graudationYear) - Integer.valueOf(recruitDate.substring(0, 4));
                    if (year != 0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);
                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    }
                } catch (Exception e) {
                    endTime = "";
                }
            }
            //如果没有结业考核年份，按照届别与培养年限计算
            if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(trianYear) && StringUtil.isBlank(endTime)) {
                int year = 0;
                if (trianYear.equals(TrainYearEnum.OneYear.getId())) {
                    year = 1;
                }
                if (trianYear.equals(TrainYearEnum.TwoYear.getId())) {
                    year = 2;
                }
                if (trianYear.equals(TrainYearEnum.ThreeYear.getId())) {
                    year = 3;
                }
                if (year != 0) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);

                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    } catch (Exception e) {

                    }
                }
            }
            resultMap.put("completeStartDate", startTime);
            resultMap.put("completeEndDate", endTime);
//            resultMap.put("startYear",startTime.substring(0,4));
//            resultMap.put("startMonth",startTime.substring(5,7));
//            resultMap.put("endYear",endTime.substring(0,4));
//            resultMap.put("endMonth",endTime.substring(5,7));
            resultMap.put("orgName", recruit.getOrgName());
            resultMap.put("speName", recruit.getSpeName());
        } else {
            return ResultDataThrow("暂无证书信息");
        }
        String userFlow = "jsst";
        PubFile file = pubFileBiz.readProductFile(userFlow, "globalSeal");
        resultMap.put("sealPath", null == file ? "" : uploadBaseUrl + "/" + file.getFilePath());
        return resultMap;
    }

    private String getShowCountryOrProvince(com.pinde.core.model.ResDoctorRecruit recruit) {
        String completeNo = "";
        String sessionNumber = recruit.getSessionNumber();
        if (StringUtil.isBlank(sessionNumber)) {
            return "";
        }
        //所有助理全科人员都只生成助理全科证书
        if (recruit.getCatSpeId().equals(com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId())) {
            completeNo = "AssiGeneral";
        } else {
            int sessionYear = Integer.valueOf(recruit.getSessionNumber());
            //2013年以前全部用江苏省证书
            if (sessionYear <= 2013) {
                //江苏省生成规则待定
                completeNo = getProvinceOrgNo(recruit);
            } else if (sessionYear == 2014) {
                SysOrg org = jswjwBiz.getOrg(recruit.getOrgFlow());
                //只有国家基地使用国家证书
                if ("CountryOrg".equals(org.getOrgLevelId())) {
                    completeNo = "country";
                } else {
                    //江苏省生成规则待定
                    completeNo = getProvinceOrgNo(recruit);
                }
            } else {
                SysOrg org = jswjwBiz.getOrg(recruit.getOrgFlow());
                //国家基地使用国家证书
                if ("CountryOrg".equals(org.getOrgLevelId())) {
                    completeNo = "country";
                } else {
                    List<ResJointOrg> jointOrgList = jswjwBiz.searchResJointByJointOrgFlow(org.getOrgFlow());
                    if (jointOrgList != null && jointOrgList.size() > 0) {
                        ResJointOrg resJointOrg = jointOrgList.get(0);
                        SysOrg org2 = jswjwBiz.getOrg(resJointOrg.getOrgFlow());
                        //国家基地的协同基地也使用国家证书
                        if (org2.getOrgLevelId().equals("CountryOrg")) {
                            completeNo = "country";
                        } else {
                            //江苏省生成规则待定
//							completeNo = getProvinceOrgNo(recruit);
                            completeNo = "province";
                        }
                    } else {
                        //江苏省生成规则待定
//						completeNo = getProvinceOrgNo(recruit);
                        completeNo = "province";
                    }
                }
            }
        }
        return completeNo;
    }

    public String getProvinceOrgNo(ResDoctorRecruit resDoctor) {
        String no = "";
        if (resDoctor.getSpeId().equals("51") || resDoctor.getSpeId().equals("52") || resDoctor.getSpeId().equals("0700")) {
            no = "provinceAll";
        } else {
            //非全科
            no = "provinceNoAll";
        }
        return no;
    }

    //数据列表
    @RequestMapping(value = {"/showCertificateImages"}, method = {RequestMethod.GET})
    @ResponseBody
    public Object showCertificateImages(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwStudentBiz.findCertificates(userFlow);
        return JSON.toJSONString(recruits);
    }

    //数据列表
    @RequestMapping(value = {"/scoreList"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object scoreList(String userFlow, HttpServletRequest request, HttpServletResponse response) throws DocumentException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        List<ResScore> scorelist = resScoreBiz.selectByExampleWithBLOBs(userFlow);

        //技能成绩
        List<ResScore> skillList = new ArrayList<ResScore>();
        List<ResScore> theoryList = new ArrayList<ResScore>();

        //理论成绩
        ResScore theoryScore = new ResScore();
        //技能成绩
        ResScore skillScore = new ResScore();
        //公共成绩
        ResScore publicScore = new ResScore();
        if (null != scorelist && scorelist.size() > 0) {
            int theoryYear = 0;
            int skillYear = 0;
            for (ResScore resScore : scorelist) {
                int socreYear = Integer.valueOf(StringUtil.isBlank(resScore.getScorePhaseId()) ? "-1" : resScore.getScorePhaseId());

                if (com.pinde.core.common.enums.ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId())) {
                    theoryList.add(resScore);
                    if (StringUtil.isNotBlank(String.valueOf(socreYear))) {
                        if (socreYear > theoryYear) {
                            theoryYear = socreYear;
                            theoryScore = resScore;
                        }
                    }

                }
                if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())) {
                    skillList.add(resScore);
                    if (StringUtil.isNotBlank(String.valueOf(socreYear))) {
                        if (socreYear > skillYear) {
                            skillYear = socreYear;
                            skillScore = resScore;
                        }
                    }
                }
                if (com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId().equals(resScore.getScoreTypeId())) {
                    publicScore = resScore;
                }
            }
        }
        //当前年份
//        resultMap.put("thisYear", DateUtil.getYear());

//        resultMap.put("theoryScore", theoryScore);
//        resultMap.put("skillScore", skillScore);
//        resultMap.put("publicScore", publicScore);

        //所有技能科目详情
        Map<String, Map<String, String>> skillExtScoreMap = new HashMap<String, Map<String, String>>();
        for (int i = 0; i < skillList.size(); i++) {
            Map<String, String> extScore = new HashMap<String, String>();
            ResScore resScore = skillList.get(i);
            String content = null == resScore ? "" : resScore.getExtScore();
            if (StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScore.put(attrName, attrValue);
                        }
                    }
                }
            }
            skillExtScoreMap.put(resScore.getScoreFlow(), extScore);
        }
//        resultMap.put("skillList", skillList);
//        resultMap.put("theoryList", theoryList);
//        resultMap.put("skillExtScoreMap", skillExtScoreMap);
        //成绩标准分
//        List<ResPassScoreCfg> scoreCfgs = resScoreBiz.getScoreCfgList();
//        Map<String, Object> scoreCfgMap = new HashMap<>();
//        if (scoreCfgs != null) {
//            for (ResPassScoreCfg c : scoreCfgs) {
//                scoreCfgMap.put(c.getCfgYear(), c.getCfgPassScore());
//            }
//        }
//        resultMap.put("scoreCfgMap", scoreCfgMap);
        List<Map<String, Object>> skillMapList = new ArrayList<>();
        if (null != skillList && skillList.size() > 0) {
            for (ResScore rs : skillList) {
                Map<String, Object> map = new HashMap<>();
                map.put("scoreYear", rs.getScorePhaseId());
                if (rs.getSkillScore().toString().equals("2")) {
                    map.put("isPass", "缺考");
                } else if (rs.getSkillScore().toString().equals("1")) {
                    map.put("isPass", "合格");
                } else if (rs.getSkillScore().toString().equals("0")) {
                    map.put("isPass", "不合格");
                }
                Map<String, String> scoreMap = skillExtScoreMap.get(rs.getScoreFlow());
                map.put("firstStationScore", scoreMap.get("firstStationScore"));
                map.put("secondStationScore", scoreMap.get("secondStationScore"));
                map.put("thirdStationScore", scoreMap.get("thirdStationScore"));
                map.put("fourthStationScore", scoreMap.get("fourthStationScore"));
                map.put("fifthStationScore", scoreMap.get("fifthStationScore"));
                map.put("sixthStationScore", scoreMap.get("sixthStationScore"));
                map.put("seventhStationScore", scoreMap.get("seventhStationScore"));
                map.put("eighthStationScore", scoreMap.get("eighthStationScore"));
                map.put("ninthStationScore", scoreMap.get("ninthStationScore"));
                skillMapList.add(map);
            }
        }
        resultMap.put("skillMapList", skillMapList);
        List<Map<String, Object>> theoryMapList = new ArrayList<>();
        if (null != theoryList && theoryList.size() > 0) {
            for (ResScore rs : theoryList) {
                Map<String, Object> map = new HashMap<>();
                map.put("scoreYear", rs.getScorePhaseId());
                if (rs.getTheoryScore().toString().equals("2")) {
                    map.put("isPass", "缺考");
                } else if (rs.getTheoryScore().toString().equals("1")) {
                    map.put("isPass", "合格");
                } else if (rs.getTheoryScore().toString().equals("0")) {
                    map.put("isPass", "不合格");
                }
                theoryMapList.add(map);
            }
        }
        resultMap.put("theoryMapList", theoryMapList);
        return resultMap;
    }

    @RequestMapping(value = {"/findActivityList"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object findActivityList(String userFlow, String typeId, String isCurrent, String deptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) throws DocumentException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(typeId)) {
            return ResultDataThrow("类型标识符为空");
        }
        if (!"isNew".equals(typeId) && !"isEval".equals(typeId) && !"isCurrent".equals(typeId)) {
            return ResultDataThrow("类型标识符不正确,isNew，isEval， isCurrent");
        }
        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空");
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        if (doctor == null) {
            return ResultDataThrow("医师信息不存在");
        }
        Map<String, String> param = new HashMap<>();
        if ("isNew".equals(typeId)) {
            param.put("isNew", com.pinde.core.common.GlobalConstant.FLAG_Y);//最新活动
        }
        if ("isEval".equals(typeId)) {
            param.put("isEval", com.pinde.core.common.GlobalConstant.FLAG_Y);//活动评价
        }
        if ("isCurrent".equals(typeId)) {
            param.put("now", com.pinde.core.common.GlobalConstant.FLAG_Y);//当前活动
        }
        param.put("roleFlag", "doctor");
        param.put("userFlow", userFlow);
        String orgFlow = "";
        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            orgFlow = doctor.getSecondOrgFlow();
        } else {
            orgFlow = doctor.getOrgFlow();
        }
        List<Map<String, Object>> depts = new ArrayList<>();
        Map<String, Object> deptMap = new HashMap<>();
        deptMap.put("deptFlow", "isCurrent");
        deptMap.put("deptName", "当前轮转科室");
        depts.add(deptMap);
        deptMap = new HashMap<>();
        deptMap.put("deptFlow", "All");
        deptMap.put("deptName", "全部科室");
        depts.add(deptMap);
        List<Map<String, Object>> deptList = activityBiz.searchDeptByDoctor(userFlow, orgFlow);
        depts.addAll(deptList);
        resultMap.put("depts", depts);
        param.put("orgFlow", orgFlow);
        param.put("isCurrent", isCurrent);
        param.put("deptFlow", deptFlow);
        resultMap.put("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
//        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> list = activityBiz.findActivityList(param);
//        resultMap.put("list", list);
        resultMap.put("dataCount", PageHelper.total);
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        if (null != list && list.size() > 0) {
            for (Map<String, Object> m : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("activityFlow", m.get("activityFlow"));
                map.put("activityName", m.get("activityName"));
                map.put("activityTypeName", m.get("activityTypeName"));
                map.put("activityAddress", m.get("activityAddress"));
                map.put("activityRemark", m.get("activityRemark"));
                String resultFlow = (String) m.get("resultFlow");
                map.put("resultFlow", resultFlow);
                map.put("userName", m.get("userName"));
                map.put("deptName", m.get("deptName"));
                String startTime = (String) m.get("startTime");
                map.put("startTime", startTime);
                map.put("endTime", m.get("endTime"));
                map.put("fileName", m.get("fileName"));
                String isRegiest = (String) m.get("isRegiest");
                map.put("isRegiest", isRegiest);
                String isScan = (String) m.get("isScan");
                map.put("isScan", isScan);
                String isScan2 = (String) m.get("isScan2");
                map.put("isScan2", isScan2);
                if ("isNew".equals(typeId) && startTime.compareTo(DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm")) >= 0 && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRegiest)) {
                    map.put("operId", "Regiest");
                    map.put("operName", "报名");
                }
                BigDecimal evalScore2 = (BigDecimal) m.get("evalScore2");
                if ("isEval".equals(typeId) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isScan) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isScan2) && null == evalScore2) {
                    map.put("operId", "Evaluate");
                    map.put("operName", "评价");
                    map.put("evalScore", "0");
                }
                if ("isEval".equals(typeId) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isScan) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isScan2) && null != evalScore2) {
                    map.put("operId", "ShowEvaluate");
                    map.put("operName", "查看评价");
                }
                if (StringUtil.isNotBlank(resultFlow) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRegiest) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isScan)) {
                    map.put("operId", "CannelRegiest");
                    map.put("operName", "取消报名");
                }
                if ("isCurrent".equals(typeId) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRegiest) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isScan)) {
                    map.put("operName", "已报名");
                }
                if ("isCurrent".equals(typeId) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isScan)) {
                    map.put("operName", "已签到");
                }
                resultMapList.add(map);
            }
        }
        resultMap.put("resultMapList", resultMapList);
        return resultMap;
    }

    @RequestMapping(value = {"/joinActivity"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object joinActivity(String activityFlow, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(activityFlow)) {
            return ResultDataThrow("活动标识符为空");
        }
        TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
        if (info == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
            return ResultDataThrow("活动信息不存在，请刷新列表页面！");
        }

        if (DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(info.getStartTime()) > 0) {
            return ResultDataThrow("活动已开始，无法报名！");
        }
        TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
        if (result != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsRegiest())) {
            return ResultDataThrow("你已报名，请勿重复报名！");
        }
        List<TeachingActivityInfo> infos = activityBiz.checkJoinList(activityFlow, userFlow);
        if (infos != null && infos.size() > 0) {
            TeachingActivityInfo activityInfo = infos.get(0);
            return ResultDataThrow("已报名同一时间【" + activityInfo.getActivityName() + "】，不能报名！");
        }
        if (result == null)
            result = new TeachingActivityResult();

        result.setActivityFlow(activityFlow);
        result.setUserFlow(userFlow);
        result.setIsRegiest(com.pinde.core.common.GlobalConstant.FLAG_Y);
        result.setRegiestTime(DateUtil.getCurrDateTime());
        result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        int c = activityBiz.saveResult(result, userFlow);
        if (c == 0) {
            return ResultDataThrow("报名失败！");
        }
        return resultMap;

    }

    @RequestMapping(value = {"/cannelRegiest"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object cannelRegiest(String activityFlow, String resultFlow, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(activityFlow)) {
            return ResultDataThrow("活动标识符为空");
        }
        TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
        if (info == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
            return ResultDataThrow("活动信息不存在，请刷新列表页面！");
        }
        TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
        if (result == null) {
            return ResultDataThrow("你未报名，无法取消报名信息！");
        }
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsRegiest())) {
            return ResultDataThrow("你已取消报名！");
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsScan())) {
            return ResultDataThrow("你已扫码签到，无法取消报名信息！");
        }
        result.setIsRegiest(com.pinde.core.common.GlobalConstant.FLAG_N);
        int c = activityBiz.saveResult(result, userFlow);
        if (c == 0) {
            return ResultDataThrow("取消报名失败！");
        }
        return resultMap;

    }

    @RequestMapping(value = {"/showDocEval"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object showDocEval(String resultFlow, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(resultFlow)) {
            return ResultDataThrow("评价标识符为空");
        }
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        //评价人员
        TeachingActivityResult result = activityBiz.readResult(resultFlow);
//        resultMap.put("result", result);
        if (result != null) {
            //评价人员评分详情
            Map<String, Object> evalDetailMap = new HashMap<>();
            Map<String, Object> evalRemarkMap = new HashMap<>();
            //评价的指标
            TeachingActivityInfo activityInfo = activityBiz.readActivityInfo(result.getActivityFlow());
            List<TeachingActivityInfoTarget> infoTargets = activityTargeBiz.readActivityTargets(result.getActivityFlow());
            List<TeachingActivityTarget> targetList = activityTargeBiz.readByOrgNewNoStatus(activityInfo.getActivityTypeId(), activityInfo.getOrgFlow());
            List<String> targetFlowList = targetList.stream().map(TeachingActivityTarget::getTargetFlow).collect(Collectors.toList());
            for (TeachingActivityInfoTarget infoTarget : infoTargets) {
                if (!targetFlowList.contains(infoTarget.getTargetFlow())) {
                    infoTarget.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                    activityTargeBiz.saveInfoTarget(infoTarget, sysUser);
                }
            }
            List<Map<String, Object>> targets = activityTargeBiz.readActivityTargetEvals(result.getActivityFlow());
//            resultMap.put("targets", targets);
            List<TeachingActivityEval> evals = activityBiz.readActivityResultEvals(resultFlow);
            if (evals != null) {
                for (TeachingActivityEval e : evals) {
                    evalDetailMap.put(e.getResultFlow() + e.getTargetFlow(), e.getEvalScore());
                    evalRemarkMap.put(e.getResultFlow() + e.getTargetFlow(), e.getEvalRemarks());
                }
            }
//            resultMap.put("evalDetailMap", evalDetailMap);
            List<Map<String, Object>> resultMapList = new ArrayList<>();
            if (null != targets && targets.size() > 0) {
                for (Map<String, Object> m : targets) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("targetFlow", m.get("targetFlow"));
                    map.put("targetName", m.get("targetName"));
                    map.put("isText", m.get("isText"));
                    if (m.get("isText").equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                        map.put("evalScore", "1");
                    }else {
                        map.put("evalScore", evalDetailMap.get(resultFlow + m.get("targetFlow")));
                    }
                    map.put("evalRemarks", evalRemarkMap.get(resultFlow + m.get("targetFlow")));
                    resultMapList.add(map);
                }
            }
            resultMap.put("resultMapList", resultMapList);
        } else {
            return ResultDataThrow("扫码信息不存在！");
        }
        return resultMap;

    }

    @RequestMapping(value = {"/saveEvalInfo"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object saveEvalInfo(TeachingActivityEvalExt evalExt) throws DocumentException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (null == evalExt) {
            return ResultDataThrow("参数格式错误");
        }
        String userFlow = evalExt.getUserFlow();
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        String resultFlow = evalExt.getResultFlow();
        if (StringUtil.isEmpty(resultFlow)) {
            return ResultDataThrow("请选择需要评价的活动");
        }
        TeachingActivityResult result = activityBiz.readResult(resultFlow);
        if (result == null) {
            return ResultDataThrow("你未扫码参加该活动，无法评价");
        }
        if (result != null && result.getEvalScore() != null) {
            return ResultDataThrow("该活动已评价，请刷新页面！");
        }
        String evalArr = evalExt.getEvalArr();
        if (StringUtil.isNotBlank(evalArr)) {
            List<TeachingActivityEval> evalList = JSON.parseObject(evalArr, new TypeReference<List<TeachingActivityEval>>() {
            });
            evalExt.setEvals(evalList);
        } else {
            return ResultDataThrow("请选择评分！");
        }
//        List<TeachingActivityEval> evals = evalExt.getEvals();
//        if (null == evals || evals.size() <= 0) {
//            return ResultDataThrow("请选择评分！");
//        }
        int count = activityBiz.saveEvalInfo(evalExt.getEvals(), resultFlow, userFlow);
        if (count == 0) {
            return ResultDataThrow("评价失败！");
        }
        return resultMap;
    }

    @RequestMapping(value = {"/changePass"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object changePass(String userFlow, String userPasswd, String newUserPasswd, String reNewUserPasswd) {
        String isRecruit = com.pinde.core.common.GlobalConstant.FLAG_N;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "修改成功，请重新登录！");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空！");
        }
        if (StringUtil.isBlank(userPasswd)) {
            return ResultDataThrow("密码不能为空！");
        }
        if (StringUtil.isBlank(newUserPasswd)) {
            return ResultDataThrow("新密码不能为空！");
        }
        if (StringUtil.isBlank(reNewUserPasswd)) {
            return ResultDataThrow("确认密码不能为空！");
        }
        if (!checkPass(newUserPasswd)) {
            return ResultDataThrow("密码强度不够！");
        }
        if (!newUserPasswd.equals(reNewUserPasswd)) {
            return ResultDataThrow("两次密码输入不一致，请重新输入！");
        }
        SysUser user = oscaAppBiz.readSysUser(userFlow);
        if (user == null) {
            return ResultDataThrow("用户不存在！");
        }
        if (!user.getUserPasswd().equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))) {
            return ResultDataThrow("密码错误，请重新输入！");
        }
        user = new SysUser();
        user.setUserFlow(userFlow);
        user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, newUserPasswd));
        user.setChangePasswordTime(DateUtil.getCurrDate());
        int c = oscaAppBiz.edit(user, user);
        if (c == 0) {
            return ResultDataThrow("修改失败！");
        }
        ResDoctor doctor = jswjwBiz.readResDoctorTwo(userFlow);
        if (null == doctor || !"20".equals(doctor.getDoctorStatusId())) {
            isRecruit = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        resultMap.put("isRecruit", isRecruit);
        return resultMap;
    }

    /**
     * 学员角色年度理论考试
     *
     * @return
     */
    @RequestMapping(value = {"/theoreticalExam"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object theoreticalExam(String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        ResDoctor resDoctor = doctorBiz.readDoctor(userFlow);
        if (resDoctor == null) {
            return ResultDataThrow("用户不存在！");
        }
        String orgFlow = "";
        if (resDoctor != null) {
            if (StringUtil.isNotBlank(resDoctor.getSecondOrgFlow())) {
                orgFlow = resDoctor.getSecondOrgFlow();
            } else {
                orgFlow = resDoctor.getOrgFlow();
            }
        }
        SchExamArrangement schExamArrangement = new SchExamArrangement();
        schExamArrangement.setOrgFlow(orgFlow);
        schExamArrangement.setSessionNumber(resDoctor.getSessionNumber());
        schExamArrangement.setTrainingTypeId(resDoctor.getTrainingTypeId());
        schExamArrangement.setTrainingSpeId(resDoctor.getTrainingSpeId());
        List<SchExamArrangement> examArrangements = examCfgBiz.searchList(schExamArrangement);
        //查询条件
        Map<String, Object> param = new HashMap<>();
        List<String> userFlows = new ArrayList<>();
        userFlows.add(userFlow);
        param.put("orgFlow", orgFlow);
        param.put("userFlows", userFlows);
        List<SchExamDoctorArrangement> doctorArrangements = scoreQueryBiz.getDoctorArrangements(param);
        Map<String, SchExamDoctorArrangement> doctorArrangementMap = new HashMap<>();
        if (doctorArrangements != null && doctorArrangements.size() > 0) {
            for (SchExamDoctorArrangement da : doctorArrangements) {
                doctorArrangementMap.put(da.getArrangeFlow(), da);
            }
//            resultMap.put("daMap", doctorArrangementMap);
        }
        Map<String, Map<String, String>> examLogMaps = null;
        if (examArrangements != null && examArrangements.size() > 0) {
            examLogMaps = new HashMap<>();
            for (SchExamArrangement tempExam : examArrangements) {
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("arrangeFlow", tempExam.getArrangeFlow());
                paramMap.put("doctorFlow", resDoctor.getDoctorFlow());
                List<Map<String, String>> examArrangementMaps = examCfgBiz.searchExamLogByItems(paramMap);
                if (examArrangementMaps != null && examArrangementMaps.size() > 0) {
                    for (Map<String, String> tempMap : examArrangementMaps) {
                        Map<String, String> paramTempMap = new HashMap<>();
                        paramTempMap.put("countNum", tempMap.get("COUNTNUM"));
                        paramTempMap.put("maxScore", tempMap.get("MAXSCORE"));
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsApp()) &&
                                Integer.parseInt(tempExam.getExamNumber()) > Integer.parseInt(tempMap.get("COUNTNUM"))) {
                            paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                        }
                        if (StringUtil.isNotBlank(tempExam.getExamStartTime()) &&
                                StringUtil.isNotBlank(tempExam.getExamEndTime())) {
                            Date today = DateUtil.parseDate(DateUtil.getCurrDateTime2(), DateUtil.defDtPtn02);
                            Date examStartTime = DateUtil.parseDate(tempExam.getExamStartTime(), "yyyy-MM-dd HH:mm:ss");
                            Date examEndTime = DateUtil.parseDate(tempExam.getExamEndTime(), "yyyy-MM-dd HH:mm:ss");
                            if (examStartTime.after(today)) {
                                paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                                paramTempMap.put("isNoStart", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            }
                            if (today.after(examEndTime)) {
                                paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                                paramTempMap.put("isEnd", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            }
                        }
                        examLogMaps.put(tempMap.get("ARRANGEFLOW"), paramTempMap);
                    }
                } else {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsApp())) {
                        Map<String, String> paramTempMap = new HashMap<>();
                        paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                        if (StringUtil.isNotBlank(tempExam.getExamStartTime()) &&
                                StringUtil.isNotBlank(tempExam.getExamEndTime())) {
                            Date today = DateUtil.parseDate(DateUtil.getCurrDateTime2(), DateUtil.defDtPtn02);
                            Date examStartTime = DateUtil.parseDate(tempExam.getExamStartTime(), "yyyy-MM-dd HH:mm:ss");
                            Date examEndTime = DateUtil.parseDate(tempExam.getExamEndTime(), "yyyy-MM-dd HH:mm:ss");
                            if (examStartTime.after(today)) {
                                paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                                paramTempMap.put("isNoStart", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            }
                            if (today.after(examEndTime)) {
                                paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                                paramTempMap.put("isEnd", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            }
                        }
                        examLogMaps.put(tempExam.getArrangeFlow(), paramTempMap);
                    }
                }
            }
//            resultMap.put("examLogMaps", examLogMaps);
        }
//        resultMap.put("examArrangements", examArrangements);

        List<Map<String, Object>> resultMapList = new ArrayList<>();
        if (null != examArrangements && examArrangements.size() > 0) {
            for (SchExamArrangement sea : examArrangements) {
                Map<String, Object> map = new HashMap<>();
                map.put("arrangeFlow", sea.getArrangeFlow());
                map.put("assessmentYear", sea.getAssessmentYear());
                Map<String, String> examLogMap = examLogMaps.getOrDefault(sea.getArrangeFlow(), new HashMap<>());
                if (StringUtil.isBlank(examLogMap.get("maxScore"))) {
                    map.put("examScore", "-");
                } else {
                    SchExamDoctorArrangement seda = doctorArrangementMap.get(sea.getArrangeFlow());
                    map.put("examScore", seda.getExamScore());
                    // 判断是否开放成绩查看权限，未开放则将学员成绩以**展示
                    SchExamArrangement examArrangement = examCfgBiz.readByFlow(seda.getArrangeFlow());
                    if (StringUtil.isNotBlank(examArrangement.getIsOpenResult()) && examArrangement.getIsOpenResult().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
                        map.put("examScore", "**");
                    }
                }
                map.put("canExam", examLogMap.get("canExam"));
                map.put("isEnd", examLogMap.get("isEnd"));
                map.put("isNoStart", examLogMap.get("isNoStart"));
                resultMapList.add(map);
            }
        }
        resultMap.put("resultMapList", resultMapList);
        return resultMap;
    }

    /**
     * 参加年度考试
     *
     * @param arrangeFlow
     * @param userFlow
     * @return
     */
    @RequestMapping(value = {"/toYearTest"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object toYearTest(String arrangeFlow, String userFlow, HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", ResultEnum.Success.getName());
        if (StringUtil.isBlank(arrangeFlow)) {
            return ResultDataThrow("年度考核信息标识符为空");
        }
        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            return ResultDataThrow("请联系管理员维护年度考试地址");
        }
        SchExamArrangement ment = examCfgBiz.readByFlow(arrangeFlow);
        if (ment == null) {
            return ResultDataThrow("考核信息不存在");
        }
        //校验考核是否开放
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ment.getIsOpen())) {
            return ResultDataThrow("该考核暂未开放，无法参加考试");
        }
        //当前用户

        //校验学员已经考核了几次
        int examCount = examCfgBiz.findDocExamCount(userFlow, arrangeFlow);
        if (examCount >= Integer.valueOf(ment.getExamNumber())) {
            return ResultDataThrow("考核次数已达到最大考核次数，无法再次考试");
        }
        //用户的医师信息
        ResDoctor doctor = doctorBiz.readDoctor(userFlow);
        if (doctor == null) {
            return ResultDataThrow("无医师信息，无法参加考试");
        }
        //获取考试参数
        //考试结果记录流水号
        String recordFlow = PkUtil.getUUID();
        //考试范围标准科室名称
        String standarDeptNames = "";
        Map<String, Object> map = null;
        //随机试卷
        if ("1".equals(ment.getExampaperType())) {
            map = examCfgBiz.getSuiJiConfig(ment, userFlow);
        } else if ("2".equals(ment.getExampaperType())) {
            map = examCfgBiz.getGuDingConfig(ment);
        } else {
            return ResultDataThrow("考核信息无考核类型，无法参加考试");
        }
        if (map == null || StringUtil.isBlank((String) map.get("standardDeptNames"))) {
            return ResultDataThrow("考核信息无考核范围，无法参加考试");
        }
        standarDeptNames = (String) map.get("standardDeptNames");
        //试类型 (年度考试)（传递）1：出科考试；2：年度考试
        String ExamTestType = "3";
        //时间戳
        String Date = "0";
        //试卷类型
        String paperType = ment.getExampaperType();
        String StartTime = "";
        String EndTime = "";
        String SoluTime = "";
        SysUser user = jswjwBiz.readSysUser(userFlow);
        //考试次数
        String examNumber = ment.getExamNumber();
        resultMap.put("Action", "YearExam");
        resultMap.put("CardID", user.getUserCode());
        resultMap.put("ExamTestType", ExamTestType);
        resultMap.put("ExamSoluClass", paperType);
        resultMap.put("TestNumber", examNumber);
        resultMap.put("ExamKnowledge", standarDeptNames);
        resultMap.put("SpecName", doctor.getTrainingSpeName());
        resultMap.put("ProcessFlow", recordFlow);
        resultMap.put("Date", Date);
        resultMap.put("StartTime", ment.getExamStartTime());
        resultMap.put("EndTime", ment.getExamEndTime());
        resultMap.put("SoluTime", ment.getExamDuration());
        if (StringUtil.isNotBlank(ment.getExamStartTime())) {
            StartTime = ment.getExamStartTime();
        }
        if (StringUtil.isNotBlank(ment.getExamEndTime())) {
            EndTime = ment.getExamEndTime();
        }
        if (StringUtil.isNotBlank(ment.getExamDuration())) {
            SoluTime = ment.getExamDuration();
        }
//		&StartTime=2018-05-02 00:00:00.000&EndTime=2019-04-26 23:59:59.000&SoluTime=60
        //创建考核结果
        SchExamDoctorArrangement schExamDoctorArrangement = new SchExamDoctorArrangement();
        schExamDoctorArrangement.setRecordFlow(recordFlow);
        schExamDoctorArrangement.setArrangeFlow(arrangeFlow);
        schExamDoctorArrangement.setAssessmentYear(ment.getAssessmentYear());
        schExamDoctorArrangement.setDoctorFlow(user.getUserFlow());
        schExamDoctorArrangement.setDoctorName(user.getUserName());

        schExamDoctorArrangement.setOrgName(ment.getOrgName());
        schExamDoctorArrangement.setOrgFlow(ment.getOrgFlow());

        schExamDoctorArrangement.setSessionNumber(doctor.getSessionNumber());

        int saveResult = examCfgBiz.save(schExamDoctorArrangement);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE >= saveResult) {
            return ResultDataThrow("考核结果信息创建出错");
        }

        testUrl = testUrl + "?Action=YearExam&CardID=" + user.getUserCode()
                + "&ProcessFlow=" + recordFlow
                + "&paperType=" + "5" // 写死是5，不知有没有用到
                + "&count=" + examNumber
                + "&Date=" + Date
                + "&userFlow=" + user.getUserFlow()
                + "&paperFlow=" + ment.getPaperFlow()
                + "&ExamTestType=" + ExamTestType // 写死是3，看测试环境这个是3
                + "&ExamKnowledge=" + standarDeptNames
                + "&SpecName=" + doctor.getTrainingSpeName()
                + "&StartTime=" + URLEncoder.encode(StartTime, "utf-8")
                + "&EndTime=" + URLEncoder.encode(EndTime, "utf-8")
                + "&SoluTime=" + SoluTime
                + "&systemName=" + "jsxy-zp-ks" // 江苏西医-住培-考试
                + "&token=" + request.getSession().getId();

        resultMap.put("testUrl", testUrl);

        // 现在拼的url
        /*systemName=jsxy-zp-ks
        Action=YearExam&CardID=userCode&&ProcessFlow=recordFlow&paperType=paperType&count=examNumber&Date=Date
                &ExamTestType=ExamTestType&ExamKnowledge=standarDeptNames&SpecName=trainingSpeName
            &StartTime=StartTime&EndTime=EndTime&SoluTime=SoluTime*/

        // 原来的url
        /*testUrl = testUrl + "?Action=YearExam&CardID=" + user.getUserCode()
        + "&ExamTestType=" + ExamTestType
        + "&ExamSoluClass=" + paperType
        + "&TestNumber=" + examNumber
        + "&ExamKnowledge=" + standarDeptNames
        + "&SpecName=" + doctor.getTrainingSpeName()
        + "&ProcessFlow=" + recordFlow
        + "&Date=" + Date
        + "&StartTime=" + StartTime
        + "&EndTime=" + EndTime
        + "&SoluTime=" + SoluTime;;*/

        return resultMap;
    }

    public static boolean checkPass(String str) throws PatternSyntaxException {
        String regExp = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_.!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\\W_.!@#$%^&*`~()-+=]+$)(?![0-9\\W_.!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\\W_.!@#$%^&*`~()-+=]{8,20}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    //图形验证码
    @RequestMapping(value = "/graphicVerificationCode", method = {RequestMethod.POST})
    @ResponseBody
    public Object graphicVerificationCode() {
        Map<String, Object> resultMap = new HashMap<>();
        char[] numberChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M'};
        StringBuffer verificationCode = new StringBuffer("");
        for (int i = 0; i < 4; i++) {
            verificationCode.append(numberChar[new Random().nextInt(numberChar.length)]);
        }
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "图形验证码");
        resultMap.put("resultData", verificationCode);
        return resultMap;
    }

    /**
     * 验证手机号有没有经过认证
     */
    @RequestMapping(value = "/checkPhoneIsVerify", method = {RequestMethod.POST})
    @ResponseBody
    public Object checkPhoneIsVerify(String userPhone, String systemFlag) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isBlank(userPhone)) {
            return ResultDataThrow("手机号为空");
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            return ResultDataThrow(phoneRegex(userPhone).get(1));
        }
        List<SysUser> sysUsers = jswjwBiz.checkPhoneIsVerify(userPhone);
        if (sysUsers == null || sysUsers.size() == 0) {
            return ResultDataThrow("该手机号码未经认证，无法通过手机号码找回密码，请联系管理员重置密码");
        }
        //查询是否是付费用户
        String userFlow = sysUsers.get(0).getUserFlow();
        Map<String, String> searchMap = new HashMap<String, String>();
//        searchMap.put("activity","jsres_doctor_activity_"+userFlow);
//        searchMap.put("attendance","jsres_doctor_attendance_"+userFlow);
        searchMap.put("app", "jsres_doctor_app_menu_" + userFlow);
        if (jswjwBiz.selectPowerCfg(searchMap).equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
            return ResultDataThrow("抱歉该功仅付费用户使用,请联系管理员！");
        }

       /* 查询该用户发送短信的次数
        1、查询用户是否发送给短信
        2、如果发送短信，查询发送短信的时间与当前时间是否相差一个小时，并且发送的次数小于5
              如果满足，继续发送短信并更新发送次数
              如果时间大于一个小时，删除记录，就相当于用户重新开始
              如果时间小于一小时，但是次数大于5，提示 等待   */
        VerificationCodeRecord codeRecord = jswjwBiz.selectPhone(userPhone);
        if (codeRecord != null) {
            try {
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Long timeDifference = sd.parse(DateUtil.transDateTime(DateUtil.getCurrDateTime())).getTime() - sd.parse(DateUtil.transDateTime(codeRecord.getCreateTime())).getTime();
                if (timeDifference / (1000 * 60 * 60) > 1) {
                    //时间大于一个小时，删除记录
                    jswjwBiz.deleteRecordFlow(codeRecord.getRecordFlow());
                    codeRecord = null;
                } else {
                    //时间小于一个小时，但是次数大于5
                    if (Integer.parseInt(codeRecord.getSendNum()) >= 5) {
                        return ResultDataThrow("验证次数过多，请1小时后再尝试");
                    }
                }
            } catch (ParseException e) {
                logger.error("", e);
                return ResultDataThrow("服务器发生错误");
            }
        }
        SMSUtil smsUtil = new SMSUtil(userPhone);
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        SysSmsLog sSmsRecord = new SysSmsLog();
        if ("tjres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516954", "R101", code);
        }
        if ("jsres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516946", "R101", code);
        }
        if (!"100".equals(sSmsRecord.getStatusCode())) {
            return ResultDataThrow(sSmsRecord.getStatusName());
        }
        String currDateTime = DateUtil.getCurrDateTime2();
        jswjwBiz.saveForgetPasswdUser(userPhone, String.valueOf(code), currDateTime);
        resultMap.put("userPhone", userPhone);
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "发送成功");

        //保存更新发送短信的次数
        if (codeRecord != null) {
            codeRecord.setSendNum(String.valueOf(Integer.parseInt(codeRecord.getSendNum()) + 1));
            codeRecord.setDateTime(String.valueOf(System.currentTimeMillis()));
            jswjwBiz.updateRecordFlow(codeRecord);
        } else {
            VerificationCodeRecord record = new VerificationCodeRecord();
            record.setAppSend(com.pinde.core.common.GlobalConstant.FLAG_Y);//是app发送的短信，为了与pc端区分
            record.setPhone(userPhone);
            record.setCreateTime(DateUtil.getCurrDateTime());
            record.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            record.setSendNum("1"); //次数
            record.setRecordFlow(PkUtil.getUUID());
            record.setDateTime(String.valueOf(System.currentTimeMillis()));
            verificationCodeRecordMapper.insert(record);
        }
        return resultMap;
    }

    /**
     * 验证码校验
     */
    @RequestMapping(value = "/checkVerifyCode", method = {RequestMethod.POST})
    @ResponseBody
    public Object checkVerifyCode(String userPhone, String userPhoneBefore, String verifyCode) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isBlank(verifyCode)) {
            return ResultDataThrow("验证码为空");
        }
        if (StringUtil.isBlank(userPhone)) {
            return ResultDataThrow("手机号为空");
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            return ResultDataThrow(phoneRegex(userPhone).get(1));
        }
        if (StringUtil.isBlank(userPhoneBefore) || !userPhoneBefore.equals(userPhone)) {
            return ResultDataThrow("两次手机号不一致,请重新获取验证码");
        }
        SysUser sysUser = jswjwBiz.selectByUserPhone(userPhone);
        if (sysUser == null) {
            return ResultDataThrow("操作失败，请刷新页面重试");
        }
        //连续提交5次错误的短信验证码，该用户暂停一小时使用该功能
        //1、查询用户提交错误验证码测试，如果小于5，继续执行
        //2、大于5次，获取记录的时间与当前时间比较，如果小于1小时就等待，大于则重置次数
        //3、提交正确的验证码，重置次数
        VerificationCodeRecord record = jswjwBiz.selectPhone(userPhone);
        if (StringUtil.isNotBlank(record.getCodeErrorNum()) && Integer.parseInt(record.getCodeErrorNum()) >= 5) {
            try {
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Long timeDifference = sd.parse(DateUtil.transDateTime(DateUtil.getCurrDateTime())).getTime() - Long.parseLong(record.getDateTime());
                if (timeDifference / (1000 * 60 * 60) > 1) {
                    //重置次数
                    record.setCodeErrorNum("0");
                    jswjwBiz.updateRecordFlow(record);
                } else {
                    return ResultDataThrow("验证次数过多，请1小时后再尝试");
                }
            } catch (ParseException e) {
                logger.error("", e);
                return ResultDataThrow("服务器发生错误");
            }
        }
        String currDateTime = DateUtil.getCurrDateTime2();
        String verifyCodeTime = sysUser.getVerifyCodeTime();
        long betweenTowDate = DateUtil.signSecondsBetweenTowDate(currDateTime, verifyCodeTime);
        String userVerifyCode = sysUser.getVerifyCode();
        if (verifyCode.length() != 6) {
            updateCodeErrorNum(record);
            jswjwBiz.updateRecordFlow(record);
            return ResultDataThrow("验证码错误");
        }
        if (verifyCode.equals(userVerifyCode)) {
            if (betweenTowDate > 60) {
                updateCodeErrorNum(record);
                return ResultDataThrow("验证码超时，请重新获取验证码！");
            }
            record.setCodeErrorNum("");
            resultMap.put("resultId", "200");
            resultMap.put("resultType", "验证码正确");
            return resultMap;
        } else {
            updateCodeErrorNum(record);
            return ResultDataThrow("验证码错误！");
        }

    }

    private void updateCodeErrorNum(VerificationCodeRecord record) {
        if (record != null) {
            if (StringUtil.isNotBlank(record.getCodeErrorNum())) {
                record.setCodeErrorNum(String.valueOf(Integer.valueOf(record.getCodeErrorNum()) + 1));
            } else {
                record.setCodeErrorNum("1");
            }
            jswjwBiz.updateRecordFlow(record);
        }
    }

    /**
     * 设置新密码
     *
     * @param userPhone
     * @param userPasswd
     * @return
     */
    @RequestMapping(value = {"/passwdNew"}, method = RequestMethod.POST)
    @ResponseBody
    public Object passwdNew(String userPhone, String userPasswd) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isBlank(userPhone)) {
            return ResultDataThrow("手机号为空");
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            return ResultDataThrow(phoneRegex(userPhone).get(1));
        }
        if (StringUtil.isBlank(userPasswd)) {
            return ResultDataThrow("密码为空");
        }
        SysUser sysUser = jswjwBiz.selectByUserPhone(userPhone);
        if (sysUser == null) {
            return ResultDataThrow("修改失败");
        }
        sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), userPasswd));
        int count = jswjwBiz.updateUser(sysUser);
        if (count == com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return ResultDataThrow("修改失败");
        }
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "修改成功");
        return resultMap;
    }

    /**
     * 认证和修改手机号（发送验证码）
     */
    @RequestMapping(value = {"/authenPhone"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object authenPhone(String userPhone, String systemFlag, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("登陆异常");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (user == null) {
            return ResultDataThrow("用户不存在");
        }
        if (StringUtil.isBlank(userPhone)) {
            return ResultDataThrow("手机号为空");
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            return ResultDataThrow(phoneRegex(userPhone).get(1));
        }
        List<SysUser> sysUsers = jswjwBiz.selectByUserPhoneAndIsVerify(userPhone);
        if (sysUsers != null && sysUsers.size() > 0) {
            if (!sysUsers.get(0).getUserFlow().equals(user.getUserFlow())) {
                return ResultDataThrow("该手机号已绑定过账号");
            }
        }
        SMSUtil smsUtil = new SMSUtil(userPhone);
        int code = (int) ((Math.random() * 9 + 1) * 1000);
        SysSmsLog sSmsRecord = new SysSmsLog();
        if ("tjres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516954", "R101", code);
        }
        if ("jsres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516946", "R101", code);
        }
        if (!"100".equals(sSmsRecord.getStatusCode())) {
            return ResultDataThrow(sSmsRecord.getStatusName());
        }
        String currDateTime = DateUtil.getCurrDateTime2();
        user.setVerifyCode(String.valueOf(code));
        user.setVerifyCodeTime(currDateTime);
        jswjwBiz.saveModifyUser(user);
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "发送成功");
        resultMap.put("userPhone", userPhone);
        return resultMap;
    }

    /**
     * 认证和修改手机号
     */
    @RequestMapping(value = {"/authenVerifyCode"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object authenVerifyCode(String userPhone, String userPhoneBefore, String userFlow, String verifyCode) {
        Map<String, Object> resuleMap = new HashMap<>();
        if (StringUtil.isBlank(verifyCode)) {
            return ResultDataThrow("验证码为空");
        }
        if (StringUtil.isBlank(userPhone)) {
            return ResultDataThrow("手机号为空");
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            return ResultDataThrow(phoneRegex(userPhone).get(1));
        }
        if (StringUtil.isBlank(userPhoneBefore) || !userPhoneBefore.equals(userPhone)) {
            return ResultDataThrow("两次手机号不一致,请重新获取验证码");
        }
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("登陆异常");
        }
        SysUser userInfo = jswjwBiz.readSysUser(userFlow);
        if (userInfo == null) {
            return ResultDataThrow("用户不存在");
        }
        String currDateTime = DateUtil.getCurrDateTime2();
        String verifyCodeTime = userInfo.getVerifyCodeTime();
        long betweenTowDate = DateUtil.signSecondsBetweenTowDate(currDateTime, verifyCodeTime);
        String userVerifyCode = userInfo.getVerifyCode();
        if (verifyCode.equals(userVerifyCode)) {
            if (betweenTowDate > 300) {
                return ResultDataThrow("验证码超时，请重新获取验证码！");
            }
            userInfo.setUserPhone(userPhone);
            jswjwBiz.saveAuthenSuccessUser(userInfo);
            resuleMap.put("resultId", "200");
            resuleMap.put("resultType", "验证码正确");
            resuleMap.put("userInfo", userInfo);
            return resuleMap;
        } else {
            return ResultDataThrow("验证码错误！");
        }
    }


    private List<String> phoneRegex(String phone) {
        List<String> list = new ArrayList<>();
        String regex = "^[1][3456789]\\d{9}$";
        if (phone.length() != 11) {
            list.add("0");
            list.add("手机号应为11位数");
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (isMatch) {
                list.add("1");
                list.add("手机号是正确格式");
            } else {
                list.add("0");
                list.add("您的手机号" + phone + "是错误格式！！！");
            }
        }
        return list;
    }

    private Object getUserInfo(SysUser userinfo, Map<String, Object> resultMap, String userPasswd, String uuid, String openId,HttpServletRequest request) {
        // 微信公众号拉取授权登录时提示用户名
        String userCodeMsg = "";
        if(StringUtil.isNotBlank(openId)){
            userCodeMsg = "用户名：" + userinfo.getUserCode() + "，";
        }
        //是否招录
        String isRecruit = com.pinde.core.common.GlobalConstant.FLAG_N;
        //超级密码
        boolean userFlag = false;
        // 获取公钥系数和公钥指数
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        resultMap.put("userinfo", userinfo);
        //若是微信一键登录，不判断密码
        if (StringUtil.isBlank(openId)) {
            if (PasswordUtil.isRootPass(userPasswd)) {
                userFlag = true;
            } else {
                //判断密码
                String passwd = StringUtil.defaultString(userinfo.getUserPasswd());
                String appLastLoginErrorTime = userinfo.getAppLastLoginErrorTime();
                String currDateTime2 = DateUtil.getCurrDateTime2();
                String appLoginErrorCountOld = userinfo.getAppLoginErrorCount();
                if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(userinfo.getUserFlow(), userPasswd.trim()))) {
                    if (StringUtil.isNotBlank(appLoginErrorCountOld) && "3".equals(appLoginErrorCountOld)) {
                        if (DateUtil.signMinutesBetweenTowDate(appLastLoginErrorTime, currDateTime2) > 10) {
                            userinfo.setAppLastLoginErrorTime(currDateTime2);
                            userinfo.setAppLoginErrorCount("1");
                            userinfo.setStatusId(UserStatusEnum.Activated.getId());
                            userinfo.setStatusDesc(UserStatusEnum.Activated.getName());
                            jswjwBiz.updateUser(userinfo);
                        }
                    } else {
                        userinfo.setAppLastLoginErrorTime(DateUtil.getCurrDateTime2());
                        int appLoginErrorCount = StringUtil.isBlank(appLoginErrorCountOld) ? 0 : Integer.parseInt(appLoginErrorCountOld);
                        String appLoginErrorCountNew = appLoginErrorCount + 1 + "";
                        userinfo.setAppLoginErrorCount(appLoginErrorCountNew);
                        if ("3".equals(appLoginErrorCountNew)) {
                            userinfo.setStatusId(UserStatusEnum.SysLocked.getId());
                            userinfo.setStatusDesc(UserStatusEnum.SysLocked.getName());
                        }
                        jswjwBiz.updateUser(userinfo);
                    }
                    if (UserStatusEnum.Activated.getId().equals(userinfo.getStatusId())) {
                        return ResultDataThrow("用户名或密码错误");
                    }
                } else {
                    if (StringUtil.isNotBlank(appLoginErrorCountOld) && "3".equals(appLoginErrorCountOld)) {
                        if (DateUtil.signMinutesBetweenTowDate(appLastLoginErrorTime, currDateTime2) > 10) {
                            userinfo.setAppLastLoginErrorTime("");
                            userinfo.setAppLoginErrorCount("");
                            userinfo.setStatusId(UserStatusEnum.Activated.getId());
                            userinfo.setStatusDesc(UserStatusEnum.Activated.getName());
                            jswjwBiz.updateUser(userinfo);
                        }
                    } else {
                        userinfo.setAppLastLoginErrorTime("");
                        userinfo.setAppLoginErrorCount("");
                        jswjwBiz.updateUser(userinfo);
                    }
                    userFlag = true;
                }
            }
        } else {
            userFlag = true;
        }
        String userStatus = userinfo.getStatusId();
        if (UserStatusEnum.SysLocked.getId().equals(userStatus)) {
            return ResultDataThrow(userCodeMsg + "该用户已被锁定，请联系培训基地进行解锁");
        }
        if (UserStatusEnum.Locked.getId().equals(userStatus)) {
            return ResultDataThrow(userCodeMsg + "该用户已被停用，请联系培训基地进行启用");
        }
        if (UserStatusEnum.Lifted.getId().equals(userStatus)) {
            return ResultDataThrow(userCodeMsg + "你暂无权限使用,请联系培训基地管理员！");
        }

        if (userFlag) {
            String userFlow = userinfo.getUserFlow();
            //验证用户是否有角色
            List<SysUserRole> userRoles = jswjwBiz.getSysUserRole(userFlow);

            if (userRoles == null || userRoles.isEmpty()) {
                if(StringUtil.isNotBlank(openId)){
                    userinfo.setOpenId("");
                    jswjwBiz.updateUser(userinfo);
                }
                return ResultDataThrow(userCodeMsg + "用户未赋权");
            }

            boolean isDoctor = false;
            boolean isTeacher = false;
            boolean isHead = false;
            boolean isTeachingHead = false;
            boolean isSeretary = false;
            boolean isTeachingSeretary = false;
            boolean isAdmin = false;
            boolean isCharge = false;
            boolean isNurse = false;
            // 系统配置角色信息
            Map<String, Object> roleMap = jswjwBiz.getUserRoleCfgInfo();
            if (userRoles != null && userRoles.size() > 0) {
                List<Map<String, String>> roles = new ArrayList<>();
                for (SysUserRole sur : userRoles) {
                    Map<String, String> map = new HashMap<>();
                    if (sur.getRoleFlow().equals(roleMap.get("RES_DOCTOR_ROLE_FLOW"))) {
                        isDoctor = true;
                        map.put("roleId", "Student");
                        map.put("roleName", "医师");
                        roles.add(map);
                        resultMap.put("roleId", "Student");
                        resultMap.put("roleName", "医师");
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_ADMIN_ROLE_FLOW"))) {
                        isAdmin = true;
                        map.put("roleId", "Admin");
                        map.put("roleName", "医院管理员");
                        roles.add(map);
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_HEAD_ROLE_FLOW"))) {
                        isHead = true;
                        map.put("roleId", "Head");
                        map.put("roleName", "科主任");
                        roles.add(map);
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_HEADER_ROLE_FLOW"))) {
                        isTeachingHead = true;
                        map.put("roleId", "TeachingHead");
                        map.put("roleName", "教学主任");
                        roles.add(map);
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_TEACHER_ROLE_FLOW"))) {
                        isTeacher = true;
                        map.put("roleId", "Teacher");
                        map.put("roleName", "带教老师");
                        roles.add(map);
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_SECRETARY_ROLE_FLOW"))) {
                        isSeretary = true;
                        map.put("roleId", "Seretary");
                        map.put("roleName", "科秘");
                        roles.add(map);
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_SECRETARYER_ROLE_FLOW"))) {
                        isTeachingSeretary = true;
                        map.put("roleId", "TeachingSeretary");
                        map.put("roleName", "教学秘书");
                        roles.add(map);
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_CHARGE_ROLE_FLOW"))) {
                        isCharge = true;
                        map.put("roleId", "Charge");
                        map.put("roleName", "市局");
                        roles.add(map);
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_NURSE_ROLE_FLOW"))) {
                        isNurse = true;
                        map.put("roleId", "Nurse");
                        map.put("roleName", "护士");
                        roles.add(map);
                    }
                    resultMap.put("isDoctor", isDoctor);
                }
                resultMap.put("roles", roles);
            }
            //如果有两个角色 带教与主任、教秘 则只要判断主任角色权限
            if (isHead) {
                isNurse = false;
                isCharge = false;
                isAdmin = false;
                isTeacher = false;
                isSeretary = false;
                isTeachingSeretary = false;
                isTeachingHead = false;
                resultMap.put("roleId", "Head");
                resultMap.put("roleName", "科主任");
            }
            if (isSeretary) {
                isNurse = false;
                isCharge = false;
                isAdmin = false;
                isTeacher = false;
                isHead = false;
                isTeachingSeretary = false;
                isTeachingHead = false;
                resultMap.put("roleId", "Seretary");
                resultMap.put("roleName", "科秘");
            }
            if (isAdmin) {
                isNurse = false;
                isCharge = false;
                isTeacher = false;
                isSeretary = false;
                isHead = false;
                isTeachingSeretary = false;
                isTeachingHead = false;
                resultMap.put("roleId", "Admin");
                resultMap.put("roleName", "医院管理员");
            }
            if (isTeacher) {
                isNurse = false;
                isCharge = false;
                isAdmin = false;
                isSeretary = false;
                isHead = false;
                isTeachingSeretary = false;
                isTeachingHead = false;
                resultMap.put("roleId", "Teacher");
                resultMap.put("roleName", "带教老师");
            }
            if (isTeachingSeretary) {
                isNurse = false;
                isCharge = false;
                isAdmin = false;
                isSeretary = false;
                isHead = false;
                isTeacher = false;
                isTeachingHead = false;
                resultMap.put("roleId", "TeachingSeretary");
                resultMap.put("roleName", "教学秘书");
            }
            if (isTeachingHead) {
                isNurse = false;
                isCharge = false;
                isAdmin = false;
                isSeretary = false;
                isHead = false;
                isTeacher = false;
                isTeachingSeretary = false;
                resultMap.put("roleId", "TeachingHead");
                resultMap.put("roleName", "教学主任");
            }
            if (isCharge) {
                isAdmin = false;
                isNurse = false;
                isTeacher = false;
                isSeretary = false;
                isHead = false;
                isTeachingSeretary = false;
                isTeachingHead = false;
                resultMap.put("roleId", "Charge");
                resultMap.put("roleName", "市局");
            }
            if (isNurse) {
                isAdmin = false;
                isCharge = false;
                isTeacher = false;
                isSeretary = false;
                isHead = false;
                isTeachingSeretary = false;
                isTeachingHead = false;
                resultMap.put("roleId", "Nurse");
                resultMap.put("roleName", "护士");
            }

            resultMap.put("isDoctor", isDoctor);
            resultMap.put("isTeacher", isTeacher);
            resultMap.put("isSeretary", isSeretary);
            resultMap.put("isHead", isHead);
            resultMap.put("isAdmin", isAdmin);
            resultMap.put("isCharge", isCharge);
            resultMap.put("isNurse", isNurse);
            resultMap.put("isTeachingSeretary", isTeachingSeretary);
            resultMap.put("isTeachingHead", isTeachingHead);

            String isStrongPasswd = com.pinde.core.common.GlobalConstant.FLAG_Y;
            if (StringUtil.isNotBlank(InitConfig.weekPasswordMap.get(userPasswd)) && StringUtil.isBlank(openId)) {
                resultMap.put("userCode", userinfo.getUserCode());
                if (null != publicKey) {
                    //公钥-系数(n)
                    resultMap.put("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                    //公钥-指数(e1)
                    resultMap.put("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                }
                isStrongPasswd = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
            resultMap.put("isStrongPasswd", isStrongPasswd);
            if (isDoctor) {
                if (StringUtil.isBlank(openId)) {
                    if (!PasswordUtil.isRootPass(userPasswd)) {
                        //判断是否为强密码
                        boolean flag = Pattern.matches(regex, userPasswd);
                        if (!flag) {
                            resultMap.put("userCode", userinfo.getUserCode());
                            if (null != publicKey) {
                                //公钥-系数(n)
                                resultMap.put("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                                //公钥-指数(e1)
                                resultMap.put("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                            }
                            isStrongPasswd = com.pinde.core.common.GlobalConstant.FLAG_N;
                        }
                    }

                    //强密码是否过期
                    String changePasswordTime = userinfo.getChangePasswordTime();
                    if (StringUtil.isBlank(changePasswordTime)) {
                        userinfo.setChangePasswordTime(DateUtil.getCurrDate());
                        jswjwBiz.updateUser(userinfo);
                    } else {
                        int passwordFailureTime = Integer.valueOf(StringUtil.isBlank(InitConfig.getSysCfg("Password_Failure_Time")) ? "6" : InitConfig.getSysCfg("Password_Failure_Time"));
                        int monthDiff = DateUtil.getMonthDiff(changePasswordTime, DateUtil.getCurrDate());
                        if (monthDiff >= passwordFailureTime) {
                            resultMap.put("userCode", userinfo.getUserCode());
                            resultMap.put("flag", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            if (null != publicKey) {
                                //公钥-系数(n)
                                resultMap.put("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                                //公钥-指数(e1)
                                resultMap.put("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                            }
                            isStrongPasswd = com.pinde.core.common.GlobalConstant.FLAG_N;
                        }
                    }
                    resultMap.put("isStrongPasswd", isStrongPasswd);
                }

                ResDoctor doctor = jswjwBiz.readResDoctorTwo(userinfo.getUserFlow());
                if (null != userinfo && (null == doctor || !"20".equals(doctor.getDoctorStatusId()))) {
                    isRecruit = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }
                if (null != doctor) {
                    String isChargeOrg = jswjwBiz.getJsResCfgAppMenu(doctor.getDoctorFlow());
                    resultMap.put("isChargeOrg", isChargeOrg);

                    //在校专硕无权限登录
                    boolean isGraduate = "Graduate".equals(doctor.getDoctorTypeId()) ? true : false;
                    resultMap.put("isGraduate", isGraduate);

                    boolean isAudited = jswjwBiz.getRecruitStatus(userinfo.getUserFlow());
                    if (!isRecruit.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                        if (doctor == null) {
                            return ResultDataThrow(userCodeMsg + "登录失败,学员数据出错!");
                        }
                        String orgFlow = "";

                        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                            orgFlow = doctor.getSecondOrgFlow();
                        } else {
                            orgFlow = doctor.getOrgFlow();
                        }
//                        String key1 = jswjwBiz.getJsResCfgCode(orgFlow + "_bind");
//                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(key1)) {
//                            if (StringUtil.isBlank(uuid)) {
//                                return ResultDataThrow("请输入手机uuid");
//                            }
//                            ResUserBindMacid macid = jswjwBiz.readMacidByUserFlow(userinfo.getUserFlow());
//                            if (macid != null && StringUtil.isNotBlank(macid.getMacId()) && !macid.getMacId().equals(uuid)) {
//                                return ResultDataThrow("此账号已绑定其他手机，如需更改绑定手机，请联系管理员解绑");
//                            }
//                            if (macid == null) {
//                                macid = new ResUserBindMacid();
//                                macid.setUserFlow(userinfo.getUserFlow());
//                            }
//                            if (StringUtil.isBlank(macid.getMacId())) {
//                                macid.setMacId(uuid);
//                                jswjwBiz.saveUserMacid(macid);
//                            }
//                        }
                        //黑名单校验
                        //根据用户的信息去黑名单表中查询
                        Map<String, Object> map = new HashMap<>();
                        if (StringUtil.isNotBlank(userinfo.getUserFlow())) {
                            map.put("userFlow", userinfo.getUserFlow());
                        }
                        if (StringUtil.isNotBlank(userinfo.getUserCode())) {
                            map.put("userCode", userinfo.getUserCode());
                        }
                        if (StringUtil.isNotBlank(userinfo.getUserEmail())) {
                            map.put("userEmail", userinfo.getUserEmail());
                        }
                        if (StringUtil.isNotBlank(userinfo.getIdNo())) {
                            map.put("idNo", userinfo.getIdNo());
                        }
                        if (StringUtil.isNotBlank(userinfo.getUserPhone())) {
                            map.put("userPhone", userinfo.getUserPhone());
                        }
                        int blackCount = balcklistExtMapper.selectBlackUser(map);
                        if (blackCount > 0) {
                            return ResultDataThrow(userCodeMsg + "该用户由于个人原因造成违约，用户信息已被纳入我省医务人员诚信系统，5年内不得进入我省培训基地接受住院医师规范化培训。如有相关疑问，请与相关管理部门联系。");
                        }
                        if ("test".equals(userinfo.getUserCode())) {
                            return resultMap;
                        }

                        // 学员n个月不填写数据，限制登录APP add@shengl
//                        checkDoctorDataFilling(resultMap, doctor);
                        if (checkDoctorDataFilling(doctor)) {
                            return ResultDataThrow(userCodeMsg + "由于您长时间未填写培训数据，现无法继续使用APP，请联系管理员！");
                        }

                        if (doctor != null) {
                            if (!isAudited) {
                                return ResultDataThrow(userCodeMsg + "请先等待培训信息审核通过！");
                            }
                            //学员app登录权限审核通过
                            String isAppStatus = jswjwBiz.getJsResCfgCheckByCode("jsres_doctor_app_login_" + userFlow);
                            if (!BaseStatusEnum.Passed.getId().equals(isAppStatus)) {
                                return ResultDataThrow(userCodeMsg + "你暂无权限使用，请联系培训基地管理员或学校培养部门！");
                            }
                            int authCount = jswjwBiz.getDoctorAuth(userinfo.getUserFlow());
                            if (authCount > 0) {
                                resultMap.put("authFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            } else {
                                return ResultDataThrow(userCodeMsg + "登录失败，请先上传学员培训数据登记诚信声明！");
                            }
                            if (StringUtil.isNotBlank(doctor.getTrainingSpeId())) {
                                String trainingType = doctor.getTrainingTypeId();
                                //住院医师缩减调整
                                boolean isReduction = false;
                                if (com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType)) {
                                    String sessionNumber = doctor.getSessionNumber();
                                    String trainingYears = doctor.getTrainingYears();
                                    isReduction = com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType);
                                    isReduction = isReduction && "2015".compareTo(sessionNumber) <= 0;
                                    isReduction = isReduction && (TrainYearEnum.OneYear.getId().equals(trainingYears) || TrainYearEnum.TwoYear.getId().equals(trainingYears));
                                }

                                if (StringUtil.isBlank(doctor.getRotationFlow())) {
                                    return ResultDataThrow(userCodeMsg + "轮转方案未设置，请联系管理员！");
                                } else {
                                    if (isReduction) {
                                        int doctorDeptCount = jswjwBiz.countDoctorSchRotationDept(doctor.getDoctorFlow(), doctor.getRotationFlow());
                                        if (doctorDeptCount == 0) {
                                            return ResultDataThrow(userCodeMsg + "轮转方案未调整减免，请联系管理员！");
                                        }
                                    }
                                }
                            } else {
                                return ResultDataThrow(userCodeMsg + "培训信息暂未审核通过！");
                            }
                        }

                    }
                }

            } else if (isTeacher && !isRecruit.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                SysUser user = jswjwBiz.readSysUser(userinfo.getUserFlow());
                String orgGuoChent = jswjwBiz.getJsResCfgCode("jsres_" + userinfo.getOrgFlow() + "_guocheng");
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(orgGuoChent)) {
                    resultMap.put("isChargeOrg", com.pinde.core.common.GlobalConstant.FLAG_Y);
                } else {
                    resultMap.put("isChargeOrg", com.pinde.core.common.GlobalConstant.FLAG_N);
                }
                String isAppFlag = jswjwBiz.getJsResCfgCode("jsres_teacher_app_login_" + userFlow);
                if (!(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isAppFlag) && "Passed".equals(user.getCheckStatusId()))) {
                    return ResultDataThrow(userCodeMsg + "登录失败，你暂无权限使用，请联系培训基地管理员！");
                }
                resultMap.put("userinfo", userinfo);
                List<SysDept> depts = jswjwBiz.getHeadDeptList(userFlow, userinfo.getDeptFlow());
                resultMap.put("depts", depts);

            } else if (isHead || isSeretary || isAdmin || isTeachingSeretary || isTeachingHead) {
                String orgGuoChent = jswjwBiz.getJsResCfgCode("jsres_" + userinfo.getOrgFlow() + "_guocheng");
                if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(orgGuoChent)) {
                    return ResultDataThrow(userCodeMsg + "登录失败，你暂无权限使用,请联系培训基地管理员！");
                }
                List<SysDept> depts = jswjwBiz.getHeadDeptList(userFlow, userinfo.getDeptFlow());
                resultMap.put("depts", depts);
            } else if (isCharge) {

            } else if (isNurse) {

            } else {
                if(StringUtil.isNotBlank(openId)){
                    userinfo.setOpenId("");
                    jswjwBiz.updateUser(userinfo);
                }
                return ResultDataThrow(userCodeMsg + "用户未赋权");
            }

            if (userinfo != null && (isTeacher || isHead || isCharge || isNurse)) {
                List<Map<String, String>> infos = this.noticeBiz.searchInfoByOrgNotRead("", com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID, com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID, userinfo.getUserFlow());
                if (infos != null) {
                    resultMap.put("hasNotReadInfo", infos.size());
                } else {
                    resultMap.put("hasNotReadInfo", 0);
                }
            }
        }

        HttpSession session = request.getSession();

        SysUser user = new SysUser();

        user.setUserFlow(userinfo.getUserFlow());
        user.setDeptFlow(userinfo.getDeptFlow());

        session.setAttribute(com.pinde.core.common.GlobalConstant.CURR_USER, "isLogin");

        session.setAttribute("user", JSON.toJSONString(user));

        resultMap.put("isRecruit", isRecruit);
//        if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRecruit)){
//            return ResultDataThrow("用户资料不完善暂无权登录！");
//        }
        resultMap.put("token", PkUtil.getUUID());
        return resultMap;
    }

    //数据列表
    @RequestMapping(value = {"/mainNew"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object mainNew(String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        resultMap.put("user", user);
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        resultMap.put("doctor", doctor);
        String currYear = DateUtil.getYear();
        resultMap.put("currYear", currYear);
        String currDateTime = DateUtil.getCurrDateTime2();
        String orgFlow = user.getOrgFlow();
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = doctor.getOrgFlow();
        }
        //如果当前学员是协同基地取他主基地所在的城市
        List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }
        SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = jswjwBiz.findEffective(currDateTime, sysOrg.getOrgCityId());
        //当前城市的学员有没有正在进行的考试
        Boolean inTime = resTestConfigList.size() > 0 ? true : false;
        resultMap.put("inTime", inTime);
        List<JsresExamSignup> signups = jswjwBiz.readDoctorExanSignUps(user.getUserFlow());
        resultMap.put("signups", signups);
        //是否有补考报名的资格（在系统中有资格审核记录且学员为非合格人员才有资格）
        String isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
        // 已结业学员 不允许补考报名 禅道bug：2648
        if (doctor != null && "21".equals(doctor.getDoctorStatusId())) {
            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
        }
        // isAllowApply为N 不能参加补考，无需在做结业申请判断
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setDoctorFlow(doctor.getDoctorFlow());
            recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
            //在系统中是否有资格审核记录
            if (recruitList != null && recruitList.size() > 0) {
                ResDoctorRecruit resDoctorRecruit = recruitList.get(0);
                if (resDoctorRecruit != null) {
                    JsresGraduationApply apply = jswjwBiz.searchByRecruitFlow(resDoctorRecruit.getRecruitFlow(), "");
                    if (apply == null) {
                        // 2019级及以前助理全科学员走补考
                        if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
                        }else{
                            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
                        }
                    }
                }
            }
        }
        // isAllowApply为N 不能参加补考，无需在做成绩判断
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            String isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
            String isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
            String isSkill = com.pinde.core.common.GlobalConstant.FLAG_Y;
            String isTheory = com.pinde.core.common.GlobalConstant.FLAG_Y;
            String validYear = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 3);
//            List<ResScore> resScoreList = resScoreBiz.selectByExampleWithBLOBs(userFlow);
            List<ResScore> resScoreList = jswjwBiz.selectAllScore(user.getUserFlow(), null);
            if (resScoreList != null && resScoreList.size() > 0) {
                //3年内的技能成绩集合
                List<ResScore> skillList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                //3年内的理论成绩集合
                List<ResScore> theoryList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                if (skillList.size() > 0) {
                    for (ResScore resScore : skillList) {
                        if ("1".equals(String.valueOf(resScore.getSkillScore()))) {
                            //3年内的技能成绩有合格的
                            isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                            break;
                        }
                    }
                } else {
                    isSkill = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
                // 如果技能考试没有合格记录 无需在判断理论成绩
                if (theoryList.size() > 0) {
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(isSkillQualifed)) {
                        for (ResScore resScore : theoryList) {
                            if ("1".equals(String.valueOf(resScore.getTheoryScore()))) {
                                //3年内的理论成绩有合格的
                                isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                                break;
                            }
                        }
                    }
                } else {
                    isTheory = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            // 需求变更2018（不含）届以前学员 不做该判断  未参加过考核也可以补考
            if ("2018".compareTo(doctor.getSessionNumber()) <= 0 && com.pinde.core.common.GlobalConstant.FLAG_N.equals(isSkill) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(isTheory)) {
                // 2019/2018/2017级助理全科全走补考报名
                if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                    isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }else{
                    //从未参加过考核
                    isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isSkillQualifed) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isTheoryQualifed)) {
                //3年内理论成绩和技能成绩都合格
                isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
        }
        resultMap.put("isAllowApply", isAllowApply);
        return resultMap;
    }

    @RequestMapping(value = {"/saveDocRegisteManua"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveDocRegisteManua(String registeManua, String userFlow) throws DocumentException {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser currentUser = jswjwBiz.readSysUser(userFlow);
        PubUserResume pubUserResume = jswjwBiz.readPubUserResume(currentUser.getUserFlow());
        if (pubUserResume != null) {
            //旧的数据中xmlContent没有registeManua节点
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean拓展类
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = jswjwBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    userResumeExt.setRegisteManua(registeManua);
                    String newXmlContent = JaxbUtil.convertToXml(userResumeExt);
                    pubUserResume.setUserResume(newXmlContent);
                    jswjwBiz.savePubUserResume(currentUser, pubUserResume);
                }
            }
            resultMap.put("resultId", "200");
            resultMap.put("resultType", "交易成功");
        } else {
            resultMap.put("resultId", "200");
            resultMap.put("resultType", "请到培训信息填写个人基本信息!");
        }
        return resultMap;
    }

    @RequestMapping(value = {"/saveTemRegisteManua"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveTemRegisteManua(String registeManua, String recruitFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isBlank(registeManua) || StringUtil.isBlank(recruitFlow)) {
            return ResultDataThrow("请检查参数");
        }
        String applyYear = DateUtil.getYear();
        jswjwBiz.saveRegisteManua(registeManua, recruitFlow, applyYear);
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        return resultMap;
    }

    @RequestMapping(value = {"/asseApplicationMain"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object asseApplicationNew(String userFlow) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysCfg upload_base_url = cfgMapper.selectByPrimaryKey("upload_base_url");
        if (null != upload_base_url) {
            resultMap.put("upload_base_url", upload_base_url.getCfgValue());
        }
        //获取培训记录
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        resultMap.put("currUser", currUser);
        String doctorFlow = currUser.getUserFlow();
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        // 在培
//		recruit.setDoctorStatusId("20");
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");

        if (recruitList != null && !recruitList.isEmpty()) {
            resultMap.put("recruitList", recruitList);
            recruit = recruitList.get(0);
            String recruitFlow = recruit.getRecruitFlow();
            String applyYear = DateUtil.getYear();

            resultMap.put("auditStatusId", recruit.getAuditStatusId());
            resultMap.put("doctorRecruit", recruit);

            String operUserFlow = currUser.getUserFlow();
            List<ResScore> scorelist = jswjwBiz.selectByRecruitFlowAndScoreType(recruitFlow, com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId());
            //公共成绩
            ResScore publicScore = null;
            if (null != scorelist && scorelist.size() > 0) {
                publicScore = scorelist.get(0);
                //公共科目成绩详情
                String content = null == publicScore ? "" : publicScore.getExtScore();
                Map<String, String> extScoreMap = new HashMap<String, String>();
                if (StringUtil.isNotBlank(content)) {
                    Document doc = DocumentHelper.parseText(content);
                    Element root = doc.getRootElement();
                    Element extScoreInfo = root.element("extScoreInfo");
                    if (extScoreInfo != null) {
                        List<Element> extInfoAttrEles = extScoreInfo.elements();
                        if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                            for (Element attr : extInfoAttrEles) {
                                String attrName = attr.getName();
                                String attrValue = attr.getText();
                                extScoreMap.put(attrName, attrValue);
                            }
                        }
                    }
                }
                resultMap.put("extScore", extScoreMap);
            }
            resultMap.put("publicScore", publicScore);
            //个人信息
            ResDoctor resDoctor = doctorBiz.readDoctor(operUserFlow);
            SysUser sysUser = jswjwBiz.readSysUser(operUserFlow);
            PubUserResume pubUserResume = jswjwBiz.readPubUserResume(operUserFlow);
            Map<String, String> practicingMap = new HashMap<>();
            List<SysDict> practiceTypes = com.pinde.core.common.enums.DictTypeEnum.PracticeType.getSysDictList();
            if (pubUserResume != null) {
                String xmlContent = pubUserResume.getUserResume();
                if (StringUtil.isNotBlank(xmlContent)) {
                    //xml转换成JavaBean
                    UserResumeExtInfoForm userResumeExt = null;
                    userResumeExt = jswjwBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                    if (userResumeExt != null) {
                        if (StringUtil.isNotBlank(userResumeExt.getArmyType())) {
                            userResumeExt.setArmyType(com.pinde.core.common.enums.ArmyTypeEnum.getNameById(userResumeExt.getArmyType()));
                        }
                        if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                            List<SysDict> sysDictList = jswjwBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
                            if (sysDictList != null && !sysDictList.isEmpty()) {
                                for (SysDict dict : sysDictList) {
                                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                        if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                            userResumeExt.setGraduatedName(dict.getDictName());
                                        }
                                    }
                                }

                            }
                        }
                        resultMap.put("userResumeExt", userResumeExt);
//					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
                        String isPassQualifyingExamination = userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
                        String isHaveQualificationCertificate = userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
                        String isHavePracticingCategory = userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isPassQualifyingExamination)) {
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHaveQualificationCertificate)) {

                                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHavePracticingCategory)) {

                                    practicingMap.put("graduationMaterialId", "176");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师执业证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", userResumeExt.getPracticingCategoryLevelId());//执业类型
                                    practicingMap.put("graduationCategoryName", userResumeExt.getPracticingCategoryLevelName());//执业类型
                                    practicingMap.put("graduationScopeId", userResumeExt.getPracticingCategoryScopeId());//执业范围
                                    practicingMap.put("graduationScopeName", userResumeExt.getPracticingCategoryScopeName());//执业范围
                                    String practicingCategoryLevelId = userResumeExt.getPracticingCategoryLevelId();
                                    String practicingCategoryLevelName = userResumeExt.getPracticingCategoryLevelName().replace("\t", "").replace("\n", "");
                                    if (StringUtil.isNotBlank(practicingCategoryLevelId) && StringUtil.isEmpty(practicingCategoryLevelName)) {
                                        //执业类型
                                        if (CollectionUtils.isNotEmpty(practiceTypes)) {
                                            List<SysDict> collect = practiceTypes.stream().filter(sysDict -> practicingCategoryLevelId.equals(sysDict.getDictId())).collect(Collectors.toList());
                                            if (CollectionUtils.isNotEmpty(collect)) {
                                                practicingMap.put("graduationCategoryName", collect.get(0).getDictName());//执业类型
                                            }
                                        }
                                    }
                                    //执业范围
                                    List<SysDict> sysDicts = dictBizImpl.searchDictListByDictTypeId("PracticeType." + practicingCategoryLevelId);
                                    String practicingCategoryScopeId = userResumeExt.getPracticingCategoryScopeId();
                                    String practicingCategoryScopeName = userResumeExt.getPracticingCategoryScopeName().replace("\t", "").replace("\n", "");
                                    if (StringUtil.isNotEmpty(practicingCategoryScopeId) && StringUtil.isEmpty(practicingCategoryScopeName) && CollectionUtils.isNotEmpty(sysDicts)) {
                                        List<SysDict> collect = sysDicts.stream().filter(sysDict -> practicingCategoryScopeId.equals(sysDict.getDictId())).collect(Collectors.toList());
                                        if (CollectionUtils.isNotEmpty(collect)) {
                                            practicingMap.put("graduationScopeName", collect.get(0).getDictName());//执业范围
                                        }
                                    }
                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                                } else {

                                    practicingMap.put("graduationMaterialId", "177");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师资格证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                    practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                    practicingMap.put("graduationScopeId", "暂无");//执业范围
                                    practicingMap.put("graduationScopeName", "暂无");//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                                }

                            } else {

                                practicingMap.put("graduationMaterialId", userResumeExt.getQualificationMaterialId2());//报考资格材料
                                practicingMap.put("graduationMaterialName", userResumeExt.getQualificationMaterialName2());//报考资格材料

                                practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getQualificationMaterialId2Url());//资格证书url

                                practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                practicingMap.put("graduationScopeId", "暂无");//执业范围
                                practicingMap.put("graduationScopeName", "暂无");//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                            }

                        } else {
                            practicingMap.put("graduationMaterialId", "暂无");//报考资格材料
                            practicingMap.put("graduationMaterialName", "暂无");//报考资格材料

                            practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                            practicingMap.put("graduationMaterialUri", "暂无");//资格证书url

                            practicingMap.put("graduationCategoryId", "暂无");//执业类型
                            practicingMap.put("graduationCategoryName", "暂无");//执业类型

                            practicingMap.put("graduationScopeId", "暂无");//执业范围
                            practicingMap.put("graduationScopeName", "暂无");//执业范围

                            practicingMap.put("graduationMaterialTime", "暂无");//取得时间
                        }
                    }
                }
            }
            if (null != practicingMap) {
                for (String s : practicingMap.keySet()) {
                    if ("graduationCategoryName".equals(s) || "graduationScopeName".equals(s)) {
                        if (null != practicingMap.get(s)) {
                            practicingMap.put(s, practicingMap.get(s).replace("\t", "").replace("\n", ""));
                        }
                    }
                }
            }
            resultMap.put("practicingMap", practicingMap);
            //结业考核年份不空且小于当前年份的
            if (recruit != null && StringUtil.isNotBlank(recruit.getGraduationYear()) && recruit.getGraduationYear().compareTo(applyYear) < 0) {
                applyYear = recruit.getGraduationYear();
            }
            JsresGraduationApply jsresGraduationApply = jswjwBiz.searchByRecruitFlow(recruitFlow, applyYear);
            //保存医师培训时间
            if (recruit != null) {
                String endTime = "";
                String startTime = "";
                //开始时间
                String recruitDate = recruit.getRecruitDate();
                //培养年限
                String trianYear = recruit.getTrainYear();
                String graudationYear = recruit.getGraduationYear();
                if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(graudationYear)) {
                    try {
                        int year = 0;
                        year = Integer.valueOf(graudationYear) - Integer.valueOf(recruitDate.substring(0, 4));
                        if (year != 0) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = sdf.parse(recruitDate);
                            startTime = recruitDate;
                            //然后使用Calendar操作日期
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                            calendar.add(Calendar.DATE, -1);
                            //把得到的日期格式化成字符串类型的时间
                            endTime = sdf.format(calendar.getTime());
                        }
                    } catch (Exception e) {
                        endTime = "";
                    }
                }
                //如果没有结业考核年份，按照届别与培养年限计算
                if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(trianYear) && StringUtil.isBlank(endTime)) {
                    int year = 0;
                    if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(trianYear)) {
                        year = 1;
                    }
                    if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(trianYear)) {
                        year = 2;
                    }
                    if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(trianYear)) {
                        year = 3;
                    }
                    if (year != 0) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = sdf.parse(recruitDate);
                            startTime = recruitDate;
                            //然后使用Calendar操作日期
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                            calendar.add(Calendar.DATE, -1);
                            //把得到的日期格式化成字符串类型的时间
                            endTime = sdf.format(calendar.getTime());

                        } catch (Exception e) {

                        }
                    }
                }
                resultMap.put("startDate", startTime);
                resultMap.put("endDate", endTime);
            }
            String apply = com.pinde.core.common.GlobalConstant.FLAG_N;
            //判断为当前年，当前提交时间段内，学员非二阶段，且未提交的可以提交
            String currYear = DateUtil.getYear();
            String currTime = DateUtil.getCurrDateTime();
            String currDateTime = DateUtil.getCurrDateTime2();
            String orgFlow = sysUser.getOrgFlow();
            if (StringUtil.isBlank(orgFlow)) {
                orgFlow = resDoctor.getOrgFlow();
            }
            List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
            if (resJointOrgList != null && resJointOrgList.size() > 0) {
                orgFlow = resJointOrgList.get(0).getOrgFlow();
            }
            SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
            List<ResTestConfig> resTestConfigList = jswjwBiz.findEffective(currDateTime, sysOrg.getOrgCityId());
            //当前城市的学员有没有正在进行的考试
            Boolean inTime = resTestConfigList.size() > 0 ? true : false;
            String inApplyTime = resTestConfigList.size() > 0 ? com.pinde.core.common.GlobalConstant.FLAG_Y : com.pinde.core.common.GlobalConstant.FLAG_N;
            // 判断学员有没有重新提交
            if (null != jsresGraduationApply) {
                // 查询结业申请创建时间内的考试时间没有结束的考试信息
                resTestConfigList = jswjwBiz.findEffectiveByParam(currDateTime, DateUtil.transDateTime(jsresGraduationApply.getCreateTime()), sysOrg.getOrgCityId());
                inApplyTime = resTestConfigList.size() > 0 ? com.pinde.core.common.GlobalConstant.FLAG_Y : com.pinde.core.common.GlobalConstant.FLAG_N;
            }
            Boolean isWaitAudit = false;//是否待审核
            if (jsresGraduationApply != null && StringUtil.isNotBlank(jsresGraduationApply.getAuditStatusId())) {
                isWaitAudit = true;
            }
            if (recruit != null && currYear.equals(recruit.getGraduationYear()) && inTime
                    && !com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(recruit.getCatSpeId())
                    && !isWaitAudit) {
                apply = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
            //是否是全科、助理全科、全科方向（中医）、全科方向（西医）
            String isAssiGeneral = "";
            if (recruit != null && ("0700".equals(recruit.getSpeId()) || "51".equals(recruit.getSpeId()) || "52".equals(recruit.getSpeId())
                    || "18".equals(recruit.getSpeId()) || "50".equals(recruit.getSpeId()))) {
                isAssiGeneral = com.pinde.core.common.GlobalConstant.FLAG_Y;
            } else {
                isAssiGeneral = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
            List<PubFile> files = pubFileBiz.findFileByTypeFlow("asseApplication", recruitFlow);
            PubFile file = null;
            if (files != null && files.size() > 0) {
                file = files.get(0);
                if (null != file && StringUtil.isNotEmpty(file.getFilePath())) {
                    file.setFilePath(file.getFilePath().replaceAll("\\\\", "/"));
                }
                resultMap.put("file", file);
            }
            resultMap.put("inApplyTime", inApplyTime);
//            apply = com.pinde.core.common.GlobalConstant.FLAG_Y;
            resultMap.put("apply", apply);
            resultMap.put("resDoctor", resDoctor);
            resultMap.put("user", sysUser);
            resultMap.put("isAssiGeneral", isAssiGeneral);
            resultMap.put("jsresGraduationApply", jsresGraduationApply);
            resultMap.put("recruitFlow", recruitFlow);
            showMaterials(resultMap, recruit, applyYear, jsresGraduationApply);
            List<Map<String, String>> spes = new ArrayList<>();
            if (null != recruit) {
                Map<String, List<Map<String, String>>> speMap = setSpeMap();
                spes = speMap.get(recruit.getSpeId());
            }
            if (spes != null && spes.size() > 0) {
                resultMap.put("needChange", com.pinde.core.common.GlobalConstant.FLAG_Y);
                resultMap.put("spes", spes);
            } else {
                resultMap.put("needChange", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
            // 2019级以前的助理全科学员只能走补考报名
            if("2019".compareTo(resDoctor.getSessionNumber()) >= 0 && "50".equals(resDoctor.getTrainingSpeId())){
                resultMap.put("submitBtnShow", com.pinde.core.common.GlobalConstant.FLAG_N);
            }else{
                resultMap.put("submitBtnShow", com.pinde.core.common.GlobalConstant.FLAG_Y);
            }
        }
        //处理轮转数据
        assDataList(resultMap);
        return resultMap;
    }

    @RequestMapping(value = {"/asseApply"}, method = {RequestMethod.POST})
    @ResponseBody
    public synchronized Object asseApply(String recruitFlow, String applyYear, String changeSpeId, String remark, String userFlow, String doctorFlow, String lawScore, String medicineScore, String clinicalScore, String ckScore) throws DocumentException {
        Map<String, Object> resultMap = new HashMap<>();
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (StringUtil.isEmpty(recruitFlow)) {
            return ResultDataThrow("recruitFlow为空");
        }
        if (StringUtil.isEmpty(doctorFlow)) {
            return ResultDataThrow("doctorFlow为空");
        }
        userFlow = doctorFlow;
        com.pinde.core.model.ResDoctorRecruit recruit = jswjwBiz.getNewRecruitTwo(recruitFlow);
        if (!applyYear.equals(recruit.getGraduationYear())) {
            return ResultDataThrow("结业考核年份不是当前年，无法申请！");
        }
        JsresGraduationApply jsresGraduationApply = jswjwBiz.searchByRecruitFlow(recruitFlow, "");
        if (null != jsresGraduationApply) {

            PubUserResume pubUserResume = jswjwBiz.readPubUserResume(recruit.getDoctorFlow());
            Map<String, String> practicingMap = new HashMap<>();

            if (pubUserResume != null) {
                String xmlContent = pubUserResume.getUserResume();
                if (StringUtil.isNotBlank(xmlContent)) {
                    //xml转换成JavaBean
                    UserResumeExtInfoForm userResumeExt = null;
                    userResumeExt = jswjwBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                    if (userResumeExt != null) {
//					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
                        String isPassQualifyingExamination = userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
                        String isHaveQualificationCertificate = userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
                        String isHavePracticingCategory = userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isPassQualifyingExamination)) {
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHaveQualificationCertificate)) {

                                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHavePracticingCategory)) {

                                    practicingMap.put("graduationMaterialId", "176");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师执业证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", userResumeExt.getPracticingCategoryLevelId());//执业类型
                                    practicingMap.put("graduationCategoryName", userResumeExt.getPracticingCategoryLevelName());//执业类型

                                    practicingMap.put("graduationScopeId", userResumeExt.getPracticingCategoryScopeId());//执业范围
                                    practicingMap.put("graduationScopeName", userResumeExt.getPracticingCategoryScopeName());//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间

                                } else {

                                    practicingMap.put("graduationMaterialId", "177");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师资格证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                    practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                    practicingMap.put("graduationScopeId", "暂无");//执业范围
                                    practicingMap.put("graduationScopeName", "暂无");//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                                }

                            } else {

                                practicingMap.put("graduationMaterialId", userResumeExt.getQualificationMaterialId2());//报考资格材料
                                practicingMap.put("graduationMaterialName", userResumeExt.getQualificationMaterialName2());//报考资格材料

                                practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getQualificationMaterialId2Url());//资格证书url

                                practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                practicingMap.put("graduationScopeId", "暂无");//执业范围
                                practicingMap.put("graduationScopeName", "暂无");//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                            }

                        } else {
                            practicingMap.put("graduationMaterialId", "暂无");//报考资格材料
                            practicingMap.put("graduationMaterialName", "暂无");//报考资格材料

                            practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                            practicingMap.put("graduationMaterialUri", "暂无");//资格证书url

                            practicingMap.put("graduationCategoryId", "暂无");//执业类型
                            practicingMap.put("graduationCategoryName", "暂无");//执业类型

                            practicingMap.put("graduationScopeId", "暂无");//执业范围
                            practicingMap.put("graduationScopeName", "暂无");//执业范围

                            practicingMap.put("graduationMaterialTime", "暂无");//取得时间
                        }
                    }
                }
            }

            //培训记录
            //培训方案
            SchRotation rotation = jswjwBiz.getRotationByRecruit(recruit);
            jsresGraduationApply = new JsresGraduationApply();
            jsresGraduationApply.setRemark(remark);
            jsresGraduationApply.setRecruitFlow(recruitFlow);
            if (null != rotation) {
                jsresGraduationApply.setRotationFlow(rotation.getRotationFlow());
                jsresGraduationApply.setRotationName(rotation.getRotationName());
            }
            jsresGraduationApply.setApplyYear(applyYear);
            String orgFlow = "";
            if (null != user && StringUtil.isNotEmpty(user.getOrgFlow())) {
                orgFlow = user.getOrgFlow();
            }
            //如果user表中没有org_flow去医师表中的
            if (StringUtil.isBlank(orgFlow)) {
                ResDoctor resDoctor = jswjwBiz.readDoctor(doctorFlow);
                orgFlow = resDoctor.getOrgFlow();
            }
            List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
            //如果当前学员是协同基地取他主基地所在的城市在当前时间有没有正在进行的考试
            if (resJointOrgList != null && resJointOrgList.size() > 0) {
                orgFlow = resJointOrgList.get(0).getOrgFlow();
            }
            SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
            List<ResTestConfig> resTestConfigList = jswjwBiz.findEffective(DateUtil.getCurrDateTime2(), sysOrg.getOrgCityId());
            if (resTestConfigList.size() > 0) {
                ResTestConfig resTestConfig = resTestConfigList.get(0);
                jsresGraduationApply.setTestId(resTestConfig.getTestId());
                //判断需不需要基地审核，需要则是待基地审核，不要要再判断需不需要市局审核，需要则是待市局审核，都不需要则是待省厅审核
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getLocalAudit())) {
                    jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
                    jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
                } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                    jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                    jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
                } else {
                    jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                    jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
                }
            } else {
                resultMap.put("resultType", "当前时间没有正在进行的考试");
                return resultMap;
//                return "当前时间没有正在进行的考试！";
            }

            int i = jswjwBiz.editGraduationApply2(jsresGraduationApply, recruitFlow, changeSpeId, recruit.getDoctorFlow(), applyYear, practicingMap, user);
            if (StringUtil.isNotEmpty(lawScore)) {
                saveAsseScore(lawScore, "lawScore", recruitFlow, userFlow);
            }
            if (StringUtil.isNotEmpty(medicineScore)) {
                saveAsseScore(medicineScore, "medicineScore", recruitFlow, userFlow);
            }
            if (StringUtil.isNotEmpty(clinicalScore)) {
                saveAsseScore(clinicalScore, "clinicalScore", recruitFlow, userFlow);
            }
            if (StringUtil.isNotEmpty(ckScore)) {
                saveAsseScore(ckScore, "ckScore", recruitFlow, userFlow);
            }
            resultMap.put("resultId", "200");
            resultMap.put("resultType", "提交成功！");
            return resultMap;
        } else {
            resultMap.put("resultId", "200");
            resultMap.put("resultType", "已有提交记录！");
            return resultMap;
        }
    }

    @RequestMapping(value = {"/reAsseApply"}, method = {RequestMethod.POST})
    @ResponseBody
    public synchronized Object reAsseApply(String applyYear, String doctorFlow, String recruitFlow, String remark, String userFlow, String lawScore, String medicineScore, String clinicalScore, String ckScore) throws DocumentException {
        Map<String, Object> resultMap = new HashMap<>();
        SysUser user = jswjwBiz.readSysUser(doctorFlow);
        if (StringUtil.isBlank(applyYear)) {
            return ResultDataThrow("请选择申请年份！");
        }
        if (StringUtil.isBlank(doctorFlow)) {
            return ResultDataThrow("请填写学员流水号！");
        }
        if (StringUtil.isBlank(recruitFlow)) {
            return ResultDataThrow("请填写培训记录流水号！");
        }
        userFlow = doctorFlow;
        com.pinde.core.model.ResDoctorRecruit recruit = jswjwBiz.getNewRecruitTwo(recruitFlow);
        if (!applyYear.equals(recruit.getGraduationYear())) {
            return ResultDataThrow("结业考核年份不是当前年，无法更新数据！");
        }
        JsresGraduationApply jsresGraduationApply = jswjwBiz.searchByRecruitFlow(recruitFlow, applyYear);
        if (null == jsresGraduationApply) {
            return ResultDataThrow("未提交过申请，无法更新数据！");
        }
        PubUserResume pubUserResume = jswjwBiz.readPubUserResume(recruit.getDoctorFlow());
        Map<String, String> practicingMap = new HashMap<>();

        if (pubUserResume != null) {
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = jswjwBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
//					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
                    String isPassQualifyingExamination = userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
                    String isHaveQualificationCertificate = userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
                    String isHavePracticingCategory = userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isPassQualifyingExamination)) {
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHaveQualificationCertificate)) {

                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHavePracticingCategory)) {

                                practicingMap.put("graduationMaterialId", "176");//报考资格材料
                                practicingMap.put("graduationMaterialName", "医师执业证书");//报考资格材料

                                practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

                                practicingMap.put("graduationCategoryId", userResumeExt.getPracticingCategoryLevelId());//执业类型
                                practicingMap.put("graduationCategoryName", userResumeExt.getPracticingCategoryLevelName());//执业类型

                                practicingMap.put("graduationScopeId", userResumeExt.getPracticingCategoryScopeId());//执业范围
                                practicingMap.put("graduationScopeName", userResumeExt.getPracticingCategoryScopeName());//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间

                            } else {

                                practicingMap.put("graduationMaterialId", "177");//报考资格材料
                                practicingMap.put("graduationMaterialName", "医师资格证书");//报考资格材料

                                practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

                                practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                practicingMap.put("graduationScopeId", "暂无");//执业范围
                                practicingMap.put("graduationScopeName", "暂无");//执业范围

                                practicingMap.put("graduationMaterialTime", "暂无");//取得时间

                            }

                        } else {

                            practicingMap.put("graduationMaterialId", userResumeExt.getQualificationMaterialId2());//报考资格材料
                            practicingMap.put("graduationMaterialName", userResumeExt.getQualificationMaterialName2());//报考资格材料

                            practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                            practicingMap.put("graduationMaterialUri", userResumeExt.getQualificationMaterialId2Url());//资格证书url

                            practicingMap.put("graduationCategoryId", "暂无");//执业类型
                            practicingMap.put("graduationCategoryName", "暂无");//执业类型

                            practicingMap.put("graduationScopeId", "暂无");//执业范围
                            practicingMap.put("graduationScopeName", "暂无");//执业范围

                            practicingMap.put("graduationMaterialTime", "暂无");//取得时间

                        }

                    } else {
                        practicingMap.put("graduationMaterialId", "暂无");//报考资格材料
                        practicingMap.put("graduationMaterialName", "暂无");//报考资格材料

                        practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                        practicingMap.put("graduationMaterialUri", "暂无");//资格证书url

                        practicingMap.put("graduationCategoryId", "暂无");//执业类型
                        practicingMap.put("graduationCategoryName", "暂无");//执业类型

                        practicingMap.put("graduationScopeId", "暂无");//执业范围
                        practicingMap.put("graduationScopeName", "暂无");//执业范围

                        practicingMap.put("graduationMaterialTime", "暂无");//取得时间

                    }
                }
            }
        }
        //培训记录
        //培训方案
        SchRotation rotation = jswjwBiz.getRotationByRecruit(recruit);
        jsresGraduationApply.setRemark(remark);
        jsresGraduationApply.setRecruitFlow(recruitFlow);
        if (null != rotation) {
            jsresGraduationApply.setRotationFlow(rotation.getRotationFlow());
            jsresGraduationApply.setRotationName(rotation.getRotationName());
        }
        jsresGraduationApply.setApplyYear(applyYear);
        String orgFlow = "";
        if (null != user && StringUtil.isNotEmpty(user.getOrgFlow())) {
            orgFlow = user.getOrgFlow();
        }
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            ResDoctor resDoctor = jswjwBiz.readDoctor(doctorFlow);
            if (null != resDoctor) {
                orgFlow = resDoctor.getOrgFlow();
            }
        }
        //如果当前学员是协同基地取他主基地所在的城市
        List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }
        SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = jswjwBiz.findEffectiveByParam(DateUtil.getCurrDateTime2(), DateUtil.transDateTime(jsresGraduationApply.getCreateTime()), sysOrg.getOrgCityId());
        if (resTestConfigList.size() > 0) {
            ResTestConfig resTestConfig = resTestConfigList.get(0);
            jsresGraduationApply.setTestId(resTestConfig.getTestId());
            //更新之后从头开始审核，判断需不需要基地审核，需要则是待基地审核，不要要再判断需不需要市局审核，需要则是待市局审核，都不需要则是待省厅审核
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getLocalAudit())) {
                jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
                jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
            } else {
                jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
            }
        }
        int i = jswjwBiz.editGraduationApply2(jsresGraduationApply, recruitFlow, "", recruit.getDoctorFlow(), applyYear, practicingMap, user);
        if (StringUtil.isNotEmpty(lawScore)) {
            saveAsseScore(lawScore, "lawScore", recruitFlow, userFlow);
        }
        if (StringUtil.isNotEmpty(medicineScore)) {
            saveAsseScore(medicineScore, "medicineScore", recruitFlow, userFlow);
        }
        if (StringUtil.isNotEmpty(clinicalScore)) {
            saveAsseScore(clinicalScore, "clinicalScore", recruitFlow, userFlow);
        }
        if (StringUtil.isNotEmpty(ckScore)) {
            saveAsseScore(ckScore, "ckScore", recruitFlow, userFlow);
        }
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "提交成功！");
        return resultMap;
    }

    private void showMaterials(Map<String, Object> map, com.pinde.core.model.ResDoctorRecruit recruit, String applyYear, JsresGraduationApply jsresGraduationApply) throws DocumentException {
        //培训方案
        SchRotation rotation = null;
        if (recruit != null && StringUtil.isNotBlank(recruit.getRotationFlow())) {
            rotation = jswjwBiz.readSchRotation(recruit.getRotationFlow());
        } else {
            rotation = jswjwBiz.getRotationByRecruit(recruit);
        }
        if (rotation != null && recruit != null && StringUtil.isNotBlank(rotation.getRotationFlow())) {
            map.put("rotation", rotation);
            String doctorFlow = recruit.getDoctorFlow();
            String rotationFlow = rotation.getRotationFlow();
            ResDoctor resDoctor = jswjwBiz.findByFlow(doctorFlow);
            resDoctor.setRotationFlow(rotationFlow);
            //学员的减免方案
            List<SchDoctorDept> doctorDeptList = jswjwBiz.searchDoctorDeptForReductionIgnoreStatus(doctorFlow, rotationFlow);
            Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
            if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
                for (SchDoctorDept sdd : doctorDeptList) {
                    String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
                    doctorDeptMap.put(key, sdd);
                }
            }
            Map<String, Integer> groupRowSpan = new HashMap<>();
            Map<String, Integer> deptRowSpan = new HashMap<>();


            //方案中的组
            List<SchRotationGroup> groupList = jswjwBiz.searchSchRotationGroup(rotationFlow);
            map.put("groupList", groupList);
            //方案中的科室
            List<SchRotationDept> deptList = jswjwBiz.searchSchRotationDeptTwo(rotationFlow);

            Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
            Map<String, Object> afterImgMap = new HashMap<String, Object>();
            Map<String, List<SchArrangeResult>> resultMap1 = new HashMap<String, List<SchArrangeResult>>();
            Map<String, Float> realMonthMap = new HashMap<String, Float>();
            Map<String, Object> biMap = new HashMap<>();
            Map<String, Object> avgBiMap = new HashMap<>();
            float allMonth = 0;

            //计算轮转时间内登记进度
			/*List<ResRec> recList = new ArrayList<ResRec>();
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctorFlow",resDoctor.getDoctorFlow());*/
            for (SchRotationDept dept : deptList) {
                List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
                if (temp == null) {
                    temp = new ArrayList<SchRotationDept>();
                }
                rotationDeptMap.put(dept.getGroupFlow(), temp);
                String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
                SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
                if (reductionDept != null) {
                    if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())) {
                        continue;
                    }
                    String reductionSchMonth = reductionDept.getSchMonth();
                    dept.setSchMonth(reductionSchMonth);
                }
                Integer count = groupRowSpan.get(dept.getGroupFlow());
                if (count == null)
                    count = 0;
                count++;
                groupRowSpan.put(dept.getGroupFlow(), count);
                temp.add(dept);
                // 优化：此处查询之后并未使用recs，暂时注释
				/*String groupFlow = dept.getGroupFlow();
				String standardDeptId = dept.getStandardDeptId();
				paramMap.put("standardGroupFlow",groupFlow);
				paramMap.put("standardDeptId",standardDeptId);
				paramMap.put("processFlow",dept.getRecordFlow());*/
				/*List<ResRec> recs = resRecBiz.searchProssingRec(paramMap);
				if(recs!=null && !recs.isEmpty()){
					recList.addAll(recs);
				}*/
                //轮转科室
                List<SchArrangeResult> resultList = jswjwBiz.searchResultByGroupFlow(dept.getGroupFlow(), dept.getStandardDeptId(), doctorFlow);
                if (resultList != null && resultList.size() > 0) {
                    String key = dept.getGroupFlow() + dept.getStandardDeptId();
                    resultMap1.put(key, resultList);
                    Integer t = groupRowSpan.get(dept.getGroupFlow());
                    if (t == null)
                        t = 0;
                    groupRowSpan.put(dept.getGroupFlow(), resultList.size() + t);
                    deptRowSpan.put(key, resultList.size());
                    for (SchArrangeResult result : resultList) {
                        Float month = realMonthMap.get(key);
                        if (month == null) {
                            month = 0f;
                        }
                        String realMonth = result.getSchMonth();
                        if (StringUtil.isNotBlank(realMonth)) {
                            Float realMonthF = Float.parseFloat(realMonth);
                            month += realMonthF;
                            allMonth += realMonthF;
                        }
                        realMonthMap.put(key, month);
                    }
                } else {

                    Integer t = groupRowSpan.get(dept.getGroupFlow());
                    if (t == null)
                        t = 0;
                    groupRowSpan.put(dept.getGroupFlow(), 1 + t);
                }

                //完成比例与审核比例
//				JsresDoctorDeptDetail doctorDeptDetail=resultBiz.deptDoctorWorkDetail(dept.getRecordFlow(),dept.getRotationFlow(),doctorFlow);
//				biMap.put(dept.getRecordFlow(),doctorDeptDetail);

            }
            if (jsresGraduationApply == null) {

                //出科考核表
                List<ResSchProcessExpress> recs = jswjwBiz.searchByUserFlowAndTypeIdTwo(doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
                if (recs != null && recs.size() > 0) {
                    for (ResSchProcessExpress rec : recs) {
                        List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
                        String content = null == rec ? "" : rec.getRecContent();
                        if (StringUtil.isNotBlank(content)) {
                            Document doc = DocumentHelper.parseText(content);
                            Element root = doc.getRootElement();
                            List<Element> imageEles = root.elements();
                            if (imageEles != null && imageEles.size() > 0) {
                                for (Element image : imageEles) {
                                    Map<String, Object> recContent = new HashMap<String, Object>();
                                    String imageFlow = image.attributeValue("imageFlow");
                                    List<Element> elements = image.elements();
                                    for (Element attr : elements) {
                                        String attrName = attr.getName();
                                        String attrValue = attr.getText();
                                        recContent.put(attrName, attrValue);
                                    }
                                    imagelist.add(recContent);
                                }
                            }
                        }
                        afterImgMap.put(rec.getSchRotationDeptFlow(), imagelist);
                    }
                }
                //完成比例与审核比例
                List<JsresDoctorDeptDetail> details = jswjwBiz.deptDoctorAllWorkDetailByNow(recruit.getRecruitFlow(), doctorFlow, applyYear);
                if (details != null && details.size() > 0) {
                    int isShortY = 0;
                    int isShortN = 0;
                    int shortYCount = 0;
                    int shortNCount = 0;
                    double shortYCBSum = 0;//完成比例
                    double shortNCBSum = 0;
                    double shortYOCBSum = 0;//补填比例
                    double shortNOCBSum = 0;
                    double shortYABSum = 0;//审核 比例
                    double shortNABSum = 0;
                    double avgComBi = 0;//平均完成比例
                    double avgOutComBi = 0;//平均补填比例
                    double avgAuditComBi = 0;//平均审核比例
                    for (JsresDoctorDeptDetail d : details) {
                        biMap.put(d.getSchStandardDeptFlow(), d);
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(d.getIsAdd())) {
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(d.getIsShort())) {
                                if (!"-".equals(d.getCompleteBi())) {
                                    shortYCount++;
                                    shortYCBSum += StringUtil.isBlank(d.getCompleteBi()) ? 0 : "-".equals(d.getCompleteBi()) ? 0 : Double.valueOf(d.getCompleteBi());
                                    shortYOCBSum += StringUtil.isBlank(d.getOutCompleteBi()) ? 0 : "-".equals(d.getOutCompleteBi()) ? 0 : Double.valueOf(d.getOutCompleteBi());
                                    shortYABSum += StringUtil.isBlank(d.getAuditBi()) ? 0 : "-".equals(d.getAuditBi()) ? 0 : Double.valueOf(d.getAuditBi());
                                }
                                if (isShortY == 0) {
                                    isShortY = 1;
                                }
                            }
                            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(d.getIsShort())) {
                                if (!"-".equals(d.getCompleteBi())) {
                                    shortNCount++;
                                    shortNCBSum += StringUtil.isBlank(d.getCompleteBi()) ? 0 : "-".equals(d.getCompleteBi()) ? 0 : Double.valueOf(d.getCompleteBi());
                                    shortNOCBSum += StringUtil.isBlank(d.getOutCompleteBi()) ? 0 : "-".equals(d.getOutCompleteBi()) ? 0 : Double.valueOf(d.getOutCompleteBi());
                                    shortNABSum += StringUtil.isBlank(d.getAuditBi()) ? 0 : "-".equals(d.getAuditBi()) ? 0 : Double.valueOf(d.getAuditBi());
                                }
                                if (isShortN == 0) {
                                    isShortN = 1;
                                }
                            }
                        }
                    }
                    //平均完成比例与平均审核比例
                    if ((isShortY + isShortN) > 1) {
                        if ((shortYCount) != 0) {
                            avgComBi = new BigDecimal(shortYCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgOutComBi = new BigDecimal(shortYOCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgAuditComBi = new BigDecimal(shortYABSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        }
                    } else {
                        if ((shortYCount + shortNCount) != 0) {
                            avgComBi = new BigDecimal((shortYCBSum + shortNCBSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgOutComBi = new BigDecimal((shortYOCBSum + shortNOCBSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgAuditComBi = new BigDecimal((shortYABSum + shortNABSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        }
                    }
                    avgBiMap.put("avgComplete", avgComBi);
                    avgBiMap.put("avgOutComplete", avgOutComBi);
                    avgBiMap.put("avgAudit", avgAuditComBi);
                }
            }
            map.put("allMonth", allMonth);
            if (jsresGraduationApply != null) {
                //完成比例与审核比例
                List<JsresDoctorDeptDetail> details = jswjwBiz.deptDoctorAllWorkDetailByNow(rotation.getRotationFlow(), doctorFlow, applyYear);
                if (details != null && details.size() > 0) {
                    for (JsresDoctorDeptDetail d : details) {
                        biMap.put(d.getSchStandardDeptFlow(), d);
                    }
                }
                //平均完成比例与平均审核比例
                avgBiMap = jswjwBiz.doctorDeptAvgWorkDetail(recruit.getRecruitFlow(), applyYear);

                //出科考核表
                List<ResSchProcessExpress> recs = jswjwBiz.searchByUserFlowAndTypeIdTwo(doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
                if (recs != null && recs.size() > 0) {
                    for (ResSchProcessExpress rec : recs) {
                        List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
                        String content = null == rec ? "" : rec.getRecContent();
                        if (StringUtil.isNotBlank(content)) {
                            Document doc = DocumentHelper.parseText(content);
                            Element root = doc.getRootElement();
                            List<Element> imageEles = root.elements();
                            if (imageEles != null && imageEles.size() > 0) {
                                for (Element image : imageEles) {
                                    Map<String, Object> recContent = new HashMap<String, Object>();
                                    String imageFlow = image.attributeValue("imageFlow");
                                    List<Element> elements = image.elements();
                                    for (Element attr : elements) {
                                        String attrName = attr.getName();
                                        String attrValue = attr.getText();
                                        recContent.put(attrName, attrValue);
                                    }
                                    imagelist.add(recContent);
                                }
                            }
                        }
                        afterImgMap.put(rec.getSchRotationDeptFlow(), imagelist);
                    }
                }
            }
            map.put("avgBiMap", avgBiMap);
            map.put("biMap", biMap);//各科室比例
            map.put("rotationDeptMap", rotationDeptMap);
            map.put("afterImgMap", afterImgMap);

            map.put("resultMap1", resultMap1);
            map.put("groupRowSpan", groupRowSpan);
            map.put("deptRowSpan", deptRowSpan);
            map.put("realMonthMap", realMonthMap);

        }
    }

    private Map<String, List<Map<String, String>>> setSpeMap() {
        Map<String, List<Map<String, String>>> speMap = new HashMap<>();
        List<Map<String, String>> spes = new ArrayList<>();
        Map<String, String> spe = new HashMap<>();
        spe.put("speId", "0400");
        spe.put("speName", "皮肤科");
        spes.add(spe);
        speMap.put("10", spes);//		皮肤性病科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "0700");
        spe.put("speName", "全科");
        spes.add(spe);
        speMap.put("52", spes);//		全科方向（西医）
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "0900");
        spe.put("speName", "外科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1000");
        spe.put("speName", "外科（神经外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1100");
        spe.put("speName", "外科（胸心外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1200");
        spe.put("speName", "外科（泌尿外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1300");
        spe.put("speName", "外科（整形外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1400");
        spe.put("speName", "骨科");
        spes.add(spe);
        speMap.put("2", spes);//		外科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "2000");
        spe.put("speName", "临床病理科");
        spes.add(spe);
        speMap.put("7", spes);//		病理科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "2100");
        spe.put("speName", "检验医学科");
        spes.add(spe);
        speMap.put("4", spes);//		医学检验科
        spes = new ArrayList<>();

        spe = new HashMap<>();
        spe.put("speId", "2200");
        spe.put("speName", "放射科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2500");
        spe.put("speName", "放射肿瘤科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2400");
        spe.put("speName", "核医学科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2300");
        spe.put("speName", "超声医学科");
        spes.add(spe);
        speMap.put("6", spes);//		医学影像科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "2800");
        spe.put("speName", "口腔全科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2900");
        spe.put("speName", "口腔内科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3000");
        spe.put("speName", "口腔颌面外科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3100");
        spe.put("speName", "口腔修复科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3200");
        spe.put("speName", "口腔正畸科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3300");
        spe.put("speName", "口腔病理科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3400");
        spe.put("speName", "口腔颌面影像科");
        spes.add(spe);
        speMap.put("17", spes);//		口腔科
        return speMap;
    }

    @RequestMapping(value = {"/main"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object main(String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        resultMap.put("user", user);
        ResDoctor doctor = jswjwBiz.readDoctor(user.getUserFlow());
        resultMap.put("doctor", doctor);
        String currYear = DateUtil.getYear();
        resultMap.put("currYear", currYear);
        String currDateTime = DateUtil.getCurrDateTime2();
        String orgFlow = user.getOrgFlow();
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = doctor.getOrgFlow();
        }
        //如果当前学员是协同基地取他主基地所在的城市
        List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }
        SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = jswjwBiz.findEffective(currDateTime, sysOrg.getOrgCityId());
        //当前城市的学员有没有正在进行的考试
        Boolean inTime = resTestConfigList.size() > 0 ? true : false;
        resultMap.put("inTime", inTime);
        List<JsresExamSignup> signups = jswjwBiz.readDoctorExanSignUps(user.getUserFlow());
        resultMap.put("signups", signups);
        //是否有补考报名的资格（在系统中有资格审核记录且学员为非合格人员才有资格）
        String isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
        // 已结业学员 不允许补考报名 禅道bug：2648
        if (doctor != null && "21".equals(doctor.getDoctorStatusId())) {
            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
        }
        // isAllowApply为N 不能参加补考，无需在做结业申请判断
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setDoctorFlow(doctor.getDoctorFlow());
            recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
            //在系统中是否有资格审核记录
            if (recruitList != null && recruitList.size() > 0) {
                ResDoctorRecruit resDoctorRecruit = recruitList.get(0);
                if (resDoctorRecruit != null) {
                    JsresGraduationApply apply = jswjwBiz.searchByRecruitFlow(resDoctorRecruit.getRecruitFlow(), "");
                    if (apply == null) {
                        // 2019级及以前助理全科学员走补考
                        if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
                        }else{
                            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
                        }
                    }
                }
            }
        }
        // isAllowApply为N 不能参加补考，无需在做成绩判断
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            String isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
            String isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
            String isSkill = com.pinde.core.common.GlobalConstant.FLAG_Y;
            String isTheory = com.pinde.core.common.GlobalConstant.FLAG_Y;
            String validYear = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 3);
            List<ResScore> resScoreList = jswjwBiz.selectAllScore(user.getUserFlow(), null);
            if (resScoreList != null && resScoreList.size() > 0) {
                //3年内的技能成绩集合
                List<ResScore> skillList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                //3年内的理论成绩集合
                List<ResScore> theoryList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                if (skillList.size() > 0) {
                    for (ResScore resScore : skillList) {
                        if ("1".equals(String.valueOf(resScore.getSkillScore()))) {
                            //3年内的技能成绩有合格的
                            isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                            break;
                        }
                    }
                } else {
                    isSkill = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
                // 如果技能考试没有合格记录 无需在判断理论成绩
                if (theoryList.size() > 0) {
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(isSkillQualifed)) {
                        for (ResScore resScore : theoryList) {
                            if ("1".equals(String.valueOf(resScore.getTheoryScore()))) {
                                //3年内的理论成绩有合格的
                                isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                                break;
                            }
                        }
                    }
                } else {
                    isTheory = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            // 需求变更2018（不含）届以前学员 不做该判断  未参加过考核也可以补考
            if ("2018".compareTo(doctor.getSessionNumber()) <= 0 && com.pinde.core.common.GlobalConstant.FLAG_N.equals(isSkill) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(isTheory)) {
                // 2019/2018/2017级助理全科全走补考报名
                if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                    isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }else{
                    //从未参加过考核
                    isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isSkillQualifed) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isTheoryQualifed)) {
                //3年内理论成绩和技能成绩都合格
                isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
        }
        resultMap.put("isAllowApply", isAllowApply);
        return resultMap;
    }

    @RequestMapping(value = {"/signUp"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object signUp(String typeId, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        String currYear = DateUtil.getYear();
        resultMap.put("currYear", currYear);
        SysUser user = jswjwBiz.readSysUser(userFlow);
        resultMap.put("user", user);
        ResDoctor doctor = jswjwBiz.readDoctor(user.getUserFlow());
        resultMap.put("doctor", doctor);

        ResDoctorRecruit resDoctorRecruit = null;
        if (doctor != null) {
            resDoctorRecruit = jswjwBiz.readResDoctorRecruitBySessionNumber(doctor.getDoctorFlow(), doctor.getSessionNumber());
        }
        ;
        if (resDoctorRecruit != null) {
            Map<String, List<Map<String, String>>> speMap = setSpeMap();
            List<Map<String, String>> spes = speMap.get(resDoctorRecruit.getSpeId());
            if (spes != null && spes.size() > 0) {
                resultMap.put("needChange", com.pinde.core.common.GlobalConstant.FLAG_Y);
                resultMap.put("spes", spes);
            } else {
                resultMap.put("needChange", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
        }
        return resultMap;
    }

    @RequestMapping(value = {"/saveSignUp"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveSignUp(String theory, String skill, JsresExamSignup signup, String userFlow, String[] signupTypeIds) {
        Map<String, Object> resultMap = new HashMap<>();
//        List<String> signupTypeIds = new ArrayList<>();
        SysUser currentUser = jswjwBiz.readSysUser(userFlow);
        ResDoctor doctor = jswjwBiz.readDoctor(currentUser.getUserFlow());
        signup.setDoctorFlow(doctor.getDoctorFlow());
        signup.setDoctorTypeId(doctor.getDoctorTypeId());
        signup.setDoctorTypeName(doctor.getDoctorTypeName());
        String orgFlow = currentUser.getOrgFlow();
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = doctor.getOrgFlow();
        }
        //如果当前学员是协同基地取他主基地所在的城市
        List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }

        SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = jswjwBiz.findEffective(DateUtil.getCurrDateTime2(), sysOrg.getOrgCityId());
        if (resTestConfigList.size() > 0) {
            ResTestConfig resTestConfig = resTestConfigList.get(0);
            signup.setTestId(resTestConfig.getTestId());
            //判断需不需要基地审核，需要则是待基地审核，不要要再判断需不需要市局审核，需要则是待市局审核，都不需要则是待省厅审核
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getLocalAudit())) {
                signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
                signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
            } else {
                signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
            }
        } else {
            return ResultDataThrow("当前没有正在进行的考试");
        }
        String isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
        String isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResScore> resScoreList = jswjwBiz.selectAllScore(currentUser.getUserFlow(), null);
        String validYear = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 3);
        if (resScoreList.size() > 0) {
            //3年内的技能成绩集合
            List<ResScore> skillList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
            //3年内的理论成绩集合
            List<ResScore> theoryList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
            if (skillList.size() > 0) {
                for (ResScore resScore : skillList) {
                    if ("1".equals(String.valueOf(resScore.getSkillScore()))) {
                        //3年内的技能成绩有合格的
                        isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    }
                }
            }
            if (theoryList.size() > 0) {
                for (ResScore resScore : theoryList) {
                    if ("1".equals(String.valueOf(resScore.getTheoryScore()))) {
                        //3年内的理论成绩有合格的
                        isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    }
                }
            }
        }
        int c = 0;
//        if (StringUtil.isNotEmpty(theory)) {
//            signupTypeIds.add(theory);
//        }
//        if (StringUtil.isNotEmpty(skill)) {
//            signupTypeIds.add(skill);
//        }
//        List<JsresExamSignup> signupLists = jswjwBiz.readDoctorExanSignUps(doctor.getDoctorFlow());
//        if(CollectionUtils.isNotEmpty(signupLists) && signupLists.size() == 2){
//            ResultDataThrow("您已考核超过三次，无法进行补考申请。");
//        }
        for (String signupTypeId : signupTypeIds) {
            List<JsresExamSignup> signupList = jswjwBiz.getSignUpListByYear(DateUtil.getYear(), doctor.getDoctorFlow(), signupTypeId);
            if (signupList != null && signupList.size() > 0) {
                for (JsresExamSignup jsresExamSignup : signupList) {
                    //如果有补考记录状态是待审核或者审核通过不允许多次提交
                    if (StringUtil.isNotBlank(jsresExamSignup.getAuditStatusId()) &&
                            ("Auditing".equals(jsresExamSignup.getAuditStatusId()) ||
                                    "WaitChargePass".equals(jsresExamSignup.getAuditStatusId()) ||
                                    "WaitGlobalPass".equals(jsresExamSignup.getAuditStatusId()) ||
                                    "GlobalPassed".equals(jsresExamSignup.getAuditStatusId()))) {
                        if ("Theory".equals(signupTypeId)) {
                            return ResultDataThrow("您已提交过补考报名，请勿重复提交。");
                        }
                        if ("Skill".equals(signupTypeId)) {
                            return ResultDataThrow("您已提交过补考报名，请勿重复提交。");
                        }
                    }
                }
            }
        }
        for (String signupTypeId : signupTypeIds) {
            signup.setSignupFlow("");
            signup.setSignupTypeId(signupTypeId);
            if ("Theory".equals(signupTypeId)) {
                signup.setSignupTypeName("理论补考");
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isTheoryQualifed)) {
                    return ResultDataThrow("您的历史成绩中，有报名科目的合格记录，请勿重复报名");
                }
            }
            if ("Skill".equals(signupTypeId)) {
                signup.setSignupTypeName("技能补考");
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isSkillQualifed)) {
                    return ResultDataThrow("您的历史成绩中，有报名科目的合格记录，请勿重复报名");
                }
            }
            c += jswjwBiz.saveSignUp(signup, currentUser);
        }
        if (c == 0) {
            return ResultDataThrow("报名失败！");
        }
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "报名成功！");
        return resultMap;
    }

    public void saveAsseScore(String score, String scoreType, String recruitFlow, String userFlow) throws DocumentException {
        SysUser currentUser = jswjwBiz.readSysUser(userFlow);
        ResScore resScore = new ResScore();
        if (StringUtil.isNotBlank(scoreType)) {
            resScore.setDoctorFlow(currentUser.getUserFlow());//医师流水号
            resScore.setScoreTypeId(com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId());
            resScore.setScoreTypeName(com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getName());
            resScore.setRecruitFlow(recruitFlow);
            if (scoreType.equals("theoryScore")) {
                if (StringUtil.isNotBlank(score)) {
                    BigDecimal bd = new BigDecimal(score);
                    resScore.setTheoryScore(bd);
                }
            } else if (scoreType.equals("skillScore")) {
                if (StringUtil.isNotBlank(score)) {
                    BigDecimal bd = new BigDecimal(score);
                    resScore.setSkillScore(bd);
                }
            } else {
                List<ResScore> scorelist = jswjwBiz.selectByRecruitFlowAndScoreType(recruitFlow, com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId());
                //公共成绩
                Map<String, String> extScoreMap = new HashMap<String, String>();
                extScoreMap.put("lawScore", "");
                extScoreMap.put("medicineScore", "");
                extScoreMap.put("clinicalScore", "");
                extScoreMap.put("ckScore", "");
                ResScore publicScore = null;
                if (null != scorelist && scorelist.size() > 0) {
                    publicScore = scorelist.get(0);
                    //公共科目成绩详情
                    String content = null == publicScore ? "" : publicScore.getExtScore();
                    if (StringUtil.isNotBlank(content)) {
                        Document doc = DocumentHelper.parseText(content);
                        Element root = doc.getRootElement();
                        Element extScoreInfo = root.element("extScoreInfo");
                        if (extScoreInfo != null) {
                            List<Element> extInfoAttrEles = extScoreInfo.elements();
                            if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                                for (Element attr : extInfoAttrEles) {
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    extScoreMap.put(attrName, attrValue);
                                }
                            }
                        }
                    }
                }
                if (StringUtil.isNotBlank(scoreType)) {
                    extScoreMap.put(scoreType, score);
                }
                String extScore = null;
                try {
                    extScore = jswjwBiz.convertMapToXml(extScoreMap, resScore);
                } catch (Exception e) {
                    logger.error("", e);
                }
                if (StringUtil.isNotBlank(extScore)) {
                    resScore.setExtScore(extScore);

                }
            }
        }
        int i = 0;
        jswjwBiz.savePublic(resScore, currentUser);
//        if(i == 1){
//            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
//        }else {
//            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
//        }
    }

    @RequestMapping(value = {"/uploadApplicationFile"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object uploadApplicationFile(MultipartFile file, String recruitFlow, String userFlow, ImageFileForm form) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "上传成功！");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(form.getFileName())) {
            return ResultDataThrow("文件名为空");
        }
        if (StringUtil.isBlank(form.getImageContent())) {
            return ResultDataThrow("上传文件不能为空");
        }
        String resultPath = jswjwBiz.addApplicationFile(form);
        List<PubFile> files = pubFileBiz.findFileByTypeFlow("asseApplication", recruitFlow);
        PubFile pubFile = null;
        if (files != null && files.size() > 0) {
            pubFile = files.get(0);
        } else {
            pubFile = new PubFile();
        }
        pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        pubFile.setProductFlow(recruitFlow);
        pubFile.setFilePath(resultPath);
        pubFile.setFileName(form.getFileName());
        pubFile.setProductType("asseApplication");
        pubFileBiz.editFile(pubFile);
        if (StringUtil.isNotEmpty(resultPath)) {
            SysCfg upload_base_url = cfgMapper.selectByPrimaryKey("upload_base_url");
            resultMap.put("resultPath", upload_base_url.getCfgValue() + "/" + resultPath.replaceAll("\\\\", "/"));
        }
//        if (file != null && !file.isEmpty()) {
//            String checkResult = jswjwBiz.checkImg(file);
//            String resultPath = "";
//            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(checkResult)) {
//                return ResultDataThrow(checkResult);
//            } else {
//                resultPath = jswjwBiz.saveFileToDirs("", file, "jsresImages" + File.separator + "asseApplication");
//                List<PubFile> files = pubFileBiz.findFileByTypeFlow("asseApplication", recruitFlow);
//                PubFile pubFile = null;
//                if (files != null && files.size() > 0) {
//                    pubFile = files.get(0);
//                } else {
//                    pubFile = new PubFile();
//                }
//                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//                pubFile.setProductFlow(recruitFlow);
//                pubFile.setFilePath(resultPath);
//                pubFile.setFileName(form.getFileName());
//                pubFile.setProductType("asseApplication");
//                pubFileBiz.editFile(pubFile);
//                if (StringUtil.isNotEmpty(resultPath)) {
//                    SysCfg upload_base_url = cfgMapper.selectByPrimaryKey("upload_base_url");
//                    resultMap.put("resultPath", upload_base_url.getCfgValue() + "/" + resultPath.replaceAll("\\\\", "/"));
//                }
//            }
//        }
        return resultMap;
    }

    /**
     * 认证和修改手机号（发送验证码）
     */
    @RequestMapping(value = {"/authenPhone2"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object authenPhone2(String userPhone, String systemFlag, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isBlank(userPhone)) {
            return ResultDataThrow("手机号为空");
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            return ResultDataThrow(phoneRegex(userPhone).get(1));
        }
        SysUser sysUser = jswjwBiz.findByUserCode(userPhone);
        if (sysUser != null) {
            return ResultDataThrow("该手机号已绑定过账号");
        }
        SMSUtil smsUtil = new SMSUtil(userPhone);
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        resultMap.put("userVerifyCode", "");
        HttpSession session = request.getSession();
        session.setAttribute("userVerifyCode", code);
        String currDateTime2 = DateUtil.getCurrDateTime2();
        resultMap.put("verifyCodeTime", "");
        session.setAttribute("verifyCodeTime", currDateTime2);
        SysSmsLog sSmsRecord = new SysSmsLog();
        if ("tjres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516954", "R101", code);
        }
        if ("jsres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516946", "R101", code);
        }
        if (!"100".equals(sSmsRecord.getStatusCode())) {
            return ResultDataThrow(sSmsRecord.getStatusName());
        }
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "");
        resultMap.put("userPhone", "");
        return resultMap;
    }

    /**
     * 判断是否在黑名单中
     */
    @RequestMapping(value = {"/checkBlack"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object checkBlack(String idNo) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
//        resultMap.put("pageIndex", 0);
        if (StringUtil.isEmpty(idNo)) {
            return ResultDataThrow("idNo为空");
        }
        String checkErrorMessage = "";
        int i = balcklistExtMapper.selectBlacklistByIdNo(idNo);
        if (i > 0) {
            return ResultDataThrow("您的信息已被纳入我省医务人员诚信系统，5年内不得进入我省培训基地接受住院医师规范化培训。如有相关疑问，请与相关管理部门联系。");
        }
        return resultMap;
    }

    @RequestMapping(value = {"/registerNew"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object registerNew(String userPhone, String userPasswd, String code, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "注册成功！");
        if (StringUtil.isEmpty(userPhone)) {
            return ResultDataThrow("userPhone为空");
        }
        if (StringUtil.isEmpty(userPasswd)) {
            return ResultDataThrow("userPasswd为空");
        }
        if (StringUtil.isEmpty(code)) {
            return ResultDataThrow("code为空");
        }
        String currDateTime = DateUtil.getCurrDateTime2();
        HttpSession session = request.getSession();
        if (null != session.getAttribute("verifyCodeTime") && null != session.getAttribute("userVerifyCode")) {
            String verifyCodeTime = (String) session.getAttribute("verifyCodeTime");
            long betweenTowDate = DateUtil.signSecondsBetweenTowDate(currDateTime, verifyCodeTime);
            String userVerifyCode = session.getAttribute("userVerifyCode").toString();
            if (code.length() != 6) {
                return ResultDataThrow("验证码错误");
            }
            if (code.equals(userVerifyCode)) {
                if (betweenTowDate > 300) {
                    return ResultDataThrow("验证码超时，请重新获取验证码！");
                }
            } else {
                return ResultDataThrow("验证码错误");
            }
        }

        SysUser sysUser = jswjwBiz.findByUserCode(userPhone);
        if (sysUser == null) {
            jswjwBiz.saveUserInfo2(userPhone, userPasswd, code);
        } else {
            return ResultDataThrow("该手机号已绑定过账号");
        }
        return resultMap;
    }

    /**
     * 个人基本信息
     *
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = {"/doctorInfo"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object doctorInfo(String userFlow, String viewFlag) throws DocumentException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "查询成功！");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        if (null == sysUser) {
            return ResultDataThrow("用户不存在");
        }
        ResDoctor resDoctor = jswjwBiz.readDoctor(sysUser.getUserFlow());
        if (resDoctor != null) {
            if (StringUtil.isNotBlank(resDoctor.getGraduatedId())) {
                List<SysDict> sysDictList = jswjwBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
                if (sysDictList != null && !sysDictList.isEmpty()) {
                    for (SysDict dict : sysDictList) {
                        if (dict.getDictId().equals(resDoctor.getGraduatedId())) {
                            resDoctor.setGraduatedName(dict.getDictName());
                        }
                    }
                }
            }
            if (StringUtil.isNotBlank(resDoctor.getDoctorTypeId()) && "Graduate".equals(resDoctor.getDoctorTypeId())) {
                if (StringUtil.isNotBlank(resDoctor.getWorkOrgId())) {
                    List<SysDict> sysDictList = jswjwBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
                    if (sysDictList != null && !sysDictList.isEmpty()) {
                        for (SysDict dict : sysDictList) {
                            if (dict.getDictId().equals(resDoctor.getWorkOrgId())) {
                                resDoctor.setWorkOrgName(dict.getDictName());
                            }
                        }
                    }
                }
            }
        }
        resultMap.put("user", sysUser);
        if (null == resDoctor) {
            resultMap.put("doctor", new ResDoctor());
        } else {
            resultMap.put("doctor", resDoctor);
        }
        PubUserResume pubUserResume = jswjwBiz.readPubUserResume(userFlow);
        UserResumeExtInfoForm userResumeExt = new UserResumeExtInfoForm();
        if (pubUserResume != null) {
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                userResumeExt = jswjwBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                        List<SysDict> sysDictList = jswjwBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
                        if (sysDictList != null && !sysDictList.isEmpty()) {
                            for (SysDict dict : sysDictList) {
                                if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                    if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                        userResumeExt.setGraduatedName(dict.getDictName());
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        if (StringUtil.isNotBlank(userResumeExt.getPracticingCategoryScopeName())) {
            userResumeExt.setPracticingCategoryScopeName(userResumeExt.getPracticingCategoryScopeName().replace("\t", "").replace("\n", ""));
        }
        if (StringUtil.isNotBlank(userResumeExt.getPracticingCategoryLevelName())) {
            userResumeExt.setPracticingCategoryLevelName(userResumeExt.getPracticingCategoryLevelName().replace("\t", "").replace("\n", ""));
        }

        resultMap.put("userResumeExt", userResumeExt);
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setGraduationYear(DateUtil.getYear());
        recruit.setDoctorFlow(sysUser.getUserFlow());
        recruit.setAuditStatusId("Passed");
        List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
        String canSave = com.pinde.core.common.GlobalConstant.FLAG_Y;
        if (recruits != null && recruits.size() > 0) {
            String recruitFlow = recruits.get(0).getRecruitFlow();
            JsresGraduationApply apply = jswjwBiz.searchByRecruitFlow(recruitFlow, recruits.get(0).getGraduationYear());
            if (apply != null) {
                if ("WaitChargePass".equals(apply.getAuditStatusId())) {
                    canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                } else if ("WaitGlobalPass".equals(apply.getAuditStatusId())) {
                    canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                } else if ("GlobalPassed".equals(apply.getAuditStatusId())) {
                    canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
        }
        boolean isPassed = jswjwBiz.getRecruitStatus(sysUser.getUserFlow());
        resultMap.put("isPassed", isPassed);
        resultMap.put("canSave", canSave);
        String editFlag = InitConfig.getSysCfg("assess_doctor_edit_info");
//        //学员保存个人信息放开
//        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(viewFlag)) {
//            return "jsres/doctorInfo";
//        }
//        //com.pinde.core.common.GlobalConstant.FLAG_N.equals(canSave) 含义为有一条培训记录，结业考核年份是当前年，并且结业资格审查省厅通过的。学员无法修改个人信息 并且审核期间学员是否修改个人信息为否时
//        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(canSave)) {
//            model.addAttribute("isDoctor", com.pinde.core.common.GlobalConstant.FLAG_Y);
//            return "jsres/doctorInfo";
//        }
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        resultMap.put("upload_base_url", uploadBaseUrl);
//        DictBizImpl.searchDictListByDictTypeId()
        //外语等级考试类型
        List<SysDict> englishGradeExamTypes = com.pinde.core.common.enums.DictTypeEnum.EnglishGradeExamType.getSysDictList();
        //英语能力
        List<SysDict> englishAbilitys = com.pinde.core.common.enums.DictTypeEnum.EnglishAbility.getSysDictList();
        //国籍
        List<SysDict> nationalitys = com.pinde.core.common.enums.DictTypeEnum.Nationality.getSysDictList();
        //派送单位
        List<SysDict> workOrgs = com.pinde.core.common.enums.DictTypeEnum.WorkOrg.getSysDictList();
        //医疗卫生机构
        List<SysDict> medicalHeaithOrgs = com.pinde.core.common.enums.DictTypeEnum.MedicalHeaithOrg.getSysDictList();
        //医院属性
        List<SysDict> hospitalAttrs = com.pinde.core.common.enums.DictTypeEnum.HospitalAttr.getSysDictList();
        //单位性质
        List<SysDict> baseAttributes = com.pinde.core.common.enums.DictTypeEnum.BaseAttribute.getSysDictList();
        //医院类别
        List<SysDict> hospitalCategorys = com.pinde.core.common.enums.DictTypeEnum.HospitalCategory.getSysDictList();
        //基层医疗卫生机构
        List<SysDict> basicHealthOrgs = com.pinde.core.common.enums.DictTypeEnum.BasicHealthOrg.getSysDictList();
        //派送学校
        List<SysDict> sendSchools = com.pinde.core.common.enums.DictTypeEnum.SendSchool.getSysDictList();
        //在读学历
        List<SysDict> userEducations = com.pinde.core.common.enums.DictTypeEnum.UserEducation.getSysDictList();
        //在读院校
        List<SysDict> graduateSchools = com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getSysDictList();
        //在读专业
        List<SysDict> graduateMajors = com.pinde.core.common.enums.DictTypeEnum.GraduateMajor.getSysDictList();
        //学位
        List<SysDict> userDegrees = com.pinde.core.common.enums.DictTypeEnum.UserDegree.getSysDictList();
        //执业类型
        List<SysDict> practiceTypes = com.pinde.core.common.enums.DictTypeEnum.PracticeType.getSysDictList();
        //民族
        List<SysDict> userNations = new ArrayList<>();
        //省份
        List<SysDict> Provinces = new ArrayList<>();

        resultMap.put("englishGradeExamTypes", englishGradeExamTypes);
        resultMap.put("englishAbilitys", englishAbilitys);
        resultMap.put("workOrgs", workOrgs);
        resultMap.put("medicalHeaithOrgs", medicalHeaithOrgs);
        resultMap.put("hospitalAttrs", hospitalAttrs);
        resultMap.put("baseAttributes", baseAttributes);
        resultMap.put("hospitalCategorys", hospitalCategorys);
        resultMap.put("basicHealthOrgs", basicHealthOrgs);
        resultMap.put("sendSchools", sendSchools);
        resultMap.put("userEducations", userEducations);
        resultMap.put("graduateSchools", graduateSchools);
        resultMap.put("graduateMajors", graduateMajors);
        resultMap.put("userDegrees", userDegrees);
        resultMap.put("practiceTypes", practiceTypes);
        resultMap.put("nationalitys", nationalitys);

        UserNationEnum[] values = UserNationEnum.values();
        for (UserNationEnum value : values) {
            SysDict sysDict = new SysDict();
            sysDict.setDictId(value.getId());
            sysDict.setDictName(value.getName());
            userNations.add(sysDict);
        }
        ProvinceEnum[] values1 = com.pinde.core.common.enums.ProvinceEnum.values();
        for (ProvinceEnum value : values1) {
            SysDict sysDict = new SysDict();
            sysDict.setDictId(value.getId());
            sysDict.setDictName(value.getName());
            Provinces.add(sysDict);
        }
        resultMap.put("userNations", userNations);
        resultMap.put("Provinces", Provinces);
        return resultMap;
    }

    /**
     * 执业范围获取
     */
    @RequestMapping(value = {"/getCategoryScope"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object getCategoryScope(String key) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(key)) {
            return ResultDataThrow("key为空");
        }
        List<SysDict> sysDicts = dictBizImpl.searchDictListByDictTypeId(key);
        resultMap.put("sysDicts", sysDicts);
        return resultMap;
    }


    /**
     * 保存个人基本信息
     *
     * @param doctorInfoForm
     * @return
     */
    @RequestMapping(value = {"/saveDoctorInfo"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveDoctorInfo(JsResDoctorInfoForm doctorInfoForm, String qtCountry) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "保存成功！");
        if (null == doctorInfoForm) {
            return ResultDataThrow("参数错误");
        }
        SysUser sysUser = doctorInfoForm.getUser();
        String checkResult = checkUserUnique(sysUser, sysUser.getUserFlow());
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(checkResult)) {
            return ResultDataThrow(checkResult);
        }
        ResDoctor doctor = doctorInfoForm.getDoctor();
        UserResumeExtInfoForm userResumeExt = doctorInfoForm.getUserResumeExt();
        String isMasterHaveDiploma = userResumeExt.getIsMasterHaveDiploma();
        //qtCountry:如果不是中国，设置地区
        int result = jswjwBiz.saveDoctorInfo(sysUser, doctor, userResumeExt, qtCountry);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setGraduationYear(DateUtil.getYear());
            recruit.setDoctorFlow(sysUser.getUserFlow());
            recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
            List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwBiz.readDoctorRecruits(recruit);
            String canSave = com.pinde.core.common.GlobalConstant.FLAG_Y;
            if (recruits != null && recruits.size() > 0) {
                String recruitFlow = recruits.get(0).getRecruitFlow();
                JsresGraduationApply apply = jswjwBiz.searchByRecruitFlow(recruitFlow, recruits.get(0).getGraduationYear());
                if (apply != null) {
                    if (com.pinde.core.common.enums.JsResAsseAuditListEnum.WaitChargePass.getId().equals(apply.getAuditStatusId())) {
                        canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                    } else if (com.pinde.core.common.enums.JsResAsseAuditListEnum.WaitGlobalPass.getId().equals(apply.getAuditStatusId())) {
                        canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                    } else if (com.pinde.core.common.enums.JsResAsseAuditListEnum.GlobalPassed.getId().equals(apply.getAuditStatusId())) {
                        canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                    }
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(canSave)) {
                        tempMapper.updateRecruitAsseInfoByApplyYear(apply.getApplyFlow(), sysUser.getUserFlow());
                    }
                }
            }
            return resultMap;
        } else {
            return ResultDataThrow("保存失败！");
        }
    }


    public String checkUserUnique(SysUser sysUser, String userFlow) {
        SysUser exitUser = null;
        //身份证号唯一
        String idNo = sysUser.getIdNo().toUpperCase();////身份证X大写
        if (StringUtil.isNotBlank(idNo)) {
            exitUser = jswjwBiz.findByIdNo(idNo.trim());
            if (exitUser != null) {
                if (StringUtil.isNotBlank(userFlow)) {
                    if (!exitUser.getUserFlow().equals(userFlow)) {
                        return "该身份证号已经被注册！";
                    }
                } else {
                    return "该身份证号已经被注册！";
                }
            }
        }
        //手机号唯一
        String userPhone = sysUser.getUserPhone();
        if (StringUtil.isNotBlank(userPhone)) {
            exitUser = jswjwBiz.findByUserPhone(userPhone.trim());
            if (exitUser != null) {
                if (StringUtil.isNotBlank(userFlow)) {
                    if (!exitUser.getUserFlow().equals(userFlow)) {
                        return "该手机号已经被注册！";
                    }
                } else {
                    return "该手机号已经被注册！";
                }
            }
        }
        return com.pinde.core.common.GlobalConstant.FLAG_Y;
    }

    /**
     * 招录计划
     */
    @RequestMapping(value = {"/doctorPlan"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object doctorPlan(String assignYear, Integer pageIndex, Integer pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtil.isBlank(assignYear)) {
            //默认查询正在进行的招录计划
            assignYear = DateUtil.getYear();
            paramMap.put("currDate", DateUtil.getCurrDate());
        }
        paramMap.put("assignYear", assignYear);
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> resultMapList = jswjwBiz.searchAssignInfoListNew(paramMap);
        resultMap.put("resultMapList", resultMapList);
        resultMap.put("dataCount", resultMapList.size());
        return resultMap;
    }

    @RequestMapping(value = {"/doctorPlanInfo"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object doctorPlanInfo(String assignYear, String userFlow, String orgFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("assignYear", assignYear);
        resultMap.put("assignYear", assignYear);
        // 查询报考专业信息
        List<Map<String, String>> orgSpeList = jswjwBiz.searchAssignOrgSpeListNew(paramMap);
        resultMap.put("orgSpeList", orgSpeList);
        resultMap.put("sysOrg", sysOrg);
        //判断招录计划是否开始
        String signupFlag = com.pinde.core.common.GlobalConstant.FLAG_N;//不可报名
        if (null != orgSpeList && orgSpeList.size() > 0) {
            String currDate = DateUtil.getCurrDate();
            Map<String, String> map = orgSpeList.get(0);
            String startTime = map.get("START_TIME");
            String endTime = map.get("END_TIME");
            if (startTime.compareTo(currDate) > 0 || currDate.compareTo(endTime) > 0) {
                resultMap.put("signupMsg", "招录时间未到！");
                resultMap.put("signupBtnFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
            } else {
                signupFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(signupFlag)) {
            // 判断学员是否可以报名
            String signupMsg = jswjwBiz.doctorSignupFlagNew(userFlow);
            if (StringUtil.isNotBlank(signupMsg)) {
                resultMap.put("signupMsg", signupMsg);
                resultMap.put("signupBtnFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
            } else {
                resultMap.put("signupBtnFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            }
        }
        String speFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        List<Map<String, String>> docSpeList = jswjwBiz.searchDoctorSpe();
        if (null != docSpeList && docSpeList.size() > 0) {
            for (Map<String, String> spe : docSpeList) {
                String idNo = spe.get("ID_NO");
                String doctorTypeId = spe.get("DOCTOR_TYPE_ID");
                if (idNo.equals(sysUser.getIdNo()) && !"Graduate".equals(doctorTypeId)) {
                    speFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    break;
                }
            }
        }
        List<SysOrg> sysOrgList = jswjwBiz.searchJointOrgsByOrg(orgFlow);
        resultMap.put("sysOrgList",sysOrgList);
        resultMap.put("speFlag", speFlag);
        return resultMap;
    }


    @RequestMapping(value = {"/showPlanSpeDesc"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object showPlanSpeDesc(String assignFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        // 查询招生计划信息
        ResOrgSpeAssign resOrgSpeAssign = jswjwBiz.getResOrgSpeAssignInfo(assignFlow);
        resultMap.put("assign", resOrgSpeAssign);
        return resultMap;
    }

    @RequestMapping(value = {"/editDoctorRecruit"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object editDoctorRecruit(String recruitFlow, String speId, String orgFlow, String userFlow, String assignYear, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        String doctorFlow = userFlow;
        resultMap.put("userFlow", userFlow);
        ResDoctorRecruit doctorRecruit = null;
        doctorRecruit = jswjwBiz.readResDoctorRecruit(recruitFlow);
        resultMap.put("doctorRecruit", doctorRecruit);
        resultMap.put("addRecord", com.pinde.core.common.GlobalConstant.FLAG_Y);//添加新的培训记录标识
        //根据专业ID speId ,机构流水号 orgFlow 查询对应的名称
        SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        criteria.andOrgFlowEqualTo(orgFlow);
        example.setOrderByClause("ORDINAL");
        List<SysOrg> orgList = jswjwBiz.searchOrgByExample(example);

        //根据机构ID和年份查询 专业
        List<ResOrgSpeAssign> speAssignList = jswjwBiz.searchSpeAssign(orgFlow, assignYear);
        String speName = "";
        if (speAssignList != null && speAssignList.size() > 0) {
            for (ResOrgSpeAssign rosa : speAssignList) {
                if (rosa.getSpeId().equals(speId)) {
                    speName = rosa.getSpeName();
                }
            }
        }
        //根据协同基地ID 查询 主基地与协同基地的关联表，确认当前报考的是否是主基地
        //表示如果查到的为空 表示当前选中的基地是主基地 反之为协同基地
        List<ResJointOrg> joinOrgs = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
        //协同基地ID 名称 是否在协同基地培训
        String jointOrgFlow = "";
        String jointOrgName = "";
        String inJointOrgTrain = "";
        String orgName = "";
        String placeId = "";
        String placeName = "";
        if (joinOrgs.size() == 0) {
            jointOrgFlow = "";
            jointOrgName = "";
            inJointOrgTrain = com.pinde.core.common.GlobalConstant.FLAG_N;
            if (orgList.size() == 0) {
                placeId = "";
                placeName = "";
            } else {
                placeId = orgList.get(0).getOrgCityId();
                placeName = orgList.get(0).getOrgCityName();
                orgFlow = orgList.get(0).getOrgFlow();
                orgName = orgList.get(0).getOrgName();
            }
        } else {
            jointOrgFlow = joinOrgs.get(0).getJointOrgFlow();
            jointOrgName = joinOrgs.get(0).getJointOrgName();
            //从关联表中查询主基地名称与ID
            orgFlow = joinOrgs.get(0).getOrgFlow();
            orgName = joinOrgs.get(0).getOrgName();
            inJointOrgTrain = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }

        resultMap.put("speId", speId);
        resultMap.put("speName", speName);
        resultMap.put("orgFlow", orgFlow);
        resultMap.put("jointOrgFlow", jointOrgFlow);
        resultMap.put("jointOrgName", jointOrgName);
        resultMap.put("inJointOrgTrain", inJointOrgTrain);
        //从关联表中查询主基地名称与ID
        resultMap.put("orgName", orgName);
        resultMap.put("placeId", placeId);
        resultMap.put("placeName", placeName);
        resultMap.put("assignYear", assignYear);
        //已审核通过
        ResDoctorRecruit passedRec = new ResDoctorRecruit();
        passedRec.setDoctorFlow(doctorFlow);
        passedRec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        passedRec.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        List<com.pinde.core.model.ResDoctorRecruit> passedRecruitList = jswjwBiz.searchResDoctorRecruitList(passedRec, "MODIFY_TIME DESC");
        //其中一阶段、住院医师审核通过（选二阶段使用）
        List<com.pinde.core.model.ResDoctorRecruit> prevPassedList = new ArrayList<com.pinde.core.model.ResDoctorRecruit>();
        if (passedRecruitList != null && !passedRecruitList.isEmpty()) {
            resultMap.put("passedRecruitList", passedRecruitList);
            //记录审核通过的培训类别(不包含首条为二阶段自动生成的一阶段)
            List<String> catSpeIdList = new ArrayList<String>();
            for (ResDoctorRecruit rec : passedRecruitList) {
                String catSpeId = rec.getCatSpeId();
                if (!catSpeIdList.contains(catSpeId) && StringUtil.isNotBlank(rec.getSpeId())) {
                    catSpeIdList.add(catSpeId);
                }
                if (StringUtil.isBlank(rec.getSpeId())) {//首条为二阶段
                    resultMap.put("firstRecIsWMSecond", true);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId().equals(rec.getCatSpeId()) || com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(rec.getCatSpeId())) {
                    prevPassedList.add(rec);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(rec.getCatSpeId())) {//二阶段审核通过
                    resultMap.put("isWMSecondRecPassed", true);
                }
            }
            resultMap.put("catSpeIdList", catSpeIdList);
        }
        if (prevPassedList != null && !prevPassedList.isEmpty()) {
            resultMap.put("prevPassedList", prevPassedList);
            if (StringUtil.isNotBlank(recruitFlow)) {//回显上一阶段  结业证书
                resultMap.put("latestPrevPassed", prevPassedList.get(0));
            }
        }
        return resultMap;

    }

    @RequestMapping(value = "/saveResDoctorRecruit", method = {RequestMethod.POST})
    @ResponseBody
    public Object saveResDoctorRecruit(ResDoctorRecruitInfo resDoctorRecruitInfo,
                                       ResDoctorRecruitWithBLOBs docRecWithBLOBs, String prevRecruitFlow,
                                       String prevCompleteFileUrl, String prevCompleteCertNo, String userFlow) throws ParseException, DocumentException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "报名成功");
        String doctorFlow = docRecWithBLOBs.getDoctorFlow();
        docRecWithBLOBs.setSignupWay("DoctorSignup");//学员报名方式 （DoctorSend：报送，DoctorSignup:报名）
        //查询学员提交报名次数，最多报名3次
        int signupCount = jswjwBiz.findSignupCount(doctorFlow, docRecWithBLOBs.getRecruitYear());
        if (signupCount >= 3) {
            return ResultDataThrow("操作失败！");
        }
        //设置 报名状态是待审核 医师状态待审核 begin
        docRecWithBLOBs.setAuditStatusId(BaseStatusEnum.Auditing.getId());
        docRecWithBLOBs.setAuditStatusName(BaseStatusEnum.Auditing.getName());
        //报名为协同基地 则协同基地审核后主基地审核
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(docRecWithBLOBs.getInJointOrgTrain())) {
            docRecWithBLOBs.setDoctorStatusId(BaseStatusEnum.Auditing.getId());
            docRecWithBLOBs.setDoctorStatusName(BaseStatusEnum.Auditing.getName());
            docRecWithBLOBs.setJointOrgAudit(BaseStatusEnum.Auditing.getId());
        } else {
            docRecWithBLOBs.setDoctorStatusId("OrgAuditing");
            docRecWithBLOBs.setDoctorStatusName("待主基地审核");
            docRecWithBLOBs.setJointOrgAudit(BaseStatusEnum.Passed.getId());
        }

        docRecWithBLOBs.setTrainYear("ThreeYear");
        //设置 报名状态是待审核 医师状态待审核 end at 2020.6.7
        if (!com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(docRecWithBLOBs.getCatSpeId())) {
            docRecWithBLOBs.setCompleteFileUrl("");
            docRecWithBLOBs.setCompleteCertNo("");
            if ("21".equals(docRecWithBLOBs.getDoctorStatusId())) {
                //由于页面字段展示问题现将非二阶段的该条记录的结业附件
                // name = prevCompleteFileUrl 存入special_file_url（该条记录的结业附件）中
                docRecWithBLOBs.setSpecialFileUrl(prevCompleteFileUrl);
                docRecWithBLOBs.setSpecialCertNo(prevCompleteCertNo);
            } else {
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }

            if (StringUtil.isBlank(docRecWithBLOBs.getCatSpeId())) {
                ResOrgSpe serach = new ResOrgSpe();
                serach.setOrgFlow(docRecWithBLOBs.getOrgFlow());
                serach.setSpeId(docRecWithBLOBs.getSpeId());
                serach.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                List<ResOrgSpe> resBaseList = jswjwBiz.searchResOrgSpeList(serach);
                if (resBaseList != null && resBaseList.size() > 0) {
                    ResOrgSpe resOrgSpe = resBaseList.get(0);
                    docRecWithBLOBs.setCatSpeId(resOrgSpe.getSpeTypeId());
                    docRecWithBLOBs.setCatSpeName(com.pinde.core.common.enums.TrainCategoryEnum.getNameById(resOrgSpe.getSpeTypeId()));
                }
            }

        } else {
            docRecWithBLOBs.setCompleteFileUrl(prevCompleteFileUrl);
            docRecWithBLOBs.setCompleteCertNo(prevCompleteCertNo);
            if (!"21".equals(docRecWithBLOBs.getDoctorStatusId())) {
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }
        }
        try {
            ResDoctor resDoctor = jswjwBiz.findByFlow(docRecWithBLOBs.getDoctorFlow());
            resDoctor.setDoctorStatusId("Auditing");
            resDoctor.setDoctorStatusName("报名审核中");
            jswjwBiz.editDoctor(resDoctor);
        } catch (Exception e) {
            logger.error("", e);
            return ResultDataThrow("操作失败！");
        }
        docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        //查询是否重培（有退培记录且审核通过为重培） 默认否
        docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
        docotrDelayTeturn.setDoctorFlow(docRecWithBLOBs.getDoctorFlow());
        docotrDelayTeturn.setTypeId("ReturnTraining");
        List<String> flags = new ArrayList<>();
        flags.add("1");
        List<ResDocotrDelayTeturn> resRecList = jswjwBiz.searchInfo(docotrDelayTeturn, null, flags, null);
        if (resRecList.size() > 0) {
            docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        } else {
            //非结业记录 判断入培时间 + 培训年限 + 3年  如果没结业 则为重培
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchRecruitList(docRecWithBLOBs.getDoctorFlow());
            if (CollectionUtils.isNotEmpty(recruitList)) {
                for (ResDoctorRecruit resDoctorRecruit : recruitList) {
                    //20 在培 21结业
                    int trainYear = 0;
                    if (StringUtil.isNotEmpty(resDoctorRecruit.getDoctorStatusId()) && !"21".equals(resDoctorRecruit.getDoctorStatusId())) {
                        //培训年限
                        if (StringUtil.isNotEmpty(resDoctorRecruit.getTrainYear())) {
//                    'OneYear' then '一年' 'TwoYear' then '两年' 'ThreeYear' then '三年'
                            if ("OneYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 1;
                            } else if ("TwoYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 2;
                            } else if ("ThreeYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 3;
                            }
                        }
                        if (StringUtil.isNotEmpty(resDoctorRecruit.getRecruitDate())) {
                            //培训起始时间
                            String recruitDate = resDoctorRecruit.getRecruitDate();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cd = Calendar.getInstance();
                            try {
                                cd.setTime(sdf.parse(recruitDate));
                            } catch (ParseException e) {
                                logger.error("", e);
                            }
                            cd.add(Calendar.YEAR, trainYear + 3);//增加n年
                            String format = sdf.format(cd.getTime());
                            String currDate = DateUtil.getCurrDate();

                            //当前时间
                            Date fromDate1 = sdf.parse(currDate);
                            Date toDate1 = sdf.parse(format);
                            Date d1 = new Date(fromDate1.getTime());
                            Date d2 = new Date(toDate1.getTime());
                            int num = d1.compareTo(d2);
                            //条件满足 为重培
                            if (num > 0) {
                                docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                            }
                        }
                    }
                }
            }
        }
        int result = jswjwBiz.saveDoctorRecruit(docRecWithBLOBs);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return resultMap;
        } else {
            return ResultDataThrow("保存失败！");
        }
    }

    @RequestMapping(value = "/doctorRegister", method = {RequestMethod.POST})
    @ResponseBody
    public Object doctorRegister(String assignYear, String userFlow, String orgFlow, Integer pageIndex, Integer pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功！");
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        if (null == sysUser) {
            return ResultDataThrow("用户不存在");
        }
        //构建查询对象 填充属性 begin
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        PageHelper.startPage(pageIndex, pageSize);
        recruit.setDoctorFlow(userFlow);
        recruit.setRecruitYear(assignYear);
        recruit.setOrgFlow(orgFlow);
        //构建查询对象end
        //根据当前用户的 userFlow  查询属于自己的报名信息
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "");
        resultMap.put("recruitList", recruitList);
        resultMap.put("dataCount", PageHelper.total);
        resultMap.put("userName", sysUser.getUserName());
        return resultMap;
    }

    @RequestMapping(value = "/detailRegister", method = {RequestMethod.POST})
    @ResponseBody
    public Object detailRegister(String recruitFlow, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功！");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (null == currUser) {
            return ResultDataThrow("用户不存在");
        }
        String doctorFlow = currUser.getUserFlow();
        //构建报名信息类的对象 用于操作数据库
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        ResDoctorRecruitWithBLOBs docRecWithBLOBs = new ResDoctorRecruitWithBLOBs();
        docRecWithBLOBs.setDoctorFlow(doctorFlow);
        docRecWithBLOBs.setRecruitFlow(recruitFlow);
        List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwBiz.searchResDoctorRecruitList(docRecWithBLOBs, "");
        resultMap.put("currUser", currUser);
        ResDoctorRecruit resDoctorRecruit = new ResDoctorRecruit();
        if (recruits.size() > 0) {
            resDoctorRecruit = recruits.get(0);
        }
        resultMap.put("recruit", resDoctorRecruit);
        List<JsResDoctorRecruitExt> recruitExtList = jswjwBiz.resDoctorRecruitExtList3New(resDoctorRecruit);
        Map<String, String> rankNumMap = new HashMap<>();
        if (null != recruitExtList && recruitExtList.size() > 0) {
            for (JsResDoctorRecruitExt ext : recruitExtList) {
                rankNumMap.put(ext.getRecruitFlow() + ext.getDoctorFlow(), ext.getRankNum());
            }
        }
        resultMap.put("rankNumMap", rankNumMap);
        return resultMap;
    }

    @RequestMapping(value = "/editDoctorRecruitNew", method = {RequestMethod.POST})
    @ResponseBody
    public Object editDoctorRecruitNew(String recruitFlow, String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功！");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (null == currUser) {
            return ResultDataThrow("用户不存在");
        }
        String doctorFlow = currUser.getUserFlow();
        ResDoctorRecruit doctorRecruit = null;
        String addFlag = "";
        if (StringUtil.isNotBlank(recruitFlow)) {
            addFlag = "edit";
            doctorRecruit = jswjwBiz.readResDoctorRecruit(recruitFlow);
            resultMap.put("catSpeId", doctorRecruit.getCatSpeId());
            resultMap.put("catSpeName", doctorRecruit.getCatSpeName());
            resultMap.put("doctorRecruit", doctorRecruit);
            resultMap.put("addRecord", com.pinde.core.common.GlobalConstant.FLAG_Y);//添加新的培训记录标识
        }
        //已审核通过
        ResDoctorRecruit passedRec = new ResDoctorRecruit();
        passedRec.setDoctorFlow(doctorFlow);
        passedRec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        passedRec.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        List<com.pinde.core.model.ResDoctorRecruit> passedRecruitList = jswjwBiz.searchResDoctorRecruitList(passedRec, "MODIFY_TIME DESC");
        //其中一阶段、住院医师审核通过（选二阶段使用）
        List<com.pinde.core.model.ResDoctorRecruit> prevPassedList = new ArrayList<com.pinde.core.model.ResDoctorRecruit>();
        if (passedRecruitList != null && !passedRecruitList.isEmpty()) {
            resultMap.put("passedRecruitList", passedRecruitList);
            //记录审核通过的培训类别(不包含首条为二阶段自动生成的一阶段)
            List<String> catSpeIdList = new ArrayList<String>();
            for (ResDoctorRecruit rec : passedRecruitList) {
                String catSpeId = rec.getCatSpeId();
                if (!catSpeIdList.contains(catSpeId) && StringUtil.isNotBlank(rec.getSpeId())) {
                    catSpeIdList.add(catSpeId);
                }
                if (StringUtil.isBlank(rec.getSpeId())) {//首条为二阶段
                    resultMap.put("firstRecIsWMSecond", true);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId().equals(rec.getCatSpeId()) || com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(rec.getCatSpeId())) {
                    prevPassedList.add(rec);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(rec.getCatSpeId())) {//二阶段审核通过
                    resultMap.put("isWMSecondRecPassed", true);
                }
            }
            resultMap.put("catSpeIdList", catSpeIdList);
        }
        if (prevPassedList != null && !prevPassedList.isEmpty()) {
            resultMap.put("prevPassedList", prevPassedList);
            if (StringUtil.isNotBlank(recruitFlow)) {//回显上一阶段  结业证书
                resultMap.put("latestPrevPassed", prevPassedList.get(0));
            }
        }
        SysCfg startDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_start");
        SysCfg endDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_end");
        resultMap.put("startDate", startDate.getCfgValue());
        resultMap.put("endDate", endDate.getCfgValue());
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        resultMap.put("upload_base_url", uploadBaseUrl);
        //已培养年限
        List<SysDict> yearSysDictList = com.pinde.core.common.enums.DictTypeEnum.YetTrainYear.getSysDictList();
        //医师状态
        List<SysDict> doctorStatusSysDictList = com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getSysDictList();
        //医师走向
        List<SysDict> doctorStrikeSysDictList = com.pinde.core.common.enums.DictTypeEnum.DoctorStrike.getSysDictList();
        resultMap.put("yearSysDictList", yearSysDictList);
        resultMap.put("doctorStatusSysDictList", doctorStatusSysDictList);
        resultMap.put("doctorStrikeSysDictList", doctorStrikeSysDictList);
        return resultMap;
    }

    @RequestMapping(value = "/doRegister", method = {RequestMethod.POST})
    @ResponseBody
    public Object doRegister(com.pinde.core.model.ResDoctorRecruit recruit, String userFlow, String prevRecruitFlow, String prevCompleteFileUrl, String prevCompleteCertNo) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "报到成功！");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (null == currUser) {
            return ResultDataThrow("用户不存在");
        }
        String doctorFlow = currUser.getUserFlow();
        //构建报名信息类的对象 用于操作数据库
//        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        ResDoctorRecruitWithBLOBs docRecWithBLOBs = new ResDoctorRecruitWithBLOBs();
        docRecWithBLOBs.setDoctorFlow(doctorFlow);
        docRecWithBLOBs.setRecruitFlow(recruit.getRecruitFlow());
        docRecWithBLOBs.setProveFileUrl(recruit.getProveFileUrl());//减免附件
        if ("21".equals(docRecWithBLOBs.getDoctorStatusId())) {
            docRecWithBLOBs.setSpecialFileUrl(prevCompleteFileUrl);
            docRecWithBLOBs.setSpecialCertNo(prevCompleteCertNo);
        }

        //确认报到之前，先查询该条记录是否已被基地录取
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(docRecWithBLOBs, "");
        boolean recruitFlag = false;
        if (null != recruitList && recruitList.size() >= 0) {
            recruitFlag = com.pinde.core.common.GlobalConstant.FLAG_Y.equalsIgnoreCase(recruitList.get(0).getRecruitFlag()) ? true : false;
            if (!recruitFlag) {
                return ResultDataThrow("需要等待基地确认录取，学员方可确认报到");
            }
            boolean confirmFlag = com.pinde.core.common.GlobalConstant.FLAG_Y.equalsIgnoreCase(recruitList.get(0).getConfirmFlag()) ? true : false;
            if (confirmFlag) {
                return ResultDataThrow("已经报到过了，无需重复确认");
            }
        }
        //confirmFlag字段 当学员确认报到 置为Y 表示学员已报到
        docRecWithBLOBs.setConfirmFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);

        //查询专业轮转方案 设置学员轮转方案
        String catSpeId = recruit.getCatSpeId();
        SchRotation rotation = new SchRotation();
        // 此处不能直接使用住院医师类型，助理全科无法查到培训方案
        rotation.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
        if (RecDocCategoryEnum.AssiGeneral.getId().equals(catSpeId)) {
            rotation.setDoctorCategoryId(catSpeId);
        }
        String speId = recruit.getSpeId();
        rotation.setSpeId(speId);
        rotation.setPublishFlag(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SchRotation> rotationList = jswjwBiz.searchOrgStandardRotation(rotation);
        if (null != rotationList && rotationList.size() > 0) {
            // 判断如果是全科 使用2020年新培训方案 需求1453
            if ("50".equals(speId)) {
                for (SchRotation schRotation : rotationList) {
                    if (schRotation.getRotationName().contains("2020西医助理")) {
                        docRecWithBLOBs.setRotationFlow(schRotation.getRotationFlow());
                        docRecWithBLOBs.setRotationName(schRotation.getRotationName());
                        break;
                    }
                }
                if (StringUtil.isBlank(docRecWithBLOBs.getRotationFlow())) {
                    return ResultDataThrow("助理全科暂停使用旧方案，请维护助理全科专业2020西医助理全科培训方案");
                }
            } else {
                docRecWithBLOBs.setRotationFlow(rotationList.get(0).getRotationFlow());
                docRecWithBLOBs.setRotationName(rotationList.get(0).getRotationName());
            }
        }
        docRecWithBLOBs.setCurrDegreeCategoryId(recruit.getCurrDegreeCategoryId());
        docRecWithBLOBs.setRecruitDate(recruit.getRecruitDate());
        docRecWithBLOBs.setSessionNumber(recruit.getSessionNumber());
        docRecWithBLOBs.setTrainYear(recruit.getTrainYear());
        docRecWithBLOBs.setYetTrainYear(recruit.getYetTrainYear());
        docRecWithBLOBs.setDoctorStatusId(recruit.getDoctorStatusId());
        docRecWithBLOBs.setDoctorStrikeId(recruit.getDoctorStrikeId());
        docRecWithBLOBs.setCatSpeId(recruit.getCatSpeId());
        docRecWithBLOBs.setCatSpeName(recruit.getCatSpeName());
        //医师状态
        if (StringUtil.isNotBlank(recruit.getDoctorStatusId())) {
            docRecWithBLOBs.setDoctorStatusName(com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getDictNameById(recruit.getDoctorStatusId()));
        }
        //医师走向
        if (StringUtil.isNotBlank(recruit.getDoctorStrikeId())) {
            docRecWithBLOBs.setDoctorStrikeName(com.pinde.core.common.enums.DictTypeEnum.DoctorStrike.getDictNameById(recruit.getDoctorStrikeId()));
        }
        if (StringUtil.isNotBlank(recruit.getCurrDegreeCategoryId())) {
            docRecWithBLOBs.setCurrDegreeCategoryName(com.pinde.core.common.enums.JsResDegreeCategoryEnum.getNameById(recruit.getCurrDegreeCategoryId()));
        }

        //结业审核年份
        int year = 0;
        if (StringUtil.isNotBlank(docRecWithBLOBs.getSessionNumber()) && StringUtil.isNotBlank(docRecWithBLOBs.getTrainYear())) {
            int num = 0;
            if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(docRecWithBLOBs.getTrainYear())) {
                num = 1;
            }
            if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(docRecWithBLOBs.getTrainYear())) {
                num = 2;
            }
            if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(docRecWithBLOBs.getTrainYear())) {
                num = 3;
            }
            year = Integer.parseInt(docRecWithBLOBs.getSessionNumber()) + num;
            docRecWithBLOBs.setGraduationYear(year + "");
        }
        //报名通道确认报到后进去报到审核
        docRecWithBLOBs.setAuditStatusId("Auditing");
        docRecWithBLOBs.setAuditStatusName("待报到审核");
        if (StringUtil.isNotBlank(recruitList.get(0).getJointOrgFlow())) {
            docRecWithBLOBs.setJointOrgAudit("Auditing");
            docRecWithBLOBs.setOrgAudit("Auditing");
        } else {
            docRecWithBLOBs.setJointOrgAudit("Passed");
            docRecWithBLOBs.setOrgAudit("Auditing");
        }
        int result = jswjwBiz.saveDoctorRecruit(docRecWithBLOBs);
        if (result > 0) {
            return resultMap;
        } else {
            return ResultDataThrow("交易失败！");
        }
    }

    @RequestMapping(value = {"/uploadDoctorFile"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object uploadDoctorFile(MultipartFile file, String userFlow, String fileType) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "上传成功！");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (file != null && !file.isEmpty()) {
            String checkResult = "";
            if (StringUtil.isNotEmpty(fileType)) {
                if ("idCard".equals(fileType)) {
                    //身份证图片校验
                    checkResult = jswjwBiz.checkUserHeader(file);
                } else {
                    checkResult = jswjwBiz.checkImg(file);
                }

            }
            String resultPath = "";
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(checkResult)) {
                return ResultDataThrow(checkResult);
            } else {
                if (StringUtil.isNotEmpty(fileType)) {
                    if ("idCard".equals(fileType)) {
                        //身份证
                        resultPath = jswjwBiz.saveFileToDirsNew("", file, "idCard", fileType);
                    } else if ("userImages".equals(fileType)) {
                        //头像
                        resultPath = jswjwBiz.saveFileToDirsNew("", file, "userImages", fileType);
                    } else {
                        resultPath = jswjwBiz.saveFileToDirsNew("", file, "jsresImages", fileType);
                    }
                }
                if (StringUtil.isNotEmpty(resultPath)) {
                    SysCfg upload_base_url = cfgMapper.selectByPrimaryKey("upload_base_url");
                    resultMap.put("showPath", upload_base_url.getCfgValue() + "/" + resultPath.replaceAll("\\\\", "/"));
                    resultMap.put("resultPath", resultPath.replaceAll("\\\\", "/"));
                }
            }
        } else {
            return ResultDataThrow("文件为空！");
        }
        return resultMap;
    }

    @RequestMapping(value = "/doctorMain", method = {RequestMethod.POST})
    @ResponseBody
    public Object doctorMain(String userFlow) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功！");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysCfg sysCfg = cfgMapper.selectByPrimaryKey("jsres_is_train");
        resultMap.put("jsres_is_train", sysCfg.getCfgValue());
        String isRetrain = com.pinde.core.common.GlobalConstant.FLAG_N;
        String isDoctorSend = com.pinde.core.common.GlobalConstant.FLAG_N;
        //获取培训记录
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        String doctorFlow = currUser.getUserFlow();
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
        if (recruitList != null && !recruitList.isEmpty()) {
            List<com.pinde.core.model.ResDoctorRecruit> doctorSend = recruitList.stream().filter(r -> StringUtil.isNotEmpty(r.getSignupWay()) && r.getSignupWay().equals("DoctorSend")).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(doctorSend)) {
                String recruitFlow = doctorSend.get(0).getRecruitFlow();
                resultMap.put("recruitFlow", recruitFlow);
                isDoctorSend = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
            resultMap.put("recruitList", recruitList);
            for (ResDoctorRecruit rec : recruitList) {
                if (com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId().equals(rec.getAuditStatusId())
                        || com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId().equals(rec.getAuditStatusId())
                        || com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotPassed.getId().equals(rec.getAuditStatusId())) {
                    resultMap.put("unPassed", true);
                }
                //20 在培 21结业
                int trainYear = 0;
                if (StringUtil.isNotEmpty(rec.getDoctorStatusId()) && !"21".equals(rec.getDoctorStatusId())) {
                    //培训年限
                    if (StringUtil.isNotEmpty(rec.getTrainYear())) {
//                    'OneYear' then '一年' 'TwoYear' then '两年' 'ThreeYear' then '三年'
                        if ("OneYear".equals(rec.getTrainYear())) {
                            trainYear = 1;
                        } else if ("TwoYear".equals(rec.getTrainYear())) {
                            trainYear = 2;
                        } else if ("ThreeYear".equals(rec.getTrainYear())) {
                            trainYear = 3;
                        }
                    }
                    if (StringUtil.isNotEmpty(rec.getRecruitDate())) {
                        //培训起始时间
                        String recruitDate = rec.getRecruitDate();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar cd = Calendar.getInstance();
                        try {
                            cd.setTime(sdf.parse(recruitDate));
                        } catch (ParseException e) {
                            logger.error("", e);
                        }
                        cd.add(Calendar.YEAR, trainYear + 3);//增加n年
                        String format = sdf.format(cd.getTime());
                        String currDate = DateUtil.getCurrDate();

                        //当前时间
                        Date fromDate1 = sdf.parse(currDate);
                        Date toDate1 = sdf.parse(format);
                        Date d1 = new Date(fromDate1.getTime());
                        Date d2 = new Date(toDate1.getTime());
                        int num = d1.compareTo(d2);
                        //条件满足 为重培
                        if (num > 0) {
                            isRetrain = com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
                        } else {
                            isRetrain = com.pinde.core.common.GlobalConstant.RECORD_STATUS_N;
                        }
                    }
                } else {
                    isRetrain = com.pinde.core.common.GlobalConstant.RECORD_STATUS_N;
                }

            }
        }
        ResDocotrDelayTeturn docotrBackTeturn = new ResDocotrDelayTeturn();
        docotrBackTeturn.setDoctorFlow(doctorFlow);
        docotrBackTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
        List<ResDocotrDelayTeturn> backList = jswjwBiz.searchInfo(docotrBackTeturn, null, null, null);
        resultMap.put("resRecList", backList);
        resultMap.put("isDoctorSend", isDoctorSend);
        //如果有退赔记录 则不根据时间判断
        if (CollectionUtils.isNotEmpty(backList)) {
            for (ResDocotrDelayTeturn resDocotrDelayTeturn : backList) {
                if (StringUtil.isNotEmpty(resDocotrDelayTeturn.getAuditStatusName()) && "审核通过".equals(resDocotrDelayTeturn.getAuditStatusName())) {
                    List<com.pinde.core.model.ResDoctorRecruit> collect = recruitList.stream().filter(ResDoctorRecruit -> ResDoctorRecruit.getDoctorStatusId().equals("20")).collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(collect)) {
                        isRetrain = com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
                    }
                }
            }
        }
        resultMap.put("isRetrain", isRetrain);
        ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
        docotrDelayTeturn.setDoctorFlow(doctorFlow);
        docotrDelayTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
        List<ResDocotrDelayTeturn> delayList = jswjwBiz.searchInfo(docotrDelayTeturn, null, null, null);
        resultMap.put("delayList", delayList);
        SysCfg startDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_start");
        SysCfg endDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_end");
        resultMap.put("startDate", startDate.getCfgValue());
        resultMap.put("endDate", endDate.getCfgValue());
        String isButton = com.pinde.core.common.GlobalConstant.FLAG_N;
        if (null == sysCfg || com.pinde.core.common.GlobalConstant.FLAG_Y.equals(sysCfg.getCfgValue())) {
            if (CollectionUtils.isEmpty(recruitList) || "21".equals(recruitList.get(recruitList.size() - 1).getDoctorStatusId()) || com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRetrain)) {
                isButton = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        resultMap.put("isButton", isButton);
        return resultMap;
    }

    @RequestMapping(value = "/searchOrgListNew", method = {RequestMethod.POST})
    @ResponseBody
    public Object searchOrgListNew(SysOrg sysOrg, String catSpeId) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功！");
        List<SysOrg> orgList = null;
        sysOrg.setOrgTypeId("Hospital");
        if ("DoctorTrainingSpe".equals(catSpeId)) {
            List<String> orgLevelList = new ArrayList<String>();
            orgLevelList.add("CountryOrg");
            orgList = jswjwBiz.searchOrgNotJointOrg(sysOrg, orgLevelList);
        } else {
            sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            orgList = jswjwBiz.searchOrg(sysOrg);
        }
        resultMap.put("orgList", orgList);
        return resultMap;
    }

    /**
     * 加载基地专业
     *
     * @return
     */
    @RequestMapping(value = "/searchResOrgSpeListNew", method = {RequestMethod.POST})
    @ResponseBody
    public Object searchResOrgSpeListNew(String userFlow, String sessionNumber, String trainCategoryTypeId, ResOrgSpe resOrgSpe) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功！");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        List<ResOrgSpe> speList = null;
        Map<String, List> map = new HashMap<>();
        resOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        String speFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        List<Map<String, String>> docSpeList = jswjwBiz.searchDoctorSpe();
        if (null != docSpeList && docSpeList.size() > 0) {
            for (Map<String, String> spe : docSpeList) {
                String idNo = spe.get("ID_NO");
                String doctorTypeId = spe.get("DOCTOR_TYPE_ID");
                if (idNo.equals(sysUser.getIdNo()) && !"Graduate".equals(doctorTypeId)) {
                    speFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    break;
                }
            }
        }

        if (StringUtil.isNotBlank(sessionNumber)) {
            int year = Integer.parseInt(sessionNumber);
            if (year >= 2015) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(speFlag)) {
                    speList = jswjwBiz.searchResOrgSpeListNew(resOrgSpe, trainCategoryTypeId, speFlag);
                } else {
                    speList = jswjwBiz.searchResOrgSpeListNew(resOrgSpe, trainCategoryTypeId, speFlag);
                }
            } else {
                String speTypeId = resOrgSpe.getSpeTypeId();
                if (StringUtil.isNotBlank(speTypeId)) {
                    Map<String, String> speMap = InitConfig.getDictNameMap(speTypeId);
                    speList = new ArrayList<ResOrgSpe>();
                    ResOrgSpe orgSpe = null;
                    for (Map.Entry<String, String> m : speMap.entrySet()) {
                        orgSpe = new ResOrgSpe();
                        orgSpe.setSpeId(m.getValue());
                        orgSpe.setSpeName(m.getKey());
                        speList.add(orgSpe);
                    }
                }
            }

            List<ResJointOrg> jointOrgs = jswjwBiz.searchResJointByOrgFlow(resOrgSpe.getOrgFlow());
            map.put("main", speList);
            if (jointOrgs != null && !jointOrgs.isEmpty()) {
                map.put("joint", jointOrgs);
            }
            resultMap.put("map", map);
        }
        return resultMap;
    }

    /**
     * 判断同一个级别是否存在相同的记录
     *
     * @return
     */
    @RequestMapping(value = "/validate", method = {RequestMethod.POST})
    @ResponseBody
    public Object validate(String doctorFlow, String sessionNumber) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功！");
        if (StringUtil.isEmpty(doctorFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        ResDoctor doctor = jswjwBiz.readDoctor(doctorFlow);
        if (null == doctor) {
            resultMap.put("resultType", "请补全基本信息");
            resultMap.put("isPass", com.pinde.core.common.GlobalConstant.FLAG_N);
            return resultMap;
        }
        if (StringUtil.isNotBlank(doctorFlow) && StringUtil.isNotBlank(sessionNumber)) {
            com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setSessionNumber(sessionNumber);
            recruit.setDoctorFlow(doctorFlow);
            recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
            List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwBiz.readDoctorRecruits(recruit);
            if (recruit != null && (!recruits.isEmpty())) {
                resultMap.put("resultType", "您在当前届别，已添加过培训记录，无法继续添加！");
                resultMap.put("isPass", com.pinde.core.common.GlobalConstant.FLAG_N);
                return resultMap;
            } else {
                resultMap.put("isPass", com.pinde.core.common.GlobalConstant.FLAG_Y);
                return resultMap;
            }
        }
        resultMap.put("isPass", com.pinde.core.common.GlobalConstant.FLAG_Y);
        return resultMap;
    }


    @RequestMapping(value = "/saveResDoctorRecruitNew", method = {RequestMethod.POST})
    @ResponseBody
    public Object saveResDoctorRecruitNew(ResDoctorRecruitWithBLOBs docRecWithBLOBs, String prevRecruitFlow, String prevCompleteFileUrl, String prevCompleteCertNo, String userFlow) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "保存成功");
        docRecWithBLOBs.setSignupWay("DoctorSend");//学员报名方式 （DoctorSend：报送，DoctorSignup:报名）
        ResDoctorRecruitWithBLOBs prevDocRec = new ResDoctorRecruitWithBLOBs();//选择的结业阶段
        prevDocRec.setRecruitFlow(prevRecruitFlow);
        prevDocRec.setCompleteFileUrl(prevCompleteFileUrl);
        prevDocRec.setCompleteCertNo(prevCompleteCertNo);
        //住院医师需要协同基地审核后主基地审核，助理全科只需要选择的基地审核
        if ("DoctorTrainingSpe".equals(docRecWithBLOBs.getCatSpeId())) {
            if (StringUtil.isNotBlank(docRecWithBLOBs.getJointOrgFlow())) {
                docRecWithBLOBs.setJointOrgAudit("Auditing");
            } else {
                docRecWithBLOBs.setJointOrgAudit("Passed");
            }
            docRecWithBLOBs.setOrgAudit("Auditing");
        } else {
            //判断是否是协同基地
            List<ResJointOrg> tempJoinOrgs = jswjwBiz.searchResJointByJointOrgFlow(docRecWithBLOBs.getOrgFlow());
            if (!tempJoinOrgs.isEmpty() && tempJoinOrgs.size() > 0) {//是协同基地
                docRecWithBLOBs.setJointOrgAudit("Auditing");
                docRecWithBLOBs.setOrgAudit("Auditing");
                docRecWithBLOBs.setJointOrgFlow(docRecWithBLOBs.getOrgFlow());
                docRecWithBLOBs.setJointOrgName(docRecWithBLOBs.getOrgName());
            } else {
                docRecWithBLOBs.setJointOrgAudit("Passed");
                docRecWithBLOBs.setOrgAudit("Auditing");
                docRecWithBLOBs.setJointOrgFlow(docRecWithBLOBs.getOrgFlow());
                docRecWithBLOBs.setJointOrgName(docRecWithBLOBs.getOrgName());
            }
        }

        if (!com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(docRecWithBLOBs.getCatSpeId())) {
            docRecWithBLOBs.setCompleteFileUrl("");
            docRecWithBLOBs.setCompleteCertNo("");
            if ("21".equals(docRecWithBLOBs.getDoctorStatusId())) {
                //由于页面字段展示问题现将非二阶段的该条记录的结业附件
                // name = prevCompleteFileUrl 存入special_file_url（该条记录的结业附件）中
                docRecWithBLOBs.setSpecialFileUrl(prevCompleteFileUrl);
                docRecWithBLOBs.setSpecialCertNo(prevCompleteCertNo);
            } else {
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }
        } else {
            docRecWithBLOBs.setCompleteFileUrl(prevCompleteFileUrl);
            docRecWithBLOBs.setCompleteCertNo(prevCompleteCertNo);
            if (!"21".equals(docRecWithBLOBs.getDoctorStatusId())) {
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }
        }
        //查询专业轮转方案
        SchRotation rotation = new SchRotation();
        rotation.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId()); // 此处不能直接使用住院医师类型，助理全科无法查到培训方案
        rotation.setSpeId(docRecWithBLOBs.getSpeId());
        String catSpeId = docRecWithBLOBs.getCatSpeId();
        if (com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId().equals(catSpeId) || RecDocCategoryEnum.WMFirst.getId().equals(catSpeId)) {
            rotation.setDoctorCategoryId(catSpeId);
        }
        rotation.setPublishFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
        List<SchRotation> rotationList = jswjwBiz.searchOrgStandardRotation(rotation);
        if (null != rotationList && rotationList.size() > 0) {
            docRecWithBLOBs.setRotationFlow(rotationList.get(0).getRotationFlow());
            docRecWithBLOBs.setRotationName(rotationList.get(0).getRotationName());
        }
        ResDoctor doctor = jswjwBiz.readDoctor(docRecWithBLOBs.getDoctorFlow());
        docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        //查询是否重培（有退培记录且审核通过为重培） 默认否
        docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
        docotrDelayTeturn.setDoctorFlow(docRecWithBLOBs.getDoctorFlow());
        docotrDelayTeturn.setTypeId("ReturnTraining");
        List<String> flags = new ArrayList<>();
        flags.add("1");
        List<ResDocotrDelayTeturn> resRecList = jswjwBiz.searchInfo(docotrDelayTeturn, null, flags, null);
        if (resRecList.size() > 0) {
            docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        } else {
            //非结业记录 判断入培时间 + 培训年限 + 3年  如果没结业 则为重培
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchRecruitList(docRecWithBLOBs.getDoctorFlow());
            if (CollectionUtils.isNotEmpty(recruitList)) {
                for (ResDoctorRecruit resDoctorRecruit : recruitList) {
                    //20 在培 21结业
                    int trainYear = 0;
                    if (StringUtil.isNotEmpty(resDoctorRecruit.getDoctorStatusId()) && !"21".equals(resDoctorRecruit.getDoctorStatusId())) {
                        //培训年限
                        if (StringUtil.isNotEmpty(resDoctorRecruit.getTrainYear())) {
//                    'OneYear' then '一年' 'TwoYear' then '两年' 'ThreeYear' then '三年'
                            if ("OneYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 1;
                            } else if ("TwoYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 2;
                            } else if ("ThreeYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 3;
                            }
                        }
                        if (StringUtil.isNotEmpty(resDoctorRecruit.getRecruitDate())) {
                            //培训起始时间
                            String recruitDate = resDoctorRecruit.getRecruitDate();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cd = Calendar.getInstance();
                            try {
                                cd.setTime(sdf.parse(recruitDate));
                            } catch (ParseException e) {
                                logger.error("", e);
                            }
                            cd.add(Calendar.YEAR, trainYear + 3);//增加n年
                            String format = sdf.format(cd.getTime());
                            String currDate = DateUtil.getCurrDate();

                            //当前时间
                            Date fromDate1 = sdf.parse(currDate);
                            Date toDate1 = sdf.parse(format);
                            Date d1 = new Date(fromDate1.getTime());
                            Date d2 = new Date(toDate1.getTime());
                            int num = d1.compareTo(d2);
                            //条件满足 为重培
                            if (num > 0) {
                                docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                            }
                        }
                    }
                }
            }
        }
        int result = jswjwBiz.editResDoctorRecruit(docRecWithBLOBs, prevDocRec);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return resultMap;
        } else {
            return ResultDataThrow("保存失败");
        }
    }


    @RequestMapping(value = "/getDoctorRecruit", method = {RequestMethod.POST})
    @ResponseBody
    public Object getDoctorRecruit(String recruitFlow, String doctorFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功！");
        if (StringUtil.isEmpty(doctorFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(recruitFlow)) {
            return ResultDataThrow("recruitFlow为空");
        }
        boolean isLatest = false;//是否最新记录
        JsresRecruitDocInfo jsresRecruit = jswjwBiz.readRecruit(recruitFlow);
        resultMap.put("jsresRecruitDocInfo", jsresRecruit);
        ResDocotrDelayTeturn ret = jswjwBiz.findTeturnInfo(recruitFlow);
        resultMap.put("ret", ret);
        if (ret != null) {
            resultMap.put("haveReturn", com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        String applyYear = "";
        if (StringUtil.isNotBlank(recruitFlow)) {
            ResDoctorRecruit doctorRecruit = jswjwBiz.readResDoctorRecruit(recruitFlow);
            SysUser sysUser = jswjwBiz.readSysUser(doctorFlow);
            resultMap.put("user", sysUser);
            if (doctorRecruit != null) {
                applyYear = doctorRecruit.getGraduationYear();
                resultMap.put("doctorRecruit", doctorRecruit);
                if (StringUtil.isNotBlank(doctorRecruit.getDoctorFlow()) && !StringUtil.isNotBlank(doctorRecruit.getProveFileUrl())) {
                    ResDoctor doctor = jswjwBiz.readDoctor(doctorRecruit.getDoctorFlow());
                    if (doctor != null) {
                        resultMap.put("doctor", doctor);
                        String degreeType = doctor.getDegreeCategoryId();
                        if (com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)) {
                            PubUserResume resume = jswjwBiz.readPubUserResume(doctorRecruit.getDoctorFlow());
                            String content = resume.getUserResume();
                            if (StringUtil.isNotBlank(content)) {
                                UserResumeExtInfoForm userResumeExt = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
                                resultMap.put("userResumeExt", userResumeExt);
                                if (userResumeExt != null) {
                                    doctorRecruit.setProveFileUrl(userResumeExt.getDegreeUri());
                                }
                            }
                        }
                    }
                }
            }

            ResDoctorRecruit lastRecruit = new ResDoctorRecruit();
            lastRecruit.setDoctorFlow(doctorFlow);
            lastRecruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(lastRecruit, "CREATE_TIME DESC");
            if (recruitList != null && !recruitList.isEmpty()) {
                lastRecruit = recruitList.get(0);
                if (lastRecruit.getRecruitFlow().equals(recruitFlow)) {
                    isLatest = true;
                }
            }
            List<DoctorAuth> doctorAuths = jswjwBiz.searchAuthsByOperUserFlow(doctorFlow);
            if (doctorAuths != null & !doctorAuths.isEmpty()) {
                resultMap.put("resRec", doctorAuths.get(0));
            }
            //最新记录 && 审核通过
            if (com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId().equals(doctorRecruit.getAuditStatusId())) {
                ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
                docOrgHistory.setDoctorFlow(doctorFlow);
                docOrgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
                docOrgHistory.setRecruitFlow(recruitFlow);
                //List<String> changeStatusIdList = new ArrayList<String>();
                //changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyWaiting.getId());//待转出审核
                //changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
                List<ResDoctorOrgHistory> docOrgHistoryList = jswjwBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
                if (docOrgHistoryList != null && !docOrgHistoryList.isEmpty()) {
                    resultMap.put("docOrgHistoryList", docOrgHistoryList);
                    resultMap.put("latestDocOrgHistory", docOrgHistoryList.get(0));//最新变更记录
                }
                docOrgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
                List<ResDoctorOrgHistory> changeSpeList = jswjwBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
                if (changeSpeList != null && !changeSpeList.isEmpty()) {
                    resultMap.put("changeSpeList", changeSpeList);
                    resultMap.put("lastChangeSpe", changeSpeList.get(0));
                }
            }
        }
        //判断是否申请结业考核资格
        Boolean applyFlag = false;
        JsresGraduationApply jsresGraduationApply = jswjwBiz.searchByRecruitFlow(recruitFlow, applyYear);
        if (jsresGraduationApply == null) {
            applyFlag = true;
        }
        if (jsresGraduationApply != null) {
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.LocalNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())
                    || com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())
                    || com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())
                    || com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())) {
                applyFlag = true;
            }
        }
        resultMap.put("isLatest", isLatest);
        resultMap.put("applyFlag", applyFlag);
        resultMap.put("sessionNumber", cfgMapper.selectByPrimaryKey("jsres_doctorCount_sessionNumber").getCfgValue());
        SimpleDateFormat date = new SimpleDateFormat("yyyy");
        String year = date.format(new Date());
        resultMap.put("nowYear", year);
        SysCfg sysCfg = cfgMapper.selectByPrimaryKey("jsres_is_train");
        SysCfg sysCfg1 = cfgMapper.selectByPrimaryKey("upload_base_url");
        resultMap.put("jsres_is_train", sysCfg.getCfgValue());
        resultMap.put("upload_base_url", sysCfg1.getCfgValue());
        return resultMap;

    }

    /**
     * 提交培训信息
     *
     * @param recruitWithBLOBs
     * @return
     */
    @RequestMapping(value = "/submitResDoctorRecruit", method = {RequestMethod.POST})
    @ResponseBody
    public Object submitResDoctorRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功！");
        String auditStatusId = recruitWithBLOBs.getAuditStatusId();
        if (StringUtil.isNotBlank(auditStatusId)) {
            recruitWithBLOBs.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.getNameById(auditStatusId));
        }
        recruitWithBLOBs.setRecruitFlag(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        recruitWithBLOBs.setConfirmFlag(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        int result = jswjwBiz.saveDoctorRecruit(recruitWithBLOBs);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return resultMap;
        } else {
            return ResultDataThrow("提交失败！");
        }
    }

    @RequestMapping(value = "/delDoctorRecruit", method = {RequestMethod.POST})
    @ResponseBody
    public Object delDoctorRecruit(String recruitFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "删除成功！");
        ResDoctorRecruitWithBLOBs recruit = (ResDoctorRecruitWithBLOBs) jswjwBiz.readResDoctorRecruit(recruitFlow);
        if (recruit != null) {
            recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            int result = jswjwBiz.saveDoctorRecruit(recruit);
            if (result == com.pinde.core.common.GlobalConstant.ONE_LINE) {
                return resultMap;
            }
        }
        return ResultDataThrow("删除失败！");
    }

    @RequestMapping(value = "/saveGraduationInfo", method = {RequestMethod.POST})
    @ResponseBody
    public Object saveGraduationInfo(String recruitFlow, String specialFileUrl, String specialCertNo) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "保存成功！");
        if (StringUtil.isEmpty(recruitFlow)) {
            return ResultDataThrow("flow为空");
        }
        ResDoctorRecruitWithBLOBs recruitBlob = null;
        if (StringUtil.isNotBlank(recruitFlow)) {
            recruitBlob = jswjwBiz.readRecruitNew(recruitFlow);
            if (recruitBlob != null) {
                recruitBlob = new ResDoctorRecruitWithBLOBs();
                recruitBlob.setRecruitFlow(recruitFlow);
                recruitBlob.setSpecialCertNo(specialCertNo);
                recruitBlob.setSpecialFileUrl(specialFileUrl);
            } else {
                return ResultDataThrow("保存失败！");
            }
        } else {
            return ResultDataThrow("保存失败！");
        }
        int result = jswjwBiz.saveDoctorRecruit(recruitBlob);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return resultMap;
        }
        return ResultDataThrow("保存失败！");
    }

    //报送列表
    @RequestMapping(value = "/doctorSendList", method = {RequestMethod.POST})
    @ResponseBody
    public Object doctorSendList(String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功！");
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        if (null == sysUser) {
            return ResultDataThrow("用户不存在");
        }
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(userFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
        List<com.pinde.core.model.ResDoctorRecruit> doctorSend = new ArrayList<>();
        //筛选报送记录
        if (CollectionUtils.isNotEmpty(recruitList)) {
            doctorSend = recruitList.stream().filter(rd -> StringUtil.isNotEmpty(rd.getSignupWay()) && rd.getSignupWay().equals("DoctorSend")).collect(Collectors.toList());
        }
        resultMap.put("recruitList", doctorSend);
        resultMap.put("dataCount", recruitList.size());
        resultMap.put("userName", sysUser.getUserName());
        return resultMap;
    }

    @RequestMapping(value = "/editDoctorRecruitNew2", method = {RequestMethod.POST})
    @ResponseBody
    public Object editDoctorRecruitNew2(String recruitFlow, String userFlow, String doctorStatus) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功！");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (null == currUser) {
            return ResultDataThrow("用户不存在");
        }
        String doctorFlow = currUser.getUserFlow();
        ResDoctorRecruit doctorRecruit = null;
        String addFlag = "";
        if (StringUtil.isNotBlank(recruitFlow)) {
            addFlag = "edit";
            doctorRecruit = jswjwBiz.readResDoctorRecruit(recruitFlow);
            resultMap.put("catSpeId", doctorRecruit.getCatSpeId());
            resultMap.put("catSpeName", doctorRecruit.getCatSpeName());
            resultMap.put("doctorRecruit", doctorRecruit);
            resultMap.put("addRecord", com.pinde.core.common.GlobalConstant.FLAG_Y);//添加新的培训记录标识
        }
        //已审核通过
        ResDoctorRecruit passedRec = new ResDoctorRecruit();
        passedRec.setDoctorFlow(doctorFlow);
        passedRec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        passedRec.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        List<com.pinde.core.model.ResDoctorRecruit> passedRecruitList = jswjwBiz.searchResDoctorRecruitList(passedRec, "MODIFY_TIME DESC");
        //其中一阶段、住院医师审核通过（选二阶段使用）
        List<com.pinde.core.model.ResDoctorRecruit> prevPassedList = new ArrayList<com.pinde.core.model.ResDoctorRecruit>();
        if (passedRecruitList != null && !passedRecruitList.isEmpty()) {
            resultMap.put("passedRecruitList", passedRecruitList);
            //记录审核通过的培训类别(不包含首条为二阶段自动生成的一阶段)
            List<String> catSpeIdList = new ArrayList<String>();
            for (ResDoctorRecruit rec : passedRecruitList) {
                String catSpeId = rec.getCatSpeId();
                if (!catSpeIdList.contains(catSpeId) && StringUtil.isNotBlank(rec.getSpeId())) {
                    catSpeIdList.add(catSpeId);
                }
                if (StringUtil.isBlank(rec.getSpeId())) {//首条为二阶段
                    resultMap.put("firstRecIsWMSecond", true);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId().equals(rec.getCatSpeId()) || com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(rec.getCatSpeId())) {
                    prevPassedList.add(rec);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(rec.getCatSpeId())) {//二阶段审核通过
                    resultMap.put("isWMSecondRecPassed", true);
                }
            }
            resultMap.put("catSpeIdList", catSpeIdList);
        }
        if (prevPassedList != null && !prevPassedList.isEmpty()) {
            resultMap.put("prevPassedList", prevPassedList);
            if (StringUtil.isNotBlank(recruitFlow)) {//回显上一阶段  结业证书
                resultMap.put("latestPrevPassed", prevPassedList.get(0));
            }
        }
        SysCfg startDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_start");
        SysCfg endDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_end");
        resultMap.put("startDate", startDate.getCfgValue());
        resultMap.put("endDate", endDate.getCfgValue());
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        resultMap.put("upload_base_url", uploadBaseUrl);
        return resultMap;
    }

    @RequestMapping(value = "/getReductionInfo", method = {RequestMethod.POST})
    @ResponseBody
    public Object getReductionInfo(String recruitFlow, String doctorFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "保存成功！");
        if (StringUtil.isNotBlank(recruitFlow)) {
            ResDoctorRecruit doctorRecruit = jswjwBiz.readResDoctorRecruit(recruitFlow);
            if (doctorRecruit != null) {
                resultMap.put("doctorRecruit", doctorRecruit);
                if (StringUtil.isNotBlank(doctorRecruit.getDoctorFlow())) {
                    ResDoctor doctor = jswjwBiz.readDoctor(doctorRecruit.getDoctorFlow());
                    if (doctor != null) {
                        resultMap.put("doctor", doctor);
                    }
                }
            }
            ResDoctorReduction reduction = jswjwBiz.findReductionByRecruitFlow(recruitFlow);
            if (reduction != null) {
                List<PubFile> reductionFiles = pubFileBiz.findFileByTypeFlow("Reduction", reduction.getRecordFlow());
                resultMap.put("reductionFiles", reductionFiles);
            }
            //如果审核状态是空则可以编辑或者审核不通过
            if (reduction == null || StringUtil.isBlank(reduction.getAuditStatusId())
                    || JszyBaseStatusEnum.LocalUnPassed.getId().equals(reduction.getAuditStatusId())
                    || JszyBaseStatusEnum.GlobalUnPassed.getId().equals(reduction.getAuditStatusId())) {
                resultMap.put("canEdit", com.pinde.core.common.GlobalConstant.FLAG_Y);
            } else {
                resultMap.put("canEdit", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
            //1.助理全科2.已经减免的不可以申请减免3.只有审核通过的记录才可以减免
            //1.招录信息审核通过展示减免
            if (com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId().equals(doctorRecruit.getAuditStatusId())) {
                //2.助理全科不展示减免
                /*if (com.pinde.core.common.enums.JsResTrainYearEnum.TCMAssiGeneral.getId().equals(doctorRecruit.getCatSpeId())) {
                    resultMap.put("showReduction", com.pinde.core.common.GlobalConstant.FLAG_N);
                } else {
                    resultMap.put("showReduction", com.pinde.core.common.GlobalConstant.FLAG_Y);
                }*/
                resultMap.put("showReduction", com.pinde.core.common.GlobalConstant.FLAG_Y);
            } else {
                resultMap.put("showReduction", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
            resultMap.put("reduction", reduction);
        }
        return resultMap;
    }

    @RequestMapping(value = "/saveOutLock", method = {RequestMethod.POST})
    @ResponseBody
    public Object saveOutLock(String userFlow, String outDate) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "申请成功！");
        if (StringUtil.isEmpty(userFlow)) {
            resultMap.put("resultId", "31601");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        if (StringUtil.isEmpty(outDate)) {
            resultMap.put("resultId", "31601");
            resultMap.put("resultType", "outDate为空");
            return resultMap;
        }
        int i = jswjwBiz.saveOutLock(outDate, userFlow);
        if (i > 0) {
            return resultMap;
        }
        resultMap.put("resultId", "31601");
        resultMap.put("resultType", "保存失败！");
        return resultMap;
    }

    public String dayAddAndSub(String outTime, int day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(outTime));
        calendar.add(Calendar.DATE, day);
        Date startDate = calendar.getTime();

        return sdf.format(startDate);

    }

    @RequestMapping(value = "/checkOutTime", method = {RequestMethod.POST})
    @ResponseBody
    public Object checkOutTime(String userFlow, String processFlow, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "校验成功！");
        if (StringUtil.isEmpty(userFlow)) {
            resultMap.put("resultId", "31601");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        if (StringUtil.isEmpty(processFlow)) {
            resultMap.put("resultId", "31601");
            resultMap.put("resultType", "processFlow为空");
            return resultMap;
        }
        //超时
        resultMap.put("isOutTime", com.pinde.core.common.GlobalConstant.FLAG_N);
        resultMap.put("outDate", "");
        resultMap.put("checkDay", "");
        ResDoctor doctor = jswjwBiz.readDoctor(userFlow);
        String orgFlow = doctor.getOrgFlow();
        String isChargeOrg = jswjwBiz.getJsResCfgAppMenu(doctor.getDoctorFlow());
        if (StringUtil.isNotEmpty(processFlow)) {
            ResDoctorSchProcess resDoctorSchProcess = jswjwBiz.readSchProcessByResultFlow(processFlow);
            if (null != resDoctorSchProcess && (StringUtil.isNotEmpty(resDoctorSchProcess.getOutDate()) || StringUtil.isNotEmpty(resDoctorSchProcess.getSchEndDate()))) {
                //申请解锁记录
                List<ResOutOfficeLock> resOutOfficeLockList = jswjwBiz.readResOutOfficeLock(userFlow, "");
                if (CollectionUtils.isNotEmpty(resOutOfficeLockList)) {
                    resultMap.put("auditStatusId", resOutOfficeLockList.get(0).getAuditStatusId());
                } else {
                    resultMap.put("auditStatusId", "");
                    resOutOfficeLockList = new ArrayList<>();
                }
                //基地限制收费用户时间
                String outDay = jswjwBiz.getJsResCfgCode("out_day_check_" + orgFlow);
                //后台收费用户限制时间
                String outDayOne = jswjwBiz.getCfgCode("res_out_day_one_cfg");
                //后台免费用户限制时间
                String outDayTwo = jswjwBiz.getCfgCode("res_out_day_two_cfg");
                if (StringUtil.isNotBlank(isChargeOrg)) {
                    if (StringUtil.isNotEmpty(outDay) && !"-1".equals(outDay)) {
                        //出科时间 + outDay > 当前时间 提示超出outDay时间不可填写数据
                        if (StringUtil.isEmpty(resDoctorSchProcess.getOutDate())) {
                            return resultMap;
                        }
                        String outDate = resDoctorSchProcess.getOutDate();
                        String outDateTwo = "";
                        int day = Integer.parseInt(outDay);
                        //审核通过 审核时间+5天
                        List<ResOutOfficeLock> passList = resOutOfficeLockList.stream().filter(resOutOfficeLock -> "Passed".equals(resOutOfficeLock.getAuditStatusId())).sorted(Comparator.comparing(ResOutOfficeLock::getCreateTime))
                                .collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(passList)) {
                            ResOutOfficeLock resOutOfficeLock = passList.get(0);
                            String modifyTime = resOutOfficeLock.getModifyTime();
                            outDateTwo = dayAddAndSub(modifyTime, 5);
                        } else {
                            outDateTwo = dayAddAndSub(outDate, day);
                        }
                        String currDate = DateUtil.getCurrDate();
                        int days = differentDays(outDate, currDate);
                        int num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), outDateTwo);
                        if (num > 0) {
                            //超时
                            resultMap.put("isOutTime", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            //出科时间
                            resultMap.put("outDate", outDate);
                            //超出出科时间天数
                            resultMap.put("checkDay", days);
                        }
                    } else {
                        if (StringUtil.isNotEmpty(outDayOne) && !"-1".equals(outDayOne)) {
                            //出科时间 + outDayOne > 当前时间 提示超出outDayOne时间不可填写数据
                            if (StringUtil.isEmpty(resDoctorSchProcess.getOutDate())) {
                                return resultMap;
                            }
                            String outDate = resDoctorSchProcess.getOutDate();
                            String outDateTwo = "";
                            int day = Integer.parseInt(outDayOne);
                            //审核通过 审核时间+5天
                            List<ResOutOfficeLock> passList = resOutOfficeLockList.stream().filter(resOutOfficeLock -> "Passed".equals(resOutOfficeLock.getAuditStatusId())).sorted(Comparator.comparing(ResOutOfficeLock::getCreateTime))
                                    .collect(Collectors.toList());
                            if (CollectionUtils.isNotEmpty(passList)) {
                                ResOutOfficeLock resOutOfficeLock = passList.get(0);
                                String modifyTime = resOutOfficeLock.getModifyTime();
                                outDateTwo = dayAddAndSub(modifyTime, 5);
                            } else {
                                outDateTwo = dayAddAndSub(outDate, day);
                            }
                            String currDate = DateUtil.getCurrDate();
                            int days = differentDays(outDate, currDate);
                            int num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), outDateTwo);
                            if (num > 0) {
                                //超时
                                resultMap.put("isOutTime", com.pinde.core.common.GlobalConstant.FLAG_Y);
                                //出科时间
                                resultMap.put("outDate", outDate);
                                //超出出科时间天数
                                resultMap.put("checkDay", days);
                            }
                        }
                    }

                } else {
                    //免费用户
                    if (StringUtil.isNotEmpty(outDayTwo) && !"-1".equals(outDayTwo)) {
                        //出科时间 + outDayTwo > 当前时间 提示超出outDayTwo时间不可填写数据
                        String outDate = resDoctorSchProcess.getSchEndDate();
                        String outDateTwo = "";
                        int day = Integer.parseInt(outDayTwo);
                        //审核通过 审核时间+5天
                        List<ResOutOfficeLock> passList = resOutOfficeLockList.stream().filter(resOutOfficeLock -> "Passed".equals(resOutOfficeLock.getAuditStatusId())).sorted(Comparator.comparing(ResOutOfficeLock::getCreateTime))
                                .collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(passList)) {
                            ResOutOfficeLock resOutOfficeLock = passList.get(0);
                            String modifyTime = resOutOfficeLock.getModifyTime();
                            outDateTwo = dayAddAndSub(modifyTime, 5);
                        } else {
                            outDateTwo = dayAddAndSub(outDate, day);
                        }
                        String currDate = DateUtil.getCurrDate();
                        int days = differentDays(outDate, currDate);
                        int num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), outDateTwo);
                        if (num > 0) {
                            //超时
                            resultMap.put("isOutTime", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            //出科时间
                            resultMap.put("outDate", outDate);
                            //超出出科时间天数
                            resultMap.put("checkDay", days);
                        }
                    }
                }
            }
        }
        return resultMap;
    }

    public int differentDays(String date1, String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date3 = sdf.parse(date1);
        Date date4 = sdf.parse(date2);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date3);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date4);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {//同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else {// 不同年
            return day2 - day1;
        }
    }

    public Map<String, Object> assDataList(Map<String, Object> resutMap) {
        List<SchRotationGroup> groupList = (List<SchRotationGroup>) resutMap.get("groupList");
        if (CollectionUtils.isEmpty(groupList)) {
            resutMap.put("dataList", null);
            return resutMap;
        }
        SchRotation rotation = (SchRotation) resutMap.get("rotation");
        if (null == rotation) {
            resutMap.put("dataList", null);
            return resutMap;
        }
        List<AssDataForm> dataList = new ArrayList<>();
        for (SchRotationGroup schRotationGroup : groupList) {
            Map<String, List<SchRotationDept>> rotationDeptMap = (Map<String, List<SchRotationDept>>) resutMap.get("rotationDeptMap");
            if (null == rotationDeptMap) {
                continue;
            }
            List<SchRotationDept> schRotationDepts = rotationDeptMap.get(schRotationGroup.getGroupFlow());
            if (CollectionUtils.isNotEmpty(schRotationDepts)) {
                for (SchRotationDept dept : schRotationDepts) {
                    String rotationType = "";
                    String standardDeptName = "";
                    String schMonth1 = "";
                    String schMonth2 = "";
                    String schDeptName = "";
                    String schDate1 = "";
                    String schDate2 = "";
                    String imageUrl = "";
                    String completeBi = "";
                    String outCompleteBi = "";
                    String auditBi = "";
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(rotation.getIsStage())) {
                        rotationType = rotationType + schRotationGroup.getSchStageName();
                    }
                    rotationType = rotationType + schRotationGroup.getGroupName();
                    if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(dept.getIsRequired())) {
                        rotationType = rotationType + "【选科】";
                    }
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(dept.getIsRequired())) {
                        rotationType = rotationType + "【必轮】";
                    }
                    rotationType = rotationType + "(轮转时间" + schRotationGroup.getSchMonth() + "月";
                    if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(schRotationGroup.getIsRequired())) {
                        rotationType = rotationType + schRotationGroup.getSelTypeName() + ":" + schRotationGroup.getDeptNum() + ")";
                    }
                    if ("Free".equals(schRotationGroup.getIsRequired())) {
                        rotationType = rotationType + "~" + schRotationGroup.getMaxDeptNum() + ")";
                    }
                    rotationType = rotationType + ")";
                    Map<String, Object> biMap = (Map<String, Object>) resutMap.get("biMap");
                    if (null == biMap) {
                        continue;
                    }
                    JsresDoctorDeptDetail d = (JsresDoctorDeptDetail) biMap.get(dept.getRecordFlow());
                    Map<String, List<SchArrangeResult>> resultMap1 = (Map<String, List<SchArrangeResult>>) resutMap.get("resultMap1");
                    Map<String, Object> afterImgMap = (Map<String, Object>) resutMap.get("afterImgMap");
                    if (null == resultMap1) {
                        AssDataForm assDataForm = new AssDataForm();
                        assDataForm.setRotationType(rotationType);
                        assDataForm.setStandardDeptName(standardDeptName);
                        assDataForm.setSchMonth1(schMonth1);
                        assDataForm.setSchMonth2(schMonth2);
                        assDataForm.setSchDeptName(schDeptName);
                        assDataForm.setSchDate1(schDate1);
                        assDataForm.setSchDate2(schDate2);
                        assDataForm.setImageUrl(imageUrl);
                        assDataForm.setCompleteBi(completeBi);
                        assDataForm.setOutCompleteBi(outCompleteBi);
                        assDataForm.setAuditBi(auditBi);
                        dataList.add(assDataForm);
                    }
                    int num = 0;
                    List<SchArrangeResult> schArrangeResults = resultMap1.get(schRotationGroup.getGroupFlow() + dept.getStandardDeptId());
                    if (CollectionUtils.isEmpty(schArrangeResults)) {
                        continue;
                    }
                    if (CollectionUtils.isNotEmpty(schArrangeResults)) {
                        for (SchArrangeResult schArrangeResult : schArrangeResults) {
                            num++;
                            Map<String, Float> realMonthMap = (Map<String, Float>) resutMap.get("realMonthMap");
                            standardDeptName = dept.getStandardDeptName();
                            schMonth1 = dept.getSchMonth();
                            schMonth2 = "0";
                            if (null != realMonthMap && null != realMonthMap.get(schRotationGroup.getGroupFlow() + dept.getStandardDeptId())) {
                                schMonth1 = dept.getSchMonth();
                                schMonth2 = realMonthMap.get(schRotationGroup.getGroupFlow() + dept.getStandardDeptId()) + "";
                            }
                            schDeptName = schArrangeResult.getSchDeptName();
                            schDate1 = schArrangeResult.getSchStartDate();
                            schDate2 = schArrangeResult.getSchEndDate();
                            List<Map<String, Object>> imagelist = (List<Map<String, Object>>) afterImgMap.get(dept.getRecordFlow());
                            if (CollectionUtils.isNotEmpty(imagelist)) {
                                for (Map<String, Object> stringObjectMap : imagelist) {
                                    imageUrl = (String) stringObjectMap.get("imageUrl");
                                }
                            }
                            if (null != d) {
                                if (StringUtil.isEmpty(d.getCompleteBi())) {
                                    completeBi = "0";
                                }
                                if (StringUtil.isNotEmpty(d.getCompleteBi())) {
                                    completeBi = d.getCompleteBi();
                                    if (!"-".equals(d.getCompleteBi())) {
                                        completeBi = completeBi + "%";
                                    }
                                }
                                if (StringUtil.isEmpty(d.getOutCompleteBi())) {
                                    outCompleteBi = "0";
                                }
                                if (StringUtil.isNotEmpty(d.getOutCompleteBi())) {
                                    outCompleteBi = d.getOutCompleteBi();
                                    if (!"-".equals(d.getOutCompleteBi())) {
                                        outCompleteBi = outCompleteBi + "%";
                                    }
                                }
                                if (StringUtil.isEmpty(d.getAuditBi())) {
                                    auditBi = "0";
                                }
                                if (StringUtil.isNotEmpty(d.getAuditBi())) {
                                    auditBi = d.getAuditBi();
                                    if (!"-".equals(d.getAuditBi())) {
                                        auditBi = auditBi + "%";
                                    }
                                }
                            }

                            AssDataForm assDataForm = new AssDataForm();
                            assDataForm.setRotationType(rotationType);
                            assDataForm.setStandardDeptName(standardDeptName);
                            assDataForm.setSchMonth1(schMonth1);
                            assDataForm.setSchMonth2(schMonth2);
                            assDataForm.setSchDeptName(schDeptName);
                            assDataForm.setSchDate1(schDate1);
                            assDataForm.setSchDate2(schDate2);
                            assDataForm.setImageUrl(imageUrl);
                            assDataForm.setCompleteBi(completeBi);
                            assDataForm.setOutCompleteBi(outCompleteBi);
                            assDataForm.setAuditBi(auditBi);
                            dataList.add(assDataForm);
                        }

                    } else {
                        standardDeptName = dept.getStandardDeptName();
                        schMonth1 = dept.getSchMonth();
                        schMonth2 = "0";
                        List<Map<String, Object>> imagelist = (List<Map<String, Object>>) afterImgMap.get(dept.getRecordFlow());
                        if (CollectionUtils.isNotEmpty(imagelist)) {
                            for (Map<String, Object> stringObjectMap : imagelist) {
                                imageUrl = (String) stringObjectMap.get("imageUrl");
                            }
                        }
                        if (null != d) {
                            if (StringUtil.isEmpty(d.getCompleteBi())) {
                                completeBi = "0";
                            }
                            if (StringUtil.isNotEmpty(d.getCompleteBi())) {
                                completeBi = d.getCompleteBi();
                                if (!"-".equals(d.getCompleteBi())) {
                                    completeBi = completeBi + "%";
                                }
                            }
                            if (StringUtil.isEmpty(d.getOutCompleteBi())) {
                                outCompleteBi = "0";
                            }
                            if (StringUtil.isNotEmpty(d.getOutCompleteBi())) {
                                outCompleteBi = d.getOutCompleteBi();
                                if (!"-".equals(d.getOutCompleteBi())) {
                                    outCompleteBi = outCompleteBi + "%";
                                }
                            }
                            if (StringUtil.isEmpty(d.getAuditBi())) {
                                auditBi = "0";
                            }
                            if (StringUtil.isNotEmpty(d.getAuditBi())) {
                                auditBi = d.getAuditBi();
                                if (!"-".equals(d.getAuditBi())) {
                                    auditBi = auditBi + "%";
                                }
                            }
                        }

                        AssDataForm assDataForm = new AssDataForm();
                        assDataForm.setRotationType(rotationType);
                        assDataForm.setStandardDeptName(standardDeptName);
                        assDataForm.setSchMonth1(schMonth1);
                        assDataForm.setSchMonth2(schMonth2);
                        assDataForm.setSchDeptName(schDeptName);
                        assDataForm.setSchDate1(schDate1);
                        assDataForm.setSchDate2(schDate2);
                        assDataForm.setImageUrl(imageUrl);
                        assDataForm.setCompleteBi(completeBi);
                        assDataForm.setOutCompleteBi(outCompleteBi);
                        assDataForm.setAuditBi(auditBi);
                        dataList.add(assDataForm);
                    }
                }
            }

        }
        Map<String, List<AssDataForm>> dataMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(dataList)) {
            List<String> rotationTypeName = dataList.stream().map(AssDataForm::getRotationType).distinct().collect(Collectors.toList());
            for (String s : rotationTypeName) {
                List<AssDataForm> assDataForms = new ArrayList<>();
                for (AssDataForm assDataForm : dataList) {
                    if (s.equals(assDataForm.getRotationType())) {
                        assDataForms.add(assDataForm);
                    }
                }
                dataMap.put(s, assDataForms);
            }

        }
        resutMap.put("dataMap", dataMap);
        return resutMap;
    }


    //学员评带教
    @RequestMapping(value = {"/gradeDeptListTwo"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object gradeDeptListTwo(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "校验成功！");
        if (StringUtil.isEmpty(userFlow)) {
            resultMap.put("resultId", "31601");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        if (null == doctor) {
            resultMap.put("resultId", "30401");
            resultMap.put("resultType", "医师信息不能为空");
            return resultMap;
        }
        List<SchArrangeResult> resultList = jswjwBiz.getSchArrangeResult(userFlow, doctor.getOrgFlow(), pageIndex, pageSize, doctor.getRotationFlow());
//        resultMap.put("resultList", resultList);
        resultMap.put("dataCount", PageHelper.total);

        List<DeptTeacherGradeInfo> gradeList = jswjwBiz.searchAllGradeTwo(userFlow);
        Map<String, String> gradeMap = jswjwBiz.getNewGradeMap(gradeList);
//        resultMap.put("gradeMap", gradeMap);

        Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
        Map<String, SchRotationDept> deptMap = new HashMap<String, SchRotationDept>();
        for (SchArrangeResult result : resultList) {
            ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
            processMap.put(result.getResultFlow(), process);
            SchRotationDept dept = jswjwBiz.searchGroupFlowAndStandardDeptIdQuery(result.getStandardGroupFlow(), result.getStandardDeptId());
            deptMap.put(result.getResultFlow(), dept);
        }

        List<Map<String, Object>> resultMapList = new ArrayList<>();
        if (null != resultList && resultList.size() > 0) {
            for (SchArrangeResult result : resultList) {
                Map<String, Object> map = new HashMap<>();
                map.put("subDeptFlow", result.getResultFlow());
                SchRotationDept rotationDept = deptMap.get(result.getResultFlow());
                map.put("deptFlow", rotationDept.getRecordFlow());
                map.put("subDeptName", result.getSchDeptName());
                map.put("startDate", result.getSchStartDate());
                map.put("endDate", result.getSchEndDate());
                map.put("sysDeptFlow", result.getDeptFlow());
                map.put("resultFlow", result.getResultFlow());
                ResDoctorSchProcess process = processMap.get(result.getResultFlow());
                map.put("teacherFlow", process.getTeacherUserFlow());
                map.put("teacherName", process.getTeacherUserName());
                map.put("headFlow", process.getHeadUserFlow());
                map.put("headName", process.getHeadUserName());
                String teacherKey = process.getProcessFlow() + "_TeacherAssess";
                String teacherKey1 = process.getProcessFlow() + "_TeacherAssessTwo";
                String teacherKey3 = process.getProcessFlow();
                String teacherKey4 = process.getProcessFlow() + "TypeId";
                map.put("teacherScore", StringUtil.isBlank(gradeMap.get(teacherKey)) ? "未评价" : gradeMap.get(teacherKey));
                if (null != gradeMap.get(teacherKey) && null == gradeMap.get(teacherKey1)) {
                    map.put("teacherScore", gradeMap.get(teacherKey));
                    map.put("status", "已评价");
                }
                if (null != gradeMap.get(teacherKey1) && null == gradeMap.get(teacherKey)) {
                    map.put("teacherScore", gradeMap.get(teacherKey1));
                    map.put("status", "已评价");
                }
                if (null == gradeMap.get(teacherKey1) && null == gradeMap.get(teacherKey)) {
                    map.put("teacherScore", "");
                    map.put("status", "未评价");
                }
                map.put("recFlow", StringUtil.isBlank(gradeMap.get(teacherKey3)) ? "" : gradeMap.get(teacherKey3));
                map.put("typeId", StringUtil.isBlank(gradeMap.get(teacherKey4)) ? "" : gradeMap.get(teacherKey4));
                resultMapList.add(map);
            }
        }
        resultMap.put("resultMapList", resultMapList);
        return resultMap;
    }

    //评学员
    @RequestMapping(value = "/evaluation", method = {RequestMethod.POST})
    @ResponseBody
    public Object evaluation(String userFlow, String roleFlag, ResDoctor doctor, String assessStatusId,
                             String recTypeId, Integer pageIndex, Integer pageSize, String[] datas) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "校验成功！");
        if (StringUtil.isEmpty(userFlow)) {
            resultMap.put("resultId", "31601");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        if (StringUtil.isEmpty(roleFlag)) {
            resultMap.put("resultId", "31601");
            resultMap.put("resultType", "roleFlag为空");
            return resultMap;
        }
        String dataStr = "";
        List<String> docTypeList = new ArrayList<>();
        if (datas != null && datas.length > 0) {
            docTypeList = Arrays.asList(datas);
            for (String d : datas) {
                dataStr += d + ",";
            }
        }
        resultMap.put("dataStr", dataStr);
        resultMap.put("roleFlag", roleFlag);
        SysUser user = jswjwBiz.readSysUser(userFlow);
        resultMap.put("datas", datas);
        if (StringUtil.isNotBlank(recTypeId)) {
            resultMap.put("recTypeId", recTypeId);
        }
        //权限期间是否开通
        String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
        List<ResDoctorSchProcess> processList = null;
        Map<String, Object> param = new HashMap<>();
        param.put("assessStatusId", assessStatusId);
        param.put("roleFlog", roleFlag);
        if ("Teacher".equals(roleFlag)) {
            param.put("teacherUserFlow", userFlow);
        }
        param.put("isOpen", isOpen);
        param.put("doctor", doctor);
        //护士多科室
        List<SysUserDept> userDeptList = jswjwBiz.getUserDept(user);
        if(CollectionUtils.isNotEmpty(userDeptList)){
            param.put("deptFlows",userDeptList.stream().map(SysUserDept::getDeptFlow).collect(Collectors.toList()));
        }else{
            param.put("deptFlow", user.getDeptFlow());
        }
//        param.put("deptFlow", user.getDeptFlow());
        param.put("orgFlow", user.getOrgFlow());
        param.put("docTypeList", docTypeList);
        param.put("userFlow", userFlow);
        PageHelper.startPage(pageIndex, pageSize);
        processList = jswjwBiz.selectProcessByDoctorNew(param);
        resultMap.put("dataCount", PageHelper.total);
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        if (processList != null && processList.size() > 0) {
            Map<String, SchArrangeResult> resultMapMap = new HashMap<String, SchArrangeResult>();
            Map<String, PubFile> fileMap = new HashMap<String, PubFile>();
            for (ResDoctorSchProcess rdsp : processList) {
                Map<String, Object> map = new HashMap<>();
                SysUser user1 = jswjwBiz.readSysUser(rdsp.getUserFlow());
                ResDoctor resDoctor = jswjwBiz.readDoctor(rdsp.getUserFlow());

                String resultFlow = rdsp.getSchResultFlow();
                SchArrangeResult result = jswjwBiz.readSchArrangeResult(resultFlow);
                List<String> recTypeIds = new ArrayList<String>();
                if ("Teacher".equals(roleFlag)) {
                    recTypeIds.add("TeacherDoctorAssess");
                    recTypeIds.add("TeacherDoctorAssessTwo");
                }
                if ("Nurse".equals(roleFlag)) {
                    recTypeIds.add("NurseDoctorGrade");
                }
                Map<String, Object> paramMap = new HashMap();
                paramMap.put("recTypeIds", recTypeIds);
                paramMap.put("processFlow", rdsp.getProcessFlow());
                paramMap.put("currentUserFlow", user.getUserFlow());
                List<DeptTeacherGradeInfo> gradeInfoList = gradeBiz.searchResGradeByItems(paramMap);
                if (gradeInfoList != null && gradeInfoList.size() > 0) {
                    for (DeptTeacherGradeInfo tempGradeInfo : gradeInfoList) {
                        Map<String, Object> gradeMap = jswjwBiz.parseGradeXmlTwo(tempGradeInfo.getRecContent());
                        map.put("userName", user1.getUserName());
                        map.put("sessionNumber", resDoctor.getSessionNumber());
                        map.put("trainingSpeName", resDoctor.getTrainingSpeName());
                        map.put("doctorTypeName", resDoctor.getDoctorTypeName());
                        map.put("schStartDate", rdsp.getSchStartDate());
                        map.put("schEndDate", rdsp.getSchEndDate());
                        map.put("schDeptName", rdsp.getSchDeptName());
                        if (null != tempGradeInfo) {
                            map.put("status", "已评价");
                            map.put("totalScore", gradeMap.get("totalScore"));
                            map.put("recTypeId", tempGradeInfo.getRecTypeId());
                            map.put("schResultFlow", rdsp.getSchResultFlow());
                            map.put("rotationFlow", result.getRotationFlow());
                            map.put("doctorFlow", resDoctor.getDoctorFlow());
                            map.put("recFlow", tempGradeInfo.getRecFlow());
                            map.put("processFlow", rdsp.getProcessFlow());
                            map.put("schDeptFlow", rdsp.getSchDeptFlow());
                            map.put("typeId", tempGradeInfo.getRecTypeId());
                            map.put("deptFlow", rdsp.getDeptFlow());
                        } else {
                            map.put("status", "待评价");
                            map.put("totalScore", "");
                            map.put("recTypeId", tempGradeInfo.getRecTypeId());
                            map.put("schResultFlow", rdsp.getSchResultFlow());
                            map.put("rotationFlow", result.getRotationFlow());
                            map.put("doctorFlow", doctor.getDoctorFlow());
                            map.put("recFlow", tempGradeInfo.getRecFlow());
                            map.put("processFlow", rdsp.getProcessFlow());
                            map.put("schDeptFlow", rdsp.getSchDeptFlow());
                            map.put("deptFlow", rdsp.getDeptFlow());
                            map.put("typeId", "");
                        }
                    }
                } else {
                    map.put("userName", user1.getUserName());
                    map.put("sessionNumber", resDoctor.getSessionNumber());
                    map.put("trainingSpeName", resDoctor.getTrainingSpeName());
                    map.put("doctorTypeName", resDoctor.getDoctorTypeName());
                    map.put("schStartDate", rdsp.getSchStartDate());
                    map.put("schEndDate", rdsp.getSchEndDate());
                    map.put("status", "待评价");
                    map.put("totalScore", "");
                    map.put("recTypeId", "");
                    map.put("schResultFlow", rdsp.getSchResultFlow());
                    map.put("rotationFlow", result.getRotationFlow());
                    map.put("doctorFlow", doctor.getDoctorFlow());
                    map.put("recFlow", "");
                    map.put("processFlow", rdsp.getProcessFlow());
                    map.put("schDeptFlow", rdsp.getSchDeptFlow());
                    map.put("schDeptName", rdsp.getSchDeptName());
                    map.put("deptFlow", rdsp.getDeptFlow());
                    map.put("typeId", "");
                }
                resultMapList.add(map);

            }

        }


        resultMap.put("resultMapList", resultMapList);
        return resultMap;
    }

    @RequestMapping(value = {"/grade"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object grade(String recFlow, String assessType,String processFlow,String resultFlow, String deptFlow, HttpServletRequest request, HttpServletResponse response) throws
            DocumentException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "校验成功！");
        if (StringUtil.isEmpty(assessType)) {
            resultMap.put("resultId", "31801");
            resultMap.put("resultType", "评价类型不能为空!");
            return resultMap;
        }
        DeptTeacherGradeInfo rec = gradeBiz.getRec(recFlow);
        ResAssessCfg assessCfg = null;
        if (rec == null) {
            List<ResAssessCfg> resAssessCfgList = gradeBiz.getAssCfg(assessType);
            if (resAssessCfgList != null && resAssessCfgList.size() > 0) {
                assessCfg = resAssessCfgList.get(0);
            } else {
                resultMap.put("resultId", "31801");
                resultMap.put("resultType", "未查到配置评分表,请联系管理员");
                return resultMap;
            }
        }
        List<Map<String, Object>> assessMaps = new ArrayList<>();
        if (assessCfg != null) {
            String content = assessCfg.getCfgBigValue();
            //解析评分xml
            assessMaps = jswjwBiz.parseAssessCfg(content);
//            resultMap.put("assessMaps", assessMaps);
        }
        Map<String, Object> gradeMap = new HashMap<>();
        if (rec != null) {
            gradeMap = jswjwBiz.parseGradeXmlNew(rec.getRecContent());
            assessCfg = jswjwBiz.readResAssessCfg(rec.getCfgFlow());
            getForm(resultMap, assessCfg);
        }
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        resultMap.put("processFlow",processFlow);
        resultMap.put("deptFlow",deptFlow);
        resultMap.put("resultFlow",resultFlow);
        resultMap.put("recFlow",recFlow);
        if(null == rec){
            for (Map<String, Object> assessMap : assessMaps) {
                List<Map<String,Object>> items = (List<Map<String, Object>>) assessMap.get("items");
                if(CollectionUtils.isNotEmpty(items)){
                    for (Map<String, Object> item : items) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("inputId",item.get("id"));
                        map.put("type",item.get("type"));
                        map.put("name",item.get("name"));
                        map.put("score",0);
                        resultMapList.add(map);
                    }
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("inputId","lostReason");
            map.put("score",StringUtil.isBlank((String) gradeMap.get("lostReason")) ? "" : gradeMap.get("lostReason"));
            map.put("name","主观评价");
            resultMapList.add(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("inputId","totalScore");
            map1.put("score",StringUtil.isBlank((String) gradeMap.get("totalScore")) ? "0" : gradeMap.get("totalScore"));
            map1.put("name","总分");
            resultMapList.add(map1);

        }else{
            List<ResAssessCfgTitleForm> titleFormList = (List<ResAssessCfgTitleForm>) resultMap.get("titleFormList");
            if(CollectionUtils.isNotEmpty(titleFormList)){
                for (ResAssessCfgTitleForm resAssessCfgTitleForm : titleFormList) {
                    List<ResAssessCfgItemForm> itemList = resAssessCfgTitleForm.getItemList();
                    if(CollectionUtils.isNotEmpty(itemList)){
                        for (ResAssessCfgItemForm resAssessCfgItemForm : itemList) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("inputId",resAssessCfgItemForm.getId());
                            map.put("type",resAssessCfgItemForm.getType());
                            map.put("name",resAssessCfgItemForm.getName());
                            Map<String,String> o = (Map<String, String>) gradeMap.get(resAssessCfgItemForm.getId());
                            map.put("score",Integer.parseInt(o.get("score")));
                            resultMapList.add(map);
                        }
                    }
                }
                Map<String, Object> map = new HashMap<>();
                map.put("inputId","lostReason");
                map.put("score",StringUtil.isBlank((String) gradeMap.get("lostReason")) ? "" : gradeMap.get("lostReason"));
                map.put("name","主观评价");
                resultMapList.add(map);
                Map<String, Object> map1 = new HashMap<>();
                map1.put("inputId","totalScore");
                map1.put("score",StringUtil.isBlank((String) gradeMap.get("totalScore")) ? "0" : gradeMap.get("totalScore"));
                map1.put("name","总分");
                resultMapList.add(map1);
            }
        }
        resultMap.put("resultMap",resultMapList);
        return resultMap;
    }


    private void getForm(Map<String, Object> resultMap, ResAssessCfg assessCfg) throws DocumentException {
        resultMap.put("assessCfg", assessCfg);
        Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
        String titleXpath = "//title";
        List<Element> titleElementList = dom.selectNodes(titleXpath);
        if (titleElementList != null && !titleElementList.isEmpty()) {
            List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
            for (Element te : titleElementList) {
                ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
                titleForm.setType(te.attributeValue("type"));
                titleForm.setRow(te.attributeValue("row"));
                titleForm.setId(te.attributeValue("id"));
                titleForm.setName(te.attributeValue("name"));
                List<Element> itemElementList = te.elements("item");
                List<ResAssessCfgItemForm> itemFormList = null;
                if (itemElementList != null && !itemElementList.isEmpty()) {
                    itemFormList = new ArrayList<ResAssessCfgItemForm>();
                    for (Element ie : itemElementList) {
                        ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
                        itemForm.setId(ie.attributeValue("id"));
                        itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                        itemForm.setType(ie.element("type") == null ? "" : ie.element("type").getTextTrim());
                        itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                        itemForm.setRow(ie.element("row") == null ? "" : ie.element("row").getTextTrim());
                        itemFormList.add(itemForm);
                    }
                }
                titleForm.setItemList(itemFormList);
                titleFormList.add(titleForm);
            }
            if (CollectionUtils.isNotEmpty(titleFormList)) {
                for (ResAssessCfgTitleForm resAssessCfgTitleForm : titleFormList) {
                    List<ResAssessCfgItemForm> itemList = resAssessCfgTitleForm.getItemList();
                    if (CollectionUtils.isNotEmpty(itemList)) {
                        resultMap.put("itemList", itemList);
                    }
                }
            }
            resultMap.put("titleFormList", titleFormList);

        }
    }

    @RequestMapping(value = {"/saveGradeTwo"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveGradeTwo(String userFlow, String recFlow, String deptFlow, String resultFlow, String
            assessType, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "校验成功！");
        if (StringUtil.isEmpty(userFlow)) {
            resultMap.put("resultId", "31601");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        if (StringUtil.isEmpty(deptFlow)) {
            resultMap.put("resultId", "31801");
            resultMap.put("resultType", "标准科室不能为空");
            return resultMap;
        }

        if (StringUtil.isEmpty(assessType)) {
            resultMap.put("resultId", "31801");
            resultMap.put("resultType", "评价类型不能为空!");
            return resultMap;
        }

        DeptTeacherGradeInfo rec = gradeBiz.getRec(recFlow);
        ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(resultFlow);
        if (process == null) {
            resultMap.put("resultId", "31801");
            resultMap.put("resultType", "轮转科室未关联，请重新选择轮转科室.");
            return resultMap;
        }
        ResAssessCfg assessCfg;
        if (rec != null) {
            assessCfg = jswjwBiz.readCfgAssess(rec.getCfgFlow());
        } else {
            List<ResAssessCfg> resAssessCfgList = gradeBiz.getAssCfg(assessType);
            if (resAssessCfgList != null && resAssessCfgList.size() > 0) {
                assessCfg = resAssessCfgList.get(0);
            } else {
                resultMap.put("resultId", "31801");
                resultMap.put                                                                                                                     ("resultType", "未查到配置评分表,请联系管理员");
                return "res/jswjw/saveGrade";
            }
        }

        String result = gradeBiz.saveGradeTwo(assessCfg, assessCfg.getCfgFlow(), userFlow, deptFlow, resultFlow, assessType, request);
        if (result != null && result.startsWith("error:")) {
            resultMap.put("resultId", "31801");
            resultMap.put("resultType", result.split(":")[1]);
            return resultMap;
        }
        return resultMap;
    }

//    @RequestMapping(value = {"/planUserMsgList"}, method = {RequestMethod.POST})
//    @ResponseBody
//    public Object planUserMsgList(String userFlow,String planFlow,String certificateNo,String startTime, Integer pageIndex, Integer pageSize,String endTime,
//                                  Integer currentPage,HttpServletRequest request, HttpServletResponse response) {
//        Map<String, Object> resultMap = new HashMap<>();
//        if (StringUtil.isEmpty(userFlow)) {
//            resultMap.put("resultId", "31601");
//            resultMap.put("resultType", "userFlow为空");
//            return resultMap;
//        }
//        SysUser user = jswjwBiz.readSysUser(userFlow);
//        Map<String,Object> paramMap = new HashMap<>();
//        paramMap.put("planFlow",planFlow);
//        paramMap.put("certificateNo",certificateNo);
//        paramMap.put("gainCertificateId",com.pinde.core.common.GlobalConstant.FLAG_Y);
//        paramMap.put("sendCertificateId",com.pinde.core.common.GlobalConstant.FLAG_Y);
//        paramMap.put("startTime",startTime);
//        paramMap.put("endTime",endTime);
//        List<SysDict> dictList = dictBiz.searchDictListByDictTypeId("CertificateTermValidity");
//        if (null !=dictList && dictList.size()>0){
//            resultMap.put("dayNum", dictList.get(0).getDictId());
//            paramMap.put("dayNum",dictList.get(0).getDictId());
//        }
//        paramMap.put("userFlow",userFlow);
//        List<Map<String, Object>> doctorList = jswjwBiz.phyAssDoctorList(paramMap);
//        resultMap.put("doctorList", doctorList);
//        return resultMap;
//    }


    @RequestMapping(value = {"/temporaryOutFamily"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object temporaryOutFamily(String processFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isEmpty(processFlow)) {
            resultMap.put("resultId", "31702");
            resultMap.put("resultType", "请检查是否已维护好科主任带教");
            return resultMap;
        }
        ResDoctorSchProcess resDoctorSchProcess = jswjwBiz.readSchProcess(processFlow);
        if(StringUtil.isEmpty(resDoctorSchProcess.getHeadUserFlow()) || StringUtil.isEmpty(resDoctorSchProcess.getTeacherUserFlow())){
            resultMap.put("resultId", "31702");
            resultMap.put("resultType", "请检查是否已维护好科主任带教");
            return resultMap;
        }
        int i = jswjwBiz.temporaryOutFamily(processFlow);
        if(i>0){
            resultMap.put("resultId", "200");
            resultMap.put("resultType", "提交成功！");
        }else{
            resultMap.put("resultId", "200");
            resultMap.put("resultType", "提交失败！");
        }
        return resultMap;
    }

    @RequestMapping(value = {"/messagePush"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object messagePush(String userFlow) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isEmpty(userFlow)) {
            resultMap.put("resultId", "201");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        JsresPowerCfg cfg = jswjwBiz.readPowerCfg("jsres_"+user.getOrgFlow()+"_message_push");
        if (null == cfg || StringUtil.isBlank(cfg.getCfgValue()) || !cfg.getCfgValue().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
            resultMap.put("resultId", "202");
            resultMap.put("resultType", "基地未开启提醒功能！");
            return resultMap;
        }
        JsresPowerCfg cfgTime = jswjwBiz.readPowerCfg("jsres_"+user.getOrgFlow()+"_message_push_time");
        if (null==cfgTime || StringUtil.isBlank(cfgTime.getCfgValue())){
            resultMap.put("resultId", "203");
            resultMap.put("resultType", "基地未设置提醒功能限制时间！");
            return resultMap;
        }
        String appointDateRec = DateUtil.getAppointDate2(new Date(), Integer.parseInt("-" + cfgTime.getCfgValue()));
        String appointDateSch = DateUtil.getAppointDate(DateUtil.getCurrDate(), Integer.parseInt("-" + cfgTime.getCfgValue()));

        Map<String, Object> param =new HashMap<>();
        param.put("userFlow",userFlow);
        param.put("appointDateSch",appointDateSch);
        param.put("appointDateRec",appointDateRec);
        //查询该科秘所在科室下所有未出科并且在指定时间内未填写数据人员名单
        List<String> list= jswjwBiz.findDoctorNameByRecTime(param);
        if (null!=list && !list.isEmpty()){
            resultMap.put("resultId", "200");
            resultMap.put("resultType", list);
            resultMap.put("messagePushTime", cfgTime.getCfgValue());
        }else {
            resultMap.put("resultId", "204");
            resultMap.put("resultType", "无人员名单");
        }
        return resultMap;
    }

    /**
     *
     * @param userFlow 用户主键
     * @param roleFlag  角色标识
     * @param Month 年月
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = {"/getActivityListByRole"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object getActivityListByRole(String userFlow,String roleFlag,String Month) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isEmpty(userFlow)) {
            resultMap.put("resultId", "201");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        if (StringUtil.isEmpty(roleFlag)) {
            resultMap.put("resultId", "201");
            resultMap.put("resultType", "roleFlag为空");
            return resultMap;
        }
        if (StringUtil.isEmpty(Month)) {
           Month=DateUtil.getMonth();
        }
        Map<String, Object> param =new HashMap<>();
        param.put("userFlow",userFlow);
        param.put("roleFlag",roleFlag);
        param.put("Month",Month);
        if (roleFlag.equals("local")){
            SysUser user = jswjwBiz.readSysUser(userFlow);
            param.put("orgFlow",user.getOrgFlow());
        }
        List<Map<String, Object>> list = activityBiz.getActivityListByRole(param);
        resultMap.put("resultId", "200");
        resultMap.put("resultType", list);
        return resultMap;
    }

    /**
     *
     * @param userFlow 用户主键
     * @param roleFlag  角色标识
     * @param date 年月日
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = {"/getActivityListByRole2"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object getActivityListByRole2(String userFlow,String roleFlag,String date) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isEmpty(userFlow)) {
            resultMap.put("resultId", "201");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        if (StringUtil.isEmpty(roleFlag)) {
            resultMap.put("resultId", "201");
            resultMap.put("resultType", "roleFlag为空");
            return resultMap;
        }
        if (StringUtil.isEmpty(date)) {
            date=DateUtil.getCurrDate();
        }
        Map<String, Object> param =new HashMap<>();
        param.put("userFlow",userFlow);
        param.put("roleFlag",roleFlag);
        param.put("date",date);
        if (roleFlag.equals("local")){
            SysUser user = jswjwBiz.readSysUser(userFlow);
            param.put("orgFlow",user.getOrgFlow());
        }
        List<Map<String, Object>> list = activityBiz.getActivityListByRole(param);
//        if (null!=list && list.size()>0){
//            for (TeachingActivityInfo info : list) {
//                info.setRecordStatus(jswjwBiz.readSysUser(info.getSpeakerFlow()).getUserName());
//            }
//        }
        resultMap.put("resultId", "200");
        resultMap.put("resultType", list);
        return resultMap;
    }

    //账号密码登录并绑定账号
    @RequestMapping(value = {"/webLogin"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object webLogin(String userCode, String userPasswd, String openId, String uuid, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");

        if (StringUtil.isEmpty(userCode)) {
            return ResultDataThrow("用户名不能为空");
        }
        if (StringUtil.isEmpty(userPasswd)) {
            return ResultDataThrow("密码为空");
        }
        if (StringUtil.isEmpty(openId)) {
            return ResultDataThrow("openId为空");
        }
        SysUser userinfo = jswjwBiz.findByUserCode(userCode);
        if (userinfo == null) {
            userinfo = jswjwBiz.findByUserPhone(userCode);
        }
        if (userinfo == null) {
            return ResultDataThrow("用户不存在");
        }
        //有历史绑定，清除历史绑定
        SysUser oldUser = jswjwBiz.readSysUserByOpenId(openId);
        if (oldUser != null) {
            oldUser.setOpenId("");
            jswjwBiz.updateUser(oldUser);
        }
        //更新新绑定
        userinfo.setOpenId(openId);
        jswjwBiz.updateUser(userinfo);


        if(userinfo.getUserPhone()==null||"".equals(userinfo.getUserPhone())) {
            resultMap.put("resultId", "1002");
            resultMap.put("resultType", "未绑定手机号,请绑定手机号");
            return resultMap;
        }

        try {
            userPasswd = DESUtil.decryptWx(userPasswd);
        }catch (Exception e){
            return ResultDataThrow("用户密码错误");
        }

//        ServletContext application = request.getServletContext();
//        if (application.getAttribute("onlineCountNum") == null) {
//            application.setAttribute("onlineCountNum", 1);
//        } else {
//            application.setAttribute("onlineCountNum", (Integer) application.getAttribute("onlineCountNum") + 1);
//        }
        return getUserInfo(userinfo, resultMap, userPasswd, uuid, null,request);
    }

    //测试接口

//    private static String WECHAT_TOKEN = "WADJFSASF"; //WECHAT_TOKEN和你申请时填写的Token一样
//
//    @RequestMapping(value = {"/verityToken"}, method = {RequestMethod.GET})
//    @ResponseBody
//    public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        logger.info("请求进来了...");
//
//        Enumeration pNames = request.getParameterNames();
//        while (pNames.hasMoreElements()) {
//            String name = (String) pNames.nextElement();
//            String value = request.getParameter(name);
//            String log = "name =" + name + "     value =" + value;
//            logger.info(log);
//        }
//
//        String signature = request.getParameter("signature");/// 微信加密签名
//        String timestamp = request.getParameter("timestamp");/// 时间戳
//        String nonce = request.getParameter("nonce"); /// 随机数
//        String echostr = request.getParameter("echostr"); // 随机字符串
//        PrintWriter out = response.getWriter();
//        out.print(echostr);
//        out.close();
//    }

}

