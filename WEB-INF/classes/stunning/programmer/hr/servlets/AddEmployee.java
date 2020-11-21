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
String designation =request.getParameter("designation");
Date dateOfBirth=new Date(request.getParameter("dataOfBirth"));
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
try{
dateOfBirth=simpleDateFormat.parse(dateOfBirth.toString());
}
catch(Exception e){}
String gender=request.getParameter("gender");
boolean isIndian=new Boolean(request.getParameter("isIndian"));
BigDecimal basicSalary=new BigDecimal(request.getParameter("basicSalary"));
String panNumber=request.getParameter("panNumber");
String aadharCardNumber=request.getParameter("aadharCardNumber");
EmployeeDAO employeeDAO = new EmployeeDAO();
EmployeeDTO employeeDTO =new EmployeeDTO();
employeeDTO.setName(name);
employeeDTO.setDesignation(designation);
employeeDTO.setDateOfBirth(dateOfBirth);
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