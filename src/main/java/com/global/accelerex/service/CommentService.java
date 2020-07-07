package com.global.accelerex.service;

import com.global.accelerex.dto.CommentDTO;
import com.global.accelerex.dto.response.CommentResponse;
import com.global.accelerex.exception.ResourceNotFoundException;
import com.global.accelerex.model.CommentModel;
import com.global.accelerex.model.EpisodeModel;
import com.global.accelerex.repository.CommentRepository;
import com.global.accelerex.repository.EpisodeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    public CommentResponse addComment(Long episodeId, CommentDTO commentDTO){
        if (episodeId == null) throw new ResourceNotFoundException("Episode id  can not be null");
        Optional<EpisodeModel> episodeModel = episodeRepository.findById(episodeId);
        if (!episodeModel.isPresent()) throw new ResourceNotFoundException("Episode id " + episodeId + " not found");
        CommentModel commentModel = mapDtoToCommentModel(commentDTO);
        commentModel.setEpisode(episodeModel.get());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        commentModel.setIpAddressLocation(request.getRemoteAddr());
        commentModel = commentRepository.save(commentModel);
        return mapCommentModelToCommentResponse(commentModel);
    }

    public List<CommentResponse> getAllComments(){
        List<CommentModel> commentModelList = commentRepository.findAll(Sort.by("ipAddressLocation").and(Sort.by("created")));
        List<CommentResponse> commentResponseList = new ArrayList<>();
        if (commentModelList != null){
            commentResponseList = commentModelList.stream().map(
                    commentModel -> mapCommentModelToCommentResponse(commentModel)
            ).collect(Collectors.toList());
        }
        return commentResponseList;
    }

    public List<CommentResponse> getEpisodeComments(Long episodeId){
        if (episodeId == null) throw new ResourceNotFoundException("Episode id  can not be null");
        Optional<EpisodeModel> episodeModel = episodeRepository.findById(episodeId);
        if (!episodeModel.isPresent()) throw new ResourceNotFoundException("Episode id " + episodeId + " not found");
        Set<CommentModel> commentModelList = episodeModel.get().getComments();
        List<CommentResponse> commentResponseList = new ArrayList<>();
        if (commentModelList != null){
            commentResponseList = commentModelList.stream().map(
                    commentModel -> mapCommentModelToCommentResponse(commentModel)
            ).collect(Collectors.toList());
        }
        return commentResponseList;
    }

    private CommentModel mapDtoToCommentModel(CommentDTO commentDTO){
        CommentModel commentModel = new CommentModel();
        BeanUtils.copyProperties(commentDTO, commentModel);
        commentModel.setCreated(LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()));
        return commentModel;
    }

    private CommentResponse mapCommentModelToCommentResponse(CommentModel commentModel){
        CommentResponse commentResponse = new CommentResponse();
        BeanUtils.copyProperties(commentModel, commentResponse);
        commentResponse.setEpisodeId(commentModel.getEpisode().getId());
        return commentResponse;
    }
}
