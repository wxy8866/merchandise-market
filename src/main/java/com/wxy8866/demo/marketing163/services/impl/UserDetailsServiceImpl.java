package com.wxy8866.demo.marketing163.services.impl;

import com.wxy8866.demo.marketing163.entities.Person;
import com.wxy8866.demo.marketing163.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by wxy8866 on 2017/4/16.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private IPersonService personService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Person person = personService.findByUsername(username);

        String password = person.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(person.getUsertype().toString()));

        org.springframework.security.core.userdetails.User securedUser = new org.springframework.security.core.userdetails.User(
                username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        return securedUser;

    }
}
