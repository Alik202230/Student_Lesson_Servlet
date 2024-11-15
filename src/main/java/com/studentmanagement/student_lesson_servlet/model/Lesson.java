package com.studentmanagement.student_lesson_servlet.model;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Lesson {

  private int id;
  private String name;
  private Date duration;
  private String lecturerName;
  private double price;

  public Lesson(String name, Date duration, String lecturerName, double price) {
    this.name = name;
    this.duration = duration;
    this.lecturerName = lecturerName;
    this.price = price;
  }

  public Lesson(int id, String name, Date duration, String lecturerName, double price) {
    this.id = id;
    this.name = name;
    this.duration = duration;
    this.lecturerName = lecturerName;
    this.price = price;
  }

}
