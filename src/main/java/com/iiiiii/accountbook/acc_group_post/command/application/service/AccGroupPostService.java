package com.iiiiii.accountbook.acc_group_post.command.application.service;

import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.AccGroupPost;
import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.AccGroupPostEntity;
import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.AccGroupPostVO;
import com.iiiiii.accountbook.acc_group_post.command.domain.repository.AccGroupPostRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("CommandAccGroupPostService")
public class AccGroupPostService {
    private AccGroupPostRepository accGroupPostRepository;
    private ModelMapper modelmapper;

    @Autowired
    public AccGroupPostService(AccGroupPostRepository accGroupPostRepository,
                               ModelMapper modelmapper) {
        this.accGroupPostRepository = accGroupPostRepository;
        this.modelmapper = modelmapper;
    }

    public void registAccGroupPost(AccGroupPost newAccGroupPost) {
        AccGroupPostEntity groupPostEntity = modelmapper.map(newAccGroupPost, AccGroupPostEntity.class);
        groupPostEntity.setCreatedAt(LocalDateTime.now());
        accGroupPostRepository.save(groupPostEntity);
    }


    public void modifyAccGroupPost(AccGroupPostVO modifyAccGroupPost) {
        AccGroupPostEntity groupPostEntity = modelmapper.map(modifyAccGroupPost, AccGroupPostEntity.class);
        groupPostEntity.setCreatedAt(LocalDateTime.parse(modifyAccGroupPost.getCreatedAt()));
        accGroupPostRepository.saveAndFlush(groupPostEntity);
    }

    public void deleteAccGroupPost(AccGroupPostVO deleteAccGroupPost) {
        AccGroupPostEntity groupPostEntity = modelmapper.map(deleteAccGroupPost, AccGroupPostEntity.class);
//        accGroupPostRepository.delete(groupPostEntity);
        accGroupPostRepository.deleteById(groupPostEntity.getCode());
    }
}
