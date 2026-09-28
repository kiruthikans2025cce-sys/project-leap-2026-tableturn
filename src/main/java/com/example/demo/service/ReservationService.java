package com.example.demo.service;

import com.example.demo.entity.Reservation;
import com.example.demo.entity.RestaurantTable;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TableRepository tableRepository;

    public Reservation reserveTable(Reservation reservation) {

        RestaurantTable table =
                tableRepository.findById(
                                reservation.getTable().getTableId())
                        .orElseThrow();

        if (!table.getStatus().equalsIgnoreCase("FREE")) {
            throw new RuntimeException("Table already occupied");
        }

        table.setStatus("RESERVED");
        tableRepository.save(table);

        return reservationRepository.save(reservation);
    }
}