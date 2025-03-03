package com.pooja.GreetingApp.Repository;

import com.pooja.GreetingApp.model.GreetingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<GreetingModel, Long> {
}