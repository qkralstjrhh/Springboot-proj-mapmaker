package com.proj.mapmaker.user.service;


import com.proj.mapmaker.user.model.KakaoDTO;
import com.proj.mapmaker.user.model.UserDTO;

import java.util.HashMap;

public interface UserService {

    public UserDTO kakaoLogin(KakaoDTO kakaoDTO) throws Exception;

    public UserDTO generalLogin(HashMap<String, String> logFrm) throws Exception;

}
