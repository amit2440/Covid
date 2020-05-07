package com.med.disease.tracking.app.repository;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import com.med.disease.tracking.app.dto.UserDTO;

public class UserSqlProvider implements ProviderMethodResolver{
	
	
	
	public static String getUsersByName(UserDTO userDTO) {
	    return new SQL(){{
	      SELECT("*");
	      FROM("user ");
	      if (userDTO.getMobile() != null ) {
	    	  
	        WHERE("mobile = #{mobile}  and 1=1");
	      }else {
	    	  if(userDTO.getFirstName()!=null && userDTO.getLastName()!=null) {
	    		  WHERE("first_name like '% #{firstName }%' OR last_name like '%#{lastName}%' ");
	    	  }else {
	    		  if(userDTO.getFirstName()!=null && userDTO.getLastName()==null ) {
	    			  WHERE("first_name like '% #{firstName }%' ");
	    		  }else if(userDTO.getFirstName()==null && userDTO.getLastName()!=null) {
	    			  WHERE(" last_name like '%#{lastName}%'");
	    		  }
	    	  }
	      }
//	      ORDER_BY("id");
	    }}.toString();
	  }

	public static String updateSql(final UserDTO userDTO) {
		
		return new SQL(){{
//			UPDATE("USER");
//			SET("mobile").eq
			
			
			
			
		}}.toString();
		
		
	}
}
