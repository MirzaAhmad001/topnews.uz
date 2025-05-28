package dasturlash.uz.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import dasturlash.uz.enums.ProfileRole;
import jakarta.persistence.*;


@Entity
@Table(name = "profile_roles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false, nullable = false)
    private ProfileEntity profile;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ProfileRole role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProfileRole getRole() {
        return role;
    }

    public void setRole(ProfileRole role) {
        this.role = role;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }
}