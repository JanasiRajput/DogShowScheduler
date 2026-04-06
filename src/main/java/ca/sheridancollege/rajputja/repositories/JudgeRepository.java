package ca.sheridancollege.rajputja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.rajputja.beans.Judge;

@Repository
public interface JudgeRepository extends JpaRepository<Judge, Long> {}