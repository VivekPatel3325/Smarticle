package com.asdc.smarticle.tag;

import com.asdc.smarticle.article.Article;
import com.asdc.smarticle.token.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
* Developed By: Khushboo Patel, Rushi Patel
* Version: 1
* Since: 27-02-2022
* */

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    TagRepository tagRepository;


}
