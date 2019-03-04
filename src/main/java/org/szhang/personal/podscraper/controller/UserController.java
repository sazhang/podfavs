package org.szhang.personal.podscraper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Used by React to 1) find out if a user is authenticated and 2) perform global logout.
 */
@RestController
@RequestMapping("/api")
public class UserController {

  private ClientRegistration registration;
  private final UserService userService;

  @Autowired
  public UserController(ClientRegistrationRepository registrations, UserService userService) {
    this.registration = registrations.findByRegistrationId("okta");
    this.userService = userService;
  }

  @GetMapping("/user")
  public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
    if (user == null) {
      return new ResponseEntity<>("", HttpStatus.OK);
    } else {
      return ResponseEntity.ok().body(user.getAttributes());
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request,
                                  @AuthenticationPrincipal(expression = "idToken") OidcIdToken idToken) {
    // send logout URL to client so they can initiate logout
    String logoutUrl = this.registration.getProviderDetails()
        .getConfigurationMetadata().get("end_session_endpoint").toString();

    Map<String, String> logoutDetails = new HashMap<>();
    logoutDetails.put("logoutUrl", logoutUrl);
    logoutDetails.put("idToken", idToken.getTokenValue());
    request.getSession(false).invalidate();
    return ResponseEntity.ok().body(logoutDetails);
  }

  @GetMapping("/mypodcasts")
  public Collection<Podcast> getMySavedPodcasts(@PathVariable(value = "id") Long id) {
    return userService.getMySavedPodcasts(id);
  }
}