package com.MarkusE.Wigell_Travel_API.repo;

import com.MarkusE.Wigell_Travel_API.entity.Member;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
