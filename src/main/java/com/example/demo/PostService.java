package com.example.demo;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    Post createPost(Post post);


    Post updatePost(long id, Post post);

    void deletePost(long id);

    Post getPostById(long id);



}
