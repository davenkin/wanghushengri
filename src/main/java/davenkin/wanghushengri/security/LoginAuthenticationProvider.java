package davenkin.wanghushengri.security;

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

    public static final String AUTHENTICATION_FAILED_MESSAGE = "Authentication Failed. Invalid phone number or password.";
    private final PasswordEncoder encoder;

    private final CredentialUserRepository credentialUserRepository;

    @Autowired
    public LoginAuthenticationProvider(PasswordEncoder encoder,
                                       CredentialUserRepository credentialUserRepository) {
        this.encoder = encoder;
        this.credentialUserRepository = credentialUserRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PhoneNumber phoneNumber = (PhoneNumber) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        CredentialUser credentialUser = credentialUserRepository.principalUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException(AUTHENTICATION_FAILED_MESSAGE));


        if (!encoder.matches(password, credentialUser.getPassword())) {
            throw new BadCredentialsException(AUTHENTICATION_FAILED_MESSAGE);
        }

        return new UsernamePasswordAuthenticationToken(credentialUser.getUser(), null, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
