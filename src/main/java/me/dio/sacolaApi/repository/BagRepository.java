package me.dio.sacolaApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.sacolaApi.model.Bag;

@Repository
public interface BagRepository extends JpaRepository<Bag, Long>{

}
