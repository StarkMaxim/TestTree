package com.max.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
public class TreeElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Integer parentid;
    private Calendar createdate;
    private Calendar updatedate;
    private boolean finite;
    private int childrencount;

    public Calendar getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Calendar createdate) {
        this.createdate = createdate;
    }

    public Calendar getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Calendar updatedate) {
        this.updatedate = updatedate;
    }

    public boolean isFinite() {
        return finite;
    }

    public void setFinite(boolean finite) {
        this.finite = finite;
    }

    public int getChildrencount() {
        return childrencount;
    }

    public void setChildrencount(int childrencount) {
        this.childrencount = childrencount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}
