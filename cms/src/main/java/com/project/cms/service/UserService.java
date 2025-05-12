package com.project.cms.service;

import com.project.cms.entity.UserEntity;
import com.project.cms.exception.CustomException;
import com.project.cms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void checkEmailNotExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new CustomException("İstifadəçi artıq mövcuddur", "User already exists", "Bad request",
                    400, null);
        }
    }

    public UserEntity getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("İstifadəçi tapılmadı", "User not found", "Not found",
                        404, null));
    }
}
