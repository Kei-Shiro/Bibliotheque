package org.nahary.bibliotheque.service.implementation;

import org.nahary.bibliotheque.entity.Reservation;
import org.nahary.bibliotheque.repository.ReservationRepository;
import org.nahary.bibliotheque.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationImplementation implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public ResponseEntity<Reservation> getReservationById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public ResponseEntity<Reservation> updateReservation(Long id, Reservation updatedReservation) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation.isPresent()) {
            Reservation reservation = existingReservation.get();
            reservation.setDateReservation(updatedReservation.getDateReservation());
            reservation.setDateExpiration(updatedReservation.getDateExpiration());
            reservation.setStatut(updatedReservation.getStatut());
            return ResponseEntity.ok(reservationRepository.save(reservation));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
