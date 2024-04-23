package com.javaweb.converter;

import com.javaweb.entity.TransactionTypeEntity;
import com.javaweb.model.dto.TransactionTypeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionTypeConverter
{
    @Autowired
    private ModelMapper modelMapper;

    public TransactionTypeDTO toTransactionTypeDTO(TransactionTypeEntity transactionTypeEntity)
    {
        TransactionTypeDTO transactionTypeDTO = modelMapper.map(transactionTypeEntity, TransactionTypeDTO.class);
        transactionTypeDTO.setCustomerId(transactionTypeEntity.getCustomer().getId());
        return transactionTypeDTO;
    }
}
