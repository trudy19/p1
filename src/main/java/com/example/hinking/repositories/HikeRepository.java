package com.example.hinking.repositories;
import com.example.hinking.models.Hike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HikeRepository extends JpaRepository<Hike, Long> {
}
