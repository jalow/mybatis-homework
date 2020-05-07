package com.random.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;

public class MyIntegerListTypeHandler extends BaseTypeHandler<List<Integer>> {
    private static final String BRACE_LEFT = "{";
    private static final String BRACE_RIGHT = "}";
    private static final String SYMBOL_COMMA = ",";
    private static final String REGEX_INTEGER_LIST = "\\{((\\d)+(,))+(\\d)+\\}";
    
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType)
			throws SQLException {
		if (null == parameter){
	        throw new TypeException(
	                "Parameter must not be NULL");		
		}
		
		Object[] paramArray = parameter.toArray();
		StringBuffer charBuffer = new StringBuffer();
		charBuffer.append(BRACE_LEFT);
		for (int y = 0; y < paramArray.length; y++){
			charBuffer.append(paramArray[y]);
			if (y < paramArray.length - 1){
				charBuffer.append(SYMBOL_COMMA);
			}
		}
		charBuffer.append(BRACE_RIGHT);
		
		ps.setString(i, charBuffer.toString());
	}

	@Override
	public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return getList(rs.getString(columnName));
	}
	
	@Override
	public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {	
		return getList(rs.getString(columnIndex));
	}

	@Override
	public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getList(cs.getString(columnIndex));
	}
	
	/**
	 * 
	 * Cast string to integer list
	 * 
	 * @param columnResult
	 * @return
	 */
    private List<Integer> getList(String columnResult) {
		if(!checkFormat(columnResult)){
	        throw new TypeException(
	                "Can not cast the result, please check your format: " + columnResult);				
		}
		
		// Remove braces
		String newResult = columnResult.substring(1, columnResult.length() - 1);
		
		// Cast to string array
		String[] resultList = newResult.split(SYMBOL_COMMA);
		
		// Cast to integer list
		List<Integer> retList = new ArrayList<Integer>();
		for (String s : resultList){
			retList.add(Integer.valueOf(s));
		}
		
		return retList;
	}
    
	/**
     * 
     * Check if the specified string matches regular expression
     * 
     * 
     * @param columnResult
     * @return
     */
	private boolean checkFormat(String columnResult) {
		return Pattern.matches(REGEX_INTEGER_LIST, columnResult);
	}	
}
