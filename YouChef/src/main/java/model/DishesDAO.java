package model;

import java.util.List;

public interface DishesDAO {
	
	DishesBean insert(DishesBean dishesBean);	
	
	List<DishesBean> selectAll();
	
	DishesBean select (int d_id);
	
	List<DishesBean> selectType(int t_id);
	
	DishesBean update(String d_name,String d_briefing,double price,String menu,TypeBean typeBean,
			String d_status);
	
	DishesBean update(DishesBean dishesBean);
	
	List<DishesBean> selectMchef (int mc_id);
	
}
