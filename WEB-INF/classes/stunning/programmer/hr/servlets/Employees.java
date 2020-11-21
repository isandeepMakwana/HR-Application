package stunning.programmer.hr.servlets;
import stunning.programmer.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.math.*;
import java.util.*;
import java.text.*;
import java.io.*;
public class Employees extends HttpServlet
{
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
EmployeeDAO employeeDAO = new EmployeeDAO();
try
{
PrintWriter pw= null;
List<EmployeeDTO> list=null;
try
{
list=employeeDAO.getAll();
pw= response.getWriter();
response.setContentType("text/plain");
int i=0;
for(EmployeeDTO employeeDTO:list)
{
i++;
pw.print(employeeDTO.getName()+","+employeeDTO.getDesignation()+","+employeeDTO.getDateOfBirth()+","+employeeDTO.getGender()+","+employeeDTO.getIsIndian()+","+employeeDTO.getBasicSalary()+","+employeeDTO.getPANNumber()+","+employeeDTO.getAadharCardNumber());
if(i<list.size()) pw.print(",");
}
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