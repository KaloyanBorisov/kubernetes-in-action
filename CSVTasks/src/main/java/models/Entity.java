package models;


import lombok.Getter;
import lombok.Setter;

public class Entity {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String gender;

    @Getter
    @Setter
    private String department;

    @Getter
    @Setter
    private String jobTitle;

    @Getter
    @Setter
    private double salary;

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", id, name, gender, department, jobTitle, salary );
    }
}
