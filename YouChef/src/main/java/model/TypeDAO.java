package model;

import java.util.List;

public interface TypeDAO {
	
	TypeBean select(int t_id);
	
	TypeBean insert(TypeBean typeBean);
	
	List<TypeBean> select();
	
}
