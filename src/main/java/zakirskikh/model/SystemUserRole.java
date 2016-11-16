package zakirskikh.model;

/**
 * Created by Anvar on 15/11/2016.
 */
public enum SystemUserRole {

    SUPERUSER(0),
    ADMINISTRATOR(1),
    RECEPTION(2),
    USER(3);

    private int roleId;

    SystemUserRole(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public static SystemUserRole getSystemUserRole(int roleId) {
        switch (roleId) {
            case 0:
                return SUPERUSER;
            case 1:
                return ADMINISTRATOR;
            case 2:
            default:
                return RECEPTION;
        }
    }
}
