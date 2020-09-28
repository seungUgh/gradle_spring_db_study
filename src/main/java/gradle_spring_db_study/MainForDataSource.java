package gradle_spring_db_study;

import java.io.IOException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import gradle_spring_db_study.config.AppCtx;
import gradle_spring_db_study.spring.Member;
import gradle_spring_db_study.spring.MemberDao;

public class MainForDataSource {
    private static MemberDao memberDao;
    
    public static void main(String[] args) throws IOException {
        try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);){
            DataSource ds = ctx.getBean(DataSource.class);
            System.out.println(ds);
            
            memberDao = ctx.getBean(MemberDao.class);
            
            selectByEmail();
        }
    }

    private static void selectByEmail() {
        System.out.println("selectByEmail()");
        Member member = memberDao.selectByEmail("test@test.co.kr");
        System.out.printf("%d : %s : %s%n", member.getId(), member.getEmail(), member.getName());
    }

}
