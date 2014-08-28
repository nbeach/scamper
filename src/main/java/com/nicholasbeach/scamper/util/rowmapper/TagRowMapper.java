package com.nicholasbeach.scamper.util.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nicholasbeach.scamper.domain.Tag;

public class TagRowMapper implements RowMapper<Tag> {

	@Override
	public Tag mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

		Tag newTag = new Tag();
		
		newTag.setId(resultSet.getInt("tag_id"));
		newTag.setTitle(resultSet.getString("title"));
		
		return newTag;
	}

}
