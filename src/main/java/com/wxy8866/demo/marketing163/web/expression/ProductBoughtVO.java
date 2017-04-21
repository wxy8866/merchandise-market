package com.wxy8866.demo.marketing163.web.expression;

/**
 * Created by wxy8866 on 2017/4/18.
 */
public class ProductBoughtVO extends ProductShowVO
{
    private Long buyTime;

    public Long getBuyTime()
    {
        return buyTime;
    }

    public void setBuyTime(Long buyTime)
    {
        this.buyTime = buyTime;
    }
}