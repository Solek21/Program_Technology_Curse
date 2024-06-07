package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.UpdateTimestamp;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "meet")
@Data
@Getter
@Setter
public class Model
{
    @Id
    @Column(name = "id_meeting")
    private UUID id_meeting;

    public Model()
    {
        if(this.id_meeting==null)
        {
            this.id_meeting = UUID.randomUUID();
        }
    }

    @NotNull
    @Column(name="id_owner")
    private String IdOwner;

    @NotNull
    @Column(name="id_audience")
    private String IdAudience;

    @NotNull
    @Column(name="max_size")
    private int MaxSize;

    @NotNull
    @Column(name="id_equipment")
    private String IdEquipment;

    @Column(name ="is_deleted")
    private boolean IsDeleted;

    @NotNull
    @Column(name="id_meeting_name")
    private String IdMeetingName;
    @UpdateTimestamp
    @Column(name="date")
    private Timestamp Date;

    @UpdateTimestamp
    @Column (name = "updated_at")
    private LocalDateTime updatedAt;

    @Column (name = "created_at", updatable = false)
    private LocalDateTime createdAt;


}
