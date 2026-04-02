package com.MarkusE.Wigell_Travel_API.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long bookingId;

        @ManyToOne
        @JoinColumn(name = "destination_id")
        private Destination destination;

        @Column(name = "departure", nullable = false)
        private LocalDate departure;

        @Column(name = "trip_duration_weeks", nullable = false)
        private int tripDurationWeeks;

        @Column(name = "hotelName", length = 50, nullable = false)
        private String hotelName;

        @Column(name = "totalPrice", nullable = false)
        private double totalPrice;

        protected Booking() {}

        public Booking(double totalPrice, String hotelName, int tripDurationWeeks, LocalDate departure, Destination destination) {
            this.totalPrice = totalPrice;
            this.hotelName = hotelName;
            this.tripDurationWeeks = tripDurationWeeks;
            this.departure = departure;
            this.destination = destination;
        }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public int getTripDurationWeeks() {
        return tripDurationWeeks;
    }

    public void setTripDurationWeeks(int tripDurationWeeks) {
        this.tripDurationWeeks = tripDurationWeeks;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

