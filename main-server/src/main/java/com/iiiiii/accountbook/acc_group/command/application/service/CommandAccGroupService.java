package com.iiiiii.accountbook.acc_group.command.application.service;

import com.iiiiii.accountbook.acc_group.command.domain.aggregate.CommandAccGroup;
import com.iiiiii.accountbook.acc_group.command.domain.aggregate.CommandAccGroupEntity;
import com.iiiiii.accountbook.acc_group.command.domain.repository.CommandAccGroupRepository;
import com.iiiiii.accountbook.acc_group.query.service.QueryAccGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandAccGroupService {
    private CommandAccGroupRepository commandAccGroupRepository;
    private ModelMapper modelMapper;
    private QueryAccGroupService queryAccGroupService;

    @Autowired
    public CommandAccGroupService(CommandAccGroupRepository commandAccGroupRepository,
                                  ModelMapper modelMapper,
                                  QueryAccGroupService queryAccGroupService) {
        this.commandAccGroupRepository = commandAccGroupRepository;
        this.modelMapper = modelMapper;
        this.queryAccGroupService = queryAccGroupService;
    }

    public void registAccGroup(CommandAccGroup newAccGroup) {
        List<String> names = queryAccGroupService.findAccGroupNames()
                .stream()
                .filter(name -> name.equals((newAccGroup.getName())))
                .collect(Collectors.toList());

        if (names.size() == 0) {
            CommandAccGroupEntity regist = modelMapper.map(newAccGroup, CommandAccGroupEntity.class);
            commandAccGroupRepository.save(regist);
        }
    }

    public void modifyAccGroup(CommandAccGroup modifyAccGroup) {
        List<Integer> codes = queryAccGroupService.findAccGroupCodes()
                .stream()
                .filter(code -> code == modifyAccGroup.getCode())
                .collect(Collectors.toList());

        if (codes.size() > 0) {
            CommandAccGroupEntity modify = modelMapper.map(modifyAccGroup, CommandAccGroupEntity.class);
            commandAccGroupRepository.saveAndFlush(modify);
        }
    }

    public void deleteAccGroup(CommandAccGroup deleteAccGroup) {
        List<Integer> codes = queryAccGroupService.findAccGroupCodes()
                .stream()
                .filter(code -> code == deleteAccGroup.getCode())
                .collect(Collectors.toList());

        if (codes.size() > 0) {
            CommandAccGroupEntity delete = modelMapper.map(deleteAccGroup, CommandAccGroupEntity.class);
            commandAccGroupRepository.delete(delete);
        }
    }
}
