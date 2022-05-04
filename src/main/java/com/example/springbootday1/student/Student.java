package com.example.springbootday1.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private int id;
    private String name;
    private int age;
    private String avatar;
    private int status;
}
