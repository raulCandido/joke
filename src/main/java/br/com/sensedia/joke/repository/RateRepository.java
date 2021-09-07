package br.com.sensedia.joke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sensedia.joke.model.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    
}
