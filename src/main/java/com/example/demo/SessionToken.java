package com.example.demo;

public class SessionToken {
    private String guid;
    private String VATNumber;
    private String productType;
    private String subjectId;
    private long created;

    @Override
    public String toString() {
        return "SessionToken{" +
                "guid='" + guid + '\'' +
                ", VATNumber='" + VATNumber + '\'' +
                ", productType='" + productType + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", created=" + created +
                '}';
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getVATNumber() {
        return VATNumber;
    }

    public void setVATNumber(String VATNumber) {
        this.VATNumber = VATNumber;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
