/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.models;

import org.apache.commons.lang.builder.ToStringBuilder;
/**
 *
 * @author Maddy
 */
public class Employee {

    private int employeeId;
    private String empName;
    private String svcNum;
    private int rankId;
    private String cprNum;
    private int countryId;
    private String mobile;
    private String relContact;
    private String relRelation;
    private String bloodGroup;
    private String doj;
    private String address;
    private String maritalStatus;
    private String duty;
    private String lastRankDate;
    private String leaveDays;
    private String leaveType;
    private String empPic;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getSvcNum() {
        return svcNum;
    }

    public void setSvcNum(String svcNum) {
        this.svcNum = svcNum;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public String getCprNum() {
        return cprNum;
    }

    public void setCprNum(String cprNum) {
        this.cprNum = cprNum;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRelContact() {
        return relContact;
    }

    public void setRelContact(String relContact) {
        this.relContact = relContact;
    }

    public String getRelRelation() {
        return relRelation;
    }

    public void setRelRelation(String relRelation) {
        this.relRelation = relRelation;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getLastRankDate() {
        return lastRankDate;
    }

    public void setLastRankDate(String lastRankDate) {
        this.lastRankDate = lastRankDate;
    }

    public String getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(String leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getEmpPic() {
        return empPic;
    }

    public void setEmpPic(String empPic) {
        this.empPic = empPic;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
