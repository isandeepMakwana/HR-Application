package stunning.programmer.hr.dl;
public class AdministrationDTO implements java.io.Serializable,Comparable<AdministrationDTO>{
    private String username;
    private String password;
    public AdministrationDTO(){
        this.username="";
        this.password="";
    }
    public void setUserName(String username){
        this.username=username;
    }
    public String getUserName(){
        return this.username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }
public boolean equals(Object object)
{
if(!(object instanceof AdministrationDTO)) return false;
AdministrationDTO other =(AdministrationDTO)object;
return this.username.equals(other.username);
}

public int compareTo(AdministrationDTO other)
{
return this.username.compareToIgnoreCase(other.username);
}
public int hashCode(){
return username.hashCode();
}
}