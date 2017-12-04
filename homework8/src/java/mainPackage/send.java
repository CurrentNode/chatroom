/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "send", urlPatterns = {"/send"})
public class send extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        /* TODO output your page here. You may use following sample code. */
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession();
        String message = request.getParameter("message");
        String nickName = (String) session.getAttribute("nickname");
        if(message != null){
            List<String> messageList = (List<String>)context.getAttribute("messages");
            List<String> historyMessageList = (List<String>)context.getAttribute("history");
            if(messageList == null){
                messageList = new LinkedList<>();
                messageList.add(nickName + " : " +message);
            }else{
                if(messageList.size() > 10){//每次最多显示20条, 剩下的存到一个历史记录表中
                    if(historyMessageList == null){
                        historyMessageList = new LinkedList<>();
                    }
                    historyMessageList.add(messageList.get(0));
                    messageList.remove(0);
                    context.setAttribute("history", historyMessageList);
                }
                messageList.add(nickName + " : " +message);
            }
            context.setAttribute("messages", messageList);
        }else{
            
        }
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
        processRequest(request, response);
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
