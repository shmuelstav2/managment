package project.com.managment.repositories;

import org.springframework.data.jpa.domain.Specification;
import project.com.managment.domain.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.function.Predicate;

public class UserSpecification implements Specification<User> {

    private final User criteria;

    public UserSpecification(User criteria) {
        this.criteria=criteria;
    }

    @Override
    public javax.persistence.criteria.Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    //@Override
    //public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        // create query/predicate here.


}