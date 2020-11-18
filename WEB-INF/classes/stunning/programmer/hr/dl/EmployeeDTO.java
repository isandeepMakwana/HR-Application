package stunning.programmer.hr.dl;
import java.util.*;
import java.math.*;

public class EmployeeDTO implements java.io.Serializable,Comparable<EmployeeDTO>
{
private String employeeId;
private String name;
private int designationCode;
private String designation;
//private String title;
private java.util.Date dateOfBirth;
private String gender;
private boolean isIndian;
private BigDecimal basicSalary;
private String panNumber;
private String aadharCardNumber;
public EmployeeDTO()
{
this.employeeId="";
this.name="";
this.designationCode=0;
this.designation="";
//this.title="";
this.dateOfBirth=null;
this.gender="";
this.isIndian=false;
this.basicSalary=null;
this.panNumber="";
this.aadharCardNumber="";
}
public void setEmployeeId(java.lang.String employeeId)
{
this.employeeId=employeeId;
}
public java.lang.String getEmployeeId()
{
return this.employeeId;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}

public void setDesignationCode(int designationCode)
{
this.designationCode=designationCode;
}
public int getDesignationCode()
{
return this.designationCode;
 }
public void setDesignation(String designation)
{
this.designation=designation;
}
public String getDesignation()
{
return this.designation;
}
/*public void setTitle(String title)
{
this.title=title;
}
public String getTitle()
{
return this.title;
}
*/
public void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public java.util.Date getDateOfBirth()
{
return this.dateOfBirth;
}

public void setGender(String gender)
{
this.gender=gender;
}
public String getGender()
{
return this.gender;
}
public void setIsIndian(java.lang.Boolean isIndian)
{
this.isIndian=isIndian;
}
public boolean getIsIndian()
{
return this.isIndian;
}
public void setBasicSalary(java.math.BigDecimal basicSalary)
{
this.basicSalary=basicSalary;
}
public java.math.BigDecimal getBasicSalary()
{
return this.basicSalary;
}
public void setPANNumber(java.lang.String panNumber)
{
this.panNumber=panNumber;
}
public java.lang.String getPANNumber()
{
return this.panNumber;
}
public void setAadharCardNumber(java.lang.String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}
public String getAadharCardNumber()
{
return this.aadharCardNumber;
}
public int hashCode()
{
return employeeId.hashCode();
}
public boolean equals(Object object)
{
if(!(object instanceof EmployeeDTO)) return false;
EmployeeDTO employee=(EmployeeDTO)object;
return this.employeeId.equalsIgnoreCase(employee.employeeId);
}
public int compareTo(EmployeeDTO employee)
{
return this.employeeId.compareTo(employeeId);
}
}