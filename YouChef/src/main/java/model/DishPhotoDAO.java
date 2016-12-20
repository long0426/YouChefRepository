package model;

import java.util.List;

public interface DishPhotoDAO {
	public DishPhotoBean insert(DishPhotoBean bean);
	public List<DishPhotoBean> selectByMcid(Integer mc_id);
	public List<DishPhotoBean> selectByDid(Integer d_id);
	public List<DishPhotoBean> selectByMcidAndDid(Integer mc_id, Integer d_id);
}
