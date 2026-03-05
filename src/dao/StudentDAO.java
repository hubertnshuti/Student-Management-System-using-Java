package dao;

import database.DatabaseOperations;

public class StudentDAO implements DatabaseOperations {

    @Override
    public void add() {
        System.out.println("DAO: Adding student to database...");
    }

    @Override
    public void delete() {
        System.out.println("DAO: Deleting student from database...");
    }

    @Override
    public void update() {
        System.out.println("DAO: Updating student in database...");
    }

    @Override
    public void search(String keyword) {
        System.out.println("DAO: Searching for student with keyword: " + keyword);
    }
}