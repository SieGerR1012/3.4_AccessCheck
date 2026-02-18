void main() {
    Scanner scanner = new Scanner(System.in);

    try {
        System.out.printf("Введите логин: ");
        String login = scanner.nextLine();

        System.out.printf("Введите пароль: ");
        String password = scanner.nextLine();

        User user = getUserByLoginAndPassword(login, password);

        validateUser(user);

        System.out.println("\nДоступ предоставлен!");

    } catch (UserNotFoundException | AccessDeniedException e) {
        System.err.println("Ошибка: " + e.getMessage());
    }
}

public static User[] getUsers() {
    return new User[]{
            new User("john", "pass123", "john@gmail.com", 24),
            new User("alice", "qwerty", "alice@mail.ru", 17),
            new User("admin", "admin", "admin@web.com", 30)
    };
}

public static User getUserByLoginAndPassword(String login, String password) throws UserNotFoundException {
    User[] users = getUsers();
    for (User user : users) {
        if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
            return user;
        }
    }
    throw new UserNotFoundException("Логин или пароль введены неверно.");
}

public static void validateUser(User user) throws AccessDeniedException {
    if (user.getAge() < 18) {
        throw new AccessDeniedException("Вам меньше 18 лет. Доступ запрещен.");
    }
}
