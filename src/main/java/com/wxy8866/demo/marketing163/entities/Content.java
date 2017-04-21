package com.wxy8866.demo.marketing163.entities;

public class Content
{
    private Integer id;

    private Long price;

    private String title;

    private String summary;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Long getPrice()
    {
        return price;
    }

    public void setPrice(Long price)
    {
        this.price = price;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary == null ? null : summary.trim();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", title=").append(title);
        sb.append(", summary=").append(summary);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that)
    {
        if (this == that)
        {
            return true;
        }
        if (that == null)
        {
            return false;
        }
        if (getClass() != that.getClass())
        {
            return false;
        }
        Content other = (Content) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getSummary() == null ? other.getSummary() == null : this.getSummary().equals(other.getSummary()));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getSummary() == null) ? 0 : getSummary().hashCode());
        return result;
    }
}