package com.top.effitopia.service;

import com.top.effitopia.domain.Member;
import com.top.effitopia.mapper.MemberMapper;
import com.top.effitopia.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.selectOneByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username));
        return new CustomUserDetails(member);
    }
}
