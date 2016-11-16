package zakirskikh.model;

/**
 * Created by Anvar on 15/11/2016.
 */
public class SystemUser {

    private int id;

    private String email;

    private String password;

    private SystemUserRole role;

    private int hotelId;

    public SystemUser() {
    }

    public SystemUser(String email, String password, SystemUserRole role, int hotelId) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.hotelId = hotelId;
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

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Hotel getHotel() {
        return null;
    }
}
