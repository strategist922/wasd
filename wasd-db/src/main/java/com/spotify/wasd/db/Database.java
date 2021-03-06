package com.spotify.wasd.db;

import com.typesafe.config.Config;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
public class Database {
    @Getter
    private final Sites sites;
    @Getter
    private final Hosts hosts;
    @Getter
    private final Services services;
    @Getter
    private final Records records;

    Database(Config config) throws IOException, ExecutionException {
        sites = new Sites(config.getConfig("Sites"));
        hosts = new Hosts();
        records = new Records(sites, hosts);
        services = new Services(config.getConfig("Services"), records, hosts);
    }
}
