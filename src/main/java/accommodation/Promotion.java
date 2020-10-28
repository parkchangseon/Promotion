package accommodation;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Coupon_table")
public class Promotion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int couponId;
    private int paymentId;
    private int paymentPrice;
    private String paymentStatus;
    private int point;
    private String reserveStatus;
    private String service;

    @PrePersist
    public void onPrePersist() {
        if ("promotion".equals(reserveStatus) ) {
            System.out.println("=============마일리지 적립 처리중=============");
            PromotionSaved couponSaved = new PromotionSaved();

            couponSaved.setPaymentId(paymentId);
            couponSaved.setPaymentPrice(paymentPrice);
            couponSaved.setPaymentStatus(paymentStatus);

            if("Y".equals(paymentStatus)) {
                if (paymentPrice >= 100000) {
                    service = "DISCOUNT COUPON";
                } else if (paymentPrice >= 50000 && paymentPrice < 100000) {
                    service = "BEVERAGE";
                } else {
                    point = paymentPrice / 10;
                }
            } else {
                point = 0;
            }
            BeanUtils.copyProperties(this, couponSaved);

            couponSaved.publishAfterCommit();

            try {
                Thread.currentThread().sleep((long) (400 + Math.random() * 220));
                System.out.println("=============마일리지 적립 완료=============");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int CouponId) {
        this.couponId = couponId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(int paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getReserveStatus() {
        return reserveStatus;
    }

    public void setReserveStatus(String reserveStatus) {
        this.reserveStatus = reserveStatus;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}