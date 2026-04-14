package com.comp229.crud.service;

import com.comp229.crud.model.Student;
import com.comp229.crud.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllStudents_returnsAllStudents() {
        List<Student> mockStudents = Arrays.asList(
                new Student("Jane", "Smith", "jane@email.com", "Software Engineering"),
                new Student("John", "Doe", "john@email.com", "Computer Science")
        );
        when(studentRepository.findAll()).thenReturn(mockStudents);

        List<Student> result = studentService.getAllStudents();

        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void getStudentById_returnsStudent_whenFound() {
        Student mockStudent = new Student("Jane", "Smith", "jane@email.com", "Software Engineering");
        mockStudent.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(mockStudent));

        Optional<Student> result = studentService.getStudentById(1L);

        assertTrue(result.isPresent());
        assertEquals("Jane", result.get().getFirstName());
    }

    @Test
    void getStudentById_returnsEmpty_whenNotFound() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Student> result = studentService.getStudentById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void saveStudent_returnsSavedStudent() {
        Student student = new Student("Jane", "Smith", "jane@email.com", "Software Engineering");
        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.saveStudent(student);

        assertNotNull(result);
        assertEquals("jane@email.com", result.getEmail());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void deleteStudent_callsRepositoryDelete() {
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void emailExists_returnsTrue_whenEmailExists() {
        when(studentRepository.existsByEmail("jane@email.com")).thenReturn(true);

        assertTrue(studentService.emailExists("jane@email.com"));
    }

    @Test
    void emailExists_returnsFalse_whenEmailNotExists() {
        when(studentRepository.existsByEmail("new@email.com")).thenReturn(false);

        assertFalse(studentService.emailExists("new@email.com"));
    }
}
