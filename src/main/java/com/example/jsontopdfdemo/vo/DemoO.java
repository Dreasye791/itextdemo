package com.example.jsontopdfdemo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author l
 * @Date 2021/11/09
 */
@Data
public class DemoO {
    /**
     * 本年欠款
     */
    private Double bnqk;
    /**
     * 订单号
     */
    private String djh;
    /**
     * 单据类型
     */
    private String djlx;
    private String kh;
    private String pk_dzd_b1;
    private String pk_dzd_h;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private String rq;
    /**
     * 时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String sj;
    /**
     * 收款金额
     */
    private int skje;
    /**
     * 数量
     */
    private int sl;
    private int ssje;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM")
    private String yf;
    /**
     * 应收金额
     */
    private int ysje;
    private int ysk;
    /**
     * 其他折扣额
     */
    private int zjzk;
    /**
     * 折扣额
     */
    private int zke;
    /**
     * 债务重组
     */
    private int zwcz;
    /**
     * 摘要
     */
    private String zy;
    private String zz;
}
