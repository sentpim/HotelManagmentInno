package zakirskikh.model;

/**
 * Created by Anvar on 15/11/2016.
 */
public enum Gender {

    MALE(0),
    FEMALE(1);

    private int genderId;

    Gender(int genderId) {
        this.genderId = genderId;
    }

    public int getGenderId() {
        return genderId;
    }

    public static Gender getGender(int genderId) {
        switch (genderId) {
            case 0:
                return MALE;
            case 1:
            default:
                return FEMALE;
        }
    }
}
