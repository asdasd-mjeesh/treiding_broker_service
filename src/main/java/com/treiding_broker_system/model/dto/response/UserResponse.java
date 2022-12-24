package com.treiding_broker_system.model.dto.response;

import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.user.Role;
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
public class UserResponse {
    private String username;
    private Role role;
    private BigDecimal balance;
    private List<Order> orders;
}
