package ec.edu.espe.springlab1.service;

import ec.edu.espe.springlab1.domain.Student;
import ec.edu.espe.springlab1.dto.StudentCreateRequest;
import ec.edu.espe.springlab1.repository.StudentRepository;
import ec.edu.espe.springlab1.service.impl.StudentServiceImpl;
import ec.edu.espe.springlab1.web.advice.ConflictException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import({StudentServiceImpl.class})
public class StudentServiceTest {
    @Autowired
    private StudentServiceImpl service;

    @Autowired
    private StudentRepository repository;


    @Test
    void shouldNotAllowDuplicatedEmail() {

        Student existing = new Student();
        existing.setFullName("Existing");
        existing.setEmail("duplicated@example.com");
        existing.setBirthDate(LocalDate.of(2001, 12, 1));
        existing.setActive(true);

        repository.save(existing);


        StudentCreateRequest req = new StudentCreateRequest();
        req.setFullName("New User");
        req.setEmail("duplicated@example.com");
        req.setBirthday(LocalDate.of(2001, 12, 1));


        assertThatThrownBy(() -> service.create(req)).isInstanceOf(ConflictException.class);
    }

}
