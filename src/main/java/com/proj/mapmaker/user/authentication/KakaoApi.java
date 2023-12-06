package com.proj.mapmaker.user.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.mapmaker.user.model.KakaoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

@Service
public class KakaoApi {

    @Value("${kakao.client.id}")
    private String REST_API_KEY;

    @Value("${kakao.redirect.uri}")
    private String REDIRECT_URI;

    public String getAccessToken(String code){

        String reqUrl = "https://kauth.kakao.com/oauth/token";
        String access_token = "";
        try {
            URL url = new URL(reqUrl);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("grant_type=authorization_code");
            stringBuilder.append("&client_id="+REST_API_KEY);
            stringBuilder.append("&redirect_uri="+REDIRECT_URI);
            stringBuilder.append("&code="+code);
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();

            if(conn.getResponseCode() == 200){
                System.out.println("AccessToekn 요청 성공");
            }else {
                System.out.println("AccessToekn 요청 실패");
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line="";
            String result = "";

            while ((line = bufferedReader.readLine())!=null){
                result += line;
            }
            System.out.println("result : " + result);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result);
            access_token =  jsonNode.get("access_token").asText();
            System.out.println("access_token : " + access_token);

            bufferedWriter.close();
            bufferedReader.close();

            return access_token;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public KakaoDTO getKakaoUserInfo(String accessToken) {
        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        KakaoDTO kakaoDTO = new KakaoDTO();

        try {

            URL url = new URL(reqUrl);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " +accessToken);

            if(conn.getResponseCode() == 200){
                System.out.println("카카오 유저 정보가져오기 성공");
            }else {
                System.out.println("카카오 유저 정보가져오기 실패");
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line="";
            String result = "";

            while ((line = bufferedReader.readLine())!=null){
                result += line;
            }
            System.out.println("kakaoUserInfo : " + result);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result);
            Long kakaoId = jsonNode.get("id").asLong();
            String userNick = jsonNode.get("properties").get("nickname").asText();
            kakaoDTO.setKakaoId(kakaoId);
            kakaoDTO.setUserNick(userNick);

            bufferedReader.close();

            return kakaoDTO;
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
}
