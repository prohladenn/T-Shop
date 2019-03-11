package com.example.tshop.t_shop;

import com.google.firebase.firestore.DocumentReference;

import java.sql.Timestamp;

public class Order {
    private Long count;
    private Timestamp dateBegin;
    private Timestamp dateCompletion;
    private String orderNumber;
    private DocumentReference orderRef;
    private String status;
    private String type;
    private String contactPhone;
    private String userName;
    private DocumentReference userRef;

    protected Order(Long count,
                    Timestamp dateBegin,
                    Timestamp dateCompletion,
                    String orderNumber,
                    DocumentReference orderRef,
                    String status,
                    String type,
                    String contactPhone,
                    String userName,
                    DocumentReference userRef) {
        this.count = count;
        this.dateBegin = dateBegin;
        this.dateCompletion = dateCompletion;
        this.orderNumber = orderNumber;
        this.orderRef = orderRef;
        this.status = status;
        this.type = type;
        this.contactPhone = contactPhone;
        this.userName = userName;
        this.userRef = userRef;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Timestamp getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Timestamp dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Timestamp getDateCompletion() {
        return dateCompletion;
    }

    public void setDateCompletion(Timestamp dateCompletion) {
        this.dateCompletion = dateCompletion;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public DocumentReference getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(DocumentReference orderRef) {
        this.orderRef = orderRef;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public DocumentReference getUserRef() {
        return userRef;
    }

    public void setUserRef(DocumentReference userRef) {
        this.userRef = userRef;
    }
}
