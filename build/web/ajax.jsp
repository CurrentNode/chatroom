<%@page import="mainPackage.sessionAndNickname"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=gb2312" %>  
<%  
    //设置输出信息的格式及字符集  
    response.setContentType("text/xml; charset=UTF-8");  
    response.setHeader("Cache-Control","no-cache");  
    out.println("<response>");  
    List<String> messageList = (List<String>)session.getServletContext().getAttribute("messages");
    if(messageList != null){
        Iterator<String> it1 = messageList.iterator();
        while(it1.hasNext()){
            out.println("<message>" + it1.next() + "</message>" );
        }
    }else{
    //    out.println("<message></message>");
    }
    List<sessionAndNickname> userList = (List<sessionAndNickname>)session.getServletContext().getAttribute("nicknames");
    if(userList != null){
        Iterator<sessionAndNickname> it2 = userList.iterator();
        while(it2.hasNext()){
            out.println("<user>" + it2.next().getNickNameString() + "</user>" );
        }
    }else{
        out.println("<user>" + "获取用户列表失败!" + "</user>" );
    }
    out.println("<onlinenumber>" + (String)session.getServletContext().getAttribute("number") +"</onlinenumber>");
    out.println("</response>");  
    out.close();  
%>