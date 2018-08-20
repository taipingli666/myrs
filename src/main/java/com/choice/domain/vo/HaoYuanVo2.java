package com.choice.domain.vo;

/**
 * 封装号源的vo
 * Created by duhuo on 2017/7/26.
 */
public class HaoYuanVo2 {
    private String kaishishijian;
    private String jieshushijian;
    private String shunxuhao;

    public String getKaishishijian() {
        return kaishishijian;
    }

    public void setKaishishijian(String kaishishijian) {
        this.kaishishijian = kaishishijian;
    }

    public String getJieshushijian() {
        return jieshushijian;
    }

    public void setJieshushijian(String jieshushijian) {
        this.jieshushijian = jieshushijian;
    }

    public String getShunxuhao() {
        return shunxuhao;
    }

    public void setShunxuhao(String shunxuhao) {
        this.shunxuhao = shunxuhao;
    }
    @Override
    public String toString(){
        return this.shunxuhao;
    }
}
