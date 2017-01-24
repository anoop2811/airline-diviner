package com.cquent.airline.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A UserEvent.
 */
@Entity
@Table(name = "user_event")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "best_onward_path", nullable = false)
    private String bestOnwardPath;

    @NotNull
    @Column(name = "best_return_path", nullable = false)
    private String bestReturnPath;

    @NotNull
    @Column(name = "best_price", precision=10, scale=2, nullable = false)
    private BigDecimal bestPrice;

    @NotNull
    @Column(name = "run_date", nullable = false)
    private LocalDate runDate;

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

    public String getBestOnwardPath() {
        return bestOnwardPath;
    }

    public UserEvent bestOnwardPath(String bestOnwardPath) {
        this.bestOnwardPath = bestOnwardPath;
        return this;
    }

    public void setBestOnwardPath(String bestOnwardPath) {
        this.bestOnwardPath = bestOnwardPath;
    }

    public String getBestReturnPath() {
        return bestReturnPath;
    }

    public UserEvent bestReturnPath(String bestReturnPath) {
        this.bestReturnPath = bestReturnPath;
        return this;
    }

    public void setBestReturnPath(String bestReturnPath) {
        this.bestReturnPath = bestReturnPath;
    }

    public BigDecimal getBestPrice() {
        return bestPrice;
    }

    public UserEvent bestPrice(BigDecimal bestPrice) {
        this.bestPrice = bestPrice;
        return this;
    }

    public void setBestPrice(BigDecimal bestPrice) {
        this.bestPrice = bestPrice;
    }

    public LocalDate getRunDate() {
        return runDate;
    }

    public UserEvent runDate(LocalDate runDate) {
        this.runDate = runDate;
        return this;
    }

    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    public RunStatus getRunStatus() {
        return runStatus;
    }

    public UserEvent runStatus(RunStatus runStatus) {
        this.runStatus = runStatus;
        return this;
    }

    public void setRunStatus(RunStatus runStatus) {
        this.runStatus = runStatus;
    }

    public UserPreference getUserPreference() {
        return userPreference;
    }

    public UserEvent userPreference(UserPreference userPreference) {
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
        UserEvent userEvent = (UserEvent) o;
        if (userEvent.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userEvent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserEvent{" +
            "id=" + id +
            ", bestOnwardPath='" + bestOnwardPath + "'" +
            ", bestReturnPath='" + bestReturnPath + "'" +
            ", bestPrice='" + bestPrice + "'" +
            ", runDate='" + runDate + "'" +
            '}';
    }
}
