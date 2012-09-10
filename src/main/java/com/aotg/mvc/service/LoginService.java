package com.aotg.mvc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotg.mvc.domain.Users;

/**
 * Service for processing Persons. 
 * <p>
 * For a complete reference to Spring JDBC and JdbcTemplate
 * see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html
 * <p>
 * For transactions, see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/transaction.html
 */
@Service("loginService")
@Transactional
public class LoginService {

	protected static Logger logger = Logger.getLogger("LoginService");
	
	private SimpleJdbcTemplate jdbcTemplate;
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	/**
	 * Retrieves user info based on phone number
	 * 
	 * @return users info 
	 */
	public List<Users> getAll(String phoneNumber) {
		logger.debug("Retrieving current users info");
		
		// Prepare our SQL statement
		String sql = "select id, userName, pin from users where phoneNumber=" + phoneNumber;
		
		// Maps a SQL result to a Java object
		RowMapper<Users> mapper = new RowMapper<Users>() {  
	        public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Users users = new Users();
	        	users.setId(rs.getInt("id"));
	        	users.setUserName(rs.getString("userName"));
	        	users.setPin(rs.getInt("pin"));
	            return users;
	        }
	    };
		
		// Retrieve all
		return jdbcTemplate.query(sql, mapper);
	}

}
