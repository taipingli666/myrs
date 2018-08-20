package com.choice.domain.vo;
import java.util.List;

/**
 * 封装排版的vo
 * Created by duhuo on 2017/7/25.
 */
public class PaiBanVo1 {
    private String yishengxm;
    private String yishengbh;
    private String keshimc;
    private List list;

    public String getYishengxm() {
        return yishengxm;
    }

    public void setYishengxm(String yishengxm) {
        this.yishengxm = yishengxm;
    }

    public String getYishengbh() {
        return yishengbh;
    }

    public void setYishengbh(String yishengbh) {
        this.yishengbh = yishengbh;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getKeshimc() {
        return keshimc;
    }

    public void setKeshimc(String keshimc) {
        this.keshimc = keshimc;
    }
}
