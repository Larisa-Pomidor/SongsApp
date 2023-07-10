package com.spring.SpringApp.chord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ChordRepository extends JpaRepository<Chord, Long> {
    Optional<Chord> findByName(String name);
}
