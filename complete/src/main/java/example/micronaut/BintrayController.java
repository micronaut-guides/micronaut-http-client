package example.micronaut;

import io.micronaut.http.annotation.*;
import io.reactivex.Maybe;

import java.util.List;

@Controller("/bintray") // <1>
public class BintrayController {

    private final BintrayLowLevelClient bintrayLowLevelClient;

    private final BintrayClient bintrayClient;

    public BintrayController(BintrayLowLevelClient bintrayLowLevelClient, // <2>
                             BintrayClient bintrayClient) {
        this.bintrayLowLevelClient = bintrayLowLevelClient;
        this.bintrayClient = bintrayClient;
    }

    @Get("/packages-lowlevel") // <3>
    Maybe<List<BintrayPackage>> packagesWithLowLevelClient() { // <4>
        return bintrayLowLevelClient.fetchPackages();
    }

    @Get("/packages")  // <3>
    Maybe<List<BintrayPackage>> packages() { // <4>
        return bintrayClient.fetchPackages();
    }
}
