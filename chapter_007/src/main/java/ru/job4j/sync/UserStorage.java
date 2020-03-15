package ru.job4j.sync;

public interface UserStorage {

    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    boolean transfer(int from, int to, int amount);

}
