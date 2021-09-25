package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User myUser = userDao.getUserByName(s);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + s);
        }
        return new org.springframework.security.core.userdetails.User(myUser.getUsername(),
                myUser.getPassword(),
                myUser.isEnabled(),
                myUser.isAccountNonExpired(),
                myUser.isCredentialsNonExpired(),
                myUser.isAccountNonLocked(),
                myUser.getAuthorities());
    }
}