package com.pinde.sci.biz.irb;

import com.pinde.sci.model.mo.IrbInfoUser;

import java.util.List;

/**
 * @Description 伦理委员会成员biz
 */
public interface IIrbInfoUserBiz {
    /**
     * 添加或修改
     *
     * @param user
     * @return
     */
    int edit(IrbInfoUser user);

    /**
     * 批量添加或修改
     *
     * @param users
     */
    void editUsers(List<IrbInfoUser> users);

    /**
     * 查询
     *
     * @param user
     * @return
     */
    List<IrbInfoUser> queryUserList(IrbInfoUser user);

    /**
     * 删除
     *
     * @param user
     * @return
     */
    int delete(IrbInfoUser user);
//    /**
//     * 查询扩展
//     * @param user
//     * @return
//     */
//	public List<IrbInfoUserExt> queryUserExtList(IrbInfoUser user);

    //    /**
//     * 按主键查找
//     *
//     * @param flow
//     * @return
//     */
//	public IrbInfoUser queryUserByFlow(String flow);
    void saveOrder(String[] userFlow, String irbInfoFlow);

}
