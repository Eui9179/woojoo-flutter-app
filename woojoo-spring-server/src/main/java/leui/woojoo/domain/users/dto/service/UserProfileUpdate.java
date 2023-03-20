package leui.woojoo.domain.users.dto.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileUpdate {
    private String profileImageName;
    private String name;
    private String groupName;
    private String groupDetail;
}