package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter
{
    @Autowired
    ModelMapper modelMapper;

    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO)
    {
        CustomerEntity res = modelMapper.map(customerDTO, CustomerEntity.class);
        res.setIsActive("1");
        return res;
    }

    public CustomerSearchResponse toCustomerSearchResponse(CustomerEntity customerEntity)
    {
        CustomerSearchResponse res = modelMapper.map(customerEntity, CustomerSearchResponse.class);
        return res;
    }

    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity)
    {
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }
}
