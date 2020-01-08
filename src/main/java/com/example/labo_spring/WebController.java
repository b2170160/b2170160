
package com.example.labo_spring;

        import com.example.labo_spring.labo.IndexService;
        import com.example.labo_spring.labo.LaboService;
        import com.example.labo_spring.model.model.AuthnRepository;
        import com.example.labo_spring.model.model.SetumeiService;
        import com.example.labo_spring.model.model.SignService;
        import com.example.labo_spring.model.model.RetrospectService;
        import com.example.labo_spring.labo.Labo;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private SetumeiService setumeiService;

    @GetMapping("test")
    @ResponseBody
    public String test(Model model) {
        return LocalDateTime.now().toString();
    }


    @GetMapping("SignIn")
    public String signin(Model model) { return "signin"; }

   @PostMapping("Signed")
   public String signed(Model model, String id, String pass) {
     boolean m = sign.doSignIn(id, pass);

     System.out.println(m);

      if (m) {
           model.addAttribute("userId", id);
          System.out.println("利用中のブラウザ識別番号:" + httpSession.getId());
          httpSession.setAttribute("userId", id);
           return "signed";
      } else {
         return "signin";
     }
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


    @PostMapping("toukou")
    public String toukou(Model model, String id, String pass){

        System.out.println("aaa");
        var user_page = (String) httpSession.getAttribute("user_page");

        if(!user_page.equals(id)){
            return "signin";
        }

        boolean m = sign.doSignIn(id, pass);
      if (m) {
          model.addAttribute("studentId",user_page);
           model.addAttribute("userId", id);
           System.out.println("利用中のブラウザ識別番号:" + httpSession.getId());
           httpSession.setAttribute("userId", id);

          var koe = setumeiService.findvoice(user_page);
          var syousai = setumeiService.findsyousai(user_page);

          model.addAttribute("syousai",syousai.getSyousai());
          model.addAttribute("koe",koe.getKoe());

          return "toukou";
       } else {
          return "signin";
      }

    }

    @PostMapping("kanryou")
    public String kanryou(Model model,String syousai, String koe){

        var userId = (String) httpSession.getAttribute("user_page");


        setumeiService.setumeiupdate(syousai,koe,userId);

        System.out.println(syousai);
        System.out.println(koe);


        httpSession.invalidate();

        return "kanryou";

    }



    @RequestMapping("/")
    public String index(Model model){

        var userName = "ゲスト";

        var classA = indexService.laboEachClass("A");
        model.addAttribute("classA",classA);
        var classB = indexService.laboEachClass("B");
        model.addAttribute("classB",classB);
        var classC = indexService.laboEachClass("C");
        model.addAttribute("classC",classC);

        model.addAttribute("userName",userName);
        return "index";
    }
    @RequestMapping("/{laboId}")
    public String labo(Model model,@PathVariable("laboId") String laboId){
        Labo labo = laboService.findById(laboId);
        model.addAttribute("laboname",labo.getLaboName());
        var students = laboService.StudentAll(laboId);
        model.addAttribute("laboId",laboId);
        model.addAttribute("students",students);

        System.out.println(labo);

        return "laboview";
    }
    @RequestMapping(value="/students/id",method=RequestMethod.GET)
    public String studentget(Model model, @RequestParam("id") String student){

        httpSession.setAttribute("user_page", student);


        model.addAttribute("studentId",student);


        var LaboId = laboService.findraboId(student);
        String laboId = LaboId.getLaboId();

        Labo labo = laboService.findById(laboId);
        model.addAttribute("laboId",laboId);
        var students = laboService.findname(student);

        model.addAttribute("laboname",labo.getLaboName());
        model.addAttribute("students",students.getName());


        var retrospectives = service.findAll(student);
        model.addAttribute("retrospectives", retrospectives);

        var koe = setumeiService.findvoice(student);
        var syousai = setumeiService.findsyousai(student);

        model.addAttribute("syousai",syousai.getSyousai());
        model.addAttribute("koe",koe.getKoe());


        System.out.println(students.getName());
        System.out.println(laboId);
        System.out.println(labo.getLaboName());
        System.out.println(student);



        return "kozin";
    }

    @RequestMapping(value="/students/id",method=RequestMethod.POST)
    public String studentpost(Model model, @RequestParam("id") String student,String text){

        model.addAttribute("studentId",student);


        service.register(text,student);



        var LaboId = laboService.findraboId(student);
        String laboId = LaboId.getLaboId();
        model.addAttribute("laboId",laboId);
        Labo labo = laboService.findById(laboId);

        var students = laboService.findname(student);

        model.addAttribute("laboname",labo.getLaboName());
        model.addAttribute("students",students.getName());


        var retrospectives = service.findAll(student);
        model.addAttribute("retrospectives", retrospectives);

        var koe = setumeiService.findvoice(student);
        var syousai = setumeiService.findsyousai(student);

        model.addAttribute("syousai",syousai.getSyousai());
        model.addAttribute("koe",koe.getKoe());



        return "kozin";
    }

}