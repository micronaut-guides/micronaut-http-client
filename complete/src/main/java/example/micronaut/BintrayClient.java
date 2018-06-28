package example.micronaut;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.Client;
import io.reactivex.Maybe;

import java.util.List;

@Client(BintrayConfiguration.BINTRAY_API_URL) // <1>
public interface BintrayClient {

    @Get("/api/${bintray.apiversion}/repos/${bintray.organization}/${bintray.repository}/packages")  // <2>
    Maybe<List<BintrayPackage>> fetchPackages(); // <3>
}
