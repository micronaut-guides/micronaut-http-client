package example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.Client;
import io.micronaut.http.client.RxStreamingHttpClient;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Flowable;

import javax.inject.Singleton;

@Singleton // <1>
public class BintrayLowLevelClient {


    private final RxStreamingHttpClient httpClient;
    private final BintrayConfiguration configuration;

    public BintrayLowLevelClient(@Client(BintrayConfiguration.BINTRAY_API_URL) RxStreamingHttpClient httpClient,  // <2>
                                 BintrayConfiguration configuration) {  // <3>
        this.httpClient = httpClient;
        this.configuration = configuration;
    }

    Flowable<BintrayPackage> fetchPackages() {
        String path = "/api/{apiversion}/repos/{organization}/{repository}/packages";
        String uri = UriTemplate.of(path).expand(configuration);
        HttpRequest<?> req = HttpRequest.GET(uri);  // <4>
        return httpClient.jsonStream(req, BintrayPackage.class);
    }

}
