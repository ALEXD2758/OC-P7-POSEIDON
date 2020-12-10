package com.alex.poseidon.models;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "trade")
public class TradeModel {

    @Id
    @Column(name = "trade_id")
    private int tradeId;
    @NotBlank(message = "Account is mandatory")
    @Size(max=30, message = "The account name must be of maximum 30 characters")
    @Column(name = "account")
    private String account;
    @Size(max=30, message = "The type must be of maximum 30 characters")
    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;
    @PositiveOrZero(message = "Buy Quantity must be greater than or equal to zero")
    @Column(name = "buy_quantity")
    private Double buyQuantity;
    @PositiveOrZero(message = "Sell Quantity must be greater than or equal to zero")
    @Column(name = "sell_quantity")
    private Double sellQuantity;
    @PositiveOrZero(message = "Buy price must be greater than or equal to zero")
    @Column(name = "buy_price")
    private Double buyPrice;
    @PositiveOrZero(message = "Sell price must be greater than or equal to zero")
    @Column(name = "sell_price")
    private Double sellPrice;
    @Size(max=125, message = "The benchmark must be of maximum 125 characters")
    @Column(name = "benchmark")
    private String benchmark;
    @NotNull(message = "Trade date is mandatory")
    @FutureOrPresent(message = "The trade date should be a date in the future or now")
    @Column(name = "trade_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime tradeDate;
    @Size(max=125, message = "The security must be of maximum 125 characters")
    @Column(name = "security")
    private String security;
    @Size(max=10, message = "The status name must be of maximum 10 characters")
    @Column(name = "status")
    private String status;
    @Size(max=125, message = "The trader name must be of maximum 125 characters")
    @Column(name = "trader")
    private String trader;
    @Size(max=125, message = "The account name must be of maximum 125 characters")
    @Column(name = "book")
    private String book;
    @NotBlank(message = "Creation Name is mandatory")
    @Size(max=125, message = "The creation name must be of maximum 125 characters")
    @Column(name = "creation_name")
    private String creationName;
    @NotNull(message = "Creation date is mandatory")
    @FutureOrPresent(message = "The date should be a date in the future or now")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "creation_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime creationDate;
    @Size(max=125, message = "The revision name must be of maximum 125 characters")
    @Column(name = "revision_name")
    private String revisionName;
    @FutureOrPresent(message = "The date should be a date in the future or now")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "revision_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime revisionDate;
    @Size(max=125, message = "The deal name must be of maximum 125 characters")
    @Column(name = "deal_name")
    private String dealName;
    @Size(max=125, message = "The deal type must be of maximum 125 characters")
    @Column(name = "deal_type")
    private String dealType;
    @Size(max=125, message = "The source list id must be of maximum 125 characters")
    @Column(name = "source_list_id")
    private String sourceListId;
    @Size(max=125, message = "The side name must be of maximum 125 characters")
    @Column(name = "side")
    private String side;

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public LocalDateTime getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDateTime tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public LocalDateTime getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(LocalDateTime revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}