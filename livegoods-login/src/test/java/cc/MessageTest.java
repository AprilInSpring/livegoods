package cc;

import cc.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = LoginApplication.class)
@RunWith(SpringRunner.class)
public class MessageTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void test(){
        loginService.sendCode("13268014239");
    }
}
