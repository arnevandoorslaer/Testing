package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.List;

public interface PersonDb {
    Person get(String personId);

    List<Person> getAll(String order);

    void add(Person person);

    void update(Person person);

    void delete(String personId);

    int getNumberOfPersons();

    ArrayList<String> getHeaders();
}
