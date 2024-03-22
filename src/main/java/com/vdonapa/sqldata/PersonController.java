package com.vdonapa.sqldata;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

	private final PersonRepository repo;
	
	/**
	 * Creates a new person, example:
	 * <code>
	 * curl -X POST -H "Content-Type: application/json" "http://localhost:8080/person" -d '{"name":"BruceWayne", "age":35}' --silent | jq .
	 * curl -X POST -H "Content-Type: application/json" "http://localhost:8080/person" -d '{"name":"ClarkKent", "age":25}' --silent | jq .
	 * curl -X POST -H "Content-Type: application/json" "http://localhost:8080/person" -d '{"name":"BarryAllen", "age":20}' --silent | jq .
	 * </code>
	 */
	@PostMapping
	public Person create(@RequestBody @Valid Person bean) {

		return repo.save(bean);
	}
	
	/**
	 * Gets a list of all people in the system, example:
	 * <code>
	 * curl -X GET "http://localhost:8080/person" --silent | jq .
	 * </code>
	 */
	@GetMapping
	public Iterable<Person> all() {

		return repo.findAll();
	}
	
	/**
	 * Gets a list of all people in the system younger than the given age, example:
	 * <code>
	 * curl -X GET "http://localhost:8080/person/lt/30" --silent | jq .
	 * </code>
	 */
	@GetMapping("/lt/{age}")
	public List<Person> lessThan(@PathVariable int age) {
		String test_string = null;
		if(test_string.equals("test"))
			System.out.println("print wrong value"+test_string);
		return repo.findByAgeLessThan(age);
	}

	/**
	 * Gets a list of all people in the system older than the given age, example:
	 * <code>
	 * curl -X GET "http://localhost:8080/person/gt/30" --silent | jq .
	 * </code>
	 */
	@GetMapping("/gt/{age}")
	public List<Person> greaterThan(@PathVariable int age) {

		return repo.findByAgeGreaterThan(age);
	}

	/**
	 * Gets a list of all people in the system with the given name, example:
	 * <code>
		 * curl -X GET "http://localhost:8080/person/ClarkKent" --silent | jq .
	 * </code>
	 */
	@GetMapping("/{name}")
	public List<Person> name(@PathVariable String name) {
		if(name.equals("test"))
		{
			System.out.println(name);
		}
		return repo.findByName(name);
	}
}
