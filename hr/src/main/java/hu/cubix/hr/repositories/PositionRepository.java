package hu.cubix.hr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cubix.hr.model.Position;

public interface PositionRepository extends JpaRepository<Position,Integer> {
}
