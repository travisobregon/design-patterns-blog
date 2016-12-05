package database;

import org.junit.Test;
import static org.junit.Assert.*;

public class QueryBuilderTest {
    @Test
    public void it_can_build_a_simple_select_statement() {
        DB DB = new DB();

        String query = DB.query()
                         .select("*")
                         .from("users")
                         .getQuery();

        assertEquals("select * from users", query.trim());
    }

    @Test
    public void it_can_build_a_select_statement_with_a_where_clause() {
        DB DB = new DB();

        String query = DB.query()
                         .select("*")
                         .from("posts")
                         .where("published_at", "is not", null)
                         .getQuery();

        assertEquals("select * from posts where published_at is not null", query.trim());
    }

    @Test
    public void it_can_build_a_select_statement_with_a_where_clause_and_an_order_by() {
        DB DB = new DB();

        String query = DB.query()
                         .select("*")
                         .from("posts")
                         .where("published_at", "is not", null)
                         .orderBy("published_at", "desc")
                         .getQuery();

        assertEquals("select * from posts where published_at is not null order by published_at desc", query.trim());
    }
}