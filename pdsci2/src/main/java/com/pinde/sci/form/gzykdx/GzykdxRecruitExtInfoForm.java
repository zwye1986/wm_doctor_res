package com.pinde.sci.form.gzykdx;

import com.pinde.sci.model.mo.PubUserResume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author littlesheep
 * @Copyright njpdxx.com
 * <p/>
 * 广州医科大学招录大字段信息
 * @since 2017/3/9
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class GzykdxRecruitExtInfoForm implements Serializable {

    /**
     * 考试方式码
     */
    private String ksfsm;
    /**
     * 业务课一名称
     */
    private String ywk1mc;
    /**
     * 业务课二名称
     */
    private String ywk2mc;
    /**
     * 政治理论成绩
     */
    private String zzll;
    /**
     * 外国语成绩
     */
    private String wgy;
    /**
     * 业务课一成绩
     */
    private String ywk1;
    /**
     * 业务课二成绩
     */
    private String ywk2;
    /**
     * 总分
     */
    private String zf;
    /**
     * 初试成绩
     */
    private String cscj;
    /**
     * 报考类别
     */
    private String bklb;
    /**

     * 业务课一码
     */
    private String ywk1m;
    /**
     * 业务课二码
     */
    private String ywk2m;
    /**
     * 姓名拼音
     */
    private String xmpy;
    /**
     * 证件类型
     */
    private String zjlx;
    /**
     * 证件号码
     */
    private String zjhm;
    /**
     * 出生年月
     */
    private String csny;
    /**
     * 民族码
     */
    private String mzm;
    /**
     * 性别码
     */
    private String xbm;
    /**
     * 婚姻状况码
     */
    private String hfm;
    /**
     * 现役军人码
     */
    private String xyjrm;
    /**
     * 政治面貌码
     */
    private String zzmmm;
    /**
     * 户口所在地码
     */
    private String hkszdm;
    /**
     * 户口所在地详细地址
     */
    private String hkszdxxdz;
    /**
     * 考生档案所在单位码
     */
    private String daszdm;
    /**
     * 考生档案所在单位
     */
    private String daszdw;
    /**
     * 考生档案所在单位地址
     */
    private String daszdwdz;
    /**
     * 考生档案所在单位邮政编码
     */
    private String daszdwyzbm;
    /**
     * 考生来源码
     */
    private String kslym;
    /**
     * 毕业学校代码
     */
    private String bydwm;
    /**
     * 毕业学校名称
     */
    private String bydw;
    /**
     * 毕业专业代码
     */
    private String byzydm;
    /**
     * 毕业专业名称
     */
    private String byzymc;
    /**
     * 取得最后学历的学习形式
     */
    private String xxxs;
    /**
     * 最后学历码
     */
    private String xlm;
    /**
     * 毕业证书编号
     */
    private String xlzsbh;
    /**
     * 获得最后学历毕业年月
     */
    private String byny;
    /**
     * 注册学号
     */
    private String zcxh;
    /**
     * 最后学位码
     */
    private String xwm;
    /**
     * 学位证书编号
     */
    private String xwzsbh;
    /**
     * 专项计划
     */
    private String zxjh;
    /**
     * 定向就业单位所在地码
     */
    private String dxwpdwszdm;
    /**
     * 定向就业单位
     */
    private String dxwpdw;
    /**
     * 政治理论码
     */
    private String zzllm;
    /**
     * 政治理论名称
     */
    private String zzllmc;
    /**
     * 外国语码
     */
    private String wgym;
    /**
     * 外国语名称
     */
    private String wgymc;
    /**
     * 入伍批准书编号
     */
    private String rwpzsbh;
    /**
     * 退出现役证编号
     */
    private String tcxyzbh;
    /**
     * 录取单位代码
     */
    private String lqdwdm;
    /**
     * 录取单位名称
     */
    private String lqdwmc;
    /**
     * 录取专业代码
     */
    private String lqzydm;
    /**
     * 录取专业名称
     */
    private String lqzymc;
    /**
     * 录取类别码
     */
    private String lqlbm;
    /**
     * 录取院系所码
     */
    private String lqxysm;
    /**
     * 复试总成绩
     */
    private String fscj;
    /**
     * 总成绩折算办法
     */
    private String zcjzsbf;
    /**
     * 总成绩
     */
    private String zcj;
    /**
     * 加试科目1名称
     */
    private String js1mc;
    /**
     * 加试科目2名称
     */
    private String js2mc;
    /**
     * 加试科目1成绩
     */
    private String js1cj;
    /**
     * 加试科目2成绩
     */
    private String js2cj;

    public String getJs1cj() {
        return js1cj;
    }

    public void setJs1cj(String js1cj) {
        this.js1cj = js1cj;
    }

    public String getJs2cj() {
        return js2cj;
    }

    public void setJs2cj(String js2cj) {
        this.js2cj = js2cj;
    }

    /**

     * 保留入学资格年限
     */
    private String blzgnx;
    /**
     * 是否破格
     */
    private String qg;
    /**
     * 享受照顾政策
     */
    private String xszgzc;
    /**
     * 招生单位所在省市码
     */
    private String szssm;
    /**
     * 备注
     */
    private String bz;
    /**
     * 联合培养单位代码
     */
    private String lhpydwm;
    /**
     * 联合培养单位名称
     */
    private String lhpydw;
    /**
     * 学制
     */
    private String xz;

    private String bkdwdm;

    private String bklbm;

    private String bkyxsm;

    private String jgszdm;

    private String csdm;

    private String bmddm;

    private String bmh;

    private String xxgzdw;

    private String xxgzjl;

    private String jlcf;

    private String kszbqk;

    private String jtcy;

    private String txdz;

    private String yzbm;

    private String lxdh;

    private String yddh;

    private String dzxx;

    private String zsdwsm;

    private String bkdsm;

    private String byxx;

    private String byxx1;

    private String byxx2;

    private String byxx3;

    private String bydwssm;

    private String kzyxx;

    private String jfbz;

    private String zxbz;

    private String bmsj;

    private String xgsj;

    private String qrsj;

    private String bkdwmc;

    private String bkyxsmc;

    private String fsh;//新增：复式号

    private String halfFscj; //新增复试换算后成绩，计算总分用

    public String getHalfFscj() {
        return halfFscj;
    }

    public void setHalfFscj(String halfFscj) {
        this.halfFscj = halfFscj;
    }

    public String getFsh() {
        return fsh;
    }

    public void setFsh(String fsh) {
        this.fsh = fsh;
    }

    public String getBklb() {
        return bklb;
    }

    public void setBklb(String bklb) {
        this.bklb = bklb;
    }

    public String getYwk1m() {
        return ywk1m;
    }

    public void setYwk1m(String ywk1m) {
        this.ywk1m = ywk1m;
    }

    public String getYwk2m() {
        return ywk2m;
    }

    public void setYwk2m(String ywk2m) {
        this.ywk2m = ywk2m;
    }

    public String getXmpy() {
        return xmpy;
    }

    public void setXmpy(String xmpy) {
        this.xmpy = xmpy;
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public String getMzm() {
        return mzm;
    }

    public void setMzm(String mzm) {
        this.mzm = mzm;
    }

    public String getXbm() {
        return xbm;
    }

    public void setXbm(String xbm) {
        this.xbm = xbm;
    }

    public String getHfm() {
        return hfm;
    }

    public void setHfm(String hfm) {
        this.hfm = hfm;
    }

    public String getXyjrm() {
        return xyjrm;
    }

    public void setXyjrm(String xyjrm) {
        this.xyjrm = xyjrm;
    }

    public String getZzmmm() {
        return zzmmm;
    }

    public void setZzmmm(String zzmmm) {
        this.zzmmm = zzmmm;
    }

    public String getHkszdm() {
        return hkszdm;
    }

    public void setHkszdm(String hkszdm) {
        this.hkszdm = hkszdm;
    }

    public String getHkszdxxdz() {
        return hkszdxxdz;
    }

    public void setHkszdxxdz(String hkszdxxdz) {
        this.hkszdxxdz = hkszdxxdz;
    }

    public String getDaszdm() {
        return daszdm;
    }

    public void setDaszdm(String daszdm) {
        this.daszdm = daszdm;
    }

    public String getDaszdw() {
        return daszdw;
    }

    public void setDaszdw(String daszdw) {
        this.daszdw = daszdw;
    }

    public String getDaszdwdz() {
        return daszdwdz;
    }

    public void setDaszdwdz(String daszdwdz) {
        this.daszdwdz = daszdwdz;
    }

    public String getDaszdwyzbm() {
        return daszdwyzbm;
    }

    public void setDaszdwyzbm(String daszdwyzbm) {
        this.daszdwyzbm = daszdwyzbm;
    }

    public String getKslym() {
        return kslym;
    }

    public void setKslym(String kslym) {
        this.kslym = kslym;
    }

    public String getBydwm() {
        return bydwm;
    }

    public void setBydwm(String bydwm) {
        this.bydwm = bydwm;
    }

    public String getBydw() {
        return bydw;
    }

    public void setBydw(String bydw) {
        this.bydw = bydw;
    }

    public String getByzydm() {
        return byzydm;
    }

    public void setByzydm(String byzydm) {
        this.byzydm = byzydm;
    }

    public String getByzymc() {
        return byzymc;
    }

    public void setByzymc(String byzymc) {
        this.byzymc = byzymc;
    }

    public String getXxxs() {
        return xxxs;
    }

    public void setXxxs(String xxxs) {
        this.xxxs = xxxs;
    }

    public String getXlm() {
        return xlm;
    }

    public void setXlm(String xlm) {
        this.xlm = xlm;
    }

    public String getXlzsbh() {
        return xlzsbh;
    }

    public void setXlzsbh(String xlzsbh) {
        this.xlzsbh = xlzsbh;
    }

    public String getByny() {
        return byny;
    }

    public void setByny(String byny) {
        this.byny = byny;
    }

    public String getZcxh() {
        return zcxh;
    }

    public void setZcxh(String zcxh) {
        this.zcxh = zcxh;
    }

    public String getXwm() {
        return xwm;
    }

    public void setXwm(String xwm) {
        this.xwm = xwm;
    }

    public String getXwzsbh() {
        return xwzsbh;
    }

    public void setXwzsbh(String xwzsbh) {
        this.xwzsbh = xwzsbh;
    }

    public String getZxjh() {
        return zxjh;
    }

    public void setZxjh(String zxjh) {
        this.zxjh = zxjh;
    }

    public String getDxwpdwszdm() {
        return dxwpdwszdm;
    }

    public void setDxwpdwszdm(String dxwpdwszdm) {
        this.dxwpdwszdm = dxwpdwszdm;
    }

    public String getDxwpdw() {
        return dxwpdw;
    }

    public void setDxwpdw(String dxwpdw) {
        this.dxwpdw = dxwpdw;
    }

    public String getZzllm() {
        return zzllm;
    }

    public void setZzllm(String zzllm) {
        this.zzllm = zzllm;
    }

    public String getZzllmc() {
        return zzllmc;
    }

    public void setZzllmc(String zzllmc) {
        this.zzllmc = zzllmc;
    }

    public String getWgym() {
        return wgym;
    }

    public void setWgym(String wgym) {
        this.wgym = wgym;
    }

    public String getWgymc() {
        return wgymc;
    }

    public void setWgymc(String wgymc) {
        this.wgymc = wgymc;
    }

    public String getRwpzsbh() {
        return rwpzsbh;
    }

    public void setRwpzsbh(String rwpzsbh) {
        this.rwpzsbh = rwpzsbh;
    }

    public String getTcxyzbh() {
        return tcxyzbh;
    }

    public void setTcxyzbh(String tcxyzbh) {
        this.tcxyzbh = tcxyzbh;
    }

    public String getLqdwdm() {
        return lqdwdm;
    }

    public void setLqdwdm(String lqdwdm) {
        this.lqdwdm = lqdwdm;
    }

    public String getLqdwmc() {
        return lqdwmc;
    }

    public void setLqdwmc(String lqdwmc) {
        this.lqdwmc = lqdwmc;
    }

    public String getLqzydm() {
        return lqzydm;
    }

    public void setLqzydm(String lqzydm) {
        this.lqzydm = lqzydm;
    }

    public String getLqzymc() {
        return lqzymc;
    }

    public void setLqzymc(String lqzymc) {
        this.lqzymc = lqzymc;
    }

    public String getLqlbm() {
        return lqlbm;
    }

    public void setLqlbm(String lqlbm) {
        this.lqlbm = lqlbm;
    }

    public String getLqxysm() {
        return lqxysm;
    }

    public void setLqxysm(String lqxysm) {
        this.lqxysm = lqxysm;
    }

    public String getFscj() {
        return fscj;
    }

    public void setFscj(String fscj) {
        this.fscj = fscj;
    }

    public String getZcjzsbf() {
        return zcjzsbf;
    }

    public void setZcjzsbf(String zcjzsbf) {
        this.zcjzsbf = zcjzsbf;
    }

    public String getZcj() {
        return zcj;
    }

    public void setZcj(String zcj) {
        this.zcj = zcj;
    }

    public String getJs1mc() {
        return js1mc;
    }

    public void setJs1mc(String js1mc) {
        this.js1mc = js1mc;
    }

    public String getJs2mc() {
        return js2mc;
    }

    public void setJs2mc(String js2mc) {
        this.js2mc = js2mc;
    }

    public String getBlzgnx() {
        return blzgnx;
    }

    public void setBlzgnx(String blzgnx) {
        this.blzgnx = blzgnx;
    }

    public String getQg() {
        return qg;
    }

    public void setQg(String qg) {
        this.qg = qg;
    }

    public String getXszgzc() {
        return xszgzc;
    }

    public void setXszgzc(String xszgzc) {
        this.xszgzc = xszgzc;
    }

    public String getSzssm() {
        return szssm;
    }

    public void setSzssm(String szssm) {
        this.szssm = szssm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getLhpydwm() {
        return lhpydwm;
    }

    public void setLhpydwm(String lhpydwm) {
        this.lhpydwm = lhpydwm;
    }

    public String getLhpydw() {
        return lhpydw;
    }

    public void setLhpydw(String lhpydw) {
        this.lhpydw = lhpydw;
    }

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    public String getBkdwdm() {
        return bkdwdm;
    }

    public void setBkdwdm(String bkdwdm) {
        this.bkdwdm = bkdwdm;
    }

    public String getBklbm() {
        return bklbm;
    }

    public void setBklbm(String bklbm) {
        this.bklbm = bklbm;
    }

    public String getBkyxsm() {
        return bkyxsm;
    }

    public void setBkyxsm(String bkyxsm) {
        this.bkyxsm = bkyxsm;
    }

    public String getJgszdm() {
        return jgszdm;
    }

    public void setJgszdm(String jgszdm) {
        this.jgszdm = jgszdm;
    }

    public String getCsdm() {
        return csdm;
    }

    public void setCsdm(String csdm) {
        this.csdm = csdm;
    }

    public String getBmddm() {
        return bmddm;
    }

    public void setBmddm(String bmddm) {
        this.bmddm = bmddm;
    }

    public String getBmh() {
        return bmh;
    }

    public void setBmh(String bmh) {
        this.bmh = bmh;
    }

    public String getXxgzdw() {
        return xxgzdw;
    }

    public void setXxgzdw(String xxgzdw) {
        this.xxgzdw = xxgzdw;
    }

    public String getXxgzjl() {
        return xxgzjl;
    }

    public void setXxgzjl(String xxgzjl) {
        this.xxgzjl = xxgzjl;
    }

    public String getJlcf() {
        return jlcf;
    }

    public void setJlcf(String jlcf) {
        this.jlcf = jlcf;
    }

    public String getKszbqk() {
        return kszbqk;
    }

    public void setKszbqk(String kszbqk) {
        this.kszbqk = kszbqk;
    }

    public String getJtcy() {
        return jtcy;
    }

    public void setJtcy(String jtcy) {
        this.jtcy = jtcy;
    }

    public String getTxdz() {
        return txdz;
    }

    public void setTxdz(String txdz) {
        this.txdz = txdz;
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getYddh() {
        return yddh;
    }

    public void setYddh(String yddh) {
        this.yddh = yddh;
    }

    public String getDzxx() {
        return dzxx;
    }

    public void setDzxx(String dzxx) {
        this.dzxx = dzxx;
    }

    public String getZsdwsm() {
        return zsdwsm;
    }

    public void setZsdwsm(String zsdwsm) {
        this.zsdwsm = zsdwsm;
    }

    public String getBkdsm() {
        return bkdsm;
    }

    public void setBkdsm(String bkdsm) {
        this.bkdsm = bkdsm;
    }

    public String getByxx() {
        return byxx;
    }

    public void setByxx(String byxx) {
        this.byxx = byxx;
    }

    public String getByxx1() {
        return byxx1;
    }

    public void setByxx1(String byxx1) {
        this.byxx1 = byxx1;
    }

    public String getByxx2() {
        return byxx2;
    }

    public void setByxx2(String byxx2) {
        this.byxx2 = byxx2;
    }

    public String getByxx3() {
        return byxx3;
    }

    public void setByxx3(String byxx3) {
        this.byxx3 = byxx3;
    }

    public String getBydwssm() {
        return bydwssm;
    }

    public void setBydwssm(String bydwssm) {
        this.bydwssm = bydwssm;
    }

    public String getKzyxx() {
        return kzyxx;
    }

    public void setKzyxx(String kzyxx) {
        this.kzyxx = kzyxx;
    }

    public String getJfbz() {
        return jfbz;
    }

    public void setJfbz(String jfbz) {
        this.jfbz = jfbz;
    }

    public String getZxbz() {
        return zxbz;
    }

    public void setZxbz(String zxbz) {
        this.zxbz = zxbz;
    }

    public String getBmsj() {
        return bmsj;
    }

    public void setBmsj(String bmsj) {
        this.bmsj = bmsj;
    }

    public String getXgsj() {
        return xgsj;
    }

    public void setXgsj(String xgsj) {
        this.xgsj = xgsj;
    }

    public String getQrsj() {
        return qrsj;
    }

    public void setQrsj(String qrsj) {
        this.qrsj = qrsj;
    }

    public String getBkdwmc() {
        return bkdwmc;
    }

    public void setBkdwmc(String bkdwmc) {
        this.bkdwmc = bkdwmc;
    }

    public String getBkyxsmc() {
        return bkyxsmc;
    }

    public void setBkyxsmc(String bkyxsmc) {
        this.bkyxsmc = bkyxsmc;
    }

    public String getKsfsm() {
        return ksfsm;
    }

    public void setKsfsm(String ksfsm) {
        this.ksfsm = ksfsm;
    }

    public String getYwk1mc() {
        return ywk1mc;
    }

    public void setYwk1mc(String ywk1mc) {
        this.ywk1mc = ywk1mc;
    }

    public String getYwk2mc() {
        return ywk2mc;
    }

    public void setYwk2mc(String ywk2mc) {
        this.ywk2mc = ywk2mc;
    }

    public String getZzll() {
        return zzll;
    }

    public void setZzll(String zzll) {
        this.zzll = zzll;
    }

    public String getWgy() {
        return wgy;
    }

    public void setWgy(String wgy) {
        this.wgy = wgy;
    }

    public String getYwk1() {
        return ywk1;
    }

    public void setYwk1(String ywk1) {
        this.ywk1 = ywk1;
    }

    public String getYwk2() {
        return ywk2;
    }

    public void setYwk2(String ywk2) {
        this.ywk2 = ywk2;
    }

    public String getZf() {
        return zf;
    }

    public void setZf(String zf) {
        this.zf = zf;
    }

    public String getCscj() {
        return cscj;
    }

    public void setCscj(String cscj) {
        this.cscj = cscj;
    }
}
