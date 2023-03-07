package leui.woojoo.service.user_groups;

import leui.woojoo.domain.user_groups.UserGroupsRepository;
import leui.woojoo.web.dto.groups.SignupGroupsParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserGroupsService {

    private final UserGroupsRepository userGroupsRepository;

    public Long save(SignupGroupsParam signupGroupsParam) {
        return userGroupsRepository.save(signupGroupsParam.toEntity()).getId();
    }
}