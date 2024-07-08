package com.fast_food.controller;

import com.fast_food.model.Event;
import com.fast_food.repository.EventRepository;
import com.fast_food.response.MessageResponse;
import com.fast_food.service.EventService;
import com.fast_food.service.RestaurantService;
import com.fast_food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class EventController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private EventService eventService;


    @PostMapping("admin/event/create/{id}")
    public ResponseEntity<Event> createCategory(
            @RequestBody Event event,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {

        Event newEvent=eventService.createEvent(event,id);


        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);

    }

    @GetMapping("event/all")
    public ResponseEntity<List<Event>> getAllEvents(

    ) throws Exception {

        List<Event> events =eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.CREATED);

    }

    @DeleteMapping("admin/event/{id}")
    public ResponseEntity<MessageResponse> deleteEvent(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {


        eventService.deleteEvent(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Event Deleted Successfully");

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }

    @GetMapping("event/id/{id}")
    public ResponseEntity<List<Event>> getEventById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id

    ) throws Exception {

        List<Event> events =eventService.getEvent(id);


        return new ResponseEntity<>(events, HttpStatus.CREATED);

    }
}
