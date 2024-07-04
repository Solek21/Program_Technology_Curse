package com.example.demo.controller;

import com.example.demo.Model;
import com.example.demo.meetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/meet")
public class MeetController
{
    private final meetService Meetservice;
    @Autowired
    public MeetController(meetService Meetservice)
    {
        this.Meetservice = Meetservice;
    }

    @GetMapping("/{id_meeting}")
    public ResponseEntity<Model> getMeetsById(@PathVariable UUID id_meeting)
    {
        Optional<Model> meetOptional = Meetservice.getMeetById(id_meeting);
        return meetOptional.map(meet -> new ResponseEntity<>(meet, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Model>> getAll(){
        return new ResponseEntity<List<Model>>(Meetservice.getMeetingCount(),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Model> createMeet(@RequestBody Model request)
    {

        Model createdMeet = Meetservice.createMeet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMeet);
    }

    @PatchMapping("/delete")
    public ResponseEntity<Model> deleteMeetById(UUID id_meeting)
    {
        if(Meetservice.deleteMeet(id_meeting))
        {
            return ResponseEntity.status(HttpStatus.OK).body(Meetservice.getMeetById(id_meeting).orElse(null));
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/filterByDate")
    public List<Model> getMeetingsByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate)
    {
        return Meetservice.getMeetingsByDateRange(startDate,endDate);
    }

    @GetMapping("/SortBySize")
    public List<Model> SortByOwnerMeet(@RequestParam String Idowner)
    {
        return Meetservice.SortByOwnerMeet(Idowner);
    }

//    @PatchMapping("/recover/{id}")
//    public ResponseEntity<Model> recoverMeet(@PathVariable UUID id_meeting)
//    {
//        if(Meetservice.recoverMeet(id_meeting))
//        {
//            return ResponseEntity.status(HttpStatus.OK).body(Meetservice.getMeetById(id_meeting).get());
//        }
//        else return ResponseEntity.notFound().build();
//    }
//
    @PutMapping("/update")
    public ResponseEntity<Model> updateMeet(UUID id_meeting, @RequestBody Model newData)
    {
        if(!Meetservice.getMeetById(id_meeting).isEmpty())
        {
            return ResponseEntity.status(HttpStatus.OK).body(Meetservice.updateMeet(id_meeting,newData));
        }
        else return ResponseEntity.notFound().build();
    }

//    @GetMapping("/count")
//    public ResponseEntity<Integer> getMeetingCount()
//    {
//        Integer count = Meetservice.getMeetCount();
///        return ResponseEntity.ok(count);
//    }
}
