package accommodation;

import accommodation.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    PromotionRepository couponRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentRequested_Payment(@Payload PromotionSaved couponCompleted){
        if(couponCompleted.isMe()){
            System.out.println("##### listener CouponCompleted : " + couponCompleted.toJson());
        }
    }

}
