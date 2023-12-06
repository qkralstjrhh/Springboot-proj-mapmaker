package com.proj.mapmaker.user.controller;

import com.proj.mapmaker.user.authentication.KakaoApi;
import com.proj.mapmaker.user.model.KakaoDTO;
import com.proj.mapmaker.user.model.UserDTO;
import com.proj.mapmaker.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Value("${kakao.client.id}")
    private String REST_API_KEY;

    @Value("${kakao.redirect.uri}")
    private String REDIRECT_URI;

    @Autowired
    private KakaoApi kakaoApi;

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login(){

        return "/login";
    }

    @PostMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){

        return new ModelAndView();
    }

    /* ************카카오 로그인 및 회원 등록 Start**************/
    @GetMapping("/kakaoLogin")
    public ModelAndView kakaoLogin(String code, HttpServletRequest request) throws Exception{
        //카카오 인가 코드 redirect url : /kakaoLogin
        UserDTO userDTO = new UserDTO();
        String access_token = kakaoApi.getAccessToken(code);
        KakaoDTO kakaoDTO = kakaoApi.getKakaoUserInfo(access_token);
        userDTO = userService.kakaoLogin(kakaoDTO);
        System.out.println("컨트롤러@@@로그인 성공@@@@");
        System.out.println(userDTO.getKakaoId());
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        session.setAttribute("userDTO", userDTO);
        session.setMaxInactiveInterval(60 * 30);
        mav.addObject("userDTO", userDTO);
        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/requestAuthorizeCode")
    public String requestAuthorizeCode(String code) throws Exception {
        String authorizeUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="
                + REST_API_KEY + "&redirect_uri=" + REDIRECT_URI;

        return "redirect:" + authorizeUrl;
    }
    /**************카카오 로그인 및 회원 등록 End************/
}
