package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
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

    @Get("/packageswithlowlevelhttpclient") // <3>
    Flowable<HttpResponse<List<BintrayPackage>>> packagesWithLowLevelClient() { // <4>
        return bintrayLowLevelClient.fetchPackages();
    }

    @Get("/packages")  // <3>
    Flowable<HttpResponse<List<BintrayPackage>>> packages() { // <4>
        return bintrayClient.fetchPackages();
    }
}
