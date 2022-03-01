package com.asdc.smarticle.articletag;

import com.asdc.smarticle.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ArticleTagServiceImpl implements ArticleTagService{

    @Autowired
    ArticleTagRepository articletagRepository;

    @Override
    public ArticleTag saveArticleTag(Article article) {

            ArticleTag articletag=null;
            List<Long> tag_list= article.getTagid();

            Iterator<Long> itr = tag_list.iterator();
            while(itr.hasNext()){

                articletag =  new ArticleTag();
                Long tags = itr.next();
                System.out.println(tags);

                if(tags!=null){
                    articletag.setArticleID(article);
                    articletag.setTagId(tags);
                    articletagRepository.save(articletag);
                }
            }
        return articletag;
    }
}
