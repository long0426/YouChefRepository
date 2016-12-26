package model;


import java.util.List;

public interface ChefDAO {
	
	ChefBean select (int c_id);
	
	List<ChefBean> select();
	
	ChefBean insert(ChefBean bean);
	
	ChefBean update(ChefBean bean);
	
	List<ChefBean> selectChefByType(int t_id);

}


