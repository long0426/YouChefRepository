package model;

import java.util.List;

public interface EssayDAO {
	EssayBean select(EssayBean bean);
	
	int insert(EssayBean bean);

	List<EssayBean> selectAll();

	EssayBean update(EssayBean bean);

}
