package com.dproject.pizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order",nullable = false)
    private Integer idOrder;
    @Column(name = "id_customer_order",nullable = false)
    private Integer idCustomerOrder;
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime dateOrder;
    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double totalOrder;
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String methodOrder;
    @Column(length = 200)
    private String additionalNotes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer_order", referencedColumnName = "id_customer", insertable = false, updatable = false)
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @OrderBy("priceOrder asc ")
    private List<OrderItemEntity> orderItem;
}
