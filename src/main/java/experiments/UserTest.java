package experiments;

public class UserTest {


    public static void main(String[] args) {
        User user = User.builder().name("Alice").age(30).build();
        user.getAge();

    }
}
