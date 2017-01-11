package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import model.CsBean;
import model.CsService;
import model.MemberBean;
import model.MemberService;
import model.OutPutMessage;


@Controller
public class WebSocketController {
	@Autowired
	MemberService memberService;
	@Autowired
	CsService csService;
	
    @MessageMapping("/hello")
    @SendTo("/topic/chatroom")
    public OutPutMessage ChatMsg(String content) throws Exception {
        Thread.sleep(1000); // simulated delay
//        String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";
        String[] AfterSplit = content.split("\\{\"|\":\"|\",\"|\":\"|\",\"|\":\"|\"\\}");
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        int m_idint=0;
        String content1 = null, m_id = null, name;
        if(AfterSplit.length==7){
        	content1 = AfterSplit[2].trim();
        	m_id= AfterSplit[4].trim();
        	name= AfterSplit[6].trim();
        	m_idint= Integer.parseInt(m_id);
        	MemberBean user = memberService.select(m_idint);
        	
        	CsBean bean = new CsBean();
        	bean.setContent(content1);
        	bean.setCs_status("0");
        	long time1= new java.util.Date().getTime();
			bean.setTime((new java.sql.Time(time1)));
        	
        	csService.sendMessage(bean,user);
        	
            return new OutPutMessage(name, content1, time);
        } else{
        	content1 = AfterSplit[2].trim();
        	name = "訪客";
            return new OutPutMessage(name, content1, time);
        }
//        String time = new SimpleDateFormat("HH:mm").format(new Date());
//        String tmp = "{\"Data\":{\"Name\":\""+ name +"\",\"Content\":\""+ content +"\",\"time\":\""+time+"\"}}";
//        j = new JSONObject(tmp);
//        Object jsonOb = j.getJSONObject("Data");      
    }   
}
