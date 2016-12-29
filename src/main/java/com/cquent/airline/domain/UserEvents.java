package com.cquent.airline.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A UserEvents.
 */
@Entity
@Table(name = "user_events")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserEvents implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "run_date", nullable = false)
    private LocalDate runDate;

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

    @ManyToOne
    private RunStatus runStatus;

    @ManyToOne
    private UserPreference userPreference;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRunDate() {
        return runDate;
    }

    public UserEvents runDate(LocalDate runDate) {
        this.runDate = runDate;
        return this;
    }

    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    public String getFromCity() {
        return fromCity;
    }

    public UserEvents fromCity(String fromCity) {
        this.fromCity = fromCity;
        return this;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public UserEvents toCity(String toCity) {
        this.toCity = toCity;
        return this;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public LocalDate getDepartDate() {
        return departDate;
    }

    public UserEvents departDate(LocalDate departDate) {
        this.departDate = departDate;
        return this;
    }

    public void setDepartDate(LocalDate departDate) {
        this.departDate = departDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public UserEvents returnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public RunStatus getRunStatus() {
        return runStatus;
    }

    public UserEvents runStatus(RunStatus runStatus) {
        this.runStatus = runStatus;
        return this;
    }

    public void setRunStatus(RunStatus runStatus) {
        this.runStatus = runStatus;
    }

    public UserPreference getUserPreference() {
        return userPreference;
    }

    public UserEvents userPreference(UserPreference userPreference) {
        this.userPreference = userPreference;
        return this;
    }

    public void setUserPreference(UserPreference userPreference) {
        this.userPreference = userPreference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserEvents userEvents = (UserEvents) o;
        if (userEvents.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userEvents.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserEvents{" +
            "id=" + id +
            ", runDate='" + runDate + "'" +
            ", fromCity='" + fromCity + "'" +
            ", toCity='" + toCity + "'" +
            ", departDate='" + departDate + "'" +
            ", returnDate='" + returnDate + "'" +
            '}';
    }
}
