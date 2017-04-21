package com.wxy8866.demo.marketing163.web.expression;

/**
 * Created by wxy8866 on 2017/4/19.
 */
public class ProductPublicVO
{
    private Integer id;

    private String title;

    private String summary;

    private String detail;

    private String image;

    private Long price;

    public ProductPublicVO()
    {
    }

    public ProductPublicVO(Integer id, String title, String summary, String detail, String image, Long price)
    {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.detail = detail;
        this.image = image;
        this.price = price;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public Long getPrice()
    {
        return price;
    }

    public void setPrice(Long price)
    {
        this.price = price;
    }
}
