package com.iiiiii.accountbook.acc_group.command.application.service;

import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroup;
import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroupEntity;
import com.iiiiii.accountbook.acc_group.command.domain.repository.AccGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("CommandAccGroupService")
public class AccGroupService {
    private AccGroupRepository accGroupRepository;
    private ModelMapper modelMapper;
    private com.iiiiii.accountbook.acc_group.query.service.AccGroupService accGroupService;

    @Autowired
    public AccGroupService(AccGroupRepository accGroupRepository,
                           ModelMapper modelMapper,
                           com.iiiiii.accountbook.acc_group.query.service.AccGroupService accGroupService) {
        this.accGroupRepository = accGroupRepository;
        this.modelMapper = modelMapper;
        this.accGroupService = accGroupService;
    }

    public void registAccGroup(AccGroup newAccGroup) {
        List<String> names = accGroupService.findAccGroupNames()
                .stream()
                .filter(name -> name.equals((newAccGroup.getName())))
                .collect(Collectors.toList());

        if (names.size() == 0) {
            AccGroupEntity regist = modelMapper.map(newAccGroup, AccGroupEntity.class);
            accGroupRepository.save(regist);
        }
    }

    public void modifyAccGroup(AccGroup modifyAccGroup) {
        List<Integer> codes = accGroupService.findAccGroupCodes()
                .stream()
                .filter(code -> code == modifyAccGroup.getCode())
                .collect(Collectors.toList());

        if (codes.size() > 0) {
            AccGroupEntity modify = modelMapper.map(modifyAccGroup, AccGroupEntity.class);
            accGroupRepository.saveAndFlush(modify);
        }
    }

    public void deleteAccGroup(AccGroup deleteAccGroup) {
        List<Integer> codes = accGroupService.findAccGroupCodes()
                .stream()
                .filter(code -> code == deleteAccGroup.getCode())
                .collect(Collectors.toList());

        if (codes.size() > 0) {
            AccGroupEntity delete = modelMapper.map(deleteAccGroup, AccGroupEntity.class);
            accGroupRepository.delete(delete);
        }
    }
}
