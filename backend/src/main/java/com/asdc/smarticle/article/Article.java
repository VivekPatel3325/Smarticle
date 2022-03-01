package com.asdc.smarticle.article;

import com.asdc.smarticle.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String heading;

    @Column
    private String content;

    @Column
    private boolean visibility;

    @Column
    @CreationTimestamp
    private Date creationdate;

    @Column
    @CreationTimestamp
    private Date updationdate;


    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User userid;

    @Column(insertable = false)
    @ElementCollection(targetClass=Long.class)
    private List<Long> tagid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Long> getTagid() {
        return tagid;
    }

    public void setTagid(List<Long> tagid) {
        this.tagid = tagid;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Date getUpdationdate() {
        return updationdate;
    }

    public void setUpdationdate(Date updationdate) {
        this.updationdate = updationdate;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
