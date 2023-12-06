package com.proj.mapmaker.user.model;

public class UserDTO {


    long uId;
    long kakaoId;
    String mmId;
    String mmPw;
    String userNick;
    String loginType;

    @Override
    public String toString() {
        return "UserVo{" +
                "uId=" + uId +
                ", kakaoId=" + kakaoId +
                ", mmId='" + mmId + '\'' +
                ", mmPw='" + mmPw + '\'' +
                ", userNick='" + userNick + '\'' +
                ", login_type='" + loginType + '\'' +
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

    public String getMmId() {
        return mmId;
    }

    public void setMmId(String mmId) {
        this.mmId = mmId;
    }

    public String getMmPw() {
        return mmPw;
    }

    public void setMmPw(String mmPw) {
        this.mmPw = mmPw;
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
