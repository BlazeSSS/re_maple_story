package com.avalon.domain;

public class User {
    private String id;
    private String name;
    private String password;
    private String email;
    private String phonenum;
    private int isGM;
    private int isFreeze;
    private String heroCollection;
    private String monsterCollection;

    public User() {

    }

    public User(String id, String name, String password, String email, String phonenum, int isGM, int isFreeze) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phonenum = phonenum;
        this.isGM = isGM;
        this.isFreeze = isFreeze;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public int getIsGM() {
        return isGM;
    }

    public void setIsGM(int isGM) {
        this.isGM = isGM;
    }

    public int getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(int isFreeze) {
        this.isFreeze = isFreeze;
    }

    public String getHeroCollection() {
        return heroCollection;
    }

    public void setHeroCollection(String heroCollection) {
        this.heroCollection = heroCollection;
    }

    public String getMonsterCollection() {
        return monsterCollection;
    }

    public void setMonsterCollection(String monsterCollection) {
        this.monsterCollection = monsterCollection;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", phonenum="
                + phonenum + ", isGM=" + isGM + ", isFreeze=" + isFreeze + ", heroCollection=" + heroCollection
                + ", monsterCollection=" + monsterCollection + "]";
    }
}
