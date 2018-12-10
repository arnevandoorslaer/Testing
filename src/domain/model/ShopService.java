package domain.model;

import domain.db.PersonDb;
import domain.db.PersonDbInMemory;
import domain.db.ProductDb;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ShopService {
    private PersonDb personDb;
    private ProductDb productDb;

    public ShopService(Properties p) {
        personDb = new PersonDbSql(p);
        productDb = new ProductDbSql(p);
    }

    public Person getPerson(String personId) {
        return getPersonDb().get(personId);
    }

    public List<Person> getPersons(String order) {
        return getPersonDb().getAll(order);
    }

    public void addPerson(Person person) {
        getPersonDb().add(person);
    }

    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }

    private PersonDb getPersonDb() {
        return personDb;
    }

    public ArrayList<String> getHeaders(){
        return getPersonDb().getHeaders();
    }

    public Product getProduct(String productId) {
        return getProductDb().get(Integer.parseInt(productId));
    }

    public List<Product> getProducts() {
        return getProductDb().getAll();
    }

    public void addProduct(Product p) {
        getProductDb().add(p);
    }

    public void updateProduct(Product p) {
        getProductDb().update(p);
    }

    public void deleteProduct(String id) {
        getProductDb().delete(Integer.parseInt(id));
    }

    private ProductDb getProductDb() {
        return productDb;
    }
}
