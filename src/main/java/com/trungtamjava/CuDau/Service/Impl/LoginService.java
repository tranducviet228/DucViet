package com.trungtamjava.CuDau.Service.Impl;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trungtamjava.CuDau.Dao.UserDao;
import com.trungtamjava.CuDau.Dto.UserPrincipal;
import com.trungtamjava.CuDau.Entity.UserEntity;

@Service
@Transactional
public class LoginService implements UserDetailsService{
	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity= userDao.getbyName(username);
		if (userEntity==null) {
			throw new UsernameNotFoundException("not found");
		}
		//convert role sang security
		String role= userEntity.getRole();
		List<SimpleGrantedAuthority> authorities= new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role) );
		
		UserPrincipal userPrincipal= new UserPrincipal(userEntity.getUsername(),userEntity.getPassword(),true, true, true, true, authorities);	
	    userPrincipal.setId(userEntity.getId());
	    userPrincipal.setName(userEntity.getName());
	    userPrincipal.setEmail(userEntity.getEmail());
	    userPrincipal.setPhone(userEntity.getPhone());
	    userPrincipal.setRole(userEntity.getRole());
		return userPrincipal;
	}

}
