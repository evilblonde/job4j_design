package ru.job4j.sync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class UserStorageImpl implements UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    public synchronized boolean update(User user) {
        return users.computeIfPresent(user.getId(), (k, v) -> users.put(k, user)) != null;
    }

    @Override
    public synchronized boolean delete(User user) {
        return users.remove(user.getId()) != null;
    }

    @Override
    public synchronized boolean transfer(int from, int to, int amount) {
        User sender = users.get(from);
        User recipient = users.get(to);
        if (sender == null || recipient == null || sender.getAmount() < amount) {
            return false;
        }
        sender.setAmount(sender.getAmount() - amount);
        recipient.setAmount(recipient.getAmount() + amount);
        return true;
    }
}
