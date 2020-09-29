package gradle_spring_db_study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ChangePasswordService {
    private MemberDao memberDao;

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    @Transactional
    public void changePassword(String email, String oldPwd, String newPwd) {
//        Member member = memberDao.selectByEmail(email);
//        if (member == null) {
//            throw new MemberNotFoundException();
//        }
//        member.changePassword(oldPwd, newPwd);
//        memberDao.update(member);
//    }
        try {
        	Member member = memberDao.selectByEmail(email);
        	member.changePassword(oldPwd, newPwd);
        	memberDao.update(member);
        }catch (EmptyResultDataAccessException e) {
        	throw new MemberNotFoundException();
        }
    }
}
    
