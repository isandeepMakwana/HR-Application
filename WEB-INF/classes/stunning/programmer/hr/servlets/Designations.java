package stunning.programmer.hr.servlets;
import stunning.programmer.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
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
List<DesignationDTO> designations=designationDAO.getAll();
int i;
for(DesignationDTO designation:designations)
{
i++;
pw.println();
}
}
catch(DAOException daoException)
{
//done done 
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