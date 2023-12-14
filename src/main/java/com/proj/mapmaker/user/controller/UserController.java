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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

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
    public String loginGet(){

        return "/login";
    }
    @PostMapping("/login")
    public ModelAndView loginPost(@RequestParam("userId") String userId,
                          @RequestParam("userPw") String userPw,
                          HttpSession session
                          ) throws Exception{

       ModelAndView mav = new ModelAndView();
        HashMap<String, String> logFrm = new HashMap<>();
        logFrm.put("userId", userId);
        logFrm.put("userPw", userPw);

        UserDTO userDTO = userService.generalLogin(logFrm);
        session.setAttribute("user", userDTO);
        session.setMaxInactiveInterval(60 * 30);

        if (userDTO == null){
            mav.addObject("logFail", "로그인 정보가 없습니다.");
            mav.setViewName("/login");
            return mav;
        }
        mav.addObject("logSuccess", "로그인 성공");
        mav.setViewName("/home");
        return mav;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
//    @PostMapping("/login")
//    public ModelAndView login(ModelAndView modelAndView){
//
//        return new ModelAndView();
//    }

    /* ************카카오 로그인 및 회원 등록 Start**************/
    @GetMapping("/kakaoLogin")
    public String kakaoLogin(String code, HttpSession session) throws Exception{
        //카카오 인가 코드 redirect url : /kakaoLogin
        ModelAndView mav = new ModelAndView();
        UserDTO userDTO = new UserDTO();
        String access_token = kakaoApi.getAccessToken(code);
        KakaoDTO kakaoDTO = kakaoApi.getKakaoUserInfo(access_token);

        userDTO = userService.kakaoLogin(kakaoDTO);
        session.setAttribute("user", userDTO);
        session.setMaxInactiveInterval(60 * 30);
        return "redirect:/";
    }

    @GetMapping("/requestAuthorizeCode")
    public String requestAuthorizeCode(String code) throws Exception {
        String authorizeUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="
                + REST_API_KEY + "&redirect_uri=" + REDIRECT_URI;
        return "redirect:" + authorizeUrl;
    }
    /**************카카오 로그인 및 회원 등록 End************/
}
