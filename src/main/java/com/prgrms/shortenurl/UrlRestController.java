package com.prgrms.shortenurl;

import com.prgrms.shortenurl.domain.Url;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shortenUrl")
public class UrlRestController {
    private final UrlService urlService;

    public UrlRestController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/{url}")
    @ResponseBody
    public ResponseEntity<String> shortenUrl(@PathVariable String url) {
        Url shortenUrl = urlService.addLink(url);
        return ResponseEntity.ok(shortenUrl.getOriginUrl());
    }

    @GetMapping(value = "/{key}")
    @ResponseBody
    public ResponseEntity<String> getUrl(@PathVariable String key) {
        String url = urlService.getUrlByKey(key);
        return ResponseEntity.ok(url);
    }
}