package com.avalon.domain;

public class Hero {
    private int id;
    private String name;
    private String race;
    private String type;
    private String charPicPath;
    private String introPicPath;

    public Hero() {

    }

    public Hero(int id, String name, String charPicPath, String introPicPath) {
        this.id = id;
        this.name = name;
        this.charPicPath = charPicPath;
        this.introPicPath = introPicPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCharPicPath() {
        return charPicPath;
    }

    public void setCharPicPath(String charPicPath) {
        this.charPicPath = charPicPath;
    }

    public String getIntroPicPath() {
        return introPicPath;
    }

    public void setIntroPicPath(String introPicPath) {
        this.introPicPath = introPicPath;
    }

    @Override
    public String toString() {
        return "Hero [id=" + id + ", name=" + name + ", race=" + race + ", type=" + type + ", charPicPath="
                + charPicPath + ", introPicPath=" + introPicPath + "]";
    }
}
