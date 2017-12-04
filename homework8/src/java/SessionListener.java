
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext; 
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import mainPackage.sessionAndNickname;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author Administrator
 */
public class SessionListener implements HttpSessionListener {
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context =se.getSession().getServletContext(); 
        String number =(String) context.getAttribute("number");
        if(number == null){
            number = "1";
            context.setAttribute("number", number);
        }else{
            int temp = Integer.parseInt(number);
            temp ++;
            number = Integer.toString(temp);
            context.setAttribute("number", number);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context =se.getSession().getServletContext(); 
        String number =(String) context.getAttribute("number");
        int temp = Integer.parseInt(number);
        temp --;
        number = Integer.toString(temp);
        context.setAttribute("number", number);        
        List<sessionAndNickname> nickNameList = (List<sessionAndNickname>) context.getAttribute("nicknames");
        Iterator<sessionAndNickname> it = nickNameList.iterator();
        while(it.hasNext()){
            if(it.next().getSessionString().equals(se.getSession().getId())){
                it.remove();
                return;
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
