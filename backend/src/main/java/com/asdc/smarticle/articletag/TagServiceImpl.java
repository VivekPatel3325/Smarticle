package com.asdc.smarticle.articletag;

import com.asdc.smarticle.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    TagRepository articletagRepository;

   

	@Override
	public List<Tag> getTags() {
		List<Tag> tag =  (List<Tag>) articletagRepository.findAll();
		System.out.println(tag.size());
		return tag;
	}
}
