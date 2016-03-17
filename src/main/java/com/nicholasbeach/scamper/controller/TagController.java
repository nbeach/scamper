package com.nicholasbeach.scamper.controller;

import com.nicholasbeach.scamper.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicholasbeach.scamper.domain.Tag;


@RequestMapping(value =  "/tag")
@RestController
public class TagController extends AbstractRestfulController<Tag> {

    @Autowired
    private TagService tagService;
	
	protected TagService getService() {
		return tagService;
	}

	protected Class<Tag> getResourceClass() {
		return Tag.class;
	}
	
}
