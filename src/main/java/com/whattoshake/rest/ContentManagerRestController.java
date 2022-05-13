package com.whattoshake.rest;

import com.whattoshake.service.user.impl.AppUserServiceImpl;
import com.whattoshake.service.user.impl.ContentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/content_manager")
public class ContentManagerRestController {
    private final ContentManagerService contentManagerService;
    private final AppUserServiceImpl appUserService;

    @Autowired
    public ContentManagerRestController(
            @Qualifier("contentManagerService") ContentManagerService contentManagerService,
            @Qualifier("appUserServiceImpl") AppUserServiceImpl appUserService

    ) {
        this.contentManagerService = contentManagerService;
        this.appUserService = appUserService;
    }

}
