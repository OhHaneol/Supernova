package com.hanshin.supernova.user.dto.response;

import com.hanshin.supernova.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterResponse {

    private Long id;
    private String nickname;
}
