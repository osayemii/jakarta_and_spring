package com.api.social_media.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.api.social_media.entity.*;
import com.api.social_media.service.ShareService;

@RestController
@RequestMapping("/posts/{postId}/shares")
public class ShareController {

    private final ShareService shareService;

    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @PostMapping
    public PostShare share(@PathVariable Long postId, @RequestBody PostShare share) {
        share.setId(null);
        share.setPostId(postId);
        return shareService.share(share);
    }

    @DeleteMapping
    public void unshare(@PathVariable Long postId, @RequestParam Long userId) {
        shareService.unshare(postId, userId);
    }

    @GetMapping
    public List<PostShare> getShares(@PathVariable Long postId) {
        return shareService.getShares(postId);
    }
}
