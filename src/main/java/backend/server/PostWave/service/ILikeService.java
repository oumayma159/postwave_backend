package backend.server.PostWave.service;

import backend.server.PostWave.model.Like;

public interface ILikeService {
    Like addLike(Long postId, Long likeId);
    long getNumberOfLikesForPost(Long postId);
    void deleteLike(Long likeId);
}