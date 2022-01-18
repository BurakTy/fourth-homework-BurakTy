package com.buraktuysuz.fourthhomework.entitiy;

import com.buraktuysuz.fourthhomework.enums.EnumDebtType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "debt")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Debt implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate createDate;

    @Column(nullable = false)
    private EnumDebtType debtType;

    @Column(precision = 19, scale = 2)
    private BigDecimal debtAmount;

    @Column(precision = 19, scale = 2)
    private BigDecimal remainingDebtAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_debt_user_id"), nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_super_dept", foreignKey = @ForeignKey(name = "fk_debt_super_dept_id"))
    private Debt debt = null;

    @Transient
    private BigDecimal intresetAmount;
}