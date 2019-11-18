package example.micronaut;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

import javax.inject.Singleton;
import java.util.List;

@Singleton // <1>
public class BintrayLowLevelClient {

    private final RxHttpClient httpClient;
    private final String uri;

    public BintrayLowLevelClient(@Client(BintrayConfiguration.BINTRAY_API_URL) RxHttpClient httpClient,  // <2>
                                 BintrayConfiguration configuration) {  // <3>
        this.httpClient = httpClient;
        String path = "/api/{apiversion}/repos/{organization}/{repository}/packages";
        uri = UriTemplate.of(path).expand(configuration.toMap());
    }

    Maybe<List<BintrayPackage>> fetchPackages() {
        HttpRequest<?> req = HttpRequest.GET(uri);  // <4>
        Flowable flowable = httpClient.retrieve(req, Argument.of(List.class, BintrayPackage.class)); // <5>
        return (Maybe<List<BintrayPackage>>) flowable.firstElement(); // <6>
    }

}
