package com.example.bean;


import android.arch.persistence.room.Entity;

@Entity
public class Bean {
    private Long id;
    private String img;
    private String title;
    private String name;
    private boolean isCollect;
    @Generated(hash = 735963739)
    public Bean(Long id, String img, String title, String name, boolean isCollect) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.name = name;
        this.isCollect = isCollect;
    }
    @Generated(hash = 80546095)
    public Bean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getIsCollect() {
        return this.isCollect;
    }
    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

}
