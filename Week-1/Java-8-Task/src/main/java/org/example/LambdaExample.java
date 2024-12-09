package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LambdaExample {

    public static void main(final String[] args) {

        final UsersRepository repository = new UsersRepository();

        banner("Listing all users");
        // SOLVED all users
        repository.select(null, null);

        banner("Listing all active users");
        // SOLVED With functional interfaces declared
        Predicate<User> activeUserPredicate = new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.active;
            }
        };
        repository.select(activeUserPredicate, null);

        banner("Listing all active users - lambda");
        // SOLVED With functional interfaces used directly
        repository.select(user -> user.active, null);
        
        banner("Listing users with age > 5 sorted by name");
        // SOLVED With functional interfaces declared
        Predicate<User> userAgePredicate = new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.age>5;
            }
        };
        Comparator<User> userByNameComparator=new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.compareTo(o2.name);
            }
        };
        repository.select(userAgePredicate,userByNameComparator);

        banner("Listing users with age > 5 sorted by name - lambda");
        // SOLVED With functional interfaces used directly
        repository.select(user -> user.age>5,Comparator.comparing(User::getName));

        banner("Listing users with age < 10 sorted by age");
        // SOLVED With functional interfaces declared
        Predicate<User> userAgePredicate2 = new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.age<10;
            }
        };
        Comparator<User> userByAgeComparator=new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.age, o2.age);
            }
        };
        repository.select(userAgePredicate2,userByAgeComparator);

        banner("Listing users with age < 10 sorted by age - lambda");
        // SOLVED With functional interfaces used directly
        repository.select(user -> user.age<10,Comparator.comparing(User::getAge) );

        banner("Listing active users sorted by name");
        // SOLVED With functional interfaces declared
        repository.select(activeUserPredicate,userByNameComparator);

        banner("Listing active users sorted by name - lambda");
        // SOLVED With functional interfaces used directly
        repository.select(user-> user.active,Comparator.comparing(User::getName));

        banner("Listing active users with age > 8 sorted by name");
        // SOLVED With functional interfaces declared
        Predicate<User> activeUerAgePredicate = new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.age>8&& user.active;
            }
        };
        repository.select(activeUerAgePredicate,userByNameComparator);
        banner("Listing active users with age > 8 sorted by name - lambda");
        // SOLVED With functional interfaces used directly
        repository.select(user-> user.active&&user.age>8,Comparator.comparing(User::getName));

    }

    private static void banner(final String m) {
        System.out.println("#### " + m + " ####");
    }
    
}

class UsersRepository {
    List<User> users;

    UsersRepository() {
        users = Arrays.asList(
            new User("Seven", 7, true),
            new User("Four", 4, false),
            new User("Eleven", 11, true),
            new User("Three", 3, true),
            new User("Nine", 9, false),
            new User("One", 1, true),
            new User("Twelve", 12, true));
    }

    void select(final Predicate<User> filter, final Comparator<User> order) {
        Stream<User> usersStream = users.stream();

        if (filter != null) {
            usersStream = usersStream.filter(filter);
        }
        if (order != null) {
            usersStream = usersStream.sorted(order);
        }
        usersStream.forEach(System.out::println);
    }
}

class User {
    String name;
    int age;
    boolean active;

    User(final String name, final int age, boolean active) {
        this.name = name;
        this.age = age;
        this.active = active;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    @Override
    public String toString() {
        return name + "\t| " + age;
    }
}