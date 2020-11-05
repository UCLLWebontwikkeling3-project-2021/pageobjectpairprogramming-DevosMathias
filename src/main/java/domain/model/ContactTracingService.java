package domain.model;

import domain.db.*;
import domain.model.Contact;
import domain.model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactTracingService {
	private Map<String, Person> persons = new HashMap<>();

	private PersonDB personDB = new PersonDBSQL();
	private ContactDB contactDB = new ContactDBSQL();

	
	public ContactTracingService() {
		Person administrator = new Person("admin", "admin@ucll.be", "t", "Ad", "Ministrator");
		//add(administrator);
	}

	//Methods for personDB
	public Person getPerson(String personId){
		/*if(personId == null){
			throw new DbException("No id given");
		}*/
		return personDB.get(personId);
		//return persons.get(personId);
	}
	
	public List<Person> getAllPersons(){
		/*return new ArrayList<Person>(persons.values());*/
		//System.out.println("test8881");
		return personDB.getAll();
	}

	public void addPerson(Person person){
		/*if(person == null){
			throw new DbException("No person given");
		}
		if (persons.containsKey(person.getUserid())) {
			throw new DbException("User already exists");
		}
		persons.put(person.getUserid(), person);*/
		personDB.add(person);
	}
	
	/*public void updatePerson(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(!persons.containsKey(person.getUserid())){
			throw new DbException("No person found");
		}
		persons.put(person.getUserid(), person);
	}*/
	
	/*public void deletePerson(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		persons.remove(personId);
	}

	public int getNumberOfPersons() {
		return persons.size();
	}*/

	public void changeMailPerson(String userid, String newMail) {
		personDB.changeMail(userid, newMail);
	}

	//methods for contact
	public void addContact(Contact contact) {
		contactDB.add(contact);
	}

	public List<Contact> getAllContacts() {
		return contactDB.getAll();
	}

	public List<Contact> getUniqueContact() {return contactDB.getUnique();}
}
