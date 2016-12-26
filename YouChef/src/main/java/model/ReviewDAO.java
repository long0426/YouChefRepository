package model;

import java.util.List;

public interface ReviewDAO {
	ReviewBean select(ReviewBean bean);
	
//	ReviewBean selectMchefReview(Integer mc_id);
//	
//	ReviewBean selectChefReview(Integer c_id);
	
	int insert(ReviewBean bean);

	List<ReviewBean> selectAll();

	ReviewBean update(ReviewBean bean);

}
