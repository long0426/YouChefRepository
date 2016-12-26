package model;

import java.util.List;

public interface VenueDAO {
	VenueBean insert(VenueBean venueBean);

	VenueBean update(String v_name, String v_address, String v_status, Integer v_id);
	
	VenueBean findByPrimaryKey(Integer v_id);

	List<VenueBean> getAll();

}
