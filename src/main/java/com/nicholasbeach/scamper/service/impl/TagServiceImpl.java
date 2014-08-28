package com.nicholasbeach.scamper.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.nicholasbeach.scamper.domain.Tag;
import com.nicholasbeach.scamper.service.DatabaseDoaService;
import com.nicholasbeach.scamper.util.rowmapper.TagRowMapper;

@Scope("singleton")
@Service("TagServiceImpl")
public class TagServiceImpl extends DatabaseDoaService<Tag> {

	@Resource
	private Map<String, String> tagServiceSql;

	protected RowMapper<Tag> getRowMapper() {
		return new TagRowMapper();
	}

	protected Map<String, String> getSql() {
		return tagServiceSql;
	}

}
