package app.models;

import database.Collection;
import database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Post extends Model {
    private Integer id;
    private User author;
    private String title;
    private String body;
    private Date publishedAt;

    public Post() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPublishedAt() {
        String pattern = "MM/dd/yyyy HH:mm:ss.SSS";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        LocalDateTime datetime = LocalDateTime.parse(simpleDateFormat.format(publishedAt), DateTimeFormatter.ofPattern(pattern));

        return datetime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public Model mapValue(ResultSet resultSet) throws SQLException {
        throw new UnsupportedOperationException("Need to implement");
    }

    @Override
    public Collection mapValues(ResultSet resultSet) throws SQLException {
        Collection<Post> collection = new Collection<>(new ArrayList<Post>());

        while (resultSet.next()) {
            Post post = new Post();

            post.setId(resultSet.getInt("id"));

            DB DB = new DB();
            User user = (User) DB.query().find("users", resultSet.getInt("user_id"), User.class);
            post.setAuthor(user);

            post.setTitle(resultSet.getString("title"));
            post.setBody(resultSet.getString("body"));
            post.setPublishedAt(resultSet.getTimestamp("published_at"));

            collection.addItem(post);
        }

        return collection;
    }
}
