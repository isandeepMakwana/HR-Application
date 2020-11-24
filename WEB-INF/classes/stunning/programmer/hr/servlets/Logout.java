package stunning.programmer.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
public class Logout extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try
{
HttpSession session = request.getSession();
//session.removeAttribute("username");
session.invalidate();
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
requestDispatcher.forward(request, response);
}
catch(Exception e)
{
System.out.println(e);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/ErrorPage.jsp");
try
{
requestDispatcher.forward(request, response);
}
catch(Exception exception)
{
// do nothing
}
}
}
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
doGet(request,response);
}
}