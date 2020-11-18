package stunning.programmer.hr.dl;
import java.sql.*;
import java.util.*;
public class AdministrationDAO
{
public AdministrationDTO getByUserName(String userName) throws DAOException
{
Connection connection=null;
try
{
connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from administrator where uname=?");
preparedStatement.setString(1,userName);
ResultSet resultSet=preparedStatement.executeQuery();
while(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid userName"+userName);
}
AdministrationDTO administrationDTO= new AdministrationDTO();
administrationDTO.setUserName(resultSet.getString("uname").trim());
administrationDTO.setPassword(resultSet.getString("password").trim());
resultSet.close();
preparedStatement.close();
connection.close();

return administrationDTO;
}catch(SQLException sqlexception)
{
throw new DAOException(sqlexception.getMessage());
}
}
}