package com.example.demo;

import org.springframework.stereotype.Service;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import com.example.demo.PostService;
import com.example.demo.Post;
import com.example.demo.PostRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        super();
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(long id, Post postRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post"));

        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        post.setContent(postRequest.getContent());
        return postRepository.save(post);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post"));

        postRepository.delete(post);

    }

    @Override
    public Post getPostById(long id) {
        Optional<Post> result = postRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("Post");
        }
    }
}
