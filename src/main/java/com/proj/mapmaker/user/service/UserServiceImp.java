package com.proj.mapmaker.user.service;


import com.proj.mapmaker.mapper.UserMapper;
import com.proj.mapmaker.user.model.KakaoDTO;
import com.proj.mapmaker.user.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDTO kakaoLogin(KakaoDTO kakaoDTO) throws Exception {
        UserDTO userDTO = new UserDTO();
        System.out.println(kakaoDTO.getKakaoId());
        System.out.println(kakaoDTO.getUserNick());
        while (true){
            int resDuplicate = userMapper.kakaoUserChk(kakaoDTO);
            System.out.println("중복 체크 결과 = " + resDuplicate);

            if(resDuplicate >= 1){
                userDTO = userMapper.kakaoLogin(kakaoDTO);
                System.out.println("로그인@@@@@@");
                return userDTO;
            }else {
                userMapper.insertByKakaoUser(kakaoDTO);
                System.out.println("insert 실행");
            }
        }


    }
}
