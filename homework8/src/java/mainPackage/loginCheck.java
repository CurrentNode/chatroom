/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class loginCheck extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String nickName = request.getParameter("nickname");
        if(nickName == null || nickName.equals("")){
            //提示昵称不能为空
            //out.write("<script Language=\"JavaScript\">" + "alert(\"昵称不能为空~\");" + "</script>");
            response.sendRedirect("index.html");
            request.getRequestDispatcher("index.html");
            return;
        }
        HttpSession thisSession = request.getSession();
        ServletContext context = thisSession.getServletContext();
        List<sessionAndNickname> nickNameList = (List<sessionAndNickname>) context.getAttribute("nicknames");//名字列表
        if(nickNameList != null){//有列表, 将此昵称和列表中的昵称比较, 先判断session
            Iterator<sessionAndNickname> it = nickNameList.iterator();
            Iterator<sessionAndNickname> it_ = nickNameList.iterator();
            while(it_.hasNext()){
                if(thisSession.getId().equals(it_.next().getSessionString())){
                    request.getRequestDispatcher("/WEB-INF/chatRoom.jsp").forward(request, response);
                    return;
                }
            }
            while(it.hasNext()){
                if(nickName.equals(it.next().getNickNameString())){
                    //提示昵称重复 之后回到首页
                    request.getRequestDispatcher("index.html");
                    return;
                }
            }
        }else{//没有列表
            nickNameList = new LinkedList<>();
        }
        //把昵称加入列表并跳转到聊天室页面
        sessionAndNickname newUser = new sessionAndNickname(nickName, thisSession.getId());
        nickNameList.add(newUser);
        context.setAttribute("nicknames", nickNameList);
        thisSession.setAttribute("nickname", nickName);
        //request.getRequestDispatcher("/WEB-INF/chatRoom.jsp").forward(request, response);
        response.sendRedirect("chatRoom.jsp");
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
