package com.nails.nastya.nailsme.mapper;

import com.nails.nastya.nailsme.dto.ContactDto;
import com.nails.nastya.nailsme.persistance.Contact;
import com.nails.nastya.nailsme.web.response.MasterContactsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactMapper {

    MasterContactsResponse contactDtoToMasterContactsResponse(ContactDto dto);

    ContactDto contactToContactDto(Contact entity);

    Contact contactDtoToContact(ContactDto dto);

    List<ContactDto> contactToContactDtoList(List<Contact> entities);

    List<Contact> contactDtoToContactList(List<ContactDto> dtos);
}
