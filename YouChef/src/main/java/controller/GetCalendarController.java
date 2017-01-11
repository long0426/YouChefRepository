package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import model.CalendarBean;
import model.CalendarService;
import model.misc.CalendarDTO;
import model.misc.LeaveBean;

@Controller
@RequestMapping(path = { "/pages/getcalendar.controller" })
public class GetCalendarController {
	@Autowired
	CalendarService calendarService;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public void process(LeaveBean bean, BindingResult bindingResult, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String c_id = request.getParameter("c_id");
		String date = request.getParameter("date");
		String mc_id = request.getParameter("mc_id");
		List<CalendarDTO> list = new ArrayList<>();
		
		if(null != c_id){
			List<CalendarBean> cbs = calendarService.selectChef(Integer.parseInt(c_id));
			if(null != cbs){
				for (CalendarBean cb : cbs) {
					String data = cb.toString();
					String[] arr = null;
					if (data.length() > 0) {
						if (data.contains(",")) {
							arr = data.split(",");
							for (String s : arr) {
								CalendarDTO cd = new CalendarDTO();
								String[] ar = s.split("_");
								cd.setStart(ar[0] + ar[1]);
								cd.setTitle(ar[3]);
								cd.setEnd(ar[0] + ar[2]);
								cd.setColor(ar[4]);
								list.add(cd);
							}
						} else {
							CalendarDTO cd = new CalendarDTO();
							String[] ar = data.split("_");
							cd.setStart(ar[0] + ar[1]);
							cd.setTitle(ar[3]);
							cd.setEnd(ar[0] + ar[2]);
							cd.setColor(ar[4]);
							list.add(cd);
						}
					}
				}
			}
		}
		
		if(null != mc_id){
			long t1 = System.currentTimeMillis();
			List<CalendarBean> cbs = calendarService.selectMchef(Integer.parseInt(mc_id));
			long t2 = System.currentTimeMillis();
			System.out.println("select mchef time = " + (t2 - t1));
			System.out.println("beans size = " + cbs.size());
			if(null != cbs){
				for (CalendarBean cb : cbs) {
					String data = cb.toString();
					String[] arr = null;
					if (data.length() > 0) {
						if (data.contains(",")) {
							arr = data.split(",");
							for (String s : arr) {
								CalendarDTO cd = new CalendarDTO();
								String[] ar = s.split("_");
								cd.setStart(ar[0] + ar[1]);
								cd.setTitle(ar[3]);
								cd.setEnd(ar[0] + ar[2]);
								cd.setColor(ar[4]);
								list.add(cd);
							}
						} else {
							CalendarDTO cd = new CalendarDTO();
							String[] ar = data.split("_");
							cd.setStart(ar[0] + ar[1]);
							cd.setTitle(ar[3]);
							cd.setEnd(ar[0] + ar[2]);
							cd.setColor(ar[4]);
							list.add(cd);
						}
					}
				}
			}
		}
		response.setContentType("application/json;chartset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(new Gson().toJson(list));
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}

	}
}
