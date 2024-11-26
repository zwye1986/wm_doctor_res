package com.pinde.sci.ctrl.util;

import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.XmlParse;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.dao.common.CommonQueryMapper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/common/util" })
public class CommonUtilController extends GeneralController {
	
	private static Logger logger = LoggerFactory.getLogger(CommonUtilController.class);

	/*************************************************加载sql START*****************************************************/
	private static Map<String,String> customSqlMap = new HashMap<String,String>();
	/**
	 * 加载自定义sql资源,提供给通用数据获取方法使用
	 * @param id
	 */
	private static String getCustomSqlById(String id){
		String sql;
		sql = customSqlMap.get(id);
		if(StringUtil.isBlank(sql)){
			try {
				XmlParse commonsqlxml = new XmlParse(SpringUtil.getResource("classpath:" + GlobalConstant.COMMON_SQL + "/customSqlRes.xml").getFile());
				Element root = commonsqlxml.getRootElement();
				Element sqle = (Element)root.selectSingleNode("//sql[@id='"+id+"']");
				if(sqle!=null){
					sql = sqle.getTextTrim();
					customSqlMap.put(sqle.attributeValue("id"),sql);
				}
			} catch (Exception e) {
				logger.error("通用sql资源加载出错...", e);
				e.printStackTrace();
			}
		}
		return sql;
	}
	/*************************************************加载sql END*******************************************************/

	@Autowired
	CommonQueryMapper queryMapper;
	/**
	 * executeIds为需执行sql的id集合
	 * 可一次执行多条以map类型包装sql的id为key
	 * sql执行参数接收名为 GlobalConstant.ARGUMENTS
	 * 如果一次调用多条参数接收名格式为 (sql的id).(GlobalConstant.ARGUMENTS)
	 * 若要执行多次相同语句但参数不同 参数可以这么传 executeIds为(sql的id)[key],arguments为(sql的id)[key].(GlobalConstant.ARGUMENTS)
	 * 若执行不同语句参数相同则直接设置参数接收名为GlobalConstant.ARGUMENTS
	 * @param executeIds
	 * @param request
     * @return
     */
	@RequestMapping(value = {"/getDatas" })
	@ResponseBody
	public Object getDatas(String[] executeIds, HttpServletRequest request) throws Exception {
		Object result = null;
		if(executeIds!=null){
			String[] args;
			String sql;
			int length = executeIds.length;
			if(length==1){//如果只有一条需执行的sql则执行以下包装方式
				args = request.getParameterValues(GlobalConstant.ARGUMENTS);//根据预定义的参数名获取参数
				sql = getUsableSql(executeIds[0],args);//获取可执行的sql

				List<Map<String,Object>> queryResult = queryMapper.commonQuery(sql);//执行

				result = resultHandler(queryResult);//将结果集优化后返回出去,可以以更合理的方式获取结果
			}else if(length>1){//如果需要执行一条以上的sql需要使用下面的包装方式,会将每一条sql的结果集以其sql的id为key包装进结果map内
				Map<String,Object> rm = new HashMap<String,Object>();
				for(String sqlKey : executeIds){//循环执行多条sql
					args = request.getParameterValues(sqlKey+"."+GlobalConstant.ARGUMENTS);//根据sql的id获取其对应的参数

					boolean isMap = sqlKey.endsWith("]");
					if(isMap){//如果是同sql的id则需要如此包装,用[]内的key作为辨认标识
						int cutEndIndex = sqlKey.indexOf("[");

						String mk = sqlKey.substring(cutEndIndex+1,sqlKey.length()-1);//截取[]内值作为同id的sql结果集的key
						sqlKey = sqlKey.substring(0,cutEndIndex);//截取sql的id

						Object currVal = rm.get(sqlKey);
						if(currVal==null){
							currVal = new HashMap<String,Object>();
						}

						sql = getUsableSql(sqlKey,args);

						List<Map<String,Object>> queryResult = queryMapper.commonQuery(sql);
						((Map<String,Object>)currVal).put(mk,resultHandler(queryResult));
						rm.put(sqlKey,currVal);
					}else{
						sql = getUsableSql(sqlKey,args);

						List<Map<String,Object>> queryResult = queryMapper.commonQuery(sql);
						rm.put(sqlKey,resultHandler(queryResult));
					}
				}
				result = rm;
			}
		}
		return result;
	}

	/**
	 * 获取可用的sql
	 * @param id
	 * @param args
     * @return
     */
	private String getUsableSql(String id,String[] args) throws Exception {
		String sql;

		sql = getCustomSqlById(id);
		if(sql==null){
			throw new Exception("当前Sql的Id为："+id+" 请验证是否正确!..");
		}

		sql = sqlSetArguments(sql,args);
		if(sql.indexOf("{")>=0 || sql.indexOf("}")>=0){
			throw new Exception(sql+"：sql参数不足或格式有误...");
		}

		return sql;
	}

	/**
	 * 为sql内占位符赋值
	 * @param sql
	 * @param args
     * @return
     */
	private String sqlSetArguments(String sql,String[] args){
		if(sql==null){
			return sql;
		}
		if(args==null || args.length==0){
			return sql;
		}

		int len = args.length;
		for(int i = 0 ; i<len ; i++){
			sql = sql.replace("{"+i+"}",args[i]);
		}

		return sql;
	}

	/**
	 * 将读取出的文件转换成最终类型,方便读写
	 * @param queryResult
	 * @return
     */
	private Object resultHandler(List<Map<String,Object>> queryResult){
		Object o = null;
		if(queryResult!=null){
			List<Map<String,Object>> rs = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> map : queryResult){
				Map<String,Object> hanedMap = handlerTheMap(map);
				rs.add(hanedMap);
			}
			o = rs;
		}
		return o;
	}

	/**
	 * 将字段名称处理为我们常用的格式
	 * @param map
	 * @return
     */
	private Map<String,Object> handlerTheMap(Map<String,Object> map){
		Map<String,Object> hanedMap = null;
		if(map!=null){
			hanedMap = new HashMap<String,Object>();
			for(String dbName : map.keySet()){
				hanedMap.put(convertDbName2PropName(dbName),map.get(dbName));
			}
		}
		return hanedMap;
	}

	//数据库命名格式分词符
	private static String DB_NAME_SPLIT = "_";
	/**
	 * 将数据库命名格式转换为java属性命名格式
	 * @param dbName
	 * @return
     */
	private String convertDbName2PropName(String dbName){
		String result = "";
		if(dbName!=null){
			String[] words = dbName.split(DB_NAME_SPLIT);
			if(words!=null){
				int length = words.length;
				for(int i = 0 ; i<length ; i++){
					String currWord = words[i].toLowerCase();
					if(i>0){
						currWord = currWord.substring(0,1).toUpperCase() + currWord.substring(1);
					}
					result+=currWord;
				}
			}
		}
		return result;
	}
}
