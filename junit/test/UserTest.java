package units;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserTest extends TestCase {

    @Test
    public void testGetAllUsers() {

        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        // актуальное значение
        List<User> actual = new ArrayList<>();
        actual.add(user1);
        actual.add(user2);
        actual.add(user3);

        // ожидаемое значение
        List<User> expected = User.getAllUsers();

        // проверка на их совпадение
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllUsersMale() {

        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        List<User> actual = new ArrayList<>();
        actual.add(user1);
        actual.add(user2);
        // вызовет ошибку
        // actual.add(user3);

        List<User> expected = User.getAllUsers(Sex.MALE);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllUsersFemale() {

        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        List<User> actual = new ArrayList<>();
        actual.add(user3);

        List<User> expected = User.getAllUsers(Sex.FEMALE);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCountMale() {
        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        int actual = 2;

        // для провала теста
        // int actual = 1;

        int expected = User.getCount(Sex.MALE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCountFemale() {
        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        int actual = 1;

        int expected = User.getCount(Sex.FEMALE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCount() {

        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        int actual = 3;
        int expected = User.getCount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetSumAgeUsers() {
        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        int actual = 93;
        // для ошибки
        // int actual = 90;
        int expected = User.getSumAgeUsers();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetSumAgeUsersMale() {
        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        int actual = 77;
        int expected = User.getSumAgeUsers(Sex.MALE);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetSumAgeUsersFemale() {
        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        int actual = 16;
        int expected = User.getSumAgeUsers(Sex.FEMALE);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAvgAgeUsers() {
        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        double actual = (45 + 32 + 16) / 3;

        double expected = User.getAvgAgeUsers();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAvgAgeUsersMale() {
        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        double actual = (45 + 32 ) / 2;

        double expected = User.getAvgAgeUsers(Sex.MALE);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAvgAgeUsersFemale() {
        User user1 = new User("Сергей", 45, Sex.MALE);
        User user2 = new User("Иван", 32, Sex.MALE);
        User user3 = new User("Александра", 16, Sex.FEMALE);

        double actual = 16 / 1;

        double expected = User.getAvgAgeUsers(Sex.FEMALE);

        Assert.assertEquals(expected, actual);
    }

}
