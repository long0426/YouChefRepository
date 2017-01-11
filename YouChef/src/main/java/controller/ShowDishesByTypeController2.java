package controller; 

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.DishesBean;
import model.DishesService; 

 
@Controller 
@RequestMapping(path = { "/showDishes2.controller" }) 
public class ShowDishesByTypeController2 { 
 @Autowired 
 DishesService dishesService; 
  
 @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }) 
 public String process( Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) { 
   
  List<Object[]> ob = dishesService.selectType(Integer.parseInt(request.getParameter("t_id")));//t_id 
  //tid> did 把did存起來之後去抓圖> getphoto 
//  Map <String, Integer> numbers = new HashMap <String, Integer>(); 
// 
  List <DishesBean> list = new ArrayList(); 
  for (Object[] row: ob){ 
   for(Object item :row){ 
    if(item instanceof DishesBean){ 
//    System.out.print("item= "+item+","); 
     list.add((DishesBean)item); 
     System.out.println(((DishesBean) item).getD_id()); 
    } 
   } 
   //System.out.println(""); 
   } 
  System.out.println("fff"+list); 
  model.addAttribute("ob", list); 
  session.setAttribute("taiDishesList", list);
 return "SelectTypeDishes2"; 
}



}