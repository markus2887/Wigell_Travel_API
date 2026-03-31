package com.MarkusE.Wigell_Travel_API.repo;

import com.MarkusE.Wigell_Travel_API.entity.Customer;
import com.MarkusE.Wigell_Travel_API.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

}
