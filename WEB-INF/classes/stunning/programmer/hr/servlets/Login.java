package stunning.programmer.hr.servlets;
import stunning.programmer.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Login extends HttpServlet
{
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
try
{
String username=request.getParameter("username");
String password=request.getParameter("password");
AdministrationDTO administrationDTO;
administrationDTO = new AdministrationDTO();
PrintWriter pw=null;
try
{
administrationDTO = new AdministrationDAO().getByUserName(username);
}
catch(DAOException daoException)
{
pw=response.getWriter();
response.setContentType("text/plain");
pw.print(daoException);
}
String dlpassword=administrationDTO.getPassword();
if(dlpassword.equals(password)==false)
{
pw=response.getWriter();
response.setContentType("text/plain");
pw.print("wrong password");
return;
}
HttpSession session= request.getSession();
session.setAttribute("username", username);
pw=response.getWriter();
response.setContentType("text/plain");
pw.print("successful login");
}catch(Exception exception)
{
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception e)
{
//do nothing
}
}
}
}