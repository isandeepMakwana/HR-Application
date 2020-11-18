package stunning.programmer.hr.dl;
import java.sql.*;
import java.util.*;
public class DesignationDAO{
public void add(DesignationDTO designation) throws DAOException
{
try     
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement =connection.prepareStatement("select * from designation where title=?");
preparedStatement.setString(1,designation.getTitle());
ResultSet resultSet=preparedStatement.executeQuery();

if(resultSet.next()==true){
    resultSet.close();
    preparedStatement.close();
    connection.close();
    throw new DAOException("Designation :"+designation.getTitle()+"exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement("insert into designation (title) values (?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,designation.getTitle());
preparedStatement.executeUpdate();
resultSet = preparedStatement.getGeneratedKeys();
resultSet.next();
int code = resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
designation.setCode(code);
} catch (SQLException e) {
throw new DAOException(e.getMessage());    //TODO: handle exception remove after testing

}
}
public List<DesignationDTO> getAll() throws DAOException{
        List<DesignationDTO> designations;
        designations = new LinkedList<>();
         
try{
Connection connection = DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet = statement.executeQuery("select * from designation order by title");
DesignationDTO designationDTO;
int code;
String title;

while(resultSet.next())
{
code =resultSet.getInt("code");
title=resultSet.getString("title").trim(); // trim is very v v v importent
designationDTO = new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(title);
designations.add(designationDTO);//link list me object dal diya
}
resultSet.close();
statement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return designations;
}
public DesignationDTO getByCode(int code) throws DAOException
{
    try {
        Connection connection = DAOConnection.getConnection();
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("select * from designation where code=?");
        preparedStatement.setInt(1,code);
        ResultSet resultSet;
        resultSet=preparedStatement.executeQuery();
        if(resultSet.next()==false)
        {
            resultSet.close();
	            preparedStatement.close();
            connection.close();
            throw new DAOException("Invalid designation code :"+code);

        }
        DesignationDTO designationDTO = new DesignationDTO();
        designationDTO.setCode(resultSet.getInt("code"));
        designationDTO.setTitle(resultSet.getString("title").trim());
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return designationDTO;
            }
            catch(Exception e){
                
            throw new DAOException(e.getMessage());        //TODO: handle exception
    }
}

public void update(DesignationDTO designation) throws DAOException
{
    try {
        int code=designation.getCode();
        String title=designation.getTitle();
        Connection connection= DAOConnection.getConnection();
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("select * from designation where code=?");
        preparedStatement.setInt(1,code);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()==false){
            resultSet.close();
            preparedStatement.close();
            connection.close();
            throw new DAOException("Invalid designation code :"+code);

        }

resultSet.close();
preparedStatement.close();
        preparedStatement=connection.prepareStatement("select * from designation where title=? and code!=?");
        preparedStatement.setString(1,title);
        preparedStatement.setInt(2,code);
        resultSet=preparedStatement.executeQuery();
        if(resultSet.next()==true){
            resultSet.close();
            preparedStatement.close();
            connection.close();
            throw new DAOException(title+"exists.");
        }
        resultSet.close();
        preparedStatement.close();
        preparedStatement=connection.prepareStatement("update designation set title=? where code=?");
        preparedStatement.setString(1,title);
        preparedStatement.setInt(2,code);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    } catch (Exception e) {
        throw new DAOException(e.getMessage());//TODO: handle exception
    }
}

public void deleteByCode(int code) throws DAOException
{
try{
        Connection connection= DAOConnection.getConnection();
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("select * from designation where code=?");
        preparedStatement.setInt(1,code);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()==false){
            resultSet.close();
            preparedStatement.close();
            connection.close();
            throw new DAOException("Invalid designation code :"+code);
}
resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement("select gender from employee where designation_code=?");// one check pending related to if this designation has allocated to an employee
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()){
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Cannot delete designation as it has been alloted to an employee");
}
resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement("delete from designation where code=?");
preparedStatement.setInt(1,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();


}
catch(Exception exception)
{
        throw new DAOException(exception.getMessage());//TODO: handle exception
}
}


public boolean designationCodeExists(int code) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select code from designation where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}
catch(Exception exception)
{
        throw new DAOException(exception.getMessage());//TODO: handle exception
}
return exists;
}
}