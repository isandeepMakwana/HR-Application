package stunning.programmer.hr.servlets;
import stunning.programmer.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class EditEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
String employeeId=request.getParameter("employeeId");
EmployeeDTO employeeDTO=new EmployeeDTO();
EmployeeDAO employeeDAO = new EmployeeDAO();
try
{
try
{
employeeDTO = employeeDAO.getByEmployeeId(employeeId);
}
catch(DAOException daoException)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Employees.jsp");
requestDispatcher.forward(request,response);
}
HttpSession session = request.getSession();
session.setAttribute("employeeId",employeeDTO.getEmployeeId());
session.setAttribute("name",employeeDTO.getName());
session.setAttribute("designationCode",employeeDTO.getDesignationCode());
session.setAttribute("designation",employeeDTO.getDesignation());
session.setAttribute("dateOfBirth",employeeDTO.getDateOfBirth());
session.setAttribute("gender",employeeDTO.getGender());
String nationality;
if(employeeDTO.getIsIndian())
{
nationality="Indian";
}
else
{
nationality="other";
}
session.setAttribute("isIndian",nationality);
session.setAttribute("basicSalary",employeeDTO.getBasicSalary());
session.setAttribute("panNumber",employeeDTO.getPANNumber());
session.setAttribute("aadharCardNumber",employeeDTO.getAadharCardNumber());
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/EmployeeEditForm.jsp");
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