package shop.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import shop.config.kafka.KafkaProcessor;
import shop.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_StartDelivery(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener StartDelivery : " + orderPlaced + "\n\n"
        );

        // Sample Logic //
        Delivery.startDelivery(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryStarted'"
    )
    public void wheneverDeliveryStarted_DecreaseInventory(
        @Payload DeliveryStarted deliveryStarted
    ) {
        DeliveryStarted event = deliveryStarted;
        System.out.println(
            "\n\n##### listener DecreaseInventory : " + deliveryStarted + "\n\n"
        );

        // Sample Logic //
        Inventory.decreaseInventory(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCanceld'"
    )
    public void wheneverOrderCanceld_CancelDelivery(
        @Payload OrderCanceld orderCanceld
    ) {
        OrderCanceld event = orderCanceld;
        System.out.println(
            "\n\n##### listener CancelDelivery : " + orderCanceld + "\n\n"
        );

        // Sample Logic //
        Delivery.cancelDelivery(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryCanceld'"
    )
    public void wheneverDeliveryCanceld_IncreaseInventory(
        @Payload DeliveryCanceld deliveryCanceld
    ) {
        DeliveryCanceld event = deliveryCanceld;
        System.out.println(
            "\n\n##### listener IncreaseInventory : " + deliveryCanceld + "\n\n"
        );

        // Sample Logic //
        Inventory.increaseInventory(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
