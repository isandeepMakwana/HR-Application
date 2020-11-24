package stunning.programmer.hr.servlets;
import stunning.programmer.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class DeleteDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
HttpSession session = request.getSession();
session.removeAttribute("code");
session.removeAttribute("title");
int code =0;
code =Integer.parseInt(request.getParameter("code"));
DesignationDAO designationDAO = new DesignationDAO();
DesignationDTO designationDTO =new DesignationDTO();
try
{
PrintWriter pw= null;
try
{
designationDAO.deleteByCode(code);
pw= response.getWriter();
response.setContentType("text/plain");
pw.println("Designation deleted");
}
catch(DAOException daoException)
{
pw = response.getWriter();
response.setContentType("text/plain");
pw.println(daoException.getMessage());
}
}
catch(Exception exception)
{
System.out.println(exception);
}
}
public void doPost(HttpServletRequest request ,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}
catch(Exception exception){}
}

}