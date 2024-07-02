package kz.logitex.lab.animalCare360.auth;

import kz.logitex.lab.animalCare360.entity.Role;
import kz.logitex.lab.animalCare360.entity.User;
import kz.logitex.lab.animalCare360.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setFirstName(oAuth2User.getAttribute("given_name"));
                    newUser.setLastName(oAuth2User.getAttribute("family_name"));
                    newUser.setRole(Role.RESEARCHER);
                    return userRepository.save(newUser);
                });

        return new DefaultOAuth2User(
                user.getAuthorities(),
                oAuth2User.getAttributes(),
                "email"
        );
    }
}
