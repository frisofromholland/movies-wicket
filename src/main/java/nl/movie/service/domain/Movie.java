package nl.movie.service.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Getter
@Setter
public class Movie implements Serializable {

    private static final long serialVersionUID = -4039587343866359063L;

    private String title;
    private String description;
    private int duration;
    private int year;
    private String trailerLink;
    private List<Screening> screenings;

}