package com.talabaty.backend.service;

import com.talabaty.backend.dto.response.SearchResponse;

public interface SearchService {

    SearchResponse search(String search);
}
