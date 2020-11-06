package domain.db;

import util.DbConnectionService;
import domain.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDBSQL implements PersonDB {
    private Connection connection;
    private String schema;

    public PersonDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("Nothing to add.");
        }
        if (personExists(person)) {
            throw new DbException("User already exists");
        }
        String sql = String.format("INSERT INTO %s.person (userid, email, password, fname, lname) VALUES (?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, person.getUserid());
            statementSQL.setString(2, person.getEmail());
            statementSQL.setString(3, person.getPassword());
            statementSQL.setString(4, person.getFirstName());
            statementSQL.setString(5, person.getLastName());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private boolean personExists(Person person) {
        List<Person> persons = getAll();
        for (Person p : persons) {
            if (p.getUserid().equals(person.getUserid())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.person ORDER BY fname, lname", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String fname = result.getString("fname");
                String lname = result.getString("lname");

                Person person = new Person(userid, email, password, fname, lname);
                persons.add(person);
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return persons;
    }

    public Person get(String id) {
        if (id == null) {
            throw new DbException("No id given");
        }
        Person person = null;
        String sql = String.format("SELECT * FROM %s.person WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, id);
            ResultSet result = statementSQL.executeQuery();

            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String fname = result.getString("fname");
                String lname = result.getString("lname");

                person = new Person(userid, email, password, fname, lname);
            }

        } catch (SQLException e) {
            throw new DbException(e);
        }

        if (person == null) {
            throw new DbException("User with id: " + id + " does not exist");
        }
        return person;
    }

    public void changeMail(String userid, String newMail) {
        String sql = String.format("UPDATE %s.person SET email = ? WHERE userid = ?", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, newMail);
            statementSQL.setString(2, userid);
            statementSQL.execute();

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
