package com.iiiiii.accountbook.acc_category.command.application.service;

import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategory;
import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategoryEntity;
import com.iiiiii.accountbook.acc_category.command.domain.repository.CommandAccCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommandAccCategoryService {

    private CommandAccCategoryRepository commandAccCategoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CommandAccCategoryService(CommandAccCategoryRepository commandAccCategoryRepository,
                                     ModelMapper modelMapper) {
        this.commandAccCategoryRepository = commandAccCategoryRepository;
        this.modelMapper = modelMapper;
    }

    public void registAccCategory(AccCategory newAccCategory) {

        // newAccCategory.getMemberCode를 member 테이블의 회원코드와 비교해 회원 유무 판별

        // newAccCategory.getParentCode가 Null이 아닌경우 acc_category 테이블에 존재 여부 판별
        if (newAccCategory.getParentCode() > 0 &
                commandAccCategoryRepository.findById(newAccCategory.getParentCode()) != null) {
            AccCategoryEntity newAccCategoryEntity = modelMapper.map(newAccCategory, AccCategoryEntity.class);
            commandAccCategoryRepository.save(newAccCategoryEntity);
        }

    }

    public void modifyAccCategory(AccCategory modifyAccCategory) {
        AccCategoryEntity modifyAccCategoryEntity = modelMapper.map(modifyAccCategory, AccCategoryEntity.class);

        // if modifyAccCategory.getCode() in acc_category table
        commandAccCategoryRepository.saveAndFlush(modifyAccCategoryEntity);
    }

    public void deleteAccCategory(AccCategory deleteAccCategory) {
        AccCategoryEntity deleteAccCategoryEntity = modelMapper.map(deleteAccCategory, AccCategoryEntity.class);

        // if deleteAccCategory.getCode() in acc_category table
        commandAccCategoryRepository.delete(deleteAccCategoryEntity);
    }
}
