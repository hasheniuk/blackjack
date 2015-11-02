package com.blackjack.entity;

import com.blackjack.util.percistence.converter.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "money_income")
public class MoneyIncomeEntity {

    @Id
    @SequenceGenerator(name="money_income_operation_id_seq", sequenceName="money_income_operation_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="money_income_operation_id_seq")
    @Column(name = "operation_id")
    private long operationId;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "operation_date")
    private LocalDateTime operationDate;

    @ManyToOne
    @JoinColumn(name = "player")
    private PlayerEntity player;

    @Column(name = "income")
    private int income;

    public MoneyIncomeEntity() {}

    public MoneyIncomeEntity(LocalDateTime operationDate, PlayerEntity player, int income) {
        this.operationDate = operationDate;
        this.player = player;
        this.income = income;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoneyIncomeEntity)) return false;

        MoneyIncomeEntity that = (MoneyIncomeEntity) o;

        if (operationId != that.operationId) return false;
        if (income != that.income) return false;
        if (!operationDate.equals(that.operationDate)) return false;
        return player.equals(that.player);

    }

    @Override
    public int hashCode() {
        int result = (int) (operationId ^ (operationId >>> 32));
        result = 31 * result + operationDate.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + income;
        return result;
    }

    public long getOperationId() {
        return operationId;
    }

    public void setOperationId(long operationId) {
        this.operationId = operationId;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }

    public PlayerEntity getPlayerEntity() {
        return player;
    }

    public void setPlayerEntity(PlayerEntity playerEntity) {
        this.player = player;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "MoneyIncomeEntity{" +
                "operationId=" + operationId +
                ", operationDate=" + operationDate +
                ", playerEntity=" + player +
                ", income=" + income +
                '}';
    }
}
