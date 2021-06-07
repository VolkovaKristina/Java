import java.util.*;

public class User {
    static int id; // уникакальный идентификатор
    static String name; // имя
    static String sex; //пол
    static int age; // количество лет

    public static User (int id, string name, int id, int age, string sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;

        if (allUsers == null){
            HashMap<Integer> allUsers = new HashMap<>();
        }
    //Для проверки того, есть ли в нашей HashMap хотя бы один элемент
        if (!id.isEmpty()) {
            allUsers.put(id, this);
        }

        @Override
        public int hashCode(){
            return Objects.hash(name, age, sex);
        }

        public static List<User> getAllUsers(){
            ArrayList<> users = allUsers ArrayList<>();
            for (Integer id : this.sex.keySet()){
                if (this.sex.get(id).equals(sex)){
                    users.add(id);
                }
            }
            ArrayList<String> usersNames = new ArrayList<>();
            for (Integer idUser : users){
                usersNames.add(name.get(idUser));
            }
            for (Map.Entry entry: id.entrySet()) {
                System.out.println("Id");
            }
            for (Map.Entry entry: name.entrySet()) {
                System.out.println("Name");
            }
            @Override
            public String toString() {
                return "User{" + "id=" + id + ", name='" + name + '\'' + ", sex=" + sex + ", age=" + age + '}';
            }

        }
        }
}