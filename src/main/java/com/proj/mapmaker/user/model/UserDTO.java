package com.proj.mapmaker.user.model;

public class UserDTO {


    long uId;
    long kakaoId;
    String userId;
    String userPw;
    String userNick;
    String loginType;

    @Override
    public String toString() {
        return "UserDTO{" +
                "uId=" + uId +
                ", kakaoId=" + kakaoId +
                ", userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userNick='" + userNick + '\'' +
                ", loginType='" + loginType + '\'' +
                '}';
    }

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public long getKakaoId() {
        return kakaoId;
    }

    public void setKakaoId(long kakaoId) {
        this.kakaoId = kakaoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
