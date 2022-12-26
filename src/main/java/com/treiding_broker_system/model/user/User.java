package com.treiding_broker_system.model.user;

import com.treiding_broker_system.model.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private BigDecimal balance;
    private BigDecimal availableBalance;
    private Role role;
    private List<Order> orders;
}
