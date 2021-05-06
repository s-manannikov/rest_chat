package chat.service;

import chat.model.Person;
import chat.model.Role;
import chat.repository.PersonRepository;
import chat.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository persons;
    private final RoleRepository roles;

    public PersonService(PersonRepository persons, RoleRepository roles) {
        this.persons = persons;
        this.roles = roles;
    }

    public List<Person> findAllPersons() {
        return persons.findAll();
    }

    public Optional<Person> findPersonById(int id) {
        return persons.findById(id);
    }

    public Person createPerson(Person person) {
        Role role = roles.findByName("ROLE_USER");
        person.setRole(role);
        return persons.save(person);
    }

    public void deletePerson(Person person) {
        persons.delete(person);
    }
}
