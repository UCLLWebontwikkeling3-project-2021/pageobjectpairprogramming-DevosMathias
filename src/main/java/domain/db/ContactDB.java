package domain.db;

import domain.model.Contact;

import java.util.List;

public interface ContactDB {

    /**
     * Stores the given contact
     * @param contact The contact to be added
     * @throws DbException if the contact is null
     * @throws DbException if the person cannot be added
     */
    void add(Contact contact);

    void delete(Contact contact);

    /**
     * Returns a list of all the contacts stored in the database
     * @return an Arraylist of all the contacts stored in the database
     * @throws DbException if something went wrong
     */
    List<Contact> getAll();

    /**
     * Returns a list of al the unique contacts stored in the database
     * @return an Arraylist of all t he unique contacts stored in the database
     * @throws DbException if something went wrong
     */
    List<Contact> getUnique();
}