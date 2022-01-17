package com.buraktuysuz.fourthhomework.reposity;

import com.buraktuysuz.fourthhomework.entitiy.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtRepository extends JpaRepository<Debt,Long> {
}
