package shop.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import shop.domain.*;
import shop.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderCanceld extends AbstractEvent {

    private Long id;
    private String customerId;
    private String customerName;
    private Long productId;
    private Integer qty;
    private String address;

    public OrderCanceld(Order aggregate) {
        super(aggregate);
    }

    public OrderCanceld() {
        super();
    }
}
//>>> DDD / Domain Event
