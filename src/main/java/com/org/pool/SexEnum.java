package com.org.pool;

public enum SexEnum {

    MAM_STATE(1, "男"),
    WoMAM_STATE(2, "男2");

    SexEnum() {

    }

    SexEnum(Integer id, String sex) {
        this.id = id;
        this.sex = sex;
    }

    private Integer id;
    private String sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}
