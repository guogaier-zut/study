package entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Order {
    private int id;
    private String SN;
    private int areaid;
    private String building;
    private String floor;
    private int equipmentid;
    private String equipmentname;
    private String equipmenttype;
    private int userid;
    private String username;
    private String content;//报修描述
    private String imgurl;
    private String location;
    /** 表单状态 0报修 1 受理 2 完成*/
    private int status;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;
    private int workerid;
    private String workername;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date accepttime;
    private String tip; //维修描述
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;
    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public int getAreaid() {
        return areaid;
    }

    public void setAreaid(int areaid) {
        this.areaid = areaid;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getEquipmentid() {
        return equipmentid;
    }

    public void setEquipmentid(int equipmentid) {
        this.equipmentid = equipmentid;
    }

    public String getEquipmentname() {
        return equipmentname;
    }

    public void setEquipmentname(String equipmentname) {
        this.equipmentname = equipmentname;
    }

    public String getEquipmenttype() {
        return equipmenttype;
    }

    public void setEquipmenttype(String equipmenttype) {
        this.equipmenttype = equipmenttype;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return imgurl;
    }

    public void setImg(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public int getWorkerid() {
        return workerid;
    }

    public void setWorkerid(int workerid) {
        this.workerid = workerid;
    }

    public String getWorkername() {
        return workername;
    }

    public void setWorkername(String workername) {
        this.workername = workername;
    }

    public Date getAccepttime() {
        return accepttime;
    }

    public void setAccepttime(Date accepttime) {
        this.accepttime = accepttime;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Order(String SN, int areaid, int equipmentid, int userid, String imgurl, String location, int status, Date starttime, String content) {
        this.SN = SN;
        this.areaid = areaid;
        this.equipmentid = equipmentid;
        this.userid = userid;
        this.imgurl = imgurl;
        this.location = location;
        this.status = status;
        this.starttime = starttime;
        this.content = content;
    }

    public Order(int id, String SN, int areaid, int equipmentid, int userid, String content, String imgurl, String location, Date starttime) {
        this.id = id;
        this.SN = SN;
        this.areaid = areaid;
        this.equipmentid = equipmentid;
        this.userid = userid;
        this.content = content;
        this.imgurl = imgurl;
        this.location = location;
        this.starttime = starttime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "SN='" + SN + '\'' +
                ", areaid=" + areaid +
                ", equipmentid=" + equipmentid +
                ", userid=" + userid +
                ", content='" + content + '\'' +
                ", location='" + location + '\'' +
                ", imgurl='"+ imgurl+'\''+
                ", status=" + status +
                ", starttime=" + starttime +
                '}';
    }
}
