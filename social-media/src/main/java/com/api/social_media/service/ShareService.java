package com.api.social_media.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.social_media.entity.Post;
import com.api.social_media.entity.PostShare;
import com.api.social_media.repository.PostRepository;
import com.api.social_media.repository.PostShareRepository;

@Service
public class ShareService {

    private final PostShareRepository shareRepository;
    private final PostRepository postRepository;

    public ShareService(PostShareRepository shareRepository, PostRepository postRepository) {
        this.shareRepository = shareRepository;
        this.postRepository = postRepository;
    }

    public PostShare share(PostShare share) {
        PostShare saved = shareRepository.save(share);

        Post post = postRepository.findById(share.getPostId()).orElse(null);
        if (post != null) {
            post.setShareCount(post.getShareCount() + 1);
            postRepository.save(post);
        }

        return saved;
    }

    public void unshare(Long postId, Long userId) {
        shareRepository.findByPostIdAndUserId(postId, userId).ifPresent(shareRepository::delete);

        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            post.setShareCount(Math.max(0, post.getShareCount() - 1));
            postRepository.save(post);
        }
    }

    public List<PostShare> getShares(Long postId) {
        return shareRepository.findByPostId(postId);
    }
}
