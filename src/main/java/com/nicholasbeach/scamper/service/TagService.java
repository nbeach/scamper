package com.nicholasbeach.scamper.service;

import com.nicholasbeach.scamper.domain.Tag;
import com.nicholasbeach.scamper.persistence.TagMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class TagService extends AbstractResourceService<Tag> {

    @Inject
    private TagMapper tagMapper;

    @Override
    protected Class<Tag> getResourceClass() {
        return Tag.class;
    }

    @Override
    protected TagMapper getMapper() {
        return tagMapper;
    }
}
