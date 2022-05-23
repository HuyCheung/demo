package com.example.demo.mapper;

import com.example.demo.dto.AuthUserDTO;

public interface AuthCustomizeMapper {
    AuthUserDTO authUserInfo(Long userId);
}