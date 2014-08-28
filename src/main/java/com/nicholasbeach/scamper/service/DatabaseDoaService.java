package com.nicholasbeach.scamper.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.nicholasbeach.scamper.domain.DatabaseRow;

public abstract class DatabaseDoaService<T extends DatabaseRow> implements DaoService<T> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	protected JdbcTemplate JdbcTemplate;

	protected JdbcTemplate getJdbcTemplate() {
		return this.JdbcTemplate;
	}

	public T get(Integer id) {
		logger.debug("Getting object. id={}", id);

		try {
			List<T> queryResult = getJdbcTemplate().query(getSql().get("select"), new Object[] { id }, getRowMapper());

			if (queryResult != null && queryResult.size() > 0) {
				return queryResult.get(0);
			}
			return null;
		} catch (Exception exception) {
			logger.error("An error occurred while getting an object from the database. Error = {}", exception.getMessage());
			throw new RuntimeException(exception);
		}
	}


	public List<T> getAll() {
		logger.debug("Getting all objects.");
		
		try {
			return getJdbcTemplate().query(getSql().get("selectAll"), new Object[] { }, getRowMapper());

		} catch (Exception exception) {
			logger.error("An error occurred while getting all objects from the database. Error = {}", exception.getMessage());
			throw new RuntimeException(exception);
		}
	}

	
	public List<T> getLimited(int limit) {
		logger.debug("Getting all objects. limit={}", limit);
		
		try {
			return getJdbcTemplate().query(getSql().get("selectAllLimited"), new Object[] { limit }, getRowMapper());

		} catch (Exception exception) {
			logger.error("An error occurred while getting all objects with limit from the database. Error = {}", exception.getMessage());
			throw new RuntimeException(exception);
		}
	}


	public T create(final T item) {
		logger.debug("Saving object. id=", item.getId());
		
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			PreparedStatementCreator psc = new PreparedStatementCreator() {		
				public java.sql.PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(getSql().get("insert"), Statement.RETURN_GENERATED_KEYS);
					return item.getInsertPreparedStatement(ps);
				}
			};
	 
			JdbcTemplate.update(psc, keyHolder);
			item.setId(keyHolder.getKey().intValue());
			
			return item;
		
	}
	
	public boolean update(final T item) {
		logger.debug("Updating object. id={}", item.getId());
		
		PreparedStatementCreator psc = new PreparedStatementCreator() {		
			public java.sql.PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(getSql().get("update"));
				return item.getUpdatePreparedStatement(ps);
			}
		};
 
		int affectedRows = JdbcTemplate.update(psc);
		
		if(affectedRows > 0) return true;
		else return false;
				
	}
	
	public boolean delete(Integer id) {
		logger.debug("Deleting object. id={}", id);
		
		try {
			
			int affectedRows = getJdbcTemplate().update(getSql().get("delete"), new Object[] { id });
			
			if(affectedRows > 0) return true;
			else return false;
			
		} catch (Exception exception) {
			logger.error("An error occurred while deleting an object from the database. Error = {}", exception.getMessage());
			throw new RuntimeException(exception);
		}
	}

	protected  abstract RowMapper<T> getRowMapper();
	protected  abstract Map<String, String> getSql();
			
}
