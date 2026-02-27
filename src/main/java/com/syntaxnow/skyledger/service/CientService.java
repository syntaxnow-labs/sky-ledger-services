package com.syntaxnow.skyledger.service;

import com.syntaxnow.skyledger.model.BusinessProfile;
import com.syntaxnow.skyledger.model.Client;

import java.util.List;
import java.util.UUID;

public interface CientService {

    List<Client> getAlltheClients();

    Client getClientById(UUID id);

    Client creatClient( Client client);

    Client updateClinet( Client client);

    List<Client> getPaginationClients();
}
