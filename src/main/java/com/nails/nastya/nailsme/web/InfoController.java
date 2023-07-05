package com.nails.nastya.nailsme.web;

import com.nails.nastya.nailsme.NailsmeApplication;
import com.nails.nastya.nailsme.facade.InfoFacade;
import com.nails.nastya.nailsme.web.response.AddressResponse;
import com.nails.nastya.nailsme.web.response.MasterContactsResponse;
import com.nails.nastya.nailsme.web.response.MasterServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(NailsmeApplication.VERSION_URL + "/info")
@RequiredArgsConstructor
@Tag(name = "Инфо", description = "Информация о салоне и мастерах")
public class InfoController {

    private final InfoFacade infoFacade;

    @Operation(summary = "Контакты мастера")
    @GetMapping("/contacts/{masterId}")
    public MasterContactsResponse getMasterContacts(@PathVariable Long masterId) {
        return null;
    }

    @Operation(summary = "Адрес студии мастера")
    @GetMapping("/contacts/address")
    public AddressResponse getMasterAddress() {
        return null;
    }

    @Operation(summary = "Услуги мастера")
    @GetMapping("/contacts/{masterId}")
    public MasterServiceResponse getMasterService(@PathVariable Long masterId) {
        return null;
    }
}
