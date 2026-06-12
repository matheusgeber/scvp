package com.scvp.repository;

import com.scvp.model.Modal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalRepository extends JpaRepository<Modal, Long> {
}