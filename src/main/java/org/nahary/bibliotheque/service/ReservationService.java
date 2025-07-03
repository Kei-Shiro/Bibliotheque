package org.nahary.bibliotheque.service;

import org.nahary.bibliotheque.entity.Reservation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();

    ResponseEntity<Reservation> getReservationById(Long id);

    Reservation createReservation(Reservation reservation);

    ResponseEntity<Reservation> updateReservation(Long id, Reservation updatedReservation);

    ResponseEntity<Void> deleteReservation(Long id);
}
