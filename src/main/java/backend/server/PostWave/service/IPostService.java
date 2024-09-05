package backend.server.PostWave.service;

import backend.server.PostWave.model.Post;

import java.util.List;

public interface IPostService {

    List<Post> getAllPosts();

    Post createPost(Post post);
}
