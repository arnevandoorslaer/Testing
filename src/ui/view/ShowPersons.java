package ui.view;
import java.sql.SQLException;

import domain.model.PersonDbSql;
import domain.model.Person;

public class ShowPersons {
    public static void main(String[] args) throws SQLException {
    /*    Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX33?currentSchema=r0706266";
        properties.setProperty("user", "r0706266");//"local_r0706266");
        properties.setProperty("password", "Cukumore1");//")bVahsj&VàH7i(ù");
        //Secret.setPass(properties);	// implements line 17 and 18
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode","prefer");

        Connection connection = DriverManager.getConnection(url,properties);
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery( "SELECT firstname, lastname, userid, email, password FROM \"person\"");

        while(result.next()){
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            String userid = result.getString("userid");
            String email = result.getString("email");
            String passwd = result.getString("password");
            try {	// validation of data stored in database
                Person person = new Person(userid, email, passwd, firstname, lastname);
                System.out.println(firstname + " " + lastname + ": " + userid + ", " + email);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        statement.close();
        connection.close();*/
        //PersonDbSql db = new PersonDbSql();
        //db.update(new Person("222222", "niewtest@test.com", "randompass", "testnieuwer", "testouderhuh"));
        //System.out.println(db.getAll());
    }
}


