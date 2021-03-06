package com.ubb.cms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alexandra Muresan on 4/10/2017.
 */
@Entity
@javax.persistence.Table(name = "edition")
public class Edition implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "The edition must be assigned to a conference")
    @ManyToOne
    @JoinColumn(name = "conference_id", referencedColumnName = "id")
    private Conference conference;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String name;

    @NotNull(message = "The edition must have a submission date assigned")
    @Column(name = "paperSubmissionDeadline")
    private Date   paperSubmissionDeadline;

    @NotNull(message = "The edition must have a final deadline")
    @Column(name = "finalDeadline")
    private Date   finalDeadline;

    @NotNull(message = "The edition must have a beginning date")
    @Column(name = "beginningDate")
    private Date   beginningDate;

    @NotNull(message = "The edition must have an end date")
    @Column(name = "endingDate")
    private Date   endingDate;

    public Edition(int id, Conference conference, Date beginningDate, Date endingDate, String name, Date paperSubmissionDeadline, Date finalDeadline) {
        this.id = id;
        this.conference = conference;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.name = name;
        this.paperSubmissionDeadline = paperSubmissionDeadline;
        this.finalDeadline = finalDeadline;
    }

    public Edition(Conference conference, Date beginningDate, Date endingDate, String name, Date paperSubmissionDeadline, Date finalDeadline) {
        this.conference = conference;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.name = name;
        this.paperSubmissionDeadline = paperSubmissionDeadline;
        this.finalDeadline = finalDeadline;
    }

    public Edition() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPaperSubmissionDeadline() {
        return paperSubmissionDeadline;
    }

    public void setPaperSubmissionDeadline(Date paperSubmissionDeadline) {
        this.paperSubmissionDeadline = paperSubmissionDeadline;
    }

    public Date getFinalDeadline() {
        return finalDeadline;
    }

    public void setFinalDeadline(Date finalDeadline) {
        this.finalDeadline = finalDeadline;
    }


    @Override
    public String toString() {
        return "Edition{" +
                "id=" + id +
                ", conference=" + conference +
                ", name='" + name + '\'' +
                ", paperSubmissionDeadline=" + paperSubmissionDeadline +
                ", finalDeadline=" + finalDeadline +
                ", beginningDate=" + beginningDate +
                ", endingDate=" + endingDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Edition edition = (Edition) o;

        return getId() == edition.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
