package domain.model;

import domain.db.DbException;
import domain.db.PersonDb;
import domain.model.Person;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.sql.*;
import java.util.*;

public class PersonDbSql implements PersonDb {
    private Properties properties;
    private String url;

    public PersonDbSql(Properties p) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = p;
            this.url = properties.getProperty("url");
            System.out.println(url);
        } catch (ClassNotFoundException e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public Person get(String personId) {
        Person person = null;
        if (personId == null) {
            throw new DbException("No id given");
        }
        String sql = "SELECT userid, email, passwd, firstname,lastname,admin FROM \"person\" WHERE userid = ?::varchar;";
        try (
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, personId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String userid = result.getString("userid");
                String email = result.getString("email");
                String passwd = result.getString("passwd");
                Role r = Role.USER;
                System.out.println(r.toString());
                if(result.getString("admin") != null &&
                        result.getString("admin").equals("t")){
                    r = Role.ADMIN;
                    System.out.println(r.toString());
                }
                try {    // validation of data stored in database
                    person = new Person(userid, email, passwd, firstname, lastname, r);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.println("foutje in PersonDBSql");
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return person;
    }

    @Override
    public List<Person> getAll(String order) {
        ArrayList<Person> persons = new ArrayList<>();
        String sql = "SELECT firstname, lastname, userid, passwd, email FROM \"person\"";

        if(!order.equals("")) {
            sql += " ORDER BY " + order;
        }

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement(sql);
            ) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String userid = result.getString("userid");
                String email = result.getString("email");
                String passwd = result.getString("passwd");
                try {    // validation of data stored in database
                    Person person = new Person(userid, email, passwd, firstname, lastname);
                    persons.add(person);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return persons;
    }

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("No person given");
        }
        String sql = "INSERT INTO person(userid, email, passwd, firstname, lastname)" +
                " VALUES (?,?,?,?,?)";
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement(sql);
            ) {
            statement.setString(1,person.getUserid());
            statement.setString(2, person.getEmail());
            statement.setString(3,person.getPassword());
            statement.setString(4, person.getFirstName());
            statement.setString(5,person.getLastName());

            statement.execute();
        } catch (SQLException e){
            throw new DbException("query failed", e);
        }
    }

    @Override
    public void update(Person person) {
        if (person == null) {
            throw new DbException("No person given");
        }
        String sql = "UPDATE person SET " +
                "email=?, passwd=?, firstname=?, lastname=?" +
                "WHERE userid=?;";
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            //still returns error fix needed
            statement.setString(1, person.getEmail());
            statement.setString(2,person.getPassword());
            statement.setString(3, person.getFirstName());
            statement.setString(4, person.getLastName());
            statement.setString(5, person.getUserid());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String personId) {
        if (personId.equals("")) {
            throw new DbException("No person given");
        }
        String sql = "DELETE FROM person WHERE userid =?;";
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            //still returns error fix needed
            statement.setString(1, personId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public int getNumberOfPersons() {
        int amount = 0;

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                Statement statement = connection.createStatement();
        ) {
            //still returns error fix needed
            ResultSet res = statement.executeQuery("SELECT count(userid) from person");
            while(res.next()){
                amount = Integer.parseInt(res.getString("amount"));
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage(), e);
        }
        return amount;
    }

    @Override
    public ArrayList<String> getHeaders(){
        String sql = "SELECT * FROM person";
        ArrayList<String> res = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i = 1; i <= rsmd.getColumnCount();i++){
                res.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage(), e);
        }
        if(res.contains("passwd")) {
            res.remove("passwd");
        }
        if(res.contains("admin")) {
            res.remove("admin");
        }
        return res;
    }
}