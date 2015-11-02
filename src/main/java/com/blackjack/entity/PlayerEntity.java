package com.blackjack.entity;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class PlayerEntity {

    @Id
    @SequenceGenerator(name="player_wallet_id_seq", sequenceName="player_wallet_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="player_wallet_id_seq")
    @Column(name = "wallet_id")
    private long walletId;

    @Column(name = "name")
    private String name;

    @Column(name = "money")
    private int money;

    public PlayerEntity() {}

    public PlayerEntity(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerEntity)) return false;

        PlayerEntity that = (PlayerEntity) o;

        if (walletId != that.walletId) return false;
        if (money != that.money) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = (int) (walletId ^ (walletId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + money;
        return result;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "walletId=" + walletId +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
