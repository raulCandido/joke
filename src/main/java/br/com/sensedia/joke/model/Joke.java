package br.com.sensedia.joke.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonAlias("id")
    private int idApi;
    private String category;
    @Length(max = 500)
    private String joke;
    private String type;
    @Length(max = 500)
    private String setup;
    @Length(max = 500)
    private String delivery;
    @JsonManagedReference
    @OneToMany(mappedBy = "joke")
    private List<Rate> rate;
    private Boolean error;

}
