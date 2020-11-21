package stunning.programmer.hr.servlets;
import stunning.programmer.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.math.*;
import java.util.*;
import java.text.*;
import java.io.*;
public class AddEmployee extends HttpServlet
{
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
String name =request.getParameter("name");
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
System.out.println(designationCode);
PrintWriter pw= null;
try
{
panNumberExists=employeeDAO.panNumberExists(panNumber);
aadharCardNumberExists=employeeDAO.aadharCardNumberExists(aadharCardNumber);
if(panNumberExists==true||aadharCardNumberExists==true)
{
pw= response.getWriter();
response.setContentType("text/plain");
if(panNumberExists==true && aadharCardNumberExists==false)
{
pw.println("panNumber,Exists");
}
else if(panNumberExists==false && aadharCardNumberExists==true)
{
pw.println("aadharCardNumber,Exists");
}else
{
pw.println("both,Exists");
}
return;
}
employeeDAO.add(employeeDTO);
pw= response.getWriter();
response.setContentType("text/plain");
pw.println("Employee added");
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