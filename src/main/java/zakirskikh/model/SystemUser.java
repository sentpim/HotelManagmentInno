package zakirskikh.model;

import zakirskikh.dao.EmployeeDao;
import zakirskikh.dao.PersonDao;
import zakirskikh.security.AuthProviderImpl;

/**
 * Created by Anvar on 15/11/2016.
 */
public class SystemUser {

    private int id;

    private String email;

    private String password;

    private SystemUserRole role;

    private int personId;

    private Person person;

    public SystemUser() {
    }

    public SystemUser(String email, String password, SystemUserRole role, int personId) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SystemUserRole getRole() {
        return role;
    }

    public void setRole(SystemUserRole role) {
        this.role = role;
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

    public Employee getEmployee() {
        if (!getRole().isAdmin())
            return null;

        return EmployeeDao.getByPersonId(personId);
    }

    public static SystemUser getCurrent() {
        return AuthProviderImpl.getCurrentUser();
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", personId=" + personId +
                '}';
    }
}
