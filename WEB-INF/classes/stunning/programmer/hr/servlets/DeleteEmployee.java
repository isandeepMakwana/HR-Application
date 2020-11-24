package stunning.programmer.hr.servlets;
import stunning.programmer.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class DeleteEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
HttpSession session = request.getSession();
session.removeAttribute("employeeId");
session.removeAttribute("name");
session.removeAttribute("designationCode");
session.removeAttribute("designation");
session.removeAttribute("dateOfBirth");
session.removeAttribute("gender");
session.removeAttribute("isIndian");
session.removeAttribute("basicSalary");
session.removeAttribute("panNumber");
session.removeAttribute("aadharCardNumber");
String employeeId=request.getParameter("employeeId");
EmployeeDAO employeeDAO;
employeeDAO = new EmployeeDAO();
try
{
PrintWriter pw= null;
try
{
employeeDAO.DeleteByEmployeeId(employeeId);
pw= response.getWriter();
response.setContentType("text/plain");
pw.println("Employee deleted");
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