package com.pin.chen.dao.pojo;

import java.io.Serializable;

/**
 * pinext
 * @author 
 */
public class Pinext implements Serializable {
    private Integer pinid;

    private Byte pintype;

    private String comment;

    private Integer carblank;

    private static final long serialVersionUID = 1L;

    public Integer getPinid() {
        return pinid;
    }

    public void setPinid(Integer pinid) {
        this.pinid = pinid;
    }

    public Byte getPintype() {
        return pintype;
    }

    public void setPintype(Byte pintype) {
        this.pintype = pintype;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCarblank() {
        return carblank;
    }

    public void setCarblank(Integer carblank) {
        this.carblank = carblank;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Pinext other = (Pinext) that;
        return (this.getPinid() == null ? other.getPinid() == null : this.getPinid().equals(other.getPinid()))
            && (this.getPintype() == null ? other.getPintype() == null : this.getPintype().equals(other.getPintype()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
            && (this.getCarblank() == null ? other.getCarblank() == null : this.getCarblank().equals(other.getCarblank()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPinid() == null) ? 0 : getPinid().hashCode());
        result = prime * result + ((getPintype() == null) ? 0 : getPintype().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getCarblank() == null) ? 0 : getCarblank().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pinid=").append(pinid);
        sb.append(", pintype=").append(pintype);
        sb.append(", comment=").append(comment);
        sb.append(", carblank=").append(carblank);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}