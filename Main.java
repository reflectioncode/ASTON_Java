import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*Задание:
1. Написать класс Student в котором одно из полей будет List<Book>
2. Создать коллекцию классов Student (List, Set)
3. При помощи стримов получить коллекцию типа Book, получить из него стрим типа Integer (год выпуска книги) 
4. При помощи методов короткого замыкания получить объект типа Optional<Integer>
5. Вывести в консоль год выпуска книги, либо сообщение о том что книга с таким годом выпуска отсутствует */

public class Main {
    public static void main(String[] args) {

        class Book{
            private final String name;
            private final String author;
            private final int year;

            public Book(String name, String author, int year){
                this.name = name;
                this.author = author;
                this.year = year;
            }

            public String getName() {
                return name;
            }
            public String getAuthor() {
                return author;
            }
            public int getYear() {
                return year;
            }
        }

        class Student{
            private final String Name;
            private final List<Book> Books;

            Student(String name, List<Book> books) {
                Name = name;
                Books = books;
            }

            public String getName() {
                return Name;
            }

            public List<Book> getBooks(){
                return Books;
            }
        }

        List<Book> books = List.of(new Book("Мастер и Маргарита", "м.А. булгаков", 1999),
                new Book("Преступление и наказание", "Ф.М. Достоевский", 2000),
                new Book("Война и мир", "Л.Н. Толстой", 2005),
                new Book("Идиот", "Ф.М. Достоевский", 2006),
                new Book("Горе от ума", "А.С. Грибоедов", 2000));

        List<Student> studentsList = List.of(new Student("Кирилл", List.of(books.get(0), books.get(1))),
                new Student("Кирилл", List.of(books.get(1), books.get(2))),
                new Student("Кирилл", List.of(books.get(2), books.get(3))),
                new Student("Кирилл", List.of(books.get(3), books.get(4))),
                new Student("Кирилл", List.of(books.get(4), books.get(0))));

        Set<Student> studentsSet = Set.copyOf(studentsList);


        studentsSet
                .stream()
                .map(Student::getBooks)
                .flatMap(Collection::stream)
                .map(Book::getYear)
                .filter(x -> x == 2007)
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Книги с данным годом отсуствуют"));

    }
}