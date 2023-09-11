package com.nails.nastya.nailsme.facade;

import com.nails.nastya.nailsme.mapper.ClientMapper;
import com.nails.nastya.nailsme.service.ClientService;
import com.nails.nastya.nailsme.service.ServiceService;
import com.nails.nastya.nailsme.web.response.AddressResponse;
import com.nails.nastya.nailsme.web.response.MasterContactsResponse;
import com.nails.nastya.nailsme.web.response.MasterServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InfoFacade {
    private final ClientService clientService;
    private final ServiceService serviceService;
    private final ClientMapper clientMapper;

    public InfoFacade(ClientService clientService,
                      ServiceService serviceService,
                      ClientMapper clientMapper) {
        this.clientService = clientService;
        this.serviceService = serviceService;
        this.clientMapper = clientMapper;
    }

    public MasterContactsResponse getMasterContacts(Long masterId) {
        return null;
    }

    public AddressResponse getMasterAddress() {
        return null;
    }

    public MasterServiceResponse getMasterService(Integer masterId) {
        return null;
    }
}
