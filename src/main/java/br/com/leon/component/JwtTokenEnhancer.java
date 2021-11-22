package br.com.leon.component;

import br.com.leon.model.Manager;
import br.com.leon.model.User;
import br.com.leon.repository.ManagerRepository;
import br.com.leon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        Manager manager = managerRepository.findByAuthenticationEmail(oAuth2Authentication.getName());
        User user = userRepository.findByAuthenticationEmail(oAuth2Authentication.getName());

        Map<String, Object> map = new HashMap<>();

        if (manager == null) {
            map.put("authenticationType", "user");
            map.put("userName", user.getName());
            map.put("userId", user.getId());
        } else {
            map.put("authenticationType", "manager");
            map.put("managerName", manager.getName());
            map.put("managerId", manager.getId());
        }

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
        token.setAdditionalInformation(map);

        return oAuth2AccessToken;
    }
}