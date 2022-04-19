package com.example.clevertecspringshop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private Integer discount;

    private Card(Builder builder) {
        setId(builder.id);
        setNumber(builder.number);
        setDiscount(builder.discount);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private Integer id;

        private Integer number;

        private Integer discount;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder number(Integer integer) {
            number = integer;
            return this;
        }

        public Builder discount(Integer integer) {
            discount = integer;
            return this;
        }


        public Card build() {
            return new Card(this);
        }
    }
}
