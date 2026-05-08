package ec.edu.espe.springlab1.service;

import ec.edu.espe.springlab1.domain.Student;
import ec.edu.espe.springlab1.dto.StudentCreateRequest;
import ec.edu.espe.springlab1.repository.StudentRepository;
import ec.edu.espe.springlab1.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

@DataJpaTest
@Import({StudentServiceImpl.class})
public class StudentServiceTest {
    @Autowired
    private StudentServiceImpl service;

    @Autowired
    private StudentRepository repository;

    @Test
    void shouldNotFindStudentByEmail() {
        Student existing = new Student();
        existing.setFullName("Existing");
        existing.setEmail("duplicated@example.com");
        existing.setBirthDate(LocalDate.of(2003,04,26));
        existing.setActive(true);

        repository.save(existing);

        StudentCreateRequest req = new StudentCreateRequest();
        req.setFullName("New User");
        req.setEmail("duplicated@example");

    }
}
