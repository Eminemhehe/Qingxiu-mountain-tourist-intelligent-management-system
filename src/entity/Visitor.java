/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.Date;
/**
 *
 * @author Administrator
 */
public class Visitor {
    
    private int id;  //主键id
    private String name; //姓名
    private String idCardNum;  //身份证号码
    private String telNum;  //手机号
    private double ticketCardBalance;  //门票储值卡余额
    private String ticketType; //门票类型
    private String local;  //位置信息
    private String visitPlace;  //到过的景点
    private Date visitDate;  //到访时间
    private String reamrks;  //备注

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the idCardNum
     */
    public String getIdCardNum() {
        return idCardNum;
    }

    /**
     * @param idCardNum the idCardNum to set
     */
    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    /**
     * @return the telNum
     */
    public String getTelNum() {
        return telNum;
    }

    /**
     * @param telNum the telNum to set
     */
    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    /**
     * @return the ticketCardBalance
     */
    public double getTicketCardBalance() {
        return ticketCardBalance;
    }

    /**
     * @param ticketCardBalance the ticketCardBalance to set
     */
    public void setTicketCardBalance(double ticketCardBalance) {
        this.ticketCardBalance = ticketCardBalance;
    }

    /**
     * @return the ticketType
     */
    public String getTicketType() {
        return ticketType;
    }

    /**
     * @param ticketType the ticketType to set
     */
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * @return the visitPlace
     */
    public String getVisitPlace() {
        return visitPlace;
    }

    /**
     * @param visitPlace the visitPlace to set
     */
    public void setVisitPlace(String visitPlace) {
        this.visitPlace = visitPlace;
    }

    /**
     * @return the visitDate
     */
    public Date getVisitDate() {
        return visitDate;
    }

    /**
     * @param visitDate the visitDate to set
     */
    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * @return the reamrks
     */
    public String getReamrks() {
        return reamrks;
    }

    /**
     * @param reamrks the reamrks to set
     */
    public void setReamrks(String reamrks) {
        this.reamrks = reamrks;
    }

    
    




    
    
    
    
    
}
