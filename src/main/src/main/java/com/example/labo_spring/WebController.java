package com.example.labo_spring;

import com.example.labo_spring.labo.IndexService;
import com.example.labo_spring.labo.LaboService;
import com.example.labo_spring.model.model.AuthnRepository;
import com.example.labo_spring.model.model.SignService;
import com.example.labo_spring.model.model.Authn;
import com.example.labo_spring.model.model.RetrospectService;
import com.example.labo_spring.labo.Index;
import com.example.labo_spring.labo.IndexService;
import com.example.labo_spring.labo.Labo;
import com.example.labo_spring.labo.LaboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @Autowired
    private IndexService indexService;
    @Autowired
    private LaboService laboService;

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
    @RequestMapping("/")
    public String index(Model model){
        var classA = indexService.laboEachClass("A");
        model.addAttribute("classA",classA);
        var classB = indexService.laboEachClass("B");
        model.addAttribute("classB",classB);
        var classC = indexService.laboEachClass("C");
        model.addAttribute("classC",classC);
        return "index";
    }
    @RequestMapping("/{laboId}")
    public String labo(Model model,@PathVariable("laboId") String laboId){
        Labo labo = laboService.findById(laboId);
        model.addAttribute("laboname",labo.getLaboName());
        var students = laboService.StudentAll(laboId);
        model.addAttribute("laboId",laboId);
        model.addAttribute("students",students);
        return "laboview";
    }
    @RequestMapping("/students/{studentId}")
    public String student(Model model,@PathVariable("studentId") String studentId){

        return "student";
    }

}