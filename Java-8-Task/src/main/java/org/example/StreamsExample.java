package org.example;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

public class StreamsExample {

    public static void main(final String[] args) {

        List<Author> authors = Library.getAuthors();

        banner("Authors information");
        // SOLVED With functional interfaces declared
        Consumer<Author> authorPrintConsumer = new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                System.out.println(author);
            }
        };
        authors
                .stream()
                .forEach(authorPrintConsumer);

        // SOLVED With functional interfaces used directly
        authors
                .stream()
                .forEach(System.out::println);

        banner("Active authors");
        // SOLVED With functional interfaces declared

        Predicate<Author> authorActivePredicate = new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.active;
            }
        };
        authors
                .stream()
                .filter(authorActivePredicate)
                .forEach(authorPrintConsumer);

        banner("Active authors - lambda");
        // SOLVED With functional interfaces used directly
        authors
                .stream()
                .filter(Author::isActive)
                .forEach(System.out::println);

        banner("Active books for all authors");
        // Solved With functional interfaces declared
        Function<Author, Stream<Book>> getBooksFromAuthor = new Function<Author, Stream<Book>>() {
            @Override
            public Stream<Book> apply(Author author) {
                return author.getBooks().stream();
            }
        };
        Predicate<Book> activeBooksPredicate = new Predicate<Book>() {
            @Override
            public boolean test(Book book) {
                return book.published;
            }
        };
        Consumer<Book> bookPrintConsumer = new Consumer<Book>() {
            @Override
            public void accept(Book book) {
                System.out.println(book);
            }
        };
        authors
                .stream()
                .flatMap(getBooksFromAuthor)
                .filter(activeBooksPredicate)
                .forEach(bookPrintConsumer);


        banner("Active books for all authors - lambda");
        // SOLVED With functional interfaces used directly
        authors
                .stream()
                .flatMap(author -> author.getBooks().stream())
                .filter(Book::isPublished)
                .forEach(System.out::println);
        banner("Average price for all books in the library");
        // TODO With functional interfaces declared

        ToDoubleFunction<Book> mapBookToPriceFunction = new ToDoubleFunction<Book>() {
            @Override
            public double applyAsDouble(Book book) {
                return book.price;
            }
        };
        OptionalDouble av = authors
                .stream()
                .flatMap(getBooksFromAuthor)
                .mapToDouble(mapBookToPriceFunction)
                .average();
        if (av.isPresent()) {
            System.out.println(av.getAsDouble());
        }
        banner("Average price for all books in the library - lambda");
        // SOLVED With functional interfaces used directly

        av = authors
                .stream()
                .flatMap(author -> author.getBooks().stream())
                .mapToDouble(Book::getPrice)
                .average();
        if (av.isPresent()) {
            System.out.println(av.getAsDouble());
        }
        banner("Active authors that have at least one published book");
        // SOLVED With functional interfaces declared
        Predicate<Author> authorHasActiveBookPredicate = new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                List<Book> books = author.getBooks();
                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).published) return true;
                }
                return false;
            }
        };
        authors
                .stream()
                .filter(authorActivePredicate)
                .filter(authorHasActiveBookPredicate)
                .forEach(authorPrintConsumer);
        banner("Active authors that have at least one published book - lambda");
        // SOLVED With functional interfaces used directly
        authors
                .stream()
                .filter(Author::isActive)
                .filter(author -> {
                    List<Book> books = author.getBooks();
                    for (int i = 0; i < books.size(); i++) {
                        if (books.get(i).published) return true;
                    }
                    return false;
                })
                .forEach(System.out::println);

    }

    private static void banner(final String m) {
        System.out.println("#### " + m + " ####");
    }
}


class Library {
    public static List<Author> getAuthors() {
        return Arrays.asList(
            new Author("Author A", true, Arrays.asList(
                new Book("A1", 100, true),
                new Book("A2", 200, true),
                new Book("A3", 220, true))),
            new Author("Author B", true, Arrays.asList(
                new Book("B1", 80, true),
                new Book("B2", 80, false),
                new Book("B3", 190, true),
                new Book("B4", 210, true))),
            new Author("Author C", true, Arrays.asList(
                new Book("C1", 110, true),
                new Book("C2", 120, false),
                new Book("C3", 130, true))),
            new Author("Author D", false, Arrays.asList(
                new Book("D1", 200, true),
                new Book("D2", 300, false))),
            new Author("Author X", true, Collections.emptyList()));
    }
}

class Author {
    String name;
    boolean active;
    List<Book> books;

    Author(String name, boolean active, List<Book> books) {
        this.name = name;
        this.active = active;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return name + "\t| " + (active ? "Active" : "Inactive");
    }
}

class Book {
    String name;
    int price;
    boolean published;

    Book(String name, int price, boolean published) {
        this.name = name;
        this.price = price;
        this.published = published;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPublished() {
        return published;
    }

    @Override
    public String toString() {
        return name + "\t| " + "\t| $" + price + "\t| " + (published ? "Published" : "Unpublished");
    }
}
