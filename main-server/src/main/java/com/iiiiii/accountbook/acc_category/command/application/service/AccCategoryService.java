package com.iiiiii.accountbook.acc_category.command.application.service;

import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategory;
import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategoryEntity;
import com.iiiiii.accountbook.acc_category.command.domain.repository.AccCategoryRepository;
import com.iiiiii.accountbook.acc_category.query.dto.AccCategoryDTO;
import com.iiiiii.accountbook.common.YesOrNo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("CommandAccCategoryService")
public class AccCategoryService {

    private AccCategoryRepository accCategoryRepository;
    private com.iiiiii.accountbook.acc_category.query.service.AccCategoryService accCategoryService;
    private ModelMapper modelMapper;

    @Autowired
    public AccCategoryService(AccCategoryRepository accCategoryRepository,
                              com.iiiiii.accountbook.acc_category.query.service.AccCategoryService accCategoryService,
                              ModelMapper modelMapper) {
        this.accCategoryRepository = accCategoryRepository;
        this.accCategoryService = accCategoryService;
        this.modelMapper = modelMapper;
    }

    public void registAccCategory(AccCategory newAccCategory) {
        AccCategoryEntity newAccCategoryEntity = modelMapper.map(newAccCategory, AccCategoryEntity.class);
        accCategoryRepository.save(newAccCategoryEntity);

    }

    public void modifyAccCategory(AccCategory modifyAccCategory) {
        AccCategoryEntity modifyAccCategoryEntity = modelMapper.map(modifyAccCategory, AccCategoryEntity.class);
        accCategoryRepository.saveAndFlush(modifyAccCategoryEntity);
    }

    public void deleteAccCategory(AccCategory deleteAccCategory) {
        AccCategoryEntity deleteAccCategoryEntity = modelMapper.map(deleteAccCategory, AccCategoryEntity.class);
        deleteAccCategoryEntity.setIsDeleted(YesOrNo.Y);
        accCategoryRepository.saveAndFlush(deleteAccCategoryEntity);
    }
}
