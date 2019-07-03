import com.ningdali.HelloWorld;
import com.ningdali.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @RunWith-----说明是spring环境
 * @SpringBootTest-----将入口类传入即可
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloWorld.class)

public class HelloWorldTest {

    //将person自动注入
    @Autowired
    Person person;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void fun1(){
        //输出一下person的值，在控制台查看结果
        System.out.println(person);
    }

    @Test
    public void fun2(){
        boolean helloService = applicationContext.containsBean("helloService");
        System.out.println(helloService);
    }
}
