package domain.model;

import domain.db.DbException;
import domain.db.ProductDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDbSql implements ProductDb {
    private Properties properties;
    private String url;

    public ProductDbSql(Properties p) {


        try {
            Class.forName("org.postgresql.Driver");
            this.properties = p;
            this.url = properties.getProperty("url");
            System.out.println(properties.getProperty("user"));
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public Product get(int id) {
        Product product = null;
        String sql = "SELECT  id, name, description, price FROM product where id =?;";
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, String.valueOf(id));
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int identification = Integer.parseInt(result.getString("id"));
                String name = result.getString("name");
                String description = result.getString("description");
                double price = Double.parseDouble(result.getString("price"));


                try {
                    product = new Product(identification, name, description, price);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            throw new DomainException(e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        Product product = null;
        ArrayList<Product> products = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                Statement statement = connection.createStatement();
        ) {
            ResultSet result = statement.executeQuery("SELECT id, name, description, price FROM product");

            while (result.next()) {
                int identification = Integer.parseInt(result.getString("id"));
                String name = result.getString("name");
                String description = result.getString("description");
                double price = Double.parseDouble(result.getString("price"));


                try {
                    product = new Product(identification, name, description, price);
                    products.add(product);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            throw new DomainException(e.getMessage());
        }
        return products;
    }

    @Override
    public void add(Product product) {
        if (product == null) {
            throw new DbException("No product given");
        }
        String sql = "INSERT INTO product(id, name, description, price) VALUES " +
                "(?, ?, ?, ?::double precision );";
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            //still returns error fix needed
            statement.setString(1,String.valueOf(product.getProductId()));
            statement.setString(2,product.getName());
            statement.setString(3,product.getDescription());
            statement.setString(4,String.valueOf(product.getPrice()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new DbException("No product given");
        }
        String sql = "UPDATE product " +
                "SET name =?, description =?, price =?::double precision "+
                "WHERE id =?";
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            //still returns error fix needed
            statement.setString(4, String.valueOf(product.getProductId()));
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setString(3, String.valueOf(product.getPrice()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM product WHERE id=?;";
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            //still returns error fix needed
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public int getNumberOfProducts() {
        int amount = 0;
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                Statement statement = connection.createStatement();
        ) {
            //still returns error fix needed
            ResultSet res = statement.executeQuery("SELECT count(id) from product");
            while (res.next()) {
                amount = Integer.parseInt(res.getString("amount"));
            }
        } catch (SQLException e) {
            throw new DbException("query failed", e);
        }
        return amount;
    }
}

