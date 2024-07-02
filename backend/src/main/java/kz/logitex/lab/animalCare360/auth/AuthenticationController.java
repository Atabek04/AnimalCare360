package kz.logitex.lab.animalCare360.auth;

import jakarta.servlet.http.HttpServletResponse;
import kz.logitex.lab.animalCare360.entity.User;
import kz.logitex.lab.animalCare360.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/profile")
    public User getUserProfile(Principal principal) {
        return userService.getUser(principal.getName());
    }

    @PutMapping("/profile")
    public User updateUserProfile(Principal principal, @RequestBody User user) {
        return userService.updateUser(principal.getName(), user);
    }

    @GetMapping("/oauth2/login/google")
    public void redirectToGoogleOAuth2Login(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

    @GetMapping("/oauth2/login/github")
    public void redirectToGithubOAuth2Login(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/github");
    }
}