/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nutan Gupta
 */
@WebServlet(urlPatterns = {"/newcycleRequest"})
public class newcycleRequest extends HttpServlet {
  String rowid1="",rowid2="",rowid3="",rowid4="",rowid5="",rowid6="",rowid7="";
            String lastcompany="",lastcolor="",lastmodel="",lastframe="",lastcycle="";
            int newid=0,newid1=0,newid2=0,newid3=0,newid4=0; 
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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
         
        try{
            
        
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/invertis","root","invertis");
           Statement st=con.createStatement();
           Statement st1=con.createStatement();
           Statement st2=con.createStatement();
           Statement st3=con.createStatement();
           Statement st4=con.createStatement();
           Statement st5=con.createStatement();
           Statement st6=con.createStatement();
           Statement st7=con.createStatement();
           Statement st8=con.createStatement();
           Statement st9=con.createStatement();
           Statement s10=con.createStatement();
           Statement st11=con.createStatement();
           Statement st12=con.createStatement();
          //Date and Time
          java.util.Date dt = new java.util.Date();
          java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String currentTime = sdf.format(dt);
         //textbox data
          String company=request.getParameter("ccompany");
          
          String model=request.getParameter("cmodel");
          
          String frame=request.getParameter("cframe");
          
         String quantity=request.getParameter("cquantity");
         String color=request.getParameter("ccolor");
        
         String type=request.getParameter("ctype");
          
         String sql="select * from company where company_name='"+company+"'";
         String sql1="select * from cycle_model where model_name='"+model+"'";
         String sql2="select * from cycle_frame where frame_size='"+frame+"' and type='"+type+"' ";
         
         //String sql="select * from company where company_name='"+company+"'";
         
         //last id
         String sql4="Select company_id from company order by timestamp desc limit 1";
         String sql5="Select model_id from cycle_model order by timestamp desc limit 1";
         
         
         
         
          ResultSet rs=st.executeQuery(sql);
          ResultSet rs1=st1.executeQuery(sql1);
          ResultSet rs2=st2.executeQuery(sql2);
          
          
          ResultSet rs4=st4.executeQuery(sql4);
          ResultSet rs5=st5.executeQuery(sql5);
          
          
          
   
        if(rs.next())
        {
             rowid1=rs.getString("company_id");
        }
        else
        {
             if(rs4.next())
             {
                 lastcompany=rs4.getString("company_id");
                 String id1=lastcompany.substring(7,lastcompany.length());
                newid=(Integer.parseInt(id1));
                ++newid;
                rowid1="company"+newid;
               
                boolean i=st8.execute("insert into company(company_id,company_name,timestamp)values('"+rowid1+"','"+company+"','"+currentTime+"')");
                
             }
        }
        if(rs1.next())
        {
             rowid2=rs1.getString("model_id");
        }
        else
        {
             if(rs5.next())
             {
                 lastmodel=rs5.getString("model_id");
                 String id2=lastmodel.substring(5,lastmodel.length());
                newid1=(Integer.parseInt(id2));
                ++newid1;
                rowid2="model"+newid1;
               
                boolean j=st8.execute("insert into cycle_model(model_id,model_name,timestamp)values('"+rowid2+"','"+model+"','"+currentTime+"')");
                
             }
        }
        if(rs2.next())
        {
             rowid3=rs2.getString("frame_id");
            
        }
        else
        {  String sql6="Select frame_id from cycle_frame order by timestamp desc limit 1";
             ResultSet rs6=st6.executeQuery(sql6);
             if(rs6.next())
             {
                 lastframe=rs6.getString("frame_id");
                 String id3=lastframe.substring(5,lastframe.length());
                newid2=(Integer.parseInt(id3));
                ++newid2;
                rowid3="frame"+newid2;
               
               boolean k=st8.execute("insert into cycle_frame(frame_id,frame_size,type,timestamp)values('"+rowid3+"','"+frame+"','"+type+"','"+currentTime+"')");
                
             }
        }
        String sql3="select * from color where color_name='"+color+"'";
        ResultSet rs3=st3.executeQuery(sql3);
        if(rs3.next())
        {
             rowid4=rs3.getString("color_id");
        }
        else
        {
             String sql7="Select color_id from color order by timestamp desc limit 1";
             ResultSet rs7=st7.executeQuery(sql7);
             if(rs7.next())
             {
                 lastcolor=rs7.getString("color_id");
                 String id4=lastcolor.substring(5,lastcolor.length());
                newid3=(Integer.parseInt(id4));
                ++newid3;
                rowid4="color"+newid3;
                
                boolean l=st8.execute("insert into color(color_id,color_name,timestamp)values('"+rowid4+"','"+color+"','"+currentTime+"')");
                
             }
        }
        
        Statement stt=con.createStatement();
       System.out.println(rowid1);
       System.out.println(rowid2);
       System.out.println(rowid3);
       System.out.println(rowid4);
       
       String s="select cycle_id from cycle where company_id='"+rowid1+"' and model_id='"+rowid2+"' and frame_id='"+rowid3+"' and color_id='"+rowid4+"'";
       ResultSet r=stt.executeQuery(s);
       System.out.println(s);
       if(r.next())
       {  out.print("hello");
          rowid7=r.getString("cycle_id");
          out.print(rowid7);
       }
      else
       {
       String sql8="Select cycle_id from cycle order by timestamp desc limit 1";
       ResultSet rs8=st8.executeQuery(sql8);
       if(rs8.next()){
        lastcycle=rs8.getString("cycle_id");
        String id5=lastcycle.substring(5,lastcycle.length());
        newid4=(Integer.parseInt(id5));
        ++newid4;
        rowid7="cycle"+newid4;
        out.print(rowid7);
       boolean m=st9.execute("insert into cycle(cycle_id,model_id,frame_id,color_id,company_id,quantity,gst,per_amount,discount,timestamp)values('"+rowid7+"','"+rowid2+"','"+rowid3+"','"+rowid4+"','"+rowid1+"','0','0','0','0','"+currentTime+"')");
        out.print(m);
       out.print("data inserted");
       }
       }
 
String sql9="select request_id from request order by timestamp desc limit 1";
ResultSet rs10=st11.executeQuery(sql9);
if(rs10.next())
{
    rowid6=rs10.getString("request_id");
}
boolean n=st12.execute("insert into request_product(request_id,cycle_id,cycle_quantity)values('"+rowid6+"','"+rowid7+"','"+quantity+"')");
out.print("<script>alert(\"request of data complete\")</script>");
        }catch(Exception e){e.printStackTrace();}
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(newcycleRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(newcycleRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
