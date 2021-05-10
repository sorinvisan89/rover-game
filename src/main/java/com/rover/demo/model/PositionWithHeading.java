package com.rover.demo.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PositionWithHeading {

    private Heading heading;
    private Position position;
}
