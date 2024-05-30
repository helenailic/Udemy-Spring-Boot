package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //create constructor in class for injections
    //define private field for dependency
    private Coach myCoach;
    private Coach anotherCoach;

    //define constructor for dependency injection
    @Autowired //tells spring to inject the dependency
    //qualifier with beanid that you want to inject
    //@Qualifier("baseballCoach")  --> we decided to use the primary annotation on the track coach object
    //Qualifier annotation has higher priority than the Primary annotation
    //@Qualifier("cricketCoach") Coach theAnotherCoach

    //aquatic is the custom beanid
    public DemoController(@Qualifier("aquatic") Coach theCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
        //anotherCoach = theAnotherCoach;
    }

    /*
    @Autowired //autowired annotation allows us to name the method whatever we want
    public void setCoach(Coach theCoach) {
        myCoach = theCoach;
    }
     */


    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    /*
    @GetMapping("/check")
    public String check() {
        return "Comparing beans: myCoach == anotherCoach, " + (myCoach == anotherCoach); //this checks whether they reference same bean address
    }
     */
}
