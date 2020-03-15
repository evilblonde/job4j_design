package ru.job4j.sync;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenUserIsAbsentThenAllOperationsAreCorrect() {
        UserStorage storage = new UserStorageImpl();
        User user = new User(1, 100);
        assertFalse(storage.delete(user));
        assertFalse(storage.update(new User(1, 200)));
        assertTrue(storage.add(user));
    }

    @Test
    public void whenUserIsPresentAllOperationsAreCorrect() {
        UserStorage storage = new UserStorageImpl();
        User user = new User(1, 100);
        storage.add(user);
        assertFalse(storage.add(new User(1, 200)));
        assertTrue(storage.update(new User(1, 200)));
        assertTrue(storage.delete(user));
    }

    @Test
    public void whenNoSenderThenDoNotTransfer() {
        UserStorage storage = new UserStorageImpl();
        User user = new User(1, 100);
        storage.add(user);
        assertFalse(storage.transfer(2, 1, 50));
        assertEquals(100, user.getAmount());
    }

    @Test
    public void whenNoRecipientThenDoNotTransfer() {
        UserStorage storage = new UserStorageImpl();
        User user = new User(1, 100);
        storage.add(user);
        assertFalse(storage.transfer(1, 2, 50));
        assertEquals(100, user.getAmount());
    }

    @Test
    public void whenSenderDoesNotHaveEnoughMoneyThenDoNotTransfer() {
        UserStorage storage = new UserStorageImpl();
        User sender = new User(1, 20);
        User recipient = new User(2, 100);
        storage.add(sender);
        storage.add(recipient);
        assertFalse(storage.transfer(1, 2, 50));
        assertEquals(20, sender.getAmount());
        assertEquals(100, recipient.getAmount());
    }

    @Test
    public void whenTransferThenUsersHaveCorrectValues() {
        UserStorage storage = new UserStorageImpl();
        User sender = new User(1, 120);
        User recipient = new User(2, 100);
        storage.add(sender);
        storage.add(recipient);
        assertTrue(storage.transfer(1, 2, 50));
        assertEquals(70, sender.getAmount());
        assertEquals(150, recipient.getAmount());
    }
}