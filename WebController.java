package com.example.wsp.spring;

import com.example.wsp.spring.model.model.Authn;
import com.example.wsp.spring.model.model.AuthnRepository;
import com.example.wsp.spring.model.model.SignService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.wsp.spring.model.model.RetrospectService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;

@Controller
public class WebController {
    int login = 0;
    @Autowired
    private RetrospectService service;
    @Autowired
    private SignService sign;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private AuthnRepository repository;

    @GetMapping("test")
    @ResponseBody
    public String test(Model model) {
        return LocalDateTime.now().toString();
    }

    @GetMapping("GetPost")
    public String get(Model model) {
        return "retrospect";
    }

    @PostMapping("GetPost")
    public String post(String text, Model model) {
        var n = service.register(text);
        var retrospectives = service.findAll();
        model.addAttribute("retrospectives", retrospectives);
        return "retrospect";
    }

    @GetMapping("SignIn")
    public String signin(Model model) {
        return "signin";
    }

    @PostMapping("Signed")
    public String signed(Model model, String id, String pass) {
        boolean m = sign.doSignIn(id, pass);
        if (m) {
            model.addAttribute("userId", id);
            System.out.println("利用中のブラウザ識別番号:" + httpSession.getId());
            httpSession.setAttribute("userId", id);
            int login = 1;
            return "signed";
        } else {
            int login = 0;
            return "signin";
        }
    }

    @GetMapping("Profile")
    public String profile(Model model) {
        System.out.println("利用中のブラウザ識別番号:" + httpSession.getId());
        var userId = (String) httpSession.getAttribute("userId");
        model.addAttribute("userId", userId);
        var userName = (String) httpSession.getAttribute("userName");
        model.addAttribute("userName", sign.doName(userId));
        var userRole = (String) httpSession.getAttribute("userRole");
        model.addAttribute("userRole", sign.doRole(userId));
        return "profile";
    }

    @GetMapping("Signed")
    public String signed(Model model) {
        System.out.println("利用中のブラウザ識別番号:" + httpSession.getId());
        var userId = (String) httpSession.getAttribute("userId");
        model.addAttribute("userId", userId);
        return "signed";
    }

    @GetMapping("SignOut")
    public String signout(Model model) {
        System.out.println("利用中のブラウザ識別番号:" + httpSession.getId());
        var userId = (String) httpSession.getAttribute("userId");
        model.addAttribute("userId", userId);
        httpSession.invalidate();
        return "signin";
    }
}