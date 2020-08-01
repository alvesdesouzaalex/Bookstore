package br.com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bookstore.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(@Param("userName") String userName);

}
