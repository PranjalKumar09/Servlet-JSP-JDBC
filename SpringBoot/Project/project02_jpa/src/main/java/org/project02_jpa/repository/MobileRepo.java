package org.project02_jpa.repository;

import org.project02_jpa.entities.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileRepo extends JpaRepository <Mobile, Integer> {
}
