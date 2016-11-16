package zakirskikh.model;

/**
 * Created by Anvar on 15/11/2016.
 */
public class Post {

    private int id;

    private String name;

    public Post() {
    }

    public Post(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
