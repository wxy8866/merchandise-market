package com.wxy8866.demo.marketing163.entities;

import java.util.Arrays;

public class ContentWithBLOBs extends Content
{
    private byte[] icon;

    private byte[] text;

    public byte[] getIcon()
    {
        return icon;
    }

    public void setIcon(byte[] icon)
    {
        this.icon = icon;
    }

    public byte[] getText()
    {
        return text;
    }

    public void setText(byte[] text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", icon=").append(icon);
        sb.append(", text=").append(text);
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
        ContentWithBLOBs other = (ContentWithBLOBs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getSummary() == null ? other.getSummary() == null : this.getSummary().equals(other.getSummary()))
                && (Arrays.equals(this.getIcon(), other.getIcon()))
                && (Arrays.equals(this.getText(), other.getText()));
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
        result = prime * result + (Arrays.hashCode(getIcon()));
        result = prime * result + (Arrays.hashCode(getText()));
        return result;
    }
}