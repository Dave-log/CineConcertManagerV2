package org.davelogapps.cineconcertmanagerv2.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Sequence {
    private String name;
    private List<Video> videos;

}
