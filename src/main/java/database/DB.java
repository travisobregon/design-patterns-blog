package database;

public class DB {
    private QueryBuilder queryBuilder;

    public DB() {
        this.queryBuilder = new QueryBuilder();
    }

    public QueryBuilder query() {
        this.queryBuilder = new QueryBuilder();

        return this.queryBuilder;
    }
}
