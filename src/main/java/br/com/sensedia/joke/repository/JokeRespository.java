package br.com.sensedia.joke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sensedia.joke.model.CategoryRate;
import br.com.sensedia.joke.model.Joke;

@Repository
public interface JokeRespository extends JpaRepository<Joke, Long> {
    boolean existsJokeByIdApi(int id);

    Joke findByIdApi(Integer id);
    List<Joke> findByRateIsNull();
    
   @Query("SELECT  new br.com.sensedia.joke.model.CategoryRate(j.category, AVG(r.note)) FROM Joke j JOIN j.rate r group by j.category")
   List<CategoryRate> findCategoryAverageRate();
}
