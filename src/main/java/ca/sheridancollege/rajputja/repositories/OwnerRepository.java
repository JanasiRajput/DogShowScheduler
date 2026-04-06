package ca.sheridancollege.rajputja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.rajputja.beans.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {}