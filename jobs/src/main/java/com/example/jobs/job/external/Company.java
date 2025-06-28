package com.example.jobs.job.external;


public class Company {
    private Long companyId;
    private String companyName;
    private String companyDescription;

    public Company() {
    }

    public Company(Long companyId, String companyName, String companyDescription) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyDescription = companyDescription;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }
}
