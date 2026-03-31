package com.MarkusE.Wigell_Travel_API.repo;

import com.MarkusE.Wigell_Travel_API.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
