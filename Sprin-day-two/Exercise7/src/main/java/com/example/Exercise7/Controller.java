package com.example.Exercise7;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController("/")
public class Controller {
    private final GitService gitService;

    public Controller(GitService gitService) {
        this.gitService = gitService;
    }

    @GetMapping
    List<String> getGitInfo() throws IOException {
        return gitService.getGitInfo();
    }
}
