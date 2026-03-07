package database;

public interface DatabaseOperations {
    void add();
    void delete();
    void update();
    void search(String keyword);
}