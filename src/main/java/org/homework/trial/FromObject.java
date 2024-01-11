package org.homework.trial;

import java.time.LocalDate;

public class FromObject {
    private String name;
    private int age;
    private LocalDate birthday;

    public FromObject(String name, int age, LocalDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public FromObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
