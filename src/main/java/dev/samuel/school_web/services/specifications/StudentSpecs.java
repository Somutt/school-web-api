package dev.samuel.school_web.services.specifications;

import dev.samuel.school_web.entities.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecs {

    public static Specification<Student> nameLike(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
    }

    public static Specification<Student> registryEquals(String registry) {
        return ((root, query, criteriaBuilder) ->
            criteriaBuilder.equal(criteriaBuilder.upper(root.get("registry")), registry.toUpperCase()));
    }

    public static Specification<Student> gradeEquals(String grade) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.upper(root.get("grade")), grade.toUpperCase()));
    }
}
