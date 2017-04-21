package com.wxy8866.demo.marketing163.web.expression;

/**
 * Created by wxy8866 on 2017/4/18.
 */
public class ProductShowVO extends ProductPublicVO
{
    private Integer buyPrice;

    private Long buyNum;

    private Long saleNum;

    private boolean isBuy;

    private boolean isSell;

    public Integer getBuyPrice()
    {
        return buyPrice;
    }

    public void setBuyPrice(Integer buyPrice)
    {
        this.buyPrice = buyPrice;
    }

    public Long getBuyNum()
    {
        return buyNum;
    }

    public void setBuyNum(Long buyNum)
    {
        this.buyNum = buyNum;
    }


    public Long getSaleNum()
    {
        return saleNum;
    }

    public void setSaleNum(Long saleNum)
    {
        this.saleNum = saleNum;
    }

    public boolean getIsBuy()
    {
        return isBuy;
    }

    public void setIsBuy(boolean buy)
    {
        isBuy = buy;
    }

    public boolean getIsSell()
    {
        return isSell;
    }

    public void setIsSell(boolean sell)
    {
        isSell = sell;
    }
}
