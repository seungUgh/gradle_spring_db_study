package gradle_spring_db_study;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import gradle_spring_db_study.config.AppCtx;
import gradle_spring_db_study.spring.Member;
import gradle_spring_db_study.spring.MemberDao;

public class MainForDataSource {
    private static MemberDao memberDao;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
    
    public static void main(String[] args) throws IOException {
        try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);){
            DataSource ds = ctx.getBean(DataSource.class);
            System.out.println(ds);
            
			
			  memberDao = ctx.getBean(MemberDao.class);
			  
//			  selectByEmail();
			  insertMember();
			  selectByAll();
//			  selectCount();
//			  selectByAll();
//			  updateMember();
////			  deleteMember();
//			  selectByAll();
			 
        }
    }

    private static void deleteMember() {
    	System.out.println("delete()");
    	Member member = memberDao.selectByEmail("test@test.co.kr");
    	memberDao.delete(member);
	}

	private static void updateMember() {
    	System.out.println("update()");
    	Member member = memberDao.selectByEmail("test@test.co.kr");
    	String oldPw = member.getPassword();
    	String newPw = Double.toHexString(Math.random());
    	member.changePassword(oldPw, newPw);
    	
    	memberDao.update(member);
    	System.out.println("member >> " + member);
    	System.out.println("암호 변경 : " + oldPw + " > " + newPw );
    	System.out.println(member.getId() + "데이터 삭제 ");
    	
    }

	private static void insertMember() {
		System.out.println("insertMember()");
		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member(prefix + "@test.co.kr", prefix, prefix, LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId() + "데이터 추가");
		
	}

	private static void selectCount() {
    	System.out.println("selectCount()");
    	int count = memberDao.count();
    	System.out.println("count() >> " + count);
    	
	}

	private static void selectByAll() {
		System.out.println("selectByAll()");
		List<Member> list = memberDao.selectAll();
		list.stream().forEach(System.out::println);
	}

	private static void selectByEmail() {
        System.out.println("selectByEmail()");
        Member member = memberDao.selectByEmail("test@test.co.kr");
        System.out.printf("%d : %s : %s%n", member.getId(), member.getEmail(), member.getName());
    }

}
