package com.example.todoapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.Objects;

@Document("currencies")
public class Currency {
    @Id
    private String id;
    private Long timestamp;
    private BigDecimal low;
    private BigDecimal high;
    private String pair;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id)
                && Objects.equals(timestamp, currency.timestamp)
                && Objects.equals(low, currency.low)
                && Objects.equals(high, currency.high)
                && Objects.equals(pair, currency.pair);
    }

    @Override
    public String toString() {
        return "Currency{"
                + "id='" + id + '\''
                + ", timestamp=" + timestamp
                + ", low=" + low
                + ", high=" + high
                + ", pair='" + pair + '\''
                + '}';
    }
}
