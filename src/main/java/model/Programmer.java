package model;

import java.util.Optional;

public class Programmer {

    private final Long id;
    private String name;
    private Optional<Long> companyId = Optional.empty();
    private Optional<Company> company = Optional.empty();

    public Programmer(Long id, String name, Optional<Long> companyId) {
        this.id = id;
        setName(name);
        setCompanyId(companyId);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Long> getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Optional<Long> companyId) {
        this.companyId = companyId;
    }

    public Optional<Company> getCompany() {
        return company;
    }

    public void setCompany(Optional<Company> company) {
        this.company = company;
    }

}
