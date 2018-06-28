package example.micronaut;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.*;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Maybe;

import javax.inject.Singleton;
import java.util.List;

@Singleton // <1>
public class BintrayLowLevelClient {


    private final RxHttpClient httpClient;
    private final BintrayConfiguration configuration;

    public BintrayLowLevelClient(@Client(BintrayConfiguration.BINTRAY_API_URL) RxHttpClient httpClient,  // <2>
                                 BintrayConfiguration configuration) {  // <3>
        this.httpClient = httpClient;
        this.configuration = configuration;
    }

    Maybe<List<BintrayPackage>> fetchPackages() {
        String uri = "/api/{apiversion}/repos/{organization}/{repository}/packages";
        HttpRequest req = HttpRequest.GET(UriTemplate.of(uri).expand(configuration.toMap()));  // <4>
        return (Maybe<List<BintrayPackage>>) httpClient.retrieve(req, Argument.of(List.class, BintrayPackage.class)).firstElement();  // <5>
    }

}
