package dev.samuel.school_web.controllers.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public class URIUtils {

    public static URI createHeaderLocation(UUID id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
