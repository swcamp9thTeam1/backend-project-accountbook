package com.iiiiii.accountbook.community.command.application.service;

import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityPostScrapDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.entity.CommunityPostScrap;
import com.iiiiii.accountbook.community.command.domain.repository.CommunityPostRepository;
import com.iiiiii.accountbook.community.command.domain.repository.CommunityPostScrapRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CommunityPostScrapServiceCommand")
public class CommunityPostScrapService {

    private final ModelMapper modelMapper;
    private final CommunityPostScrapRepository communityPostScrapRepository;
    private final CommunityPostRepository communityPostRepository;

    @Autowired
    public CommunityPostScrapService(ModelMapper modelMapper,
                                     CommunityPostScrapRepository communityPostScrapRepository,
                                     CommunityPostRepository communityPostRepository) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());   // null인 부분은 매핑 제외
        this.communityPostScrapRepository = communityPostScrapRepository;
        this.communityPostRepository = communityPostRepository;
    }

    /* 게시글 스크랩(등록) 트랜잭션 */
    @Transactional
    public int addScrap(Integer communityPostCode, CommunityPostScrapDTO newScrap) {

        if (!communityPostRepository.existsById(communityPostCode)) {
            throw new EntityNotFoundException("존재하지 않는 게시글입니다.");
        } else if (!communityPostCode.equals(newScrap.getCommunityPostCode())) {
            throw new IllegalArgumentException("게시글 코드가 일치하지 않습니다.");
        }

        if (newScrap.getMemberCode() == communityPostRepository.findById(communityPostCode).get().getMemberCode()) {
            throw new IllegalArgumentException("작성자 본인의 게시글은 스크랩 할 수 없습니다.");
        }

        CommunityPostScrap scrap = communityPostScrapRepository
                                    .save(modelMapper.map(newScrap, CommunityPostScrap.class));

        return scrap.getMemberCode();
    }

    /* 게시글 스크랩 취소(삭제) 트랜잭션 */
    @Transactional
    public void cancelScrap(CommunityPostScrapDTO postScrap) {

        communityPostScrapRepository.delete(modelMapper.map(postScrap, CommunityPostScrap.class));
    }
}
