package com.pinde.core.typehandler;

import com.pinde.core.util.StringUtil;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlServerStringTypeHandler implements  TypeHandler<String> {
	 
	@Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (StringUtil.isNotBlank(parameter)) {
        	if(StringUtil.isNotEquals(parameter,parameter.trim())){
//        		System.err.println(" sqlserver 0......");
        	}
            ps.setObject(i, parameter.trim());
        } else {
            ps.setString(i, "");
        }
    }
 
    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
    	if(rs.getString(columnName)!=null&&StringUtil.isNotEquals(rs.getString(columnName), StringUtil.defaultString(rs.getString(columnName)).trim())){
//    		System.err.println(columnName+" sqlserver 1......");
    	}
        return StringUtil.defaultString(rs.getString(columnName)).trim();
    }
 
    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
    	if(rs.getString(columnIndex)!=null&&StringUtil.isNotEquals(rs.getString(columnIndex), StringUtil.defaultString(rs.getString(columnIndex)).trim())){
//    		System.err.println("sqlserver 2......");
    	}
        return StringUtil.defaultString(rs.getString(columnIndex)).trim();
    }
 
    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
    	if(cs.getString(columnIndex)!=null&&StringUtil.isNotEquals(cs.getString(columnIndex), StringUtil.defaultString(cs.getString(columnIndex)).trim())){
//    		System.err.println("sqlserver 3......");
    	}
        return StringUtil.defaultString(cs.getString(columnIndex)).trim();
    }
}
