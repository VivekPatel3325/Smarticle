package com.asdc.smarticle.articletag;

import com.asdc.smarticle.article.Article;

import javax.persistence.*;
import java.util.List;
/**
 * @author Khushboo Patel
 * @version 1.0
 * @since 2022-02-28
 */

@Entity
@Table(name = "articletags")
public class ArticleTag{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "articleFK", referencedColumnName = "id")
    private Article articleID;

    @Column
    private Long tagId;

    public Article getArticleID() {
        return articleID;
    }

    public void setArticleID(Article articleID) {
        this.articleID = articleID;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
