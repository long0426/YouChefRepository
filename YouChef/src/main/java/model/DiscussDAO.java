package model;

import java.util.List;

public interface DiscussDAO {
	DiscussBean select(DiscussBean bean);
	
	int insert(DiscussBean bean);

	List<DiscussBean> selectAll();

	DiscussBean update(DiscussBean bean);
	
	List<DiscussBean> discussAll(Integer essayBean);
	
	boolean delete(int discuss, String string);
}
