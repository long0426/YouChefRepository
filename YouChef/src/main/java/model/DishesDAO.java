package model;

import java.util.List;

public interface DishesDAO {
	
	DishesBean insert(DishesBean dishesBean);	
	
	List<DishesBean> selectAllDishes();
	
	DishesBean selectDishById(int d_id);
	
	List<Object[]> selectDishesByType(int t_id);
	
	DishesBean update(String d_name,String d_briefing,double price,String menu,TypeBean typeBean,
			String d_status);
	
	List<Object[]> selectDishesByMchef (int mc_id);
	
	List<Object[]> selectMenuByType(int t_id);

	DishesBean updatedish(int d_id,String d_name, String d_briefing, double price, TypeBean typeBean, String d_status);
	
}
