package com.nicholasbeach.scamper.service;

import com.nicholasbeach.scamper.domain.Tag;
import com.nicholasbeach.scamper.persistence.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService extends AbstractResourceService<Tag> {

    @Autowired
    private TagDao tagDao;

    @Override
    protected Class<Tag> getResourceClass() {
        return Tag.class;
    }

    @Override
    protected TagDao getDao() {
        return tagDao;
    }
}
