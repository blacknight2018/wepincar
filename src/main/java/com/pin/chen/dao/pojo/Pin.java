package com.pin.chen.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * pin
 * @author 
 */
public class Pin implements Serializable {
    private Integer pinid;

    private Integer reqnum;

    private String passenger;

    private Date stoptime;

    private Date createtime;

    private Integer price;

    private String carnumber;

    private Boolean cancel;

    private String senderOpenid;

    private String driverOpenid;

    private String fromcity;

    private String tocity;

    private String senderMobile;

    private static final long serialVersionUID = 1L;

    public Integer getPinid() {
        return pinid;
    }

    public void setPinid(Integer pinid) {
        this.pinid = pinid;
    }

    public Integer getReqnum() {
        return reqnum;
    }

    public void setReqnum(Integer reqnum) {
        this.reqnum = reqnum;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public Date getStoptime() {
        return stoptime;
    }

    public void setStoptime(Date stoptime) {
        this.stoptime = stoptime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }

    public String getSenderOpenid() {
        return senderOpenid;
    }

    public void setSenderOpenid(String senderOpenid) {
        this.senderOpenid = senderOpenid;
    }

    public String getDriverOpenid() {
        return driverOpenid;
    }

    public void setDriverOpenid(String driverOpenid) {
        this.driverOpenid = driverOpenid;
    }

    public String getFromcity() {
        return fromcity;
    }

    public void setFromcity(String fromcity) {
        this.fromcity = fromcity;
    }

    public String getTocity() {
        return tocity;
    }

    public void setTocity(String tocity) {
        this.tocity = tocity;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
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
        Pin other = (Pin) that;
        return (this.getPinid() == null ? other.getPinid() == null : this.getPinid().equals(other.getPinid()))
            && (this.getReqnum() == null ? other.getReqnum() == null : this.getReqnum().equals(other.getReqnum()))
            && (this.getPassenger() == null ? other.getPassenger() == null : this.getPassenger().equals(other.getPassenger()))
            && (this.getStoptime() == null ? other.getStoptime() == null : this.getStoptime().equals(other.getStoptime()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getCarnumber() == null ? other.getCarnumber() == null : this.getCarnumber().equals(other.getCarnumber()))
            && (this.getCancel() == null ? other.getCancel() == null : this.getCancel().equals(other.getCancel()))
            && (this.getSenderOpenid() == null ? other.getSenderOpenid() == null : this.getSenderOpenid().equals(other.getSenderOpenid()))
            && (this.getDriverOpenid() == null ? other.getDriverOpenid() == null : this.getDriverOpenid().equals(other.getDriverOpenid()))
            && (this.getFromcity() == null ? other.getFromcity() == null : this.getFromcity().equals(other.getFromcity()))
            && (this.getTocity() == null ? other.getTocity() == null : this.getTocity().equals(other.getTocity()))
            && (this.getSenderMobile() == null ? other.getSenderMobile() == null : this.getSenderMobile().equals(other.getSenderMobile()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPinid() == null) ? 0 : getPinid().hashCode());
        result = prime * result + ((getReqnum() == null) ? 0 : getReqnum().hashCode());
        result = prime * result + ((getPassenger() == null) ? 0 : getPassenger().hashCode());
        result = prime * result + ((getStoptime() == null) ? 0 : getStoptime().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCarnumber() == null) ? 0 : getCarnumber().hashCode());
        result = prime * result + ((getCancel() == null) ? 0 : getCancel().hashCode());
        result = prime * result + ((getSenderOpenid() == null) ? 0 : getSenderOpenid().hashCode());
        result = prime * result + ((getDriverOpenid() == null) ? 0 : getDriverOpenid().hashCode());
        result = prime * result + ((getFromcity() == null) ? 0 : getFromcity().hashCode());
        result = prime * result + ((getTocity() == null) ? 0 : getTocity().hashCode());
        result = prime * result + ((getSenderMobile() == null) ? 0 : getSenderMobile().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pinid=").append(pinid);
        sb.append(", reqnum=").append(reqnum);
        sb.append(", passenger=").append(passenger);
        sb.append(", stoptime=").append(stoptime);
        sb.append(", createtime=").append(createtime);
        sb.append(", price=").append(price);
        sb.append(", carnumber=").append(carnumber);
        sb.append(", cancel=").append(cancel);
        sb.append(", senderOpenid=").append(senderOpenid);
        sb.append(", driverOpenid=").append(driverOpenid);
        sb.append(", fromcity=").append(fromcity);
        sb.append(", tocity=").append(tocity);
        sb.append(", senderMobile=").append(senderMobile);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}