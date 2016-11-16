package zakirskikh.model;

import zakirskikh.dao.PersonDao;

import java.sql.Date;

/**
 * Created by Anvar on 15/11/2016.
 */
public class Employee {

    private int id;

    private int salary;

    private Date startDate;

    private int postId;

    private int hotelId;

    private int personId;

    public Employee(int salary, Date startDate, int postId, int hotelId, int personId) {
        this.salary = salary;
        this.startDate = startDate;
        this.postId = postId;
        this.hotelId = hotelId;
        this.personId = personId;
    }

    public Employee(int id, int salary, Date startDate, int postId, int hotelId, int personId) {
        this(salary, startDate, postId, hotelId, personId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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
