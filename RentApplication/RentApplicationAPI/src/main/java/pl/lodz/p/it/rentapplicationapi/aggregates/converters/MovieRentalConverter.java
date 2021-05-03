package pl.lodz.p.it.rentapplicationapi.aggregates.converters;

import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.ClientDTO;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.MovieDTO;
import pl.lodz.p.it.rentapplicationapi.rentviewmodel.modelDTO.MovieRentalDTO;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Movie;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.MovieRental;


import java.util.Date;
import java.util.List;

public class MovieRentalConverter {

    private MovieRentalConverter() {
    }

    public static MovieRentalDTO convertMovieRentalToDTO(MovieRental movieRental) {
        Movie movie = movieRental.getMovie();
        MovieDTO movieDTO = MovieConverter.convertMovieToDTO(movie);
        Client account = movieRental.getAccount();
        ClientDTO accountDTO = ClientConverter.convertClientToDTO(account);
        String id = movieRental.getId();
        List<Date> range = movieRental.getRange();
        Date rentalStart = movieRental.getRentalStart();
        Date rentalEnd = movieRental.getRentalEnd();

        MovieRentalDTO newMovieRental = new MovieRentalDTO(movieDTO, accountDTO);
        newMovieRental.setId(id);
        newMovieRental.setRange(range);
        newMovieRental.setRentalStart(rentalStart);
        newMovieRental.setRentalEnd(rentalEnd);

        return newMovieRental;
    }

    public static MovieRental convertDTOToMovieRental(MovieRentalDTO movieRental) {
        MovieDTO movieDTO = movieRental.getMovie();
        Movie movie = MovieConverter.convertDTOToMovie(movieDTO);
        ClientDTO accountDTO = movieRental.getAccount();
        Client account = ClientConverter.convertDTOToClient(accountDTO);
        String id = movieRental.getId();
        List<Date> range = movieRental.getRange();
        Date rentalStart = movieRental.getRentalStart();
        Date rentalEnd = movieRental.getRentalEnd();

        MovieRental newMovieRental = new MovieRental(movie, account);
        newMovieRental.setId(id);
        newMovieRental.setRange(range);
        newMovieRental.setRentalStart(rentalStart);
        newMovieRental.setRentalEnd(rentalEnd);

        return newMovieRental;
    }
}