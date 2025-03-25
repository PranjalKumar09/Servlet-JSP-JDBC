package com.icwd.hotelservice.reposistory;

import com.icwd.hotelservice.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelReposistory extends JpaRepository<Hotel, String> {

}
