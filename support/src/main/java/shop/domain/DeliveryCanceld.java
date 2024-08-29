package shop.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import shop.domain.*;
import shop.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryCanceld extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String address;
    private Long productId;
    private Integer qty;

    public DeliveryCanceld(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryCanceld() {
        super();
    }
}
//>>> DDD / Domain Event
