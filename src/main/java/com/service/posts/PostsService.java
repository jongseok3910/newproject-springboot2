package com.service.posts;

import com.domain.posts.Posts;
import com.domain.posts.PostsRepository;
import com.web.dto.PostsResponseDto;
import com.web.dto.PostsSaveRequestDto;
import com.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) throws IllegalAccessException {
        Posts posts=postsRepository.findById(id).orElseThrow(()->new IllegalAccessException("nothing gesiguel. id="+id));

        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity=postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Notthing gesiguel. id"+id));
        return new PostsResponseDto(entity);
    }
}
