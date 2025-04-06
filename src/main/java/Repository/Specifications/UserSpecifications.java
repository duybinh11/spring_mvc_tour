package Repository.Specifications;

import Entity.CartEntity;
import Entity.UserEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<UserEntity> hasUsername(String username) {
        return (Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.equal(root.get("username"), username);
    }

    public static Specification<UserEntity> hasUsernameAndCount(String username, int count) {
        return (Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->{
            root.fetch("carts", JoinType.INNER);
            Join<UserEntity, CartEntity> cartJoin = root.join("carts");

            Predicate preUsername = cb.equal(root.get("username"), username);
            Predicate preTotalMoney = cb.greaterThan(cartJoin.get("quantity"), count);

            return cb.and(preUsername, preTotalMoney);
        };
    }

}
