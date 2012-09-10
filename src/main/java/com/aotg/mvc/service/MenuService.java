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

import com.aotg.mvc.domain.Menu;

/**
 * Service for processing Menus. 
 * <p>
 * For a complete reference to Spring JDBC and JdbcTemplate
 * see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html
 * <p>
 * For transactions, see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/transaction.html
 */
@Service("menuService")
@Transactional
public class MenuService {

	protected static Logger logger = Logger.getLogger("MenuService");
	
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
	public List<Menu> listMenu(Integer parentMenuId) {
		logger.debug("Retrieving current menu info");
		
		// Prepare our SQL statement
		String sql = "SELECT id, controller, prompt, parentMenuId FROM menu WHERE parentMenuId = " + parentMenuId;
		
		// Maps a SQL result to a Java object
		RowMapper<Menu> mapper = new RowMapper<Menu>() {  
	        public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Menu menu = new Menu();
	        	menu.setId(rs.getInt("id"));
	        	menu.setController(rs.getString("controller"));
	        	menu.setPrompt(rs.getString("prompt"));
	        	menu.setParentMenuId(rs.getInt("parentMenuId"));	        	
	            return menu;
	        }
	    };
		
		// Retrieve all
		return jdbcTemplate.query(sql, mapper);
	}
	
	 public String getMenuText(Integer menuTypeId) {
		 return "This is a menu";
	 }
	
	 public void lastLogin(Integer id) {
		  logger.debug("Updating current users last login date/time");
		   
		  // Prepare our SQL statement
		  String sql = "UPDATE users SET lastLogin = NOW() WHERE id = :id";
		   
		  // Assign values to parameters
		  Map<String, Object> parameters = new HashMap<String, Object>();
		  parameters.put("id", id);
		   
		  // Edit
		  jdbcTemplate.update(sql, parameters);
		   
	}

}