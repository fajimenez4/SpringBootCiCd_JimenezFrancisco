package ec.edu.espe.springlab1.web.controller;

import ec.edu.espe.springlab1.domain.Student;
import ec.edu.espe.springlab1.dto.StudentCreateRequest;
import ec.edu.espe.springlab1.dto.StudentResponse;
import ec.edu.espe.springlab1.dto.StudentUpdateRequest;
import ec.edu.espe.springlab1.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    //Inyeccion de dependencia
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    //Crear estudiante
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));

    }

    //Obtener estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    //Obtener todos los estudiantes
    @GetMapping
    public ResponseEntity<Page<StudentResponse>> getAllStudents(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(service.list(name, PageRequest.of(page, size)));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<StudentResponse> deactivateStudent(@PathVariable Long id) {
        return ResponseEntity.ok(service.deactivate(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id,
                                                         @Valid @RequestBody StudentUpdateRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @GetMapping("/")
    public String health() {
        return "Aplicación Spring Boot CI/CD funcionando correctamente";
    }
}
