package ec.edu.espe.springlab1.service.impl;

import ec.edu.espe.springlab1.domain.Student;
import ec.edu.espe.springlab1.dto.StudentCreateRequest;
import ec.edu.espe.springlab1.dto.StudentResponse;
import ec.edu.espe.springlab1.dto.StudentUpdateRequest;
import ec.edu.espe.springlab1.repository.StudentRepository;
import ec.edu.espe.springlab1.service.StudentService;
import ec.edu.espe.springlab1.web.advice.ConflictException;
import ec.edu.espe.springlab1.web.advice.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    //Inyeccion de dependencia
    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public StudentResponse create(StudentCreateRequest request) {
        if(repo.existsByEmail(request.getEmail())){
            throw new ConflictException("El email ya esta registrado");
        }
        Student s = new Student();
        s.setFullName(request.getFullName());
        s.setEmail(request.getEmail());
        s.setBirthDate(request.getBirthday());
        s.setActive(true);

        Student saved = repo.save(s);
        return toResponse(saved);
    }

    @Override
    public StudentResponse getById(Long id) {

        Student s = repo.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        return toResponse(s);
    }

    @Override
    public List<StudentResponse> list() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }


    @Override
    public StudentResponse deactivate(Long id) {
        Student s = repo.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        s.setActive(false);
        return toResponse(repo.save(s));
    }

    public Page<StudentResponse> list(String name, Pageable pageable) {
        return repo.findByFullNameContainingIgnoreCase(name, pageable)
                .map(this::toResponse);
    }

    @Override
    public StudentResponse update(Long id, StudentUpdateRequest req) {
        Student s = repo.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        s.setFullName(req.getFullName());
        s.setEmail(req.getEmail());
        s.setBirthDate(req.getBirthDate());
        return toResponse(repo.save(s));
    }

    private StudentResponse toResponse(Student s) {
        StudentResponse r = new StudentResponse();
        r.setId(s.getId());
        r.setFullName(s.getFullName());
        r.setEmail(s.getEmail());
        r.setBirthDay(s.getBirthDate());
        r.setActive(s.getActive());
        return r;
    }
}
