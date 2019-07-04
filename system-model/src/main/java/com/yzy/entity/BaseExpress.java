package com.yzy.entity;

import javax.persistence.*;

@Table(name = "base_express")
public class BaseExpress {
    private String pinyin;

    private String hanzi;

    /**
     * @return pinyin
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * @param pinyin
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * @return hanzi
     */
    public String getHanzi() {
        return hanzi;
    }

    /**
     * @param hanzi
     */
    public void setHanzi(String hanzi) {
        this.hanzi = hanzi;
    }
}