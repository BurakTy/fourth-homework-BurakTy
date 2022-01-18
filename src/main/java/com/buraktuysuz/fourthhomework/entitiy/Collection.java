package com.buraktuysuz.fourthhomework.entitiy;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "collection")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_debt", foreignKey = @ForeignKey(name = "fk_collection_debt_id"), nullable = false)
    private Debt debt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date collectionDate;
}
