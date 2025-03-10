package com.example.Exercise7;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

@Service
public class GitService {

    public ArrayList<String> getGitInfo() throws IOException {
        Properties properties = new Properties();
        properties.load(new ClassPathResource("git.properties").getInputStream());
        ArrayList<String> list=new ArrayList<>();
        list.add(properties.getProperty("git.closest.tag.name"));
        list.add(properties.getProperty("git.commit.id.abbrev"));
        list.add(properties.getProperty("build.version"));
        return list;
    }
}
