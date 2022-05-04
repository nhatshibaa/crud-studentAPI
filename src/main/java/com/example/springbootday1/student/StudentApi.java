package com.example.springbootday1.student;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentApi {
    private static ArrayList<Student> listStudent;
    private static Logger logger = Logger.getLogger(StudentApi.class.getName());

    static {
        listStudent = new ArrayList<>();
        listStudent.add(new Student(1, "Hoàng", 19, "http://avatar.png", 1));
        listStudent.add(new Student(2, "Nam", 20, "http://avatar.png", 1));
        listStudent.add(new Student(3, "Minh", 21, "http://avatar.png", 1));
        listStudent.add(new Student(4, "Thanh", 18, "http://avatar.png", 1));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<Student> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1") int limit) {
        logger.log(Level.INFO, String.valueOf(page));
        logger.log(Level.INFO, String.valueOf(limit));
        return listStudent;
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public Student finById(@PathVariable int id) {
        Student found = null;
        for (Student student :
                listStudent) {
            if (student.getId() == id) {
                found = student;
                break;
            }
        }
        return found;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Student save(@RequestBody Student student) {
        listStudent.add(student);
        return student;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public Student update(@PathVariable int id,@RequestBody Student updateStudent){
        for (Student student:
             listStudent) {
            if (student.getId()==id){
                student.setName(updateStudent.getName());
                student.setAge(updateStudent.getAge());
            }
        }
        return updateStudent;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public String deleteById(@PathVariable int id) {
        int foundIndex = -1;
        for (int i = 0; i < listStudent.size(); i++) {
            if (listStudent.get(i).getId() == 1) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex != -1) {
            listStudent.remove(foundIndex);
        }
        return "Delete Success";
    }
}
