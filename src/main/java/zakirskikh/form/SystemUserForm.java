package zakirskikh.form;

import zakirskikh.model.Hotel;
import zakirskikh.model.SystemUserRole;

/**
 * Created by Anvar on 16/11/2016.
 */
public class SystemUserForm {

    private int id;

    private String email;

    private String password;

    private int role = 2;

    private String firstName;

    private String lastName;

    public SystemUserForm() {
    }

    public SystemUserForm(String email, String password, int role) {
        this.email = email;
        this.password = password;
        this.role = role;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
