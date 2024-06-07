package com.example.demo.controller;

import com.example.demo.Model;
import com.example.demo.meetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TetsMeet {

    private MockMvc mockMvc;

    @Mock
    private meetService meetService;

    @InjectMocks
    private MeetController meetController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(meetController).build();
    }

    @Test
    public void testGetMeetsById() throws Exception {
        UUID meetId = UUID.randomUUID();
        Model meet = new Model();
        meet.setId_meeting(meetId);
        meet.setIdMeetingName("Meeting 1");

        when(meetService.getMeetById(meetId)).thenReturn(Optional.of(meet));

        mockMvc.perform(get("/api/meet/{id_meeting}", meetId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_meeting").value(meetId.toString()))
                .andExpect(jsonPath("$.idMeetingName").value("Meeting 1"));
    }

    @Test
    public void testGetMeetsByIdNotFound() throws Exception {
        UUID meetId = UUID.randomUUID();
        when(meetService.getMeetById(meetId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/meet/{id_meeting}", meetId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAll() throws Exception {
        Model meet1 = new Model();
        meet1.setId_meeting(UUID.randomUUID());
        meet1.setIdMeetingName("Meeting 1");

        Model meet2 = new Model();
        meet2.setId_meeting(UUID.randomUUID());
        meet2.setIdMeetingName("Meeting 2");

        List<Model> meets = Arrays.asList(meet1, meet2);

        when(meetService.getMeetingCount()).thenReturn(meets);

        mockMvc.perform(get("/api/meet/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idMeetingName").value("Meeting 1"))
                .andExpect(jsonPath("$[1].idMeetingName").value("Meeting 2"));
    }

    @Test
    public void testCreateMeet() throws Exception {
        Model meet = new Model();
        meet.setId_meeting(UUID.randomUUID());
        meet.setIdMeetingName("New Meeting");

        when(meetService.createMeet(any(Model.class))).thenReturn(meet);

        mockMvc.perform(post("/api/meet/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idMeetingName\":\"New Meeting\", \"idOwner\":\"owner1\", \"idAudience\":\"audience1\", \"maxSize\":10, \"idEquipment\":\"equipment1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idMeetingName").value("New Meeting"));
    }

    @Test
    public void testDeleteMeetById() throws Exception {
        UUID meetId = UUID.randomUUID();
        Model meet = new Model();
        meet.setId_meeting(meetId);
        meet.setIdMeetingName("Meeting to delete");

        when(meetService.deleteMeet(meetId)).thenReturn(true);
        when(meetService.getMeetById(meetId)).thenReturn(Optional.of(meet));

        mockMvc.perform(patch("/api/meet/delete")
                        .param("id_meeting", meetId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_meeting").value(meetId.toString()));
    }

    @Test
    public void testDeleteMeetByIdNotFound() throws Exception {
        UUID meetId = UUID.randomUUID();
        when(meetService.deleteMeet(meetId)).thenReturn(false);

        mockMvc.perform(patch("/api/meet/delete")
                        .param("id_meeting", meetId.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateMeet() throws Exception {
        UUID meetId = UUID.randomUUID();
        Model meet = new Model();
        meet.setId_meeting(meetId);
        meet.setIdMeetingName("Updated Meeting");

        when(meetService.getMeetById(meetId)).thenReturn(Optional.of(meet));
        when(meetService.updateMeet(any(UUID.class), any(Model.class))).thenReturn(meet);

        mockMvc.perform(put("/api/meet/update")
                        .param("id_meeting", meetId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idMeetingName\":\"Updated Meeting\", \"idOwner\":\"owner1\", \"idAudience\":\"audience1\", \"maxSize\":10, \"idEquipment\":\"equipment1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idMeetingName").value("Updated Meeting"));
    }

    @Test
    public void testUpdateMeetNotFound() throws Exception {
        UUID meetId = UUID.randomUUID();

        when(meetService.getMeetById(meetId)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/meet/update")
                        .param("id_meeting", meetId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idMeetingName\":\"Non-existent Meeting\", \"idOwner\":\"owner1\", \"idAudience\":\"audience1\", \"maxSize\":10, \"idEquipment\":\"equipment1\"}"))
                .andExpect(status().isNotFound());
    }
}
