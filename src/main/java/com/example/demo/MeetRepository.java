package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;
@Repository
public interface MeetRepository extends JpaRepository<Model,UUID>
{
    @Transactional
    @Modifying
    @Query("UPDATE Model a SET a.IsDeleted = true WHERE a.id_meeting = :id_meeting AND a.IsDeleted = false")
    void deleteSoftById(@Param("id_meeting")UUID id_meeting);

    @Transactional
    @Modifying
    @Query("UPDATE Model a SET a.updatedAt = :upd WHERE a.id_meeting = :id_meeting")
    void SetUpdatedById(@Param("id_meeting")UUID id_meeting,@Param("upd") LocalDateTime updatedAt);


//    @Modifying
//    @Transactional
//    @Query("UPDATE Meeting a SET a.isDeleted = false WHERE a.idMeeting = :id AND a.isDeleted = true")
//    void recoverMeetingById(@Param("id") UUID id_meeting);
//
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE Meeting a SET a.idOwner = :owner, a.idAudience = :audience, a.maxSize = :maxsize, a.idEquipment = :equipment, a.idMeetingName = :meetingname WHERE a.idMeeting = :id_meeting")
//    void updateMeet(@Param("owner") String owner,
//                       @Param("audience") String audience,
//                       @Param("maxsize") Integer maxSize,
//                       @Param("equipment") String equipment,
//                       @Param("meetingname") String meetingName,
//                       @Param("id_meeting") UUID id_meeting);
    @Query("SELECT Count(*) from Model")
    Integer getMeetingCount();

}
