package dam.davinci.tarea10.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonCrdbDaoTest {

    DbFactory database = new CockroachDatabase();
    PersonCrdbDao personCrdbDao = new PersonCrdbDao(database);
    Person person1;

    @BeforeEach
    void setUp() {
        person1 = new Person("Sinforoso", "45789345Y");
    }

    @Test
    void read(){
        assertDoesNotThrow(() -> {
            Person person = personCrdbDao.read("168b0aea-6a20-4527-b5db-619cb37710c0");
            System.out.println(person);
        });

    }

    @Test
    void readAll() {
        assertDoesNotThrow(() -> {
            List<Person> persons = personCrdbDao.readAll();
            System.out.println(persons);
        });
    }

    @Test
    void create() {
        assertDoesNotThrow(() -> {
            personCrdbDao.create(person1);
        });
    }

    @Test
    void update() {
        person1.setSurname("Casto");
        assertDoesNotThrow(() -> {
            personCrdbDao.update(person1);
        });
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> {
            List<Person> persons = personCrdbDao.readAll().stream().filter(p -> p.getName()
                    .equalsIgnoreCase("Sinforoso")).toList();
            Person delete = persons.get(0);
            personCrdbDao.delete(delete);
        });
    }
}