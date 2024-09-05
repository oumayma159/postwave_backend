package backend.server.PostWave.service.implementation;

import backend.server.PostWave.model.Post;
import backend.server.PostWave.repository.IPostRepo;
import backend.server.PostWave.service.IPostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PostService implements IPostService {

    @Autowired
    IPostRepo postRepo ;

    public List<Post> getAllPosts(){

        return postRepo.findAll();
    }

    public Post createPost(Post post){

        return postRepo.save(post);
    }

}
