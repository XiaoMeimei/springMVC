package com.ivy.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ivy.dao.UserDao;
import com.ivy.entity.User;
import com.ivy.entity.UserInfo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
    private boolean enableAuthorities = true;
	
    protected void addCustomAuthorities(String username, List<GrantedAuthority> authorities) {}
    
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 // -- mini-web示例中无以下属性, 暂时全部设为true. --//  
		  boolean enabled = true;  
		  boolean accountNonExpired = true;  
		  boolean credentialsNonExpired = true;  
		  boolean accountNonLocked = true;  
		
		User user = userDao.getUserByUsername(username);

        if (user == null) {
        	throw new UsernameNotFoundException("用户" + username + " 不存在");
        }

        Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();

        if (enableAuthorities) {
        	for (String roleName : userDao.getAuthorityList(user.getUsername()).split(",")) {
        		dbAuthsSet.add(new SimpleGrantedAuthority(roleName));
        	}
        }

        List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);

        addCustomAuthorities(user.getUsername(), dbAuths);

        if (dbAuths.size() == 0) {
            throw new UsernameNotFoundException("User"+ username +"has no GrantedAuthority");
        }

        UserInfo userdetails = new UserInfo(user.getUsername(), user.getPassword(), user.getIsvip(), user.getComment(),
        		accountNonExpired, accountNonLocked, credentialsNonExpired, enabled,dbAuths);  
        
        return userdetails;
	}

}
