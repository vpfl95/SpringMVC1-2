package hello.SpringMVC12.basic.request;

import hello.SpringMVC12.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age =  Integer.parseInt(request.getParameter("age"));
        log.info("username ={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    @ResponseBody // "OK"문자를 바로 넘겨버림 @RestController 와 같음
    public String requestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge){
        log.info("username ={}, age={}", memberName, memberAge);
        return "ok";
    }
    /**
     * @RequestParam 사용
     * HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
     */
    @RequestMapping("/request-param-v3")
    @ResponseBody // "OK"문자를 바로 넘겨버림 @RestController 와 같음
    public String requestParamV3(@RequestParam String username, @RequestParam int age){
        log.info("username ={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam 사용
     * String, int 등의 단순 타입이면 @RequestParam 도 생략 가능
     */
    @RequestMapping("/request-param-v4")
    @ResponseBody // "OK"문자를 바로 넘겨버림 @RestController 와 같음
    public String requestParamV4(String username, int age){
        log.info("username ={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam.required
     * /request-param-required -> username이 없으므로 예외
     *
     * 주의!
     * /request-param-required?username= -> 빈문자로 통과
     *
     * 주의!
     * /request-param-required
     * int age -> null을 int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는
    defaultValue 사용)
     */
    @RequestMapping("/request-param-required")
    @ResponseBody // "OK"문자를 바로 넘겨버림 @RestController 와 같음
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age){
        log.info("username ={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-default")
    @ResponseBody // "OK"문자를 바로 넘겨버림 @RestController 와 같음
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") int age){
        log.info("username ={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-map")
    @ResponseBody // "OK"문자를 바로 넘겨버림 @RestController 와 같음
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username ={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){;
        log.info("hellodata = {}", helloData);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){;
        log.info("hellodata = {}", helloData);
        return "OK";
    }
}
