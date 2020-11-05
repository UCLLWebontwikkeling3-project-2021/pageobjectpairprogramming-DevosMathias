package domain.db;

import domain.model.Person;

import java.util.List;

public interface PersonDB {
    /**
     * Stores the given person
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the give person can not be added
     */
    void add(Person person);

    /**
     * Returns a list with all the persons stored in the database
     * @return an arraylist with all persons stored in the database
     * @throws DbException if something went wrong
     */
    List<Person> getAll();

    /**
     * Returns the person which is searched for
     * @param userid The id of the person which is searched for
     * @return The person which is searched for
     * @throws DbException if userid is null
     * @throws DbException if something went wrong
     */
    Person get(String userid);

    /**
     * Changes the email of the person with the given id
     * @param userid The id of the person who's email needs to change
     * @param newMail The new email for the person
     * @throws DbException if something went wrong
     */
    void changeMail(String userid, String newMail);
}