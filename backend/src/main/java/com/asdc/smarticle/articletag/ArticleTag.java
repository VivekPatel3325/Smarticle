package com.asdc.smarticle.articletag;

import com.asdc.smarticle.article.Article;

import javax.persistence.*;
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
    
    @Column
    private String tagName;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
    
    
}
