package com.iiiiii.accountbook.community.command.application.service;

import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommnunityFileDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.entity.CommunityFile;
import com.iiiiii.accountbook.community.command.domain.repository.CommunityFileRepository;
import com.iiiiii.accountbook.community.command.domain.repository.CommunityPostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CommunityFileServiceCommand")
public class CommunityFileService {

    private final ModelMapper modelMapper;
    private final CommunityFileRepository communityFileRepository;
    private final CommunityPostRepository communityPostRepository;

    @Autowired
    public CommunityFileService(ModelMapper modelMapper,
                                CommunityFileRepository communityFileRepository,
                                CommunityPostRepository communityPostRepository) {
        this.modelMapper = modelMapper;
        this.communityFileRepository = communityFileRepository;
        this.communityPostRepository = communityPostRepository;
    }

    /* 게시글 첨부파일 등록 트랜잭션 */
    @Transactional
    public int registFile(Integer postCode, CommnunityFileDTO newFile) {

        if (!communityPostRepository.existsById(postCode)) {
            throw new EntityNotFoundException("존재하지 않는 게시글입니다.");
        } else if (!postCode.equals(newFile.getCommunityPostCode())) {
            throw new IllegalArgumentException("게시글 코드가 일치하지 않습니다.");
        }

        CommunityFile registedFile = communityFileRepository.save(modelMapper.map(newFile, CommunityFile.class));

        return registedFile.getFileCode();
    }

    /* 게시글 첨부파일 수정 트랜잭션 */
    // 파일명(name), 파일경로(path) 수정 가능
    @Transactional
    public void modifyFile(Integer postCode, Integer fileCode, CommnunityFileDTO modifiedFile) {

        CommunityFile file = communityFileRepository
                                .findById(fileCode)
                                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 파일입니다."));

        if (!file.getCommunityPostCode().equals(postCode)) {
            throw new IllegalArgumentException("이 게시글에는 해당 파일이 존재하지 않습니다.");
        } else if (!fileCode.equals(modifiedFile.getFileCode())) {
            throw new IllegalArgumentException("첨부파일 코드가 일치하지 않습니다.");
        }

        communityFileRepository.save(modelMapper.map(modifiedFile, CommunityFile.class));
    }

    /* 게시글 첨부파일 삭제 트랜잭션 */
    @Transactional
    public void removeFile(Integer postCode, Integer fileCode) {

        if (!communityPostRepository.existsById(postCode))
            throw new EntityNotFoundException("존재하지 않는 게시글입니다.");

        communityFileRepository.deleteById(fileCode);
    }
}
