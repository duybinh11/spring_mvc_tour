package Repository;


import Entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> , JpaSpecificationExecutor<UserEntity> {
    @EntityGraph(attributePaths = {"carts", "carts.item"})
    Optional<UserEntity> findById(int idUser);

    Optional<UserEntity> findByUsername(String username);


;

    @Query("SELECT p.name FROM UserEntity u " +
            "JOIN u.userHasRoles uhr " +
            "JOIN uhr.role r " +
            "JOIN r.hasPermissionSet rhp " +
            "JOIN rhp.permission p " +
            "WHERE u.username = :username")
    Optional<List<String>> findUserGetPermisstion(@Param("username") String username);

}
