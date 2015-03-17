package com.nicholasbeach.scamper.controller;

import javax.inject.Inject;

import com.nicholasbeach.scamper.persistence.TagMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicholasbeach.scamper.domain.Tag;


@RequestMapping(value =  "/tag")
@RestController
public class TagController extends RepositoryRestfulController<Tag> {

    @Inject
    private TagMapper tagRepository;
	
	protected TagMapper getDaoService() {
		return tagRepository;
	}

	protected Class<Tag> getResourceClass() {
		return Tag.class;
	}
	
}
