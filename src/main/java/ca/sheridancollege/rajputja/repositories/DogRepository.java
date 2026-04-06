package ca.sheridancollege.rajputja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.rajputja.beans.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {}