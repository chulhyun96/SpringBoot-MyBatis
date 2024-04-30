package com.example.Project1.web.service.member;

import com.example.Project1.web.entity.Member;
import com.example.Project1.web.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository repository;
    public Member login(String loginId, String password) {
         return repository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
