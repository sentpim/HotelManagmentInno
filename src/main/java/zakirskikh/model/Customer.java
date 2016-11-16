package zakirskikh.model;

import zakirskikh.dao.PersonDao;

/**
 * Created by Anvar on 15/11/2016.
 */
public class Customer implements PersonGettable {

    private int id;

    private String password;

    private int personId;

    private Person person;

    public Customer() {
    }

    public Customer(String password, int personId) {
        this.password = password;
        this.personId = personId;
    }

    public Customer(int id, String password, int personId) {
        this(password, personId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Person getPerson() {
        return (person == null) ? person = PersonDao.get(personId) : person;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", personId=" + personId +
                '}';
    }
}
