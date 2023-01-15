package com.restApi.springRestApiDemo.restController;

import com.restApi.springRestApiDemo.entity.Student;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class ControllerDemo {

    List<Student> students = List.of(
            new Student("arkadyuti", "Sikdar"),
            new Student("Ratan", "Das"),
            new Student("Ritam", "Fakir")
        );

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(RequestEntity<String> request){

        System.out.println(request.getMethod() + "  ================  " + request.getUrl() + "   =================   " +
                LocalDateTime.now());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/jnsajn").build(true).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.add("location", location.getRawPath());


        ResponseEntity<String> entity = ResponseEntity.ok().headers(headers)
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body("Hello World");

        return entity;
    }

    @GetMapping("/students")
    public List<Student> getStudentList(){
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentList(@PathVariable int studentId){
        return students.get(studentId);
    }



}
