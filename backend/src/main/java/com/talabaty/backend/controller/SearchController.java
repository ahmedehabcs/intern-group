package com.talabaty.backend.controller;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.response.SearchResponse;
import com.talabaty.backend.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Search", description = "Search restaurants and menu items")
@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;


    @Operation(
            summary = "Search restaurants and menu items",
            description = "Publicly search active restaurants and available menu items."
    )
    @GetMapping
    public ResponseEntity<SearchResponse> search(
            @RequestParam String search
    ) {
        return ResponseEntity.ok(searchService.search(search));
    }
}
