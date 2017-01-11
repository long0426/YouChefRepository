package model;


import java.util.List;

public interface ChefDAO {
	
	ChefBean select (int c_id);
	
	List<ChefBean> select();
	
	ChefBean insert(ChefBean bean);
	
	ChefBean update(ChefBean bean);
	
	List<ChefBean> selectChefByType(Integer t_id);
	
	ChefBean update(int c_id, String fisrtName, String lastName, String sex, String phone, 
            String address,TypeBean typeBean,String c_status,
            String background,Integer years, byte[] photo);
}


