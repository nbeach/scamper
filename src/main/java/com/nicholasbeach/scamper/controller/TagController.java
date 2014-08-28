package com.nicholasbeach.scamper.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicholasbeach.scamper.domain.Tag;
import com.nicholasbeach.scamper.service.DaoService;

@RequestMapping(value =  "/tag")
@RestController
public class TagController extends DatabaseRestfulController<Tag> {
	
	@Resource(name = "TagServiceImpl")
	private DaoService<Tag> tagService;
	
	protected DaoService<Tag> getDaoService() {
		return tagService;
	}

	protected Class<Tag> getResourceClass() {
		return Tag.class;
	}
	
}
