package com.api.social_media.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.api.social_media.entity.*;
import com.api.social_media.service.BookmarkService;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping
    public Bookmark addBookmark(@RequestBody Bookmark bookmark) {
        bookmark.setId(null);
        return bookmarkService.addBookmark(bookmark);
    }

    @DeleteMapping("/{id}")
    public void removeBookmark(@PathVariable Long id) {
        bookmarkService.removeBookmark(id);
    }

    @GetMapping
    public List<Bookmark> getBookmarks(@RequestParam Long userId) {
        return bookmarkService.getBookmarks(userId);
    }
}
