package org.davelogapps.cineconcertmanagerv2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Video {
    private String path;
    private int duration;
    private boolean isMuted;
    private boolean hasPromoImage;
}
