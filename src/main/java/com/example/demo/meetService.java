package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class meetService
{
    @Autowired
    private MeetRepository repository;
    public Model createMeet(Model meet)
    {
        return repository.save(meet);
    }


    public List<Model> getAllMeet()
    {
        return repository.findAll();
    }

    public Optional<Model> getMeetById(UUID id_meeting)
    {
        return repository.findById(id_meeting);
    }

    public void deletedAllMeeting()
    {
        repository.deleteAll();
    }

    public boolean deleteMeet(UUID id_meeting)
    {
        try
        {
            repository.deleteById(id_meeting);
        }catch (Exception ex)
        {
            System.out.println("Request error: " + ex.getMessage());
            return false;
        }
        repository.SetUpdatedById(id_meeting, LocalDateTime.now());
        repository.deleteSoftById(id_meeting);
        return true;
    }

//    public boolean recoverMeet(UUID id_meeting)
//    {
//        try
//        {
//            repository.recoverMeetingById(id_meeting);
//        }catch (Exception ex)
//        {
//            System.out.println("Request error: " + ex.getMessage());
//            return false;
//        }
//        repository.SetUpdatedById(id_meeting, LocalDateTime.now());
//        return true;
//    }
//
    public Model updateMeet(UUID id_meeting, Model newData)
    {
        try {
            var data= repository.findById(id_meeting).get();
            data.setUpdatedAt(newData.getUpdatedAt());
            data.setCreatedAt(newData.getCreatedAt());
            data.setDate(newData.getDate());
            data.setIdMeetingName(newData.getIdMeetingName());
            data.setIdAudience(newData.getIdAudience());
            data.setIdOwner(newData.getIdOwner());
            data.setIdEquipment(newData.getIdEquipment());
            data.setMaxSize(newData.getMaxSize());
            data.setIsDeleted(newData.isIsDeleted());
            repository.save(data);

          //  repository.updateMeet(newData.getIdOwner(), newData.getIdAudience(), newData.getMaxSize(), newData.getIdEquipment(), newData.getIdMeetingName(), id_meeting);
            repository.SetUpdatedById(id_meeting,LocalDateTime.now());
        }catch (Exception ex)
        {
            System.out.println("Request error: " + ex.getMessage());
        }
        return newData;
    }
//    public Integer getMeetCount()
//    {
//        return repository.getMeetingCount();
//    }
    public List<Model> getMeetingCount()
    {
        return repository.findAll();
    }

}
