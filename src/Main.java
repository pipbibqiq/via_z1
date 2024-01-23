import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String lastName;
    private String firstName;
    private String birthDate;
    private String group;

    public Student(String lastName, String firstName, String birthDate, String group) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.group = group;
    }

    public Student() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}

class StudentGroup {
    private String groupName;
    private ArrayList<Student> students;

    public StudentGroup(String groupName) {
        this.groupName = groupName;
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student findStudentByLastName(String lastName) {
        for (Student student : students) {
            if (student.getLastName().equals(lastName)) {
                return student;
            }
        }
        return null;
    }

    public void sortByLastName() {
        Collections.sort(students, Comparator.comparing(Student::getLastName));
    }

    public Student getStudentByIndex(int index) {
        if (index >= 0 && index < students.size()) {
            return students.get(index);
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("StudentGroup: ").append(groupName).append("\n");
        for (Student student : students) {
            result.append("\t").append(student).append("\n");
        }
        return result.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, StudentGroup> groupMap = new HashMap<>();

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить студента");
            System.out.println("2. Удалить студента");
            System.out.println("3. Поиск студента по фамилии");
            System.out.println("4. Сортировка группы по фамилии");
            System.out.println("5. Вывести информацию о группе");
            System.out.println("6. Вывести список всех групп и студентов");
            System.out.println("0. Выйти из программы");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после ввода числа

            switch (choice) {
                case 1:
                    // Добавление студента
                    System.out.println("Введите фамилию студента:");
                    String lastName = scanner.nextLine();
                    System.out.println("Введите имя студента:");
                    String firstName = scanner.nextLine();
                    System.out.println("Введите дату рождения студента:");
                    String birthDate = scanner.nextLine();
                    System.out.println("Введите название группы студента:");
                    String groupName = scanner.nextLine();

                    Student newStudent = new Student(lastName, firstName, birthDate, groupName);

                    StudentGroup group = groupMap.get(groupName);
                    if (group == null) {
                        group = new StudentGroup(groupName);
                        groupMap.put(groupName, group);
                    }

                    group.addStudent(newStudent);
                    System.out.println("Студент добавлен в группу.");
                    break;
                case 2:
                    // Удаление студента
                    System.out.println("Введите фамилию студента для удаления:");
                    String removeLastName = scanner.nextLine();

                    System.out.println("Введите название группы для удаления студента:");
                    String removeGroupName = scanner.nextLine();

                    StudentGroup removeGroup = groupMap.get(removeGroupName);
                    if (removeGroup != null) {
                        Student removeStudent = removeGroup.findStudentByLastName(removeLastName);
                        if (removeStudent != null) {
                            removeGroup.removeStudent(removeStudent);
                            System.out.println("Студент удален из группы.");
                        } else {
                            System.out.println("Студент не найден в группе.");
                        }
                    } else {
                        System.out.println("Группа не найдена.");
                    }

                    break;
                case 3:
                    // Поиск студента по фамилии
                    System.out.println("Введите фамилию студента для поиска:");
                    String searchLastName = scanner.nextLine();

                    System.out.println("Введите название группы для поиска студента:");
                    String searchGroupName = scanner.nextLine();

                    StudentGroup searchGroup = groupMap.get(searchGroupName);
                    if (searchGroup != null) {
                        Student foundStudent = searchGroup.findStudentByLastName(searchLastName);
                        if (foundStudent != null) {
                            System.out.println("Найден студент: " + foundStudent);
                        } else {
                            System.out.println("Студент не найден в группе.");
                        }
                    } else {
                        System.out.println("Группа не найдена.");
                    }

                    break;
                case 4:
                    // Сортировка группы по фамилии
                    System.out.println("Введите название группы для сортировки:");
                    String sortGroupName = scanner.nextLine();

                    StudentGroup sortGroup = groupMap.get(sortGroupName);
                    if (sortGroup != null) {
                        sortGroup.sortByLastName();
                        System.out.println("Группа отсортирована по фамилии.");
                    } else {
                        System.out.println("Группа не найдена.");
                    }

                    break;
                case 5:
                    // Вывод информации о группе
                    System.out.println("Введите название группы для вывода информации:");
                    String displayGroupName = scanner.nextLine();

                    StudentGroup displayGroup = groupMap.get(displayGroupName);
                    if (displayGroup != null) {
                        System.out.println("Информация о группе:");
                        System.out.println(displayGroup);
                    } else {
                        System.out.println("Группа не найдена.");
                    }

                    break;
                case 6:
                    // Вывод списка всех групп и студентов
                    System.out.println("Список всех групп и студентов:");
                    for (StudentGroup studentGroup : groupMap.values()) {
                        System.out.println(studentGroup);
                    }
                    break;
                case 0:
                    // Выход из программы
                    System.out.println("Программа завершена.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Повторите попытку.");
            }
        }
    }
}