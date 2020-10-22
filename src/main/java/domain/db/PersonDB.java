package domain.db;

import domain.model.Person;

import java.util.List;

public interface PersonDB {
    /**
     * Stores the given person
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the give country can not be added
     */
    void add(Person person);

    /**
     * Returns a list with all the persons stored in the database
     * @return an arraylist with all countries stored in the database
     * @throws DbException if something went wrong
     */
    List<Person> getAll();

    Person get(String userid);

    void changeMail(String userid, String newMail);
}
