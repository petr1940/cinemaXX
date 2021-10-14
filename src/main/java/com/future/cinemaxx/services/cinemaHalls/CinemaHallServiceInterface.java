package com.future.cinemaxx.services.cinemaHalls;

import com.future.cinemaxx.entities.CinemaHall;

import java.util.List;

public interface CinemaHallServiceInterface{
    List<CinemaHall> getAllCinemaHalls();
    CinemaHall getCinemaHallById(int id);
    CinemaHall createCinemaHall(CinemaHall cinemaHall, String name, int numberOfRows, int numberOfColumns);
    void deleteCinemaHall (int CinemaHallId);
}