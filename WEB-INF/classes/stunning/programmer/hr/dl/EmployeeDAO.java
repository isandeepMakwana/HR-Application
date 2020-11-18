package stunning.programmer.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;
public class EmployeeDAO{

public List<EmployeeDTO> getAll() throws DAOException{
        List<EmployeeDTO> employees;
        employees = new LinkedList<>();
         
try{
Connection connection = DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet = statement.executeQuery("select employee.id, employee.name, employee.designation_code, designation.title, employee.date_of_birth, employee.gender, employee.is_indian, employee.basic_salary, employee.pan_number,employee.aadhar_card_number from employee inner join designation on employee.designation_code=designation.code order by employee.name");
int id;
String name;
int designationCode;
String title;
java.sql.Date dateOfBirth;
String gender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
String aadharCardNumber;
EmployeeDTO employeeDTO;
while(resultSet.next())
{
id=resultSet.getInt("id");
name=resultSet.getString("name").trim();
designationCode=resultSet.getInt("designation_code");
title=resultSet.getString("title").trim();
dateOfBirth=resultSet.getDate("date_of_birth");
gender=resultSet.getString("gender");
isIndian=resultSet.getBoolean("is_indian");
basicSalary=resultSet.getBigDecimal("basic_salary");
panNumber=resultSet.getString("pan_number").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId("A"+id);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDesignation(title);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
employees.add(employeeDTO);//link list me object dal diya
}
resultSet.close();
statement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return employees;
}


public void add(EmployeeDTO employee) throws DAOException
{
try
{
String panNumber = employee.getPANNumber();
String aAdharCardNumber=employee.getAadharCardNumber();
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement("select id from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet = preparedStatement.executeQuery();

if (resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN Number :"+panNumber+"exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement("select id from employee where aadhar_card_number=?");
preparedStatement.setString(1,aAdharCardNumber);
resultSet = preparedStatement.executeQuery();
if (resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number :"+aAdharCardNumber+"exists.");
}

resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement("insert into employee (name, designation_code,date_of_birth,gender,is_indian,basic_salary,pan_number,aadhar_card_number) values (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,employee.getName());
preparedStatement.setInt(2,employee.getDesignationCode());
//spacal for date
//======================================

java.util.Date dateOfBirth = employee.getDateOfBirth();
java.sql.Date sqlDate= new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(), dateOfBirth.getDate());

//=============================================
preparedStatement.setDate(3,sqlDate);
preparedStatement.setString(4,employee.getGender());
preparedStatement.setBoolean(5,employee.getIsIndian());
preparedStatement.setBigDecimal(6,employee.getBasicSalary());
preparedStatement.setString(7,panNumber);
preparedStatement.setString(8, aAdharCardNumber);
preparedStatement.executeUpdate();
resultSet = preparedStatement.getGeneratedKeys();
resultSet.next();
int id = resultSet.getInt(1);
employee.setEmployeeId("A"+id);
resultSet.close();
preparedStatement.close();
connection.close();


}catch(SQLException sqlexception)
{
throw new DAOException(sqlexception.getMessage());
}
}


public void update(EmployeeDTO employee) throws DAOException
{
try
{
String employeeId = employee.getEmployeeId();
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}
catch(Exception exception)
{
throw new DAOException("Invalid employee id :"+employeeId);
}
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement("select gender from employee where id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet = preparedStatement.executeQuery();
if (resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid employee"+employeeId);
}
resultSet.close();
preparedStatement.close();
String panNumber=employee.getPANNumber();
String aAdharCardNumber=employee.getAadharCardNumber();
preparedStatement = connection.prepareStatement("select id from employee where pan_number=? and id<>?");
preparedStatement.setString(1,panNumber);
preparedStatement.setInt(2,actualEmployeeId);
resultSet = preparedStatement.executeQuery();
if (resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN Number :"+panNumber+"exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement("select id from employee where aadhar_card_number=? and id<>?");
preparedStatement.setString(1,aAdharCardNumber);
preparedStatement.setInt(2,actualEmployeeId);
resultSet = preparedStatement.executeQuery();
if (resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number :"+aAdharCardNumber+"exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement("update employee set name=?,designation_code=?,date_of_birth=?,gender=?,is_indian=?,basic_salary=?,pan_number=?,aadhar_card_number=? where id=?");
preparedStatement.setString(1,employee.getName());
preparedStatement.setInt(2,employee.getDesignationCode());
//spacal for date
//======================================

java.util.Date dateOfBirth = employee.getDateOfBirth();
java.sql.Date sqlDate= new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(), dateOfBirth.getDate());

//=============================================
preparedStatement.setDate(3,sqlDate);
preparedStatement.setString(4,employee.getGender());
preparedStatement.setBoolean(5,employee.getIsIndian());
preparedStatement.setBigDecimal(6,employee.getBasicSalary());
preparedStatement.setString(7,panNumber);
preparedStatement.setString(8, aAdharCardNumber);
preparedStatement.setInt(9,actualEmployeeId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}
catch(SQLException sqlexception)
{
throw new DAOException(sqlexception.getMessage());
}
}


public boolean panNumberExists(String panNumber) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
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


public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
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


public void DeleteByEmployeeId(String employeeId) throws DAOException
{
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}
catch(Exception exception)
{
throw new DAOException("Invalid employee id : "+employeeId);
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid employee Id. : "+employeeId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from employee where id=?");
preparedStatement.setInt(1,actualEmployeeId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}
catch(Exception exception)
{
        throw new DAOException(exception.getMessage());//TODO: handle exception
}
}


public EmployeeDTO getByEmployeeId(String employeeId) throws DAOException
{
EmployeeDTO employeeDTO=null;
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}
catch(Exception exception)
{
throw new DAOException("Invalid employee id : "+employeeId);
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select employee.id, employee.name, employee.designation_code, designation.title, employee.date_of_birth, employee.gender, employee.is_indian, employee.basic_salary, employee.pan_number,employee.aadhar_card_number from employee inner join designation on employee.designation_code=designation.code and id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid employee Id. : "+employeeId);
}
int id;
String name;
int designationCode;
String title;
java.sql.Date dateOfBirth;
String gender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
String aadharCardNumber;
id=resultSet.getInt("id");
name=resultSet.getString("name").trim();
designationCode=resultSet.getInt("designation_code");
title=resultSet.getString("title").trim();
dateOfBirth=resultSet.getDate("date_of_birth");
gender=resultSet.getString("gender");
isIndian=resultSet.getBoolean("is_indian");
basicSalary=resultSet.getBigDecimal("basic_salary");
panNumber=resultSet.getString("pan_number").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId("A"+id);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDesignation(title);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);

resultSet.close();
preparedStatement.close();
connection.close();
}
catch(Exception exception)
{
throw new DAOException(exception.getMessage());//TODO: handle exception
}
return employeeDTO;
}



public boolean employeeIdExists(String employeeId) throws DAOException
{
boolean exists=false;
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}
catch(Exception exception)
{
return false;
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}
catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return exists;
}

public EmployeeDTO getByPANNumber(String panNumber) throws DAOException
{
EmployeeDTO employeeDTO=null;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select employee.id, employee.name, employee.designation_code, designation.title, employee.date_of_birth, employee.gender, employee.is_indian, employee.basic_salary, employee.pan_number,employee.aadhar_card_number from employee inner join designation on employee.designation_code=designation.code and pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid PAN number : "+panNumber);
}
int id;
String name;
int designationCode;
String title;
java.sql.Date dateOfBirth;
String gender;
boolean isIndian;
BigDecimal basicSalary;
String aadharCardNumber;
id=resultSet.getInt("id");
name=resultSet.getString("name").trim();
designationCode=resultSet.getInt("designation_code");
title=resultSet.getString("title").trim();
dateOfBirth=resultSet.getDate("date_of_birth");
gender=resultSet.getString("gender");
isIndian=resultSet.getBoolean("is_indian");
basicSalary=resultSet.getBigDecimal("basic_salary");
panNumber=resultSet.getString("pan_number").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId("A"+id);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDesignation(title);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);

resultSet.close();
preparedStatement.close();
connection.close();
}
catch(Exception exception)
{
throw new DAOException(exception.getMessage());//TODO: handle exception
}
return employeeDTO;
}


public EmployeeDTO getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
EmployeeDTO employeeDTO=null;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select employee.id, employee.name, employee.designation_code, designation.title, employee.date_of_birth, employee.gender, employee.is_indian, employee.basic_salary, employee.pan_number,employee.aadhar_card_number from employee inner join designation on employee.designation_code=designation.code and aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Aadhar card number : "+aadharCardNumber);
}
int id;
String name;
int designationCode;
String title;
java.sql.Date dateOfBirth;
String gender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
id=resultSet.getInt("id");
name=resultSet.getString("name").trim();
designationCode=resultSet.getInt("designation_code");
title=resultSet.getString("title").trim();
dateOfBirth=resultSet.getDate("date_of_birth");
gender=resultSet.getString("gender");
isIndian=resultSet.getBoolean("is_indian");
basicSalary=resultSet.getBigDecimal("basic_salary");
panNumber=resultSet.getString("pan_number").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId("A"+id);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDesignation(title);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);

resultSet.close();
preparedStatement.close();
connection.close();
}
catch(Exception exception)
{
throw new DAOException(exception.getMessage());//TODO: handle exception
}
return employeeDTO;
}
}