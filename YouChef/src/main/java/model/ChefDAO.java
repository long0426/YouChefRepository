package model;


import java.util.List;

public interface ChefDAO {
	
	ChefBean selectChefById (int c_id);
	
	List<ChefBean> getAll();
	
	ChefBean insert(ChefBean bean);
	
	ChefBean update(ChefBean bean);
	
	List<ChefBean> selectChefByType(int t_id);
	

	
	

}


