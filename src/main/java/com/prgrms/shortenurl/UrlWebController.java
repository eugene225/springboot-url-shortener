package com.prgrms.shortenurl;

import com.prgrms.shortenurl.domain.Url;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class UrlWebController {
    private final UrlService urlService;
    public UrlWebController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("originUrl") String originUrl, Model model) {
        Url url = urlService.addLink(originUrl);

        model.addAttribute("shortenUrl", url.getShortenUrl());
        return "index";
    }

    @GetMapping("/{key}")
    public ResponseEntity<Object> redirect(@PathVariable String key) throws URISyntaxException {
        URI redirectUri = new URI(urlService.getUrlByKey(key));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}