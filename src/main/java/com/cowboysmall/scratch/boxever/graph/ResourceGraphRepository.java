package com.cowboysmall.scratch.boxever.graph;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

@Component
public class ResourceGraphRepository implements GraphRepository<Graph<String>> {

    @Value("classpath:graph.txt")
    private Resource graphResource;

    @Override
    public Graph<String> createGraph(Boolean directed) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(graphResource.getInputStream()))) {

            Graph<String> graph = new Graph<>(directed);

            reader.lines().forEach(line -> {

                String[] split = line.split(" ");
                graph.addEdge(split[0], split[1], parseInt(split[2]));
            });

            return graph;

        } catch (Exception e) {

            throw new GraphRepositoryException(e);
        }
    }
}
