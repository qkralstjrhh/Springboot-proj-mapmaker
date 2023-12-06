package com.proj.mapmaker.mapper;

import com.proj.mapmaker.user.model.KakaoDTO;
import com.proj.mapmaker.user.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    public int kakaoUserChk(KakaoDTO kakaoDTO);

    public UserDTO kakaoLogin(KakaoDTO kakaoDTO);

    public void insertByKakaoUser(KakaoDTO kakaoDTO);

}
