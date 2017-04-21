package com.wxy8866.demo.marketing163.web.expression;

/**
 * Created by wxy8866 on 2017/4/20.
 */
public class ProductToBuyVO
{
    private Integer id;
    private Long    number;

    public ProductToBuyVO()
    {
    }

    public ProductToBuyVO(Integer id, Long number)
    {
        this.id = id;
        this.number = number;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Long getNumber()
    {
        return number;
    }

    public void setNumber(Long number)
    {
        this.number = number;
    }
}
