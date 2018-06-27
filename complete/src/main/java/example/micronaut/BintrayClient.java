package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.Client;
import io.reactivex.Flowable;
import java.util.List;

@Client(BintrayApi.BINTRAY_API_URL) // <1>
public interface BintrayClient {

    @Get("/api/${bintray.apiversion}/repos/${bintray.organization}/${bintray.repository}/packages")  // <2>
    Flowable<HttpResponse<List<BintrayPackage>>> fetchPackages(); // <3>
}
