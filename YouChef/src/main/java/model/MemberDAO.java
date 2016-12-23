package model;

import java.util.List;

public interface MemberDAO {
	MemberBean select(int m_id);
	
	MemberBean select(String email);
	
	MemberBean insert(MemberBean bean);
	
	List<MemberBean> select();
	
	MemberBean update(int m_id, String fisrtName, String lastName, String nickName, String sex, String city, 
			                       String district, String address, String briefing, byte[] photo);
	
	boolean update(String password, int m_id);
}
