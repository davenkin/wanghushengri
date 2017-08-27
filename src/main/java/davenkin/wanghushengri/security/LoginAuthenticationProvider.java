package davenkin.wanghushengri.security;

import davenkin.wanghushengri.registration.UserRepository;
import davenkin.wanghushengri.sms.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by yteng on 8/27/17.
 */

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    @Autowired
    public LoginAuthenticationProvider(PasswordEncoder encoder,
                                       UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phoneNumber = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        PrincipalUser user = userRepository.principalUserByPhoneNumber(PhoneNumber.of(phoneNumber)).orElseThrow(() -> new UsernameNotFoundException("User not found for: " + phoneNumber));


        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Phone number or Password not valid.");
        }

        return new UsernamePasswordAuthenticationToken(user.getUser(), null, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
