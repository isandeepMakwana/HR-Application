package stunning.programmer.hr.servlets;
import stunning.programmer.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class EditDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
int code=0;
try
{
code= Integer.parseInt(request.getParameter("code"));
}catch(NumberFormatException nfe)
{
System.out.println(nfe.getMessage());
}
DesignationDAO designationDAO = new DesignationDAO();
DesignationDTO designationDTO =new DesignationDTO();
try
{
try
{
designationDTO=designationDAO.getByCode(code);
}
catch(DAOException daoException)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
requestDispatcher.forward(request,response);
}
String title=designationDTO.getTitle();
HttpSession httpSession = request.getSession();
httpSession.setAttribute("code",code);
httpSession.setAttribute("title",title);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/DesignationEditForm.jsp");
requestDispatcher.forward(request,response);
}catch(Exception e)
{
// do nothing
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