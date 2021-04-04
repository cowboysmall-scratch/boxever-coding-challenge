package com.cowboysmall.scratch.boxever.graph;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Long.parseLong;

@Component
public class ResourceGraphRepository implements GraphRepository<Graph<String, Long>> {

    @Value("classpath:graph.txt")
    private Resource graphResource;

    @Override
    public Graph<String, Long> createGraph(Boolean directed) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(graphResource.getInputStream()))) {

            Graph<String, Long> graph =
                    Graph.<String, Long>builder()
                            .directed(directed)
                            .build();

            reader.lines().forEach(line -> {

                String[] split = line.split(" ");
                graph.addEdge(split[0], split[1], parseLong(split[2]));
            });

            return graph;

        } catch (Exception e) {

            throw new GraphRepositoryException(e);
        }
    }
}
