package com.hatefulbug.authserver.controller;

import com.hatefulbug.authserver.dto.ClientDto;
import com.hatefulbug.authserver.dto.MessageDto;
import com.hatefulbug.authserver.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @PostMapping("create")
    public ResponseEntity<MessageDto> create(@RequestBody ClientDto clientDto) {
        MessageDto messageDto = clientService.create(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageDto);
    }

}
