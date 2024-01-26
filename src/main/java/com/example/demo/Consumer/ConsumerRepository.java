package com.example.demo.Consumer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ConsumerRepository extends JpaRepository<Investor, String> {

   //SELECT * FROM investors WHERE email=?
//    @Query("SELECT s FROM Investor s WHERE s.email =?1")
    Optional<Investor> findByEmail(String email);

}
