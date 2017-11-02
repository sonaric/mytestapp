package com.testapp.db.repository;

import com.testapp.db.CalcHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by stanislav on 01.11.17.
 */
public interface CalcHistoryRepository extends JpaRepository<CalcHistory, Long> {
}
