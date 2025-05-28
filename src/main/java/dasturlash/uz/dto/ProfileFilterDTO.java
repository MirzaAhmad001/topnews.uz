package dasturlash.uz.dto;

import dasturlash.uz.enums.ProfileRole;

import java.time.LocalDateTime;
import java.util.List;

public class ProfileFilterDTO {
    private String name;
    private String surname;
    private String userName;
    private List<ProfileRole> roles;
    private LocalDateTime createdDateFrom;
    private LocalDateTime createdDateTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ProfileRole> getRoles() {
        return roles;
    }

    public void setRoles(List<ProfileRole> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreatedDateFrom() {
        return createdDateFrom;
    }

    public void setCreatedDateFrom(LocalDateTime createdDateFrom) {
        this.createdDateFrom = createdDateFrom;
    }

    public LocalDateTime getCreatedDateTo() {
        return createdDateTo;
    }

    public void setCreatedDateTo(LocalDateTime createdDateTo) {
        this.createdDateTo = createdDateTo;
    }
}
