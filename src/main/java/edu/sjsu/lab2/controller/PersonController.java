package edu.sjsu.lab2.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import edu.sjsu.lab2.dao.PersonDAO;
import edu.sjsu.lab2.dao.impl.PersonDAOImpl;
import edu.sjsu.lab2.model.Address;
import edu.sjsu.lab2.model.Person;

@Controller @RequestMapping("/person*")
public class PersonController {
	//private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonDAO personDAO;
	
	
	public PersonController(){
		personDAO = new PersonDAOImpl();
	}
	
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createPerson(@RequestParam("firstname") String firstName,
                                          @RequestParam("lastname") String lastName,
                                          @RequestParam("email") String email,
                                          @RequestParam Map<String, String> params) {
        Person person = new Person(firstName, lastName, email);
        person.setDescription(params.get("description"));
        if (params.containsKey("friends")) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        
        /*if (params.containsKey("organization")) {
            Organization org = new Organization();
            org.setId(Long.parseLong(params.get("organization")));
            person.setOrganization(org);
        }*/
        
        if (params.containsKey("street") && params.containsKey("city") &&
                params.containsKey("state") && params.containsKey("zip")) {
            Address address = new Address(params.get("street"), params.get("city"),
                    params.get("state"), params.get("zip"));
            person.setAddress(address);
        }

        try {
            personDAO.create(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<?> getPerson(@PathVariable("id") long id, @RequestParam String format) {
        try {
        	System.out.println("FORMAT:" + format);
            Person person = personDAO.read(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
	
    
    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> updatePerson(@PathVariable("id") long userId,
                                          @RequestParam("email") String email,
                                          @RequestParam Map<String, String> params) {
        Person person = new Person();
        person.setEmail(email);
        person.setId(userId);
        person.setDescription(params.get("description"));
        if (params.containsKey("friends")) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (params.containsKey("street") && params.containsKey("city") &&
                params.containsKey("state") && params.containsKey("zip")) {
            Address address = new Address(params.get("street"), params.get("city"),
                    params.get("state"), params.get("zip"));
            person.setAddress(address);
        }

        /*if (params.containsKey("organization")) {
            Organization org = new Organization();
            org.setId(Long.parseLong(params.get("organization")));
            person.setOrganization(org);
        }*/

        try {
            Person updatedPerson = personDAO.update(person);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deletePerson(@PathVariable("id") long id) {
        try {
            personDAO.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
	

	
}
