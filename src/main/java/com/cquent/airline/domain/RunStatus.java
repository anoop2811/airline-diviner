package com.cquent.airline.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A RunStatus.
 */
@Entity
@Table(name = "run_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RunStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "status_code", nullable = false)
    private String statusCode;

    @NotNull
    @Column(name = "status_description", nullable = false)
    private String statusDescription;

    @OneToMany(mappedBy = "runStatus")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserEvent> userEvents = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public RunStatus statusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public RunStatus statusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
        return this;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Set<UserEvent> getUserEvents() {
        return userEvents;
    }

    public RunStatus userEvents(Set<UserEvent> userEvents) {
        this.userEvents = userEvents;
        return this;
    }

    public RunStatus addUserEvent(UserEvent userEvent) {
        userEvents.add(userEvent);
        userEvent.setRunStatus(this);
        return this;
    }

    public RunStatus removeUserEvent(UserEvent userEvent) {
        userEvents.remove(userEvent);
        userEvent.setRunStatus(null);
        return this;
    }

    public void setUserEvents(Set<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RunStatus runStatus = (RunStatus) o;
        if (runStatus.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, runStatus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RunStatus{" +
            "id=" + id +
            ", statusCode='" + statusCode + "'" +
            ", statusDescription='" + statusDescription + "'" +
            '}';
    }
}
