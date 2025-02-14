package com.kxmall.order.domain.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2019/7/6.
 */
@Data
public class OrderRequestBo {

    private List<OrderRequestProductBo> productList;

    /**
     * 商品支付总价
     */
    private BigDecimal totalPrice;

    private BigDecimal totalOriginalPrice;

    private Long addressId;

    private Long groupShopId;

    private String mono;

    /**
     * 购物车 ？ 直接点击购买商品
     */
    private String takeWay;

    private BigDecimal freightPrice;

    /**
     * 预计到达时间
     */
    private String predictTime;

    private Date predictDate;

    private Long storageId;

}
