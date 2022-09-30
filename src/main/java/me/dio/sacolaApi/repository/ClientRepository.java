package me.dio.sacolaApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.sacolaApi.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
