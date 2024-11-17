package com.gmail.drack.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gmail.drack.repository.projection.BlockedUserProjection;

public interface BlockUserService {
    Page<BlockedUserProjection> getBlockList(Pageable pageable);
    Boolean processBlockList(Long userId);
}
