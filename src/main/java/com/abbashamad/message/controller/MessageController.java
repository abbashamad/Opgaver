package com.abbashamad.message.controller;

import com.abbashamad.message.model.Message;
import com.abbashamad.message.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("message")
// Controller-klassen håndterer HTTP-anmodninger fra klienten.
// Den bruger @Controller i stedet for @RestController og returnerer data via ResponseEntity.
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService messageService) {
        this.service = messageService;
    }

    @GetMapping("")
    public ResponseEntity<List<Message>> getMessages() {
        List<Message> messages = service.getMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int id, @RequestParam(required = false) String caps) {
        Message message = service.findMessageById(id, caps);
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}