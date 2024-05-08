package com.micosoft.estoreback.farmers;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "farmer")
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String physicalAddress;
    private String contactPerson;
    private String contactPersonPhone;
    private String terms;
    private String notes;
    private Boolean isActive;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime updatedAt;


    public void setUpdatedAt() {
        this.updatedAt = ZonedDateTime.now();
    }
}