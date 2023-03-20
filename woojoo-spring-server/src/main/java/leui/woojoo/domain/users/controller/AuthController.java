package leui.woojoo.domain.users.controller;

import leui.woojoo.domain.users.entity.Users;
import leui.woojoo.jwt.JwtProvider;
import leui.woojoo.service.user_groups.UserGroupsService;
import leui.woojoo.domain.users.service.AuthService;
import leui.woojoo.domain.users.service.UsersService;
import leui.woojoo.domain.users.utils.UserUtils;
import leui.woojoo.utils.file.FileUtils;
import leui.woojoo.domain.users.dto.UserDetail;
import leui.woojoo.domain.users.dto.web.auth.LoginRequest;
import leui.woojoo.domain.users.dto.web.auth.LoginResponse;
import leui.woojoo.domain.users.dto.web.auth.SignupRequest;
import leui.woojoo.domain.users.dto.web.auth.SignupResponse;
import leui.woojoo.domain.users.dto.web.auth.FcmRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UsersService usersService;
    private final UserGroupsService userGroupsService;
    private final JwtProvider jwtProvider;
    private final FileUtils fileUtils;

    @PostMapping(value = "/signup", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<SignupResponse> signup(SignupRequest requestDto) throws IllegalStateException, IOException {

        if (authService.findByPhoneNumber(requestDto.getPhone_number()) != null) {
            return new ResponseEntity<>(new SignupResponse(), HttpStatus.CONFLICT);
        }

        String profileImageName = fileUtils.upload(requestDto.getFile(), "profile");
        Users users = authService.save(requestDto.toUserEntity(profileImageName));

        userGroupsService.save(requestDto.toUserGroupEntity(users));

        String token = jwtProvider.createToken(users.getId());
        return new ResponseEntity<>(new SignupResponse(token), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetail users = authService.findByPhoneNumber(loginRequest.getPhoneNumber());
        if (users == null) {
            return new ResponseEntity<>(new LoginResponse(), HttpStatus.UNAUTHORIZED);
        }

        if (!users.getFcmToken().equals(loginRequest.getFcmToken())) {
            authService.updateFcmToken(users.getId(), loginRequest.getFcmToken());
        }

        String token = jwtProvider.createToken(users.getId());
        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
    }

    @DeleteMapping("/withdrawal")
    @ResponseStatus(HttpStatus.OK)
    public String withdrawUser(@AuthenticationPrincipal User user) {
        String profileImageName = authService.deleteUser(Long.parseLong(user.getUsername()));
        fileUtils.delete(profileImageName, "profile");
        return "ok";
    }

    @PostMapping("/async-token")
    @ResponseStatus(HttpStatus.OK)
    public String asyncFcmToken(@AuthenticationPrincipal User user, @RequestBody FcmRequest fcmRequest) {
        usersService.asyncFcmToken(UserUtils.resolveUserId(user), fcmRequest.getFcmToken());
        return "ok";
    }
}