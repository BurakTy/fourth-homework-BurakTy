package com.buraktuysuz.fourthhomework.entitiy;

import com.buraktuysuz.fourthhomework.enums.EnumDebtType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


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
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Column(nullable = false)
    private EnumDebtType debtType= EnumDebtType.MAIN;
//    private char debtType = 0;

    @Column(precision = 19, scale = 2)
    private BigDecimal debtAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date collectDate;

    private boolean isCollect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_debt_user_id"), nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_super_dept", foreignKey = @ForeignKey(name = "fk_debt_super_dept_id"))
    private Debt debt = null;

}
