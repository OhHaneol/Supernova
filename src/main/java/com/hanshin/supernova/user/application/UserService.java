package com.hanshin.supernova.user.application;

import com.hanshin.supernova.exception.dto.ErrorType;
import com.hanshin.supernova.exception.user.UserRegisterInvalidException;
import com.hanshin.supernova.user.domain.User;
import com.hanshin.supernova.user.dto.request.UserRegisterRequest;
import com.hanshin.supernova.user.dto.response.UserRegisterResponse;
import com.hanshin.supernova.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserRegisterResponse register(UserRegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new UserRegisterInvalidException(ErrorType.EMAIL_DUPLICATE_ERROR);
        }

        if (userRepository.existsByNickname(request.getNickname())) {
            throw new UserRegisterInvalidException(ErrorType.NICKNAME_DUPLICATE_ERROR);
        }

        // 추가적인 비밀번호 유효성 검사 로직 구현 가능

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .nickname(request.getNickname())
                .build();

        User savedUser = userRepository.save(user);

        return new UserRegisterResponse(savedUser.getId(), savedUser.getNickname());
    }
}
