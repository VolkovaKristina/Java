package units;

import java.util.*;

public class User {

    private int id;
    private String name;
    private int age;
    private Sex sex;

    private static HashMap<Integer, User> allUsers;

    private static int countId = 0;

    public User(String name, int age, Sex sex) {
        this.name = name;
        this.sex = sex;
        this.age = age;

        if (allUsers == null){
            allUsers = new HashMap<Integer, User>();
        }

        if (!hasUser()){
            this.id = ++countId;
            allUsers.put(id, this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                name.equals(user.name) &&
                sex == user.sex;
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, age, sex);
    }

    private boolean hasUser(){
        for (User user : allUsers.values()){
            if (user.equals(this) && user.hashCode() == this.hashCode()){
                return true;
            }
        }
        return false;
    }

    public static List<User> getAllUsers(){
        return new ArrayList<>(allUsers.values());
    }

    public static List<User> getAllUsers(Sex sex){
        List<User> listAllUsers = new ArrayList<>();
        for (User user: allUsers.values()){
            if (user.sex == sex){
                listAllUsers.add(user);
            }
        }
        return listAllUsers;
    }

    public static int getCount() {
        return allUsers.size();
    }

    public static int getCount(Sex sex) {
        return getAllUsers(sex).size();
    }

    public static int getSumAgeUsers() {
        int counter = 0;
        for (User user: allUsers.values()) {
            counter += user.age;
        }
        return counter;
    }

    public static int getSumAgeUsers(Sex sex) {
        int counter = 0;
        for (User user: getAllUsers(sex)) {
            counter += user.age;
        }
        return counter;
    }

    public static double getAvgAgeUsers() {
        return getSumAgeUsers() / getCount();
    }

    public static double getAvgAgeUsers(Sex sex) {
        return getSumAgeUsers(sex) / getCount(sex);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
