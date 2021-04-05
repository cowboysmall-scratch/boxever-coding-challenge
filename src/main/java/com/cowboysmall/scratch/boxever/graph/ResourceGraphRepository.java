package com.cowboysmall.scratch.boxever.graph;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

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

            graph.addEdges(
                    reader.lines()
                            .map(line -> line.split(" "))
                            .map(split ->
                                    Edge.<String, Long>builder()
                                            .source(split[0])
                                            .destination(split[1])
                                            .weight(parseLong(split[2]))
                                            .build()
                            )
                            .collect(Collectors.toList())
            );

            return graph;

        } catch (Exception e) {

            throw new GraphRepositoryException(e);
        }
    }
}
