/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ganga Chathuri
 */
public class fetchResult extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet fetchResult</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet fetchResult at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
            String filterSubject = request.getParameter("subject");
            MongoClient mongoClient = new MongoClient();
// or
//MongoClient mongoClient = new MongoClient( "localhost" );
// or
//MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
// or, to connect to a replica set, with auto-discovery of the primary, supply a seed list of members
//MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
            //                                    new ServerAddress("localhost", 27018),
            //                                  new ServerAddress("localhost", 27019)));

            DB db = mongoClient.getDB("schooldb");
            DBCollection coll = db.getCollection("schoolResult");
//DBObject myDoc = coll.;
//String json = myDoc.toString();
            DBCursor cursor = coll.find();
            DecimalFormat df = new DecimalFormat("#.00");


            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>");
            out.println("schoolName");
            out.println("</th>");
             boolean exec = false;
            
            try {
                while (cursor.hasNext()) {

                    DBObject school = cursor.next();
                 
//
                    ArrayList<Map<String, String>> subject = (ArrayList<Map<String, String>>) school.get("subjects");

               

                    for (int i = 0; i < subject.size(); i++) {
                        if (subject.get(i).get("subject").equals(filterSubject)) {
                           // Iterator it = subject.get(i).entrySet().iterator();

                            subject.get(i).remove("subject");
                                if(!exec){
                    for (String key : subject.get(i).keySet()) {
                        out.println("<th>");
                        out.println(key);
                        out.println("</th>");
                    }
                    out.println("</tr>");
                    exec=true;
                   }
                            out.println("<tr>");
                            out.println("<td>");
                            out.println(school.get("schoolName"));
                            out.println("</td>");
                            for (Object value : subject.get(i).values()) {
                                out.println("<td>");
                                if(value.equals(0.0)){
                                out.println("0");
                                }else{
                                out.println(df.format(value));}
                                out.println("</td>");
                            }
                            out.println("</tr>");
//                            while (it.hasNext()) {
//                                Map.Entry pairs = (Map.Entry) it.next();
//                                if(i==0){
//                                out.println("<th>");
//                                out.println(pairs.getKey());
//                                out.println("</th>");
//                                }
//                                out.println(pairs.getKey() + " = " + pairs.getValue());
//                                it.remove(); // avoids a ConcurrentModificationException
//                            }
                        }
                    }
//       DBCursor sub = subject.find();
//       while(sub.hasNext()){
//       if(((String)sub.next().get("subject")).equals("numeracy")){
//           out.print(sub.next().get("latestY3"));
//       }
//       }

                    //ArrayList<String[]> subjects= (ArrayList<String[]>)school.fdin("subjects");

                }
                out.println("</table>");
            } finally {
                cursor.close();
            }


//out.println(myDoc);
            //out.println("came");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
