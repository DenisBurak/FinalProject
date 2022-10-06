package com.zemelya.security;

import com.zemelya.domain.SystemRoles;
import com.zemelya.domain.hibernate.HibernateRole;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.role.RoleSpringDataRepository;
import com.zemelya.repository.user.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserSpringDataRepository userRepository;

    private final RoleSpringDataRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            /*Find user in DB*/
            Optional<HibernateUser> searchResult = userRepository.findByCredentialsLogin(username);

            if (searchResult.isPresent()) {
                HibernateUser user = searchResult.get();

                /*We are creating Spring Security User object*/

                return new org.springframework.security.core.userdetails.User(
                        user.getCredentials().getLogin(),
                        user.getCredentials().getPassword(),
//                        ["ROLE_USER", "ROLE_ADMIN"]
                        AuthorityUtils.commaSeparatedStringToAuthorityList(
                                roleRepository.findByUserId(user.getId())
                                        .stream()
                                        .map(HibernateRole::getRoleName)
                                        .map(SystemRoles::name)
                                        .collect(Collectors.joining(","))
                        )
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with login '%s'.", username));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }
}
