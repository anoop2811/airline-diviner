package com.cquent.airline.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.cquent.airline.domain.enumeration.Currency;

/**
 * A UserPreference.
 */
@Entity
@Table(name = "user_preference")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserPreference implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "from_city", nullable = false)
    private String fromCity;

    @NotNull
    @Column(name = "to_city", nullable = false)
    private String toCity;

    @NotNull
    @Column(name = "depart_date", nullable = false)
    private LocalDate departDate;

    @NotNull
    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_id")
    private Currency currencyId;

    @NotNull
    @Column(name = "threshold", precision=10, scale=2, nullable = false)
    private BigDecimal threshold;

    @Min(value = 3)
    @Max(value = 7)
    @Column(name = "frequency")
    private Integer frequency;

    @Column(name = "next_run_date")
    private LocalDate nextRunDate;

    @OneToMany(mappedBy = "userPreference")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserEvents> userEventsRelation1S = new HashSet<>();

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromCity() {
        return fromCity;
    }

    public UserPreference fromCity(String fromCity) {
        this.fromCity = fromCity;
        return this;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public UserPreference toCity(String toCity) {
        this.toCity = toCity;
        return this;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public LocalDate getDepartDate() {
        return departDate;
    }

    public UserPreference departDate(LocalDate departDate) {
        this.departDate = departDate;
        return this;
    }

    public void setDepartDate(LocalDate departDate) {
        this.departDate = departDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public UserPreference returnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Currency getCurrencyId() {
        return currencyId;
    }

    public UserPreference currencyId(Currency currencyId) {
        this.currencyId = currencyId;
        return this;
    }

    public void setCurrencyId(Currency currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public UserPreference threshold(BigDecimal threshold) {
        this.threshold = threshold;
        return this;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public UserPreference frequency(Integer frequency) {
        this.frequency = frequency;
        return this;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public LocalDate getNextRunDate() {
        return nextRunDate;
    }

    public UserPreference nextRunDate(LocalDate nextRunDate) {
        this.nextRunDate = nextRunDate;
        return this;
    }

    public void setNextRunDate(LocalDate nextRunDate) {
        this.nextRunDate = nextRunDate;
    }

    public Set<UserEvents> getUserEventsRelation1S() {
        return userEventsRelation1S;
    }

    public UserPreference userEventsRelation1S(Set<UserEvents> userEvents) {
        this.userEventsRelation1S = userEvents;
        return this;
    }

    public UserPreference addUserEventsRelation1(UserEvents userEvents) {
        userEventsRelation1S.add(userEvents);
        userEvents.setUserPreference(this);
        return this;
    }

    public UserPreference removeUserEventsRelation1(UserEvents userEvents) {
        userEventsRelation1S.remove(userEvents);
        userEvents.setUserPreference(null);
        return this;
    }

    public void setUserEventsRelation1S(Set<UserEvents> userEvents) {
        this.userEventsRelation1S = userEvents;
    }

    public User getUser() {
        return user;
    }

    public UserPreference user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserPreference userPreference = (UserPreference) o;
        if (userPreference.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userPreference.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserPreference{" +
            "id=" + id +
            ", fromCity='" + fromCity + "'" +
            ", toCity='" + toCity + "'" +
            ", departDate='" + departDate + "'" +
            ", returnDate='" + returnDate + "'" +
            ", currencyId='" + currencyId + "'" +
            ", threshold='" + threshold + "'" +
            ", frequency='" + frequency + "'" +
            ", nextRunDate='" + nextRunDate + "'" +
            '}';
    }
}
