package zakirskikh.model;

import zakirskikh.dao.PersonDao;

/**
 * Created by Anvar on 17/11/2016.
 */
public class Report {

    private int id;

    private String text;

    private int personId;

    public Report() {
    }

    public Report(String text, int personId) {
        this.id = id;
        this.text = text;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Person getPerson() {
        return PersonDao.get(personId);
    }
}
