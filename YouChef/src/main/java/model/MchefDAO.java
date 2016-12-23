package model;

import java.util.List;

public interface MchefDAO {
	MchefBean select (MchefBean bean);

	int insert(MchefBean bean);
	
	List<MchefBean> selectAll();
	
	MchefBean update(MchefBean bean);
	
	MchefBean select(Integer mc_id);
}
