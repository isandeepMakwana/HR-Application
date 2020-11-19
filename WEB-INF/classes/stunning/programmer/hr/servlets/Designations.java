package stunning.programmer.hr.servlets;
import stunning.programmer.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class Designations extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
DesignationDAO designationDAO = new DesignationDAO();
DesignationDTO designationDTO = new DesignationDTO();
try
{
PrintWriter pw = response.getWriter();
response.setContentType("text/plain");
try{
List<DesignationDTO> designations=designationDAO.getAll();
int i=0;
for(DesignationDTO designation:designations)
{
i++;
pw.println(designation.getCode()+","+designation.getTitle());
if(i<designations.size())pw.println(",");
}
}
catch(DAOException daoException)
{
System.out.println(daoException);
}
}catch(IOException ioException)
{
// nothing
}
}
public void doPost(HttpServletRequest request ,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}
catch(Exception exception)
{

}
}

}