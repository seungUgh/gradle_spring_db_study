package gradle_spring_db_study.config;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"gradle_spring_db_study.spring"})
public class AppCtx {
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("oracle.jdbc.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
        ds.setUsername("spring5");
        ds.setPassword("rootroot");
        ds.setInitialSize(2);
        ds.setMaxIdle(10);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true);//유휴 커넥션 검사
        ds.setMinEvictableIdleTimeMillis(100*60 * 3); //최소 유휴시간 3분
        ds.setTimeBetweenEvictionRunsMillis(1000 * 10); // 10초 주기
        return ds;
    }
}
