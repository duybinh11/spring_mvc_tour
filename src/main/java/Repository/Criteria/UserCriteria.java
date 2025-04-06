package Repository.Criteria;

import Entity.CartEntity;
import Entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;


@Repository
public class UserCriteria {
    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity searchUsers(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> user = query.from(UserEntity.class);

        Predicate predicate = cb.equal(user.get("username"), username);
        query.select(user)
                .where(cb.and(predicate));

        try {
            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException ex) {
            throw new EmptyResultDataAccessException("username not found",1);
        }
    }

    public UserEntity userWithCartTotalMoneyThan(String username,int count) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> user = query.from(UserEntity.class);

        user.fetch("carts", JoinType.INNER);
        Join<UserEntity,CartEntity> cartJoin = user.join("carts");

        Predicate preUsername = cb.equal(user.get("username"), username);
        Predicate preTotalMoney = cb.greaterThan(cartJoin.get("quantity"), count);

        query.select(user).where(cb.and(preUsername, preTotalMoney));

        try {
            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException ex) {
            throw  new EmptyResultDataAccessException("not user name have quantuty > count",1);
        }
    }
}
