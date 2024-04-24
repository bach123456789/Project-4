package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

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
        Map<String, String> statuss = Status.type();
        CustomerSearchResponse res = modelMapper.map(customerEntity, CustomerSearchResponse.class);
        res.setStatus(statuss.get(customerEntity.getStatus()));
        return res;
    }

    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity)
    {
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }
}
