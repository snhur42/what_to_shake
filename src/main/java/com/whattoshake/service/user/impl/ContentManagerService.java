package com.whattoshake.service.user.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@Qualifier("managerService")
public class ContentManagerService {

}

