public class Task {

    private String title;
    private String description;
    private String status;
    private int id;

    public Task() {
        this.title = "";
        this.description = "";
        this.id = 0;
        this.status = "NEW";
    }
    public Task(String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.status = "NEW";
    }

    // SETTER SECTION

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {

        this.id = id;
    }

    // GETTER SECTION

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public Task getTask() {
        return this;
    }
}

class SubTask extends Task {

}
