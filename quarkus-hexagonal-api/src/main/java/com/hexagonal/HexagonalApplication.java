package com.hexagonal;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Quarkus Hexagonal",
                version = "0.0.1")
)
public class HexagonalApplication extends Application {
}
