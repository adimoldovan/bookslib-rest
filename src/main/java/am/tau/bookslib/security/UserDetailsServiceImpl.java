package am.tau.bookslib.security;

import am.tau.bookslib.model.UserAccount;
import am.tau.bookslib.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount applicationUserAccount = null;
        try {
            applicationUserAccount = userService.findByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (applicationUserAccount == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(applicationUserAccount.getUsername(), applicationUserAccount.getPassword(), emptyList());
    }
}
