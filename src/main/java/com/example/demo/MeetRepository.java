package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
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

    @Query("Select a From Model a Where a.Date between :start_date AND :end_date")
    List<Model> findAllByDateBetween(@Param("start_date") LocalDateTime startDate, @Param("end_date") LocalDateTime endDate);

    @Query("Select a From Model a Where a.IdOwner = :idowner")
    List<Model> SortByOwner(@Param("idowner") String Idowner);

//    @Transactional
//    @Modifying
//    @Query("SELECT * From meet where = ")

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
