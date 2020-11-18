package stunning.programmer.hr.dl;
import java.sql.*;
import java.sql.Driver;
public class DAOConnection{
    private DAOConnection(){}
    static public Connection getConnection() throws DAOException{
        Connection connection=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tmdb","root","sandeepmakwana@123");
            return connection;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

}