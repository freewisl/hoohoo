package com.example.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Member;
import com.example.repository.MemberRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	MemberRepository mRepository;
	
	// 로그인을 누르면 아이디/ 암호가 전달됨
	// 아이디를 이용해서 로그인 사용자의 정보를 가져온 후 UserDetails로 리턴하면 security가 비교해서 로그인 처리
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Member obj = mRepository.findById(id);
		
		if(obj == null) {
			throw new UsernameNotFoundException(id);
		}
		
		// 가져온 권한정보를 문자열 배열로 만들기
		String[] strRoles = {obj.getRole()};
		
		// 문자열 배열을 Collection<GrantedAuthority>타입으로 바꾸기
		Collection<GrantedAuthority> roles = AuthorityUtils.createAuthorityList(strRoles);
		
		
		// 가져온 정보에서 아이디, 암호, 권한을 리턴함.
		// 세션에서 아이디와 권한만 + 이름, 가입일자
		return new MyUser(obj.getId(), obj.getPw(), roles, obj.getName());
	}
}
