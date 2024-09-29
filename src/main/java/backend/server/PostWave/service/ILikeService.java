package backend.server.PostWave.service;

import backend.server.PostWave.model.Like;

public interface ILikeService {
    Like addLike(Long postId);
    boolean isPostLikedByUser(Long postId, Long userId);
    void deleteLike(Long likeId);
}