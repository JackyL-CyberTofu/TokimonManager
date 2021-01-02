package ca.cmpt213.asn4.web.controller;

import ca.cmpt213.asn4.web.models.Tokimon;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.lang.Object;

/**
 *  Responsible for the REST API
 *  Developer: Jacky Lim
 */


@RestController
public class TokimonController {
    private final AtomicLong counter = new AtomicLong();
    private List<Tokimon> people = new ArrayList<>();

    //@RequestMapping(value = "/person", method = RequestMethod.GET)
    @GetMapping("/api/tokimon/all")
    public List<Tokimon> getTokimon() throws IOException {
        System.out.println("Executing @GetMapping(\"/person\")");
        System.out.println(System.getProperty("user.dir"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(System.getProperty("user.dir")+"\\tokimon.json").toFile(), people);

        return people;
    }

    @GetMapping("/api/tokimon/{id}")
    public Tokimon getTokimon(@PathVariable long id){
        // Get Person pid
        System.out.println("GET Tokimon " + id);
        for (Tokimon eachTokimon : people){
            if (eachTokimon.getPid() == id) {
                return eachTokimon;
            }
        }
        return null;
    }

    @PostMapping("/api/tokimon/add")
    public void addPerson(@RequestBody Tokimon newPerson, HttpServletResponse response) throws IOException {
        // ... create a new Person
        System.out.println("/api/tokimon/add");
        newPerson.setPid((int) counter.incrementAndGet());
        people.add(newPerson);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(System.getProperty("user.dir")+"\\tokimon.json").toFile(), people);
        response.setStatus(201);
    }

    @PostMapping("/api/tokimon/change/{id}")
    public void changeTokimon(@PathVariable long id, @RequestBody Tokimon newPerson, HttpServletResponse response) throws IOException {
        // ... create a new Person
        System.out.println("/api/tokimon/change/");
        System.out.println("changing " + id);
        System.out.println("new color is "+ newPerson.getColor());
        for (Tokimon eachTokimon : people) {
            if (eachTokimon.getPid() == id){
                System.out.println("obtained element with id=" + eachTokimon.getPid());

                eachTokimon.setName(newPerson.getName());
                eachTokimon.setWeight(newPerson.getWeight());
                eachTokimon.setHeight(newPerson.getHeight());
                eachTokimon.setAbility(newPerson.getAbility());
                eachTokimon.setStrength(newPerson.getStrength());
                eachTokimon.setColor(newPerson.getColor());
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(System.getProperty("user.dir")+"\\tokimon.json").toFile(), people);
        response.setStatus(201);
    }

    @DeleteMapping("/api/tokimon/{pid}")
    public void deletePerson(@PathVariable long pid, HttpServletResponse response){
        people.remove(getTokimon(pid));
        response.setStatus(204);
        //return false;
    }

    @PostConstruct
    public void init(){
        System.out.println("POST CONSTRUCT CODE");
        //Tokimon p1 = new Tokimon("timothy", 23,34,"fire",100,"purple");
        //Tokimon p2 = new Tokimon("bobby2", 56,54,"water",94,"pink");
        //Tokimon p3 = new Tokimon("bobby3", 43,45,"air",23,"red");
        //p1.setPid(counter.incrementAndGet());
        //p2.setPid(counter.incrementAndGet());
        //p3.setPid(counter.incrementAndGet());
        //people.add(p1);
        //people.add(p2);
        //people.add(p3);

    }
}