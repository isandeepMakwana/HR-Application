package stunning.programmer.hr.servlets;
import stunning.programmer.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.math.*;
import java.util.*;
import java.text.*;
import java.io.*;
public class UpdateEmployee extends HttpServlet
{
public void doPost(HttpServletRequest request, HttpServletResponse response)
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
String name =request.getParameter("name");
String employeeId=request.getParameter("employeeId");
int designationCode =Integer.parseInt(request.getParameter("designationCode"));
String dateOfBirth=request.getParameter("dateOfBirth");
String gender=request.getParameter("gender");
boolean isIndian=new Boolean(request.getParameter("isIndian"));
BigDecimal basicSalary=new BigDecimal(request.getParameter("basicSalary"));
String panNumber=request.getParameter("panNumber");
String aadharCardNumber=request.getParameter("aadharCardNumber");
boolean panNumberExists=false;
boolean aadharCardNumberExists=false;
try
{
EmployeeDAO employeeDAO = new EmployeeDAO();
EmployeeDTO employeeDTO =new EmployeeDTO();
employeeDTO.setName(name);
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setDesignationCode(designationCode);
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
employeeDTO.setDateOfBirth(simpleDateFormat.parse(dateOfBirth));
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);

try
{
PrintWriter pw= null;
try
{
employeeDAO.update(employeeDTO);
pw= response.getWriter();
response.setContentType("text/plain");
pw.println("Employee updated");
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
}catch(Exception e)
{
// do nothing
}
}
public void doGet(HttpServletRequest request ,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}
catch(Exception exception){}
}
}