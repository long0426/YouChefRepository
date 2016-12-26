package model;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

public class VenueService {


	
	@Autowired
	private VenueDAO venueDAO;
	
	public VenueBean insertVenue(VenueBean bean){
		VenueBean result = null;
		if (bean != null) {
			result = venueDAO.insert(bean);	
			}
		return result;
		
	}
	
}
