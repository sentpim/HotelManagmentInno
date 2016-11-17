package zakirskikh.form;


/**
 * Created by Anvar on 16/11/2016.
 */
public class SystemUserForm {

    private int id;

    private String email;

    private String password;

    private int roleId;

    private int personId;

    public SystemUserForm() {
    }

    public SystemUserForm(String email, String password, int roleId, int personId) {
        this.email = email;
        this.password = password;
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "SystemUserForm{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", personId=" + personId +
                '}';
    }
}
