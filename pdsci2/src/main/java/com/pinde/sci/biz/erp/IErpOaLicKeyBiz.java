package com.pinde.sci.biz.erp;

import com.pinde.sci.model.mo.ErpOaLicKey;
import org.bouncycastle.openpgp.PGPException;

import java.io.IOException;
import java.util.List;

public interface IErpOaLicKeyBiz {

    /**
     * 根据授权文件流水号查询
     *
     * @param licFlow
     * @return
     */
    ErpOaLicKey readLicKey(String licFlow);

    /**
     * 根据条件查询授权文件
     *
     * @param erpOaLicKey
     * @return
     */
    List<ErpOaLicKey> searchLicList(ErpOaLicKey erpOaLicKey);

    /**
     * 保存授权文件信息
     *
     * @param erpOaLicKey
     * @return
     */
    int save(ErpOaLicKey erpOaLicKey);

    /**
     * 生成授权文件并保存
     *
     * @param erpOaLicKey
     * @return
     * @throws IOException
     * @throws PGPException
     * @throws Exception
     */
    void createLicFile(ErpOaLicKey erpOaLicKey) throws Exception;

    /**
     * 发送授权文件
     *
     * @param erpOaLicKey
     * @param email
     * @return
     */
    int sendLicFile(ErpOaLicKey erpOaLicKey, String email);

    /**
     * 保存并发送
     *
     * @param erpOaLicKey
     * @return
     * @throws PGPException
     * @throws IOException
     */
    String saveAndSendLicFile(ErpOaLicKey erpOaLicKey) throws Exception;

    /**
     * 回写
     *
     * @param erpOaLicKey
     * @return
     */
    void backUpdateContractProduct(ErpOaLicKey erpOaLicKey);
}
