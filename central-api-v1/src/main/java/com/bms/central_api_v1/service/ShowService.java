package com.bms.central_api_v1.service;

import com.bms.central_api_v1.exception.AlredyBookedSeatException;
import com.bms.central_api_v1.exception.InvalidShowException;
import com.bms.central_api_v1.exception.UnAuthorizedException;
import com.bms.central_api_v1.integration.DBAPI;
import com.bms.central_api_v1.models.*;
import com.bms.central_api_v1.requestbody.CreateShowRequestBody;
import com.bms.central_api_v1.requestbody.PurchaseTicketRequestBody;
import com.bms.central_api_v1.responsebody.BillResponseBody;
import com.bms.central_api_v1.responsebody.ShowsByHallResposneBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ShowService {

    @Autowired
    HallService hallService;
    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;

    @Autowired
    DBAPI dbapi;

    public LocalDateTime getTimeInLocalDateTime(Long timeInMillis){
        LocalDateTime referenceTime = LocalDateTime.of(2014, 1, 1, 00, 00);
        Duration duration = Duration.ofMillis(timeInMillis);
        referenceTime.plus(duration);
        return referenceTime;
    }

    public boolean isShowOverLapping(List<Show> shows, Show show2){

        for(int i = 0; i < shows.size(); i++){
            Show show1 = shows.get(i); // 4pm to 6pm
            // 6pm >= 3pm
            if(show1.getEndTime() >= show2.getStartTime() && show1.getStartTime() <= show2.getEndTime()){
                return true; // overlapping exist
            }
        }

        return false; // overlapping exist

    }

    public Show createShow(CreateShowRequestBody showRB,
                           UUID ownerId){
        // I need to get hall
        // I need to get movie

        Hall hall = hallService.getHallById(showRB.getHallId());
        Movie movie = movieService.getMovieById(showRB.getMovieId());

        if(!hall.getTheather().getOwner().getId().equals(ownerId)){
            throw new UnAuthorizedException(String.format("User does not own the hall"));
        }

        LocalDateTime startTime = showRB.getStartTime();
        LocalDateTime endTime = showRB.getEndTime();
        LocalDateTime referenceTime = LocalDateTime.of(2014, 1, 1, 00, 00);

        Long startTimeInMillis = Duration.between(referenceTime, startTime).toMillis();
        Long endTimeInMillis = Duration.between(referenceTime, endTime).toMillis();
        Show show = new Show();
        show.setHall(hall);
        show.setMovie(movie);
        show.setStartTime(startTimeInMillis);
        show.setEndTime(endTimeInMillis);
        show.setPrice(showRB.getPrice());

        List<Show> shows = this.getAllShowsByHallId(hall.getId());
        boolean isOverLapping = this.isShowOverLapping(shows, show);
        if(isOverLapping){
            throw new InvalidShowException("Over lapping timinng");
        }
        return dbapi.callCreateShowEndpoint(show);
    }

    public List<Show> getAllShowsByHallId(UUID hallId){
        ShowsByHallResposneBody hallShows = dbapi.callGetShowsByHallId(hallId);
        return hallShows.getShows();
    }

    public Show getShowById(UUID showId){
        return dbapi.callGetShowByShowId(showId);
    }

    public List<Integer> getAvailableSeatsInShow(UUID showId){
        Show show = this.getShowById(showId);
        int totalSeats = show.getHall().getCapacity();
        List<Integer> notBookedSeats = new ArrayList<>();
        for(int seat = 1; seat <= totalSeats; seat++){
            // We will check is this seat is booked or not
            BookedSeat bookedSeat = dbapi.callGetBookedSeatEndPoint(showId, seat);
            if(bookedSeat == null){
                notBookedSeats.add(seat);
            }
        }
        return notBookedSeats;
    }


    public BillResponseBody purchaseTicket(UUID userId, UUID showId, PurchaseTicketRequestBody ticketsRB){
        AppUser user = userService.getUserById(userId);
        Show show = this.getShowById(showId);
        List<Integer> seats = ticketsRB.getSeats();
        Double totalPrice  = 0.0;
        for(int seat : seats){
            BookedSeat bookedSeat = dbapi.callGetBookedSeatEndPoint(showId, seat);
            if(bookedSeat == null){
                // now we need to make it book
                BookedSeat newBookedSeat = new BookedSeat();
                newBookedSeat.setShowId(showId);
                newBookedSeat.setSeatNumber(seat);
                dbapi.callCreateBookedSeatEndpoint(newBookedSeat);
                totalPrice += show.getPrice();
            }else{
                throw new AlredyBookedSeatException("Seat is already booked");
            }
        }

        BillResponseBody billResponseBody = new BillResponseBody();
        LocalDateTime startTime = this.getTimeInLocalDateTime(show.getStartTime());
        LocalDateTime endTime = this.getTimeInLocalDateTime(show.getEndTime());

        billResponseBody.setStartTime(startTime);
        billResponseBody.setEndTime(endTime);
        billResponseBody.setMovieName(show.getMovie().getName());
        billResponseBody.setUserName(user.getName());
        billResponseBody.setTotalAmount(totalPrice);
        billResponseBody.setSeats(ticketsRB.getSeats());
        return billResponseBody;
    }
}
