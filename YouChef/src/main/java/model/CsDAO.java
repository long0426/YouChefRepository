package model;

import java.util.List;

public interface CsDAO {
	CsBean insert(CsBean bean);
	
	CsBean select(int message_id);
	
	List<CsBean> select();
	
	boolean update(int message_id, String cs_status);
	
	List<CsBean> selectAll(int m_id);
}
