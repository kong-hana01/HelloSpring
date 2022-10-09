package Hello.HelloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // hello로 들어오면 해당 메소드를 호출한다.
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello!!");
        return "hello"; // 뷰리조버(viewResolver)가 화면을 찾아서 처리한다. resources에 있는 hello 템플릿 실행
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

   @GetMapping("hello-string")
   @ResponseBody // http에서의 바디에 데이터를 직접 넣는다 -> 문자가 그대로 반영된다..
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
   }

   @GetMapping("hello-api")
   @ResponseBody // 객체는 json 구조로 반환, HttpMessageConverter가 동작한다.
   public Hello helloApi(@RequestParam("name") String name) {
       Hello hello = new Hello();
       hello.setName(name);
       return hello;
   }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
