package com.iiiiii.accountbook.acc_group_post_file.command.application.service;

import com.iiiiii.accountbook.acc_group_post.query.service.AccGroupPostService;
import com.iiiiii.accountbook.acc_group_post_file.command.domain.aggregate.AccGroupPostFile;
import com.iiiiii.accountbook.acc_group_post_file.command.domain.aggregate.AccGroupPostFileEntity;
import com.iiiiii.accountbook.acc_group_post_file.command.domain.aggregate.AccGroupPostFileVO;
import com.iiiiii.accountbook.acc_group_post_file.command.domain.repository.AccGroupPostFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("CommandAccGroupPostFileService")
public class AccGroupPostFileService {
    private AccGroupPostFileRepository accGroupPostFileRepository;
    private AccGroupPostService accGroupPostService;
    private ModelMapper modelMapper;

    @Autowired
    public AccGroupPostFileService(AccGroupPostFileRepository accGroupPostFileRepository,
                                   ModelMapper modelMapper) {
        this.accGroupPostFileRepository = accGroupPostFileRepository;
        this.modelMapper = modelMapper;
    }


    public void registAccGroupPostFile(AccGroupPostFileVO newAccGroupPostFile) {
        AccGroupPostFileEntity postFile = modelMapper.map(newAccGroupPostFile, AccGroupPostFileEntity.class);
        accGroupPostFileRepository.save(postFile);
    }

    public void modifyAccGroupPostFile(AccGroupPostFile modifyAccGroupPostFile) {
        AccGroupPostFileEntity postFile = modelMapper.map(modifyAccGroupPostFile, AccGroupPostFileEntity.class);
        accGroupPostFileRepository.saveAndFlush(postFile);
    }

    public void deleteAccGroupPostFile(AccGroupPostFile deleteAccGroupPostFile) {
        AccGroupPostFileEntity postFile = modelMapper.map(deleteAccGroupPostFile, AccGroupPostFileEntity.class);
        accGroupPostFileRepository.delete(postFile);
    }
}
