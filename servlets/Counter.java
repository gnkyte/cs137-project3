/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefmark
 */
@WebServlet(name = "Counter", urlPatterns = {"/Counter"})
public class Counter extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
    	try {
    		PrintWriter out = response.getWriter();
            checkIfMapExists(request);
            ((Map<String, Set<String>>)getServletContext().
                getAttribute("visitMapCount")).
                get(request.getRequestURI()).add(request.getRemoteAddr());
            
            out.println("<div id=\"hit-counter-container\">");
            out.println("<p class=\"counter-text\">There are currently <b class=\"count\">" + 
                    getNumberOfVisitors(request) + 
                    "</b> other people viewing this product.");
            out.println("You might want to jump on this soon! There's only a limited stock available!</p>");
            out.println("</div>");
        }
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
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
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet responsible for counting the number of unique hits based on IP Address using ServletContext objects.";
    }// </editor-fold>
        
    private void checkIfMapExists(HttpServletRequest request)
    {
        // If the value visitMapCount is not found in the servlet context object, create a new 
        // attribute in the servlet context object as a new ConcurrentHashMap of Strings to
        // HashSets of Strings
        if(getServletContext().getAttribute("visitMapCount") == null)
        {
            // TODO: request.getHeader("referer") is returning null because it is being forwarded?
            getServletContext().setAttribute("visitMapCount", new ConcurrentHashMap<String, HashSet<String>>());
            ((Map<String, Set<String>>)getServletContext().
                    getAttribute("visitMapCount")).
                    put(request.getRequestURI(), new HashSet<String>());
        }
        
        // Else if the value visitMapCount is already set, but the current page is not a key
        // in the map, then add the key with a new HashSet of Strings 
        else if(!((Map<String, Set<String>>)getServletContext().
                    getAttribute("visitMapCount")).
                    containsKey(request.getRequestURI()))
        {
            ((Map<String, Set<String>>)getServletContext().
                    getAttribute("visitMapCount")).
                    put(request.getRequestURI(), new HashSet<String>());
        }
        
        // Else, this means that the value is set in the context object and the 
        // key is already a value in the Map, so there also exists a corresponding
        // HashSet of IP address strings and we do not need to do anything
    }
    
    private int getNumberOfVisitors(HttpServletRequest request)
    {
        return ((Map<String, Set<String>>)getServletContext().
                    getAttribute("visitMapCount")).
                    get(request.getRequestURI()).size() - 1;
        // -1 because we don't want to include the person currently looking at the page
    }
}
