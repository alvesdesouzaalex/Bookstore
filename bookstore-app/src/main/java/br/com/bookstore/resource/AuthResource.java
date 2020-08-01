package br.com.bookstore.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bookstore.repository.UserRepository;
import br.com.bookstore.security.AccountCredentialsVo;
import br.com.bookstore.security.jwt.JwtTokenProvider;


@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<Map<Object, Object>> signin(@RequestBody AccountCredentialsVo credentialsVo) {

        try {
            //TODO passar lógica para o service
            var username = credentialsVo.getUsername();
            var password = credentialsVo.getPassword();

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = this.userRepository.findByUserName(username);
            var token = this.jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> model = new HashMap<>();
            model.put("token", token);
            model.put("username", username);
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Invalid password/username supplied!");
        }
    }
}
