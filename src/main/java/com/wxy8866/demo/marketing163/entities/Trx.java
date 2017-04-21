package com.wxy8866.demo.marketing163.entities;

public class Trx
{
    private Integer id;

    private Integer contentid;

    private Integer personid;

    private Integer price;

    private Long time;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getContentid()
    {
        return contentid;
    }

    public void setContentid(Integer contentid)
    {
        this.contentid = contentid;
    }

    public Integer getPersonid()
    {
        return personid;
    }

    public void setPersonid(Integer personid)
    {
        this.personid = personid;
    }

    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }

    public Long getTime()
    {
        return time;
    }

    public void setTime(Long time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", contentid=").append(contentid);
        sb.append(", personid=").append(personid);
        sb.append(", price=").append(price);
        sb.append(", time=").append(time);
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
        Trx other = (Trx) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getContentid() == null ? other.getContentid() == null : this.getContentid().equals(other.getContentid()))
                && (this.getPersonid() == null ? other.getPersonid() == null : this.getPersonid().equals(other.getPersonid()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContentid() == null) ? 0 : getContentid().hashCode());
        result = prime * result + ((getPersonid() == null) ? 0 : getPersonid().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        return result;
    }
}