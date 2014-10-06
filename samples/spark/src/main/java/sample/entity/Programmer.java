package sample.entity;

import java.time.ZonedDateTime;
import java.util.Optional;

public class Programmer {

    private final Long id;
    private String gitHubName;
    private Optional<String> realName;
    private Optional<Long> companyId;
    private Optional<Company> company;
    private ZonedDateTime createdAt;

    public Programmer(Long id, String gitHubName, Optional<String> realName, Optional<Long> companyId, ZonedDateTime createdAt) {
        this.id = id;
        setGitHubName(gitHubName);
        setRealName(realName);
        setCompanyId(companyId);
        setCreatedAt(createdAt);
    }

    public Long getId() {
        return id;
    }

    public String getGitHubName() {
        return gitHubName;
    }

    public void setGitHubName(String gitHubName) {
        this.gitHubName = gitHubName;
    }

    public Optional<String> getRealName() {
        return realName;
    }

    public void setRealName(Optional<String> realName) {
        this.realName = realName;
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
