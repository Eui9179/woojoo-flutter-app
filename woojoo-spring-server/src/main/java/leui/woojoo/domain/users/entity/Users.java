package leui.woojoo.domain.users.entity;

import jakarta.persistence.*;
import leui.woojoo.domain.BaseTimeEntity;
import leui.woojoo.domain.entity.authority.Authority;
import leui.woojoo.domain.entity.friends.Friends;
import leui.woojoo.domain.entity.today_games.TodayGames;
import leui.woojoo.domain.entity.user_games.UserGames;
import leui.woojoo.domain.entity.user_groups.UserGroups;
import leui.woojoo.domain.users.dto.UserSimple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Users extends BaseTimeEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

    @Column(length = 200)
    private String profileImageName;

    @Column(length = 200)
    private String fcmToken;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Friends> friends;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<UserGames> games;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<TodayGames> todayGames;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private UserGroups userGroups;

    public void asyncFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public UserSimple toProfile() {
        return UserSimple.builder()
                .userId(id)
                .name(name)
                .profileImageName(profileImageName)
                .build();
    }

    public void updateProfileImageName(String profileImageName) {
        this.profileImageName = profileImageName;
    }

    public void updateUserName(String name) {
        this.name = name;
    }
}