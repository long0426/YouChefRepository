package model;


import java.util.List;

public interface ChefDAO {
	
	ChefBean select (int c_id);
	
	List<ChefBean> select();
	
	ChefBean insert(ChefBean bean);
	
	ChefBean update(int c_id, String fisrtName, String lastName,  String sex, String phone, 
            String address, String c_status,String background,byte[] photo,Integer years);
	
	boolean delete(int c_id);
}


