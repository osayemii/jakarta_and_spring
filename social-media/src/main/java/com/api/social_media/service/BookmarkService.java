package com.api.social_media.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.social_media.entity.Bookmark;
import com.api.social_media.repository.BookmarkRepository;

@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Bookmark addBookmark(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    public void removeBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }

    public List<Bookmark> getBookmarks(Long userId) {
        return bookmarkRepository.findByUserId(userId);
    }
}
