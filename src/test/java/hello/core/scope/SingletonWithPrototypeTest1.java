package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import static org.assertj.core.api.Assertions.
        *;
public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int x = clientBean1.logic();
        assertThat(x).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int y = clientBean2.logic();
        assertThat(y).isEqualTo(1);
    }

    static class ClientBean {
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;
        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}